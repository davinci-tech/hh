package com.huawei.hms.scankit.p;

import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import java.util.Map;

/* loaded from: classes9.dex */
public class e2 {
    private static final int[] g = {com.huawei.hms.kit.awareness.barrier.internal.e.a.C, FitnessSleepType.HW_FITNESS_DREAM, 264, 244, 270, OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE};
    private static final int[] h = {21, 25, 29, 33, 37, 41};

    /* renamed from: a, reason: collision with root package name */
    private final s f5770a;
    private v6 b;
    private g3 c;
    private g3 d;
    private g3 e;
    private g3 f;

    public e2(s sVar) {
        this.f5770a = sVar;
    }

    private float b(int i, int i2, s sVar) throws a {
        int c = sVar.c();
        int[] iArr = new int[5];
        for (int i3 = 0; i3 < 5; i3++) {
            iArr[i3] = 0;
        }
        int i4 = i;
        while (i4 >= 0 && sVar.b(i2, i4)) {
            iArr[2] = iArr[2] + 1;
            i4--;
        }
        if (i4 < 0) {
            throw a.a();
        }
        while (i4 >= 0 && !sVar.b(i2, i4)) {
            iArr[1] = iArr[1] + 1;
            i4--;
        }
        if (i4 < 0) {
            throw a.a();
        }
        while (i4 >= 0 && sVar.b(i2, i4)) {
            iArr[0] = iArr[0] + 1;
            i4--;
        }
        int i5 = i + 1;
        while (i5 < c && sVar.b(i2, i5)) {
            iArr[2] = iArr[2] + 1;
            i5++;
        }
        if (i5 == c) {
            throw a.a();
        }
        while (i5 < c && !sVar.b(i2, i5)) {
            iArr[3] = iArr[3] + 1;
            i5++;
        }
        if (i5 == c) {
            throw a.a();
        }
        while (i5 < c && sVar.b(i2, i5)) {
            iArr[4] = iArr[4] + 1;
            i5++;
        }
        return ((((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4]) / 6.0f;
    }

    public final g3[] a(Map<l1, ?> map) throws a {
        v6 v6Var = map == null ? null : (v6) map.get(l1.NEED_RESULT_POINT_CALLBACK);
        this.b = v6Var;
        return new h3(this.f5770a, v6Var).a(map);
    }

    public final int a(g3[] g3VarArr, g3 g3Var) throws a {
        this.c = g3VarArr[0];
        this.d = g3VarArr[1];
        g3 g3Var2 = g3VarArr[2];
        this.e = g3Var2;
        if (g3Var == null) {
            float b = g3Var2.b();
            float b2 = this.d.b();
            this.f = new g3((b - b2) + this.c.b(), (this.e.c() - this.d.c()) + this.c.c(), 6.0f);
        } else {
            this.f = g3Var;
        }
        float a2 = a(this.d, this.e, this.c, this.f5770a);
        if (a2 >= 1.0f) {
            int a3 = a(this.d, this.e, this.c, a2);
            if (a3 < 0 || a3 > 7) {
                throw a.a();
            }
            return a3;
        }
        throw a.a();
    }

    public final j2 a(int i) throws a {
        s a2;
        int i2 = i - 1;
        double d = g[i2];
        float cos = (float) ((Math.cos(0.7853981633974483d) * d) + 300.0d);
        float cos2 = (float) (300.0d - (d * Math.cos(0.7853981633974483d)));
        s a3 = a(this.f5770a, a(this.d, this.e, this.c, this.f, new u6(cos2, cos2), new u6(cos, cos2), new u6(cos2, cos), new u6(cos, cos)), 600);
        int i3 = h[i2];
        s sVar = new s(i3, i3);
        switch (i) {
            case 1:
                a2 = e8.a(sVar, a3, i3, 300.0d);
                break;
            case 2:
                a2 = h8.a(sVar, a3, i3, 300.0d);
                break;
            case 3:
                a2 = g8.a(sVar, a3, i3, 300.0d);
                break;
            case 4:
                a2 = d8.a(sVar, a3, i3, 300.0d);
                break;
            case 5:
                a2 = c8.a(sVar, a3, i3, 300.0d);
                break;
            case 6:
                a2 = f8.a(sVar, a3, i3, 300.0d);
                break;
            default:
                throw a.a();
        }
        return new j2(a2, new u6[]{this.c, this.d, this.e, this.f});
    }

    private static d6 a(u6 u6Var, u6 u6Var2, u6 u6Var3, u6 u6Var4, u6 u6Var5, u6 u6Var6, u6 u6Var7, u6 u6Var8) throws a {
        return d6.a(u6Var5.b(), u6Var5.c(), u6Var6.b(), u6Var6.c(), u6Var8.b(), u6Var8.c(), u6Var7.b(), u6Var7.c(), u6Var.b(), u6Var.c(), u6Var2.b(), u6Var2.c(), u6Var4.b(), u6Var4.c(), u6Var3.b(), u6Var3.c());
    }

    private static s a(s sVar, d6 d6Var, int i) throws a {
        return s3.a().a(sVar, i, i, d6Var, false);
    }

    private static int a(u6 u6Var, u6 u6Var2, u6 u6Var3, float f) throws a {
        float a2 = ((u6.a(u6Var, u6Var2) / f) + (u6.a(u6Var, u6Var3) / f)) / 2.0f;
        if (a2 >= 28.1f && a2 <= 31.1f) {
            return 1;
        }
        if (a2 >= 31.7f && a2 <= 34.7f) {
            return 2;
        }
        if (a2 >= 35.9f && a2 <= 38.9f) {
            return 3;
        }
        if (a2 >= 41.7f && a2 <= 44.7f) {
            return 4;
        }
        if (a2 >= 46.3f && a2 <= 49.3f) {
            return 5;
        }
        if (a2 < 54.4f || a2 > 57.4f) {
            return Math.round((a2 - 25.0f) / 4.0f);
        }
        return 6;
    }

    protected final float a(u6 u6Var, u6 u6Var2, u6 u6Var3, s sVar) throws a {
        float a2 = a((int) u6Var.b(), (int) u6Var.c(), sVar);
        float a3 = a((int) u6Var2.b(), (int) u6Var2.c(), sVar);
        float a4 = a((int) u6Var3.b(), (int) u6Var3.c(), sVar);
        return (((((a2 + a3) + a4) + b((int) u6Var.c(), (int) u6Var.b(), sVar)) + b((int) u6Var2.c(), (int) u6Var2.b(), sVar)) + b((int) u6Var3.c(), (int) u6Var3.b(), sVar)) / 6.0f;
    }

    private float a(int i, int i2, s sVar) throws a {
        int e = sVar.e();
        int[] iArr = new int[5];
        for (int i3 = 0; i3 < 5; i3++) {
            iArr[i3] = 0;
        }
        int i4 = i;
        while (i4 >= 0 && sVar.b(i4, i2)) {
            iArr[2] = iArr[2] + 1;
            i4--;
        }
        if (i4 >= 0) {
            while (i4 >= 0 && !sVar.b(i4, i2)) {
                iArr[1] = iArr[1] + 1;
                i4--;
            }
            if (i4 >= 0) {
                while (i4 >= 0 && sVar.b(i4, i2)) {
                    iArr[0] = iArr[0] + 1;
                    i4--;
                }
                int i5 = i + 1;
                while (i5 < e && sVar.b(i5, i2)) {
                    iArr[2] = iArr[2] + 1;
                    i5++;
                }
                if (i5 != e) {
                    while (i5 < e && !sVar.b(i5, i2)) {
                        iArr[3] = iArr[3] + 1;
                        i5++;
                    }
                    if (i5 != e) {
                        while (i5 < e && sVar.b(i5, i2)) {
                            iArr[4] = iArr[4] + 1;
                            i5++;
                        }
                        return ((((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4]) / 6.0f;
                    }
                    throw a.a();
                }
                throw a.a();
            }
            throw a.a();
        }
        throw a.a();
    }
}
