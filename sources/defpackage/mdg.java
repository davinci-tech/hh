package defpackage;

/* loaded from: classes6.dex */
public class mdg {

    /* renamed from: a, reason: collision with root package name */
    private int f14893a;
    private String b;
    private int c;
    private String d;
    private String e;

    public mdg() {
        this("", 0, 0);
    }

    public mdg(String str, int i, int i2) {
        this.b = str;
        this.f14893a = i;
        this.c = i2;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int b() {
        return this.f14893a;
    }

    public void e(int i) {
        this.f14893a = i;
    }

    public String e() {
        return this.d;
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }

    public String c() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    public String toString() {
        return "{taskId:" + this.b + ", status:" + this.f14893a + ", productId:" + this.d + ", valueType:" + this.c + ", timeZone:" + this.e + '}';
    }
}
