package com.huawei.devicesdk.entity;

/* loaded from: classes3.dex */
public enum SendMode {
    PROTOCOL_TYPE_5A(0),
    PROTOCOL_TYPE_D(1),
    PROTOCOL_TYPE_DIRECT(2),
    PROTOCOL_TYPE_FRAGMENT(3);

    private int mSendMode;

    SendMode(int i) {
        this.mSendMode = i;
    }

    public int value() {
        return this.mSendMode;
    }

    public static SendMode getValue(int i) {
        if (i < 0 || i >= values().length) {
            return null;
        }
        return values()[i];
    }
}
