package defpackage;

/* loaded from: classes5.dex */
public class kuc {

    /* renamed from: a, reason: collision with root package name */
    private String f14597a;
    private long b;
    private long c;
    private long e;

    public kuc(String str, long j, long j2, long j3) {
        this.f14597a = str;
        this.b = j2;
        this.e = j;
        this.c = j3;
    }

    public kuc(String str) {
        this.b = 0L;
        this.e = 0L;
        this.c = 0L;
        this.f14597a = str;
    }

    public void a(long j) {
        this.e += j;
        this.c += j;
    }

    public void d(long j) {
        this.b += j;
        this.c += j;
    }

    public long d() {
        return this.b;
    }

    public void c(long j) {
        this.b = j;
    }

    public long c() {
        return this.e;
    }

    public void b(long j) {
        this.e = j;
    }

    public long e() {
        return this.c;
    }

    public void e(long j) {
        this.c = j;
    }

    public String a() {
        return this.f14597a;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("date: ");
        stringBuffer.append(this.f14597a);
        stringBuffer.append(" received: ");
        stringBuffer.append(this.b);
        stringBuffer.append(" requested: ");
        stringBuffer.append(this.e);
        stringBuffer.append(" total: ");
        stringBuffer.append(this.c);
        return stringBuffer.toString();
    }
}
