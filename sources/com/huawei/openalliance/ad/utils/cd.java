package com.huawei.openalliance.ad.utils;

import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.util.Arrays;

/* loaded from: classes5.dex */
public abstract class cd {
    private static boolean a() {
        return true;
    }

    public static boolean b(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (bg.a(strArr)) {
                return false;
            }
            return Arrays.asList(strArr).contains(str);
        } catch (Throwable unused) {
            ho.a("PermissionUtil", "get permission error");
            return false;
        }
    }

    public static boolean a(Context context, String[] strArr) {
        boolean z = true;
        for (String str : strArr) {
            if (!a(context, str)) {
                z = false;
            }
        }
        return z;
    }

    public static boolean a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            return a(context, str, context.getPackageName(), Process.myPid(), Process.myUid()) == 0;
        }
        ho.b("PermissionUtil", "hasPermission Invalid Input Param");
        return false;
    }

    public static boolean a(Context context) {
        return a(context, Build.VERSION.SDK_INT > 30 ? "android.permission.BLUETOOTH_CONNECT" : "android.permission.BLUETOOTH");
    }

    public static void a(Activity activity, String[] strArr, int i) {
        if (a()) {
            activity.requestPermissions(strArr, i);
        }
    }

    private static int a(Context context, String str, String str2, int i, int i2) {
        String permissionToOp;
        try {
            if (-1 == context.checkPermission(str, i, i2)) {
                return -1;
            }
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            if ((applicationInfo != null && applicationInfo.targetSdkVersion > 23) || (permissionToOp = AppOpsManager.permissionToOp(str)) == null) {
                return 0;
            }
            if (TextUtils.isEmpty(str2)) {
                String[] packagesForUid = context.getPackageManager().getPackagesForUid(i2);
                if (bg.a(packagesForUid)) {
                    return -1;
                }
                str2 = packagesForUid[0];
            }
            return ((AppOpsManager) context.getSystemService(AppOpsManager.class)).noteProxyOpNoThrow(permissionToOp, str2) != 0 ? -2 : 0;
        } catch (Throwable th) {
            ho.c("PermissionUtil", "validatePermission " + th.getClass().getSimpleName());
            return -1;
        }
    }
}
