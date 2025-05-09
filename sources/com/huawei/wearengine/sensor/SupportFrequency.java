package com.huawei.wearengine.sensor;

/* loaded from: classes9.dex */
public enum SupportFrequency {
    HIGH(1),
    MID(2);

    private int value;

    SupportFrequency(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
