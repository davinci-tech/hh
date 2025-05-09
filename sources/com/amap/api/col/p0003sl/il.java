package com.amap.api.col.p0003sl;

/* loaded from: classes2.dex */
public final class il extends kx {
    private static int g = 10000000;

    /* renamed from: a, reason: collision with root package name */
    protected int f1187a;
    protected long b;
    private boolean d;
    private boolean e;
    private int f;
    private long h;

    @Override // com.amap.api.col.p0003sl.kx
    public final int a() {
        return 320000;
    }

    public il(boolean z, kx kxVar, long j, int i) {
        super(kxVar);
        this.e = false;
        this.d = z;
        this.f1187a = 600000;
        this.h = j;
        this.f = i;
    }

    public final void a(boolean z) {
        this.e = z;
    }

    public final void a(int i) {
        if (i <= 0) {
            return;
        }
        this.h += i;
    }

    public final long b() {
        return this.h;
    }

    @Override // com.amap.api.col.p0003sl.kx
    protected final boolean c() {
        if (this.e && this.h <= this.f) {
            return true;
        }
        if (!this.d || this.h >= this.f) {
            return false;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b < this.f1187a) {
            return false;
        }
        this.b = currentTimeMillis;
        return true;
    }
}
