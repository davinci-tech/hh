package com.huawei.hms.scankit.p;

import java.util.Map;

/* loaded from: classes9.dex */
public class h2 {

    /* renamed from: a, reason: collision with root package name */
    private final s f5785a;
    private v6 b;

    public h2(s sVar) {
        this.f5785a = sVar;
    }

    private static float b(float f, float f2, float f3) {
        return Math.min(Math.min(f, f2), f3);
    }

    public final f3[] a(Map<l1, ?> map) throws a {
        v6 v6Var = map == null ? null : (v6) map.get(l1.NEED_RESULT_POINT_CALLBACK);
        this.b = v6Var;
        return new j3(this.f5785a, v6Var).a(map);
    }

    public static boolean a(f3 f3Var, f3 f3Var2, f3 f3Var3) {
        float[] fArr = new float[3];
        a(f3Var, f3Var2, f3Var3, fArr);
        float f = fArr[0];
        float f2 = fArr[1];
        float f3 = fArr[2];
        float sqrt = (float) Math.sqrt(f2);
        float sqrt2 = (float) Math.sqrt(f3);
        float sqrt3 = (float) Math.sqrt(f);
        if (sqrt / sqrt2 >= 1.8f || sqrt2 / sqrt >= 1.8f || b(sqrt, sqrt2, sqrt3) <= a(f3Var.e(), f3Var2.e(), f3Var3.e()) * 6.0f) {
            return false;
        }
        float f4 = ((f2 + f3) - f) / ((sqrt * 2.0f) * sqrt2);
        float f5 = sqrt3 * 2.0f;
        float f6 = ((f + f2) - f3) / (sqrt * f5);
        float f7 = ((f + f3) - f2) / (f5 * sqrt2);
        return Math.abs(f4) <= 0.342f && f6 >= 0.5736f && f6 <= 0.8191f && f7 >= 0.5736f && f7 <= 0.8191f;
    }

    private static void a(f3 f3Var, f3 f3Var2, f3 f3Var3, float[] fArr) {
        float b = f3Var.b() - f3Var2.b();
        float c = f3Var.c() - f3Var2.c();
        float f = (b * b) + (c * c);
        float b2 = f3Var.b() - f3Var3.b();
        float c2 = f3Var.c() - f3Var3.c();
        float f2 = (b2 * b2) + (c2 * c2);
        float b3 = f3Var2.b() - f3Var3.b();
        float c3 = f3Var2.c() - f3Var3.c();
        float f3 = (b3 * b3) + (c3 * c3);
        if (f > f3 && f > f2) {
            fArr[0] = f;
            fArr[1] = f2;
            fArr[2] = f3;
        } else if (f3 > f && f3 > f2) {
            fArr[0] = f3;
            fArr[1] = f;
            fArr[2] = f2;
        } else {
            fArr[0] = f2;
            fArr[1] = f;
            fArr[2] = f3;
        }
    }

    private static float a(float f, float f2, float f3) {
        return Math.max(Math.max(f, f2), f3);
    }

    public static boolean a(f3[] f3VarArr, f3[] f3VarArr2, int[] iArr) {
        int i;
        f3 f3Var = f3VarArr[0];
        f3 f3Var2 = f3VarArr[1];
        f3 f3Var3 = f3VarArr[2];
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        float b = f3Var3.b();
        float b2 = f3Var2.b();
        float b3 = f3Var.b();
        float c = f3Var3.c();
        float c2 = f3Var2.c();
        float c3 = f3Var.c();
        float e = ((f3Var.e() + f3Var2.e()) + f3Var3.e()) / 3.0f;
        int i5 = 0;
        while (i5 < f3VarArr2.length) {
            if (i5 == i2 || i5 == i3 || i5 == i4) {
                i = i5;
            } else {
                f3 f3Var4 = f3VarArr2[i5];
                float b4 = ((b - b2) + b3) - f3Var4.b();
                float c4 = ((c - c2) + c3) - f3Var4.c();
                float f = (b4 * b4) + (c4 * c4);
                i = i5;
                if (((float) Math.sqrt(f)) < 10.0f * e) {
                    return true;
                }
            }
            i5 = i + 1;
        }
        return false;
    }
}
