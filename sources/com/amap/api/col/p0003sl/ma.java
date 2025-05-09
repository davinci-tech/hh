package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class ma extends nb {
    public static int a(na naVar, int i) {
        naVar.b(1);
        b(naVar, i);
        return a(naVar);
    }

    private static void b(na naVar, int i) {
        naVar.b(0, i);
    }

    public static int a(na naVar, int[] iArr) {
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
