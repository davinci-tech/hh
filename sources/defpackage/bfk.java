package defpackage;

/* loaded from: classes3.dex */
public class bfk {
    private long c;
    private int d;
    private long e;

    public void c(long j) {
        this.c = j;
    }

    public void b(long j) {
        this.e = j;
    }

    public long b() {
        return ((Long) jdy.d(Long.valueOf(this.e))).longValue();
    }

    public void d(int i) {
        this.d = i;
    }

    public int a() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public String toString() {
        return "SleepErrorFrame{startTime=" + this.c + ", endTime=" + this.e + ", errorCode=" + this.d + '}';
    }
}
