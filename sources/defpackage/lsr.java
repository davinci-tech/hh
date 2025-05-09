package defpackage;

/* loaded from: classes9.dex */
public class lsr {
    private String b;
    private String c;
    private String d;
    private String j;

    /* renamed from: a, reason: collision with root package name */
    private long f14858a = -1;
    private long e = -1;

    public String h() {
        return this.j;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.j = str;
    }

    public long e() {
        return this.e;
    }

    public void b(String str) {
        this.c = str;
    }

    public long a() {
        return this.f14858a;
    }

    public void d(String str) {
        this.b = str;
    }

    public void e(long j) {
        this.e = j;
    }

    public String b() {
        return this.b;
    }

    public void e(String str) {
        this.d = str;
    }

    public void d(lsr lsrVar) {
        if (lsrVar == null) {
            return;
        }
        if (lsrVar.a() != -1) {
            b(lsrVar.a());
        }
        if (lsrVar.e() != -1) {
            e(lsrVar.e());
        }
        e(lsrVar.d());
        d(lsrVar.b());
        b(lsrVar.c());
        c(lsrVar.h());
    }

    public void b(long j) {
        this.f14858a = j;
    }

    public String d() {
        return this.d;
    }
}
