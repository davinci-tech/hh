package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.hwhealthlinkage.wearsdk.CallbackService;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dwo {

    /* renamed from: a, reason: collision with root package name */
    private static Context f11869a;
    private static volatile dwo e;
    private CallbackService d;
    private CallbackService.MyBinder f;
    private List<IBaseResponseCallback> g = new ArrayList(10);
    private List<IBaseResponseCallback> h = new ArrayList(10);
    private IBaseResponseCallback i = new IBaseResponseCallback() { // from class: dwo.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "onResponse notified");
            dwo dwoVar = dwo.this;
            dwoVar.d((List<IBaseResponseCallback>) dwoVar.h);
            LogUtil.a("HWhealthLinkage_WearSDK", "after registerBinderListenerCallbackList notified");
            dwo dwoVar2 = dwo.this;
            dwoVar2.d((List<IBaseResponseCallback>) dwoVar2.g);
        }
    };
    private ServiceConnection j = new ServiceConnection() { // from class: dwo.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (iBinder != null) {
                try {
                    dwo.this.d = ((CallbackService.MyBinder) iBinder).getCallbackService();
                    dwo.this.f = (CallbackService.MyBinder) iBinder;
                    dwo.this.f.setRemoteServerListener(dwo.this.i);
                    ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "onServiceConnected notified");
                    dwo dwoVar = dwo.this;
                    dwoVar.d((List<IBaseResponseCallback>) dwoVar.g);
                    if (dwo.this.d != null) {
                        jez.bGS_(dwo.this.d.getLinkageBinder());
                        dwo.this.m();
                    } else {
                        dwo.this.l();
                    }
                } catch (ClassCastException e2) {
                    ReleaseLogUtil.c("HWhealthLinkage_WearSDK", LogAnonymous.b((Throwable) e2));
                }
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "localServiceCallbackConnection has been connected!");
                return;
            }
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "onServiceConnected service is null");
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "Service has unexpectedly disconnected");
        }
    };
    private static final byte[] c = new byte[1];
    private static final byte[] b = new byte[1];

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "unBindCallbackService!!!");
        HandlerExecutor.d(new Runnable() { // from class: dwn
            @Override // java.lang.Runnable
            public final void run() {
                dwo.this.k();
            }
        }, 2000L);
    }

    /* synthetic */ void k() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: dwp
            @Override // java.lang.Runnable
            public final void run() {
                dwo.this.o();
            }
        });
    }

    public void o() {
        try {
            ServiceConnection serviceConnection = this.j;
            if (serviceConnection != null) {
                f11869a.unbindService(serviceConnection);
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "unbindService");
            }
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "unBindService is error");
        }
    }

    public void m(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "registerServiceConnectedListener");
        if (this.d != null && iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100000, "success");
        }
        if (this.g.contains(iBaseResponseCallback)) {
            return;
        }
        this.g.add(iBaseResponseCallback);
    }

    public void p(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "unregisterServiceConnectedListener");
        if (this.g.contains(iBaseResponseCallback)) {
            this.g.remove(iBaseResponseCallback);
        }
    }

    public void j(IBaseResponseCallback iBaseResponseCallback) {
        if (this.h.contains(iBaseResponseCallback)) {
            return;
        }
        this.h.add(iBaseResponseCallback);
    }

    public static dwo d() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    f11869a = BaseApplication.getContext();
                    e = new dwo();
                }
            }
        }
        return e;
    }

    private dwo() {
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        Intent intent = new Intent("local.proxy");
        intent.setClass(f11869a, CallbackService.class);
        intent.setPackage("com.huawei.health");
        intent.putExtra("from_flag", "WearSDK" + hashCode());
        f11869a.bindService(intent, this.j, 1);
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("getOperator", null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        if (this.d != null) {
            if (cvs.d() == null) {
                ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "CapabilityUtils.getDeviceCapability() == null");
                iBaseResponseCallback.d(-3, "");
                return;
            }
            boolean isSupportWorkoutCapabilicy = cvs.d().isSupportWorkoutCapabilicy();
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "isSupport = ", Boolean.valueOf(isSupportWorkoutCapabilicy));
            if (isSupportWorkoutCapabilicy) {
                LogUtil.a("HWhealthLinkage_WearSDK", "getDeviceStepRateAlgorithmEnterprise requestWearTask");
                this.d.requestWearTask("getWorkoutCapability", null, iBaseResponseCallback);
                return;
            } else {
                LogUtil.a("HWhealthLinkage_WearSDK", "!isSupportWorkoutCapabilicy");
                iBaseResponseCallback.d(-3, "false");
                return;
            }
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null");
        iBaseResponseCallback.d(201000, "");
    }

    public void i(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setOperator parameters is null");
        } else if (this.d != null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setOperator callbackservice is not null");
            this.d.requestWearTask("setOperator", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setOperator callbackservice is null");
        }
    }

    public void b(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setHeartRateReportStatus parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setHeartRateReportStatus", jSONObject.toString(), iBaseResponseCallback);
            this.d.registerNotification("notificationHeartRateInfo", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void c(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "openOrCloseReport parameters is null");
            sqo.w("openOrCloseReport parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("openOrCloseReport", jSONObject.toString(), iBaseResponseCallback);
            this.d.registerNotification("registerGetRTSportDataListCallbackList", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "openOrCloseReport callbackservice is null");
        }
    }

    public void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("operator_type", i);
            CallbackService callbackService = this.d;
            if (callbackService != null) {
                callbackService.requestWearTask("openOrCloseReport", jSONObject.toString(), iBaseResponseCallback);
            } else {
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "openOrCloseReport callbackservice is null");
            }
        } catch (JSONException e2) {
            ReleaseLogUtil.d("HWhealthLinkage_", "openAllDataReport JSONException = ", e2.getMessage());
        }
    }

    public void d(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setAw70LinkReportStatus enter");
        if (jSONObject == null || iBaseResponseCallback == null) {
            ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "setAw70LinkReportStatus parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setAw70LinkReportStatus", jSONObject.toString(), iBaseResponseCallback);
            this.d.registerNotification("notificationAw70LinkInfo", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "setAw70LinkReportStatus callbackservice is null");
        }
    }

    public void h(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("notificationStateConnectionStateChanged", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
        }
    }

    public void f(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("notificationStatus", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void l(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("notificationSportReminder", iBaseResponseCallback);
        }
    }

    public List<DeviceInfo> b() {
        CallbackService.MyBinder myBinder = this.f;
        List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo> usedDeviceList = myBinder != null ? myBinder.getUsedDeviceList() : null;
        ArrayList arrayList = new ArrayList(10);
        if (usedDeviceList == null || usedDeviceList.size() <= 0) {
            return null;
        }
        for (com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo : usedDeviceList) {
            if (deviceInfo != null) {
                arrayList.add(deviceInfo.convertToCommonDevice());
            }
        }
        return arrayList;
    }

    public List<DeviceInfo> g() {
        CallbackService.MyBinder myBinder = this.f;
        List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo> usedDeviceListInHealth = myBinder != null ? myBinder.getUsedDeviceListInHealth() : null;
        ArrayList arrayList = new ArrayList(10);
        if (usedDeviceListInHealth == null || usedDeviceListInHealth.size() <= 0) {
            return null;
        }
        for (com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo : usedDeviceListInHealth) {
            if (deviceInfo != null) {
                arrayList.add(deviceInfo.convertToCommonDevice());
            }
        }
        return arrayList;
    }

    public List<DeviceInfo> j() {
        CallbackService.MyBinder myBinder = this.f;
        List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo> usedDeviceListInWear = myBinder != null ? myBinder.getUsedDeviceListInWear() : null;
        ArrayList arrayList = new ArrayList(10);
        if (usedDeviceListInWear == null || usedDeviceListInWear.size() <= 0) {
            return null;
        }
        for (com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo : usedDeviceListInWear) {
            if (deviceInfo != null) {
                arrayList.add(deviceInfo.convertToCommonDevice());
            }
        }
        return arrayList;
    }

    public List<DeviceInfo> h() {
        CallbackService.MyBinder myBinder = this.f;
        List<com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo> wearDeviceList = myBinder != null ? myBinder.getWearDeviceList() : null;
        ArrayList arrayList = new ArrayList(10);
        if (wearDeviceList == null || wearDeviceList.size() <= 0) {
            return null;
        }
        for (com.huawei.health.devicemgr.business.entity.legacy.DeviceInfo deviceInfo : wearDeviceList) {
            if (deviceInfo != null) {
                arrayList.add(deviceInfo.convertToCommonDevice());
            }
        }
        LogUtil.a("HWhealthLinkage_WearSDK", "getWearDeviceList is size:" + arrayList.size());
        return arrayList;
    }

    public DeviceInfo e() {
        synchronized (b) {
            if (this.f == null) {
                return null;
            }
            List<DeviceInfo> b2 = b();
            if (b2 != null) {
                LogUtil.a("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfoList.size() = " + b2.size());
                DeviceInfo e2 = e(b2);
                if (e2 != null) {
                    return e2;
                }
                ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
                return null;
            }
            ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfoList is null");
            return null;
        }
    }

    private DeviceInfo e(List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null) {
                if (deviceInfo.getDeviceActiveState() == 1 && deviceInfo.getDeviceConnectState() == 2) {
                    return deviceInfo;
                }
            } else {
                ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "deviceInfo is null");
            }
        }
        return null;
    }

    public DeviceInfo a(boolean z) {
        List<DeviceInfo> g;
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo isWear:" + z);
        synchronized (b) {
            if (this.f == null) {
                return null;
            }
            if (z) {
                g = j();
            } else {
                g = g();
            }
            if (g != null) {
                LogUtil.a("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfoList.size() = " + g.size());
                DeviceInfo e2 = e(g);
                if (e2 != null) {
                    return e2;
                }
                ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfo's ActiveState not DeviceActiveState.DEVICE_ACTIVE_ENABLE");
                return null;
            }
            ReleaseLogUtil.c("HWhealthLinkage_WearSDK", "getCurrentDeviceInfo() deviceInfoList is null");
            return null;
        }
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (this.d != null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "sendHealthDataTohealth jsonString:" + str);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("jsonString", str);
            } catch (JSONException unused) {
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService JSONException");
            }
            this.d.requestWearTask("sendHealthDataTohealth", jSONObject.toString(), iBaseResponseCallback);
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
    }

    public void b(String str, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "unbindAllDevice ");
        if (this.d != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("typeList", str);
            } catch (JSONException unused) {
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService JSONException");
            }
            this.d.requestWearTask("unbindDevicesByTypes", jSONObject.toString(), iBaseResponseCallback);
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "Enter getMessageCenterFromWear ");
        if (this.d != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("commandType", 1);
            } catch (JSONException unused) {
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService JSONException");
            }
            this.d.requestWearTask("getWearData", jSONObject.toString(), iBaseResponseCallback);
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
    }

    public void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "Enter getMessageCenterFromWear ");
        if (this.d != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("commandType", 3);
                jSONObject.put("leomac", str);
            } catch (JSONException unused) {
                ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService JSONException");
            }
            this.d.requestWearTask("getWearData", jSONObject.toString(), iBaseResponseCallback);
            return;
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
    }

    public void b(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "Enter getDeviceListFromWear");
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("getDeviceList", null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackService is null!!!");
        }
    }

    public JSONObject a() {
        CallbackService.MyBinder myBinder = this.f;
        if (myBinder != null) {
            return myBinder.getDeviceCapability();
        }
        return null;
    }

    public DeviceCapability i() {
        CallbackService.MyBinder myBinder = this.f;
        if (myBinder != null) {
            return myBinder.getWearDeviceCapability();
        }
        return null;
    }

    public List<DeviceCapability> c() {
        CallbackService.MyBinder myBinder = this.f;
        if (myBinder != null) {
            return myBinder.getHealthDeviceCapability();
        }
        return null;
    }

    public boolean n() {
        CallbackService.MyBinder myBinder = this.f;
        DeviceCapability wearDeviceCapability = myBinder != null ? myBinder.getWearDeviceCapability() : null;
        if (wearDeviceCapability != null) {
            return wearDeviceCapability.isSupportHeartRateInfo();
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "isWearDeviceSupportHeart deviceCapability is null");
        return false;
    }

    public boolean f() {
        CallbackService.MyBinder myBinder = this.f;
        List<DeviceCapability> healthDeviceCapability = myBinder != null ? myBinder.getHealthDeviceCapability() : null;
        boolean z = false;
        if (healthDeviceCapability != null) {
            Iterator<DeviceCapability> it = healthDeviceCapability.iterator();
            while (it.hasNext()) {
                z = it.next().isSupportHeartRateInfo();
                if (z) {
                    return z;
                }
            }
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "isHealthDeviceSupportHeart deviceCapability is null");
        }
        return z;
    }

    public void i(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("getRunPlanParameter", null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void j(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setRunPlan parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setRunPlan", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void f(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setRunPlanReminderSwitch parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setRunPlanReminderSwitch", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void a(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "deliverRealTimeSportData parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("workoutOperateRealtimeData", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void t(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("syncFitnessDetailData", null, iBaseResponseCallback);
            return;
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(100001, null);
        }
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
    }

    public void k(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("registerNotificationRunPlanRecordInfoCallbackList", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void o(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("notificationGetWorkoutRecordStatistic", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void n(IBaseResponseCallback iBaseResponseCallback) {
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.registerNotification("notificationWorkoutRecordSpeechPlay", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void g(IBaseResponseCallback iBaseResponseCallback) {
        if (this.d != null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "registerNotificationPress ---");
            this.d.registerNotification("registerNotificationPressCallBack", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void s(IBaseResponseCallback iBaseResponseCallback) {
        if (this.d != null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "unRegisterNotificationPress ---");
            this.d.unRegisterNotification("registerNotificationPressCallBack", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void e(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "notifyVoicePlayStatus object is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("notificationWorkoutRecordSpeechPlayReportStatus", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void h(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setNotificationStatusResponse parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setNotificationStatusResponse", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void g(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setMetricUnit parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setMetricUnit", jSONObject.toString(), iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<IBaseResponseCallback> list) {
        if (list.isEmpty() || this.d == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            LogUtil.a("HWhealthLinkage_WearSDK", "notify notifyAllListener~~~~" + list.get(i));
            list.get(i).d(100000, "success");
        }
    }

    public void o(JSONObject jSONObject, IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setStressMeasureStatus()");
        if (jSONObject == null) {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "setStressMeasureStatus parameters is null");
            return;
        }
        CallbackService callbackService = this.d;
        if (callbackService != null) {
            callbackService.requestWearTask("setStressReportStatus", jSONObject.toString(), iBaseResponseCallback);
            this.d.registerNotification("notificationStressInfo", iBaseResponseCallback);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "callbackservice is null");
        }
    }

    public void e(String str) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "Enter removeFunctionCallback()");
        CallbackService.MyBinder myBinder = this.f;
        if (myBinder != null) {
            myBinder.removeFunctionCallback(str);
        } else {
            ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "localBinder is null");
        }
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "getWorkoutBitmap enter.");
        CallbackService callbackService = this.d;
        if (callbackService != null && iBaseResponseCallback != null) {
            callbackService.requestWearTask("getAw70SupportSportType", null, iBaseResponseCallback);
        } else {
            ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "getWorkoutBitmap service is null");
        }
    }

    public void a(IBaseResponseCallback iBaseResponseCallback, String str) {
        ReleaseLogUtil.e("HWhealthLinkage_WearSDK", "sendEcgBlackList enter.");
        if (this.d != null && iBaseResponseCallback != null) {
            if (TextUtils.isEmpty(str)) {
                ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "json is empty.");
                iBaseResponseCallback.d(100007, "");
                return;
            } else {
                this.d.requestWearTask("sendEcgBlockList", str, iBaseResponseCallback);
                return;
            }
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(-1, "");
        }
        ReleaseLogUtil.d("HWhealthLinkage_WearSDK", "getWorkoutBitmap service is null");
    }
}
