package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class a3 {

    /* renamed from: a, reason: collision with root package name */
    private final w4 f5730a = w4.f;

    public int a(int[] iArr, int i, int[] iArr2) throws a {
        x4 x4Var = new x4(this.f5730a, iArr);
        int[] iArr3 = new int[i];
        boolean z = false;
        for (int i2 = i; i2 > 0; i2--) {
            int a2 = x4Var.a(this.f5730a.a(i2));
            iArr3[i - i2] = a2;
            if (a2 != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        x4 a3 = this.f5730a.a();
        if (iArr2 != null) {
            for (int i3 : iArr2) {
                int a4 = this.f5730a.a((iArr.length - 1) - i3);
                w4 w4Var = this.f5730a;
                a3 = a3.b(new x4(w4Var, new int[]{w4Var.d(0, a4), 1}));
            }
        }
        x4[] a5 = a(this.f5730a.b(i, 1), new x4(this.f5730a, iArr3), i);
        x4 x4Var2 = a5[0];
        x4 x4Var3 = a5[1];
        int[] a6 = a(x4Var2);
        int[] a7 = a(x4Var3, x4Var2, a6);
        for (int i4 = 0; i4 < a6.length; i4++) {
            int length = (iArr.length - 1) - this.f5730a.c(a6[i4]);
            if (length < 0) {
                throw a.a();
            }
            iArr[length] = this.f5730a.d(iArr[length], a7[i4]);
        }
        return a6.length;
    }

    private x4[] a(x4 x4Var, x4 x4Var2, int i) throws a {
        if (x4Var.a() < x4Var2.a()) {
            x4Var2 = x4Var;
            x4Var = x4Var2;
        }
        x4 c = this.f5730a.c();
        x4 a2 = this.f5730a.a();
        while (true) {
            x4 x4Var3 = x4Var2;
            x4Var2 = x4Var;
            x4Var = x4Var3;
            x4 x4Var4 = a2;
            x4 x4Var5 = c;
            c = x4Var4;
            if (x4Var.a() >= i / 2) {
                if (!x4Var.b()) {
                    x4 c2 = this.f5730a.c();
                    int b = this.f5730a.b(x4Var.b(x4Var.a()));
                    while (x4Var2.a() >= x4Var.a() && !x4Var2.b()) {
                        int a3 = x4Var2.a() - x4Var.a();
                        int c3 = this.f5730a.c(x4Var2.b(x4Var2.a()), b);
                        c2 = c2.a(this.f5730a.b(a3, c3));
                        x4Var2 = x4Var2.c(x4Var.a(a3, c3));
                    }
                    a2 = c2.b(c).c(x4Var5).c();
                } else {
                    throw a.a();
                }
            } else {
                int b2 = c.b(0);
                if (b2 != 0) {
                    int b3 = this.f5730a.b(b2);
                    return new x4[]{c.c(b3), x4Var.c(b3)};
                }
                throw a.a();
            }
        }
    }

    private int[] a(x4 x4Var) throws a {
        int a2 = x4Var.a();
        int[] iArr = new int[a2];
        int i = 0;
        for (int i2 = 1; i2 < this.f5730a.b() && i < a2; i2++) {
            if (x4Var.a(i2) == 0) {
                iArr[i] = this.f5730a.b(i2);
                i++;
            }
        }
        if (i == a2) {
            return iArr;
        }
        throw a.a();
    }

    private int[] a(x4 x4Var, x4 x4Var2, int[] iArr) {
        int a2 = x4Var2.a();
        int[] iArr2 = new int[a2];
        for (int i = 1; i <= a2; i++) {
            iArr2[a2 - i] = this.f5730a.c(i, x4Var2.b(i));
        }
        x4 x4Var3 = new x4(this.f5730a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int b = this.f5730a.b(iArr[i2]);
            iArr3[i2] = this.f5730a.c(this.f5730a.d(0, x4Var.a(b)), this.f5730a.b(x4Var3.a(b)));
        }
        return iArr3;
    }
}
