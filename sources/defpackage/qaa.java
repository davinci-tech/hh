package defpackage;

/* loaded from: classes6.dex */
public class qaa {

    /* renamed from: a, reason: collision with root package name */
    private float f16366a;
    private String b;
    private float c;
    private int d;
    private float e;
    private int f;
    private String g;
    private int h;
    private long i;
    private String j;
    private float k;
    private int n;
    private long o;

    public void d(long j) {
        this.o = j;
    }

    public long m() {
        return this.o;
    }

    public int i() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public int d() {
        return this.d;
    }

    public void d(int i) {
        this.d = i;
    }

    public float e() {
        return this.f16366a;
    }

    public void d(float f) {
        this.f16366a = f;
    }

    public int g() {
        return this.h;
    }

    public void e(int i) {
        this.h = i;
    }

    public float c() {
        return this.e;
    }

    public void b(float f) {
        this.e = f;
    }

    public float a() {
        return this.c;
    }

    public void c(float f) {
        this.c = f;
    }

    public String b() {
        return this.b;
    }

    public void c(String str) {
        this.b = str;
    }

    public String h() {
        return this.g;
    }

    public void e(String str) {
        this.g = str;
    }

    public void d(String str) {
        this.j = str;
    }

    public int k() {
        return this.n;
    }

    public void c(int i) {
        this.n = i;
    }

    public long f() {
        return this.i;
    }

    public void c(long j) {
        if (String.valueOf(j).matches("\\d{13}")) {
            this.i = j / 1000;
        } else {
            this.i = j;
        }
    }

    public float j() {
        return this.k;
    }

    public void e(float f) {
        this.k = f;
    }

    public String toString() {
        return "BloodSugarDietBean{mDietType=" + this.d + ", mDiffNum=" + this.f16366a + ", mDiffStatus=" + this.h + ", mDietCalorie=" + this.e + ", mDietCarbohydrates=" + this.c + ", mDietDescription='" + this.b + "', mHighGiTitle='" + this.j + "', mHighGiFood='" + this.g + "', mWhichMeal=" + this.n + ", mEatTime=" + this.i + '}';
    }
}
