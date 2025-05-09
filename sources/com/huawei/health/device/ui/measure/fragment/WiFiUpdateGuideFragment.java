package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusRsp;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cqh;
import defpackage.ctk;
import defpackage.jbs;
import defpackage.nsy;

/* loaded from: classes3.dex */
public class WiFiUpdateGuideFragment extends BaseFragment {
    private static final int MSG_GET_DEVICE_STATUS_FAIL = 2;
    private static final int MSG_GET_DEVICE_STATUS_SUCCESS = 1;
    private static final String TAG = "WiFiUpdateGuideFragment";
    private static final float TRANS_PARENCY_03 = 0.3f;
    private ImageView mGuideLogo;
    private String mProductId = null;
    private String mUniqueId = null;
    private ContentValues contentValues = null;
    private ctk mWiFiDevice = null;
    private Handler mWiFiHandler = null;
    private String mNewVer = null;
    private String mReleaseNote = null;
    private String mNowVersion = null;
    private HealthTextView mGuideNode = null;
    private HealthButton mNextBtn = null;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.wifi_update_guide_layout, viewGroup, false);
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_guide_node);
        this.mGuideNode = healthTextView;
        healthTextView.setText(R.string.IDS_device_wifi_ota_update_prompt_msg);
        this.mNextBtn = (HealthButton) nsy.cMd_(this.child, R.id.next_btn);
        this.mGuideLogo = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_logo);
        this.mNextBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUpdateGuideFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WiFiUpdateGuideFragment.this.m250x1312f89e(view);
            }
        });
    }

    /* renamed from: lambda$initView$1$com-huawei-health-device-ui-measure-fragment-WiFiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m250x1312f89e(View view) {
        this.mNextBtn.setAlpha(TRANS_PARENCY_03);
        this.mNextBtn.setEnabled(false);
        WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq = new WifiDeviceGetDeviceStatusReq();
        wifiDeviceGetDeviceStatusReq.setDevId(getDevId());
        jbs.a(this.mainActivity.getApplicationContext()).a(wifiDeviceGetDeviceStatusReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiUpdateGuideFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WiFiUpdateGuideFragment.this.m249xda3297ff((WifiDeviceGetDeviceStatusRsp) obj, str, z);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$initView$0$com-huawei-health-device-ui-measure-fragment-WiFiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m249xda3297ff(WifiDeviceGetDeviceStatusRsp wifiDeviceGetDeviceStatusRsp, String str, boolean z) {
        LogUtil.a(TAG, "get device status: ", wifiDeviceGetDeviceStatusRsp, ", text: ", str, ", is success: ", Boolean.valueOf(z));
        Message obtain = Message.obtain();
        if (wifiDeviceGetDeviceStatusRsp != null && !TextUtils.isEmpty(wifiDeviceGetDeviceStatusRsp.getStatus()) && wifiDeviceGetDeviceStatusRsp.getStatus().equals("online")) {
            obtain.what = 1;
        } else {
            obtain.what = 2;
        }
        this.mWiFiHandler.sendMessage(obtain);
    }

    private String getDevId() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            LogUtil.a(TAG, "device info: ", this.mWiFiDevice.b().toString());
            return this.mWiFiDevice.b().a();
        }
        LogUtil.h(TAG, "device id is null");
        return null;
    }

    private void initData() {
        this.mWiFiHandler = new WifiHandler();
        if (getArguments() != null) {
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString("cer_version");
            this.mReleaseNote = getArguments().getString("update_nodes");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.contentValues = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.contentValues.getAsString("uniqueId");
            }
            if (!TextUtils.isEmpty(this.mUniqueId)) {
                if (ceo.d().d(this.mUniqueId, false) instanceof ctk) {
                    this.mWiFiDevice = (ctk) ceo.d().d(this.mUniqueId, false);
                } else {
                    LogUtil.h(TAG, "device instanceof WiFiDevice");
                }
            } else {
                LogUtil.h(TAG, "productId is empty, can't get WiFiDevice");
            }
        } else {
            LogUtil.h(TAG, "Arguments is null");
        }
        if ("a8ba095d-4123-43c4-a30a-0240011c58de".equals(this.mProductId)) {
            this.mGuideLogo.setImageResource(R.drawable.wifi_device_honor_ota_guide_img);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.h(TAG, "DeviceMainActivity is Destroyed");
        return true;
    }

    static class WifiHandler extends StaticHandler<WiFiUpdateGuideFragment> {
        private WifiHandler(WiFiUpdateGuideFragment wiFiUpdateGuideFragment) {
            super(wiFiUpdateGuideFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiUpdateGuideFragment wiFiUpdateGuideFragment, Message message) {
            if (wiFiUpdateGuideFragment.isDestroy()) {
                return;
            }
            if (!wiFiUpdateGuideFragment.isAdded()) {
                LogUtil.h(WiFiUpdateGuideFragment.TAG, "WifiHandler mFragment is not add");
                return;
            }
            LogUtil.a(WiFiUpdateGuideFragment.TAG, "WifiHandler what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 1) {
                if (i != 2) {
                    return;
                }
                wiFiUpdateGuideFragment.mNextBtn.setAlpha(1.0f);
                wiFiUpdateGuideFragment.mNextBtn.setEnabled(true);
                cqh.c().Li_(wiFiUpdateGuideFragment.mainActivity);
                return;
            }
            wiFiUpdateGuideFragment.mNextBtn.setAlpha(1.0f);
            wiFiUpdateGuideFragment.mNextBtn.setEnabled(true);
            Bundle bundle = new Bundle();
            bundle.putString("productId", wiFiUpdateGuideFragment.mProductId);
            bundle.putString("version", wiFiUpdateGuideFragment.mNewVer);
            bundle.putString("cer_version", wiFiUpdateGuideFragment.mNowVersion);
            bundle.putString("update_nodes", wiFiUpdateGuideFragment.mReleaseNote);
            WiFiOtaUpdateFragment wiFiOtaUpdateFragment = new WiFiOtaUpdateFragment();
            bundle.putParcelable("commonDeviceInfo", wiFiUpdateGuideFragment.contentValues);
            wiFiOtaUpdateFragment.setArguments(bundle);
            wiFiUpdateGuideFragment.switchFragment(wiFiOtaUpdateFragment);
        }
    }
}
