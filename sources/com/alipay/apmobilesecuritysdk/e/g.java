package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import android.content.SharedPreferences;
import defpackage.ip;
import defpackage.mm;
import defpackage.mq;

/* loaded from: classes7.dex */
public final class g {
    public static void a(Context context, String str, String str2) {
        synchronized (g.class) {
            try {
                SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, mm.c(mm.b(), str2));
                    edit.commit();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Context context) {
        synchronized (g.class) {
            SharedPreferences.Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    public static void a() {
        synchronized (g.class) {
        }
    }

    public static String a(Context context, String str) {
        synchronized (g.class) {
            String e = ip.e(context, "openapi_file_pri", "openApi" + str, "");
            if (mq.e(e)) {
                return "";
            }
            String a2 = mm.a(mm.b(), e);
            return mq.e(a2) ? "" : a2;
        }
    }
}
