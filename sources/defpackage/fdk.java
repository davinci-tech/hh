package defpackage;

/* loaded from: classes3.dex */
public class fdk extends fdi {
    protected int g;
    protected long k;
    protected int l;
    protected String m;
    protected long n;
    protected String o;
    protected int p;
    protected int q;
    protected int r;
    protected int s;
    protected int t;
    protected int u;
    private String y = "PhoneSleepData";

    public int p() {
        return this.g;
    }

    public void a(int i) {
        this.g = i;
    }

    public int t() {
        if (this.g < 0 || this.i <= 0) {
            return -1;
        }
        return Math.round((this.g * 100.0f) / this.i);
    }

    public int u() {
        return this.q;
    }

    public void i(int i) {
        this.q = i;
    }

    public int ac() {
        return this.u;
    }

    public void m(int i) {
        this.u = i;
    }

    public int ab() {
        return this.t;
    }

    public void f(int i) {
        this.t = i;
    }

    public int y() {
        return this.r;
    }

    public void h(int i) {
        this.r = i;
    }

    public int z() {
        return this.p;
    }

    public void j(int i) {
        this.p = i;
    }

    public int w() {
        return this.l;
    }

    public void c(int i) {
        this.l = i;
    }

    public String x() {
        return this.o;
    }

    public void b(String str) {
        this.o = str;
    }

    public long v() {
        return this.k;
    }

    public void g(long j) {
        this.k = j;
    }

    public long r() {
        return this.n;
    }

    public void i(long j) {
        this.n = j;
    }

    public void g(int i) {
        this.s = i;
    }

    public String s() {
        return this.m;
    }

    public void c(String str) {
        this.m = str;
    }

    public int aa() {
        int i = this.r;
        if (i != -1) {
            return fcj.c(i);
        }
        return -1;
    }

    public boolean ad() {
        return this.i <= 0 && this.l > 0;
    }

    @Override // defpackage.fdi
    public int i() {
        if (this.d <= 0 || this.c <= 0) {
            return -1;
        }
        return c() - b();
    }

    @Override // defpackage.fdi
    public boolean o() {
        return super.o() || this.l > 0 || this.r > 0;
    }

    @Override // defpackage.fdi
    public String toString() {
        return "PhoneSleepData{mWakeupDuration=" + this.p + ", mNoonSleepTime=" + this.l + ", mStayInBedTime=" + this.h + ", mEnvNoiseSeqData='" + this.o + "', mEnvNoiseStartTime=" + this.k + ", mEnvNoiseEndTime=" + this.n + ", mSleepEndReason=" + this.s + ", mEnvNoiseMetaData='" + this.m + "', getSleepLevel=" + aa() + ", getSleepEfficiency=" + j() + ", getSleepLatencyTime=" + i() + '}' + super.toString();
    }
}
