package defpackage;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class fdn extends fdj {
    private int s;
    private int u;
    private int x;
    private int t = 0;
    private int p = 0;
    private List<Integer> r = new ArrayList();
    private List<Integer> w = new ArrayList();
    private List<Integer> q = new ArrayList();
    private List<Integer> y = new ArrayList();
    private int o = 0;
    private int l = 0;
    private int n = 0;
    private int m = 0;

    public int v() {
        return this.o;
    }

    public int y() {
        return this.l;
    }

    public int x() {
        return this.n;
    }

    public int u() {
        return this.m;
    }

    public int ab() {
        return this.u;
    }

    public int aa() {
        return this.s;
    }

    public int ad() {
        return this.x;
    }

    public int z() {
        int i = this.t;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    public int ac() {
        int i = this.p;
        if (i >= 0) {
            return fcj.d((int) Math.sqrt(i));
        }
        return -1;
    }

    @Override // defpackage.fdj, defpackage.fdi
    public int i() {
        return this.k;
    }

    public boolean ae() {
        return this.s > 0 && this.x == 0;
    }

    public void a(fdj fdjVar) {
        if (fdjVar.h() > 0 && !fdjVar.q()) {
            this.i += fdjVar.h();
            this.u += fdjVar.h();
            this.x++;
            if (fdjVar.c() >= 0) {
                this.r.add(Integer.valueOf(fdjVar.c()));
            }
            if (fdjVar.l() >= 0) {
                this.w.add(Integer.valueOf(fdjVar.l()));
            }
            if (fdjVar.b() >= 0) {
                this.q.add(Integer.valueOf(fdjVar.b()));
            }
            if (fdjVar.g() >= 0) {
                this.y.add(Integer.valueOf(fdjVar.g()));
            }
            if (fdjVar.k() > 0) {
                this.h += fdjVar.k();
            }
            if (fdjVar.i() >= 0) {
                this.k += fdjVar.i();
                return;
            }
            return;
        }
        if (fdjVar.q()) {
            this.s++;
        }
    }

    public void w() {
        if (this.x <= 0) {
            return;
        }
        this.k = Math.round(this.k / this.x);
        this.h = Math.round(this.h / this.x);
        this.i = Math.round(this.i / this.x);
        if (this.q.size() > 0) {
            this.q = fcj.d(this.q);
            int round = Math.round(fcj.e(r0) / this.q.size());
            this.o = round;
            List<Integer> list = this.q;
            this.t = fcj.c(list, round, list.size());
        } else {
            this.o = -1;
            this.t = -1;
        }
        if (this.y.size() > 0) {
            this.y = fcj.d(this.y);
            int round2 = Math.round(fcj.e(r0) / this.y.size());
            this.l = round2;
            List<Integer> list2 = this.y;
            this.p = fcj.c(list2, round2, list2.size());
        } else {
            this.l = -1;
            this.p = -1;
        }
        if (this.r.size() > 0) {
            this.r = fcj.d(this.r);
            this.m = Math.round(fcj.e(r0) / this.r.size());
        } else {
            this.m = -1;
        }
        if (this.w.size() > 0) {
            this.w = fcj.d(this.w);
            this.n = Math.round(fcj.e(r0) / this.w.size());
            return;
        }
        this.n = -1;
    }

    @Override // defpackage.fdj, defpackage.fdi
    public String toString() {
        return "ManualSleepStatData{mSleepDayCount=" + this.x + ", mGoBedScore=" + this.t + ", mRisingScore=" + this.p + ", mFallAsSleepTimeList=" + this.r + ", mWakeupTimeList=" + this.w + ", mGoBedTimeList=" + this.q + ", mRisingTimeList=" + this.y + ", mAvgGoBedTime=" + this.o + ", mAvgRisingTime=" + this.l + ", mAvgWakeupTime=" + this.n + ", mAvgFallInSleepTime=" + this.m + ", mSleepTotalTime=" + this.u + ", mOnlyNoonCount=" + this.s + ", getRisingRegularity=" + ac() + ", getBedTimeRegularity=" + z() + '}' + super.toString();
    }
}
