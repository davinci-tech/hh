package com.amap.api.services.geocoder;

/* loaded from: classes2.dex */
public class RegeocodeResult {

    /* renamed from: a, reason: collision with root package name */
    private RegeocodeQuery f1500a;
    private RegeocodeAddress b;

    public RegeocodeResult(RegeocodeQuery regeocodeQuery, RegeocodeAddress regeocodeAddress) {
        this.f1500a = regeocodeQuery;
        this.b = regeocodeAddress;
    }

    public RegeocodeQuery getRegeocodeQuery() {
        return this.f1500a;
    }

    public void setRegeocodeQuery(RegeocodeQuery regeocodeQuery) {
        this.f1500a = regeocodeQuery;
    }

    public RegeocodeAddress getRegeocodeAddress() {
        return this.b;
    }

    public void setRegeocodeAddress(RegeocodeAddress regeocodeAddress) {
        this.b = regeocodeAddress;
    }
}
