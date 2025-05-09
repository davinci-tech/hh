package com.huawei.hms.kit.awareness.b.a;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.core.app.AppOpsManagerCompat;
import androidx.core.content.ContextCompat;
import com.huawei.hms.kit.awareness.AwarenessStatusCodes;

/* loaded from: classes9.dex */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4829a = "PermissionChecker";

    public static int b(Context context, String str) {
        if (context.checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            return 10102;
        }
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_COARSE_LOCATION", str) != 0) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        return 0;
    }

    public static boolean a(Context context, String str, String str2) {
        if (context.getPackageManager().checkPermission(str, str2) == -1) {
            return false;
        }
        String permissionToOp = AppOpsManagerCompat.permissionToOp(str);
        if (permissionToOp == null) {
            return true;
        }
        try {
            return AppOpsManagerCompat.noteOpNoThrow(context, permissionToOp, context.getPackageManager().getPackageUid(str2, 0), str2) == 0;
        } catch (PackageManager.NameNotFoundException unused) {
            c.d(f4829a, " can not find the packageName." + str2, new Object[0]);
            return false;
        }
    }

    public static boolean a(Context context) {
        if (!g.a() && ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH") != 0) {
            c.c(f4829a, "checkCoreSelfBlueToothPermission()-> BLUETOOTH denied", new Object[0]);
            return false;
        }
        if (!g.a() || ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0) {
            return true;
        }
        c.c(f4829a, "checkCoreSelfBlueToothPermission()-> BLUETOOTH_CONNECT denied", new Object[0]);
        return false;
    }

    public static int a(Context context, String str) {
        if (context.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            return 10102;
        }
        if (context.getPackageManager().checkPermission("android.permission.ACCESS_FINE_LOCATION", str) != 0) {
            return AwarenessStatusCodes.AWARENESS_LOCATION_PERMISSION_CODE;
        }
        return 0;
    }

    private f() {
    }
}
