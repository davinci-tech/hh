package defpackage;

/* loaded from: classes.dex */
public class cvc {

    /* renamed from: a, reason: collision with root package name */
    private String f11494a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String m;
    private cvj o;

    public cvj f() {
        return (cvj) jdy.d(this.o);
    }

    public void c(cvj cvjVar) {
        this.o = (cvj) jdy.d(cvjVar);
    }

    public void g(String str) {
        this.f = (String) jdy.d(str);
    }

    public String e() {
        return (String) jdy.d(this.h);
    }

    public void h(String str) {
        this.h = (String) jdy.d(str);
    }

    public String b() {
        return (String) jdy.d(this.m);
    }

    public void f(String str) {
        this.m = (String) jdy.d(str);
    }

    public void i(String str) {
        this.i = (String) jdy.d(str);
    }

    public int d() {
        return ((Integer) jdy.d(Integer.valueOf(this.c))).intValue();
    }

    public void a(int i) {
        this.c = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String a() {
        return (String) jdy.d(this.e);
    }

    public void d(String str) {
        this.e = (String) jdy.d(str);
    }

    public void b(String str) {
        this.j = (String) jdy.d(str);
    }

    public void c(String str) {
        this.d = (String) jdy.d(str);
    }

    public void j(String str) {
        this.g = (String) jdy.d(str);
    }

    public void e(String str) {
        this.f11494a = (String) jdy.d(str);
    }

    public String c() {
        return (String) jdy.d(this.b);
    }

    public void a(String str) {
        this.b = (String) jdy.d(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("DescriptionInfo{, pluginName='");
        sb.append(this.f);
        sb.append("', pluginUuid='");
        sb.append(this.h);
        sb.append("', version='");
        sb.append(this.m);
        sb.append("', modifyDate='");
        sb.append(this.i);
        sb.append("', fileSize='");
        sb.append(this.c);
        sb.append("', digest='");
        sb.append(this.e);
        sb.append("', form='");
        sb.append(this.j);
        sb.append("', description='");
        sb.append(this.d);
        sb.append("', prompt='");
        sb.append(this.g);
        sb.append("', fileName='");
        sb.append(this.f11494a);
        sb.append("', fileType='");
        sb.append(this.b);
        sb.append("'}");
        return sb.toString();
    }
}
