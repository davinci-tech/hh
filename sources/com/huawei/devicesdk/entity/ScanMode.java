package com.huawei.devicesdk.entity;

/* loaded from: classes3.dex */
public enum ScanMode {
    BLE(0),
    BR(1),
    BR_BLE(2);

    private int mType;

    ScanMode(int i) {
        this.mType = i;
    }

    public int value() {
        return this.mType;
    }

    public static ScanMode getValue(int i) {
        if (i < 0 || i >= values().length) {
            return null;
        }
        return values()[i];
    }
}
