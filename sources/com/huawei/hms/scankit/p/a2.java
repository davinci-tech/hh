package com.huawei.hms.scankit.p;

import java.util.Formatter;

/* loaded from: classes9.dex */
class a2 {

    /* renamed from: a, reason: collision with root package name */
    private final a0 f5729a;
    private final x0[] b;

    a2(a0 a0Var) {
        this.f5729a = new a0(a0Var);
        this.b = new x0[(a0Var.d() - a0Var.f()) + 1];
    }

    final void a(int i, x0 x0Var) {
        this.b[c(i)] = x0Var;
    }

    final x0 b(int i) {
        x0 x0Var;
        x0 x0Var2;
        x0 a2 = a(i);
        if (a2 != null) {
            return a2;
        }
        for (int i2 = 1; i2 < 5; i2++) {
            int c = c(i) - i2;
            if (c >= 0 && (x0Var2 = this.b[c]) != null) {
                return x0Var2;
            }
            int c2 = c(i) + i2;
            x0[] x0VarArr = this.b;
            if (c2 < x0VarArr.length && (x0Var = x0VarArr[c2]) != null) {
                return x0Var;
            }
        }
        return null;
    }

    final int c(int i) {
        return i - this.f5729a.f();
    }

    public String toString() {
        Formatter formatter = new Formatter();
        try {
            int i = 0;
            for (x0 x0Var : this.b) {
                if (x0Var == null) {
                    formatter.format("%3d:    |   %n", Integer.valueOf(i));
                } else {
                    formatter.format("%3d: %3d|%3d%n", Integer.valueOf(i), Integer.valueOf(x0Var.c()), Integer.valueOf(x0Var.e()));
                }
                i++;
            }
            String formatter2 = formatter.toString();
            formatter.close();
            return formatter2;
        } catch (Throwable th) {
            try {
                formatter.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    final x0 a(int i) {
        return this.b[c(i)];
    }

    final a0 a() {
        return this.f5729a;
    }

    final x0[] b() {
        return this.b;
    }
}
