package com.huawei.health.ecologydevice.ui.healthdata.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.view.dashboard.BloodSugarDashboardView;
import com.huawei.ui.commonui.linechart.view.dashboard.DashboardRingView;
import defpackage.deb;
import defpackage.nsj;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class BloodSugarBaseFragment extends BaseFragment {
    private static final int BLOOD_SUGAR_VALUE_MAX = 33;
    private static final int BLOOD_SUGAR_VALUE_MIN = 1;
    private static final int COLOR_PART_SIZE = 2;
    private static final String TAG = "BloodSugarBaseFragment";
    protected BloodSugarHistoryActivity mActivity;
    protected HiHealthData mBloodSugar;
    protected HealthButton mButtonConfirm;
    protected FrameLayout mContainerLayout;
    protected BloodSugarDashboardView mDashboardView;
    protected long mTime;
    protected int mTimePeriod;
    protected HealthTextView mTopDateView;
    protected HealthTextView mTopTimePeriodView;
    protected HealthTextView mTopTimeView;
    protected float mValue;

    protected abstract HiHealthData getHealthData();

    protected void initData() {
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentActivity activity = getActivity();
        if (activity instanceof BloodSugarHistoryActivity) {
            this.mActivity = (BloodSugarHistoryActivity) activity;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_blood_sugar_base, (ViewGroup) null, false);
        if (inflate != null) {
            initView(inflate);
            initData();
            updateView();
        } else {
            LogUtil.h(TAG, "onCreateView, view is null");
        }
        return inflate;
    }

    protected void initView(View view) {
        this.mTopDateView = (HealthTextView) view.findViewById(R.id.blood_sugar_top_date);
        this.mTopTimeView = (HealthTextView) view.findViewById(R.id.blood_sugar_top_time);
        this.mTopTimePeriodView = (HealthTextView) view.findViewById(R.id.blood_sugar_top_time_period);
        this.mDashboardView = (BloodSugarDashboardView) view.findViewById(R.id.blood_sugar_dashboard_view);
        this.mContainerLayout = (FrameLayout) view.findViewById(R.id.blood_sugar_fl_container);
        this.mButtonConfirm = (HealthButton) view.findViewById(R.id.blood_sugar_bt_done);
    }

    protected void updateData() {
        HiHealthData healthData = getHealthData();
        this.mBloodSugar = healthData;
        if (healthData == null) {
            LogUtil.h(TAG, "updateData BloodSugar data is null");
            return;
        }
        this.mTime = healthData.getEndTime();
        this.mTimePeriod = BigDecimal.valueOf(this.mBloodSugar.getType()).intValue();
        this.mValue = BigDecimal.valueOf(this.mBloodSugar.getValue()).floatValue();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (z) {
            return;
        }
        updateView();
    }

    protected void updateView() {
        LogUtil.a(TAG, "updateView");
        updateData();
        updateDashboardRingViewRange();
    }

    protected void updateTopDateView(boolean z) {
        this.mTopDateView.setText(nsj.g(this.mTime));
        this.mTopTimeView.setText(nsj.i(this.mTime));
        if (z) {
            String a2 = deb.a(this.mTimePeriod);
            this.mTopTimePeriodView.setVisibility(0);
            this.mTopTimePeriodView.setText(a2);
            return;
        }
        this.mTopTimePeriodView.setVisibility(8);
    }

    protected void updateDashboardRingViewRange() {
        ArrayList arrayList = new ArrayList();
        List<Float> c = deb.c(this.mTimePeriod);
        int i = 0;
        int i2 = 0;
        while (i2 < c.size() && this.mValue > c.get(i2).floatValue()) {
            i2++;
        }
        int[] e = deb.e();
        int length = (e.length / 2) + i2;
        float f = 0.0f;
        while (i < c.size()) {
            float floatValue = c.get(i).floatValue();
            int i3 = i == i2 ? length : i;
            if (i3 < e.length) {
                arrayList.add(new DashboardRingView.b(f, floatValue, e[i3]));
                f = floatValue;
            }
            i++;
        }
        this.mDashboardView.setRingAreas(1.0f, 33.0f, arrayList);
        int c2 = deb.c(getContext(), this.mTimePeriod, this.mValue);
        if (length < e.length) {
            this.mDashboardView.setStatusText(deb.b(c2), e[length]);
        } else {
            this.mDashboardView.setStatusText(getString(R.string._2130849885_res_0x7f02305d), ContextCompat.getColor(BaseApplication.e(), R.color._2131299241_res_0x7f090ba9));
        }
        this.mDashboardView.setCurrentValue(this.mValue);
        this.mDashboardView.c();
    }
}
