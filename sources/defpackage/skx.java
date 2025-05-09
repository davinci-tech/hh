package defpackage;

import android.content.Context;
import android.util.Pair;
import com.huawei.uikit.hwcolumnsystem.widget.bqmxo;

/* loaded from: classes7.dex */
public class skx extends bqmxo {
    private int ar;
    private int as;
    private static final int[] ah = {4, 8, 12};
    private static final int[] af = {4, 6, 8};
    private static final int[] ae = {4, 6, 8};
    private static final int[] ag = {2, 3, 4};
    private static final int[] ai = {4, 6, 8};
    private static final int[] an = {2, 3, 4};
    private static final int[] al = {4, 6, 8};
    private static final int[] ak = {4, 6, 8};
    private static final int[] am = {4, 6, 8};
    private static final int[] aj = {4, 6, 6};
    private static final int[] aq = {4, 6, 6};
    private static final int[] d = {4, 6, 8};
    private static final int[] e = {4, 6, 8};
    private static final int[] h = {4, 6, 8};
    private static final int[] i = {4, 6, 8};
    private static final int[] g = {2, 2, 2};
    private static final int[] j = {4, 6, 6};
    private static final int[] k = {2, 2, 2};
    private static final int[] o = {4, 6, 6};
    private static final int[] n = {4, 6, 10};
    private static final int[] l = {4, 6, 10};
    private static final int[] m = {-2, 8, 12};
    private static final int[] r = {-2, 8, 12};
    private static final int[] p = {4, 6, 6};
    private static final int[] q = {4, 6, 6};
    private static final int[] t = {-2, 8, 8};
    private static final int[] s = {-2, 8, 8};
    private static final int[] w = {4, 6, 10};
    private static final int[] u = {4, 6, 10};
    private static final int[] y = {4, 6, 8};
    private static final int[] v = {4, 6, 8};
    private static final int[] x = {4, 6, 6};
    private static final int[] ac = {4, 6, 6};
    private static final int[] ab = {-2, 6, 6};
    private static final int[] aa = {-2, 6, 6};
    private static final int[][] z = {new int[]{4, 6, 8}, new int[]{2, 3, 4}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 6}, new int[]{2, 2, 2}, new int[]{4, 6, 6}, new int[]{-2, 8, 8}, new int[]{4, 6, 10}, new int[]{-2, 8, 12}, new int[]{2, 2, 2}, new int[]{4, 6, 8}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{2, 3, 4}, new int[]{4, 6, 8}, new int[]{-2, 6, 6}, new int[]{4, 6, 10}, new int[]{4, 6, 6}};
    private static final int[][] ad = {new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{4, 6, 6}, new int[]{4, 6, 6}, new int[]{4, 6, 6}, new int[]{-2, 8, 8}, new int[]{4, 6, 10}, new int[]{-2, 8, 12}, new int[]{4, 6, 6}, new int[]{4, 6, 8}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 4, 5}, new int[]{4, 6, 8}, new int[]{4, 6, 8}, new int[]{-2, 6, 6}, new int[]{4, 6, 10}, new int[]{4, 6, 6}};

    public skx(Context context) {
        super(context);
        this.as = 32;
        this.ar = 16;
        this.f10622a = 520;
        this.b = 840;
    }

    private int s() {
        return a(this.ar);
    }

    private int t() {
        return a(this.as);
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
    public int b(int i2, int i3, int i4) {
        return 0;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf b() {
        int t2 = t();
        int s2 = s();
        int[] iArr = aj;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], aq[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf c() {
        int t2 = t();
        int s2 = s();
        int[] iArr = ag;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], ai[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf d() {
        int t2 = t();
        int s2 = s();
        int[] iArr = an;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], al[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf e() {
        int t2 = t();
        int s2 = s();
        int[] iArr = ak;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], am[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf f() {
        int t2 = t();
        int s2 = s();
        int[] iArr = h;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], i[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf g() {
        int t2 = t();
        int s2 = s();
        int[] iArr = af;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], ae[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf h() {
        int t2 = t();
        int s2 = s();
        int[] iArr = d;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], e[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf i() {
        int t2 = t();
        int s2 = s();
        int[] iArr = m;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], r[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf j() {
        int t2 = t();
        int s2 = s();
        int[] iArr = t;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], s[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf k() {
        int t2 = t();
        int s2 = s();
        int[] iArr = k;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], o[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf l() {
        int t2 = t();
        int s2 = s();
        int[] iArr = n;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], l[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf m() {
        int t2 = t();
        int s2 = s();
        int[] iArr = p;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], q[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf n() {
        int t2 = t();
        int s2 = s();
        int[] iArr = g;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], j[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public int o() {
        return ah[this.c];
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public boolean b(int i2) {
        return i2 >= 0 && i2 < z.length && i2 < ad.length;
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf c(int i2, int i3, int i4) {
        int t2 = t();
        int s2 = s();
        int[] iArr = w;
        int i5 = this.c;
        return new slf(t2, s2, iArr[i5], u[i5]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf d(int i2, int i3, int i4) {
        int t2 = t();
        int s2 = s();
        int[] iArr = x;
        int i5 = this.c;
        return new slf(t2, s2, iArr[i5], ac[i5]);
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
        int t2 = t();
        int s2 = s();
        int[] iArr = y;
        int i5 = this.c;
        return new slf(t2, s2, iArr[i5], v[i5]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf a() {
        int t2 = t();
        int s2 = s();
        int[] iArr = ab;
        int i2 = this.c;
        return new slf(t2, s2, iArr[i2], aa[i2]);
    }

    @Override // com.huawei.uikit.hwcolumnsystem.widget.bqmxo
    public slf a(int i2, Pair<Integer, Integer> pair, int i3, int i4, float f) {
        return new slf(t(), s(), z[i3][i4], ad[i3][i4]);
    }
}
