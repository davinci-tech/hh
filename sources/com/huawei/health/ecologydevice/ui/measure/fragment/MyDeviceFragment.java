package com.huawei.health.ecologydevice.ui.measure.fragment;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.CommonLibUtilApi;
import com.huawei.health.device.api.DataBaseapiApi;
import com.huawei.health.device.api.DeviceFragmentFactoryApi;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.manager.ResourceFileListener;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.DeviceStateConstants;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceSilentGuideFragment;
import com.huawei.health.versionmgr.api.VersionMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.cez;
import defpackage.cjv;
import defpackage.cke;
import defpackage.cpm;
import defpackage.cpp;
import defpackage.cun;
import defpackage.dbp;
import defpackage.dcq;
import defpackage.dcr;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dks;
import defpackage.gmz;
import defpackage.gnm;
import defpackage.jed;
import defpackage.msc;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class MyDeviceFragment extends ListFragment {
    private static final int BLUETOOTH_SWITCH = 1;
    private static final String CLICK_BUTTON_NO = "cancelEnableBluetooth";
    private static final String KIND = "kind";
    private static final int MSG_BATTERY = 4;
    private static final int MSG_CONNECTED = 0;
    private static final int MSG_CONNECTING = 3;
    private static final int MSG_DISCONNECTED = 2;
    private static final int MSG_TIME_OUT = 1;
    private static final int MSG_UNAVAILABLE = 5;
    private static final String PRODUCT_ID = "productId";
    private static final int SHOW_BINDED = 0;
    private static final int SHOW_UNIQUE_LENGTH = 3;
    private static final String TAG = "MyDeviceFragment";
    private static final String VIEW = "view";
    private static WeakReference<MyDeviceFragment> sFragment;
    private Context mContext;
    private DeviceInfoUtilsApi mDeviceInfoUtilsApi;
    private HealthDeviceEntryApi mHealthDeviceEntryApi;
    private HonourDeviceConstantsApi mHonourDeviceConstantsApi;
    private boolean mIsShowWiFiDevice;
    private ArrayList<String> mNotHeartRateDeviceList;
    private OnItemReconnectListener mOnItemReconnectListener;
    private ProductListAdapter mProductAdapter;
    private String mReconnectDevice;
    private VersionMgrApi mVersionMgrApi;
    private static final BroadcastReceiver DEVICE_BATTERY_RECEIVER = new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo currentDevice;
            if (!"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || intent.getExtras() == null || (currentDevice = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getCurrentDevice()) == null || currentDevice.getDeviceConnectState() != 2) {
                return;
            }
            MyDeviceFragment.sHandler.sendEmptyMessage(4);
        }
    };
    private static Handler sHandler = new Handler() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            MyDeviceFragment myDeviceFragment = MyDeviceFragment.sFragment != null ? (MyDeviceFragment) MyDeviceFragment.sFragment.get() : null;
            if (myDeviceFragment == null) {
                LogUtil.a(MyDeviceFragment.TAG, "MyDeviceFragment is null");
                return;
            }
            if (!myDeviceFragment.isAdded()) {
                LogUtil.a(MyDeviceFragment.TAG, "MyDeviceFragment current fragment not attached activity!");
                return;
            }
            int i = message.what;
            if (i == 0 || i == 1) {
                MyDeviceFragment.connectedOrTimeOut(message, myDeviceFragment);
                return;
            }
            if (i == 2) {
                MyDeviceFragment.updateAdapterList(message, myDeviceFragment);
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    if (i != 5) {
                        return;
                    }
                    myDeviceFragment.showBandUnavailableDialog(myDeviceFragment.mContext);
                    return;
                } else {
                    myDeviceFragment.initList(myDeviceFragment.mKind);
                    myDeviceFragment.showEmpty();
                    myDeviceFragment.mProductAdapter.notifyDataSetChanged();
                    return;
                }
            }
            LogUtil.a(MyDeviceFragment.TAG, "msg_connect_change state: " + myDeviceFragment.mProductAdapter.isConnecting + " msg: " + message.what);
            myDeviceFragment.mReconnectDevice = (String) message.obj;
            myDeviceFragment.mProductAdapter.setConnecting(true);
            myDeviceFragment.initList(myDeviceFragment.mKind);
            myDeviceFragment.mProductAdapter.notifyDataSetChanged();
        }
    };
    private static final BroadcastReceiver NON_LOCAL_BROAD_CASTRECEIVER = new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver()  intent : " + intent.getAction());
            try {
                DeviceInfo deviceInfo = intent.getParcelableExtra("deviceinfo") instanceof DeviceInfo ? (DeviceInfo) intent.getParcelableExtra("deviceinfo") : null;
                if (deviceInfo != null && MyDeviceFragment.sHandler != null) {
                    Message obtainMessage = MyDeviceFragment.sHandler.obtainMessage();
                    obtainMessage.obj = deviceInfo.getDeviceIdentify();
                    if (deviceInfo.getDeviceConnectState() == 2) {
                        LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver() DEVICE_CONNECTED");
                        obtainMessage.what = 0;
                        MyDeviceFragment.sHandler.sendMessage(obtainMessage);
                        return;
                    }
                    if (deviceInfo.getDeviceConnectState() != 3 && deviceInfo.getDeviceConnectState() != 4) {
                        if (deviceInfo.getDeviceConnectState() == 1) {
                            LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver() DEVICE_CONNECTING");
                            obtainMessage.what = 3;
                            MyDeviceFragment.sHandler.sendMessage(obtainMessage);
                            return;
                        } else if (deviceInfo.getDeviceConnectState() == 5) {
                            obtainMessage.what = 5;
                            MyDeviceFragment.sHandler.sendMessage(obtainMessage);
                            return;
                        } else {
                            LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver() onReceive other");
                            return;
                        }
                    }
                    LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver() DEVICE_DISCONNECTED");
                    obtainMessage.what = 2;
                    MyDeviceFragment.sHandler.sendMessage(obtainMessage);
                    ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setBattery(-1);
                    return;
                }
                LogUtil.a(MyDeviceFragment.TAG, "mNonLocalBroadcastReceiver() deviceInfo = null");
            } catch (ClassCastException e) {
                LogUtil.a(MyDeviceFragment.TAG, "DeviceInfo deviceInfo error :" + e.getMessage());
            }
        }
    };
    private static int sReconnectCount = 0;
    private CustomViewDialog mCustomViewDialog = null;
    private ArrayList<dcz> mProductInfoList = new ArrayList<>(10);
    private ArrayList<cjv> mProductList = new ArrayList<>(10);
    private ArrayList<cjv> mDeviceGroupInfoList = new ArrayList<>(10);
    private String mEntryType = "";
    private ArrayList<ContentValues> mListCompare = new ArrayList<>(10);
    private ArrayList<cpm> mWearInfoList = new ArrayList<>(10);
    private HealthDevice.HealthDeviceKind mKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN;
    private NoTitleCustomAlertDialog mCloseBtDialog = null;
    private boolean isFromFitnessAdvice = false;
    private int mLvMarginBottomForToolBar = 100;
    private CustomTextAlertDialog mDialog = null;
    private Handler mBindHandler = new Handler() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.4
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            dcz dczVar;
            if (message == null) {
                LogUtil.a(MyDeviceFragment.TAG, "MyDeviceFragment bindHandler handleMessage:msg == null");
                return;
            }
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            if (message.obj instanceof String) {
                dczVar = ResourceManager.e().d((String) message.obj);
            } else {
                dczVar = null;
            }
            if (dczVar != null) {
                Iterator it = MyDeviceFragment.this.mListCompare.iterator();
                while (it.hasNext()) {
                    ContentValues contentValues = (ContentValues) it.next();
                    if (contentValues.getAsString("productId").equals(dczVar.t())) {
                        cjv cjvVar = new cjv();
                        cjvVar.FU_(contentValues);
                        cjvVar.a(0);
                        cjvVar.c(dczVar);
                        MyDeviceFragment.this.mProductList.add(cjvVar);
                        MyDeviceFragment.this.mProductAdapter.notifyDataSetChanged();
                        return;
                    }
                }
            }
        }
    };

    public interface OnItemReconnectListener {
        void onItemClick(int i);

        void onReconnect(cpm cpmVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void connectedOrTimeOut(Message message, MyDeviceFragment myDeviceFragment) {
        LogUtil.a(TAG, "reconnect device timeout ？" + message.what);
        myDeviceFragment.initList(myDeviceFragment.mKind);
        String str = (String) message.obj;
        if (message.what == 1) {
            sHandler.removeMessages(1);
            myDeviceFragment.mProductAdapter.stopLoading();
            myDeviceFragment.mProductAdapter.setConnecting(false);
        } else if (!TextUtils.isEmpty(str) && str.equals(myDeviceFragment.mReconnectDevice)) {
            sHandler.removeMessages(1);
            myDeviceFragment.mProductAdapter.stopLoading();
            myDeviceFragment.mProductAdapter.setConnecting(false);
            myDeviceFragment.mReconnectDevice = null;
            ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).sendDeraultSwitch();
        } else {
            LogUtil.a(TAG, "connectedOrTimeOut other");
        }
        myDeviceFragment.initList(myDeviceFragment.mKind);
        myDeviceFragment.mProductAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void updateAdapterList(Message message, MyDeviceFragment myDeviceFragment) {
        String str = message.obj instanceof String ? (String) message.obj : null;
        if (!TextUtils.isEmpty(str) && str.equals(myDeviceFragment.mReconnectDevice)) {
            LogUtil.c(TAG, "stop loading...");
            sHandler.removeMessages(1);
            myDeviceFragment.mProductAdapter.stopLoading();
            myDeviceFragment.mProductAdapter.setConnecting(false);
            myDeviceFragment.mReconnectDevice = null;
        }
        LogUtil.c(TAG, "fragment.mReconnectDevice : " + myDeviceFragment.mReconnectDevice + " isConnecting: " + myDeviceFragment.mProductAdapter.getConnecting());
        myDeviceFragment.initList(myDeviceFragment.mKind);
        myDeviceFragment.mProductAdapter.notifyDataSetChanged();
    }

    public static void setFragment(WeakReference<MyDeviceFragment> weakReference) {
        sFragment = weakReference;
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a(TAG, "MyDeviceFragment onCreateView");
        setFragment(new WeakReference(this));
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ResourceManager.e().e(new BindResourceFileListener());
        this.mVersionMgrApi = (VersionMgrApi) Services.c("HWVersionMgr", VersionMgrApi.class);
        this.mDeviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
        this.mHealthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
        this.mHonourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
        init();
        return onCreateView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEmpty() {
        LinearLayout linearLayout = (LinearLayout) this.child.findViewById(R.id.hw_no_bonded_device_layout);
        if (this.mProductList.isEmpty() && this.mListCompare.isEmpty()) {
            linearLayout.setVisibility(0);
            int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
            float f = Resources.getSystem().getDisplayMetrics().density;
            LogUtil.c(TAG, "MyDeviceFragment heightInDp is " + ((height / f) + 0.5f));
            if (linearLayout.getLayoutParams() instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
                layoutParams.topMargin = (int) ((height / 3.0d) - (f * 72.5d));
                linearLayout.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        linearLayout.setVisibility(8);
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        FragmentActivity activity = getActivity();
        this.mContext = activity;
        if (activity == null) {
            LogUtil.c(TAG, "onActivityCreated mContext== null");
        }
    }

    private void init() {
        Bundle arguments = getArguments();
        String string = (arguments == null || arguments.getString("root_in_me") == null) ? "" : arguments.getString("root_in_me");
        if (arguments != null && arguments.getString(KIND) != null) {
            this.mKind = dks.c(arguments.getString(KIND));
            LogUtil.a(TAG, "====the kind is " + this.mKind.name());
        }
        if (arguments != null) {
            this.isFromFitnessAdvice = arguments.getBoolean("isFromFitnessAdvice", false);
            try {
                this.mNotHeartRateDeviceList = arguments.getStringArrayList("notHeartRateDeviceList");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b(TAG, "onCreateView init ArrayIndexOutOfBoundsException");
            }
        }
        if (CommonUtil.ce()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), NON_LOCAL_BROAD_CASTRECEIVER, intentFilter, LocalBroadcast.c, null);
            registerBatteryBroadcast();
        }
        initList(this.mKind);
        this.mProductAdapter = new ProductListAdapter(this.mProductList);
        if (arguments != null && arguments.getString("EntryType") != null) {
            String string2 = arguments.getString("EntryType");
            this.mEntryType = string2;
            this.mIsShowWiFiDevice = "WiFiDevice".equals(string2);
        }
        this.myDevicesListview = (ListView) this.child.findViewById(R.id.device_list_view);
        if (this.myDevicesListview.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.myDevicesListview.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, this.mLvMarginBottomForToolBar);
            this.myDevicesListview.setLayoutParams(layoutParams);
        }
        this.myDevicesListview.setAdapter((ListAdapter) this.mProductAdapter);
        clickAdapterItem(string);
    }

    private void clickAdapterItem(final String str) {
        this.mProductAdapter.setOnItemReconnectListener(new OnItemReconnectListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.5
            @Override // com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.OnItemReconnectListener
            public void onReconnect(cpm cpmVar) {
                MyDeviceFragment.this.connectStatus(cpmVar);
            }

            @Override // com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.OnItemReconnectListener
            public void onItemClick(int i) {
                MyDeviceFragment.this.clickItemPosition(i);
            }
        });
        if ("List".equals(this.mEntryType)) {
            setTitle(getResources().getString(R.string.IDS_device_show_operate_my_device));
            showEmpty();
            showMoreButton(true, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.this.m295xab00a490(str, view);
                }
            });
            setAddButton(str);
        } else {
            dcr c = ResourceManager.e().a().c(this.mKind);
            if (c != null) {
                setTitle(getResources().getString(R.string.IDS_device_show_device_string, getResources().getString(dcx.e(c.e()))));
                showButton(false, null);
                showMoreButton(false, null);
            }
        }
        showButton(false, null);
        LogUtil.a(TAG, "MyDeviceFragment init finished");
    }

    /* renamed from: lambda$clickAdapterItem$0$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment, reason: not valid java name */
    /* synthetic */ void m295xab00a490(String str, View view) {
        if ("me".equals(str)) {
            switchFragment(new DeviceBindingFragment(), str);
        } else {
            switchFragment(new DeviceBindingFragment());
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickItemPosition(int i) {
        if (i >= this.mProductList.size()) {
            LogUtil.a(TAG, "onItemClick position >= productInfoList.size()");
            return;
        }
        cjv cjvVar = this.mProductList.get(i);
        boolean z = this.mProductAdapter.isConnecting;
        LogUtil.a(TAG, "onItemClick deviceGroupInfo.getDeviceType() " + cjvVar.a());
        if (cjvVar.a() == 0) {
            LogUtil.a(TAG, "onItemClick sanfang");
            if (z && this.mReconnectDevice != null) {
                LogUtil.c(TAG, "other device is connecting.");
                return;
            }
            dcz dczVar = (dcz) cjvVar.i();
            if (dczVar != null) {
                LogUtil.a(TAG, "productInfo != null And position = " + i + "productId = " + dczVar.t());
                if ("List".equals(this.mEntryType) || "WiFiDevice".equals(this.mEntryType)) {
                    LogUtil.a(TAG, "EntryType == List");
                    entryTypeEqualsList(i, dczVar, cjvVar);
                    return;
                } else if ("Measure".equals(this.mEntryType)) {
                    LogUtil.a(TAG, "EntryType == Measure");
                    useWifiDeviceMeasure(dczVar);
                    entryTypeEqualsMeasure(cjvVar);
                    return;
                } else if ("Pick".equals(this.mEntryType)) {
                    LogUtil.a(TAG, "EntryType == Pick");
                    entryTypeEqualsPick(dczVar);
                    return;
                } else {
                    LogUtil.a(TAG, "clickItemPosition third");
                    return;
                }
            }
            return;
        }
        if (cjvVar.a() == 1) {
            smartWearer(cjvVar, z);
        } else {
            LogUtil.a(TAG, "clickItemPosition other device");
        }
    }

    private void useWifiDeviceMeasure(dcz dczVar) {
        if (!this.mHonourDeviceConstantsApi.isWiFiDevice(dczVar.t()) || CommonUtil.aa(this.mContext)) {
            return;
        }
        ((CommonLibUtilApi) Services.c("PluginDevice", CommonLibUtilApi.class)).showWiFiEnableDialog(this.mContext);
    }

    private void smartWearer(cjv cjvVar, boolean z) {
        LogUtil.a(TAG, "onItemClick wear");
        cpm cpmVar = (cpm) cjvVar.i();
        String str = this.mReconnectDevice;
        boolean z2 = false;
        boolean z3 = (str == null || str.equals(cpmVar.a())) ? false : true;
        if (z && cpmVar.e() != 2) {
            z2 = true;
        }
        if (z3 && z2) {
            LogUtil.c(TAG, "other devices is connection,can not start activity.");
            return;
        }
        if (cpmVar.e() == 2) {
            openWearHome(cpmVar.a());
            return;
        }
        if (this.mDeviceInfoUtilsApi.getWearInfo().size() == 1) {
            openWearHome(cpmVar.a());
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.mContext, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
        intent.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, cpmVar.d());
        intent.putExtra("device_identify", cpmVar.a());
        intent.putExtra("device_picID", cpmVar.m());
        intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, cpmVar.i());
        gnm.aPB_(this.mContext, intent);
        LogUtil.c(TAG, "onclick wear not connected name:" + cpmVar.d() + "identify:" + cpmVar.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectStatus(cpm cpmVar) {
        LogUtil.a(TAG, "user choose connect");
        List<DeviceInfo> wearDeviceList = this.mDeviceInfoUtilsApi.getWearDeviceList();
        if (wearDeviceList == null) {
            LogUtil.a(TAG, "mDeviceInfoList is null");
            return;
        }
        if (cpmVar.o() == 1) {
            LogUtil.a(TAG, "handleWorkMode goingConnected == DeviceWorkMode.RUN_WORK_MODE");
            wearRunWorkMode(cpmVar, wearDeviceList);
        } else {
            LogUtil.a(TAG, "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
            wearRunOtherMode(cpmVar, wearDeviceList);
            this.mDeviceInfoUtilsApi.resetUpdate();
        }
        sHandler.sendEmptyMessageDelayed(1, 20000L);
    }

    private void wearRunWorkMode(cpm cpmVar, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a(TAG, "wearRunWorkMode handleWorkMode set device enable");
                deviceInfo.setDeviceActiveState(1);
                deviceInfo.setDeviceConnectState(1);
            }
            if (!cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && deviceInfo.getAutoDetectSwitchStatus() == 1 && deviceInfo.getDeviceActiveState() == 1) {
                LogUtil.a(TAG, "wearRunWorkMode handleWorkMode target device disable");
                deviceInfo.setDeviceActiveState(0);
                deviceInfo.setDeviceConnectState(3);
            }
        }
    }

    private void wearRunOtherMode(cpm cpmVar, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a(TAG, "wearRunOtherMode handleWorkMode set device enable");
                deviceInfo.setDeviceActiveState(1);
                deviceInfo.setDeviceConnectState(1);
            }
            if (!cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && deviceInfo.getAutoDetectSwitchStatus() != 1 && deviceInfo.getDeviceActiveState() == 1) {
                LogUtil.a(TAG, "wearRunOtherMode handleWorkMode target device disable");
                deviceInfo.setDeviceActiveState(0);
                deviceInfo.setDeviceConnectState(3);
            }
        }
    }

    private void setAddButton(final String str) {
        HealthToolBar healthToolBar = (HealthToolBar) this.child.findViewById(R.id.buttomview);
        healthToolBar.setVisibility(0);
        healthToolBar.cHc_(LayoutInflater.from(cpp.a()).inflate(R.layout.hw_toolbar_bottomview, (ViewGroup) null));
        healthToolBar.setIconVisible(1, 8);
        healthToolBar.setIcon(2, R.drawable._2131429693_res_0x7f0b093d);
        healthToolBar.setIconTitle(2, this.mainActivity.getResources().getString(R.string._2130841394_res_0x7f020f32));
        healthToolBar.setIconVisible(3, 8);
        healthToolBar.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda5
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                MyDeviceFragment.this.m299x42e52e38(str, i);
            }
        });
        healthToolBar.cHf_(this.mainActivity);
    }

    /* renamed from: lambda$setAddButton$1$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment, reason: not valid java name */
    /* synthetic */ void m299x42e52e38(String str, int i) {
        if (i != 2) {
            return;
        }
        if ("me".equals(str)) {
            switchFragment(new DeviceBindingFragment(), str);
        } else {
            switchFragment(new DeviceBindingFragment());
        }
    }

    private void getWiFiDevice() {
        dcz d;
        ArrayList<String> bondedWiFiDevices = this.mHealthDeviceEntryApi.getBondedWiFiDevices();
        if (bondedWiFiDevices != null && bondedWiFiDevices.size() > 0) {
            Iterator<String> it = bondedWiFiDevices.iterator();
            while (it.hasNext()) {
                String wiFiDeviceProductId = ((DataBaseapiApi) Services.c("PluginDevice", DataBaseapiApi.class)).getWiFiDeviceProductId(it.next());
                if (!TextUtils.isEmpty(wiFiDeviceProductId) && (d = ResourceManager.e().d(wiFiDeviceProductId)) != null && !d.n().d().trim().isEmpty()) {
                    this.mProductInfoList.add(d);
                }
            }
        }
        ArrayList<cjv> c = dbp.c(this.mProductInfoList);
        if (c.size() > 0) {
            this.mProductList.addAll(c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initList(HealthDevice.HealthDeviceKind healthDeviceKind) {
        int i;
        LogUtil.a(TAG, "enter initList");
        clearList();
        if (this.mIsShowWiFiDevice) {
            getWiFiDevice();
            return;
        }
        boolean z = this.isFromFitnessAdvice && healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_HEART_RATE;
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_UNKNOWN || z) {
            addWearDeviceInfo(healthDeviceKind);
            Iterator<cpm> it = this.mWearInfoList.iterator();
            i = 0;
            while (it.hasNext()) {
                cpm next = it.next();
                cjv cjvVar = new cjv();
                cjvVar.a(1);
                String a2 = next.a();
                String f = next.f();
                ContentValues contentValues = new ContentValues();
                contentValues.put("uniqueId", a2);
                contentValues.put("productId", f);
                cjvVar.FU_(contentValues);
                cjvVar.c(next);
                if (next.e() == 2) {
                    i++;
                    this.mDeviceGroupInfoList.add(0, cjvVar);
                } else {
                    this.mDeviceGroupInfoList.add(cjvVar);
                }
                ProductListAdapter productListAdapter = this.mProductAdapter;
                boolean z2 = productListAdapter != null && productListAdapter.getConnecting();
                String str = this.mReconnectDevice;
                boolean z3 = str != null && str.equals(next.a());
                if (z2 && z3) {
                    next.a(1);
                }
                LogUtil.c(TAG, " has wear device name :" + next.d());
                LogUtil.c(TAG, " has wear device state :" + next.e());
            }
        } else {
            i = 0;
        }
        LogUtil.a(TAG, " has wear DEVICE_CONNECTED : " + i);
        compareDevice(this.mHealthDeviceEntryApi.getBondedDeviceByDeviceKind(healthDeviceKind));
        if (i > 0 && this.mDeviceGroupInfoList.size() > i) {
            addProduct(this.mDeviceGroupInfoList.subList(0, i));
            ArrayList<cjv> arrayList = this.mProductList;
            ArrayList<cjv> arrayList2 = this.mDeviceGroupInfoList;
            arrayList.addAll(arrayList2.subList(i, arrayList2.size()));
        } else {
            this.mProductList.addAll(this.mDeviceGroupInfoList);
        }
        LogUtil.a(TAG, "MyDeviceFragment productInfos size is ", Integer.valueOf(this.mProductList.size()), "list_compare size is ", Integer.valueOf(this.mListCompare.size()));
    }

    private void clearList() {
        this.mProductList.clear();
        this.mDeviceGroupInfoList.clear();
        this.mProductInfoList.clear();
        this.mListCompare.clear();
        this.mWearInfoList.clear();
    }

    private void addProduct(List<cjv> list) {
        for (int i = 0; i < list.size(); i++) {
            cjv cjvVar = list.get(i);
            if (i == list.size() - 1) {
                cjvVar.e(true);
            } else {
                cjvVar.e(false);
            }
            this.mProductList.add(cjvVar);
        }
        cjv cjvVar2 = new cjv();
        cjvVar2.a(3);
        cjvVar2.c(new Object());
        this.mProductList.add(cjvVar2);
    }

    private void addWearDeviceInfo(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (this.isFromFitnessAdvice && healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
            ArrayList<cpm> wearInfoHeartRate = this.mDeviceInfoUtilsApi.getWearInfoHeartRate(this.mNotHeartRateDeviceList);
            if (wearInfoHeartRate.size() <= 0 || !CommonUtil.ce()) {
                return;
            }
            LogUtil.c(TAG, " has wear device getWearInfoHeartRate");
            this.mWearInfoList = wearInfoHeartRate;
            return;
        }
        if (this.mDeviceInfoUtilsApi.getWearInfo().size() <= 0 || !CommonUtil.ce()) {
            return;
        }
        LogUtil.c(TAG, " has wear device");
        this.mWearInfoList = this.mDeviceInfoUtilsApi.getWearInfo();
    }

    private void compareDevice(ArrayList<ContentValues> arrayList) {
        Iterator<ContentValues> it = arrayList.iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            dcz d = ResourceManager.e().d(next.getAsString("productId"));
            if (d == null) {
                LogUtil.h(TAG, "MyDeviceFragment compareDevice get productInfo is null");
            } else {
                cjv cjvVar = new cjv();
                cjvVar.FU_(next);
                cjvVar.c(d);
                cjvVar.a(0);
                cjvVar.a(d.h());
                cjvVar.a(new cke("deviceUsedTime").b(cpp.a(), next.getAsString("uniqueId")));
                this.mDeviceGroupInfoList.add(cjvVar);
            }
        }
        Collections.sort(this.mDeviceGroupInfoList);
        LogUtil.a(TAG, "MyDeviceFragment mProductInfoList size is " + this.mDeviceGroupInfoList.size());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        initList(this.mKind);
        this.mProductAdapter.notifyDataSetChanged();
        showEmpty();
        if (cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, null, "", TAG) != -1) {
            this.mProductAdapter.notifyDataSetChanged();
            LogUtil.a(TAG, "Device's battery is not -1 , battery is " + cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, null, "", TAG));
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        WeakReference<MyDeviceFragment> weakReference = sFragment;
        if (weakReference != null) {
            weakReference.clear();
        }
        ResourceManager.e().f();
        if (CommonUtil.ce()) {
            unRegisterBatteryBroadcast();
            try {
                LogUtil.a(TAG, "unregisterWearBroadcast");
                BaseApplication.getContext().unregisterReceiver(NON_LOCAL_BROAD_CASTRECEIVER);
            } catch (IllegalArgumentException e) {
                LogUtil.a(TAG, e.getMessage());
            } catch (Exception unused) {
                LogUtil.a(TAG, "unregisterReceiver Exception");
            }
        }
    }

    private void entryTypeEqualsPick(final dcz dczVar) {
        if (BluetoothAdapter.getDefaultAdapter().getState() == 10) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
            builder.e(R.string.IDS_device_bluetooth_open_request);
            builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.this.m297xc6888e56(dczVar, view);
                }
            });
            builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.this.m298xff68eef5(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            e.show();
            return;
        }
        LogUtil.a(TAG, "MyDeviceFragment sendMessage with bluetooth open");
        sendMessageInMyDeviceFragment(dczVar);
    }

    /* renamed from: lambda$entryTypeEqualsPick$2$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment, reason: not valid java name */
    /* synthetic */ void m297xc6888e56(dcz dczVar, View view) {
        LogUtil.a(TAG, "MyDeviceFragment sendMessage with bluetooth off");
        sendMessageInMyDeviceFragment(dczVar);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: lambda$entryTypeEqualsPick$3$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment, reason: not valid java name */
    /* synthetic */ void m298xff68eef5(View view) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = CLICK_BUTTON_NO;
        cpp.Kk_(TAG, obtain);
        getActivity().finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void entryTypeEqualsMeasure(cjv cjvVar) {
        dcz dczVar = (dcz) cjvVar.i();
        ContentValues FT_ = cjvVar.FT_();
        String asString = FT_ != null ? FT_.getAsString("uniqueId") : "";
        Fragment measureFragment = ((DeviceFragmentFactoryApi) Services.c("PluginDevice", DeviceFragmentFactoryApi.class)).getMeasureFragment(dczVar.t());
        if (measureFragment != null) {
            Bundle bundle = new Bundle();
            bundle.putString(VIEW, "measure");
            bundle.putString("productId", dczVar.t());
            bundle.putParcelable("commonDeviceInfo", FT_);
            bundle.putString(KIND, dczVar.l().name());
            measureFragment.setArguments(bundle);
            switchFragment(measureFragment);
            return;
        }
        if ("H5".equals(dczVar.b()) && "H5".equals(dczVar.r())) {
            checkHealthKitLinked(dczVar, asString);
        } else if ("H5".equals(dczVar.r())) {
            jumpToWebViewActivity(dczVar, asString);
        } else {
            jumpToSilentGuideOrMeasureFragment(cjvVar, dczVar, asString);
        }
    }

    private void jumpToSilentGuideOrMeasureFragment(cjv cjvVar, dcz dczVar, String str) {
        if (this.mHealthDeviceEntryApi.isDeviceKitUniversal(dczVar.t())) {
            com.huawei.hihealth.device.open.HealthDevice bondedDeviceUniversal = this.mHealthDeviceEntryApi.getBondedDeviceUniversal(dczVar.t(), str);
            dcz d = ResourceManager.e().d(dczVar.t());
            if (d != null && bondedDeviceUniversal != null) {
                if (HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(d.p())) {
                    jumpToSilentGuideFragment(cjvVar);
                    return;
                } else {
                    jumpToMeasureFragment(cjvVar);
                    return;
                }
            }
            LogUtil.b(TAG, "the productInfo or device is null");
            return;
        }
        MeasurableDevice measurableDevice = this.mHealthDeviceEntryApi.getBondedDevice(dczVar.t()) instanceof MeasurableDevice ? (MeasurableDevice) this.mHealthDeviceEntryApi.getBondedDevice(dczVar.t()) : null;
        boolean equals = HiAnalyticsConstant.KeyAndValue.NUMBER_01.equals(dczVar.p());
        if (measurableDevice != null) {
            if (measurableDevice.isAutoDevice() && equals) {
                jumpToSilentGuideFragment(cjvVar);
                return;
            } else {
                jumpToMeasureFragment(cjvVar);
                return;
            }
        }
        LogUtil.b(TAG, "device is null!");
    }

    private void checkHealthKitLinked(dcz dczVar, String str) {
        if ("true".equals(gmz.d().c(402))) {
            dks.n(dczVar.t());
            jumpToWebViewActivity(dczVar, str);
        } else {
            dks.WC_(this.mainActivity, dczVar.t(), str, true);
        }
    }

    private void jumpToWebViewActivity(dcz dczVar, String str) {
        dks.n(dczVar.t());
        if ("1".equals(dczVar.j())) {
            dks.d(getContext(), dczVar, dczVar.t(), str);
        } else {
            Intent intent = new Intent();
            intent.setPackage(cez.w);
            intent.setClassName(cez.w, "com.huawei.operation.activity.WebViewActivity");
            if (!TextUtils.isEmpty(dczVar.r()) && !TextUtils.isEmpty(dczVar.b())) {
                intent.putExtra("url", dcq.b().c(dczVar.t()) + "#/type=2/sn=" + str);
            } else {
                intent.putExtra("url", dcq.b().c(dczVar.t()));
            }
            intent.putExtra("productId", dczVar.t());
            ContentValues contentValues = new ContentValues();
            contentValues.put("uniqueId", str);
            contentValues.put("name", dks.e(dczVar.t(), dczVar.n().b()));
            contentValues.put("deviceType", dczVar.l().name());
            contentValues.put(Constants.KEY_BLE_SCAN_MODE, Integer.valueOf(dczVar.x().c()));
            intent.putExtra("commonDeviceInfo", contentValues);
            intent.putExtra("bleIntroductionType", dczVar.m().d());
            if (!TextUtils.isEmpty(WebViewActivity.getProductId())) {
                LogUtil.a(TAG, "WebViewActivity productId is not empty");
                intent.setFlags(335544320);
            }
            startActivity(intent);
        }
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    private void jumpToSilentGuideFragment(cjv cjvVar) {
        DeviceSilentGuideFragment deviceSilentGuideFragment = new DeviceSilentGuideFragment();
        dcz dczVar = (dcz) cjvVar.i();
        Bundle bundle = new Bundle();
        bundle.putString(VIEW, "bond");
        bundle.putString("productId", dczVar.t());
        bundle.putParcelable("commonDeviceInfo", cjvVar.FT_());
        deviceSilentGuideFragment.setArguments(bundle);
        switchFragment(deviceSilentGuideFragment);
    }

    private void jumpToMeasureFragment(cjv cjvVar) {
        if (cjvVar != null) {
            dcz dczVar = (dcz) cjvVar.i();
            Bundle bundle = new Bundle();
            String t = dczVar.t();
            if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(t)) {
                LogUtil.a(TAG, "MyDeviceFragment hygrid scale set type:", -1);
                bundle.putInt("type", -1);
            }
            bundle.putString(VIEW, "measure");
            bundle.putString(KIND, dczVar.l().name());
            bundle.putString("productId", t);
            bundle.putParcelable("commonDeviceInfo", cjvVar.FT_());
            Fragment measureFragment = ((DeviceFragmentFactoryApi) Services.c("PluginDevice", DeviceFragmentFactoryApi.class)).getMeasureFragment(dczVar.l().name());
            if (measureFragment != null) {
                measureFragment.setArguments(bundle);
            }
            switchFragment(measureFragment);
        }
    }

    private void entryTypeEqualsList(final int i, final dcz dczVar, final cjv cjvVar) {
        Fragment productIntroductionFragment;
        if (dczVar.e().size() <= 0) {
            LogUtil.a(TAG, "MyDeviceFragment Disassociate this device?");
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(getActivity());
            builder.e(R.string.IDS_device_selection_cancel_unbind_device);
            builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.this.m296x6eef7077(cjvVar, dczVar, i, view);
                }
            });
            builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda7
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            e.show();
            return;
        }
        ContentValues FT_ = cjvVar.FT_();
        Bundle bundle = new Bundle();
        bundle.putString("productId", dczVar.t());
        DeviceMainActivityApi deviceMainActivityApi = (DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class);
        if (this.mHonourDeviceConstantsApi.isHuaweiWspScaleProduct(dczVar.t())) {
            productIntroductionFragment = deviceMainActivityApi.getPluginDeviceFragment(WeightConstants.FragmentType.HAGRID_DEVICE_MANAGER);
        } else if (this.mHonourDeviceConstantsApi.isHonourNewDevice(dczVar.t())) {
            productIntroductionFragment = deviceMainActivityApi.getPluginDeviceFragment(WeightConstants.FragmentType.HONOUR_DEVICE);
        } else if (this.mDeviceInfoUtilsApi.isWiFiDevice(dczVar.t())) {
            productIntroductionFragment = deviceMainActivityApi.getPluginDeviceFragment(WeightConstants.FragmentType.WIFI_PRODUCT_INTRODUCTION);
        } else {
            productIntroductionFragment = new ProductIntroductionFragment();
        }
        if (productIntroductionFragment == null) {
            LogUtil.h(TAG, "entryTypeEqualsList fragment is null");
            return;
        }
        bundle.putParcelable("commonDeviceInfo", FT_);
        productIntroductionFragment.setArguments(bundle);
        switchFragment(productIntroductionFragment);
    }

    /* renamed from: lambda$entryTypeEqualsList$4$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment, reason: not valid java name */
    /* synthetic */ void m296x6eef7077(cjv cjvVar, dcz dczVar, int i, View view) {
        if (this.mHealthDeviceEntryApi.unbindDeviceByUniqueId(cjvVar.FT_().getAsString("uniqueId"))) {
            this.mHealthDeviceEntryApi.stopMeasure(dczVar.t(), cjvVar.FT_().getAsString("uniqueId"));
            this.mProductList.remove(i);
            this.mProductAdapter.notifyDataSetChanged();
            showEmpty();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void sendMessageInMyDeviceFragment(dcz dczVar) {
        Message obtain = Message.obtain();
        obtain.obj = dczVar.t();
        obtain.what = 1;
        cpp.Kk_(TAG, obtain);
        getActivity().finish();
    }

    class BindResourceFileListener implements ResourceFileListener {
        private BindResourceFileListener() {
        }

        @Override // com.huawei.health.device.manager.ResourceFileListener
        public void onResult(int i, String str) {
            boolean z;
            LogUtil.a(MyDeviceFragment.TAG, "MyDeviceFragment resultCode is ", Integer.valueOf(i), " resultValue is ", str);
            Iterator it = MyDeviceFragment.this.mListCompare.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                } else if (((ContentValues) it.next()).getAsString("productId").equals(str)) {
                    z = true;
                    break;
                }
            }
            if (i == 200 && z) {
                Message obtain = Message.obtain();
                obtain.what = 0;
                obtain.obj = str;
                MyDeviceFragment.this.mBindHandler.sendMessage(obtain);
            }
        }
    }

    class ProductListAdapter extends com.huawei.health.ecologydevice.ui.measure.adapter.ListAdapter {
        private static final int NORMAL_ITEM = 0;
        private static final int SUB_COLUMN_ITEM = 1;
        private boolean isConnecting = false;
        HealthProgressBar mLoadingImg;

        ProductListAdapter(ArrayList<cjv> arrayList) {
            LogUtil.a(MyDeviceFragment.TAG, "ProductListAdapter list.size" + arrayList.size());
            MyDeviceFragment.this.mProductList = arrayList;
            super.setProductList(MyDeviceFragment.this.mProductList);
        }

        public void setOnItemReconnectListener(OnItemReconnectListener onItemReconnectListener) {
            MyDeviceFragment.this.mOnItemReconnectListener = onItemReconnectListener;
        }

        public void stopLoading() {
            HealthProgressBar healthProgressBar = this.mLoadingImg;
            if (healthProgressBar != null) {
                healthProgressBar.setVisibility(8);
            }
        }

        public void startLoading() {
            HealthProgressBar healthProgressBar = this.mLoadingImg;
            if (healthProgressBar != null) {
                healthProgressBar.setVisibility(0);
            }
        }

        public void setConnecting(boolean z) {
            this.isConnecting = z;
        }

        public boolean getConnecting() {
            return this.isConnecting;
        }

        @Override // com.huawei.health.ecologydevice.ui.measure.adapter.ListAdapter
        public void setProductList(ArrayList<cjv> arrayList) {
            super.setProductList(arrayList);
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public int getItemViewType(int i) {
            return (MyDeviceFragment.this.mProductList == null || i >= MyDeviceFragment.this.mProductList.size() || ((cjv) MyDeviceFragment.this.mProductList.get(i)).a() != 3) ? 0 : 1;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            LogUtil.a(MyDeviceFragment.TAG, "getView first position:" + i);
            LogUtil.a(MyDeviceFragment.TAG, "MyDeviceFragment getItemViewType: " + getItemViewType(i));
            if (getItemViewType(i) == 1) {
                return LayoutInflater.from(cpp.a()).inflate(R.layout.my_device_list_result_sub_item, (ViewGroup) null);
            }
            View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.my_device_list_result, (ViewGroup) null);
            if (inflate == null) {
                LogUtil.a(MyDeviceFragment.TAG, "getView convertView is null");
                return inflate;
            }
            HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.reconnect_layout);
            HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.reconnect_loading_img);
            this.mLoadingImg = healthProgressBar;
            healthProgressBar.setLayerType(1, null);
            dealAdapterConvertView(inflate, i, healthButton);
            return inflate;
        }

        private void dealAdapterConvertView(View view, int i, HealthButton healthButton) {
            cjv cjvVar;
            if (MyDeviceFragment.this.mProductList == null || i >= MyDeviceFragment.this.mProductList.size()) {
                LogUtil.b(MyDeviceFragment.TAG, "ProductListAdapter dealAdapterConvertView position is invalid");
                return;
            }
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.device_manager_on_device_device_name_tv);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_device_icon_bind);
            ImageView imageView2 = (ImageView) view.findViewById(R.id.iv_device_icon_bind_wear);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.device_manager_on_device_device_states_info_rl);
            linearLayout.setVisibility(8);
            ImageView imageView3 = (ImageView) view.findViewById(R.id.device_manager_on_device_connect_states_iv);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.device_manager_on_device_connect_states_tv);
            HealthDivider healthDivider = (HealthDivider) view.findViewById(R.id.hw_show_main_layout_sport_bottom_image_interval);
            ImageView imageView4 = (ImageView) view.findViewById(R.id.rightarrow_icon);
            clickItemRelativeLayout(i, (RelativeLayout) view.findViewById(R.id.iv_device_icon_rl_rl), imageView4, healthDivider);
            RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.device_manager_on_device_battery_level_rl);
            LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.reconnect_loading_layout);
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.device_manager_on_device_battery_level_tv);
            ImageView imageView5 = (ImageView) view.findViewById(R.id.device_manager_on_device_battery_level_iv);
            cjv cjvVar2 = (cjv) MyDeviceFragment.this.mProductList.get(i);
            if (cjvVar2.a() == 1) {
                cpm cpmVar = (cpm) cjvVar2.i();
                dealWearInitState(cpmVar, healthTextView, imageView, imageView2, linearLayout);
                if (cpmVar.e() == 2) {
                    LogUtil.a(MyDeviceFragment.TAG, "getView deviceGroupInfo.isLine():" + cjvVar2.g());
                    dealSplitLine(cjvVar2, healthDivider);
                    if (MyDeviceFragment.this.mVersionMgrApi.isDeviceOtaUpdating(cpmVar.a())) {
                        LogUtil.a(MyDeviceFragment.TAG, "current device is OTAing");
                        upgradeCondition(imageView4, relativeLayout, linearLayout2, imageView3, healthTextView2);
                    } else {
                        doWithoutUpgradeCondition(cpmVar, healthTextView3, relativeLayout, imageView5, imageView3);
                        dealViewWhetherVisible(imageView4, healthTextView2, healthButton, linearLayout2);
                    }
                    cjvVar = cjvVar2;
                } else {
                    cjvVar = cjvVar2;
                    if (cpmVar.e() == 1) {
                        imageView4.setVisibility(8);
                        dealDeviceConnectState(healthTextView2, imageView3, relativeLayout, healthButton, linearLayout2);
                    } else {
                        imageView4.setVisibility(8);
                        healthTextView2.setText(R.string._2130841443_res_0x7f020f63);
                        dealDeviceOtherState(cpmVar, imageView3, relativeLayout, linearLayout2, healthButton);
                    }
                }
            } else {
                cjvVar = cjvVar2;
            }
            dealThirdDevice(cjvVar, imageView4, imageView2, imageView, healthTextView);
        }

        private void dealViewWhetherVisible(ImageView imageView, HealthTextView healthTextView, HealthButton healthButton, LinearLayout linearLayout) {
            imageView.setVisibility(0);
            healthTextView.setText(R.string._2130841442_res_0x7f020f62);
            healthButton.setVisibility(8);
            linearLayout.setVisibility(8);
        }

        private void clickItemRelativeLayout(final int i, RelativeLayout relativeLayout, ImageView imageView, HealthDivider healthDivider) {
            relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$ProductListAdapter$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.ProductListAdapter.this.m300x7043c13(i, view);
                }
            });
            dealLanguageAndSplitLine(imageView, i, healthDivider);
        }

        /* renamed from: lambda$clickItemRelativeLayout$0$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment$ProductListAdapter, reason: not valid java name */
        /* synthetic */ void m300x7043c13(int i, View view) {
            MyDeviceFragment.this.mOnItemReconnectListener.onItemClick(i);
            ViewClickInstrumentation.clickOnView(view);
        }

        private void dealSplitLine(cjv cjvVar, HealthDivider healthDivider) {
            if (cjvVar.g()) {
                healthDivider.setVisibility(8);
            } else {
                healthDivider.setVisibility(0);
            }
        }

        private void dealDeviceConnectState(HealthTextView healthTextView, ImageView imageView, RelativeLayout relativeLayout, HealthButton healthButton, LinearLayout linearLayout) {
            healthTextView.setText(R.string._2130841443_res_0x7f020f63);
            imageView.setBackgroundResource(R.mipmap._2131820825_res_0x7f110119);
            relativeLayout.setVisibility(8);
            healthButton.setVisibility(8);
            linearLayout.setVisibility(0);
            startLoading();
        }

        private void dealWearInitState(cpm cpmVar, HealthTextView healthTextView, ImageView imageView, ImageView imageView2, LinearLayout linearLayout) {
            LogUtil.a(MyDeviceFragment.TAG, "getView wear device state:" + cpmVar.e());
            LogUtil.a(MyDeviceFragment.TAG, "getView wear device name:" + cpmVar.d());
            LogUtil.a(MyDeviceFragment.TAG, "getView wear device is connecting :" + this.isConnecting);
            healthTextView.setText(cpmVar.d());
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
            loadDeviceType(cpmVar, imageView2);
            linearLayout.setVisibility(0);
        }

        private void dealDeviceOtherState(cpm cpmVar, ImageView imageView, RelativeLayout relativeLayout, LinearLayout linearLayout, HealthButton healthButton) {
            imageView.setBackgroundResource(R.mipmap._2131820825_res_0x7f110119);
            relativeLayout.setVisibility(8);
            linearLayout.setVisibility(8);
            healthButton.setVisibility(0);
            healthButton.setTextColor(MyDeviceFragment.this.getResources().getColor(R.color._2131296937_res_0x7f0902a9));
            if (!this.isConnecting) {
                initReconnect(healthButton, linearLayout, cpmVar);
            } else {
                healthButton.setTextColor(MyDeviceFragment.this.getResources().getColor(R.color._2131296913_res_0x7f090291));
                healthButton.setClickable(false);
            }
        }

        private void dealLanguageAndSplitLine(ImageView imageView, int i, HealthDivider healthDivider) {
            if (!LanguageUtil.bc(MyDeviceFragment.this.mContext)) {
                imageView.setImageDrawable(MyDeviceFragment.this.mContext.getResources().getDrawable(R.mipmap._2131820614_res_0x7f110046));
            } else {
                imageView.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            if (i == getCount() - 1) {
                healthDivider.setVisibility(8);
            } else {
                healthDivider.setVisibility(0);
            }
        }

        private void doWithoutUpgradeCondition(cpm cpmVar, HealthTextView healthTextView, RelativeLayout relativeLayout, ImageView imageView, ImageView imageView2) {
            imageView2.setBackgroundResource(R.mipmap._2131820826_res_0x7f11011a);
            int unused = MyDeviceFragment.sReconnectCount = 0;
            if (cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", MyDeviceFragment.TAG) != -1) {
                relativeLayout.setVisibility(0);
                healthTextView.setText(jed.b(cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", MyDeviceFragment.TAG), 2, 0));
                imageView.setBackground(nsn.cKX_(cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", MyDeviceFragment.TAG)));
                return;
            }
            relativeLayout.setVisibility(8);
        }

        private void upgradeCondition(ImageView imageView, RelativeLayout relativeLayout, LinearLayout linearLayout, ImageView imageView2, HealthTextView healthTextView) {
            imageView.setVisibility(8);
            relativeLayout.setVisibility(8);
            linearLayout.setVisibility(0);
            this.mLoadingImg.setVisibility(0);
            imageView2.setVisibility(8);
            healthTextView.setText(R.string._2130841463_res_0x7f020f77);
        }

        private void dealThirdDevice(cjv cjvVar, ImageView imageView, ImageView imageView2, ImageView imageView3, HealthTextView healthTextView) {
            String str;
            if (cjvVar.a() == 0) {
                imageView.setVisibility(8);
                dcz dczVar = cjvVar.i() instanceof dcz ? (dcz) cjvVar.i() : null;
                ContentValues FT_ = cjvVar.FT_();
                if (FT_ != null) {
                    str = FT_.getAsString("sn");
                    if (TextUtils.isEmpty(str)) {
                        str = FT_.getAsString("uniqueId");
                    }
                } else {
                    str = "";
                }
                imageView2.setVisibility(8);
                imageView3.setVisibility(0);
                if (dczVar == null) {
                    LogUtil.h(MyDeviceFragment.TAG, "dealThirdDevice productInfo is null");
                    healthTextView.setText((CharSequence) null);
                    imageView3.setImageResource(0);
                    imageView3.setImageBitmap(null);
                    return;
                }
                if (dczVar.e().size() <= 0) {
                    healthTextView.setText(dczVar.n().b() + "\n" + dczVar.n().c());
                    imageView3.setImageResource(dcx.a(dczVar.n().d()));
                    return;
                }
                if (!TextUtils.isEmpty(str)) {
                    String replaceAll = str.replaceAll(":", "");
                    if (replaceAll.length() > 3) {
                        replaceAll = replaceAll.substring(replaceAll.length() - 3);
                    }
                    healthTextView.setText(dcx.d(dczVar.t(), dczVar.n().b()) + com.huawei.openalliance.ad.constant.Constants.LINK + replaceAll);
                } else {
                    healthTextView.setText(dcx.d(dczVar.t(), dczVar.n().b()));
                }
                imageView3.setImageBitmap(dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d())));
            }
        }

        private void loadDeviceType(cpm cpmVar, ImageView imageView) {
            int i = cpmVar.i();
            if (MyDeviceFragment.this.mDeviceInfoUtilsApi.isPluginDownloadByType(i)) {
                LogUtil.a(MyDeviceFragment.TAG, "is plugin download");
                String uUIDForPlugin = MyDeviceFragment.this.mDeviceInfoUtilsApi.getUUIDForPlugin(i);
                LogUtil.a(MyDeviceFragment.TAG, "is plugin download uuid:" + uUIDForPlugin);
                boolean g = EzPluginManager.a().g(uUIDForPlugin);
                LogUtil.a(MyDeviceFragment.TAG, "is plugin download pluginAvailable:" + g);
                loadPlugin(g, uUIDForPlugin, imageView, i);
                return;
            }
            if (!TextUtils.isEmpty(cpmVar.d()) && cpmVar.d().contains("HUAWEI CM-R1P")) {
                imageView.setBackgroundResource(R.mipmap._2131821232_res_0x7f1102b0);
                return;
            }
            try {
                imageView.setBackgroundResource(Integer.parseInt(cpmVar.f()));
            } catch (NumberFormatException unused) {
                LogUtil.a(MyDeviceFragment.TAG, "loadDeviceType NumberFormatException");
            }
        }

        private void loadPlugin(boolean z, String str, ImageView imageView, int i) {
            if (!z) {
                if (MyDeviceFragment.this.mDeviceInfoUtilsApi.isDeviceBand(i)) {
                    imageView.setBackgroundResource(R.mipmap._2131820663_res_0x7f110077);
                    return;
                } else {
                    imageView.setBackgroundResource(R.mipmap._2131820673_res_0x7f110081);
                    return;
                }
            }
            msc c = EzPluginManager.a().c(str);
            if (c == null) {
                if (MyDeviceFragment.this.mDeviceInfoUtilsApi.isDeviceBand(i)) {
                    imageView.setBackgroundResource(R.mipmap._2131820663_res_0x7f110077);
                    return;
                } else {
                    imageView.setBackgroundResource(R.mipmap._2131820673_res_0x7f110081);
                    return;
                }
            }
            LogUtil.a(MyDeviceFragment.TAG, "is plugin download img:" + c.g().a());
            imageView.setBackground(new BitmapDrawable(EzPluginManager.a().cop_(c, c.g().a())));
        }

        private void initReconnect(final HealthButton healthButton, final LinearLayout linearLayout, final cpm cpmVar) {
            LogUtil.a(MyDeviceFragment.TAG, "enter initReconnect");
            healthButton.setClickable(true);
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$ProductListAdapter$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MyDeviceFragment.ProductListAdapter.this.m303x1be638cc(healthButton, linearLayout, cpmVar, view);
                }
            });
        }

        /* renamed from: lambda$initReconnect$3$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment$ProductListAdapter, reason: not valid java name */
        /* synthetic */ void m303x1be638cc(final HealthButton healthButton, final LinearLayout linearLayout, final cpm cpmVar, View view) {
            try {
                if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                    NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(MyDeviceFragment.this.mContext).e(MyDeviceFragment.this.mContext.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$ProductListAdapter$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            MyDeviceFragment.ProductListAdapter.this.m301x49d6aeca(healthButton, linearLayout, cpmVar, view2);
                        }
                    }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$ProductListAdapter$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            MyDeviceFragment.ProductListAdapter.this.m302x32de73cb(view2);
                        }
                    });
                    MyDeviceFragment.this.mCloseBtDialog = czz_.e();
                    MyDeviceFragment.this.mCloseBtDialog.setCancelable(false);
                    MyDeviceFragment.this.mCloseBtDialog.show();
                } else {
                    requestGpsPermission(healthButton, linearLayout, cpmVar, false);
                }
            } catch (SecurityException e) {
                LogUtil.b(MyDeviceFragment.TAG, "initReconnect SecurityException:", ExceptionUtils.d(e));
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$initReconnect$1$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment$ProductListAdapter, reason: not valid java name */
        /* synthetic */ void m301x49d6aeca(HealthButton healthButton, LinearLayout linearLayout, cpm cpmVar, View view) {
            LogUtil.a(MyDeviceFragment.TAG, "user choose open BT");
            try {
                if (BluetoothAdapter.getDefaultAdapter().enable()) {
                    requestGpsPermission(healthButton, linearLayout, cpmVar, true);
                }
            } catch (RuntimeException e) {
                LogUtil.a(MyDeviceFragment.TAG, "user choose open BT error :" + e.getMessage());
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* renamed from: lambda$initReconnect$2$com-huawei-health-ecologydevice-ui-measure-fragment-MyDeviceFragment$ProductListAdapter, reason: not valid java name */
        /* synthetic */ void m302x32de73cb(View view) {
            if (MyDeviceFragment.this.mCloseBtDialog != null) {
                MyDeviceFragment.this.mCloseBtDialog.dismiss();
                MyDeviceFragment.this.mCloseBtDialog = null;
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        private void requestGpsPermission(final HealthButton healthButton, final LinearLayout linearLayout, final cpm cpmVar, final boolean z) {
            if (MyDeviceFragment.this.getActivity().isFinishing()) {
                return;
            }
            if (MyDeviceFragment.this.mDeviceInfoUtilsApi.getBtType(cpmVar.i()) == 2) {
                PermissionUtil.b(MyDeviceFragment.this.mContext, PermissionUtil.PermissionType.LOCATION, new CustomPermissionAction(MyDeviceFragment.this.mContext) { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment.ProductListAdapter.1
                    @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                    public void onGranted() {
                        ProductListAdapter.this.postConnect(healthButton, linearLayout, cpmVar, z);
                    }
                });
            } else {
                postConnect(healthButton, linearLayout, cpmVar, z);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void postConnect(HealthButton healthButton, LinearLayout linearLayout, cpm cpmVar, boolean z) {
            if (z) {
                int unused = MyDeviceFragment.sReconnectCount = 0;
            }
            onclickReconnect(cpmVar, healthButton, linearLayout);
            MyDeviceFragment myDeviceFragment = MyDeviceFragment.this;
            myDeviceFragment.initList(myDeviceFragment.mKind);
            MyDeviceFragment.this.mProductAdapter.notifyDataSetChanged();
        }

        private void onclickReconnect(cpm cpmVar, HealthButton healthButton, LinearLayout linearLayout) {
            if (!MyDeviceFragment.this.mVersionMgrApi.isOtherDeviceOtaUpdating(cpmVar.a())) {
                if (MyDeviceFragment.this.mDeviceInfoUtilsApi.idCurrentDeviceActive(cpmVar.a()) || MyDeviceFragment.this.mDeviceInfoUtilsApi.getCurrentDeviceList().isEmpty()) {
                    MyDeviceFragment.this.startReconnect(cpmVar, healthButton, linearLayout);
                    return;
                } else {
                    handleWorkMode(cpmVar);
                    MyDeviceFragment.this.startReconnect(cpmVar, healthButton, linearLayout);
                    return;
                }
            }
            LogUtil.a(MyDeviceFragment.TAG, "user choose connect other wear device is OTAing");
            MyDeviceFragment.this.showTipDialog();
        }

        private void handleWorkMode(cpm cpmVar) {
            List<DeviceInfo> wearDeviceList = MyDeviceFragment.this.mDeviceInfoUtilsApi.getWearDeviceList();
            if (wearDeviceList == null) {
                LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode, mDeviceInfoList = null");
                return;
            }
            if (cpmVar.o() == 1) {
                LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.RUN_WORK_MODE");
                for (DeviceInfo deviceInfo : wearDeviceList) {
                    if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                        LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode set device enable");
                        deviceInfo.setDeviceActiveState(1);
                        deviceInfo.setDeviceConnectState(1);
                    }
                    if (!cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && deviceInfo.getAutoDetectSwitchStatus() == 1 && deviceInfo.getDeviceActiveState() == 1) {
                        LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode target device disable");
                        deviceInfo.setDeviceActiveState(0);
                        deviceInfo.setDeviceConnectState(3);
                    }
                }
                return;
            }
            LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
            for (DeviceInfo deviceInfo2 : wearDeviceList) {
                if (cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify())) {
                    LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode set device enable");
                    deviceInfo2.setDeviceActiveState(1);
                    deviceInfo2.setDeviceConnectState(1);
                }
                if (!cpmVar.a().equalsIgnoreCase(deviceInfo2.getDeviceIdentify()) && deviceInfo2.getAutoDetectSwitchStatus() != 1 && deviceInfo2.getDeviceActiveState() == 1) {
                    LogUtil.a(MyDeviceFragment.TAG, "handleWorkMode target device disable");
                    deviceInfo2.setDeviceActiveState(0);
                    deviceInfo2.setDeviceConnectState(3);
                }
            }
            MyDeviceFragment.this.mDeviceInfoUtilsApi.resetUpdate();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startReconnect(cpm cpmVar, HealthButton healthButton, LinearLayout linearLayout) {
        LogUtil.a(TAG, "startReconnect sReconnectCount ：" + sReconnectCount);
        if (sReconnectCount < 2) {
            this.mReconnectDevice = cpmVar.a();
            this.mProductAdapter.setConnecting(true);
            this.mOnItemReconnectListener.onReconnect(cpmVar);
            this.mProductAdapter.setConnecting(true);
            healthButton.setVisibility(8);
            linearLayout.setVisibility(0);
            this.mProductAdapter.startLoading();
            sReconnectCount++;
            return;
        }
        createAlertDialog(this.mDeviceInfoUtilsApi.checkProductType(cpmVar.i()));
        sReconnectCount = 0;
    }

    private void createAlertDialog(View view) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mContext);
        builder.a(this.mContext.getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).czg_(view).cze_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                MyDeviceFragment.lambda$createAlertDialog$6(view2);
            }
        });
        CustomViewDialog e = builder.e();
        this.mCustomViewDialog = e;
        e.setCancelable(true);
        this.mCustomViewDialog.show();
    }

    static /* synthetic */ void lambda$createAlertDialog$6(View view) {
        LogUtil.a(TAG, "showAlertDialog onclick PositiveButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void openWearHome(String str) {
        LogUtil.a(TAG, "Enter openWearHome ");
        if (this.mVersionMgrApi.isOtherDeviceOtaUpdating(str)) {
            LogUtil.a(TAG, "Enter openWearHome other device is OTAing");
            showTipDialog();
            return;
        }
        if (this.mVersionMgrApi.isDeviceOtaUpdating(str)) {
            LogUtil.a(TAG, "Enter openWearHome wear device is OTAing");
            Intent intent = new Intent();
            intent.setClassName(this.mContext, "com.huawei.ui.device.activity.update.UpdateVersionActivity");
            intent.putExtra("device_id", str);
            gnm.aPB_(this.mContext, intent);
            return;
        }
        Intent intent2 = new Intent();
        intent2.setClassName(this.mContext, "com.huawei.ui.homewear21.home.WearHomeActivity");
        intent2.putExtra("device_id", str);
        gnm.aPB_(this.mContext, intent2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBandUnavailableDialog(Context context) {
        LogUtil.c(TAG, "showBandUnavailableDialog");
        boolean h = CommonUtil.h(context, "com.huawei.health.device.ui.DeviceMainActivity");
        LogUtil.c(TAG, "isForeground : " + h);
        if (h) {
            CustomTextAlertDialog customTextAlertDialog = this.mDialog;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                LogUtil.c(TAG, "showBandUnavailableDialog Already show!");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_service_area_notice_title).e(context.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.mDialog = a2;
            a2.setCancelable(false);
            if (this.mDialog.isShowing()) {
                return;
            }
            this.mDialog.show();
        }
    }

    private void unRegisterBatteryBroadcast() {
        try {
            this.mContext.unregisterReceiver(DEVICE_BATTERY_RECEIVER);
        } catch (IllegalArgumentException e) {
            LogUtil.a(TAG, "unRegisterBatteryBroadcast Exception: " + e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.a(TAG, "unRegisterBatteryBroadcast Exception: " + e2.getMessage());
        }
    }

    private void registerBatteryBroadcast() {
        LogUtil.a(TAG, "enter registerBatteryBroadcast");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), DEVICE_BATTERY_RECEIVER, intentFilter, LocalBroadcast.c, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTipDialog() {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.mContext).e(this.mContext.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.MyDeviceFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyDeviceFragment.lambda$showTipDialog$8(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    static /* synthetic */ void lambda$showTipDialog$8(View view) {
        LogUtil.a(TAG, "showTipDialog click known button");
        ViewClickInstrumentation.clickOnView(view);
    }
}
