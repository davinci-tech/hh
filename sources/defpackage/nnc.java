package defpackage;

/* loaded from: classes6.dex */
public class nnc extends nnz {

    /* renamed from: a, reason: collision with root package name */
    protected int f15396a;
    protected float b;
    protected float c;
    protected int e;

    public nnc(float f, float f2) {
        this.f15396a = 0;
        this.e = 0;
        this.c = f;
        this.b = f2;
    }

    public nnc(float f) {
        this.b = 0.0f;
        this.f15396a = 0;
        this.e = 0;
        this.c = f;
    }

    public nnc(float f, int i, int i2) {
        this.b = 0.0f;
        this.c = f;
        this.f15396a = i;
        this.e = i2;
    }

    public nnc(float f, float f2, int i, int i2) {
        this.c = f;
        this.b = f2;
        this.f15396a = i;
        this.e = i2;
    }

    public float b() {
        return this.c;
    }

    public void c(float f) {
        this.c = f;
    }

    public float e() {
        return this.b;
    }

    public int f() {
        return this.f15396a;
    }

    public int g() {
        return this.e;
    }
}
