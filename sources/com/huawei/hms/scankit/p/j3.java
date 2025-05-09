package com.huawei.hms.scankit.p;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class j3 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5805a;
    private final List<f3> b = new ArrayList();
    private final int[] c = new int[5];
    private final v6 d;

    static final class c implements Comparator<f3>, Serializable {

        /* renamed from: a, reason: collision with root package name */
        private final float f5806a;

        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f3 f3Var, f3 f3Var2) {
            return Float.compare(Math.abs(f3Var2.e() - this.f5806a), Math.abs(f3Var.e() - this.f5806a));
        }

        private c(float f) {
            this.f5806a = f;
        }
    }

    public j3(s sVar, v6 v6Var) {
        this.f5805a = sVar;
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
        if (i < 5) {
            return false;
        }
        float f = i / 5.0f;
        float f2 = f / 1.6f;
        float f3 = f * 0.8f;
        float f4 = 0.8f * f2;
        if (Math.abs(f3 - iArr[0]) >= f4) {
            return false;
        }
        float f5 = f * 1.2f;
        float f6 = 1.2f * f2;
        return Math.abs(f5 - ((float) iArr[1])) < f6 && Math.abs(f - ((float) iArr[2])) < f2 && Math.abs(f5 - ((float) iArr[3])) < f6 && Math.abs(f3 - ((float) iArr[4])) < f4;
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
        if (i < 5) {
            return false;
        }
        float f = i / 5.0f;
        float f2 = f / 1.0f;
        float f3 = f * 0.8f;
        float f4 = 0.8f * f2;
        if (Math.abs(f3 - iArr[0]) >= f4) {
            return false;
        }
        float f5 = f * 1.2f;
        float f6 = 1.2f * f2;
        return Math.abs(f5 - ((float) iArr[1])) < f6 && Math.abs(f - ((float) iArr[2])) < f2 && Math.abs(f5 - ((float) iArr[3])) < f6 && Math.abs(f3 - ((float) iArr[4])) < f4;
    }

    private void d(int[] iArr) {
        iArr[0] = iArr[2];
        iArr[1] = iArr[3];
        iArr[2] = iArr[4];
        iArr[3] = 1;
        iArr[4] = 0;
    }

    final f3[] a(Map<l1, ?> map) throws com.huawei.hms.scankit.p.a {
        int c2 = this.f5805a.c();
        int e = this.f5805a.e();
        int[] iArr = new int[5];
        for (int i = 1; i < c2; i += 2) {
            a(iArr);
            int i2 = 0;
            for (int i3 = 0; i3 < e; i3++) {
                if (this.f5805a.b(i3, i)) {
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

    private float d(int i, int i2, int i3) {
        s sVar = this.f5805a;
        int c2 = sVar.c();
        int[] a2 = a();
        int i4 = i;
        while (i4 >= 0 && sVar.b(i2, i4)) {
            a2[2] = a2[2] + 1;
            i4--;
        }
        if (i4 < 0) {
            return Float.NaN;
        }
        while (i4 >= 0 && !sVar.b(i2, i4)) {
            a2[1] = a2[1] + 1;
            i4--;
        }
        if (i4 < 0 || a2[1] == 0) {
            return Float.NaN;
        }
        while (i4 >= 0 && sVar.b(i2, i4)) {
            a2[0] = a2[0] + 1;
            i4--;
        }
        if (a2[0] == 0) {
            return Float.NaN;
        }
        int i5 = i + 1;
        while (i5 < c2 && sVar.b(i2, i5)) {
            a2[2] = a2[2] + 1;
            i5++;
        }
        if (i5 == c2 || a2[2] == 0) {
            return Float.NaN;
        }
        while (i5 < c2 && !sVar.b(i2, i5)) {
            a2[3] = a2[3] + 1;
            i5++;
        }
        if (i5 == c2 || a2[3] == 0) {
            return Float.NaN;
        }
        while (i5 < c2 && sVar.b(i2, i5)) {
            a2[4] = a2[4] + 1;
            i5++;
        }
        if (a2[4] == 0) {
            return Float.NaN;
        }
        int i6 = a2[0];
        int i7 = a2[1];
        if (Math.abs(((((i6 + i7) + a2[2]) + a2[3]) + r12) - i3) < i3 * 0.4f && b(a2)) {
            return a(a2, i5);
        }
        return Float.NaN;
    }

    private boolean b(int i, int i2, int i3) {
        int i4;
        int i5;
        int[] a2 = a();
        int c2 = this.f5805a.c();
        int e = this.f5805a.e();
        int i6 = 0;
        while (true) {
            int i7 = i + i6;
            if (i7 >= c2 || i2 < i6 || !this.f5805a.b(i2 - i6, i7)) {
                break;
            }
            a2[2] = a2[2] + 1;
            i6++;
        }
        if (a2[2] == 0) {
            return false;
        }
        while (true) {
            int i8 = i + i6;
            if (i8 >= c2 || i2 < i6 || this.f5805a.b(i2 - i6, i8)) {
                break;
            }
            a2[1] = a2[1] + 1;
            i6++;
        }
        if (a2[1] == 0) {
            return false;
        }
        while (true) {
            int i9 = i + i6;
            if (i9 >= c2 || i2 < i6 || !this.f5805a.b(i2 - i6, i9)) {
                break;
            }
            a2[0] = a2[0] + 1;
            i6++;
        }
        if (a2[0] == 0) {
            return false;
        }
        int i10 = 1;
        while (i >= i10) {
            int i11 = i2 + i10;
            if (i11 >= e || !this.f5805a.b(i11, i - i10)) {
                break;
            }
            a2[2] = a2[2] + 1;
            i10++;
        }
        while (i >= i10 && (i5 = i2 + i10) < e && !this.f5805a.b(i5, i - i10)) {
            a2[3] = a2[3] + 1;
            i10++;
        }
        if (a2[3] == 0) {
            return false;
        }
        while (i >= i10 && (i4 = i2 + i10) < e && this.f5805a.b(i4, i - i10)) {
            a2[4] = a2[4] + 1;
            i10++;
        }
        if (a2[4] == 0) {
            return false;
        }
        if (Math.abs(((((a2[0] + a2[1]) + a2[2]) + a2[3]) + r12) - i3) >= i3 * 0.5f) {
            return false;
        }
        return c(a2);
    }

    private float c(int i, int i2, int i3) {
        s sVar = this.f5805a;
        int e = sVar.e();
        int[] a2 = a();
        int i4 = i;
        while (i4 >= 0 && sVar.b(i4, i2)) {
            a2[2] = a2[2] + 1;
            i4--;
        }
        if (i4 < 0) {
            return Float.NaN;
        }
        while (i4 >= 0 && !sVar.b(i4, i2)) {
            a2[1] = a2[1] + 1;
            i4--;
        }
        if (i4 < 0 || a2[1] == 0) {
            return Float.NaN;
        }
        while (i4 >= 0 && sVar.b(i4, i2)) {
            a2[0] = a2[0] + 1;
            i4--;
        }
        if (a2[0] == 0) {
            return Float.NaN;
        }
        int i5 = i + 1;
        while (i5 < e && sVar.b(i5, i2)) {
            a2[2] = a2[2] + 1;
            i5++;
        }
        if (i5 == e || a2[2] == 0) {
            return Float.NaN;
        }
        while (i5 < e && !sVar.b(i5, i2)) {
            a2[3] = a2[3] + 1;
            i5++;
        }
        if (i5 == e || a2[3] == 0) {
            return Float.NaN;
        }
        while (i5 < e && sVar.b(i5, i2)) {
            a2[4] = a2[4] + 1;
            i5++;
        }
        if (a2[4] == 0) {
            return Float.NaN;
        }
        int i6 = a2[0];
        int i7 = a2[1];
        if (Math.abs(((((i6 + i7) + a2[2]) + a2[3]) + r12) - i3) < i3 * 0.2f && b(a2)) {
            return a(a2, i5);
        }
        return Float.NaN;
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

    private boolean a(int i, int i2, int i3) {
        int i4;
        int i5;
        int i6;
        int[] a2 = a();
        int i7 = 0;
        while (i >= i7 && i2 >= i7 && this.f5805a.b(i2 - i7, i - i7)) {
            a2[2] = a2[2] + 1;
            i7++;
        }
        if (a2[2] == 0) {
            return false;
        }
        while (i >= i7 && i2 >= i7 && !this.f5805a.b(i2 - i7, i - i7)) {
            a2[1] = a2[1] + 1;
            i7++;
        }
        if (a2[1] == 0) {
            return false;
        }
        while (i >= i7 && i2 >= i7 && this.f5805a.b(i2 - i7, i - i7)) {
            a2[0] = a2[0] + 1;
            i7++;
        }
        if (a2[0] == 0) {
            return false;
        }
        int c2 = this.f5805a.c();
        int e = this.f5805a.e();
        int i8 = 1;
        while (true) {
            int i9 = i + i8;
            if (i9 >= c2 || (i6 = i2 + i8) >= e || !this.f5805a.b(i6, i9)) {
                break;
            }
            a2[2] = a2[2] + 1;
            i8++;
        }
        while (true) {
            int i10 = i + i8;
            if (i10 >= c2 || (i5 = i2 + i8) >= e || this.f5805a.b(i5, i10)) {
                break;
            }
            a2[3] = a2[3] + 1;
            i8++;
        }
        if (a2[3] == 0) {
            return false;
        }
        while (true) {
            int i11 = i + i8;
            if (i11 >= c2 || (i4 = i2 + i8) >= e || !this.f5805a.b(i4, i11)) {
                break;
            }
            a2[4] = a2[4] + 1;
            i8++;
        }
        if (a2[4] == 0) {
            return false;
        }
        if (Math.abs(((((a2[0] + a2[1]) + a2[2]) + a2[3]) + r13) - i3) >= i3 * 0.5f) {
            return false;
        }
        return c(a2);
    }

    private f3[] b() throws com.huawei.hms.scankit.p.a {
        int i = 0;
        int i2 = 0;
        while (i2 < this.b.size()) {
            f3 f3Var = this.b.get(i2);
            if (f3Var.e() <= 5.0f || f3Var.a() <= 2) {
                this.b.remove(i2);
                i2--;
            }
            i2++;
        }
        int size = this.b.size();
        if (size >= 3) {
            Iterator<f3> it = this.b.iterator();
            float f = 0.0f;
            float f2 = 0.0f;
            float f3 = 0.0f;
            while (it.hasNext()) {
                float e = it.next().e();
                f2 += e;
                f3 += e * e;
            }
            float f4 = f2 / size;
            float sqrt = (float) Math.sqrt((f3 / r1) - (f4 * f4));
            Collections.sort(this.b, new c(f4));
            float max = Math.max(0.36f * f4, sqrt);
            int i3 = 0;
            while (i3 < this.b.size() && this.b.size() > 3) {
                if (Math.abs(this.b.get(i3).e() - f4) > max) {
                    this.b.remove(i3);
                    i3--;
                }
                i3++;
            }
            int size2 = this.b.size();
            while (this.b.iterator().hasNext()) {
                f += r3.next().a();
            }
            float f5 = f / size2;
            while (i < this.b.size() && this.b.size() > 3) {
                if (this.b.get(i).a() <= 0.5f * f5) {
                    this.b.remove(i);
                    i--;
                }
                i++;
            }
            Collections.sort(this.b, new b());
            List<f3> list = this.b;
            return (f3[]) list.toArray(new f3[list.size()]);
        }
        throw com.huawei.hms.scankit.p.a.a();
    }

    protected final boolean a(int[] iArr, int i, int i2) {
        int i3 = 0;
        int i4 = iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4];
        int a2 = (int) a(iArr, i2);
        float d = d(i, a2, i4);
        if (!Float.isNaN(d)) {
            int i5 = (int) d;
            float c2 = c(a2, i5, i4);
            if (!Float.isNaN(c2)) {
                int i6 = (int) c2;
                if (a(i5, i6, i4) && b(i5, i6, i4)) {
                    float f = i4 / 5.0f;
                    while (true) {
                        if (i3 < this.b.size()) {
                            f3 f3Var = this.b.get(i3);
                            if (f3Var.b(f, d, c2)) {
                                this.b.set(i3, f3Var.c(d, c2, f));
                                break;
                            }
                            i3++;
                        } else {
                            f3 f3Var2 = new f3(c2, d, f);
                            this.b.add(f3Var2);
                            v6 v6Var = this.d;
                            if (v6Var != null) {
                                v6Var.a(f3Var2);
                            }
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    static final class b implements Comparator<f3>, Serializable {
        @Override // java.util.Comparator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(f3 f3Var, f3 f3Var2) {
            return Float.compare(f3Var2.e(), f3Var.e());
        }

        private b() {
        }
    }
}
