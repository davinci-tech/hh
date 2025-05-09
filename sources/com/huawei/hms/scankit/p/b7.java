package com.huawei.hms.scankit.p;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Locale;

/* loaded from: classes4.dex */
final class b7 {
    static final b7 e = new b7(i7.b, 0, 0, 0);

    /* renamed from: a, reason: collision with root package name */
    private final int f5745a;
    private final i7 b;
    private final int c;
    private final int d;

    private b7(i7 i7Var, int i, int i2, int i3) {
        this.b = i7Var;
        this.f5745a = i;
        this.c = i2;
        this.d = i3;
    }

    int a() {
        return this.c;
    }

    int b() {
        return this.d;
    }

    int c() {
        return this.f5745a;
    }

    public String toString() {
        String[] strArr = c4.b;
        if (w7.a(strArr, this.f5745a)) {
            return String.format(Locale.ENGLISH, "%s bits=%d bytes=%d", strArr[this.f5745a], Integer.valueOf(this.d), Integer.valueOf(this.c));
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    b7 a(int i, int i2) {
        int i3 = this.d;
        i7 i7Var = this.b;
        int i4 = this.f5745a;
        if (i != i4) {
            int i5 = c4.c[i4][i];
            int i6 = i5 >> 16;
            i7Var = i7Var.a(i5 & 65535, i6);
            i3 += i6;
        }
        int i7 = i == 2 ? 4 : 5;
        return new b7(i7Var.a(i2, i7), i, 0, i3 + i7);
    }

    b7 b(int i, int i2) {
        i7 i7Var = this.b;
        int i3 = this.f5745a;
        int i4 = i3 == 2 ? 4 : 5;
        if (i3 >= 0) {
            int[][] iArr = c4.e;
            if (i3 < iArr.length && i > 0) {
                int[] iArr2 = iArr[i3];
                if (i < iArr2.length) {
                    i7Var = i7Var.a(iArr2[i], i4);
                }
            }
        }
        return new b7(i7Var.a(i2, 5), this.f5745a, 0, this.d + i4 + 5);
    }

    b7 a(int i) {
        i7 i7Var = this.b;
        int i2 = this.f5745a;
        int i3 = this.d;
        if (i2 == 4 || i2 == 2) {
            int i4 = c4.c[i2][0];
            int i5 = i4 >> 16;
            i7Var = i7Var.a(i4 & 65535, i5);
            i3 += i5;
            i2 = 0;
        }
        int i6 = this.c;
        b7 b7Var = new b7(i7Var, i2, i6 + 1, i3 + ((i6 == 0 || i6 == 31) ? 18 : i6 == 62 ? 9 : 8));
        return b7Var.c == 2078 ? b7Var.b(i + 1) : b7Var;
    }

    b7 b(int i) {
        int i2 = this.c;
        return i2 == 0 ? this : new b7(this.b.b(i - i2, i2), this.f5745a, 0, this.d);
    }

    boolean a(b7 b7Var) {
        int i;
        int i2 = this.d + (c4.c[this.f5745a][b7Var.f5745a] >> 16);
        int i3 = b7Var.c;
        if (i3 > 0 && ((i = this.c) == 0 || i > i3)) {
            i2 += 10;
        }
        return i2 <= b7Var.d;
    }

    r a(byte[] bArr) {
        LinkedList linkedList = new LinkedList();
        for (i7 i7Var = b(bArr.length).b; i7Var != null; i7Var = i7Var.a()) {
            linkedList.addFirst(i7Var);
        }
        r rVar = new r();
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            ((i7) it.next()).a(rVar, bArr);
        }
        return rVar;
    }
}
