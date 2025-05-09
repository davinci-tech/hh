package defpackage;

/* loaded from: classes.dex */
public class msc {

    /* renamed from: a, reason: collision with root package name */
    private String f15142a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private mrt l;
    private String o;

    public mrt g() {
        return (mrt) jdy.d(this.l);
    }

    public void c(mrt mrtVar) {
        this.l = (mrt) jdy.d(mrtVar);
    }

    public void i(String str) {
        this.i = (String) jdy.d(str);
    }

    public String a() {
        return (String) jdy.d(this.g);
    }

    public void g(String str) {
        this.g = (String) jdy.d(str);
    }

    public String i() {
        return (String) jdy.d(this.o);
    }

    public void j(String str) {
        this.o = (String) jdy.d(str);
    }

    public void h(String str) {
        this.f = (String) jdy.d(str);
    }

    public int c() {
        return ((Integer) jdy.d(Integer.valueOf(this.d))).intValue();
    }

    public void e(int i) {
        this.d = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String b() {
        return (String) jdy.d(this.b);
    }

    public void c(String str) {
        this.b = (String) jdy.d(str);
    }

    public void b(String str) {
        this.h = (String) jdy.d(str);
    }

    public void a(String str) {
        this.e = (String) jdy.d(str);
    }

    public void f(String str) {
        this.j = (String) jdy.d(str);
    }

    public String e() {
        return (String) jdy.d(this.c);
    }

    public void e(String str) {
        this.c = (String) jdy.d(str);
    }

    public String d() {
        return (String) jdy.d(this.f15142a);
    }

    public void d(String str) {
        this.f15142a = (String) jdy.d(str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("EzPluginInfo{, pluginName='");
        sb.append(this.i);
        sb.append("', pluginUuid='");
        sb.append(this.g);
        sb.append("', version='");
        sb.append(this.o);
        sb.append("', modifyDate='");
        sb.append(this.f);
        sb.append("', fileSize='");
        sb.append(this.d);
        sb.append("', digest='");
        sb.append(this.b);
        sb.append("', form='");
        sb.append(this.h);
        sb.append("', description='");
        sb.append(this.e);
        sb.append("', prompt='");
        sb.append(this.j);
        sb.append("', fileName='");
        sb.append(this.c);
        sb.append("', fileType='");
        sb.append(this.f15142a);
        sb.append("'}");
        return sb.toString();
    }
}
