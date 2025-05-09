package com.huawei.hms.scankit.p;

import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes9.dex */
public final class z7 {
    private static final int[][] h;
    private static final z7[] i = a();

    /* renamed from: a, reason: collision with root package name */
    private final int f5936a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final c f;
    private final int g;

    static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final int f5937a;
        private final int b;

        int a() {
            return this.f5937a;
        }

        int b() {
            return this.b;
        }

        private b(int i, int i2) {
            this.f5937a = i;
            this.b = i2;
        }
    }

    static {
        int[] iArr = new int[8];
        // fill-array-data instruction
        iArr[0] = 15;
        iArr[1] = 52;
        iArr[2] = 52;
        iArr[3] = 24;
        iArr[4] = 24;
        iArr[5] = 42;
        iArr[6] = 2;
        iArr[7] = 102;
        h = new int[][]{new int[]{1, 10, 10, 8, 8, 5, 1, 3}, new int[]{2, 12, 12, 10, 10, 7, 1, 5}, new int[]{3, 14, 14, 12, 12, 10, 1, 8}, new int[]{4, 16, 16, 14, 14, 12, 1, 12}, new int[]{5, 18, 18, 16, 16, 14, 1, 18}, new int[]{6, 20, 20, 18, 18, 18, 1, 22}, new int[]{7, 22, 22, 20, 20, 20, 1, 30}, new int[]{8, 24, 24, 22, 22, 24, 1, 36}, new int[]{9, 26, 26, 24, 24, 28, 1, 44}, new int[]{10, 32, 32, 14, 14, 36, 1, 62}, new int[]{11, 36, 36, 16, 16, 42, 1, 86}, new int[]{12, 40, 40, 18, 18, 48, 1, 114}, new int[]{13, 44, 44, 20, 20, 56, 1, 144}, new int[]{14, 48, 48, 22, 22, 68, 1, 174}, iArr, new int[]{16, 64, 64, 14, 14, 56, 2, 140}, new int[]{17, 72, 72, 16, 16, 36, 4, 92}, new int[]{18, 80, 80, 18, 18, 48, 4, 114}, new int[]{19, 88, 88, 20, 20, 56, 4, 144}, new int[]{20, 96, 96, 22, 22, 68, 4, 174}, new int[]{21, 104, 104, 24, 24, 56, 6, 136}, new int[]{22, 120, 120, 18, 18, 68, 6, 175}, new int[]{23, UserInfomation.WEIGHT_DEFAULT_ENGLISH, UserInfomation.WEIGHT_DEFAULT_ENGLISH, 20, 20, 62, 8, MachineControlPointResponse.OP_CODE_EXTENSION_SET_STEP_COUNT}, new int[]{24, 144, 144, 22, 22, 62, 8, 156, 2, 155}, new int[]{25, 8, 18, 6, 16, 7, 1, 5}, new int[]{26, 8, 32, 6, 14, 11, 1, 10}, new int[]{27, 12, 26, 10, 24, 14, 1, 16}, new int[]{28, 12, 36, 10, 16, 18, 1, 22}, new int[]{29, 16, 36, 14, 16, 24, 1, 32}, new int[]{30, 16, 48, 14, 22, 28, 1, 49}};
    }

    private z7(int i2, int i3, int i4, int i5, int i6, c cVar) {
        this.f5936a = i2;
        this.b = i3;
        this.c = i4;
        this.d = i5;
        this.e = i6;
        this.f = cVar;
        int b2 = cVar.b();
        int i7 = 0;
        for (b bVar : cVar.a()) {
            i7 += bVar.a() * (bVar.b() + b2);
        }
        this.g = i7;
    }

    public static z7 a(int i2, int i3) throws com.huawei.hms.scankit.p.a {
        if ((i2 & 1) != 0 || (i3 & 1) != 0) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        for (z7 z7Var : i) {
            if (z7Var.b == i2 && z7Var.c == i3) {
                return z7Var;
            }
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    public int b() {
        return this.e;
    }

    public int c() {
        return this.d;
    }

    c d() {
        return this.f;
    }

    public int e() {
        return this.c;
    }

    public int f() {
        return this.b;
    }

    public int g() {
        return this.g;
    }

    public int h() {
        return this.f5936a;
    }

    public String toString() {
        return String.valueOf(this.f5936a);
    }

    static final class c {

        /* renamed from: a, reason: collision with root package name */
        private final int f5938a;
        private final b[] b;

        b[] a() {
            return this.b;
        }

        int b() {
            return this.f5938a;
        }

        private c(int i, b bVar) {
            this.f5938a = i;
            this.b = new b[]{bVar};
        }

        private c(int i, b bVar, b bVar2) {
            this.f5938a = i;
            this.b = new b[]{bVar, bVar2};
        }
    }

    private static z7[] a() {
        z7[] z7VarArr = new z7[h.length];
        int i2 = 0;
        while (true) {
            int[][] iArr = h;
            if (i2 >= iArr.length) {
                return z7VarArr;
            }
            int[] iArr2 = iArr[i2];
            if (i2 == 23) {
                z7VarArr[i2] = new z7(iArr2[0], iArr2[1], iArr2[2], iArr2[3], iArr2[4], new c(iArr2[5], new b(iArr2[6], iArr2[7]), new b(iArr2[8], iArr2[9])));
            } else {
                z7VarArr[i2] = new z7(iArr2[0], iArr2[1], iArr2[2], iArr2[3], iArr2[4], new c(iArr2[5], new b(iArr2[6], iArr2[7])));
            }
            i2++;
        }
    }
}
