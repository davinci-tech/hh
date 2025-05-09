package defpackage;

/* loaded from: classes5.dex */
public class kbd {

    /* renamed from: a, reason: collision with root package name */
    private double[] f14246a;
    private long b;

    public long e() {
        return ((Long) jdy.d(Long.valueOf(this.b))).longValue();
    }

    public void e(long j) {
        this.b = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public double[] c() {
        double[] dArr = this.f14246a;
        return dArr == null ? dArr : (double[]) jdy.d((double[]) dArr.clone());
    }

    public void c(double[] dArr) {
        if (dArr != null) {
            this.f14246a = (double[]) jdy.d((double[]) dArr.clone());
        }
    }
}
