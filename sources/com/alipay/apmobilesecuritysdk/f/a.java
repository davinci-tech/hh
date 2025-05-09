package com.alipay.apmobilesecuritysdk.f;

import android.content.Context;
import android.os.Environment;
import defpackage.ig;
import defpackage.ij;
import defpackage.ip;
import defpackage.mm;
import defpackage.mq;
import java.io.File;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class a {
    public static void a(String str, String str2, String str3) {
        synchronized (a.class) {
            if (mq.e(str) || mq.e(str2)) {
                return;
            }
            try {
                String e = ig.e(str);
                JSONObject jSONObject = new JSONObject();
                if (mq.b(e)) {
                    try {
                        jSONObject = new JSONObject(e);
                    } catch (Exception unused) {
                        jSONObject = new JSONObject();
                    }
                }
                jSONObject.put(str2, mm.c(mm.b(), str3));
                jSONObject.toString();
                try {
                    System.clearProperty(str);
                } catch (Throwable unused2) {
                }
                if (ij.e()) {
                    String str4 = ".SystemConfig" + File.separator + str;
                    if (ij.e()) {
                        File file = new File(Environment.getExternalStorageDirectory(), str4);
                        if (file.exists() && file.isFile()) {
                            file.delete();
                        }
                    }
                }
            } catch (Throwable unused3) {
            }
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        if (mq.e(str) || mq.e(str2) || context == null) {
            return;
        }
        try {
            String c = mm.c(mm.b(), str3);
            HashMap hashMap = new HashMap();
            hashMap.put(str2, c);
            ip.b(context, str, hashMap);
        } catch (Throwable unused) {
        }
    }

    public static String a(String str, String str2) {
        synchronized (a.class) {
            if (mq.e(str) || mq.e(str2)) {
                return null;
            }
            try {
                String e = ig.e(str);
                if (mq.e(e)) {
                    return null;
                }
                String string = new JSONObject(e).getString(str2);
                if (mq.e(string)) {
                    return null;
                }
                return mm.a(mm.b(), string);
            } catch (Throwable unused) {
                return null;
            }
        }
    }

    public static String a(Context context, String str, String str2) {
        if (context == null || mq.e(str) || mq.e(str2)) {
            return null;
        }
        try {
            String e = ip.e(context, str, str2, "");
            if (mq.e(e)) {
                return null;
            }
            return mm.a(mm.b(), e);
        } catch (Throwable unused) {
            return null;
        }
    }
}
