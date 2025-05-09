package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class mb extends nb {
    public static int a(na naVar, boolean z, long j, short s, int i, short s2, short s3) {
        naVar.b(6);
        a(naVar, j);
        a(naVar, i);
        c(naVar, s3);
        b(naVar, s2);
        a(naVar, s);
        a(naVar, z);
        return a(naVar);
    }

    private static void a(na naVar, boolean z) {
        naVar.a(z);
    }

    private static void a(na naVar, long j) {
        naVar.a(1, j);
    }

    private static void a(na naVar, short s) {
        naVar.a(2, s);
    }

    private static void a(na naVar, int i) {
        naVar.b(3, i);
    }

    private static void b(na naVar, short s) {
        naVar.a(4, s);
    }

    private static void c(na naVar, short s) {
        naVar.a(5, s);
    }

    private static int a(na naVar) {
        return naVar.b();
    }
}
