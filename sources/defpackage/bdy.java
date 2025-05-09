package defpackage;

/* loaded from: classes3.dex */
public class bdy {

    /* renamed from: a, reason: collision with root package name */
    private float f343a;
    private int b;
    private float e;

    public bdy(c cVar) {
        this.e = -1.0f;
        this.f343a = -1.0f;
        this.b = -1;
        this.e = cVar.e;
        this.f343a = cVar.d;
        this.b = cVar.c;
    }

    public float e() {
        return this.e;
    }

    public float c() {
        return this.f343a;
    }

    public int a() {
        return this.b;
    }

    /* loaded from: classes.dex */
    public static class c {
        private float e = -1.0f;
        private float d = -1.0f;
        private int c = -1;

        public c a(float f) {
            this.e = f;
            return this;
        }

        public c e(float f) {
            this.d = f;
            return this;
        }

        public bdy e() {
            return new bdy(this);
        }
    }
}
