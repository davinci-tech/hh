package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
public final class p0 extends g5 {
    public static final char[] e = "0123456789-$:/.+ABCD".toCharArray();
    public static final int[] f = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] g = {'A', 'B', 'C', 'D'};

    /* renamed from: a, reason: collision with root package name */
    private final StringBuilder f5850a = new StringBuilder(20);
    private int[] b = new int[80];
    private int c = 0;
    private int d;

    private int b() throws a {
        for (int i = 1; i < this.c; i += 2) {
            int b = b(i);
            if (b != -1 && a(g, e[b])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.b[i3];
                }
                if (i == 1 || this.b[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw a.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x009d, code lost:
    
        throw com.huawei.hms.scankit.p.a.a();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(int r15) throws com.huawei.hms.scankit.p.a {
        /*
            r14 = this;
            r0 = 0
            int[] r1 = new int[]{r0, r0, r0, r0}
            int[] r2 = new int[]{r0, r0, r0, r0}
            java.lang.StringBuilder r3 = r14.f5850a
            int r3 = r3.length()
            int r3 = r3 + (-1)
            r5 = r15
            r4 = r0
        L13:
            int[] r6 = com.huawei.hms.scankit.p.p0.f
            java.lang.StringBuilder r7 = r14.f5850a
            char r7 = r7.charAt(r4)
            r6 = r6[r7]
            r7 = 6
            r8 = r7
        L1f:
            r9 = 2
            if (r8 < 0) goto L3e
            r10 = r8 & 1
            r11 = r6 & 1
            int r11 = r11 * r9
            int r10 = r10 + r11
            r9 = r1[r10]
            int[] r11 = r14.b
            int r12 = r5 + r8
            r11 = r11[r12]
            int r9 = r9 + r11
            r1[r10] = r9
            r9 = r2[r10]
            int r9 = r9 + 1
            r2[r10] = r9
            int r6 = r6 >> 1
            int r8 = r8 + (-1)
            goto L1f
        L3e:
            if (r4 < r3) goto La6
            r4 = 4
            float[] r6 = new float[r4]
            float[] r8 = new float[r4]
            r4 = r0
        L46:
            if (r4 >= r9) goto L6e
            r5 = 0
            r8[r4] = r5
            int r5 = r4 + 2
            r10 = r1[r4]
            float r10 = (float) r10
            r11 = r2[r4]
            float r11 = (float) r11
            float r10 = r10 / r11
            r11 = r1[r5]
            float r11 = (float) r11
            r12 = r2[r5]
            float r12 = (float) r12
            float r13 = r11 / r12
            float r10 = r10 + r13
            r13 = 1073741824(0x40000000, float:2.0)
            float r10 = r10 / r13
            r8[r5] = r10
            r6[r4] = r10
            float r11 = r11 * r13
            r10 = 1069547520(0x3fc00000, float:1.5)
            float r11 = r11 + r10
            float r11 = r11 / r12
            r6[r5] = r11
            int r4 = r4 + 1
            goto L46
        L6e:
            int[] r1 = com.huawei.hms.scankit.p.p0.f
            java.lang.StringBuilder r2 = r14.f5850a
            char r2 = r2.charAt(r0)
            r1 = r1[r2]
            r2 = r7
        L79:
            if (r2 < 0) goto L9e
            r4 = r2 & 1
            r5 = r1 & 1
            int r5 = r5 * r9
            int r4 = r4 + r5
            int[] r5 = r14.b
            int r10 = r15 + r2
            r5 = r5[r10]
            float r5 = (float) r5
            r10 = r8[r4]
            int r10 = (r5 > r10 ? 1 : (r5 == r10 ? 0 : -1))
            if (r10 < 0) goto L99
            r4 = r6[r4]
            int r4 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
            if (r4 > 0) goto L99
            int r1 = r1 >> 1
            int r2 = r2 + (-1)
            goto L79
        L99:
            com.huawei.hms.scankit.p.a r15 = com.huawei.hms.scankit.p.a.a()
            throw r15
        L9e:
            if (r0 < r3) goto La1
            return
        La1:
            int r15 = r15 + 8
            int r0 = r0 + 1
            goto L6e
        La6:
            int r5 = r5 + 8
            int r4 = r4 + 1
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.p0.c(int):void");
    }

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        Arrays.fill(this.b, 0);
        a(rVar);
        int[] a2 = a();
        int i2 = a2[0];
        int i3 = a2[1];
        for (int i4 = 0; i4 < this.f5850a.length(); i4++) {
            StringBuilder sb = this.f5850a;
            sb.setCharAt(i4, e[sb.charAt(i4)]);
        }
        char charAt = this.f5850a.charAt(0);
        char[] cArr = g;
        if (!a(cArr, charAt)) {
            throw a.a();
        }
        StringBuilder sb2 = this.f5850a;
        if (!a(cArr, sb2.charAt(sb2.length() - 1))) {
            throw a.a();
        }
        if (this.f5850a.length() <= 3) {
            throw a.a();
        }
        int i5 = this.d;
        for (int i6 = 0; i6 < i2; i6++) {
            i5 += this.b[i6];
        }
        float f2 = i5;
        while (i2 < i3 - 1) {
            i5 += this.b[i2];
            i2++;
        }
        float f3 = i;
        return new s6(this.f5850a.toString(), null, new u6[]{new u6(f2, f3), new u6(i5, f3)}, BarcodeFormat.CODABAR);
    }

    private int b(int i) {
        int i2 = i + 7;
        if (i2 >= this.c) {
            return -1;
        }
        int[] iArr = this.b;
        HashSet hashSet = new HashSet();
        for (int i3 = i; i3 < i2; i3++) {
            hashSet.add(Integer.valueOf(iArr[i3]));
        }
        Iterator it = hashSet.iterator();
        int i4 = 0;
        int i5 = 0;
        while (it.hasNext()) {
            i5 += ((Integer) it.next()).intValue();
        }
        if (hashSet.size() > 0) {
            int size = i5 / hashSet.size();
            int i6 = 128;
            int i7 = 0;
            for (int i8 = 0; i8 < 7; i8++) {
                i6 >>= 1;
                if (iArr[i + i8] > size) {
                    i7 |= i6;
                }
            }
            while (true) {
                int[] iArr2 = f;
                if (i4 >= iArr2.length) {
                    break;
                }
                if (iArr2[i4] == i7) {
                    return i4;
                }
                i4++;
            }
        }
        return -1;
    }

    private int[] a() throws a {
        int i;
        int b = b();
        int i2 = 0;
        this.f5850a.setLength(0);
        int i3 = b;
        while (true) {
            int b2 = b(i3);
            if (b2 != -1) {
                this.f5850a.append((char) b2);
                i = i3 + 8;
                if ((this.f5850a.length() <= 1 || !a(g, e[b2])) && i < this.c) {
                    i3 = i;
                }
            } else {
                throw a.a();
            }
        }
        int i4 = this.b[i3 + 7];
        for (int i5 = -8; i5 < -1; i5++) {
            i2 += this.b[i + i5];
        }
        if (i < this.c && i4 < i2 / 2) {
            throw a.a();
        }
        c(b);
        return new int[]{b, i};
    }

    private void a(r rVar) throws a {
        int i = 0;
        this.c = 0;
        int d = rVar.d(0);
        this.d = d;
        int e2 = rVar.e();
        if (d < e2) {
            boolean z = true;
            while (d < e2) {
                if (rVar.b(d) != z) {
                    i++;
                } else {
                    a(i);
                    z = !z;
                    i = 1;
                }
                d++;
            }
            a(i);
            return;
        }
        throw a.a();
    }

    private void a(int i) throws a {
        try {
            int[] iArr = this.b;
            int i2 = this.c;
            iArr[i2] = i;
            int i3 = i2 + 1;
            this.c = i3;
            if (i3 >= iArr.length) {
                int[] iArr2 = new int[i3 * 2];
                System.arraycopy(iArr, 0, iArr2, 0, i3);
                this.b = iArr2;
            }
        } catch (NumberFormatException unused) {
            throw a.a();
        }
    }

    public static boolean a(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }
}
