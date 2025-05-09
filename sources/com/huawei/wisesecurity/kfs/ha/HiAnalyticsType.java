package com.huawei.wisesecurity.kfs.ha;

/* loaded from: classes7.dex */
public enum HiAnalyticsType {
    HIANALYTICS_OPERATION(0),
    HIANALYTICS_MAINTENANCE(1),
    HIANALYTICS_DIFF(3);

    private final int type;

    HiAnalyticsType(int i) {
        this.type = i;
    }

    public int getCode() {
        return this.type;
    }
}
