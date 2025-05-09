package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.ecologydevice.fitness.datastruct.DeviceInformation;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dij;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class DeviceInfoFragment extends BaseFragment {
    private static final String HEBREW_LANGUAGE_CODE = "iw";
    private static final String ROPE_DEVICE_INFO = "rope_device_info";
    private static final String TAG = "EcologyDevice_DeviceInfoFragment";
    private DeviceInformation mDeviceInformation;
    private String mMacAddress;
    protected HealthTextView mMacaddressTv;
    protected HealthTextView mManufacturerTv;
    protected HealthTextView mModelTv;
    private String mProductId;
    protected HealthTextView mSerialNumberTv;
    private HealthTextView mSerialTv;
    protected HealthTextView mSoftwareVersionTv;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        initParam();
    }

    private void initParam() {
        if (getArguments() != null) {
            Bundle arguments = getArguments();
            this.mDeviceInformation = (DeviceInformation) arguments.getParcelable(ROPE_DEVICE_INFO);
            this.mProductId = arguments.getString("productId");
            this.mMacAddress = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getBondedDeviceAddress(this.mProductId, arguments.getString("uniqueId"));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h(TAG, "DeviceInfoFragment onCreateView inflater is null");
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.rope_device_info_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
        }
        return viewGroup2;
    }

    private void initView(View view) {
        this.mCustomTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131299340_res_0x7f090c0c));
        setTitle(getResources().getString(R.string.IDS_device_rope_device_info));
        this.mManufacturerTv = (HealthTextView) view.findViewById(R.id.htv_manufacturer_value);
        this.mModelTv = (HealthTextView) view.findViewById(R.id.htv_model_value);
        this.mMacaddressTv = (HealthTextView) view.findViewById(R.id.htv_macaddress_value);
        this.mSoftwareVersionTv = (HealthTextView) view.findViewById(R.id.htv_software_version_value);
        this.mSerialNumberTv = (HealthTextView) view.findViewById(R.id.htv_serial_value);
        this.mSerialTv = (HealthTextView) view.findViewById(R.id.htv_serial_title);
        if (dij.o(HEBREW_LANGUAGE_CODE) || dij.o(Constants.URDU_LANG)) {
            this.mSerialTv.setGravity(5);
        }
        this.mMacaddressTv.setText(this.mMacAddress);
        DeviceInformation deviceInformation = this.mDeviceInformation;
        if (deviceInformation != null) {
            this.mManufacturerTv.setText(deviceInformation.getManufacturerString());
            this.mModelTv.setText(this.mDeviceInformation.getModelString());
            this.mSoftwareVersionTv.setText(this.mDeviceInformation.getSoftwareVersion());
            this.mSerialNumberTv.setText(this.mDeviceInformation.getSerialNumber());
        }
    }
}
