package defpackage;

/* loaded from: classes7.dex */
public class rjz {

    /* renamed from: a, reason: collision with root package name */
    private String f16793a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean l;
    private boolean n;
    private boolean o;

    public void e(String str) {
        this.f16793a = str;
    }

    public boolean h() {
        return this.j;
    }

    public void h(boolean z) {
        this.j = z;
    }

    public boolean j() {
        return this.g;
    }

    public void i(boolean z) {
        this.g = z;
    }

    public boolean a() {
        return this.i;
    }

    public void d(boolean z) {
        this.i = z;
    }

    public boolean d() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean g() {
        return this.f;
    }

    public void g(boolean z) {
        this.f = z;
    }

    public boolean k() {
        return this.l;
    }

    public void n(boolean z) {
        this.l = z;
    }

    public boolean f() {
        return this.h;
    }

    public void j(boolean z) {
        this.h = z;
    }

    public boolean i() {
        return this.n;
    }

    public void f(boolean z) {
        this.n = z;
    }

    public boolean b() {
        return this.c;
    }

    public void e(boolean z) {
        this.c = z;
    }

    public boolean e() {
        return this.e;
    }

    public void c(boolean z) {
        this.e = z;
    }

    public boolean c() {
        return this.b;
    }

    public void b(boolean z) {
        this.b = z;
    }

    public boolean n() {
        return this.o;
    }

    public void o(boolean z) {
        this.o = z;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("ShowDataBean{ deviceUniqueCode=");
        stringBuffer.append(this.f16793a);
        stringBuffer.append(", isStep=").append(this.j);
        stringBuffer.append(", isDistance=").append(this.g);
        stringBuffer.append(", isClimbStair=").append(this.i);
        stringBuffer.append(", isCalorie=").append(this.d);
        stringBuffer.append(", isHeartRate=").append(this.f);
        stringBuffer.append(", isWeight=").append(this.l);
        stringBuffer.append(", isSleep=").append(this.h);
        stringBuffer.append(", isStress=").append(this.n);
        stringBuffer.append(", isBloodOxygen=").append(this.c);
        stringBuffer.append(", isBloodPressure=").append(this.e);
        stringBuffer.append(", isBloodSugar=").append(this.b);
        stringBuffer.append(", isTemperature=").append(this.o);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
