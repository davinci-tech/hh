package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class cps {
    private wn d;
    private int e;

    public cps(float f, float f2, int i, int i2, int i3, double d) {
        this(f, f2, i, i2, i3, d, 0.0d);
    }

    public cps(float f, float f2, int i, int i2, int i3, double d, double d2) {
        wn wnVar = new wn(f, f2, i, cgs.e(i3, i2), cgs.a(i3), (float) d, (d2 < 0.03d || d2 > 0.6d) ? 0.0f : (float) d2);
        this.d = wnVar;
        int e = wnVar.e();
        this.e = e;
        if (e < 0) {
            ReleaseLogUtil.d("R_Weight_PluginDevice_HwWeightAlgorithm", "HwWeightAlgorithm pole four illegal arguments, check code :", Integer.valueOf(e));
        }
    }

    public cps(float f, float f2, int i, int i2, int i3, double[] dArr) {
        this(f, f2, i, i2, i3, dArr, 0.0d);
    }

    public cps(float f, float f2, int i, int i2, int i3, double[] dArr, double d) {
        int e = cgs.e(i3, i2);
        int a2 = cgs.a(i3);
        if (dArr == null || dArr.length < 6) {
            this.d = new wn(f, f2, i, e, a2, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
            LogUtil.h("PluginDevice_HwWeightAlgorithm", "can not initialize Algorithm with illegal resistArray");
        } else {
            this.d = new wn(f, f2, i, e, a2, (float) dArr[1], (float) dArr[2], (float) dArr[3], (float) dArr[4], (float) dArr[5], (float) dArr[0], (d < 0.03d || d > 0.6d) ? 0.0f : (float) d);
        }
        int e2 = this.d.e();
        this.e = e2;
        if (e2 < 0) {
            ReleaseLogUtil.d("R_Weight_PluginDevice_HwWeightAlgorithm", "HwWeightAlgorithm pole eight illegal arguments, check code :", Integer.valueOf(e2));
        }
    }

    public cps(float f, float f2, int i, int i2, int i3, int i4, double[] dArr, int i5, double[] dArr2) {
        this(f, f2, i, i2, i3, i4, dArr, 0.0d, i5, dArr2);
    }

    public cps(float f, float f2, int i, int i2, int i3, int i4, double[] dArr, double d, int i5, double[] dArr2) {
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12;
        float f13;
        float f14;
        int i6;
        int i7;
        float f15;
        if (dArr == null || dArr.length < 6) {
            f3 = 0.0f;
            f4 = 0.0f;
            f5 = 0.0f;
            f6 = 0.0f;
            f7 = 0.0f;
            f8 = 0.0f;
        } else {
            float f16 = (float) dArr[1];
            float f17 = (float) dArr[2];
            float f18 = (float) dArr[3];
            float f19 = (float) dArr[4];
            float f20 = (float) dArr[5];
            f8 = (float) dArr[0];
            f6 = f19;
            f4 = f17;
            f5 = f18;
            f7 = f20;
            f3 = f16;
        }
        if (dArr2 == null || dArr2.length < 6) {
            f9 = 0.0f;
            f10 = 0.0f;
            f11 = 0.0f;
            f12 = 0.0f;
            f13 = 0.0f;
            f14 = 0.0f;
        } else {
            float f21 = (float) dArr2[1];
            float f22 = (float) dArr2[2];
            float f23 = (float) dArr2[3];
            float f24 = (float) dArr2[4];
            float f25 = (float) dArr2[5];
            f9 = f21;
            f14 = (float) dArr2[0];
            f10 = f22;
            f11 = f23;
            f12 = f24;
            f13 = f25;
        }
        if (d < 0.03d || d > 0.6d) {
            i6 = i2;
            i7 = i3;
            f15 = 0.0f;
        } else {
            i6 = i2;
            i7 = i3;
            f15 = (float) d;
        }
        wn wnVar = new wn(f, f2, i, cgs.e(i7, i6), cgs.a(i3), i4, i5, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15);
        this.d = wnVar;
        int e = wnVar.e();
        this.e = e;
        if (e < 0) {
            ReleaseLogUtil.d("R_Weight_PluginDevice_HwWeightAlgorithm", "HwWeightAlgorithm pole eight illegal arguments, check code :", Integer.valueOf(e));
        }
    }

    public static boolean b(double[] dArr) {
        if (dArr != null && dArr.length == 6) {
            double d = dArr[0];
            if (d != 0.0d) {
                boolean z = d != 0.0d;
                for (int i = 1; i < dArr.length; i++) {
                    if (dArr[i] != 0.0d) {
                        z = false;
                    }
                }
                return z;
            }
        }
        LogUtil.a("PluginDevice_HwWeightAlgorithm", "isFourElectrode illegal resistance array");
        return false;
    }

    public static double[] d(double[] dArr) {
        if (dArr == null || dArr.length == 0) {
            return new double[0];
        }
        int length = dArr.length;
        double[] dArr2 = new double[length];
        System.arraycopy(dArr, 0, dArr2, 0, dArr.length);
        for (int i = 0; i < length; i++) {
            dArr2[i] = dArr2[i] / 10.0d;
        }
        return dArr2;
    }

    public float aj() {
        if (this.e >= 0) {
            return this.d.am();
        }
        return -1.0f;
    }

    public float ak() {
        if (this.e >= 0) {
            return this.d.aq() * 100.0f;
        }
        return -1.0f;
    }

    public float al() {
        if (this.e >= 0) {
            return this.d.ao() * 100.0f;
        }
        return -1.0f;
    }

    public float u() {
        if (this.e >= 0) {
            return this.d.x();
        }
        return -1.0f;
    }

    public float w() {
        if (this.e >= 0) {
            return this.d.v();
        }
        return -1.0f;
    }

    public float m() {
        if (this.e >= 0) {
            return this.d.n();
        }
        return -1.0f;
    }

    public float k() {
        if (this.e >= 0) {
            return this.d.o();
        }
        return -1.0f;
    }

    public float x() {
        if (this.e >= 0) {
            return this.d.w();
        }
        return -1.0f;
    }

    public float v() {
        if (this.e >= 0) {
            return this.d.u();
        }
        return -1.0f;
    }

    public float n() {
        if (this.e >= 0) {
            return this.d.l() * 100.0f;
        }
        return -1.0f;
    }

    public float h() {
        if (this.e >= 0) {
            return this.d.j() * 100.0f;
        }
        return -1.0f;
    }

    public float c() {
        if (this.e >= 0) {
            return this.d.a();
        }
        return -1.0f;
    }

    public float ac() {
        if (this.e >= 0) {
            return this.d.ad();
        }
        return -1.0f;
    }

    public float q() {
        if (this.e >= 0) {
            return this.d.s();
        }
        return -1.0f;
    }

    public float ag() {
        if (this.e >= 0) {
            return this.d.ah();
        }
        return -1.0f;
    }

    public float t() {
        if (this.e >= 0) {
            return this.d.r();
        }
        return -1.0f;
    }

    public float aq() {
        if (this.e >= 0) {
            return this.d.aj();
        }
        return -1.0f;
    }

    public float aa() {
        if (this.e >= 0) {
            return this.d.ab();
        }
        return -1.0f;
    }

    public float r() {
        if (this.e >= 0) {
            return this.d.q();
        }
        return -1.0f;
    }

    public float ab() {
        if (this.e >= 0) {
            return this.d.ag();
        }
        return -1.0f;
    }

    public float s() {
        if (this.e >= 0) {
            return this.d.t();
        }
        return -1.0f;
    }

    public float an() {
        if (this.e >= 0) {
            return this.d.ak();
        }
        return -1.0f;
    }

    public float a() {
        if (this.e >= 0) {
            return this.d.g();
        }
        return -1.0f;
    }

    public float e() {
        if (this.e >= 0) {
            return this.d.i();
        }
        return -1.0f;
    }

    public float ap() {
        if (this.e >= 0) {
            return this.d.ar();
        }
        return -1.0f;
    }

    public float af() {
        if (this.e >= 0) {
            return this.d.al();
        }
        return -1.0f;
    }

    public float ah() {
        if (this.e >= 0) {
            return this.d.ai();
        }
        return -1.0f;
    }

    public float y() {
        if (this.e >= 0) {
            return this.d.z();
        }
        return -1.0f;
    }

    public float as() {
        if (this.e >= 0) {
            return this.d.ap();
        }
        return -1.0f;
    }

    public float ao() {
        if (this.e >= 0) {
            return this.d.as();
        }
        return -1.0f;
    }

    public float ai() {
        if (this.e >= 0) {
            return this.d.af();
        }
        return -1.0f;
    }

    public float ae() {
        if (this.e >= 0) {
            return this.d.ae();
        }
        return -1.0f;
    }

    public float b() {
        if (this.e >= 0) {
            return this.d.d();
        }
        return -1.0f;
    }

    public float d() {
        if (this.e >= 0) {
            return this.d.b();
        }
        return -1.0f;
    }

    public float i() {
        if (this.e >= 0) {
            return this.d.h();
        }
        return -1.0f;
    }

    public float f() {
        if (this.e >= 0) {
            return this.d.f();
        }
        return -1.0f;
    }

    public float g() {
        if (this.e >= 0) {
            return this.d.c();
        }
        return -1.0f;
    }

    public float p() {
        if (this.e >= 0) {
            return this.d.y();
        }
        return -1.0f;
    }

    public float j() {
        if (this.e >= 0) {
            return this.d.k();
        }
        return -1.0f;
    }

    public float z() {
        if (this.e >= 0) {
            return this.d.ac() * 100.0f;
        }
        return -1.0f;
    }

    public float o() {
        if (this.e >= 0) {
            return this.d.m() * 100.0f;
        }
        return -1.0f;
    }

    public float ad() {
        if (this.e >= 0) {
            return this.d.aa() * 100.0f;
        }
        return -1.0f;
    }

    public float l() {
        if (this.e >= 0) {
            return this.d.p() * 100.0f;
        }
        return -1.0f;
    }

    public float am() {
        if (this.e >= 0) {
            return this.d.an() * 100.0f;
        }
        return -1.0f;
    }
}
