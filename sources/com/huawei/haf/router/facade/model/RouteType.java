package com.huawei.haf.router.facade.model;

/* loaded from: classes.dex */
public enum RouteType {
    ACTIVITY(0),
    FRAGMENT(1),
    SERVICE(2),
    HANDLER(3);

    private final int mType;

    RouteType(int i) {
        this.mType = i;
    }

    public int type() {
        return this.mType;
    }
}
