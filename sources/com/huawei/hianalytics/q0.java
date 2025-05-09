package com.huawei.hianalytics;

import android.text.TextUtils;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.kit.HiAnalyticsClientImpl;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import java.util.LinkedHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class q0 {

    /* renamed from: a, reason: collision with root package name */
    public static final q0 f3896a = new q0();

    /* renamed from: a, reason: collision with other field name */
    public long f68a = 86400000;

    /* renamed from: a, reason: collision with other field name */
    public boolean f69a;
    public boolean b;

    public final void a() {
        synchronized (this) {
            String a2 = j.a("global_v2", "ha_config", "");
            if (TextUtils.isEmpty(a2)) {
                HiLog.i("ConfigHandler", "ha config is empty");
                this.f68a = 86400000L;
                this.f69a = false;
                this.b = false;
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(a2);
                this.f68a = jSONObject.optLong("requestInterval", 86400000L);
                this.f69a = jSONObject.optBoolean("enableCheckTime", false);
                this.b = jSONObject.optBoolean("enableCheckOaid", false);
            } catch (JSONException unused) {
                this.f68a = 86400000L;
                this.f69a = false;
                this.b = false;
                HiLog.w("ConfigHandler", "ha config failed");
            }
        }
    }

    public void b() {
        synchronized (this) {
            HiLog.i("ConfigHandler", "start requestConfig");
            long a2 = j.a("global_v2", "ha_config_last_request_time", 0L);
            if (a2 > 0 && System.currentTimeMillis() - a2 <= this.f68a) {
                HiLog.i("ConfigHandler", "within request interval");
                return;
            }
            if (!j.m573f()) {
                a(401);
                return;
            }
            if (!j.h()) {
                HiLog.d("ConfigHandler", "not phone or pad");
                return;
            }
            if (!j.m566b()) {
                HiLog.d("ConfigHandler", "not China Rom");
                return;
            }
            if (!x0.a(true)) {
                a(402);
                return;
            }
            if (EnvUtils.getAppContext() == null) {
                a(403);
                return;
            }
            try {
                HiAnalyticsClientImpl hiAnalyticsClientImpl = new HiAnalyticsClientImpl(EnvUtils.getAppContext(), new v0());
                hiAnalyticsClientImpl.setApiLevel(1);
                hiAnalyticsClientImpl.setKitSdkVersion(302130501);
                hiAnalyticsClientImpl.getHaConfig(new s0("get_ha_config", j.c())).addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hianalytics.q0$$ExternalSyntheticLambda0
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public final void onFailure(Exception exc) {
                        HiLog.w("ConfigHandler", "request ha config failed");
                    }
                }).addOnSuccessListener(new OnSuccessListener() { // from class: com.huawei.hianalytics.q0$$ExternalSyntheticLambda1
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    public final void onSuccess(Object obj) {
                        q0.this.a((w0) obj);
                    }
                }).addOnCompleteListener(new OnCompleteListener() { // from class: com.huawei.hianalytics.q0$$ExternalSyntheticLambda2
                    @Override // com.huawei.hmf.tasks.OnCompleteListener
                    public final void onComplete(Task task) {
                        q0.this.a(task);
                    }
                });
            } catch (Throwable th) {
                HiLog.e("ConfigHandler", "request ha config error: " + th.getClass().getSimpleName());
            }
            j.m558a("global_v2", "ha_config_last_request_time", System.currentTimeMillis());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(w0 w0Var) {
        String str;
        String str2 = w0Var.f3959a;
        if (TextUtils.isEmpty(str2)) {
            HiLog.i("ConfigHandler", "request ha config failed, rsp is empty");
        }
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.optInt("status") != 200) {
                str = "request ha config status code 500";
            } else {
                this.f68a = jSONObject.optLong("requestInterval", 86400000L);
                this.f69a = jSONObject.optBoolean("enableCheckTime", false);
                this.b = jSONObject.optBoolean("enableCheckOaid", false);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("requestInterval", this.f68a);
                jSONObject2.put("enableCheckTime", this.f69a);
                jSONObject2.put("enableCheckOaid", this.b);
                j.m559a("global_v2", "ha_config", jSONObject2.toString());
                str = "request ha config success";
            }
            HiLog.i("ConfigHandler", str);
        } catch (JSONException unused) {
            HiLog.w("ConfigHandler", "request ha config failed, exception occur");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Task task) {
        JSONObject jSONObject = new JSONObject();
        try {
            if (task.isSuccessful()) {
                jSONObject = new JSONObject(((w0) task.getResult()).f3959a);
            } else {
                jSONObject.put("status", task.getException().getMessage());
            }
        } catch (JSONException unused) {
            HiLog.e("ConfigHandler", "report request ha config");
        }
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
        if (instanceByTag == null) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        if (TextUtils.isEmpty(jSONObject.optString("message"))) {
            linkedHashMap.put("$code", jSONObject.optString("status"));
        } else {
            linkedHashMap.put("$code", String.valueOf(jSONObject.optInt("status")));
            linkedHashMap.put("$message", jSONObject.optString("message"));
        }
        instanceByTag.onEvent(1, "$request_ha_config", linkedHashMap);
    }

    public final void a(int i) {
        String str;
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
        if (instanceByTag == null) {
            return;
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("$code", String.valueOf(i));
        switch (i) {
            case 401:
                str = "QoES request failed because device is not Huawei device";
                break;
            case 402:
                str = "QoES request failed because hms base is not exist";
                break;
            case 403:
                str = "QoES request failed because context is null";
                break;
        }
        linkedHashMap.put("$message", str);
        instanceByTag.onEvent(1, "$request_ha_config", linkedHashMap);
    }

    public q0() {
        a();
    }
}
