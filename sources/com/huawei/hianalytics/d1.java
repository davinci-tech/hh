package com.huawei.hianalytics;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.core.storage.Event;
import com.huawei.hianalytics.framework.HAFrameworkInstance;
import com.huawei.hianalytics.framework.config.ICallback;
import com.huawei.hianalytics.framework.utils.JsonUtils;
import com.huawei.hianalytics.process.HaEvent;
import com.huawei.hianalytics.process.HiAnalyticsConfig;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.db.bean.EventMonitorRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class d1 implements HiAnalyticsInstance {

    /* renamed from: a, reason: collision with root package name */
    public long f3847a;

    /* renamed from: a, reason: collision with other field name */
    public e1 f26a = new e1();

    /* renamed from: a, reason: collision with other field name */
    public HAFrameworkInstance f27a;

    /* renamed from: a, reason: collision with other field name */
    public ICallback f28a;

    /* renamed from: a, reason: collision with other field name */
    public final String f29a;

    public final JSONObject a(LinkedHashMap<String, String> linkedHashMap) {
        LinkedHashMap<String, String> a2 = j.a(linkedHashMap, 10, 128L, 512L, "x_");
        return a2 == null ? new JSONObject() : g1.a(a2);
    }

    public void b(HiAnalyticsConfig hiAnalyticsConfig) {
        if (hiAnalyticsConfig != null) {
            this.f26a.b = hiAnalyticsConfig.f3894a;
        } else {
            this.f26a.b = null;
            HiLog.w("HAImpl", "config for oper is null, tag: " + this.f29a);
        }
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void disableAutoReport() {
        y0 a2 = y0.a();
        String str = this.f29a;
        a2.getClass();
        y0.f109a.remove(str);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public String getCollectUrl() {
        a1 a1Var = this.f26a.b;
        String str = a1Var != null ? a1Var.f14c : "";
        a1 a1Var2 = this.f26a.f3850a;
        String str2 = a1Var2 != null ? a1Var2.f14c : "";
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) ? "" : (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str.equals(str2)) ? TextUtils.isEmpty(str) ? str2 : str : "";
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public String getUDID(int i) {
        a1 a2 = a(i);
        if (a2 == null) {
            HiLog.sw("DeviceUtil", "config is null");
            return "";
        }
        b1 m544a = a2.m544a();
        String str = m544a.b;
        String str2 = str != null ? str : "";
        return (TextUtils.isEmpty(str2) && m544a.f23c) ? j.f() : str2;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public String getUUID(int i) {
        m0 a2 = m0.a();
        String str = this.f29a;
        String m548a = m548a(i);
        a2.getClass();
        return j.b(str, m548a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void newInstanceUUID() {
        String a2 = j.a("global_v2", this.f29a, "");
        if (TextUtils.isEmpty(a2)) {
            a2 = UUID.randomUUID().toString().replace(Constants.LINK, "");
            j.m559a("global_v2", this.f29a, a2);
        } else if (a2.length() > 32) {
            String decrypt = AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Uuid_Sp_Key", a2);
            if (!TextUtils.isEmpty(decrypt)) {
                j.m559a("global_v2", this.f29a, decrypt);
                a2 = decrypt;
            }
        }
        a1 a1Var = this.f26a.b;
        if (a1Var != null) {
            a1Var.f7a = a2;
        }
        a1 a1Var2 = this.f26a.f3850a;
        if (a1Var2 != null) {
            a1Var2.f7a = a2;
        }
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onBackground(long j) {
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(int i, List<HaEvent> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() > 100) {
            HiLog.w("HAImpl", "events size more than 100. tag: " + this.f29a + ", type: " + i);
            return;
        }
        ArrayList arrayList = new ArrayList(list);
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HaEvent haEvent = (HaEvent) it.next();
            if (haEvent != null && !(true ^ j.a(EventMonitorRecord.EVENT_ID, haEvent.f64a, 256))) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("event", haEvent.f64a);
                    jSONObject.put(Event.EventConstants.PROPERTIES, haEvent.f65a);
                    jSONObject.put(Event.EventConstants.RECORD_TIME, haEvent.f3893a);
                } catch (JSONException unused) {
                }
                jSONArray.put(jSONObject);
            }
        }
        if (jSONArray.length() == 0) {
            return;
        }
        if (jSONArray.length() == 1) {
            JSONObject optJSONObject = jSONArray.optJSONObject(0);
            onEvent(i, optJSONObject.optString("event"), optJSONObject.optJSONObject(Event.EventConstants.PROPERTIES));
        } else {
            JSONObject jSONObject2 = new JSONObject();
            JsonUtils.put(jSONObject2, Event.EventConstants.EVENTS, jSONArray);
            JsonUtils.put(jSONObject2, Event.EventConstants.SUB_COUNT, Integer.valueOf(jSONArray.length()));
            onEvent(i, Event.EventConstants.EVENT_ID_AGGREGATE, jSONObject2);
        }
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onForeground(long j) {
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onPause(Context context, LinkedHashMap<String, String> linkedHashMap) {
        if (context != null) {
            a(context.getClass().getCanonicalName(), linkedHashMap, "$AppOnPause", "OnPause");
            this.f3847a = 0L;
        } else {
            HiLog.sw("HAImpl", "context is null. tag: " + this.f29a);
        }
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onReport(int i) {
        HiLog.si("HAImpl", "onReport. tag:" + this.f29a + ", type:" + i);
        if (j1.f3879a.a()) {
            this.f27a.onReport(m548a(i));
            return;
        }
        HiLog.sw("HAImpl", "user locked. tag: " + this.f29a + ", type: " + i);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onResume(Context context, LinkedHashMap<String, String> linkedHashMap) {
        if (context != null) {
            a(context.getClass().getCanonicalName(), linkedHashMap, "$AppOnResume", "OnResume");
            return;
        }
        HiLog.sw("HAImpl", "context is null. tag: " + this.f29a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onStreamEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        JSONObject b = b(linkedHashMap);
        HiLog.si("HAImpl", "onStreamEvent. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onStreamEvent(m548a(i), str, b, this.f28a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void refresh(int i, HiAnalyticsConfig hiAnalyticsConfig) {
        HiAnalyticsConfig hiAnalyticsConfig2;
        d1 a2;
        if (hiAnalyticsConfig == null) {
            HiLog.si("HAImpl", "config is null. tag: " + this.f29a + ", type: " + i);
            hiAnalyticsConfig2 = null;
        } else {
            hiAnalyticsConfig2 = new HiAnalyticsConfig(hiAnalyticsConfig);
        }
        if (i == 0) {
            b(hiAnalyticsConfig2);
        } else if (i != 1) {
            HiLog.sw("HAImpl", "wrong type. tag: " + this.f29a);
        } else {
            a(hiAnalyticsConfig2);
        }
        if (hiAnalyticsConfig2 != null && hiAnalyticsConfig2.f3894a != null && (a2 = k.a().a("ha_default_collection")) != null) {
            HiAnalyticsConfig build = new HiAnalyticsConfig.Builder().setCollectURL(hiAnalyticsConfig2.f3894a.f14c).setHttpHeader(hiAnalyticsConfig2.f3894a.f8a).setAutoReportThresholdSize(100).build();
            if (i == 0) {
                a2.b(build);
            } else if (i != 1) {
                HiLog.i("HACU", "wrong type");
            } else {
                a2.a(build);
            }
        }
        j.a(i, hiAnalyticsConfig2);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setAccountBrandId(String str) {
        if (!j.a("accountBrandId", str, 256)) {
            str = "";
        }
        e1 a2 = i.a().a(this.f29a);
        if (a2 == null) {
            return;
        }
        a2.d = str;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setAppBrandId(String str) {
        if (!j.a("appBrandId", str, 256)) {
            str = "";
        }
        e1 a2 = i.a().a(this.f29a);
        if (a2 == null) {
            return;
        }
        a2.f33b = str;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setAppid(String str) {
        if (!j.m564a("appID", str, "[a-zA-Z0-9_][a-zA-Z0-9. _-]{0,255}")) {
            str = "";
        }
        e1 a2 = i.a().a(this.f29a);
        if (a2 == null) {
            return;
        }
        a2.f32a = str;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setCommonProp(int i, Map<String, String> map) {
        if (!j.a(map)) {
            HiLog.sw("HAImpl", "common prop error. tag: " + this.f29a + ", type: " + i);
            return;
        }
        JSONObject jSONObject = new JSONObject(map);
        a1 a2 = a(i);
        if (a2 != null) {
            a2.h = String.valueOf(jSONObject);
            return;
        }
        HiLog.sw("HAImpl", "no related config found. tag: " + this.f29a + ", type: " + i);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setHandsetManufacturer(String str) {
        if (!j.a("handsetManufacturer", str, 256)) {
            str = "";
        }
        e1 a2 = i.a().a(this.f29a);
        if (a2 == null) {
            return;
        }
        a2.e = str;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setHansetBrandId(String str) {
        if (!j.a("hansetBrandId", str, 256)) {
            str = "";
        }
        e1 a2 = i.a().a(this.f29a);
        if (a2 == null) {
            return;
        }
        a2.c = str;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setOAID(int i, String str) {
        a1 a2 = a(i);
        if (a2 == null) {
            HiLog.sw("HAImpl", "no related config found. tag: " + this.f29a + ", type: " + i);
            return;
        }
        if (!j.a("oaid", str, 4096)) {
            str = "";
        }
        a2.f16d = str;
        if (TextUtils.isEmpty(str)) {
            j.m559a("global_v2", this.f29a + "_" + i + "_oaid", "");
            return;
        }
        j.m559a("global_v2", this.f29a + "_" + i + "_oaid", AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", str));
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setOAIDTrackingFlag(int i, boolean z) {
        a1 a2 = a(i);
        if (a2 != null) {
            a2.f18e = z ? "true" : "false";
            return;
        }
        HiLog.sw("HAImpl", "no related config found. tag: " + this.f29a + ", type: " + i);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setUpid(int i, String str) {
        a1 a2 = a(i);
        if (a2 != null) {
            if (!j.a("upid", str, 4096)) {
                str = "";
            }
            a2.f19f = str;
        } else {
            HiLog.sw("HAImpl", "no related config found. tag: " + this.f29a + ", type: " + i);
        }
    }

    public final a1 a(int i) {
        if (i == 0) {
            return this.f26a.b;
        }
        if (i != 1) {
            return null;
        }
        return this.f26a.f3850a;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(String str, LinkedHashMap<String, String> linkedHashMap) {
        onEvent(0, str, b(linkedHashMap));
    }

    public final boolean a(int i, String str) {
        if (!j1.f3879a.a()) {
            HiLog.sw("HAImpl", "user locked. tag: " + this.f29a + ", type: " + i);
            return true;
        }
        if (!j.a(EventMonitorRecord.EVENT_ID, str, 256)) {
            HiLog.sw("HAImpl", "eventId error. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
            return true;
        }
        if (this.f27a != null) {
            return false;
        }
        HiLog.sw("HAImpl", "framework instance is null. tag:" + this.f29a + ", type:" + i);
        return true;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:5|(1:7)(2:25|(10:27|9|(1:11)|(1:13)(1:24)|14|15|(1:17)|18|19|20))|8|9|(0)|(0)(0)|14|15|(0)|18|19|20) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
    
        com.huawei.hianalytics.core.log.HiLog.e("BasicEvents", "json exception");
     */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0038  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004b A[Catch: JSONException -> 0x0056, TryCatch #0 {JSONException -> 0x0056, blocks: (B:15:0x0045, B:17:0x004b, B:18:0x0050), top: B:14:0x0045 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r7, java.util.LinkedHashMap<java.lang.String, java.lang.String> r8, java.lang.String r9, java.lang.String r10) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            if (r0 != 0) goto L62
            java.lang.String r0 = "viewName"
            java.lang.String r1 = "[a-zA-Z_][a-zA-Z0-9. _-]{0,255}"
            boolean r0 = com.huawei.hianalytics.j.m564a(r0, r7, r1)
            if (r0 != 0) goto L11
            goto L62
        L11:
            java.lang.String r0 = "OnResume"
            boolean r0 = r0.equals(r10)
            java.lang.String r1 = "OnPause"
            if (r0 == 0) goto L22
            long r2 = java.lang.System.currentTimeMillis()
            r6.f3847a = r2
            goto L30
        L22:
            boolean r0 = r1.equals(r10)
            if (r0 == 0) goto L30
            long r2 = java.lang.System.currentTimeMillis()
            long r4 = r6.f3847a
            long r2 = r2 - r4
            goto L32
        L30:
            r2 = 0
        L32:
            boolean r0 = com.huawei.hianalytics.j.a(r8)
            if (r0 != 0) goto L39
            r8 = 0
        L39:
            if (r8 != 0) goto L41
            org.json.JSONObject r8 = new org.json.JSONObject
            r8.<init>()
            goto L45
        L41:
            org.json.JSONObject r8 = com.huawei.hianalytics.g1.a(r8)
        L45:
            boolean r10 = r1.equals(r10)     // Catch: org.json.JSONException -> L56
            if (r10 == 0) goto L50
            java.lang.String r10 = "_event_duration"
            r8.put(r10, r2)     // Catch: org.json.JSONException -> L56
        L50:
            java.lang.String r10 = "_activity_name"
            r8.put(r10, r7)     // Catch: org.json.JSONException -> L56
            goto L5d
        L56:
            java.lang.String r7 = "BasicEvents"
            java.lang.String r10 = "json exception"
            com.huawei.hianalytics.core.log.HiLog.e(r7, r10)
        L5d:
            r7 = 0
            r6.onEvent(r7, r9, r8)
            return
        L62:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r8 = "viewName error. tag: "
            r7.<init>(r8)
            java.lang.String r8 = r6.f29a
            r7.append(r8)
            java.lang.String r8 = ", type: "
            r7.append(r8)
            r7.append(r10)
            java.lang.String r7 = r7.toString()
            java.lang.String r8 = "HAImpl"
            com.huawei.hianalytics.core.log.HiLog.sw(r8, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.d1.a(java.lang.String, java.util.LinkedHashMap, java.lang.String, java.lang.String):void");
    }

    public void a(HiAnalyticsConfig hiAnalyticsConfig) {
        if (hiAnalyticsConfig == null) {
            HiLog.w("HAImpl", "config for maint is null, tag: " + this.f29a);
            this.f26a.f3850a = null;
            return;
        }
        this.f26a.f3850a = hiAnalyticsConfig.f3894a;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void setCallback(ICallback iCallback) {
        if (iCallback == null) {
            return;
        }
        this.f28a = iCallback;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onStreamEvent(int i, String str, JSONObject jSONObject) {
        HiLog.si("HAImpl", "onStreamEvent. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onStreamEvent(m548a(i), str, jSONObject, this.f28a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onStreamEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, LinkedHashMap<String, String> linkedHashMap3) {
        HiLog.si("HAImpl", "onStreamEvent Ex. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onStreamEvent(m548a(i), str, b(linkedHashMap), a(linkedHashMap2), a(linkedHashMap3), this.f28a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onResume(String str, LinkedHashMap<String, String> linkedHashMap) {
        a(str, linkedHashMap, "$AppOnResume", "OnResume");
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onResume(Context context) {
        onResume(context, (LinkedHashMap<String, String>) null);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onReport(Context context, int i) {
        onReport(i);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onPause(String str, LinkedHashMap<String, String> linkedHashMap) {
        a(str, linkedHashMap, "$AppOnPause", "OnPause");
        this.f3847a = 0L;
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onPause(Context context) {
        onPause(context, (LinkedHashMap<String, String>) null);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEventSync(int i, String str, JSONObject jSONObject) {
        HiLog.si("HAImpl", "onEventSync. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onEventSync(m548a(i), str, jSONObject);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(Context context, String str, String str2) {
        if (!j.a("value", str2, 65536)) {
            HiLog.sw("HAImpl", "value error. tag: " + this.f29a);
            str2 = "";
        }
        JSONObject jSONObject = new JSONObject();
        JsonUtils.put(jSONObject, "_constants", str2);
        onEvent(0, str, jSONObject);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(int i, String str, JSONObject jSONObject) {
        HiLog.si("HAImpl", "onEvent. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onEvent(m548a(i), str, jSONObject, this.f28a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap, LinkedHashMap<String, String> linkedHashMap2, LinkedHashMap<String, String> linkedHashMap3) {
        HiLog.si("HAImpl", "onEventEx. tag:" + this.f29a + ", type:" + i + ", eventId:" + str);
        if (a(i, str)) {
            return;
        }
        this.f27a.onEvent(m548a(i), str, b(linkedHashMap), a(linkedHashMap2), a(linkedHashMap3), this.f28a);
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void onEvent(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        onEvent(i, str, b(linkedHashMap));
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public String getOAID(int i) {
        return j.m556a(this.f29a, m548a(i));
    }

    @Override // com.huawei.hianalytics.process.HiAnalyticsInstance
    public void clearData() {
        this.f27a.clearCacheDataByTag();
    }

    public final JSONObject b(LinkedHashMap<String, String> linkedHashMap) {
        return j.a(linkedHashMap) ? g1.a(linkedHashMap) : new JSONObject();
    }

    /* renamed from: a, reason: collision with other method in class */
    public final String m548a(int i) {
        return i != 0 ? i != 1 ? "allType" : "maint" : "oper";
    }

    public d1(String str) {
        this.f29a = str;
        try {
            this.f27a = new HAFrameworkInstance.Builder().setStorageHandler(n.a(EnvUtils.getAppContext())).setStoragePolicy(new e(str)).setCollectorConfig(new a(str)).setMandatoryParameters(new d()).build(str);
        } catch (Exception unused) {
            HiLog.e("HAImpl", "init HAImpl create frameworkInstance failed");
        }
    }
}
