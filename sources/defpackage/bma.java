package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import com.huawei.openalliance.ad.constant.OsType;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bma {
    private bma() {
    }

    public static boolean a(Context context, String[] strArr) {
        for (String str : strArr) {
            if (!d(context, str)) {
                ReleaseLogUtil.b("R_PermissionUtil", "isHasPermissions permissions are not granted: ", str);
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x000f, code lost:
    
        if (e(r2, r3) == false) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean d(android.content.Context r2, java.lang.String r3) {
        /*
            java.lang.Class<bma> r0 = defpackage.bma.class
            monitor-enter(r0)
            if (r2 == 0) goto L16
            int r1 = androidx.core.app.ActivityCompat.checkSelfPermission(r2, r3)     // Catch: java.lang.Throwable -> L13
            if (r1 == 0) goto L11
            boolean r2 = e(r2, r3)     // Catch: java.lang.Throwable -> L13
            if (r2 != 0) goto L16
        L11:
            r2 = 1
            goto L17
        L13:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L16:
            r2 = 0
        L17:
            monitor-exit(r0)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bma.d(android.content.Context, java.lang.String):boolean");
    }

    private static boolean e(Context context, String str) {
        PermissionInfo permissionInfo;
        try {
            permissionInfo = context.getPackageManager().getPermissionInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.a("PermissionUtil", "cannot found permission: ", str);
            permissionInfo = null;
        }
        if (permissionInfo == null) {
            return false;
        }
        LogUtil.c("PermissionUtil", "hwext res: ", Boolean.valueOf(OsType.ANDROID.equals(permissionInfo.packageName)), " android res: ", Boolean.valueOf("androidhwext".equals(permissionInfo.packageName)));
        return OsType.ANDROID.equals(permissionInfo.packageName) || "androidhwext".equals(permissionInfo.packageName);
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
