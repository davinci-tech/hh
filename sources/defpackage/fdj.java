package defpackage;

/* loaded from: classes3.dex */
public class fdj extends fdi {
    protected int g;
    protected int k;

    @Override // defpackage.fdi
    public boolean q() {
        return this.h < 180 && this.h > 0;
    }

    public float t() {
        if (this.i < 0 || this.h <= 0) {
            return -1.0f;
        }
        return this.i / this.h;
    }

    @Override // defpackage.fdi
    public int i() {
        if (this.d < 0 || this.c < 0) {
            return -1;
        }
        return c() - b();
    }

    public int p() {
        int i = this.g;
        if (i != -1) {
            return fcj.c(i);
        }
        return -1;
    }

    public boolean s() {
        return this.h > 0 && this.i <= 0;
    }

    public int r() {
        float t = t();
        int i = i();
        if (t != -1.0f && i != -1 && this.i != -1) {
            this.g = (int) ((fcj.b(this.i) * 0.5f) + (fcj.b(t) * 0.3f) + (fcj.e(i) * 0.2f));
        } else {
            this.g = -1;
        }
        return this.g;
    }

    @Override // defpackage.fdi
    public boolean o() {
        return super.o() || this.h > 0;
    }

    @Override // defpackage.fdi
    public String toString() {
        return "ManualSleepData{mStayInBedTime=" + this.h + ", mScore=" + this.g + ", isIsOnlyOnBed=" + s() + ", getSleepLevel=" + p() + ", getSleepLatencyTime=" + i() + ", getSleepEfficiency=" + j() + '}' + super.toString();
    }
}
