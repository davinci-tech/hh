package defpackage;

import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fdq extends fdr {
    private int ap;
    private int aq;
    private int at;
    private int aw;
    private int ax;
    private int az;
    private int ay = 0;
    private int bc = 0;
    private int bb = 0;
    private int ba = 0;
    private int ai = 0;
    private List<Integer> ag = new ArrayList();
    private List<Integer> bg = new ArrayList();
    private List<Integer> ah = new ArrayList();
    private List<Integer> au = new ArrayList();
    private int ad = 0;
    private int ae = 0;
    private int ac = 0;
    private int af = 0;
    private int av = 0;
    private int am = -2;
    private int an = -2;
    private int as = -2;
    private int ak = -2;
    private int ao = -2;
    private int aj = -2;
    private int ar = -2;
    private int al = -2;
    private boolean z = false;

    private int b(int i, int i2, int i3) {
        return (i != -2 && (i3 != 1 || i <= i2) && (i3 != 0 || i >= i2)) ? i : i2;
    }

    public int bs() {
        return this.az;
    }

    public int bp() {
        return this.aw;
    }

    public int bn() {
        return this.ap;
    }

    public int br() {
        return this.ax;
    }

    public int bd() {
        return this.ad;
    }

    public int bh() {
        return this.ae;
    }

    public int bi() {
        return this.as;
    }

    public int bm() {
        return this.am;
    }

    public int bq() {
        return this.ao;
    }

    public int bk() {
        return this.ar;
    }

    public int bl() {
        return this.ak;
    }

    public int be() {
        return this.an;
    }

    public int bj() {
        return this.aj;
    }

    public int bf() {
        return this.al;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void c(String str, int i) {
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
            this.ar = i;
            return;
        }
        if (c == 1) {
            this.as = i;
            return;
        }
        if (c == 2) {
            this.ao = i;
        } else if (c == 3) {
            this.am = i;
        } else {
            ReleaseLogUtil.a("RScienceSleepStatData", "unknow itemType: ", str);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public boolean a(String str) {
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
            return bu();
        }
        if (c == 1) {
            return bw();
        }
        if (c == 2) {
            return by();
        }
        if (c == 3) {
            return bv();
        }
        ReleaseLogUtil.a("RScienceSleepStatData", "unknow itemType: ", str);
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void b(String str, int i) {
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
            this.al = i;
            return;
        }
        if (c == 1) {
            this.ak = i;
            return;
        }
        if (c == 2) {
            this.aj = i;
        } else if (c == 3) {
            this.an = i;
        } else {
            ReleaseLogUtil.a("RScienceSleepStatData", "unknow itemType: ", str);
        }
    }

    private boolean bw() {
        return c(this.as) && c(this.ak) && this.as <= this.ak;
    }

    private boolean bv() {
        return c(this.am) && c(this.an) && this.am <= this.an;
    }

    private boolean bu() {
        return c(this.ar) && c(this.al) && this.ar <= this.al;
    }

    private boolean by() {
        return c(this.ao) && c(this.aj) && this.ao <= this.aj;
    }

    @Override // defpackage.fdr
    public boolean ba() {
        return (this.as == -2 && this.ak == -2 && this.am == -2 && this.an == -2 && this.ao == -2 && this.aj == -2 && this.ar == -2 && this.al == -2 && !this.z) ? false : true;
    }

    public int bo() {
        int i = this.av;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    public int bg() {
        int i = this.ai;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    @Override // defpackage.fdr
    public int w() {
        return this.g;
    }

    public boolean bt() {
        return this.ap > 0 && this.aq == 0 && this.aw == 0;
    }

    public void b(fdr fdrVar) {
        if (fdrVar.ap() > 0) {
            this.aq++;
            this.r += fdrVar.ap();
        }
        if (fdrVar.h() > 0 && !fdrVar.q()) {
            this.i += fdrVar.h();
            this.az += fdrVar.h();
            this.aw++;
            if (fdrVar.k() > 0) {
                this.bc++;
                this.bb += fdrVar.h();
                this.ba += fdrVar.k();
                this.h += fdrVar.k();
            }
            if (fdrVar.i() >= 0) {
                this.ay++;
                this.f12460a += fdrVar.i();
            }
            if (fdrVar.ak().doubleValue() > 0.0d) {
                this.p = this.p == -1.0d ? fdrVar.ak().doubleValue() : Math.min(this.p, fdrVar.ak().doubleValue());
            }
            if (fdrVar.ai().doubleValue() > 0.0d) {
                this.m = this.m == -1.0d ? fdrVar.ai().doubleValue() : Math.max(this.m, fdrVar.ai().doubleValue());
            }
            if (fdrVar.ah().doubleValue() > 0.0d) {
                this.s = this.s == -1.0d ? fdrVar.ah().doubleValue() : Math.min(this.s, fdrVar.ah().doubleValue());
            }
            if (fdrVar.aa() > 0.0d) {
                this.n = this.n == -1.0d ? fdrVar.aa() : Math.max(this.n, fdrVar.aa());
            }
            if (fdrVar.al() > 0) {
                this.q = this.q == -1 ? fdrVar.al() : Math.min(this.q, fdrVar.al());
            }
            if (fdrVar.ae() > 0) {
                this.l = this.l == -1 ? fdrVar.ae() : Math.max(this.l, fdrVar.ae());
            }
            if (fdrVar.x() > 0) {
                this.k += fdrVar.x();
            }
            if (fdrVar.aw() > 0) {
                this.x += fdrVar.aw();
            }
            if (fdrVar.aq() > 0) {
                this.t += fdrVar.aq();
            }
            if (fdrVar.y() >= 20) {
                this.o += fdrVar.y();
            }
            if (fdrVar.ay() >= 0 && fdrVar.h() > 0) {
                this.aa += fdrVar.ay();
            }
            if (fdrVar.bc() > 0 && fdrVar.h() > 0) {
                this.w += fdrVar.bc();
            }
            if (fdrVar.c() >= 0) {
                this.ag.add(Integer.valueOf(fdrVar.c()));
            }
            if (fdrVar.l() >= 0) {
                this.bg.add(Integer.valueOf(fdrVar.l()));
            }
            if (fdrVar.b() >= 0) {
                this.ah.add(Integer.valueOf(fdrVar.b()));
            }
            if (fdrVar.g() >= 0) {
                this.au.add(Integer.valueOf(fdrVar.g()));
            }
            if (fdrVar.ar() > 0) {
                this.y += fdrVar.ar();
                this.at++;
            }
            int a2 = fcj.a(fdrVar.au(), fdrVar.aq());
            if (a2 > 0) {
                this.g += a2;
            }
            if (fdrVar.ba()) {
                this.z = true;
            }
            if (fdrVar.c("TRUSLEEP_FIVE_HEART_RATE")) {
                d("TRUSLEEP_FIVE_HEART_RATE", fdrVar);
            }
            if (fdrVar.c("TRUSLEEP_FIVE_BREATH_RATE")) {
                d("TRUSLEEP_FIVE_BREATH_RATE", fdrVar);
            }
            if (fdrVar.c("TRUSLEEP_FIVE_SpO2")) {
                d("TRUSLEEP_FIVE_SpO2", fdrVar);
            }
            if (fdrVar.c("TRUSLEEP_FIVE_HRV")) {
                d("TRUSLEEP_FIVE_HRV", fdrVar);
                return;
            }
            return;
        }
        if (fdrVar.q()) {
            this.ap++;
        } else if (fdrVar.ap() > 0) {
            this.ax++;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(String str, fdr fdrVar) {
        char c;
        int t;
        int bk;
        int bf;
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
            t = fdrVar.t();
            bk = bk();
            bf = bf();
        } else if (c == 1) {
            t = fdrVar.r();
            bk = bi();
            bf = bl();
        } else if (c == 2) {
            t = fdrVar.s();
            bk = bq();
            bf = bj();
        } else if (c == 3) {
            t = fdrVar.p();
            bk = bm();
            bf = be();
        } else {
            ReleaseLogUtil.a("RScienceSleepStatData", "unknow itemType: ", str);
            return;
        }
        c(str, b(bk, t, 1));
        b(str, b(bf, t, 0));
    }

    public void az() {
        if (this.aq > 0) {
            this.r = Math.round(this.r / this.aq);
        }
        if (this.aw <= 0) {
            return;
        }
        this.i = Math.round(this.i / this.aw);
        this.k = Math.round(this.k / this.aw);
        this.x = Math.round(this.x / this.aw);
        this.t = Math.round(this.t / this.aw);
        this.o = Math.round(this.o / this.aw);
        if (this.at > 0) {
            this.y = Math.round(this.y / this.at);
        }
        this.g = Math.round(this.g / this.aw);
        this.w = Math.round(this.w / this.aw);
        if (this.ay > 0) {
            this.f12460a = Math.round(this.f12460a / this.ay);
        }
        if (this.bc > 0) {
            this.h = Math.round(this.h / this.bc);
            this.ba = Math.round(this.ba / this.bc);
            this.bb = Math.round(this.bb / this.bc);
        }
        if (this.ah.size() > 0) {
            this.ah = fcj.d(this.ah);
            int round = Math.round(fcj.e(r0) / this.ah.size());
            this.ac = round;
            List<Integer> list = this.ah;
            this.ai = fcj.c(list, round, list.size());
        } else {
            this.ac = -1;
            this.ai = -1;
        }
        if (this.au.size() > 0) {
            this.au = fcj.d(this.au);
            int round2 = Math.round(fcj.e(r0) / this.au.size());
            this.af = round2;
            List<Integer> list2 = this.au;
            this.av = fcj.c(list2, round2, list2.size());
        } else {
            this.af = -1;
            this.av = -1;
        }
        if (this.ag.size() > 0) {
            this.ag = fcj.d(this.ag);
            this.ad = Math.round(fcj.e(r0) / this.ag.size());
        } else {
            this.ad = -1;
        }
        if (this.bg.size() > 0) {
            this.bg = fcj.d(this.bg);
            this.ae = Math.round(fcj.e(r0) / this.bg.size());
            return;
        }
        this.ae = -1;
    }

    @Override // defpackage.fdi
    public int j() {
        int i;
        if (this.bb < 0 || (i = this.ba) <= 0) {
            return -1;
        }
        return (int) UnitUtil.a((r0 / i) * 100.0f, 0);
    }

    @Override // defpackage.fdr, defpackage.fdi
    public int i() {
        if (this.bc == 0) {
            return -1;
        }
        return this.f12460a;
    }

    @Override // defpackage.fdi
    public int k() {
        if (this.bc == this.aw) {
            return this.h;
        }
        return -1;
    }

    @Override // defpackage.fdr, defpackage.fdi
    public String toString() {
        return "ScienceSleepStatData{mSleepLatencyCount=" + this.ay + ", mStayInBedCount=" + this.bc + ", mTmpSleepTime=" + this.bb + ", mTmpBedTime=" + this.ba + ", mSleepDayCount=" + this.aw + ", mNoonDayCount=" + this.aq + ", mOnlyNoonCount=" + this.ax + ", mScoreCount=" + this.at + ", mNotSatisfiedSleepCount=" + this.ap + ", mGoBedScore=" + this.ai + ", mFallAsSleepTimeList=" + this.ag + ", mWakeupTimeList=" + this.bg + ", mGoBedTimeList=" + this.ah + ", mRisingTimeList=" + this.au + ", mAvgFallAsSleepTime=" + this.ad + ", mAvgWakeupTime=" + this.ae + ", mAvgGoBedTime=" + this.ac + ", mAvgRisingTime=" + this.af + ", mRisingScore=" + this.av + ", mSleepTotalTime=" + this.az + ", mMinWeekMonthHeartrate=" + this.as + ", mMaxWeekMonthHeartrate=" + this.ak + ", mMinWeekMonthSpO2=" + this.ao + ", mMaxWeekMonthSpO2=" + this.aj + ", mMinWeekMonthHRV=" + this.ar + ", mMaxWeekMonthHRV=" + this.al + ", mMinWeekMonthBreathrate=" + this.am + ", mMaxWeekMonthBreathrate=" + this.an + '}' + super.toString();
    }
}
