package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class j8 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5811a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public j8(s sVar) throws a {
        this(sVar, 10, sVar.e() / 2, sVar.c() / 2);
    }

    private u6[] b(int[] iArr) throws a {
        int i = iArr[1] - iArr[0];
        u6 u6Var = null;
        u6 u6Var2 = null;
        for (int i2 = 1; u6Var2 == null && i2 < i; i2++) {
            u6Var2 = a(iArr[0], r6 - i2, r4 + i2, iArr[3]);
        }
        if (u6Var2 == null) {
            throw a.a();
        }
        u6 u6Var3 = null;
        for (int i3 = 1; u6Var3 == null && i3 < i; i3++) {
            u6Var3 = a(iArr[0], r8 + i3, r5 + i3, iArr[2]);
        }
        if (u6Var3 == null) {
            throw a.a();
        }
        u6 u6Var4 = null;
        for (int i4 = 1; u6Var4 == null && i4 < i; i4++) {
            u6Var4 = a(iArr[1], r10 + i4, r2 - i4, iArr[2]);
        }
        if (u6Var4 == null) {
            throw a.a();
        }
        for (int i5 = 1; u6Var == null && i5 < i; i5++) {
            u6Var = a(iArr[1], r9 - i5, r3 - i5, iArr[3]);
        }
        if (u6Var != null) {
            return a(u6Var, u6Var2, u6Var4, u6Var3);
        }
        throw a.a();
    }

    private void c(int[] iArr) {
        while (true) {
            int i = iArr[0];
            if (i < 0) {
                return;
            }
            boolean a2 = a(iArr[2], iArr[3], i, false);
            if (a2) {
                iArr[0] = iArr[0] - 1;
                iArr[5] = 1;
                iArr[9] = 1;
            } else if (iArr[9] != 1) {
                iArr[0] = iArr[0] - 1;
            }
            if (!a2 && iArr[9] == 1) {
                return;
            }
        }
    }

    private void d(int[] iArr) {
        while (true) {
            int i = iArr[1];
            if (i >= this.c) {
                return;
            }
            boolean a2 = a(iArr[2], iArr[3], i, false);
            if (a2) {
                iArr[1] = iArr[1] + 1;
                iArr[5] = 1;
                iArr[7] = 1;
            } else if (iArr[7] != 1) {
                iArr[1] = iArr[1] + 1;
            }
            if (!a2 && iArr[7] == 1) {
                return;
            }
        }
    }

    private void e(int[] iArr) {
        while (true) {
            int i = iArr[2];
            if (i < 0) {
                return;
            }
            boolean a2 = a(iArr[0], iArr[1], i, true);
            if (a2) {
                iArr[2] = iArr[2] - 1;
                iArr[5] = 1;
                iArr[10] = 1;
            } else if (iArr[10] != 1) {
                iArr[2] = iArr[2] - 1;
            }
            if (!a2 && iArr[10] == 1) {
                return;
            }
        }
    }

    public u6[] a() throws a {
        int[] iArr = {this.d, this.e, this.g, this.f, 0, 1, 0, 0, 0, 0, 0};
        while (true) {
            if (iArr[5] != 1) {
                break;
            }
            iArr[5] = 0;
            d(iArr);
            if (iArr[1] >= this.c) {
                iArr[4] = 1;
                break;
            }
            a(iArr);
            if (iArr[3] >= this.b) {
                iArr[4] = 1;
                break;
            }
            c(iArr);
            if (iArr[0] < 0) {
                iArr[4] = 1;
                break;
            }
            e(iArr);
            if (iArr[2] < 0) {
                iArr[4] = 1;
                break;
            }
            if (iArr[5] == 1) {
                iArr[6] = 1;
            }
        }
        if (iArr[4] == 1 || iArr[6] != 1) {
            throw a.a();
        }
        return b(iArr);
    }

    public j8(s sVar, int i, int i2, int i3) throws a {
        this.f5811a = sVar;
        int c = sVar.c();
        this.b = c;
        int e = sVar.e();
        this.c = e;
        int i4 = i / 2;
        int i5 = i2 - i4;
        this.d = i5;
        int i6 = i2 + i4;
        this.e = i6;
        int i7 = i3 - i4;
        this.g = i7;
        int i8 = i3 + i4;
        this.f = i8;
        if (i7 < 0 || i5 < 0 || i8 >= c || i6 >= e) {
            throw a.a();
        }
    }

    private void a(int[] iArr) {
        while (true) {
            int i = iArr[3];
            if (i >= this.b) {
                return;
            }
            boolean a2 = a(iArr[0], iArr[1], i, true);
            if (a2) {
                iArr[3] = iArr[3] + 1;
                iArr[5] = 1;
                iArr[8] = 1;
            } else if (iArr[8] != 1) {
                iArr[3] = iArr[3] + 1;
            }
            if (!a2 && iArr[8] == 1) {
                return;
            }
        }
    }

    private u6 a(float f, float f2, float f3, float f4) {
        int a2 = s4.a(s4.a(f, f2, f3, f4));
        float f5 = a2;
        float f6 = (f3 - f) / f5;
        float f7 = (f4 - f2) / f5;
        for (int i = 0; i < a2; i++) {
            float f8 = i;
            int a3 = s4.a((f8 * f6) + f);
            int a4 = s4.a((f8 * f7) + f2);
            if (this.f5811a.b(a3, a4)) {
                return new u6(a3, a4);
            }
        }
        return null;
    }

    private u6[] a(u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4) {
        float b = u6Var.b();
        float c = u6Var.c();
        float b2 = u6Var2.b();
        float c2 = u6Var2.c();
        float b3 = u6Var3.b();
        float c3 = u6Var3.c();
        float b4 = u6Var4.b();
        float c4 = u6Var4.c();
        if (b < this.c / 2.0f) {
            return new u6[]{new u6(b4 - 1.0f, c4 + 1.0f), new u6(b2 + 1.0f, c2 + 1.0f), new u6(b3 - 1.0f, c3 - 1.0f), new u6(b + 1.0f, c - 1.0f)};
        }
        return new u6[]{new u6(b4 + 1.0f, c4 + 1.0f), new u6(b2 + 1.0f, c2 - 1.0f), new u6(b3 - 1.0f, c3 + 1.0f), new u6(b - 1.0f, c - 1.0f)};
    }

    private boolean a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.f5811a.b(i, i3)) {
                    return true;
                }
                i++;
            }
            return false;
        }
        while (i <= i2) {
            if (this.f5811a.b(i3, i)) {
                return true;
            }
            i++;
        }
        return false;
    }
}
