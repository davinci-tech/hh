package com.huawei.ui.main.stories.health.fragment;

import android.os.Bundle;
import android.os.Message;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.indicator.HealthLevelIndicator;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.main.R$string;
import defpackage.nmm;
import defpackage.qrv;
import defpackage.ruf;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes6.dex */
public class Vo2maxDayDetailFragment extends BaseVo2maxDetailFragment {

    /* renamed from: a, reason: collision with root package name */
    private int f10179a;
    private String d;

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        isCurrentFragment(true);
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    private int[] a(Integer[] numArr) {
        if (numArr != null) {
            if (numArr.length > 1) {
                int length = numArr.length - 1;
                int[] iArr = new int[length];
                int i = 0;
                for (int i2 = 1; i2 < numArr.length; i2++) {
                    i += numArr[i2].intValue() - numArr[i2 - 1].intValue();
                }
                for (int i3 = 0; i3 < length; i3++) {
                    iArr[i3] = (int) (((numArr[r2].intValue() - numArr[i3].intValue()) * 270.0f) / i);
                }
                return iArr;
            }
        }
        return new int[]{(int) 270.0f};
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    protected void initFragmentView() {
        if (this.mView == null || this.mContext == null) {
            return;
        }
        if (this.mHealthLevelIndicator == null) {
            this.mHealthLevelIndicator = (HealthLevelIndicator) this.mView.findViewById(R.id.current_state_level_indicator);
        }
        List<nmm> c2 = ruf.c(this.mOxRange);
        int length = this.mOxRange.length - 2;
        Integer[] numArr = new Integer[length];
        System.arraycopy(this.mOxRange, 0, numArr, 0, length);
        this.mHealthLevelIndicator.setAllLevelsData(c2, a(numArr));
        if (this.mVo2maxValue <= 0) {
            this.mHealthLevelIndicator.setLevel(Float.NaN, "--");
            this.mDayViewData.setText("--");
        } else {
            a(this.mVo2maxValue);
            this.mDayViewData.setText(DateUtils.formatDateTime(this.mContext, this.mVo2maxTime, 4));
        }
        this.mHealthLevelIndicator.setLevelUnit(this.mContext.getString(R$string.IDS_hwh_health_vo2max_unit));
        int b = qrv.b(this.mVo2maxValue, this.mOxRange);
        this.f10179a = b;
        this.d = a(b);
        e(this.mGender);
    }

    private void a(float f) {
        this.mHealthLevelIndicator.setLevel(f, UnitUtil.e(f, 1, 0));
    }

    private void e(int i) {
        if (i == 0) {
            this.mCurrentRanking.setText(qrv.e(this.f10179a));
            return;
        }
        if (i == 1) {
            this.mCurrentRanking.setText(qrv.b(this.f10179a));
        } else {
            if (this.mContext == null || this.mContext.getResources() == null) {
                return;
            }
            this.mCurrentRanking.setText(this.mContext.getResources().getString(R$string.IDS_motiontrack_detail_ox_level, this.d));
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    protected DataInfos getDataInfo() {
        return DataInfos.Vo2maxWeekDetail;
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    public void requestData() {
        if (this.mVo2maxValue <= 0) {
            this.mIsLoadingState = true;
            this.mRequestHandler.sendEmptyMessage(3);
            this.mDataManager.e(new c(this));
        }
    }

    private String a(int i) {
        int i2;
        switch (i) {
            case 0:
                i2 = R$string.IDS_hwh_health_vo2max_level_verypoor;
                break;
            case 1:
                i2 = R$string.IDS_hwh_health_vo2max_level_poor;
                break;
            case 2:
                i2 = R$string.IDS_hwh_health_vo2max_level_fair;
                break;
            case 3:
                i2 = R$string.IDS_hwh_health_vo2max_level_average;
                break;
            case 4:
                i2 = R$string.IDS_hwh_health_vo2max_level_good;
                break;
            case 5:
                i2 = R$string.IDS_hwh_health_vo2max_level_verygood;
                break;
            case 6:
                i2 = R$string.IDS_hwh_health_vo2max_level_excellent;
                break;
            default:
                i2 = R$string.IDS_hwh_health_vo2max_level_average;
                break;
        }
        return this.mContext.getResources().getString(i2);
    }

    static class c implements IBaseResponseCallback {
        WeakReference<Vo2maxDayDetailFragment> d;

        c(Vo2maxDayDetailFragment vo2maxDayDetailFragment) {
            this.d = new WeakReference<>(vo2maxDayDetailFragment);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Vo2maxDayDetailFragment vo2maxDayDetailFragment = this.d.get();
            if (vo2maxDayDetailFragment == null) {
                LogUtil.h("Track_Vo2maxDayDetailFragment", "fragment is null");
                return;
            }
            vo2maxDayDetailFragment.mIsLoadingState = false;
            if (i == 0 && obj != null) {
                vo2maxDayDetailFragment.mDataMap = new HashMap<>();
                Message obtain = Message.obtain();
                obtain.obj = obj;
                obtain.what = 1;
                vo2maxDayDetailFragment.mRequestHandler.sendMessage(obtain);
                return;
            }
            vo2maxDayDetailFragment.mDataMap = null;
            vo2maxDayDetailFragment.mRequestHandler.sendEmptyMessage(2);
        }
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    public void updateLayout(Vo2maxDetail vo2maxDetail) {
        updateBar();
        this.mVo2maxValue = vo2maxDetail.getVo2maxValue();
        a(this.mVo2maxValue);
        this.mDayViewData.setText(DateUtils.formatDateTime(this.mContext, vo2maxDetail.getTimestamp(), 4));
        int b = qrv.b(this.mVo2maxValue, this.mOxRange);
        this.f10179a = b;
        this.d = a(b);
        e(this.mGender);
    }

    @Override // com.huawei.ui.main.stories.health.fragment.BaseVo2maxDetailFragment
    protected void updateBar() {
        this.mProgressBar.setVisibility(8);
    }

    public void d(int i) {
        this.mGender = i;
        this.mOxRange = qrv.a(this.mGender, this.mAge);
        initFragmentView();
    }
}
