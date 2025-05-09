package com.amap.api.col.p0003sl;

import java.io.Serializable;

/* loaded from: classes2.dex */
public final class mm extends mi implements Serializable {
    public int j;
    public int k;
    public int l;
    public int m;

    public mm() {
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
    }

    public mm(boolean z, boolean z2) {
        super(z, z2);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.col.p0003sl.mi
    /* renamed from: a */
    public final mi clone() {
        mm mmVar = new mm(this.h, this.i);
        mmVar.a(this);
        mmVar.j = this.j;
        mmVar.k = this.k;
        mmVar.l = this.l;
        mmVar.m = this.m;
        return mmVar;
    }

    @Override // com.amap.api.col.p0003sl.mi
    public final String toString() {
        return "AmapCellWcdma{lac=" + this.j + ", cid=" + this.k + ", psc=" + this.l + ", uarfcn=" + this.m + ", mcc='" + this.f1336a + "', mnc='" + this.b + "', signalStrength=" + this.c + ", asuLevel=" + this.d + ", lastUpdateSystemMills=" + this.e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newApi=" + this.i + '}';
    }
}
