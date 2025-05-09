package com.amap.api.services.geocoder;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class GeocodeResult {

    /* renamed from: a, reason: collision with root package name */
    private GeocodeQuery f1496a;
    private List<GeocodeAddress> b;

    public GeocodeResult(GeocodeQuery geocodeQuery, List<GeocodeAddress> list) {
        new ArrayList();
        this.f1496a = geocodeQuery;
        this.b = list;
    }

    public GeocodeQuery getGeocodeQuery() {
        return this.f1496a;
    }

    public void setGeocodeQuery(GeocodeQuery geocodeQuery) {
        this.f1496a = geocodeQuery;
    }

    public List<GeocodeAddress> getGeocodeAddressList() {
        return this.b;
    }

    public void setGeocodeAddressList(List<GeocodeAddress> list) {
        this.b = list;
    }
}
