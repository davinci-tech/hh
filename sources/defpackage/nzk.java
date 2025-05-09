package defpackage;

/* loaded from: classes6.dex */
public class nzk extends nzn {
    private static final long serialVersionUID = 1069953673580218483L;

    /* renamed from: a, reason: collision with root package name */
    private String f15566a;
    private nzi b;
    private String c;

    public String b() {
        return this.c;
    }

    public String a() {
        return this.f15566a;
    }

    public nzi d() {
        return this.b;
    }

    public void a(String str) {
        this.c = str;
    }

    public void d(String str) {
        this.f15566a = str;
    }

    public void e(nzi nziVar) {
        this.b = nziVar;
    }

    public String toString() {
        return "Checkbox{name=" + this.c + ", description='" + this.f15566a + ", config= " + this.b + '}';
    }
}
