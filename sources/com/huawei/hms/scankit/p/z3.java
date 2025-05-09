package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.aiscan.common.BarcodeFormat;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes9.dex */
public class z3 implements o6 {

    /* renamed from: a, reason: collision with root package name */
    private Map<l1, Object> f5932a = new EnumMap(l1.class);
    private final u1 b = new u1();

    private s6 a(int i, e2 e2Var, Map<l1, ?> map) throws a {
        int i2 = 1;
        int max = Math.max(1, i - 2);
        int min = Math.min(6, i + 2);
        int max2 = Math.max(Math.abs(i - max), Math.abs(i - min));
        int i3 = 0;
        j2 j2Var = null;
        while (i3 <= max2 * 2) {
            i += i3 * i2;
            if (i >= max && i <= min) {
                try {
                    j2Var = e2Var.a(i);
                    w1 a2 = this.b.a(j2Var.a(), map);
                    if (a2.d() != null) {
                        return new s6(a2.d(), a2.c(), j2Var.d(), BarcodeFormat.HARMONY_CODE);
                    }
                    continue;
                } catch (a unused) {
                    continue;
                }
            }
            i3++;
            i2 *= -1;
        }
        if (j2Var == null || j2Var.d().length <= 3) {
            return null;
        }
        return new s6(null, null, j2Var.d(), BarcodeFormat.HARMONY_CODE);
    }

    private s6 a(int i, int i2, g3[] g3VarArr, e2 e2Var, Map<l1, ?> map) throws a {
        char c;
        float b;
        float b2;
        float b3;
        float c2;
        float c3;
        float c4;
        float max;
        float max2;
        s6 a2;
        char c5 = 0;
        int i3 = 0;
        while (i3 <= i - 2) {
            int i4 = i3 + 1;
            int i5 = i4;
            while (i5 <= i - 1) {
                int i6 = i5 + 1;
                for (int i7 = i6; i7 <= i; i7++) {
                    int i8 = i;
                    while (i8 < i2) {
                        g3[] g3VarArr2 = new g3[3];
                        g3VarArr2[c5] = g3VarArr[i3];
                        g3VarArr2[1] = g3VarArr[i5];
                        g3VarArr2[2] = g3VarArr[i7];
                        u6.a(g3VarArr2);
                        g3 g3Var = g3VarArr[i8];
                        try {
                            b = g3VarArr2[2].b();
                            b2 = g3VarArr2[1].b();
                            b3 = g3VarArr2[c5].b();
                            c2 = g3VarArr2[2].c();
                            c3 = g3VarArr2[1].c();
                            c4 = g3VarArr2[c5].c();
                            try {
                                max = Math.max(Math.abs(g3VarArr2[1].b() - g3VarArr2[c5].b()), Math.abs(g3VarArr2[1].b() - g3VarArr2[2].b()));
                                c = 0;
                                try {
                                    max2 = Math.max(Math.abs(g3VarArr2[1].c() - g3VarArr2[0].c()), Math.abs(g3VarArr2[1].c() - g3VarArr2[2].c()));
                                } catch (a unused) {
                                }
                            } catch (a unused2) {
                                c = 0;
                            }
                        } catch (a unused3) {
                            c = c5;
                        }
                        if (Math.abs(((b - b2) + b3) - g3Var.b()) < max / 2.0f && Math.abs(((c2 - c3) + c4) - g3Var.c()) < max2 / 2.0f) {
                            try {
                                a2 = a(e2Var.a(g3VarArr2, g3Var), e2Var, map);
                            } catch (a unused4) {
                                continue;
                            }
                            if (a2 != null) {
                                return a2;
                            }
                            i8++;
                            c5 = c;
                        }
                        i8++;
                        c5 = c;
                    }
                }
                i5 = i6;
            }
            i3 = i4;
        }
        throw a.a();
    }

    @Override // com.huawei.hms.scankit.p.o6
    public s6 a(p pVar, Map<l1, ?> map) throws a {
        e2 e2Var = new e2(pVar.b());
        g3[] a2 = e2Var.a(map);
        int length = a2.length;
        if (length == 3) {
            u6.a(a2);
            s6 a3 = a(e2Var.a(a2, null), e2Var, map);
            if (a3 != null) {
                return a3;
            }
        } else {
            if (length == 4) {
                g3[] g3VarArr = new g3[3];
                for (int i = 0; i < 3; i++) {
                    g3VarArr[i] = a2[i];
                }
                u6.a(g3VarArr);
                s6 a4 = a(e2Var.a(g3VarArr, a2[3]), e2Var, map);
                if (a4 != null) {
                    return a4;
                }
            } else {
                float e = ((a2[0].e() + a2[1].e()) + a2[2].e()) / 3.0f;
                float e2 = a2[length - 1].e();
                int i2 = length - 2;
                while (i2 > 2 && Math.abs(a2[i2].e() - e) >= Math.abs(a2[i2].e() - e2)) {
                    e2 = (((r6 - 1) * e2) + a2[i2].e()) / (length - i2);
                    i2--;
                }
                return a(i2, length, a2, e2Var, map);
            }
        }
        throw a.a();
    }
}
