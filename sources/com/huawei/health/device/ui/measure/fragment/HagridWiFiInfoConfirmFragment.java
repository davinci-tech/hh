package com.huawei.health.device.ui.measure.fragment;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.inputmethodservice.KeyboardView;
import android.location.LocationManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.android.net.wifi.NfcWifiManagerEx;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.ui.measure.adapter.HagridWifiListAdapter;
import com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment;
import com.huawei.health.device.wifi.interfaces.CommBaseCallback;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.edittext.HealthIconTextLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.listviewforscroll.ListViewForScroll;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.ceo;
import defpackage.cgt;
import defpackage.cld;
import defpackage.coy;
import defpackage.cpa;
import defpackage.cpn;
import defpackage.cpw;
import defpackage.cpx;
import defpackage.cpz;
import defpackage.cqh;
import defpackage.ctk;
import defpackage.ctv;
import defpackage.ctz;
import defpackage.cub;
import defpackage.dcz;
import defpackage.dja;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes3.dex */
public class HagridWiFiInfoConfirmFragment extends BaseFragment implements View.OnClickListener {
    private static final int CONNECT_WIFI_DELAY_TIME = 2000;
    public static final String KEY_SHOW_GUIDE = "showGuide";
    private static final int SHOW_KEYBOARD_DELAY_TIME = 300;
    private static final int SHOW_LOADING_DIALOG_DELAY_TIME = 400;
    private static final String TAG = "HagridWiFiInfoConfirmFragment";
    private static final String TAG_RELEASE = "R_Weight_HagridWiFiInfoConfirmFragment";
    private HealthTextView mAddOtherNetwork;
    private ImageView mBackImage;
    private HealthButton mBackLayout;
    private View mChooseOtherWifiButtonView;
    private LinearLayout mConfirmImage;
    private LinearLayout mConfirmWifiInfoBtnLayout;
    private HealthScrollView mConfirmWifiInfoLayout;
    private ctz mConnUtils;
    private String mConnectedWifiPwd;
    private CommonDialog21 mConnectingWifiDialog;
    private ContentValues mDeviceInfo;
    private String mDeviceSsid;
    private CommonDialog21 mEmptyLoadingDialog;
    private HealthButton mFreshWifiInfoListBtn;
    private LinearLayout mGuideBackLayout;
    private LinearLayout mGuideButtonLayout;
    private ImageView mGuideNextImg;
    private LinearLayout mGuideNextLayout;
    private HealthTextView mGuideNextTv;
    private MyHandler mHandler;
    private boolean mIsRequestPermission;
    private LinearLayout mKeyBoardLinearLayout;
    private cpx mKeyboardUtil;
    private KeyboardView mKeyboardView;
    private HealthTextView mNetworkErrorHint;
    private HealthTextView mNetworkSupportHint;
    private HealthButton mNextLayout;
    private HealthButton mOpenWifiInfoListBtn;
    private HealthTextView mPasswordErrorHint;
    private dcz mProductInfo;
    private HagridWifiListAdapter mSaveWifiListAdapter;
    private ScanResult mScanResult;
    private LinearLayout mSelectWifiInfoLayout;
    private String mUniqueId;
    private ListViewForScroll mUsefulSaveWifiInfoListView;
    private ListViewForScroll mUsefulWifiInfoListView;
    private cld mWeightInteractor;
    private LinearLayout mWifiConnectPromptLayout;
    private ctk mWifiDevice;
    private HagridWifiListAdapter mWifiListAdapter;
    private HealthTextView mWifiListTip;
    private HealthEditText mWifiNameEdit;
    private HealthIconTextLayout mWifiPwdLayout;
    private HealthProgressBar mWifiScanProgressBar;
    private View mWifiTipGap;
    private boolean mIsFirstCreate = true;
    private String mWifiSsid = "";
    private String mWifiPwd = "";
    private String mConnectedWifiSsid = "";
    private String mProductId = "";
    private String mDefaultSsid = "";
    private int mPwdMode = 0;
    private int mConfigMode = 1;
    private boolean mIsMainUser = false;
    private boolean mIs2P4gWifi = true;
    private boolean mIsOpenWifi = false;
    private boolean mIsHideWifiList = false;
    private boolean mIsAddOtherWifi = false;
    private List<ScanResult> mShowWifiList = new ArrayList(16);
    private List<ScanResult> mShowSaveWifiList = new ArrayList(16);
    private List<ScanResult> mStorageWifiList = new ArrayList(16);
    private CopyOnWriteArrayList<ScanResult> mStorage5gWifiList = new CopyOnWriteArrayList<>();
    private List<WifiConfiguration> mConfigurationWifiList = new ArrayList(16);
    private Handler mAutoRefreshHandler = new Handler();
    private boolean mIsConnectingWifi = false;
    private boolean mIsGetPassEmpty = false;
    private boolean mIsHaveSsid = true;
    private boolean mIsWifiError = false;
    private int mSdkInitValue = Build.VERSION.SDK_INT;
    private long mLastRefreshTime = 0;
    private CommBaseCallback mConnectWifiCallback = new CommBaseCallback<HagridWiFiInfoConfirmFragment>(this) { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment.1
        @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallback
        public void onResult(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment, int i, String str, Object obj) {
            if (hagridWiFiInfoConfirmFragment == null) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG_RELEASE, "connect WiFiInfoConfirmFragment is null");
                return;
            }
            if (!hagridWiFiInfoConfirmFragment.isAdded()) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG_RELEASE, "connect WiFiInfoConfirmFragment not add");
                return;
            }
            if (i == 101) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "connect wifi timeout.");
                HagridWiFiInfoConfirmFragment.this.mIsWifiError = true;
                hagridWiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1004);
                return;
            }
            if (i == 103) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "connect wifi failure.");
                HagridWiFiInfoConfirmFragment.this.mIsWifiError = true;
                hagridWiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1004);
            } else if (i == 102) {
                LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "connect wifi success.");
                hagridWiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1003);
            } else {
                if (i == 105) {
                    LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "connect wifi pwd is error.");
                    HagridWiFiInfoConfirmFragment.this.mIsWifiError = true;
                    hagridWiFiInfoConfirmFragment.mHandler.sendEmptyMessage(1006);
                    return;
                }
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "connect wifi error is other code: ", Integer.valueOf(i));
            }
        }
    };
    private BroadcastReceiver mReceiver = new AnonymousClass2();
    private Runnable mAutoRefreshRunnable = new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment.3
        @Override // java.lang.Runnable
        public void run() {
            if (HagridWiFiInfoConfirmFragment.this.mHandler != null) {
                HagridWiFiInfoConfirmFragment.this.mHandler.sendEmptyMessage(1001);
                HagridWiFiInfoConfirmFragment.this.mAutoRefreshHandler.postDelayed(HagridWiFiInfoConfirmFragment.this.mAutoRefreshRunnable, 5000L);
            }
        }
    };

    /* renamed from: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$2, reason: invalid class name */
    class AnonymousClass2 extends BroadcastReceiver {
        AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "onReceive Action...", intent.getAction());
            if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction()) || "android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra("networkInfo");
                if (networkInfo == null || networkInfo.getState() == null) {
                    LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "wifi connect failure msg");
                    HagridWiFiInfoConfirmFragment.this.mIsWifiError = true;
                    HagridWiFiInfoConfirmFragment.this.mHandler.sendEmptyMessageDelayed(1004, 500L);
                    return;
                } else {
                    if (networkInfo.getState().equals(NetworkInfo.State.CONNECTED)) {
                        LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "wifi wifi network connect...");
                        HagridWiFiInfoConfirmFragment.this.mHandler.sendEmptyMessageDelayed(1005, 500L);
                        return;
                    }
                    return;
                }
            }
            if ("android.net.wifi.SCAN_RESULTS".equals(intent.getAction())) {
                LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "get wifi scanResult list...");
                if (!HagridWiFiInfoConfirmFragment.this.mIsHaveSsid || HagridWiFiInfoConfirmFragment.this.mIsWifiError) {
                    HagridWiFiInfoConfirmFragment.this.mIsHaveSsid = true;
                    HagridWiFiInfoConfirmFragment.this.mIsWifiError = false;
                    HagridWiFiInfoConfirmFragment.this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$2$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            HagridWiFiInfoConfirmFragment.AnonymousClass2.this.m189x751b2cd8();
                        }
                    }, 2000L);
                }
                HagridWiFiInfoConfirmFragment.this.mHandler.sendEmptyMessage(1001);
                return;
            }
            LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "mReceiver default");
        }

        /* renamed from: lambda$onReceive$0$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment$2, reason: not valid java name */
        /* synthetic */ void m189x751b2cd8() {
            HagridWiFiInfoConfirmFragment.this.handleConnectWifi();
        }
    }

    static class MyHandler extends StaticHandler<HagridWiFiInfoConfirmFragment> {
        MyHandler(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment) {
            super(hagridWiFiInfoConfirmFragment);
        }

        @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
        public void handleMessage(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment, Message message) {
            if (hagridWiFiInfoConfirmFragment.isDestroy() || message == null) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG_RELEASE, "MyHandler handleMessage object is destory or msg is null");
                return;
            }
            if (!hagridWiFiInfoConfirmFragment.isAdded()) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG_RELEASE, "MyHandler mFragment is not add");
            }
            LogUtil.a(HagridWiFiInfoConfirmFragment.TAG_RELEASE, "MyHandler msg.what: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1005) {
                removeMessages(1005);
                hagridWiFiInfoConfirmFragment.refreshWifiInfo();
                return;
            }
            switch (i) {
                case 1000:
                    dealForUpdateWifiListMsg(hagridWiFiInfoConfirmFragment, message);
                    break;
                case 1001:
                    dealForGetWifiListMsg(hagridWiFiInfoConfirmFragment, message);
                    break;
                case 1002:
                    if (!hagridWiFiInfoConfirmFragment.mIsHideWifiList) {
                        removeMessages(1002);
                        if (cub.l(BaseApplication.getContext())) {
                            sendEmptyMessageDelayed(1002, OpAnalyticsConstants.H5_LOADING_DELAY);
                        } else {
                            sendEmptyMessageDelayed(1002, 3000L);
                        }
                        sendEmptyMessageDelayed(1001, 2000L);
                        if (hagridWiFiInfoConfirmFragment.mIsFirstCreate) {
                            hagridWiFiInfoConfirmFragment.mAutoRefreshHandler.postDelayed(hagridWiFiInfoConfirmFragment.mAutoRefreshRunnable, 5000L);
                            break;
                        }
                    }
                    break;
                default:
                    HagridWiFiInfoConfirmFragment.doOtherCase(hagridWiFiInfoConfirmFragment, message);
                    break;
            }
        }

        private void dealForGetWifiListMsg(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment, Message message) {
            LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "GET_WIFI_LIST_MSG");
            removeMessages(1001);
            if (hagridWiFiInfoConfirmFragment.mIsHideWifiList) {
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (Math.abs(currentTimeMillis - hagridWiFiInfoConfirmFragment.mLastRefreshTime) >= 2000) {
                hagridWiFiInfoConfirmFragment.mLastRefreshTime = currentTimeMillis;
                hagridWiFiInfoConfirmFragment.getWifiListInfo();
            } else {
                LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "refresh interval time is too short");
            }
        }

        private void dealForUpdateWifiListMsg(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment, Message message) {
            LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "UPDATE_WIFI_LIST_MSG");
            removeMessages(1000);
            Bundle data = message.getData();
            if (data == null) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "bundle is null");
                return;
            }
            Serializable serializable = data.getSerializable("ScanResults");
            Serializable serializable2 = data.getSerializable("WifiConfiguration");
            if (serializable == null || serializable2 == null) {
                LogUtil.h(HagridWiFiInfoConfirmFragment.TAG, "scanResults is null, or wifiConfiguration is null");
                return;
            }
            if ((serializable instanceof List) && (serializable2 instanceof List)) {
                hagridWiFiInfoConfirmFragment.updateWiFiList((List) serializable, (List) serializable2);
            }
            hagridWiFiInfoConfirmFragment.refreshSaveList();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void doOtherCase(HagridWiFiInfoConfirmFragment hagridWiFiInfoConfirmFragment, Message message) {
        LogUtil.a(TAG, "enter doOtherCase");
        switch (message.what) {
            case 1003:
                hagridWiFiInfoConfirmFragment.prepareSendWifiInfoToDevice();
                hagridWiFiInfoConfirmFragment.startSendWifiInfoToDevice();
                break;
            case 1004:
                hagridWiFiInfoConfirmFragment.showNetworkUnavailableMsg();
                break;
            case 1005:
            default:
                LogUtil.h(TAG, "handleMessage doOtherCase default");
                break;
            case 1006:
                hagridWiFiInfoConfirmFragment.showPasswordErrorMsg();
                break;
            case 1007:
                hagridWiFiInfoConfirmFragment.getConnectSsidPassword();
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        LogUtil.a(TAG, "onSaveInstanceState");
        if (bundle != null) {
            LogUtil.a(TAG, "onSaveInstanceState put value");
            bundle.putBoolean("isConnectingWifi", this.mIsConnectingWifi);
        }
    }

    private void getConnectSsidPassword() {
        LogUtil.a(TAG, "enter getConnectSsidPassword");
        this.mainActivity.runOnUiThread(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() {
                HagridWiFiInfoConfirmFragment.this.m179x8fc5ceaa();
            }
        });
    }

    /* renamed from: lambda$getConnectSsidPassword$0$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m179x8fc5ceaa() {
        ScanResult scanResult;
        if (!CommonUtil.ar()) {
            LogUtil.h(TAG, "EMUI is not 10");
            this.mIsGetPassEmpty = true;
            return;
        }
        String e = cub.e(cub.c(this.mainActivity));
        if (e != null && (scanResult = this.mScanResult) != null && scanResult.SSID.equals(e)) {
            LogUtil.a(TAG, "nowConnectWifi is valid");
            String wpaSuppConfig = NfcWifiManagerEx.getWpaSuppConfig((WifiManager) null);
            if (!TextUtils.isEmpty(wpaSuppConfig) && wpaSuppConfig.contains(this.mScanResult.SSID)) {
                this.mWifiPwdLayout.setText(wpaSuppConfig.substring(wpaSuppConfig.indexOf(this.mScanResult.SSID) + this.mScanResult.SSID.length()));
                checkIfWifiCanConnect();
                return;
            } else {
                this.mIsGetPassEmpty = true;
                LogUtil.h(TAG, "EMUI is not 10");
                return;
            }
        }
        LogUtil.a(TAG, "getConnectSsidPassword mIsAddOtherWifi: ", Boolean.valueOf(this.mIsAddOtherWifi));
        if (this.mIsAddOtherWifi) {
            this.mIsGetPassEmpty = true;
        }
        LogUtil.h(TAG, "now connect wifi: ", e);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(TAG, "onCreate mSdkInitValue: ", Integer.valueOf(this.mSdkInitValue));
        nsn.cLy_(this.mainActivity.getWindow());
        this.mHandler = new MyHandler(this);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (getView() == null) {
            LogUtil.h(TAG, "get view is null");
            return;
        }
        checkHavePermission();
        MeasurableDevice d = !TextUtils.isEmpty(this.mUniqueId) ? ceo.d().d(this.mUniqueId, true) : null;
        if (d == null || (d instanceof ctk)) {
            return;
        }
        this.mWeightInteractor.onResume();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.hygride_wifi_info_confirm_fragment_layout, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.mCustomTitleBar);
            viewGroup2.addView(this.child);
        }
        LogUtil.a(TAG, "onCreateView");
        if (bundle != null && bundle.getBoolean("isConnectingWifi")) {
            LogUtil.a(TAG, "onCreateView savedInstanceState is not null and have value");
            CommonDialog21 commonDialog21 = this.mConnectingWifiDialog;
            if (commonDialog21 != null && commonDialog21.isShowing()) {
                LogUtil.a(TAG, "mConnectingWifiDialog is not null, need dismiss");
                destroyLoadingDialog();
            }
        }
        initView();
        initData();
        showCustomTitleBar(null);
        Bundle bundle2 = new Bundle();
        bundle2.putInt("type", -1);
        bundle2.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        cgt.e().prepare(!TextUtils.isEmpty(this.mUniqueId) ? ceo.d().d(this.mUniqueId, false) : null, null, bundle2);
        cld HJ_ = cld.HJ_(this.mainActivity, this.mProductId, this.mUniqueId);
        this.mWeightInteractor = HJ_;
        HJ_.a();
        this.mainActivity.getWindow().setSoftInputMode(18);
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        LogUtil.a(TAG, "onViewStateRestored");
    }

    private void initData() {
        boolean z;
        LogUtil.a(TAG, "initData()");
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mConfigMode = arguments.getInt("config_mode", 1);
            z = arguments.getBoolean(KEY_SHOW_GUIDE, false);
            this.mProductId = arguments.getString("productId");
            this.mDeviceSsid = arguments.getString("deviceSsid");
            ContentValues contentValues = (ContentValues) arguments.getParcelable("commonDeviceInfo");
            this.mDeviceInfo = contentValues;
            if (contentValues != null) {
                this.mProductId = contentValues.getAsString("productId");
                this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
            }
            if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(this.mUniqueId)) {
                LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
            }
            refreshGuideView(z);
        } else {
            z = false;
        }
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d == null) {
            LogUtil.b(TAG_RELEASE, "initData mProductInfo is null");
            this.mainActivity.onBackPressed();
            return;
        }
        initDevice();
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        intentFilter.addAction("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.supplicant.STATE_CHANGE");
        this.mainActivity.registerReceiver(this.mReceiver, intentFilter);
        this.mConnUtils = ctz.c(this.mainActivity);
        this.mWifiListAdapter = new HagridWifiListAdapter(this.mainActivity, this.mShowWifiList);
        this.mSaveWifiListAdapter = new HagridWifiListAdapter(this.mainActivity, this.mShowSaveWifiList);
        this.mWifiListAdapter.c(false);
        this.mSaveWifiListAdapter.c(true);
        if (this.mWifiDevice != null) {
            this.mSaveWifiListAdapter.c(cpn.b(this.mUniqueId, this.mProductId));
        }
        this.mSaveWifiListAdapter.b(z);
        this.mUsefulWifiInfoListView.setAdapter((ListAdapter) this.mWifiListAdapter);
        this.mUsefulSaveWifiInfoListView.setAdapter((ListAdapter) this.mSaveWifiListAdapter);
        initWifiInfo();
    }

    private void initDevice() {
        MeasurableDevice measurableDevice;
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            LogUtil.a(TAG, "mDeviceInfo is not null, deviceIdentify: ", cpw.d(this.mUniqueId));
            measurableDevice = ceo.d().d(this.mUniqueId, false);
            if (measurableDevice instanceof ctk) {
                this.mWifiDevice = (ctk) measurableDevice;
            }
        } else {
            LogUtil.h(TAG, "mDeviceInfo is null");
            measurableDevice = null;
        }
        if (measurableDevice != null && (measurableDevice instanceof ctk) && ((ctk) measurableDevice).b().k() == 1) {
            this.mIsMainUser = true;
        }
    }

    private void refreshGuideView(boolean z) {
        if (z) {
            this.mGuideButtonLayout.setVisibility(0);
            this.mGuideBackLayout.setOnClickListener(this);
            this.mGuideNextLayout.setOnClickListener(this);
            return;
        }
        this.mGuideButtonLayout.setVisibility(8);
    }

    private void initWifiInfo() {
        this.mDefaultSsid = nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg);
        updateSsidAndPwd();
        if (this.mWifiDevice != null) {
            this.mWifiConnectPromptLayout.setVisibility(8);
        } else {
            this.mWifiConnectPromptLayout.setVisibility(0);
        }
        if (cub.g(this.mainActivity)) {
            String e = cub.e(cub.c(this.mainActivity));
            if (!TextUtils.isEmpty(e)) {
                if (cub.MA_(this.mainActivity, e) == null) {
                    LogUtil.a(TAG, "initWifiInfo scanResult is null");
                    showSelectWifiView();
                } else {
                    LogUtil.h(TAG, "initWifiInfo scanResult is not null");
                    showConnectedWifiView(e);
                }
            } else {
                showSelectWifiView();
            }
        } else {
            showSelectWifiView();
        }
        updateSsidAndPwd();
    }

    private void showConnectedWifiView(String str) {
        LogUtil.a(TAG, "showConnectedWifiView connectedSsid: ", str);
        this.mWifiNameEdit.setText(str);
        this.mWifiPwdLayout.setText("");
        this.mWifiPwdLayout.getEditText().setEnabled(true);
        ScanResult MA_ = cub.MA_(this.mainActivity, str);
        this.mScanResult = MA_;
        checkWifiLegal(MA_);
        checkWifiOpenMode(cub.i(this.mainActivity));
    }

    private void showSelectWifiView() {
        this.mHandler.sendEmptyMessage(1002);
        this.mWifiNameEdit.setText(nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg));
        this.mWifiPwdLayout.setText("");
        this.mWifiPwdLayout.getEditText().setEnabled(false);
        this.mIs2P4gWifi = true;
        checkWifiOpenMode(false);
        isClickNextStep(false);
    }

    private void prepareSendWifiInfoToDevice() {
        destroyLoadingDialog();
        clearInterfaceData(true);
        this.mConnectedWifiSsid = this.mWifiSsid;
        this.mConnectedWifiPwd = this.mWifiPwd;
    }

    private void showPasswordErrorMsg() {
        destroyLoadingDialog();
        this.mPasswordErrorHint.setText(getString(R.string.IDS_device_hygride_connect_wifi_password_error_msg));
        this.mPasswordErrorHint.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshWifiInfo() {
        Context context = BaseApplication.getContext();
        LogUtil.a(TAG, "refreshWifiInfo()");
        String e = cub.g(context) ? cub.e(cub.c(context)) : "";
        HealthEditText healthEditText = this.mWifiNameEdit;
        boolean z = healthEditText != null && (this.mDefaultSsid.equals(healthEditText.getText().toString()) || !this.mIs2P4gWifi);
        LogUtil.a(TAG, "refreshWifiInfo isEmptySsid: ", Boolean.valueOf(z), " mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        if (z && this.mWifiPwdLayout != null && !TextUtils.isEmpty(e)) {
            this.mWifiNameEdit.setText(e);
            this.mWifiPwdLayout.setText("");
            this.mWifiPwdLayout.getEditText().setEnabled(true);
            ScanResult MA_ = cub.MA_(context, e);
            this.mScanResult = MA_;
            checkWifiLegal(MA_);
            LogUtil.a(TAG, "refreshWifiInfo mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
            isClickGuideNextStep(this.mIs2P4gWifi);
            checkWifiOpenMode(cub.i(context));
            updateSsidAndPwd();
            this.mWifiListAdapter.notifyDataSetChanged();
            LogUtil.a(TAG, "refreshWifiInfo() mWifiListAdapter.notifyDataSetChanged()");
            return;
        }
        LogUtil.h(TAG_RELEASE, "refreshWifiInfo don't need to refresh wifi info...");
    }

    private void checkWifiOpenMode(boolean z) {
        LogUtil.a(TAG, "checkWifiOpenMode()...isOpen: ", Boolean.valueOf(z));
        this.mIsOpenWifi = z;
        if (z) {
            this.mWifiPwdLayout.setVisibility(8);
        } else {
            this.mWifiPwdLayout.setVisibility(0);
        }
        checkWifiInfoContent();
    }

    private void initView() {
        initFindView();
        this.mNextLayout.setText(BaseApplication.getContext().getResources().getString(R.string.IDS_device_wifi_network_configuration).toUpperCase(Locale.ENGLISH));
        this.mAddOtherNetwork.setText(BaseApplication.getContext().getResources().getString(R.string.IDS_device_hygride_add_other_network).toUpperCase(Locale.ENGLISH));
        initClickListener();
        setItemClickListener();
        isClickNextStep(false);
        isClickBackStep(true);
        initFormatView();
        initEditView();
        if (CommonUtil.bh()) {
            return;
        }
        initRandomKeyBoard(this.mWifiPwdLayout.getEditText());
    }

    private void initFindView() {
        this.mCustomTitleBar = (CustomTitleBar) this.child.findViewById(R.id.device_wlan_config_title);
        this.mBackLayout = (HealthButton) this.child.findViewById(R.id.back_step_button_layout);
        this.mNextLayout = (HealthButton) this.child.findViewById(R.id.next_step_button_layout);
        this.mWifiNameEdit = (HealthEditText) this.child.findViewById(R.id.wifi_name_tv);
        this.mWifiPwdLayout = (HealthIconTextLayout) this.child.findViewById(R.id.wifi_password_layout);
        this.mNetworkErrorHint = (HealthTextView) this.child.findViewById(R.id.wifi_network_error_prompt_tv);
        this.mPasswordErrorHint = (HealthTextView) this.child.findViewById(R.id.wifi_password_error_prompt_tv);
        this.mNetworkSupportHint = (HealthTextView) this.child.findViewById(R.id.wifi_connect_prompt_tv);
        this.mSelectWifiInfoLayout = (LinearLayout) this.child.findViewById(R.id.wifi_info_select_layout);
        this.mConfirmWifiInfoLayout = (HealthScrollView) this.child.findViewById(R.id.sv_wifi_info_confirm_layout);
        this.mConfirmWifiInfoBtnLayout = (LinearLayout) this.child.findViewById(R.id.wifi_info_confirm_button_layout);
        this.mUsefulWifiInfoListView = (ListViewForScroll) this.child.findViewById(R.id.wifi_info_useful_list);
        this.mUsefulSaveWifiInfoListView = (ListViewForScroll) this.child.findViewById(R.id.wifi_info_useful_save_list);
        this.mWifiListTip = (HealthTextView) this.child.findViewById(R.id.wifi_list_tip);
        this.mWifiTipGap = this.child.findViewById(R.id.wifi_tip_gap);
        this.mAddOtherNetwork = (HealthTextView) this.child.findViewById(R.id.wifi_add_other_network);
        this.mOpenWifiInfoListBtn = (HealthButton) this.child.findViewById(R.id.select_other_network_btn);
        this.mWifiScanProgressBar = (HealthProgressBar) this.child.findViewById(R.id.wifi_scan_progress);
        this.mWifiConnectPromptLayout = (LinearLayout) this.child.findViewById(R.id.wifi_connect_prompt_layout);
        this.mGuideButtonLayout = (LinearLayout) this.child.findViewById(R.id.wifi_info_guide_button_layout);
        this.mGuideBackLayout = (LinearLayout) this.child.findViewById(R.id.guide_back_step_button_layout);
        this.mBackImage = (ImageView) this.child.findViewById(R.id.back_step_arrow_img);
        this.mGuideNextLayout = (LinearLayout) this.child.findViewById(R.id.guide_next_step_button_layout);
        this.mGuideNextTv = (HealthTextView) this.child.findViewById(R.id.next_step_tv);
        this.mGuideNextImg = (ImageView) this.child.findViewById(R.id.next_step_arrow_img);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mGuideNextImg.setBackgroundResource(R.drawable.wifi_device_arrow_left);
            this.mBackImage.setBackgroundResource(R.drawable.wifi_device_arrow_right);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.hygride_wifi_list_choose_wlan_bottom_view, (ViewGroup) null);
        this.mChooseOtherWifiButtonView = inflate;
        this.mFreshWifiInfoListBtn = (HealthButton) inflate.findViewById(R.id.wifi_list_info_refresh_btn);
        isClickGuideNextStep(false);
    }

    private void initClickListener() {
        this.mBackLayout.setOnClickListener(this);
        this.mNextLayout.setOnClickListener(this);
        this.mOpenWifiInfoListBtn.setOnClickListener(this);
        this.mFreshWifiInfoListBtn.setOnClickListener(this);
        this.mAddOtherNetwork.setOnClickListener(this);
    }

    private void initRandomKeyBoard(EditText editText) {
        this.mainActivity.getWindow().setSoftInputMode(18);
        try {
            Method method = editText.getClass().getMethod("setShowSoftInputOnFocus", Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(editText, false);
        } catch (IllegalAccessException unused) {
            LogUtil.b(TAG, "IllegalAccessException");
        } catch (NoSuchMethodException unused2) {
            LogUtil.b(TAG, "NoSuchMethodException");
        } catch (SecurityException unused3) {
            LogUtil.b(TAG, "SecurityException");
        } catch (InvocationTargetException unused4) {
            LogUtil.b(TAG, "InvocationTargetException");
        }
        this.mKeyboardView = (KeyboardView) this.child.findViewById(R.id.keyboard_view);
        this.mKeyBoardLinearLayout = (LinearLayout) this.child.findViewById(R.id.safe_key_board);
        this.mConfirmImage = (LinearLayout) this.child.findViewById(R.id.image_keyboard_down);
        this.mKeyBoardLinearLayout.setVisibility(8);
        setLockPasswordEditText(editText);
    }

    private void setLockPasswordEditText(final EditText editText) {
        editText.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HagridWiFiInfoConfirmFragment.this.m184x102da46e(editText, view, motionEvent);
            }
        });
        this.mWifiNameEdit.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HagridWiFiInfoConfirmFragment.this.m185x5244d1cd(view, motionEvent);
            }
        });
        this.mConfirmImage.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWiFiInfoConfirmFragment.this.m186x945bff2c(view);
            }
        });
    }

    /* renamed from: lambda$setLockPasswordEditText$1$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ boolean m184x102da46e(EditText editText, View view, MotionEvent motionEvent) {
        showRandomKeyBoard(editText);
        return false;
    }

    /* renamed from: lambda$setLockPasswordEditText$2$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ boolean m185x5244d1cd(View view, MotionEvent motionEvent) {
        cpx cpxVar = this.mKeyboardUtil;
        if (cpxVar == null) {
            return false;
        }
        cpxVar.c();
        return false;
    }

    /* renamed from: lambda$setLockPasswordEditText$3$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m186x945bff2c(View view) {
        cpx cpxVar = this.mKeyboardUtil;
        if (cpxVar != null) {
            cpxVar.c();
        } else {
            LogUtil.h(TAG, "click confirmImage keyboardUtil is null");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showRandomKeyBoard(EditText editText) {
        boolean z;
        LogUtil.a(TAG, "showRandomKeyBoard enter");
        Object systemService = this.mainActivity.getSystemService("input_method");
        if (systemService instanceof InputMethodManager) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (!inputMethodManager.isActive() || this.mainActivity.getCurrentFocus() == null || this.mainActivity.getCurrentFocus().getWindowToken() == null) {
                z = false;
            } else {
                inputMethodManager.hideSoftInputFromWindow(this.mainActivity.getCurrentFocus().getWindowToken(), 2);
                z = true;
            }
            int visibility = this.mKeyboardView.getVisibility();
            if (visibility == 8 || visibility == 4) {
                this.mKeyboardUtil = new cpx(this.mainActivity, editText, this.mKeyboardView, this.mKeyBoardLinearLayout, false);
                LogUtil.a(TAG, "showRandomKeyBoard isShowSystemInput: ", Boolean.valueOf(z));
                showRandomKeyBoardCase(z);
            }
        }
    }

    private void showRandomKeyBoardCase(boolean z) {
        if (z) {
            this.mHandler.postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda8
                @Override // java.lang.Runnable
                public final void run() {
                    HagridWiFiInfoConfirmFragment.this.m188xf67fbd79();
                }
            }, 300L);
        } else {
            this.mKeyboardUtil.b();
        }
    }

    /* renamed from: lambda$showRandomKeyBoardCase$4$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m188xf67fbd79() {
        this.mKeyboardUtil.b();
    }

    private void setKeyboardSetting() {
        cpx cpxVar;
        LogUtil.a(TAG, "setKeyboardSetting enter");
        if (CommonUtil.ao() || (cpxVar = this.mKeyboardUtil) == null) {
            return;
        }
        cpxVar.c();
        this.mKeyBoardLinearLayout.setVisibility(8);
    }

    private void setItemClickListener() {
        this.mUsefulSaveWifiInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda1
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HagridWiFiInfoConfirmFragment.this.m182x1bf2eda8(adapterView, view, i, j);
            }
        });
        this.mUsefulWifiInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda2
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                HagridWiFiInfoConfirmFragment.this.m183x5e0a1b07(adapterView, view, i, j);
            }
        });
    }

    /* renamed from: lambda$setItemClickListener$5$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m182x1bf2eda8(AdapterView adapterView, View view, int i, long j) {
        LogUtil.a(TAG, "mUsefulSaveWifiInfoListView click position: ", Integer.valueOf(i));
        if (i < this.mShowSaveWifiList.size()) {
            if (!cub.d(this.mainActivity, this.mShowSaveWifiList.get(i).SSID) && this.mSdkInitValue >= 29 && cub.MB_(this.mShowSaveWifiList.get(i))) {
                showOpenPhoneWifiList();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            this.mScanResult = this.mShowSaveWifiList.get(i);
            if (!cub.MB_(this.mShowSaveWifiList.get(i))) {
                LogUtil.h(TAG, "select wifi position is 5g, Does not support");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            this.mIsHideWifiList = true;
            this.mWifiNameEdit.setEnabled(false);
            clearInterfaceData(false);
            LogUtil.a(TAG, "setItemClickListener mUsefulSaveWifiInfoListView onItemClick");
            changeLayoutView();
            this.mNextLayout.setEnabled(this.mIsOpenWifi);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    /* renamed from: lambda$setItemClickListener$6$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m183x5e0a1b07(AdapterView adapterView, View view, int i, long j) {
        LogUtil.a(TAG, "select wifi position: ", Integer.valueOf(i), "| mShowWifiList.size(): ", Integer.valueOf(this.mShowWifiList.size()));
        if (i < this.mShowWifiList.size()) {
            if (!cub.d(this.mainActivity, this.mShowWifiList.get(i).SSID) && this.mSdkInitValue >= 29 && cub.MB_(this.mShowWifiList.get(i))) {
                showOpenPhoneWifiList();
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            this.mScanResult = this.mShowWifiList.get(i);
            if (!cub.MB_(this.mShowWifiList.get(i))) {
                LogUtil.h(TAG, "select wifi position is 5g,Does not support");
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            this.mIsHideWifiList = true;
            this.mWifiNameEdit.setEnabled(false);
            clearInterfaceData(false);
            changeLayoutView();
            this.mNextLayout.setEnabled(this.mIsOpenWifi);
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            return;
        }
        LogUtil.b(TAG, "select wifi position is error");
        ViewClickInstrumentation.clickOnListView(adapterView, view, i);
    }

    private void showCustomTitleBar(String str) {
        LogUtil.a(TAG, "showCustomTitleBar mIsHideWifiList: ", Boolean.valueOf(this.mIsHideWifiList));
        if (this.mIsMainUser && !this.mIsHideWifiList) {
            this.mCustomTitleBar.setPadding(0, 0, 0, 0);
            this.mCustomTitleBar.setTitleText(getString(R.string.IDS_device_wifi_config_network));
            this.mCustomTitleBar.setLeftButtonVisibility(0);
            this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridWiFiInfoConfirmFragment.this.m187x68eb8ca4(view);
                }
            });
            return;
        }
        if (this.mIsHideWifiList) {
            this.mCustomTitleBar.setPadding(0, 0, 0, 0);
            if (!TextUtils.isEmpty(str)) {
                this.mCustomTitleBar.setTitleText(str);
                setVisibility(this.mWifiNameEdit, 8);
            } else {
                setVisibility(this.mWifiNameEdit, 0);
                this.mCustomTitleBar.setTitleText(getString(R.string.IDS_device_wifi_add_network_title));
            }
            this.mCustomTitleBar.setLeftButtonVisibility(0);
            leftButtonClickListener();
            return;
        }
        this.mCustomTitleBar.setPadding(0, 0, 0, 0);
        if (!TextUtils.isEmpty(str)) {
            this.mCustomTitleBar.setTitleText(str);
            setVisibility(this.mWifiNameEdit, 8);
        } else {
            setVisibility(this.mWifiNameEdit, 0);
            this.mCustomTitleBar.setTitleText(getString(R.string.IDS_device_wifi_config_network));
        }
        this.mCustomTitleBar.setLeftButtonVisibility(0);
        leftButtonClickListener();
    }

    /* renamed from: lambda$showCustomTitleBar$7$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m187x68eb8ca4(View view) {
        LogUtil.a(TAG, "showCustomTitleBar mCustomTitleBar is click");
        if (this.mIsMainUser && !this.mIsHideWifiList) {
            this.mainActivity.onBackPressed();
            setKeyboardSetting();
        } else {
            goBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void leftButtonClickListener() {
        this.mCustomTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWiFiInfoConfirmFragment.this.m181x4fce4a3(view);
            }
        });
    }

    /* renamed from: lambda$leftButtonClickListener$8$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m181x4fce4a3(View view) {
        if (this.mIsMainUser && !this.mIsHideWifiList) {
            this.mainActivity.onBackPressed();
        } else {
            goBackPressed();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setVisibility(View view, int i) {
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void initFormatView() {
        this.mNetworkSupportHint.setText(R.string.IDS_device_wifi_tips);
    }

    private void clearInterfaceData(boolean z) {
        if (!z) {
            this.mWifiPwdLayout.setText("");
        }
        this.mNetworkErrorHint.setText("");
        this.mNetworkErrorHint.setVisibility(8);
        this.mPasswordErrorHint.setText("");
        this.mPasswordErrorHint.setVisibility(8);
        isClickNextStep(z);
    }

    private void initEditView() {
        this.mWifiPwdLayout.getEditText().setFilters(new InputFilter[]{new InputFilter.LengthFilter(64)});
        this.mWifiPwdLayout.getEditText().setLongClickable(false);
        this.mWifiPwdLayout.getEditText().setTextIsSelectable(false);
        this.mWifiPwdLayout.getEditText().setImeOptions(268435456);
        this.mWifiPwdLayout.getEditText().setCustomSelectionActionModeCallback(new ActionMode.Callback() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment.4
            @Override // android.view.ActionMode.Callback
            public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }

            @Override // android.view.ActionMode.Callback
            public void onDestroyActionMode(ActionMode actionMode) {
            }

            @Override // android.view.ActionMode.Callback
            public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                return false;
            }
        });
        editViewListener();
    }

    private void editViewListener() {
        this.mWifiPwdLayout.getEditText().addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment.5
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                HagridWiFiInfoConfirmFragment.this.checkInputContent();
            }
        });
        this.mWifiNameEdit.addTextChangedListener(new TextWatcher() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment.6
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                HagridWiFiInfoConfirmFragment.this.checkInputContent();
            }
        });
    }

    private void changeLayoutView() {
        LogUtil.a(TAG, "changeLayoutView enter. mIsGetPassEmpty: ", Boolean.valueOf(this.mIsGetPassEmpty));
        if (this.mIsHideWifiList) {
            this.mIsGetPassEmpty = false;
            this.mHandler.sendEmptyMessage(1007);
            ScanResult scanResult = this.mScanResult;
            if (scanResult != null) {
                if (!this.mIsAddOtherWifi) {
                    this.mIsOpenWifi = cub.MC_(scanResult);
                    checkWifiLegal(this.mScanResult);
                    checkWifiOpenMode(this.mIsOpenWifi);
                    this.mWifiNameEdit.setText(this.mScanResult.SSID);
                }
                this.mNetworkErrorHint.setVisibility(8);
                this.mWifiPwdLayout.getEditText().setEnabled(true);
            } else {
                LogUtil.h(TAG_RELEASE, "changeLayoutView mScanResult is null");
            }
            this.mConfirmWifiInfoLayout.setVisibility(0);
            this.mSelectWifiInfoLayout.setVisibility(8);
            this.mConfirmWifiInfoBtnLayout.setVisibility(0);
            ScanResult scanResult2 = this.mScanResult;
            showCustomTitleBar(scanResult2 != null ? scanResult2.SSID : null);
            return;
        }
        this.mIsGetPassEmpty = false;
        this.mConfirmWifiInfoLayout.setVisibility(8);
        this.mSelectWifiInfoLayout.setVisibility(0);
        this.mConfirmWifiInfoBtnLayout.setVisibility(8);
        showCustomTitleBar(null);
    }

    private void checkIfWifiCanConnect() {
        LogUtil.a(TAG, "into checkIfWifiCanConnect...mIs2P4gWifi = ", Boolean.valueOf(this.mIs2P4gWifi));
        this.mNetworkErrorHint.setVisibility(8);
        this.mPasswordErrorHint.setVisibility(8);
        updateSsidAndPwd();
        setKeyboardSetting();
        Context context = BaseApplication.getContext();
        if (cub.g(context)) {
            if (!cub.d(context, this.mWifiSsid)) {
                LogUtil.h(TAG, "checkIfWifiCanConnect is not wifi consistency.");
                connectNewWifi();
                return;
            }
            LogUtil.a(TAG, "checkIfWifiCanConnect wifi consistency. mIsGetPassEmpty: ", Boolean.valueOf(this.mIsGetPassEmpty));
            if (this.mIsGetPassEmpty) {
                connectNewWifi();
                return;
            } else {
                prepareSendWifiInfoToDevice();
                startSendWifiInfoToDevice();
                return;
            }
        }
        LogUtil.h(TAG_RELEASE, "checkIfWifiCanConnect wifi isn't connected");
        connectNewWifi();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWiFiList(List<ScanResult> list, List<WifiConfiguration> list2) {
        this.mStorageWifiList.clear();
        this.mConfigurationWifiList.clear();
        this.mStorageWifiList.addAll(list);
        this.mConfigurationWifiList.addAll(list2);
        sortWifiListInfo(this.mStorageWifiList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSaveList() {
        ScanResult MA_ = cub.MA_(this.mainActivity, cub.e(cub.c(this.mainActivity)));
        this.mShowSaveWifiList.clear();
        if (MA_ != null) {
            if (cub.MB_(MA_)) {
                this.mShowSaveWifiList.add(MA_);
            } else {
                this.mStorage5gWifiList.add(0, MA_);
            }
        }
        for (ScanResult scanResult : this.mStorageWifiList) {
            if (cpn.e(scanResult.SSID, this.mConfigurationWifiList) && !cpn.c(scanResult.SSID, this.mShowSaveWifiList)) {
                this.mShowSaveWifiList.add(scanResult);
            }
        }
        this.mSaveWifiListAdapter.notifyDataSetChanged();
        LogUtil.a(TAG, "refreshSaveList() mSaveWifiListAdapter.notifyDataSetChanged() mShowSaveWifiList size: ", Integer.valueOf(this.mShowSaveWifiList.size()), " mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        if (koq.b(this.mShowSaveWifiList) || !this.mIs2P4gWifi) {
            this.mWifiListTip.setVisibility(8);
            this.mWifiTipGap.setVisibility(8);
            isClickGuideNextStep(false);
        } else {
            this.mWifiListTip.setVisibility(0);
            this.mWifiTipGap.setVisibility(0);
            isClickGuideNextStep(true);
        }
        refreshOtherView();
        destroyEmptyLoadingDialog();
    }

    private void refreshOtherView() {
        if (!koq.b(this.mStorageWifiList)) {
            this.mShowWifiList.clear();
            ArrayList<ScanResult> arrayList = new ArrayList(16);
            arrayList.addAll(this.mStorageWifiList);
            Iterator<ScanResult> it = this.mStorage5gWifiList.iterator();
            while (it.hasNext()) {
                ScanResult next = it.next();
                if (next != null && !cpn.a(next.SSID, this.mStorageWifiList)) {
                    arrayList.add(next);
                }
            }
            for (ScanResult scanResult : arrayList) {
                if (scanResult != null && !cpn.a(scanResult.SSID, this.mShowSaveWifiList)) {
                    this.mShowWifiList.add(scanResult);
                }
            }
            removeDuplicate(this.mShowWifiList);
            this.mWifiListAdapter.notifyDataSetChanged();
            LogUtil.h(TAG, "refreshView() mWifiListAdapter.notifyDataSetChanged()");
        } else {
            LogUtil.h(TAG, "refreshView() mStorageWifiList is empty");
        }
        this.mWifiScanProgressBar.setVisibility(8);
    }

    private void checkWifiLegal(ScanResult scanResult) {
        if (scanResult != null) {
            this.mIs2P4gWifi = cub.MB_(scanResult);
        } else {
            this.mIs2P4gWifi = cub.f(BaseApplication.getContext());
        }
        LogUtil.a(TAG, "checkWifiLegal mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        if (this.mIs2P4gWifi) {
            return;
        }
        showNetworkNotSupportMsg();
    }

    private void showNetworkNotSupportMsg() {
        String format = String.format(Locale.ENGLISH, getString(R.string.IDS_device_hygride_device_not_support_network_msg), 5);
        if (this.mPasswordErrorHint.getVisibility() != 0) {
            LogUtil.a(TAG, "showNetworkNotSupportMsg mPasswordErrorHint is gone");
            this.mNetworkErrorHint.setText(format);
            this.mNetworkErrorHint.setVisibility(0);
        } else {
            LogUtil.a(TAG, "showNetworkNotSupportMsg mPasswordErrorHint is visible");
            this.mNetworkErrorHint.setVisibility(8);
        }
    }

    private void showNetworkUnavailableMsg() {
        destroyLoadingDialog();
        if (Build.VERSION.SDK_INT >= 29) {
            LogUtil.a(TAG, "showNetworkUnavailableMsg mPasswordErrorHint");
            this.mNetworkErrorHint.setVisibility(8);
            showPasswordErrorMsg();
        } else {
            String string = getString(R.string.IDS_device_hygride_current_network_unavailable);
            LogUtil.a(TAG, "showNetworkUnavailableMsg");
            this.mNetworkErrorHint.setText(string);
            this.mNetworkErrorHint.setVisibility(0);
        }
    }

    private void showNoTitleCustomAlertDialog(String str, String str2) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getContext());
        builder.e(str).czE_(str2, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HagridWiFiInfoConfirmFragment.lambda$showNoTitleCustomAlertDialog$9(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void lambda$showNoTitleCustomAlertDialog$9(View view) {
        LogUtil.a(TAG, "showNoTitleCustomAlertDialog PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void isClickGuideNextStep(boolean z) {
        this.mGuideNextLayout.setEnabled(z);
        if (z) {
            int color = getResources().getColor(R.color._2131297781_res_0x7f0905f5);
            this.mGuideNextTv.setTextColor(color);
            this.mGuideNextImg.setImageTintList(ColorStateList.valueOf(color));
            if (LanguageUtil.bc(BaseApplication.getContext())) {
                this.mGuideNextImg.setBackgroundResource(R.drawable.wifi_device_arrow_left);
                return;
            } else {
                this.mGuideNextImg.setBackgroundResource(R.drawable.wifi_device_arrow_right);
                return;
            }
        }
        int color2 = getResources().getColor(R.color._2131297322_res_0x7f09042a);
        this.mGuideNextTv.setTextColor(color2);
        this.mGuideNextImg.setImageTintList(ColorStateList.valueOf(color2));
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            this.mGuideNextImg.setBackgroundResource(R.drawable.wifi_device_arrow_disable_left);
        } else {
            this.mGuideNextImg.setBackgroundResource(R.drawable.wifi_device_arrow_disable_right);
        }
    }

    private boolean isClickNextStep(boolean z) {
        LogUtil.c(TAG, "into isClickNextStep() isClick: ", Boolean.valueOf(z));
        if (this.mainActivity == null) {
            return false;
        }
        this.mNextLayout.setEnabled(z);
        this.mNextLayout.setClickable(z);
        return z;
    }

    private boolean isClickBackStep(boolean z) {
        LogUtil.c(TAG, "into isClickBackStep() isClick = ", Boolean.valueOf(z));
        if (this.mainActivity == null) {
            return false;
        }
        this.mBackLayout.setEnabled(z);
        this.mBackLayout.setClickable(z);
        return z;
    }

    private void checkWifiInfoContent() {
        LogUtil.a(TAG, "checkWifiInfoContent()...mIsOpenWifi = ", Boolean.valueOf(this.mIsOpenWifi), " mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        String obj = this.mWifiNameEdit.getText().toString();
        String obj2 = this.mWifiPwdLayout.getText().toString();
        if (!this.mIs2P4gWifi) {
            showNetworkNotSupportMsg();
            isClickNextStep(false);
        } else if (this.mIsOpenWifi) {
            if (!this.mDefaultSsid.equals(obj)) {
                isClickNextStep(true);
            } else {
                isClickNextStep(false);
            }
        } else if (!this.mDefaultSsid.equals(obj) && !TextUtils.isEmpty(obj2) && obj2.length() >= 8) {
            isClickNextStep(true);
        } else {
            isClickNextStep(false);
        }
        setWifiInfoProperty(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkInputContent() {
        LogUtil.a(TAG, "checkInputContent mIsOpenWifi: ", Boolean.valueOf(this.mIsOpenWifi), " mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        String obj = this.mWifiNameEdit.getText().toString();
        String obj2 = this.mWifiPwdLayout.getText().toString();
        LogUtil.a(TAG, "checkInputContent ssid: ", CommonUtil.l(obj), Boolean.valueOf(TextUtils.isEmpty(obj2)));
        if (this.mIsOpenWifi) {
            if (!this.mDefaultSsid.equals(obj)) {
                isClickNextStep(true);
            } else {
                isClickNextStep(false);
            }
        } else {
            boolean z = !TextUtils.isEmpty(obj) || isValidInputSsid();
            LogUtil.a(TAG, "checkInputContent isCanClick: ", Boolean.valueOf(z), " mWifiSsid: ", CommonUtil.l(this.mWifiSsid));
            if (z && !TextUtils.isEmpty(obj2) && obj2.length() >= 8) {
                isClickNextStep(true);
            } else {
                isClickNextStep(false);
            }
        }
        setWifiInfoProperty(obj);
    }

    private boolean isValidInputSsid() {
        return (TextUtils.isEmpty(this.mWifiSsid) || nsn.d(this.mainActivity, R.string.IDS_device_wifi_select_msg).equals(this.mWifiSsid)) ? false : true;
    }

    private void setWifiInfoProperty(String str) {
        if (this.mainActivity == null || this.mainActivity.getResources() == null) {
            LogUtil.b(TAG, "setWifiInfoProperty mainActivity or mainActivity.getResources() = null");
        } else if (!this.mDefaultSsid.equals(str)) {
            this.mWifiNameEdit.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299236_res_0x7f090ba4));
        } else {
            this.mWifiNameEdit.setTextColor(this.mainActivity.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_step_button_layout) {
            isClickNextStep(false);
            this.mIsAddOtherWifi = false;
            if (this.mIsMainUser && !this.mIsHideWifiList) {
                this.mainActivity.onBackPressed();
            } else {
                goBackPressed();
            }
        } else if (id == R.id.next_step_button_layout) {
            LogUtil.a(TAG, "click next step");
            checkIfWifiCanConnect();
        } else if (id == R.id.select_other_network_btn) {
            if (nsn.o()) {
                LogUtil.h(TAG, "click to fast!");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (isWifiOpen() && isGpsOpen()) {
                LogUtil.a(TAG, "select other wifi, wifi open and gps open");
                this.mIsHideWifiList = false;
                changeLayoutView();
                this.mHandler.sendEmptyMessage(1001);
            }
        } else if (id == R.id.wifi_list_info_refresh_btn) {
            this.mHandler.sendEmptyMessage(1001);
        } else if (id == R.id.wifi_add_other_network) {
            addOtherNetwork();
        } else if (id == R.id.guide_back_step_button_layout) {
            isClickNextStep(false);
            this.mIsAddOtherWifi = false;
            if (this.mIsMainUser && !this.mIsHideWifiList) {
                this.mainActivity.onBackPressed();
            } else {
                goBackPressed();
            }
        } else if (id == R.id.guide_next_step_button_layout) {
            LogUtil.a(TAG, "click guide next step.");
            this.mIsHideWifiList = true;
            clearInterfaceData(false);
            String e = cub.e(cub.c(this.mainActivity));
            if (!TextUtils.isEmpty(e) && cub.MA_(this.mainActivity, e) != null) {
                LogUtil.a(TAG, "click execute showConnectedWifiView.");
                showConnectedWifiView(e);
            }
            changeLayoutView();
        } else {
            LogUtil.a(TAG, "You have clicked but do nothing.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void addOtherNetwork() {
        this.mIsHideWifiList = true;
        this.mIsHaveSsid = true;
        this.mIsAddOtherWifi = true;
        this.mIs2P4gWifi = true;
        this.mWifiNameEdit.setEnabled(true);
        this.mWifiNameEdit.setText("");
        this.mWifiPwdLayout.getEditText().setEnabled(true);
        this.mWifiPwdLayout.setVisibility(0);
        this.mWifiPwdLayout.setText("");
        this.mNetworkErrorHint.setVisibility(8);
        this.mScanResult = null;
        isClickNextStep(true);
        this.mNextLayout.setEnabled(false);
        LogUtil.a(TAG, "addOtherNetwork changeLayoutView.");
        changeLayoutView();
        this.mHandler.sendEmptyMessage(1001);
    }

    private boolean isGpsOpen() {
        if (this.mainActivity == null || this.mainActivity.isFinishing()) {
            LogUtil.a(TAG, "mainActivity is finish");
            return false;
        }
        Object systemService = this.mainActivity.getSystemService("location");
        if (!(systemService instanceof LocationManager)) {
            LogUtil.a(TAG, "systemService is not LocationManager");
            return false;
        }
        if (ctv.Mg_(this.mainActivity, (LocationManager) systemService)) {
            return true;
        }
        LogUtil.a(TAG, "checkGpsIsOpen: gps service is not open");
        showGpsDialog();
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showGpsDialog() {
        cqh.c().La_(this.mainActivity);
    }

    private void showOpenPhoneWifiList() {
        cqh.c().Lg_(this.mainActivity);
    }

    private boolean isWifiOpen() {
        if (this.mainActivity == null || this.mainActivity.isFinishing()) {
            LogUtil.a(TAG, "mainActivity is finish");
            return false;
        }
        if (cub.d(this.mainActivity)) {
            return true;
        }
        LogUtil.a(TAG, "checkWiFiIsOpen checkWifiStatus false");
        cqh.c().c(this.mainActivity);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDataLoadingDialog() {
        LogUtil.a(TAG, "enter showDataLoadingDialog");
        CommonDialog21 commonDialog21 = this.mEmptyLoadingDialog;
        if (commonDialog21 == null) {
            new CommonDialog21(getActivity(), R.style.common_dialog21);
            CommonDialog21 a2 = CommonDialog21.a(getActivity());
            this.mEmptyLoadingDialog = a2;
            a2.e(getString(R.string._2130841415_res_0x7f020f47));
            this.mEmptyLoadingDialog.setCancelable(false);
            this.mEmptyLoadingDialog.a();
            return;
        }
        if (commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a(TAG, "showDataLoadingDialog");
        this.mEmptyLoadingDialog.a();
    }

    private void destroyEmptyLoadingDialog() {
        LogUtil.a(TAG, "enter destroyEmptyLoadingDialog");
        CommonDialog21 commonDialog21 = this.mEmptyLoadingDialog;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.mEmptyLoadingDialog = null;
        }
    }

    private void startSendWifiInfoToDevice() {
        LogUtil.a(TAG, "enter startSendWifiInfoToDevice()");
        ScanResult MA_ = cub.MA_(this.mainActivity, cub.e(cub.c(this.mainActivity)));
        if (MA_ != null) {
            this.mIs2P4gWifi = cub.MB_(MA_);
        } else {
            LogUtil.h(TAG, "startSendWifiInfoToDevice() currentResult is null");
            this.mIs2P4gWifi = cub.f(BaseApplication.getContext());
        }
        if (!this.mIs2P4gWifi) {
            LogUtil.h(TAG, "startSendWifiInfoToDevice() is not 2.4G, mIsAddOtherWifi: ", Boolean.valueOf(this.mIsAddOtherWifi));
            showNetworkNotSupportMsg();
            isClickNextStep(false);
        } else if (this.mWeightInteractor != null) {
            if (!cpn.e()) {
                this.mWeightInteractor.b(1);
            } else {
                handleDeviceConnect();
            }
        }
    }

    private void handleDeviceConnect() {
        if (this.mWeightInteractor.b()) {
            goToNextView();
        } else {
            showNoTitleCustomAlertDialog(getString(R.string.IDS_device_hygride_device_not_wake_up_prompt_content), getString(R.string.IDS_device_measureactivity_result_confirm));
        }
    }

    private void connectNewWifi() {
        LogUtil.a(TAG, "connectNewWifi()...");
        if (!TextUtils.isEmpty(this.mWifiSsid)) {
            this.mIsHaveSsid = cub.b(this.mainActivity, this.mWifiSsid);
        }
        LogUtil.a(TAG, "connectNewWifi()... mIsHaveSsid: ", Boolean.valueOf(this.mIsHaveSsid));
        showLoadingDialog(getString(R.string.IDS_device_hygride_verify_network_prompt_msg));
        if (!this.mIsHaveSsid || this.mIsWifiError) {
            cub.l(BaseApplication.getContext());
        } else {
            handleConnectWifi();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleConnectWifi() {
        Context context = BaseApplication.getContext();
        if (!TextUtils.isEmpty(this.mWifiSsid)) {
            int b = cub.b(this.mWifiSsid, context);
            this.mPwdMode = b;
            this.mConnUtils.a(context, this.mWifiSsid, b);
            this.mConnUtils.c(this.mWifiSsid, this.mWifiPwd, this.mPwdMode, true, this.mConnectWifiCallback);
            this.mIsConnectingWifi = true;
            return;
        }
        ScanResult scanResult = this.mScanResult;
        if (scanResult != null) {
            this.mConnUtils.a(context, this.mWifiSsid, ctz.Ml_(scanResult));
            this.mConnUtils.Mo_(this.mScanResult, this.mWifiPwd, true, this.mConnectWifiCallback);
            this.mIsConnectingWifi = true;
        } else {
            destroyLoadingDialog();
            LogUtil.h(TAG, "connectNewWifi() scan result is empty string.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean goBackPressed() {
        LogUtil.a(TAG, "Enter goBackPressed()");
        this.mWifiPwdLayout.setPasswordType(true);
        setKeyboardSetting();
        boolean z = this.mIsHideWifiList;
        if (!z && !this.mIsMainUser) {
            if (getSelectFragment(HagridDeviceBindResultFragment.class) != null) {
                HagridDeviceBindResultFragment.setBindStatus(8);
                popupFragment(HagridDeviceBindResultFragment.class);
            } else {
                popupFragment(HagridDeviceManagerFragment.class);
            }
            return false;
        }
        if (!z) {
            return true;
        }
        this.mIsHideWifiList = false;
        LogUtil.a(TAG, "goBackPressed mIsHideWifiList is true");
        changeLayoutView();
        return false;
    }

    private void goToNextView() {
        MeasurableDevice measurableDevice;
        this.mIsHideWifiList = false;
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            measurableDevice = ceo.d().d(this.mUniqueId, false);
        } else {
            LogUtil.h(TAG, "goToNextView, mDeviceInfo is null");
            measurableDevice = null;
        }
        if (cpa.x(this.mProductId) && measurableDevice != null && !(measurableDevice instanceof ctk)) {
            coy.d(this.mProductId, this.mUniqueId);
        }
        LogUtil.a(TAG, "goToNextView()...mConfigMode: ", Integer.valueOf(this.mConfigMode), " mDeviceSsid: ", this.mDeviceSsid, " mConnectedWifiSsid: ", this.mConnectedWifiSsid, " mIs2P4gWifi: ", Boolean.valueOf(this.mIs2P4gWifi));
        cpz.c(this.mConfigMode);
        Bundle bundleArgs = getBundleArgs();
        if (cpa.ae(this.mProductId)) {
            switchConfigNetworkFragment(bundleArgs);
            return;
        }
        if (this.mProductInfo.d().size() > 0) {
            WiFiDeviceBindGuideFragment wiFiDeviceBindGuideFragment = new WiFiDeviceBindGuideFragment();
            wiFiDeviceBindGuideFragment.setArguments(bundleArgs);
            switchFragment(wiFiDeviceBindGuideFragment);
            return;
        }
        int i = this.mConfigMode;
        if (i == 1 || i == 5) {
            WiFiDeviceBindResultFragment wiFiDeviceBindResultFragment = new WiFiDeviceBindResultFragment();
            wiFiDeviceBindResultFragment.setArguments(bundleArgs);
            switchFragment(wiFiDeviceBindResultFragment);
        } else {
            WiFiDeviceScanFragment wiFiDeviceScanFragment = new WiFiDeviceScanFragment();
            wiFiDeviceScanFragment.setArguments(bundleArgs);
            switchFragment(wiFiDeviceScanFragment);
        }
    }

    private void switchConfigNetworkFragment(Bundle bundle) {
        HagridDeviceBindResultFragment hagridDeviceBindResultFragment;
        Fragment selectFragment = getSelectFragment(HagridDeviceBindResultFragment.class);
        if (selectFragment instanceof HagridDeviceBindResultFragment) {
            hagridDeviceBindResultFragment = (HagridDeviceBindResultFragment) selectFragment;
            Object[] objArr = new Object[2];
            objArr[0] = "goToNextView resultFragment is null";
            objArr[1] = Boolean.valueOf(hagridDeviceBindResultFragment != null);
            LogUtil.a(TAG, objArr);
        } else {
            hagridDeviceBindResultFragment = null;
        }
        if (hagridDeviceBindResultFragment == null) {
            LogUtil.a(TAG, "goToNextView resultFragment is new");
            hagridDeviceBindResultFragment = new HagridDeviceBindResultFragment();
        }
        hagridDeviceBindResultFragment.setJumpFromWifiInfoFragment(true);
        HagridDeviceBindResultFragment.setBindStatus(7);
        try {
            hagridDeviceBindResultFragment.setArguments(bundle);
            switchFragment(hagridDeviceBindResultFragment);
        } catch (IllegalStateException unused) {
            LogUtil.b(TAG, "switchConfigNetworkFragment goToNextView IllegalStateException");
        }
    }

    private Bundle getBundleArgs() {
        Bundle bundle = new Bundle();
        bundle.putString("productId", this.mProductId);
        bundle.putString("outhName", this.mConnectedWifiSsid);
        bundle.putString("outhPd", this.mConnectedWifiPwd);
        bundle.putString("deviceSsid", this.mDeviceSsid);
        bundle.putInt("config_mode", this.mConfigMode);
        bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getWifiListInfo() {
        LogUtil.a(TAG, "getWifiListInfo()");
        this.mWifiScanProgressBar.setVisibility(0);
        ThreadPoolManager.d().c(TAG, new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                HagridWiFiInfoConfirmFragment.this.m180x6f61ec74();
            }
        });
    }

    /* renamed from: lambda$getWifiListInfo$10$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment, reason: not valid java name */
    /* synthetic */ void m180x6f61ec74() {
        this.mStorage5gWifiList.clear();
        List<ScanResult> c = cub.c(this.mainActivity, false);
        List<ScanResult> b = cub.b(this.mainActivity);
        List<WifiConfiguration> e = cub.e(this.mainActivity);
        removeWifiInfoFromList(b, cub.e(cub.c(this.mainActivity)));
        Bundle bundle = new Bundle();
        if (c instanceof Serializable) {
            bundle.putSerializable("ScanResults", (Serializable) c);
        }
        if (e instanceof Serializable) {
            bundle.putSerializable("WifiConfiguration", (Serializable) e);
        }
        Message obtain = Message.obtain();
        obtain.obj = c;
        obtain.setData(bundle);
        obtain.what = 1000;
        MyHandler myHandler = this.mHandler;
        if (myHandler != null) {
            if (this.mIsFirstCreate) {
                myHandler.sendMessageDelayed(obtain, 500L);
                this.mIsFirstCreate = false;
                return;
            } else {
                myHandler.sendMessage(obtain);
                return;
            }
        }
        LogUtil.h(TAG, "mHandler is null");
    }

    private void removeWifiInfoFromList(List<ScanResult> list, String str) {
        if (list == null) {
            return;
        }
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        for (ScanResult scanResult : list) {
            if (scanResult != null && !scanResult.SSID.equals(str)) {
                copyOnWriteArrayList.add(scanResult);
            }
        }
        this.mStorage5gWifiList.clear();
        this.mStorage5gWifiList.addAll(copyOnWriteArrayList);
    }

    static /* synthetic */ int lambda$sortWifiListInfo$11(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult.level - scanResult2.level;
    }

    private void sortWifiListInfo(List<ScanResult> list) {
        Collections.sort(list, new Comparator() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return HagridWiFiInfoConfirmFragment.lambda$sortWifiListInfo$11((ScanResult) obj, (ScanResult) obj2);
            }
        });
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

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        MeasurableDevice measurableDevice;
        LogUtil.a(TAG, "HagridWiFiInfoConfirmFragment onDestory");
        nsn.cKR_(this.mainActivity.getWindow());
        clearData();
        super.onDestroy();
        if (!TextUtils.isEmpty(this.mUniqueId)) {
            measurableDevice = ceo.d().d(this.mUniqueId, true);
        } else {
            LogUtil.a(TAG, "onDestory, mDeviceInfo is null");
            measurableDevice = null;
        }
        if (measurableDevice == null || (measurableDevice instanceof ctk)) {
            return;
        }
        this.mWeightInteractor.e();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        return goBackPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.mConnUtils.d();
        if (this.mainActivity != null) {
            this.mainActivity.unregisterReceiver(this.mReceiver);
        }
    }

    private void clearData() {
        LogUtil.a(TAG, "HagridWiFiInfoConfirmFragment clearData");
        if (this.mHandler != null) {
            LogUtil.a(TAG, "HagridWiFiInfoConfirmFragment mHandler removeCallbacksAndMessages");
            this.mHandler.removeCallbacksAndMessages(null);
            this.mHandler = null;
        }
        if (this.mAutoRefreshHandler != null) {
            LogUtil.a(TAG, "HagridWiFiInfoConfirmFragment mAutoRefreshHandler removeCallbacksAndMessages");
            this.mAutoRefreshHandler.removeCallbacksAndMessages(null);
            this.mAutoRefreshHandler = null;
        }
    }

    private void checkHavePermission() {
        if (this.mIsRequestPermission) {
            LogUtil.a(TAG, "checkHavePermission is RequestPermission back");
            this.mIsRequestPermission = false;
        } else {
            if (PermissionUtil.e(getActivity(), PermissionUtil.PermissionType.LOCATION) == PermissionUtil.PermissionResult.DENIED) {
                LogUtil.a(TAG, "checkHavePermission DENIED");
                this.mIsRequestPermission = true;
            }
            PermissionUtil.b(getActivity(), PermissionUtil.PermissionType.LOCATION, new AnonymousClass7(getActivity()));
        }
    }

    /* renamed from: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$7, reason: invalid class name */
    class AnonymousClass7 extends CustomPermissionAction {
        AnonymousClass7(Context context) {
            super(context);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a(HagridWiFiInfoConfirmFragment.TAG, "location permission ok.");
            if (dja.VG_(HagridWiFiInfoConfirmFragment.this.mainActivity)) {
                HagridWiFiInfoConfirmFragment.this.postScanWifiList();
            } else {
                HagridWiFiInfoConfirmFragment.this.showGpsDialog();
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            nsn.cLJ_(HagridWiFiInfoConfirmFragment.this.mainActivity, PermissionUtil.PermissionType.LOCATION, null, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$7$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridWiFiInfoConfirmFragment.AnonymousClass7.this.m190x6d095fad(view);
                }
            });
        }

        /* renamed from: lambda$onDenied$0$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment$7, reason: not valid java name */
        /* synthetic */ void m190x6d095fad(View view) {
            HagridWiFiInfoConfirmFragment.this.goBackPressed();
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            nsn.cLJ_(HagridWiFiInfoConfirmFragment.this.mainActivity, PermissionUtil.PermissionType.LOCATION, null, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$7$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    HagridWiFiInfoConfirmFragment.AnonymousClass7.this.m191xfc1a8e9d(view);
                }
            });
        }

        /* renamed from: lambda$onForeverDenied$1$com-huawei-health-device-ui-measure-fragment-HagridWiFiInfoConfirmFragment$7, reason: not valid java name */
        /* synthetic */ void m191xfc1a8e9d(View view) {
            HagridWiFiInfoConfirmFragment.this.goBackPressed();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postScanWifiList() {
        LogUtil.a(TAG, "checkHavePermission isHasPermissions true");
        if (this.mIsFirstCreate) {
            requireView().postDelayed(new Runnable() { // from class: com.huawei.health.device.ui.measure.fragment.HagridWiFiInfoConfirmFragment$$ExternalSyntheticLambda10
                @Override // java.lang.Runnable
                public final void run() {
                    HagridWiFiInfoConfirmFragment.this.showDataLoadingDialog();
                }
            }, 400L);
        }
        this.mHandler.sendEmptyMessage(1002);
    }

    private void destroyLoadingDialog() {
        CommonDialog21 commonDialog21 = this.mConnectingWifiDialog;
        if (commonDialog21 != null) {
            commonDialog21.cancel();
            this.mConnectingWifiDialog = null;
            this.mIsConnectingWifi = false;
            LogUtil.a(TAG, "destroy mLoadingDialog21");
        }
    }

    private void showLoadingDialog(String str) {
        if (this.mConnectingWifiDialog == null) {
            new CommonDialog21(getActivity(), R.style.app_update_dialogActivity);
            CommonDialog21 a2 = CommonDialog21.a(getActivity());
            this.mConnectingWifiDialog = a2;
            a2.e(str);
            this.mConnectingWifiDialog.a();
            this.mConnectingWifiDialog.setCancelable(false);
            return;
        }
        LogUtil.a(TAG, "mLoadingDialog21 is not null");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a(TAG, "onActivityResult: ", Integer.valueOf(i2), " requestCode: ", Integer.valueOf(i));
        if (i2 == -1 && i == 1000 && intent != null) {
            setArguments(intent.getExtras());
        }
    }

    private static void removeDuplicate(List<ScanResult> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(16);
        ArrayList arrayList = new ArrayList(16);
        for (ScanResult scanResult : list) {
            if (linkedHashSet.add(scanResult.SSID)) {
                arrayList.add(scanResult);
            }
        }
        list.clear();
        list.addAll(arrayList);
    }

    private void updateSsidAndPwd() {
        String obj = this.mWifiNameEdit.getText().toString();
        String obj2 = this.mWifiPwdLayout.getText().toString();
        LogUtil.a(TAG, "wifiNameString: ", obj, " wifiPwdString: ", cpw.d(obj2));
        if (TextUtils.isEmpty(obj)) {
            obj = this.mWifiSsid;
        }
        this.mWifiSsid = obj;
        if (TextUtils.isEmpty(obj2)) {
            obj2 = this.mWifiPwd;
        }
        this.mWifiPwd = obj2;
    }
}
