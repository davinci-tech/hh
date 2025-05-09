package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes3.dex */
public class crs {

    /* renamed from: a, reason: collision with root package name */
    private static final String f11418a = "R_Weight_" + crs.class.getName();
    private static final String c = "com.huawei.health.device.util.WeightAlgorithm";
    private float b;
    private oi d;
    private int e;
    private int f;
    private float g;
    private byte h;
    private float i;
    private float j;

    public crs(float f, float f2, byte b, int i, float f3, double d) {
        this.i = f;
        this.j = f2;
        this.h = b;
        this.e = i;
        this.b = f3;
        oi oiVar = new oi();
        this.d = oiVar;
        if (d == 0.0d) {
            oiVar.b(f, f2, b, i, o());
            this.f = this.d.e(f, f2, b, i, o());
        } else {
            float f4 = (float) d;
            oiVar.b(f, f2, b, i, f4);
            this.f = this.d.e(f, f2, b, i, f4);
        }
        ReleaseLogUtil.e(f11418a, "WeightAlgorithm mCheckCode == ", Integer.valueOf(this.f));
    }

    public float f() {
        return this.g;
    }

    private float o() {
        if (oi.b(this.i, this.h, this.j, this.e, this.b) < 0) {
            LogUtil.h(c, "calcResistance()");
            return -1.0f;
        }
        float b = oi.b(this.i, this.h, this.j, this.e, this.b);
        this.g = b;
        LogUtil.a(c, "get resistance = ", Float.valueOf(b));
        return this.g;
    }

    public float g() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.h();
        } catch (Exception unused) {
            LogUtil.b(c, "getTFR() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 0.0f && f <= 100.0f) {
            return f;
        }
        LogUtil.h(c, "getTFR()");
        return -1.0f;
    }

    public float e() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.c();
        } catch (Exception unused) {
            LogUtil.b(c, "getBMR() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 0.0f) {
            return f;
        }
        LogUtil.h(c, "getBMR()");
        return -1.0f;
    }

    public float k() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.f();
        } catch (Exception unused) {
            LogUtil.b(c, "getVFR() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 1.0d && f <= 59.0f) {
            return f;
        }
        LogUtil.h(c, "getVFR()");
        return -1.0f;
    }

    public float i() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.j();
        } catch (Exception unused) {
            LogUtil.b(c, "getSLM() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 1.0d && f <= 150.0f) {
            return f;
        }
        LogUtil.h(c, "getSLM()");
        return -1.0f;
    }

    public float c() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.d();
        } catch (Exception unused) {
            LogUtil.b(c, "getMSW() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 1.0d && f <= 4.0f) {
            return f;
        }
        LogUtil.h(c, "getMSW()");
        return -1.0f;
    }

    public float b() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.b();
        } catch (Exception unused) {
            LogUtil.b(c, "getPM() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 0.0f && f <= this.j) {
            return f;
        }
        LogUtil.h(c, "getPM()");
        return -1.0f;
    }

    public float d() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.a();
        } catch (Exception unused) {
            LogUtil.b(c, "getBodyAge() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 18.0f && f <= 99.0f) {
            return f;
        }
        LogUtil.h(c, "getBodyAge()");
        return -1.0f;
    }

    public float j() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.i();
        } catch (Exception unused) {
            LogUtil.b(c, "getScore() getScore Exception.");
            f = -1.0f;
        }
        if (f >= 0.0f && f <= 100.0f) {
            return f;
        }
        LogUtil.h(c, "getScore()");
        return -1.0f;
    }

    public float a() {
        if (this.i != 0.0f) {
            if (this.j >= 0.0f) {
                return new BigDecimal(((r2 * 100.0f) * 100.0f) / (r0 * r0)).setScale(1, RoundingMode.HALF_UP).floatValue();
            }
        }
        LogUtil.h(c, "getBMI()");
        return -1.0f;
    }

    public float h() {
        float f;
        if (this.f < 0) {
            return -1.0f;
        }
        try {
            f = this.d.g();
        } catch (Exception unused) {
            LogUtil.b(c, "getSMM() occurred unknown exception");
            f = -1.0f;
        }
        if (f >= 1.0d && f <= 150.0f) {
            return f;
        }
        LogUtil.h(c, "getSMM()");
        return -1.0f;
    }
}
