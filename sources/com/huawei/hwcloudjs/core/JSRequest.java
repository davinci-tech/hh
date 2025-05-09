package com.huawei.hwcloudjs.core;

import com.huawei.hwcloudjs.support.enables.INoProguard;

/* loaded from: classes5.dex */
public abstract class JSRequest implements INoProguard {

    /* renamed from: a, reason: collision with root package name */
    private static final String f6192a = "JSRequest";

    /* JADX WARN: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0027  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void execute(java.lang.reflect.Method r7, java.lang.Object r8, com.huawei.hwcloudjs.core.JsCallback r9) {
        /*
            r6 = this;
            java.lang.String r0 = "JSRequest"
            java.lang.String r1 = "execute begin"
            r2 = 1
            com.huawei.hwcloudjs.f.d.c(r0, r1, r2)
            java.lang.Class[] r1 = r7.getParameterTypes()
            int r3 = r1.length
            r4 = 0
            if (r3 <= 0) goto L20
            java.lang.Object[] r5 = new java.lang.Object[r3]
            r5[r4] = r8
            int r3 = r3 + (-1)
            r8 = r1[r3]
            java.lang.Class<com.huawei.hwcloudjs.core.JsCallback> r1 = com.huawei.hwcloudjs.core.JsCallback.class
            if (r8 != r1) goto L21
            r5[r3] = r9
            r8 = r2
            goto L22
        L20:
            r5 = 0
        L21:
            r8 = r4
        L22:
            r7.invoke(r6, r5)     // Catch: java.lang.reflect.InvocationTargetException -> L2b java.lang.IllegalAccessException -> L47
            if (r8 != 0) goto L2a
            r9.success()
        L2a:
            return
        L2b:
            r7 = move-exception
            java.lang.String r8 = "InvocationTargetException"
            com.huawei.hwcloudjs.f.d.b(r0, r8, r2)
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r1 = "InvocationTargetException:"
            r8.<init>(r1)
            java.lang.Throwable r7 = r7.getTargetException()
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            com.huawei.hwcloudjs.f.d.b(r0, r7, r4)
            goto L4c
        L47:
            java.lang.String r7 = "IllegalAccessException"
            com.huawei.hwcloudjs.f.d.b(r0, r7, r2)
        L4c:
            r9.failure()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwcloudjs.core.JSRequest.execute(java.lang.reflect.Method, java.lang.Object, com.huawei.hwcloudjs.core.JsCallback):void");
    }
}
