package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class lt extends nb {
    public static int a(na naVar, byte b, int i) {
        naVar.b(2);
        a(naVar, i);
        a(naVar, b);
        return a(naVar);
    }

    private static void a(na naVar, byte b) {
        naVar.a(0, b);
    }

    private static void a(na naVar, int i) {
        naVar.b(1, i);
    }

    public static int a(na naVar, byte[] bArr) {
        naVar.a(1, bArr.length, 1);
        for (int length = bArr.length - 1; length >= 0; length--) {
            naVar.a(bArr[length]);
        }
        return naVar.a();
    }

    private static int a(na naVar) {
        return naVar.b();
    }
}
