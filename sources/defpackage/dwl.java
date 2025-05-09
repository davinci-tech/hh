package defpackage;

/* loaded from: classes3.dex */
public class dwl {

    /* renamed from: a, reason: collision with root package name */
    private long f11867a;
    private float b;
    private float c;
    private float d;
    private float e;
    private double f;
    private boolean g;
    private double h;
    private String i;
    private float j;

    public void c(boolean z) {
        this.g = z;
    }

    public void a(double d) {
        this.f = d;
    }

    public void b(double d) {
        this.h = d;
    }

    public boolean f() {
        return this.g;
    }

    public String i() {
        return this.i;
    }

    public long h() {
        return this.f11867a;
    }

    public double j() {
        return this.f;
    }

    public double g() {
        return this.h;
    }

    public float d() {
        return this.j;
    }

    public float b() {
        return this.b;
    }

    public float a() {
        return this.d;
    }

    public float e() {
        return this.e;
    }

    public float c() {
        return this.c;
    }

    public dwl(dwl dwlVar) {
        this.j = 0.0f;
        this.g = false;
        if (dwlVar != null) {
            this.f = dwlVar.j();
            this.h = dwlVar.g();
            this.c = dwlVar.c();
            this.b = dwlVar.b();
            this.e = dwlVar.e();
            this.d = dwlVar.a();
            this.f11867a = dwlVar.h();
            this.i = dwlVar.i();
            this.j = dwlVar.d();
        }
    }

    public dwl(double d, double d2, float f, float f2, float f3, float f4, long j, String str) {
        this.j = 0.0f;
        this.g = false;
        this.f = d2;
        this.h = d;
        this.c = f;
        this.b = f2;
        this.e = f3;
        this.d = f4;
        this.f11867a = j;
        this.i = str;
    }

    public dwl() {
        this.j = 0.0f;
        this.g = false;
    }
}
