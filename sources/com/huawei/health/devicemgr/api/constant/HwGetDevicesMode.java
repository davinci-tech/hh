package com.huawei.health.devicemgr.api.constant;

/* loaded from: classes.dex */
public enum HwGetDevicesMode {
    ALL_DEVICES(0),
    CONNECTED_MAIN_DEVICES(1),
    ACTIVE_MAIN_DEVICES(2),
    CONNECTED_AW70_DEVICES(3),
    IDENTIFY_DEVICES(4),
    CONNECTED_DEVICES(5),
    ACTIVE_DEVICES(6),
    MAIN_FOLLOW_DEVICES(7),
    FOLLOWED_DEVICES(8);

    private int mType;

    HwGetDevicesMode(int i) {
        this.mType = i;
    }

    public int value() {
        return this.mType;
    }

    public static HwGetDevicesMode getValue(int i) {
        if (i < 0 || i >= values().length) {
            return null;
        }
        return values()[i];
    }
}
