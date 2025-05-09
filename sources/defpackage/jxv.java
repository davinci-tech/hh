package defpackage;

/* loaded from: classes5.dex */
public class jxv {
    private int h;
    private int j;
    private long e = 0;
    private int b = 60;
    private int d = -1;
    private int c = 0;
    private int g = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f14178a = 0;

    public jxv(int i, int i2) {
        this.j = i;
        this.h = i2;
    }

    public long b() {
        return this.e;
    }

    public int d() {
        return this.b;
    }

    public int a() {
        return this.j;
    }

    public int e() {
        return this.h;
    }

    public int c() {
        return this.g;
    }

    public void d(long j) {
        this.e = j;
    }

    public void b(int i) {
        this.g = i;
    }

    public void a(int i) {
        this.f14178a = i;
    }

    public String toString() {
        String str;
        String str2 = "Sample{startTime=" + this.e + ", duration=" + this.b + ", type=" + this.j;
        int i = this.j;
        if (i == 6 || i == 7) {
            str = str2 + ", value=xx";
        } else {
            str = str2 + ", value=" + this.h;
        }
        return str + ", intensive=" + this.f14178a + '}';
    }
}
