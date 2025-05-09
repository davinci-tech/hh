package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.google.gson.Gson;
import com.google.json.JsonSanitizer;
import com.huawei.health.R;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.ecologydevice.fitness.SkipperTargetSettingActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dij;
import defpackage.diy;
import defpackage.gnm;
import defpackage.nsj;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;

/* loaded from: classes3.dex */
public class RopeDeviceIntroductionFragment extends BaseRopeIntroductionFragment implements View.OnClickListener, ResourceFileListener {
    private static final String TAG = "PDROPE_DeviceIntroduction";
    private LinearLayout mDeviceInfoLayout;
    private HealthTextView mDeviceInfoTv;
    private HealthTextView mLastCaloriesTv;
    private HealthTextView mLastCaloriesUnitsTv;
    private HealthTextView mLastDurationTv;
    private HealthTextView mLastDurationUnitsTv;
    private ImageView mLastJumpRight;
    private HealthTextView mLastTimeTv;
    private HealthTextView mLastValueTv;
    private HealthTextView mLastValueUnitsTv;
    private LinearLayout mModeCountLayout;
    private HealthTextView mModeCountTv;
    private LinearLayout mModeFreeLayout;
    private HealthTextView mModeFreeTv;
    private LinearLayout mModeTimeLayout;
    private HealthTextView mModeTimeTv;
    private ImageView mOtherDeviceRight;

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected int getLayoutId() {
        return R.layout.rope_device_introduction_layout;
    }

    @Override // com.huawei.health.ecologydevice.callback.RopeDeviceDataListener
    public void onRopeSportStatusChange(int i) {
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void initChildView() {
        if (this.child == null) {
            LogUtil.h(TAG, "initChildView child is null");
            return;
        }
        this.mRopeHistoryLayout = (ViewGroup) this.child.findViewById(R.id.rope_device_go_details);
        this.mRopeHistoryLayout.setOnClickListener(this);
        this.mLastTimeTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_time);
        this.mLastValueTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_value);
        this.mLastValueUnitsTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_value_units);
        this.mLastDurationTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_duration);
        this.mLastDurationUnitsTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_duration_units);
        this.mLastCaloriesTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_calories);
        this.mLastCaloriesUnitsTv = (HealthTextView) this.child.findViewById(R.id.rope_device_last_calories_units);
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.rope_device_mode_free_layout);
        this.mModeFreeLayout = linearLayout;
        linearLayout.setOnClickListener(this);
        this.mModeFreeTv = (HealthTextView) this.child.findViewById(R.id.rope_device_mode_free);
        LinearLayout linearLayout2 = (LinearLayout) this.child.findViewById(R.id.rope_device_mode_count_layout);
        this.mModeCountLayout = linearLayout2;
        linearLayout2.setOnClickListener(this);
        this.mModeCountTv = (HealthTextView) this.child.findViewById(R.id.rope_device_mode_count);
        LinearLayout linearLayout3 = (LinearLayout) this.child.findViewById(R.id.rope_device_mode_time_layout);
        this.mModeTimeLayout = linearLayout3;
        linearLayout3.setOnClickListener(this);
        this.mModeTimeTv = (HealthTextView) this.child.findViewById(R.id.rope_device_mode_time);
        LinearLayout linearLayout4 = (LinearLayout) this.child.findViewById(R.id.rope_device_info_layout);
        this.mDeviceInfoLayout = linearLayout4;
        linearLayout4.setOnClickListener(this);
        this.mDeviceInfoTv = (HealthTextView) this.child.findViewById(R.id.rope_device_info_tv);
        this.mLastJumpRight = (ImageView) this.child.findViewById(R.id.rope_device_last_jump_icon_right);
        this.mOtherDeviceRight = (ImageView) this.child.findViewById(R.id.rope_device_other_icon_right);
        fontAdaptation();
        initRtLanguageView();
        initMoreMenuData();
    }

    private void initMoreMenuData() {
        if ("yes".equals(this.mProductInfo.c("ota"))) {
            this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        }
        this.mMoreMenuList.add(getResources().getString(R.string.IDS_device_wear_home_delete_device));
        setOtaRedPoint(true);
    }

    private void initRtLanguageView() {
        if (LanguageUtil.bc(getActivity())) {
            this.mLastJumpRight.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            this.mOtherDeviceRight.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
        }
    }

    private void fontAdaptation() {
        if (nsn.p()) {
            int dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen._2131362869_res_0x7f0a0435);
            this.mModeFreeLayout.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
            this.mModeCountLayout.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
            this.mModeTimeLayout.setPadding(0, dimensionPixelSize, 0, dimensionPixelSize);
            return;
        }
        int dimensionPixelSize2 = getContext().getResources().getDimensionPixelSize(R.dimen._2131362914_res_0x7f0a0462);
        this.mModeFreeLayout.setPadding(0, dimensionPixelSize2, 0, dimensionPixelSize2);
        this.mModeCountLayout.setPadding(0, dimensionPixelSize2, 0, dimensionPixelSize2);
        this.mModeTimeLayout.setPadding(0, dimensionPixelSize2, 0, dimensionPixelSize2);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (getActivity() == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (nsn.o()) {
            LogUtil.a(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view == this.mModeFreeLayout) {
            LogUtil.c(TAG, "onClick mode free");
            goSkipperTargetSettingActivity(6);
        } else if (view == this.mModeCountLayout) {
            LogUtil.c(TAG, "onClick mode count");
            goSkipperTargetSettingActivity(5);
        } else if (view == this.mModeTimeLayout) {
            LogUtil.c(TAG, "onClick mode time");
            goSkipperTargetSettingActivity(0);
        } else if (view == this.mDeviceInfoLayout || view == this.mDeviceInfoTv) {
            LogUtil.c(TAG, "onClick device info");
            if (this.mDeviceInformation != null) {
                goToDeviceInfoFragment();
            }
        } else if (view == this.mRopeHistoryLayout) {
            LogUtil.c(TAG, "onClick start history activity");
            diy.d(this.mainActivity, 283);
        } else {
            LogUtil.c(TAG, "onClick unknown view");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void goSkipperTargetSettingActivity(int i) {
        saveDeviceName();
        Intent intent = new Intent(getActivity(), (Class<?>) SkipperTargetSettingActivity.class);
        intent.putExtra("currentSkipperTarget", i);
        gnm.aPB_(getActivity(), intent);
    }

    private void saveDeviceName() {
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0).edit();
        if (edit != null) {
            edit.putString("device_name_key", this.mTitle);
            edit.apply();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void onDeviceStateChanged() {
        super.onDeviceStateChanged();
        if (checkMacAddress()) {
            return;
        }
        setViewClickable(this.mRopeDeviceDataManager.e() == 2);
    }

    private void setViewClickable(boolean z) {
        if (getContext() == null) {
            return;
        }
        this.mModeFreeLayout.setClickable(z);
        HealthTextView healthTextView = this.mModeFreeTv;
        Resources resources = getResources();
        int i = R.color._2131299241_res_0x7f090ba9;
        int i2 = R.color._2131299244_res_0x7f090bac;
        healthTextView.setTextColor(resources.getColor(z ? R.color._2131299241_res_0x7f090ba9 : R.color._2131299244_res_0x7f090bac));
        this.mModeCountLayout.setClickable(z);
        this.mModeCountTv.setTextColor(getResources().getColor(z ? R.color._2131299241_res_0x7f090ba9 : R.color._2131299244_res_0x7f090bac));
        this.mModeTimeLayout.setClickable(z);
        HealthTextView healthTextView2 = this.mModeTimeTv;
        Resources resources2 = getResources();
        if (!z) {
            i = R.color._2131299244_res_0x7f090bac;
        }
        healthTextView2.setTextColor(resources2.getColor(i));
        this.mDeviceInfoLayout.setClickable(z);
        HealthTextView healthTextView3 = this.mDeviceInfoTv;
        Resources resources3 = getResources();
        if (z) {
            i2 = R.color._2131299236_res_0x7f090ba4;
        }
        healthTextView3.setTextColor(resources3.getColor(i2));
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void initDefaultRopeView() {
        this.mRopeHistoryLayout.setVisibility(8);
        this.mLastTimeTv.setText("- -");
        this.mLastValueTv.setText("- -");
        this.mLastDurationTv.setText("- -");
        this.mLastCaloriesTv.setText("- -");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.BaseRopeIntroductionFragment
    protected void refreshLastRopeDataUi() {
        if (this.mRopeDeviceDataManager == null) {
            LogUtil.h(TAG, "onNewLastRopeData mRopeDeviceDataManager is null");
            return;
        }
        this.mRopeHistoryLayout.setVisibility(0);
        HiHealthData c = this.mRopeDeviceDataManager.c();
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) new Gson().fromJson(JsonSanitizer.sanitize(c.getMetaData()), HiTrackMetaData.class);
        if (hiTrackMetaData != null) {
            int parseLastRopeValue = parseLastRopeValue(hiTrackMetaData);
            long totalTime = hiTrackMetaData.getTotalTime();
            String d = dij.d(hiTrackMetaData.getTotalCalories());
            this.mLastTimeTv.setText(nsj.a(c.getStartTime()));
            this.mLastValueTv.setText(UnitUtil.e(parseLastRopeValue, 1, 0));
            this.mLastDurationTv.setText(dij.b(totalTime));
            this.mLastCaloriesTv.setText(d);
            this.mLastValueUnitsTv.setText(dij.b(getContext(), parseLastRopeValue));
            this.mLastDurationUnitsTv.setText(getContext().getResources().getString(R.string._2130837641_res_0x7f020089, ""));
            this.mLastCaloriesUnitsTv.setText(getContext().getResources().getString(R.string.IDS_plugindameon_hw_show_sport_kcal_string, ""));
        }
    }

    private int parseLastRopeValue(HiTrackMetaData hiTrackMetaData) {
        String str = hiTrackMetaData.getExtendTrackMap().get("skipNum");
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return CommonUtil.h(str);
    }
}
