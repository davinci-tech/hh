package defpackage;

/* loaded from: classes5.dex */
public class jie {

    /* renamed from: a, reason: collision with root package name */
    private long f13864a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int g;
    private int h;
    private int i;

    public jie() {
        this.f13864a = 0L;
        this.b = 60;
        this.i = -1;
        this.g = 0;
        this.d = -1;
        this.e = 0;
        this.h = 0;
        this.c = 0;
    }

    public jie(int i, int i2) {
        this.f13864a = 0L;
        this.b = 60;
        this.i = i;
        this.g = i2;
        this.d = -1;
        this.e = 0;
        this.h = 0;
        this.c = 0;
    }

    public long b() {
        return this.f13864a;
    }

    public int e() {
        return this.b;
    }

    public int c() {
        return this.i;
    }

    public int a() {
        return this.g;
    }

    public int d() {
        return this.h;
    }

    public void c(long j) {
        this.f13864a = j;
    }

    public void b(int i) {
        this.h = i;
    }

    public void a(int i) {
        this.c = i;
    }

    public String toString() {
        String str;
        String str2 = "Sample{startTime=" + this.f13864a + ", duration=" + this.b + ", type=" + this.i;
        int i = this.i;
        if (i == 6 || i == 7) {
            str = str2 + ", value=xx";
        } else {
            str = str2 + ", value=" + this.g;
        }
        return str + ", intensive=" + this.c + '}';
    }
}
