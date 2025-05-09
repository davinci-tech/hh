package defpackage;

/* loaded from: classes3.dex */
public class ckh {

    /* renamed from: a, reason: collision with root package name */
    private int f766a;
    private int b;
    private int c;
    private int e;

    public int c() {
        return this.c;
    }

    public void c(int i) {
        if (i == 65535) {
            this.c = -1;
        } else {
            this.c = i;
        }
    }

    public int e() {
        return this.e;
    }

    public void d(int i) {
        if (i == 65535) {
            this.e = -1;
        } else {
            this.e = i;
        }
    }

    public int b() {
        return this.b;
    }

    public void e(int i) {
        this.b = i;
    }

    public int d() {
        return this.f766a;
    }

    public void a(int i) {
        this.f766a = i;
    }

    public String toString() {
        return "RopeSkippingTrick{mSingleTrickNum=" + this.f766a + ", mDoubleTrickNum=" + this.b + ", mMultipleTrickNum=" + this.e + ", mReverseTrickNum=" + this.c + '}';
    }
}
