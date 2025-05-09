package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class fdo extends fdk {
    private int ad;
    private int ae;
    private int ah;
    private int ai;
    private int z;
    private int ab = 0;
    private int ag = 0;
    private List<Integer> ac = new ArrayList();
    private List<Integer> al = new ArrayList();
    private List<Integer> aa = new ArrayList();
    private List<Integer> af = new ArrayList();
    private int v = 0;
    private int w = 0;
    private int x = 0;
    private int y = 0;

    public int as() {
        return this.ai;
    }

    public int ae() {
        return this.v;
    }

    public int ah() {
        return this.w;
    }

    public int af() {
        return this.x;
    }

    public int ag() {
        return this.y;
    }

    public int am() {
        return this.ae;
    }

    public int aj() {
        int i = this.ab;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    public int ak() {
        int i = this.ag;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    public int al() {
        return this.ah;
    }

    public int an() {
        return this.z;
    }

    public boolean ap() {
        return this.z > 0 && this.ad == 0 && this.ae == 0;
    }

    public void c(fdk fdkVar) {
        if (fdkVar.w() > 0) {
            this.ad++;
            this.l += fdkVar.w();
        }
        if (fdkVar.h() > 0 && !fdkVar.q()) {
            this.i += fdkVar.h();
            this.ai += fdkVar.h();
            this.ae++;
            if (fdkVar.k() > 0) {
                this.h += fdkVar.k();
            }
            if (fdkVar.z() > 0) {
                this.p += fdkVar.z();
            }
            if (fdkVar.p() > 0) {
                this.g += fdkVar.p();
            }
            if (fdkVar.u() > 0) {
                this.q += fdkVar.u();
            }
            if (fdkVar.c() >= 0) {
                this.ac.add(Integer.valueOf(fdkVar.c()));
            }
            if (fdkVar.l() >= 0) {
                this.al.add(Integer.valueOf(fdkVar.l()));
            }
            if (fdkVar.b() >= 0) {
                this.aa.add(Integer.valueOf(fdkVar.b()));
            }
            if (fdkVar.g() >= 0) {
                this.af.add(Integer.valueOf(fdkVar.g()));
            }
            if (fdkVar.i() >= 0) {
                this.f12460a += fdkVar.i();
            }
            if (fdkVar.y() > 0) {
                this.r += fdkVar.y();
            }
            if (fdkVar.ab() > 0) {
                this.t += fdkVar.ab();
                return;
            }
            return;
        }
        if (fdkVar.q()) {
            this.z++;
        } else if (fdkVar.w() > 0) {
            this.ah++;
        }
    }

    public void ai() {
        if (this.ad > 0) {
            this.l = Math.round(this.l / this.ad);
        }
        if (this.ae <= 0) {
            return;
        }
        this.f12460a = Math.round(this.f12460a / this.ae);
        this.g = Math.round(this.g / this.ae);
        this.q = Math.round(this.q / this.ae);
        this.i = Math.round(this.i / this.ae);
        this.h = Math.round(this.h / this.ae);
        this.r = Math.round(this.r / this.ae);
        this.t = Math.round(this.t / this.ae);
        if (this.aa.size() > 0) {
            this.aa = fcj.d(this.aa);
            int round = Math.round(fcj.e(r0) / this.aa.size());
            this.v = round;
            List<Integer> list = this.aa;
            this.ab = fcj.c(list, round, list.size());
        } else {
            this.v = -1;
            this.ab = -1;
        }
        if (this.af.size() > 0) {
            this.af = fcj.d(this.af);
            int round2 = Math.round(fcj.e(r0) / this.af.size());
            this.w = round2;
            List<Integer> list2 = this.af;
            this.ag = fcj.c(list2, round2, list2.size());
        } else {
            this.w = -1;
            this.ag = -1;
        }
        if (this.ac.size() > 0) {
            this.ac = fcj.d(this.ac);
            this.y = Math.round(fcj.e(r0) / this.ac.size());
        } else {
            this.y = -1;
        }
        if (this.al.size() > 0) {
            this.al = fcj.d(this.al);
            this.x = Math.round(fcj.e(r0) / this.al.size());
            return;
        }
        this.x = -1;
    }

    @Override // defpackage.fdk, defpackage.fdi
    public int i() {
        return this.f12460a;
    }

    @Override // defpackage.fdk, defpackage.fdi
    public String toString() {
        return "PhoneSleepStatData{mSleepDayCount=" + this.ae + ", mOnlyNoonCount=" + this.ah + ", mNotSatisfiedSleepCount=" + this.z + ", mGoBedScore=" + this.ab + ", mRisingScore=" + this.ag + ", mNoonDayCount=" + this.ad + ", mFallAsSleepTimeList=" + this.ac + ", mWakeupTimeList=" + this.al + ", mGoBedTimeList=" + this.aa + ", mRisingTimeList=" + this.af + ", mAvgGoBedTime=" + this.v + ", mAvgRisingTime=" + this.w + ", mAvgWakeupTime=" + this.x + ", mAvgFallAsSleepTime=" + this.y + ", mSleepTotalTime=" + this.ai + ", getRisingRegularity=" + ak() + ", getBedTimeRegularity=" + aj() + '}' + super.toString();
    }
}
