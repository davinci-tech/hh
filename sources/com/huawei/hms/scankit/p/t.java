package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class t {

    /* renamed from: a, reason: collision with root package name */
    private final s f5881a;
    private a8 b;
    private m3 c;
    private boolean d;

    t(s sVar) throws a {
        int c = sVar.c();
        if (c < 21 || (c & 3) != 1) {
            throw a.a();
        }
        this.f5881a = sVar;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.f5881a.b(i2, i) : this.f5881a.b(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    byte[] b() throws a {
        m3 c = c();
        a8 d = d();
        f1 f1Var = f1.values()[c.b()];
        int c2 = this.f5881a.c();
        f1Var.a(this.f5881a, c2);
        s a2 = d.a();
        byte[] bArr = new byte[d.l()];
        int i = c2 - 1;
        boolean z = true;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            for (int i6 = 0; i6 < c2; i6++) {
                int i7 = z ? i - i6 : i6;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i2 - i8;
                    if (!a2.b(i9, i7)) {
                        i5++;
                        i4 <<= 1;
                        if (this.f5881a.b(i9, i7)) {
                            i4 |= 1;
                        }
                        if (i5 == 8) {
                            bArr[i3] = (byte) i4;
                            i3++;
                            i4 = 0;
                            i5 = 0;
                        }
                    }
                }
            }
            z = !z;
            i2 -= 2;
        }
        if (i3 == d.l()) {
            return bArr;
        }
        throw a.a();
    }

    m3 c() throws a {
        m3 m3Var = this.c;
        if (m3Var != null) {
            return m3Var;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 = a(i3, 8, i2);
        }
        int a2 = a(8, 7, a(8, 8, a(7, 8, i2)));
        for (int i4 = 5; i4 >= 0; i4--) {
            a2 = a(8, i4, a2);
        }
        int c = this.f5881a.c();
        for (int i5 = c - 1; i5 >= c - 7; i5--) {
            i = a(8, i5, i);
        }
        for (int i6 = c - 8; i6 < c; i6++) {
            i = a(i6, 8, i);
        }
        m3 a3 = m3.a(a2, i);
        this.c = a3;
        if (a3 != null) {
            return a3;
        }
        throw a.a();
    }

    a8 d() throws a {
        a8 a8Var = this.b;
        if (a8Var != null) {
            return a8Var;
        }
        int c = this.f5881a.c();
        int i = (c - 17) / 4;
        if (i <= 6) {
            return a8.b(i);
        }
        int i2 = c - 11;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = c - 9; i6 >= i2; i6--) {
                i4 = a(i6, i5, i4);
            }
        }
        a8 a2 = a8.a(i4);
        if (a2 != null && a2.k() == c) {
            this.b = a2;
            return a2;
        }
        for (int i7 = 5; i7 >= 0; i7--) {
            for (int i8 = c - 9; i8 >= i2; i8--) {
                i3 = a(i7, i8, i3);
            }
        }
        a8 a3 = a8.a(i3);
        if (a3 == null || a3.k() != c) {
            throw a.a();
        }
        this.b = a3;
        return a3;
    }

    void e() {
        if (this.c == null) {
            return;
        }
        f1.values()[this.c.b()].a(this.f5881a, this.f5881a.c());
    }

    void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }

    void a() throws a {
        if (this.f5881a != null) {
            int i = 0;
            while (i < this.f5881a.e()) {
                int i2 = i + 1;
                for (int i3 = i2; i3 < this.f5881a.c(); i3++) {
                    if (this.f5881a.b(i, i3) != this.f5881a.b(i3, i)) {
                        this.f5881a.a(i3, i);
                        this.f5881a.a(i, i3);
                    }
                }
                i = i2;
            }
            return;
        }
        throw a.a();
    }
}
