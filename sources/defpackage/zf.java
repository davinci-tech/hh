package defpackage;

/* loaded from: classes8.dex */
public final class zf extends Exception {
    private static final long serialVersionUID = 398957975986243915L;
    private final int d;

    public zf(int i, Throwable th) {
        super("Module Load Error: " + i, th);
        this.d = i;
    }

    int a() {
        return this.d;
    }
}
