package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class lq extends nb {
    public static int a(na naVar, int i, byte b, int i2, int i3) {
        naVar.b(4);
        c(naVar, i3);
        b(naVar, i2);
        a(naVar, i);
        a(naVar, b);
        return a(naVar);
    }

    private static void a(na naVar, int i) {
        naVar.b(0, i);
    }

    private static void a(na naVar, byte b) {
        naVar.a(1, b);
    }

    private static void b(na naVar, int i) {
        naVar.b(2, i);
    }

    public static int a(na naVar, int[] iArr) {
        naVar.a(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            naVar.a(iArr[length]);
        }
        return naVar.a();
    }

    private static void c(na naVar, int i) {
        naVar.b(3, i);
    }

    public static int b(na naVar, int[] iArr) {
        naVar.a(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            naVar.a(iArr[length]);
        }
        return naVar.a();
    }

    private static int a(na naVar) {
        return naVar.b();
    }
}
