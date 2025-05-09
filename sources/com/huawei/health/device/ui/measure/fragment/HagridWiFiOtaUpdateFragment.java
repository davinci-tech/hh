package com.huawei.health.device.ui.measure.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.ui.measure.fragment.HagridWiFiOtaUpdateFragment;
import com.huawei.health.device.ui.util.RoundProgressView;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.BaseCallbackInterface;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.DeviceServiceInfo;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceControlDataModelReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetDeviceStatusRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cld;
import defpackage.cpz;
import defpackage.cqh;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.cuf;
import defpackage.dij;
import defpackage.jbs;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class HagridWiFiOtaUpdateFragment extends BaseFragment implements View.OnClickListener {
    private static final String CER_VERSION = "cer_version";
    private static final int DEFAULT_VALUE = 0;
    private static final String GET_NETWORK_DEVICE_VERSION = "getNetworkDeviceVersion";
    private static final int MSG_DEVICE_OFFLINE = 6;
    private static final int MSG_DEVICE_ONLINE = 5;
    private static final int MSG_UPDATE_END = 4;
    private static final int MSG_UPDATE_FAIL = 3;
    private static final int MSG_UPDATE_SUCCESS = 2;
    private static final int MSG_UPDATING = 1;
    private static final String TAG = "HagridWiFiOtaUpdateFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridWiFiOtaUpdateFragment";
    private static final String UPDATE_NODES = "update_nodes";
    private static final int UPGRADE_STATUS_NORMAL = 0;
    private static final String VERSION = "version";
    private static final float VESION_UPDATE_ALPHA = 0.3f;
    private BaseCallbackInterface mBaseCallback;
    private String mUniqueId;
    private ImageView mVersionLogo;
    private cld mWeightInteractor;
    private String mProductId = null;
    private String mNewVer = null;
    private String mReleaseNote = null;
    private String mNowVersion = null;
    private boolean isFromProductView = false;
    private HealthTextView mNowVersionTv = null;
    private HealthTextView mNewVersionTv = null;
    private HealthTextView mNoNewVersionTv = null;
    private HealthTextView mOtaUpgradeTipOneTv = null;
    private HealthTextView mOtaUpgradeTipTwoTv = null;
    private LinearLayout mLatestVersionLayout = null;
    private LinearLayout mNowVersionLayout = null;
    private WifiHandler mHandler = null;
    private RoundProgressView mRoundProgress = null;
    private HealthButton mCheckVersionUpdate = null;
    private ctk mWiFiDevice = null;
    private int mUpgradeStatus = 0;
    private ContentValues mDeviceInfo = null;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiOtaUpdateFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(HagridWiFiOtaUpdateFragment.TAG, "mBroadcastReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a(HagridWiFiOtaUpdateFragment.TAG, "mBroadcastReceiver onReceive: action = ", action);
            if (!"action_app_check_new_version_state".equals(action)) {
                LogUtil.h(HagridWiFiOtaUpdateFragment.TAG, "mBroadcastReceiver other action");
                return;
            }
            int intExtra = intent.getIntExtra("state", 0);
            if (intExtra == 50) {
                HagridWiFiOtaUpdateFragment.this.mNewVer = intent.getStringExtra("content");
                ReleaseLogUtil.e(HagridWiFiOtaUpdateFragment.TAG_RELEASE, "mBroadcastReceiver mNewVer:", HagridWiFiOtaUpdateFragment.this.mNewVer, ",mNowVersion:", HagridWiFiOtaUpdateFragment.this.mNowVersion);
                if (TextUtils.isEmpty(HagridWiFiOtaUpdateFragment.this.mNewVer) || TextUtils.isEmpty(HagridWiFiOtaUpdateFragment.this.mNowVersion)) {
                    HagridWiFiOtaUpdateFragment.this.startUpgrade();
                    return;
                } else if (!HagridWiFiOtaUpdateFragment.this.mNewVer.equals(HagridWiFiOtaUpdateFragment.this.mNowVersion)) {
                    HagridWiFiOtaUpdateFragment.this.startUpgrade();
                    return;
                } else {
                    HagridWiFiOtaUpdateFragment.this.setCheckVersionUpdateEnable(2);
                    return;
                }
            }
            LogUtil.a(HagridWiFiOtaUpdateFragment.TAG, "mBroadcastReceiver other state:", Integer.valueOf(intExtra));
            HagridWiFiOtaUpdateFragment.this.startUpgrade();
        }
    };

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.child = layoutInflater.inflate(R.layout.wifi_upgrade_layout, viewGroup, false);
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        this.mWeightInteractor = cld.HJ_(this.mainActivity, this.mProductId, this.mUniqueId);
        return viewGroup2;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_scale_device_firmware_version));
        this.mNowVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.now_version_nodes_tv);
        this.mNoNewVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.no_new_version_tv);
        this.mOtaUpgradeTipOneTv = (HealthTextView) nsy.cMd_(this.child, R.id.ota_upgrade_tip_one_tv);
        this.mOtaUpgradeTipTwoTv = (HealthTextView) nsy.cMd_(this.child, R.id.ota_upgrade_tip_two_tv);
        this.mNewVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.new_version_tv);
        this.mVersionLogo = (ImageView) nsy.cMd_(this.child, R.id.image_logo);
        this.mLatestVersionLayout = (LinearLayout) nsy.cMd_(this.child, R.id.latest_version_button);
        this.mRoundProgress = (RoundProgressView) nsy.cMd_(this.child, R.id.version_update_anim);
        this.mCheckVersionUpdate = (HealthButton) nsy.cMd_(this.child, R.id.check_version_update);
        this.mNowVersionLayout = (LinearLayout) nsy.cMd_(this.child, R.id.now_version_layout);
        this.mOtaUpgradeTipOneTv.setVisibility(8);
        this.mOtaUpgradeTipTwoTv.setVisibility(8);
        this.mNowVersionLayout.setEnabled(false);
        this.mLatestVersionLayout.setOnClickListener(this);
        this.mCheckVersionUpdate.setOnClickListener(this);
        this.mLatestVersionLayout.setVisibility(0);
        this.mCheckVersionUpdate.setText(R.string._2130841549_res_0x7f020fcd);
        this.mOtaUpgradeTipOneTv.setText(String.format(Locale.ROOT, this.mainActivity.getString(R.string.IDS_device_ota_upgrade_tip_one), 1));
        this.mOtaUpgradeTipTwoTv.setText(String.format(Locale.ROOT, this.mainActivity.getString(R.string.IDS_device_ota_upgrade_tip_two), 2));
    }

    private void initData() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFC_(this.mainActivity, this.mBroadcastReceiver, intentFilter, LocalBroadcast.c, null);
        this.mBaseCallback = new OtaStatusCallback(this);
        this.mHandler = new WifiHandler();
        if (getArguments() != null) {
            ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            }
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString(CER_VERSION);
            this.mReleaseNote = getArguments().getString(UPDATE_NODES);
            this.mNewVersionTv.setText(this.mNewVer);
            this.mNowVersionTv.setText(this.mNowVersion);
            this.isFromProductView = getArguments().getBoolean("fromProductView");
            ContentValues contentValues2 = this.mDeviceInfo;
            if (contentValues2 != null && !TextUtils.isEmpty(contentValues2.getAsString("uniqueId"))) {
                MeasurableDevice d = ceo.d().d(this.mDeviceInfo.getAsString("uniqueId"), false);
                if (d instanceof ctk) {
                    this.mWiFiDevice = (ctk) d;
                }
            }
            if (this.mWiFiDevice == null) {
                LogUtil.h(TAG_RELEASE, "initData WiFiDevice is empty");
                popupFragment(HagridDeviceManagerFragment.class);
                return;
            }
            dij.Vc_(this.mVersionLogo, this.mProductId);
            LogUtil.a(TAG, "initData mUpgradeStatus:", Integer.valueOf(this.mUpgradeStatus));
            if (this.isFromProductView) {
                initStatusData();
                return;
            }
            int i = this.mUpgradeStatus;
            if (i == 0) {
                startUpgrade();
            } else {
                setCheckVersionUpdateEnable(i);
            }
        }
    }

    private void initStatusData() {
        String str = csc.d().e(this.mWiFiDevice.d()).get("status");
        LogUtil.a(TAG, "initStatusData otaStatus:", str);
        int i = this.mUpgradeStatus;
        if (i == 0) {
            if ("3".equals(str)) {
                Message obtain = Message.obtain();
                obtain.what = 4;
                this.mHandler.sendMessage(obtain);
                return;
            } else {
                if ("2".equals(str)) {
                    LogUtil.a(TAG, "initStatusData start update");
                    this.mLatestVersionLayout.setEnabled(false);
                    cpz.Kr_(this.mProductId, this.mNowVersion, this.mNewVer, this.mDeviceInfo);
                    setCheckVersionUpdateEnable(1);
                    cuf.e(this.mainActivity.getApplicationContext()).c(this.mUniqueId, this.mBaseCallback);
                    return;
                }
                LogUtil.h(TAG, "initStatusData otaStatus is error");
                return;
            }
        }
        setCheckVersionUpdateEnable(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startUpgrade() {
        LogUtil.a(TAG, "startUpgrade");
        this.mLatestVersionLayout.setEnabled(false);
        cpz.Kr_(this.mProductId, this.mNowVersion, this.mNewVer, this.mDeviceInfo);
        setCheckVersionUpdateEnable(1);
        updatingVersion("CH");
    }

    private void updatingVersion(String str) {
        WifiDeviceControlDataModelReq wifiDeviceControlDataModelReq = new WifiDeviceControlDataModelReq();
        ArrayList arrayList = new ArrayList(16);
        HashMap hashMap = new HashMap(16);
        wifiDeviceControlDataModelReq.setDeviceServiceInfo(arrayList);
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            wifiDeviceControlDataModelReq.setDevId(this.mWiFiDevice.b().a());
        } else {
            LogUtil.h(TAG_RELEASE, "update device id is null");
        }
        hashMap.put("event", "2");
        hashMap.put("version", str);
        DeviceServiceInfo deviceServiceInfo = new DeviceServiceInfo();
        deviceServiceInfo.setData(hashMap);
        deviceServiceInfo.setSid("setDevParam");
        arrayList.add(deviceServiceInfo);
        LogUtil.a(TAG, "version: ", hashMap.get("version"), ", event: " + hashMap.get("event"));
        if (this.mainActivity == null) {
            LogUtil.h(TAG, "updatingVersion mainActivity is null");
            setCheckVersionUpdateEnable(3);
        } else {
            jbs.a(this.mainActivity.getApplicationContext()).d(wifiDeviceControlDataModelReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiOtaUpdateFragment$$ExternalSyntheticLambda1
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str2, boolean z) {
                    HagridWiFiOtaUpdateFragment.this.m193x7897856f((CloudCommonReponse) obj, str2, z);
                }
            });
        }
    }

    /* renamed from: lambda$updatingVersion$0$com-huawei-health-device-ui-measure-fragment-HagridWiFiOtaUpdateFragment, reason: not valid java name */
    /* synthetic */ void m193x7897856f(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
        ReleaseLogUtil.e(TAG_RELEASE, "send update version. cloudCommonReponse: ", cloudCommonReponse, ", is success: ", Boolean.valueOf(z));
        Message obtain = Message.obtain();
        if (z) {
            obtain.what = 2;
        } else {
            obtain.what = 3;
        }
        this.mHandler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        return activity == null || activity.isFinishing() || activity.isDestroyed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.latest_version_button) {
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putString("version", this.mNewVer);
            bundle.putString(CER_VERSION, this.mNowVersion);
            bundle.putString(UPDATE_NODES, this.mReleaseNote);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            HagridWifiVersionDetailsFragment hagridWifiVersionDetailsFragment = new HagridWifiVersionDetailsFragment();
            hagridWifiVersionDetailsFragment.setArguments(bundle);
            switchFragment(hagridWifiVersionDetailsFragment);
        } else if (id == R.id.check_version_update) {
            LogUtil.a(TAG, " UpgradeStatus =", Integer.valueOf(this.mUpgradeStatus));
            int i = this.mUpgradeStatus;
            if (i != 0) {
                if (i == 3) {
                    LogUtil.h(TAG_RELEASE, "update fail.");
                    restartUpgrade();
                } else if (i == 2) {
                    LogUtil.a(TAG, "update success.");
                    onBackPressed();
                } else {
                    LogUtil.a(TAG, "onclick status:", Integer.valueOf(i));
                }
            } else {
                if (nsn.o()) {
                    LogUtil.a(TAG, "isFastClick");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                checkDeviceStatus();
            }
        } else {
            LogUtil.h(TAG_RELEASE, "no click");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showWifiOtaActivationPromptDialog() {
        cqh.c().Lh_(this.mainActivity);
    }

    private void checkDeviceStatus() {
        WifiDeviceGetDeviceStatusReq wifiDeviceGetDeviceStatusReq = new WifiDeviceGetDeviceStatusReq();
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            wifiDeviceGetDeviceStatusReq.setDevId(this.mWiFiDevice.b().a());
            jbs.a(BaseApplication.getContext()).a(wifiDeviceGetDeviceStatusReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiOtaUpdateFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                public final void operationResult(Object obj, String str, boolean z) {
                    HagridWiFiOtaUpdateFragment.this.m192xb3e48030((WifiDeviceGetDeviceStatusRsp) obj, str, z);
                }
            });
        } else {
            LogUtil.h(TAG, "checkDeviceStatus device id is null");
        }
    }

    /* renamed from: lambda$checkDeviceStatus$1$com-huawei-health-device-ui-measure-fragment-HagridWiFiOtaUpdateFragment, reason: not valid java name */
    /* synthetic */ void m192xb3e48030(WifiDeviceGetDeviceStatusRsp wifiDeviceGetDeviceStatusRsp, String str, boolean z) {
        LogUtil.a(TAG, "get device status: ", wifiDeviceGetDeviceStatusRsp, ", text: ", str, ", is success: ", Boolean.valueOf(z));
        if (this.mHandler == null) {
            LogUtil.h(TAG, "checkDeviceOnlineStatus :mHandler is null");
            return;
        }
        Message obtain = Message.obtain();
        if (wifiDeviceGetDeviceStatusRsp != null && !TextUtils.isEmpty(wifiDeviceGetDeviceStatusRsp.getStatus()) && wifiDeviceGetDeviceStatusRsp.getStatus().equals("online")) {
            obtain.what = 5;
        } else {
            obtain.what = 6;
        }
        this.mHandler.sendMessage(obtain);
    }

    private void restartUpgrade() {
        cld cldVar = this.mWeightInteractor;
        boolean b = cldVar != null ? cldVar.b() : false;
        LogUtil.a(TAG, "restartUpgrade device connect :", Boolean.valueOf(b));
        Fragment selectFragment = getSelectFragment(HagridWifiUpdateGuideFragment.class);
        Object[] objArr = new Object[2];
        objArr[0] = " fragment is null ? ";
        objArr[1] = Boolean.valueOf(selectFragment == null);
        LogUtil.a(TAG, objArr);
        if (b) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(GET_NETWORK_DEVICE_VERSION, true);
            EventBus.d(new EventBus.b("get_scale_version_code", bundle));
            this.mRoundProgress.b();
            setCheckVersionUpdateEnable(1);
            return;
        }
        if (selectFragment != null) {
            popupFragment(HagridWifiUpdateGuideFragment.class);
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putString("productId", this.mProductId);
        bundle2.putString("version", this.mNewVer);
        bundle2.putString(CER_VERSION, this.mNowVersion);
        bundle2.putString(UPDATE_NODES, this.mReleaseNote);
        bundle2.putString("devId", this.mWiFiDevice.d());
        bundle2.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        HagridWifiUpdateGuideFragment hagridWifiUpdateGuideFragment = new HagridWifiUpdateGuideFragment();
        hagridWifiUpdateGuideFragment.setArguments(bundle2);
        switchFragment(hagridWifiUpdateGuideFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckVersionUpdateEnable(int i) {
        if (i == 1) {
            this.mOtaUpgradeTipOneTv.setVisibility(8);
            this.mOtaUpgradeTipTwoTv.setVisibility(8);
            this.mRoundProgress.b();
            this.mNoNewVersionTv.setText(R.string._2130841851_res_0x7f0210fb);
            this.mCheckVersionUpdate.setAlpha(VESION_UPDATE_ALPHA);
            this.mCheckVersionUpdate.setEnabled(false);
            this.mCheckVersionUpdate.setText(R.string._2130841463_res_0x7f020f77);
        } else if (i == 2) {
            this.mOtaUpgradeTipOneTv.setVisibility(8);
            this.mOtaUpgradeTipTwoTv.setVisibility(8);
            this.mNoNewVersionTv.setText(R.string._2130841497_res_0x7f020f99);
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string.IDS_device_show_complete);
        } else if (i == 3) {
            this.mOtaUpgradeTipOneTv.setVisibility(0);
            this.mOtaUpgradeTipTwoTv.setVisibility(0);
            this.mNoNewVersionTv.setText(R.string.IDS_device_ota_upgrade_title);
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string._2130841467_res_0x7f020f7b);
        } else {
            this.mOtaUpgradeTipOneTv.setVisibility(8);
            this.mOtaUpgradeTipTwoTv.setVisibility(8);
            LogUtil.h(TAG_RELEASE, "check is empty");
        }
        this.mUpgradeStatus = i;
    }

    static class WifiHandler extends StaticHandler<HagridWiFiOtaUpdateFragment> {
        private WifiHandler(HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment) {
            super(hagridWiFiOtaUpdateFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment, Message message) {
            if (hagridWiFiOtaUpdateFragment.isDestroy()) {
            }
            if (!hagridWiFiOtaUpdateFragment.isAdded()) {
                LogUtil.h(HagridWiFiOtaUpdateFragment.TAG_RELEASE, "WifiHandler mFragment is not add");
                return;
            }
            ReleaseLogUtil.e(HagridWiFiOtaUpdateFragment.TAG_RELEASE, "WifiHandler what: ", Integer.valueOf(message.what));
            switch (message.what) {
                case 1:
                    hagridWiFiOtaUpdateFragment.mRoundProgress.c();
                    hagridWiFiOtaUpdateFragment.setCheckVersionUpdateEnable(1);
                    break;
                case 2:
                    hagridWiFiOtaUpdateFragment.checkUpdateResult();
                    break;
                case 3:
                    hagridWiFiOtaUpdateFragment.mLatestVersionLayout.setEnabled(true);
                    hagridWiFiOtaUpdateFragment.mRoundProgress.c();
                    hagridWiFiOtaUpdateFragment.setCheckVersionUpdateEnable(3);
                    break;
                case 4:
                    HagridWiFiOtaUpdateFragment.updateEnd(hagridWiFiOtaUpdateFragment);
                    break;
                case 5:
                    hagridWiFiOtaUpdateFragment.startUpgrade();
                    break;
                case 6:
                    hagridWiFiOtaUpdateFragment.showWifiOtaActivationPromptDialog();
                    break;
                default:
                    LogUtil.h(HagridWiFiOtaUpdateFragment.TAG, "WifiHandler what is other");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateEnd(HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment) {
        csc.d().c(hagridWiFiOtaUpdateFragment.mWiFiDevice.d(), false);
        hagridWiFiOtaUpdateFragment.mLatestVersionLayout.setEnabled(true);
        hagridWiFiOtaUpdateFragment.mLatestVersionLayout.setVisibility(8);
        hagridWiFiOtaUpdateFragment.mNowVersionTv.setText(hagridWiFiOtaUpdateFragment.mNewVer);
        hagridWiFiOtaUpdateFragment.mRoundProgress.c();
        hagridWiFiOtaUpdateFragment.setCheckVersionUpdateEnable(2);
        Map<String, String> e = csc.d().e(hagridWiFiOtaUpdateFragment.mWiFiDevice.d());
        e.put("status", "1");
        csc.d().d(hagridWiFiOtaUpdateFragment.mWiFiDevice.d(), e);
        csf.c(hagridWiFiOtaUpdateFragment.mWiFiDevice.d(), (CommBaseCallbackInterface) null);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        if (this.mBroadcastReceiver != null) {
            this.mainActivity.unregisterReceiver(this.mBroadcastReceiver);
        }
        popupFragment(HagridDeviceManagerFragment.class);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkUpdateResult() {
        cuf.e(this.mainActivity.getApplicationContext()).e(this.mUniqueId, this.mBaseCallback);
    }

    class OtaStatusCallback implements BaseCallbackInterface {
        private WeakReference<HagridWiFiOtaUpdateFragment> mActivity;

        private OtaStatusCallback(HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment) {
            this.mActivity = new WeakReference<>(hagridWiFiOtaUpdateFragment);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onSuccess(Object obj) {
            if (isDestroy() || HagridWiFiOtaUpdateFragment.this.mHandler == null) {
                LogUtil.h(HagridWiFiOtaUpdateFragment.TAG, "onSuccess: isDestroy or mHandler is null");
                return;
            }
            if (!(obj instanceof ContentValues)) {
                LogUtil.h(HagridWiFiOtaUpdateFragment.TAG, "onSuccess: Incorrect parameter type.");
                return;
            }
            Message obtain = Message.obtain();
            if (HagridWiFiOtaUpdateFragment.this.isOtaSuccess((ContentValues) obj)) {
                obtain.what = 4;
            } else {
                obtain.what = 3;
            }
            HagridWiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
        }

        private boolean isDestroy() {
            WeakReference<HagridWiFiOtaUpdateFragment> weakReference = this.mActivity;
            if (weakReference == null) {
                return true;
            }
            HagridWiFiOtaUpdateFragment hagridWiFiOtaUpdateFragment = weakReference.get() instanceof HagridWiFiOtaUpdateFragment ? this.mActivity.get() : null;
            return hagridWiFiOtaUpdateFragment == null || hagridWiFiOtaUpdateFragment.isDestory();
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onFailure(int i) {
            Message obtain = Message.obtain();
            obtain.what = 3;
            HagridWiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
        }

        @Override // com.huawei.health.device.wifi.interfaces.BaseCallbackInterface
        public void onStatus(int i) {
            if (HagridWiFiOtaUpdateFragment.this.mainActivity != null) {
                HagridWiFiOtaUpdateFragment.this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiOtaUpdateFragment$OtaStatusCallback$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        HagridWiFiOtaUpdateFragment.OtaStatusCallback.this.m194xce346805();
                    }
                });
            }
            Message obtain = Message.obtain();
            obtain.what = 3;
            HagridWiFiOtaUpdateFragment.this.mHandler.sendMessage(obtain);
        }

        /* renamed from: lambda$onStatus$0$com-huawei-health-device-ui-measure-fragment-HagridWiFiOtaUpdateFragment$OtaStatusCallback, reason: not valid java name */
        /* synthetic */ void m194xce346805() {
            nrh.b(HagridWiFiOtaUpdateFragment.this.mainActivity, R.string.IDS_device_wifi_not_network);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isOtaSuccess(ContentValues contentValues) {
        String asString = contentValues.getAsString("fwCurVer");
        String asString2 = contentValues.getAsString("status");
        if (TextUtils.isEmpty(asString) || TextUtils.isEmpty(asString2)) {
            LogUtil.h(TAG, "isOtaSuccess nowVersionDevice or status is empty");
            return false;
        }
        if (!TextUtils.equals(asString, this.mNowVersion)) {
            return "3".equals(asString2);
        }
        LogUtil.h(TAG, "isOtaSuccess: device version No change after the ota upgrade");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestory() {
        FragmentActivity activity = getActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.h(TAG, "DeviceMainActivity is Destroyed");
            return true;
        }
        if (isAdded()) {
            return false;
        }
        LogUtil.h(TAG, "MyHandler mFragment is not add");
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        LogUtil.a(TAG, "onDestroyView");
        RoundProgressView roundProgressView = this.mRoundProgress;
        if (roundProgressView != null) {
            roundProgressView.c();
        }
        super.onDestroyView();
    }
}
