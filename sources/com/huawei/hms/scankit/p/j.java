package com.huawei.hms.scankit.p;

import java.lang.reflect.Array;

/* loaded from: classes4.dex */
public final class j {

    /* renamed from: a, reason: collision with root package name */
    private final l[] f5801a;
    private int b;
    private final int c;
    private final int d;

    j(int i, int i2) {
        this.f5801a = new l[i];
        for (int i3 = 0; i3 < i; i3++) {
            this.f5801a[i3] = new l(((i2 + 4) * 17) + 1);
        }
        this.d = i2 * 17;
        this.c = i;
        this.b = -1;
    }

    l a() {
        int i = this.b;
        if (i >= 0) {
            l[] lVarArr = this.f5801a;
            if (i < lVarArr.length) {
                return lVarArr[i];
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    void b() {
        this.b++;
    }

    public byte[][] a(int i, int i2) {
        int i3 = this.c * i2;
        byte[][] bArr = (byte[][]) Array.newInstance((Class<?>) Byte.TYPE, i3, this.d * i);
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[(i3 - i4) - 1] = this.f5801a[i4 / i2].a(i);
        }
        return bArr;
    }
}
