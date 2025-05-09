package defpackage;

import com.google.gson.annotations.SerializedName;
import com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LanguageUtil;
import java.io.Serializable;

/* loaded from: classes3.dex */
public class cfe implements Serializable, BaseViewBeanApi {
    private static final long serialVersionUID = 7211266658293312319L;

    /* renamed from: a, reason: collision with root package name */
    @SerializedName("mAge")
    private int f676a;

    @SerializedName("mModifyTime")
    private long aa;

    @SerializedName("mLeftUpperLimbMuscleMass")
    private double ab;

    @SerializedName("mPole")
    private int ac;

    @SerializedName("mMuscleMass")
    private double ad;

    @SerializedName("mRasm")
    private double ae;

    @SerializedName("mRightFootFatRate")
    private double af;

    @SerializedName("mPressureIndex")
    private double ah;

    @SerializedName("mProtein")
    private double ai;

    @SerializedName("mRightLowerLimbMuscleMass")
    private double aj;

    @SerializedName("mRightHandFatRate")
    private double ak;

    @SerializedName("mRightLowerLimbFatMass")
    private double al;

    @SerializedName("mRightUpperLimbFatMass")
    private double am;

    @SerializedName("mRightUpperLimbMuscleMass")
    private double an;

    @SerializedName("mTotalWater")
    private double ao;

    @SerializedName("mSkeletalMuscle")
    private double ap;

    @SerializedName("mTotalWaterRate")
    private double aq;

    @SerializedName("mSex")
    private byte ar;

    @SerializedName("mTrunkFatMass")
    private double as;

    @SerializedName("mTrunkMuscleMass")
    private double at;

    @SerializedName("mWaistToHipRatioUser")
    private double au;

    @SerializedName("mWaistToHipRatio")
    private double av;

    @SerializedName("mVisceraFatLevel")
    private double aw;

    @SerializedName("mTrunkFatRate")
    private double ax;

    @SerializedName("resistance")
    private double ay;

    @SerializedName("mWeight")
    private double az;

    @SerializedName("mBasalMetabolicRate")
    private double b;

    @SerializedName("mWeightEndTime")
    private long bb;

    @SerializedName("mWeighTime")
    private long bc;

    @SerializedName("mBodyAge")
    private double c;

    @SerializedName("mBodyMassIndex")
    private double d;

    @SerializedName("mBodyScore")
    private double e;

    @SerializedName("mClientId")
    private int f;

    @SerializedName("mBodyShape")
    private double g;

    @SerializedName("mBodyType")
    private double h;

    @SerializedName("mDataType")
    private int i;

    @SerializedName("mDeviceName")
    private String j;

    @SerializedName("mFatBalance")
    private double l;

    @SerializedName("mDeviceUuid")
    private String m;

    @SerializedName("mFatMass")
    private double n;

    @SerializedName("mFatRate")
    private double o;

    @SerializedName("mIsManual")
    private boolean q;

    @SerializedName("mInorganicSalt")
    private double r;

    @SerializedName("mHeartRate")
    private double s;

    @SerializedName("mHeight")
    private int t;

    @SerializedName("mLeftUpperLimbFatMass")
    private double u;

    @SerializedName("mLeftLowerLimbFatMass")
    private double v;

    @SerializedName("mLeftLowerLimbMuscleMass")
    private double w;

    @SerializedName("mLeftHandFatRate")
    private double x;

    @SerializedName("mLeftFootFatRate")
    private double y;

    @SerializedName("mMuscleBalance")
    private double z;

    @SerializedName("mResistanceArray")
    private double[] ag = new double[6];

    @SerializedName("mHfResistanceArray")
    private double[] p = new double[6];

    @SerializedName("mFreq")
    private int k = 1;

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double getDoubleOrIntLevelByType(int i) {
        return -1.0d;
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public int getFractionDigitByType(int i) {
        return 1;
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public boolean isNewScaleType() {
        return false;
    }

    public String n() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public String m() {
        return this.m;
    }

    public void e(String str) {
        this.m = str;
    }

    public double ax() {
        if (Double.isNaN(this.az) || Double.isInfinite(this.az)) {
            return 0.0d;
        }
        return ((Double) cpt.d(Double.valueOf(this.az))).doubleValue();
    }

    public void ae(double d) {
        this.az = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double a() {
        return ((Double) cpt.d(Double.valueOf(this.o))).doubleValue();
    }

    public void e(double d) {
        this.o = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double c() {
        return ((Double) cpt.d(Double.valueOf(this.n))).doubleValue();
    }

    public void a(double d) {
        this.n = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double ae() {
        return ((Double) cpt.d(Double.valueOf(this.ay))).doubleValue();
    }

    public void y(double d) {
        this.ay = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double al() {
        return ((Double) cpt.d(Double.valueOf(this.ao))).doubleValue();
    }

    public void aa(double d) {
        this.ao = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double ap() {
        return this.aq;
    }

    public void ai(double d) {
        this.aq = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double s() {
        return ((Double) cpt.d(Double.valueOf(this.aw))).doubleValue();
    }

    public void l(double d) {
        this.aw = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double i() {
        return ((Double) cpt.d(Double.valueOf(this.r))).doubleValue();
    }

    public void j(double d) {
        this.r = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double j() {
        return ((Double) cpt.d(Double.valueOf(this.d))).doubleValue();
    }

    public void c(double d) {
        this.d = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double d() {
        return ((Double) cpt.d(Double.valueOf(this.b))).doubleValue();
    }

    public void d(double d) {
        this.b = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double z() {
        return ((Double) cpt.d(Double.valueOf(this.ad))).doubleValue();
    }

    public void q(double d) {
        this.ad = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double ab() {
        return ((Double) cpt.d(Double.valueOf(this.ai))).doubleValue();
    }

    public void t(double d) {
        this.ai = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double h() {
        return ((Double) cpt.d(Double.valueOf(this.e))).doubleValue();
    }

    public void g(double d) {
        this.e = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double b() {
        return ((Double) cpt.d(Double.valueOf(this.c))).doubleValue();
    }

    public void b(double d) {
        this.c = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public double p() {
        return this.s;
    }

    public void n(double d) {
        this.s = d;
    }

    public double ad() {
        return this.ah;
    }

    public void s(double d) {
        this.ah = d;
    }

    public long au() {
        return ((Long) cpt.d(Long.valueOf(this.bc))).longValue();
    }

    public void b(long j) {
        this.bc = ((Long) cpt.d(Long.valueOf(j))).longValue();
    }

    public long av() {
        return ((Long) cpt.d(Long.valueOf(this.bb))).longValue();
    }

    public void d(long j) {
        this.bb = ((Long) cpt.d(Long.valueOf(j))).longValue();
    }

    public int l() {
        return this.i;
    }

    public void a(int i) {
        this.i = i;
    }

    public double aj() {
        return ((Double) cpt.d(Double.valueOf(this.ap))).doubleValue();
    }

    public void ad(double d) {
        this.ap = ((Double) cpt.d(Double.valueOf(d))).doubleValue();
    }

    public int aa() {
        return this.ac;
    }

    public void j(int i) {
        this.ac = i;
    }

    public double ah() {
        return this.aj;
    }

    public void v(double d) {
        this.aj = d;
    }

    public double x() {
        return this.w;
    }

    public void m(double d) {
        this.w = d;
    }

    public double ak() {
        return this.an;
    }

    public void ab(double d) {
        this.an = d;
    }

    public double y() {
        return this.ab;
    }

    public void r(double d) {
        this.ab = d;
    }

    public double aq() {
        return this.at;
    }

    public void z(double d) {
        this.at = d;
    }

    public double ai() {
        return this.al;
    }

    public void w(double d) {
        this.al = d;
    }

    public double u() {
        return this.v;
    }

    public void o(double d) {
        this.v = d;
    }

    public double am() {
        return this.am;
    }

    public void u(double d) {
        this.am = d;
    }

    public double v() {
        return this.u;
    }

    public void k(double d) {
        this.u = d;
    }

    public double ar() {
        return this.as;
    }

    public void ac(double d) {
        this.as = d;
    }

    public double as() {
        return this.av;
    }

    public void ag(double d) {
        this.av = d;
    }

    public void ah(double d) {
        this.au = d;
    }

    public double ao() {
        return this.au;
    }

    public void e(int i) {
        this.k = i;
    }

    public double[] q() {
        double[] dArr = this.p;
        return dArr != null ? (double[]) dArr.clone() : new double[6];
    }

    public void e(double[] dArr) {
        if (dArr != null) {
            this.p = (double[]) dArr.clone();
        }
    }

    public double[] ag() {
        double[] dArr = this.ag;
        return dArr != null ? (double[]) dArr.clone() : new double[6];
    }

    public void c(double[] dArr) {
        if (dArr != null) {
            this.ag = (double[]) dArr.clone();
        }
    }

    public double af() {
        return this.ae;
    }

    public void x(double d) {
        this.ae = d;
    }

    public double g() {
        return this.h;
    }

    public void f(double d) {
        this.h = d;
    }

    public double f() {
        return this.g;
    }

    public void h(double d) {
        this.g = d;
    }

    public double o() {
        return this.l;
    }

    public void i(double d) {
        this.l = d;
    }

    public double ac() {
        return this.z;
    }

    public void p(double d) {
        this.z = d;
    }

    public int e() {
        return ((Integer) cpt.d(Integer.valueOf(this.f676a))).intValue();
    }

    public void d(int i) {
        this.f676a = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public int t() {
        return ((Integer) cpt.d(Integer.valueOf(this.t))).intValue();
    }

    public void c(int i) {
        this.t = ((Integer) cpt.d(Integer.valueOf(i))).intValue();
    }

    public byte an() {
        return ((Byte) cpt.d(Byte.valueOf(this.ar))).byteValue();
    }

    public void a(byte b) {
        this.ar = ((Byte) cpt.d(Byte.valueOf(b))).byteValue();
    }

    public int k() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public long w() {
        return this.aa;
    }

    public void e(long j) {
        this.aa = j;
    }

    public boolean r() {
        return this.q;
    }

    public void b(boolean z) {
        this.q = z;
    }

    public boolean equals(Object obj) {
        return (obj instanceof cfe) && ((cfe) obj).au() == this.bc;
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public boolean isVisible(int i, boolean z) {
        if (i != 31) {
            return true;
        }
        if (z || !dks.a(this.e)) {
            return false;
        }
        return LanguageUtil.m(BaseApplication.getContext()) || LanguageUtil.p(BaseApplication.getContext());
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return "WeightBean{Weight=" + this.az + ", FatRate=" + this.o + ", FatMass=" + this.n + ", resistance=" + this.ay + ", TotalWater=" + this.ao + ", TotalWaterRate=" + this.aq + ", VisceraFatLevel=" + this.aw + ", InorganicSalt=" + this.r + ", BodyMassIndex=" + this.d + ", BasalMetabolicRate=" + this.b + ", MuscleMass=" + this.ad + ", Protein=" + this.ai + ", BodyScore=" + this.e + ", BodyAge=" + this.c + ", WeighTime=" + this.bc + ", WeightEndTime=" + this.bb + ", DataType=" + this.i + ", Pole=" + this.ac + ", deviceUuid=" + this.m + ", IsManual=" + this.q + '}';
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getWeightScaleProductId() {
        return "";
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String getStringLevelByType(int i) {
        return "";
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public String[] getStringArrayLevelByType(int i) {
        return new String[0];
    }

    @Override // com.huawei.health.device.fatscale.datatypes.BaseViewBeanApi
    public double[] getDoubleArrayLevelByType(int i) {
        return new double[0];
    }
}
