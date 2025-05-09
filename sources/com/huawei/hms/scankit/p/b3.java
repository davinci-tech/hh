package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
public enum b3 {
    L(1),
    M(0),
    Q(3),
    H(2);

    private static final b3[] f;

    /* renamed from: a, reason: collision with root package name */
    private final int f5741a;

    static {
        b3 b3Var = L;
        b3 b3Var2 = M;
        b3 b3Var3 = Q;
        f = new b3[]{b3Var2, b3Var, H, b3Var3};
    }

    b3(int i) {
        this.f5741a = i;
    }

    public int a() {
        return this.f5741a;
    }

    public static b3 a(int i) {
        if (i >= 0) {
            b3[] b3VarArr = f;
            if (i < b3VarArr.length) {
                return b3VarArr[i];
            }
        }
        throw new IllegalArgumentException();
    }
}
