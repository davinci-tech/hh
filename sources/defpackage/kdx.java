package defpackage;

/* loaded from: classes5.dex */
public class kdx {

    /* renamed from: a, reason: collision with root package name */
    private long f14310a;
    private int c;
    private int d;

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void c(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int b() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void a(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long c() {
        return ((Long) jdy.d(Long.valueOf(this.f14310a))).longValue();
    }

    public void b(long j) {
        this.f14310a = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public String toString() {
        return "[StressRecord: stressScore = " + this.d + ", stressTime = " + this.f14310a + ", stressTestType = " + this.c + "]";
    }
}
