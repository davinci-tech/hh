package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.datatype.HeartRateInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.ICallbackInterface;
import com.huawei.health.IRemoteProxyCallback;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseAdviceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwLinkageServiceManager;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwWorkoutServiceManager;
import com.huawei.hwdevice.phoneprocess.mgr.heartrate.HwHeartRateManager;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwservicesmgr.IBaseCallback;
import com.huawei.hwservicesmgr.IHeartRateStateAIDLCallback;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class jsl {
    private static ICallbackInterface b;
    private static HwDeviceMgrInterface f;
    private static c g;
    private ExecutorService i;
    private BroadcastReceiver k;
    private List<Integer> l;
    private List<Integer> m;
    private Context n;
    private HiUnSubscribeListener o;
    private IRemoteProxyCallback q;
    private IBinder r;
    private HiSubscribeListener s;
    private ExecutorService t;
    private static final byte[] c = new byte[0];
    private static Map<String, ParserInterface> e = new HashMap(16);
    private static String j = null;
    private static boolean h = false;
    private static Gson d = new Gson();

    /* renamed from: a, reason: collision with root package name */
    private static IBaseResponseCallback f14049a = new IBaseResponseCallback() { // from class: jsl.5
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Map hashMap;
            if (obj instanceof Map) {
                hashMap = (Map) obj;
            } else {
                hashMap = new HashMap();
            }
            hashMap.put("code", Integer.valueOf(i));
            String obj2 = hashMap.get("funcName") != null ? hashMap.get("funcName").toString() : "";
            LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "retMap code:", Integer.valueOf(i), ",funcName:", obj2);
            if ("registerGetRTSportDataListCallbackList".equals(obj2)) {
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "workoutReportPlayData :", ((kon) moj.e(hashMap.get("value").toString(), kon.class)).s());
            }
            if ("notificationHeartRateInfo".equals(obj2)) {
                List b2 = moj.b(hashMap.get("value").toString(), HeartRateInfo[].class);
                if (!b2.isEmpty()) {
                    LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "heartRateInfo :", ((HeartRateInfo) b2.get(0)).toEncryptionDataString());
                }
            }
            try {
                if (jsl.b != null) {
                    jsl.b.onResponse(hashMap);
                } else {
                    LogUtil.b("HWhealthLinkage_RemoteServiceMgr", "sCallbackInterface is null, the AIDL communication is broken");
                }
            } catch (RemoteException e2) {
                LogUtil.b("HWhealthLinkage_RemoteServiceMgr", e2.getMessage());
                sqo.w("sCallbackInterface onResponse Exception " + e2.getMessage());
            }
        }
    };

    /* loaded from: classes5.dex */
    static class c extends Handler {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<jsl> f14052a;

        c(jsl jslVar) {
            this.f14052a = new WeakReference<>(jslVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.f14052a.get() == null) {
                return;
            }
            LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "handleMessage msg = ", Integer.valueOf(message.what));
            if (message.what != 1) {
                return;
            }
            try {
                if (jsl.b != null) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("state", "health_deviceDisconnected");
                    jsl.b.onResponse(kkm.d(jSONObject.toString(), "notificationStateConnectionStateChanged"));
                }
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "set sIsDisconnectedReported ======> true");
                boolean unused = jsl.h = true;
            } catch (RemoteException | JSONException e) {
                LogUtil.b("HWhealthLinkage_RemoteServiceMgr", e.getMessage());
                sqo.w("MSG_EXERCISEADVICE_BT_DISCONNECTED Exception : " + e.getMessage());
            }
        }
    }

    private jsl(Context context) {
        this.r = new Binder();
        this.i = Executors.newSingleThreadExecutor();
        this.m = new ArrayList();
        this.t = Executors.newFixedThreadPool(20);
        this.l = null;
        this.o = new HiUnSubscribeListener() { // from class: jsl.3
            @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
            public void onResult(boolean z) {
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "mHiUnSubscribeListener onResult result : ", Boolean.valueOf(z));
            }
        };
        this.q = new IRemoteProxyCallback.Stub() { // from class: jsl.2
            @Override // com.huawei.health.IRemoteProxyCallback
            public void requestWearTask(final Map map) throws RemoteException {
                if (map == null) {
                    LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "map is null");
                    sqo.w("map is null");
                    return;
                }
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "requestWearTask has been called the function name is:", map.get("funcName"), ",the parameters are:", map.get("parameters"));
                final String e2 = jsl.this.e(map.get("parameters"));
                final ParserInterface parserInterface = (ParserInterface) jsl.e.get(map.get("funcName"));
                if (parserInterface == null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("code", SmartMsgConstant.SHOW_METHOD_SMART_CARD);
                    hashMap.put("value", map.get("funcName") + " not found");
                    if (jsl.b != null) {
                        jsl.b.onResponse(hashMap);
                        return;
                    }
                    return;
                }
                if ("syncFitnessDetailData".equals(map.get("funcName"))) {
                    jsl.this.n.sendBroadcast(new Intent("com.huawei.bone.action.ACTION_HEALTH_MANUAL_DROP_DO_REFRESH"), LocalBroadcast.c);
                }
                jsl.this.t.execute(new Runnable() { // from class: jsl.2.5
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            String str = e2;
                            if (str != null && str.length() != 0) {
                                JSONObject jSONObject = new JSONObject(e2);
                                parserInterface.getClass().getMethod((String) map.get("funcName"), jSONObject.getClass(), IBaseResponseCallback.class).invoke(parserInterface, jSONObject, jsl.f14049a);
                                return;
                            }
                            Object obj = map.get("funcName");
                            if (!(obj instanceof String)) {
                                LogUtil.h("HWhealthLinkage_RemoteServiceMgr", "requestWearTask funcNameObject not instanceof String");
                                sqo.w("requestWearTask funcNameObject not instanceof String.");
                            } else {
                                Method method = parserInterface.getClass().getMethod((String) obj, IBaseResponseCallback.class);
                                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "the parser is :", parserInterface.getClass().getSimpleName(), ",the method is ", method.getName());
                                method.invoke(parserInterface, jsl.f14049a);
                            }
                        } catch (IllegalAccessException e3) {
                            e = e3;
                            LogUtil.b("HWhealthLinkage_RemoteServiceMgr", e.getMessage());
                            sqo.w("mRemoteProxyCallback Exception " + e.getMessage());
                        } catch (NoSuchMethodException e4) {
                            e = e4;
                            LogUtil.b("HWhealthLinkage_RemoteServiceMgr", e.getMessage());
                            sqo.w("mRemoteProxyCallback Exception " + e.getMessage());
                        } catch (InvocationTargetException e5) {
                            LogUtil.b("HWhealthLinkage_RemoteServiceMgr", "Parameter ERROR : ", e5.getTargetException().getMessage());
                            sqo.w("Parameter ERROR : " + e5.getMessage());
                        } catch (JSONException e6) {
                            e = e6;
                            LogUtil.b("HWhealthLinkage_RemoteServiceMgr", e.getMessage());
                            sqo.w("mRemoteProxyCallback Exception " + e.getMessage());
                        }
                    }
                });
            }
        };
        this.k = new BroadcastReceiver() { // from class: jsl.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (context2 == null || intent == null) {
                    return;
                }
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "mConnectStateChangedReceiver() action : ", intent.getAction());
                if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                    JSONObject jSONObject = new JSONObject();
                    DeviceInfo deviceInfo = (DeviceInfo) intent.getParcelableExtra("deviceinfo");
                    if (deviceInfo != null) {
                        LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "mConnectStateChangedReceiver() status : ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                        jsl.this.b(deviceInfo, jSONObject);
                    }
                }
            }
        };
        this.s = new HiSubscribeListener() { // from class: jsl.4
            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onResult(List<Integer> list, List<Integer> list2) {
                ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "regDeviceInfoConfigListener onResult");
                if (list != null && !list.isEmpty()) {
                    LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "regDeviceInfoConfigListener success");
                    jsl.this.l = list;
                }
                if (list2 != null) {
                    ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "failList size ", Integer.valueOf(list2.size()));
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
            public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j2) {
                LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "regDeviceInfoConfigListener success type : ", Integer.valueOf(i), ",changeType : ", str);
                Intent intent = new Intent();
                intent.setPackage(jsl.this.n.getPackageName());
                switch (i) {
                    case 100:
                        intent.setAction("com.huawei.bone.ACTION_RECEIVE_SEND_HEIGHT_WEIGHT");
                        jsl.this.n.sendBroadcast(intent, LocalBroadcast.c);
                        break;
                    case 101:
                        intent.setAction("com.huawei.bone.ACTION_RECEIVE_SEND_SPORT_GOAL");
                        intent.setPackage(jsl.this.n.getPackageName());
                        jsl.this.n.sendBroadcast(intent, LocalBroadcast.c);
                        break;
                    case 102:
                        if ("custom.UserPreference_HeartZone_Config".equals(str)) {
                            intent.setAction("com.huawei.bone.ACTION_RECEIVE_SEND_HEART_CONFIG");
                            jsl.this.n.sendBroadcast(intent, LocalBroadcast.c);
                            break;
                        }
                        break;
                    default:
                        ReleaseLogUtil.d("HWhealthLinkage_RemoteServiceMgr", "onChange Unknown type: ", Integer.valueOf(i));
                        break;
                }
            }
        };
        this.n = context;
        m();
        g = new c(d.c);
        f = jsz.b(BaseApplication.getContext());
        o();
        IntentFilter intentFilter = new IntentFilter("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("com.huawei.bone.action.PHONE_SERVICE_BIND_SUCCESS");
        intentFilter.addAction("com.huawei.plugin_device.am16_bind_success");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.k, intentFilter, LocalBroadcast.c, null);
    }

    public static jsl a() {
        return d.c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void f() {
        try {
            BaseApplication.getContext().unregisterReceiver(this.k);
        } catch (IllegalArgumentException e2) {
            LogUtil.b("HWhealthLinkage_RemoteServiceMgr", "destroy IllegalArgumentException");
            sqo.w("destroy IllegalArgumentException : " + e2.getMessage());
        }
        i();
        this.i.shutdown();
        this.t.shutdown();
        if (koq.c(this.l)) {
            HiHealthManager.d(this.n).unSubscribeHiHealthData(this.l, this.o);
            this.m = null;
            this.l = null;
        }
    }

    /* loaded from: classes5.dex */
    static class d {
        private static jsl c = new jsl(BaseApplication.getContext());
    }

    private static void c(String str) {
        j = str;
    }

    private static void i() {
        jsl unused = d.c = null;
    }

    private List<DeviceCapability> h() {
        ArrayList arrayList = new ArrayList();
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, "HWhealthLinkage_RemoteServiceMgr");
        if (deviceList != null && deviceList.size() > 0) {
            for (DeviceInfo deviceInfo : deviceList) {
                if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
                    Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, "", "HWhealthLinkage_RemoteServiceMgr");
                    if (!queryDeviceCapability.isEmpty() && queryDeviceCapability.containsKey(deviceInfo.getDeviceIdentify())) {
                        arrayList.add(queryDeviceCapability.get(deviceInfo.getDeviceIdentify()));
                    }
                }
            }
        }
        Map<String, DeviceCapability> queryDeviceCapability2 = f.queryDeviceCapability(5, "", "HWhealthLinkage_RemoteServiceMgr");
        for (String str : queryDeviceCapability2.keySet()) {
            if (str instanceof String) {
                arrayList.add(queryDeviceCapability2.get(str));
            }
        }
        return arrayList;
    }

    private void d(DeviceInfo deviceInfo) {
        c(deviceInfo.getSecurityUuid());
        this.i.execute(new Runnable() { // from class: jsl.10
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(1015), "key_device_last_device", jsl.j, (StorageParams) null);
            }
        });
    }

    public static void bJh_(Intent intent) {
        if (d.c != null) {
            d.c.bJj_(intent);
        }
    }

    private void bJj_(Intent intent) {
        if (intent == null || !intent.getBooleanExtra("isFromCallbackService", false)) {
            return;
        }
        LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "setIsFromUiThread enter");
    }

    private static void o() {
        HwWorkoutServiceManager hwWorkoutServiceManager = HwWorkoutServiceManager.getInstance();
        HwExerciseAdviceManager hwExerciseAdviceManager = HwExerciseAdviceManager.getInstance();
        HwHeartRateManager hwHeartRateManager = HwHeartRateManager.getInstance();
        HwLinkageServiceManager hwLinkageServiceManager = HwLinkageServiceManager.getInstance();
        Method[] declaredMethods = hwWorkoutServiceManager.getClass().getDeclaredMethods();
        Method[] declaredMethods2 = hwExerciseAdviceManager.getClass().getDeclaredMethods();
        Method[] declaredMethods3 = hwHeartRateManager.getClass().getDeclaredMethods();
        Method[] declaredMethods4 = hwLinkageServiceManager.getClass().getDeclaredMethods();
        for (Method method : declaredMethods) {
            e.put(method.getName(), hwWorkoutServiceManager);
        }
        for (Method method2 : declaredMethods2) {
            e.put(method2.getName(), hwExerciseAdviceManager);
        }
        for (Method method3 : declaredMethods3) {
            e.put(method3.getName(), hwHeartRateManager);
        }
        for (Method method4 : declaredMethods4) {
            e.put(method4.getName(), hwLinkageServiceManager);
        }
    }

    private void m() {
        HwHeartRateManager hwHeartRateManager = HwHeartRateManager.getInstance();
        hwHeartRateManager.registerNotificationHeartRateCallback(f14049a);
        hwHeartRateManager.registerNotificationStressCallback(f14049a);
        hwHeartRateManager.registerNotificationPressCallback(f14049a);
        HwExerciseAdviceManager.getInstance().registerNotificationRunPlanRecordInfoCallbackList(f14049a);
        HwWorkoutServiceManager.getInstance().registerNotificationSportReminderCallbackList(f14049a);
        HwLinkageServiceManager hwLinkageServiceManager = HwLinkageServiceManager.getInstance();
        hwLinkageServiceManager.registerNotificationStatusCallbackList(f14049a);
        hwLinkageServiceManager.registerNotificationGetWorkoutRecordStatisticCallbackList(f14049a);
        hwLinkageServiceManager.registerNotificationWorkoutRecordSpeechPlayCallbackList(f14049a);
        hwLinkageServiceManager.registerGetRealtimeSportDataListCallbackList(f14049a);
    }

    private void n() {
        k();
    }

    private void k() {
        LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "regDeviceInfoConfigListener");
        this.m.clear();
        this.m.add(102);
        this.m.add(100);
        this.m.add(101);
        HiHealthManager.d(this.n).subscribeHiHealthData(this.m, this.s);
    }

    private static List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo> g() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "HWhealthLinkage_RemoteServiceMgr");
        ArrayList arrayList = new ArrayList();
        arrayList.clear();
        if (deviceList == null) {
            return arrayList;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next()));
        }
        LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "return getUsedDeviceList() with deviceInfoListBak size : ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    private static com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo a(DeviceInfo deviceInfo) {
        com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo2 = new com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo();
        deviceInfo2.setDeviceActiveState(deviceInfo.getDeviceActiveState());
        deviceInfo2.setDeviceConnectState(deviceInfo.getDeviceConnectState());
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setProductType(c(deviceInfo));
        deviceInfo2.setDeviceIdentify(deviceInfo.getDeviceIdentify());
        if (TextUtils.isEmpty(deviceInfo.getUuid())) {
            deviceInfo2.setUuid(deviceInfo.getDeviceIdentify());
        } else {
            deviceInfo2.setUuid(deviceInfo.getUuid());
        }
        deviceInfo2.setDeviceProtocol(deviceInfo.getDeviceProtocol());
        deviceInfo2.setEncryptType(deviceInfo.getEncryptType());
        deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setDeviceIdType(deviceInfo.getDeviceIdType());
        return deviceInfo2;
    }

    private static int c(DeviceInfo deviceInfo) {
        int b2 = jrf.b(deviceInfo);
        if (b2 == 0 && deviceInfo.getProductType() == 11) {
            return 43;
        }
        return b2;
    }

    public void e(int i, int i2, final IHeartRateStateAIDLCallback iHeartRateStateAIDLCallback) throws RemoteException {
        iyv.i();
        HwHeartRateManager.getInstance().switchHeartRateDetection(i, i2, new IBaseResponseCallback() { // from class: jsl.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                try {
                    IHeartRateStateAIDLCallback iHeartRateStateAIDLCallback2 = iHeartRateStateAIDLCallback;
                    if (iHeartRateStateAIDLCallback2 != null) {
                        iHeartRateStateAIDLCallback2.heartRateResponse(i3);
                    }
                } catch (Exception e2) {
                    LogUtil.b("HWhealthLinkage_RemoteServiceMgr", "isLoudspeakerMuteOpenOrCloseHeartRate exception");
                    sqo.w("isLoudspeakerMuteOpenOrCloseHeartRate exception : " + e2.getMessage());
                }
            }
        });
    }

    public boolean j() {
        boolean z;
        synchronized (c) {
            z = b == null;
        }
        return z;
    }

    private static void bJi_(IBinder iBinder) {
        b = ICallbackInterface.Stub.asInterface(iBinder);
    }

    public void bJk_(IBinder iBinder, IBaseCallback iBaseCallback) {
        try {
            if (iBinder != null) {
                ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "initLinkageCallback begin");
                synchronized (c) {
                    ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "sCallbackInterface old is: ", b);
                    bJi_(iBinder);
                    ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "sCallbackInterface new is: ", b);
                    if (b != null) {
                        iBinder.linkToDeath(new IBinder.DeathRecipient() { // from class: jsl.8
                            @Override // android.os.IBinder.DeathRecipient
                            public void binderDied() {
                                jsl.this.l();
                            }
                        }, 0);
                        ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "initLinkageCallback callbackInterface start");
                        HwExerciseAdviceManager.getInstance().setCallbackInterface(b);
                        b.registerRemoteCallbackForHealth("com.huawei.health", this.q);
                        b.setDeviceCapabilityForHealth(d.toJson(h()));
                        b.setUsedDeviceListForHealth(g());
                        b.registerBinder(this.r, "PhoneServiceInHealth", "");
                        ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "initLinkageCallback callbackInterface done");
                    } else {
                        ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "sCallbackInterface is null");
                    }
                }
                n();
                return;
            }
            ReleaseLogUtil.c("HWhealthLinkage_RemoteServiceMgr", "remote binder is null");
            if (iBaseCallback != null) {
                l();
                iBaseCallback.onResponse(100007, "binder is null");
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_RemoteServiceMgr", "onServiceConnected Exception");
            sqo.w("onServiceConnected Exception : " + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        ReleaseLogUtil.d("HWhealthLinkage_RemoteServiceMgr", "handleBinderDied called");
        synchronized (c) {
            ICallbackInterface iCallbackInterface = b;
            if (iCallbackInterface != null) {
                try {
                    iCallbackInterface.unRegisterRemoteCallback(this.q);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c("HWhealthLinkage_RemoteServiceMgr", "handleBinderDied,", ExceptionUtils.d(e2));
                }
            }
            b = null;
        }
        if (this.l != null) {
            HiHealthManager.d(this.n).unSubscribeHiHealthData(this.l, this.o);
            this.l = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DeviceInfo deviceInfo, JSONObject jSONObject) {
        try {
            jSONObject.put("product_type", deviceInfo.getProductType());
            int deviceConnectState = deviceInfo.getDeviceConnectState();
            if (deviceConnectState == 2) {
                e(deviceInfo, jSONObject);
                return;
            }
            if (deviceConnectState != 3) {
                return;
            }
            if ("main_relationship".equals(deviceInfo.getRelationship()) && !cvt.c(deviceInfo.getProductType())) {
                if (b != null) {
                    jSONObject.put("state", "health_deviceInstantDisconnected");
                    b.setUsedDeviceListForHealth(g());
                    b.onResponse(kkm.d(jSONObject.toString(), "notificationStateConnectionStateChanged"));
                }
                ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "deviceInstantDisconnected sent");
                g.sendEmptyMessageDelayed(1, 300000L);
                return;
            }
            ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "checkDeviceCapability not is main relationship.");
        } catch (ClassCastException e2) {
            ReleaseLogUtil.c("HWhealthLinkage_RemoteServiceMgr", "mConnectStateChangedReceiver ClassCastException", ExceptionUtils.d(e2));
            sqo.w("mConnectStateChangedReceiver ClassCastException  " + e2.getMessage());
        } catch (Exception e3) {
            ReleaseLogUtil.c("HWhealthLinkage_RemoteServiceMgr", "mConnectStateChangedReceiver exception:", ExceptionUtils.d(e3));
            sqo.w("mConnectStateChangedReceiver exception  " + e3.getMessage());
        }
    }

    private void e(DeviceInfo deviceInfo, JSONObject jSONObject) throws JSONException, ClassCastException, RemoteException {
        g.removeMessages(1);
        ICallbackInterface iCallbackInterface = b;
        if (iCallbackInterface != null) {
            iCallbackInterface.setDeviceCapabilityForHealth(d.toJson(h()));
            b.setUsedDeviceListForHealth(g());
            jSONObject.put("state", "health_deviceInstantConnected");
            b.onResponse(kkm.d(jSONObject.toString(), "notificationStateConnectionStateChanged"));
            if (j == null) {
                j = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(1015), "key_device_last_device");
            }
            LogUtil.a("HWhealthLinkage_RemoteServiceMgr", "sLastKnownDeviceIdentify is ", iyl.d().e(j), "deviceInfo.getSecUUID() is ", iyl.d().e(deviceInfo.getSecurityUuid()));
            String str = j;
            if (str == null || !str.equalsIgnoreCase(deviceInfo.getSecurityUuid())) {
                jSONObject.put("state", "health_deviceDisconnected");
                ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "deviceDisconnected sent");
                b.onResponse(kkm.d(jSONObject.toString(), "notificationStateConnectionStateChanged"));
            } else if (!j.equals(deviceInfo.getSecurityUuid()) || h) {
                LogUtil.h("HWhealthLinkage_RemoteServiceMgr", "mConnectStateChangedReceiver onReceive lastKnownDeviceIdentify : ", j);
            } else {
                ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "set sIsDisconnectedReported false");
                h = false;
                return;
            }
            d(deviceInfo);
            jSONObject.put("state", "health_deviceConnected");
            ReleaseLogUtil.e("HWhealthLinkage_RemoteServiceMgr", "deviceConnected sent");
            b.onResponse(kkm.d(jSONObject.toString(), "notificationStateConnectionStateChanged"));
            return;
        }
        d(deviceInfo);
    }
}
