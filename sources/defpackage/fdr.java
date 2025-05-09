package defpackage;

import health.compact.a.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class fdr extends fdi {
    protected int aa;
    protected int ab;
    protected int g;
    protected int k;
    protected int o;
    protected int r;
    protected int t;
    protected double u;
    protected int v;
    protected int w;
    protected int x;
    protected int y;
    protected int q = -1;
    protected int l = -1;
    protected double p = -1.0d;
    protected double m = -1.0d;
    protected double s = -1.0d;
    protected double n = -1.0d;
    private int z = -2;
    private int ar = -2;
    private int am = -2;
    private int ae = -2;
    private int ai = -2;
    private int ao = -2;
    private int ak = -2;
    private int ax = -2;
    private int ac = -2;
    private int aq = -2;
    private int al = -2;
    private int af = -2;
    private int ad = -2;
    private int ap = -2;
    private int ah = -2;
    private int ag = -2;
    private int aw = -2;
    private int aj = -2;
    private int au = -2;
    private int as = -2;
    private int at = -2;
    private int an = -2;

    private boolean ag(int i) {
        return i > 0 && i <= 100;
    }

    private boolean ah(int i) {
        return i > 0 && i <= 200;
    }

    private boolean ai(int i) {
        return i > 0 && i <= 80;
    }

    protected boolean c(int i) {
        return i > 0 && i <= 255;
    }

    public boolean ba() {
        return (this.z == -2 && this.ai == -2 && this.ac == -2 && this.ad == -2 && this.ar == -2 && this.am == -2 && this.ap == -2 && this.ah == -2 && this.ao == -2 && this.ak == -2 && this.aq == -2 && this.al == -2) ? false : true;
    }

    private boolean bg() {
        int i;
        int i2 = this.ar;
        return i2 > 0 && i2 <= (i = this.am) && i <= 255;
    }

    private boolean az() {
        int i;
        int i2 = this.ap;
        return i2 > 0 && i2 <= (i = this.ah) && i <= 80;
    }

    private boolean bh() {
        int i;
        int i2 = this.aq;
        return i2 > 0 && i2 <= (i = this.al) && i <= 200;
    }

    private boolean bd() {
        int i;
        int i2 = this.ao;
        return i2 > 0 && i2 <= (i = this.ak) && i <= 100;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1022318430:
                if (str.equals("TRUSLEEP_FIVE_HRV")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1285815879:
                if (str.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1627456622:
                if (str.equals("TRUSLEEP_FIVE_SpO2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1993820721:
                if (str.equals("TRUSLEEP_FIVE_BREATH_RATE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return ah(this.ac);
        }
        if (c == 1) {
            return c(this.z);
        }
        if (c == 2) {
            return ag(this.ai);
        }
        if (c == 3) {
            return ai(this.ad);
        }
        ReleaseLogUtil.a("R_Sleep_ScienceSleepData", "unknow itemType: ", str);
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean d(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1022318430:
                if (str.equals("TRUSLEEP_FIVE_HRV")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1285815879:
                if (str.equals("TRUSLEEP_FIVE_HEART_RATE")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1627456622:
                if (str.equals("TRUSLEEP_FIVE_SpO2")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1993820721:
                if (str.equals("TRUSLEEP_FIVE_BREATH_RATE")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return bh();
        }
        if (c == 1) {
            return bg();
        }
        if (c == 2) {
            return bd();
        }
        if (c == 3) {
            return az();
        }
        ReleaseLogUtil.a("R_Sleep_ScienceSleepData", "unknow itemType: ", str);
        return false;
    }

    public int r() {
        return this.z;
    }

    public void i(int i) {
        this.z = i;
    }

    public int am() {
        return this.ar;
    }

    public void x(int i) {
        this.ar = i;
    }

    public int ag() {
        return this.am;
    }

    public void r(int i) {
        this.am = i;
    }

    public int ab() {
        return this.ae;
    }

    public void k(int i) {
        this.ae = i;
    }

    public int s() {
        return this.ai;
    }

    public void f(int i) {
        this.ai = i;
    }

    public int as() {
        return this.ao;
    }

    public void w(int i) {
        this.ao = i;
    }

    public int af() {
        return this.ak;
    }

    public void s(int i) {
        this.ak = i;
    }

    public int av() {
        return this.ax;
    }

    public void ac(int i) {
        this.ax = i;
    }

    public int t() {
        return this.ac;
    }

    public void g(int i) {
        this.ac = i;
    }

    public int aj() {
        return this.aq;
    }

    public void t(int i) {
        this.aq = i;
    }

    public int ad() {
        return this.al;
    }

    public void o(int i) {
        this.al = i;
    }

    public int ac() {
        return this.af;
    }

    public void m(int i) {
        this.af = i;
    }

    public int p() {
        return this.ad;
    }

    public void a(int i) {
        this.ad = i;
    }

    public int an() {
        return this.ap;
    }

    public void p(int i) {
        this.ap = i;
    }

    public int z() {
        return this.ah;
    }

    public void l(int i) {
        this.ah = i;
    }

    public int u() {
        return this.ag;
    }

    public void j(int i) {
        this.ag = i;
    }

    public int ay() {
        return this.aa;
    }

    public void af(int i) {
        this.aa = i;
    }

    public int x() {
        return this.k;
    }

    public void n(int i) {
        this.k = i;
    }

    public int aw() {
        return this.x;
    }

    public void z(int i) {
        this.x = i;
    }

    public int al() {
        int i = this.q;
        if (i <= 0 || 255 <= i) {
            return -1;
        }
        return i;
    }

    public void v(int i) {
        this.q = i;
    }

    public int ae() {
        int i = this.l;
        if (i <= 0 || 255 <= i) {
            return -1;
        }
        return i;
    }

    public void q(int i) {
        this.l = i;
    }

    public Double ak() {
        double d = this.p;
        if (0.0d >= d || 100.0d < d) {
            return Double.valueOf(-1.0d);
        }
        return Double.valueOf(d);
    }

    public void b(Double d) {
        this.p = d.doubleValue();
    }

    public Double ai() {
        double d = this.m;
        if (0.0d >= d || 100.0d < d) {
            return Double.valueOf(-1.0d);
        }
        return Double.valueOf(d);
    }

    public void a(Double d) {
        this.m = d.doubleValue();
    }

    public Double ah() {
        double d = this.s;
        if (0.0d >= d || 255.0d <= d) {
            return Double.valueOf(-1.0d);
        }
        return Double.valueOf(d);
    }

    public void e(Double d) {
        this.s = d.doubleValue();
    }

    public double aa() {
        double d = this.n;
        if (0.0d >= d || 255.0d < d) {
            return -1.0d;
        }
        return d;
    }

    public void c(Double d) {
        this.n = d.doubleValue();
    }

    public boolean bb() {
        return this.i <= 0 && this.r > 0;
    }

    public int ao() {
        if (this.t < 0 || this.i <= 0) {
            return -1;
        }
        return Math.max((100 - v()) - ax(), 0);
    }

    public int ar() {
        return this.y;
    }

    public void ad(int i) {
        this.y = i;
    }

    public int ax() {
        if (this.x < 0 || this.i <= 0) {
            return -1;
        }
        return Math.round((this.x * 100.0f) / this.i);
    }

    public int v() {
        if (this.k < 0 || this.i <= 0) {
            return -1;
        }
        return Math.round((this.k * 100.0f) / this.i);
    }

    public int ap() {
        return this.r;
    }

    public void y(int i) {
        this.r = i;
    }

    public int aq() {
        return this.t;
    }

    public void u(int i) {
        this.t = i;
    }

    public int y() {
        return this.o;
    }

    public void h(int i) {
        this.o = i;
    }

    public int bc() {
        return this.w;
    }

    public void aa(int i) {
        this.w = i;
    }

    @Override // defpackage.fdi
    public int i() {
        if (k() <= 0 || c() == 0 || l() == 0 || b() == 0 || g() == 0) {
            return -1;
        }
        return this.f12460a;
    }

    public int w() {
        int i;
        int i2 = this.v;
        if (i2 < 0 || (i = this.t) < 0) {
            return -1;
        }
        return fcj.a(i2, i);
    }

    public double at() {
        return this.u;
    }

    public void a(double d) {
        this.u = d;
    }

    public int au() {
        return this.v;
    }

    public void ab(int i) {
        this.v = i;
    }

    @Override // defpackage.fdi
    public boolean o() {
        return super.o() || this.r > 0;
    }

    @Override // defpackage.fdi
    public String toString() {
        return "ScienceSleepData{mRemSleepTime=" + this.t + ", mDeepSleepContinuity=" + this.o + ", mWakeupCount=" + this.w + ", mValidData=" + this.u + ", mSnoreFreq=" + this.v + ", mScore=" + this.y + ", mDeepSleepTime=" + this.k + ", mShallowSleepTime=" + this.x + ", mNoonSleepTime=" + this.r + ", mWakeupDuration=" + this.aa + ", mBreathQualityScore=" + this.g + ", mMinHeartrate=" + this.q + ", mMaxHeartrate=" + this.l + ", mMinOxygenSaturation=" + this.p + ", mMaxOxygenSaturation=" + this.m + ", mMinBreathrate=" + this.s + ", mMaxBreathrate=" + this.n + ", mWakeupFeeling=" + this.ab + ", mAvgHeartrate=" + this.z + ", mMinHeartrateBaseline=" + this.ar + ", mMaxHeartrateBaseline=" + this.am + ", mHeartrateDayTobaseline=" + this.ae + ", mAvgSpO2=" + this.ai + ", mMinSpO2Baseline=" + this.ao + ", mMaxSpO2Baseline=" + this.ak + ", mSpO2DayToBaseline=" + this.ax + ", mAvgHRV=" + this.ac + ", mMinHRVBaseline=" + this.aq + ", mMaxHRVBaseline=" + this.al + ", mHRVDayToBaseline=" + this.af + ", mAvgBreathrate=" + this.ad + ", mMinBreathrateBaseline=" + this.ap + ", mMaxBreathrateBaseline=" + this.ah + ", mBreathrateDayToBaseline=" + this.ag + ", mMinWeekMonthHeartrate=" + this.aw + ", mMaxWeekMonthHeartrate=" + this.aj + ", mMinWeekMonthSpO2=" + this.au + ", mMaxWeekMonthSpO2=" + this.as + ", mMinWeekMonthHRV=" + this.at + ", mMaxWeekMonthHRV=" + this.an + '}' + super.toString();
    }
}
