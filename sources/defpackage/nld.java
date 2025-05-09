package defpackage;

/* loaded from: classes6.dex */
public class nld {
    private boolean b;
    private boolean c;
    private boolean e;

    public nld() {
    }

    public nld(boolean z, boolean z2) {
        this.c = z;
        this.b = z2;
    }

    public nld(boolean z, boolean z2, boolean z3) {
        this.c = z;
        this.b = z2;
        this.e = z3;
    }

    public nld b(boolean z) {
        this.c = z;
        return this;
    }

    public boolean a() {
        return this.c;
    }

    public nld c(boolean z) {
        this.b = z;
        return this;
    }

    public boolean b() {
        return this.b;
    }

    public nld d(boolean z) {
        this.e = z;
        return this;
    }

    public boolean d() {
        return this.e;
    }
}
