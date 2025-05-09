package com.huawei.hms.scankit.p;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class h3 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5786a;
    private final List<g3> b = new ArrayList();
    private final int[] c = new int[5];
    private final v6 d;

    static final class d implements Comparator<g3>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final float f5787a;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(g3 g3Var, g3 g3Var2) {
            return Float.compare(Math.abs(g3Var.e() - this.f5787a), Math.abs(g3Var2.e() - this.f5787a));
        }

        private d(float f) {
            this.f5787a = f;
        }
    }

    public h3(s sVar, v6 v6Var) {
        this.f5786a = sVar;
        this.d = v6Var;
    }

    protected static boolean b(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 6) {
            return false;
        }
        float f = i / 6.0f;
        float f2 = f / 1.5f;
        if (Math.abs(f - iArr[0]) >= f2 || Math.abs(f - iArr[1]) >= f2) {
            return false;
        }
        return Math.abs((f * 2.0f) - ((float) iArr[2])) < 2.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    protected static boolean c(int[] iArr) {
        int i = 0;
        for (int i2 = 0; i2 < 5; i2++) {
            int i3 = iArr[i2];
            if (i3 == 0) {
                return false;
            }
            i += i3;
        }
        if (i < 6) {
            return false;
        }
        float f = i / 6.0f;
        float f2 = f / 1.0f;
        if (Math.abs(f - iArr[0]) >= f2 || Math.abs(f - iArr[1]) >= f2) {
            return false;
        }
        return Math.abs((f * 2.0f) - ((float) iArr[2])) < 2.0f * f2 && Math.abs(f - ((float) iArr[3])) < f2 && Math.abs(f - ((float) iArr[4])) < f2;
    }

    private void d(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    final g3[] a(Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        int c2 = this.f5786a.c();
        int e = this.f5786a.e();
        int[] iArr = new int[5];
        for (int i = 0; i < c2; i++) {
            a(iArr);
            int i2 = 0;
            for (int i3 = 0; i3 < e; i3++) {
                if (this.f5786a.b(i3, i)) {
                    if ((i2 & 1) == 1) {
                        i2++;
                    }
                    iArr[i2] = iArr[i2] + 1;
                } else if ((i2 & 1) != 0) {
                    iArr[i2] = iArr[i2] + 1;
                } else if (i2 == 4) {
                    if (!b(iArr)) {
                        d(iArr);
                    } else if (a(iArr, i, i3)) {
                        a(iArr);
                        i2 = 0;
                    } else {
                        d(iArr);
                    }
                    i2 = 3;
                } else {
                    i2++;
                    iArr[i2] = iArr[i2] + 1;
                }
            }
            if (b(iArr)) {
                a(iArr, i, e);
            }
        }
        return b();
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0082, code lost:
    
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0086, code lost:
    
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008c, code lost:
    
        if (r0.b(r12, r11) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008e, code lost:
    
        r9 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0090, code lost:
    
        if (r9 >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0092, code lost:
    
        r2[4] = r9 + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0099, code lost:
    
        r12 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009b, code lost:
    
        if (r12 < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x009d, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b2, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r12) - r14) * 5) < (r14 * 2)) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b4, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b9, code lost:
    
        if (b(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00bf, code lost:
    
        return a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float b(int r11, int r12, int r13, int r14) {
        /*
            r10 = this;
            com.huawei.hms.scankit.p.s r0 = r10.f5786a
            int r1 = r0.c()
            int[] r2 = r10.a()
            r3 = r11
        Lb:
            r4 = 2
            r5 = 1
            if (r3 < 0) goto L1d
            boolean r6 = r0.b(r12, r3)
            if (r6 == 0) goto L1d
            r6 = r2[r4]
            int r6 = r6 + r5
            r2[r4] = r6
            int r3 = r3 + (-1)
            goto Lb
        L1d:
            r6 = 2143289344(0x7fc00000, float:NaN)
            if (r3 >= 0) goto L22
            return r6
        L22:
            if (r3 < 0) goto L35
            boolean r7 = r0.b(r12, r3)
            if (r7 != 0) goto L35
            r7 = r2[r5]
            if (r7 > r13) goto L35
            int r7 = r7 + 1
            r2[r5] = r7
            int r3 = r3 + (-1)
            goto L22
        L35:
            if (r3 < 0) goto Lbf
            r7 = r2[r5]
            if (r7 <= r13) goto L3d
            goto Lbf
        L3d:
            r7 = 0
            if (r3 < 0) goto L51
            boolean r8 = r0.b(r12, r3)
            if (r8 == 0) goto L51
            r8 = r2[r7]
            if (r8 > r13) goto L51
            int r8 = r8 + 1
            r2[r7] = r8
            int r3 = r3 + (-1)
            goto L3d
        L51:
            r3 = r2[r7]
            if (r3 <= r13) goto L56
            return r6
        L56:
            int r11 = r11 + r5
        L57:
            if (r11 >= r1) goto L67
            boolean r3 = r0.b(r12, r11)
            if (r3 == 0) goto L67
            r3 = r2[r4]
            int r3 = r3 + r5
            r2[r4] = r3
            int r11 = r11 + 1
            goto L57
        L67:
            if (r11 != r1) goto L6a
            return r6
        L6a:
            r3 = 3
            if (r11 >= r1) goto L7e
            boolean r8 = r0.b(r12, r11)
            if (r8 != 0) goto L7e
            r8 = r2[r3]
            if (r8 >= r13) goto L7e
            int r8 = r8 + 1
            r2[r3] = r8
            int r11 = r11 + 1
            goto L6a
        L7e:
            if (r11 == r1) goto Lbf
            r8 = r2[r3]
            if (r8 < r13) goto L85
            goto Lbf
        L85:
            r8 = 4
            if (r11 >= r1) goto L99
            boolean r9 = r0.b(r12, r11)
            if (r9 == 0) goto L99
            r9 = r2[r8]
            if (r9 >= r13) goto L99
            int r9 = r9 + 1
            r2[r8] = r9
            int r11 = r11 + 1
            goto L85
        L99:
            r12 = r2[r8]
            if (r12 < r13) goto L9e
            return r6
        L9e:
            r13 = r2[r7]
            r0 = r2[r5]
            r1 = r2[r4]
            r3 = r2[r3]
            int r13 = r13 + r0
            int r13 = r13 + r1
            int r13 = r13 + r3
            int r13 = r13 + r12
            int r13 = r13 - r14
            int r12 = java.lang.Math.abs(r13)
            int r12 = r12 * 5
            int r14 = r14 * r4
            if (r12 < r14) goto Lb5
            return r6
        Lb5:
            boolean r12 = b(r2)
            if (r12 == 0) goto Lbf
            float r6 = a(r2, r11)
        Lbf:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.h3.b(int, int, int, int):float");
    }

    private static float a(int[] iArr, int i) {
        return ((i - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    private int[] a() {
        a(this.c);
        return this.c;
    }

    private void a(int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
    }

    private boolean a(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int[] a2 = a();
        int i6 = 0;
        while (i >= i6 && i2 >= i6 && this.f5786a.b(i2 - i6, i - i6)) {
            a2[2] = a2[2] + 1;
            i6++;
        }
        if (a2[2] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && !this.f5786a.b(i2 - i6, i - i6)) {
            a2[1] = a2[1] + 1;
            i6++;
        }
        if (a2[1] == 0) {
            return false;
        }
        while (i >= i6 && i2 >= i6 && this.f5786a.b(i2 - i6, i - i6)) {
            a2[0] = a2[0] + 1;
            i6++;
        }
        if (a2[0] == 0) {
            return false;
        }
        int c2 = this.f5786a.c();
        int e = this.f5786a.e();
        int i7 = 1;
        while (true) {
            int i8 = i + i7;
            if (i8 >= c2 || (i5 = i2 + i7) >= e || !this.f5786a.b(i5, i8)) {
                break;
            }
            a2[2] = a2[2] + 1;
            i7++;
        }
        while (true) {
            int i9 = i + i7;
            if (i9 >= c2 || (i4 = i2 + i7) >= e || this.f5786a.b(i4, i9)) {
                break;
            }
            a2[3] = a2[3] + 1;
            i7++;
        }
        if (a2[3] == 0) {
            return false;
        }
        while (true) {
            int i10 = i + i7;
            if (i10 >= c2 || (i3 = i2 + i7) >= e || !this.f5786a.b(i3, i10)) {
                break;
            }
            a2[4] = a2[4] + 1;
            i7++;
        }
        if (a2[4] == 0) {
            return false;
        }
        return c(a2);
    }

    private g3[] b() throws com.huawei.hms.scankit.p.a {
        int i = 0;
        while (i < this.b.size()) {
            g3 g3Var = this.b.get(i);
            if (g3Var.e() <= 5.0f && g3Var.a() <= 2) {
                this.b.remove(i);
                i--;
            }
            i++;
        }
        int size = this.b.size();
        int i2 = 3;
        if (size >= 3) {
            if (size >= 4) {
                float f = 0.0f;
                float f2 = 0.0f;
                while (this.b.iterator().hasNext()) {
                    f2 += r8.next().a();
                }
                float f3 = f2 / size;
                int i3 = 0;
                while (i3 < this.b.size() && this.b.size() > 4) {
                    if (this.b.get(i3).a() <= 0.5f * f3) {
                        this.b.remove(i3);
                        i3--;
                    }
                    i3++;
                }
                int size2 = this.b.size();
                Iterator<g3> it = this.b.iterator();
                float f4 = 0.0f;
                while (it.hasNext()) {
                    float e = it.next().e();
                    f += e;
                    f4 += e * e;
                }
                float f5 = f / size2;
                float sqrt = (float) Math.sqrt((f4 / r2) - (f5 * f5));
                Collections.sort(this.b, new d(f5));
                float max = Math.max(0.36f * f5, sqrt);
                int i4 = 0;
                while (i4 < this.b.size() && this.b.size() > 4) {
                    if (Math.abs(this.b.get(i4).e() - f5) > max) {
                        this.b.remove(i4);
                        i4--;
                    }
                    i4++;
                }
                int size3 = this.b.size();
                if (size3 >= 4) {
                    Collections.sort(this.b, new c());
                    if (size3 > 4 && this.b.get(3).a() - this.b.get(4).a() > 2) {
                        while (4 < this.b.size()) {
                            this.b.remove(4);
                        }
                    }
                    double[] dArr = new double[3];
                    while (i2 < this.b.size()) {
                        dArr[0] = a(this.b.get(0), this.b.get(1));
                        dArr[1] = a(this.b.get(1), this.b.get(i2));
                        dArr[2] = a(this.b.get(0), this.b.get(i2));
                        Arrays.sort(dArr);
                        double d2 = dArr[1];
                        double d3 = dArr[0];
                        double sqrt2 = ((d2 + d3) - dArr[2]) / ((Math.sqrt(d3) * 2.0d) * Math.sqrt(dArr[1]));
                        dArr[0] = a(this.b.get(0), this.b.get(2));
                        dArr[1] = a(this.b.get(2), this.b.get(i2));
                        dArr[2] = a(this.b.get(0), this.b.get(i2));
                        Arrays.sort(dArr);
                        double d4 = dArr[1];
                        double d5 = dArr[0];
                        double sqrt3 = ((d4 + d5) - dArr[2]) / ((Math.sqrt(d5) * 2.0d) * Math.sqrt(dArr[1]));
                        if (Math.abs(sqrt2) > 0.25d || Math.abs(sqrt3) > 0.25d) {
                            this.b.remove(i2);
                            i2--;
                        }
                        i2++;
                    }
                }
            }
            Collections.sort(this.b, new b());
            List<g3> list = this.b;
            return (g3[]) list.toArray(new g3[list.size()]);
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x0082, code lost:
    
        if (r2[3] < r13) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0086, code lost:
    
        if (r11 >= r1) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x008c, code lost:
    
        if (r0.b(r11, r12) == false) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x008e, code lost:
    
        r9 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0090, code lost:
    
        if (r9 >= r13) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0092, code lost:
    
        r2[4] = r9 + 1;
        r11 = r11 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0099, code lost:
    
        r12 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x009b, code lost:
    
        if (r12 < r13) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x009d, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x00b1, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r12) - r14) * 5) < r14) goto L62;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00b3, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00b8, code lost:
    
        if (b(r2) == false) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00be, code lost:
    
        return a(r2, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float a(int r11, int r12, int r13, int r14) {
        /*
            r10 = this;
            com.huawei.hms.scankit.p.s r0 = r10.f5786a
            int r1 = r0.e()
            int[] r2 = r10.a()
            r3 = r11
        Lb:
            r4 = 2
            r5 = 1
            if (r3 < 0) goto L1d
            boolean r6 = r0.b(r3, r12)
            if (r6 == 0) goto L1d
            r6 = r2[r4]
            int r6 = r6 + r5
            r2[r4] = r6
            int r3 = r3 + (-1)
            goto Lb
        L1d:
            r6 = 2143289344(0x7fc00000, float:NaN)
            if (r3 >= 0) goto L22
            return r6
        L22:
            if (r3 < 0) goto L35
            boolean r7 = r0.b(r3, r12)
            if (r7 != 0) goto L35
            r7 = r2[r5]
            if (r7 > r13) goto L35
            int r7 = r7 + 1
            r2[r5] = r7
            int r3 = r3 + (-1)
            goto L22
        L35:
            if (r3 < 0) goto Lbe
            r7 = r2[r5]
            if (r7 <= r13) goto L3d
            goto Lbe
        L3d:
            r7 = 0
            if (r3 < 0) goto L51
            boolean r8 = r0.b(r3, r12)
            if (r8 == 0) goto L51
            r8 = r2[r7]
            if (r8 > r13) goto L51
            int r8 = r8 + 1
            r2[r7] = r8
            int r3 = r3 + (-1)
            goto L3d
        L51:
            r3 = r2[r7]
            if (r3 <= r13) goto L56
            return r6
        L56:
            int r11 = r11 + r5
        L57:
            if (r11 >= r1) goto L67
            boolean r3 = r0.b(r11, r12)
            if (r3 == 0) goto L67
            r3 = r2[r4]
            int r3 = r3 + r5
            r2[r4] = r3
            int r11 = r11 + 1
            goto L57
        L67:
            if (r11 != r1) goto L6a
            return r6
        L6a:
            r3 = 3
            if (r11 >= r1) goto L7e
            boolean r8 = r0.b(r11, r12)
            if (r8 != 0) goto L7e
            r8 = r2[r3]
            if (r8 >= r13) goto L7e
            int r8 = r8 + 1
            r2[r3] = r8
            int r11 = r11 + 1
            goto L6a
        L7e:
            if (r11 == r1) goto Lbe
            r8 = r2[r3]
            if (r8 < r13) goto L85
            goto Lbe
        L85:
            r8 = 4
            if (r11 >= r1) goto L99
            boolean r9 = r0.b(r11, r12)
            if (r9 == 0) goto L99
            r9 = r2[r8]
            if (r9 >= r13) goto L99
            int r9 = r9 + 1
            r2[r8] = r9
            int r11 = r11 + 1
            goto L85
        L99:
            r12 = r2[r8]
            if (r12 < r13) goto L9e
            return r6
        L9e:
            r13 = r2[r7]
            r0 = r2[r5]
            r1 = r2[r4]
            r3 = r2[r3]
            int r13 = r13 + r0
            int r13 = r13 + r1
            int r13 = r13 + r3
            int r13 = r13 + r12
            int r13 = r13 - r14
            int r12 = java.lang.Math.abs(r13)
            int r12 = r12 * 5
            if (r12 < r14) goto Lb4
            return r6
        Lb4:
            boolean r12 = b(r2)
            if (r12 == 0) goto Lbe
            float r6 = a(r2, r11)
        Lbe:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.h3.a(int, int, int, int):float");
    }

    protected final boolean a(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int a2 = (int) a(iArr, i2);
        float b2 = b(i, a2, iArr[2], i4);
        if (!Float.isNaN(b2)) {
            int i5 = (int) b2;
            float a3 = a(a2, i5, iArr[2], i4);
            if (!Float.isNaN(a3) && a(i5, (int) a3)) {
                float f = i4 / 6.0f;
                while (true) {
                    if (i3 < this.b.size()) {
                        g3 g3Var = this.b.get(i3);
                        if (g3Var.b(f, b2, a3)) {
                            this.b.set(i3, g3Var.c(b2, a3, f));
                            break;
                        }
                        i3++;
                    } else {
                        g3 g3Var2 = new g3(a3, b2, f);
                        this.b.add(g3Var2);
                        v6 v6Var = this.d;
                        if (v6Var != null) {
                            v6Var.a(g3Var2);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static double a(g3 g3Var, g3 g3Var2) {
        double b2 = g3Var.b() - g3Var2.b();
        double c2 = g3Var.c() - g3Var2.c();
        return (b2 * b2) + (c2 * c2);
    }

    static final class b implements Comparator<g3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(g3 g3Var, g3 g3Var2) {
            return Float.compare(g3Var2.e(), g3Var.e());
        }

        private b() {
        }
    }

    static final class c implements Comparator<g3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(g3 g3Var, g3 g3Var2) {
            return Integer.compare(g3Var2.a(), g3Var.a());
        }

        private c() {
        }
    }
}
