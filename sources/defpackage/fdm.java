package defpackage;

/* loaded from: classes3.dex */
public class fdm extends fdi {
    protected int g;
    protected int l;
    protected int n;
    protected int o;

    public int x() {
        return this.n;
    }

    public void g(int i) {
        this.n = i;
    }

    public int t() {
        return this.l;
    }

    public void f(int i) {
        this.l = i;
    }

    public int r() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int p() {
        if (this.g < 0 || this.i <= 0) {
            return -1;
        }
        return Math.round((this.g * 100.0f) / this.i);
    }

    public int s() {
        return this.o;
    }

    public void c(int i) {
        this.o = i;
    }

    @Override // defpackage.fdi
    public String toString() {
        return "CommonSleepData{mWakeupTimes=" + this.l + ", mDeepSleepTime=" + this.g + ", mShallowSleepTime=" + this.o + '}' + super.toString();
    }
}
