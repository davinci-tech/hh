package com.huawei.devicesdk.entity;

/* loaded from: classes3.dex */
public enum ConnectMode {
    GENERAL(0),
    SIMPLE(1),
    TRANSPARENT(2);

    private int mType;

    ConnectMode(int i) {
        this.mType = i;
    }

    public int value() {
        return this.mType;
    }

    public static ConnectMode getValue(int i) {
        if (i < 0 || i >= values().length) {
            return null;
        }
        return values()[i];
    }
}
