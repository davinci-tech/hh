package com.amap.api.services.nearby;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class NearbySearchResult {

    /* renamed from: a, reason: collision with root package name */
    private List<NearbyInfo> f1511a = new ArrayList();
    private int b = 0;

    public List<NearbyInfo> getNearbyInfoList() {
        return this.f1511a;
    }

    public int getTotalNum() {
        return this.b;
    }

    public void setNearbyInfoList(List<NearbyInfo> list) {
        this.f1511a = list;
        this.b = list.size();
    }
}
