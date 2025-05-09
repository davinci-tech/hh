package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public enum c3 {
    L(1),
    M(0),
    Q(3),
    H(2);

    private static final c3[] f;

    /* renamed from: a, reason: collision with root package name */
    private final int f5753a;

    static {
        c3 c3Var = L;
        c3 c3Var2 = M;
        c3 c3Var3 = Q;
        f = new c3[]{c3Var2, c3Var, H, c3Var3};
    }

    c3(int i) {
        this.f5753a = i;
    }

    public static c3 a(int i) {
        if (i >= 0) {
            c3[] c3VarArr = f;
            if (i < c3VarArr.length) {
                return c3VarArr[i];
            }
        }
        throw new IllegalArgumentException();
    }
}
