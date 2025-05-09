package defpackage;

/* loaded from: classes4.dex */
public class fhu {

    /* renamed from: a, reason: collision with root package name */
    private String f12518a;
    private String b;
    private String c;
    private String e;

    private fhu() {
    }

    public String d() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String b() {
        return this.e;
    }

    public void e(String str) {
        this.e = str;
    }

    public String j() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String c() {
        return this.f12518a;
    }

    public void a(String str) {
        this.f12518a = str;
    }

    public void a() {
        this.c = "";
        this.f12518a = "";
        this.e = "";
        this.b = "";
    }

    static class c {
        private static final fhu b = new fhu();
    }

    public static final fhu e() {
        return c.b;
    }
}
