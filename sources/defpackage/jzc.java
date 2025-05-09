package defpackage;

/* loaded from: classes5.dex */
public class jzc {
    private boolean c;
    private int d;

    jzc(int i, boolean z) {
        this.d = i;
        this.c = z;
    }

    public int d() {
        return this.d;
    }

    public boolean e() {
        return this.c;
    }

    public String toString() {
        return "SyncState{errorCode=" + this.d + ", isNeedSync=" + this.c + '}';
    }
}
