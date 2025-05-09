package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class b2 extends a2 {
    private final boolean c;

    b2(a0 a0Var, boolean z) {
        super(a0Var);
        this.c = z;
    }

    private void b(k kVar) {
        a0 a2 = a();
        u6 g = this.c ? a2.g() : a2.h();
        u6 a3 = this.c ? a2.a() : a2.b();
        int c = c((int) a3.c());
        x0[] b = b();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        for (int c2 = c((int) g.c()); c2 < c; c2++) {
            x0 x0Var = b[c2];
            if (x0Var != null) {
                x0Var.h();
                int c3 = x0Var.c() - i;
                if (c3 == 0) {
                    i2++;
                } else {
                    if (c3 == 1) {
                        i3 = Math.max(i3, i2);
                        i = x0Var.c();
                    } else if (x0Var.c() >= kVar.c()) {
                        b[c2] = null;
                    } else {
                        i = x0Var.c();
                    }
                    i2 = 1;
                }
            }
        }
    }

    private void f() {
        for (x0 x0Var : b()) {
            if (x0Var != null) {
                x0Var.h();
            }
        }
    }

    void a(k kVar) throws a {
        int c;
        x0[] b = b();
        f();
        a(b, kVar);
        a0 a2 = a();
        u6 g = this.c ? a2.g() : a2.h();
        u6 a3 = this.c ? a2.a() : a2.b();
        int c2 = c((int) g.c());
        int c3 = c((int) a3.c());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (c2 < c3) {
            x0 x0Var = b[c2];
            if (x0Var != null) {
                int c4 = x0Var.c() - i;
                if (c4 == 0) {
                    i2++;
                } else {
                    if (c4 == 1) {
                        int max = Math.max(i3, i2);
                        c = x0Var.c();
                        i3 = max;
                    } else if (c4 < 0 || x0Var.c() >= kVar.c() || c4 > c2) {
                        b[c2] = null;
                    } else {
                        if (i3 > 2) {
                            c4 *= i3 - 2;
                        }
                        boolean z = c4 >= c2;
                        for (int i4 = 1; i4 <= c4 && !z; i4++) {
                            z = b[c2 - i4] != null;
                        }
                        if (z) {
                            b[c2] = null;
                        } else {
                            c = x0Var.c();
                        }
                    }
                    i = c;
                    i2 = 1;
                }
            }
            c2++;
        }
    }

    k c() throws a {
        x0[] b = b();
        m mVar = new m();
        m mVar2 = new m();
        m mVar3 = new m();
        m mVar4 = new m();
        for (x0 x0Var : b) {
            if (x0Var != null) {
                x0Var.h();
                int e = x0Var.e() % 30;
                int c = x0Var.c();
                if (!this.c) {
                    c += 2;
                }
                int i = c % 3;
                if (i == 0) {
                    mVar2.a((e * 3) + 1);
                } else if (i == 1) {
                    mVar4.a(e / 3);
                    mVar3.a(e % 3);
                } else {
                    if (i != 2) {
                        throw a.a();
                    }
                    mVar.a(e + 1);
                }
            }
        }
        if (mVar.a().length == 0 || mVar2.a().length == 0 || mVar3.a().length == 0 || mVar4.a().length == 0 || mVar.a()[0] < 1 || mVar2.a()[0] + mVar3.a()[0] < 3 || mVar2.a()[0] + mVar3.a()[0] > 90) {
            return null;
        }
        k kVar = new k(mVar.a()[0], mVar2.a()[0], mVar3.a()[0], mVar4.a()[0]);
        a(b, kVar);
        return kVar;
    }

    int[] d() throws a {
        int c;
        k c2 = c();
        if (c2 == null) {
            return null;
        }
        b(c2);
        int c3 = c2.c();
        int[] iArr = new int[c3];
        for (x0 x0Var : b()) {
            if (x0Var != null && (c = x0Var.c()) < c3) {
                iArr[c] = iArr[c] + 1;
            }
        }
        return iArr;
    }

    boolean e() {
        return this.c;
    }

    @Override // com.huawei.hms.scankit.p.a2
    public String toString() {
        return "IsLeft: " + this.c + '\n' + super.toString();
    }

    private void a(x0[] x0VarArr, k kVar) throws a {
        for (int i = 0; i < x0VarArr.length; i++) {
            x0 x0Var = x0VarArr[i];
            if (x0Var != null) {
                int e = x0Var.e() % 30;
                int c = x0Var.c();
                if (c > kVar.c()) {
                    x0VarArr[i] = null;
                } else {
                    if (!this.c) {
                        c += 2;
                    }
                    int i2 = c % 3;
                    if (i2 != 0) {
                        if (i2 != 1) {
                            if (i2 == 2) {
                                if (e + 1 != kVar.a()) {
                                    x0VarArr[i] = null;
                                }
                            } else {
                                throw a.a();
                            }
                        } else if (e / 3 != kVar.b() || e % 3 != kVar.d()) {
                            x0VarArr[i] = null;
                        }
                    } else if ((e * 3) + 1 != kVar.e()) {
                        x0VarArr[i] = null;
                    }
                }
            }
        }
    }
}
