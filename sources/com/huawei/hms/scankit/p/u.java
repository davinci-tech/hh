package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class u {

    /* renamed from: a, reason: collision with root package name */
    private final s f5887a;
    private b8 b;
    private l3 c;
    private boolean d;

    u(s sVar) throws a {
        int c = sVar.c();
        if (c < 21 || (c & 3) != 1) {
            throw a.a();
        }
        this.f5887a = sVar;
    }

    private int a(int i, int i2, int i3) {
        return this.d ? this.f5887a.b(i2, i) : this.f5887a.b(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    byte[] b() throws a {
        l3 c = c();
        b8 d = d();
        g1 g1Var = g1.values()[c.a()];
        int c2 = this.f5887a.c();
        g1Var.a(this.f5887a, c2);
        s a2 = d.a();
        byte[] bArr = new byte[d.e()];
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
                        if (this.f5887a.b(i9, i7)) {
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
        if (i3 == d.e()) {
            return bArr;
        }
        throw a.a();
    }

    l3 c() throws a {
        l3 l3Var = this.c;
        if (l3Var != null) {
            return l3Var;
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
        int c = this.f5887a.c();
        for (int i5 = c - 1; i5 >= c - 7; i5--) {
            i = a(8, i5, i);
        }
        for (int i6 = c - 8; i6 < c; i6++) {
            i = a(i6, 8, i);
        }
        l3 a3 = l3.a(a2, i);
        this.c = a3;
        if (a3 != null) {
            return a3;
        }
        throw a.a();
    }

    b8 d() throws a {
        b8 b8Var = this.b;
        if (b8Var != null) {
            return b8Var;
        }
        int c = this.f5887a.c();
        int i = (c - 17) / 4;
        if (i <= 6) {
            return b8.c(i);
        }
        int i2 = c - 11;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = c - 9; i6 >= i2; i6--) {
                i4 = a(i6, i5, i4);
            }
        }
        b8 a2 = b8.a(i4);
        if (a2 != null && a2.d() == c) {
            this.b = a2;
            return a2;
        }
        for (int i7 = 5; i7 >= 0; i7--) {
            for (int i8 = c - 9; i8 >= i2; i8--) {
                i3 = a(i7, i8, i3);
            }
        }
        b8 a3 = b8.a(i3);
        if (a3 == null || a3.d() != c) {
            throw a.a();
        }
        this.b = a3;
        return a3;
    }

    void e() {
        if (this.c == null) {
            return;
        }
        g1.values()[this.c.a()].a(this.f5887a, this.f5887a.c());
    }

    void a(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }

    void a() {
        int i = 0;
        while (i < this.f5887a.e()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f5887a.c(); i3++) {
                if (this.f5887a.b(i, i3) != this.f5887a.b(i3, i)) {
                    this.f5887a.a(i3, i);
                    this.f5887a.a(i, i3);
                }
            }
            i = i2;
        }
    }
}
