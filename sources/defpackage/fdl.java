package defpackage;

/* loaded from: classes3.dex */
public class fdl extends fdm {
    private int k;
    private int m;
    private int s;

    public int y() {
        return this.s;
    }

    public int v() {
        return this.k;
    }

    public int u() {
        return this.m;
    }

    public boolean ad() {
        return this.m > 0 && this.k == 0;
    }

    public void b(fdm fdmVar) {
        if (fdmVar.h() > 0 && !fdmVar.q()) {
            this.i += fdmVar.h();
            this.s += fdmVar.h();
            this.k++;
            if (fdmVar.r() > 0) {
                this.g += fdmVar.r();
            }
            if (fdmVar.s() > 0) {
                this.o += fdmVar.s();
            }
            if (fdmVar.x() > 0) {
                this.n += fdmVar.x();
            }
            if (fdmVar.t() > 0) {
                this.l += fdmVar.t();
                return;
            }
            return;
        }
        if (fdmVar.q()) {
            this.m++;
        }
    }

    public void w() {
        if (this.k <= 0) {
            return;
        }
        this.g = Math.round(this.g / this.k);
        this.o = Math.round(this.o / this.k);
        this.i = Math.round(this.i / this.k);
        this.n = Math.round(this.n / this.k);
        this.l = Math.round(this.l / this.k);
    }

    @Override // defpackage.fdm, defpackage.fdi
    public String toString() {
        return "CommonSleepStatData{mSleepDayCount=" + this.k + ", mOnlyNoonCount=" + this.m + ", mSleepTotalTime=" + this.s + '}' + super.toString();
    }
}
