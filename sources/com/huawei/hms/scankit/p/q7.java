package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class q7 extends g5 {
    public static final int[] c = {1, 1, 1};
    public static final int[] d = {1, 1, 1, 1, 1};
    public static final int[] e = {1, 1, 1, 1, 1, 1};
    public static final int[][] f;
    public static final int[][] g;

    /* renamed from: a, reason: collision with root package name */
    private final StringBuilder f5865a = new StringBuilder(20);
    private final p7 b = new p7();

    static {
        int[][] iArr = {new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
        f = iArr;
        int[][] iArr2 = new int[20][];
        g = iArr2;
        System.arraycopy(iArr, 0, iArr2, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr3 = f[i - 10];
            int[] iArr4 = new int[iArr3.length];
            for (int i2 = 0; i2 < iArr3.length; i2++) {
                iArr4[i2] = iArr3[(iArr3.length - i2) - 1];
            }
            g[i] = iArr4;
        }
    }

    protected q7() {
    }

    static int[] a(r rVar) throws a {
        return b(rVar, 0);
    }

    static ArrayList<int[]> b(r rVar) throws a {
        int e2 = rVar.e() / 2;
        ArrayList<int[]> arrayList = new ArrayList<>();
        int i = 0;
        while (i < e2) {
            try {
                int[] b = b(rVar, i);
                arrayList.add(b);
                i = b[0] + 1;
            } catch (a unused) {
            }
        }
        if (arrayList.size() != 0) {
            return arrayList;
        }
        throw a.a();
    }

    protected abstract int a(r rVar, int[] iArr, StringBuilder sb) throws a;

    abstract BarcodeFormat a();

    abstract boolean a(int i, int i2, r rVar);

    abstract boolean a(int[] iArr, int[] iArr2) throws a;

    @Override // com.huawei.hms.scankit.p.g5
    public s6 a(int i, r rVar, Map<l1, ?> map) throws a {
        return a(i, rVar, a(rVar), map);
    }

    public s6 a(int i, r rVar, int[] iArr, Map<l1, ?> map) throws a {
        v6 v6Var = map == null ? null : (v6) map.get(l1.NEED_RESULT_POINT_CALLBACK);
        if (v6Var != null) {
            v6Var.a(new u6((iArr[0] + iArr[1]) / 2.0f, i));
        }
        StringBuilder sb = this.f5865a;
        sb.setLength(0);
        int a2 = a(rVar, iArr, sb);
        if (v6Var != null) {
            v6Var.a(new u6(a2, i));
        }
        int[] a3 = a(rVar, a2);
        if (a3[0] - a2 <= 1) {
            if (v6Var != null) {
                v6Var.a(new u6((a3[0] + a3[1]) / 2.0f, i));
            }
            if (a(iArr, a3)) {
                int i2 = a3[1];
                if ((i2 - a3[0]) + i2 < rVar.e() && a(a3[0], i2, rVar)) {
                    String sb2 = sb.toString();
                    if (sb2.length() >= 8) {
                        if (a(sb2)) {
                            float f2 = i;
                            s6 s6Var = new s6(sb2, null, new u6[]{new u6(iArr[0], f2), new u6(a3[1], f2)}, a());
                            a(s6Var, a3, i, rVar, map);
                            return s6Var;
                        }
                        throw a.a();
                    }
                    throw a.a();
                }
                throw a.a();
            }
            throw a.a();
        }
        throw a.a();
    }

    static int[] b(r rVar, int i) throws a {
        int[] iArr = new int[c.length];
        int[] iArr2 = null;
        boolean z = false;
        while (!z) {
            int[] iArr3 = c;
            Arrays.fill(iArr, 0, iArr3.length, 0);
            iArr2 = a(rVar, i, false, iArr3, iArr);
            int i2 = iArr2[0];
            int i3 = iArr2[1];
            int i4 = i2 - (i3 - i2);
            for (int i5 = i4; i5 <= i4 + 3 && (i5 < 0 || !(z = rVar.a(i5, i2, false, true))); i5++) {
            }
            i = i3;
        }
        return iArr2;
    }

    public static int b(CharSequence charSequence) throws a {
        int length = charSequence.length();
        int i = 0;
        for (int i2 = length - 1; i2 >= 0; i2 -= 2) {
            int charAt = charSequence.charAt(i2) - '0';
            if (charAt < 0 || charAt > 9) {
                throw a.a();
            }
            i += charAt;
        }
        int i3 = i * 3;
        for (int i4 = length - 2; i4 >= 0; i4 -= 2) {
            int charAt2 = charSequence.charAt(i4) - '0';
            if (charAt2 < 0 || charAt2 > 9) {
                throw a.a();
            }
            i3 += charAt2;
        }
        return (1000 - i3) % 10;
    }

    private void a(s6 s6Var, int[] iArr, int i, r rVar, Map<l1, ?> map) throws a {
        int i2;
        try {
            s6 a2 = this.b.a(i, rVar, iArr[1]);
            s6Var.a(a2.j());
            i2 = a2.k().length();
        } catch (a unused) {
            i2 = 0;
        }
        int[] iArr2 = map == null ? null : (int[]) map.get(l1.ALLOWED_EAN_EXTENSIONS);
        if (iArr2 != null) {
            for (int i3 : iArr2) {
                if (i2 == i3) {
                    return;
                }
            }
            throw a.a();
        }
    }

    boolean a(String str) throws a {
        return a((CharSequence) str);
    }

    public static boolean a(CharSequence charSequence) throws a {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i = length - 1;
        return b(charSequence.subSequence(0, i)) == Character.digit(charSequence.charAt(i), 10);
    }

    int[] a(r rVar, int i) throws a {
        return a(rVar, i, false, c);
    }

    static int[] a(r rVar, int i, boolean z, int[] iArr) throws a {
        return a(rVar, i, z, iArr, new int[iArr.length]);
    }

    private static int[] a(r rVar, int i, boolean z, int[] iArr, int[] iArr2) throws a {
        int e2 = rVar.e();
        int d2 = z ? rVar.d(i) : rVar.c(i);
        int length = iArr.length;
        boolean z2 = z;
        int i2 = 0;
        int i3 = d2;
        while (d2 < e2) {
            if (rVar.b(d2) != z2) {
                if (i2 >= 0 && i2 < iArr2.length) {
                    iArr2[i2] = iArr2[i2] + 1;
                } else {
                    throw a.a();
                }
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else {
                    if (g5.a(iArr2, iArr, 0.8f) < 0.46f) {
                        return new int[]{i3, d2};
                    }
                    i3 += iArr2[0] + iArr2[1];
                    int i4 = i2 - 1;
                    System.arraycopy(iArr2, 2, iArr2, 0, i4);
                    iArr2[i4] = 0;
                    iArr2[i2] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                z2 = !z2;
            }
            d2++;
        }
        throw a.a();
    }

    static int a(r rVar, int[] iArr, int i, int[][] iArr2) throws a {
        g5.a(rVar, i, iArr);
        int length = iArr2.length;
        float f2 = 0.46f;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            float a2 = g5.a(iArr, iArr2[i3], 0.8f);
            if (a2 < f2) {
                i2 = i3;
                f2 = a2;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw a.a();
    }
}
