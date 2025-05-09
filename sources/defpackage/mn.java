package defpackage;

/* loaded from: classes7.dex */
public final class mn {
    /* JADX WARN: Can't wrap try/catch for region: R(2:(3:9|10|(1:12)(0))|13) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String d(java.lang.String r4, java.lang.String r5) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            r2.<init>(r4, r5)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            boolean r4 = r2.exists()     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            if (r4 != 0) goto L12
            return r1
        L12:
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            java.lang.String r2 = "UTF-8"
            r5.<init>(r3, r2)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L32 java.io.IOException -> L3a
        L23:
            java.lang.String r5 = r4.readLine()     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            if (r5 == 0) goto L3d
            r0.append(r5)     // Catch: java.lang.Throwable -> L2d java.io.IOException -> L30
            goto L23
        L2d:
            r5 = move-exception
            r1 = r4
            goto L34
        L30:
            r1 = r4
            goto L3a
        L32:
            r4 = move-exception
            r5 = r4
        L34:
            if (r1 == 0) goto L39
            r1.close()     // Catch: java.lang.Throwable -> L39
        L39:
            throw r5
        L3a:
            if (r1 == 0) goto L40
            r4 = r1
        L3d:
            r4.close()     // Catch: java.lang.Throwable -> L40
        L40:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mn.d(java.lang.String, java.lang.String):java.lang.String");
    }
}
