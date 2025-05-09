package defpackage;

/* loaded from: classes8.dex */
public final class zj extends zm {
    private final int b;
    private final Throwable d;

    public zj(zm zmVar, int i, Throwable th) {
        super(zmVar.f17774a, zmVar.e, zmVar.c);
        this.b = i;
        this.d = th;
    }

    public int b() {
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
