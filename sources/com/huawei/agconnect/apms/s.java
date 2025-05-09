package com.huawei.agconnect.apms;

/* loaded from: classes8.dex */
public class s implements Runnable {
    public final /* synthetic */ t abc;

    public s(t tVar) {
        this.abc = tVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:20:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r15 = this;
            long r1 = java.lang.System.currentTimeMillis()
            com.huawei.agconnect.apms.t r0 = r15.abc
            r0.getClass()
            r7 = 0
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            java.lang.String r4 = r0.ghi     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            r8.<init>(r3)     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            java.lang.String r3 = r8.readLine()     // Catch: java.lang.Throwable -> L7a
            if (r3 == 0) goto L74
            int r4 = r3.length()     // Catch: java.lang.Throwable -> L7a
            if (r4 != 0) goto L23
            goto L74
        L23:
            java.lang.String r4 = " "
            java.lang.String[] r3 = r3.split(r4)     // Catch: java.lang.Throwable -> L7a
            int r4 = r3.length     // Catch: java.lang.Throwable -> L7a
            r5 = 16
            if (r5 >= r4) goto L4d
            r4 = 13
            r4 = r3[r4]     // Catch: java.lang.Throwable -> L7a
            long r9 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Throwable -> L7a
            r4 = 15
            r4 = r3[r4]     // Catch: java.lang.Throwable -> L7a
            long r11 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Throwable -> L7a
            r4 = 14
            r4 = r3[r4]     // Catch: java.lang.Throwable -> L7a
            long r13 = java.lang.Long.parseLong(r4)     // Catch: java.lang.Throwable -> L7a
            r3 = r3[r5]     // Catch: java.lang.Throwable -> L7a
            long r3 = java.lang.Long.parseLong(r3)     // Catch: java.lang.Throwable -> L7a
            goto L52
        L4d:
            r9 = 0
            r3 = r9
            r11 = r3
            r13 = r11
        L52:
            long r9 = r9 + r11
            double r5 = (double) r9     // Catch: java.lang.Throwable -> L7a
            long r9 = r0.hij     // Catch: java.lang.Throwable -> L7a
            double r9 = (double) r9     // Catch: java.lang.Throwable -> L7a
            double r5 = r5 / r9
            long r9 = com.huawei.agconnect.apms.t.bcd     // Catch: java.lang.Throwable -> L7a
            double r9 = (double) r9     // Catch: java.lang.Throwable -> L7a
            double r5 = r5 * r9
            long r5 = java.lang.Math.round(r5)     // Catch: java.lang.Throwable -> L7a
            long r3 = r3 + r13
            double r3 = (double) r3     // Catch: java.lang.Throwable -> L7a
            long r11 = r0.hij     // Catch: java.lang.Throwable -> L7a
            double r11 = (double) r11     // Catch: java.lang.Throwable -> L7a
            double r3 = r3 / r11
            double r3 = r3 * r9
            long r9 = java.lang.Math.round(r3)     // Catch: java.lang.Throwable -> L7a
            com.huawei.agconnect.apms.y r11 = new com.huawei.agconnect.apms.y     // Catch: java.lang.Throwable -> L7a
            r0 = r11
            r3 = r5
            r5 = r9
            r0.<init>(r1, r3, r5)     // Catch: java.lang.Throwable -> L7a
            goto L75
        L74:
            r11 = r7
        L75:
            r8.close()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            r7 = r11
            goto Lc8
        L7a:
            r0 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r1 = com.huawei.agconnect.apms.t.abc     // Catch: java.lang.Throwable -> L96
            java.util.Locale r2 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> L96
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L96
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L96
            r4 = 0
            r3[r4] = r0     // Catch: java.lang.Throwable -> L96
            java.lang.String r0 = "unable to read 'proc/[pid]/stat' file: %s"
            java.lang.String r0 = java.lang.String.format(r2, r0, r3)     // Catch: java.lang.Throwable -> L96
            r1.warn(r0)     // Catch: java.lang.Throwable -> L96
            r8.close()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            goto Lc8
        L96:
            r0 = move-exception
            r8.close()     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
            throw r0     // Catch: java.lang.Throwable -> L9b java.io.IOException -> Lb2
        L9b:
            r0 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r1 = com.huawei.agconnect.apms.t.abc
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "unexpected '/proc/[pid]/stat' file format encountered: %s"
            java.lang.String r0 = java.lang.String.format(r2, r3, r0)
            r1.warn(r0)
            goto Lc8
        Lb2:
            r0 = move-exception
            com.huawei.agconnect.apms.log.AgentLog r1 = com.huawei.agconnect.apms.t.abc
            java.util.Locale r2 = java.util.Locale.ENGLISH
            java.lang.String r0 = r0.getMessage()
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r3 = "unable to read 'proc/[pid]/stat' file:  %s"
            java.lang.String r0 = java.lang.String.format(r2, r3, r0)
            r1.warn(r0)
        Lc8:
            if (r7 == 0) goto Ld1
            com.huawei.agconnect.apms.t r0 = r15.abc
            java.util.concurrent.ConcurrentLinkedQueue<com.huawei.agconnect.apms.y> r0 = r0.ijk
            r0.offer(r7)
        Ld1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.agconnect.apms.s.run():void");
    }
}
