package com.huawei.hms.scankit.p;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes9.dex */
public class i3 {
    private static boolean h = false;
    private static boolean i = false;

    /* renamed from: a, reason: collision with root package name */
    private final s f5795a;
    private final v6 e;
    private static final int[] f = {1, 3, 1, 1};
    private static final int[] g = {1, 1, 3, 1};
    private static final d j = new d();
    private final List<e3> b = new ArrayList();
    private final int[] d = new int[5];
    private final List<e3> c = new ArrayList();

    static final class b implements Comparator<e3>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final float f5796a;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            int compare = Integer.compare(e3Var2.a(), e3Var.a());
            return compare == 0 ? Float.compare(Math.abs(e3Var.e() - this.f5796a), Math.abs(e3Var2.e() - this.f5796a)) : compare;
        }

        private b(float f) {
            this.f5796a = f;
        }
    }

    static final class e implements Comparator<e3>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final float f5797a;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            return Float.compare(Math.abs(e3Var2.e() - this.f5797a), Math.abs(e3Var.e() - this.f5797a));
        }

        private e(float f) {
            this.f5797a = f;
        }
    }

    public i3(s sVar, v6 v6Var) {
        this.f5795a = sVar;
        this.e = v6Var;
    }

    protected static float a(int[] iArr, int[] iArr2, float f2) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            i2 += iArr[i4];
            i3 += iArr2[i4];
        }
        if (i2 < i3) {
            return Float.POSITIVE_INFINITY;
        }
        float f3 = i2;
        float f4 = f3 / i3;
        float f5 = 0.0f;
        for (int i5 = 0; i5 < length; i5++) {
            float f6 = iArr2[i5] * f4;
            float f7 = iArr[i5];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f2 * f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f3;
    }

    private e3[] c() throws com.huawei.hms.scankit.p.a {
        if (this.b.size() > 2) {
            try {
                return f();
            } catch (com.huawei.hms.scankit.p.a unused) {
                if (this.c.size() <= 0) {
                    throw com.huawei.hms.scankit.p.a.a();
                }
                Collections.sort(this.c, new c());
                int min = Math.min(3, this.c.size());
                for (int i2 = 0; i2 < min; i2++) {
                    e3 e3Var = this.c.get(i2);
                    float e2 = e3Var.e();
                    float c2 = e3Var.c();
                    float b2 = e3Var.b();
                    int i3 = 0;
                    while (true) {
                        if (i3 >= this.b.size()) {
                            this.b.add(e3Var);
                            break;
                        }
                        e3 e3Var2 = this.b.get(i3);
                        if (e3Var2.b(e2, c2, b2)) {
                            this.b.set(i3, e3Var2.a(c2, b2, e2, false));
                            break;
                        }
                        i3++;
                    }
                }
                return f();
            }
        }
        if (this.b.size() == 2) {
            int i4 = this.b.get(0).e() > this.b.get(1).e() ? 0 : 1;
            if (Math.max(this.b.get(0).e(), this.b.get(1).e()) / Math.min(this.b.get(0).e(), this.b.get(1).e()) > 1.5d) {
                e3 e3Var3 = this.b.get(i4);
                this.b.clear();
                this.b.add(e3Var3);
            }
        }
        if (this.b.size() <= 1 && this.c.size() >= 1) {
            for (int i5 = 0; i5 < this.b.size(); i5++) {
                e3 e3Var4 = this.b.get(i5);
                float e3 = e3Var4.e();
                float c3 = e3Var4.c();
                float b3 = e3Var4.b();
                int i6 = 0;
                while (true) {
                    if (i6 >= this.c.size()) {
                        this.c.add(e3Var4);
                        break;
                    }
                    e3 e3Var5 = this.c.get(i6);
                    if (e3Var5.b(e3, c3, b3)) {
                        this.c.set(i6, e3Var4.a(e3Var5.c(), e3Var5.b(), e3Var5.e(), false));
                        break;
                    }
                    i6++;
                }
            }
            this.b.clear();
            this.b.addAll(this.c);
            this.c.clear();
        }
        if (this.b.size() == 2) {
            try {
                return g();
            } catch (com.huawei.hms.scankit.p.a unused2) {
                return a();
            }
        }
        if (this.b.size() > 1) {
            return f();
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    private int[] d() {
        a(this.d);
        return this.d;
    }

    private static void e() {
        h = false;
        i = false;
    }

    private e3[] f() throws com.huawei.hms.scankit.p.a {
        int size = this.b.size();
        if (size < 3) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        e3[] e3VarArr = new e3[3];
        if (size == 3) {
            e3VarArr[0] = this.b.get(0);
            e3VarArr[1] = this.b.get(1);
            e3 e3Var = this.b.get(2);
            e3VarArr[2] = e3Var;
            if (b(e3VarArr[0], e3VarArr[1], e3Var, new float[3])) {
                return e3VarArr;
            }
            throw com.huawei.hms.scankit.p.a.a();
        }
        Collections.sort(this.b, new c());
        if (this.b.get(2).a() - this.b.get(3).a() > 1 && this.b.get(2).a() > 1) {
            e3VarArr[0] = this.b.get(0);
            e3VarArr[1] = this.b.get(1);
            e3VarArr[2] = this.b.get(2);
            return e3VarArr;
        }
        float f2 = 0.0f;
        if (this.b.get(3).a() > 1) {
            float f3 = 0.0f;
            for (int i2 = 0; i2 < 4; i2++) {
                f3 += this.b.get(i2).e();
            }
            float f4 = f3 / 4.0f;
            int i3 = 0;
            for (int i4 = 0; i4 < 4; i4++) {
                float abs = Math.abs(this.b.get(i4).e() - f4);
                if (abs > f2) {
                    i3 = i4;
                    f2 = abs;
                }
            }
            if (i3 == 0) {
                e3VarArr[0] = this.b.get(1);
                e3VarArr[1] = this.b.get(2);
                e3VarArr[2] = this.b.get(3);
            } else if (i3 == 1) {
                e3VarArr[0] = this.b.get(0);
                e3VarArr[1] = this.b.get(2);
                e3VarArr[2] = this.b.get(3);
            } else if (i3 != 2) {
                e3VarArr[0] = this.b.get(0);
                e3VarArr[1] = this.b.get(1);
                e3VarArr[2] = this.b.get(2);
            } else {
                e3VarArr[0] = this.b.get(0);
                e3VarArr[1] = this.b.get(1);
                e3VarArr[2] = this.b.get(3);
            }
            if (b(e3VarArr[0], e3VarArr[1], e3VarArr[2], new float[3])) {
                return e3VarArr;
            }
            throw com.huawei.hms.scankit.p.a.a();
        }
        if (this.b.get(1).a() > 1 && this.b.get(2).a() == 1) {
            ArrayList arrayList = new ArrayList();
            float e2 = (this.b.get(0).e() + this.b.get(1).e()) / 2.0f;
            for (int i5 = 2; i5 < size; i5++) {
                if (Math.abs(this.b.get(i5).e() - e2) < e2 * 0.5d) {
                    arrayList.add(this.b.get(i5));
                }
            }
            int i6 = 0;
            for (int i7 = 0; i7 < arrayList.size(); i7++) {
                float[] fArr = new float[3];
                if (b(this.b.get(0), this.b.get(1), (e3) arrayList.get(i7), fArr)) {
                    float f5 = fArr[0];
                    if (f5 >= f2) {
                        i6 = i7;
                        f2 = f5;
                    }
                }
            }
            e3VarArr[0] = this.b.get(0);
            e3VarArr[1] = this.b.get(1);
            if (i6 >= arrayList.size()) {
                throw com.huawei.hms.scankit.p.a.a();
            }
            e3VarArr[2] = (e3) arrayList.get(i6);
            return e3VarArr;
        }
        if (size > 3) {
            float f6 = 0.0f;
            float f7 = 0.0f;
            for (int i8 = 0; i8 < size; i8++) {
                float e3 = this.b.get(i8).e();
                f6 += e3;
                f7 += e3 * e3;
            }
            float f8 = f6 / size;
            float sqrt = (float) Math.sqrt((f7 / r1) - (f8 * f8));
            Collections.sort(this.b, new e(f8));
            float max = Math.max(0.5f * f8, sqrt);
            int i9 = 0;
            while (i9 < this.b.size() && this.b.size() > 3) {
                if (Math.abs(this.b.get(i9).e() - f8) > max) {
                    this.b.remove(i9);
                    i9--;
                }
                i9++;
            }
        }
        if (this.b.size() > 15) {
            Collections.sort(this.b, new c());
            List<e3> list = this.b;
            list.subList(15, list.size()).clear();
        } else if (this.b.size() > 12) {
            Collections.sort(this.b, new c());
            List<e3> list2 = this.b;
            list2.subList(12, list2.size()).clear();
        }
        if (this.b.size() >= 6) {
            Collections.sort(this.b, new f());
            List<e3> list3 = this.b;
            list3.subList(4, list3.size() - 2).clear();
            Collections.sort(this.b, new g());
            this.b.subList(1, 3).clear();
            Collections.sort(this.b, new g());
            List<e3> list4 = this.b;
            list4.subList(list4.size() - 1, this.b.size()).clear();
        } else if (this.b.size() > 3) {
            for (int i10 = 0; i10 < this.b.size(); i10++) {
                f2 += this.b.get(i10).e();
            }
            Collections.sort(this.b, new b(f2 / this.b.size()));
            List<e3> list5 = this.b;
            list5.subList(3, list5.size()).clear();
        }
        e3VarArr[0] = this.b.get(0);
        e3VarArr[1] = this.b.get(1);
        e3 e3Var2 = this.b.get(2);
        e3VarArr[2] = e3Var2;
        if (b(e3VarArr[0], e3VarArr[1], e3Var2, new float[3])) {
            return e3VarArr;
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x01b5, code lost:
    
        if (r2 == false) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.huawei.hms.scankit.p.e3[] g() throws com.huawei.hms.scankit.p.a {
        /*
            Method dump skipped, instructions count: 539
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.g():com.huawei.hms.scankit.p.e3[]");
    }

    final k3 b() throws com.huawei.hms.scankit.p.a {
        int c2 = this.f5795a.c();
        int e2 = this.f5795a.e();
        int i2 = (c2 * 3) / 388;
        if (i2 < 3) {
            i2 = 3;
        }
        if (r3.n) {
            i2 = 2;
        }
        a(i2, c2, e2);
        e3[] c3 = c();
        if (c3 == null) {
            throw com.huawei.hms.scankit.p.a.a();
        }
        u6.a(c3);
        if ((this.f5795a.c() * this.f5795a.e()) / (Math.sqrt(a(c3[0], c3[1])) * Math.sqrt(a(c3[1], c3[2]))) <= 900.0d) {
            return new k3(c3);
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    protected final void d(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0036, code lost:
    
        if (r8[2] <= r7) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0039, code lost:
    
        if (r5 < 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x003f, code lost:
    
        if (r0.b(r6, r5) != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0041, code lost:
    
        r1 = r8[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0043, code lost:
    
        if (r1 > r7) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0045, code lost:
    
        r8[1] = r1 + 1;
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004c, code lost:
    
        if (r5 < 0) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0050, code lost:
    
        if (r8[1] <= r7) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0054, code lost:
    
        if (r5 < 0) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x005a, code lost:
    
        if (r0.b(r6, r5) == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x005c, code lost:
    
        r3 = r8[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x005e, code lost:
    
        if (r3 > r7) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0060, code lost:
    
        r8[0] = r3 + 1;
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0069, code lost:
    
        if (r8[0] <= r7) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x006b, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x006c, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d(int r5, int r6, int r7, int[] r8) {
        /*
            r4 = this;
            com.huawei.hms.scankit.p.s r0 = r4.f5795a
        L2:
            r1 = 3
            if (r5 < 0) goto L16
            boolean r2 = r0.b(r6, r5)
            if (r2 != 0) goto L16
            r2 = r8[r1]
            if (r2 > r7) goto L16
            int r2 = r2 + 1
            r8[r1] = r2
            int r5 = r5 + (-1)
            goto L2
        L16:
            r2 = 1
            if (r5 < 0) goto L6d
            r1 = r8[r1]
            if (r1 <= r7) goto L1e
            goto L6d
        L1e:
            r1 = 2
            if (r5 < 0) goto L32
            boolean r3 = r0.b(r6, r5)
            if (r3 == 0) goto L32
            r3 = r8[r1]
            if (r3 > r7) goto L32
            int r3 = r3 + 1
            r8[r1] = r3
            int r5 = r5 + (-1)
            goto L1e
        L32:
            if (r5 < 0) goto L6d
            r1 = r8[r1]
            if (r1 <= r7) goto L39
            goto L6d
        L39:
            if (r5 < 0) goto L4c
            boolean r1 = r0.b(r6, r5)
            if (r1 != 0) goto L4c
            r1 = r8[r2]
            if (r1 > r7) goto L4c
            int r1 = r1 + 1
            r8[r2] = r1
            int r5 = r5 + (-1)
            goto L39
        L4c:
            if (r5 < 0) goto L6d
            r1 = r8[r2]
            if (r1 <= r7) goto L53
            goto L6d
        L53:
            r1 = 0
            if (r5 < 0) goto L67
            boolean r3 = r0.b(r6, r5)
            if (r3 == 0) goto L67
            r3 = r8[r1]
            if (r3 > r7) goto L67
            int r3 = r3 + 1
            r8[r1] = r3
            int r5 = r5 + (-1)
            goto L53
        L67:
            r5 = r8[r1]
            if (r5 <= r7) goto L6c
            return r2
        L6c:
            return r1
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.d(int, int, int, int[]):boolean");
    }

    private void a(int i2, int i3, int i4) {
        int i5;
        int[] iArr = new int[5];
        for (int i6 = i2 - 1; i6 < i3; i6 += i2) {
            a(iArr);
            int[] iArr2 = {0, i6, 0, i4, i2};
            int i7 = 0;
            while (i7 < i4) {
                iArr2[2] = i7;
                if (this.f5795a.b(i7, i6)) {
                    a(iArr, iArr2);
                } else if (b(iArr, iArr2)) {
                    i7++;
                }
                i7 = iArr2[2];
                i2 = iArr2[4];
                i7++;
            }
            if (b(iArr)) {
                i5 = i4;
                for (int i8 = iArr2[0]; i8 > 2; i8--) {
                    i5 -= iArr[i8];
                }
                b(iArr, i6, i5);
            } else {
                i5 = i4;
            }
            if (a(iArr, false) && a(iArr, i6, i5, false)) {
                i2 = 2;
            }
        }
    }

    private boolean b(int[] iArr, int[] iArr2) {
        int i2 = iArr2[0];
        if ((i2 & 1) != 0) {
            iArr[i2] = iArr[i2] + 1;
        } else if (i2 == 4) {
            if (a(iArr, false)) {
                boolean a2 = a(iArr, iArr2[1], iArr2[2], false);
                if (a2) {
                    iArr2[4] = 2;
                }
                if (!a2) {
                    a2 = a(iArr, iArr2[1], iArr2[2]);
                }
                if (a2) {
                    iArr2[0] = 0;
                    a(iArr);
                    return true;
                }
            }
            if (b(iArr)) {
                int i3 = iArr2[2];
                for (int i4 = iArr2[0]; i4 > 2; i4--) {
                    i3 -= iArr[i4];
                }
                if (b(iArr, iArr2[1], i3)) {
                    d(iArr);
                    iArr2[0] = 3;
                    return true;
                }
            }
            if (r3.h && a(iArr, true) && a(iArr, iArr2[1], iArr2[2], true)) {
                iArr2[0] = 0;
                a(iArr);
                return true;
            }
            d(iArr);
            iArr2[0] = 3;
        } else {
            int i5 = i2 + 1;
            iArr2[0] = i5;
            iArr[i5] = iArr[i5] + 1;
        }
        return false;
    }

    private void a(int[] iArr, int[] iArr2) {
        int i2 = iArr2[0];
        if ((i2 & 1) == 1) {
            iArr2[0] = i2 + 1;
        }
        int i3 = iArr2[0];
        if (i3 >= 0 && i3 < iArr.length) {
            iArr[i3] = iArr[i3] + 1;
        }
        if (iArr2[2] == iArr2[3] - 1 && iArr2[0] == 4) {
            if (a(iArr, false)) {
                boolean a2 = a(iArr, iArr2[1], iArr2[2], false);
                if (a2) {
                    iArr2[4] = 2;
                }
                if (!a2) {
                    a2 = a(iArr, iArr2[1], iArr2[2]);
                }
                if (a2) {
                    iArr2[0] = 0;
                    a(iArr);
                    while (iArr2[2] < this.f5795a.e() && !this.f5795a.b(iArr2[2], iArr2[1])) {
                        iArr2[2] = iArr2[2] + 1;
                    }
                }
            }
            if (r3.h && a(iArr, true) && a(iArr, iArr2[1], iArr2[2], true)) {
                iArr2[0] = 0;
                a(iArr);
            }
        }
    }

    private static float a(int[] iArr, int i2) {
        return ((i2 - iArr[4]) - iArr[3]) - (iArr[2] / 2.0f);
    }

    protected static boolean c(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f2 = i2 / 7.0f;
        float f3 = 0.75f * f2;
        if (Math.abs(f2 - iArr[0]) >= f3 || Math.abs(f2 - iArr[1]) >= f3) {
            return false;
        }
        return Math.abs((f2 * 3.0f) - ((float) iArr[2])) < 3.0f * f3 && Math.abs(f2 - ((float) iArr[3])) < f3 && Math.abs(f2 - ((float) iArr[4])) < f3;
    }

    protected static boolean a(int[] iArr, boolean z) {
        float f2;
        float f3;
        e();
        int i2 = 0;
        for (int i3 = 0; i3 < 5; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        if (z && r3.h) {
            f3 = 0.75f;
            f2 = 1.0f;
        } else {
            f2 = 3.0f;
            f3 = 0.5f;
        }
        float f4 = i2 / 7.0f;
        float f5 = f3 * f4;
        if (Math.abs(f4 - iArr[0]) < f5 && Math.abs(f4 - iArr[1]) < f5) {
            if (Math.abs((3.0f * f4) - iArr[2]) < f2 * f5 && Math.abs(f4 - iArr[3]) < f5 && Math.abs(f4 - iArr[4]) < f5) {
                return true;
            }
        }
        if (!z) {
            return false;
        }
        int[] iArr2 = new int[iArr.length - 1];
        int i5 = 0;
        while (i5 < iArr.length - 1) {
            int i6 = i5 + 1;
            iArr2[i5] = iArr[i6];
            i5 = i6;
        }
        int[] iArr3 = new int[iArr.length - 1];
        for (int i7 = 0; i7 < iArr.length - 1; i7++) {
            iArr3[i7] = iArr[i7];
        }
        float a2 = a(iArr2, f, 0.5f);
        float a3 = a(iArr3, g, 0.5f);
        boolean z2 = a2 < 0.3f;
        h = z2;
        boolean z3 = a3 < 0.3f;
        i = z3;
        return z2 || z3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0041, code lost:
    
        if (r2[3] < r12) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:
    
        if (r10 >= r1) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        if (r0.b(r11, r10) == false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004d, code lost:
    
        r8 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004f, code lost:
    
        if (r8 >= r12) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0051, code lost:
    
        r2[4] = r8 + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005c, code lost:
    
        if (a(r2, r14) != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005e, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005f, code lost:
    
        r11 = (((r2[0] + r2[1]) + r2[2]) + r2[3]) + r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x006e, code lost:
    
        if (r14 == false) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0078, code lost:
    
        if ((java.lang.Math.abs(r11 - r13) * 5) < (r13 * 3)) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
    
        if (com.huawei.hms.scankit.p.i3.i != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0080, code lost:
    
        if (com.huawei.hms.scankit.p.i3.h != false) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0082, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x008f, code lost:
    
        return a(r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0085, code lost:
    
        if (r11 >= (r13 * 3)) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0088, code lost:
    
        if ((r11 * 3) > r13) goto L48;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float b(int r10, int r11, int r12, int r13, boolean r14) {
        /*
            r9 = this;
            com.huawei.hms.scankit.p.s r0 = r9.f5795a
            int r1 = r0.c()
            int[] r2 = r9.d()
            boolean r3 = r9.b(r10, r11, r12, r2)
            r4 = 2143289344(0x7fc00000, float:NaN)
            if (r3 == 0) goto L13
            return r4
        L13:
            r3 = 1
            int r10 = r10 + r3
        L15:
            r5 = 2
            if (r10 >= r1) goto L26
            boolean r6 = r0.b(r11, r10)
            if (r6 == 0) goto L26
            r6 = r2[r5]
            int r6 = r6 + r3
            r2[r5] = r6
            int r10 = r10 + 1
            goto L15
        L26:
            if (r10 != r1) goto L29
            return r4
        L29:
            r6 = 3
            if (r10 >= r1) goto L3d
            boolean r7 = r0.b(r11, r10)
            if (r7 != 0) goto L3d
            r7 = r2[r6]
            if (r7 >= r12) goto L3d
            int r7 = r7 + 1
            r2[r6] = r7
            int r10 = r10 + 1
            goto L29
        L3d:
            if (r10 == r1) goto L90
            r7 = r2[r6]
            if (r7 < r12) goto L44
            goto L90
        L44:
            r7 = 4
            if (r10 >= r1) goto L58
            boolean r8 = r0.b(r11, r10)
            if (r8 == 0) goto L58
            r8 = r2[r7]
            if (r8 >= r12) goto L58
            int r8 = r8 + 1
            r2[r7] = r8
            int r10 = r10 + 1
            goto L44
        L58:
            boolean r11 = a(r2, r14)
            if (r11 != 0) goto L5f
            return r4
        L5f:
            r11 = 0
            r11 = r2[r11]
            r12 = r2[r3]
            int r11 = r11 + r12
            r12 = r2[r5]
            int r11 = r11 + r12
            r12 = r2[r6]
            int r11 = r11 + r12
            r12 = r2[r7]
            int r11 = r11 + r12
            if (r14 == 0) goto L83
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            int r13 = r13 * r6
            if (r11 < r13) goto L8b
            boolean r11 = com.huawei.hms.scankit.p.i3.i
            if (r11 != 0) goto L8b
            boolean r11 = com.huawei.hms.scankit.p.i3.h
            if (r11 != 0) goto L8b
            return r4
        L83:
            int r12 = r13 * 3
            if (r11 >= r12) goto L90
            int r11 = r11 * r6
            if (r11 > r13) goto L8b
            goto L90
        L8b:
            float r10 = a(r2, r10)
            return r10
        L90:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.b(int, int, int, int, boolean):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x001a, code lost:
    
        if (r8[1] <= r7) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x001e, code lost:
    
        if (r5 < 0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0024, code lost:
    
        if (r0.b(r6, r5) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0026, code lost:
    
        r3 = r8[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0028, code lost:
    
        if (r3 > r7) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x002a, code lost:
    
        r8[0] = r3 + 1;
        r5 = r5 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0033, code lost:
    
        if (r8[0] <= r7) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0035, code lost:
    
        return true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0036, code lost:
    
        return false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(int r5, int r6, int r7, int[] r8) {
        /*
            r4 = this;
            com.huawei.hms.scankit.p.s r0 = r4.f5795a
        L2:
            r1 = 1
            if (r5 < 0) goto L16
            boolean r2 = r0.b(r6, r5)
            if (r2 != 0) goto L16
            r2 = r8[r1]
            if (r2 > r7) goto L16
            int r2 = r2 + 1
            r8[r1] = r2
            int r5 = r5 + (-1)
            goto L2
        L16:
            if (r5 < 0) goto L37
            r2 = r8[r1]
            if (r2 <= r7) goto L1d
            goto L37
        L1d:
            r2 = 0
            if (r5 < 0) goto L31
            boolean r3 = r0.b(r6, r5)
            if (r3 == 0) goto L31
            r3 = r8[r2]
            if (r3 > r7) goto L31
            int r3 = r3 + 1
            r8[r2] = r3
            int r5 = r5 + (-1)
            goto L1d
        L31:
            r5 = r8[r2]
            if (r5 <= r7) goto L36
            return r1
        L36:
            return r2
        L37:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.c(int, int, int, int[]):boolean");
    }

    protected final void a(int[] iArr) {
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = 0;
        }
    }

    private boolean a(int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int[] d2 = d();
        int i7 = 0;
        while (i2 >= i7 && i3 >= i7 && this.f5795a.b(i3 - i7, i2 - i7)) {
            d2[2] = d2[2] + 1;
            i7++;
        }
        if (d2[2] == 0) {
            return false;
        }
        while (i2 >= i7 && i3 >= i7 && !this.f5795a.b(i3 - i7, i2 - i7)) {
            d2[1] = d2[1] + 1;
            i7++;
        }
        if (d2[1] == 0) {
            return false;
        }
        while (i2 >= i7 && i3 >= i7 && this.f5795a.b(i3 - i7, i2 - i7)) {
            d2[0] = d2[0] + 1;
            i7++;
        }
        if (d2[0] == 0) {
            return false;
        }
        int c2 = this.f5795a.c();
        int e2 = this.f5795a.e();
        int i8 = 1;
        while (true) {
            int i9 = i2 + i8;
            if (i9 >= c2 || (i6 = i3 + i8) >= e2 || !this.f5795a.b(i6, i9)) {
                break;
            }
            d2[2] = d2[2] + 1;
            i8++;
        }
        while (true) {
            int i10 = i2 + i8;
            if (i10 >= c2 || (i5 = i3 + i8) >= e2 || this.f5795a.b(i5, i10)) {
                break;
            }
            d2[3] = d2[3] + 1;
            i8++;
        }
        if (d2[3] == 0) {
            return false;
        }
        while (true) {
            int i11 = i2 + i8;
            if (i11 >= c2 || (i4 = i3 + i8) >= e2 || !this.f5795a.b(i4, i11)) {
                break;
            }
            d2[4] = d2[4] + 1;
            i8++;
        }
        if (d2[4] == 0) {
            return false;
        }
        return c(d2);
    }

    private boolean b(int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        s sVar = this.f5795a;
        while (true) {
            z = true;
            if (i2 < 0 || !sVar.b(i3, i2)) {
                break;
            }
            iArr[2] = iArr[2] + 1;
            i2--;
        }
        if (i2 < 0) {
            return true;
        }
        while (i2 >= 0 && !sVar.b(i3, i2)) {
            int i6 = iArr[1];
            if (i6 > i4) {
                break;
            }
            iArr[1] = i6 + 1;
            i2--;
        }
        if (i2 >= 0 && iArr[1] <= i4) {
            while (true) {
                z = false;
                if (i2 < 0 || !sVar.b(i3, i2) || (i5 = iArr[0]) > i4) {
                    break;
                }
                iArr[0] = i5 + 1;
                i2--;
            }
        }
        return z;
    }

    private static boolean b(e3 e3Var, e3 e3Var2, e3 e3Var3, float[] fArr) {
        a(e3Var, e3Var2, e3Var3, fArr);
        float sqrt = (float) Math.sqrt(fArr[1]);
        float sqrt2 = (float) Math.sqrt(fArr[2]);
        float sqrt3 = (float) Math.sqrt(fArr[0]);
        if (Math.min(Math.min(sqrt, sqrt2), sqrt3) <= Math.max(Math.max(e3Var.e(), e3Var2.e()), e3Var3.e()) * 7.0f) {
            return false;
        }
        float f2 = fArr[1];
        float f3 = fArr[2];
        float f4 = fArr[0];
        float f5 = ((f2 + f3) - f4) / ((sqrt * 2.0f) * sqrt2);
        float f6 = sqrt3 * 2.0f;
        float f7 = ((f4 + f2) - f3) / (sqrt * f6);
        float f8 = ((f4 + f3) - f2) / (f6 * sqrt2);
        return Math.abs(f5) <= 0.45f && f7 >= 0.2588f && f7 <= 0.94f && f8 >= 0.2588f && f8 <= 0.94f;
    }

    protected static boolean b(int[] iArr) {
        int i2 = 0;
        for (int i3 = 0; i3 < 3; i3++) {
            int i4 = iArr[i3];
            if (i4 == 0) {
                return false;
            }
            i2 += i4;
        }
        if (i2 < 7) {
            return false;
        }
        float f2 = i2 / 7.0f;
        float f3 = 0.5f * f2;
        if (Math.abs(f2 - iArr[0]) < f3) {
            return Math.abs((5.0f * f2) - ((float) iArr[1])) < f3 && Math.abs(f2 - ((float) iArr[2])) < f3;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x0041, code lost:
    
        if (r2[3] < r12) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0045, code lost:
    
        if (r10 >= r1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x004b, code lost:
    
        if (r0.b(r10, r11) == false) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004d, code lost:
    
        r8 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x004f, code lost:
    
        if (r8 >= r12) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0051, code lost:
    
        r2[4] = r8 + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005c, code lost:
    
        if (a(r2, r14) != false) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x005e, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x005f, code lost:
    
        r11 = r2[0];
        r12 = r2[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0075, code lost:
    
        if ((java.lang.Math.abs(((((r11 + r12) + r2[2]) + r2[3]) + r2[4]) - r13) * 5) < r13) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
    
        if (com.huawei.hms.scankit.p.i3.i != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x007d, code lost:
    
        if (com.huawei.hms.scankit.p.i3.h != false) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x007f, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0084, code lost:
    
        return a(r2, r10);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float a(int r10, int r11, int r12, int r13, boolean r14) {
        /*
            r9 = this;
            com.huawei.hms.scankit.p.s r0 = r9.f5795a
            int r1 = r0.e()
            int[] r2 = r9.d()
            boolean r3 = r9.a(r10, r11, r12, r2)
            r4 = 2143289344(0x7fc00000, float:NaN)
            if (r3 == 0) goto L13
            return r4
        L13:
            r3 = 1
            int r10 = r10 + r3
        L15:
            r5 = 2
            if (r10 >= r1) goto L26
            boolean r6 = r0.b(r10, r11)
            if (r6 == 0) goto L26
            r6 = r2[r5]
            int r6 = r6 + r3
            r2[r5] = r6
            int r10 = r10 + 1
            goto L15
        L26:
            if (r10 != r1) goto L29
            return r4
        L29:
            r6 = 3
            if (r10 >= r1) goto L3d
            boolean r7 = r0.b(r10, r11)
            if (r7 != 0) goto L3d
            r7 = r2[r6]
            if (r7 >= r12) goto L3d
            int r7 = r7 + 1
            r2[r6] = r7
            int r10 = r10 + 1
            goto L29
        L3d:
            if (r10 == r1) goto L85
            r7 = r2[r6]
            if (r7 < r12) goto L44
            goto L85
        L44:
            r7 = 4
            if (r10 >= r1) goto L58
            boolean r8 = r0.b(r10, r11)
            if (r8 == 0) goto L58
            r8 = r2[r7]
            if (r8 >= r12) goto L58
            int r8 = r8 + 1
            r2[r7] = r8
            int r10 = r10 + 1
            goto L44
        L58:
            boolean r11 = a(r2, r14)
            if (r11 != 0) goto L5f
            return r4
        L5f:
            r11 = 0
            r11 = r2[r11]
            r12 = r2[r3]
            r14 = r2[r5]
            r0 = r2[r6]
            r1 = r2[r7]
            int r11 = r11 + r12
            int r11 = r11 + r14
            int r11 = r11 + r0
            int r11 = r11 + r1
            int r11 = r11 - r13
            int r11 = java.lang.Math.abs(r11)
            int r11 = r11 * 5
            if (r11 < r13) goto L80
            boolean r11 = com.huawei.hms.scankit.p.i3.i
            if (r11 != 0) goto L80
            boolean r11 = com.huawei.hms.scankit.p.i3.h
            if (r11 != 0) goto L80
            return r4
        L80:
            float r10 = a(r2, r10)
            return r10
        L85:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.a(int, int, int, int, boolean):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x002d, code lost:
    
        if (r2[3] <= r11) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0031, code lost:
    
        if (r9 >= r1) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0037, code lost:
    
        if (r0.b(r10, r9) == false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0039, code lost:
    
        r2[4] = r2[4] + 1;
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0041, code lost:
    
        r10 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0043, code lost:
    
        if (r10 <= r11) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0045, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005c, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r10) - r12) * 5) < (r12 * 2)) goto L28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005e, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0063, code lost:
    
        if (a(r2, true) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0069, code lost:
    
        return a(r2, r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float b(int r9, int r10, int r11, int r12) {
        /*
            r8 = this;
            com.huawei.hms.scankit.p.s r0 = r8.f5795a
            int r1 = r0.c()
            int[] r2 = r8.d()
            boolean r3 = r8.d(r9, r10, r11, r2)
            r4 = 2143289344(0x7fc00000, float:NaN)
            if (r3 == 0) goto L13
            return r4
        L13:
            r3 = 1
            int r9 = r9 + r3
        L15:
            r5 = 3
            if (r9 >= r1) goto L29
            boolean r6 = r0.b(r10, r9)
            if (r6 != 0) goto L29
            r6 = r2[r5]
            if (r6 > r11) goto L29
            int r6 = r6 + 1
            r2[r5] = r6
            int r9 = r9 + 1
            goto L15
        L29:
            if (r9 < 0) goto L69
            r6 = r2[r5]
            if (r6 <= r11) goto L30
            goto L69
        L30:
            r6 = 4
            if (r9 >= r1) goto L41
            boolean r7 = r0.b(r10, r9)
            if (r7 == 0) goto L41
            r7 = r2[r6]
            int r7 = r7 + r3
            r2[r6] = r7
            int r9 = r9 + 1
            goto L30
        L41:
            r10 = r2[r6]
            if (r10 <= r11) goto L46
            return r4
        L46:
            r11 = 0
            r11 = r2[r11]
            r0 = r2[r3]
            r1 = 2
            r6 = r2[r1]
            r5 = r2[r5]
            int r11 = r11 + r0
            int r11 = r11 + r6
            int r11 = r11 + r5
            int r11 = r11 + r10
            int r11 = r11 - r12
            int r10 = java.lang.Math.abs(r11)
            int r10 = r10 * 5
            int r12 = r12 * r1
            if (r10 < r12) goto L5f
            return r4
        L5f:
            boolean r10 = a(r2, r3)
            if (r10 == 0) goto L69
            float r4 = a(r2, r9)
        L69:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.b(int, int, int, int):float");
    }

    protected final boolean b(int[] iArr, int i2, int i3) {
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = i4 + i5 + iArr[2];
        float f2 = i3 - (i6 / 2);
        int i7 = (int) f2;
        float a2 = a(i2, i7, i5, i6);
        if (Float.isNaN(a2)) {
            int i8 = iArr[1];
            float f3 = (i8 * 2) / 5;
            a2 = a(i2, (int) (f2 - f3), (int) (f3 + f2), i8, i6);
            if (Float.isNaN(a2) && r3.h) {
                a2 = b(i2, i7, iArr[1], i6);
            }
        }
        if (Float.isNaN(a2)) {
            return false;
        }
        return a(false, a2, f2, i6 / 7.0f);
    }

    private boolean a(int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        s sVar = this.f5795a;
        while (true) {
            z = true;
            if (i2 < 0 || !sVar.b(i2, i3)) {
                break;
            }
            iArr[2] = iArr[2] + 1;
            i2--;
        }
        if (i2 < 0) {
            return true;
        }
        while (i2 >= 0 && !sVar.b(i2, i3)) {
            int i6 = iArr[1];
            if (i6 > i4) {
                break;
            }
            iArr[1] = i6 + 1;
            i2--;
        }
        if (i2 >= 0 && iArr[1] <= i4) {
            while (true) {
                z = false;
                if (i2 < 0 || !sVar.b(i2, i3) || (i5 = iArr[0]) > i4) {
                    break;
                }
                iArr[0] = i5 + 1;
                i2--;
            }
        }
        return z;
    }

    protected final boolean a(int[] iArr, int i2, int i3, boolean z) {
        int i4;
        int i5;
        int i6 = 0;
        int i7 = iArr[0];
        int i8 = iArr[1];
        int i9 = iArr[2];
        int i10 = iArr[3];
        int i11 = iArr[4];
        boolean z2 = h;
        boolean z3 = i;
        if (z2) {
            i5 = i3;
            i4 = i8 + i8 + i9 + i10 + i11;
        } else {
            int i12 = i7 + i8 + i9 + i10;
            i4 = z3 ? i12 + i10 : i12 + i11;
            i5 = i3;
        }
        int a2 = (int) a(iArr, i5);
        float b2 = b(i2, a2, iArr[2], i4, z);
        boolean z4 = h;
        boolean z5 = i;
        if (!Float.isNaN(b2)) {
            float f2 = i4 / 7.0f;
            int i13 = (int) b2;
            float a3 = a(a2, i13, iArr[2], i4, z);
            if (!Float.isNaN(a3) && (a(i13, (int) a3) || (z && (z2 || z3 || z4 || z5)))) {
                if (z) {
                    return a(false, b2, a3, f2);
                }
                while (true) {
                    if (i6 < this.b.size()) {
                        e3 e3Var = this.b.get(i6);
                        if (e3Var.b(f2, b2, a3)) {
                            this.b.set(i6, e3Var.a(b2, a3, f2, true));
                            break;
                        }
                        i6++;
                    } else {
                        e3 e3Var2 = new e3(a3, b2, f2, true);
                        this.b.add(e3Var2);
                        v6 v6Var = this.e;
                        if (v6Var != null) {
                            v6Var.a(e3Var2);
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static double a(e3 e3Var, e3 e3Var2) {
        double b2 = e3Var.b() - e3Var2.b();
        double c2 = e3Var.c() - e3Var2.c();
        return (b2 * b2) + (c2 * c2);
    }

    private static void a(e3 e3Var, e3 e3Var2, e3 e3Var3, float[] fArr) {
        float b2 = e3Var.b() - e3Var2.b();
        float c2 = e3Var.c() - e3Var2.c();
        float f2 = (b2 * b2) + (c2 * c2);
        float b3 = e3Var.b() - e3Var3.b();
        float c3 = e3Var.c() - e3Var3.c();
        float f3 = (b3 * b3) + (c3 * c3);
        float b4 = e3Var2.b() - e3Var3.b();
        float c4 = e3Var2.c() - e3Var3.c();
        float f4 = (b4 * b4) + (c4 * c4);
        if (f2 > f4 && f2 > f3) {
            fArr[0] = f2;
            fArr[1] = f3;
            fArr[2] = f4;
        } else if (f4 > f2 && f4 > f3) {
            fArr[0] = f4;
            fArr[1] = f2;
            fArr[2] = f3;
        } else {
            fArr[0] = f3;
            fArr[1] = f2;
            fArr[2] = f4;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x005c, code lost:
    
        if (r2[3] < r12) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0060, code lost:
    
        if (r10 >= r1) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0066, code lost:
    
        if (r0.b(r11, r10) == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0068, code lost:
    
        r8 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x006a, code lost:
    
        if (r8 >= r12) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x006c, code lost:
    
        r2[4] = r8 + 1;
        r10 = r10 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0073, code lost:
    
        r11 = r2[4];
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0075, code lost:
    
        if (r11 < r12) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0077, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x008d, code lost:
    
        if ((java.lang.Math.abs(((((r2[0] + r2[1]) + r2[2]) + r2[3]) + r11) - r13) * 5) < (r13 * 2)) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x008f, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0092, code lost:
    
        if (com.huawei.hms.scankit.p.r3.h == false) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0098, code lost:
    
        if (a(r2, true) == false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x009e, code lost:
    
        return a(r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00a3, code lost:
    
        if (a(r2, false) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00a9, code lost:
    
        return a(r2, r10);
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:?, code lost:
    
        return Float.NaN;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:?, code lost:
    
        return Float.NaN;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float a(int r10, int r11, int r12, int r13) {
        /*
            r9 = this;
            com.huawei.hms.scankit.p.s r0 = r9.f5795a
            int r1 = r0.c()
            int[] r2 = r9.d()
            boolean r3 = r9.c(r10, r11, r12, r2)
            r4 = 2143289344(0x7fc00000, float:NaN)
            if (r3 == 0) goto L13
            return r4
        L13:
            r3 = 1
            int r10 = r10 + r3
        L15:
            if (r10 >= r1) goto L28
            boolean r5 = r0.b(r11, r10)
            if (r5 != 0) goto L28
            r5 = r2[r3]
            if (r5 > r12) goto L28
            int r5 = r5 + 1
            r2[r3] = r5
            int r10 = r10 + 1
            goto L15
        L28:
            if (r10 < 0) goto La9
            r5 = r2[r3]
            if (r5 <= r12) goto L30
            goto La9
        L30:
            r5 = 2
            if (r10 >= r1) goto L41
            boolean r6 = r0.b(r11, r10)
            if (r6 == 0) goto L41
            r6 = r2[r5]
            int r6 = r6 + r3
            r2[r5] = r6
            int r10 = r10 + 1
            goto L30
        L41:
            if (r10 != r1) goto L44
            return r4
        L44:
            r6 = 3
            if (r10 >= r1) goto L58
            boolean r7 = r0.b(r11, r10)
            if (r7 != 0) goto L58
            r7 = r2[r6]
            if (r7 >= r12) goto L58
            int r7 = r7 + 1
            r2[r6] = r7
            int r10 = r10 + 1
            goto L44
        L58:
            if (r10 == r1) goto La9
            r7 = r2[r6]
            if (r7 < r12) goto L5f
            goto La9
        L5f:
            r7 = 4
            if (r10 >= r1) goto L73
            boolean r8 = r0.b(r11, r10)
            if (r8 == 0) goto L73
            r8 = r2[r7]
            if (r8 >= r12) goto L73
            int r8 = r8 + 1
            r2[r7] = r8
            int r10 = r10 + 1
            goto L5f
        L73:
            r11 = r2[r7]
            if (r11 < r12) goto L78
            return r4
        L78:
            r12 = 0
            r0 = r2[r12]
            r1 = r2[r3]
            r7 = r2[r5]
            r6 = r2[r6]
            int r0 = r0 + r1
            int r0 = r0 + r7
            int r0 = r0 + r6
            int r0 = r0 + r11
            int r0 = r0 - r13
            int r11 = java.lang.Math.abs(r0)
            int r11 = r11 * 5
            int r13 = r13 * r5
            if (r11 < r13) goto L90
            return r4
        L90:
            boolean r11 = com.huawei.hms.scankit.p.r3.h
            if (r11 == 0) goto L9f
            boolean r11 = a(r2, r3)
            if (r11 == 0) goto L9e
            float r4 = a(r2, r10)
        L9e:
            return r4
        L9f:
            boolean r11 = a(r2, r12)
            if (r11 == 0) goto La9
            float r4 = a(r2, r10)
        La9:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.a(int, int, int, int):float");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002b, code lost:
    
        r11 = r19 * 1.5d;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0034, code lost:
    
        if (r6[1] <= r11) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0039, code lost:
    
        if (r7 < 0) goto L65;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x003f, code lost:
    
        if (r3.b(r5, r7) == false) goto L66;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0041, code lost:
    
        r10 = r6[0];
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0043, code lost:
    
        if (r10 > r19) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0045, code lost:
    
        r6[0] = r10 + 1;
        r7 = r7 - 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0050, code lost:
    
        if (r6[0] <= (r19 / 2)) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0053, code lost:
    
        r7 = r16 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0055, code lost:
    
        if (r7 >= r4) goto L67;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        if (r3.b(r5, r7) != false) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x005d, code lost:
    
        r10 = r6[1];
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x005f, code lost:
    
        if (r10 > r19) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0061, code lost:
    
        r6[1] = r10 + 1;
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
    
        if (r7 < 0) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x006f, code lost:
    
        if (r6[1] <= r11) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
    
        if (r7 >= r4) goto L70;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0079, code lost:
    
        if (r3.b(r5, r7) == false) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x007b, code lost:
    
        r6[2] = r6[2] + 1;
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0095, code lost:
    
        if ((java.lang.Math.abs(((r6[0] + r6[1]) + r6[2]) - r20) * 5) < (r20 * 2)) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009c, code lost:
    
        if (b(r6) == false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a6, code lost:
    
        return (r7 - (r6[1] / 2)) - r6[2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00a7, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a7, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a7, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00a7, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00a7, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private float a(int r16, int r17, int r18, int r19, int r20) {
        /*
            r15 = this;
            r0 = r18
            r1 = r19
            r2 = r15
            com.huawei.hms.scankit.p.s r3 = r2.f5795a
            int r4 = r3.c()
            r5 = r17
        Ld:
            if (r5 > r0) goto Lac
            int[] r6 = r15.d()
            r7 = r16
        L15:
            r8 = 1
            if (r7 < 0) goto L29
            boolean r9 = r3.b(r5, r7)
            if (r9 != 0) goto L29
            r9 = r6[r8]
            if (r9 > r1) goto L29
            int r9 = r9 + 1
            r6[r8] = r9
            int r7 = r7 + (-1)
            goto L15
        L29:
            if (r7 < 0) goto La7
            r9 = r6[r8]
            double r9 = (double) r9
            double r11 = (double) r1
            r13 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            double r11 = r11 * r13
            int r9 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r9 <= 0) goto L38
            goto La7
        L38:
            r9 = 0
            if (r7 < 0) goto L4c
            boolean r10 = r3.b(r5, r7)
            if (r10 == 0) goto L4c
            r10 = r6[r9]
            if (r10 > r1) goto L4c
            int r10 = r10 + 1
            r6[r9] = r10
            int r7 = r7 + (-1)
            goto L38
        L4c:
            r7 = r6[r9]
            int r10 = r1 / 2
            if (r7 <= r10) goto L53
            goto La7
        L53:
            int r7 = r16 + 1
        L55:
            if (r7 >= r4) goto L68
            boolean r10 = r3.b(r5, r7)
            if (r10 != 0) goto L68
            r10 = r6[r8]
            if (r10 > r1) goto L68
            int r10 = r10 + 1
            r6[r8] = r10
            int r7 = r7 + 1
            goto L55
        L68:
            if (r7 < 0) goto La7
            r10 = r6[r8]
            double r13 = (double) r10
            int r10 = (r13 > r11 ? 1 : (r13 == r11 ? 0 : -1))
            if (r10 <= 0) goto L72
            goto La7
        L72:
            r10 = 2
            if (r7 >= r4) goto L83
            boolean r11 = r3.b(r5, r7)
            if (r11 == 0) goto L83
            r11 = r6[r10]
            int r11 = r11 + r8
            r6[r10] = r11
            int r7 = r7 + 1
            goto L72
        L83:
            r9 = r6[r9]
            r11 = r6[r8]
            r12 = r6[r10]
            int r9 = r9 + r11
            int r9 = r9 + r12
            int r9 = r9 - r20
            int r9 = java.lang.Math.abs(r9)
            int r9 = r9 * 5
            int r11 = r20 * 2
            if (r9 < r11) goto L98
            goto La7
        L98:
            boolean r9 = b(r6)
            if (r9 == 0) goto La7
            r0 = r6[r8]
            int r0 = r0 / r10
            int r7 = r7 - r0
            r0 = r6[r10]
            int r7 = r7 - r0
            float r0 = (float) r7
            return r0
        La7:
            int r6 = r0 - r17
            int r5 = r5 + r6
            goto Ld
        Lac:
            r0 = 2143289344(0x7fc00000, float:NaN)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.scankit.p.i3.a(int, int, int, int, int):float");
    }

    protected final boolean a(int[] iArr, int i2, int i3) {
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        float a2 = a(iArr, i3);
        float b2 = b(i2, (int) a2, iArr[2], i4, false);
        if (Float.isNaN(b2)) {
            int i5 = iArr[2];
            float f2 = i5 / 2;
            int i6 = iArr[1];
            b2 = a(i2, (int) ((a2 - f2) - (i6 / 2)), (int) (f2 + a2 + (r13 / 2)), i6 + i5 + iArr[3], i4);
        }
        if (Float.isNaN(b2)) {
            return false;
        }
        return a(false, b2, a2, i4 / 7.0f);
    }

    private boolean a(boolean z, float f2, float f3, float f4) {
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            e3 e3Var = this.c.get(i2);
            if (e3Var.b(f4, f2, f3)) {
                this.c.set(i2, e3Var.a(f2, f3, f4, false));
                return true;
            }
        }
        if (z) {
            return true;
        }
        e3 e3Var2 = new e3(f3, f2, f4, false);
        this.c.add(e3Var2);
        v6 v6Var = this.e;
        if (v6Var == null) {
            return true;
        }
        v6Var.a(e3Var2);
        return true;
    }

    private e3[] a() throws com.huawei.hms.scankit.p.a {
        e3 e3Var = this.b.get(0);
        e3 e3Var2 = this.b.get(1);
        float[] fArr = e3Var.b() < e3Var2.b() ? new float[]{e3Var.b(), e3Var2.b()} : new float[]{e3Var2.b(), e3Var.b()};
        float[] fArr2 = e3Var.b() < e3Var2.b() ? new float[]{e3Var.c(), e3Var2.c()} : new float[]{e3Var2.c(), e3Var.c()};
        float e2 = (e3Var.e() + e3Var2.e()) / 2.0f;
        float e3 = ((e3Var.e() + e3Var2.e()) * 7.0f) / 1.5f;
        if (Math.abs(fArr[0] - fArr[1]) > e3 && Math.abs(fArr2[0] - fArr2[1]) <= e3) {
            float f2 = fArr[0];
            float f3 = fArr2[0];
            this.b.add(new e3((f2 + f3) - fArr2[1], (f3 + fArr[1]) - f2, e2, false, 0));
        } else if (Math.abs(fArr[0] - fArr[1]) <= e3 && Math.abs(fArr2[0] - fArr2[1]) > e3) {
            float f4 = fArr[0];
            float f5 = fArr[1];
            if (f4 < f5) {
                float f6 = fArr2[0];
                float f7 = fArr2[1];
                this.b.add(new e3((f6 + f5) - f7, (f7 + f5) - f4, e2, false, 0));
            } else {
                float f8 = fArr2[1];
                float f9 = fArr2[0];
                this.b.add(new e3((f8 + f4) - f9, (f9 + f4) - f5, e2, false, 0));
            }
        } else if (Math.abs(fArr[0] - fArr[1]) > e3 && Math.abs(fArr2[0] - fArr2[1]) > e3) {
            float f10 = fArr[0];
            float f11 = fArr[1];
            float f12 = fArr2[1];
            float f13 = fArr2[0];
            this.b.add(new e3((((f10 + f11) + f12) - f13) / 2.0f, (((f13 + f12) + f10) - f11) / 2.0f, e2, false, 0));
        }
        if (this.b.size() == 3) {
            return new e3[]{this.b.get(0), this.b.get(1), this.b.get(2)};
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    static final class c implements Comparator<e3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            return Integer.compare(e3Var2.a(), e3Var.a());
        }

        private c() {
        }
    }

    static final class d implements Comparator<e3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            return Float.compare(e3Var.e(), e3Var2.e());
        }

        private d() {
        }
    }

    static final class f implements Comparator<e3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            return Float.compare(e3Var.b(), e3Var2.b());
        }

        private f() {
        }
    }

    static final class g implements Comparator<e3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(e3 e3Var, e3 e3Var2) {
            return Float.compare(e3Var.c(), e3Var2.c());
        }

        private g() {
        }
    }
}
