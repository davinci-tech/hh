package com.amap.api.col.p0003sl;

import java.io.Serializable;

/* loaded from: classes2.dex */
public final class mj extends mi implements Serializable {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public mj() {
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    public mj(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = 0;
    }

    @Override // com.amap.api.col.p0003sl.mi
    /* renamed from: a */
    public final mi clone() {
        mj mjVar = new mj(this.h, this.i);
        mjVar.a(this);
        mjVar.j = this.j;
        mjVar.k = this.k;
        mjVar.l = this.l;
        mjVar.m = this.m;
        mjVar.n = this.n;
        return mjVar;
    }

    @Override // com.amap.api.col.p0003sl.mi
    public final String toString() {
        return "AmapCellCdma{sid=" + this.j + ", nid=" + this.k + ", bid=" + this.l + ", latitude=" + this.m + ", longitude=" + this.n + ", mcc='" + this.f1336a + "', mnc='" + this.b + "', signalStrength=" + this.c + ", asuLevel=" + this.d + ", lastUpdateSystemMills=" + this.e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newApi=" + this.i + '}';
    }
}
