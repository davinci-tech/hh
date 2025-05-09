package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class ls extends nb {
    public static int a(na naVar, byte b, byte b2, short s, byte b3, int i) {
        naVar.b(5);
        a(naVar, i);
        a(naVar, s);
        c(naVar, b3);
        b(naVar, b2);
        a(naVar, b);
        return a(naVar);
    }

    private static void a(na naVar, byte b) {
        naVar.a(0, b);
    }

    private static void b(na naVar, byte b) {
        naVar.a(1, b);
    }

    private static void a(na naVar, short s) {
        naVar.a(2, s);
    }

    private static void c(na naVar, byte b) {
        naVar.a(3, b);
    }

    private static void a(na naVar, int i) {
        naVar.b(4, i);
    }

    private static int a(na naVar) {
        return naVar.b();
    }
}
