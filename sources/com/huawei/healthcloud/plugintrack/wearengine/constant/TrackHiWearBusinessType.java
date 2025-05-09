package com.huawei.healthcloud.plugintrack.wearengine.constant;

/* loaded from: classes8.dex */
public enum TrackHiWearBusinessType {
    RUN_PLAN_INFO_FILE(2),
    RQ_DATA_INFO_FILE(3);

    int mTypeValue;

    TrackHiWearBusinessType(int i) {
        this.mTypeValue = i;
    }

    public int getTypeValue() {
        return this.mTypeValue;
    }
}
