package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
final class p3 {

    /* renamed from: a, reason: collision with root package name */
    private final o3 f5853a;
    private final int[] b;

    p3(o3 o3Var, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f5853a = o3Var;
        int length = iArr.length;
        int i = 1;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = new int[]{0};
            return;
        }
        int i2 = length - i;
        int[] iArr2 = new int[i2];
        this.b = iArr2;
        System.arraycopy(iArr, i, iArr2, 0, i2);
    }

    int[] a() {
        return this.b;
    }

    int b() {
        return this.b.length - 1;
    }

    boolean c() {
        return this.b[0] == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(b() * 8);
        for (int b = b(); b >= 0; b--) {
            int b2 = b(b);
            if (b2 != 0) {
                if (b2 < 0) {
                    sb.append(" - ");
                    b2 = -b2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b == 0 || b2 != 1) {
                    int c = this.f5853a.c(b2);
                    if (c == 0) {
                        sb.append('1');
                    } else if (c == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(c);
                    }
                }
                if (b != 0) {
                    if (b == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b);
                    }
                }
            }
        }
        return sb.toString();
    }

    int a(int i) {
        if (i == 0) {
            return b(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int i3 : this.b) {
                i2 = o3.a(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.b;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            i4 = o3.a(this.f5853a.c(i, i4), this.b[i5]);
        }
        return i4;
    }

    int b(int i) {
        return this.b[(r0.length - 1) - i];
    }

    p3 c(p3 p3Var) {
        if (!this.f5853a.equals(p3Var.f5853a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        }
        if (c() || p3Var.c()) {
            return this.f5853a.d();
        }
        int[] iArr = this.b;
        int length = iArr.length;
        int[] iArr2 = p3Var.b;
        int length2 = iArr2.length;
        int[] iArr3 = new int[(length + length2) - 1];
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            for (int i3 = 0; i3 < length2; i3++) {
                int i4 = i + i3;
                iArr3[i4] = o3.a(iArr3[i4], this.f5853a.c(i2, iArr2[i3]));
            }
        }
        return new p3(this.f5853a, iArr3);
    }

    p3[] b(p3 p3Var) {
        if (this.f5853a.equals(p3Var.f5853a)) {
            if (!p3Var.c()) {
                p3 d = this.f5853a.d();
                int b = this.f5853a.b(p3Var.b(p3Var.b()));
                p3 p3Var2 = this;
                while (p3Var2.b() >= p3Var.b() && !p3Var2.c()) {
                    int b2 = p3Var2.b() - p3Var.b();
                    int c = this.f5853a.c(p3Var2.b(p3Var2.b()), b);
                    p3 a2 = p3Var.a(b2, c);
                    d = d.a(this.f5853a.b(b2, c));
                    p3Var2 = p3Var2.a(a2);
                }
                return new p3[]{d, p3Var2};
            }
            throw new IllegalArgumentException("Divide by 0");
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    p3 a(p3 p3Var) {
        if (this.f5853a.equals(p3Var.f5853a)) {
            if (c()) {
                return p3Var;
            }
            if (p3Var.c()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = p3Var.b;
            if (iArr.length <= iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr3, 0, length);
            for (int i = length; i < iArr.length; i++) {
                iArr3[i] = o3.a(iArr2[i - length], iArr[i]);
            }
            return new p3(this.f5853a, iArr3);
        }
        throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
    }

    p3 c(int i) {
        if (i == 0) {
            return this.f5853a.d();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f5853a.c(this.b[i2], i);
        }
        return new p3(this.f5853a, iArr);
    }

    p3 a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f5853a.d();
        }
        int length = this.b.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f5853a.c(this.b[i3], i2);
        }
        return new p3(this.f5853a, iArr);
    }
}
