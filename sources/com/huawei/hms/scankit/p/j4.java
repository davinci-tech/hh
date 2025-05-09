package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes4.dex */
public final class j4 extends g5 {
    private static final int[] c = {6, 8, 10, 12, 14};
    private static final int[] d = {1, 1, 1, 1};
    private static final int[][] e = {new int[]{1, 1, 2}, new int[]{1, 1, 3}};
    public static final int[][] f = {new int[]{1, 1, 2, 2, 1}, new int[]{2, 1, 1, 1, 2}, new int[]{1, 2, 1, 1, 2}, new int[]{2, 2, 1, 1, 1}, new int[]{1, 1, 2, 1, 2}, new int[]{2, 1, 2, 1, 1}, new int[]{1, 2, 2, 1, 1}, new int[]{1, 1, 1, 2, 2}, new int[]{2, 1, 1, 2, 1}, new int[]{1, 2, 1, 2, 1}, new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};

    /* renamed from: a, reason: collision with root package name */
    private int f5807a = -1;
    private int b = -1;

    private int[] b(r rVar) throws a {
        int c2 = c(rVar);
        while (true) {
            int[] c3 = c(rVar, c2, d);
            int i = c3[1];
            int i2 = c3[0];
            this.f5807a = (i - i2) / 4;
            if (a(rVar, i2)) {
                return c3;
            }
            c2 = c3[2];
        }
    }

    private static int c(r rVar) throws a {
        int e2 = rVar.e();
        int c2 = rVar.c(0);
        if (c2 != e2) {
            return c2;
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a, a {
        boolean z;
        int[] b = b(rVar);
        int[] a2 = a(rVar);
        StringBuilder sb = new StringBuilder(20);
        a(rVar, b[1], a2[0], sb);
        String sb2 = sb.toString();
        int[] iArr = c;
        int length = sb2.length();
        int length2 = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length2) {
                z = false;
                break;
            }
            int i4 = iArr[i2];
            if (length == i4) {
                z = true;
                break;
            }
            if (i4 > i3) {
                i3 = i4;
            }
            i2++;
        }
        if ((z || length <= i3) && !z) {
            throw a.a();
        }
        float f2 = i;
        return new s6(sb2, null, new u6[]{new u6(b[0], f2), new u6(a2[1], f2)}, BarcodeFormat.ITF);
    }

    private int[] c(r rVar, int i, int[] iArr) throws a {
        char c2;
        int i2;
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int e2 = rVar.e();
        char c3 = 0;
        int i3 = i;
        int i4 = i3;
        boolean z = false;
        int i5 = 0;
        while (i3 < e2) {
            if (rVar.b(i3) == z) {
                if (i5 == length - 1) {
                    int[] iArr3 = (int[]) iArr2.clone();
                    Arrays.sort(iArr3);
                    int i6 = iArr3[c3];
                    double d2 = iArr3[1] + i6;
                    int i7 = iArr3[2];
                    int i8 = iArr3[3];
                    int i9 = i7 + i8;
                    int i10 = i3;
                    if ((i9 * 0.5d) / (d2 * 0.5d) < 4.0d && (i8 * 1.0d) / i6 <= 3.0d) {
                        int[] iArr4 = new int[10];
                        g5.a(rVar, i10, iArr4);
                        this.b = -1;
                        for (int i11 = 0; i11 < 10; i11++) {
                            int i12 = iArr4[i11];
                            if (i12 > this.b) {
                                this.b = i12;
                            }
                        }
                        return new int[]{i4, i10, iArr2[0] + i4 + iArr2[1]};
                    }
                    i2 = i10;
                    c2 = 0;
                    i4 += iArr2[0] + iArr2[1];
                    int i13 = i5 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i13);
                    iArr2[i13] = 0;
                    iArr2[i5] = 0;
                    i5--;
                } else {
                    int i14 = i3;
                    c2 = c3;
                    i2 = i14;
                    i5++;
                }
                iArr2[i5] = 1;
                z = !z;
            } else if (i5 >= 0 && i5 < length) {
                iArr2[i5] = iArr2[i5] + 1;
                int i15 = i3;
                c2 = c3;
                i2 = i15;
            } else {
                throw a.a();
            }
            char c4 = c2;
            i3 = i2 + 1;
            c3 = c4;
        }
        throw a.a();
    }

    private int[] b(r rVar, int i, int[] iArr) throws a {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        int e2 = rVar.e();
        int i2 = i;
        int i3 = i2;
        boolean z = false;
        int i4 = 0;
        while (i2 < e2) {
            if (rVar.b(i2) == z) {
                if (i4 != length - 1) {
                    i4++;
                } else if (Math.min(iArr2[0], iArr2[1]) != 0 && Math.max(iArr2[0], iArr2[1]) != 0) {
                    float max = Math.max(iArr2[0], iArr2[1]) / Math.min(iArr2[0], iArr2[1]);
                    float f2 = iArr2[2];
                    int i5 = iArr2[0];
                    int i6 = iArr2[1];
                    int i7 = i5 + i6;
                    float f3 = (f2 * 2.0f) / i7;
                    if (max <= 3.0f && f3 > 1.5d && f3 < 4.0f) {
                        return new int[]{i3, i2, i5 + i3 + i6};
                    }
                    i3 += i7;
                    int i8 = i4 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i8);
                    iArr2[i8] = 0;
                    iArr2[i4] = 0;
                    i4 = i8;
                } else {
                    throw a.a();
                }
                iArr2[i4] = 1;
                z = !z;
            } else if (i4 >= 0 && i4 < length) {
                iArr2[i4] = iArr2[i4] + 1;
            } else {
                throw a.a();
            }
            i2++;
        }
        throw a.a();
    }

    private static void a(r rVar, int i, int i2, StringBuilder sb) throws a {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        while (i < i2) {
            g5.a(rVar, i, iArr);
            int i3 = -1;
            int i4 = 10000;
            for (int i5 = 0; i5 < 10; i5++) {
                int i6 = iArr[i5];
                if (i3 <= i6) {
                    i3 = i6;
                }
                if (i4 >= i6) {
                    i4 = i6;
                }
            }
            if (i3 / i4 <= 8) {
                for (int i7 = 0; i7 < 5; i7++) {
                    int i8 = i7 * 2;
                    iArr2[i7] = iArr[i8];
                    iArr3[i7] = iArr[i8 + 1];
                }
                sb.append((char) (b(iArr2) + 48));
                sb.append((char) (b(iArr3) + 48));
                for (int i9 = 0; i9 < 10; i9++) {
                    i += iArr[i9];
                }
            } else {
                throw a.a();
            }
        }
        if (i != i2) {
            throw a.a();
        }
    }

    private static int b(int[] iArr) throws a {
        int length = f.length;
        float f2 = 0.3f;
        int i = -1;
        for (int i2 = 0; i2 < length; i2++) {
            float a2 = g5.a(iArr, f[i2], 0.75f);
            if (a2 < f2) {
                i = i2;
                f2 = a2;
            } else if (Math.abs(a2 - f2) < 1.0E-7d) {
                i = -1;
            }
        }
        if (i >= 0) {
            return i % 10;
        }
        throw a.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0020, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.huawei.hms.scankit.p.r r6, int r7) {
        /*
            r5 = this;
            int r0 = r5.f5807a
            int r0 = r0 * 10
            int r1 = r5.b
            double r1 = (double) r1
            r3 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r1 = r1 * r3
            int r1 = (int) r1
            if (r0 >= r1) goto Le
            r0 = r1
        Le:
            int r7 = r7 + (-1)
            if (r0 <= 0) goto L1e
            if (r7 < 0) goto L1e
            boolean r1 = r6.b(r7)
            if (r1 == 0) goto L1b
            goto L1e
        L1b:
            int r0 = r0 + (-1)
            goto Le
        L1e:
            if (r0 == 0) goto L22
            r6 = 0
            return r6
        L22:
            r6 = 1
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.j4.a(com.huawei.hms.scankit.p.r, int):boolean");
    }

    private int[] a(r rVar) throws a {
        try {
            rVar.h();
            int c2 = c(rVar);
            while (true) {
                int[] b = b(rVar, c2, e[0]);
                if (a(rVar, b[0])) {
                    int i = b[0];
                    b[0] = rVar.e() - b[1];
                    b[1] = rVar.e() - i;
                    return b;
                }
                c2 = b[2];
            }
        } finally {
            rVar.h();
        }
    }
}
