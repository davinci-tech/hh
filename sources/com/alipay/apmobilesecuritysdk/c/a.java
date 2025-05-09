package com.alipay.apmobilesecuritysdk.c;

import android.content.Context;
import android.os.Build;
import defpackage.iv;
import defpackage.ix;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes7.dex */
public final class a {
    public static iv b(Context context, String str, String str2, String str3) {
        String str4;
        try {
            str4 = context.getPackageName();
        } catch (Throwable unused) {
            str4 = "";
        }
        return new iv(Build.MODEL, str4, "APPSecuritySDK-ALIPAYSDK", "3.4.0.202303020703", str, str2, str3);
    }

    public static void a(Throwable th) {
        synchronized (a.class) {
            ix.d(th);
        }
    }

    public static void a(String str) {
        synchronized (a.class) {
            ix.a(str);
        }
    }

    public static void a(Context context, String str, String str2, String str3) {
        synchronized (a.class) {
            iv b = b(context, str, str2, str3);
            ix.c(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", b.toString());
        }
    }
}
