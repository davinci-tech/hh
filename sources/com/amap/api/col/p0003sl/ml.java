package com.amap.api.col.p0003sl;

import java.io.Serializable;

/* loaded from: classes2.dex */
public final class ml extends mi implements Serializable {
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;

    public ml() {
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
    }

    public ml(boolean z) {
        super(z, true);
        this.j = 0;
        this.k = 0;
        this.l = Integer.MAX_VALUE;
        this.m = Integer.MAX_VALUE;
        this.n = Integer.MAX_VALUE;
    }

    @Override // com.amap.api.col.p0003sl.mi
    /* renamed from: a */
    public final mi clone() {
        ml mlVar = new ml(this.h);
        mlVar.a(this);
        mlVar.j = this.j;
        mlVar.k = this.k;
        mlVar.l = this.l;
        mlVar.m = this.m;
        mlVar.n = this.n;
        return mlVar;
    }

    @Override // com.amap.api.col.p0003sl.mi
    public final String toString() {
        return "AmapCellLte{tac=" + this.j + ", ci=" + this.k + ", pci=" + this.l + ", earfcn=" + this.m + ", timingAdvance=" + this.n + ", mcc='" + this.f1336a + "', mnc='" + this.b + "', signalStrength=" + this.c + ", asuLevel=" + this.d + ", lastUpdateSystemMills=" + this.e + ", lastUpdateUtcMills=" + this.f + ", age=" + this.g + ", main=" + this.h + ", newApi=" + this.i + '}';
    }
}
