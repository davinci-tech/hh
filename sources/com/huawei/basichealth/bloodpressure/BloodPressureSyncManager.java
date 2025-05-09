package com.huawei.basichealth.bloodpressure;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import androidx.core.util.Pair;
import com.google.gson.Gson;
import com.huawei.basichealth.bloodpressure.BloodPressureSyncManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.MainLoginCallBack;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.cbi;
import defpackage.cbk;
import defpackage.cun;
import defpackage.cwi;
import defpackage.spn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class BloodPressureSyncManager extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicInteger f1893a;
    private static final Object b = new Object();
    private static volatile BloodPressureSyncManager d;
    private ExtendHandler c;
    private Pair<Integer, IBloodPressureMeasureCallback> e;
    private Runnable g;
    private final ThreadPoolManager h;

    public interface IBloodPressureMeasureCallback {
        void onMeasureFinish(int i, long j);

        void onMeasureStart(long j);

        void onNotifyMeasure(int i);
    }

    private BloodPressureSyncManager() {
        super(BaseApplication.e());
        this.c = null;
        this.h = ThreadPoolManager.e(1, 1, "BloodPressureSyncManager");
        this.c = HandlerCenter.yt_(new Handler.Callback() { // from class: com.huawei.basichealth.bloodpressure.BloodPressureSyncManager.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                return false;
            }
        }, "BloodPressureSyncManager");
    }

    public static BloodPressureSyncManager c() {
        synchronized (b) {
            if (d == null) {
                f1893a = new AtomicInteger();
                d = new BloodPressureSyncManager();
            }
            f1893a.incrementAndGet();
        }
        return d;
    }

    private static void g() {
        LogUtil.a("BloodPressureSyncManager", "destory");
        f1893a = null;
        d = null;
    }

    public void a() {
        LogUtil.a("BloodPressureSyncManager", "release");
        synchronized (b) {
            AtomicInteger atomicInteger = f1893a;
            if (atomicInteger != null && atomicInteger.decrementAndGet() == 0) {
                this.h.shutdown();
                this.c.quit(false);
                d.onDestroy();
                g();
            }
        }
    }

    private void c(Runnable runnable) {
        this.h.execute(runnable);
    }

    private void b(final byte[] bArr) {
        c(new Runnable() { // from class: asl
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureSyncManager.this.d(bArr);
            }
        });
    }

    public /* synthetic */ void d(byte[] bArr) {
        LogUtil.a("BloodPressureSyncManager", "parseMessage");
        try {
            String str = new String(bArr, StandardCharsets.UTF_8);
            LogUtil.a("BloodPressureSyncManager", "receive, ", str);
            JSONObject jSONObject = new JSONObject(str);
            int i = jSONObject.getInt("version");
            if (i != 1) {
                LogUtil.h("BloodPressureSyncManager", "parseResponse Unknown version, version = ", Integer.valueOf(i));
            } else {
                e(jSONObject);
            }
        } catch (JSONException e) {
            LogUtil.b("BloodPressureSyncManager", e);
        }
    }

    private void e(JSONObject jSONObject) {
        try {
            int i = jSONObject.getInt("msgType");
            JSONObject jSONObject2 = jSONObject.getJSONObject("msgBody");
            if (i == 900) {
                a(jSONObject2);
            } else if (i == 8003) {
                b(jSONObject2);
            } else if (i != 8004) {
                LogUtil.h("BloodPressureSyncManager", "unknow case, type=", Integer.valueOf(i));
            } else {
                c(jSONObject2);
            }
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureSyncManager", "startMeasure parseMessageVersionOne");
        }
    }

    private void a(JSONObject jSONObject) throws JSONException {
        LogUtil.a("BloodPressureSyncManager", "common response");
        int i = jSONObject.getInt("errcode");
        int i2 = jSONObject.getInt("rspMsgType");
        if (i2 == 8001) {
            if (i == 0) {
                LogUtil.a("BloodPressureSyncManager", "sync measure plan successed");
            } else {
                LogUtil.a("BloodPressureSyncManager", "sync measure plan failed");
            }
            a();
            return;
        }
        if (i2 != 8002) {
            if (i2 != 8005) {
                LogUtil.h("BloodPressureSyncManager", "unknow case, rspCode=", Integer.valueOf(i2));
                return;
            } else {
                LogUtil.a("BloodPressureSyncManager", "sync wake up, code=", Integer.valueOf(i));
                a();
                return;
            }
        }
        LogUtil.a("BloodPressureSyncManager", "notify measure, code=", Integer.valueOf(i));
        Pair<Integer, IBloodPressureMeasureCallback> pair = this.e;
        if (pair != null) {
            pair.second.onNotifyMeasure(i);
            if (i != 0) {
                this.e = null;
            }
        }
    }

    private void c(JSONObject jSONObject) throws JSONException {
        LogUtil.a("BloodPressureSyncManager", "measure finish");
        if (this.e == null || jSONObject.getInt(EventMonitorRecord.EVENT_ID) != this.e.first.intValue()) {
            return;
        }
        d(jSONObject.getInt("resultCode"), jSONObject.getLong("endTime"));
    }

    private void b(JSONObject jSONObject) throws JSONException {
        LogUtil.a("BloodPressureSyncManager", "measure start");
        if (this.e == null || jSONObject.getInt(EventMonitorRecord.EVENT_ID) != this.e.first.intValue()) {
            return;
        }
        f();
        this.e.second.onMeasureStart(jSONObject.getLong("startTime"));
    }

    public void e(final IBloodPressureMeasureCallback iBloodPressureMeasureCallback) {
        c(new Runnable() { // from class: asi
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureSyncManager.this.d(iBloodPressureMeasureCallback);
            }
        });
    }

    public /* synthetic */ void d(IBloodPressureMeasureCallback iBloodPressureMeasureCallback) {
        LogUtil.a("BloodPressureSyncManager", "notifyWearMeasure");
        try {
            this.e = Pair.create(Integer.valueOf(j()), iBloodPressureMeasureCallback);
            JSONObject d2 = d(8002);
            d2.getJSONObject("msgBody").put(EventMonitorRecord.EVENT_ID, this.e.first);
            LogUtil.a("BloodPressureSyncManager", "send,", d2.toString());
            e(d2.toString());
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureSyncManager", "startMeasure JSONException");
        }
    }

    private int j() {
        String valueOf = String.valueOf(SystemClock.elapsedRealtime());
        int length = valueOf.length();
        if (length > 6) {
            valueOf = valueOf.substring(length - 6, length);
        }
        try {
            return Integer.parseInt(valueOf);
        } catch (NumberFormatException unused) {
            LogUtil.b("BloodPressureSyncManager", "Failed to createEventId");
            return -1;
        }
    }

    private void f() {
        Runnable runnable = this.g;
        if (runnable != null) {
            this.c.removeTasks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: asj
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureSyncManager.this.b();
            }
        };
        this.g = runnable2;
        LogUtil.a("BloodPressureSyncManager", "startMeasureClock, ", runnable2);
        this.c.postTask(this.g, 90000L);
    }

    public /* synthetic */ void b() {
        LogUtil.a("BloodPressureSyncManager", "execute startMeasureClock");
        d(-2, 0L);
    }

    private void d(int i, long j) {
        synchronized (b) {
            Runnable runnable = this.g;
            if (runnable != null && i != -2) {
                this.c.removeTasks(runnable);
                this.g = null;
            }
            Pair<Integer, IBloodPressureMeasureCallback> pair = this.e;
            if (pair != null) {
                pair.second.onMeasureFinish(i, j);
                this.e = null;
            }
        }
    }

    public void d() {
        cbi.d(new ResponseCallback() { // from class: asg
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                BloodPressureSyncManager.this.d(i, (List) obj);
            }
        });
    }

    public /* synthetic */ void d(int i, List list) {
        b((List<cbk>) list);
    }

    public void b(List<cbk> list) {
        LogUtil.a("BloodPressureSyncManager", "sendMeasurePlan");
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.d("BloodPressureSyncManager", "sendMeasurePlan measurePlans is empty");
            a();
            return;
        }
        try {
            JSONObject d2 = d(8001);
            JSONArray c = c(new HashSet(list));
            if (c.length() == 0) {
                LogUtil.a("BloodPressureSyncManager", "measure plan not need sync");
                a();
            } else {
                d2.getJSONObject("msgBody").put("planVersion", 1);
                d2.getJSONObject("msgBody").put("data", c);
                LogUtil.a("BloodPressureSyncManager", "send, ", d2.toString());
                e(d2.toString());
            }
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureSyncManager", "sendMeasurePlan JSONException");
        }
    }

    private JSONObject d(int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", 1);
        jSONObject.put("msgType", i);
        jSONObject.put("msgBody", new JSONObject());
        return jSONObject;
    }

    private JSONArray c(Set<cbk> set) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (set.size() > 0) {
            jSONArray = new JSONArray(new Gson().toJson(set));
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                jSONObject.remove("alarmId");
                jSONObject.remove("name");
                jSONObject.remove("retryTimes");
                jSONObject.remove("toneDuration");
                jSONObject.remove("toneInterval");
                jSONObject.remove("vibrate");
                jSONObject.remove("isNeedSync");
                jSONObject.put("enabled", jSONObject.getBoolean("enabled") ? 1 : 0);
            }
        }
        return jSONArray;
    }

    public static boolean e() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "BloodPressureSyncManager"), 59);
    }

    public void b(final int i) {
        c(new Runnable() { // from class: asf
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureSyncManager.this.c(i);
            }
        });
    }

    public /* synthetic */ void c(int i) {
        LogUtil.a("BloodPressureSyncManager", "sendWakeUpPlan");
        try {
            JSONObject d2 = d(MainLoginCallBack.MSG_NO_NETWORK);
            d2.getJSONObject("msgBody").put("switchStatus", i);
            LogUtil.a("BloodPressureSyncManager", "send,", d2.toString());
            e(d2.toString());
        } catch (JSONException unused) {
            LogUtil.b("BloodPressureSyncManager", "sendWakeUpPlan JSONException");
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        if (spnVar == null) {
            LogUtil.h("BloodPressureSyncManager", "data is null");
        } else {
            b(spnVar.b());
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.BLOOD_PRESSURE_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.a("BloodPressureSyncManager", "connectState ", connectState);
        if (connectState != ConnectState.CONNECTED) {
            LogUtil.a("BloodPressureSyncManager", "onDeviceDisconnected");
            d(-1, 0L);
        }
    }

    private void e(String str) {
        sendComand(new spn.b().c(str.getBytes(StandardCharsets.UTF_8)).e(), new SendCallback() { // from class: com.huawei.basichealth.bloodpressure.BloodPressureSyncManager.3
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                LogUtil.a("BloodPressureSyncManager", "sendCommandResult=", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
                LogUtil.a("BloodPressureSyncManager", "sendCommand onFileTransferReport transferWay: ", str2);
            }
        });
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "hw.watch.health.bp";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }
}
