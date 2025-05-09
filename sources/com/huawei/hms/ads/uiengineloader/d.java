package com.huawei.hms.ads.uiengineloader;

import android.os.Build;
import android.text.TextUtils;
import com.hihonor.android.os.Build;

/* loaded from: classes4.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static final int f4380a = 27;
    private static final String b = "DeviceUtil";

    private static boolean d() {
        try {
            if (Build.MANUFACTURER.equalsIgnoreCase("HONOR") && Build.VERSION.SDK_INT >= 31) {
                if (Build.VERSION.MAGIC_SDK_INT >= 33) {
                    return true;
                }
            }
        } catch (Throwable th) {
            af.d(b, "isHonor6UpPhone Error:" + th.getClass().getSimpleName());
        }
        return false;
    }

    private static boolean c() {
        String a2 = a("ro.build.2b2c.partner.ext_channel");
        return !TextUtils.isEmpty(a2) && a2.startsWith(com.huawei.hms.ads.dynamic.a.t);
    }

    private static boolean b() {
        if (a()) {
            return true;
        }
        String a2 = a("ro.build.2b2c.partner.ext_channel");
        return !TextUtils.isEmpty(a2) && a2.startsWith(com.huawei.hms.ads.dynamic.a.t);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a() {
        /*
            java.lang.String r0 = "HONOR"
            java.lang.String r1 = "HUAWEI"
            r2 = 0
            java.lang.String r3 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L4a
            boolean r3 = r3.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L4a
            r4 = 1
            if (r3 != 0) goto L29
            java.lang.String r3 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L4a
            boolean r1 = r3.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L4a
            if (r1 != 0) goto L29
            java.lang.String r1 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L4a
            boolean r1 = r1.equalsIgnoreCase(r0)     // Catch: java.lang.Throwable -> L4a
            if (r1 != 0) goto L29
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L4a
            boolean r0 = r1.equalsIgnoreCase(r0)     // Catch: java.lang.Throwable -> L4a
            if (r0 == 0) goto L27
            goto L29
        L27:
            r0 = r2
            goto L2a
        L29:
            r0 = r4
        L2a:
            if (r0 != 0) goto L68
            java.lang.String r1 = "com.huawei.android.os.BuildEx$VERSION"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Throwable -> L47
            java.lang.String r3 = "EMUI_SDK_INT"
            java.lang.reflect.Field r1 = r1.getDeclaredField(r3)     // Catch: java.lang.Throwable -> L47
            r3 = 0
            java.lang.Object r1 = r1.get(r3)     // Catch: java.lang.Throwable -> L47
            java.lang.Integer r1 = (java.lang.Integer) r1     // Catch: java.lang.Throwable -> L47
            int r0 = r1.intValue()     // Catch: java.lang.Throwable -> L47
            if (r0 <= 0) goto L67
            r2 = r4
            goto L67
        L47:
            r1 = move-exception
            r2 = r0
            goto L4c
        L4a:
            r0 = move-exception
            r1 = r0
        L4c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r3 = "isHuaweiPhone Error:"
            r0.<init>(r3)
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "DeviceUtil"
            com.huawei.hms.ads.uiengineloader.af.d(r1, r0)
        L67:
            r0 = r2
        L68:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.ads.uiengineloader.d.a():boolean");
    }

    public static String a(String str) {
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT >= 27) {
                try {
                    cls = Class.forName(d() ? "com.hihonor.android.os.SystemPropertiesEx" : "com.huawei.android.os.SystemPropertiesEx");
                } catch (ClassNotFoundException unused) {
                }
                return (String) cls.getMethod("get", String.class).invoke(cls, str);
            }
            cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, str);
        } catch (Throwable th) {
            af.c(b, "getSystemProperties Exception:" + th.getClass().getSimpleName());
            return null;
        }
    }
}
