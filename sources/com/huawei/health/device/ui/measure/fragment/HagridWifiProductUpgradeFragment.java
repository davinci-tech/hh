package com.huawei.health.device.ui.measure.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
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
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.MeasureController;
import com.huawei.health.device.open.MeasureKit;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.util.RoundProgressView;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.networkclient.HealthEngineRequestManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cfl;
import defpackage.cgk;
import defpackage.cgt;
import defpackage.cpa;
import defpackage.cpz;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.ctv;
import defpackage.cuf;
import defpackage.dcr;
import defpackage.dcz;
import defpackage.dij;
import defpackage.jbs;
import defpackage.koq;
import defpackage.mst;
import defpackage.msx;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsy;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class HagridWifiProductUpgradeFragment extends BaseFragment implements View.OnClickListener {
    private static final float ALPHA_VALUE_STATUS_DONG = 0.3f;
    private static final float ALPHA_VALUE_STATUS_NOMAL = 1.0f;
    private static final String CURRENT_VERSION = "cer_version";
    private static final int DEFAULT_STATUS = -1;
    private static final int DEFAULT_VALUE = 0;
    private static final String DEVICE_ID = "devId";
    private static final String DEVICE_PAIRING_GUIDE_ACTIVITY = "com.huawei.ui.device.activity.adddevice.PairingGuideActivity";
    private static final int FAILED_REASON_NOT_FOUND = 0;
    private static final String GET_NETWORK_DEVICE_VERSION = "getNetworkDeviceVersion";
    private static final String KIND_ID = "kind_id";
    private static final int MSG_CHECKING_VERSION = 10;
    private static final int MSG_EXIST_NEW_VERSION = 3;
    private static final int MSG_GET_VERSION_FAIL = 2;
    private static final int MSG_GET_VERSION_SUCCESS = 1;
    private static final int MSG_NOT_EXIST_DEVICE = 4;
    private static final int MSG_SENDGRS_SUCCESS = 11;
    private static final String PAIR_GUIDE = "pair_guide";
    private static final String TAG = "HagridWifiProductUpgradeFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridWifiProductUpgradeFragment";
    private static final String UPDATE_NODES = "update_nodes";
    private static final String UUID_LIST = "uuid_list";
    private static final String VERSION = "version";
    private String mDeviceSoftVersion;
    private ImageView mNextImage;
    private String mUniqueId;
    private ImageView mVersionLogo;
    private VersionMgrApi mVersionMgrApi;
    private String mProductId = null;
    private String mNewVer = null;
    private HealthTextView mNowVersionTv = null;
    private HealthTextView mNoNewVersionTv = null;
    private HealthTextView mNewVersionTv = null;
    private LinearLayout mLatestVersionLayout = null;
    private String mNowVersion = null;
    private String mReleaseNote = null;
    private WifiHandler mHandler = null;
    private RoundProgressView mRoundProgress = null;
    private HealthButton mCheckVersionUpdate = null;
    private ctk mWiFiDevice = null;
    private boolean mIsExistNewVer = false;
    private boolean isFromProductView = false;
    private ContentValues mDeviceInfo = null;
    private String mDeviceBtMac = null;
    private boolean mIsConnected = false;
    private View.OnClickListener positiveListener = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Fragment selectFragment = HagridWifiProductUpgradeFragment.this.getSelectFragment(DeviceCategoryFragment.class);
            if (HagridWifiProductUpgradeFragment.this.isFromProductView && selectFragment != null) {
                HagridWifiProductUpgradeFragment.this.popupFragment(DeviceCategoryFragment.class);
            } else {
                dcr switchProductGroupItem = HagridWifiProductUpgradeFragment.this.switchProductGroupItem(ResourceManager.e().a().e(), HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
                if (HagridWifiProductUpgradeFragment.this.mainActivity == null) {
                    LogUtil.h(HagridWifiProductUpgradeFragment.TAG_RELEASE, "positiveListener mainActivity is null");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                } else if (switchProductGroupItem != null) {
                    LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "item: ", switchProductGroupItem.toString());
                    HagridWifiProductUpgradeFragment.this.startPairingGuideActivity();
                } else {
                    LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "WEIGHT ProductGroup is error");
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    };
    private IHealthDeviceCallback mCallback = new IHealthDeviceCallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment.2
        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, List<HealthData> list) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
            if (healthDevice != null) {
                LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "mCallback onStatusChanged device name is ", healthDevice.getDeviceName());
            }
            LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "mCallback onStatusChanged status is ", Integer.valueOf(i));
            if (i == 3) {
                HagridWifiProductUpgradeFragment.this.mIsConnected = false;
            } else if (i == 2) {
                HagridWifiProductUpgradeFragment.this.mIsConnected = true;
            } else {
                HagridWifiProductUpgradeFragment.this.mIsConnected = false;
                LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "onStatusChanged: status: ", Integer.valueOf(i));
            }
        }
    };
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "mBroadcastReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "onReceive: action: ", action);
            if ("action_app_check_new_version_state".equals(action)) {
                HagridWifiProductUpgradeFragment.this.upDateLayout(intent);
                cpz.Ks_(HagridWifiProductUpgradeFragment.this.mProductId, HagridWifiProductUpgradeFragment.this.mNowVersion, HagridWifiProductUpgradeFragment.this.mDeviceInfo);
            }
        }
    };
    private EventBus.ICallback mEventCallback = new EventBus.ICallback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment.4
        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            if ("set_scale_version_code".equals(bVar.e())) {
                LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "mEventCallback get version code success");
                HagridWifiProductUpgradeFragment.this.startCheckVersion(bVar.Km_());
            } else {
                if ("event_bus_send_ota_url_success".equals(bVar.e())) {
                    LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "[grs][ota update] grs callback success");
                    Message obtain = Message.obtain();
                    obtain.what = 11;
                    HagridWifiProductUpgradeFragment.this.mHandler.sendMessage(obtain);
                    return;
                }
                if ("get_version_code_fail".equals(bVar.e())) {
                    LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "mEventCallback get version code fail");
                    Message obtain2 = Message.obtain();
                    obtain2.what = 2;
                    HagridWifiProductUpgradeFragment.this.mHandler.sendMessage(obtain2);
                    return;
                }
                LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "[grs][ota update] grs callback for another");
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void upDateLayout(Intent intent) {
        int intExtra;
        Map<String, String> e = csc.d().e(this.mWiFiDevice.d());
        int intExtra2 = intent.getIntExtra("state", 0);
        Message obtain = Message.obtain();
        if (intExtra2 == 49) {
            intExtra = intent.getIntExtra("result", 0);
            if (intExtra == 0) {
                this.mNowVersion = this.mDeviceSoftVersion;
                obtain.what = 1;
            } else {
                obtain.what = 2;
            }
            this.mHandler.sendMessage(obtain);
        } else if (intExtra2 != 50) {
            if (intExtra2 == 53) {
                String stringExtra = intent.getStringExtra("content");
                this.mReleaseNote = stringExtra;
                if (e != null) {
                    e.put("releaseNote", stringExtra);
                }
                csc.d().d(this.mWiFiDevice.d(), e);
            } else {
                LogUtil.a(TAG, "upDateLayout default case ", Integer.valueOf(intExtra2));
            }
            intExtra = -1;
        } else {
            this.mNewVer = intent.getStringExtra("content");
            intExtra = intent.getIntExtra("result", 0);
            this.mNowVersion = this.mDeviceSoftVersion;
            if (TextUtils.isEmpty(this.mNewVer) || TextUtils.isEmpty(this.mNowVersion)) {
                obtain.what = 2;
                this.mHandler.sendMessage(obtain);
            } else {
                if (!this.mNewVer.equals(this.mNowVersion)) {
                    obtain.what = 3;
                    this.mHandler.sendMessage(obtain);
                    LogUtil.a(TAG, "exist new version curver:", this.mNowVersion, ",new ver:", this.mNewVer);
                }
                if (e != null) {
                    e.put("fwNewVer", this.mNewVer);
                    e.put("fwCurVer", this.mDeviceSoftVersion);
                }
                csc.d().d(this.mWiFiDevice.d(), e);
            }
        }
        Integer valueOf = Integer.valueOf(intExtra2);
        Integer valueOf2 = Integer.valueOf(intExtra);
        String str = this.mNewVer;
        LogUtil.a(TAG, "upDateLayout: state = ", valueOf, ",result = ", valueOf2, ",content = ", str, ",upDateLayout mNewVer:", str, ",mNowVersion:", this.mNowVersion, ",mReleaseNote:", this.mReleaseNote);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        unregisterBroadcastReceiver();
        return super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startCheckVersion(Intent intent) {
        String str;
        Map<String, String> map;
        if (intent != null && cpa.ae(this.mProductId)) {
            this.mDeviceSoftVersion = intent.getStringExtra("bleVersion");
            this.mDeviceBtMac = intent.getStringExtra(HealthEngineRequestManager.PARAMS_DEVICE_SN);
        }
        LogUtil.a(TAG, "startCheckVersion mDeviceSoftVersion=", this.mDeviceSoftVersion);
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null) {
            str = ctkVar.d();
            map = csc.d().e(str);
        } else {
            str = "";
            map = null;
        }
        if (TextUtils.isEmpty(this.mDeviceSoftVersion) && map != null) {
            this.mDeviceSoftVersion = map.get("fwCurVer");
            if (this.mWiFiDevice != null && cpa.ae(this.mProductId)) {
                LogUtil.a(TAG, "startCheckVersion getSerialNumber.");
                this.mDeviceBtMac = this.mWiFiDevice.getSerialNumber();
            }
        }
        this.mVersionMgrApi.checkScaleNewVersionService(this.mProductId, this.mDeviceSoftVersion, this.mDeviceBtMac, false, this.mUniqueId);
        if (map != null) {
            map.put("fwCurVer", this.mDeviceSoftVersion);
            csc.d().d(str, map);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        LogUtil.c(TAG, "onCreateView child: ", this.child, ",view:", viewGroup2);
        this.child = layoutInflater.inflate(R.layout.wifi_upgrade_layout, viewGroup, false);
        this.mIsExistNewVer = getArguments().getBoolean("is_exist_new_version");
        this.mVersionMgrApi = (VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class);
        initView();
        initDataAndReceiver();
        getConnected();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void getConnected() {
        MeasurableDevice c;
        Bundle bundle = new Bundle();
        bundle.putInt("type", -1);
        bundle.putString("productId", this.mProductId);
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            c = ceo.d().d(this.mUniqueId, false);
        } else {
            c = ceo.d().c(this.mProductId);
        }
        if (c != null) {
            dcz d = ResourceManager.e().d(this.mProductId);
            if (d == null) {
                LogUtil.h(TAG, "getConnected productInfo is null");
                return;
            }
            MeasureKit e = cfl.a().e(d.s());
            if (e == null) {
                LogUtil.h(TAG, "getConnected kit is null");
                return;
            }
            MeasureController measureController = e.getMeasureController();
            if (measureController != null) {
                measureController.prepare(c, this.mCallback, bundle);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPairingGuideActivity() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            health.compact.a.util.LogUtil.c(TAG, "DeviceMainActivity is null");
            return;
        }
        List<msx> c = mst.a().c(this.mProductId);
        if (koq.b(c)) {
            health.compact.a.util.LogUtil.c(TAG, "DevicePluginInfoBean is null");
            return;
        }
        msx msxVar = c.get(0);
        String m = msxVar.m();
        if (TextUtils.isEmpty(m)) {
            health.compact.a.util.LogUtil.c(TAG, "pairGuid is empty");
            return;
        }
        String f = msxVar.f();
        if (TextUtils.isEmpty(f)) {
            health.compact.a.util.LogUtil.c(TAG, "kindId is empty");
            return;
        }
        List<String> k = msxVar.k();
        if (koq.b(k)) {
            health.compact.a.util.LogUtil.c(TAG, "uuidList is empty");
            return;
        }
        Iterator<String> it = k.iterator();
        while (it.hasNext()) {
            health.compact.a.util.LogUtil.c(TAG, "uuidList uuid = ", it.next());
        }
        health.compact.a.util.LogUtil.c(TAG, "pairGuide = ", m, "kindId = ", f);
        Intent intent = new Intent();
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(activity, DEVICE_PAIRING_GUIDE_ACTIVITY);
        if (k instanceof ArrayList) {
            intent.putStringArrayListExtra(UUID_LIST, (ArrayList) k);
        }
        intent.putExtra("is_invalidation", true);
        intent.putExtra(KIND_ID, f);
        intent.putExtra(PAIR_GUIDE, m);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "startPairingGuideActivity startActivity ActivityNotFoundException occur.");
        }
        activity.finish();
    }

    private void unregisterBroadcastReceiver() {
        EventBus.a(this.mEventCallback, "set_scale_version_code", "event_bus_send_ota_url_success", "get_version_code_fail");
        if (this.mBroadcastReceiver == null) {
            return;
        }
        try {
            this.mainActivity.unregisterReceiver(this.mBroadcastReceiver);
            this.mBroadcastReceiver = null;
        } catch (IllegalArgumentException e) {
            LogUtil.a(TAG, e.getMessage());
        }
        cgk.d().e(this.mCallback);
        cgt.e().b(this.mCallback);
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_scale_device_firmware_version));
        this.mNowVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.now_version_nodes_tv);
        this.mNoNewVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.no_new_version_tv);
        this.mNewVersionTv = (HealthTextView) nsy.cMd_(this.child, R.id.new_version_tv);
        this.mLatestVersionLayout = (LinearLayout) nsy.cMd_(this.child, R.id.latest_version_button);
        this.mRoundProgress = (RoundProgressView) nsy.cMd_(this.child, R.id.version_update_anim);
        this.mCheckVersionUpdate = (HealthButton) nsy.cMd_(this.child, R.id.check_version_update);
        this.mVersionLogo = (ImageView) nsy.cMd_(this.child, R.id.image_logo);
        this.mNextImage = (ImageView) nsy.cMd_(this.child, R.id.image_device_next);
        this.mLatestVersionLayout.setOnClickListener(this);
        this.mCheckVersionUpdate.setOnClickListener(this);
        if (this.mIsExistNewVer) {
            showLatestVersionLayout(true);
        } else {
            showLatestVersionLayout(false);
        }
        if (LanguageUtil.bc(this.mainActivity)) {
            this.mNextImage.setBackground(getResources().getDrawable(R.drawable.wifi_device_public_next_left));
        } else {
            this.mNextImage.setBackground(getResources().getDrawable(R.drawable.wifi_device_public_next_right));
        }
    }

    private void setRightTitleButton() {
        this.mCustomTitleBar.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131428308_res_0x7f0b03d4), nsf.h(R.string._2130841425_res_0x7f020f51));
        this.mCustomTitleBar.setRightButtonClickable(true);
        this.mCustomTitleBar.setRightButtonVisibility(0);
        this.mCustomTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWifiProductUpgradeFragment.this.m196x68c0fb01(view);
            }
        });
    }

    /* renamed from: lambda$setRightTitleButton$0$com-huawei-health-device-ui-measure-fragment-HagridWifiProductUpgradeFragment, reason: not valid java name */
    /* synthetic */ void m196x68c0fb01(View view) {
        if (this.mWiFiDevice != null) {
            HagridOtaSettingFragment hagridOtaSettingFragment = new HagridOtaSettingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("deviceId", this.mWiFiDevice.d());
            hagridOtaSettingFragment.setArguments(bundle);
            switchFragment(hagridOtaSettingFragment);
        } else {
            LogUtil.h(TAG, "setRightTitleButton mWiFiDevice is null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void checkDevice() {
        if (this.mWiFiDevice == null) {
            LogUtil.h(TAG_RELEASE, "checkDevice mWiFiDevice is null");
            return;
        }
        String devId = getDevId();
        if (TextUtils.isEmpty(devId)) {
            LogUtil.h(TAG_RELEASE, "checkDevice devId is null");
            return;
        }
        WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
        wifiDeviceGetWifiDeviceInfoReq.setDevId(devId);
        jbs.a(this.mainActivity).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                HagridWifiProductUpgradeFragment.this.m195xfafc2150((WifiDeviceGetWifiDeviceInfoRsp) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$checkDevice$1$com-huawei-health-device-ui-measure-fragment-HagridWifiProductUpgradeFragment, reason: not valid java name */
    /* synthetic */ void m195xfafc2150(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
        int i;
        String str2;
        if (!z) {
            if (wifiDeviceGetWifiDeviceInfoRsp != null && wifiDeviceGetWifiDeviceInfoRsp.getResultCode() != null) {
                i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
                str2 = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
                if (i == 112000000) {
                    LogUtil.h(TAG, TAG, "device already not exists");
                    this.mHandler.sendEmptyMessage(4);
                }
            } else {
                this.mHandler.sendEmptyMessage(4);
                i = Constants.CODE_UNKNOWN_ERROR;
                str2 = "unknown error";
            }
            LogUtil.a(TAG, "checkDevice() errCode: ", Integer.valueOf(i), ",resultDesc: ", str2);
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp != null && wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
            LogUtil.h(TAG_RELEASE, "checkDevice device already not exists");
            this.mHandler.sendEmptyMessage(4);
        } else {
            LogUtil.a(TAG_RELEASE, "checkDevice device already exists");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public dcr switchProductGroupItem(ArrayList<dcr> arrayList, String str) {
        Iterator<dcr> it = arrayList.iterator();
        dcr dcrVar = null;
        while (it.hasNext()) {
            dcr next = it.next();
            if (next.c() != null && next.c().name() != null && next.c().name().equals(str)) {
                LogUtil.a(TAG, " item.kind.name: ", next.c().name(), " deviceType: ", str);
                dcrVar = next;
            }
        }
        return dcrVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoDeviceDialog() {
        Activity activity = this.mainActivity;
        ctk ctkVar = this.mWiFiDevice;
        String d = ctkVar == null ? null : ctkVar.d();
        View.OnClickListener onClickListener = this.positiveListener;
        csf.LF_(activity, d, onClickListener, onClickListener);
    }

    private void initData() {
        if (this.mWiFiDevice == null) {
            MeasurableDevice measurableDevice = null;
            String string = getArguments() != null ? getArguments().getString(DEVICE_ID) : null;
            ctk c = !TextUtils.isEmpty(string) ? ctq.c(string) : null;
            if (c != null && !TextUtils.isEmpty(c.getSerialNumber())) {
                measurableDevice = ceo.d().c(c.getSerialNumber(), false);
            }
            if (measurableDevice != null && (measurableDevice instanceof ctk)) {
                this.mWiFiDevice = (ctk) measurableDevice;
            }
        }
        if (this.mWiFiDevice == null) {
            LogUtil.h(TAG_RELEASE, "wifi device is null");
            this.mHandler.sendEmptyMessage(4);
            return;
        }
        if (this.mDeviceInfo == null) {
            setMyDeviceInfo();
        }
        this.isFromProductView = getArguments().getBoolean("fromProductView", false);
        if (this.mIsExistNewVer) {
            checkDevice();
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString(CURRENT_VERSION);
            this.mReleaseNote = getArguments().getString(UPDATE_NODES);
            Message obtain = Message.obtain();
            obtain.what = 3;
            this.mHandler.sendMessage(obtain);
        } else {
            detectionExistNewVersion();
        }
        String str = this.mProductId;
        if (str != null) {
            dij.Vc_(this.mVersionLogo, str);
        } else {
            LogUtil.h(TAG, "mProductId isEmpty");
        }
        cuf.e(this.mainActivity.getApplicationContext()).b();
        setRightTitleButton();
    }

    private void setMyDeviceInfo() {
        LogUtil.h(TAG, "initData mDeviceInfo is null");
        ContentValues contentValues = new ContentValues();
        this.mDeviceInfo = contentValues;
        contentValues.put("productId", this.mWiFiDevice.getProductId());
        this.mDeviceInfo.put("uniqueId", this.mWiFiDevice.getUniqueId());
        this.mDeviceInfo.put("deviceId", this.mWiFiDevice.d());
        this.mDeviceInfo.put("sn", this.mWiFiDevice.getSerialNumber());
    }

    private void initDataAndReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_app_check_new_version_state");
        BroadcastManagerUtil.bFC_(this.mainActivity, this.mBroadcastReceiver, intentFilter, LocalBroadcast.c, null);
        EventBus.d(this.mEventCallback, 0, "set_scale_version_code", "event_bus_send_ota_url_success", "get_version_code_fail");
        this.mHandler = new WifiHandler(this);
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            LogUtil.a(TAG, "mProductId:", this.mProductId);
        }
        if (this.mDeviceInfo != null && !TextUtils.isEmpty(this.mUniqueId)) {
            MeasurableDevice d = ceo.d().d(this.mUniqueId, false);
            if (d != null && (d instanceof ctk)) {
                this.mWiFiDevice = (ctk) d;
                return;
            } else {
                LogUtil.h(TAG, "mUniqueId can not get WiFiDevice");
                return;
            }
        }
        LogUtil.h(TAG, "uniqueId is empty, can't get WiFiDevice");
    }

    private void detectionExistNewVersion() {
        this.mRoundProgress.b();
        setCheckVersionUpdateEnable(10);
        Context context = BaseApplication.getContext();
        if (!ctv.d(context)) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            this.mHandler.sendMessage(obtain);
            nrh.b(context, R.string.IDS_device_wifi_not_network);
            return;
        }
        EventBus.d(new EventBus.b("event_bus_send_ota_url"));
        getDeviceStatus();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.a(this.mEventCallback, "set_scale_version_code", "event_bus_send_ota_url_success", "get_version_code_fail");
        this.mRoundProgress = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDevId() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            LogUtil.a(TAG, "device info");
            String a2 = this.mWiFiDevice.b().a();
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
            LogUtil.h(TAG, "devId is null");
            return null;
        }
        LogUtil.h(TAG, "device id is null");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLatestVersionLayout(boolean z) {
        this.mLatestVersionLayout.setVisibility(z ? 0 : 8);
    }

    private void getDeviceStatus() {
        LogUtil.a(TAG, "getDeviceStatus mIsConnected: ", Boolean.valueOf(this.mIsConnected));
        showLatestVersionLayout(false);
        if (this.mIsConnected || this.mWiFiDevice == null) {
            Bundle bundle = new Bundle();
            bundle.putBoolean(GET_NETWORK_DEVICE_VERSION, true);
            EventBus.d(new EventBus.b("get_scale_version_code", bundle));
            return;
        }
        LogUtil.a(TAG, "mIsConnected is false or mWiFiDevice not null");
        String str = csc.d().e(this.mWiFiDevice.d()).get("fwCurVer");
        if (TextUtils.isEmpty(str)) {
            str = cpa.k(this.mUniqueId);
            LogUtil.a(TAG, "get device version by uniqueId");
        }
        if (!TextUtils.isEmpty(str)) {
            this.mDeviceSoftVersion = str;
            if (cpa.ae(this.mProductId)) {
                String serialNumber = this.mWiFiDevice.getSerialNumber();
                this.mDeviceBtMac = serialNumber;
                this.mVersionMgrApi.checkScaleNewVersionService(this.mProductId, this.mDeviceSoftVersion, serialNumber, false, this.mUniqueId);
                return;
            }
            LogUtil.h(TAG, "getDeviceStatus isHuaweiWspScaleProduct is false");
            return;
        }
        LogUtil.h(TAG, "getDeviceStatus nowVersion is null");
        EventBus.d(new EventBus.b("get_scale_version_code"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDestroy() {
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing() && !activity.isDestroyed()) {
            return false;
        }
        LogUtil.h(TAG_RELEASE, "DeviceMainActivity is Destroyed");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0091  */
    @Override // android.view.View.OnClickListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onClick(android.view.View r6) {
        /*
            r5 = this;
            int r0 = r6.getId()
            r1 = 2131565682(0x7f0d1c72, float:1.8756884E38)
            if (r0 != r1) goto Le
            r5.latestVersion()
            goto Lac
        Le:
            int r0 = r6.getId()
            r1 = 2131559971(0x7f0d0623, float:1.8745301E38)
            java.lang.String r2 = "HagridWifiProductUpgradeFragment"
            if (r0 != r1) goto La3
            java.lang.Object r0 = r6.getTag()
            java.lang.String r1 = "check version update tag: "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            r1 = 3
            if (r0 == 0) goto L40
            java.lang.String r3 = r0.toString()     // Catch: java.lang.NumberFormatException -> L32
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L32
            goto L41
        L32:
            r3 = move-exception
            java.lang.String r4 = "onClick exception: "
            java.lang.String r3 = r3.getMessage()
            java.lang.Object[] r3 = new java.lang.Object[]{r4, r3}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r3)
        L40:
            r3 = r1
        L41:
            if (r0 == 0) goto L89
            if (r3 == r1) goto L46
            goto L89
        L46:
            com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment r0 = new com.huawei.health.device.ui.measure.fragment.HagridWifiUpdateGuideFragment
            r0.<init>()
            java.lang.String r1 = "onClick check version: "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r1)
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
            java.lang.String r2 = "commonDeviceInfo"
            android.content.ContentValues r3 = r5.mDeviceInfo
            r1.putParcelable(r2, r3)
            java.lang.String r2 = "version"
            java.lang.String r3 = r5.mNewVer
            r1.putString(r2, r3)
            java.lang.String r2 = "cer_version"
            java.lang.String r3 = r5.mNowVersion
            r1.putString(r2, r3)
            java.lang.String r2 = "update_nodes"
            java.lang.String r3 = r5.mReleaseNote
            r1.putString(r2, r3)
            ctk r2 = r5.mWiFiDevice
            java.lang.String r2 = r2.d()
            java.lang.String r3 = "devId"
            r1.putString(r3, r2)
            r0.setArguments(r1)
            r5.switchFragment(r0)
            goto Lac
        L89:
            boolean r0 = r5.mIsConnected
            if (r0 == 0) goto L91
            r5.detectionExistNewVersion()
            goto Lac
        L91:
            android.app.Activity r0 = r5.mainActivity
            r1 = 2130842672(0x7f021430, float:1.7290446E38)
            defpackage.nrh.c(r0, r1)
            java.lang.String r0 = "onClick mIsConnected is false"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r0)
            goto Lac
        La3:
            java.lang.String r0 = "no click"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.h(r2, r0)
        Lac:
            com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation.clickOnView(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.device.ui.measure.fragment.HagridWifiProductUpgradeFragment.onClick(android.view.View):void");
    }

    private void latestVersion() {
        HagridWifiVersionDetailsFragment hagridWifiVersionDetailsFragment = new HagridWifiVersionDetailsFragment();
        LogUtil.a(TAG, "onClick last version: ", hagridWifiVersionDetailsFragment);
        Bundle bundle = new Bundle();
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        bundle.putString("version", this.mNewVer);
        bundle.putString(CURRENT_VERSION, this.mNowVersion);
        bundle.putString(UPDATE_NODES, this.mReleaseNote);
        bundle.putBoolean("show_update", true);
        hagridWifiVersionDetailsFragment.setArguments(bundle);
        switchFragment(hagridWifiVersionDetailsFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckVersionUpdateEnable(int i) {
        if (i == 1) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mNoNewVersionTv.setText(R.string._2130841484_res_0x7f020f8c);
        } else if (i == 2) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mNoNewVersionTv.setText(R.string.IDS_device_wifi_ota_check_fail);
            this.mCheckVersionUpdate.setText(R.string._2130841467_res_0x7f020f7b);
        } else if (i == 3) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string.IDS_device_manager_update_health);
            this.mNoNewVersionTv.setText(R.string._2130841460_res_0x7f020f74);
        } else if (i == 10) {
            this.mCheckVersionUpdate.setAlpha(ALPHA_VALUE_STATUS_DONG);
            this.mCheckVersionUpdate.setEnabled(false);
            this.mCheckVersionUpdate.setText(R.string._2130841456_res_0x7f020f70);
            this.mNoNewVersionTv.setText(R.string._2130841461_res_0x7f020f75);
        } else {
            LogUtil.h(TAG, "check is empty");
        }
        this.mCheckVersionUpdate.setTag(Integer.valueOf(i));
    }

    static class WifiHandler extends StaticHandler<HagridWifiProductUpgradeFragment> {
        WifiHandler(HagridWifiProductUpgradeFragment hagridWifiProductUpgradeFragment) {
            super(hagridWifiProductUpgradeFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridWifiProductUpgradeFragment hagridWifiProductUpgradeFragment, Message message) {
            if (hagridWifiProductUpgradeFragment.isDestroy()) {
                return;
            }
            if (!hagridWifiProductUpgradeFragment.isAdded()) {
                LogUtil.h(HagridWifiProductUpgradeFragment.TAG_RELEASE, "WifiHandler mFragment is not add");
                return;
            }
            if (message == null) {
                LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "msg is null");
                return;
            }
            LogUtil.a(HagridWifiProductUpgradeFragment.TAG, "WifiHandler what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (hagridWifiProductUpgradeFragment.mNowVersion != null) {
                    hagridWifiProductUpgradeFragment.mNowVersionTv.setText(hagridWifiProductUpgradeFragment.mNowVersion);
                }
                csc.d().c(hagridWifiProductUpgradeFragment.getDevId(), false);
                hagridWifiProductUpgradeFragment.mRoundProgress.c();
                hagridWifiProductUpgradeFragment.setCheckVersionUpdateEnable(1);
                return;
            }
            if (i == 2) {
                csc.d().c(hagridWifiProductUpgradeFragment.getDevId(), false);
                hagridWifiProductUpgradeFragment.mRoundProgress.c();
                hagridWifiProductUpgradeFragment.setCheckVersionUpdateEnable(2);
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    hagridWifiProductUpgradeFragment.showNoDeviceDialog();
                    return;
                } else if (i == 11) {
                    LogUtil.c(HagridWifiProductUpgradeFragment.TAG, "[ota][ota update] success");
                    return;
                } else {
                    LogUtil.h(HagridWifiProductUpgradeFragment.TAG, "WifiHandler what is other");
                    return;
                }
            }
            csc.d().c(hagridWifiProductUpgradeFragment.getDevId(), true);
            hagridWifiProductUpgradeFragment.mRoundProgress.c();
            hagridWifiProductUpgradeFragment.setCheckVersionUpdateEnable(3);
            hagridWifiProductUpgradeFragment.mNewVersionTv.setText(hagridWifiProductUpgradeFragment.mNewVer);
            hagridWifiProductUpgradeFragment.mNowVersionTv.setText(hagridWifiProductUpgradeFragment.mNowVersion);
            hagridWifiProductUpgradeFragment.showLatestVersionLayout(true);
            hagridWifiProductUpgradeFragment.mIsExistNewVer = true;
        }
    }
}
