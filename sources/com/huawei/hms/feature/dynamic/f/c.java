package com.huawei.hms.feature.dynamic.f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.hms.common.util.Logger;
import com.huawei.hms.feature.dynamic.ModuleCopy;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4522a = "HMSPkgManager";
    private static final List<String> b = new a();
    private static final int c = 8;
    private static final int d = 4;
    private static final int e = 5;

    public static boolean c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            Logger.w(f4522a, "context is null or pkgName is null.");
            return false;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
            boolean z = (packageInfo == null || (packageInfo.applicationInfo.flags & 1) == 0) ? false : true;
            Logger.i(f4522a, "isHMSSystemApp:" + z);
            if (z) {
                Logger.i(f4522a, "The HMS package:" + str + " is SystemApp");
                return true;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            Logger.e(f4522a, "getSystemApp flag error for " + str + ":" + e2.getMessage());
        }
        return false;
    }

    public static boolean b(Context context, String str) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        if (context == null || TextUtils.isEmpty(str)) {
            Logger.e(f4522a, "context is null or pkgName is null.");
            return false;
        }
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 16384);
        } catch (PackageManager.NameNotFoundException e2) {
            Logger.e(f4522a, "get PrivAppFlag err for " + str + ":" + e2.getMessage());
            packageInfo = null;
        }
        if (packageInfo == null || (applicationInfo = packageInfo.applicationInfo) == null) {
            Logger.i(f4522a, "Get pkg application null:" + str);
            return false;
        }
        try {
            Field field = applicationInfo.getClass().getField("privateFlags");
            AccessController.doPrivileged(new b(field));
            Object obj = field.get(applicationInfo);
            if (!(obj instanceof Integer)) {
                Logger.i(f4522a, "Get privFlag instance error.");
                return false;
            }
            int intValue = ((Integer) obj).intValue();
            Logger.d(f4522a, "privFlag of " + str + " is:" + intValue);
            boolean z = (intValue & 8) != 0;
            Logger.i(f4522a, "pkgName:" + str + ", isPrivApp:" + z);
            return z;
        } catch (IllegalAccessException | NoSuchFieldException e3) {
            Logger.e(f4522a, "get Priv App Flag err for " + str + ":" + e3.getMessage());
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null) {
            Logger.e(f4522a, "context is null.");
            return false;
        }
        if (ModuleCopy.isPathInvalid(str)) {
            return false;
        }
        try {
        } catch (IOException unused) {
            Logger.e(f4522a, "checkPathValidity IOException");
        }
        if (!new File(str).exists()) {
            Logger.w(f4522a, "the file does not exist.");
            return false;
        }
        String canonicalPath = new File(str).getCanonicalPath();
        if (canonicalPath.startsWith("/system/app/HFF")) {
            Logger.i(f4522a, "HFF file path, need not to verify.");
            return true;
        }
        if (canonicalPath.startsWith("/data/data/")) {
            String[] split = canonicalPath.split("/");
            if (split.length >= 4) {
                return b(context, split[3]);
            }
        } else {
            if (!canonicalPath.startsWith("/data/user_de/") && !canonicalPath.startsWith("/data/user/")) {
                Logger.w(f4522a, "illegal path.");
            }
            String[] split2 = canonicalPath.split("/");
            if (split2.length >= 5) {
                return b(context, split2[4]);
            }
        }
        return false;
    }

    public class b implements PrivilegedAction {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Field f4523a;

        @Override // java.security.PrivilegedAction
        public Object run() {
            this.f4523a.setAccessible(true);
            return null;
        }

        public b(Field field) {
            this.f4523a = field;
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            Logger.e(f4522a, "The given context is null.");
            return false;
        }
        for (String str : b) {
            try {
            } catch (PackageManager.NameNotFoundException unused) {
                Logger.w(f4522a, "Query for HMS Core package name:" + str + " failed.");
            }
            if (context.getPackageManager().getPackageInfo(str, 0) != null) {
                Logger.i(f4522a, "The HMS Core is installed, pkgName:" + str);
                return true;
            }
            continue;
        }
        return false;
    }

    public class a extends ArrayList<String> {
        public a() {
            add("com.huawei.hwid");
            add("com.huawei.hwid.tv");
        }
    }
}
