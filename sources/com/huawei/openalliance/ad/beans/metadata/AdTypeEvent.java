package com.huawei.openalliance.ad.beans.metadata;

import java.util.List;

/* loaded from: classes5.dex */
public class AdTypeEvent {
    private int adType;
    private List<String> eventTypeList;

    public List<String> b() {
        return this.eventTypeList;
    }

    public void a(List<String> list) {
        this.eventTypeList = list;
    }

    public void a(int i) {
        this.adType = i;
    }

    public int a() {
        return this.adType;
    }
}
