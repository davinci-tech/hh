package com.alipay.sdk.m.p;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.hms.network.base.common.trans.FileBinary;
import defpackage.kg;
import defpackage.kl;
import defpackage.kv;
import defpackage.ld;
import defpackage.lf;
import defpackage.lh;
import defpackage.lj;
import defpackage.lk;
import defpackage.lu;
import defpackage.lv;
import defpackage.lw;
import defpackage.lx;
import defpackage.ma;
import defpackage.md;
import defpackage.mf;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public abstract class e {
    public static final String c = "msp-gzip";
    public static final String d = "Msp-Param";
    public static final String e = "Operation-Type";
    public static final String f = "content-type";
    public static final String g = "Version";
    public static final String h = "AppId";
    public static final String i = "des-mode";
    public static final String j = "namespace";
    public static final String k = "api_name";
    public static final String l = "api_version";
    public static final String m = "data";
    public static final String n = "params";
    public static final String o = "public_key";
    public static final String p = "device";
    public static final String q = "action";
    public static final String r = "type";
    public static final String s = "method";

    /* renamed from: a, reason: collision with root package name */
    public boolean f866a = true;
    public boolean b = true;

    public Map<String, String> a(boolean z, String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(c, String.valueOf(z));
        hashMap.put(e, "alipay.msp.cashier.dispatch.bytes");
        hashMap.put(f, FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
        hashMap.put(g, "2.0");
        hashMap.put(h, "TAOBAO");
        hashMap.put(d, ld.c(str));
        hashMap.put(i, "CBC");
        return hashMap;
    }

    public abstract JSONObject a() throws JSONException;

    public abstract boolean c();

    public String a(lv lvVar) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("device", Build.MODEL);
        hashMap.put(j, "com.alipay.mobilecashier");
        hashMap.put("api_name", "com.alipay.mcpay");
        hashMap.put(l, b());
        return a(lvVar, hashMap, new HashMap<>());
    }

    public static JSONObject a(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("type", str);
        jSONObject2.put("method", str2);
        jSONObject.put("action", jSONObject2);
        return jSONObject;
    }

    public String a(lv lvVar, String str, JSONObject jSONObject) {
        lw c2 = lw.c();
        lu b = lu.b(c2.d());
        JSONObject c3 = lx.c(new JSONObject(), jSONObject);
        try {
            c3.put("external_info", str);
            c3.put("tid", b.a());
            c3.put("user_agent", c2.a().e(lvVar, b, c()));
            c3.put("has_alipay", md.e(lvVar, c2.d(), kg.d, false));
            c3.put("has_msp_app", md.i(c2.d()));
            c3.put("app_key", "2014052600006128");
            c3.put("utdid", c2.e());
            c3.put("new_client_key", b.e());
            c3.put("pa", kv.c(c2.d()));
        } catch (Throwable th) {
            kl.e(lvVar, "biz", "BodyErr", th);
            ma.c(th);
        }
        return c3.toString();
    }

    public static boolean a(lf.b bVar) {
        return Boolean.valueOf(a(bVar, c)).booleanValue();
    }

    public static String a(lf.b bVar, String str) {
        Map<String, List<String>> map;
        List<String> list;
        if (bVar == null || str == null || (map = bVar.b) == null || (list = map.get(str)) == null) {
            return null;
        }
        return TextUtils.join(",", list);
    }

    public String a(lv lvVar, HashMap<String, String> hashMap, HashMap<String, String> hashMap2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        if (hashMap != null) {
            for (Map.Entry<String, String> entry : hashMap.entrySet()) {
                jSONObject2.put(entry.getKey(), entry.getValue());
            }
        }
        if (hashMap2 != null) {
            JSONObject jSONObject3 = new JSONObject();
            for (Map.Entry<String, String> entry2 : hashMap2.entrySet()) {
                jSONObject3.put(entry2.getKey(), entry2.getValue());
            }
            jSONObject2.put(n, jSONObject3);
        }
        jSONObject.put("data", jSONObject2);
        return jSONObject.toString();
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str).getJSONObject("data");
            if (!jSONObject.has(n)) {
                return false;
            }
            String optString = jSONObject.getJSONObject(n).optString(o, null);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            kv.b(optString);
            return true;
        } catch (JSONException e2) {
            ma.c(e2);
            return false;
        }
    }

    public lh a(lv lvVar, Context context) throws Throwable {
        return a(lvVar, context, "");
    }

    public lh a(lv lvVar, Context context, String str) throws Throwable {
        return a(lvVar, context, str, mf.a(context));
    }

    public lh a(lv lvVar, Context context, String str, String str2) throws Throwable {
        return a(lvVar, context, str, str2, true);
    }

    public lh a(lv lvVar, Context context, String str, String str2, boolean z) throws Throwable {
        ma.a("mspl", "Packet: " + str2);
        lj ljVar = new lj(this.b);
        lh lhVar = new lh(a(lvVar), a(lvVar, str, a()));
        Map<String, String> a2 = a(false, str);
        lk b = ljVar.b(lhVar, this.f866a, a2.get("iSr"));
        lf.b d2 = lf.d(context, new lf.a(str2, a(b.e(), str), b.a()));
        if (d2 != null) {
            lh e2 = ljVar.e(new lk(a(d2), d2.c), a2.get("iSr"));
            return (e2 != null && a(e2.c()) && z) ? a(lvVar, context, str, str2, false) : e2;
        }
        throw new RuntimeException("Response is null.");
    }

    public String b() {
        return "4.9.0";
    }
}
