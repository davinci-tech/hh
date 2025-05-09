package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import defpackage.mq;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public final class a {
    public static b c(Context context) {
        synchronized (a.class) {
            String a2 = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid");
            if (mq.e(a2)) {
                return null;
            }
            return a(a2);
        }
    }

    public static b b(Context context) {
        b a2;
        synchronized (a.class) {
            String a3 = com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid");
            if (mq.e(a3)) {
                a3 = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx");
            }
            a2 = a(a3);
        }
        return a2;
    }

    public static b b() {
        synchronized (a.class) {
            String a2 = com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx");
            if (mq.e(a2)) {
                return null;
            }
            return a(a2);
        }
    }

    public static void a(Context context, b bVar) {
        synchronized (a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", bVar.f840a);
                jSONObject.put("deviceInfoHash", bVar.b);
                jSONObject.put("timestamp", bVar.c);
                String jSONObject2 = jSONObject.toString();
                com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid", jSONObject2);
                com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx", jSONObject2);
            } catch (Exception e) {
                com.alipay.apmobilesecuritysdk.c.a.a(e);
            }
        }
    }

    public static void a(Context context) {
        synchronized (a.class) {
            com.alipay.apmobilesecuritysdk.f.a.a(context, "vkeyid_profiles_v3", "deviceid", "");
            com.alipay.apmobilesecuritysdk.f.a.a("wxcasxx_v3", "wxcasxx", "");
        }
    }

    public static void a() {
        synchronized (a.class) {
        }
    }

    public static b a(String str) {
        try {
            if (!mq.e(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"));
            }
        } catch (Exception e) {
            com.alipay.apmobilesecuritysdk.c.a.a(e);
        }
        return null;
    }
}
