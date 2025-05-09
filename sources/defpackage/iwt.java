package defpackage;

/* loaded from: classes7.dex */
public class iwt extends Exception {
    private static final long serialVersionUID = 1726426141039183018L;
    private boolean b;

    public iwt(String str) {
        super(str);
    }

    public iwt(String str, boolean z) {
        super(str);
        this.b = z;
    }

    public boolean b() {
        return this.b;
    }
}
