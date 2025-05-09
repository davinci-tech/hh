package com.huawei.hianalytics;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hianalytics.core.common.AesGcmKsWrapper;
import com.huawei.hianalytics.core.common.EnvUtils;
import com.huawei.hianalytics.core.log.HiLog;
import com.huawei.hianalytics.framework.config.DeviceAttributeCollector;
import com.huawei.hianalytics.process.HiAnalyticsInstance;
import com.huawei.hianalytics.process.HiAnalyticsManager;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes4.dex */
public class b implements DeviceAttributeCollector {

    /* renamed from: a, reason: collision with root package name */
    public final String f3834a;
    public final String b;

    public String a() {
        String str;
        String str2;
        m0 a2 = m0.a();
        String str3 = this.f3834a;
        String str4 = this.b;
        Context context = a2.f56a;
        b1 m552a = j.m552a(str3, str4);
        if (m552a == null || (str = m552a.c) == null) {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            b1 m552a2 = j.m552a(str3, str4);
            return (m552a2 == null || (str2 = m552a2.c) == null) ? "" : str2;
        }
        b1 m552a3 = j.m552a(str3, str4);
        if (m552a3 == null || !m552a3.f24d) {
            return "";
        }
        if (TextUtils.isEmpty(i.a().m550a().e)) {
            i.a().m550a().e = context != null ? Settings.Secure.getString(context.getContentResolver(), "android_id") : "";
        }
        return i.a().m550a().e;
    }

    public final String b() {
        if (!q0.f3896a.b) {
            return TextUtils.isEmpty(j.m556a(this.f3834a, this.b)) ? "" : j.m556a(this.f3834a, this.b);
        }
        String a2 = j.a("global_v2", c(), "");
        String decrypt = TextUtils.isEmpty(a2) ? "" : AesGcmKsWrapper.decrypt("HiAnalytics_Sdk_Public_Sp_Key", a2);
        if (TextUtils.isEmpty(decrypt)) {
            return decrypt;
        }
        Pair<String, Boolean> a3 = j.a(EnvUtils.getAppContext());
        if (decrypt.equals(a3.first)) {
            return decrypt;
        }
        String str = (String) a3.first;
        HiAnalyticsInstance instanceByTag = HiAnalyticsManager.getInstanceByTag("ha_default_collection");
        if (instanceByTag != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("oaid_before", decrypt);
                jSONObject.put("oaid_refresh", str);
                jSONObject.put("oaid_refresh_time", System.currentTimeMillis());
                instanceByTag.onEventSync(0, "107", jSONObject);
                instanceByTag.onReport(0);
            } catch (JSONException unused) {
                HiLog.e("HADC", "JSONException occur");
            }
        }
        j.m559a("global_v2", c(), AesGcmKsWrapper.encrypt("HiAnalytics_Sdk_Public_Sp_Key", (String) a3.first));
        return (String) a3.first;
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0217 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:25:0x01c0, B:27:0x01e4, B:28:0x01e8, B:32:0x01ff, B:34:0x0217, B:35:0x021b, B:39:0x0230, B:41:0x0240, B:43:0x0246, B:44:0x0248, B:47:0x0253, B:51:0x024f, B:52:0x0222, B:54:0x022c, B:56:0x01ef, B:58:0x01fb), top: B:24:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0246 A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:25:0x01c0, B:27:0x01e4, B:28:0x01e8, B:32:0x01ff, B:34:0x0217, B:35:0x021b, B:39:0x0230, B:41:0x0240, B:43:0x0246, B:44:0x0248, B:47:0x0253, B:51:0x024f, B:52:0x0222, B:54:0x022c, B:56:0x01ef, B:58:0x01fb), top: B:24:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x024f A[Catch: JSONException -> 0x0262, TryCatch #0 {JSONException -> 0x0262, blocks: (B:25:0x01c0, B:27:0x01e4, B:28:0x01e8, B:32:0x01ff, B:34:0x0217, B:35:0x021b, B:39:0x0230, B:41:0x0240, B:43:0x0246, B:44:0x0248, B:47:0x0253, B:51:0x024f, B:52:0x0222, B:54:0x022c, B:56:0x01ef, B:58:0x01fb), top: B:24:0x01c0 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x021a  */
    @Override // com.huawei.hianalytics.framework.config.DeviceAttributeCollector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject doCollector(org.json.JSONObject r14) {
        /*
            Method dump skipped, instructions count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hianalytics.b.doCollector(org.json.JSONObject):org.json.JSONObject");
    }

    /* renamed from: a, reason: collision with other method in class */
    public final JSONObject m545a() {
        a1 a2 = j.a(this.f3834a, this.b);
        String str = a2 != null ? a2.h : "";
        if (TextUtils.isEmpty(str)) {
            return new JSONObject();
        }
        try {
            return new JSONObject(str);
        } catch (Exception unused) {
            HiLog.e("HADC", "json exception, tag: " + this.f3834a + ", type: " + this.b);
            return new JSONObject();
        }
    }

    public final String c() {
        String str;
        int i;
        if (this.b.equals("oper")) {
            i = 0;
        } else {
            if (!this.b.equals("maint")) {
                str = "";
                return this.f3834a + "_" + str + "_oaid";
            }
            i = 1;
        }
        str = String.valueOf(i);
        return this.f3834a + "_" + str + "_oaid";
    }

    public b(String str, String str2) {
        this.f3834a = str;
        this.b = str2;
    }
}
