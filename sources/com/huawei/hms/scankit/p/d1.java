package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.p.b8;

/* loaded from: classes9.dex */
final class d1 {

    /* renamed from: a, reason: collision with root package name */
    private final int f5759a;
    private final byte[] b;

    private d1(int i, byte[] bArr) {
        this.f5759a = i;
        this.b = bArr;
    }

    static d1[] a(byte[] bArr, b8 b8Var, b3 b3Var) {
        if (bArr.length != b8Var.e()) {
            throw new IllegalArgumentException();
        }
        b8.b a2 = b8Var.a(b3Var);
        b8.a[] a3 = a2.a();
        int i = 0;
        for (b8.a aVar : a3) {
            i += aVar.a();
        }
        d1[] d1VarArr = new d1[i];
        int i2 = 0;
        for (b8.a aVar2 : a3) {
            int i3 = 0;
            while (i3 < aVar2.a()) {
                int b = aVar2.b();
                d1VarArr[i2] = new d1(b, new byte[a2.b() + b]);
                i3++;
                i2++;
            }
        }
        int length = d1VarArr[0].b.length;
        do {
            i--;
            if (i < 0) {
                break;
            }
        } while (d1VarArr[i].b.length != length);
        return a(d1VarArr, bArr, length, a2, i2, i + 1);
    }

    int b() {
        return this.f5759a;
    }

    private static d1[] a(d1[] d1VarArr, byte[] bArr, int i, b8.b bVar, int i2, int i3) {
        int b = i - bVar.b();
        int i4 = 0;
        for (int i5 = 0; i5 < b; i5++) {
            int i6 = 0;
            while (i6 < i2) {
                d1VarArr[i6].b[i5] = bArr[i4];
                i6++;
                i4++;
            }
        }
        int i7 = i3;
        while (i7 < i2) {
            d1VarArr[i7].b[b] = bArr[i4];
            i7++;
            i4++;
        }
        int length = d1VarArr[0].b.length;
        while (b < length) {
            int i8 = 0;
            while (i8 < i2) {
                int i9 = i8 < i3 ? b : b + 1;
                if (i8 >= 0 && i8 < d1VarArr.length && w7.a(d1VarArr[i8].b, i9) && w7.a(bArr, i4)) {
                    d1VarArr[i8].b[i9] = bArr[i4];
                    i8++;
                    i4++;
                } else {
                    throw new ArrayIndexOutOfBoundsException();
                }
            }
            b++;
        }
        return d1VarArr;
    }

    byte[] a() {
        return this.b;
    }
}
