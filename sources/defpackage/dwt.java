package defpackage;

/* loaded from: classes3.dex */
public class dwt {
    private boolean c = true;

    /* renamed from: a, reason: collision with root package name */
    private int f11871a = 3;
    private int g = 100;
    private float f = 1.5f;
    private float h = 3.0f;
    private float[] e = {12.0f, 12.0f, 33.3f};
    private float i = 0.3f;
    private float b = 2.8f;
    private boolean d = true;
    private int k = 3;
    private int j = 20000;

    public void d(boolean z) {
        this.c = z;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public void c(float f) {
        this.f = f;
    }

    public void e(float f) {
        this.i = f;
    }

    public void d(int i) {
        this.g = i;
    }

    public void d(float f) {
        this.h = f;
    }

    public void b(float[] fArr) {
        if (fArr != null) {
            this.e = (float[]) fArr.clone();
        }
    }

    public void b(float f) {
        this.b = f;
    }

    public boolean j() {
        return this.c;
    }

    public boolean h() {
        return this.d;
    }

    public int i() {
        return this.k;
    }

    public int g() {
        return this.j;
    }

    public float f() {
        return this.f;
    }

    public float e() {
        return this.i;
    }

    public int b() {
        return this.g;
    }

    public float a() {
        return this.h;
    }

    public int c() {
        return this.f11871a;
    }

    public float a(int i) {
        if (i < 0) {
            return 0.0f;
        }
        float[] fArr = this.e;
        if (i >= fArr.length) {
            return 0.0f;
        }
        return fArr[i];
    }

    public float d() {
        return this.b;
    }
}
