package defpackage;

/* loaded from: classes8.dex */
public class zn extends zm {
    private final int b;
    private final Throwable d;

    public zn(zm zmVar, int i, Throwable th) {
        super(zmVar.f17774a, zmVar.e, zmVar.c);
        this.b = i;
        this.d = th;
    }

    public int e() {
        return this.b;
    }

    @Override // defpackage.zm
    protected StringBuilder b(StringBuilder sb) {
        super.b(sb);
        sb.append(", errorCode:");
        sb.append(this.b);
        sb.append(", errorMsg:");
        sb.append(this.d.getMessage());
        return sb;
    }
}
