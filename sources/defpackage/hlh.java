package defpackage;

/* loaded from: classes4.dex */
public class hlh {
    private float b = 0.0f;
    private hjd c = new hjd(0.0d, 0.0d);
    private float e = 0.0f;
    private float d = 0.0f;

    /* renamed from: a, reason: collision with root package name */
    private float f13234a = 0.0f;

    public static hlh a(hlh hlhVar) {
        if (hlhVar == null) {
            return new hlh();
        }
        hlh hlhVar2 = new hlh();
        hlhVar2.e(hlhVar.c()).b(hlhVar.b()).d(hlhVar.a()).c(hlhVar.d()).c(hlhVar.e());
        return hlhVar2;
    }

    public float e() {
        return this.f13234a;
    }

    public hlh c(float f) {
        this.f13234a = f;
        return this;
    }

    public float a() {
        return this.b;
    }

    public hlh d(float f) {
        this.b = f;
        return this;
    }

    public hjd d() {
        return this.c;
    }

    public hlh c(hjd hjdVar) {
        this.c = hjdVar;
        return this;
    }

    public float b() {
        return this.e;
    }

    public hlh b(float f) {
        this.e = f;
        return this;
    }

    public float c() {
        return this.d;
    }

    public hlh e(float f) {
        this.d = f;
        return this;
    }
}
