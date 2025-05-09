package defpackage;

/* loaded from: classes7.dex */
public class uxu extends vbj {

    /* renamed from: a, reason: collision with root package name */
    public static final uxu f17579a = new uxu(vbj.c);

    public uxu(byte[] bArr) {
        this(bArr, true);
    }

    private uxu(byte[] bArr, boolean z) {
        super(bArr, 8, z);
    }

    @Override // defpackage.vbj
    public String toString() {
        return "Token=" + e();
    }

    public static uxu d(byte[] bArr) {
        return new uxu(bArr, false);
    }
}
