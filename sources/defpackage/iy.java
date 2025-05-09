package defpackage;

/* loaded from: classes7.dex */
public final class iy implements Runnable {
    public final /* synthetic */ iw d;

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.d.e();
        } catch (Exception e) {
            ix.d(e);
        }
    }

    public iy(iw iwVar) {
        this.d = iwVar;
    }
}
