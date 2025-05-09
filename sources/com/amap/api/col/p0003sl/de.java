package com.amap.api.col.p0003sl;

import com.autonavi.amap.mapcore.DPoint;

/* loaded from: classes8.dex */
public final class de {

    /* renamed from: a, reason: collision with root package name */
    public final double f966a;
    public final double b;
    public final double c;
    public final double d;
    public final double e;
    public final double f;

    public de(double d, double d2, double d3, double d4) {
        this.f966a = d;
        this.b = d3;
        this.c = d2;
        this.d = d4;
        this.e = (d + d2) / 2.0d;
        this.f = (d3 + d4) / 2.0d;
    }

    public final boolean a(double d, double d2) {
        return this.f966a <= d && d <= this.c && this.b <= d2 && d2 <= this.d;
    }

    public final boolean a(DPoint dPoint) {
        return a(dPoint.x, dPoint.y);
    }

    private boolean a(double d, double d2, double d3, double d4) {
        return d < this.c && this.f966a < d2 && d3 < this.d && this.b < d4;
    }

    public final boolean a(de deVar) {
        return a(deVar.f966a, deVar.c, deVar.b, deVar.d);
    }

    public final boolean b(de deVar) {
        return deVar.f966a >= this.f966a && deVar.c <= this.c && deVar.b >= this.b && deVar.d <= this.d;
    }
}
