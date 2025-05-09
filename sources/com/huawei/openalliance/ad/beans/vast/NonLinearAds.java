package com.huawei.openalliance.ad.beans.vast;

import com.huawei.openalliance.ad.annotations.a;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class NonLinearAds {

    @a
    private String nonLinearClickThrough;
    private List<NonLinear> nonLinears;
    private Map<String, List<Tracking>> trackingEvents;

    public List<NonLinear> b() {
        return this.nonLinears;
    }

    public void a(Map<String, List<Tracking>> map) {
        this.trackingEvents = map;
    }

    public void a(List<NonLinear> list) {
        this.nonLinears = list;
    }

    public Map<String, List<Tracking>> a() {
        return this.trackingEvents;
    }
}
