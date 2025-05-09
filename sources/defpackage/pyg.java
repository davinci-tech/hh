package defpackage;

/* loaded from: classes6.dex */
public class pyg extends nnc {
    private pyl d;

    public pyg(float f, pyl pylVar) {
        super(f);
        this.d = pylVar;
    }

    public pyl c() {
        return this.d;
    }

    @Override // defpackage.nnc
    public float b() {
        pyl pylVar = this.d;
        if (pylVar != null) {
            return pylVar.b();
        }
        return super.b();
    }

    @Override // defpackage.nnc
    public float e() {
        pyl pylVar = this.d;
        if (pylVar != null) {
            return pylVar.d();
        }
        return super.e();
    }
}
