package com.huawei.health.h5pro.jsbridge.system.permission;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.health.h5pro.utils.LogUtil;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes3.dex */
public class PermissionUtils {
    public static final Set<String> d = getVersionPermissionSet();

    public static boolean shouldIgnorePermissionNotFound(String str) {
        synchronized (PermissionUtils.class) {
            LogUtil.d("PermissionUtils", "Permission not found: " + str);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0017, code lost:
    
        if (shouldIgnorePermissionNotFound(r2) != false) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isHasPermission(android.content.Context r1, java.lang.String r2) {
        /*
            java.lang.Class<com.huawei.health.h5pro.jsbridge.system.permission.PermissionUtils> r0 = com.huawei.health.h5pro.jsbridge.system.permission.PermissionUtils.class
            monitor-enter(r0)
            if (r1 == 0) goto L1e
            int r1 = androidx.core.app.ActivityCompat.checkSelfPermission(r1, r2)     // Catch: java.lang.Throwable -> L1b
            if (r1 == 0) goto L19
            java.util.Set<java.lang.String> r1 = com.huawei.health.h5pro.jsbridge.system.permission.PermissionUtils.d     // Catch: java.lang.Throwable -> L1b
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L1b
            if (r1 != 0) goto L1e
            boolean r1 = shouldIgnorePermissionNotFound(r2)     // Catch: java.lang.Throwable -> L1b
            if (r1 == 0) goto L1e
        L19:
            r1 = 1
            goto L1f
        L1b:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L1e:
            r1 = 0
        L1f:
            monitor-exit(r0)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.h5pro.jsbridge.system.permission.PermissionUtils.isHasPermission(android.content.Context, java.lang.String):boolean");
    }

    public static boolean isGpsLocationEnable(Context context) {
        boolean z;
        boolean z2;
        if (context == null) {
            LogUtil.w("PermissionUtils", "context is null");
            return false;
        }
        Object systemService = context.getSystemService("location");
        if (systemService instanceof LocationManager) {
            LocationManager locationManager = (LocationManager) systemService;
            z2 = locationManager.isProviderEnabled(GeocodeSearch.GPS);
            LogUtil.i("PermissionUtils", "isGPSLocationEnable:" + z2);
            z = locationManager.isProviderEnabled(HAWebViewInterface.NETWORK);
            LogUtil.i("PermissionUtils", "isNetWorkLocationEnable:" + z);
        } else {
            z = false;
            z2 = false;
        }
        return z2 || z;
    }

    public static Set<String> getVersionPermissionSet() {
        HashSet hashSet = new HashSet();
        for (Field field : Manifest.permission.class.getFields()) {
            try {
                hashSet.add((String) field.get(""));
            } catch (IllegalAccessException unused) {
                LogUtil.e("PermissionUtils", "Could not access the field");
            }
        }
        return hashSet;
    }

    public static void avoidImplicitProblem(Activity activity, Intent intent, int i) {
        ResolveInfo resolveActivity;
        PackageManager packageManager = activity.getPackageManager();
        if (packageManager == null || (resolveActivity = packageManager.resolveActivity(intent, 65536)) == null) {
            return;
        }
        ActivityInfo activityInfo = resolveActivity.activityInfo;
        intent.setComponent(new ComponentName(activityInfo.packageName, activityInfo.name));
        activity.startActivityForResult(intent, i);
    }
}
