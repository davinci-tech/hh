package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.ctk;
import defpackage.dcz;
import defpackage.dij;
import defpackage.nsf;
import defpackage.nsy;

/* loaded from: classes3.dex */
public class HagridWifiVersionDetailsFragment extends BaseFragment {
    private static final String TAG = "HagridWifiVersionDetailsFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridWifiVersionDetailsFragment";
    private ImageView mVersionLogo;
    private dcz mProductInfo = null;
    private String mProductId = null;
    private String mNewVersion = null;
    private String mReleaseNote = null;
    private String mNowVersion = null;
    private HealthTextView mVersionTv = null;
    private HealthTextView mUpdateNodesTv = null;
    private HealthButton mUpgradeBtn = null;
    private ctk mWiFiDevice = null;
    private ContentValues mDeviceInfo = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.child = layoutInflater.inflate(R.layout.wifi_version_details_layout, viewGroup, false);
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        initView();
        initData();
        if (onCreateView instanceof ViewGroup) {
            ((ViewGroup) onCreateView).addView(this.child);
        }
        return onCreateView;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_scale_device_firmware_version));
        this.mVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.version_tv);
        this.mUpdateNodesTv = (HealthTextView) nsy.cMd_(this.child, R.id.update_nodes_tv);
        this.mUpgradeBtn = (HealthButton) nsy.cMd_(this.child, R.id.upgrade_btn);
        this.mVersionLogo = (ImageView) nsy.cMd_(this.child, R.id.image_logo);
        this.mUpgradeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiVersionDetailsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiVersionDetailsFragment.this.m203x7f6f401d(view);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-health-device-ui-measure-fragment-HagridWifiVersionDetailsFragment, reason: not valid java name */
    /* synthetic */ void m203x7f6f401d(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("version", this.mNewVersion);
        bundle.putString("cer_version", this.mNowVersion);
        bundle.putString("update_nodes", this.mReleaseNote);
        bundle.putString("devId", this.mWiFiDevice.d());
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        HagridWifiUpdateGuideFragment hagridWifiUpdateGuideFragment = new HagridWifiUpdateGuideFragment();
        hagridWifiUpdateGuideFragment.setArguments(bundle);
        switchFragment(hagridWifiUpdateGuideFragment);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setRightTitleButton() {
        this.mCustomTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131428308_res_0x7f0b03d4), nsf.h(R.string._2130841425_res_0x7f020f51));
        this.mCustomTitleBar.setRightButtonClickable(true);
        this.mCustomTitleBar.setRightButtonVisibility(0);
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiVersionDetailsFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiVersionDetailsFragment.this.m204x51ce705f(view);
            }
        });
    }

    /* renamed from: lambda$setRightTitleButton$1$com-huawei-health-device-ui-measure-fragment-HagridWifiVersionDetailsFragment, reason: not valid java name */
    /* synthetic */ void m204x51ce705f(View view) {
        if (this.mWiFiDevice != null) {
            HagridOtaSettingFragment hagridOtaSettingFragment = new HagridOtaSettingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("deviceId", this.mWiFiDevice.d());
            hagridOtaSettingFragment.setArguments(bundle);
            switchFragment(hagridOtaSettingFragment);
        } else {
            LogUtil.h(TAG_RELEASE, "setRightTitleButton mWiFiDevice is null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData() {
        if (getArguments() != null) {
            this.mProductId = getArguments().getString("productId");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
            }
            ContentValues contentValues2 = this.mDeviceInfo;
            if (contentValues2 != null && !TextUtils.isEmpty(contentValues2.getAsString("uniqueId")) && (ceo.d().d(this.mDeviceInfo.getAsString("uniqueId"), false) instanceof ctk)) {
                this.mWiFiDevice = (ctk) ceo.d().d(this.mDeviceInfo.getAsString("uniqueId"), false);
            }
            if (!TextUtils.isEmpty(this.mProductId)) {
                LogUtil.c(TAG, "mWiFiDevice is ", this.mWiFiDevice, ". mProductId: ", this.mProductId);
                dcz d = ResourceManager.e().d(this.mProductId);
                this.mProductInfo = d;
                if (d == null) {
                    LogUtil.h(TAG_RELEASE, "ProductInfo is null, can't get version");
                }
            } else {
                LogUtil.h(TAG_RELEASE, "productId is empty, can't get ProductInfo");
            }
            this.mNewVersion = getArguments().getString("version");
            this.mNowVersion = getArguments().getString("cer_version");
            this.mReleaseNote = getArguments().getString("update_nodes");
            this.mVersionTv.setText(this.mainActivity.getString(R.string.IDS_device_wifi_ota_latest_version) + this.mNewVersion);
            this.mUpdateNodesTv.setText(this.mReleaseNote);
            if (getArguments().getBoolean("show_update")) {
                this.mUpgradeBtn.setVisibility(0);
            } else {
                this.mUpgradeBtn.setVisibility(8);
            }
            dij.Vc_(this.mVersionLogo, this.mProductId);
        } else {
            LogUtil.h(TAG_RELEASE, "Arguments is null");
        }
        if (this.mWiFiDevice != null) {
            setRightTitleButton();
        }
    }
}
