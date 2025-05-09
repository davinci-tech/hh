package com.huawei.phoneservice.feedback.media.impl.utils;

import com.huawei.phoneservice.faq.base.util.i;

/* loaded from: classes5.dex */
public class c {
    private static final String d = "b";

    private static void d(Exception exc, String str) {
        if (exc == null || exc.getMessage() == null) {
            return;
        }
        i.c(str, exc.getMessage());
    }

    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0027 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String c(java.lang.String r5) {
        /*
            r0 = 0
            java.lang.String r1 = "android.os.SystemProperties"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.NoSuchMethodException -> L1a java.lang.ClassNotFoundException -> L1c
            r2 = 1
            java.lang.Class[] r2 = new java.lang.Class[r2]     // Catch: java.lang.NoSuchMethodException -> L16 java.lang.ClassNotFoundException -> L18
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4 = 0
            r2[r4] = r3     // Catch: java.lang.NoSuchMethodException -> L16 java.lang.ClassNotFoundException -> L18
            java.lang.String r3 = "get"
            java.lang.reflect.Method r2 = r1.getDeclaredMethod(r3, r2)     // Catch: java.lang.NoSuchMethodException -> L16 java.lang.ClassNotFoundException -> L18
            goto L25
        L16:
            r2 = move-exception
            goto L1f
        L18:
            r2 = move-exception
            goto L1f
        L1a:
            r1 = move-exception
            goto L1d
        L1c:
            r1 = move-exception
        L1d:
            r2 = r1
            r1 = r0
        L1f:
            java.lang.String r3 = com.huawei.phoneservice.feedback.media.impl.utils.c.d
            d(r2, r3)
            r2 = r0
        L25:
            if (r2 == 0) goto L3d
            java.lang.Object[] r5 = new java.lang.Object[]{r5}     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            java.lang.Object r5 = r2.invoke(r1, r5)     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            java.lang.String r5 = (java.lang.String) r5     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            r0 = r5
            goto L3d
        L33:
            r5 = move-exception
            goto L38
        L35:
            r5 = move-exception
            goto L38
        L37:
            r5 = move-exception
        L38:
            java.lang.String r1 = com.huawei.phoneservice.feedback.media.impl.utils.c.d
            d(r5, r1)
        L3d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.feedback.media.impl.utils.c.c(java.lang.String):java.lang.String");
    }
}
