package com.huawei.hms.common.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import defpackage.ksy;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

/* loaded from: classes4.dex */
public class PresetUtil {
    public static boolean isSystemApp(Context context, String str) {
        PackageInfo packageInfo;
        boolean z = false;
        if (context == null) {
            ksy.c("PresetUtil", "Invalid context.", true);
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            ksy.b("PresetUtil", "Invalid pkgName.", true);
            return false;
        }
        ksy.a("PresetUtil", "pkgName: " + str, true);
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (PackageManager.NameNotFoundException e) {
            ksy.c("PresetUtil", "getSystemApp flag error for " + str + ":" + e.getMessage(), true);
            packageInfo = null;
        }
        if (packageInfo != null && (packageInfo.applicationInfo.flags & 1) != 0) {
            z = true;
        }
        ksy.a("PresetUtil", "isSystemApp: " + z, true);
        return z;
    }

    public static boolean isPrivApp(Context context, String str) {
        PackageInfo packageInfo;
        if (context == null) {
            ksy.c("PresetUtil", "Invalid context.", true);
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            ksy.c("PresetUtil", "Invalid pkgName.", true);
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (PackageManager.NameNotFoundException e) {
            ksy.c("PresetUtil", "getPrivAppFlag err for " + str + ":" + e.getMessage(), true);
            packageInfo = null;
        }
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            ksy.b("PresetUtil", "Get pkg application null:" + str, true);
            return false;
        }
        ApplicationInfo applicationInfo = packageInfo.applicationInfo;
        try {
            final Field field = applicationInfo.getClass().getField("privateFlags");
            AccessController.doPrivileged(new PrivilegedAction() { // from class: com.huawei.hms.common.utils.PresetUtil.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    field.setAccessible(true);
                    return null;
                }
            });
            Object obj = field.get(applicationInfo);
            if (!(obj instanceof Integer)) {
                ksy.b("PresetUtil", "Get privFlag instance error.", true);
                return false;
            }
            int intValue = ((Integer) obj).intValue();
            ksy.a("PresetUtil", "privFlag of " + str + " is:" + intValue, true);
            return (intValue & 8) != 0;
        } catch (IllegalAccessException | NoSuchFieldException e2) {
            ksy.c("PresetUtil", "getPrivAppFlag err for " + str + ":" + e2.getMessage(), true);
            return false;
        }
    }

    public static String getSystemAppPackage(Context context, Intent intent) {
        String str;
        if (context != null && intent != null) {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 65536);
            if (queryIntentActivities.size() == 0) {
                return "";
            }
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo != null && (str = resolveInfo.activityInfo.packageName) != null && (isSystemApp(context, str) || isPrivApp(context, str))) {
                    return str;
                }
            }
        }
        return "";
    }
}
