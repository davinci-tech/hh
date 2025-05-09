package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import defpackage.ik;
import defpackage.ip;
import defpackage.mi;
import defpackage.mq;
import java.util.UUID;

/* loaded from: classes7.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    public static String f843a = "";

    public static long h(Context context, String str) {
        try {
            String c = ik.c(context, "vkeyid_settings", "vkey_valid" + str);
            if (mq.e(c)) {
                return 0L;
            }
            return Long.parseLong(c);
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static void g(Context context, String str) {
        a(context, "apse_degrade", str);
    }

    public static void f(Context context, String str) {
        a(context, "webrtc_url", str);
    }

    public static String f(Context context) {
        String str;
        SharedPreferences.Editor edit;
        synchronized (h.class) {
            if (mq.e(f843a)) {
                String e = ip.e(context, "alipay_vkey_random", "random", "");
                f843a = e;
                if (mq.e(e)) {
                    String b = mi.b(UUID.randomUUID().toString());
                    f843a = b;
                    if (b != null && (edit = context.getSharedPreferences("alipay_vkey_random", 0).edit()) != null) {
                        edit.putString("random", b);
                        edit.commit();
                    }
                }
            }
            str = f843a;
        }
        return str;
    }

    public static void e(Context context, String str) {
        a(context, "dynamic_key", str);
    }

    public static String e(Context context) {
        return ik.c(context, "vkeyid_settings", "apse_degrade");
    }

    public static void d(Context context, String str) {
        a(context, "agent_switch", str);
    }

    public static String d(Context context) {
        return ik.c(context, "vkeyid_settings", "dynamic_key");
    }

    public static boolean c(Context context) {
        String c = ik.c(context, "vkeyid_settings", "log_switch");
        return c != null && "1".equals(c);
    }

    public static void c(Context context, String str) {
        a(context, "last_apdid_env", str);
    }

    public static void b(Context context, String str) {
        a(context, "last_machine_boot_time", str);
    }

    public static String b(Context context) {
        return ik.c(context, "vkeyid_settings", "last_apdid_env");
    }

    public static void a(Context context, boolean z) {
        a(context, "log_switch", z ? "1" : "0");
    }

    public static void a(Context context, String str, String str2) {
        ik.c(context, "vkeyid_settings", str, str2);
    }

    public static void a(Context context, String str, long j) {
        ik.c(context, "vkeyid_settings", "vkey_valid" + str, String.valueOf(j));
    }

    public static void a(Context context, String str) {
        a(context, "update_time_interval", str);
    }

    public static long a(Context context) {
        String c = ik.c(context, "vkeyid_settings", "update_time_interval");
        if (mq.b(c)) {
            try {
                return Long.parseLong(c);
            } catch (Exception unused) {
            }
        }
        return 86400000L;
    }
}
