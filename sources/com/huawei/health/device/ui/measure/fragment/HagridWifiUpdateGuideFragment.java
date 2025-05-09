package com.huawei.health.device.ui.measure.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
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
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cpa;
import defpackage.cqh;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctv;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dij;
import defpackage.jbs;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes3.dex */
public class HagridWifiUpdateGuideFragment extends BaseFragment {
    private static final String KEY_HAGRID_FIRST_UPGRADE_MARK = "hagride_first_update";
    private static final int MSG_GET_DEVICE_STATUS_FAIL = 2;
    private static final int MSG_GET_DEVICE_STATUS_SUCCESS = 1;
    private static final int MSG_SHOW_AUTO_UPGRADE_DIALOG = 3;
    private static final float NEXT_BUTTON = 0.3f;
    private static final String TAG = "HagridWifiUpdateGuideFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridWifiUpdateGuideFragment";
    private String mDeviceId;
    private ContentValues mDeviceInfo;
    private ImageView mGuideLogo;
    private String mProductId = null;
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
        setTitle(this.mainActivity.getString(R.string.IDS_device_scale_device_firmware_version));
        HealthTextView healthTextView = (HealthTextView) nsy.cMd_(this.child, R.id.wifi_guide_node);
        this.mGuideNode = healthTextView;
        healthTextView.setText(R.string.IDS_device_wifi_ota_update_prompt_msg);
        this.mNextBtn = (HealthButton) nsy.cMd_(this.child, R.id.next_btn);
        this.mGuideLogo = (ImageView) nsy.cMd_(this.child, R.id.wifi_device_logo);
        final Context context = BaseApplication.getContext();
        this.mNextBtn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiUpdateGuideFragment.this.m198x21c51ff(context, view);
            }
        });
    }

    /* renamed from: lambda$initView$1$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m198x21c51ff(Context context, View view) {
        if (!ctv.d(context)) {
            LogUtil.h(TAG_RELEASE, "upgrade network disconnected");
            nrh.b(context, R.string.IDS_device_wifi_not_network);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.mNextBtn.setAlpha(NEXT_BUTTON);
            this.mNextBtn.setEnabled(false);
            WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq = new WifiDeviceGetDeviceStatusReq();
            wifiDeviceGetDeviceStatusReq.setDevId(getDevId());
            jbs.a(context).a(wifiDeviceGetDeviceStatusReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda4
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    HagridWifiUpdateGuideFragment.this.m197xc00524a0((WifiDeviceGetDeviceStatusRsp) obj, str, z);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: lambda$initView$0$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m197xc00524a0(WifiDeviceGetDeviceStatusRsp wifiDeviceGetDeviceStatusRsp, String str, boolean z) {
        LogUtil.a(TAG, "get device status: ", wifiDeviceGetDeviceStatusRsp, ", text: ", str, ", is success: ", Boolean.valueOf(z));
        Message obtain = Message.obtain();
        if (wifiDeviceGetDeviceStatusRsp != null && !TextUtils.isEmpty(wifiDeviceGetDeviceStatusRsp.getStatus()) && wifiDeviceGetDeviceStatusRsp.getStatus().equals("online")) {
            if (isFirstUpgtade() && !isOpenAutoUpgrade()) {
                obtain.what = 3;
            } else {
                obtain.what = 1;
            }
        } else {
            obtain.what = 2;
        }
        this.mWiFiHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAutoUpgtadeDialog() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.mainActivity);
        builder.b(R.string.IDS_device_scale_hygride_wlan_ota_auto_title).d(R.string.IDS_device_scale_hygride_wlan_ota_auto_content).cyR_(R.string.IDS_device_hygride_button_cancel, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiUpdateGuideFragment.this.m201x6749aa17(view);
            }
        }).cyU_(R.string.IDS_device_scale_hygride_wlan_ota_auto_open, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiUpdateGuideFragment.this.m202xa960d776(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        a2.show();
    }

    /* renamed from: lambda$showAutoUpgtadeDialog$2$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m201x6749aa17(View view) {
        setFirstUpgtadeMark();
        this.mWiFiHandler.sendEmptyMessage(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$showAutoUpgtadeDialog$3$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m202xa960d776(View view) {
        openAutoUpgrade();
        setFirstUpgtadeMark();
        this.mWiFiHandler.sendEmptyMessage(1);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void openAutoUpgrade() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar == null) {
            LogUtil.h(TAG_RELEASE, "openAutoUpgrade mWiFiDevice is null");
        } else {
            csf.c(ctkVar.d(), "1", new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda3
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    HagridWifiUpdateGuideFragment.this.m199x44952cc8(obj, str, z);
                }
            });
        }
    }

    /* renamed from: lambda$openAutoUpgrade$4$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m199x44952cc8(Object obj, String str, boolean z) {
        if (z) {
            csc.a(this.mWiFiDevice.d(), "1");
        }
        LogUtil.a(TAG, "openAutoUpgrade isSuccess:", Boolean.valueOf(z));
    }

    private String getDevId() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            LogUtil.a(TAG, "device info");
            String a2 = this.mWiFiDevice.b().a();
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
            LogUtil.h(TAG, "getDevId deviceId isEmpty");
            return this.mDeviceId;
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
            this.mDeviceId = getArguments().getString("devId");
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
            }
            ContentValues contentValues2 = this.mDeviceInfo;
            if (contentValues2 != null && !TextUtils.isEmpty(contentValues2.getAsString("uniqueId"))) {
                MeasurableDevice d = ceo.d().d(this.mDeviceInfo.getAsString("uniqueId"), false);
                if (d instanceof ctk) {
                    this.mWiFiDevice = (ctk) d;
                } else {
                    LogUtil.h(TAG, "measurableDevice not instanceof WiFiDevice");
                }
            } else {
                LogUtil.h(TAG_RELEASE, "productId is empty, can't get WiFiDevice");
            }
        } else {
            LogUtil.h(TAG_RELEASE, "Arguments is null");
        }
        if (cpa.x(this.mProductId)) {
            if (cpa.w(this.mProductId)) {
                showGuideVersionLogo();
            } else if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(this.mProductId)) {
                dij.Vc_(this.mGuideLogo, this.mProductId);
            } else {
                this.mGuideLogo.setImageResource(R.drawable.hagrid_device_ota_upgrade_guide_img);
            }
        } else {
            dij.Vc_(this.mGuideLogo, this.mProductId);
        }
        if (this.mWiFiDevice != null) {
            setRightTitleButton();
        }
    }

    private void showGuideVersionLogo() {
        dcz d = ResourceManager.e().d(this.mProductId);
        if (d != null) {
            String c = d.c("device_ota_img");
            if (TextUtils.isEmpty(c)) {
                LogUtil.h(TAG, "initData img = null");
                return;
            }
            String a2 = dcq.b().a(this.mProductId, c);
            LogUtil.a(TAG, "set Device img image = ", a2);
            if (TextUtils.isEmpty(a2)) {
                return;
            }
            Bitmap TK_ = dcx.TK_(a2);
            if (TK_ != null) {
                LogUtil.a(TAG, "initData bitmap != null");
                this.mGuideLogo.setImageBitmap(TK_);
            } else {
                LogUtil.h(TAG, "initData bitmap = null");
            }
        }
    }

    private void setRightTitleButton() {
        this.mCustomTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131428308_res_0x7f0b03d4), nsf.h(R.string._2130841425_res_0x7f020f51));
        this.mCustomTitleBar.setRightButtonClickable(true);
        this.mCustomTitleBar.setRightButtonVisibility(0);
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiUpdateGuideFragment.this.m200x22a0611a(view);
            }
        });
    }

    /* renamed from: lambda$setRightTitleButton$5$com-huawei-health-device-ui-measure-fragment-HagridWifiUpdateGuideFragment, reason: not valid java name */
    /* synthetic */ void m200x22a0611a(View view) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    static class WifiHandler extends StaticHandler<HagridWifiUpdateGuideFragment> {
        private WifiHandler(HagridWifiUpdateGuideFragment hagridWifiUpdateGuideFragment) {
            super(hagridWifiUpdateGuideFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridWifiUpdateGuideFragment hagridWifiUpdateGuideFragment, Message message) {
            if (hagridWifiUpdateGuideFragment == null) {
                LogUtil.h(HagridWifiUpdateGuideFragment.TAG, "object is null.");
                return;
            }
            if (message != null) {
                if (hagridWifiUpdateGuideFragment.isDestroy()) {
                    return;
                }
                if (!hagridWifiUpdateGuideFragment.isAdded()) {
                    LogUtil.h(HagridWifiUpdateGuideFragment.TAG, "WifiHandler mFragment is not add");
                    return;
                } else {
                    LogUtil.a(HagridWifiUpdateGuideFragment.TAG, "WifiHandler what: ", Integer.valueOf(message.what));
                    processHandler(hagridWifiUpdateGuideFragment, message);
                    return;
                }
            }
            LogUtil.h(HagridWifiUpdateGuideFragment.TAG, "msg is null.");
        }

        private void processHandler(HagridWifiUpdateGuideFragment hagridWifiUpdateGuideFragment, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    hagridWifiUpdateGuideFragment.mNextBtn.setAlpha(1.0f);
                    hagridWifiUpdateGuideFragment.mNextBtn.setEnabled(true);
                    cqh.c().d(hagridWifiUpdateGuideFragment.mainActivity);
                    return;
                } else if (i == 3) {
                    hagridWifiUpdateGuideFragment.showAutoUpgtadeDialog();
                    return;
                } else {
                    LogUtil.h(HagridWifiUpdateGuideFragment.TAG_RELEASE, "Handler what is other");
                    return;
                }
            }
            hagridWifiUpdateGuideFragment.mNextBtn.setAlpha(1.0f);
            hagridWifiUpdateGuideFragment.mNextBtn.setEnabled(true);
            Bundle bundle = new Bundle();
            bundle.putString("productId", hagridWifiUpdateGuideFragment.mProductId);
            bundle.putParcelable("commonDeviceInfo", hagridWifiUpdateGuideFragment.mDeviceInfo);
            bundle.putString("version", hagridWifiUpdateGuideFragment.mNewVer);
            bundle.putString("cer_version", hagridWifiUpdateGuideFragment.mNowVersion);
            bundle.putString("update_nodes", hagridWifiUpdateGuideFragment.mReleaseNote);
            HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment = new HagridWiFiOtaUpdateFragment();
            hagridWiFiOtaUpdateFragment.setArguments(bundle);
            hagridWifiUpdateGuideFragment.switchFragment(hagridWiFiOtaUpdateFragment);
        }
    }

    private boolean isOpenAutoUpgrade() {
        return "1".equals(csc.b(this.mWiFiDevice.d()));
    }

    private boolean isFirstUpgtade() {
        if (this.mWiFiDevice == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.mWiFiDevice.d());
        sb.append(KEY_HAGRID_FIRST_UPGRADE_MARK);
        return TextUtils.isEmpty(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), sb.toString()));
    }

    private void setFirstUpgtadeMark() {
        LogUtil.a(TAG, "setFirstUpgtadeMark result:", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), this.mWiFiDevice.d() + KEY_HAGRID_FIRST_UPGRADE_MARK, String.valueOf(System.currentTimeMillis()), (StorageParams) null)));
    }
}
