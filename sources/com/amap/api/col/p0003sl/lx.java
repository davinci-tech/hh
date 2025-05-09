package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class lx extends nb {
    public static int a(na naVar, int i, int i2, int i3, short s) {
        naVar.b(5);
        c(naVar, i3);
        b(naVar, i2);
        a(naVar, i);
        a(naVar, s);
        a(naVar);
        return b(naVar);
    }

    private static void a(na naVar) {
        naVar.a(0, (byte) 2);
    }

    private static void a(na naVar, int i) {
        naVar.a(1, i);
    }

    private static void b(na naVar, int i) {
        naVar.a(2, i);
    }

    private static void c(na naVar, int i) {
        naVar.a(3, i);
    }

    private static void a(na naVar, short s) {
        naVar.a(4, s);
    }

    private static int b(na naVar) {
        return naVar.b();
    }
}
