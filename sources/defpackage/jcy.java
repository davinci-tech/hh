package defpackage;

/* loaded from: classes5.dex */
public class jcy {

    /* renamed from: a, reason: collision with root package name */
    private boolean f13749a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean g;
    private boolean i;
    private boolean j;

    public jcy(boolean[] zArr) {
        if (zArr == null || zArr.length != 8) {
            return;
        }
        this.e = zArr[0];
        this.f13749a = zArr[1];
        this.d = zArr[2];
        this.b = zArr[3];
        this.c = zArr[4];
        this.g = zArr[5];
        this.i = zArr[6];
        this.j = zArr[7];
    }

    public boolean b() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.e))).booleanValue();
    }

    public boolean c() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.f13749a))).booleanValue();
    }

    public boolean d() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.d))).booleanValue();
    }

    public boolean e() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.b))).booleanValue();
    }

    public boolean a() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.c))).booleanValue();
    }

    public boolean g() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.g))).booleanValue();
    }

    public boolean f() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.i))).booleanValue();
    }

    public boolean h() {
        return ((Boolean) jdy.d(Boolean.valueOf(this.j))).booleanValue();
    }
}
