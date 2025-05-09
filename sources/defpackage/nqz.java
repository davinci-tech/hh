package defpackage;

import android.graphics.Rect;
import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public class nqz {
    private int b;
    private long c;
    private int[] d;
    private long e;
    private int f;
    private int[] g;
    private nrb h;
    private int o;
    private int j = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f15450a = 0;
    private int n = 0;
    private boolean i = false;
    private nqy m = new nqy();
    private Rect l = new Rect();

    private int d(int i, int i2, int i3) {
        return (i >= i2 && i2 + (-1) < i3) ? i3 + (i - i2) : i;
    }

    public nqz(nrb nrbVar) {
        this.h = nrbVar;
    }

    public void b() {
        this.g = new int[0];
        this.d = new int[0];
        this.e = 0L;
        this.c = 0L;
        this.b = 0;
        this.f = 0;
        this.o = 0;
        this.i = false;
    }

    public void d(int i, int i2, int i3, int i4, int i5) {
        this.g = new int[i];
        this.d = new int[i2];
        this.f15450a = i3;
        this.n = i4;
        this.j = i5;
        this.i = true;
    }

    void d() {
        if (!this.i) {
            throw new IllegalStateException("You need to init matrix before work!");
        }
    }

    public void o() {
        d();
        this.e = 0L;
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i2 >= this.d.length) {
                break;
            }
            long j = this.e + r4[i2];
            this.e = j;
            if (i2 == this.j - 1) {
                this.f = (int) j;
            }
            i2++;
        }
        this.c = 0L;
        this.o = 0;
        while (true) {
            int[] iArr = this.g;
            if (i >= iArr.length) {
                return;
            }
            long j2 = this.c;
            int i3 = iArr[i];
            long j3 = j2 + i3;
            this.c = j3;
            if (i == this.f15450a - 1) {
                this.b = (int) j3;
            } else if (i >= iArr.length - this.n) {
                this.o += i3;
            }
            i++;
        }
    }

    public int a(int i) {
        d();
        if (i >= 0) {
            int[] iArr = this.d;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return 0;
    }

    public void c(int i, int i2) {
        d();
        if (i >= 0) {
            int[] iArr = this.d;
            if (i < iArr.length) {
                iArr[i] = i2;
            }
        }
    }

    public int d(int i, int i2) {
        d();
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            int[] iArr = this.d;
            if (iArr == null) {
                break;
            }
            i3 += iArr[i4];
        }
        return i3 + ((i2 - i) * this.h.b());
    }

    public int a() {
        int[] iArr = this.d;
        if (iArr != null) {
            return iArr.length;
        }
        return 0;
    }

    public int d(int i) {
        d();
        if (i >= 0) {
            int[] iArr = this.g;
            if (i < iArr.length) {
                return iArr[i];
            }
        }
        return 0;
    }

    public void a(int i, int i2) {
        d();
        if (i >= 0) {
            int[] iArr = this.g;
            if (i < iArr.length) {
                iArr[i] = i2;
            }
        }
    }

    public int e(int i, int i2) {
        d();
        int i3 = 0;
        for (int i4 = i; i4 < i2; i4++) {
            int[] iArr = this.g;
            if (iArr == null) {
                break;
            }
            i3 += iArr[i4];
        }
        return i3 + ((i2 - i) * this.h.a());
    }

    public int j() {
        int[] iArr = this.g;
        if (iArr != null) {
            return iArr.length;
        }
        return 0;
    }

    public int n() {
        return this.n;
    }

    public long i() {
        d();
        return this.e + ((this.h.k() ? a() + 1 : a() - 1) * this.h.b());
    }

    public long c() {
        d();
        return this.c + ((this.h.l() ? j() + 1 : j() - 1) * this.h.a());
    }

    public int g() {
        return Math.max(0, this.f + ((this.h.k() ? this.j : this.j - 1) * this.h.b()));
    }

    public int e() {
        return Math.max(0, this.b + ((this.h.l() ? this.f15450a : this.f15450a - 1) * this.h.a()));
    }

    public int f() {
        return Math.max(0, this.o + ((this.h.l() ? this.n : this.n - 1) * this.h.a()));
    }

    public int e(int i) {
        d();
        int b = i - ((this.h.k() ? 1 : 0) * this.h.b());
        int i2 = 0;
        if (b <= 0) {
            return 0;
        }
        int length = this.d.length;
        int i3 = 0;
        while (i2 < length) {
            int b2 = this.d[i2] + i3 + this.h.b();
            if (b > i3 && b < b2) {
                return i2;
            }
            if (b < b2) {
                return i2 - 1;
            }
            i2++;
            i3 = b2;
        }
        return this.d.length - 1;
    }

    public int b(int i) {
        d();
        int a2 = i - ((this.h.l() ? 1 : 0) * this.h.a());
        int i2 = 0;
        if (a2 <= 0) {
            return 0;
        }
        int length = this.g.length;
        int i3 = 0;
        while (i2 < length) {
            int a3 = this.g[i2] + i3 + this.h.a();
            if (a2 > i3 && a2 < a3) {
                return i2;
            }
            if (a2 < a3) {
                return i2 - 1;
            }
            i2++;
            i3 = a3;
        }
        return this.g.length - 1;
    }

    public a[][] cGC_(Rect rect) {
        int i;
        int i2 = this.h.j() ? this.f15450a : 0;
        int i3 = this.h.n() ? this.n : 0;
        int i4 = this.h.o() ? this.j : 0;
        int e = e(rect.left);
        int e2 = e(rect.right);
        int b = b(rect.top);
        int b2 = b(rect.bottom);
        if (e > e2) {
            i = e2;
        } else {
            i = e;
            e = e2;
        }
        int i5 = (e - i) + 1;
        int i6 = i4 + (-1) < i ? i5 + i4 : i5 + i;
        int j = (b2 - b) + 1 + (i2 + (-1) < b ? i2 : b) + (i3 < (j() + (-1)) - b2 ? i3 : (j() - 1) - b2);
        a[][] aVarArr = (a[][]) Array.newInstance((Class<?>) a.class, j, i6);
        int i7 = 0;
        while (i7 < j) {
            int i8 = i7;
            int e3 = e(i7, j, b, i2, i3);
            for (int i9 = 0; i9 < i6; i9++) {
                int d = d(i9, i4, i);
                aVarArr[i8][i9] = new a(e3, d, b(e3, d));
            }
            i7 = i8 + 1;
        }
        return aVarArr;
    }

    private int e(int i, int i2, int i3, int i4, int i5) {
        if (i < i4) {
            return i;
        }
        if (i > (i2 - i5) - 1) {
            return j() - (i2 - i);
        }
        return i4 + (-1) < i3 ? i3 + (i - i4) : i;
    }

    int b(int i, int i2) {
        int i3;
        if (i < this.j) {
            i3 = 2;
        } else {
            i3 = i > (j() - 1) - this.n ? 4 : 0;
        }
        return i3 | (i2 < this.f15450a ? 1 : 0);
    }

    public nqy h() {
        return this.m;
    }

    public Rect cGB_(boolean z) {
        if (z) {
            this.l.set(((int) Math.max(i(), this.h.c())) - this.m.a(), this.m.e(), ((int) Math.max(i(), this.h.c())) - (this.m.a() + this.h.c()), this.m.e() + this.h.e());
        } else {
            this.l.set(this.m.a(), this.m.e(), this.m.a() + this.h.c(), this.m.e() + this.h.e());
        }
        return this.l;
    }

    public static class a {
        private int b;
        private int d;
        private int e;

        a(int i, int i2, int i3) {
            this.e = i;
            this.b = i2;
            this.d = i3;
        }

        public int c() {
            return this.b;
        }

        public int b() {
            return this.e;
        }

        public int d() {
            return this.d;
        }
    }
}
