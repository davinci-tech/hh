package defpackage;

/* loaded from: classes7.dex */
public class slf {

    /* renamed from: a, reason: collision with root package name */
    private int f17100a;
    private int b;
    private int c;
    private int e;

    public slf() {
        this(0, 0, 0, 0);
    }

    public int a() {
        return this.e;
    }

    public int b() {
        return this.f17100a;
    }

    public int d() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public String toString() {
        return "HwColumn{mMargin=" + this.e + ", mGutter=" + this.c + ", mColumnCount=" + this.b + ", mMaxColumnCount=" + this.f17100a + '}';
    }

    slf(int i, int i2, int i3, int i4) {
        this.e = i;
        this.c = i2;
        this.b = i3;
        this.f17100a = i4;
    }

    public void a(int i) {
        this.b = i;
    }

    public void c(int i) {
        this.f17100a = i;
    }

    public void d(int i) {
        this.c = i;
    }

    public void e(int i) {
        this.e = i;
    }
}
