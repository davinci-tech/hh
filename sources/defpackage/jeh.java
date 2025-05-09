package defpackage;

import java.io.File;

/* loaded from: classes.dex */
public class jeh {
    public static boolean a() {
        return (new File(b()).exists() && e(b())) || (new File(d()).exists() && e(d()));
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0062, code lost:
    
        if (r9 != null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a2, code lost:
    
        return false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009f, code lost:
    
        r9.destroy();
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x009d, code lost:
    
        if (r9 != null) goto L43;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean e(java.lang.String r9) {
        /*
            r0 = 1
            r1 = 0
            r2 = 0
            java.lang.ProcessBuilder r3 = new java.lang.ProcessBuilder     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.String[] r4 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.String r6 = "ls -l "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            r5.append(r9)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.String r9 = r5.toString()     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            r4[r1] = r9     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.ProcessBuilder r9 = r3.redirectErrorStream(r0)     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.lang.Process r9 = r9.start()     // Catch: java.lang.Throwable -> L79 java.io.IOException -> L7f
            java.io.InputStream r3 = r9.getInputStream()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L80
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L72
            java.lang.String r5 = "UTF-8"
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L72
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L73
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L73
            r2 = 10
            char[] r2 = new char[r2]     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r6 = 5
            int r6 = r5.read(r2, r1, r6)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r7 = 4
            if (r6 < r7) goto L59
            r6 = 3
            char r2 = r2[r6]     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r6 = 115(0x73, float:1.61E-43)
            if (r2 == r6) goto L4a
            r6 = 120(0x78, float:1.68E-43)
            if (r2 != r6) goto L59
        L4a:
            health.compact.a.IoUtils.e(r3)
            health.compact.a.IoUtils.e(r4)
            health.compact.a.IoUtils.e(r5)
            if (r9 == 0) goto L58
            r9.destroy()
        L58:
            return r0
        L59:
            health.compact.a.IoUtils.e(r3)
            health.compact.a.IoUtils.e(r4)
            health.compact.a.IoUtils.e(r5)
            if (r9 == 0) goto La2
            goto L9f
        L65:
            r0 = move-exception
            r2 = r5
            goto L6e
        L68:
            r2 = r5
            goto L73
        L6a:
            r0 = move-exception
            goto L6e
        L6c:
            r0 = move-exception
            r4 = r2
        L6e:
            r8 = r3
            r3 = r2
            r2 = r8
            goto La4
        L72:
            r4 = r2
        L73:
            r8 = r3
            r3 = r2
            r2 = r8
            goto L82
        L77:
            r0 = move-exception
            goto L7c
        L79:
            r9 = move-exception
            r0 = r9
            r9 = r2
        L7c:
            r3 = r2
            r4 = r3
            goto La4
        L7f:
            r9 = r2
        L80:
            r3 = r2
            r4 = r3
        L82:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> La3
            java.lang.String r6 = "isExecutable IOException"
            r5[r1] = r6     // Catch: java.lang.Throwable -> La3
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r1)     // Catch: java.lang.Throwable -> La3
            r5[r0] = r6     // Catch: java.lang.Throwable -> La3
            java.lang.String r0 = "RootUtil"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r5)     // Catch: java.lang.Throwable -> La3
            health.compact.a.IoUtils.e(r2)
            health.compact.a.IoUtils.e(r4)
            health.compact.a.IoUtils.e(r3)
            if (r9 == 0) goto La2
        L9f:
            r9.destroy()
        La2:
            return r1
        La3:
            r0 = move-exception
        La4:
            health.compact.a.IoUtils.e(r2)
            health.compact.a.IoUtils.e(r4)
            health.compact.a.IoUtils.e(r3)
            if (r9 == 0) goto Lb2
            r9.destroy()
        Lb2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jeh.e(java.lang.String):boolean");
    }

    private static String d() {
        return "/system/xbin/su";
    }

    private static String b() {
        return "/system/bin/su";
    }
}
