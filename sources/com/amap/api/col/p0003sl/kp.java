package com.amap.api.col.p0003sl;

import android.content.Context;

/* loaded from: classes2.dex */
public final class kp extends kr {

    /* renamed from: a, reason: collision with root package name */
    public static int f1264a = 13;
    public static int b = 6;
    private Context e;

    public kp(Context context, kr krVar) {
        super(krVar);
        this.e = context;
    }

    @Override // com.amap.api.col.p0003sl.kr
    protected final byte[] a(byte[] bArr) {
        byte[] a2 = a(this.e);
        byte[] bArr2 = new byte[a2.length + bArr.length];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(bArr, 0, bArr2, a2.length, bArr.length);
        return bArr2;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:2|3)|4|5|6|(1:(0))) */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] a(android.content.Context r5) {
        /*
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            r1 = 0
            byte[] r2 = new byte[r1]
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L77
            java.lang.String r4 = "1.2."
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L77
            int r4 = com.amap.api.col.p0003sl.kp.f1264a     // Catch: java.lang.Throwable -> L77
            r3.append(r4)     // Catch: java.lang.Throwable -> L77
            java.lang.String r4 = "."
            r3.append(r4)     // Catch: java.lang.Throwable -> L77
            int r4 = com.amap.api.col.p0003sl.kp.b     // Catch: java.lang.Throwable -> L77
            r3.append(r4)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = "Android"
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hr.v(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hr.k(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hr.h(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = android.os.Build.MANUFACTURER     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = android.os.Build.MODEL     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = android.os.Build.DEVICE     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hr.y(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hn.c(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r3 = com.amap.api.col.p0003sl.hn.d(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r3)     // Catch: java.lang.Throwable -> L77
            java.lang.String r5 = com.amap.api.col.p0003sl.hn.f(r5)     // Catch: java.lang.Throwable -> L77
            com.amap.api.col.p0003sl.ia.a(r0, r5)     // Catch: java.lang.Throwable -> L77
            r5 = 1
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L77
            r5[r1] = r1     // Catch: java.lang.Throwable -> L77
            r0.write(r5)     // Catch: java.lang.Throwable -> L77
            byte[] r2 = r0.toByteArray()     // Catch: java.lang.Throwable -> L77
            goto L7f
        L77:
            r5 = move-exception
            java.lang.String r1 = "sm"
            java.lang.String r3 = "gh"
            com.amap.api.col.p0003sl.iv.c(r5, r1, r3)     // Catch: java.lang.Throwable -> L88
        L7f:
            r0.close()     // Catch: java.lang.Throwable -> L83
            goto L87
        L83:
            r5 = move-exception
            r5.printStackTrace()
        L87:
            return r2
        L88:
            r5 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L8d
            goto L91
        L8d:
            r0 = move-exception
            r0.printStackTrace()
        L91:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.col.p0003sl.kp.a(android.content.Context):byte[]");
    }
}
