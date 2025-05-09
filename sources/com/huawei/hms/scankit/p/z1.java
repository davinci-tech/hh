package com.huawei.hms.scankit.p;

import java.util.Formatter;

/* loaded from: classes9.dex */
final class z1 {

    /* renamed from: a, reason: collision with root package name */
    private final k f5930a;
    private final a2[] b;
    private a0 c;
    private final int d;

    z1(k kVar, a0 a0Var) {
        this.f5930a = kVar;
        int a2 = kVar.a();
        this.d = a2;
        this.c = a0Var;
        this.b = new a2[a2 + 2];
    }

    private void a(a2 a2Var) throws a {
        if (a2Var != null) {
            try {
                ((b2) a2Var).a(this.f5930a);
            } catch (ClassCastException unused) {
                throw a.a();
            }
        }
    }

    private int b() {
        c();
        return d() + e();
    }

    private void c() {
        a2[] a2VarArr = this.b;
        a2 a2Var = a2VarArr[0];
        if (a2Var == null || a2VarArr[this.d + 1] == null) {
            return;
        }
        x0[] b = a2Var.b();
        x0[] b2 = this.b[this.d + 1].b();
        for (int i = 0; i < b.length; i++) {
            x0 x0Var = b[i];
            if (x0Var != null && b2[i] != null && x0Var.c() == b2[i].c()) {
                for (int i2 = 1; i2 <= this.d; i2++) {
                    x0 x0Var2 = this.b[i2].b()[i];
                    if (x0Var2 != null) {
                        x0Var2.b(b[i].c());
                        if (!x0Var2.g()) {
                            this.b[i2].b()[i] = null;
                        }
                    }
                }
            }
        }
    }

    private int d() {
        a2 a2Var = this.b[0];
        if (a2Var == null) {
            return 0;
        }
        x0[] b = a2Var.b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            x0 x0Var = b[i2];
            if (x0Var != null) {
                int c = x0Var.c();
                int i3 = 0;
                for (int i4 = 1; i4 < this.d + 1 && i3 < 2; i4++) {
                    x0 x0Var2 = this.b[i4].b()[i2];
                    if (x0Var2 != null) {
                        i3 = a(c, i3, x0Var2);
                        if (!x0Var2.g()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    private int e() {
        a2 a2Var = this.b[this.d + 1];
        if (a2Var == null) {
            return 0;
        }
        x0[] b = a2Var.b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            x0 x0Var = b[i2];
            if (x0Var != null) {
                int c = x0Var.c();
                int i3 = 0;
                for (int i4 = this.d + 1; i4 > 0 && i3 < 2; i4--) {
                    x0 x0Var2 = this.b[i4].b()[i2];
                    if (x0Var2 != null) {
                        i3 = a(c, i3, x0Var2);
                        if (!x0Var2.g()) {
                            i++;
                        }
                    }
                }
            }
        }
        return i;
    }

    int f() {
        return this.d;
    }

    int g() {
        return this.f5930a.b();
    }

    int h() {
        return this.f5930a.c();
    }

    a0 i() {
        return this.c;
    }

    a2[] j() throws a {
        a(this.b[0]);
        a(this.b[this.d + 1]);
        int i = 928;
        while (true) {
            int a2 = a();
            if (a2 <= 0 || a2 >= i) {
                break;
            }
            i = a2;
        }
        return this.b;
    }

    public String toString() {
        a2[] a2VarArr = this.b;
        a2 a2Var = a2VarArr[0];
        if (a2Var == null) {
            a2Var = a2VarArr[this.d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < a2Var.b().length; i++) {
            try {
                formatter.format("CW %3d:", Integer.valueOf(i));
                for (int i2 = 0; i2 < this.d + 2; i2++) {
                    a2 a2Var2 = this.b[i2];
                    if (a2Var2 == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        x0 x0Var = a2Var2.b()[i];
                        if (x0Var == null) {
                            formatter.format("    |   ", new Object[0]);
                        } else {
                            formatter.format(" %3d|%3d", Integer.valueOf(x0Var.c()), Integer.valueOf(x0Var.e()));
                        }
                    }
                }
                formatter.format("%n", new Object[0]);
            } catch (Throwable th) {
                try {
                    formatter.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private int a() {
        int b = b();
        if (b == 0) {
            return 0;
        }
        for (int i = 1; i < this.d + 1; i++) {
            x0[] b2 = this.b[i].b();
            for (int i2 = 0; i2 < b2.length; i2++) {
                x0 x0Var = b2[i2];
                if (x0Var != null && !x0Var.g()) {
                    a(i, i2, b2);
                }
            }
        }
        return b;
    }

    private static int a(int i, int i2, x0 x0Var) {
        if (x0Var == null || x0Var.g()) {
            return i2;
        }
        if (!x0Var.a(i)) {
            return i2 + 1;
        }
        x0Var.b(i);
        return 0;
    }

    private void a(int i, int i2, x0[] x0VarArr) {
        x0 x0Var = x0VarArr[i2];
        x0[] b = this.b[i - 1].b();
        a2 a2Var = this.b[i + 1];
        x0[] b2 = a2Var != null ? a2Var.b() : b;
        x0[] x0VarArr2 = new x0[14];
        x0VarArr2[2] = b[i2];
        x0VarArr2[3] = b2[i2];
        if (i2 > 0) {
            int i3 = i2 - 1;
            x0VarArr2[0] = x0VarArr[i3];
            x0VarArr2[4] = b[i3];
            x0VarArr2[5] = b2[i3];
        }
        if (i2 > 1) {
            int i4 = i2 - 2;
            x0VarArr2[8] = x0VarArr[i4];
            x0VarArr2[10] = b[i4];
            x0VarArr2[11] = b2[i4];
        }
        if (i2 < x0VarArr.length - 1) {
            int i5 = i2 + 1;
            x0VarArr2[1] = x0VarArr[i5];
            x0VarArr2[6] = b[i5];
            x0VarArr2[7] = b2[i5];
        }
        if (i2 < x0VarArr.length - 2) {
            int i6 = i2 + 2;
            x0VarArr2[9] = x0VarArr[i6];
            x0VarArr2[12] = b[i6];
            x0VarArr2[13] = b2[i6];
        }
        for (int i7 = 0; i7 < 14 && !a(x0Var, x0VarArr2[i7]); i7++) {
        }
    }

    private static boolean a(x0 x0Var, x0 x0Var2) {
        if (x0Var2 == null || !x0Var2.g() || x0Var2.a() != x0Var.a()) {
            return false;
        }
        x0Var.b(x0Var2.c());
        return true;
    }

    void a(a0 a0Var) {
        this.c = a0Var;
    }

    void a(int i, a2 a2Var) {
        this.b[i] = a2Var;
    }

    a2 a(int i) {
        return this.b[i];
    }
}
