package com.huawei.health.hwhealthlinkage.wearsdk;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import androidx.core.app.NotificationManagerCompat;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.exercise.modle.RunPlanParameter;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.IBinderInterceptor;
import com.huawei.health.ICallbackInterface;
import com.huawei.health.IGetbindDeviceCommonCallback;
import com.huawei.health.IRemoteProxyCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.datatypes.HealthSupportModel;
import com.huawei.hwcommonmodel.datatypes.HuaweiHealthData;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import defpackage.cpa;
import defpackage.cvw;
import defpackage.duv;
import defpackage.ixp;
import defpackage.jdd;
import defpackage.jdh;
import defpackage.jdx;
import defpackage.moj;
import defpackage.sqo;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class CallbackService extends Service {
    private static final int GET_SUPPORT_DEVICE = 1;
    private static final int GET_SWITCH_STATE = 2;
    private static final String HEALTH = "health";
    private static final int NOTICE_ID = 20220531;
    public static final String PACKAGE_NAME_HUAWEI_WEAR = "com.huawei.bone";
    private static final int SEND_SUPPORT_DEVICE_TO_WEAR = 1000;
    private static final String TAG = "HWhealthLinkage_CallbackService";
    private static final String WEAR = "wear";
    private static String mCallingApp = "";
    private static boolean mIsHuaweiHealthConnected = false;
    private static boolean mIsHuaweiWearConnected = false;
    private JSONObject mDeviceCapability;
    private List<DeviceCapability> mDeviceCapabilityListForHealth;
    private JSONObject mOldDeviceCapabilityForHealth;
    private static final Object OBJECT_LOCK = new Object();
    private static final byte[] LOCK = new byte[1];
    private final IBinder mBinder = new MyBinder();
    private DeviceCapability mWearDeviceCapability = null;
    private DeviceCapability mOldhealthDeviceCapability = null;
    private List<DeviceInfo> mUsedDeviceList = new ArrayList(10);
    private List<DeviceInfo> mUsedDeviceListInHealth = new CopyOnWriteArrayList();
    private List<b> mClients = new ArrayList(10);
    private IBaseResponseCallback mRemoteServerListener = null;
    private Gson mGson = new Gson();
    private Map<String, a> mRemoteFunctionDataList = new HashMap(16);
    private Map<String, a> mNoDeviceRemoteFunctionDataList = new HashMap(16);
    private Map<String, IBaseResponseCallback> mOneTimeCallbackCache = Collections.synchronizedMap(new HashMap(16));
    private Map<String, List<IBaseResponseCallback>> mNotificationCallbackCache = new HashMap(16);
    private RemoteCallbackList<IRemoteProxyCallback> mCallbackList = new RemoteCallbackList<>();
    private RemoteCallbackList<IRemoteProxyCallback> mCallbackListForHealth = new RemoteCallbackList<>();
    private Map<String, Integer> mRunPlanMap = new HashMap(16);
    private int mCount = 0;
    private IBinderInterceptor.Stub binderIntercepter = new e(this);
    private ICallbackInterface.Stub iCallback = new ICallbackInterface.Stub() { // from class: com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.2
        @Override // com.huawei.health.ICallbackInterface
        public void registerRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
            ReleaseLogUtil.e(CallbackService.TAG, "registerRemoteCallback called mCallingApp:", CallbackService.mCallingApp, "is connect PhoneService", Boolean.valueOf(CallbackService.isConnectedWithRemote()));
            if ("com.huawei.bone".equals(CallbackService.mCallingApp)) {
                boolean unused = CallbackService.mIsHuaweiWearConnected = true;
                ReleaseLogUtil.e(CallbackService.TAG, "connection to wear isSussess:", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_CONNECTION_TO_WEAR", "0", (StorageParams) null)));
                if (CommonUtil.al(BaseApplication.getContext())) {
                    CallbackService.this.sendSupportDeviceToWear();
                }
                CallbackService.this.mCallbackList.register(iRemoteProxyCallback);
            }
            a();
        }

        @Override // com.huawei.health.ICallbackInterface
        public void registerRemoteCallbackForHealth(String str, IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
            ReleaseLogUtil.e(CallbackService.TAG, "registerRemoteCallback called packageName:", str);
            if ("com.huawei.health".equals(str) || BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(str)) {
                boolean unused = CallbackService.mIsHuaweiHealthConnected = true;
                CallbackService.this.mCallbackListForHealth.register(iRemoteProxyCallback);
            }
            a();
        }

        private void a() {
            synchronized (CallbackService.LOCK) {
                Iterator it = CallbackService.this.mNoDeviceRemoteFunctionDataList.entrySet().iterator();
                while (it.hasNext()) {
                    a aVar = (a) ((Map.Entry) it.next()).getValue();
                    ReleaseLogUtil.e(CallbackService.TAG, "registerRemoteCallback remoteFunctionData funcName = ", aVar.d());
                    CallbackService.this.requestWearTask(aVar.d(), aVar.a(), aVar.c());
                }
                CallbackService.this.mNoDeviceRemoteFunctionDataList.clear();
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void unRegisterRemoteCallback(IRemoteProxyCallback iRemoteProxyCallback) throws RemoteException {
            ReleaseLogUtil.e(CallbackService.TAG, "unRegisterRemoteCallback:", iRemoteProxyCallback);
            try {
                CallbackService.this.mCallbackList.unregister(iRemoteProxyCallback);
            } catch (RuntimeException e2) {
                ReleaseLogUtil.e(CallbackService.TAG, "mCallbackList RuntimeException:", e2.getMessage());
                sqo.w("mCallbackList RuntimeException:" + e2.getMessage());
            }
            try {
                CallbackService.this.mCallbackListForHealth.unregister(iRemoteProxyCallback);
            } catch (RuntimeException e3) {
                ReleaseLogUtil.e(CallbackService.TAG, "mCallbackListForHealth RuntimeException:", e3.getMessage());
                sqo.w("mCallbackListForHealth RuntimeException:" + e3.getMessage());
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public boolean onResponse(Map map) throws RemoteException {
            IBaseResponseCallback iBaseResponseCallback;
            if (map != null) {
                LogUtil.c(CallbackService.TAG, "onResponse called!!", map.get("funcName"));
                synchronized (CallbackService.LOCK) {
                    iBaseResponseCallback = (IBaseResponseCallback) CallbackService.this.mOneTimeCallbackCache.remove(map.get("funcName"));
                }
                Object obj = map.get("code");
                if (iBaseResponseCallback == null) {
                    LogUtil.a(CallbackService.TAG, "oneTime callback iBaseResponseCallback is null");
                    if (obj == null) {
                        return true;
                    }
                    List list = (List) CallbackService.this.mNotificationCallbackCache.get(map.get("funcName"));
                    CallbackService.this.printRealTimeSportData(map);
                    if (list != null) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            try {
                                ((IBaseResponseCallback) it.next()).d(Integer.parseInt(obj.toString()), map.get("value"));
                            } catch (NumberFormatException e2) {
                                ReleaseLogUtil.e(CallbackService.TAG, "onResponse NumberFormatException");
                                sqo.w("onResponse NumberFormatException:" + e2.getMessage());
                            }
                        }
                    }
                } else {
                    try {
                        if ("getRunPlanParameter".equals(map.get("funcName"))) {
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse value", String.valueOf(map.get("value")));
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse code", String.valueOf(obj));
                            RunPlanParameter runPlanParameter = (RunPlanParameter) moj.e((String) map.get("value"), RunPlanParameter.class);
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse getRun_plan_sync_size_sub:", Integer.valueOf(runPlanParameter.getRun_plan_sync_size_sub()));
                            CallbackService.this.mRunPlanMap.put("wear", Integer.valueOf(runPlanParameter.getRun_plan_sync_size_sub() + runPlanParameter.getRun_plan_sync_size_sub()));
                        }
                        if ("getRunPlanParameterforhealth".equals(map.get("funcName"))) {
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse health value", String.valueOf(map.get("value")));
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse health code", String.valueOf(obj));
                            RunPlanParameter runPlanParameter2 = (RunPlanParameter) moj.e((String) map.get("value"), RunPlanParameter.class);
                            LogUtil.a(CallbackService.TAG, "runplanlog onResponse health getRun_plan_sync_size_sub", Integer.valueOf(runPlanParameter2.getRun_plan_sync_size_sub()));
                            CallbackService.this.mRunPlanMap.put(CallbackService.HEALTH, Integer.valueOf(runPlanParameter2.getRun_plan_sync_size_sub() + runPlanParameter2.getRun_plan_sync_size_sub()));
                        }
                    } catch (JsonSyntaxException e3) {
                        ReleaseLogUtil.e(CallbackService.TAG, "onResponse JsonSyntaxException:", e3.getMessage());
                        sqo.w("onResponse JsonSyntaxException:" + e3.getMessage());
                    }
                    if (obj == null) {
                        return true;
                    }
                    try {
                        iBaseResponseCallback.d(Integer.parseInt(obj.toString()), map.get("value"));
                    } catch (NumberFormatException e4) {
                        ReleaseLogUtil.e(CallbackService.TAG, "onResponse NumberFormatException");
                        sqo.w("onResponse NumberFormatException:" + e4.getMessage());
                    }
                }
            } else {
                ReleaseLogUtil.e(CallbackService.TAG, "obj is null");
            }
            return true;
        }

        @Override // com.huawei.health.ICallbackInterface
        public void registerBinder(IBinder iBinder, String str, String str2) throws RemoteException {
            LogUtil.c(CallbackService.TAG, "registerBinder called ");
            if (iBinder == null) {
                ReleaseLogUtil.e(CallbackService.TAG, "registerBinder token is null");
                return;
            }
            b bVar = CallbackService.this.new b(iBinder, str, str2);
            iBinder.linkToDeath(bVar, 0);
            CallbackService.this.mClients.add(bVar);
            ReleaseLogUtil.e(CallbackService.TAG, "mRemoteServerListener is called!!!!");
            if (CallbackService.this.mRemoteServerListener != null) {
                CallbackService.this.mRemoteServerListener.d(100000, "success");
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void setUsedDeviceList(List<DeviceInfo> list) throws RemoteException {
            if (!CallbackService.this.isSameDevice(list)) {
                ReleaseLogUtil.e(CallbackService.TAG, "setUsedDeviceList the different device");
                synchronized (CallbackService.LOCK) {
                    CallbackService.this.mRemoteFunctionDataList.clear();
                }
            }
            CallbackService.this.mUsedDeviceList = list;
            if (CallbackService.this.mUsedDeviceList != null) {
                for (DeviceInfo deviceInfo : CallbackService.this.mUsedDeviceList) {
                    LogUtil.a(CallbackService.TAG, "setUsedDeviceList remoteFunctionData active = ", Integer.valueOf(deviceInfo.getDeviceActiveState()), "connectstats  ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    ReleaseLogUtil.e(CallbackService.TAG, "setUsedDeviceList remoteFunctionData deviceInfo = ", deviceInfo.toString());
                    if (1 == deviceInfo.getDeviceActiveState() && 2 == deviceInfo.getDeviceConnectState()) {
                        synchronized (CallbackService.LOCK) {
                            Iterator it = CallbackService.this.mRemoteFunctionDataList.entrySet().iterator();
                            while (it.hasNext()) {
                                a aVar = (a) ((Map.Entry) it.next()).getValue();
                                ReleaseLogUtil.e(CallbackService.TAG, "setUsedDeviceList remoteFunctionData funcName = ", aVar.d(), "callback ", aVar.c());
                                CallbackService.this.requestWearTask(aVar.d(), aVar.a(), aVar.c());
                            }
                            CallbackService.this.mRemoteFunctionDataList.clear();
                        }
                    }
                }
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void setDeviceCapability(String str) throws RemoteException {
            try {
                ReleaseLogUtil.e(CallbackService.TAG, "calling deviceCapability ：", str);
                CallbackService.this.mDeviceCapability = new JSONObject(str);
            } catch (JSONException e2) {
                ReleaseLogUtil.c(CallbackService.TAG, LogAnonymous.b((Throwable) e2));
                sqo.w("setDeviceCapability JSONException:" + e2.getMessage());
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void unbindAllDevice(IBaseCommonCallback iBaseCommonCallback) {
            ReleaseLogUtil.e(CallbackService.TAG, "calling unbindAllDevice......");
            duv.e().b();
        }

        @Override // com.huawei.health.ICallbackInterface
        public void getDeviceList(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) {
            ReleaseLogUtil.e(CallbackService.TAG, "calling getDeviceList......");
            if (iGetbindDeviceCommonCallback != null) {
                try {
                    iGetbindDeviceCommonCallback.onResponse(0, 0);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.e(CallbackService.TAG, "RemoteException:", e2.getMessage());
                    sqo.w("getDeviceList RemoteException:" + e2.getMessage());
                }
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void isHealthSupportWearDevice(IBaseCommonCallback iBaseCommonCallback) {
            ReleaseLogUtil.e(CallbackService.TAG, "calling isHealthSupportWearDevice......");
            if (iBaseCommonCallback != null) {
                try {
                    iBaseCommonCallback.onResponse(0, "false");
                } catch (RemoteException e2) {
                    ReleaseLogUtil.e(CallbackService.TAG, "RemoteException:", e2.getMessage());
                    sqo.w("call isHealthSupportWearDevice RemoteException:" + e2.getMessage());
                }
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void sendDataToHealth(String str, IBaseCommonCallback iBaseCommonCallback) {
            ReleaseLogUtil.e(CallbackService.TAG, "calling sendDataToHealth...... data", str);
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.homewear21.intentservice.MessageCenterIntentService"));
                intent.putExtra("data", str);
                BaseApplication.getContext().startService(intent);
                if (iBaseCommonCallback != null) {
                    iBaseCommonCallback.onResponse(0, "success");
                }
            } catch (RemoteException e2) {
                ReleaseLogUtil.e(CallbackService.TAG, "RemoteException:", e2.getMessage());
                sqo.w("call sendDataToHealth RemoteException:" + e2.getMessage());
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void getDeviceListSize(IGetbindDeviceCommonCallback iGetbindDeviceCommonCallback) {
            LogUtil.a(CallbackService.TAG, "calling getDeviceListSize......");
            int m = CommonUtil.m(BaseApplication.getContext(), cvw.e());
            ReleaseLogUtil.e(CallbackService.TAG, "calling getDeviceList size:", Integer.valueOf(m));
            if (iGetbindDeviceCommonCallback != null) {
                try {
                    iGetbindDeviceCommonCallback.onResponse(0, m);
                } catch (RemoteException e2) {
                    ReleaseLogUtil.e(CallbackService.TAG, "RemoteException:", e2.getMessage());
                    sqo.w("call getDeviceListSize RemoteException:" + e2.getMessage());
                }
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void getCommonData(int i, IBaseCommonCallback iBaseCommonCallback) {
            ReleaseLogUtil.e(CallbackService.TAG, "calling getCommonData commandType:", Integer.valueOf(i));
            try {
                if (1 == i) {
                    HealthSupportModel c = cvw.c();
                    if (iBaseCommonCallback != null) {
                        iBaseCommonCallback.onResponse(0, CallbackService.this.mGson.toJson(c));
                    }
                } else if (i != 2 || iBaseCommonCallback == null) {
                } else {
                    iBaseCommonCallback.onResponse(0, "false");
                }
            } catch (RemoteException e2) {
                ReleaseLogUtil.e(CallbackService.TAG, "RemoteException:", e2.getMessage());
                sqo.w("call getCommonData RemoteException:" + e2.getMessage());
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void setUsedDeviceListForHealth(List<DeviceInfo> list) throws RemoteException {
            if (!CallbackService.this.isSameDeviceInHealth(list)) {
                LogUtil.a(CallbackService.TAG, "setUsedDeviceList the different device");
                synchronized (CallbackService.LOCK) {
                    CallbackService.this.mRemoteFunctionDataList.clear();
                }
            }
            CallbackService.this.mUsedDeviceListInHealth = list;
            if (CallbackService.this.mUsedDeviceListInHealth != null) {
                for (DeviceInfo deviceInfo : CallbackService.this.mUsedDeviceListInHealth) {
                    ReleaseLogUtil.e(CallbackService.TAG, "setUsedDeviceListForHealth remoteFunctionData active = ", Integer.valueOf(deviceInfo.getDeviceActiveState()), "connectstats  ", Integer.valueOf(deviceInfo.getDeviceConnectState()));
                    LogUtil.a(CallbackService.TAG, "setUsedDeviceListForHealth remoteFunctionData deviceInfo = ", deviceInfo.toString());
                    if (1 == deviceInfo.getDeviceActiveState() && 2 == deviceInfo.getDeviceConnectState()) {
                        synchronized (CallbackService.LOCK) {
                            Iterator it = CallbackService.this.mRemoteFunctionDataList.entrySet().iterator();
                            while (it.hasNext()) {
                                a aVar = (a) ((Map.Entry) it.next()).getValue();
                                LogUtil.a(CallbackService.TAG, "setUsedDeviceListForHealth remoteFunctionData funcName = ", aVar.d() + "callback ", aVar.c());
                                CallbackService.this.requestWearTask(aVar.d(), aVar.a(), aVar.c());
                            }
                            CallbackService.this.mRemoteFunctionDataList.clear();
                        }
                    }
                }
            }
        }

        @Override // com.huawei.health.ICallbackInterface
        public void setDeviceCapabilityForHealth(String str) throws RemoteException {
            ReleaseLogUtil.e(CallbackService.TAG, "calling setDeviceCapabilityForHealth ：", str);
            CallbackService.this.mDeviceCapabilityListForHealth = (List) new Gson().fromJson(CommonUtil.z(str), new TypeToken<List<DeviceCapability>>() { // from class: com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.2.2
            }.getType());
        }
    };

    public static boolean isConnectedWithRemote() {
        LogUtil.c(TAG, "mIsHuaweiWearConnected:", Boolean.valueOf(mIsHuaweiWearConnected), " mIsHuaweiHealthConnected:", Boolean.valueOf(mIsHuaweiHealthConnected));
        return mIsHuaweiHealthConnected || mIsHuaweiWearConnected;
    }

    static class e extends IBinderInterceptor.Stub {
        private CallbackService b;

        public e(CallbackService callbackService) {
            this.b = callbackService;
        }

        @Override // com.huawei.health.IBinderInterceptor
        public IBinder getServiceBinder(String str) {
            synchronized (CallbackService.OBJECT_LOCK) {
                String unused = CallbackService.mCallingApp = BaseApplication.getContext().getPackageManager().getNameForUid(Binder.getCallingUid());
                ReleaseLogUtil.e(CallbackService.TAG, "mCallingApp is:", CallbackService.mCallingApp, "packageName:", str);
                if (ixp.e(BaseApplication.getContext(), str)) {
                    return this.b.iCallback;
                }
                if (!BaseApplication.getContext().getPackageName().equals(CallbackService.mCallingApp) && !BaseApplication.APP_PACKAGE_GOOGLE_HEALTH.equals(CallbackService.mCallingApp)) {
                    return null;
                }
                return this.b.iCallback;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendSupportDeviceToWear() {
        ReleaseLogUtil.e(TAG, "Enter sendSupportDeviceToWear");
        HuaweiHealthData huaweiHealthData = new HuaweiHealthData();
        huaweiHealthData.setCommandType(1000);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(BaseApplication.getAppPackage(), "com.huawei.ui.homewear21.intentservice.MessageCenterIntentService"));
        intent.putExtra("data", this.mGson.toJson(huaweiHealthData));
        BaseApplication.getContext().startService(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void printRealTimeSportData(Map map) {
        Object obj;
        if (!"registerGetRTSportDataListCallbackList".equals(map.get("funcName") instanceof String ? (String) map.get("funcName") : "") || (obj = map.get("value")) == null) {
            return;
        }
        try {
            LogUtil.a(TAG, "printRealTimeSportData hr:", CommonUtil.n(new JSONObject(obj.toString()).get("hr").toString()));
        } catch (JSONException e2) {
            ReleaseLogUtil.c(TAG, "printRealTimeSportData JSONException");
            sqo.w("printRealTimeSportData JSONException:" + e2.getMessage());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        this.mCount++;
        ReleaseLogUtil.e(TAG, "onBind from_flag ", intent.getStringExtra("from_flag"), " count:", Integer.valueOf(this.mCount));
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callbackServiceError", "onBind from_flag " + intent.getStringExtra("from_flag") + " count:" + this.mCount);
        if (intent != null && "local.proxy".equals(intent.getAction())) {
            return this.mBinder;
        }
        return this.binderIntercepter;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        this.mCount--;
        ReleaseLogUtil.e(TAG, "onUnbind from_flag ", intent.getStringExtra("from_flag"), " count:", Integer.valueOf(this.mCount));
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callbackServiceError", "onUnbind from_flag " + intent.getStringExtra("from_flag") + " count:" + this.mCount);
        return super.onUnbind(intent);
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        ReleaseLogUtil.e(TAG, "checkIsWearAPPInstalled is true");
        try {
            if (isStartForegroundNotification()) {
                startForeground(NOTICE_ID, jdh.c().xf_().build());
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c(TAG, "startForeground Exception ", ExceptionUtils.d(e2));
            sqo.w("startForeground NOTICE_ID Exception:" + e2.getMessage());
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_CONNECTION_TO_WEAR", "1", (StorageParams) null);
        startRemoteService();
    }

    private boolean isStartForegroundNotification() {
        if (CommonUtil.bh() || NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            return true;
        }
        LogUtil.h(TAG, "notifyMsg notification bar permission is not enabled.");
        return false;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        stopSelf(i2);
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        ReleaseLogUtil.e(TAG, "onDestroy count:", Integer.valueOf(this.mCount));
        OpAnalyticsUtil.getInstance().setProbabilityProblemEvent("callbackServiceError", "onDestroy count:" + this.mCount);
        super.onDestroy();
    }

    public boolean isWearDeviceSupportHeart() {
        JSONObject jSONObject = this.mDeviceCapability;
        if (jSONObject != null) {
            try {
                this.mWearDeviceCapability = (DeviceCapability) this.mGson.fromJson(jSONObject.toString(), DeviceCapability.class);
            } catch (JsonSyntaxException e2) {
                ReleaseLogUtil.e(TAG, "mWearDeviceCapability JsonSyntaxException:", e2.getMessage());
                sqo.w("mWearDeviceCapability JsonSyntaxException:" + e2.getMessage());
                this.mWearDeviceCapability = null;
            }
        }
        DeviceCapability deviceCapability = this.mWearDeviceCapability;
        boolean isSupportHeartRateInfo = deviceCapability != null ? deviceCapability.isSupportHeartRateInfo() : false;
        ReleaseLogUtil.e(TAG, "isWearDeviceSupportHeart :", Boolean.valueOf(isSupportHeartRateInfo));
        return isSupportHeartRateInfo;
    }

    public boolean isHealthDeviceSupportHeart() {
        List<DeviceCapability> list = this.mDeviceCapabilityListForHealth;
        if (list != null) {
            for (DeviceCapability deviceCapability : list) {
                if (deviceCapability != null && deviceCapability.isSupportHeartRateInfo()) {
                    LogUtil.a(TAG, "isHealthDeviceSupportHeart HAS device support heart rate");
                    return true;
                }
            }
        }
        ReleaseLogUtil.e(TAG, "isHealthDeviceSupportHeart no device support heart rate");
        return false;
    }

    public boolean isHealthDeviceSupportStress() {
        List<DeviceCapability> list = this.mDeviceCapabilityListForHealth;
        if (list != null) {
            for (DeviceCapability deviceCapability : list) {
                if (deviceCapability != null && deviceCapability.isSupportStressInfo()) {
                    LogUtil.a(TAG, "isHealthDeviceSupportHeart no device support StressInfo");
                    return true;
                }
            }
        }
        ReleaseLogUtil.e(TAG, "isHealthDeviceSupportHeart no device support heart rate");
        return false;
    }

    public class MyBinder extends Binder {
        public MyBinder() {
        }

        public CallbackService getCallbackService() {
            ReleaseLogUtil.e(CallbackService.TAG, "CallbackService.this is", CallbackService.this);
            return CallbackService.this;
        }

        public List<DeviceInfo> getUsedDeviceList() {
            if (CallbackService.this.mUsedDeviceList == null) {
                if (CallbackService.this.mUsedDeviceListInHealth == null) {
                    return null;
                }
                ArrayList arrayList = new ArrayList(10);
                arrayList.addAll(CallbackService.this.mUsedDeviceListInHealth);
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(10);
            if (CallbackService.this.mUsedDeviceListInHealth != null) {
                arrayList2.addAll(CallbackService.this.mUsedDeviceListInHealth);
            }
            arrayList2.addAll(CallbackService.this.mUsedDeviceList);
            return arrayList2;
        }

        public List<DeviceInfo> getUsedDeviceListInHealth() {
            if (CallbackService.this.mUsedDeviceListInHealth == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(10);
            arrayList.addAll(CallbackService.this.mUsedDeviceListInHealth);
            return arrayList;
        }

        public List<DeviceInfo> getUsedDeviceListInWear() {
            if (CallbackService.this.mUsedDeviceList == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(10);
            arrayList.addAll(CallbackService.this.mUsedDeviceList);
            return arrayList;
        }

        public JSONObject getDeviceCapability() {
            JSONObject jSONObject;
            ReleaseLogUtil.e(CallbackService.TAG, "Enter getDeviceCapability");
            if (CallbackService.this.mDeviceCapabilityListForHealth != null) {
                LogUtil.a(CallbackService.TAG, "getDeviceCapability null != mDeviceCapabilityListForHealth");
                if (CallbackService.this.mDeviceCapabilityListForHealth.size() > 1) {
                    ReleaseLogUtil.e(CallbackService.TAG, "getDeviceCapability has two devices");
                    Iterator it = CallbackService.this.mDeviceCapabilityListForHealth.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        DeviceCapability deviceCapability = (DeviceCapability) it.next();
                        if (!deviceCapability.isSupportRunPosture()) {
                            LogUtil.a(CallbackService.TAG, "getDeviceCapability has extra device");
                            try {
                                CallbackService.this.mOldhealthDeviceCapability = deviceCapability;
                                CallbackService.this.mOldDeviceCapabilityForHealth = new JSONObject(CallbackService.this.mGson.toJson(deviceCapability, DeviceCapability.class));
                                break;
                            } catch (Exception e) {
                                ReleaseLogUtil.e(CallbackService.TAG, "getDeviceCapability Exception");
                                sqo.w("getDeviceCapability Exception:" + e.getMessage());
                            }
                        }
                    }
                } else {
                    ReleaseLogUtil.e(CallbackService.TAG, "getDeviceCapability has one devices");
                    try {
                        CallbackService callbackService = CallbackService.this;
                        callbackService.mOldhealthDeviceCapability = (DeviceCapability) callbackService.mDeviceCapabilityListForHealth.get(0);
                        CallbackService.this.mOldDeviceCapabilityForHealth = new JSONObject(CallbackService.this.mGson.toJson(CallbackService.this.mDeviceCapabilityListForHealth.get(0), DeviceCapability.class));
                    } catch (Exception e2) {
                        ReleaseLogUtil.e(CallbackService.TAG, "getDeviceCapability Exception");
                        if (CommonUtil.as()) {
                            sqo.w("getDeviceCapability Exception :" + e2.getMessage());
                        }
                    }
                }
            }
            if (CallbackService.this.mDeviceCapability != null) {
                LogUtil.a(CallbackService.TAG, "getDeviceCapability null != mDeviceCapability");
                try {
                    CallbackService callbackService2 = CallbackService.this;
                    callbackService2.mWearDeviceCapability = (DeviceCapability) callbackService2.mGson.fromJson(CallbackService.this.mDeviceCapability.toString(), DeviceCapability.class);
                } catch (JsonSyntaxException e3) {
                    ReleaseLogUtil.e(CallbackService.TAG, "mWearDeviceCapability JsonSyntaxException:", e3.getMessage());
                    sqo.w("mWearDeviceCapability JsonSyntaxException:" + e3.getMessage());
                    CallbackService.this.mWearDeviceCapability = null;
                }
            }
            if (CallbackService.this.mWearDeviceCapability == null || CallbackService.this.mOldDeviceCapabilityForHealth == null) {
                jSONObject = CallbackService.this.mOldDeviceCapabilityForHealth != null ? CallbackService.this.mOldDeviceCapabilityForHealth : CallbackService.this.mDeviceCapability;
            } else {
                jSONObject = a();
            }
            ReleaseLogUtil.e(CallbackService.TAG, "CallbackService getDeviceCapability");
            return jSONObject;
        }

        private JSONObject a() {
            DeviceCapability deviceCapability = new DeviceCapability();
            deviceCapability.configureSupportExerciseAdvice(CallbackService.this.mWearDeviceCapability.isSupportExerciseAdvice() || CallbackService.this.mOldhealthDeviceCapability.isSupportExerciseAdvice());
            deviceCapability.configureSupportWorkoutExerciseDisplayLink(CallbackService.this.mWearDeviceCapability.isSupportWorkoutExerciseDisplayLink() || CallbackService.this.mOldhealthDeviceCapability.isSupportWorkoutExerciseDisplayLink());
            deviceCapability.configureSupportHeartRateInfo(CallbackService.this.mWearDeviceCapability.isSupportHeartRateInfo() || CallbackService.this.mOldhealthDeviceCapability.isSupportHeartRateInfo());
            deviceCapability.configureSupportStressInfo(CallbackService.this.mWearDeviceCapability.isSupportStressInfo() || CallbackService.this.mOldhealthDeviceCapability.isSupportStressInfo());
            deviceCapability.configureSupportWorkoutExerciseDisplayLink(CallbackService.this.mWearDeviceCapability.isSupportWorkoutExerciseDisplayLink() || CallbackService.this.mOldhealthDeviceCapability.isSupportWorkoutExerciseDisplayLink());
            deviceCapability.configureSupportAutoDetectMode(CallbackService.this.mWearDeviceCapability.isSupportAutoDetectMode() || CallbackService.this.mOldhealthDeviceCapability.isSupportAutoDetectMode());
            deviceCapability.configureSupportRunPosture(CallbackService.this.mWearDeviceCapability.isSupportRunPosture() || CallbackService.this.mOldhealthDeviceCapability.isSupportRunPosture());
            deviceCapability.configureSupportFootWear(CallbackService.this.mWearDeviceCapability.isSupportFootWear() || CallbackService.this.mOldhealthDeviceCapability.isSupportFootWear());
            deviceCapability.configureSupportInformCloseOrOpen(CallbackService.this.mWearDeviceCapability.isSupportInformCloseOrOpen() || CallbackService.this.mOldhealthDeviceCapability.isSupportInformCloseOrOpen());
            ReleaseLogUtil.e(CallbackService.TAG, "mOldhealthDeviceCapability 5.23.18", Boolean.valueOf(CallbackService.this.mWearDeviceCapability.isSupportInformCloseOrOpen()), "", Boolean.valueOf(CallbackService.this.mOldhealthDeviceCapability.isSupportInformCloseOrOpen()));
            try {
                ReleaseLogUtil.e(CallbackService.TAG, "mOldhealthDeviceCapability json string:", CallbackService.this.mGson.toJson(deviceCapability));
                JSONObject jSONObject = new JSONObject(CallbackService.this.mGson.toJson(deviceCapability));
                LogUtil.a(CallbackService.TAG, "result = ", jSONObject);
                return jSONObject;
            } catch (Exception e) {
                LogUtil.a(CallbackService.TAG, "mWearDeviceCapability JsonSyntaxException");
                sqo.w("mWearDeviceCapability JsonSyntaxException :" + e.getMessage());
                JSONObject jSONObject2 = CallbackService.this.mDeviceCapability;
                ReleaseLogUtil.e(CallbackService.TAG, "result = ", jSONObject2);
                return jSONObject2;
            }
        }

        public DeviceCapability getWearDeviceCapability() {
            if (CallbackService.this.mDeviceCapability != null) {
                try {
                    CallbackService callbackService = CallbackService.this;
                    callbackService.mWearDeviceCapability = (DeviceCapability) callbackService.mGson.fromJson(CallbackService.this.mDeviceCapability.toString(), DeviceCapability.class);
                } catch (JsonSyntaxException e) {
                    ReleaseLogUtil.e(CallbackService.TAG, "mWearDeviceCapability JsonSyntaxException:", e.getMessage());
                    sqo.w("mWearDeviceCapability JsonSyntaxException : " + e.getMessage());
                    CallbackService.this.mWearDeviceCapability = null;
                }
            }
            return CallbackService.this.mWearDeviceCapability;
        }

        public List<DeviceCapability> getHealthDeviceCapability() {
            return CallbackService.this.mDeviceCapabilityListForHealth;
        }

        public void setRemoteServerListener(IBaseResponseCallback iBaseResponseCallback) {
            CallbackService.this.mRemoteServerListener = iBaseResponseCallback;
        }

        public List<DeviceInfo> getWearDeviceList() {
            return CallbackService.this.mUsedDeviceList;
        }

        public void removeFunctionCallback(String str) {
            LogUtil.a(CallbackService.TAG, "Enter removeFunctionCallback()");
            if (CallbackService.this.mOneTimeCallbackCache.containsKey(str)) {
                ReleaseLogUtil.e(CallbackService.TAG, "Enter removeFunctionCallback() contains ", str);
                CallbackService.this.mOneTimeCallbackCache.remove(str);
            } else {
                ReleaseLogUtil.e(CallbackService.TAG, "Enter removeFunctionCallback() not contains ", str);
            }
            if (CallbackService.this.mRemoteFunctionDataList.containsKey(str)) {
                ReleaseLogUtil.e(CallbackService.TAG, "Enter removeFunctionCallback() mRemoteFunctionDataList contains ", str);
                CallbackService.this.mRemoteFunctionDataList.remove(str);
            } else {
                ReleaseLogUtil.e(CallbackService.TAG, "Enter removeFunctionCallback() mRemoteFunctionDataList not contains ", str);
            }
        }
    }

    static class a {
        private String b;
        private IBaseResponseCallback c;
        private String e;

        public a(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
            this.e = str;
            this.b = str2;
            this.c = iBaseResponseCallback;
        }

        public String d() {
            return this.e;
        }

        public String a() {
            return this.b;
        }

        public IBaseResponseCallback c() {
            return this.c;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c3 A[Catch: all -> 0x0193, TryCatch #1 {, blocks: (B:5:0x0004, B:7:0x0017, B:8:0x0027, B:12:0x002b, B:14:0x0033, B:16:0x0037, B:17:0x003e, B:19:0x0042, B:20:0x004a, B:21:0x004f, B:26:0x005b, B:28:0x00c3, B:29:0x00c8, B:31:0x00ce, B:33:0x011c, B:35:0x0128, B:36:0x014b, B:38:0x014d, B:40:0x0153, B:41:0x0166, B:43:0x016c, B:45:0x017a, B:46:0x0181, B:52:0x0072, B:49:0x009a, B:53:0x018e, B:54:0x0191), top: B:4:0x0004, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00ce A[Catch: all -> 0x0193, TryCatch #1 {, blocks: (B:5:0x0004, B:7:0x0017, B:8:0x0027, B:12:0x002b, B:14:0x0033, B:16:0x0037, B:17:0x003e, B:19:0x0042, B:20:0x004a, B:21:0x004f, B:26:0x005b, B:28:0x00c3, B:29:0x00c8, B:31:0x00ce, B:33:0x011c, B:35:0x0128, B:36:0x014b, B:38:0x014d, B:40:0x0153, B:41:0x0166, B:43:0x016c, B:45:0x017a, B:46:0x0181, B:52:0x0072, B:49:0x009a, B:53:0x018e, B:54:0x0191), top: B:4:0x0004, inners: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void requestWearTask(java.lang.String r9, java.lang.String r10, com.huawei.hwbasemgr.IBaseResponseCallback r11) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.requestWearTask(java.lang.String, java.lang.String, com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private boolean isMatchWear(RunPlanInfo runPlanInfo) {
        boolean z;
        if (runPlanInfo != null) {
            if (!this.mRunPlanMap.containsKey(HEALTH) ? !(!this.mRunPlanMap.containsKey("wear") || runPlanInfo.getRunPlanStructList() == null || this.mRunPlanMap.get("wear").intValue() < runPlanInfo.getRunPlanStructList().size()) : !(!this.mRunPlanMap.containsKey("wear") || runPlanInfo.getRunPlanStructList() == null || (this.mRunPlanMap.get("wear").intValue() != runPlanInfo.getRunPlanStructList().size() && (this.mRunPlanMap.get("wear").intValue() <= runPlanInfo.getRunPlanStructList().size() || this.mRunPlanMap.get(HEALTH).intValue() >= runPlanInfo.getRunPlanStructList().size())))) {
                z = true;
                LogUtil.a(TAG, "runplanlog isMatchWear:", Boolean.valueOf(z));
                return z;
            }
        } else {
            LogUtil.a(TAG, "runplanlog isMatchWear runPlanInfo is null");
        }
        z = false;
        LogUtil.a(TAG, "runplanlog isMatchWear:", Boolean.valueOf(z));
        return z;
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0071, code lost:
    
        if (r2 != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0098, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0096, code lost:
    
        if (r7.mRunPlanMap.get(com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.HEALTH).intValue() >= r8.getRunPlanStructList().size()) goto L33;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean isMatchHealth(com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanInfo r8) {
        /*
            r7 = this;
            java.lang.String r0 = "HWhealthLinkage_CallbackService"
            r1 = 0
            if (r8 == 0) goto L9a
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r7.mRunPlanMap
            java.lang.String r3 = "wear"
            boolean r2 = r2.containsKey(r3)
            r4 = 1
            java.lang.String r5 = "health"
            if (r2 == 0) goto L74
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r7.mRunPlanMap
            java.lang.Object r2 = r2.get(r3)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.List r3 = r8.getRunPlanStructList()
            int r3 = r3.size()
            if (r2 <= r3) goto L2b
            r2 = r4
            goto L2c
        L2b:
            r2 = r1
        L2c:
            java.util.Map<java.lang.String, java.lang.Integer> r3 = r7.mRunPlanMap
            java.lang.Object r3 = r3.get(r5)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.util.List r6 = r8.getRunPlanStructList()
            int r6 = r6.size()
            if (r3 <= r6) goto L46
            if (r2 == 0) goto L46
            r2 = r4
            goto L47
        L46:
            r2 = r1
        L47:
            java.util.Map<java.lang.String, java.lang.Integer> r3 = r7.mRunPlanMap
            java.lang.Object r3 = r3.get(r5)
            java.lang.Integer r3 = (java.lang.Integer) r3
            int r3 = r3.intValue()
            java.util.List r6 = r8.getRunPlanStructList()
            int r6 = r6.size()
            if (r3 == r6) goto L62
            if (r2 == 0) goto L60
            goto L62
        L60:
            r2 = r1
            goto L63
        L62:
            r2 = r4
        L63:
            java.util.Map<java.lang.String, java.lang.Integer> r3 = r7.mRunPlanMap
            boolean r3 = r3.containsKey(r5)
            if (r3 == 0) goto La3
            java.util.List r8 = r8.getRunPlanStructList()
            if (r8 == 0) goto La3
            if (r2 == 0) goto La3
            goto L98
        L74:
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r7.mRunPlanMap
            boolean r2 = r2.containsKey(r5)
            if (r2 == 0) goto La3
            java.util.List r2 = r8.getRunPlanStructList()
            if (r2 == 0) goto La3
            java.util.Map<java.lang.String, java.lang.Integer> r2 = r7.mRunPlanMap
            java.lang.Object r2 = r2.get(r5)
            java.lang.Integer r2 = (java.lang.Integer) r2
            int r2 = r2.intValue()
            java.util.List r8 = r8.getRunPlanStructList()
            int r8 = r8.size()
            if (r2 < r8) goto La3
        L98:
            r1 = r4
            goto La3
        L9a:
            java.lang.String r8 = "runplanlog isMatchHealth runPlanInfo is null"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r8)
        La3:
            java.lang.String r8 = "runplanlog isMatchHealth:"
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r8)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.isMatchHealth(com.huawei.hwdevice.phoneprocess.mgr.exercise.datatype.RunPlanInfo):boolean");
    }

    private void exeRequest(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        if ("workoutOperateRealtimeData".equals(str)) {
            LogUtil.a(TAG, "exeRequest connect state:", Boolean.valueOf(isConnectedWithRemote()), "funcName:", str);
        } else {
            ReleaseLogUtil.e(TAG, "exeRequest connect state:", Boolean.valueOf(isConnectedWithRemote()), "funcName:", str);
        }
        if (!isConnectedWithRemote()) {
            LogUtil.a(TAG, "service is not connected!");
            if ("syncFitnessDetailData".equals(str) && iBaseResponseCallback != null) {
                iBaseResponseCallback.d(100001, null);
            }
            startRemoteService();
            ReleaseLogUtil.e(TAG, "requestWearTask funcName ", str);
            if (jdd.c(str)) {
                if (this.mNoDeviceRemoteFunctionDataList.get(str) != null) {
                    LogUtil.a(TAG, "requestWearTask funcName exist in noDeviceRemoteFunciotnDataList");
                }
                LogUtil.a(TAG, "requestWearTask funcName put noDeviceRemoteFunciotnDataList");
                this.mNoDeviceRemoteFunctionDataList.put(str, new a(str, str2, iBaseResponseCallback));
                return;
            }
            if (this.mRemoteFunctionDataList.get(str) != null) {
                LogUtil.a(TAG, "requestWearTask funcName exist in mRemoteFunctionDataList");
            }
            this.mRemoteFunctionDataList.put(str, new a(str, str2, iBaseResponseCallback));
            return;
        }
        LogUtil.a(TAG, "exeRequest: the callbackservice is calling ", str);
        HashMap hashMap = new HashMap(16);
        hashMap.put("funcName", str);
        if (str2 != null) {
            hashMap.put("parameters", str2);
        }
        if (!"setOperator".equals(str) && !"setHeartRateReportStatus".equals(str)) {
            if ("setStressReportStatus".equals(str) && !cpa.p(str2)) {
                if (isWeaR1PConnected() && isWearDeviceSupportStress()) {
                    ReleaseLogUtil.e(TAG, "send to requestWearTaskForWear");
                    requestWearTaskForWear(hashMap, str);
                    return;
                } else if (isHealthR1PConnected() && isHealthDeviceSupportStress()) {
                    ReleaseLogUtil.e(TAG, "send to requestWearTaskForHealth");
                    requestWearTaskForHealth(hashMap, str);
                    return;
                } else {
                    ReleaseLogUtil.e(TAG, "send to other");
                    requestWearTaskForWear(hashMap, str);
                    requestWearTaskForHealth(hashMap, str);
                    return;
                }
            }
            if (checkConnected(str)) {
                if (!cpa.p(str2)) {
                    requestWearTaskForWear(hashMap, str);
                }
            } else {
                ReleaseLogUtil.e(TAG, "don't send command:", str);
            }
            requestWearTaskForHealth(hashMap, str);
            if ("syncFitnessDetailData".equals(str)) {
                duv.e().a();
                return;
            }
            return;
        }
        if (isWearDeviceSupportHeart() && !cpa.p(str2)) {
            requestWearTaskForWear(hashMap, str);
        }
        if (isHealthDeviceSupportHeart()) {
            requestWearTaskForHealth(hashMap, str);
        }
    }

    private boolean checkConnected(String str) {
        List<DeviceInfo> list = this.mUsedDeviceList;
        return (list != null && list.size() > 0) || ("syncFitnessDetailData".equals(str) ^ true) || (mIsHuaweiHealthConnected ^ true);
    }

    public boolean isWearDeviceSupportStress() {
        JSONObject jSONObject = this.mDeviceCapability;
        if (jSONObject != null) {
            try {
                this.mWearDeviceCapability = (DeviceCapability) this.mGson.fromJson(jSONObject.toString(), DeviceCapability.class);
            } catch (JsonSyntaxException e2) {
                ReleaseLogUtil.e(TAG, "mWearDeviceCapability JsonSyntaxException:", e2.getMessage());
                sqo.w("mWearDeviceCapability JsonSyntaxException:" + e2.getMessage());
                this.mWearDeviceCapability = null;
            }
        }
        DeviceCapability deviceCapability = this.mWearDeviceCapability;
        boolean isSupportStressInfo = deviceCapability != null ? deviceCapability.isSupportStressInfo() : false;
        LogUtil.a(TAG, "isWearDeviceSupportStress :", Boolean.valueOf(isSupportStressInfo));
        return isSupportStressInfo;
    }

    private boolean isWeaR1PConnected() {
        List<DeviceInfo> list = this.mUsedDeviceList;
        boolean z = false;
        if (list != null) {
            for (DeviceInfo deviceInfo : list) {
                if (1 == deviceInfo.getDeviceActiveState() && 2 == deviceInfo.getDeviceConnectState() && deviceInfo.getProductType() == 43) {
                    z = true;
                }
            }
        }
        return z;
    }

    private boolean isHealthR1PConnected() {
        List<DeviceInfo> list = this.mUsedDeviceListInHealth;
        boolean z = false;
        if (list != null) {
            for (DeviceInfo deviceInfo : list) {
                if (1 == deviceInfo.getDeviceActiveState() && 2 == deviceInfo.getDeviceConnectState() && deviceInfo.getProductType() == 43) {
                    z = true;
                }
            }
        }
        return z;
    }

    private void requestWearTaskForWear(Map<String, String> map, String str) {
        int beginBroadcast = this.mCallbackList.beginBroadcast();
        if ("workoutOperateRealtimeData".equals(str)) {
            LogUtil.a(TAG, "requestWearTaskForWear: calling remote callback, the length is", Integer.valueOf(beginBroadcast));
        } else {
            ReleaseLogUtil.e(TAG, "requestWearTaskForWear: calling remote callback, the length is", Integer.valueOf(beginBroadcast));
        }
        for (int i = 0; i < beginBroadcast; i++) {
            executeTask(map, this.mCallbackList.getBroadcastItem(i));
        }
        this.mCallbackList.finishBroadcast();
    }

    private void requestWearTaskForHealth(Map<String, String> map, String str) {
        int beginBroadcast = this.mCallbackListForHealth.beginBroadcast();
        if ("workoutOperateRealtimeData".equals(str)) {
            LogUtil.a(TAG, "requestWearTaskForHealth: calling remote callback, the length is", Integer.valueOf(beginBroadcast));
        } else {
            ReleaseLogUtil.e(TAG, "requestWearTaskForHealth: calling remote callback, the length is", Integer.valueOf(beginBroadcast));
        }
        for (int i = 0; i < beginBroadcast; i++) {
            executeTask(map, this.mCallbackListForHealth.getBroadcastItem(i));
        }
        this.mCallbackListForHealth.finishBroadcast();
    }

    private void executeTask(final Map<String, String> map, final IInterface iInterface) {
        Runnable runnable = new Runnable() { // from class: com.huawei.health.hwhealthlinkage.wearsdk.CallbackService.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    IInterface iInterface2 = iInterface;
                    if (iInterface2 != null) {
                        ((IRemoteProxyCallback) iInterface2).requestWearTask(map);
                    } else {
                        ReleaseLogUtil.c(CallbackService.TAG, "calling remote callback, item is null");
                    }
                } catch (RemoteException e2) {
                    ReleaseLogUtil.c(CallbackService.TAG, LogAnonymous.b((Throwable) e2));
                    sqo.w("executeTask remoteException:" + e2.getMessage());
                } catch (Exception e3) {
                    ReleaseLogUtil.c(CallbackService.TAG, "executeTask Exception");
                    sqo.w("executeTask exception:" + e3.getMessage());
                }
            }
        };
        if (jdx.b(runnable) != 0) {
            ThreadPoolManager.d().execute(runnable);
        }
    }

    public void registerNotification(String str, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG, "notificationName ", str, " registered");
        List<IBaseResponseCallback> list = this.mNotificationCallbackCache.get(str);
        if (list == null) {
            list = new ArrayList<>(10);
            this.mNotificationCallbackCache.put(str, list);
        }
        if (list.contains(iBaseResponseCallback)) {
            return;
        }
        list.add(iBaseResponseCallback);
    }

    public void unRegisterNotification(String str, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e(TAG, "start ----unRegisterNotification ", str, " registered");
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.e(TAG, "the callback is null. from function : ", str);
            return;
        }
        List<IBaseResponseCallback> list = this.mNotificationCallbackCache.get(str);
        if (list == null) {
            ReleaseLogUtil.e(TAG, "You have not registerNotification any callback from function : ", str);
        } else if (!list.contains(iBaseResponseCallback)) {
            LogUtil.a(TAG, "list do not contain callback. from function : ", str);
        } else {
            ReleaseLogUtil.e(TAG, "done --- unRegisterNotification ", str, " registered");
            list.remove(iBaseResponseCallback);
        }
    }

    private void startRemoteService() {
        duv.e().c();
    }

    final class b implements IBinder.DeathRecipient {
        private final IBinder c;
        private final String d;
        private final String e;

        public b(IBinder iBinder, String str, String str2) {
            this.c = iBinder;
            this.d = str;
            this.e = str2;
        }

        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            if (CallbackService.this.mClients.indexOf(this) < 0) {
                return;
            }
            ReleaseLogUtil.e(CallbackService.TAG, "client died mPackageName: ", this.d, " the size of mClients is ", Integer.valueOf(CallbackService.this.mClients.size()));
            CallbackService.this.mClients.remove(this);
            if (this.d.equals("PhoneService")) {
                boolean unused = CallbackService.mIsHuaweiWearConnected = false;
                LogUtil.a(CallbackService.TAG, "与穿戴App断开的连接状态是否存入成功 (binderDied)：", Integer.valueOf(SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "KEY_CONNECTION_TO_WEAR", "1", (StorageParams) null)));
            } else if (this.d.equals("PhoneServiceInHealth")) {
                boolean unused2 = CallbackService.mIsHuaweiHealthConnected = false;
                Intent intent = new Intent();
                intent.setAction("com.huawei.health.action.ACTION_CALLBACKSERVICE_PHONESERVICE_DEAD");
                BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
                ReleaseLogUtil.e(CallbackService.TAG, "send ", "com.huawei.health.action.ACTION_CALLBACKSERVICE_PHONESERVICE_DEAD");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSameDevice(List<DeviceInfo> list) {
        String str;
        String str2;
        List<DeviceInfo> list2 = this.mUsedDeviceList;
        if (list2 != null && list != null) {
            Iterator<DeviceInfo> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = "";
                    break;
                }
                DeviceInfo next = it.next();
                if (1 == next.getDeviceActiveState()) {
                    str2 = next.getUuid();
                    break;
                }
            }
            Iterator<DeviceInfo> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str = "";
                    break;
                }
                DeviceInfo next2 = it2.next();
                if (1 == next2.getDeviceActiveState()) {
                    str = next2.getUuid();
                    break;
                }
            }
        } else {
            str = "";
            str2 = str;
        }
        return !str.equals("") && str.equals(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSameDeviceInHealth(List<DeviceInfo> list) {
        String str;
        String str2;
        List<DeviceInfo> list2 = this.mUsedDeviceListInHealth;
        if (list2 != null && list != null) {
            Iterator<DeviceInfo> it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = "";
                    break;
                }
                DeviceInfo next = it.next();
                if (1 == next.getDeviceActiveState()) {
                    str2 = next.getUuid();
                    break;
                }
            }
            Iterator<DeviceInfo> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    str = "";
                    break;
                }
                DeviceInfo next2 = it2.next();
                if (1 == next2.getDeviceActiveState()) {
                    str = next2.getUuid();
                    break;
                }
            }
        } else {
            str = "";
            str2 = str;
        }
        return !str.equals("") && str.equals(str2);
    }

    public IBinder getLinkageBinder() {
        ICallbackInterface.Stub stub;
        synchronized (OBJECT_LOCK) {
            stub = this.iCallback;
        }
        return stub;
    }
}
