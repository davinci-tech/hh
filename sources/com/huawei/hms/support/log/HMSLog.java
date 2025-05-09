package com.huawei.hms.support.log;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.AndroidException;
import com.huawei.hms.base.log.a;
import com.huawei.hms.base.log.b;
import com.huawei.operation.utils.Constants;

/* loaded from: classes.dex */
public class HMSLog {

    /* renamed from: a, reason: collision with root package name */
    private static final b f6032a = new b();

    private static String a(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "HMS-[unknown-version]";
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 16384);
            return "HMS-" + packageInfo.versionName + Constants.LEFT_BRACKET_ONLY + packageInfo.versionCode + Constants.RIGHT_BRACKET_ONLY;
        } catch (AndroidException | RuntimeException unused) {
            return "HMS-[unknown-version]";
        }
    }

    public static void d(String str, String str2) {
        f6032a.a(3, str, str2);
    }

    public static void e(String str, String str2) {
        f6032a.a(6, str, str2);
    }

    public static void i(String str, String str2) {
        f6032a.a(4, str, str2);
    }

    public static void init(Context context, int i, String str) {
        b bVar = f6032a;
        bVar.a(context, i, str);
        bVar.a(str, "============================================================================\n====== " + a(context) + "\n============================================================================");
    }

    public static boolean isErrorEnable() {
        return f6032a.a(6);
    }

    public static boolean isInfoEnable() {
        return f6032a.a(4);
    }

    public static boolean isWarnEnable() {
        return f6032a.a(5);
    }

    public static void setExtLogger(HMSExtLogger hMSExtLogger, boolean z) throws IllegalArgumentException {
        if (hMSExtLogger == null) {
            throw new IllegalArgumentException("extLogger is not able to be null");
        }
        a aVar = new a(hMSExtLogger);
        if (z) {
            f6032a.a(aVar);
        } else {
            f6032a.a().a(aVar);
        }
    }

    public static void w(String str, String str2) {
        f6032a.a(5, str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        f6032a.b(6, str, str2, th);
    }

    public static void e(String str, long j, String str2) {
        f6032a.a(6, str, "[" + j + "] " + str2);
    }

    public static void e(String str, long j, String str2, Throwable th) {
        f6032a.b(6, str, "[" + j + "] " + str2, th);
    }
}
