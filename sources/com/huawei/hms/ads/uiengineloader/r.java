package com.huawei.hms.ads.uiengineloader;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class r {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4391a = "r";
    private static final String b = "presplits";
    private static final String c = ",";

    private static Set<q> b(Context context, String str) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (Throwable th) {
            af.d(f4391a, "getMetaSplits:cannot find the package:" + str + "info." + th.getClass().getSimpleName());
            applicationInfo = null;
        }
        return a(context, applicationInfo, str);
    }

    public static Set<q> a(Context context, ApplicationInfo applicationInfo, String str) {
        HashSet hashSet = new HashSet();
        if (context != null && applicationInfo != null) {
            try {
                if (applicationInfo.metaData != null) {
                    String string = applicationInfo.metaData.getString(b);
                    if (TextUtils.isEmpty(string)) {
                        af.b(f4391a, "No metadata: presplits found.");
                        return hashSet;
                    }
                    String[] split = string.split(",");
                    HashMap<String, String> a2 = a(context, str);
                    if (split.length != 0 && !a2.isEmpty()) {
                        for (String str2 : split) {
                            for (Map.Entry<String, String> entry : a2.entrySet()) {
                                if (str2.equals(entry.getKey())) {
                                    hashSet.add(new q(new File(entry.getValue()), entry.getKey()));
                                }
                            }
                        }
                    }
                    return hashSet;
                }
            } catch (Throwable th) {
                af.c(f4391a, "getSplitsInfo err: " + th.getClass().getSimpleName());
            }
        }
        return hashSet;
    }

    private static HashMap<String, String> a(Context context, String str) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo;
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 128);
            applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
        } catch (PackageManager.NameNotFoundException e) {
            af.c(f4391a, "getSourceDir:cannot find the package:" + str + " info." + e.getClass().getSimpleName());
        } catch (Throwable th) {
            af.c(f4391a, "getSourceDir ex: " + th.getClass().getSimpleName());
        }
        if (packageInfo.splitNames != null && applicationInfo.splitSourceDirs != null) {
            int min = Math.min(packageInfo.splitNames.length, applicationInfo.splitSourceDirs.length);
            for (int i = 0; i < min; i++) {
                hashMap.put(packageInfo.splitNames[i], applicationInfo.splitSourceDirs[i]);
            }
            return hashMap;
        }
        af.c(f4391a, "splitNames or splitSourceDirs is null.");
        return hashMap;
    }
}
