package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.unite.GetRunLevelRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.stories.health.interactors.healthdata.RunningStateIndexData;
import defpackage.ggl;
import defpackage.hqa;
import defpackage.jdx;
import defpackage.nmm;
import defpackage.ruf;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public class StateDayDetailFragment extends BaseStateIndexDetailFragment {
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private String f10178a;
    private Handler b = new Handler() { // from class: com.huawei.ui.main.stories.health.fragment.StateDayDetailFragment.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof RunningStateIndexData) {
                    StateDayDetailFragment.this.c((RunningStateIndexData) message.obj);
                    return;
                }
                return;
            }
            if (i == 2) {
                StateDayDetailFragment.this.a();
            } else if (i == 3 && StateDayDetailFragment.this.mIsLoadingState && StateDayDetailFragment.this.mProgressBar != null) {
                StateDayDetailFragment.this.mProgressBar.setVisibility(0);
            }
        }
    };
    private int d;
    private int e;
    private float j;

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        b();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected void initView() {
        super.initView();
        this.mFitnessIndexText = (HealthTextView) this.mView.findViewById(R.id.fitness_index_num);
        this.mFatigueIndexText = (HealthTextView) this.mView.findViewById(R.id.fatigue_index_num);
        this.mCurrentLevelText = (HealthTextView) this.mView.findViewById(R.id.current_fragment_bottom_text);
        this.mProgressBar = (HealthProgressBar) this.mView.findViewById(R.id.loading_iv);
        this.mHealthLevelIndicator.setVisibility(0);
        this.mCurrentRawLayout.setVisibility(0);
        this.mPolyLineDetailLayout.setVisibility(8);
    }

    private int[] c(int i) {
        int[] iArr = new int[i];
        int i2 = (int) (270.0f / i);
        for (int i3 = 0; i3 < i; i3++) {
            iArr[i3] = i2;
        }
        return iArr;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected void initFragmentView(View view, int i) {
        this.mCurrentFragmentTopText = (HealthTextView) view.findViewById(R.id.current_fragment_top_text);
        this.mHealthRecyclerView.setVisibility(8);
        this.mDayViewLinearLayout.setVisibility(0);
        this.mStateIndexDayTextView.setVisibility(0);
        List<nmm> c2 = ruf.c();
        this.mHealthLevelIndicator.setAllLevelsData(c2, c(c2.size()));
        if (this.mRunningStateIndexData != null && this.mRunningStateIndexData.getRunningLevelCurrentData() != null) {
            this.mCurrentFragmentTopText.setText(DateUtils.formatDateTime(this.mContext, ggl.e(this.mRunningStateIndexData.getRunningLevelCurrentData().getLastRecordDay()), 4));
            float lastCondition = this.mRunningStateIndexData.getRunningLevelCurrentData().getLastCondition();
            this.j = lastCondition;
            this.mFitnessIndexText.setText(UnitUtil.e(this.mRunningStateIndexData.getRunningLevelCurrentData().getLastFitness(), 1, 1));
            this.mFatigueIndexText.setText(UnitUtil.e(this.mRunningStateIndexData.getRunningLevelCurrentData().getLastFatigue(), 1, 1));
            this.mHealthLevelIndicator.setLevel(lastCondition, ruf.c(lastCondition));
            this.f10178a = ruf.h(lastCondition);
            this.mCurrentLevelText.setText(this.f10178a);
        } else {
            this.j = -20.0f;
            this.mHealthLevelIndicator.setLevel(Float.NaN, "--");
            this.mFitnessIndexText.setText("--");
            this.mFatigueIndexText.setText("--");
            this.mCurrentLevelText.setVisibility(8);
        }
        if (this.j == -20.0f) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.health.fragment.StateDayDetailFragment.2
                @Override // java.lang.Runnable
                public void run() {
                    StateDayDetailFragment.this.e();
                }
            });
        }
    }

    private void b() {
        int a2 = ggl.a(new Date(System.currentTimeMillis()));
        this.e = a2;
        this.d = a2;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseStateIndexDetailFragment
    protected DataInfos getDataInfo() {
        return DataInfos.StateIndexWeekDetail;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        new hqa().a(getContext(), this.e, this.d, 0, new a(this));
    }

    static class a implements IBaseResponseCallback {
        WeakReference<StateDayDetailFragment> b;

        a(StateDayDetailFragment stateDayDetailFragment) {
            this.b = new WeakReference<>(stateDayDetailFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            StateDayDetailFragment stateDayDetailFragment = this.b.get();
            if (stateDayDetailFragment == null) {
                LogUtil.h("Track_StateDayDetailFragment", "fragment is null");
                return;
            }
            if (i == 200) {
                synchronized (StateDayDetailFragment.c) {
                    if (!(obj instanceof GetRunLevelRsp)) {
                        stateDayDetailFragment.b.removeMessages(2);
                    } else {
                        RunningStateIndexData c = ruf.c((GetRunLevelRsp) obj);
                        Message obtain = Message.obtain();
                        obtain.obj = c;
                        obtain.what = 1;
                        stateDayDetailFragment.b.sendMessage(obtain);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.mProgressBar.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RunningStateIndexData runningStateIndexData) {
        a();
        if (runningStateIndexData == null || runningStateIndexData.getRunningLevelCurrentData() == null) {
            return;
        }
        float lastCondition = runningStateIndexData.getRunningLevelCurrentData().getLastCondition();
        this.mHealthLevelIndicator.setLevel(lastCondition, ruf.c(lastCondition));
        this.mFitnessIndexText.setText(UnitUtil.e(runningStateIndexData.getRunningLevelCurrentData().getLastFitness(), 1, 1));
        this.mFatigueIndexText.setText(UnitUtil.e(runningStateIndexData.getRunningLevelCurrentData().getLastFatigue(), 1, 1));
        this.f10178a = ruf.h(lastCondition);
        this.mCurrentLevelText.setText(this.f10178a);
    }
}
