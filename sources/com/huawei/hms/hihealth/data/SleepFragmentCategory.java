package com.huawei.hms.hihealth.data;

@Deprecated
/* loaded from: classes9.dex */
public enum SleepFragmentCategory {
    SLEEP_LIGHT(1),
    SLEEP_DREAM(2),
    SLEEP_DEEP(3),
    SLEEP_AWAKE(4),
    SLEEP_NAP(5);

    private int value;

    public int getValue() {
        return this.value;
    }

    SleepFragmentCategory(int i) {
        this.value = i;
    }
}
