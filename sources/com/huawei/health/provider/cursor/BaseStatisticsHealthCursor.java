package com.huawei.health.provider.cursor;

import java.util.Map;

/* loaded from: classes.dex */
public abstract class BaseStatisticsHealthCursor extends HealthCursor {
    protected Map<String, Object> mDataMap;

    public BaseStatisticsHealthCursor(Map<String, Object> map) {
        this.mDataMap = null;
        if (map != null) {
            this.mDataMap = map;
        }
        initData();
    }
}
