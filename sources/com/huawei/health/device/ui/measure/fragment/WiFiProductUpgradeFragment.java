package com.huawei.health.device.ui.measure.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.util.RoundProgressView;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetWifiDeviceInfoRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ceo;
import defpackage.cpw;
import defpackage.csc;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.ctv;
import defpackage.cuf;
import defpackage.dcr;
import defpackage.jbs;
import defpackage.koq;
import defpackage.mst;
import defpackage.msx;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.nsy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class WiFiProductUpgradeFragment extends BaseFragment implements View.OnClickListener {
    private static final float ALPHA_VALUE_STATUS_DONG = 0.3f;
    private static final float ALPHA_VALUE_STATUS_NOMAL = 1.0f;
    private static final String CER_VERSION = "cer_version";
    private static final int DEFAULT_PADDING = 0;
    private static final String DEVICE_ID = "devId";
    private static final String DEVICE_PAIRING_GUIDE_ACTIVITY = "com.huawei.ui.device.activity.adddevice.PairingGuideActivity";
    private static final float FLOAT_WEIGHT_VALUE = 0.5f;
    private static final String KIND_ID = "kind_id";
    private static final int MSG_CHECKING_VERSION = 10;
    private static final int MSG_EXIST_NEW_VERSION = 3;
    private static final int MSG_GET_VERSION_FAIL = 2;
    private static final int MSG_GET_VERSION_SUCCESS = 1;
    private static final int MSG_NOT_EXIST_DEVICE = 4;
    private static final String PAIR_GUIDE = "pair_guide";
    private static final String PRODUCT_ID = "productId";
    private static final String TAG = "WiFiProductUpgradeFragment";
    private static final String UPDATE_NODES = "update_nodes";
    private static final String UUID_LIST = "uuid_list";
    private static final String VERSION = "version";
    private Context mContext;
    private ContentValues mDeviceInfo;
    private LinearLayout mUpdateLinearLayout;
    private RelativeLayout mUpdateRelativeLayout;
    private ImageView mVersionLogo;
    private String mProductId = null;
    private String mUniqueId = null;
    private String mNewVer = null;
    private HealthTextView mNowVersionText = null;
    private HealthTextView mNoNewVersionText = null;
    private HealthTextView mNewVersionText = null;
    private LinearLayout mLatestVersionLayout = null;
    private String mNowVersion = null;
    private String mReleaseNote = null;
    private WifiHandler mHandler = null;
    private RoundProgressView mRoundProgress = null;
    private HealthButton mCheckVersionUpdate = null;
    private ctk mWiFiDevice = null;
    private boolean mIsExistNewVer = false;
    private boolean mIsFromProductView = false;
    private View.OnClickListener positiveListener = new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiProductUpgradeFragment$$ExternalSyntheticLambda2
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            WiFiProductUpgradeFragment.this.m248x42da7332(view);
        }
    };

    /* renamed from: lambda$new$0$com-huawei-health-device-ui-measure-fragment-WiFiProductUpgradeFragment, reason: not valid java name */
    /* synthetic */ void m248x42da7332(View view) {
        Fragment selectFragment = getSelectFragment(DeviceCategoryFragment.class);
        if (this.mIsFromProductView && selectFragment != null) {
            popupFragment(DeviceCategoryFragment.class);
        } else {
            dcr switchProductGroupItem = switchProductGroupItem(ResourceManager.e().a().e(), HealthDevice.HealthDeviceKind.HDK_WEIGHT.name());
            if (this.mainActivity == null) {
                LogUtil.h(TAG, "positiveListener mainActivity is null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (switchProductGroupItem != null) {
                LogUtil.a(TAG, "onClick item: ", switchProductGroupItem.toString());
                startPairingGuideActivity();
            } else {
                LogUtil.h(TAG, "WEIGHT ProductGroup is error");
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        LogUtil.c(TAG, "onCreateView child: ", this.child, " view: ", viewGroup2);
        this.child = layoutInflater.inflate(R.layout.wifi_upgrade_layout, viewGroup, false);
        this.mIsExistNewVer = getArguments().getBoolean("is_exist_new_version");
        initView();
        initData();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    private void initView() {
        setTitle(this.mainActivity.getString(R.string.IDS_device_wear_home_device_ota_upgrade));
        this.mUpdateLinearLayout = (LinearLayout) this.child.findViewById(R.id.update_img_linearlayout);
        this.mUpdateRelativeLayout = (RelativeLayout) this.child.findViewById(R.id.no_new_version_relativelayout);
        this.mNowVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.now_version_nodes_tv);
        this.mNoNewVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.no_new_version_tv);
        this.mNewVersionText = (HealthTextView) nsy.cMd_(this.child, R.id.new_version_tv);
        this.mLatestVersionLayout = (LinearLayout) nsy.cMd_(this.child, R.id.latest_version_button);
        this.mRoundProgress = (RoundProgressView) nsy.cMd_(this.child, R.id.version_update_anim);
        this.mCheckVersionUpdate = (HealthButton) nsy.cMd_(this.child, R.id.check_version_update);
        this.mVersionLogo = (ImageView) nsy.cMd_(this.child, R.id.image_logo);
        this.mLatestVersionLayout.setOnClickListener(this);
        this.mCheckVersionUpdate.setOnClickListener(this);
        if (this.mIsExistNewVer) {
            showLatestVersionLayout(true);
        } else {
            showLatestVersionLayout(false);
        }
        initViewTahiti();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public void initViewTahiti() {
        super.initViewTahiti();
        Context context = BaseApplication.getContext();
        this.mContext = context;
        if (nsn.ag(context)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, 0, 0.5f);
            layoutParams.gravity = 1;
            this.mUpdateLinearLayout.setLayoutParams(layoutParams);
            this.mUpdateRelativeLayout.setLayoutParams(layoutParams);
        }
    }

    private void startPairingGuideActivity() {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            LogUtil.h(TAG, "DeviceMainActivity is null");
            return;
        }
        List<msx> c = mst.a().c(this.mProductId);
        if (koq.b(c)) {
            LogUtil.h(TAG, "DevicePluginInfoBean is null");
            return;
        }
        msx msxVar = c.get(0);
        String m = msxVar.m();
        if (TextUtils.isEmpty(m)) {
            LogUtil.h(TAG, "pairGuid is empty");
            return;
        }
        String f = msxVar.f();
        if (TextUtils.isEmpty(f)) {
            LogUtil.h(TAG, "kindId is empty");
            return;
        }
        List<String> k = msxVar.k();
        if (koq.b(k)) {
            LogUtil.h(TAG, "uuidList is empty");
            return;
        }
        Iterator<String> it = k.iterator();
        while (it.hasNext()) {
            LogUtil.h(TAG, "uuidList uuid: ", it.next());
        }
        LogUtil.a(TAG, "pairGuide: ", m, "kindId: ", f);
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
            LogUtil.b(TAG, "startPairingGuideActivity ActivityNotFoundException");
        }
        activity.finish();
    }

    private void checkDevice() {
        if (this.mWiFiDevice == null) {
            LogUtil.h(TAG, "checkDevice() mWiFiDevice is null");
            return;
        }
        String devId = getDevId();
        if (TextUtils.isEmpty(devId)) {
            LogUtil.h(TAG, "devId is null");
            return;
        }
        WifiDeviceGetWifiDeviceInfoReq wifiDeviceGetWifiDeviceInfoReq = new WifiDeviceGetWifiDeviceInfoReq();
        wifiDeviceGetWifiDeviceInfoReq.setDevId(devId);
        jbs.a(this.mainActivity).a(wifiDeviceGetWifiDeviceInfoReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiProductUpgradeFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WiFiProductUpgradeFragment.this.m246xc8227991((WifiDeviceGetWifiDeviceInfoRsp) obj, str, z);
            }
        });
    }

    /* renamed from: lambda$checkDevice$1$com-huawei-health-device-ui-measure-fragment-WiFiProductUpgradeFragment, reason: not valid java name */
    /* synthetic */ void m246xc8227991(WifiDeviceGetWifiDeviceInfoRsp wifiDeviceGetWifiDeviceInfoRsp, String str, boolean z) {
        int i;
        String str2;
        if (!z) {
            if (wifiDeviceGetWifiDeviceInfoRsp != null) {
                i = wifiDeviceGetWifiDeviceInfoRsp.getResultCode().intValue();
                str2 = wifiDeviceGetWifiDeviceInfoRsp.getResultDesc();
                if (i == 112000000) {
                    LogUtil.a(TAG, "device already not exists");
                    this.mHandler.sendEmptyMessage(4);
                }
            } else {
                i = Constants.CODE_UNKNOWN_ERROR;
                str2 = "unknown error";
            }
            LogUtil.a(TAG, "checkDevice() errCode: ", Integer.valueOf(i), " resultDesc: ", str2);
            return;
        }
        if (wifiDeviceGetWifiDeviceInfoRsp != null && wifiDeviceGetWifiDeviceInfoRsp.getDeviceDetailInfo() == null) {
            LogUtil.a(TAG, "checkDevice() device already not exists");
            this.mHandler.sendEmptyMessage(4);
        } else {
            LogUtil.a(TAG, "checkDevice() device already exists");
        }
    }

    private dcr switchProductGroupItem(ArrayList<dcr> arrayList, String str) {
        Iterator<dcr> it = arrayList.iterator();
        dcr dcrVar = null;
        while (it.hasNext()) {
            dcr next = it.next();
            LogUtil.a(TAG, " item.kind.name(): ", next.c().name(), " device_Type: ", str);
            if (next.c().name().equals(str)) {
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
        this.mHandler = new WifiHandler(this);
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        }
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            if (ceo.d().d(this.mUniqueId, false) instanceof ctk) {
                this.mWiFiDevice = (ctk) ceo.d().d(this.mUniqueId, false);
            }
        } else {
            LogUtil.h(TAG, "productId is empty, can't get WiFiDevice");
        }
        reGetWifiDevice();
        if (this.mWiFiDevice == null) {
            LogUtil.h(TAG, "wifi device is null");
            this.mHandler.sendEmptyMessage(4);
        }
        this.mIsFromProductView = getArguments().getBoolean("fromProductView", false);
        if (this.mIsExistNewVer) {
            checkDevice();
            this.mNewVer = getArguments().getString("version");
            this.mNowVersion = getArguments().getString(CER_VERSION);
            this.mReleaseNote = getArguments().getString(UPDATE_NODES);
            Message obtain = Message.obtain();
            obtain.what = 3;
            this.mHandler.sendMessage(obtain);
        } else {
            detectionExistNewVersion();
        }
        if ("a8ba095d-4123-43c4-a30a-0240011c58de".equals(this.mProductId)) {
            this.mVersionLogo.setImageResource(R.drawable.wifi_device_honor_ota_logo);
        } else if ("e4b0b1d5-2003-4d88-8b5f-c4f64542040b".equals(this.mProductId)) {
            this.mVersionLogo.setImageResource(R.drawable.wifi_device_ota_logo);
        } else {
            LogUtil.h(TAG, "other wifi product");
        }
        cuf.e(this.mainActivity.getApplicationContext()).b();
    }

    private void reGetWifiDevice() {
        if (this.mWiFiDevice == null) {
            MeasurableDevice measurableDevice = null;
            String string = getArguments() != null ? getArguments().getString(DEVICE_ID) : null;
            LogUtil.a(TAG, "initData mDeviceId: ", cpw.d(string));
            ctk c = !TextUtils.isEmpty(string) ? ctq.c(string) : null;
            if (c != null && !TextUtils.isEmpty(c.getSerialNumber())) {
                measurableDevice = ceo.d().c(c.getSerialNumber(), false);
            }
            if (measurableDevice == null || !(measurableDevice instanceof ctk)) {
                return;
            }
            this.mWiFiDevice = (ctk) measurableDevice;
            if (TextUtils.isEmpty(this.mProductId)) {
                this.mProductId = this.mWiFiDevice.getProductId();
            }
            if (TextUtils.isEmpty(this.mUniqueId)) {
                this.mUniqueId = this.mWiFiDevice.getUniqueId();
            }
            if (this.mDeviceInfo == null) {
                this.mDeviceInfo = new ContentValues();
            }
            this.mDeviceInfo.put("uniqueId", this.mUniqueId);
            this.mDeviceInfo.put("productId", this.mProductId);
        }
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
        getDeviceStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDevId() {
        ctk ctkVar = this.mWiFiDevice;
        if (ctkVar != null && ctkVar.b() != null) {
            LogUtil.a(TAG, "device info: ", this.mWiFiDevice.b().toString());
            String a2 = this.mWiFiDevice.b().a();
            if (!TextUtils.isEmpty(a2)) {
                return a2;
            }
            LogUtil.h(TAG, "devId is null");
            return "";
        }
        LogUtil.h(TAG, "device id is null");
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLatestVersionLayout(boolean z) {
        this.mLatestVersionLayout.setVisibility(z ? 0 : 8);
    }

    private void getDeviceStatus() {
        showLatestVersionLayout(false);
        WifiDeviceServiceInfoReq wifiDeviceServiceInfoReq = new WifiDeviceServiceInfoReq();
        String devId = getDevId();
        if (TextUtils.isEmpty(devId)) {
            LogUtil.h(TAG, "getDeviceStatus devId is null");
            return;
        }
        wifiDeviceServiceInfoReq.setDevId(devId);
        wifiDeviceServiceInfoReq.setSid("devOtaInfo");
        jbs.a(this.mainActivity.getApplicationContext()).b(wifiDeviceServiceInfoReq, new ICloudOperationResult() { // from class: com.huawei.health.device.ui.measure.fragment.WiFiProductUpgradeFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                WiFiProductUpgradeFragment.this.m247x7044e9f2((WifiDeviceServiceInfoRsp) obj, str, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00de, code lost:
    
        r3.what = 2;
     */
    /* renamed from: processDeviceInfo, reason: merged with bridge method [inline-methods] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void m247x7044e9f2(com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp r20, java.lang.String r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 286
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.device.ui.measure.fragment.WiFiProductUpgradeFragment.m247x7044e9f2(com.huawei.hwcloudmodel.model.unite.WifiDeviceServiceInfoRsp, java.lang.String, boolean):void");
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.latest_version_button) {
            WiFiVersionDetailsFragment wiFiVersionDetailsFragment = new WiFiVersionDetailsFragment();
            cpw.c(false, TAG, "onClick last version: ", wiFiVersionDetailsFragment);
            Bundle bundle = new Bundle();
            bundle.putString("productId", this.mProductId);
            bundle.putString("version", this.mNewVer);
            bundle.putString(CER_VERSION, this.mNowVersion);
            bundle.putString(UPDATE_NODES, this.mReleaseNote);
            bundle.putBoolean("show_update", true);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            wiFiVersionDetailsFragment.setArguments(bundle);
            switchFragment(wiFiVersionDetailsFragment);
        } else if (view.getId() == R.id.check_version_update) {
            LogUtil.a(TAG, "check version update tag: ", view.getTag());
            if (view.getTag() == null || nsn.e(view.getTag().toString()) != 3) {
                detectionExistNewVersion();
            } else {
                WiFiUpdateGuideFragment wiFiUpdateGuideFragment = new WiFiUpdateGuideFragment();
                LogUtil.c(TAG, "onClick check version: ", wiFiUpdateGuideFragment);
                Bundle bundle2 = new Bundle();
                bundle2.putString("productId", this.mProductId);
                bundle2.putString("version", this.mNewVer);
                bundle2.putString(CER_VERSION, this.mNowVersion);
                bundle2.putString(UPDATE_NODES, this.mReleaseNote);
                bundle2.putParcelable("commonDeviceInfo", this.mDeviceInfo);
                wiFiUpdateGuideFragment.setArguments(bundle2);
                switchFragment(wiFiUpdateGuideFragment);
            }
        } else {
            LogUtil.a(TAG, "--------no click------");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mRoundProgress = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCheckVersionUpdateEnable(int i) {
        if (i == 1) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mNoNewVersionText.setText(R.string._2130841484_res_0x7f020f8c);
        } else if (i == 2) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mNoNewVersionText.setText(R.string.IDS_device_wifi_ota_check_fail);
            this.mCheckVersionUpdate.setText(R.string._2130841467_res_0x7f020f7b);
        } else if (i == 3) {
            this.mCheckVersionUpdate.setAlpha(1.0f);
            this.mCheckVersionUpdate.setEnabled(true);
            this.mCheckVersionUpdate.setText(R.string.IDS_device_manager_update_health);
            this.mNoNewVersionText.setText(R.string._2130841460_res_0x7f020f74);
        } else if (i == 10) {
            this.mCheckVersionUpdate.setAlpha(ALPHA_VALUE_STATUS_DONG);
            this.mCheckVersionUpdate.setEnabled(false);
            this.mCheckVersionUpdate.setText(R.string._2130841456_res_0x7f020f70);
            this.mNoNewVersionText.setText(R.string._2130841461_res_0x7f020f75);
        } else {
            LogUtil.a(TAG, "check is empty");
        }
        this.mCheckVersionUpdate.setTag(Integer.valueOf(i));
    }

    static class WifiHandler extends StaticHandler<WiFiProductUpgradeFragment> {
        WifiHandler(WiFiProductUpgradeFragment wiFiProductUpgradeFragment) {
            super(wiFiProductUpgradeFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(WiFiProductUpgradeFragment wiFiProductUpgradeFragment, Message message) {
            if (wiFiProductUpgradeFragment.isDestroy()) {
                return;
            }
            if (!wiFiProductUpgradeFragment.isAdded()) {
                LogUtil.h(WiFiProductUpgradeFragment.TAG, "WifiHandler mFragment is not add");
                return;
            }
            LogUtil.a(WiFiProductUpgradeFragment.TAG, "WifiHandler what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (wiFiProductUpgradeFragment.mNowVersion != null) {
                    wiFiProductUpgradeFragment.mNowVersionText.setText(wiFiProductUpgradeFragment.mNowVersion);
                }
                csc.d().c(wiFiProductUpgradeFragment.getDevId(), false);
                wiFiProductUpgradeFragment.mRoundProgress.c();
                wiFiProductUpgradeFragment.setCheckVersionUpdateEnable(1);
                return;
            }
            if (i == 2) {
                csc.d().c(wiFiProductUpgradeFragment.getDevId(), false);
                wiFiProductUpgradeFragment.mRoundProgress.c();
                wiFiProductUpgradeFragment.setCheckVersionUpdateEnable(2);
            } else {
                if (i != 3) {
                    if (i == 4) {
                        wiFiProductUpgradeFragment.showNoDeviceDialog();
                        return;
                    } else {
                        LogUtil.h(WiFiProductUpgradeFragment.TAG, "WifiHandler what is other");
                        return;
                    }
                }
                csc.d().c(wiFiProductUpgradeFragment.getDevId(), true);
                wiFiProductUpgradeFragment.mRoundProgress.c();
                wiFiProductUpgradeFragment.setCheckVersionUpdateEnable(3);
                wiFiProductUpgradeFragment.mNewVersionText.setText(wiFiProductUpgradeFragment.mNewVer);
                wiFiProductUpgradeFragment.mNowVersionText.setText(wiFiProductUpgradeFragment.mNowVersion);
                wiFiProductUpgradeFragment.showLatestVersionLayout(true);
                wiFiProductUpgradeFragment.mIsExistNewVer = true;
            }
        }
    }
}
