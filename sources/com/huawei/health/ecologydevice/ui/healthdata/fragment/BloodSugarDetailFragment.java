package com.huawei.health.ecologydevice.ui.healthdata.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarDetailFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriod;
import com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView;

/* loaded from: classes8.dex */
public class BloodSugarDetailFragment extends BloodSugarBaseFragment {
    private BloodSugarTimePeriodView d;

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected void initView(View view) {
        super.initView(view);
        View inflate = LayoutInflater.from(this.mActivity).inflate(R.layout.item_blood_sugar_detail, (ViewGroup) null);
        if (inflate == null) {
            LogUtil.h("BloodSugarDetailFragment", "viewChild is null");
            return;
        }
        this.mContainerLayout.addView(inflate);
        BloodSugarTimePeriodView bloodSugarTimePeriodView = (BloodSugarTimePeriodView) inflate.findViewById(R.id.blood_sugar_time_period);
        this.d = bloodSugarTimePeriodView;
        bloodSugarTimePeriodView.setOnTimePeriodItemChangedListener(new BloodSugarTimePeriodView.OnTimePeriodItemChangedListener() { // from class: dfv
            @Override // com.huawei.ui.commonui.linechart.view.timeperiod.BloodSugarTimePeriodView.OnTimePeriodItemChangedListener
            public final void onTimePeriodItemChanged(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
                BloodSugarDetailFragment.this.a(i, bloodSugarTimePeriod);
            }
        });
        this.mButtonConfirm.setText(R.string._2130845895_res_0x7f0220c7);
        this.mButtonConfirm.setOnClickListener(new View.OnClickListener() { // from class: dfs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                BloodSugarDetailFragment.this.Uk_(view2);
            }
        });
    }

    public /* synthetic */ void a(int i, BloodSugarTimePeriod bloodSugarTimePeriod) {
        this.mTimePeriod = bloodSugarTimePeriod.getCode();
        LogUtil.c("BloodSugarDetailFragment", "initView, setOnTimePeriodItemChangedListener mTimePeriod = ", Integer.valueOf(this.mTimePeriod));
        updateDashboardRingViewRange();
    }

    public /* synthetic */ void Uk_(View view) {
        this.mActivity.c(this.mTimePeriod);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected HiHealthData getHealthData() {
        return this.mActivity.c();
    }

    @Override // com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment
    protected void updateView() {
        super.updateView();
        updateTopDateView(false);
        this.d.d(this.mTime, this.mTimePeriod);
    }
}
