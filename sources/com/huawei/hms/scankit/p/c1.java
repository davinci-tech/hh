package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.p.a8;

/* loaded from: classes9.dex */
final class c1 {

    /* renamed from: a, reason: collision with root package name */
    private final int f5750a;
    private final byte[] b;

    private c1(int i, byte[] bArr) {
        this.f5750a = i;
        this.b = bArr;
    }

    static c1[] a(byte[] bArr, a8 a8Var, c3 c3Var) {
        if (bArr.length != a8Var.l()) {
            throw new IllegalArgumentException();
        }
        a8.b a2 = a8Var.a(c3Var);
        a8.a[] a3 = a2.a();
        int i = 0;
        for (a8.a aVar : a3) {
            i += aVar.a();
        }
        c1[] c1VarArr = new c1[i];
        int i2 = 0;
        for (a8.a aVar2 : a3) {
            int i3 = 0;
            while (i3 < aVar2.a()) {
                int b = aVar2.b();
                c1VarArr[i2] = new c1(b, new byte[a2.b() + b]);
                i3++;
                i2++;
            }
        }
        int length = c1VarArr[0].b.length;
        do {
            i--;
            if (i < 0) {
                break;
            }
        } while (c1VarArr[i].b.length != length);
        int i4 = i + 1;
        int b2 = length - a2.b();
        int i5 = 0;
        for (int i6 = 0; i6 < b2; i6++) {
            int i7 = 0;
            while (i7 < i2) {
                c1VarArr[i7].b[i6] = bArr[i5];
                i7++;
                i5++;
            }
        }
        int i8 = i4;
        while (i8 < i2) {
            c1VarArr[i8].b[b2] = bArr[i5];
            i8++;
            i5++;
        }
        int length2 = c1VarArr[0].b.length;
        while (b2 < length2) {
            int i9 = 0;
            while (i9 < i2) {
                c1VarArr[i9].b[i9 < i4 ? b2 : b2 + 1] = bArr[i5];
                i9++;
                i5++;
            }
            b2++;
        }
        return c1VarArr;
    }

    int b() {
        return this.f5750a;
    }

    byte[] a() {
        return this.b;
    }
}
