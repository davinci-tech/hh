package com.huawei.haf.common.utils;

/* loaded from: classes.dex */
public class ScreenUtil {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2105a;

    private ScreenUtil() {
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean a() {
        /*
            android.os.PowerManager r0 = com.huawei.haf.common.utils.PowerUtil.xN_()
            java.lang.String r1 = "HAF_ScreenUtil"
            if (r0 == 0) goto L1c
            boolean r0 = r0.isInteractive()     // Catch: java.lang.Exception -> Ld
            goto L1d
        Ld:
            r0 = move-exception
            java.lang.String r2 = "powerManager isInteractive failed, ex="
            java.lang.String r0 = health.compact.a.LogUtil.a(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0}
            health.compact.a.LogUtil.e(r1, r0)
        L1c:
            r0 = 0
        L1d:
            boolean r2 = com.huawei.haf.common.utils.ScreenUtil.f2105a
            if (r2 == r0) goto L30
            java.lang.String r2 = "isScreenOn "
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r0)
            java.lang.Object[] r2 = new java.lang.Object[]{r2, r3}
            health.compact.a.LogUtil.c(r1, r2)
            com.huawei.haf.common.utils.ScreenUtil.f2105a = r0
        L30:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.haf.common.utils.ScreenUtil.a():boolean");
    }
}
