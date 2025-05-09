package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
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
import com.huawei.ui.main.R$string;
import defpackage.ggl;
import defpackage.hqa;
import defpackage.ruf;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes6.dex */
public class RunningDayDetailFragment extends BaseRunningDetailFragment {
    private static final Object c = new Object();
    private static final int[] d = {63, 48, 60, 36, 24, 24, 15};

    /* renamed from: a, reason: collision with root package name */
    private int f10176a;
    private float b;
    private int e;

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        isCurrentFragment(true);
        b();
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    protected void initFragmentView() {
        String str;
        this.mCurrentFragmentTopText = (HealthTextView) this.mView.findViewById(R.id.current_fragment_top_text);
        this.mCurrentFragmentTopText.setText(DateUtils.formatDateTime(this.mContext, ggl.e(this.mRunningStateIndexData.getRunningLevelCurrentData().getLastRecordDay()), 4));
        this.mRateCurrentText = (HealthTextView) this.mView.findViewById(R.id.current_fragment_bottom_text);
        this.mHealthLevelIndicator.setVisibility(0);
        d();
        float lastCurrentRunLevel = this.mRunningStateIndexData.getRunningLevelCurrentData().getLastCurrentRunLevel();
        if (lastCurrentRunLevel > 0.0f) {
            str = ruf.a(lastCurrentRunLevel);
        } else {
            str = "--";
            lastCurrentRunLevel = Float.NaN;
        }
        float f = this.b;
        if (lastCurrentRunLevel > f) {
            lastCurrentRunLevel = f - 1.0f;
        }
        this.mHealthLevelIndicator.setLevel(lastCurrentRunLevel, str);
    }

    private void d() {
        if (this.mGender == 0) {
            this.mHealthLevelIndicator.setAllLevelsData(ruf.e(), d);
            this.b = 90.0f;
        } else {
            this.mHealthLevelIndicator.setAllLevelsData(ruf.b(), d);
            this.b = 90.0f;
        }
        if (this.mContext == null || this.mContext.getResources() == null) {
            return;
        }
        float lastRanking = this.mRunningStateIndexData.getRunningLevelCurrentData().getLastRanking();
        if (lastRanking <= 0.0f) {
            this.mRateCurrentText.setVisibility(8);
            return;
        }
        double d2 = lastRanking;
        String e = UnitUtil.e(d2, 2, 1);
        if (LanguageUtil.bp(this.mContext)) {
            e = "%" + UnitUtil.e(d2, 1, 1);
        }
        if (this.mGender != 0 && this.mGender != 1) {
            this.mRateCurrentText.setText(this.mContext.getResources().getString(R$string.IDS_data_rate_current_txt, e));
        } else if (this.mGender == 0) {
            this.mRateCurrentText.setText(this.mContext.getResources().getString(R$string.IDS_data_rate_current_male_txt, e));
        } else {
            this.mRateCurrentText.setText(this.mContext.getResources().getString(R$string.IDS_data_rate_current_female_txt, e));
        }
    }

    private void b() {
        int h = CommonUtil.h(new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis())));
        this.f10176a = h;
        this.e = h;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    public void requestData() {
        if (this.mRunningStateIndexData.getRunningLevelCurrentData().getLastCurrentRunLevel() < 0.0f) {
            a();
        }
    }

    private void a() {
        b bVar = new b(this);
        this.mRequestHandler.sendEmptyMessage(3);
        new hqa().a(getContext(), this.f10176a, this.e, 0, bVar);
    }

    static class b implements IBaseResponseCallback {
        WeakReference<RunningDayDetailFragment> e;

        b(RunningDayDetailFragment runningDayDetailFragment) {
            this.e = new WeakReference<>(runningDayDetailFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            RunningDayDetailFragment runningDayDetailFragment = this.e.get();
            if (runningDayDetailFragment == null) {
                LogUtil.h("RunningDayDetailFragment", "fragment is null");
                return;
            }
            if (i == 200) {
                synchronized (RunningDayDetailFragment.c) {
                    if (obj instanceof GetRunLevelRsp) {
                        Message obtainMessage = runningDayDetailFragment.mRequestHandler.obtainMessage(1);
                        obtainMessage.obj = obj;
                        runningDayDetailFragment.mRequestHandler.sendMessage(obtainMessage);
                    } else {
                        runningDayDetailFragment.mRequestHandler.sendEmptyMessage(2);
                    }
                }
            }
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    public void updateLayout(GetRunLevelRsp getRunLevelRsp) {
        updateBar();
        d();
        if (getRunLevelRsp == null || getRunLevelRsp.getUserRunLevelData() == null) {
            return;
        }
        float lastCurrentRunLevel = getRunLevelRsp.getUserRunLevelData().getLastCurrentRunLevel();
        String a2 = ruf.a(lastCurrentRunLevel);
        float f = this.b;
        if (lastCurrentRunLevel > f) {
            lastCurrentRunLevel = f - 1.0f;
        }
        this.mHealthLevelIndicator.setLevel(lastCurrentRunLevel, a2);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    protected void updateBar() {
        this.mProgressBar.setVisibility(8);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    public DataInfos getDataInfo() {
        return DataInfos.RunningWeekDetail;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseRunningDetailFragment
    public void updateGenderView(int i) {
        if (this.mGender != i) {
            this.mGender = i;
            a();
        }
    }
}
