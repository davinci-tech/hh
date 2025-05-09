package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.hihealth.ResultUtils;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class qkg implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mBmi")
    private double f16454a;

    @SerializedName("mBodyScore")
    private double b;

    @SerializedName("deviceUuid")
    private String c;

    @SerializedName("mBoneMineral")
    private double d;

    @SerializedName("mBmr")
    private double e;

    @SerializedName("mMetaData")
    private String f;

    @SerializedName("mClientId")
    private int g;

    @SerializedName("mFatLevel")
    private double h;

    @SerializedName("mDeviceType")
    private int i;

    @SerializedName("mDeviceId")
    private int j;

    @SerializedName("mMuscles")
    private double k;

    @SerializedName("type")
    private int l;

    @SerializedName("mProtein")
    private double n;

    @SerializedName("mWaterRate")
    private double x;

    @SerializedName("mWater")
    private double y;

    @SerializedName("mTime")
    private long o = 0;

    @SerializedName("mTimeExtra")
    private long m = 0;

    @SerializedName("mValueFirst")
    private double r = 0.0d;

    @SerializedName("mValueSecond")
    private double p = 0.0d;

    @SerializedName("mValueThird")
    private double w = 0.0d;

    @SerializedName("mValueForth")
    private double s = 0.0d;

    @SerializedName("mValueFifth")
    private double q = 0.0d;

    @SerializedName("mValueSixth")
    private String t = "";

    public boolean a(qkg qkgVar) {
        if (qkgVar == null) {
            return false;
        }
        return b().equals(qkgVar.b());
    }

    private String b() {
        return "HealthData{mTime=" + this.o + ", mValueFirst=" + this.r + ", mValueSecond=" + this.p + ", mValueThird=" + this.w + ", mValueForth=" + this.s + ", mValueFifth=" + this.q + ", mWater=" + this.y + ", mWaterRate=" + this.x + ", mFatLevel=" + this.h + ", mBoneMineral=" + this.d + ", mBmi=" + this.f16454a + ", mBmr=" + this.e + ", mMuscles=" + this.k + ", mProtein=" + this.n + ", mBodyScore=" + this.b + ", mDeviceId=" + this.j + ", mMetaData='" + this.f + "', mDeviceType=" + this.i + ", deviceUuid='" + this.c + "', mClientId=" + this.g + '}';
    }

    public String toString() {
        return "HealthData-->[mTime =" + this.o + "; temeextra = " + this.m + "; mValue1 = " + this.r + "; mValue2 = " + this.p + "; mValue3 = " + this.w + "; mValue4 = " + this.s + "; mValue5 = " + this.q + "; type = " + this.l + "]";
    }

    public String j() {
        return this.c;
    }

    public void d(String str) {
        this.c = (String) ResultUtils.a(str);
    }

    public String f() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public void a(long j) {
        this.o = j;
    }

    public long h() {
        return ((Long) qrn.b(Long.valueOf(this.o))).longValue();
    }

    public void b(long j) {
        this.m = j;
    }

    public long i() {
        return ((Long) qrn.b(Long.valueOf(this.m))).longValue();
    }

    public void c(double d) {
        this.r = d;
    }

    public double o() {
        return ((Double) qrn.b(Double.valueOf(this.r))).doubleValue();
    }

    public void b(double d) {
        this.p = d;
    }

    public double m() {
        return ((Double) qrn.b(Double.valueOf(this.p))).doubleValue();
    }

    public void a(double d) {
        this.w = d;
    }

    public double n() {
        return ((Double) qrn.b(Double.valueOf(this.w))).doubleValue();
    }

    public void d(double d) {
        this.s = d;
    }

    public double k() {
        return ((Double) qrn.b(Double.valueOf(this.s))).doubleValue();
    }

    public void e(double d) {
        this.q = d;
    }

    public double l() {
        return ((Double) qrn.b(Double.valueOf(this.q))).doubleValue();
    }

    public void l(double d) {
        this.y = d;
    }

    public void k(double d) {
        this.x = d;
    }

    public void h(double d) {
        this.h = d;
    }

    public void g(double d) {
        this.d = d;
    }

    public void i(double d) {
        this.f16454a = d;
    }

    public void f(double d) {
        this.e = d;
    }

    public void m(double d) {
        this.k = d;
    }

    public void n(double d) {
        this.n = d;
    }

    public void j(double d) {
        this.b = d;
    }

    public int e() {
        return this.j;
    }

    public void c(int i) {
        this.j = i;
    }

    public int c() {
        return this.i;
    }

    public void e(int i) {
        this.i = i;
    }

    public int a() {
        return this.g;
    }

    public void b(int i) {
        this.g = i;
    }

    public int g() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }
}
