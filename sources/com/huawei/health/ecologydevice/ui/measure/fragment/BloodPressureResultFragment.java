package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.bzw;
import defpackage.cez;
import defpackage.cpp;
import defpackage.dcz;
import defpackage.ddz;
import defpackage.deo;
import defpackage.dfd;
import defpackage.dij;
import defpackage.dks;
import defpackage.gnm;
import defpackage.ixx;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes3.dex */
public class BloodPressureResultFragment extends BaseFragment {
    private static final String TAG = "BloodPressureResultFragment";
    private View.OnClickListener mDeviceMeasureOnClick = new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureResultFragment$$ExternalSyntheticLambda0
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            BloodPressureResultFragment.this.m287x1e0c4eba(view);
        }
    };
    private String mProductId;
    private String mUniqueId;

    /* renamed from: lambda$new$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureResultFragment, reason: not valid java name */
    /* synthetic */ void m287x1e0c4eba(View view) {
        if (view == null) {
            LogUtil.a(TAG, "BloodPressureResultFragment onClick view is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (R.id.bt_device_measure_result_save == view.getId()) {
            saveBloodPressureData();
        } else if (R.id.bt_device_measure_result_remeasure == view.getId()) {
            onDestroy();
            popupFragment(ProductIntroductionFragment.class);
        } else {
            LogUtil.a(TAG, "BloodPressureResultFragment onClick");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mProductId = getArguments().getString("productId");
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = contentValues.getAsString("uniqueId");
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "BloodPressureResultFragment onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.device_blood_pressure_measure_result, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        Serializable serializable = getArguments().getSerializable("HealthData");
        if (serializable != null) {
            if (serializable instanceof deo) {
                initView((deo) serializable);
            }
        } else {
            LogUtil.h(TAG, "BloodPressureResultFragment onCreateView serializable = null");
        }
        LogUtil.c(TAG, "BloodPressureResultFragment productId is " + this.mProductId);
        if (this.mProductId != null) {
            reportProductInfoToKaka();
        }
        super.setTitle(getArguments().getString("title"));
        return viewGroup2;
    }

    private void reportProductInfoToKaka() {
        dcz d = ResourceManager.e().d(this.mProductId);
        String e = d != null ? dks.e(this.mProductId, d.n().b()) : null;
        String name = d != null ? d.l().name() : null;
        LogUtil.c(TAG, "BloodPressureResultFragment deviceName is " + e);
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, e);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, name);
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(new Date(System.currentTimeMillis())));
        hashMap.put("prodId", dij.e(this.mProductId));
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_MEASURE_SUCCEED_2060012.value(), hashMap, 0);
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE), hashMap);
    }

    private void initView(deo deoVar) {
        LogUtil.a(TAG, "BloodPressureResultFragment initView");
        short systolic = deoVar.getSystolic();
        short diastolic = deoVar.getDiastolic();
        if (this.child == null) {
            LogUtil.b(TAG, "BloodPressureResultFragment showPressureResultView() child == null");
            return;
        }
        ((HealthTextView) this.child.findViewById(R.id.measure_pressure_result_systolic_pressure)).setText(UnitUtil.e(systolic, 1, 0));
        ((HealthTextView) this.child.findViewById(R.id.measure_pressure_result_diastolic_pressure)).setText(UnitUtil.e(diastolic, 1, 0));
        ((HealthTextView) this.child.findViewById(R.id.measure_pressure_result_heart_rate)).setText(UnitUtil.e(deoVar.getHeartRate(), 1, 0));
        ((HealthTextView) this.child.findViewById(R.id.tv_pressure_result_level)).setText(String.format(Locale.ENGLISH, this.mainActivity.getResources().getString(R.string.IDS_device_measureactivity_reference_result), getMeasureResuleValueString(this.mainActivity, systolic, diastolic)));
        HealthButton healthButton = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_save);
        healthButton.setVisibility(0);
        healthButton.setOnClickListener(this.mDeviceMeasureOnClick);
        HealthButton healthButton2 = (HealthButton) this.child.findViewById(R.id.bt_device_measure_result_remeasure);
        healthButton2.setVisibility(0);
        healthButton2.setOnClickListener(this.mDeviceMeasureOnClick);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void saveResultData() {
        super.saveResultData();
        saveBloodPressureData();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void release() {
        super.release();
        onDestroy();
        popupFragment(ProductIntroductionFragment.class);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        return super.showCustomAlertDialog(R.string.IDS_device_cancel_save_data);
    }

    private void saveBloodPressureData() {
        HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        HealthDevice bondedDeviceByUniqueId = healthDeviceEntryApi.getBondedDeviceByUniqueId(this.mUniqueId);
        if (bondedDeviceByUniqueId != null) {
            Serializable serializable = getArguments().getSerializable("HealthData");
            HealthData healthData = serializable instanceof HealthData ? (HealthData) serializable : null;
            dfd dfdVar = new dfd(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
            dfdVar.e(new IBaseResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureResultFragment$$ExternalSyntheticLambda1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    BloodPressureResultFragment.this.m288xb9d1e807(i, obj);
                }
            });
            dfdVar.onDataChanged(bondedDeviceByUniqueId, healthData);
        }
        healthDeviceEntryApi.stopMeasure(this.mProductId, this.mUniqueId);
    }

    /* renamed from: lambda$saveBloodPressureData$1$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureResultFragment, reason: not valid java name */
    /* synthetic */ void m288xb9d1e807(int i, Object obj) {
        LogUtil.a(TAG, "BloodPressureResultFragment saveBloodPressureData() onResponse() err_code:", Integer.valueOf(i));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Intent intent = new Intent();
            intent.setPackage(cez.w);
            intent.putExtra("healthdata", "MyHealthData_jump_mainActivity");
            intent.setClassName(cez.w, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
            intent.setFlags(603979776);
            gnm.aPB_(activity, intent);
            activity.finish();
            return;
        }
        LogUtil.a(TAG, "BloodPressureResultFragment saveBloodPressureData() onResponse() mainActivity == null");
    }

    private static String getMeasureResuleValueString(Context context, int i, int i2) {
        int b = ddz.b((short) i, (short) i2);
        if (b == 0) {
            return context.getResources().getString(R.string.IDS_device_measure_pressure_result_level_low);
        }
        if (b == 1) {
            return context.getResources().getString(R.string.IDS_device_measure_pressure_result_level_normal);
        }
        if (b == 2) {
            return context.getResources().getString(R.string.IDS_device_measureactivity_result_high);
        }
        if (b == 3) {
            return context.getResources().getString(R.string.IDS_device_measure_pressure_result_level_mild);
        }
        if (b != 4) {
            return b != 5 ? "" : context.getResources().getString(R.string.IDS_device_show_bloodpressure_level_high);
        }
        return context.getResources().getString(R.string.IDS_device_measure_pressure_result_level_moderate);
    }
}
