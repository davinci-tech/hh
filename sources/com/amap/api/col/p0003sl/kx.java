package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public abstract class kx {
    kx c;

    protected abstract boolean c();

    public kx() {
    }

    public kx(kx kxVar) {
        this.c = kxVar;
    }

    public void a_(boolean z) {
        kx kxVar = this.c;
        if (kxVar != null) {
            kxVar.a_(z);
        }
    }

    public int a() {
        kx kxVar = this.c;
        return Math.min(Integer.MAX_VALUE, kxVar != null ? kxVar.a() : Integer.MAX_VALUE);
    }

    public void a_(int i) {
        kx kxVar = this.c;
        if (kxVar != null) {
            kxVar.a_(i);
        }
    }

    public final boolean d() {
        kx kxVar = this.c;
        if (kxVar == null || kxVar.d()) {
            return c();
        }
        return false;
    }
}
