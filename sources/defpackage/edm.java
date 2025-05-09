package defpackage;

/* loaded from: classes3.dex */
public class edm extends nnc {
    private float d;
    private float f;
    private edj g;
    private float h;
    private float i;

    public edm(float f, edj edjVar) {
        super(f);
        this.g = edjVar;
    }

    public float i() {
        return this.i;
    }

    public void e(float f) {
        this.i = f;
    }

    public float a() {
        return this.h;
    }

    public void d(float f) {
        this.h = f;
    }

    public float d() {
        return this.f;
    }

    public void a(float f) {
        this.f = f;
    }

    public float c() {
        return this.d;
    }

    public void b(float f) {
        this.d = f;
    }

    public void a(edj edjVar) {
        this.g = edjVar;
    }

    public edj j() {
        return this.g;
    }

    @Override // defpackage.nnc
    public float b() {
        edj edjVar = this.g;
        if (edjVar != null) {
            return edjVar.b();
        }
        return super.b();
    }

    @Override // defpackage.nnc
    public float e() {
        edj edjVar = this.g;
        if (edjVar != null) {
            return edjVar.e();
        }
        return super.e();
    }
}
