package defpackage;

/* loaded from: classes5.dex */
public class kds {

    /* renamed from: a, reason: collision with root package name */
    private long f14308a;
    private int b;
    private int c;
    private int e;

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void b(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int e() {
        return ((Integer) jdy.d(Integer.valueOf(this.e))).intValue();
    }

    public void d(int i) {
        this.e = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public long b() {
        return ((Long) jdy.d(Long.valueOf(this.f14308a))).longValue();
    }

    public void e(long j) {
        this.f14308a = ((Long) jdy.d(Long.valueOf(j))).longValue();
    }

    public int a() {
        return ((Integer) jdy.d(Integer.valueOf(this.b))).intValue();
    }

    public void a(int i) {
        this.b = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String toString() {
        return "[RelaxRecord: relaxScore = " + this.e + ", relaxLength = " + this.c + ", relaxTime = " + this.f14308a + ", relaxType = " + this.b + "]";
    }
}
