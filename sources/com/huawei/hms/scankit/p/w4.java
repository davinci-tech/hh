package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
public final class w4 {
    public static final w4 f = new w4(929, 3);

    /* renamed from: a, reason: collision with root package name */
    private final int[] f5913a;
    private final int[] b;
    private final x4 c;
    private final x4 d;
    private final int e;

    private w4(int i, int i2) {
        this.e = i;
        this.f5913a = new int[i];
        this.b = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            this.f5913a[i4] = i3;
            i3 = (i3 * i2) % i;
        }
        for (int i5 = 0; i5 < i - 1; i5++) {
            this.b[this.f5913a[i5]] = i5;
        }
        this.c = new x4(this, new int[]{0});
        this.d = new x4(this, new int[]{1});
    }

    x4 a() {
        return this.d;
    }

    x4 b(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.c;
        }
        int[] iArr = new int[i + 1];
        iArr[0] = i2;
        return new x4(this, iArr);
    }

    x4 c() {
        return this.c;
    }

    int d(int i, int i2) {
        int i3 = this.e;
        return ((i + i3) - i2) % i3;
    }

    int a(int i, int i2) {
        return (i + i2) % this.e;
    }

    int c(int i) {
        if (i != 0) {
            return this.b[i];
        }
        throw new IllegalArgumentException();
    }

    int a(int i) {
        return this.f5913a[i];
    }

    int c(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return 0;
        }
        int[] iArr = this.f5913a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i] + iArr2[i2]) % (this.e - 1)];
    }

    int b(int i) {
        if (i != 0) {
            return this.f5913a[(this.e - this.b[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int b() {
        return this.e;
    }
}
