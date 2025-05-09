package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
public final class o3 {
    public static final o3 h = new o3(4201, 4096, 1);
    public static final o3 i = new o3(1033, 1024, 1);
    public static final o3 j;
    public static final o3 k;
    public static final o3 l;
    public static final o3 m;
    public static final o3 n;
    public static final o3 o;

    /* renamed from: a, reason: collision with root package name */
    private final int[] f5845a;
    private final int[] b;
    private final p3 c;
    private final p3 d;
    private final int e;
    private final int f;
    private final int g;

    static {
        o3 o3Var = new o3(67, 64, 1);
        j = o3Var;
        k = new o3(19, 16, 1);
        l = new o3(285, 256, 0);
        o3 o3Var2 = new o3(301, 256, 1);
        m = o3Var2;
        n = o3Var2;
        o = o3Var;
    }

    public o3(int i2, int i3, int i4) {
        this.f = i2;
        this.e = i3;
        this.g = i4;
        this.f5845a = new int[i3];
        this.b = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.f5845a[i6] = i5;
            i5 *= 2;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.b[this.f5845a[i7]] = i7;
        }
        this.c = new p3(this, new int[]{0});
        this.d = new p3(this, new int[]{1});
    }

    static int a(int i2, int i3) {
        return i2 ^ i3;
    }

    int a(int i2) {
        if (w7.a(this.f5845a, i2)) {
            return this.f5845a[i2];
        }
        return -1;
    }

    p3 b() {
        return this.d;
    }

    int c(int i2) {
        if (i2 != 0) {
            return this.b[i2];
        }
        throw new IllegalArgumentException();
    }

    p3 d() {
        return this.c;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.f) + ',' + this.e + com.huawei.hms.network.embedded.g4.l;
    }

    p3 b(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        }
        if (i3 == 0) {
            return this.c;
        }
        int[] iArr = new int[i2 + 1];
        iArr[0] = i3;
        return new p3(this, iArr);
    }

    public int a() {
        return this.g;
    }

    int c(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        int[] iArr = this.f5845a;
        int[] iArr2 = this.b;
        return iArr[(iArr2[i2] + iArr2[i3]) % (this.e - 1)];
    }

    public int c() {
        return this.e;
    }

    int b(int i2) {
        if (i2 != 0) {
            return this.f5845a[(this.e - this.b[i2]) - 1];
        }
        throw new ArithmeticException();
    }
}
