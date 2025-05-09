package defpackage;

/* loaded from: classes5.dex */
public class jgr {

    /* renamed from: a, reason: collision with root package name */
    private String f13828a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String g;
    private int h;
    private long j;

    public void c(long j) {
        this.j = j;
    }

    public void e(int i) {
        this.h = i;
    }

    public void e(String str) {
        this.g = str;
    }

    public void d(String str) {
        this.b = str;
    }

    public void a(String str) {
        this.f13828a = str;
    }

    public void d(int i) {
        this.e = i;
    }

    public void b(int i) {
        this.c = i;
    }

    public void a(int i) {
        this.d = i;
    }

    public String toString() {
        return "EventInfo{ts=" + this.j + ", tokenType=" + this.h + ", token='" + this.g + "', appId='" + this.b + "', deviceId='" + iyl.d().e(this.f13828a) + "', siteId='" + this.e + "', iversion='" + this.c + "', source='" + this.d + "'}";
    }
}
