package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;
import java.util.Map;

/* loaded from: classes9.dex */
public class g2 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5781a;
    private v6 b;

    public g2(s sVar) {
        this.f5781a = sVar;
    }

    private static d6 a(u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4, u6 u6Var5, int i) {
        float b;
        float c;
        float f;
        float f2;
        float f3 = i - 3.5f;
        if (u6Var4 != null) {
            float b2 = u6Var4.b();
            b = b2;
            c = u6Var4.c();
            f = u6Var5.b();
            f2 = u6Var5.c();
        } else {
            float b3 = u6Var2.b();
            float b4 = u6Var.b();
            b = (b3 - b4) + u6Var3.b();
            c = (u6Var2.c() - u6Var.c()) + u6Var3.c();
            f = f3;
            f2 = f;
        }
        return d6.a(3.5f, 3.5f, f3, 3.5f, f, f2, 3.5f, f3, u6Var.b(), u6Var.c(), u6Var2.b(), u6Var2.c(), b, c, u6Var3.b(), u6Var3.c());
    }

    private float b(int i, int i2, int i3, int i4) {
        float f;
        float f2;
        float a2 = a(i, i2, i3, i4);
        int i5 = i - (i3 - i);
        if (i == i5) {
            return Float.NaN;
        }
        int i6 = 0;
        if (i5 < 0) {
            f = i / (i - i5);
            i5 = 0;
        } else if (i5 >= this.f5781a.e()) {
            f = ((this.f5781a.e() - 1) - i) / (i5 - i);
            i5 = this.f5781a.e() - 1;
        } else {
            f = 1.0f;
        }
        float f3 = i2;
        int i7 = (int) (f3 - ((i4 - i2) * f));
        if (i2 == i7) {
            return Float.NaN;
        }
        if (i7 < 0) {
            f2 = f3 / (i2 - i7);
        } else if (i7 >= this.f5781a.c()) {
            f2 = ((this.f5781a.c() - 1) - i2) / (i7 - i2);
            i6 = this.f5781a.c() - 1;
        } else {
            i6 = i7;
            f2 = 1.0f;
        }
        float a3 = a(i, i2, (int) (i + ((i5 - i) * f2)), i6);
        if (Math.max(a2, a3) > Math.min(a2, a3) * 1.5d) {
            return Float.NaN;
        }
        return (a2 + a3) - 1.0f;
    }

    private static s a(s sVar, d6 d6Var, int i) throws a {
        return s3.a().a(sVar, i, i, d6Var, true);
    }

    private static int a(u6 u6Var, u6 u6Var2, u6 u6Var3, float f) throws a {
        int i;
        int i2;
        try {
            i = ((s4.a(u6.a(u6Var, u6Var2) / f) + s4.a(u6.a(u6Var, u6Var3) / f)) / 2) + 7;
            i2 = i & 3;
        } catch (a unused) {
            int a2 = (((int) (u6.a(u6Var, u6Var2) / f)) + ((int) (u6.a(u6Var, u6Var3) / f))) / 2;
            i = a2 + 7;
            int i3 = i & 3;
            if (i3 != 0) {
                if (i3 != 2) {
                    return i3 != 3 ? i : a2 + 9;
                }
            }
        }
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return i;
                }
                throw a.a();
            }
            return i - 1;
        }
        return i + 1;
    }

    public final j2 a(Map<l1, ?> map) throws a {
        this.b = map == null ? null : (v6) map.get(l1.NEED_RESULT_POINT_CALLBACK);
        return a(new i3(this.f5781a, this.b).b());
    }

    protected final j2 a(k3 k3Var) throws a {
        e3 b = k3Var.b();
        e3 c = k3Var.c();
        e3 a2 = k3Var.a();
        try {
            float a3 = a(b, c, a2);
            if (a3 >= 1.0f) {
                return a(b, c, a2, a3);
            }
            throw a.a();
        } catch (a unused) {
            float e = ((b.e() + c.e()) + a2.e()) / 3.0f;
            if (e >= 1.0f) {
                return a(b, c, a2, e);
            }
            throw a.a();
        }
    }

    private j2 a(e3 e3Var, e3 e3Var2, e3 e3Var3, float f) throws a {
        d[] dVarArr;
        char c;
        d[] dVarArr2;
        int i;
        d6 d6Var;
        u6[] u6VarArr;
        int a2 = a((u6) e3Var, (u6) e3Var2, (u6) e3Var3, f);
        r3.w.push(Integer.valueOf(a2));
        b8 b = b8.b(a2);
        if (r3.s && r3.c) {
            return a(e3Var, e3Var2, e3Var3, f, a2);
        }
        int d = b.d();
        int length = b.c().length;
        int i2 = length * length;
        d[] dVarArr3 = new d[i2];
        d[] dVarArr4 = new d[i2];
        d[] dVarArr5 = new d[2];
        if (b.c().length > 0) {
            dVarArr = dVarArr5;
            c = 2;
            dVarArr2 = dVarArr4;
            i = a(e3Var, e3Var2, e3Var3, f, a2, b, dVarArr3, dVarArr4, length, d - 7, dVarArr);
        } else {
            dVarArr = dVarArr5;
            c = 2;
            dVarArr2 = dVarArr4;
            i = 0;
        }
        d dVar = dVarArr[0];
        d6 a3 = a(e3Var, e3Var2, e3Var3, dVar, dVarArr[1], a2);
        if (r3.p && r3.m) {
            d6Var = a3;
            a(a3, length, a2, e3Var, e3Var2, e3Var3, dVarArr3, i, dVarArr2);
        } else {
            d6Var = a3;
        }
        s a4 = a(this.f5781a, d6Var, a2);
        if (dVar == null) {
            u6VarArr = new u6[3];
            u6VarArr[0] = e3Var3;
            u6VarArr[1] = e3Var;
            u6VarArr[c] = e3Var2;
        } else {
            u6VarArr = new u6[4];
            u6VarArr[0] = e3Var3;
            u6VarArr[1] = e3Var;
            u6VarArr[c] = e3Var2;
            u6VarArr[3] = dVar;
        }
        float f2 = a2;
        float[] fArr = new float[8];
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[c] = f2;
        fArr[3] = 0.0f;
        fArr[4] = f2;
        fArr[5] = f2;
        fArr[6] = 0.0f;
        fArr[7] = f2;
        d6Var.a(fArr);
        return new j2(a4, u6VarArr, new u6[]{a(new u6(fArr[6], fArr[7])), a(new u6(fArr[0], fArr[1], e3Var.d())), a(new u6(fArr[c], fArr[3], e3Var2.d())), a(new u6(fArr[4], fArr[5], e3Var3.d()))}, f);
    }

    private int a(e3 e3Var, e3 e3Var2, e3 e3Var3, float f, int i, b8 b8Var, d[] dVarArr, d[] dVarArr2, int i2, int i3, d[] dVarArr3) {
        int i4;
        int i5;
        float b = e3Var2.b();
        float b2 = e3Var.b();
        float b3 = e3Var3.b();
        float c = e3Var2.c();
        float c2 = e3Var.c();
        float c3 = e3Var3.c();
        float f2 = i3;
        float f3 = 3.0f / f2;
        float f4 = 1.0f - f3;
        int b4 = (int) (e3Var.b() + ((((b - b2) + b3) - e3Var.b()) * f4));
        int c4 = (int) (e3Var.c() + (f4 * (((c - c2) + c3) - e3Var.c())));
        if (r3.v[0] && i3 == 22) {
            i5 = 8;
            i4 = 2;
        } else {
            i4 = 4;
            i5 = 16;
        }
        for (int i6 = i4; i6 <= i5; i6 <<= 1) {
            try {
                dVarArr3[0] = a(f, b4, c4, i6);
                break;
            } catch (a unused) {
            }
        }
        float f5 = i - 6.5f;
        dVarArr3[1] = new d(f5, f5, e3Var3.e());
        d dVar = dVarArr3[0];
        if (dVar != null && s4.a(b4, c4, dVar.b(), dVarArr3[0].c()) > f * 4.0f) {
            dVarArr3[0] = null;
        }
        if (dVarArr3[0] == null && i2 > 2) {
            int i7 = i2 - 2;
            dVarArr3[1] = new d(b8Var.c()[i7] + 0.5f, f5, e3Var3.e());
            int c5 = (int) (e3Var3.c() - (f3 * (e3Var3.c() - e3Var.c())));
            int b5 = (int) ((((b8Var.c()[i7] - 3.0f) / f2) * (e3Var2.b() - e3Var.b())) + e3Var3.b());
            while (i4 <= i5) {
                try {
                    dVarArr3[0] = a(f, b5, c5, i4);
                    break;
                } catch (a unused2) {
                    i4 <<= 1;
                }
            }
            d dVar2 = dVarArr3[0];
            if (dVar2 != null && s4.a(b5, c5, dVar2.b(), dVarArr3[0].c()) > f * 4.0f) {
                dVarArr3[0] = null;
            }
        }
        if (r3.p && r3.m) {
            return a(b8Var, i3, e3Var2, e3Var, e3Var3, f, i2, 0, dVarArr, dVarArr2);
        }
        return 0;
    }

    private int a(b8 b8Var, int i, e3 e3Var, e3 e3Var2, e3 e3Var3, float f, int i2, int i3, d[] dVarArr, d[] dVarArr2) {
        int i4;
        int i5;
        int i6;
        float f2;
        int i7 = i2;
        int i8 = i3;
        int i9 = 0;
        while (i9 < i7) {
            if (i9 == 0) {
                i4 = i7 - 1;
                i5 = 1;
            } else {
                i4 = i7;
                i5 = 0;
            }
            int i10 = i9 != i7 + (-1) ? i5 : 1;
            float f3 = i;
            float f4 = 3.0f;
            float b = (((b8Var.c()[i9] - 3.0f) * (e3Var.b() - e3Var2.b())) / f3) + e3Var2.b();
            float c = ((b8Var.c()[i9] - 3.0f) * (e3Var.c() - e3Var2.c())) / f3;
            float c2 = e3Var2.c();
            while (i10 < i4) {
                int c3 = (int) ((c + c2) - (((b8Var.c()[i10] - f4) * (e3Var2.c() - e3Var3.c())) / f3));
                int b2 = (int) (b - (((b8Var.c()[i10] - f4) * (e3Var2.b() - e3Var3.b())) / f3));
                int i11 = 4;
                int i12 = 4;
                while (true) {
                    if (i12 > i11) {
                        i6 = i4;
                        f2 = b;
                        break;
                    }
                    int i13 = (i9 * i7) + i10;
                    try {
                        dVarArr[i13] = a(f, b2, c3, i12);
                        i6 = i4;
                        try {
                            f2 = b;
                            try {
                                dVarArr2[i13] = new d(b8Var.c()[i9] + 0.5f, b8Var.c()[i10] + 0.5f, e3Var3.e());
                                i8++;
                                break;
                            } catch (a unused) {
                                continue;
                            }
                        } catch (a unused2) {
                            f2 = b;
                            i12 <<= 1;
                            i7 = i2;
                            b = f2;
                            i4 = i6;
                            i11 = 4;
                        }
                    } catch (a unused3) {
                        i6 = i4;
                    }
                    i12 <<= 1;
                    i7 = i2;
                    b = f2;
                    i4 = i6;
                    i11 = 4;
                }
                i10++;
                i7 = i2;
                b = f2;
                i4 = i6;
                f4 = 3.0f;
            }
            i9++;
            i7 = i2;
        }
        return i8;
    }

    private void a(d6 d6Var, int i, int i2, e3 e3Var, e3 e3Var2, e3 e3Var3, d[] dVarArr, int i3, d[] dVarArr2) {
        int i4 = i3 + 3;
        int i5 = i4 * 2;
        float[] fArr = new float[i5];
        float[] fArr2 = new float[i5];
        fArr[0] = e3Var.b();
        fArr[1] = e3Var.c();
        fArr[2] = e3Var2.b();
        fArr[3] = e3Var2.c();
        fArr[4] = e3Var3.b();
        fArr[5] = e3Var3.c();
        fArr2[0] = 3.5f;
        fArr2[1] = 3.5f;
        float f = i2 - 3.5f;
        fArr2[2] = f;
        fArr2[3] = 3.5f;
        fArr2[4] = 3.5f;
        fArr2[5] = f;
        int i6 = 6;
        int i7 = 6;
        for (int i8 = 0; i8 < i * i; i8++) {
            d dVar = dVarArr[i8];
            if (dVar != null) {
                int i9 = i7 + 1;
                fArr[i7] = dVar.b();
                i7 += 2;
                fArr[i9] = dVarArr[i8].c();
                int i10 = i6 + 1;
                fArr2[i6] = dVarArr2[i8].b();
                i6 += 2;
                fArr2[i10] = dVarArr2[i8].c();
            }
        }
        float[] QuadFitting = LoadOpencvJNIUtil.QuadFitting(fArr2, i4, fArr);
        if (QuadFitting.length != 0) {
            d6Var.a(QuadFitting[0], QuadFitting[1], QuadFitting[2], QuadFitting[3], QuadFitting[4], QuadFitting[5], QuadFitting[6], QuadFitting[7], QuadFitting[8], QuadFitting[9], QuadFitting[10], QuadFitting[11], QuadFitting[12], QuadFitting[13]);
        }
    }

    private j2 a(e3 e3Var, e3 e3Var2, e3 e3Var3, float f, int i) {
        s sVar = new s(i, i);
        float f2 = i;
        float c = this.f5781a.c() / f2;
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                double d = c * 0.5d;
                int i4 = (int) ((i3 * c) + d);
                int i5 = (int) ((i2 * c) + d);
                if (i4 >= -1 && i4 <= this.f5781a.e() && i5 >= -1 && i5 <= this.f5781a.c()) {
                    if (this.f5781a.b(i4, i5)) {
                        sVar.c(i3, i2);
                    }
                } else {
                    sVar.c(i3, i2);
                }
            }
        }
        u6[] u6VarArr = {e3Var3, e3Var, e3Var2};
        float[] fArr = {0.0f, 0.0f, f2, 0.0f, f2, f2, 0.0f, f2};
        a(e3Var, e3Var2, e3Var3, null, null, i).a(fArr);
        return new j2(sVar, u6VarArr, new u6[]{a(new u6(fArr[6], fArr[7])), a(new u6(fArr[0], fArr[1])), a(new u6(fArr[2], fArr[3])), a(new u6(fArr[4], fArr[5]))}, f);
    }

    protected final float a(u6 u6Var, u6 u6Var2, u6 u6Var3) {
        return (a(u6Var, u6Var2) + a(u6Var, u6Var3)) / 2.0f;
    }

    private float a(u6 u6Var, u6 u6Var2) {
        float b = b((int) u6Var.b(), (int) u6Var.c(), (int) u6Var2.b(), (int) u6Var2.c());
        float b2 = b((int) u6Var2.b(), (int) u6Var2.c(), (int) u6Var.b(), (int) u6Var.c());
        return Float.isNaN(b) ? b2 / 7.0f : Float.isNaN(b2) ? b / 7.0f : (b + b2) / 14.0f;
    }

    private float a(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        g2 g2Var;
        boolean z;
        int i11;
        int i12 = 1;
        boolean z2 = Math.abs(i4 - i2) > Math.abs(i3 - i);
        if (z2) {
            i6 = i;
            i5 = i2;
            i8 = i3;
            i7 = i4;
        } else {
            i5 = i;
            i6 = i2;
            i7 = i3;
            i8 = i4;
        }
        int abs = Math.abs(i7 - i5);
        int abs2 = Math.abs(i8 - i6);
        int i13 = 2;
        int i14 = (-abs) / 2;
        int i15 = i5 < i7 ? 1 : -1;
        int i16 = i6 < i8 ? 1 : -1;
        int i17 = i7 + i15;
        int i18 = i5;
        int i19 = i6;
        int i20 = 0;
        while (true) {
            if (i18 == i17) {
                i9 = i17;
                i10 = i13;
                break;
            }
            int i21 = z2 ? i19 : i18;
            int i22 = z2 ? i18 : i19;
            if (i20 == i12) {
                z = z2;
                i11 = i12;
                i9 = i17;
                g2Var = this;
            } else {
                g2Var = this;
                z = z2;
                i9 = i17;
                i11 = 0;
            }
            if (i11 == g2Var.f5781a.b(i21, i22)) {
                if (i20 == 2) {
                    return s4.a(i18, i19, i5, i6);
                }
                i20++;
            }
            i14 += abs2;
            if (i14 > 0) {
                if (i19 == i8) {
                    i10 = 2;
                    break;
                }
                i19 += i16;
                i14 -= abs;
            }
            i18 += i15;
            i17 = i9;
            z2 = z;
            i12 = 1;
            i13 = 2;
        }
        if (i20 == i10) {
            return s4.a(i9, i8, i5, i6);
        }
        return Float.NaN;
    }

    protected final d a(float f, int i, int i2, float f2) throws a {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.f5781a.e() - 1, i + i3) - max;
        float f3 = 3.0f * f;
        if (min >= f3) {
            int max2 = Math.max(0, i2 - i3);
            int min2 = Math.min(this.f5781a.c() - 1, i2 + i3) - max2;
            if (min2 >= f3) {
                return new e(this.f5781a, max, max2, min, min2, f, this.b).a();
            }
            throw a.a();
        }
        throw a.a();
    }

    private u6 a(u6 u6Var) {
        float b = u6Var.b();
        float c = u6Var.c();
        int e = this.f5781a.e();
        int c2 = this.f5781a.c();
        if (b < 0.0f) {
            b = 0.0f;
        }
        float f = e - 1;
        if (b > f) {
            b = f;
        }
        if (c < 0.0f) {
            c = 0.0f;
        }
        float f2 = c2 - 1;
        if (c > f2) {
            c = f2;
        }
        return new u6(b, c, u6Var.d());
    }
}
