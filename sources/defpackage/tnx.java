package defpackage;

/* loaded from: classes7.dex */
public class tnx extends RuntimeException {
    private int c;

    public tnx(int i) {
        super(toq.c(i));
        this.c = i;
    }

    public int c() {
        return this.c;
    }

    public static tnx e(IllegalStateException illegalStateException) {
        if (illegalStateException == null) {
            tov.d("WearEngineException", "convertIllegalStateException IllegalStateException is null");
            return new tnx(1);
        }
        return new tnx(toq.b(illegalStateException.getMessage()));
    }
}
