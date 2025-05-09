package defpackage;

/* loaded from: classes3.dex */
public class ech extends nnc {
    private eck d;

    public ech(float f, eck eckVar) {
        super(f);
        this.d = eckVar;
    }

    public eck a() {
        return this.d;
    }

    @Override // defpackage.nnc
    public float b() {
        eck eckVar = this.d;
        if (eckVar != null) {
            return eckVar.c();
        }
        return super.b();
    }

    @Override // defpackage.nnc
    public float e() {
        eck eckVar = this.d;
        if (eckVar != null) {
            return eckVar.a();
        }
        return super.e();
    }
}
