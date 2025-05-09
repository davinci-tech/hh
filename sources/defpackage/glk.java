package defpackage;

/* loaded from: classes4.dex */
public class glk {
    private transient long c;

    protected glk(long j, boolean z) {
        this.c = j;
    }

    protected glk() {
        this.c = 0L;
    }

    protected static long b(glk glkVar) {
        if (glkVar == null) {
            return 0L;
        }
        return glkVar.c;
    }
}
