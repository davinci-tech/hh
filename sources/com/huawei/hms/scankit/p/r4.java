package com.huawei.hms.scankit.p;

/* loaded from: classes4.dex */
final class r4 {
    static int a(c0 c0Var) {
        return a(c0Var, true) + a(c0Var, false);
    }

    static int b(c0 c0Var) {
        byte[][] a2 = c0Var.a();
        int c = c0Var.c();
        int b = c0Var.b();
        int i = 0;
        for (int i2 = 0; i2 < b - 1; i2++) {
            byte[] bArr = a2[i2];
            int i3 = 0;
            while (i3 < c - 1) {
                byte b2 = bArr[i3];
                int i4 = i3 + 1;
                if (b2 == bArr[i4]) {
                    byte[] bArr2 = a2[i2 + 1];
                    if (b2 == bArr2[i3] && b2 == bArr2[i4]) {
                        i++;
                    }
                }
                i3 = i4;
            }
        }
        return i * 3;
    }

    static int c(c0 c0Var) {
        byte[][] a2 = c0Var.a();
        int c = c0Var.c();
        int b = c0Var.b();
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            for (int i3 = 0; i3 < c; i3++) {
                byte[] bArr = a2[i2];
                int i4 = i3 + 6;
                if (i4 < c && bArr[i3] == 1 && bArr[i3 + 1] == 0 && bArr[i3 + 2] == 1 && bArr[i3 + 3] == 1 && bArr[i3 + 4] == 1 && bArr[i3 + 5] == 0 && bArr[i4] == 1 && (a(bArr, i3 - 4, i3) || a(bArr, i3 + 7, i3 + 11))) {
                    i++;
                }
                int i5 = i2 + 6;
                if (i5 < b && a2[i2][i3] == 1 && a2[i2 + 1][i3] == 0 && a2[i2 + 2][i3] == 1 && a2[i2 + 3][i3] == 1 && a2[i2 + 4][i3] == 1 && a2[i2 + 5][i3] == 0 && a2[i5][i3] == 1 && (a(a2, i3, i2 - 4, i2) || a(a2, i3, i2 + 7, i2 + 11))) {
                    i++;
                }
            }
        }
        return i * 40;
    }

    static int d(c0 c0Var) {
        byte[][] a2 = c0Var.a();
        int c = c0Var.c();
        int b = c0Var.b();
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            byte[] bArr = a2[i2];
            for (int i3 = 0; i3 < c; i3++) {
                if (bArr[i3] == 1) {
                    i++;
                }
            }
        }
        int b2 = c0Var.b() * c0Var.c();
        return ((Math.abs((i * 2) - b2) * 10) / b2) * 10;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (max < bArr.length && i < bArr[0].length && bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0031 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static boolean a(int r1, int r2, int r3) {
        /*
            r0 = 1
            switch(r1) {
                case 0: goto L2b;
                case 1: goto L2c;
                case 2: goto L28;
                case 3: goto L24;
                case 4: goto L1f;
                case 5: goto L18;
                case 6: goto L10;
                case 7: goto L7;
                default: goto L4;
            }
        L4:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            goto L33
        L7:
            int r1 = r3 * r2
            int r1 = r1 % 3
            int r3 = r3 + r2
            r2 = r3 & 1
            int r1 = r1 + r2
            goto L16
        L10:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
        L16:
            r1 = r1 & r0
            goto L2e
        L18:
            int r3 = r3 * r2
            r1 = r3 & 1
            int r3 = r3 % 3
            int r1 = r1 + r3
            goto L2e
        L1f:
            int r3 = r3 / 2
            int r2 = r2 / 3
            goto L2b
        L24:
            int r3 = r3 + r2
            int r1 = r3 % 3
            goto L2e
        L28:
            int r1 = r2 % 3
            goto L2e
        L2b:
            int r3 = r3 + r2
        L2c:
            r1 = r3 & 1
        L2e:
            if (r1 != 0) goto L31
            goto L32
        L31:
            r0 = 0
        L32:
            return r0
        L33:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "Invalid mask pattern: "
            r3.<init>(r0)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.r4.a(int, int, int):boolean");
    }

    private static int a(c0 c0Var, boolean z) {
        int b = z ? c0Var.b() : c0Var.c();
        int c = z ? c0Var.c() : c0Var.b();
        byte[][] a2 = c0Var.a();
        int i = 0;
        for (int i2 = 0; i2 < b; i2++) {
            byte b2 = -1;
            int i3 = 0;
            for (int i4 = 0; i4 < c; i4++) {
                byte b3 = z ? a2[i2][i4] : a2[i4][i2];
                if (b3 == b2) {
                    i3++;
                } else {
                    if (i3 >= 5) {
                        i += i3 - 2;
                    }
                    i3 = 1;
                    b2 = b3;
                }
            }
            if (i3 >= 5) {
                i += i3 - 2;
            }
        }
        return i;
    }
}
