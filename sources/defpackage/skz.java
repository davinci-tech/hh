package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import com.huawei.uikit.hwcolumnsystem.widget.bqmxo;

/* loaded from: classes7.dex */
public class skz extends bqmxo {
    private Context cc;
    private static final int[] ba = {4, 8, 12};
    private static final int[] bd = {24, 24, 24};
    private static final int[] be = {24, 24, 24};
    private static final int[] bh = {4, 6, 8};
    private static final int[] bl = {4, 6, 8};
    private static final int[] bi = {24, 24, 24};
    private static final int[] bn = {24, 24, 24};
    private static final int[] bo = {2, 3, 4};
    private static final int[] bp = {4, 6, 8};
    private static final int[] bt = {12, 12, 12};
    private static final int[] bu = {12, 12, 12};
    private static final int[] cb = {2, 3, 4};
    private static final int[] by = {4, 6, 8};
    private static final int[] bx = {12, 12, 12};
    private static final int[] e = {12, 12, 12};
    private static final int[] g = {4, 6, 8};
    private static final int[] i = {4, 6, 8};
    private static final int[] k = {24, 24, 24};
    private static final int[] l = {24, 24, 24};
    private static final int[] m = {4, 6, 6};
    private static final int[] t = {4, 6, 6};
    private static final int[] q = {24, 24, 24};
    private static final int[] r = {24, 24, 24};
    private static final int[] y = {4, 6, 8};
    private static final int[] w = {4, 6, 8};
    private static final int[] x = {12, 12, 12};
    private static final int[] v = {12, 12, 12};
    private static final int[] aa = {4, 6, 8};
    private static final int[] z = {4, 6, 8};
    private static final int[] ah = {24, 24, 24};
    private static final int[] af = {24, 24, 24};
    private static final int[] ag = {2, 2, 2};
    private static final int[] ai = {4, 6, 6};
    private static final int[] ae = {24, 24, 24};
    private static final int[] al = {24, 24, 24};
    private static final int[] an = {2, 2, 2};
    private static final int[] ak = {4, 6, 8};
    private static final int[] aj = {24, 24, 24};
    private static final int[] am = {24, 24, 24};
    private static final int[] ap = {4, 6, 10};
    private static final int[] as = {4, 6, 10};
    private static final int[] ar = {24, 24, 24};
    private static final int[] ao = {24, 24, 24};
    private static final int[] aq = {-2, 8, 12};
    private static final int[] at = {-2, 8, 12};
    private static final int[] aw = {24, 24, 24};
    private static final int[] ax = {24, 24, 24};
    private static final int[] av = {4, 6, 6};
    private static final int[] au = {4, 6, 6};
    private static final int[] az = {24, 24, 24};
    private static final int[] bb = {24, 24, 24};
    private static final int[] bc = {-2, 8, 8};
    private static final int[] ay = {-2, 8, 8};
    private static final int[] bg = {12, 12, 12};
    private static final int[] bf = {12, 12, 12};
    private static final int[] bj = {3, 4, 5};
    private static final int[] bk = {3, 4, 5};
    private static final int[] bm = {12, 12, 12};
    private static final int[] br = {12, 12, 12};
    private static final int[] bq = {4, 5, 6};
    private static final int[] bs = {4, 5, 6};
    private static final int[] bv = {12, 12, 12};
    private static final int[] bw = {12, 12, 12};
    private static final int[] bz = {2, 3, 4};
    private static final int[] ca = {2, 3, 4};
    private static final int[] cf = {12, 12, 12};
    private static final int[] d = {12, 12, 12};
    private static final int[] h = {3, 4, 5};
    private static final int[] j = {3, 4, 5};
    private static final int[] o = {12, 12, 12};
    private static final int[] n = {12, 12, 12};
    private static final int[] p = {-2, 6, 6};
    private static final int[] s = {-2, 6, 6};
    private static final int[][] u = {new int[]{4, 6, 8}, new int[]{2, 3, 4}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 6}, new int[]{2, 2, 2}, new int[]{4, 6, 6}, new int[]{-2, 8, 8}, new int[]{4, 6, 10}, new int[]{-2, 8, 12}, new int[]{2, 2, 2}, new int[]{4, 6, 8}, new int[]{4, 4, 5}, new int[]{3, 4, 5}, new int[]{4, 5, 6}, new int[]{2, 3, 4}, new int[]{3, 4, 5}, new int[]{2, 3, 4}, new int[]{4, 6, 8}, new int[]{-2, 6, 6}};
    private static final int[][] ad = {new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 6}, new int[]{4, 6, 6}, new int[]{4, 6, 6}, new int[]{-2, 8, 8}, new int[]{4, 6, 10}, new int[]{-2, 8, 12}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 4, 5}, new int[]{3, 4, 5}, new int[]{4, 5, 6}, new int[]{2, 3, 4}, new int[]{3, 4, 5}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{-2, 6, 6}};
    private static final int[][] ac = {new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{12, 12, 12}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{16, 16, 16}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}};
    private static final int[][] ab = {new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{12, 12, 12}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{24, 24, 24}, new int[]{16, 16, 16}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}, new int[]{12, 12, 12}};

    public skz(Context context) {
        super(context);
        this.cc = context;
        this.f10622a = 520;
        this.b = 840;
    }

    private int b(int i2, int i3, int i4, int i5, int i6) {
        if (i2 != 4) {
            if (i2 != 8) {
                if (i2 != 12) {
                    return 12;
                }
                if (i3 * 3 > i4 * 4) {
                    return i5;
                }
            } else if (i3 * 4 > i4 * 3) {
                return i5;
            }
        } else if (i3 * 16 > i4 * 9) {
            return i5;
        }
        return i6;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public Pair<Integer, String> a(int i2, String[] strArr, float f) {
        String str;
        int i3;
        if (i2 >= a(840, f)) {
            str = strArr[2];
            i3 = 12;
        } else if (i2 >= a(520, f)) {
            str = strArr[1];
            i3 = 8;
        } else {
            str = strArr[0];
            i3 = 4;
        }
        return new Pair<>(Integer.valueOf(i3), str);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf b() {
        int a2 = a(k[this.c]);
        int a3 = a(l[this.c]);
        int[] iArr = m;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], t[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf c() {
        int a2 = a(bi[this.c]);
        int a3 = a(bn[this.c]);
        int[] iArr = bo;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], bp[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf d() {
        int a2 = a(bt[this.c]);
        int a3 = a(bu[this.c]);
        int[] iArr = cb;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], by[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf e() {
        int a2 = a(bx[this.c]);
        int a3 = a(e[this.c]);
        int[] iArr = g;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], i[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf f() {
        int a2 = a(x[this.c]);
        int a3 = a(v[this.c]);
        int[] iArr = aa;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], z[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf g() {
        int a2 = a(bd[this.c]);
        int a3 = a(be[this.c]);
        int[] iArr = bh;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], bl[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf h() {
        int a2 = a(q[this.c]);
        int a3 = a(r[this.c]);
        int[] iArr = y;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], w[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf i() {
        int a2 = a(ar[this.c]);
        int a3 = a(ao[this.c]);
        int[] iArr = aq;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], at[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf j() {
        int a2 = a(az[this.c]);
        int a3 = a(bb[this.c]);
        int[] iArr = bc;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], ay[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf k() {
        int a2 = a(ae[this.c]);
        int a3 = a(al[this.c]);
        int[] iArr = an;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], ak[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf l() {
        int a2 = a(aj[this.c]);
        int a3 = a(am[this.c]);
        int[] iArr = ap;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], as[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf m() {
        int a2 = a(aw[this.c]);
        int a3 = a(ax[this.c]);
        int[] iArr = av;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], au[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf n() {
        int a2 = a(ah[this.c]);
        int a3 = a(af[this.c]);
        int[] iArr = ag;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], ai[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public int o() {
        return ba[this.c];
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public int b(int i2, int i3, int i4) {
        if (Double.compare(c(i2, i3), 12.0d) < 0) {
            return b(i4, i2, i3, 13, 14);
        }
        return b(i4, i2, i3, 15, 16);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf c(int i2, int i3, int i4) {
        return a(i2, i3, i4);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf d(int i2, int i3, int i4) {
        return a(i2, i3, i4);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public boolean b(int i2) {
        return i2 >= 0 && i2 < ac.length;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public Pair<Integer, Integer> a(float f) {
        int i2;
        int i3;
        if (f > 840.0f || a(f, 840.0f)) {
            i2 = 12;
            i3 = 2;
        } else if (f > 520.0f || a(f, 520.0f)) {
            i2 = 8;
            i3 = 1;
        } else {
            i2 = 4;
            i3 = 0;
        }
        return new Pair<>(Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf a(int i2, int i3, int i4) {
        int a2;
        int a3;
        int i5;
        int i6;
        switch (b(i3, i4, i2)) {
            case 13:
                a2 = a(bg[this.c]);
                a3 = a(bf[this.c]);
                int[] iArr = bj;
                int i7 = this.c;
                i5 = iArr[i7];
                i6 = bk[i7];
                break;
            case 14:
                a2 = a(bm[this.c]);
                a3 = a(br[this.c]);
                int[] iArr2 = bq;
                int i8 = this.c;
                i5 = iArr2[i8];
                i6 = bs[i8];
                break;
            case 15:
                a2 = a(bv[this.c]);
                a3 = a(bw[this.c]);
                int[] iArr3 = bz;
                int i9 = this.c;
                i5 = iArr3[i9];
                i6 = ca[i9];
                break;
            case 16:
                a2 = a(cf[this.c]);
                a3 = a(d[this.c]);
                int[] iArr4 = h;
                int i10 = this.c;
                i5 = iArr4[i10];
                i6 = j[i10];
                break;
            default:
                a2 = 0;
                a3 = 0;
                i5 = 0;
                i6 = 0;
                break;
        }
        return new slf(a2, a3, i5, i6);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf a() {
        int a2 = a(o[this.c]);
        int a3 = a(n[this.c]);
        int[] iArr = p;
        int i2 = this.c;
        return new slf(a2, a3, iArr[i2], s[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf a(int i2, Pair<Integer, Integer> pair, int i3, int i4, float f) {
        if (i3 == 20 || i3 == 21) {
            i3 = 12;
        }
        if (i3 >= 12 && i3 <= 16) {
            i3 = b(((Integer) pair.first).intValue(), ((Integer) pair.second).intValue(), i2);
        }
        return new slf(a(ac[i3][i4], f), a(ab[i3][i4], f), u[i3][i4], ad[i3][i4]);
    }

    private double c(int i2, int i3) {
        float f;
        DisplayMetrics displayMetrics = this.cc.getResources().getDisplayMetrics();
        float f2 = displayMetrics.xdpi;
        float f3 = 0.0f;
        if (f2 != 0.0f) {
            float f4 = displayMetrics.ydpi;
            if (f4 != 0.0f) {
                f3 = i2 / f2;
                f = i3 / f4;
                return Math.sqrt((f3 * f3) + (f * f));
            }
        }
        Log.w("HwColumnDelegate", "displayMetrics.xdpi or displayMetrics.ydpi get failed.");
        f = 0.0f;
        return Math.sqrt((f3 * f3) + (f * f));
    }
}
