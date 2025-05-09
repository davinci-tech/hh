package defpackage;

import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.open.data.model.IWeightAndFatRateData;

/* loaded from: classes3.dex */
public class ckm extends HealthData implements IWeightAndFatRateData {
    private static final long serialVersionUID = 191744998741984175L;

    /* renamed from: a, reason: collision with root package name */
    private double f771a;
    private float b;
    private double c;
    private double d;
    private double f;
    private double j;
    private boolean l;
    private boolean m;
    private double o;
    private double q;
    private float r;
    private double s;
    private double t;
    private double[] k = new double[6];
    private int h = 1;
    private double[] g = new double[6];
    private String p = "";
    private String e = "";
    private boolean n = false;
    private boolean i = false;

    public double f() {
        return this.j;
    }

    public void g(double d) {
        this.j = d;
    }

    public boolean s() {
        return ((Boolean) cpt.d(Boolean.valueOf(this.n))).booleanValue();
    }

    public void c(boolean z) {
        this.n = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    public boolean q() {
        return ((Boolean) cpt.d(Boolean.valueOf(this.m))).booleanValue();
    }

    public void b(boolean z) {
        this.m = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    @Override // com.huawei.health.device.open.data.model.IWeightAndFatRateData
    public float getWeight() {
        return ((Float) cpt.d(Float.valueOf(this.r))).floatValue();
    }

    @Override // com.huawei.health.device.open.data.model.IWeightAndFatRateData
    public void setWeight(float f) {
        this.r = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    @Override // com.huawei.health.device.open.data.model.IWeightAndFatRateData
    public float getBodyFatRat() {
        return ((Float) cpt.d(Float.valueOf(this.b))).floatValue();
    }

    public double k() {
        return ((Double) cpt.d(Double.valueOf(this.q))).doubleValue();
    }

    public void f(double d) {
        this.q = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    @Override // com.huawei.health.device.open.data.model.IWeightAndFatRateData
    public void setBodyFatRat(float f) {
        this.b = ((Float) cpt.d(Float.valueOf(f))).floatValue();
    }

    public double[] o() {
        return (double[]) cpt.d(this.k);
    }

    public int g() {
        return this.h;
    }

    public void d(int i) {
        this.h = i;
    }

    public double[] i() {
        return (double[]) cpt.d(this.g);
    }

    public double c(int i) {
        double[] dArr = this.k;
        if (dArr.length <= 0 || i >= dArr.length) {
            return 0.0d;
        }
        return ((Double) cpt.d(Double.valueOf(dArr[i]))).doubleValue();
    }

    public void b(int i, double d) {
        if (i >= 0) {
            double[] dArr = this.k;
            if (dArr.length > i) {
                dArr[i] = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
            }
        }
    }

    public double b(int i) {
        double[] dArr = this.g;
        if (dArr.length <= 0 || i >= dArr.length) {
            return 0.0d;
        }
        return ((Double) cpt.d(Double.valueOf(dArr[i]))).doubleValue();
    }

    public void c(int i, double d) {
        if (i >= 0) {
            double[] dArr = this.g;
            if (dArr.length > i) {
                dArr[i] = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
            }
        }
    }

    public boolean t() {
        return ((Boolean) cpt.d(Boolean.valueOf(this.l))).booleanValue();
    }

    public void e(boolean z) {
        this.l = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    public String n() {
        return this.p;
    }

    public void e(String str) {
        this.p = str;
    }

    public String j() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public double b() {
        return ((Double) cpt.d(Double.valueOf(this.f771a))).doubleValue();
    }

    public void b(double d) {
        this.f771a = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double h() {
        return ((Double) cpt.d(Double.valueOf(this.s))).doubleValue();
    }

    public void c(double d) {
        this.s = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double a() {
        return ((Double) cpt.d(Double.valueOf(this.c))).doubleValue();
    }

    public void a(double d) {
        this.c = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double m() {
        return this.t;
    }

    public void h(double d) {
        this.t = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double e() {
        return ((Double) cpt.d(Double.valueOf(this.f))).doubleValue();
    }

    public void d(double d) {
        this.f = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double l() {
        return ((Double) cpt.d(Double.valueOf(this.o))).doubleValue();
    }

    public void i(double d) {
        this.o = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double d() {
        return ((Double) cpt.d(Double.valueOf(this.d))).doubleValue();
    }

    public void e(double d) {
        this.d = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public void d(boolean z) {
        this.i = ((Boolean) cpt.d(Boolean.valueOf(z))).booleanValue();
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    public String toString() {
        return "WeightAndFatRateData [ weight=" + this.r + "body_fat_rate=" + this.b + "]";
    }

    @Override // com.huawei.health.device.open.data.model.HealthData
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public ckm mo76clone() {
        if (super.mo76clone() instanceof ckm) {
            return (ckm) super.mo76clone();
        }
        return new ckm();
    }
}
