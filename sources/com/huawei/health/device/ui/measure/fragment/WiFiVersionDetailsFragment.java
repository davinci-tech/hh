package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cpw;
import defpackage.ctk;
import defpackage.dcz;

/* loaded from: classes3.dex */
public class WiFiVersionDetailsFragment extends BaseFragment {
    private static final String TAG = "WiFiVersionDetailsFragment";
    private ImageView mVersionLogo;
    private dcz mProductInfo = null;
    private String mProductId = null;
    private String mUniqueId = null;
    private ContentValues contentValues = null;
    private String mNewVer = null;
    private String mReleaseNote = null;
    private String mNowVersion = null;
    private HealthTextView mVersionTv = null;
    private HealthTextView mUpdateNodesTv = null;
    private HealthButton mUpgradeBtn = null;
    private ctk mWiFiDevice = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_version_details_layout, viewGroup, false);
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        this.mVersionTv = (HealthTextView) this.child.findViewById(R.id.version_tv);
        this.mUpdateNodesTv = (HealthTextView) this.child.findViewById(R.id.update_nodes_tv);
        this.mUpgradeBtn = (HealthButton) this.child.findViewById(R.id.upgrade_btn);
        this.mVersionLogo = (ImageView) this.child.findViewById(R.id.image_logo);
        this.mUpgradeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiVersionDetailsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WiFiVersionDetailsFragment.this.m254xce941c5e(view);
            }
        });
    }

    /* renamed from: lambda$initView$0$com-huawei-health-device-ui-measure-fragment-WiFiVersionDetailsFragment, reason: not valid java name */
    /* synthetic */ void m254xce941c5e(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("version", this.mNewVer);
        bundle.putString("cer_version", this.mNowVersion);
        bundle.putString("update_nodes", this.mReleaseNote);
        WiFiUpdateGuideFragment wiFiUpdateGuideFragment = new WiFiUpdateGuideFragment();
        bundle.putParcelable("commonDeviceInfo", this.contentValues);
        wiFiUpdateGuideFragment.setArguments(bundle);
        switchFragment(wiFiUpdateGuideFragment);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void initData() {
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.contentValues = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.contentValues.getAsString("uniqueId");
            }
            MeasurableDevice d = ceo.d().d(this.mUniqueId, false);
            if (!TextUtils.isEmpty(this.mUniqueId) && (d instanceof ctk)) {
                ctk ctkVar = (ctk) d;
                this.mWiFiDevice = ctkVar;
                cpw.a(false, TAG, "mWiFiDevice is ", ctkVar, ". mUniqueId: ", cpw.a(this.mUniqueId));
            }
            if (!TextUtils.isEmpty(this.mProductId)) {
                dcz d2 = ResourceManager.e().d(this.mProductId);
                this.mProductInfo = d2;
                if (d2 == null) {
                    cpw.c(false, TAG, "ProductInfo is null, can't get version");
                }
            } else {
                cpw.c(false, TAG, "productId is empty, can't get ProductInfo");
            }
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString("cer_version");
            this.mReleaseNote = getArguments().getString("update_nodes");
            this.mVersionTv.setText(this.mainActivity.getString(R.string.IDS_device_wifi_ota_latest_version) + this.mNewVer);
            this.mUpdateNodesTv.setText(this.mReleaseNote);
            if (getArguments().getBoolean("show_update")) {
                this.mUpgradeBtn.setVisibility(0);
            } else {
                this.mUpgradeBtn.setVisibility(8);
            }
            if ("a8ba095d-4123-43c4-a30a-0240011c58de".equals(this.mProductId)) {
                this.mVersionLogo.setImageResource(R.drawable.wifi_device_honor_ota_logo);
                return;
            }
            return;
        }
        cpw.a(false, TAG, "Arguments is null");
    }
}
