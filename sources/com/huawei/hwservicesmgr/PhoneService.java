package com.huawei.hwservicesmgr;

import android.app.Service;
import android.app.job.JobScheduler;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.android.clockwork.companion.partnerapi.SmartWatchInfo;
import com.google.gson.Gson;
import com.huawei.callback.OtaResultCallbackInterface;
import com.huawei.datatype.DeviceCommand;
import com.huawei.datatype.DeviceStatusParam;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.constant.TransferBusinessType;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceParameter;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.manager.powerkit.PowerKitManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.TransferFileInfo;
import com.huawei.hwcommonservice.IServiceBinder;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwdevice.phoneprocess.binder.HeartStudyBinder;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinder;
import com.huawei.hwdevice.phoneprocess.mgr.connectmgr.DeviceDialogMessage;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager;
import com.huawei.hwdevice.phoneprocess.mgr.hwmenstrualmanager.bean.MenstrualSwitchStatus;
import com.huawei.hwdevice.phoneprocess.mgr.telephony.PhoneListManager;
import com.huawei.hwdevice.phoneprocess.receiver.IntegratedServicesReceiver;
import com.huawei.hwdevice.phoneprocess.receiver.NetworkConnectReceiver;
import com.huawei.hwdevice.phoneprocess.util.DeviceSettingSubscription;
import com.huawei.hwdevice.phoneprocess.util.LanguageChangedReceiver;
import com.huawei.hwdevicemgr.framework.INoitifyPhoneServiceCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwlogsmodel.common.LogConfig;
import com.huawei.hwservicesmgr.IWearPhoneServiceAIDL;
import com.huawei.hwservicesmgr.PhoneService;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.utils.Constants;
import com.huawei.phdkit.DvLiteCoreBinder;
import com.huawei.syncmgr.SyncOption;
import com.huawei.wearengine.HiWearCoreBinder;
import com.huawei.wearengine.HwWearEngineNative;
import com.huawei.wearmgr.outofprocess.datatype.BluetoothType;
import defpackage.bfg;
import defpackage.bih;
import defpackage.bmw;
import defpackage.gmu;
import defpackage.ixp;
import defpackage.iyl;
import defpackage.iza;
import defpackage.jen;
import defpackage.jqj;
import defpackage.jrg;
import defpackage.jrk;
import defpackage.jrn;
import defpackage.jsg;
import defpackage.jsl;
import defpackage.jsm;
import defpackage.jsn;
import defpackage.jso;
import defpackage.jst;
import defpackage.jsz;
import defpackage.jtc;
import defpackage.jtd;
import defpackage.jtf;
import defpackage.jtp;
import defpackage.jtr;
import defpackage.jtv;
import defpackage.jue;
import defpackage.juo;
import defpackage.jus;
import defpackage.juu;
import defpackage.jva;
import defpackage.jve;
import defpackage.jvl;
import defpackage.jya;
import defpackage.jyp;
import defpackage.jyx;
import defpackage.jza;
import defpackage.jzb;
import defpackage.kcg;
import defpackage.kco;
import defpackage.kcu;
import defpackage.kcy;
import defpackage.kdi;
import defpackage.kdk;
import defpackage.kec;
import defpackage.ket;
import defpackage.kff;
import defpackage.kfp;
import defpackage.kfv;
import defpackage.kgc;
import defpackage.khu;
import defpackage.kih;
import defpackage.kis;
import defpackage.kiw;
import defpackage.kjl;
import defpackage.kjq;
import defpackage.kjs;
import defpackage.kkr;
import defpackage.snq;
import defpackage.snr;
import defpackage.sqo;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.BuildConfigProperties;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAllowListConfig;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class PhoneService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, IDeviceStateAIDLCallback> f6393a = new HashMap();
    private static IDeviceStateAIDLCallback b = null;
    private static boolean c = false;
    private static khu d = null;
    private static boolean e = false;
    private BroadcastReceiver ab;
    private kco ac;
    private jtv ad;
    private BroadcastReceiver ae;
    private kiw af;
    private kjq ai;
    private BroadcastReceiver aj;
    private jsl ak;
    private kjl al;
    private PhoneListManager am;
    private jsm ao;
    private jya k;
    private jyp l;
    private jyx o;
    private HwDeviceMgrInterface p;
    private IAddDeviceStateAIDLCallback s;
    private DeviceSettingSubscription t;
    private e x;
    private List<d> n = new ArrayList();
    private Handler aa = new Handler();
    private ExecutorService v = Executors.newSingleThreadExecutor();
    private BroadcastReceiver ag = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "mLogReceiver onReceive intent is null");
                return;
            }
            String action = intent.getAction();
            if ("com.huawei.health.action.ACTION_OVERSEA_BETA_LOG".equals(action)) {
                ReleaseLogUtil.e("DEVMGR_PhoneService", "begin to switch beta log from oversea to domestic");
                LogAllowListConfig.e(!Boolean.parseBoolean(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(2039), "beta_log_switch")));
                LogUtil.h();
            }
            if ("com.huawei.health.action.ACTION_BETA_DEBUG_LOG".equals(action)) {
                ReleaseLogUtil.e("DEVMGR_PhoneService", "begin to open debug level for beta log");
                LogConfig.c(Boolean.parseBoolean(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(Constants.START_TO_INDOOR_RUNNING), "beta_debug_log_switch")));
                LogUtil.a("DEVMGR_PhoneService", "begin to set log level, current minimum logLevel is debug: true, BUILD_TYPE: ", BuildConfigProperties.b(), ", LOG_LEVEL: ", Integer.valueOf(health.compact.a.LogConfig.e()));
            }
        }
    };
    private BroadcastReceiver i = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.6
        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String str;
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "mAppInstalledReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            String[] split = intent.getDataString().split(":");
            if (split.length > 1) {
                str = split[1];
            } else {
                str = split[0];
            }
            LogUtil.a("DEVMGR_PhoneService", "mAppInstalledReceiver action:", action, " packageName:", str);
            if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                boolean contains = bfg.d.contains(str);
                String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10001), str);
                int i = contains;
                if (!TextUtils.isEmpty(b2)) {
                    i = CommonUtil.m(BaseApplication.getContext(), b2);
                }
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10001), str, String.valueOf(i), new StorageParams(0));
                NotificationContentProviderUtil.b(str, i);
                return;
            }
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                NotificationContentProviderUtil.b(str, 0);
            }
        }
    };
    private KitWearBinder an = new KitWearBinder();
    private HeartStudyBinder z = new HeartStudyBinder();
    private DvLiteCoreBinder y = new DvLiteCoreBinder();
    private final a j = new a();
    private long m = 1000;
    private iyl g = iyl.d();
    private DeviceStateCallback r = new DeviceStateCallback() { // from class: com.huawei.hwservicesmgr.PhoneService.7
        @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback
        public void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr) {
            if (PhoneService.this.ao != null) {
                PhoneService.this.ao.a(deviceInfo, i, bArr, PhoneService.b, PhoneService.f6393a);
            } else {
                ReleaseLogUtil.e("DEVMGR_PhoneService", "onDataReceived mlayerDataOrder is null");
            }
        }

        @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback
        public void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr) {
            if (PhoneService.b != null) {
                try {
                    PhoneService.b.onAckReceived(deviceInfo, i, bArr);
                } catch (RemoteException unused) {
                    LogUtil.b("DEVMGR_PhoneService", "onAckReceived remoteException RemoteException");
                }
            }
        }

        @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback
        public void onDatasReceived(DeviceInfo deviceInfo, int i, String str) {
            LogUtil.a("DEVMGR_PhoneService", "onDatasReceived remoteException:", str);
            if (PhoneService.b != null) {
                try {
                    PhoneService.b.onDatasReceived(deviceInfo, i, str);
                } catch (RemoteException unused) {
                    LogUtil.b("DEVMGR_PhoneService", "onDataReceived remoteException RemoteException");
                }
            }
        }

        @Override // com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger.DeviceStateCallback
        public void onConnectStatusChanged(DeviceInfo deviceInfo) {
            if (deviceInfo != null) {
                DeviceStatusParam d2 = PhoneService.this.d(deviceInfo);
                if (PhoneService.b != null) {
                    try {
                        PhoneService.b.onDeviceConnectionStateChanged(deviceInfo, deviceInfo.getDeviceConnectState(), d2);
                    } catch (RemoteException unused) {
                        LogUtil.b("DEVMGR_PhoneService", "onDataReceived remoteException RemoteException");
                    }
                }
                jsg.e(deviceInfo, d2);
                return;
            }
            LogUtil.h("DEVMGR_PhoneService", "onConnectStatusChanged deviceInfo is null.");
        }
    };
    private BroadcastReceiver w = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.10
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("DEVMGR_PhoneService", "EmergencyInfoBroadcastReceiver");
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "EmergencyInfoBroadcastReceiver is null");
                sqo.aj("PhoneService mEmergencyInfoChangeReceiver onReceive: intent is null");
            } else if ("emergency_info_change".equals(intent.getAction()) && (intent.getSerializableExtra("emergency_info_value") instanceof gmu)) {
                gmu gmuVar = (gmu) intent.getSerializableExtra("emergency_info_value");
                kdk.a().c(intent.getStringExtra("emergency_info_key"), gmuVar);
            }
        }
    };
    private BroadcastReceiver f = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.14
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            BluetoothAdapter defaultAdapter;
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "mBluetoothSwitchStateReceiver onReceive intent is null");
                return;
            }
            if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(intent.getAction()) || (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) == null) {
                return;
            }
            int state = defaultAdapter.getState();
            LogUtil.a("DEVMGR_PhoneService", "bt switch status:", Integer.valueOf(state));
            if (state == 10) {
                PhoneService.this.stopSelf();
            } else {
                LogUtil.h("DEVMGR_PhoneService", "mBluetoothSwitchStateReceiver default");
            }
        }
    };
    private BroadcastReceiver u = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.15
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "onReceive deviceStatusReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("DEVMGR_PhoneService", "onReceive deviceStatusReceiver:", action);
            if (TextUtils.isEmpty(action) || CommonUtil.bh()) {
                return;
            }
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(action)) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("DEVMGR_PhoneService", "onReceive deviceStatusReceiver is not deviceInfo");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (deviceInfo.getDeviceConnectState() == 2) {
                    PhoneService.this.ai.c();
                    jrg.c(BaseApplication.getContext(), "device_connected");
                }
                PhoneService.this.ai.b(deviceInfo);
                return;
            }
            if ("com.huawei.health.action.unbindNotification".equals(action)) {
                PhoneService.this.stopForeground(false);
            } else {
                LogUtil.h("DEVMGR_PhoneService", "onReceive deviceStatusReceiver default");
            }
        }
    };
    private BroadcastReceiver ah = new BroadcastReceiver() { // from class: com.huawei.hwservicesmgr.PhoneService.11
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("DEVMGR_PhoneService", "onReceive mLoginStatusReceiver:start");
            if (intent == null) {
                LogUtil.h("DEVMGR_PhoneService", "onReceive mLoginStatusReceiver intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("DEVMGR_PhoneService", "onReceive mLoginStatusReceiver:", action);
            if (TextUtils.isEmpty(action)) {
                return;
            }
            if ("com.huawei.plugin.account.logout".equals(action)) {
                PhoneService.this.j();
                jrn.b(context).a();
            }
            if ("com.huawei.plugin.account.login".equals(action)) {
                kff.c().e();
            }
        }
    };
    private final IServiceBinder.Stub q = new IServiceBinder.Stub() { // from class: com.huawei.hwservicesmgr.PhoneService.8
        @Override // com.huawei.hwcommonservice.IServiceBinder
        public IBinder getServiceBinder(String str, int i) {
            LogUtil.a("DEVMGR_PhoneService", "getServiceBinder input packageName is ", str);
            String[] packagesForUid = BaseApplication.getContext().getPackageManager().getPackagesForUid(Binder.getCallingUid());
            if (packagesForUid != null) {
                for (String str2 : packagesForUid) {
                    if (BaseApplication.getAppPackage().equals(str2)) {
                        LogUtil.a("DEVMGR_PhoneService", "Enter verifyPackageNameByUid true");
                        return PhoneService.this.j;
                    }
                }
            }
            if (!ixp.e(BaseApplication.getContext(), str)) {
                return null;
            }
            if ("com.plagh.heartstudy".equals(str)) {
                return PhoneService.this.z;
            }
            if ("com.huawei.dmsdp".equals(str)) {
                return PhoneService.this.y;
            }
            LogUtil.h("DEVMGR_PhoneService", "NOT PHD KIT packageName");
            return null;
        }
    };
    private final BtSwitchStateCallback h = new BtSwitchStateCallback() { // from class: com.huawei.hwservicesmgr.PhoneService.12
        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
        public void onBtSwitchStateCallback(int i) {
            LogUtil.a("DEVMGR_PhoneService", "btSwitchState:", Integer.valueOf(i));
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public DeviceStatusParam d(DeviceInfo deviceInfo) {
        List<DeviceInfo> d2;
        boolean z;
        DeviceStatusParam deviceStatusParam = new DeviceStatusParam();
        deviceStatusParam.setErrorCode(bih.d());
        if (jsn.d(deviceInfo.getProductType())) {
            z = jtc.c().a();
            d2 = jtd.b().d();
        } else {
            d2 = jsz.b().d();
            z = false;
        }
        deviceStatusParam.setIsDeviceConnectDirectly(z);
        deviceStatusParam.setDeviceInfoList(d2);
        return deviceStatusParam;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        HwDeviceMgrInterface hwDeviceMgrInterface = this.p;
        if (hwDeviceMgrInterface != null) {
            List<DeviceInfo> deviceList = hwDeviceMgrInterface.getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DEVMGR_PhoneService");
            ArrayList arrayList = new ArrayList(16);
            c(deviceList, arrayList, SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false));
            this.p.unPair(arrayList, false);
        }
    }

    private void c(List<DeviceInfo> list, List<String> list2, boolean z) {
        if (z) {
            SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "basic_service_switch", true);
            Iterator<DeviceInfo> it = list.iterator();
            while (it.hasNext()) {
                list2.add(it.next().getDeviceIdentify());
            }
            return;
        }
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && jst.d(deviceInfo.getDeviceIdentify()).isSupportAccountSwitch()) {
                LogUtil.a("DEVMGR_PhoneService", "disconnectSupportAccountSwitchDevice", CommonUtil.l(deviceInfo.getDeviceIdentify()));
                list2.add(deviceInfo.getDeviceIdentify());
            }
        }
    }

    class e extends BroadcastReceiver {
        private e() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null || !"com.huawei.health.user.exit".equals(intent.getAction())) {
                return;
            }
            LogUtil.a("ExitBroadcastReceiver", "exit by user");
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwservicesmgr.PhoneService.e.2
                @Override // java.lang.Runnable
                public void run() {
                    if (PhoneService.this.p != null) {
                        LogUtil.a("ExitBroadcastReceiver", "user exit, so disconnectAllDevices");
                        PhoneService.this.p.unPair(null, false);
                    }
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException unused) {
                        LogUtil.b("ExitBroadcastReceiver", "exit by user, InterruptedException");
                    }
                    Process.killProcess(Process.myPid());
                }
            });
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        a();
        juu.e();
        HwDeviceMgrInterface b2 = jsz.b(this);
        this.p = b2;
        b2.registerAdapter(kkr.a());
        kjs.d().e(new IBaseResponseCallback() { // from class: kvv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                PhoneService.this.b(i, obj);
            }
        });
        jsz.b().registerDeviceStateCallback(this.r);
        this.p.registerDeviceStateCallback(this.r);
        this.ak = jsl.a();
        jsm jsmVar = new jsm();
        this.ao = jsmVar;
        jsmVar.e(this);
        f();
        l();
        bmw.e(100054, "", "", "");
        k();
        jzb.e().c();
        jrk.c().a();
        a((Context) this);
        kgc.b().d();
    }

    public /* synthetic */ void b(int i, Object obj) {
        c(i);
    }

    private void a(final Context context) {
        this.v.execute(new Runnable() { // from class: com.huawei.hwservicesmgr.PhoneService.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("DEVMGR_PhoneService", "startDaemonService");
                Intent intent = new Intent();
                intent.setAction("com.huawei.daemonservice");
                intent.setPackage(context.getPackageName());
                intent.putExtra("phoneServiceStarted", "phoneServiceStarted");
                try {
                    context.startService(intent);
                } catch (IllegalStateException | SecurityException unused) {
                    LogUtil.h("DEVMGR_PhoneService", "start daemon service fail");
                }
            }
        });
    }

    private void a() {
        if (CommonUtil.az()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.hwservicesmgr.PhoneService.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("DEVMGR_PhoneService", "PowerKitManager reconnectDevice request");
                    PowerKitManager.e().d("DEVMGR_PhoneService", 65535, PreConnectManager.CONNECT_INTERNAL, "user-reconnectDevice");
                    LogUtil.a("DEVMGR_PhoneService", "PowerKitManager reconnectDevice request end");
                }
            });
        }
    }

    private void k() {
        if (!CommonUtil.bh()) {
            n();
        }
        c(this.an);
        OpAnalyticsUtil.getInstance().init(BaseApplication.getContext());
    }

    private void n() {
        kjq kjqVar = new kjq();
        this.ai = kjqVar;
        kjqVar.bOP_(this);
    }

    private void c(KitWearBinder kitWearBinder) {
        if (kitWearBinder == null) {
            LogUtil.h("DEVMGR_PhoneService", "setDaemonBinder wearBinder is null.");
        }
        HiHealthNativeApi.a(BaseApplication.getContext()).setBinder("KitWearBinderFromPhoneService", kitWearBinder, new HiCommonListener() { // from class: com.huawei.hwservicesmgr.PhoneService.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                LogUtil.h("DEVMGR_PhoneService", "setDaemonBinder onSuccess :", Integer.valueOf(i));
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h("DEVMGR_PhoneService", "setDaemonBinder onFailure :", Integer.valueOf(i));
            }
        });
    }

    private void bSb_(Intent intent) {
        String stringExtra = intent.getStringExtra("WearEngineDataKey");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        if ("WearEngineStartPhoneService".equals(stringExtra)) {
            LogUtil.a("DEVMGR_PhoneService", "setWearEngineBinder ", "WearEngineStartPhoneService");
            HwWearEngineNative hwWearEngineNative = new HwWearEngineNative(BaseApplication.getContext());
            HiWearCoreBinder hiWearCoreBinder = new HiWearCoreBinder();
            hwWearEngineNative.fcm_("WearEngine", hiWearCoreBinder);
            hiWearCoreBinder.setHwWearEngineNative(hwWearEngineNative);
        }
        if ("WearEnginePhdkitStartPhoneService".equals(stringExtra)) {
            LogUtil.a("DEVMGR_PhoneService", "setWearEngineBinder ", "WearEnginePhdkitStartPhoneService");
            new HwWearEngineNative(BaseApplication.getContext()).fcm_("WearEnginePhdkit", this.y);
        }
    }

    private void l() {
        this.aj = new NetworkConnectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        registerReceiver(this.aj, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.huawei.plugin.account.logout");
        intentFilter2.addAction("com.huawei.plugin.account.login");
        BroadcastManagerUtil.bFA_(this, this.ah, intentFilter2, LocalBroadcast.c, null);
        this.ae = new LanguageChangedReceiver();
        IntentFilter intentFilter3 = new IntentFilter();
        intentFilter3.addAction("android.intent.action.LOCALE_CHANGED");
        registerReceiver(this.ae, intentFilter3);
        IntentFilter intentFilter4 = new IntentFilter();
        intentFilter4.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        registerReceiver(this.f, intentFilter4);
        IntentFilter intentFilter5 = new IntentFilter();
        intentFilter5.addAction("emergency_info_change");
        BroadcastManagerUtil.bFA_(this, this.w, intentFilter5, LocalBroadcast.c, null);
        IntentFilter intentFilter6 = new IntentFilter();
        intentFilter6.addAction("com.huawei.health.user.exit");
        e eVar = new e();
        this.x = eVar;
        BroadcastManagerUtil.bFE_(this, eVar, intentFilter6);
        IntentFilter intentFilter7 = new IntentFilter();
        intentFilter7.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter7.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter7.addDataScheme("package");
        registerReceiver(this.i, intentFilter7);
        if (LogConfig.d()) {
            IntentFilter intentFilter8 = new IntentFilter();
            intentFilter8.addAction("com.huawei.health.action.ACTION_OVERSEA_BETA_LOG");
            intentFilter8.addAction("com.huawei.health.action.ACTION_BETA_DEBUG_LOG");
            BroadcastManagerUtil.bFA_(this, this.ag, intentFilter8, LocalBroadcast.c, null);
        }
        p();
        o();
    }

    private void o() {
        if (e) {
            ReleaseLogUtil.d("DEVMGR_PhoneService", "system wrong, service not invoke onDestroy, but system repeat invoke onCreate");
            return;
        }
        this.ab = new IntegratedServicesReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.ACTION_NOTIFICATION_PUSH");
        intentFilter.addAction("com.huawei.intelligent.action.NOTIFY_MSG");
        intentFilter.addAction("com.huawei.bone.ACTION_NOTIFICATION_DELETE");
        intentFilter.addAction("com.huawei.health.ACTION_WEATHER_PUSH");
        intentFilter.addAction("com.huawei.health.ACTION_WEATHER_DATA_PUSH");
        intentFilter.addAction("com.huawei.health.ACTION_LOCAL_PRESSURE_PUSH");
        intentFilter.addAction("com.huawei.health.ACTION_NOTIFICATION_ENABLE_PUSH");
        intentFilter.addAction("com.huawei.health.ACTION_NOTIFY_PUSH_MULTI_LINK");
        intentFilter.addAction("com.huawei.health.ACTION_NOTIFICATION_CLOCK_STATE");
        BroadcastManagerUtil.bFz_(BaseApplication.getContext(), this.ab, intentFilter);
        e = true;
    }

    private void p() {
        if (CommonUtil.bh()) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.health.action.unbindNotification");
        BroadcastManagerUtil.bFC_(this, this.u, intentFilter, LocalBroadcast.c, null);
    }

    private void t() {
        try {
            LogUtil.a("DEVMGR_PhoneService", "Enter unregisterReceiver()");
            e = false;
            unregisterReceiver(this.aj);
            unregisterReceiver(this.ae);
            unregisterReceiver(this.u);
            unregisterReceiver(this.f);
            unregisterReceiver(this.ah);
            unregisterReceiver(this.w);
            unregisterReceiver(this.i);
            if (this.ab != null) {
                ReleaseLogUtil.e("DEVMGR_PhoneService", "onDestroy unregisterReceiver mHandleIntentReceiver");
                BroadcastManagerUtil.bFJ_(getApplicationContext(), this.ab);
                this.ab = null;
            }
            if (this.x != null) {
                LogUtil.a("DEVMGR_PhoneService", "onDestroy unregisterReceiverPackage()");
                BroadcastManagerUtil.bFK_(getApplicationContext(), this.x);
                this.x = null;
            }
            kfv.d().e();
            if (this.ag == null || !LogConfig.d()) {
                return;
            }
            unregisterReceiver(this.ag);
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DEVMGR_PhoneService", "unregisterReceiver IllegalArgumentException");
        }
    }

    private void f() {
        kis.d(this);
        this.ad = jtv.b(this);
        d = khu.a(this);
        kfp.d(this);
        this.al = kjl.b();
        this.o = jyx.a();
        this.l = jyp.c();
        this.t = DeviceSettingSubscription.b();
        this.af = kiw.c();
        this.k = jya.d();
        kco.e().a();
        m();
        LogUtil.a("DEVMGR_PhoneService", "initManager finish");
    }

    private void m() {
        if (c) {
            ReleaseLogUtil.d("DEVMGR_PhoneService", "registerSystemIncomingMonitor system wrong, service not invoke onDestroy,but system repeat invoke onCreate");
            return;
        }
        PhoneListManager phoneListManager = new PhoneListManager(this);
        this.am = phoneListManager;
        phoneListManager.c();
        c = true;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        LogUtil.a("DEVMGR_PhoneService", "onStartCommand enter");
        try {
            jsl.a();
            jsl.bJh_(intent);
            if (intent != null) {
                bRX_(intent);
                return super.onStartCommand(intent, i, i2);
            }
            LogUtil.h("DEVMGR_PhoneService", "error, intent is null");
            return 1;
        } catch (Exception e2) {
            LogUtil.b("DEVMGR_PhoneService", "onStartCommand Exception error,", ExceptionUtils.d(e2));
            return 1;
        }
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        LogUtil.a("onTaskRemoved", new Object[0]);
        jzb.e().d();
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onLowMemory() {
        LogUtil.b("DEVMGR_PhoneService", "onLowMemory");
        super.onLowMemory();
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        bSa_(intent);
        return super.onUnbind(intent);
    }

    private void bSa_(Intent intent) {
        if (TextUtils.isEmpty(intent.getAction())) {
            jtp.a().e(-1, "ui process is killed, can not sync sleep.");
        } else {
            LogUtil.h("DEVMGR_PhoneService", "other situation, no need send kit message.");
        }
    }

    private void bRX_(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            LogUtil.h("DEVMGR_PhoneService", "error, action is null");
        } else {
            LogUtil.a("DEVMGR_PhoneService", "handleIntent: action :", action);
            bRZ_(action, intent);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void bRZ_(String str, Intent intent) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -57849172:
                if (str.equals("com.huawei.bone.action.REGISTER_PHONE_LISTEN")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case 266752257:
                if (str.equals("com.huawei.iconnect.ACTION_CONNECT_DEVICE")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 823795052:
                if (str.equals("android.intent.action.USER_PRESENT")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 1306405379:
                if (str.equals("com.huawei.iconnect.ACTION_RECONNECT_MSG")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1384829825:
                if (str.equals("com.huawei.bone.action.StartPhoneService")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            LogUtil.a("DEVMGR_PhoneService", "REGISTER_PHONE_LISTEN_ACTION enter");
            PhoneListManager phoneListManager = this.am;
            if (phoneListManager != null) {
                phoneListManager.a();
                return;
            }
            return;
        }
        if (c2 == 1) {
            ReleaseLogUtil.e("DEVMGR_PhoneService", "ICONNECT_CONNECT_DEVICE_ACTION");
            kjs.d().c(false);
            String stringExtra = intent.getStringExtra("DEVICE_NAME");
            String stringExtra2 = intent.getStringExtra("DEVICE_ID");
            String stringExtra3 = intent.getStringExtra("DEVICE_MODULE_ID");
            if (TextUtils.isEmpty(stringExtra) || TextUtils.isEmpty(stringExtra2) || TextUtils.isEmpty(stringExtra3)) {
                LogUtil.h("DEVMGR_PhoneService", "deviceName or encryptId or moduleId is empty.");
                return;
            }
            jtf.c();
            SharedPreferenceManager.c(String.valueOf(10), "app_big_data_device_name", stringExtra);
            jus.e().b(stringExtra3, stringExtra, stringExtra2);
            return;
        }
        if (c2 == 2) {
            ReleaseLogUtil.e("DEVMGR_PhoneService", "Receive unlock phone msg, so force connect device.");
            bmw.e(100060, "", "", "");
            HwDeviceMgrInterface hwDeviceMgrInterface = this.p;
            if (hwDeviceMgrInterface != null) {
                hwDeviceMgrInterface.connectDevice(null, BluetoothType.BR.getValue() | BluetoothType.AW.getValue());
                return;
            } else {
                LogUtil.h("DEVMGR_PhoneService", "mDeviceManager is null.");
                return;
            }
        }
        if (c2 == 3) {
            ReleaseLogUtil.e("DEVMGR_PhoneService", "EMUI iConnect start reconnect");
            bmw.e(100059, "", "", "");
            HwDeviceMgrInterface hwDeviceMgrInterface2 = this.p;
            if (hwDeviceMgrInterface2 != null) {
                hwDeviceMgrInterface2.connectDevice(null, BluetoothType.BLE.getValue());
                return;
            }
            return;
        }
        if (c2 == 4) {
            c(this.an);
            bSb_(intent);
            bRY_(intent);
            bSc_(intent);
            return;
        }
        LogUtil.h("DEVMGR_PhoneService", "handle intent default");
    }

    private void bSc_(Intent intent) {
        if (!intent.hasExtra("reconnect")) {
            LogUtil.a("DEVMGR_PhoneService", "not reconnect");
        } else {
            LogUtil.a("DEVMGR_PhoneService", "startReconnect");
        }
    }

    private void bRY_(Intent intent) {
        LogUtil.a("DEVMGR_PhoneService", "onTwsDeviceUpdate isProfileDeviceUpdate enter ");
        if (!intent.getBooleanExtra("isProfileDeviceUpdate", false)) {
            LogUtil.h("DEVMGR_PhoneService", "onTwsDeviceUpdate isProfileDeviceUpdate is false");
            return;
        }
        HwDeviceMgrInterface hwDeviceMgrInterface = this.p;
        if (hwDeviceMgrInterface == null) {
            LogUtil.h("DEVMGR_PhoneService", "onTwsDeviceUpdate mDeviceManager is null ");
            return;
        }
        Iterator<Map.Entry<String, DeviceCapability>> it = hwDeviceMgrInterface.queryDeviceCapability(2, "", "DEVMGR_PhoneService").entrySet().iterator();
        while (it.hasNext()) {
            DeviceCapability value = it.next().getValue();
            if (value != null && value.isSupportTws()) {
                LogUtil.a("DEVMGR_PhoneService", "onTwsDeviceUpdate triggerByBroadcast");
                kff.c().b();
                return;
            }
            LogUtil.c("DEVMGR_PhoneService", "onReceive deviceCapability not Support Tws");
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        t();
        LogUtil.a("DEVMGR_PhoneService", "phone service destroyManager");
        h();
        if (!CommonUtil.bh()) {
            i();
        }
        this.an = null;
        c((KitWearBinder) null);
        Process.killProcess(Process.myPid());
        this.v.shutdown();
    }

    private void i() {
        kjq kjqVar = this.ai;
        if (kjqVar == null) {
            LogUtil.h("DEVMGR_PhoneService", "onDestroy mkeepAliveOptimize null");
        } else {
            kjqVar.a();
        }
    }

    private void h() {
        PhoneListManager phoneListManager = this.am;
        if (phoneListManager != null) {
            c = false;
            phoneListManager.d();
        }
        khu khuVar = d;
        if (khuVar != null) {
            khuVar.b();
        }
        jtv jtvVar = this.ad;
        if (jtvVar != null) {
            jtvVar.a();
        }
        kjl kjlVar = this.al;
        if (kjlVar != null) {
            kjlVar.d();
        }
        kco kcoVar = this.ac;
        if (kcoVar != null) {
            kcoVar.c();
        }
        if (jue.b() != null) {
            jue.b().e();
        }
        if (kdk.a() != null) {
            kdk.a().d();
        }
        jza.c().a();
        try {
            jyp jypVar = this.l;
            if (jypVar != null) {
                jypVar.d();
            }
            jyx jyxVar = this.o;
            if (jyxVar != null) {
                jyxVar.d();
            }
            HwDeviceMgrInterface hwDeviceMgrInterface = this.p;
            if (hwDeviceMgrInterface != null) {
                hwDeviceMgrInterface.destroy();
            }
            jsl jslVar = this.ak;
            if (jslVar != null) {
                jslVar.f();
            }
            DeviceSettingSubscription deviceSettingSubscription = this.t;
            if (deviceSettingSubscription != null) {
                deviceSettingSubscription.a();
            }
            kiw kiwVar = this.af;
            if (kiwVar != null) {
                kiwVar.a();
            }
            jya jyaVar = this.k;
            if (jyaVar != null) {
                jyaVar.c();
            }
            if (!kcu.c()) {
                kcu.d().a();
            }
            jtr.b().e();
            kcg.b().j();
            jva.b().c();
            HwExerciseAdviceManager.getInstance().destroy();
            jve.c().d();
            HwHeartRateManager.getInstance().onDestroy();
            ket.a().b();
        } catch (IllegalArgumentException unused) {
            LogUtil.b("DEVMGR_PhoneService", "destroy manager IllegalArgumentException");
        }
        jzb.e().d();
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            LogUtil.h("DEVMGR_PhoneService", "on bind intent is null.");
            return null;
        }
        return this.q;
    }

    class a extends IWearPhoneServiceAIDL.Stub {
        private a() {
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void sendDeviceData(DeviceCommand deviceCommand) throws RemoteException {
            if (PhoneService.this.p != null) {
                PhoneService.this.p.sendDeviceData(deviceCommand);
            } else {
                LogUtil.h("DEVMGR_PhoneService", "sendDeviceData, HwDeviceManager is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException {
            if (PhoneService.this.p == null) {
                LogUtil.h("DEVMGR_PhoneService", "registerDeviceStateCallback, HwDeviceManager is null");
                return;
            }
            e(iDeviceStateAIDLCallback);
            IDeviceStateAIDLCallback unused = PhoneService.b = iDeviceStateAIDLCallback;
            PhoneService.this.p.registerDeviceStateCallback(PhoneService.this.r);
        }

        private void e(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) {
            String d = CommonUtil.d(Binder.getCallingPid());
            ReleaseLogUtil.e("DEVMGR_PhoneService", "callProcess is ", d);
            if (TextUtils.isEmpty(d)) {
                return;
            }
            if (BaseApplication.getAppPackage().equals(d)) {
                PhoneService.f6393a.put(BaseApplication.getAppPackage(), iDeviceStateAIDLCallback);
                return;
            }
            if (d.endsWith(":PhoneService")) {
                PhoneService.f6393a.put(":PhoneService", iDeviceStateAIDLCallback);
            } else if (!d.endsWith(":DaemonService")) {
                PhoneService.f6393a.put("other", iDeviceStateAIDLCallback);
            } else {
                PhoneService.f6393a.put(":DaemonService", iDeviceStateAIDLCallback);
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void unRegisterDeviceStateCallBack(IDeviceStateAIDLCallback iDeviceStateAIDLCallback) throws RemoteException {
            if (PhoneService.this.p != null) {
                PhoneService.this.p.unRegisterDeviceStateCallback(PhoneService.this.r);
            } else {
                LogUtil.h("DEVMGR_PhoneService", "unRegisterDeviceStateCallback, HwDeviceManager is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void addDevice(DeviceParameter deviceParameter, String str, final IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback) throws RemoteException {
            if (PhoneService.this.p == null) {
                LogUtil.h("DEVMGR_PhoneService", "addDevice, HwDeviceManager is null");
                return;
            }
            if (deviceParameter == null) {
                LogUtil.h("DEVMGR_PhoneService", "addDevice, deviceParameter is null");
                return;
            }
            kjs.d().c(false);
            jtf.c();
            ReleaseLogUtil.e("DEVMGR_PhoneService", "addDevice bluetoothType is ", Integer.valueOf(deviceParameter.getBluetoothType()));
            PhoneService.this.s = iAddDeviceStateAIDLCallback;
            PhoneService.this.p.addDevice(deviceParameter, new IAddDeviceStateCallback() { // from class: com.huawei.hwservicesmgr.PhoneService.a.4
                @Override // com.huawei.hwbtsdk.btdatatype.callback.IAddDeviceStateCallback
                public void onAddDeviceState(int i) {
                    try {
                        IAddDeviceStateAIDLCallback iAddDeviceStateAIDLCallback2 = iAddDeviceStateAIDLCallback;
                        if (iAddDeviceStateAIDLCallback2 != null) {
                            iAddDeviceStateAIDLCallback2.onAddDeviceState(i);
                        }
                    } catch (RemoteException e) {
                        LogUtil.b("DEVMGR_PhoneService", e.getMessage());
                    }
                }
            }, str);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void switchDevice(List<DeviceInfo> list, String str) throws RemoteException {
            if (PhoneService.this.p != null) {
                PhoneService.this.p.switchDevice(list, str);
            } else {
                LogUtil.h("DEVMGR_PhoneService", "setUsedDeviceList, HwDeviceManager is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void migrateUsedDeviceList(List<DeviceInfo> list) throws RemoteException {
            if (PhoneService.this.p == null) {
                LogUtil.h("DEVMGR_PhoneService", "migrateUsedDeviceList, HwDeviceManager is null");
            } else {
                jsz.b().e(list);
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void sendOTAFileData(DeviceInfo deviceInfo, String str, String str2, int i, IOTAResultAIDLCallback iOTAResultAIDLCallback) throws RemoteException {
            String str3;
            String str4;
            String str5;
            int i2;
            if (PhoneService.this.p == null) {
                LogUtil.h("DEVMGR_PhoneService", "getUsedDeviceList, HwDeviceManager is null");
                return;
            }
            String[] split = str.split("@");
            if (split == null || split.length != 2) {
                str3 = "";
                str4 = "";
            } else {
                str3 = split[0];
                str4 = split[1];
            }
            String[] split2 = str3.split(File.separatorChar == '\\' ? "\\\\" : File.separator);
            LogUtil.a("DEVMGR_PhoneService", "sendOTAFileData filePath :", str3, ",and version :", str4, ",otaParameter:", str2, ",updateMode:", Integer.valueOf(i), "sendOTAFileData tmpFilePath :", split2[split2.length - 1]);
            if (deviceInfo != null) {
                LogUtil.a("DEVMGR_PhoneService", "sendOtaFileData current upgrade device is not aw70");
                str5 = deviceInfo.getDeviceIdentify();
                i2 = deviceInfo.getProductType();
            } else {
                str5 = null;
                i2 = -1;
            }
            if (TextUtils.isEmpty(str5)) {
                LogUtil.h("DEVMGR_PhoneService", "sendOtaFileData not find target device, please check the name of version");
                return;
            }
            DeviceParameter deviceParameter = new DeviceParameter();
            deviceParameter.setMac(str5);
            deviceParameter.setFilePath(str3);
            deviceParameter.setVersion(str4);
            deviceParameter.setProductType(i2);
            PhoneService.this.b(deviceParameter, str2, i, iOTAResultAIDLCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void forceConnectDevice() throws RemoteException {
            if (PhoneService.this.p != null) {
                PhoneService.this.p.connectDevice(null, BluetoothType.BR.getValue() | BluetoothType.AW.getValue());
            } else {
                LogUtil.h("DEVMGR_PhoneService", "forceConnectBTDevice, HwDeviceManager is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public Map<String, DeviceCapability> queryDeviceCapability(int i, String str, String str2) throws RemoteException {
            if (PhoneService.this.p != null) {
                return PhoneService.this.p.queryDeviceCapability(i, str, str2);
            }
            LogUtil.h("DEVMGR_PhoneService", "queryDeviceCapability, HwDeviceManager is null");
            return null;
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException {
            LogUtil.c("DEVMGR_PhoneService", "registerBinder called");
            d dVar = PhoneService.this.new d(str, str2);
            iBinder.linkToDeath(dVar, 0);
            PhoneService.this.n.add(dVar);
            if (PhoneService.this.ak != null) {
                jsl unused = PhoneService.this.ak;
                jsl.bJh_(null);
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void getFile(TransferFileInfo transferFileInfo, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException {
            LogUtil.c("DEVMGR_PhoneService", "getFile called");
            jvl.b().b(transferFileInfo, iTransferSleepAndDFXFileCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void openSystemBluetoothSwitch(boolean z) {
            try {
                if (z) {
                    PhoneService.this.g.d(PhoneService.this.h);
                    return;
                }
                if (PhoneService.this.s != null) {
                    try {
                        PhoneService.this.s.onAddDeviceState(1);
                        return;
                    } catch (RemoteException e) {
                        ReleaseLogUtil.c("DEVMGR_PhoneService", "onAddDeviceState: ", ExceptionUtils.d(e));
                        return;
                    }
                }
                ReleaseLogUtil.d("DEVMGR_PhoneService", "openSystemBluetoothSwitch mDeviceListStateCallback is null");
            } catch (RuntimeException e2) {
                ReleaseLogUtil.c("DEVMGR_PhoneService", "openSystemBluetoothSwitch exception ", ExceptionUtils.d(e2));
                OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("PhoneService_openSystemBluetoothSwitch", DfxUtils.d(Thread.currentThread(), e2));
                throw e2;
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void isLoudspeakerMuteOpenOrCloseHeartRate(int i, int i2, IHeartRateStateAIDLCallback iHeartRateStateAIDLCallback) throws RemoteException {
            if (PhoneService.this.ak != null) {
                PhoneService.this.ak.e(i, i2, iHeartRateStateAIDLCallback);
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void startSyncWorkOut() throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "start synchronization");
            HwExerciseAdviceManager.getInstance().syncDeviceWorkoutRecordInfo();
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerWorkOutCallback(IBaseCallback iBaseCallback) throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "register Workout Callback do nothing");
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void unPair(List<String> list, boolean z) {
            kdi.c().d(list, z);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public boolean isPrompt() {
            if (PhoneService.d != null) {
                return PhoneService.d.e();
            }
            return true;
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public boolean isOutgoingCall() {
            if (PhoneService.d != null) {
                return PhoneService.d.d();
            }
            return false;
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void transFileUnite(String str, String str2, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException {
            if (str2 == null) {
                LogUtil.h("DEVMGR_PhoneService", "startAppTransFile, transferFileInfo is null");
            } else {
                PhoneService.this.bSd_(str, str2, iAppTransferFileResultAIDLCallback, parcelFileDescriptor);
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void stopTransferFile(String str, int i, IBaseCallback iBaseCallback) throws RemoteException {
            if (PhoneService.this.o != null) {
                PhoneService.this.o.e(str, i, iBaseCallback);
            } else {
                LogUtil.h("DEVMGR_PhoneService", "stopTransferFile, mCommonFileManager is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void startRequestFile(String str, ITransferSleepAndDFXFileCallback iTransferSleepAndDFXFileCallback) throws RemoteException {
            if (PhoneService.this.l == null) {
                LogUtil.h("DEVMGR_PhoneService", "startRequestFile, mCommonFileRequestManager is null");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("DEVMGR_PhoneService", "startRequestFile transFileInfo is null");
                return;
            }
            jqj jqjVar = (jqj) new Gson().fromJson(str, jqj.class);
            if (jqjVar == null || iTransferSleepAndDFXFileCallback == null) {
                return;
            }
            LogUtil.a("DEVMGR_PhoneService", "startRequestFile() transferFileInfo, fileName: ", jqjVar.j(), " fileType: ", Integer.valueOf(jqjVar.n()));
            PhoneService.this.l.b(jqjVar, iTransferSleepAndDFXFileCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void stopRequestFile(String str, IBaseCallback iBaseCallback) throws RemoteException {
            if (PhoneService.this.l == null) {
                LogUtil.h("DEVMGR_PhoneService", "stopRequestFile, mCommonFileRequestManager is null");
                return;
            }
            if (TextUtils.isEmpty(str)) {
                LogUtil.h("DEVMGR_PhoneService", "stopRequestFile transFileInfo is null");
                return;
            }
            jqj jqjVar = (jqj) new Gson().fromJson(str, jqj.class);
            if (jqjVar == null || iBaseCallback == null) {
                return;
            }
            LogUtil.a("DEVMGR_PhoneService", "stopRequestFile() transferFileInfo, fileName: ", jqjVar.j(), " fileType: ", Integer.valueOf(jqjVar.n()));
            PhoneService.this.l.b(jqjVar, iBaseCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registMusicMgrCallback(HwMusicMgrCallback hwMusicMgrCallback) throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", " phone register MusicMgrCallback");
            if (PhoneService.this.an != null) {
                if (hwMusicMgrCallback == null) {
                    LogUtil.h("DEVMGR_PhoneService", "callback is null");
                }
                PhoneService.this.an.registerMusicMgrCallback(hwMusicMgrCallback);
                return;
            }
            LogUtil.h("DEVMGR_PhoneService", "register MusicMgrCallback, mWearBinder is null");
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void unRegisterMusicMgrCallback() throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "phone unRegister MusicMgrCallback");
            if (PhoneService.this.an != null) {
                PhoneService.this.an.unRegisterMusicMgrCallback();
            } else {
                LogUtil.h("DEVMGR_PhoneService", "unregister MusicMgrCallback, mWearBinder is null");
            }
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public SmartWatchInfo getPendingConnectWatchInfo() throws RemoteException {
            return iza.c();
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void setAwFileCallback(IBaseCallback iBaseCallback) throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "setAwFileCallback called");
            jsz.b().d(iBaseCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public String dialogMessage(DeviceDialogMessage deviceDialogMessage, IBluetoothDialogAidlCallback iBluetoothDialogAidlCallback) {
            return juo.d(deviceDialogMessage, iBluetoothDialogAidlCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void setBinder(String str, IBinder iBinder, IBaseCallback iBaseCallback) throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "setBinder called tag:", str);
            jsl.a().bJk_(iBinder, iBaseCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void setSuggestionBinder(IBinder iBinder) {
            LogUtil.a("DEVMGR_PhoneService", "setSuggestionBinder()");
            HwExerciseAdviceManager.getInstance().setSuggestionAidl(iBinder);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void sendMenstrualSwitch(MenstrualSwitchStatus menstrualSwitchStatus) {
            if (menstrualSwitchStatus == null) {
                LogUtil.h("DEVMGR_PhoneService", "sendMenstrualSwitch error");
            }
            kcu.d().a(menstrualSwitchStatus);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void notifyAllSyncTask() throws RemoteException {
            LogUtil.a("DEVMGR_PhoneService", "notifyAllSyncTask enter.");
            ket.a().e("DEVMGR_PhoneService");
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void sendEcgBlockList(String str, IBaseCallback iBaseCallback) {
            kcy.e(BaseApplication.getContext()).a(str, iBaseCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void sendWifiConfigurationInformation() {
            kfv.d().c();
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public boolean isCouldGetWifiConfigurationInformation() {
            return kfv.d().b();
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public List<DeviceInfo> getDeviceList(int i, HwGetDevicesParameter hwGetDevicesParameter, String str) {
            return PhoneService.this.p.getDeviceList(HwGetDevicesMode.getValue(i), hwGetDevicesParameter, str);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public boolean notifyPhoneService(String str, DeviceInfo deviceInfo, int i, String str2) {
            return jso.d().b(str, deviceInfo, i, str2, PhoneService.this);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerCallback(String str, INoitifyPhoneServiceCallback iNoitifyPhoneServiceCallback) {
            jso.d().d(str, iNoitifyPhoneServiceCallback);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void unregisterCallback(String str) {
            jso.d().c(str);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void starSycnCoreSleep(SyncOption syncOption, final IBaseCallback iBaseCallback) {
            LogUtil.a("DEVMGR_PhoneService", "enter phoneservce to starSycnCoreSleep");
            jen.e(kec.c()).a(syncOption, new IBaseResponseCallback() { // from class: com.huawei.hwservicesmgr.PhoneService.a.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        if (obj == null) {
                            iBaseCallback.onResponse(i, "");
                        } else {
                            iBaseCallback.onResponse(i, obj.toString());
                        }
                    } catch (RemoteException e) {
                        LogUtil.b("DEVMGR_PhoneService", "startSyncCoreSleep() exception = ", e.getMessage());
                    }
                }
            });
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public boolean isCoreSleepSyncing() {
            LogUtil.a("DEVMGR_PhoneService", "enter phoneservce to isCoreSleepSyncing");
            return jen.e(kec.c()).b();
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerCoreSleepProgressListener(final IBaseCallback iBaseCallback) {
            LogUtil.a("DEVMGR_PhoneService", "enter phoneservce to registerCoreSleepProgressListener");
            jen.e(kec.c()).e(new IBaseResponseCallback() { // from class: com.huawei.hwservicesmgr.PhoneService.a.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    try {
                        if (obj == null) {
                            iBaseCallback.onResponse(i, "");
                        } else {
                            iBaseCallback.onResponse(i, obj.toString());
                        }
                    } catch (RemoteException e) {
                        LogUtil.b("DEVMGR_PhoneService", "registerProgressListener() exception = ", e.getMessage());
                    }
                }
            });
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerLinkageDeviceCommandListener(LinkageDeviceCommandListener linkageDeviceCommandListener) throws RemoteException {
            kgc.b().a(linkageDeviceCommandListener);
        }

        @Override // com.huawei.hwservicesmgr.IWearPhoneServiceAIDL
        public void registerLinkageDeviceDataListener(IBaseCallback iBaseCallback) throws RemoteException {
            kgc.b().e(iBaseCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceParameter deviceParameter, String str, int i, final IOTAResultAIDLCallback iOTAResultAIDLCallback) {
        kih.e().a(deviceParameter, str, i, new OtaResultCallbackInterface() { // from class: com.huawei.hwservicesmgr.PhoneService.3
            @Override // com.huawei.callback.OtaResultCallbackInterface
            public void onFileTransferState(String str2, int i2) {
                try {
                    IOTAResultAIDLCallback iOTAResultAIDLCallback2 = iOTAResultAIDLCallback;
                    if (iOTAResultAIDLCallback2 != null) {
                        iOTAResultAIDLCallback2.onFileTransferState(i2);
                    }
                } catch (Exception unused) {
                    LogUtil.b("DEVMGR_PhoneService", "sendOtaInfo onFileTransferState RemoteException");
                }
            }

            @Override // com.huawei.callback.OtaResultCallbackInterface
            public void onUpgradeFailed(String str2, int i2, String str3) {
                try {
                    IOTAResultAIDLCallback iOTAResultAIDLCallback2 = iOTAResultAIDLCallback;
                    if (iOTAResultAIDLCallback2 != null) {
                        iOTAResultAIDLCallback2.onUpgradeFailed(i2, str3);
                    }
                } catch (Exception unused) {
                    LogUtil.b("DEVMGR_PhoneService", "sendOtaInfo onUpgradeFailed RemoteException");
                }
            }

            @Override // com.huawei.callback.OtaResultCallbackInterface
            public void onFileRespond(String str2, int i2) {
                try {
                    IOTAResultAIDLCallback iOTAResultAIDLCallback2 = iOTAResultAIDLCallback;
                    if (iOTAResultAIDLCallback2 != null) {
                        iOTAResultAIDLCallback2.onFileRespond(i2);
                    }
                } catch (Exception unused) {
                    LogUtil.b("DEVMGR_PhoneService", "sendOtaInfo onFileRespond RemoteException");
                }
            }

            @Override // com.huawei.callback.OtaResultCallbackInterface
            public void onFileTransferReport(String str2) {
                try {
                    IOTAResultAIDLCallback iOTAResultAIDLCallback2 = iOTAResultAIDLCallback;
                    if (iOTAResultAIDLCallback2 != null) {
                        iOTAResultAIDLCallback2.onFileTransferReport(str2);
                    }
                } catch (RemoteException unused) {
                    LogUtil.b("DEVMGR_PhoneService", "sendOtaInfo onFileTransferReport RemoteException");
                }
            }
        });
    }

    final class d implements IBinder.DeathRecipient {

        /* renamed from: a, reason: collision with root package name */
        private final String f6396a;
        private final String b;

        d(String str, String str2) {
            this.b = str;
            this.f6396a = str2;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (PhoneService.this.n != null) {
                if (PhoneService.this.v != null) {
                    if (PhoneService.this.n.indexOf(this) < 0) {
                        return;
                    }
                    LogUtil.a("DEVMGR_PhoneService", "client died:", this.b);
                    PhoneService.this.n.remove(this);
                    PhoneService.this.v.execute(new Runnable() { // from class: com.huawei.hwservicesmgr.PhoneService.d.1
                        @Override // java.lang.Runnable
                        public void run() {
                            Intent intent = new Intent();
                            intent.setAction(d.this.f6396a);
                            intent.setPackage(d.this.b);
                            PhoneService.this.startService(intent);
                            LogUtil.a("DEVMGR_PhoneService", "restart service:", d.this.b, " action is ", d.this.f6396a);
                            if (d.this.c() || PhoneService.this.aa == null) {
                                PhoneService.this.m = 1000L;
                                if (PhoneService.this.ak != null) {
                                    jsl unused = PhoneService.this.ak;
                                    jsl.bJh_(null);
                                    return;
                                }
                                return;
                            }
                            PhoneService.this.aa.postDelayed(this, PhoneService.this.g());
                        }
                    });
                    return;
                }
                LogUtil.h("DEVMGR_PhoneService", "client died binderDied mExecutor is null");
                return;
            }
            LogUtil.h("DEVMGR_PhoneService", "client died binderDied mClients is null");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            for (int i = 0; i < PhoneService.this.n.size(); i++) {
                if (((d) PhoneService.this.n.get(i)).b.equals(this.b) && ((d) PhoneService.this.n.get(i)).f6396a.equals(this.f6396a)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long g() {
        long j = this.m * 2000;
        this.m = j;
        if (j <= 600000) {
            return j;
        }
        return 600000L;
    }

    private void c(final int i) {
        LogUtil.a("DEVMGR_PhoneService", "stopPhoneServiceProcess,", Integer.valueOf(i));
        stopSelf();
        this.aa.postDelayed(new Runnable() { // from class: kvu
            @Override // java.lang.Runnable
            public final void run() {
                PhoneService.this.b(i);
            }
        }, 2000L);
    }

    public /* synthetic */ void b(int i) {
        HwDeviceMgrInterface hwDeviceMgrInterface;
        if (i == 2 && (hwDeviceMgrInterface = this.p) != null) {
            List<DeviceInfo> deviceList = hwDeviceMgrInterface.getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "stopPhoneServiceProcess");
            if (deviceList == null || !deviceList.isEmpty()) {
                return;
            }
            d();
            return;
        }
        LogUtil.h("DEVMGR_PhoneService", "unknown status");
    }

    private void d() {
        if (!kjs.d().e()) {
            LogUtil.h("DEVMGR_PhoneService", "stopPhoneServiceProcess no kill process.");
            return;
        }
        try {
            Thread.sleep(500L);
        } catch (InterruptedException unused) {
            LogUtil.b("DEVMGR_PhoneService", "dealPhoneServiceKill InterruptedException.");
        }
        if (!kjs.d().e()) {
            LogUtil.h("DEVMGR_PhoneService", "stopPhoneServiceProcess again no kill process.");
            return;
        }
        if (snq.c().e()) {
            LogUtil.h("DEVMGR_PhoneService", "cancelKillSelf because isKillSelfFlag is true");
            kjs.d().e(jtc.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "cancelKillSelf"));
            snq.c().d(false);
            return;
        }
        LogUtil.a("DEVMGR_PhoneService", "kill PhoneService start");
        snr.a().b();
        LogUtil.a("DEVMGR_PhoneService", "kill UniteDeviceService");
        ((JobScheduler) BaseApplication.getContext().getSystemService("jobscheduler")).cancel(10009);
        jsg.e = false;
        LogUtil.a("DEVMGR_PhoneService", "kill PhoneJobService");
        Process.killProcess(Process.myPid());
        LogUtil.a("DEVMGR_PhoneService", "kill PhoneService end");
    }

    public void bSd_(String str, String str2, IAppTransferFileResultAIDLCallback iAppTransferFileResultAIDLCallback, ParcelFileDescriptor parcelFileDescriptor) {
        if (TextUtils.isEmpty(str2)) {
            LogUtil.h("DEVMGR_PhoneService", "transFileUniteDistribution transFileInfo is null");
            return;
        }
        TransferBusinessType busiTypeByValue = TransferBusinessType.getBusiTypeByValue(str);
        if (busiTypeByValue == null) {
            LogUtil.h("DEVMGR_PhoneService", "transferBusiType is null");
            return;
        }
        LogUtil.h("DEVMGR_PhoneService", "transFileUniteDistribution entry. busiType is :", str, ParamConstants.Param.CALLBACK, iAppTransferFileResultAIDLCallback);
        jqj jqjVar = (jqj) new Gson().fromJson(str2, jqj.class);
        jqjVar.bIR_(parcelFileDescriptor);
        if (jqjVar != null) {
            LogUtil.a("DEVMGR_PhoneService", "startAppTransFile() transferFileInfo, fileName: ", jqjVar.j(), " fileType: ", Integer.valueOf(jqjVar.n()));
        }
        int i = AnonymousClass9.b[busiTypeByValue.ordinal()];
        if (i == 1) {
            jyx jyxVar = this.o;
            if (jyxVar == null) {
                LogUtil.h("DEVMGR_PhoneService", "transFileUniteDistribution, mCommonFileManager is null");
                return;
            } else {
                jyxVar.c(jqjVar, iAppTransferFileResultAIDLCallback);
                return;
            }
        }
        if (i != 2) {
            return;
        }
        jyp jypVar = this.l;
        if (jypVar == null) {
            LogUtil.h("DEVMGR_PhoneService", "startRequestFile, mCommonFileRequestManager is null");
        } else {
            jypVar.a(jqjVar, iAppTransferFileResultAIDLCallback);
        }
    }

    /* renamed from: com.huawei.hwservicesmgr.PhoneService$9, reason: invalid class name */
    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TransferBusinessType.values().length];
            b = iArr;
            try {
                iArr[TransferBusinessType.FIVE_FORTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                b[TransferBusinessType.FIVE_FORTY_FOUR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
