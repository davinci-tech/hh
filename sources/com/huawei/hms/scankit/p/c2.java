package com.huawei.hms.scankit.p;

import com.huawei.hihealth.HiDataFilter;

/* loaded from: classes9.dex */
public final class c2 {
    private static final int[] g = {3808, 476, 2107, 1799};

    /* renamed from: a, reason: collision with root package name */
    private final s f5751a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private final int f5752a;
        private final int b;

        a(int i, int i2) {
            this.f5752a = i;
            this.b = i2;
        }

        u6 c() {
            return new u6(this.f5752a, this.b);
        }

        public String toString() {
            return HiDataFilter.DataFilterExpression.LESS_THAN + this.f5752a + ' ' + this.b + '>';
        }

        int a() {
            return this.f5752a;
        }

        int b() {
            return this.b;
        }
    }

    public c2(s sVar) {
        this.f5751a = sVar;
    }

    private a b() {
        u6 c;
        u6 c2;
        u6 u6Var;
        u6 u6Var2;
        u6 c3;
        u6 c4;
        u6 u6Var3;
        u6 u6Var4;
        try {
            u6[] a2 = new j8(this.f5751a).a();
            u6Var2 = a2[0];
            c = a2[1];
            u6Var = a2[2];
            c2 = a2[3];
        } catch (com.huawei.hms.scankit.p.a unused) {
            int e = this.f5751a.e() / 2;
            int c5 = this.f5751a.c() / 2;
            int i = e + 7;
            int i2 = c5 - 7;
            u6 c6 = a(new a(i, i2), false, 1, -1).c();
            int i3 = c5 + 7;
            c = a(new a(i, i3), false, 1, 1).c();
            int i4 = e - 7;
            u6 c7 = a(new a(i4, i3), false, -1, 1).c();
            c2 = a(new a(i4, i2), false, -1, -1).c();
            u6Var = c7;
            u6Var2 = c6;
        }
        int a3 = s4.a((((u6Var2.b() + c2.b()) + c.b()) + u6Var.b()) / 4.0f);
        int a4 = s4.a((((u6Var2.c() + c2.c()) + c.c()) + u6Var.c()) / 4.0f);
        try {
            u6[] a5 = new j8(this.f5751a, 15, a3, a4).a();
            c3 = a5[0];
            c4 = a5[1];
            u6Var3 = a5[2];
            u6Var4 = a5[3];
        } catch (com.huawei.hms.scankit.p.a unused2) {
            int i5 = a3 + 7;
            int i6 = a4 - 7;
            c3 = a(new a(i5, i6), false, 1, -1).c();
            int i7 = a4 + 7;
            c4 = a(new a(i5, i7), false, 1, 1).c();
            int i8 = a3 - 7;
            u6 c8 = a(new a(i8, i7), false, -1, 1).c();
            u6 c9 = a(new a(i8, i6), false, -1, -1).c();
            u6Var3 = c8;
            u6Var4 = c9;
        }
        return new a(s4.a((((c3.b() + u6Var4.b()) + c4.b()) + u6Var3.b()) / 4.0f), s4.a((((c3.c() + u6Var4.c()) + c4.c()) + u6Var3.c()) / 4.0f));
    }

    public g a(boolean z) throws com.huawei.hms.scankit.p.a {
        u6[] a2 = a(b());
        if (z) {
            u6 u6Var = a2[0];
            a2[0] = a2[2];
            a2[2] = u6Var;
        }
        a(a2);
        s sVar = this.f5751a;
        int i = this.f;
        return new g(a(sVar, a2[i % 4], a2[(i + 1) % 4], a2[(i + 2) % 4], a2[(i + 3) % 4]), b(a2), this.b, this.d, this.c);
    }

    private void a(u6[] u6VarArr) throws com.huawei.hms.scankit.p.a {
        long j;
        long j2;
        if (a(u6VarArr[0]) && a(u6VarArr[1]) && a(u6VarArr[2]) && a(u6VarArr[3])) {
            int i = this.e * 2;
            int[] iArr = {a(u6VarArr[0], u6VarArr[1], i), a(u6VarArr[1], u6VarArr[2], i), a(u6VarArr[2], u6VarArr[3], i), a(u6VarArr[3], u6VarArr[0], i)};
            this.f = a(iArr, i);
            long j3 = 0;
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = iArr[(this.f + i2) % 4];
                if (this.b) {
                    j = j3 << 7;
                    j2 = (i3 >> 1) & 127;
                } else {
                    j = j3 << 10;
                    j2 = ((i3 >> 2) & 992) + ((i3 >> 1) & 31);
                }
                j3 = j + j2;
            }
            int a2 = a(j3, this.b);
            if (this.b) {
                this.c = (a2 >> 6) + 1;
                this.d = (a2 & 63) + 1;
                return;
            } else {
                this.c = (a2 >> 11) + 1;
                this.d = (a2 & 2047) + 1;
                return;
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private u6[] b(u6[] u6VarArr) {
        return a(u6VarArr, this.e * 2, a());
    }

    private boolean b(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.a() - 3, aVar.b() + 3);
        a aVar6 = new a(aVar2.a() - 3, aVar2.b() - 3);
        a aVar7 = new a(aVar3.a() + 3, aVar3.b() - 3);
        a aVar8 = new a(aVar4.a() + 3, aVar4.b() + 3);
        int b = b(aVar8, aVar5);
        return b != 0 && b(aVar5, aVar6) == b && b(aVar6, aVar7) == b && b(aVar7, aVar8) == b;
    }

    private int b(a aVar, a aVar2) {
        float a2 = a(aVar, aVar2);
        float a3 = (aVar2.a() - aVar.a()) / a2;
        float b = (aVar2.b() - aVar.b()) / a2;
        float a4 = aVar.a();
        float b2 = aVar.b();
        boolean b3 = this.f5751a.b(aVar.a(), aVar.b());
        int ceil = (int) Math.ceil(a2);
        int i = 0;
        for (int i2 = 0; i2 < ceil; i2++) {
            a4 += a3;
            b2 += b;
            if (this.f5751a.b(s4.a(a4), s4.a(b2)) != b3) {
                i++;
            }
        }
        float f = i / a2;
        if (f <= 0.1f || f >= 0.9f) {
            return (f <= 0.1f) == b3 ? 1 : -1;
        }
        return 0;
    }

    private static int a(int[] iArr, int i) throws com.huawei.hms.scankit.p.a {
        int i2 = 0;
        for (int i3 : iArr) {
            i2 = (i2 << 3) + ((i3 >> (i - 2)) << 1) + (i3 & 1);
        }
        for (int i4 = 0; i4 < 4; i4++) {
            if (Integer.bitCount(g[i4] ^ (((i2 & 1) << 11) + (i2 >> 1))) <= 2) {
                return i4;
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private static int a(long j, boolean z) throws com.huawei.hms.scankit.p.a {
        int i;
        int i2;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int[] iArr = new int[i];
        for (int i3 = i - 1; i3 >= 0; i3--) {
            iArr[i3] = ((int) j) & 15;
            j >>= 4;
        }
        try {
            new p6(o3.k).a(iArr, i - i2);
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                i4 = (i4 << 4) + iArr[i5];
            }
            return i4;
        } catch (com.huawei.hms.scankit.p.a unused) {
            throw com.huawei.hms.scankit.p.a.a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private u6[] a(a aVar) throws com.huawei.hms.scankit.p.a {
        int i;
        int i2;
        int i3 = 1;
        this.e = 1;
        a aVar2 = aVar;
        a aVar3 = aVar2;
        a aVar4 = aVar3;
        a aVar5 = aVar4;
        boolean z = true;
        while (this.e < 9) {
            a a2 = a(aVar2, z, i3, -1);
            a a3 = a(aVar3, z, i3, i3);
            a a4 = a(aVar4, z, -1, i3);
            a a5 = a(aVar5, z, -1, -1);
            if (this.e > 2) {
                double a6 = (a(a5, a2) * this.e) / (a(aVar5, aVar2) * (this.e + 2));
                if (a6 < 0.75d || a6 > 1.25d || !a(a2, a3, a4, a5) || (!b(a2, a3, a4, a5) && ((i2 = this.e) == 5 || i2 == 7))) {
                    i = 1;
                    break;
                }
            }
            z = !z;
            this.e++;
            aVar5 = a5;
            aVar2 = a2;
            aVar3 = a3;
            aVar4 = a4;
            i3 = 1;
        }
        i = i3;
        int i4 = this.e;
        if (i4 != 5 && i4 != 7) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        this.b = i4 == 5 ? i : 0;
        u6[] u6VarArr = {new u6(aVar2.a() + 0.5f, aVar2.b() - 0.5f), new u6(aVar3.a() + 0.5f, aVar3.b() + 0.5f), new u6(aVar4.a() - 0.5f, aVar4.b() + 0.5f), new u6(aVar5.a() - 0.5f, aVar5.b() - 0.5f)};
        int i5 = this.e * 2;
        return a(u6VarArr, i5 - 3, i5);
    }

    private s a(s sVar, u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4) throws com.huawei.hms.scankit.p.a {
        s3 a2 = s3.a();
        int a3 = a();
        float f = a3 / 2.0f;
        float f2 = this.e;
        float f3 = f - f2;
        float f4 = f + f2;
        return a2.a(sVar, a3, a3, f3, f3, f4, f3, f4, f4, f3, f4, u6Var.b(), u6Var.c(), u6Var2.b(), u6Var2.c(), u6Var3.b(), u6Var3.c(), u6Var4.b(), u6Var4.c());
    }

    private int a(u6 u6Var, u6 u6Var2, int i) {
        float a2 = a(u6Var, u6Var2);
        float f = a2 / i;
        float b = u6Var.b();
        float c = u6Var.c();
        float b2 = ((u6Var2.b() - u6Var.b()) * f) / a2;
        float c2 = (f * (u6Var2.c() - u6Var.c())) / a2;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            float f2 = i3;
            if (this.f5751a.b(s4.a((f2 * b2) + b), s4.a((f2 * c2) + c))) {
                i2 |= 1 << ((i - i3) - 1);
            }
        }
        return i2;
    }

    private boolean a(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a((int) Math.ceil((((aVar.f5752a + aVar2.f5752a) + aVar3.f5752a) + aVar4.f5752a) / 4.0f), (int) Math.ceil((((aVar.b + aVar2.b) + aVar3.b) + aVar4.b) / 4.0f));
        float a2 = a(aVar5, aVar);
        float a3 = a(aVar5, aVar2);
        float a4 = a(aVar5, aVar3);
        float a5 = a(aVar5, aVar4);
        double d = a2 / a3;
        if (d <= 0.75d || d >= 1.25d) {
            return false;
        }
        double d2 = a2 / a4;
        if (d2 <= 0.75d || d2 >= 1.25d) {
            return false;
        }
        double d3 = a2 / a5;
        return d3 > 0.75d && d3 < 1.25d;
    }

    private a a(a aVar, boolean z, int i, int i2) {
        int a2 = aVar.a() + i;
        int b = aVar.b();
        while (true) {
            b += i2;
            if (!a(a2, b) || this.f5751a.b(a2, b) != z) {
                break;
            }
            a2 += i;
        }
        int i3 = a2 - i;
        int i4 = b - i2;
        while (a(i3, i4) && this.f5751a.b(i3, i4) == z) {
            i3 += i;
        }
        int i5 = i3 - i;
        while (a(i5, i4) && this.f5751a.b(i5, i4) == z) {
            i4 += i2;
        }
        return new a(i5, i4 - i2);
    }

    private static u6[] a(u6[] u6VarArr, int i, int i2) {
        float f = i2 / (i * 2.0f);
        float b = u6VarArr[0].b();
        float b2 = u6VarArr[2].b();
        float c = u6VarArr[0].c();
        float c2 = u6VarArr[2].c();
        float b3 = (u6VarArr[0].b() + u6VarArr[2].b()) / 2.0f;
        float c3 = (u6VarArr[0].c() + u6VarArr[2].c()) / 2.0f;
        float f2 = (b - b2) * f;
        float f3 = (c - c2) * f;
        u6 u6Var = new u6(b3 + f2, c3 + f3);
        u6 u6Var2 = new u6(b3 - f2, c3 - f3);
        float b4 = u6VarArr[1].b();
        float b5 = u6VarArr[3].b();
        float c4 = u6VarArr[1].c();
        float c5 = u6VarArr[3].c();
        float b6 = (u6VarArr[1].b() + u6VarArr[3].b()) / 2.0f;
        float c6 = (u6VarArr[1].c() + u6VarArr[3].c()) / 2.0f;
        float f4 = (b4 - b5) * f;
        float f5 = f * (c4 - c5);
        return new u6[]{u6Var, new u6(b6 + f4, c6 + f5), u6Var2, new u6(b6 - f4, c6 - f5)};
    }

    private boolean a(int i, int i2) {
        return i >= 0 && i < this.f5751a.e() && i2 > 0 && i2 < this.f5751a.c();
    }

    private boolean a(u6 u6Var) {
        return a(s4.a(u6Var.b()), s4.a(u6Var.c()));
    }

    private static float a(a aVar, a aVar2) {
        return s4.a(aVar.a(), aVar.b(), aVar2.a(), aVar2.b());
    }

    private static float a(u6 u6Var, u6 u6Var2) {
        return s4.a(u6Var.b(), u6Var.c(), u6Var2.b(), u6Var2.c());
    }

    private int a() {
        if (this.b) {
            return (this.c * 4) + 11;
        }
        int i = this.c;
        return i <= 4 ? (i * 4) + 15 : (i * 4) + ((((i - 4) / 8) + 1) * 2) + 15;
    }
}
