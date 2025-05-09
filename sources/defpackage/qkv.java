package defpackage;

/* loaded from: classes6.dex */
public class qkv {

    /* renamed from: a, reason: collision with root package name */
    private int f16465a;
    private boolean b;
    private String c;
    private boolean d;
    private boolean e;
    private boolean g;
    private boolean h;

    public qkv(String str) {
        this.g = true;
        this.c = str;
    }

    public qkv(String str, boolean z, int i, boolean z2) {
        this.c = str;
        this.g = z;
        this.f16465a = i;
        this.d = z2;
    }

    public String d() {
        return this.c;
    }

    public boolean f() {
        return this.g;
    }

    public void e(boolean z) {
        this.g = z;
    }

    public int c() {
        return this.f16465a;
    }

    public boolean b() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
        if (z) {
            this.f16465a = 1073741824;
        }
    }

    public boolean a() {
        return this.b;
    }

    public void d(boolean z) {
        this.b = z;
    }

    public boolean e() {
        return this.d;
    }

    public boolean i() {
        return this.h;
    }

    public void c(boolean z) {
        this.h = z;
    }
}
