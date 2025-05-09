package com.huawei.phoneservice.faq.base;

import com.huawei.phoneservice.faq.base.util.i;

/* loaded from: classes5.dex */
public class d {
    private static void b(Exception exc, String str) {
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
    public static java.lang.String a(java.lang.String r6) {
        /*
            java.lang.String r0 = "CommonUtils"
            r1 = 0
            java.lang.String r2 = "android.os.SystemProperties"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch: java.lang.NoSuchMethodException -> L1c java.lang.ClassNotFoundException -> L1e
            r3 = 1
            java.lang.Class[] r3 = new java.lang.Class[r3]     // Catch: java.lang.NoSuchMethodException -> L18 java.lang.ClassNotFoundException -> L1a
            java.lang.Class<java.lang.String> r4 = java.lang.String.class
            r5 = 0
            r3[r5] = r4     // Catch: java.lang.NoSuchMethodException -> L18 java.lang.ClassNotFoundException -> L1a
            java.lang.String r4 = "get"
            java.lang.reflect.Method r3 = r2.getDeclaredMethod(r4, r3)     // Catch: java.lang.NoSuchMethodException -> L18 java.lang.ClassNotFoundException -> L1a
            goto L25
        L18:
            r3 = move-exception
            goto L21
        L1a:
            r3 = move-exception
            goto L21
        L1c:
            r2 = move-exception
            goto L1f
        L1e:
            r2 = move-exception
        L1f:
            r3 = r2
            r2 = r1
        L21:
            b(r3, r0)
            r3 = r1
        L25:
            if (r3 == 0) goto L3b
            java.lang.Object[] r6 = new java.lang.Object[]{r6}     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            java.lang.Object r6 = r3.invoke(r2, r6)     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            java.lang.String r6 = (java.lang.String) r6     // Catch: java.lang.reflect.InvocationTargetException -> L33 java.lang.IllegalArgumentException -> L35 java.lang.IllegalAccessException -> L37
            r1 = r6
            goto L3b
        L33:
            r6 = move-exception
            goto L38
        L35:
            r6 = move-exception
            goto L38
        L37:
            r6 = move-exception
        L38:
            b(r6, r0)
        L3b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.phoneservice.faq.base.d.a(java.lang.String):java.lang.String");
    }
}
