package defpackage;

import java.io.Serializable;

/* loaded from: classes4.dex */
public final class hiy implements Serializable {
    private static final long serialVersionUID = -1029604556087330774L;

    /* renamed from: a, reason: collision with root package name */
    private float f13183a;
    private long b;
    private float c;
    private float d;
    private float e;
    private double f;
    private boolean g;
    private float h;
    private double i;
    private boolean j;
    private String l;

    public hiy() {
        this.h = 0.0f;
        this.j = false;
        this.g = false;
    }

    public hiy(double d, double d2, float f, float f2, float f3, float f4, long j, String str) {
        this.h = 0.0f;
        this.j = false;
        this.g = false;
        this.f = d2;
        this.i = d;
        this.f13183a = f;
        this.c = f2;
        this.e = f3;
        this.d = f4;
        this.b = j;
        this.l = str;
    }

    public hiy(hiy hiyVar) {
        this.h = 0.0f;
        this.j = false;
        this.g = false;
        if (hiyVar != null) {
            this.f = hiyVar.f();
            this.i = hiyVar.h();
            this.f13183a = hiyVar.d();
            this.c = hiyVar.a();
            this.e = hiyVar.c();
            this.d = hiyVar.e();
            this.b = hiyVar.i();
            this.l = hiyVar.g();
            this.h = hiyVar.b();
        }
    }

    public long i() {
        return this.b;
    }

    public float d() {
        return this.f13183a;
    }

    public void c(double d) {
        this.i = d;
    }

    public void b(double d) {
        this.f = d;
    }

    public double h() {
        return this.i;
    }

    public double f() {
        return this.f;
    }

    public float a() {
        return this.c;
    }

    public float c() {
        return this.e;
    }

    public float e() {
        return this.d;
    }

    public String g() {
        return this.l;
    }

    public boolean j() {
        return this.j;
    }

    public void e(boolean z) {
        this.j = z;
    }

    public float b() {
        return this.h;
    }

    public void b(float f) {
        this.h = f;
    }
}
