package com.amap.api.col.p0003sl;

import java.io.Serializable;

/* loaded from: classes2.dex */
public final class mk extends mi implements Serializable {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;

    public mk() {
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
    }

    public mk(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
        this.o = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.col.p0003sl.mi
    /* renamed from: a */
    public final mi clone() {
        mk mkVar = new mk(this.h, this.i);
        mkVar.a(this);
        mkVar.j = this.j;
        mkVar.k = this.k;
        mkVar.l = this.l;
        mkVar.m = this.m;
        mkVar.n = this.n;
        mkVar.o = this.o;
        return mkVar;
    }

    @Override // com.amap.api.col.p0003sl.mi
    public final String toString() {
        return "AmapCellGsm{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", arfcn=" + this.m + ", bsic=" + this.n + ", timingAdvance=" + this.o + ", mcc='" + this.f1336a + "', mnc='" + this.b + "', signalStrength=" + this.c + ", asuLevel=" + this.d + ", lastUpdateSystemMills=" + this.e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newApi=" + this.i + '}';
    }
}
