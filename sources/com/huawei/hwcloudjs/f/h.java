package com.huawei.hwcloudjs.f;

import android.content.Context;
import android.content.pm.PackageManager;
import java.io.Closeable;
import java.io.IOException;

/* loaded from: classes5.dex */
public class h {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6228a = "Utils";
    private static int b;

    public static boolean c(Context context, String str) {
        String str2;
        if (context == null || str == null) {
            str2 = "packageInstalled is null or context fail";
        } else {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null) {
                try {
                    packageManager.getPackageInfo(str, 16384);
                    return true;
                } catch (PackageManager.NameNotFoundException unused) {
                    d.b("Utils", "PackageManager NameNotFoundException ", true);
                }
            }
            str2 = "packageInstalled false";
        }
        d.b("Utils", str2, true);
        return false;
    }

    public static String b(Context context, String str) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    return packageManager.getPackageInfo(str, 16384).versionName;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                d.b("Utils", " NameNotFoundException .. ", true);
            }
        }
        return "";
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                d.b("Utils", "closeStream IOException", true);
            }
        }
    }

    public static int a(Context context, String str) {
        if (context != null) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null) {
                    return packageManager.getPackageInfo(str, 16384).versionCode;
                }
            } catch (PackageManager.NameNotFoundException unused) {
                d.b("Utils", " NameNotFoundException .. ", true);
            }
        }
        return 0;
    }

    public static int a() {
        int i = b;
        b = i + 1;
        return i;
    }
}
