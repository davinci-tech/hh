package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import defpackage.mq;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class d {
    public static c c(Context context) {
        synchronized (d.class) {
            String a2 = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (mq.e(a2)) {
                return null;
            }
            return a(a2);
        }
    }

    public static c b(Context context) {
        c a2;
        synchronized (d.class) {
            String a3 = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (mq.e(a3)) {
                a3 = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            a2 = a(a3);
        }
        return a2;
    }

    public static c b() {
        synchronized (d.class) {
            String a2 = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4");
            if (mq.e(a2)) {
                return null;
            }
            return a(a2);
        }
    }

    public static void a(Context context, c cVar) {
        synchronized (d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", cVar.f841a);
                jSONObject.put("deviceInfoHash", cVar.b);
                jSONObject.put("timestamp", cVar.c);
                jSONObject.put("tid", cVar.d);
                jSONObject.put("utdid", cVar.e);
                String jSONObject2 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", jSONObject2);
                com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4", jSONObject2);
            } catch (Exception e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
    }

    public static void a(Context context) {
        synchronized (d.class) {
            com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }

    public static void a() {
        synchronized (d.class) {
        }
    }

    public static c a(String str) {
        try {
            if (!mq.e(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"), jSONObject.optString("tid"), jSONObject.optString("utdid"));
            }
        } catch (Exception e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return null;
    }
}
