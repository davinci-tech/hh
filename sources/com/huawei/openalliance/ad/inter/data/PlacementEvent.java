package com.huawei.openalliance.ad.inter.data;

/* loaded from: classes9.dex */
public enum PlacementEvent {
    VIP("clickvip"),
    CLOSE("userclose");

    private final String event;

    public String a() {
        return this.event;
    }

    PlacementEvent(String str) {
        this.event = str;
    }
}
