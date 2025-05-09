package defpackage;

/* loaded from: classes7.dex */
public class uzo {

    /* renamed from: a, reason: collision with root package name */
    private long f17621a;
    private final int c;
    private boolean e;
    private long d = 0;
    private long b = 0;

    public uzo(int i, long j) {
        this.c = i;
        this.f17621a = j;
    }

    public long c(long j) {
        long j2;
        if (this.e) {
            j2 = Math.round((this.b * 0.875d) + (Math.abs(this.d - j) * 0.125d));
            j = Math.round((this.d * 0.75d) + (j * 0.25d));
        } else {
            this.e = true;
            j2 = j / 2;
        }
        this.d = j;
        this.b = j2;
        long max = j + Math.max(100L, this.c * j2);
        this.f17621a = max;
        return max;
    }
}
