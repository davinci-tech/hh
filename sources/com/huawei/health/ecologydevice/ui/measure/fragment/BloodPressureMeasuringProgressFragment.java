package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceFragmentFactoryApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceConnectFailedFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.device.open.data.MeasureResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cxh;
import defpackage.dic;
import defpackage.dku;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class BloodPressureMeasuringProgressFragment extends BluetoothMeasureFragment {
    private static final String TAG = "BloodPressureMeasuringProgressFragment";
    HealthTextView mSearchDevicePrompt;
    private HealthProgressBar measuringProgressBar;
    private ImageView measuringProgressBarBg;

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        if (layoutInflater == null) {
            LogUtil.a(TAG, "DeviceMeasuringProgressFragment onCreateView inflater is null");
            return viewGroup2;
        }
        this.child = layoutInflater.inflate(R.layout.device_show_realtime_result, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.removeViewAt(1);
            viewGroup2.addView(this.child);
        }
        this.mProductId = getArguments().getString("productId");
        this.mUniqueId = getArguments().getString("uniqueId");
        this.mDeviceInfo = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        if (this.mDeviceInfo != null) {
            this.mProductId = this.mDeviceInfo.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        }
        LogUtil.h(TAG, getClass().getSimpleName(), "initData mProductId :", this.mProductId, ",mUniqueId:", dku.b(this.mUniqueId));
        this.mKind = getArguments().getString("kind");
        init();
        this.mCustomTitleBar.setTitleText(getResources().getString(R.string.IDS_device_selection_start_measure));
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasuringProgressFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasuringProgressFragment.this.m285xb9bef7d1(view);
            }
        });
        return viewGroup2;
    }

    /* renamed from: lambda$onCreateView$0$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureMeasuringProgressFragment, reason: not valid java name */
    /* synthetic */ void m285xb9bef7d1(View view) {
        onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment
    public void saveResultData() {
        super.saveResultData();
        LogUtil.a(TAG, "DeviceMeasuringPro finish");
        popupFragment(ProductIntroductionFragment.class);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        LogUtil.a(TAG, "DeviceMeasuringProgressFragment onBackPressed");
        return super.showCustomAlertDialog(R.string.IDS_device_ui_activity_quit_dialog_in_mea);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    private void init() {
        if (this.child != null) {
            this.mSearchDevicePrompt = (HealthTextView) this.child.findViewById(R.id.device_measure_search_prompt);
            this.measuringProgressBarBg = (ImageView) this.child.findViewById(R.id.device_measure_top_progress_bar_bg);
            this.measuringProgressBar = (HealthProgressBar) this.child.findViewById(R.id.device_measure_top_progress_bar);
            this.mSearchDevicePrompt.setText(R.string.IDS_device_connecting_21);
            this.mSearchDevicePrompt.setVisibility(0);
            ((HealthTextView) this.child.findViewById(R.id.device_measure_unit_center_tv)).setVisibility(4);
            setProgressBar();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        startMeasure();
    }

    private void setProgressBar() {
        this.measuringProgressBar.setVisibility(0);
        this.measuringProgressBarBg.setVisibility(0);
        this.measuringProgressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.hw_device_meausure_progress));
        this.measuringProgressBar.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim._2130772005_res_0x7f010025));
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThread(HealthDevice healthDevice, HealthData healthData, boolean z) {
        if (healthData == null || !z) {
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("HealthData", healthData);
        switchFragment(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleDataChangedInUiThreadUniversal(MeasureResult measureResult, boolean z) {
        if (measureResult != null && z) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("HealthData", dic.c().d(measureResult));
            switchFragment(bundle);
        }
    }

    private void switchFragment(Bundle bundle) {
        Fragment resultFragment = ((DeviceFragmentFactoryApi) Services.c("PluginDevice", DeviceFragmentFactoryApi.class)).getResultFragment(this.mKind);
        if (resultFragment == null) {
            LogUtil.h(TAG, "fragment is null");
            return;
        }
        bundle.putString("productId", this.mProductId);
        bundle.putString("title", getResources().getString(R.string.IDS_device_selection_start_measure));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", this.mUniqueId);
        contentValues.put("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", contentValues);
        resultFragment.setArguments(bundle);
        switchFragment(resultFragment);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThread(HealthDevice healthDevice, int i) {
        if (healthDevice instanceof cxh) {
            boolean z = i == 5 || i == 3;
            LogUtil.a(TAG, "is bleDevice");
            if (i == 2) {
                LogUtil.a(TAG, "IHealthDeviceCallback.STATUS_CONNECTED");
                this.mSearchDevicePrompt.setText(getResources().getString(R.string.IDS_device_measureactivity_measuring));
                return;
            }
            if (z || i == 0) {
                LogUtil.a(TAG, "not IHealthDeviceCallback.STATUS_CONNECTED");
                stopTimer();
                Bundle bundle = new Bundle();
                bundle.putString("productId", this.mProductId);
                bundle.putString("uniqueId", this.mUniqueId);
                ContentValues contentValues = new ContentValues();
                contentValues.put("uniqueId", this.mUniqueId);
                contentValues.put("productId", this.mProductId);
                bundle.putParcelable("commonDeviceInfo", contentValues);
                bundle.putString("kind", this.mKind);
                bundle.putString("title", getResources().getString(R.string.IDS_device_selection_start_measure));
                Fragment deviceConnectFailedFragment = new DeviceConnectFailedFragment();
                deviceConnectFailedFragment.setArguments(bundle);
                switchFragment(deviceConnectFailedFragment);
                return;
            }
            LogUtil.a(TAG, "not IHealthDeviceCallback is other status");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleStatusChangedInUiThreadUniversal(int i) {
        boolean z = i == 5 || i == 3;
        if (i == 2) {
            LogUtil.a(TAG, "IHealthDeviceCallback.STATUS_CONNECTED");
            this.mSearchDevicePrompt.setText(getResources().getString(R.string.IDS_device_measureactivity_measuring));
            return;
        }
        if (z || i == 0) {
            LogUtil.a(TAG, "not IHealthDeviceCallback.STATUS_CONNECTED");
            stopTimer();
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putString("kind", this.mKind);
            bundle.putString("title", getResources().getString(R.string.IDS_device_selection_start_measure));
            bundle.putString("uniqueId", this.mUniqueId);
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", this.mUniqueId);
            contentValues.put("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", contentValues);
            Fragment deviceConnectFailedFragment = new DeviceConnectFailedFragment();
            deviceConnectFailedFragment.setArguments(bundle);
            switchFragment(deviceConnectFailedFragment);
            return;
        }
        LogUtil.a(TAG, "not IHealthDeviceCallback is other status");
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceMeasureGuideFragment
    public void handleFailedEventInUiThread(int i) {
        showErrorDialog();
    }

    public void showErrorDialog() {
        LogUtil.a(TAG, "onBackPressed showErrorDialog()");
        if (getActivity() != null) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
            builder.e(R.string.IDS_device_measure_error_pressure_result_error);
            builder.czC_(R.string.IDS_device_measureactivity_result_confirm, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.BloodPressureMeasuringProgressFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodPressureMeasuringProgressFragment.this.m286x6bc83937(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            e.show();
        }
    }

    /* renamed from: lambda$showErrorDialog$1$com-huawei-health-ecologydevice-ui-measure-fragment-BloodPressureMeasuringProgressFragment, reason: not valid java name */
    /* synthetic */ void m286x6bc83937(View view) {
        LogUtil.a(TAG, "showErrorDialog getActivity().finish()");
        popupFragment(ProductIntroductionFragment.class);
        ViewClickInstrumentation.clickOnView(view);
    }
}
