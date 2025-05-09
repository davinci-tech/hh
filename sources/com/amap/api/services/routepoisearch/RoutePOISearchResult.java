package com.amap.api.services.routepoisearch;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class RoutePOISearchResult {

    /* renamed from: a, reason: collision with root package name */
    private List<RoutePOIItem> f1592a;
    private RoutePOISearchQuery b;

    public RoutePOISearchResult(ArrayList<RoutePOIItem> arrayList, RoutePOISearchQuery routePOISearchQuery) {
        new ArrayList();
        this.f1592a = arrayList;
        this.b = routePOISearchQuery;
    }

    public List<RoutePOIItem> getRoutePois() {
        return this.f1592a;
    }

    public RoutePOISearchQuery getQuery() {
        return this.b;
    }
}
