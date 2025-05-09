package com.huawei.hms.scankit.p;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.hmsscankit.WriterException;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.up.model.UserInfomation;

/* loaded from: classes4.dex */
final class t4 {

    /* renamed from: a, reason: collision with root package name */
    private static final int[][] f5884a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, OldToNewMotionPath.SPORT_TYPE_TENNIS, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, OldToNewMotionPath.SPORT_TYPE_PILATES, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, UserInfomation.WEIGHT_DEFAULT_ENGLISH, 158}, new int[]{6, 32, 58, 84, 110, 136, MachineControlPointResponse.OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY}, new int[]{6, 26, 54, 82, 110, OldToNewMotionPath.SPORT_TYPE_PILATES, MachineControlPointResponse.OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    static void a(c0 c0Var) {
        c0Var.a((byte) -1);
    }

    private static void b(c0 c0Var) throws WriterException {
        if (c0Var.a(8, c0Var.b() - 8) == 0) {
            throw new WriterException();
        }
        c0Var.a(8, c0Var.b() - 8, 1);
    }

    private static boolean b(int i) {
        return i == -1;
    }

    static void c(b8 b8Var, c0 c0Var) throws WriterException {
        if (b8Var.f() < 7) {
            return;
        }
        r rVar = new r();
        a(b8Var, rVar);
        int i = 17;
        for (int i2 = 0; i2 < 6; i2++) {
            for (int i3 = 0; i3 < 3; i3++) {
                boolean b2 = rVar.b(i);
                i--;
                c0Var.a(i2, (c0Var.b() - 11) + i3, b2);
                c0Var.a((c0Var.b() - 11) + i3, i2, b2);
            }
        }
    }

    private static void d(c0 c0Var) {
        int i = 8;
        while (i < c0Var.c() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (b(c0Var.a(i, 6))) {
                c0Var.a(i, 6, i3);
            }
            if (b(c0Var.a(6, i))) {
                c0Var.a(6, i, i3);
            }
            i = i2;
        }
    }

    static void a(r rVar, b3 b3Var, b8 b8Var, int i, c0 c0Var) throws WriterException {
        a(c0Var);
        a(b8Var, c0Var);
        a(b3Var, i, c0Var);
        c(b8Var, c0Var);
        a(rVar, i, c0Var);
    }

    private static void b(int i, int i2, c0 c0Var) {
        for (int i3 = 0; i3 < 5; i3++) {
            int[] iArr = b[i3];
            for (int i4 = 0; i4 < 5; i4++) {
                c0Var.a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    private static void b(b8 b8Var, c0 c0Var) {
        if (b8Var.f() < 2) {
            return;
        }
        int f = b8Var.f() - 1;
        int[][] iArr = c;
        if (f < iArr.length) {
            int[] iArr2 = iArr[f];
            for (int i : iArr2) {
                if (i >= 0) {
                    for (int i2 : iArr2) {
                        if (i2 >= 0 && b(c0Var.a(i2, i))) {
                            b(i2 - 2, i - 2, c0Var);
                        }
                    }
                }
            }
        }
    }

    static void a(b8 b8Var, c0 c0Var) throws WriterException {
        c(c0Var);
        b(c0Var);
        b(b8Var, c0Var);
        d(c0Var);
    }

    private static void d(int i, int i2, c0 c0Var) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (b(c0Var.a(i, i4))) {
                c0Var.a(i, i4, 0);
            } else {
                throw new WriterException();
            }
        }
    }

    private static void c(int i, int i2, c0 c0Var) {
        for (int i3 = 0; i3 < 7; i3++) {
            int[] iArr = f5884a[i3];
            for (int i4 = 0; i4 < 7; i4++) {
                c0Var.a(i + i4, i2 + i3, iArr[i4]);
            }
        }
    }

    static void a(b3 b3Var, int i, c0 c0Var) throws WriterException {
        r rVar = new r();
        a(b3Var, i, rVar);
        for (int i2 = 0; i2 < rVar.e(); i2++) {
            boolean b2 = rVar.b((rVar.e() - 1) - i2);
            int[] iArr = d[i2];
            c0Var.a(iArr[0], iArr[1], b2);
            if (i2 < 8) {
                c0Var.a((c0Var.c() - i2) - 1, 8, b2);
            } else {
                c0Var.a(8, (c0Var.b() - 7) + (i2 - 8), b2);
            }
        }
    }

    private static void c(c0 c0Var) throws WriterException {
        int length = f5884a[0].length;
        c(0, 0, c0Var);
        c(c0Var.c() - length, 0, c0Var);
        c(0, c0Var.c() - length, c0Var);
        a(0, 7, c0Var);
        a(c0Var.c() - 8, 7, c0Var);
        a(0, c0Var.c() - 8, c0Var);
        d(7, 0, c0Var);
        d(c0Var.b() - 8, 0, c0Var);
        d(7, c0Var.b() - 7, c0Var);
    }

    static void a(r rVar, int i, c0 c0Var) throws WriterException {
        boolean z;
        int c2 = c0Var.c() - 1;
        int b2 = c0Var.b() - 1;
        int i2 = 0;
        int i3 = -1;
        while (c2 > 0) {
            if (c2 == 6) {
                c2--;
            }
            while (b2 >= 0 && b2 < c0Var.b()) {
                for (int i4 = 0; i4 < 2; i4++) {
                    int i5 = c2 - i4;
                    if (b(c0Var.a(i5, b2))) {
                        if (i2 < rVar.e()) {
                            z = rVar.b(i2);
                            i2++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && r4.a(i, i5, b2)) {
                            z = !z;
                        }
                        c0Var.a(i5, b2, z);
                    }
                }
                b2 += i3;
            }
            i3 = -i3;
            b2 += i3;
            c2 -= 2;
        }
        if (i2 == rVar.e()) {
            return;
        }
        throw new WriterException("Not all bits consumed: " + i2 + '/' + rVar.e());
    }

    static int a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    static int a(int i, int i2) {
        if (i2 != 0) {
            int a2 = a(i2);
            int i3 = i << (a2 - 1);
            while (a(i3) >= a2) {
                i3 ^= i2 << (a(i3) - a2);
            }
            return i3;
        }
        throw new IllegalArgumentException("0 polynomial");
    }

    static void a(b3 b3Var, int i, r rVar) throws WriterException {
        if (h6.a(i)) {
            int a2 = (b3Var.a() << 3) | i;
            rVar.a(a2, 5);
            rVar.a(a(a2, 1335), 10);
            r rVar2 = new r();
            rVar2.a(21522, 15);
            rVar.b(rVar2);
            if (rVar.e() == 15) {
                return;
            }
            throw new WriterException("should not happen but we got: " + rVar.e());
        }
        throw new WriterException("Invalid mask pattern");
    }

    static void a(b8 b8Var, r rVar) throws WriterException {
        rVar.a(b8Var.f(), 6);
        rVar.a(a(b8Var.f(), 7973), 12);
        if (rVar.e() == 18) {
            return;
        }
        throw new WriterException("should not happen but we got: " + rVar.e());
    }

    private static void a(int i, int i2, c0 c0Var) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (b(c0Var.a(i4, i2))) {
                c0Var.a(i4, i2, 0);
            } else {
                throw new WriterException();
            }
        }
    }
}
