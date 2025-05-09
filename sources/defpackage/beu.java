package defpackage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.List;

/* loaded from: classes3.dex */
public class beu {
    public static boolean d(Context context, String str) {
        return (context == null || TextUtils.isEmpty(str) || pK_(context, str) == null) ? false : true;
    }

    public static boolean d(String str, String str2) {
        if (!d(bec.e(), str)) {
            bes.e("PackageManagerUtil", "app package name is not available");
            return false;
        }
        if (c(a(bec.e(), str), str2) >= 0) {
            return true;
        }
        bes.e("PackageManagerUtil", "app version for this package name is not match");
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0036 A[LOOP:0: B:2:0x0014->B:12:0x0036, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0034 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int c(java.lang.String r6, java.lang.String r7) {
        /*
            java.lang.String r0 = "PackageManagerUtil"
            java.lang.String r1 = "\\."
            java.lang.String[] r6 = r6.split(r1)
            java.lang.String[] r7 = r7.split(r1)
            int r1 = r6.length
            int r2 = r7.length
            int r1 = java.lang.Math.max(r1, r2)
            r2 = 0
            r3 = r2
        L14:
            if (r3 >= r1) goto L3e
            int r4 = r6.length     // Catch: java.lang.NumberFormatException -> L2b
            if (r3 >= r4) goto L20
            r4 = r6[r3]     // Catch: java.lang.NumberFormatException -> L2b
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L2b
            goto L21
        L20:
            r4 = r2
        L21:
            int r5 = r7.length     // Catch: java.lang.NumberFormatException -> L2c
            if (r3 >= r5) goto L31
            r5 = r7[r3]     // Catch: java.lang.NumberFormatException -> L2c
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L2c
            goto L32
        L2b:
            r4 = r2
        L2c:
            java.lang.String r5 = "compareVersion NumberFormatException"
            defpackage.bes.d(r0, r5)
        L31:
            r5 = r2
        L32:
            if (r4 == r5) goto L36
            int r4 = r4 - r5
            return r4
        L36:
            java.lang.String r4 = "continue compare"
            defpackage.bes.e(r0, r4)
            int r3 = r3 + 1
            goto L14
        L3e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.beu.c(java.lang.String, java.lang.String):int");
    }

    public static String a(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            PackageInfo pK_ = pK_(context, str);
            if (pK_ != null) {
                return pK_.versionName;
            }
            bes.d("PackageManagerUtil", "appVersionName is null.");
        }
        return "";
    }

    private static PackageInfo pK_(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                bes.d("PackageManagerUtil", "packageManager is null.");
                return null;
            }
            return packageManager.getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException e) {
            bes.d("PackageManagerUtil", "getPackageInfo fail:" + e.getMessage());
            return null;
        }
    }

    public static String a(String str) {
        return (bet.d() && TextUtils.equals(str, "com.huawei.pcassistant")) ? "com.hihonor.pcassistant" : str;
    }

    public static boolean pL_(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities;
        return (context == null || (queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0)) == null || queryIntentActivities.isEmpty()) ? false : true;
    }
}
