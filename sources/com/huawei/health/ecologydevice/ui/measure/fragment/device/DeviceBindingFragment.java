package com.huawei.health.ecologydevice.ui.measure.fragment.device;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.R;
import com.huawei.health.device.api.DeviceInfoUtilsApi;
import com.huawei.health.device.api.DeviceMainActivityApi;
import com.huawei.health.device.api.HealthDeviceEntryApi;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.device.constants.WeightConstants;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.DeviceStateConstants;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ProductIntroductionFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.openalliance.ad.db.bean.TemplateStyleRecord;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.cjv;
import defpackage.cpm;
import defpackage.cpp;
import defpackage.cun;
import defpackage.dbu;
import defpackage.dcq;
import defpackage.dcr;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.gnm;
import defpackage.ixx;
import defpackage.jed;
import defpackage.koq;
import defpackage.msc;
import defpackage.nsn;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class DeviceBindingFragment extends ListFragment {
    private static final int MSG_BATTERY = 4;
    private static final int MSG_CONNECTED = 0;
    private static final int MSG_CONNECTING = 3;
    private static final int MSG_DISCONNECTED = 2;
    private static final int MSG_TIME_OUT = 1;
    private static final int MSG_UNAVAILABLE = 5;
    private static final String TAG = "DeviceBindingFragment";
    private static WeakReference<DeviceBindingFragment> sFragment;
    private static WeakReference<Activity> sInstance;
    private Context mContext;
    private DeviceListAdapter mDeviceListAdapter;
    private OnItemReconnectListener mOnItemReconnectListener;
    private String mReconnectDevice;
    private static final BroadcastReceiver DEVICE_BATTERY_RECEIVER = new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            DeviceInfo currentDevice;
            if (!"com.huawei.bone.action.BATTERY_LEVEL".equals(intent.getAction()) || intent.getExtras() == null || (currentDevice = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getCurrentDevice()) == null || currentDevice.getDeviceConnectState() != 2) {
                return;
            }
            DeviceBindingFragment.sHandler.sendEmptyMessage(4);
        }
    };
    private static final BroadcastReceiver NON_LOCAL_BROAD_CAST_RECEIVER = new BroadcastReceiver() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): intent = " + intent.getAction());
            try {
                DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                if (deviceInfo != null && DeviceBindingFragment.sHandler != null) {
                    String deviceIdentify = deviceInfo.getDeviceIdentify();
                    Message obtainMessage = DeviceBindingFragment.sHandler.obtainMessage();
                    obtainMessage.obj = deviceIdentify;
                    if (deviceInfo.getDeviceConnectState() == 2) {
                        LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): DEVICE_CONNECTED");
                        obtainMessage.what = 0;
                        DeviceBindingFragment.sHandler.sendMessage(obtainMessage);
                    } else {
                        if (deviceInfo.getDeviceConnectState() != 3 && deviceInfo.getDeviceConnectState() != 4) {
                            if (deviceInfo.getDeviceConnectState() == 1) {
                                LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): DEVICE_CONNECTING");
                                obtainMessage.what = 3;
                                DeviceBindingFragment.sHandler.sendMessage(obtainMessage);
                            } else if (deviceInfo.getDeviceConnectState() == 5) {
                                obtainMessage.what = 5;
                                DeviceBindingFragment.sHandler.sendMessage(obtainMessage);
                            } else {
                                LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): deviceInfo is other state");
                            }
                        }
                        LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): DEVICE_DISCONNECTED");
                        obtainMessage.what = 2;
                        DeviceBindingFragment.sHandler.sendMessage(obtainMessage);
                        ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).setBattery(-1);
                    }
                } else {
                    LogUtil.a(DeviceBindingFragment.TAG, "mNonLocalBroadcastReceiver(): deviceInfo = null");
                }
            } catch (ClassCastException e) {
                LogUtil.a(DeviceBindingFragment.TAG, "DeviceInfo deviceInfo error" + e.getMessage());
            }
        }
    };
    private static Handler sHandler = new DeviceBindingHandler();
    private boolean mIsPad = false;
    private AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.3
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (i < DeviceBindingFragment.this.mDeviceList.size()) {
                if (((cjv) DeviceBindingFragment.this.mDeviceList.get(i)).i() instanceof dcz) {
                    DeviceBindingFragment.this.onProductItemClick(i);
                } else if (((cjv) DeviceBindingFragment.this.mDeviceList.get(i)).i() instanceof dcr) {
                    DeviceBindingFragment.this.onGroupItemClick(i);
                } else if (((cjv) DeviceBindingFragment.this.mDeviceList.get(i)).i() instanceof dbu) {
                    DeviceBindingFragment.this.onWearItemClick(i);
                } else if (((cjv) DeviceBindingFragment.this.mDeviceList.get(i)).i() instanceof cpm) {
                    DeviceBindingFragment.this.onWearDeviceClick(i);
                } else {
                    LogUtil.a(DeviceBindingFragment.TAG, "onItemClick postion is other item view");
                }
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                return;
            }
            LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment onItemClick position >= mDeviceList.size()");
            ViewClickInstrumentation.clickOnListView(adapterView, view, i);
        }
    };
    private ArrayList<dcz> mProductInfoList = new ArrayList<>(10);
    private ArrayList<ContentValues> mContentValuesList = new ArrayList<>(10);
    private ArrayList<dcr> mGroupList = null;
    private ArrayList<cjv> mDeviceList = new ArrayList<>(10);
    private ArrayList<cpm> mWearInfoList = new ArrayList<>(10);
    private Map<String, String> mMap = new HashMap();
    private int mReconnectCount = 0;
    private NoTitleCustomAlertDialog mCloseBTDialog = null;
    private boolean mIsDiscover = false;
    private boolean mIsFromFitnessAdvice = false;
    private int mHeartRateDevice = 0;
    private CustomTextAlertDialog mCustomDialog = null;

    public interface OnItemReconnectListener {
        void onReconnect(cpm cpmVar);
    }

    static /* synthetic */ int access$1108(DeviceBindingFragment deviceBindingFragment) {
        int i = deviceBindingFragment.mReconnectCount;
        deviceBindingFragment.mReconnectCount = i + 1;
        return i;
    }

    public static WeakReference<Activity> getInstance() {
        return sInstance;
    }

    public static void setFragment(WeakReference<DeviceBindingFragment> weakReference) {
        sFragment = weakReference;
    }

    private static void setActivity(WeakReference<Activity> weakReference) {
        sInstance = weakReference;
    }

    private static void cleanInstance() {
        sInstance = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void connectedDeviceOrTimeOut(Message message, DeviceBindingFragment deviceBindingFragment) {
        LogUtil.a(TAG, "reconnect device timeout ？" + message.what);
        String str = (String) message.obj;
        sHandler.removeMessages(1);
        deviceBindingFragment.mDeviceListAdapter.stopLoading();
        deviceBindingFragment.mDeviceList.clear();
        if (message.what == 0) {
            if (!TextUtils.isEmpty(str) && str.equals(deviceBindingFragment.mReconnectDevice)) {
                deviceBindingFragment.mDeviceListAdapter.setConnecting(false);
            } else {
                LogUtil.a(TAG, "mHandler msg_connected mDeviceList.size", Integer.valueOf(deviceBindingFragment.mDeviceList.size()));
                LogUtil.a(TAG, "mHandler msg_connected");
            }
        } else {
            LogUtil.a(TAG, "mHandler msg_time_out deviceList.size", Integer.valueOf(deviceBindingFragment.mDeviceList.size()));
            deviceBindingFragment.mDeviceListAdapter.setConnecting(false);
        }
        deviceBindingFragment.setAdapterData();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this.mainActivity;
        setActivity(new WeakReference(getActivity()));
        setFragment(new WeakReference(this));
        ResourceManager.e().h();
        this.mIsPad = nsn.ae(this.mContext);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ListFragment, com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        init();
        initHashMap();
        return onCreateView;
    }

    private void addBindTitle(String str) {
        if (this.mProductInfoList == null || "me".equals(str)) {
            return;
        }
        if ((this.mProductInfoList.size() > 0 || ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getWearInfo().size() > 0) && CommonUtil.ce()) {
            cjv cjvVar = new cjv();
            cjvVar.c((Object) getResources().getString(R.string._2130841578_res_0x7f020fea));
            cjvVar.a(-1);
            this.mDeviceList.add(cjvVar);
        }
    }

    private void addWearTitle() {
        DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
        if (deviceInfoUtilsApi.getWearInfo().size() <= 0 || !CommonUtil.ce()) {
            return;
        }
        LogUtil.c(TAG, " has wear device");
        this.mWearInfoList = deviceInfoUtilsApi.getWearInfo();
        LogUtil.c(TAG, " has wear device size :" + this.mWearInfoList.size());
        Iterator<cpm> it = this.mWearInfoList.iterator();
        while (it.hasNext()) {
            cpm next = it.next();
            cjv cjvVar = new cjv();
            cjvVar.a(1);
            cjvVar.c(next);
            if (next.e() == 2) {
                this.mDeviceList.add(1, cjvVar);
            } else {
                this.mDeviceList.add(cjvVar);
            }
            DeviceListAdapter deviceListAdapter = this.mDeviceListAdapter;
            boolean z = false;
            boolean z2 = deviceListAdapter != null && deviceListAdapter.getConnecting();
            String str = this.mReconnectDevice;
            if (str != null && str.equals(next.a())) {
                z = true;
            }
            if (z2 && z) {
                next.a(1);
            }
            LogUtil.c(TAG, " has wear device name :" + next.d());
            LogUtil.c(TAG, " has wear device state :" + next.e());
        }
    }

    private void addProductData() {
        if (this.mProductInfoList != null) {
            for (int i = 0; i < this.mProductInfoList.size(); i++) {
                cjv cjvVar = new cjv();
                cjvVar.c(this.mProductInfoList.get(i));
                cjvVar.FU_(this.mContentValuesList.get(i));
                cjvVar.a(0);
                this.mDeviceList.add(cjvVar);
            }
        }
    }

    private void addMoreTitle() {
        ArrayList<dcz> arrayList = this.mProductInfoList;
        if (arrayList != null) {
            if (arrayList.size() > 0 || this.mWearInfoList.size() > 0) {
                cjv cjvVar = new cjv();
                cjvVar.c((Object) getResources().getString(R.string.IDS_device_common_ui_navigation_bar_more));
                cjvVar.a(-1);
                this.mDeviceList.add(cjvVar);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdapterData() {
        Bundle arguments = getArguments();
        String string = (arguments == null || arguments.getString("root_in_me") == null) ? "" : arguments.getString("root_in_me");
        LogUtil.c(TAG, "source = " + string);
        if (!this.mIsFromFitnessAdvice) {
            getBindDevice();
            addTitle(string);
        }
        if (CommonUtil.ce()) {
            addDeviceList();
        }
        Iterator<dcr> it = this.mGroupList.iterator();
        while (it.hasNext()) {
            dcr next = it.next();
            if (next.c() == HealthDevice.HealthDeviceKind.HDK_HEART_RATE) {
                Utils.o();
            }
            cjv cjvVar = new cjv();
            cjvVar.c(next);
            cjvVar.a(0);
            this.mDeviceList.add(cjvVar);
        }
        this.mDeviceListAdapter.notifyDataSetChanged();
        LogUtil.a(TAG, "setAdapterData deviceList.size", Integer.valueOf(this.mDeviceList.size()));
    }

    private void addDeviceList() {
        dbu dbuVar = new dbu();
        dbuVar.d(getResources().getString(R.string.IDS_add_device_smart_watch));
        dbuVar.d(R.drawable.hw_health_device_binding_watch_list);
        dbu dbuVar2 = new dbu();
        dbuVar2.d(getResources().getString(R.string.IDS_add_device_smart_band));
        dbuVar2.d(R.drawable.hw_health_device_binding_band_list);
        dbu dbuVar3 = new dbu();
        dbuVar3.d(getResources().getString(R.string.IDS_add_device_smart_headphones));
        dbuVar3.d(R.drawable.hw_health_device_binding_headset_list);
        cjv cjvVar = new cjv();
        cjvVar.c(dbuVar2);
        cjvVar.a(1);
        cjv cjvVar2 = new cjv();
        cjvVar2.c(dbuVar);
        cjvVar2.a(1);
        cjv cjvVar3 = new cjv();
        cjvVar3.c(dbuVar3);
        cjvVar3.a(1);
        this.mDeviceList.add(cjvVar2);
        this.mDeviceList.add(cjvVar);
        if (Utils.o()) {
            return;
        }
        this.mDeviceList.add(cjvVar3);
    }

    private void addTitle(String str) {
        if (this.mIsDiscover) {
            LogUtil.a(TAG, "setAdapterData isFromDiscover");
            addBindTitle(str);
            addWearTitle();
            addProductData();
            addMoreTitle();
        }
    }

    protected void init() {
        Bundle bundleFromDeviceMainActivity = getBundleFromDeviceMainActivity();
        if (bundleFromDeviceMainActivity != null) {
            this.mIsFromFitnessAdvice = bundleFromDeviceMainActivity.getBoolean("isFromFitnessAdvice", false);
            this.mHeartRateDevice = bundleFromDeviceMainActivity.getInt("isHeartRateDevice", 0);
        }
        this.mDeviceList.clear();
        LogUtil.a(TAG, "init deviceList.size", Integer.valueOf(this.mDeviceList.size()));
        if (this.mIsFromFitnessAdvice) {
            this.mGroupList = new ArrayList<>(10);
            dcr c = ResourceManager.e().a().c(HealthDevice.HealthDeviceKind.HDK_HEART_RATE);
            if (c != null) {
                this.mGroupList.add(c);
            }
        } else {
            this.mGroupList = ResourceManager.e().a().e();
        }
        this.myDevicesListview = (ListView) this.child.findViewById(R.id.device_list_view);
        this.mDeviceListAdapter = new DeviceListAdapter(this.mDeviceList, this.mContext);
        this.myDevicesListview.setAdapter((ListAdapter) this.mDeviceListAdapter);
        clickDeviceListAdapter();
        this.myDevicesListview.setOnItemClickListener(this.mOnItemClickListener);
        if (this.mIsFromFitnessAdvice) {
            String string = getResources().getString(R.string.IDS_user_profile_health_show_all_devices);
            if (bundleFromDeviceMainActivity != null) {
                string = bundleFromDeviceMainActivity.getString("title", getResources().getString(R.string.IDS_user_profile_health_show_all_devices));
            }
            super.setTitle(string);
        } else {
            super.setTitle(getResources().getString(R.string.IDS_user_profile_health_show_all_devices));
        }
        showButton(false, null);
        showMoreButton(false, null);
        setAdapterData();
        if (CommonUtil.ce()) {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), NON_LOCAL_BROAD_CAST_RECEIVER, intentFilter, LocalBroadcast.c, null);
            registerBatteryBroadcast();
        }
        if (getBundleFromDeviceMainActivity() != null) {
            this.mIsDiscover = getBundleFromDeviceMainActivity().getBoolean("isFromDiscover", false);
            LogUtil.a(TAG, "isDiscover :" + this.mIsDiscover);
        }
    }

    private void initHashMap() {
        this.mMap.put("HDK_WEIGHT", "ic_list_balance");
        this.mMap.put("HDK_BLOOD_SUGAR", "ic_list_blood_glucose_meter");
        this.mMap.put("HDK_BLOOD_PRESSURE", "ic_list_blood_pressure");
        this.mMap.put("HDK_ECG", "ic_adduser_icon_account");
        this.mMap.put("HDK_HEART_RATE", "ic_list_heart_rate");
        this.mMap.put("HDK_BODY_TEMPERATURE", "ic_list_thermometer");
        this.mMap.put("HDK_BLOOD_OXYGEN", "ic_list_oximetry");
        this.mMap.put("HDK_SMART_PILLOW", "ic_adduser_icon_account");
        this.mMap.put("HDK_MASSAGE_GUN", "ic_adduser_icon_account");
        this.mMap.put("HDK_ROPE_SKIPPING", "ic_list_rope_skipping");
        this.mMap.put("HDK_TREADMILL", "ic_list_treadmill");
        this.mMap.put("HDK_EXERCISE_BIKE", "ic_list_exercise_bike");
        this.mMap.put("HDK_ROWING_MACHINE", "ic_list_rowing_machine");
        this.mMap.put("HDK_ELLIPTICAL_MACHINE", "ic_list_elliptical_machine");
        this.mMap.put("HDK_WALKING_MACHINE", "ic_list_walking_machine_light");
    }

    private void clickDeviceListAdapter() {
        this.mDeviceListAdapter.setOnItemReconnectListener(new OnItemReconnectListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.4
            @Override // com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.OnItemReconnectListener
            public void onReconnect(cpm cpmVar) {
                if (cpmVar == null) {
                    LogUtil.a(DeviceBindingFragment.TAG, "onReconnect deviceInfoForWear is null");
                    return;
                }
                List<DeviceInfo> wearDeviceList = ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).getWearDeviceList();
                if (wearDeviceList == null) {
                    LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode, mDeviceInfoList = null");
                    return;
                }
                if (cpmVar.o() == 1) {
                    LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.RUN_WORK_MODE");
                    DeviceBindingFragment.this.setDeviceInfoState(wearDeviceList, cpmVar, 1);
                } else {
                    LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
                    DeviceBindingFragment.this.setDeviceInfoState(wearDeviceList, cpmVar, 0);
                    ((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).resetUpdate();
                }
                DeviceBindingFragment.sHandler.sendEmptyMessageDelayed(1, 20000L);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDeviceInfoState(List<DeviceInfo> list, cpm cpmVar, int i) {
        for (DeviceInfo deviceInfo : list) {
            boolean z = true;
            if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                LogUtil.a(TAG, "handleWorkMode set device enable");
                deviceInfo.setDeviceActiveState(1);
                deviceInfo.setDeviceConnectState(1);
            }
            if (i != 1 ? deviceInfo.getAutoDetectSwitchStatus() == 1 || deviceInfo.getDeviceActiveState() != 1 : deviceInfo.getAutoDetectSwitchStatus() != 1 || deviceInfo.getDeviceActiveState() != 1) {
                z = false;
            }
            if (!cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && z) {
                LogUtil.a(TAG, "handleWorkMode target device disable");
                deviceInfo.setDeviceActiveState(0);
                deviceInfo.setDeviceConnectState(3);
            }
        }
    }

    private void getBindDevice() {
        this.mProductInfoList.clear();
        this.mContentValuesList.clear();
        Iterator<ContentValues> it = ((HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class)).getBondedDeviceByDeviceKind(HealthDevice.HealthDeviceKind.HDK_UNKNOWN).iterator();
        while (it.hasNext()) {
            ContentValues next = it.next();
            dcz d = ResourceManager.e().d(next.getAsString("productId"));
            if (d != null && !d.n().d().trim().isEmpty()) {
                this.mProductInfoList.add(d);
                this.mContentValuesList.add(next);
            }
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mDeviceList.clear();
        LogUtil.a(TAG, "onResume deviceList.size", Integer.valueOf(this.mDeviceList.size()));
        setAdapterData();
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        WeakReference<DeviceBindingFragment> weakReference = sFragment;
        if (weakReference != null) {
            weakReference.clear();
        }
        cleanInstance();
        if (CommonUtil.ce()) {
            unRegisterBatteryBroadcast();
            try {
                LogUtil.a(TAG, "unregisterWearBroadcast");
                BaseApplication.getContext().unregisterReceiver(NON_LOCAL_BROAD_CAST_RECEIVER);
            } catch (IllegalArgumentException e) {
                LogUtil.a(TAG, e.getMessage());
            } catch (Exception unused) {
                LogUtil.a(TAG, "unregisterReceiver Exception");
            }
        }
        Handler handler = sHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            LogUtil.a(TAG, "removeCallbacksAndMessages");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onProductItemClick(int i) {
        if (this.mDeviceListAdapter.isConnecting && this.mReconnectDevice != null) {
            LogUtil.c(TAG, "other device is connecting.");
            return;
        }
        if (i >= this.mDeviceList.size()) {
            LogUtil.a(TAG, "DeviceBindingFragment onItemClick position >= mDeviceList.size()");
            return;
        }
        cjv cjvVar = this.mDeviceList.get(i);
        if (cjvVar == null || cjvVar.a() != 0) {
            return;
        }
        dcz dczVar = cjvVar.i() instanceof dcz ? (dcz) cjvVar.i() : null;
        if (dczVar != null) {
            operateProductItem(dczVar, cjvVar.FT_().getAsString("uniqueId"));
        }
    }

    private void operateProductItem(final dcz dczVar, final String str) {
        if (dczVar.e().size() <= 0) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.mContext);
            builder.e(R.string.IDS_device_selection_cancel_unbind_device);
            builder.czC_(R.string.IDS_device_ui_dialog_yes, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    HealthDeviceEntryApi healthDeviceEntryApi = (HealthDeviceEntryApi) Services.c("PluginDevice", HealthDeviceEntryApi.class);
                    if (healthDeviceEntryApi.unbindDeviceByUniqueId(str)) {
                        healthDeviceEntryApi.stopMeasure(dczVar.t(), str);
                        DeviceBindingFragment.this.init();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            builder.czz_(R.string.IDS_device_ui_dialog_no, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            e.show();
            return;
        }
        Fragment productIntroductionFragment = new ProductIntroductionFragment();
        if (((HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class)).isHonourNewDevice(dczVar.t())) {
            productIntroductionFragment = getPluginDeviceFragment(WeightConstants.FragmentType.HONOUR_DEVICE);
        } else if (((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isWiFiDevice(dczVar.t())) {
            productIntroductionFragment = getPluginDeviceFragment(WeightConstants.FragmentType.WIFI_PRODUCT_INTRODUCTION);
        } else {
            LogUtil.a(TAG, "DeviceBindingFragment nextFragment is null");
        }
        if (productIntroductionFragment == null) {
            LogUtil.h(TAG, "DeviceBindingFragment nextFragment is null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("productId", dczVar.t());
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str);
        contentValues.put("productId", dczVar.t());
        bundle.putParcelable("commonDeviceInfo", contentValues);
        productIntroductionFragment.setArguments(bundle);
        switchFragment(productIntroductionFragment);
    }

    private Fragment getPluginDeviceFragment(WeightConstants.FragmentType fragmentType) {
        return ((DeviceMainActivityApi) Services.c("PluginDevice", DeviceMainActivityApi.class)).getPluginDeviceFragment(fragmentType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGroupItemClick(int i) {
        if (i >= this.mDeviceList.size()) {
            LogUtil.a(TAG, "DeviceBindingFragment onItemClick position >= mDeviceList.size()");
            return;
        }
        if (this.mDeviceList.get(i) == null || this.mDeviceList.get(i).a() != 0) {
            return;
        }
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        ixx.d().d(cpp.a(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_NEW_DEVICE_2060001.value(), hashMap, 0);
        DeviceCategoryFragment deviceCategoryFragment = new DeviceCategoryFragment();
        Bundle bundle = new Bundle();
        dcr dcrVar = (dcr) this.mDeviceList.get(i).i();
        bundle.putString(DeviceCategoryFragment.DEVICE_KIND, getResources().getString(dcx.e(dcrVar.e())));
        bundle.putString(DeviceCategoryFragment.DEVICE_TYPE, dcrVar.c().name());
        deviceCategoryFragment.setArguments(bundle);
        switchFragment(deviceCategoryFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWearItemClick(int i) {
        if (i >= this.mDeviceList.size()) {
            LogUtil.a(TAG, "DeviceBindingFragment onItemClick position >= mDeviceList.size()");
            return;
        }
        if (this.mDeviceList.get(i) != null && this.mDeviceList.get(i).a() == 1 && CommonUtil.ce()) {
            LogUtil.a(TAG, "click wear device group start");
            dbu dbuVar = (dbu) this.mDeviceList.get(i).i();
            LogUtil.a(TAG, "click wear device group name:" + dbuVar.c());
            HashMap hashMap = new HashMap(16);
            hashMap.put("click", "1");
            operateWearItem(dbuVar, AnalyticsValue.HEALTH_PLUGIN_DEVICE_BIND_NEW_DEVICE_2060001.value(), hashMap);
        }
    }

    private void operateWearItem(dbu dbuVar, String str, Map<String, Object> map) {
        if (dbuVar.c().equals(getResources().getString(R.string.IDS_add_device_smart_band))) {
            ixx.d().d(cpp.a(), str, map, 0);
            Intent intent = new Intent();
            intent.setClassName(this.mContext, "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity");
            intent.putExtra(TemplateStyleRecord.STYLE, 2);
            intent.putExtra("isHeartRateDevice", this.mHeartRateDevice);
            startActivityForResult(intent, 1);
            return;
        }
        if (dbuVar.c().equals(getResources().getString(R.string.IDS_add_device_smart_watch))) {
            ixx.d().d(cpp.a(), str, map, 0);
            Intent intent2 = new Intent();
            intent2.setClassName(this.mContext, "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity");
            intent2.putExtra(TemplateStyleRecord.STYLE, 1);
            intent2.putExtra("isHeartRateDevice", this.mHeartRateDevice);
            startActivityForResult(intent2, 1);
            return;
        }
        if (dbuVar.c().equals(getResources().getString(R.string.IDS_add_device_smart_headphones))) {
            ixx.d().d(cpp.a(), str, map, 0);
            Intent intent3 = new Intent();
            intent3.setClassName(this.mContext, "com.huawei.ui.device.activity.adddevice.AddDeviceChildActivity");
            intent3.putExtra(TemplateStyleRecord.STYLE, 4);
            intent3.putExtra("isHeartRateDevice", this.mHeartRateDevice);
            startActivityForResult(intent3, 1);
            return;
        }
        LogUtil.a(TAG, "operateWearItem");
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(TAG, "onActivityResult requestCode:" + i + "resultCode" + i2);
        if (i == 1 && i2 == 2) {
            LogUtil.a(TAG, "onActivityResult wear pair success");
            getActivity().finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onWearDeviceClick(int i) {
        if (i >= this.mDeviceList.size()) {
            LogUtil.a(TAG, "DeviceBindingFragment onItemClick position >= mDeviceList.size()");
            return;
        }
        if (this.mDeviceList.get(i) != null) {
            cpm cpmVar = (cpm) this.mDeviceList.get(i).i();
            String str = this.mReconnectDevice;
            boolean z = (str == null || str.equals(cpmVar.a())) ? false : true;
            boolean z2 = this.mDeviceListAdapter.isConnecting && cpmVar.e() != 2;
            if (z && z2) {
                LogUtil.a(TAG, "other devices is connection,can not start activity.");
                return;
            }
            if (cpmVar.e() == 2) {
                LogUtil.a(TAG, "click onWearDeviceClick");
                Intent intent = new Intent();
                intent.setClassName(this.mContext, "com.huawei.ui.homewear21.home.WearHomeActivity");
                intent.putExtra("device_id", cpmVar.a());
                startActivity(intent);
                return;
            }
            DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
            if (deviceInfoUtilsApi.getWearInfo().size() == 1) {
                Intent intent2 = new Intent();
                intent2.setClassName(this.mContext, "com.huawei.ui.homewear21.home.WearHomeActivity");
                cpm cpmVar2 = deviceInfoUtilsApi.getWearInfo().get(0);
                intent2.putExtra("device_id", cpmVar2 != null ? cpmVar2.a() : "");
                startActivity(intent2);
                return;
            }
            Intent intent3 = new Intent();
            intent3.setClassName(this.mContext, "com.huawei.ui.homehealth.devicemanagercard.DeviceManagerWearNoConnect");
            intent3.putExtra(PluginPayAdapter.KEY_DEVICE_INFO_NAME, cpmVar.d());
            intent3.putExtra("device_identify", cpmVar.a());
            intent3.putExtra("device_picID", cpmVar.m());
            intent3.putExtra(DeviceCategoryFragment.DEVICE_TYPE, cpmVar.i());
            gnm.aPB_(this.mContext, intent3);
            LogUtil.c(TAG, "onclick wear not connected name:" + cpmVar.d() + "identify:" + cpmVar.a());
        }
    }

    class DeviceListAdapter extends BaseAdapter {
        private ArrayList<cjv> groupList;
        private HealthProgressBar mAdapterHealthProgressBar;
        private Context mContext;
        private boolean isConnecting = false;
        private CustomTextAlertDialog customTextAlertDialog = null;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        DeviceListAdapter(ArrayList<cjv> arrayList, Context context) {
            this.groupList = arrayList;
            this.mContext = context;
        }

        public void setOnItemReconnectListener(OnItemReconnectListener onItemReconnectListener) {
            DeviceBindingFragment.this.mOnItemReconnectListener = onItemReconnectListener;
        }

        public void setConnecting(boolean z) {
            this.isConnecting = z;
        }

        public boolean getConnecting() {
            return this.isConnecting;
        }

        public void stopLoading() {
            this.mAdapterHealthProgressBar.setVisibility(8);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.groupList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            ArrayList<cjv> arrayList = this.groupList;
            if (arrayList == null || i >= arrayList.size()) {
                return null;
            }
            return this.groupList.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder = null;
            Object[] objArr = 0;
            if (view == null) {
                ViewHolder viewHolder2 = new ViewHolder();
                View inflate = LayoutInflater.from(cpp.a()).inflate(R.layout.my_device_bind_list, (ViewGroup) null);
                initView(viewHolder2, inflate);
                judgeRtlLanguage(viewHolder2.groupArrowIcon);
                inflate.setTag(viewHolder2);
                LogUtil.a(DeviceBindingFragment.TAG, "DeviceListAdapter getView convertView == null");
                viewHolder = viewHolder2;
                view = inflate;
            } else if (view.getTag() instanceof ViewHolder) {
                viewHolder = (ViewHolder) view.getTag();
            }
            ArrayList<cjv> arrayList = this.groupList;
            if (arrayList != null && i < arrayList.size() && i >= 0 && viewHolder != null) {
                dealSplitLine(i, viewHolder.dividerLine);
                if (this.groupList.get(i).i() instanceof String) {
                    dealGroupProductVisible(viewHolder);
                    viewHolder.title.setText(String.valueOf(this.groupList.get(i).i()));
                    return view;
                }
                viewHolder.titleLayout.setVisibility(8);
                dealGroupProductItem(viewHolder, i);
            }
            return view;
        }

        private void initView(ViewHolder viewHolder, View view) {
            viewHolder.titleLayout = (LinearLayout) view.findViewById(R.id.device_list_view_title_ll);
            viewHolder.title = (HealthTextView) view.findViewById(R.id.device_list_view_title);
            viewHolder.groupArrowIcon = (ImageView) view.findViewById(R.id.arrow_right_icon);
            viewHolder.groupContentTv = (HealthTextView) view.findViewById(R.id.tv_device_content);
            viewHolder.groupLeftIcon = (ImageView) view.findViewById(R.id.iv_device_icon);
            viewHolder.groupItemLayout = (RelativeLayout) view.findViewById(R.id.device_list_view_item);
            viewHolder.productItemLayout = (RelativeLayout) view.findViewById(R.id.bind_item_layout);
            viewHolder.productContentTv = (HealthTextView) view.findViewById(R.id.product_info_text);
            viewHolder.productReLayout = (RelativeLayout) view.findViewById(R.id.iv_bind_device_icon_rl);
            viewHolder.productDeviceIcon = (ImageView) view.findViewById(R.id.iv_device_icon_bind);
            viewHolder.wearReLayout = (RelativeLayout) view.findViewById(R.id.device_bind_wear_layout);
            viewHolder.wearDeviceName = (HealthTextView) view.findViewById(R.id.device_bind_wear_name);
            viewHolder.wearDeviceImg = (ImageView) view.findViewById(R.id.device_bind_wear_left_img);
            viewHolder.wearStatetext = (HealthTextView) view.findViewById(R.id.device_manager_on_device_connect_states_text);
            viewHolder.wearStateimg = (ImageView) view.findViewById(R.id.device_manager_on_device_connect_states_iv);
            viewHolder.wearBatteryLayout = (RelativeLayout) view.findViewById(R.id.device_manager_on_device_battery_level_rl);
            viewHolder.produckWearBattery = (HealthTextView) view.findViewById(R.id.device_bind_wear_battery_level_tv);
            viewHolder.produckWearBatteryImg = (ImageView) view.findViewById(R.id.device_bind_wear_battery_level_img);
            viewHolder.wearReconnectLayout = (RelativeLayout) view.findViewById(R.id.reconnect_layout);
            viewHolder.wearConnectText = (HealthTextView) view.findViewById(R.id.reconnect_text);
            viewHolder.wearLoadingLayout = (LinearLayout) view.findViewById(R.id.reconnect_loading_layout);
            this.mAdapterHealthProgressBar = (HealthProgressBar) view.findViewById(R.id.reconnect_loading_img);
            viewHolder.dividerLine = (HealthDivider) view.findViewById(R.id.hw_show_main_layout_sport_bottom_image_interval);
        }

        private void dealGroupProductVisible(ViewHolder viewHolder) {
            viewHolder.groupItemLayout.setVisibility(8);
            viewHolder.productItemLayout.setVisibility(8);
            viewHolder.titleLayout.setVisibility(0);
            viewHolder.wearReLayout.setVisibility(8);
        }

        private void dealGroupProductItem(ViewHolder viewHolder, int i) {
            if (koq.b(this.groupList, i)) {
                LogUtil.a(DeviceBindingFragment.TAG, "dealGroupProductItem groupList isOutOfBounds");
                return;
            }
            cjv cjvVar = this.groupList.get(i);
            Object i2 = cjvVar.i();
            if ((i2 instanceof dcr) || (i2 instanceof dbu)) {
                viewHolder.groupItemLayout.setVisibility(0);
                viewHolder.productItemLayout.setVisibility(8);
                viewHolder.wearReLayout.setVisibility(8);
                updateGroup(viewHolder, cjvVar);
                return;
            }
            if (i2 instanceof dcz) {
                viewHolder.groupItemLayout.setVisibility(8);
                viewHolder.productItemLayout.setVisibility(0);
                viewHolder.wearReLayout.setVisibility(8);
                updateProduct(viewHolder, cjvVar);
                return;
            }
            if (i2 instanceof cpm) {
                LogUtil.a(DeviceBindingFragment.TAG, "wear initview()");
                viewHolder.wearReLayout.setVisibility(0);
                viewHolder.groupItemLayout.setVisibility(8);
                viewHolder.productItemLayout.setVisibility(8);
                updateWear(viewHolder, cjvVar);
                return;
            }
            LogUtil.a(DeviceBindingFragment.TAG, "Data type is error :", i2.getClass().getSimpleName());
        }

        private void judgeRtlLanguage(ImageView imageView) {
            if (LanguageUtil.bc(cpp.a())) {
                imageView.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            } else {
                imageView.setBackgroundResource(R.drawable._2131427842_res_0x7f0b0202);
            }
        }

        private void dealSplitLine(int i, HealthDivider healthDivider) {
            if (i == getCount() - 1) {
                healthDivider.setVisibility(8);
            } else {
                healthDivider.setVisibility(0);
            }
        }

        private void updateProduct(ViewHolder viewHolder, cjv cjvVar) {
            viewHolder.productReLayout.setVisibility(0);
            if (cjvVar.a() == 0) {
                dcz dczVar = (dcz) cjvVar.i();
                if (dczVar.e().size() <= 0) {
                    viewHolder.productContentTv.setText(dczVar.n().b() + System.lineSeparator() + dczVar.n().c());
                    viewHolder.productDeviceIcon.setImageResource(dcx.a(dczVar.n().d()));
                    return;
                }
                viewHolder.productContentTv.setText(dcx.d(dczVar.t(), dczVar.n().b()));
                viewHolder.productDeviceIcon.setImageBitmap(dcx.TK_(dcq.b().a(dczVar.t(), dczVar.n().d())));
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        private void updateGroup(ViewHolder viewHolder, cjv cjvVar) {
            InputStream inputStream;
            InputStream inputStream2 = null;
            InputStream inputStream3 = null;
            RelativeLayout.LayoutParams layoutParams = viewHolder.dividerLine.getLayoutParams() instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) viewHolder.dividerLine.getLayoutParams() : null;
            if (layoutParams != null) {
                layoutParams.addRule(18, R.id.device_detail_layout);
                viewHolder.dividerLine.setLayoutParams(layoutParams);
            }
            if (cjvVar.a() == 0) {
                dcr dcrVar = (dcr) cjvVar.i();
                String e = dcrVar.e();
                String imageIcon = getImageIcon(dcrVar.c().name());
                viewHolder.groupContentTv.setText(cpp.a().getString(dcx.e(e)));
                int a2 = dcx.a(imageIcon);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                try {
                    try {
                        inputStream = this.mContext.getResources().openRawResource(a2);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                    }
                } catch (Exception unused) {
                }
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, null, options);
                    ImageView imageView = viewHolder.groupLeftIcon;
                    imageView.setImageBitmap(decodeStream);
                    viewHolder.groupLeftIcon.setBackgroundResource(0);
                    inputStream2 = imageView;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            inputStream2 = imageView;
                        } catch (IOException e2) {
                            LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment DeviceListAdapter IOException:" + e2.getMessage());
                            inputStream2 = imageView;
                        }
                    }
                } catch (Exception unused2) {
                    inputStream3 = inputStream;
                    LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment updataGroup Exception");
                    inputStream2 = inputStream3;
                    if (inputStream3 != null) {
                        try {
                            inputStream3.close();
                            inputStream2 = inputStream3;
                        } catch (IOException e3) {
                            LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment DeviceListAdapter IOException:" + e3.getMessage());
                            inputStream2 = inputStream3;
                        }
                    }
                    return;
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment DeviceListAdapter IOException:" + e4.getMessage());
                        }
                    }
                    throw th;
                }
                return;
            }
            if (cjvVar.a() == 1) {
                dbu dbuVar = (dbu) cjvVar.i();
                viewHolder.groupContentTv.setText(dbuVar.c());
                viewHolder.groupLeftIcon.setBackgroundResource(dbuVar.d());
                viewHolder.groupLeftIcon.setImageResource(0);
                return;
            }
            LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment DeviceListAdapter other status");
        }

        private void updateWear(ViewHolder viewHolder, cjv cjvVar) {
            LogUtil.a(DeviceBindingFragment.TAG, "enter updateWear");
            cpm cpmVar = cjvVar.i() instanceof cpm ? (cpm) cjvVar.i() : null;
            if (cpmVar == null) {
                LogUtil.h(DeviceBindingFragment.TAG, "fuc updateWear wearInfo is null");
                return;
            }
            viewHolder.wearDeviceName.setText(cpmVar.d());
            int i = cpmVar.i();
            DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
            if (deviceInfoUtilsApi.isPluginDownloadByType(i)) {
                downloadPluginByType(viewHolder, i, deviceInfoUtilsApi);
            } else {
                try {
                    viewHolder.wearDeviceImg.setBackgroundResource(Integer.parseInt(cpmVar.f()));
                } catch (NumberFormatException unused) {
                    LogUtil.a(DeviceBindingFragment.TAG, "updateWear NumberFormatException");
                }
            }
            if (cpmVar.e() == 2) {
                dealDeviceReconnectCount(viewHolder, cpmVar);
                return;
            }
            if (cpmVar.e() == 1) {
                setDeviceConnectStateCommonView(viewHolder);
                viewHolder.wearReconnectLayout.setVisibility(8);
                viewHolder.wearLoadingLayout.setVisibility(0);
                this.mAdapterHealthProgressBar.setVisibility(0);
                return;
            }
            setDeviceConnectStateCommonView(viewHolder);
            viewHolder.wearReconnectLayout.setVisibility(0);
            viewHolder.wearLoadingLayout.setVisibility(8);
            if (!this.isConnecting) {
                initReconnect(viewHolder, cpmVar);
            } else {
                viewHolder.wearConnectText.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131296913_res_0x7f090291));
                viewHolder.wearReconnectLayout.setClickable(false);
            }
        }

        private void downloadPluginByType(ViewHolder viewHolder, int i, DeviceInfoUtilsApi deviceInfoUtilsApi) {
            LogUtil.a(DeviceBindingFragment.TAG, "is plugin download");
            String uUIDForPlugin = deviceInfoUtilsApi.getUUIDForPlugin(i);
            LogUtil.a(DeviceBindingFragment.TAG, "is plugin download uuid:" + uUIDForPlugin);
            boolean g = EzPluginManager.a().g(uUIDForPlugin);
            LogUtil.a(DeviceBindingFragment.TAG, "is plugin download isPluginAvailable:" + g);
            if (g) {
                dealDevicePluginInfo(viewHolder, i, EzPluginManager.a().c(uUIDForPlugin));
            } else if (deviceInfoUtilsApi.isDeviceBand(i)) {
                viewHolder.wearDeviceImg.setBackgroundResource(R.mipmap._2131820663_res_0x7f110077);
            } else {
                viewHolder.wearDeviceImg.setBackgroundResource(R.mipmap._2131820673_res_0x7f110081);
            }
        }

        private void setDeviceConnectStateCommonView(ViewHolder viewHolder) {
            viewHolder.wearStatetext.setText(R.string._2130841443_res_0x7f020f63);
            viewHolder.wearStateimg.setBackgroundResource(R.mipmap._2131820825_res_0x7f110119);
            viewHolder.wearBatteryLayout.setVisibility(8);
        }

        private void dealDeviceReconnectCount(ViewHolder viewHolder, cpm cpmVar) {
            viewHolder.wearStatetext.setText(R.string._2130841442_res_0x7f020f62);
            viewHolder.wearStateimg.setBackgroundResource(R.mipmap._2131820826_res_0x7f11011a);
            if (cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", DeviceBindingFragment.TAG) >= 0) {
                viewHolder.wearBatteryLayout.setVisibility(0);
                viewHolder.produckWearBattery.setText(jed.b(cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", DeviceBindingFragment.TAG), 2, 0));
                viewHolder.produckWearBatteryImg.setBackground(nsn.cKX_(cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, cpmVar.a(), "", DeviceBindingFragment.TAG)));
            } else {
                viewHolder.wearBatteryLayout.setVisibility(8);
            }
            viewHolder.wearReconnectLayout.setVisibility(8);
            viewHolder.wearLoadingLayout.setVisibility(8);
            DeviceBindingFragment.this.mReconnectCount = 0;
        }

        private void dealDevicePluginInfo(ViewHolder viewHolder, int i, msc mscVar) {
            if (mscVar != null) {
                LogUtil.a(DeviceBindingFragment.TAG, "is plugin download img:" + mscVar.g().a());
                viewHolder.wearDeviceImg.setBackground(new BitmapDrawable(EzPluginManager.a().cop_(mscVar, mscVar.g().j())));
                return;
            }
            if (((DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class)).isDeviceBand(i)) {
                viewHolder.wearDeviceImg.setBackgroundResource(R.mipmap._2131820663_res_0x7f110077);
            } else {
                viewHolder.wearDeviceImg.setBackgroundResource(R.mipmap._2131820673_res_0x7f110081);
            }
        }

        private void initReconnect(final ViewHolder viewHolder, final cpm cpmVar) {
            LogUtil.a(DeviceBindingFragment.TAG, "enter initReconnect");
            viewHolder.wearConnectText.setTextColor(BaseApplication.getContext().getResources().getColor(R.color._2131298056_res_0x7f090708));
            viewHolder.wearReconnectLayout.setClickable(true);
            viewHolder.wearReconnectLayout.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.DeviceListAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (BluetoothAdapter.getDefaultAdapter() == null || BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                        DeviceListAdapter.this.onclickReconnect(viewHolder, cpmVar);
                        DeviceBindingFragment.this.mDeviceList.clear();
                        LogUtil.a(DeviceBindingFragment.TAG, "DeviceListAdapter initReconnect onClick deviceList.size", Integer.valueOf(DeviceBindingFragment.this.mDeviceList.size()));
                        DeviceBindingFragment.this.setAdapterData();
                        DeviceBindingFragment.this.mDeviceListAdapter.notifyDataSetChanged();
                    } else {
                        NoTitleCustomAlertDialog.Builder czz_ = new NoTitleCustomAlertDialog.Builder(DeviceListAdapter.this.mContext).e(DeviceListAdapter.this.mContext.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.DeviceListAdapter.1.2
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                LogUtil.a(DeviceBindingFragment.TAG, "user choose open BT");
                                try {
                                    DeviceListAdapter.this.judgeBluetoothEnable(viewHolder, cpmVar);
                                } catch (RuntimeException e) {
                                    LogUtil.a(DeviceBindingFragment.TAG, "user choose open BT error :" + e.getMessage());
                                }
                                ViewClickInstrumentation.clickOnView(view2);
                            }
                        }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.DeviceListAdapter.1.1
                            @Override // android.view.View.OnClickListener
                            public void onClick(View view2) {
                                if (DeviceBindingFragment.this.mCloseBTDialog != null) {
                                    DeviceBindingFragment.this.mCloseBTDialog.dismiss();
                                    DeviceBindingFragment.this.mCloseBTDialog = null;
                                }
                                ViewClickInstrumentation.clickOnView(view2);
                            }
                        });
                        DeviceBindingFragment.this.mCloseBTDialog = czz_.e();
                        DeviceBindingFragment.this.mCloseBTDialog.setCancelable(false);
                        DeviceBindingFragment.this.mCloseBTDialog.show();
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void judgeBluetoothEnable(ViewHolder viewHolder, cpm cpmVar) {
            try {
                if (BluetoothAdapter.getDefaultAdapter().enable()) {
                    LogUtil.c(DeviceBindingFragment.TAG, "进入onclickReconnect");
                    DeviceBindingFragment.this.mReconnectCount = 0;
                    onclickReconnect(viewHolder, cpmVar);
                    LogUtil.c(DeviceBindingFragment.TAG, "进入initList(kind);");
                    DeviceBindingFragment.this.mDeviceList.clear();
                    LogUtil.a(DeviceBindingFragment.TAG, "DeviceListAdapter initReconnect onClick deviceList.size", Integer.valueOf(DeviceBindingFragment.this.mDeviceList.size()));
                    DeviceBindingFragment.this.setAdapterData();
                    DeviceBindingFragment.this.mDeviceListAdapter.notifyDataSetChanged();
                    LogUtil.c(DeviceBindingFragment.TAG, "进入  刷新over.");
                }
            } catch (SecurityException e) {
                LogUtil.b(DeviceBindingFragment.TAG, "judgeBluetoothEnable SecurityException:", ExceptionUtils.d(e));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onclickReconnect(ViewHolder viewHolder, cpm cpmVar) {
            DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
            if (deviceInfoUtilsApi.idCurrentDeviceActive(cpmVar.a()) || deviceInfoUtilsApi.getCurrentDeviceList().isEmpty()) {
                startReconnect(cpmVar, viewHolder);
            } else {
                handleWorkMode(cpmVar);
                startReconnect(cpmVar, viewHolder);
            }
        }

        private void handleWorkMode(cpm cpmVar) {
            DeviceInfoUtilsApi deviceInfoUtilsApi = (DeviceInfoUtilsApi) Services.c("PluginDevice", DeviceInfoUtilsApi.class);
            List<DeviceInfo> wearDeviceList = deviceInfoUtilsApi.getWearDeviceList();
            if (wearDeviceList == null) {
                LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode, mDeviceInfoList = null");
                return;
            }
            if (cpmVar.o() == 1) {
                LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.RUN_WORK_MODE");
                setDeviceInfoState(wearDeviceList, cpmVar);
            } else {
                LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode goingConnected == DeviceWorkMode.BAND_MODE");
                setDeviceInfoState(wearDeviceList, cpmVar);
                deviceInfoUtilsApi.resetUpdate();
            }
        }

        private void setDeviceInfoState(List<DeviceInfo> list, cpm cpmVar) {
            for (DeviceInfo deviceInfo : list) {
                if (cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify())) {
                    LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode set device enable");
                    deviceInfo.setDeviceActiveState(1);
                    deviceInfo.setDeviceConnectState(1);
                }
                boolean z = deviceInfo.getAutoDetectSwitchStatus() == 1 && deviceInfo.getDeviceActiveState() == 1;
                if (!cpmVar.a().equalsIgnoreCase(deviceInfo.getDeviceIdentify()) && z) {
                    LogUtil.a(DeviceBindingFragment.TAG, "handleWorkMode target device disable");
                    deviceInfo.setDeviceActiveState(0);
                    deviceInfo.setDeviceConnectState(3);
                }
            }
        }

        private void startReconnect(cpm cpmVar, ViewHolder viewHolder) {
            if (DeviceBindingFragment.this.mReconnectCount < 2) {
                LogUtil.c(DeviceBindingFragment.TAG, "reconnectCount < 2");
                DeviceBindingFragment.this.mReconnectDevice = cpmVar.a();
                DeviceBindingFragment.this.mDeviceListAdapter.setConnecting(true);
                DeviceBindingFragment.this.mOnItemReconnectListener.onReconnect(cpmVar);
                setConnecting(true);
                viewHolder.wearReconnectLayout.setVisibility(8);
                viewHolder.wearLoadingLayout.setVisibility(0);
                this.mAdapterHealthProgressBar.setVisibility(0);
                DeviceBindingFragment.access$1108(DeviceBindingFragment.this);
                return;
            }
            int i = cpmVar.i();
            if (i == 7) {
                showAlertDialogB3();
            } else if (i == 8) {
                showAlertDialogBandMetis();
            } else if (i != 16) {
                switch (i) {
                    case 18:
                    case 19:
                        showAlertDialogCrius();
                        break;
                    case 20:
                    case 21:
                        showAlertDialogTalos();
                        break;
                    default:
                        showDefaultAlertDialog();
                        break;
                }
            } else {
                showAlertDialogBandJanus();
            }
            DeviceBindingFragment.this.mReconnectCount = 0;
        }

        private void createAlertDialog(String str) {
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(this.mContext).b(this.mContext.getString(R.string.IDS_device_mgr_pair_note_can_not_connect)).e(str).c(true).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.DeviceListAdapter.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(DeviceBindingFragment.TAG, "showAlertDialog onclick PositiveButton");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.customTextAlertDialog = a2;
            a2.setCancelable(true);
            this.customTextAlertDialog.show();
        }

        private void showDefaultAlertDialog() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            String str = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_repair_device), 3);
            StringBuilder sb = new StringBuilder("• ");
            sb.append(this.mContext.getString(DeviceBindingFragment.this.mIsPad ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth));
            String sb2 = sb.toString();
            String string = this.mContext.getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(string).append(System.lineSeparator()).append(sb2).append(System.lineSeparator()).append(str);
            createAlertDialog(stringBuffer.toString());
        }

        private void showAlertDialogTalos() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            createDialog(String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_press_restart1), 5));
        }

        private void showAlertDialogBandJanus() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            createDialog(String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_press_restart3), 5));
        }

        private void createDialog(String str) {
            String str2 = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_repair_device), 3);
            String str3 = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_restart_device), str);
            StringBuilder sb = new StringBuilder("• ");
            sb.append(this.mContext.getString(DeviceBindingFragment.this.mIsPad ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth));
            String sb2 = sb.toString();
            String string = this.mContext.getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(string).append(System.lineSeparator()).append(sb2).append(System.lineSeparator()).append(str3).append(System.lineSeparator()).append(str2);
            createAlertDialog(stringBuffer.toString());
        }

        private void showAlertDialogB3() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            String str = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_repair_device), 3);
            String str2 = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_restart_device), String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_press_restart4), 5));
            StringBuilder sb = new StringBuilder("• ");
            sb.append(this.mContext.getString(DeviceBindingFragment.this.mIsPad ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth));
            String sb2 = sb.toString();
            String string = this.mContext.getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(string).append(System.lineSeparator()).append(sb2).append(System.lineSeparator()).append(str2).append(System.lineSeparator()).append(str);
            createAlertDialog(stringBuffer.toString());
        }

        private void showAlertDialogCrius() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            String str = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_repair_device), 3);
            String str2 = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_restart_device), this.mContext.getString(R.string.IDS_device_mgr_pair_note_press_restart2));
            StringBuilder sb = new StringBuilder("• ");
            sb.append(this.mContext.getString(DeviceBindingFragment.this.mIsPad ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth));
            String sb2 = sb.toString();
            String string = this.mContext.getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(string).append(System.lineSeparator()).append(sb2).append(System.lineSeparator()).append(str2).append(System.lineSeparator()).append(str);
            createAlertDialog(stringBuffer.toString());
        }

        private void showAlertDialogBandMetis() {
            LogUtil.a(DeviceBindingFragment.TAG, "enter showAlertDialog");
            String str = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_repair_device), 3);
            String str2 = "• " + String.format(Locale.ENGLISH, this.mContext.getString(R.string.IDS_device_mgr_pair_note_restart_device), this.mContext.getString(R.string.IDS_device_mgr_pair_note_press_restart5));
            StringBuilder sb = new StringBuilder("• ");
            sb.append(this.mContext.getString(DeviceBindingFragment.this.mIsPad ? R.string.IDS_device_reopen_bt_pad : R.string.IDS_device_mgr_pair_note_open_bluetooth));
            String sb2 = sb.toString();
            String string = this.mContext.getString(R.string.IDS_device_mgr_pair_note_avivable_ways);
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(string).append(System.lineSeparator()).append(sb2).append(System.lineSeparator()).append(str2).append(System.lineSeparator()).append(str);
            createAlertDialog(stringBuffer.toString());
        }

        private String getImageIcon(String str) {
            LogUtil.a(DeviceBindingFragment.TAG, "getImageIcon() kind = " + str);
            if (!TextUtils.isEmpty(str)) {
                return DeviceBindingFragment.this.mMap.containsKey(str) ? (String) DeviceBindingFragment.this.mMap.get(str) : "";
            }
            LogUtil.a(DeviceBindingFragment.TAG, "getImageIcon() icon is not exist");
            return "";
        }

        class ViewHolder {
            HealthDivider dividerLine;
            ImageView groupArrowIcon;
            HealthTextView groupContentTv;
            RelativeLayout groupItemLayout;
            ImageView groupLeftIcon;
            HealthTextView produckWearBattery;
            ImageView produckWearBatteryImg;
            HealthTextView productContentTv;
            ImageView productDeviceIcon;
            RelativeLayout productItemLayout;
            RelativeLayout productReLayout;
            HealthTextView title;
            LinearLayout titleLayout;
            RelativeLayout wearBatteryLayout;
            HealthTextView wearConnectText;
            ImageView wearDeviceImg;
            HealthTextView wearDeviceName;
            LinearLayout wearLoadingLayout;
            RelativeLayout wearReLayout;
            RelativeLayout wearReconnectLayout;
            ImageView wearStateimg;
            HealthTextView wearStatetext;

            private ViewHolder() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showBandUnavailableDialog(Context context) {
        LogUtil.c(TAG, "showBandUnavailableDialog");
        boolean h = CommonUtil.h(context, "com.huawei.health.device.ui.DeviceMainActivity");
        LogUtil.c(TAG, "isForeground : " + h);
        if (h) {
            CustomTextAlertDialog customTextAlertDialog = this.mCustomDialog;
            if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
                LogUtil.c(TAG, "showBandUnavailableDialog Already show!");
                return;
            }
            CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_service_area_notice_title).e(context.getString(R.string._2130842667_res_0x7f02142b)).cyU_(R.string._2130841794_res_0x7f0210c2, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceBindingFragment.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).a();
            this.mCustomDialog = a2;
            a2.setCancelable(false);
            if (this.mCustomDialog.isShowing()) {
                return;
            }
            this.mCustomDialog.show();
        }
    }

    private void registerBatteryBroadcast() {
        LogUtil.a(TAG, "enter registerBattery");
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.BATTERY_LEVEL");
        intentFilter.addAction("com.huawei.bone.action.BATTERY_LEVEL");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), DEVICE_BATTERY_RECEIVER, intentFilter, LocalBroadcast.c, null);
    }

    private void unRegisterBatteryBroadcast() {
        try {
            BaseApplication.getContext().unregisterReceiver(DEVICE_BATTERY_RECEIVER);
        } catch (IllegalArgumentException e) {
            LogUtil.a(TAG, "unRegisterBatteryBroadcast " + e.getMessage());
        } catch (RuntimeException e2) {
            LogUtil.a(TAG, "unRegisterBatteryBroadcast " + e2.getMessage());
        }
    }

    static class DeviceBindingHandler extends Handler {
        private DeviceBindingHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            DeviceBindingFragment deviceBindingFragment = DeviceBindingFragment.sFragment != null ? (DeviceBindingFragment) DeviceBindingFragment.sFragment.get() : null;
            LogUtil.a(DeviceBindingFragment.TAG, "handleMessage what: " + message.what);
            if (deviceBindingFragment == null) {
                LogUtil.a(DeviceBindingFragment.TAG, "DeviceBindingFragment is null");
                return;
            }
            if (!deviceBindingFragment.isAdded()) {
                LogUtil.a(DeviceBindingFragment.TAG, "current fragment not attached activity!");
                return;
            }
            int i = message.what;
            if (i == 0 || i == 1) {
                DeviceBindingFragment.connectedDeviceOrTimeOut(message, deviceBindingFragment);
                return;
            }
            if (i == 2) {
                changeToDissconnected(message, deviceBindingFragment);
                return;
            }
            if (i != 3) {
                if (i == 4) {
                    deviceBindingFragment.mDeviceListAdapter.notifyDataSetChanged();
                    return;
                } else {
                    if (i != 5) {
                        return;
                    }
                    deviceBindingFragment.showBandUnavailableDialog(deviceBindingFragment.mContext);
                    return;
                }
            }
            LogUtil.a(DeviceBindingFragment.TAG, "msg_connect_change state:" + deviceBindingFragment.mDeviceListAdapter.isConnecting + "msg: " + message.what);
            if (message.obj instanceof String) {
                deviceBindingFragment.mReconnectDevice = (String) message.obj;
            }
            deviceBindingFragment.mDeviceListAdapter.setConnecting(true);
            deviceBindingFragment.mDeviceList.clear();
            LogUtil.a(DeviceBindingFragment.TAG, "mHandler msg_connecting deviceList.size", Integer.valueOf(deviceBindingFragment.mDeviceList.size()));
            deviceBindingFragment.setAdapterData();
        }

        private void changeToDissconnected(Message message, DeviceBindingFragment deviceBindingFragment) {
            String str = (String) message.obj;
            if (!TextUtils.isEmpty(str) && str.equals(deviceBindingFragment.mReconnectDevice)) {
                removeMessages(1);
                deviceBindingFragment.mDeviceListAdapter.stopLoading();
                deviceBindingFragment.mDeviceList.clear();
                LogUtil.a(DeviceBindingFragment.TAG, "mHandler msg_disconnected deviceList.size", Integer.valueOf(deviceBindingFragment.mDeviceList.size()));
                deviceBindingFragment.mDeviceListAdapter.setConnecting(false);
            }
            deviceBindingFragment.setAdapterData();
        }
    }
}
