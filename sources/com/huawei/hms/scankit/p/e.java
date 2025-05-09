package com.huawei.hms.scankit.p;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
final class e {

    /* renamed from: a, reason: collision with root package name */
    private final s f5766a;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final float g;
    private final v6 i;
    private final List<d> b = new ArrayList(5);
    private final int[] h = new int[3];

    e(s sVar, int i, int i2, int i3, int i4, float f, v6 v6Var) {
        this.f5766a = sVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = f;
        this.i = v6Var;
    }

    d a() throws a {
        d a2;
        int i = this.c;
        int i2 = this.f;
        int i3 = this.e + i;
        int i4 = this.d;
        int i5 = i2 / 2;
        int[] iArr = new int[3];
        for (int i6 = 0; i6 < i2; i6++) {
            int i7 = ((i6 & 1) == 0 ? (i6 + 1) / 2 : -((i6 + 1) / 2)) + i4 + i5;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            int i8 = i;
            while (i8 < i3 && !this.f5766a.b(i8, i7)) {
                i8++;
            }
            d a3 = a(i7, i8, i3, iArr);
            if (a3 != null) {
                return a3;
            }
            if (a(iArr) && (a2 = a(iArr, i7, i3)) != null) {
                return a2;
            }
        }
        if (this.b.isEmpty()) {
            throw a.a();
        }
        return this.b.get(0);
    }

    private d a(int i, int i2, int i3, int[] iArr) {
        d a2;
        int i4 = 0;
        while (i2 < i3) {
            if (!this.f5766a.b(i2, i)) {
                if (i4 == 1) {
                    i4++;
                }
                iArr[i4] = iArr[i4] + 1;
            } else if (i4 == 1) {
                iArr[1] = iArr[1] + 1;
            } else if (i4 == 2) {
                if (a(iArr) && (a2 = a(iArr, i, i2)) != null) {
                    return a2;
                }
                iArr[0] = iArr[2];
                iArr[1] = 1;
                iArr[2] = 0;
                i4 = 1;
            } else {
                i4++;
                iArr[i4] = iArr[i4] + 1;
            }
            i2++;
        }
        return null;
    }

    private static float a(int[] iArr, int i) {
        return (i - iArr[2]) - (iArr[1] / 2.0f);
    }

    private boolean a(int[] iArr) {
        float f = this.g;
        float f2 = (3.0f * f) / 4.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - iArr[i]) >= f2) {
                return false;
            }
        }
        return true;
    }

    private float a(int i, int i2, int i3, int i4) {
        int i5;
        s sVar = this.f5766a;
        int c = sVar.c();
        int[] iArr = this.h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i6 = i;
        while (i6 >= 0 && sVar.b(i2, i6)) {
            int i7 = iArr[1];
            if (i7 > i3) {
                break;
            }
            iArr[1] = i7 + 1;
            i6--;
        }
        if (i6 < 0 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i6 >= 0 && !sVar.b(i2, i6)) {
            int i8 = iArr[0];
            if (i8 > i3) {
                break;
            }
            iArr[0] = i8 + 1;
            i6--;
        }
        if (iArr[0] > i3) {
            return Float.NaN;
        }
        int i9 = i + 1;
        while (i9 < c && sVar.b(i2, i9)) {
            int i10 = iArr[1];
            if (i10 > i3) {
                break;
            }
            iArr[1] = i10 + 1;
            i9++;
        }
        if (i9 == c || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i9 < c && !sVar.b(i2, i9)) {
            int i11 = iArr[2];
            if (i11 > i3) {
                break;
            }
            iArr[2] = i11 + 1;
            i9++;
        }
        int i12 = iArr[2];
        if (i12 <= i3 && (i5 = iArr[0] + iArr[1] + i12) < i4 * 3 && i5 * 3 > i4 && a(iArr)) {
            return a(iArr, i9);
        }
        return Float.NaN;
    }

    private d a(int[] iArr, int i, int i2) {
        int i3 = iArr[0];
        int i4 = iArr[1];
        int i5 = iArr[2];
        float a2 = a(iArr, i2);
        float a3 = a(i, (int) a2, iArr[1] * 3, i3 + i4 + i5);
        if (Float.isNaN(a3)) {
            return null;
        }
        float f = ((iArr[0] + iArr[1]) + iArr[2]) / 3.0f;
        for (d dVar : this.b) {
            if (dVar.b(f, a3, a2)) {
                return dVar.c(a3, a2, f);
            }
        }
        d dVar2 = new d(a2, a3, f);
        this.b.add(dVar2);
        v6 v6Var = this.i;
        if (v6Var == null) {
            return null;
        }
        v6Var.a(dVar2);
        return null;
    }
}
