package com.huawei.hms.scankit.p;

import com.huawei.hms.scankit.util.LoadOpencvJNIUtil;
import java.util.List;

/* loaded from: classes9.dex */
public class k2 {

    /* renamed from: a, reason: collision with root package name */
    private static d5 f5816a;
    private static p b;

    public static List<i2> a(boolean z, p pVar, int i, boolean z2) {
        int e = pVar.e();
        int c = pVar.c();
        byte[] b2 = pVar.a().c().b();
        d5 d5Var = new d5();
        f5816a = d5Var;
        d5Var.a(z, b2, c, e, i, z2);
        return f5816a.f5762a;
    }

    public static boolean a(boolean z, p pVar, i2 i2Var) throws a {
        float i;
        int e = pVar.e();
        int c = pVar.c();
        float[] fArr = {i2Var.j(), i2Var.k(), i2Var.f(), i2Var.c()};
        if (z) {
            i2Var.n = Math.max(i2Var.m(), i2Var.l());
            i2Var.o = Math.min(i2Var.m(), i2Var.l());
            i = i2Var.i();
            if (i2Var.g() == 11.0f || i2Var.g() == 0.0f) {
                i = 0.0f;
            }
            i2Var.v = Math.max(fArr[2], fArr[3]);
            i2Var.r = (int) Math.max(fArr[0] - (fArr[2] * 0.5d), 0.0d);
            i2Var.s = (int) Math.max(fArr[1] - (fArr[3] * 0.5d), 0.0d);
        } else {
            i = i2Var.i();
            i2Var.v = Math.max(fArr[2], fArr[3]);
            i2Var.r = (int) i2Var.d();
            i2Var.s = (int) i2Var.e();
        }
        i2Var.p = Math.min(e - i2Var.r, (int) fArr[2]);
        int min = Math.min(c - i2Var.s, (int) fArr[3]);
        i2Var.q = min;
        int i2 = i2Var.p;
        if (i2 > 0 && min > 0) {
            p a2 = pVar.a(i2Var.r, i2Var.s, i2, min);
            b = a2;
            a(a2, i, i2Var, fArr);
            return true;
        }
        throw a.a("crop_w <= 0 || crop_h <= 0");
    }

    private static void a(p pVar, float f, i2 i2Var, float[] fArr) {
        byte[] b2;
        float min;
        float max;
        float radians = (float) Math.toRadians(f);
        double d = radians;
        int abs = (int) ((i2Var.p * Math.abs(Math.sin(d))) + (i2Var.q * Math.abs(Math.cos(d))));
        int abs2 = (int) ((i2Var.q * Math.abs(Math.sin(d))) + (i2Var.p * Math.abs(Math.cos(d))));
        float[] fArr2 = i2Var.m;
        fArr2[0] = abs2 * 0.5f;
        fArr2[1] = abs * 0.5f;
        fArr2[2] = (abs2 - i2Var.p) * 0.5f;
        fArr2[3] = (abs - i2Var.q) * 0.5f;
        fArr2[4] = radians;
        if (!r3.b) {
            b2 = LoadOpencvJNIUtil.removeMoirePattern(pVar.a().c().b(), i2Var.q, i2Var.p);
        } else {
            b2 = pVar.a().c().b();
        }
        byte[] bArr = b2;
        if (f == 0.0f) {
            i2Var.t = 0;
            i2Var.u = 0;
            int i = i2Var.p;
            int i2 = i2Var.q;
            i2Var.l = new p(new q3(new e6(bArr, i, i2, 0, 0, i, i2, false)));
            return;
        }
        p pVar2 = new p(new q3(new e6(LoadOpencvJNIUtil.imageRotate(bArr, i2Var.q, i2Var.p, abs, abs2, f, 1.0d), abs2, abs, 0, 0, abs2, abs, false)));
        if ((i2Var.g() == 3.0f || i2Var.g() == 4.0f) && pVar2.c() > pVar2.e()) {
            min = Math.min(fArr[2], fArr[3]);
            max = Math.max(fArr[2], fArr[3]);
        } else {
            min = Math.max(fArr[2], fArr[3]);
            max = Math.min(fArr[2], fArr[3]);
        }
        i2Var.t = (int) Math.max((abs2 * 0.5d) - (min * 0.5d), 0.0d);
        i2Var.u = (int) Math.max((abs * 0.5d) - (max * 0.5d), 0.0d);
        i2Var.l = pVar2.a(i2Var.t, i2Var.u, Math.min(abs2 - i2Var.t, (int) min), Math.min(abs - i2Var.u, (int) max));
    }

    public static void a(s sVar, s6 s6Var, float f, i2 i2Var) {
        int c;
        int c2;
        u6[] j = s6Var.j();
        float min = Math.min(j[0].b(), j[1].b());
        float max = Math.max(j[0].b(), j[1].b());
        float c3 = j[0].c();
        if (max > sVar.e() - 1) {
            max = sVar.e() - 1;
        }
        float f2 = max;
        float c4 = c3 > ((float) (sVar.c() - 1)) ? sVar.c() - 1 : c3;
        int c5 = sVar.c();
        try {
            int[] a2 = a(sVar, j, min, f2, c4, c5, new int[c5]);
            c = a2[0];
            c2 = a2[1];
        } catch (IndexOutOfBoundsException unused) {
            c = (int) j[0].c();
            c2 = (int) j[0].c();
        }
        float f3 = c;
        float f4 = c2;
        u6[] u6VarArr = {new u6(min, f3), new u6(f2, f3), new u6(f2, f4), new u6(min, f4)};
        if (i2Var != null) {
            a(u6VarArr, f, i2Var);
        }
        s6Var.a();
        s6Var.a(u6VarArr);
    }

    private static int[] a(s sVar, u6[] u6VarArr, float f, float f2, float f3, int i, int[] iArr) {
        int i2;
        int i3;
        int i4;
        int i5 = (int) f;
        int i6 = i5;
        int i7 = 0;
        while (true) {
            i2 = ((int) f2) - 1;
            if (i6 >= i2) {
                break;
            }
            int i8 = (int) f3;
            boolean b2 = sVar.b(i6, i8);
            i6++;
            if (sVar.b(i6, i8) ^ b2) {
                i7++;
            }
        }
        int i9 = 0;
        for (int i10 = 0; i10 < i; i10++) {
            int i11 = i5;
            int i12 = 0;
            while (i11 < i2) {
                boolean b3 = sVar.b(i11, i10);
                i11++;
                if (b3 ^ sVar.b(i11, i10)) {
                    i12++;
                }
            }
            float f4 = i7;
            if (i12 > 1.5f * f4) {
                i12 = 0;
            }
            if (i12 < f4 * 0.5f) {
                i12 = 0;
            }
            iArr[i10] = i12;
            if (i12 > i9) {
                i9 = i12;
            }
        }
        if (i9 > 0) {
            float[] fArr = new float[i];
            for (int i13 = 0; i13 < i; i13++) {
                fArr[i13] = iArr[i13] / i9;
            }
            float f5 = 0.0f;
            for (int i14 = 0; i14 < i; i14++) {
                f5 += fArr[i14];
            }
            float f6 = f5 / i;
            if (f6 > 1.0d) {
                f6 = 0.99f;
            }
            i3 = (int) f3;
            i4 = i3;
            while (true) {
                if (i4 < 0) {
                    i4 = 0;
                    break;
                }
                if (fArr[i4] < f6) {
                    break;
                }
                i4--;
            }
            while (true) {
                if (i3 >= i) {
                    i3 = 0;
                    break;
                }
                if (fArr[i3] < f6) {
                    break;
                }
                i3++;
            }
        } else {
            i3 = 0;
            i4 = 0;
        }
        if (i4 == 0 && i3 == 0) {
            i4 = ((int) u6VarArr[0].c()) + (-10) < 0 ? 0 : ((int) u6VarArr[0].c()) - 10;
            i3 = i - 1;
            if (((int) u6VarArr[0].c()) + 10 <= i3) {
                i3 = ((int) u6VarArr[0].c()) + 10;
            }
        }
        return new int[]{i4, i3};
    }

    private static u6 a(float f, float f2, i2 i2Var) {
        float[] fArr = i2Var.m;
        if (fArr != null && fArr.length == 5) {
            float f3 = -fArr[4];
            double d = f - fArr[0];
            double d2 = f3;
            double cos = Math.cos(d2);
            double d3 = f2 - i2Var.m[1];
            double sin = Math.sin(d2);
            float f4 = i2Var.m[0];
            int sin2 = (int) (((-(f - f4)) * Math.sin(d2)) + ((f2 - i2Var.m[1]) * Math.cos(d2)) + i2Var.m[1]);
            float[] fArr2 = i2Var.m;
            return new u6((((int) (((d * cos) + (d3 * sin)) + f4)) - fArr2[2]) + i2Var.r, (sin2 - fArr2[3]) + i2Var.s);
        }
        return new u6(f, f2);
    }

    public static void a(u6[] u6VarArr, float f, i2 i2Var) {
        if (i2Var == null || u6VarArr == null) {
            return;
        }
        for (int i = 0; i < u6VarArr.length; i++) {
            u6VarArr[i] = a((u6VarArr[i].b() * f) + i2Var.t, (u6VarArr[i].c() * f) + i2Var.u, i2Var);
        }
    }
}
