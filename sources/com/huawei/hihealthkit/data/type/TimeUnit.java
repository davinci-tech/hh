package com.huawei.hihealthkit.data.type;

/* loaded from: classes.dex */
public enum TimeUnit {
    MINUTE(1),
    HOUR(2),
    DAY(3),
    WEEK(4),
    MONTH(5),
    YEAR(6),
    NATURAL_WEEK(9);

    private final int code;

    TimeUnit(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
