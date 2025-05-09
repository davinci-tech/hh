package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class a0 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5727a;
    private final u6 b;
    private final u6 c;
    private final u6 d;
    private final u6 e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;

    a0(s sVar, u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4) throws a {
        boolean z = u6Var == null || u6Var2 == null;
        boolean z2 = u6Var3 == null || u6Var4 == null;
        if (z && z2) {
            throw a.a();
        }
        if (z) {
            u6Var = new u6(0.0f, u6Var3.c());
            u6Var2 = new u6(0.0f, u6Var4.c());
        } else if (z2) {
            u6Var3 = new u6(sVar.e() - 1, u6Var.c());
            u6Var4 = new u6(sVar.e() - 1, u6Var2.c());
        }
        this.f5727a = sVar;
        this.b = u6Var;
        this.c = u6Var2;
        this.d = u6Var3;
        this.e = u6Var4;
        this.f = (int) Math.min(u6Var.b(), u6Var2.b());
        this.g = (int) Math.max(u6Var3.b(), u6Var4.b());
        this.h = (int) Math.min(u6Var.c(), u6Var3.c());
        this.i = (int) Math.max(u6Var2.c(), u6Var4.c());
    }

    static a0 a(a0 a0Var, a0 a0Var2) throws a {
        return a0Var == null ? a0Var2 : a0Var2 == null ? a0Var : new a0(a0Var.f5727a, a0Var.b, a0Var.c, a0Var2.d, a0Var2.e);
    }

    u6 b() {
        return this.e;
    }

    int c() {
        return this.g;
    }

    int d() {
        return this.i;
    }

    int e() {
        return this.f;
    }

    int f() {
        return this.h;
    }

    u6 g() {
        return this.b;
    }

    u6 h() {
        return this.d;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    com.huawei.hms.scankit.p.a0 a(int r11, int r12, boolean r13) throws com.huawei.hms.scankit.p.a {
        /*
            r10 = this;
            com.huawei.hms.scankit.p.u6 r0 = r10.b
            com.huawei.hms.scankit.p.u6 r1 = r10.c
            com.huawei.hms.scankit.p.u6 r2 = r10.d
            com.huawei.hms.scankit.p.u6 r3 = r10.e
            if (r11 <= 0) goto L29
            if (r13 == 0) goto Le
            r4 = r0
            goto Lf
        Le:
            r4 = r2
        Lf:
            float r5 = r4.c()
            int r5 = (int) r5
            int r5 = r5 - r11
            if (r5 >= 0) goto L18
            r5 = 0
        L18:
            com.huawei.hms.scankit.p.u6 r11 = new com.huawei.hms.scankit.p.u6
            float r4 = r4.b()
            float r5 = (float) r5
            r11.<init>(r4, r5)
            if (r13 == 0) goto L26
            r0 = r11
            goto L29
        L26:
            r8 = r11
            r6 = r0
            goto L2b
        L29:
            r6 = r0
            r8 = r2
        L2b:
            if (r12 <= 0) goto L5b
            if (r13 == 0) goto L32
            com.huawei.hms.scankit.p.u6 r11 = r10.c
            goto L34
        L32:
            com.huawei.hms.scankit.p.u6 r11 = r10.e
        L34:
            float r0 = r11.c()
            int r0 = (int) r0
            int r0 = r0 + r12
            com.huawei.hms.scankit.p.s r12 = r10.f5727a
            int r12 = r12.c()
            if (r0 < r12) goto L4a
            com.huawei.hms.scankit.p.s r12 = r10.f5727a
            int r12 = r12.c()
            int r0 = r12 + (-1)
        L4a:
            com.huawei.hms.scankit.p.u6 r12 = new com.huawei.hms.scankit.p.u6
            float r11 = r11.b()
            float r0 = (float) r0
            r12.<init>(r11, r0)
            if (r13 == 0) goto L58
            r1 = r12
            goto L5b
        L58:
            r9 = r12
            r7 = r1
            goto L5d
        L5b:
            r7 = r1
            r9 = r3
        L5d:
            com.huawei.hms.scankit.p.a0 r11 = new com.huawei.hms.scankit.p.a0
            com.huawei.hms.scankit.p.s r5 = r10.f5727a
            r4 = r11
            r4.<init>(r5, r6, r7, r8, r9)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.a0.a(int, int, boolean):com.huawei.hms.scankit.p.a0");
    }

    a0(a0 a0Var) {
        this.f5727a = a0Var.f5727a;
        this.b = a0Var.g();
        this.c = a0Var.a();
        this.d = a0Var.h();
        this.e = a0Var.b();
        this.f = a0Var.e();
        this.g = a0Var.c();
        this.h = a0Var.f();
        this.i = a0Var.d();
    }

    u6 a() {
        return this.c;
    }
}
