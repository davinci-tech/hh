package defpackage;

/* loaded from: classes3.dex */
public class dxi {
    private long b;
    private String c;
    private int d;
    private int e;

    public dxi(long j, int i, int i2, String str) {
        this.c = str;
        this.b = j;
        this.e = i;
        this.d = i2;
    }

    public String e() {
        return this.c;
    }

    public long d() {
        return this.b;
    }

    public int b() {
        return this.e;
    }

    public int a() {
        return this.d;
    }

    public void b(int i) {
        this.d = i;
    }

    public String toString() {
        return "mHuid = " + this.c + " mTimestamp = " + this.b + " mType = " + this.e;
    }
}
