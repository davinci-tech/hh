package com.huawei.hms.scankit.p;

/* loaded from: classes9.dex */
final class x4 {

    /* renamed from: a, reason: collision with root package name */
    private final w4 f5919a;
    private final int[] b;

    x4(w4 w4Var, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.f5919a = w4Var;
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

    int a() {
        return this.b.length - 1;
    }

    boolean b() {
        return this.b[0] == 0;
    }

    x4 c(x4 x4Var) {
        if (this.f5919a.equals(x4Var.f5919a)) {
            return x4Var.b() ? this : a(x4Var.c());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(a() * 8);
        for (int a2 = a(); a2 >= 0; a2--) {
            int b = b(a2);
            if (b != 0) {
                if (b < 0) {
                    sb.append(" - ");
                    b = -b;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (a2 == 0 || b != 1) {
                    sb.append(b);
                }
                if (a2 != 0) {
                    if (a2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(a2);
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
                i2 = this.f5919a.a(i2, i3);
            }
            return i2;
        }
        int[] iArr = this.b;
        int i4 = iArr[0];
        int length = iArr.length;
        for (int i5 = 1; i5 < length; i5++) {
            w4 w4Var = this.f5919a;
            i4 = w4Var.a(w4Var.c(i, i4), this.b[i5]);
        }
        return i4;
    }

    int b(int i) {
        return this.b[(r0.length - 1) - i];
    }

    x4 b(x4 x4Var) {
        if (this.f5919a.equals(x4Var.f5919a)) {
            if (!b() && !x4Var.b()) {
                int[] iArr = this.b;
                int length = iArr.length;
                int[] iArr2 = x4Var.b;
                int length2 = iArr2.length;
                int[] iArr3 = new int[(length + length2) - 1];
                for (int i = 0; i < length; i++) {
                    int i2 = iArr[i];
                    for (int i3 = 0; i3 < length2; i3++) {
                        int i4 = i + i3;
                        w4 w4Var = this.f5919a;
                        iArr3[i4] = w4Var.a(iArr3[i4], w4Var.c(i2, iArr2[i3]));
                    }
                }
                return new x4(this.f5919a, iArr3);
            }
            return this.f5919a.c();
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    x4 c() {
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.f5919a.d(0, this.b[i]);
        }
        return new x4(this.f5919a, iArr);
    }

    x4 c(int i) {
        if (i == 0) {
            return this.f5919a.c();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.f5919a.c(this.b[i2], i);
        }
        return new x4(this.f5919a, iArr);
    }

    x4 a(x4 x4Var) {
        if (this.f5919a.equals(x4Var.f5919a)) {
            if (b()) {
                return x4Var;
            }
            if (x4Var.b()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = x4Var.b;
            if (iArr.length <= iArr2.length) {
                iArr = iArr2;
                iArr2 = iArr;
            }
            int[] iArr3 = new int[iArr.length];
            int length = iArr.length - iArr2.length;
            System.arraycopy(iArr, 0, iArr3, 0, length);
            for (int i = length; i < iArr.length; i++) {
                iArr3[i] = this.f5919a.a(iArr2[i - length], iArr[i]);
            }
            return new x4(this.f5919a, iArr3);
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    x4 a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        }
        if (i2 == 0) {
            return this.f5919a.c();
        }
        int length = this.b.length;
        int[] iArr = new int[i + length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = this.f5919a.c(this.b[i3], i2);
        }
        return new x4(this.f5919a, iArr);
    }
}
