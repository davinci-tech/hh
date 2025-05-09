package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class p7 {
    private static final int[] c = {1, 1, 2};

    /* renamed from: a, reason: collision with root package name */
    private final n7 f5857a = new n7();
    private final o7 b = new o7();

    p7() {
    }

    s6 a(int i, r rVar, int i2) throws a {
        int[] a2 = q7.a(rVar, i2, false, c);
        try {
            return this.b.a(i, rVar, a2);
        } catch (a unused) {
            return this.f5857a.a(i, rVar, a2);
        }
    }
}
