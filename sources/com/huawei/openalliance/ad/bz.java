package com.huawei.openalliance.ad;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.inter.HiAd;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class bz {

    /* renamed from: a, reason: collision with root package name */
    private static volatile co f6668a = null;
    private static final byte[] b = new byte[0];
    private static String c = null;
    private static int d = 3;

    /* JADX INFO: Access modifiers changed from: private */
    public static void l(Context context) {
        gc b2 = fh.b(context);
        d = b2.ao();
        c = b2.ap();
        ho.a("DeviceManager", "get partner config, partnerRule: %s, partnerList: %s", Integer.valueOf(d), c);
    }

    private static void k(final Context context) {
        com.huawei.openalliance.ad.utils.m.a(new Runnable() { // from class: com.huawei.openalliance.ad.bz.1
            @Override // java.lang.Runnable
            public void run() {
                bz.l(context);
            }
        });
    }

    private static void j(Context context) {
        if (c == null) {
            l(context);
        }
    }

    public static boolean h(Context context) {
        if (c(context)) {
            return true;
        }
        j(context);
        if (!a(d, c)) {
            List asList = Arrays.asList(c.split(","));
            Iterator it = asList.iterator();
            while (it.hasNext()) {
                if (((String) it.next()).length() != d) {
                    ho.b("DeviceManager", "The partnerList format is incorrect, %s", asList);
                }
            }
            String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.build.2b2c.partner.ext_channel");
            boolean z = !TextUtils.isEmpty(a2) && asList.contains(a2.substring(0, Math.min(d, a2.length())));
            k(context);
            return z;
        }
        ho.b("DeviceManager", "partnerRule out of range");
        k(context);
        return a();
    }

    public static boolean g(Context context) {
        return "HUAWEI".equalsIgnoreCase(a(context).j()) && "HUAWEI".equalsIgnoreCase(a(context).k());
    }

    public static boolean f(Context context) {
        Integer j = HiAd.a(context).j();
        co a2 = a(context);
        return (j != null && 2 == j.intValue()) || ("HONOR".equalsIgnoreCase(a2.j()) && "HONOR".equalsIgnoreCase(a2.k()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        if (com.hihonor.android.os.Build.VERSION.MAGIC_SDK_INT >= 33) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean e(android.content.Context r4) {
        /*
            com.huawei.openalliance.ad.utils.cg r4 = com.huawei.openalliance.ad.utils.cg.a(r4)
            java.lang.String r0 = r4.X()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            r2 = 1
            if (r1 != 0) goto L18
            java.lang.String r4 = java.lang.String.valueOf(r2)
            boolean r4 = android.text.TextUtils.equals(r4, r0)
            goto L53
        L18:
            r0 = 0
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L33
            java.lang.String r3 = "HONOR"
            boolean r1 = r1.equalsIgnoreCase(r3)     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L30
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L33
            r3 = 31
            if (r1 < r3) goto L30
            int r1 = com.hihonor.android.os.Build.VERSION.MAGIC_SDK_INT     // Catch: java.lang.Throwable -> L33
            r3 = 33
            if (r1 < r3) goto L30
            goto L31
        L30:
            r2 = r0
        L31:
            r0 = r2
            goto L4f
        L33:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "isHonor6UpPhone Error:"
            r2.<init>(r3)
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getSimpleName()
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            java.lang.String r2 = "DeviceManager"
            com.huawei.openalliance.ad.ho.d(r2, r1)
        L4f:
            r4.e(r0)
            r4 = r0
        L53:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.bz.e(android.content.Context):boolean");
    }

    public static boolean d(Context context) {
        com.huawei.openalliance.ad.utils.cg a2 = com.huawei.openalliance.ad.utils.cg.a(context);
        String W = a2.W();
        if (!TextUtils.isEmpty(W)) {
            return TextUtils.equals(String.valueOf(true), W);
        }
        boolean z = c(context) && !e(context);
        a2.d(z);
        return z;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0048 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(android.content.Context r6) {
        /*
            java.lang.String r0 = "HONOR"
            java.lang.String r1 = "HUAWEI"
            android.content.Context r2 = r6.getApplicationContext()
            com.huawei.openalliance.ad.utils.cg r2 = com.huawei.openalliance.ad.utils.cg.a(r2)
            java.lang.String r3 = r2.c()
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            r5 = 1
            if (r4 != 0) goto L21
            java.lang.String r6 = java.lang.String.valueOf(r5)
            boolean r6 = android.text.TextUtils.equals(r6, r3)
            goto L9c
        L21:
            r3 = 0
            java.lang.String r4 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            boolean r4 = r4.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            if (r4 != 0) goto L45
            java.lang.String r4 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            boolean r1 = r4.equalsIgnoreCase(r1)     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            if (r1 != 0) goto L45
            java.lang.String r1 = android.os.Build.BRAND     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            boolean r1 = r1.equalsIgnoreCase(r0)     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            if (r1 != 0) goto L45
            java.lang.String r1 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            boolean r0 = r1.equalsIgnoreCase(r0)     // Catch: java.lang.Throwable -> L73 java.lang.RuntimeException -> L7c
            if (r0 == 0) goto L43
            goto L45
        L43:
            r0 = r3
            goto L46
        L45:
            r0 = r5
        L46:
            if (r0 != 0) goto L71
            com.huawei.openalliance.ad.cq r6 = com.huawei.openalliance.ad.cb.a(r6)     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            java.lang.String r6 = r6.e()     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            java.lang.String r1 = "EMUI_SDK_INT"
            java.lang.reflect.Field r6 = r6.getDeclaredField(r1)     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            r1 = 0
            java.lang.Object r6 = r6.get(r1)     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            java.lang.Integer r6 = (java.lang.Integer) r6     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            int r6 = r6.intValue()     // Catch: java.lang.Throwable -> L6b java.lang.RuntimeException -> L6e
            if (r6 <= 0) goto L68
            goto L69
        L68:
            r5 = r3
        L69:
            r0 = r5
            goto L71
        L6b:
            r6 = move-exception
            r3 = r0
            goto L74
        L6e:
            r6 = move-exception
            r3 = r0
            goto L7d
        L71:
            r6 = r0
            goto L99
        L73:
            r6 = move-exception
        L74:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "isHuaweiPhone Error:"
            r0.<init>(r1)
            goto L84
        L7c:
            r6 = move-exception
        L7d:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "isHuaweiPhone RuntimeException:"
            r0.<init>(r1)
        L84:
            java.lang.Class r6 = r6.getClass()
            java.lang.String r6 = r6.getSimpleName()
            r0.append(r6)
            java.lang.String r6 = r0.toString()
            java.lang.String r0 = "DeviceManager"
            com.huawei.openalliance.ad.ho.d(r0, r6)
            r6 = r3
        L99:
            r2.a(r6)
        L9c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.openalliance.ad.bz.c(android.content.Context):boolean");
    }

    public static boolean b(Context context) {
        return c(context) || a() || b();
    }

    public static boolean b() {
        return Build.MANUFACTURER.equalsIgnoreCase("Minrray") || Build.MANUFACTURER.equalsIgnoreCase("Hivendor") || Build.MANUFACTURER.equalsIgnoreCase("Huanglong");
    }

    private static boolean a(int i, String str) {
        return i < 2 || i > 11 || TextUtils.isEmpty(str) || !str.matches(Constants.PARTNER_REGEX);
    }

    public static boolean a() {
        String a2 = com.huawei.openalliance.ad.utils.dd.a("ro.build.2b2c.partner.ext_channel");
        return !TextUtils.isEmpty(a2) && a2.startsWith(com.huawei.hms.ads.dynamic.a.t);
    }

    public static co a(Context context) {
        if (f6668a == null) {
            synchronized (b) {
                if (f6668a == null) {
                    try {
                        f6668a = d(context) ? cj.b(context) : e(context) ? cg.b(context) : a() ? cf.b(context) : cn.b(context);
                    } catch (Throwable th) {
                        f6668a = cj.b(context);
                        ho.c("DeviceManager", "init device manager error, " + th.getClass().getSimpleName());
                    }
                }
            }
        }
        return f6668a;
    }
}
