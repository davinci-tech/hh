package com.huawei.hms.network.embedded;

import java.util.Arrays;

/* loaded from: classes9.dex */
public final class ca {
    public static final int c = 65535;
    public static final int d = 1;
    public static final int e = 2;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 10;

    /* renamed from: a, reason: collision with root package name */
    public int f5206a;
    public final int[] b = new int[10];

    public boolean e(int i2) {
        return ((1 << i2) & this.f5206a) != 0;
    }

    public int d(int i2) {
        return (this.f5206a & 64) != 0 ? this.b[6] : i2;
    }

    public int d() {
        return Integer.bitCount(this.f5206a);
    }

    public int c(int i2) {
        return (this.f5206a & 32) != 0 ? this.b[5] : i2;
    }

    public int c() {
        if ((this.f5206a & 128) != 0) {
            return this.b[7];
        }
        return 65535;
    }

    public int b(int i2) {
        return (this.f5206a & 16) != 0 ? this.b[4] : i2;
    }

    public int b() {
        if ((this.f5206a & 2) != 0) {
            return this.b[1];
        }
        return -1;
    }

    public boolean a(boolean z) {
        if ((this.f5206a & 4) != 0) {
            if (this.b[2] == 1) {
                return true;
            }
        } else if (z) {
            return true;
        }
        return false;
    }

    public void a(ca caVar) {
        for (int i2 = 0; i2 < 10; i2++) {
            if (caVar.e(i2)) {
                a(i2, caVar.a(i2));
            }
        }
    }

    public void a() {
        this.f5206a = 0;
        Arrays.fill(this.b, 0);
    }

    public ca a(int i2, int i3) {
        if (i2 >= 0) {
            int[] iArr = this.b;
            if (i2 < iArr.length) {
                this.f5206a = (1 << i2) | this.f5206a;
                iArr[i2] = i3;
            }
        }
        return this;
    }

    public int a(int i2) {
        return this.b[i2];
    }
}
