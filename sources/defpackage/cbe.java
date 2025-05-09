package defpackage;

/* loaded from: classes3.dex */
public class cbe extends Exception {
    private static final long serialVersionUID = -5701408157042313112L;

    /* renamed from: a, reason: collision with root package name */
    private final int f591a;

    public cbe(int i, String str) {
        this(i, str, null);
    }

    public cbe(int i, String str, Throwable th) {
        super("Sport HiWear Error: " + i + " Error Desc: " + str, th);
        this.f591a = i;
    }
}
