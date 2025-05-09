package defpackage;

/* loaded from: classes6.dex */
public class nmm implements Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private int f15392a;
    private float b;
    private String d;
    private float e;

    public nmm(float f, float f2, int i, String str) {
        this.e = f;
        this.b = f2;
        this.f15392a = i;
        this.d = str;
    }

    public float d() {
        return this.e;
    }

    public void d(float f) {
        this.e = f;
    }

    public float b() {
        return this.b;
    }

    public void b(float f) {
        this.b = f;
    }

    public int e() {
        return this.f15392a;
    }

    public String c() {
        return this.d;
    }

    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public nmm clone() throws CloneNotSupportedException {
        return new nmm(this.e, this.b, this.f15392a, this.d);
    }
}
