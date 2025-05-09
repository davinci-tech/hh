package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import com.huawei.basichealth.bloodsugar.BloodSugarEngineLogicManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class ask {
    private static volatile ask b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private BloodSugarEngineLogicManager f209a;
    private final Context c;
    private final AtomicReference<String> e;
    private final ThreadPoolManager h;
    private volatile long i;
    private BroadcastReceiver j = new BroadcastReceiver() { // from class: ask.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("BloodSugarSyncManager", "intent is null");
                return;
            }
            String action = intent.getAction();
            LogUtil.a("BloodSugarSyncManager", "mSyncBloodSugarDataReceiver action: ", action);
            if ("com.huawei.health.action.SYNC_BLOOD_SUGAR_DATA".equals(action)) {
                ask.this.b();
            }
        }
    };

    private ask() {
        Context context = BaseApplication.getContext();
        this.c = context;
        this.h = ThreadPoolManager.a(1, 1);
        this.e = new AtomicReference<>();
        BroadcastManagerUtil.bFE_(context, this.j, new IntentFilter("com.huawei.health.action.SYNC_BLOOD_SUGAR_DATA"));
    }

    public static ask e() {
        if (b == null) {
            LogUtil.c("BloodSugarSyncManager", "mInstance go init");
            synchronized (d) {
                if (b == null) {
                    b = new ask();
                }
            }
        }
        return b;
    }

    public void b() {
        LogUtil.a("BloodSugarSyncManager", "start mManager is ", this.f209a);
        if (this.f209a == null) {
            BloodSugarEngineLogicManager bloodSugarEngineLogicManager = new BloodSugarEngineLogicManager(this.c);
            this.f209a = bloodSugarEngineLogicManager;
            bloodSugarEngineLogicManager.e(new BloodSugarEngineLogicManager.BloodSugarEngineLogicListener() { // from class: ask.3
                @Override // com.huawei.basichealth.bloodsugar.BloodSugarEngineLogicManager.BloodSugarEngineLogicListener
                public void onDeviceConnected(String str) {
                    LogUtil.a("BloodSugarSyncManager", "onDeviceConnected");
                    ask.this.e.set(str);
                    ask.this.h();
                }

                @Override // com.huawei.basichealth.bloodsugar.BloodSugarEngineLogicManager.BloodSugarEngineLogicListener
                public void onDeviceDisconnected() {
                    LogUtil.a("BloodSugarSyncManager", "onDeviceDisconnected");
                    ask.this.e.set(null);
                }

                @Override // com.huawei.basichealth.bloodsugar.BloodSugarEngineLogicManager.BloodSugarEngineLogicListener
                public void onReceiveDeviceCommand(byte[] bArr) {
                    if (bArr == null) {
                        LogUtil.h("BloodSugarSyncManager", "data is null");
                        return;
                    }
                    long currentTimeMillis = System.currentTimeMillis();
                    LogUtil.a("BloodSugarSyncManager", "onReceiveDeviceCommand, currentTime = ", Long.valueOf(currentTimeMillis), ", diffTime = ", Long.valueOf(Math.subtractExact(currentTimeMillis, ask.this.i)));
                    String str = (String) ask.this.e.get();
                    if (str != null) {
                        ask.this.a(bArr, str);
                    } else {
                        LogUtil.h("BloodSugarSyncManager", "ReceiveDeviceCommand deviceUuid is null");
                    }
                }
            });
            LogUtil.a("BloodSugarSyncManager", "create mManager is ", this.f209a);
        }
        if (TextUtils.isEmpty(this.e.get())) {
            String c = this.f209a.c(null);
            LogUtil.a("BloodSugarSyncManager", "deviceUuid = ", c);
            if (TextUtils.isEmpty(c)) {
                return;
            }
            this.e.set(c);
            h();
            return;
        }
        h();
    }

    private JSONObject a(int i) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("version", "1");
        jSONObject.put("msgType", i);
        jSONObject.put("msgBody", new JSONObject());
        return jSONObject;
    }

    private void e(Runnable runnable) {
        this.h.execute(runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("BloodSugarSyncManager", "sync data");
        e(new Runnable() { // from class: ass
            @Override // java.lang.Runnable
            public final void run() {
                ask.this.d();
            }
        });
    }

    /* synthetic */ void d() {
        this.f209a.d(new PingCallback() { // from class: asm
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public final void onPingResult(int i) {
                ask.this.b(i);
            }
        });
    }

    /* synthetic */ void b(final int i) {
        LogUtil.a("BloodSugarSyncManager", "onPingResult code = ", Integer.valueOf(i));
        e(new Runnable() { // from class: asr
            @Override // java.lang.Runnable
            public final void run() {
                ask.this.c(i);
            }
        });
    }

    /* synthetic */ void c(int i) {
        if (i == 202) {
            c();
        }
    }

    private void c() {
        LogUtil.a("BloodSugarSyncManager", "sendSyncMessage");
        try {
            JSONObject a2 = a(1002);
            JSONObject jSONObject = a2.getJSONObject("msgBody");
            this.i = System.currentTimeMillis();
            jSONObject.put("time", this.i);
            jSONObject.put("triggerBloodSugar", true);
            this.f209a.e(a(a2));
            LogUtil.a("BloodSugarSyncManager", "sendSyncMessage time = ", Long.valueOf(this.i));
        } catch (UnsupportedEncodingException | JSONException unused) {
            LogUtil.b("BloodSugarSyncManager", "syncData JSONException or UnsupportedEncodingException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final byte[] bArr, final String str) {
        LogUtil.a("BloodSugarSyncManager", "execute parseAndRespond");
        e(new Runnable() { // from class: asn
            @Override // java.lang.Runnable
            public final void run() {
                ask.this.d(bArr, str);
            }
        });
    }

    /* synthetic */ void d(byte[] bArr, String str) {
        int b2 = b(bArr, str);
        LogUtil.c("BloodSugarSyncManager", "parseAndResponse resultCode = ", Integer.valueOf(b2), " deviceUuid ", str);
        if (b2 == 0) {
            c(true, str);
        } else if (b2 == 1) {
            c(false, str);
        } else {
            LogUtil.a("BloodSugarSyncManager", "do not respond msg");
        }
    }

    private void c(boolean z, String str) {
        String str2 = this.e.get();
        LogUtil.c("BloodSugarSyncManager", "responseAppCommand currentUuid is ", str2);
        if (str2 == null || !str2.equals(str)) {
            return;
        }
        b(z);
    }

    private int b(byte[] bArr, String str) {
        JSONObject jSONObject;
        String string;
        LogUtil.a("BloodSugarSyncManager", "execute parseMessage");
        int i = -1;
        try {
            jSONObject = new JSONObject(new String(bArr, "UTF-8"));
            string = jSONObject.getString("version");
        } catch (UnsupportedEncodingException | JSONException e) {
            LogUtil.b("BloodSugarSyncManager", e);
        }
        if (!TextUtils.isEmpty(string)) {
            LogUtil.c("BloodSugarSyncManager", "parseResponse json ", jSONObject.toString());
            if (string.hashCode() == 49 && string.equals("1")) {
                i = a(jSONObject, str);
                LogUtil.a("BloodSugarSyncManager", "execute parseMessage resultCode = ", Integer.valueOf(i));
                return i;
            }
            LogUtil.h("BloodSugarSyncManager", "parseResponse Unknown version, version = ", string);
            LogUtil.a("BloodSugarSyncManager", "execute parseMessage resultCode = ", Integer.valueOf(i));
            return i;
        }
        LogUtil.h("BloodSugarSyncManager", "parseResponse version = null");
        return -1;
    }

    private int a(JSONObject jSONObject, String str) {
        int i;
        JSONObject jSONObject2;
        LogUtil.a("BloodSugarSyncManager", "execute parseMessageVersionOne");
        try {
            jSONObject2 = jSONObject.getJSONObject("msgBody");
        } catch (JSONException e) {
            LogUtil.b("BloodSugarSyncManager", e);
        }
        if (TextUtils.isEmpty(jSONObject2.toString())) {
            LogUtil.h("BloodSugarSyncManager", "parseResponseDataVersionOne messageBody is empty");
        } else {
            int i2 = jSONObject.getInt("msgType");
            if (i2 == 1001) {
                i = b(jSONObject2);
            } else if (i2 == 1003) {
                i = e(jSONObject2, str);
            } else {
                LogUtil.h("BloodSugarSyncManager", "messageType is Unknown, messageType = ", Integer.valueOf(i2), " object: ", jSONObject.toString());
            }
            LogUtil.a("BloodSugarSyncManager", "execute parseMessageVersionOne resultCode = ", Integer.valueOf(i));
            return i;
        }
        i = -1;
        LogUtil.a("BloodSugarSyncManager", "execute parseMessageVersionOne resultCode = ", Integer.valueOf(i));
        return i;
    }

    private int b(JSONObject jSONObject) {
        int i;
        AtomicInteger atomicInteger;
        LogUtil.a("BloodSugarSyncManager", "execute parseThresholdDataVersionOne");
        try {
            CountDownLatch countDownLatch = new CountDownLatch(2);
            atomicInteger = new AtomicInteger(2);
            d("BLOOD_SUGAR_CONTINUE_MAX_THRESHOLD", jSONObject.getString("bloodSugarMaxTreshold"), countDownLatch, atomicInteger);
            d("BLOOD_SUGAR_CONTINUE_MIN_THRESHOLD", jSONObject.getString("bloodSugarMinTreshold"), countDownLatch, atomicInteger);
            countDownLatch.await();
        } catch (InterruptedException e) {
            LogUtil.b("BloodSugarSyncManager", e);
        } catch (JSONException e2) {
            LogUtil.b("BloodSugarSyncManager", e2);
            i = -1;
        }
        if (atomicInteger.get() == 0) {
            i = 0;
            LogUtil.a("BloodSugarSyncManager", "execute parseThresholdDataVersionOne resultCode = ", Integer.valueOf(i));
            return i;
        }
        i = 1;
        LogUtil.a("BloodSugarSyncManager", "execute parseThresholdDataVersionOne resultCode = ", Integer.valueOf(i));
        return i;
    }

    private int e(JSONObject jSONObject, String str) {
        int i;
        JSONArray optJSONArray;
        LogUtil.a("BloodSugarSyncManager", "execute parseBloodSugarDataVersionOne");
        int i2 = -1;
        try {
            i = jSONObject.getInt("type");
            LogUtil.a("BloodSugarSyncManager", "parseBloodSugarDataVersionOne type = ", Integer.valueOf(i));
            optJSONArray = jSONObject.optJSONArray("data");
        } catch (JSONException e) {
            LogUtil.b("BloodSugarSyncManager", e);
        }
        if (optJSONArray != null) {
            LogUtil.a("BloodSugarSyncManager", "bloodSugar length is ", Integer.valueOf(optJSONArray.length()));
            i2 = e(optJSONArray, i, str);
            LogUtil.a("BloodSugarSyncManager", "execute parseBloodSugarDataVersionOne resultCode = ", Integer.valueOf(i2));
            return i2;
        }
        LogUtil.a("BloodSugarSyncManager", "bloodSugar sync finish");
        a();
        return -1;
    }

    private int e(JSONArray jSONArray, int i, String str) {
        try {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i2);
                if (TextUtils.isEmpty(jSONObject.toString())) {
                    LogUtil.h("BloodSugarSyncManager", "insertBloodSugarData data is empty");
                    return -1;
                }
                long j = jSONObject.getLong("startTime");
                JSONObject jSONObject2 = jSONObject.getJSONObject("value");
                if (TextUtils.isEmpty(jSONObject2.toString())) {
                    LogUtil.h("BloodSugarSyncManager", "insertBloodSugarData value is empty");
                    return -1;
                }
                LogUtil.c("BloodSugarSyncManager", "TYPE_BLOOD_SUGAR_CONTINUE is inserting", ", time=", Long.valueOf(j), ", type=", Integer.valueOf(i), ", value=", jSONObject2);
                double d2 = jSONObject2.getDouble("bloodSugar");
                HiHealthData hiHealthData = new HiHealthData(2108);
                hiHealthData.setDeviceUuid(str);
                hiHealthData.setTimeInterval(j, j);
                hiHealthData.setType(i);
                hiHealthData.setValue(d2);
                HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
                hiBloodSugarMetaData.setConfirmed(true);
                hiHealthData.setMetaData(HiJsonUtil.e(hiBloodSugarMetaData));
                hiDataInsertOption.addData(hiHealthData);
            }
            long currentTimeMillis = System.currentTimeMillis();
            CountDownLatch countDownLatch = new CountDownLatch(1);
            AtomicInteger atomicInteger = new AtomicInteger(1);
            d(hiDataInsertOption, countDownLatch, atomicInteger);
            countDownLatch.await();
            LogUtil.a("BloodSugarSyncManager", "insertBatchData time = ", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
            if (atomicInteger.get() == 0) {
                return 0;
            }
        } catch (InterruptedException e) {
            LogUtil.b("BloodSugarSyncManager", e);
        } catch (JSONException e2) {
            LogUtil.b("BloodSugarSyncManager", e2);
            return -1;
        }
        return 1;
    }

    private void b(boolean z) {
        try {
            JSONObject a2 = a(900);
            a2.getJSONObject("msgBody").put("errcode", !z ? 1 : 0);
            this.f209a.e(a(a2));
            this.i = System.currentTimeMillis();
            LogUtil.c("BloodSugarSyncManager", "sendCommand 900 to response ", Boolean.valueOf(z));
        } catch (UnsupportedEncodingException | JSONException unused) {
            LogUtil.b("BloodSugarSyncManager", "responseCommand JSONException or UnsupportedEncodingException");
        }
    }

    private byte[] a(JSONObject jSONObject) throws UnsupportedEncodingException {
        return jSONObject.toString().getBytes("UTF-8");
    }

    private void d(final String str, final String str2, final CountDownLatch countDownLatch, final AtomicInteger atomicInteger) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: asq
            @Override // java.lang.Runnable
            public final void run() {
                ask.b(str, str2, atomicInteger, countDownLatch);
            }
        });
    }

    static /* synthetic */ void b(String str, String str2, AtomicInteger atomicInteger, CountDownLatch countDownLatch) {
        if (HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference(str, str2), true)) {
            LogUtil.c("BloodSugarSyncManager", "Succeeded to insertThreshold type = ", str, ", value = ", str2);
            atomicInteger.decrementAndGet();
        } else {
            LogUtil.h("BloodSugarSyncManager", "Failed to insertThreshold type = ", str, ", value = ", str2);
        }
        countDownLatch.countDown();
        ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).setPreference(str, str2);
    }

    private void d(HiDataInsertOption hiDataInsertOption, final CountDownLatch countDownLatch, final AtomicInteger atomicInteger) {
        HiHealthManager.d(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: aso
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                ask.c(atomicInteger, countDownLatch, i, obj);
            }
        });
    }

    static /* synthetic */ void c(AtomicInteger atomicInteger, CountDownLatch countDownLatch, int i, Object obj) {
        if (obj != null && i == 0) {
            LogUtil.a("BloodSugarSyncManager", "Succeeded to insertBatchData");
            atomicInteger.decrementAndGet();
        } else {
            LogUtil.h("BloodSugarSyncManager", "Failed to insertBatchData, errorCode=", Integer.valueOf(i));
        }
        countDownLatch.countDown();
    }

    private void a() {
        Intent intent = new Intent();
        intent.setClassName(this.c, "com.huawei.ui.main.stories.fitness.activity.coresleep.notification.HealthCommondReceiver");
        intent.setAction("ACTION_BLOOD_SUGAR_SYNC");
        BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }
}
