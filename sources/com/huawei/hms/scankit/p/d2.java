package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class d2 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5760a;
    private final j8 b;

    public d2(s sVar) throws a {
        this.f5760a = sVar;
        this.b = new j8(sVar);
    }

    private u6[] b(u6[] u6VarArr) {
        u6 u6Var = u6VarArr[0];
        u6 u6Var2 = u6VarArr[1];
        u6 u6Var3 = u6VarArr[3];
        u6 u6Var4 = u6VarArr[2];
        int a2 = a(u6Var, u6Var2);
        int a3 = a(u6Var2, u6Var3);
        int a4 = a(u6Var3, u6Var4);
        int a5 = a(u6Var4, u6Var);
        u6[] u6VarArr2 = {u6Var4, u6Var, u6Var2, u6Var3};
        if (a2 > a3) {
            u6VarArr2[0] = u6Var;
            u6VarArr2[1] = u6Var2;
            u6VarArr2[2] = u6Var3;
            u6VarArr2[3] = u6Var4;
            a2 = a3;
        }
        if (a2 > a4) {
            u6VarArr2[0] = u6Var2;
            u6VarArr2[1] = u6Var3;
            u6VarArr2[2] = u6Var4;
            u6VarArr2[3] = u6Var;
        } else {
            a4 = a2;
        }
        if (a4 > a5) {
            u6VarArr2[0] = u6Var3;
            u6VarArr2[1] = u6Var4;
            u6VarArr2[2] = u6Var;
            u6VarArr2[3] = u6Var2;
        }
        return u6VarArr2;
    }

    private u6[] c(u6[] u6VarArr) {
        u6 u6Var = u6VarArr[0];
        u6 u6Var2 = u6VarArr[1];
        u6 u6Var3 = u6VarArr[2];
        u6 u6Var4 = u6VarArr[3];
        int a2 = (a(u6Var, u6Var4) + 1) * 4;
        if (a(a(u6Var2, u6Var3, a2), u6Var) < a(a(u6Var3, u6Var2, a2), u6Var4)) {
            u6VarArr[0] = u6Var;
            u6VarArr[1] = u6Var2;
            u6VarArr[2] = u6Var3;
            u6VarArr[3] = u6Var4;
        } else {
            u6VarArr[0] = u6Var2;
            u6VarArr[1] = u6Var3;
            u6VarArr[2] = u6Var4;
            u6VarArr[3] = u6Var;
        }
        return u6VarArr;
    }

    private u6[] d(u6[] u6VarArr) {
        u6 u6Var = u6VarArr[0];
        u6 u6Var2 = u6VarArr[1];
        u6 u6Var3 = u6VarArr[2];
        u6 u6Var4 = u6VarArr[3];
        int a2 = a(u6Var, u6Var4);
        u6 a3 = a(u6Var, u6Var2, (a(u6Var3, u6Var4) + 1) * 4);
        u6 a4 = a(u6Var3, u6Var2, (a2 + 1) * 4);
        int a5 = a(a3, u6Var4);
        int i = a5 + 1;
        int a6 = a(a4, u6Var4);
        int i2 = a6 + 1;
        if ((i & 1) == 1) {
            i = a5 + 2;
        }
        if ((i2 & 1) == 1) {
            i2 = a6 + 2;
        }
        float b = (((u6Var.b() + u6Var2.b()) + u6Var3.b()) + u6Var4.b()) / 4.0f;
        float c = (((u6Var.c() + u6Var2.c()) + u6Var3.c()) + u6Var4.c()) / 4.0f;
        u6 a7 = a(u6Var, b, c);
        u6 a8 = a(u6Var2, b, c);
        u6 a9 = a(u6Var3, b, c);
        u6 a10 = a(u6Var4, b, c);
        int i3 = i2 * 4;
        int i4 = i * 4;
        return new u6[]{a(a(a7, a8, i3), a10, i4), a(a(a8, a7, i3), a9, i4), a(a(a9, a10, i3), a8, i4), a(a(a10, a9, i3), a7, i4)};
    }

    public j2 a() throws a {
        int i;
        int i2;
        u6[] c = c(b(this.b.a()));
        u6 a2 = a(c);
        c[3] = a2;
        if (a2 == null) {
            throw a.a();
        }
        u6[] d = d(c);
        u6 u6Var = d[0];
        u6 u6Var2 = d[1];
        u6 u6Var3 = d[2];
        u6 u6Var4 = d[3];
        int a3 = a(u6Var, u6Var4);
        int i3 = a3 + 1;
        int a4 = a(u6Var3, u6Var4);
        int i4 = a4 + 1;
        if ((i3 & 1) == 1) {
            i3 = a3 + 2;
        }
        if ((i4 & 1) == 1) {
            i4 = a4 + 2;
        }
        if (i3 * 4 >= i4 * 7 || i4 * 4 >= i3 * 7) {
            i = i3;
            i2 = i4;
        } else {
            i = Math.max(i3, i4);
            i2 = i;
        }
        return new j2(a(this.f5760a, u6Var, u6Var2, u6Var3, u6Var4, i, i2), new u6[]{u6Var, u6Var2, u6Var3, u6Var4});
    }

    private static u6 a(u6 u6Var, u6 u6Var2, int i) {
        float f = i + 1;
        return new u6(u6Var.b() + ((u6Var2.b() - u6Var.b()) / f), u6Var.c() + ((u6Var2.c() - u6Var.c()) / f));
    }

    private static u6 a(u6 u6Var, float f, float f2) {
        float b = u6Var.b();
        float c = u6Var.c();
        return new u6(b < f ? b - 1.0f : b + 1.0f, c < f2 ? c - 1.0f : c + 1.0f);
    }

    private u6 a(u6[] u6VarArr) {
        u6 u6Var = u6VarArr[0];
        u6 u6Var2 = u6VarArr[1];
        u6 u6Var3 = u6VarArr[2];
        u6 u6Var4 = u6VarArr[3];
        int a2 = a(u6Var, u6Var4);
        u6 a3 = a(u6Var, u6Var2, (a(u6Var2, u6Var4) + 1) * 4);
        u6 a4 = a(u6Var3, u6Var2, (a2 + 1) * 4);
        int a5 = a(a3, u6Var4);
        int a6 = a(a4, u6Var4);
        float f = a5 + 1;
        u6 u6Var5 = new u6(u6Var4.b() + ((u6Var3.b() - u6Var2.b()) / f), u6Var4.c() + ((u6Var3.c() - u6Var2.c()) / f));
        float f2 = a6 + 1;
        u6 u6Var6 = new u6(u6Var4.b() + ((u6Var.b() - u6Var2.b()) / f2), u6Var4.c() + ((u6Var.c() - u6Var2.c()) / f2));
        if (a(u6Var5)) {
            return (a(u6Var6) && a(a3, u6Var5) + a(a4, u6Var5) <= a(a3, u6Var6) + a(a4, u6Var6)) ? u6Var6 : u6Var5;
        }
        if (a(u6Var6)) {
            return u6Var6;
        }
        return null;
    }

    private boolean a(u6 u6Var) {
        return u6Var.b() >= 0.0f && u6Var.b() < ((float) this.f5760a.e()) && u6Var.c() > 0.0f && u6Var.c() < ((float) this.f5760a.c());
    }

    private static s a(s sVar, u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4, int i, int i2) throws a {
        float f = i - 0.5f;
        float f2 = i2 - 0.5f;
        return s3.a().a(sVar, i, i2, 0.5f, 0.5f, f, 0.5f, f, f2, 0.5f, f2, u6Var.b(), u6Var.c(), u6Var4.b(), u6Var4.c(), u6Var3.b(), u6Var3.c(), u6Var2.b(), u6Var2.c());
    }

    private int a(u6 u6Var, u6 u6Var2) {
        int i;
        int i2;
        d2 d2Var = this;
        int b = (int) u6Var.b();
        int c = (int) u6Var.c();
        int b2 = (int) u6Var2.b();
        int c2 = (int) u6Var2.c();
        boolean z = Math.abs(c2 - c) > Math.abs(b2 - b);
        if (z) {
            c = b;
            b = c;
            c2 = b2;
            b2 = c2;
        }
        int abs = Math.abs(b2 - b);
        int abs2 = Math.abs(c2 - c);
        int i3 = (-abs) / 2;
        int i4 = c < c2 ? 1 : -1;
        int i5 = b < b2 ? 1 : -1;
        boolean b3 = d2Var.f5760a.b(z ? c : b, z ? b : c);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (b != b2) {
            int i9 = b2;
            boolean b4 = d2Var.f5760a.b(z ? c : b, z ? b : c);
            i8++;
            if (b4 != b3) {
                i2 = c2;
                i = b;
                if (i8 > Math.ceil(i6 / 1.5d)) {
                    i7++;
                    i6 -= (i6 - i8) / i7;
                    b3 = b4;
                    i8 = 0;
                }
            } else {
                i = b;
                i2 = c2;
            }
            i3 += abs2;
            int i10 = i2;
            if (i3 > 0) {
                if (c == i10) {
                    break;
                }
                c += i4;
                i3 -= abs;
            }
            b = i + i5;
            d2Var = this;
            c2 = i10;
            b2 = i9;
        }
        return i7;
    }
}
