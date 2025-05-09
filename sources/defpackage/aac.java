package defpackage;

/* loaded from: classes8.dex */
public class aac {

    /* renamed from: a, reason: collision with root package name */
    private long f127a;
    private String b;
    private String c;
    private long e;
    private int h;
    private int d = -1;
    private String f = "";

    public void d(String str) {
        this.b = str;
    }

    public void e(String str) {
        this.c = str;
    }

    public String toString() {
        return "{syncType:" + this.c + ",prepareTraceId:" + this.b + ",scene:" + this.d + ",startTime:" + this.e + ",endTime:" + this.f127a + ",errCode:" + this.f + ",times:" + this.h + "}";
    }

    public void a(long j) {
        this.e = j;
    }

    public void e(long j) {
        this.f127a = j;
    }

    public void a(String str) {
        this.f = str;
    }

    public void a(int i) {
        this.h = i;
    }

    public void c(int i) {
        this.d = i;
    }
}
