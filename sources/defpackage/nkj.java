package defpackage;

/* loaded from: classes9.dex */
public class nkj {
    public float b;
    public float c;
    public float d;

    public nkj() {
    }

    public nkj(float f) {
        this(f, f, f);
    }

    public nkj(float f, float f2, float f3) {
        this.d = f;
        this.c = f2;
        this.b = f3;
    }

    public nkj c(nkj nkjVar) {
        nkj nkjVar2 = new nkj();
        nkjVar2.d = this.d + nkjVar.d;
        nkjVar2.c = this.c + nkjVar.c;
        nkjVar2.b = this.b + nkjVar.b;
        return nkjVar2;
    }

    public nkj c(float f, float f2, float f3) {
        nkj nkjVar = new nkj();
        nkjVar.d += f;
        nkjVar.c = nkjVar.c + f2 + f2;
        return nkjVar;
    }

    public String toString() {
        return "Vec3{x=" + this.d + ", y=" + this.c + ", z=" + this.b + '}';
    }
}
