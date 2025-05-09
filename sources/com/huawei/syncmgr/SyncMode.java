package com.huawei.syncmgr;

/* loaded from: classes6.dex */
public enum SyncMode {
    INCREMENTAL(0),
    FULL(1),
    CUSTOM(2);

    int mValue;

    SyncMode(int i) {
        this.mValue = i;
    }

    public int getValue() {
        return this.mValue;
    }
}
