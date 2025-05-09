package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class p6 {

    /* renamed from: a, reason: collision with root package name */
    private final o3 f5856a;

    public p6(o3 o3Var) {
        this.f5856a = o3Var;
    }

    public void a(int[] iArr, int i) throws a {
        p3 p3Var = new p3(this.f5856a, iArr);
        int[] iArr2 = new int[i];
        boolean z = true;
        for (int i2 = 0; i2 < i; i2++) {
            o3 o3Var = this.f5856a;
            int a2 = p3Var.a(o3Var.a(o3Var.a() + i2));
            iArr2[(i - 1) - i2] = a2;
            if (a2 != 0) {
                z = false;
            }
        }
        if (z) {
            return;
        }
        p3[] a3 = a(this.f5856a.b(i, 1), new p3(this.f5856a, iArr2), i);
        p3 p3Var2 = a3[0];
        p3 p3Var3 = a3[1];
        int[] a4 = a(p3Var2);
        int[] a5 = a(p3Var3, a4);
        for (int i3 = 0; i3 < a4.length; i3++) {
            int length = (iArr.length - 1) - this.f5856a.c(a4[i3]);
            if (length < 0) {
                throw a.a("Bad error location");
            }
            iArr[length] = o3.a(iArr[length], a5[i3]);
        }
    }

    private p3[] a(p3 p3Var, p3 p3Var2, int i) throws a {
        if (p3Var.b() < p3Var2.b()) {
            p3Var2 = p3Var;
            p3Var = p3Var2;
        }
        p3 d = this.f5856a.d();
        p3 b = this.f5856a.b();
        do {
            p3 p3Var3 = p3Var2;
            p3Var2 = p3Var;
            p3Var = p3Var3;
            p3 p3Var4 = b;
            p3 p3Var5 = d;
            d = p3Var4;
            if (p3Var.b() >= i / 2) {
                if (!p3Var.c()) {
                    p3 d2 = this.f5856a.d();
                    int b2 = this.f5856a.b(p3Var.b(p3Var.b()));
                    while (p3Var2.b() >= p3Var.b() && !p3Var2.c()) {
                        int b3 = p3Var2.b() - p3Var.b();
                        int c = this.f5856a.c(p3Var2.b(p3Var2.b()), b2);
                        d2 = d2.a(this.f5856a.b(b3, c));
                        p3Var2 = p3Var2.a(p3Var.a(b3, c));
                    }
                    b = d2.c(d).a(p3Var5);
                } else {
                    throw a.a("r_{i-1} was zero");
                }
            } else {
                int b4 = d.b(0);
                if (b4 != 0) {
                    int b5 = this.f5856a.b(b4);
                    return new p3[]{d.c(b5), p3Var.c(b5)};
                }
                throw a.a("sigmaTilde(0) was zero");
            }
        } while (p3Var2.b() < p3Var.b());
        throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
    }

    private int[] a(p3 p3Var) throws a {
        int b = p3Var.b();
        if (b == 1) {
            return new int[]{p3Var.b(1)};
        }
        int[] iArr = new int[b];
        int i = 0;
        for (int i2 = 1; i2 < this.f5856a.c() && i < b; i2++) {
            if (p3Var.a(i2) == 0) {
                iArr[i] = this.f5856a.b(i2);
                i++;
            }
        }
        if (i == b) {
            return iArr;
        }
        throw a.a("Error locator degree does not match number of roots");
    }

    private int[] a(p3 p3Var, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int b = this.f5856a.b(iArr[i]);
            int i2 = 1;
            for (int i3 = 0; i3 < length; i3++) {
                if (i != i3) {
                    int c = this.f5856a.c(iArr[i3], b);
                    i2 = this.f5856a.c(i2, (c & 1) == 0 ? c | 1 : c & (-2));
                }
            }
            iArr2[i] = this.f5856a.c(p3Var.a(b), this.f5856a.b(i2));
            if (this.f5856a.a() != 0) {
                iArr2[i] = this.f5856a.c(iArr2[i], b);
            }
        }
        return iArr2;
    }
}
