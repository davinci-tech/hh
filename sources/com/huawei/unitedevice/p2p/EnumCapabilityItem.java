package com.huawei.unitedevice.p2p;

/* loaded from: classes9.dex */
public enum EnumCapabilityItem {
    POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM(2),
    POINT_TO_POINT_SEND_FILES_TO_WATCH_ENUM(3);

    private int value;

    EnumCapabilityItem(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
