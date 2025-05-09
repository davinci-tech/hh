package com.huawei.health.marketing.datatype;

import com.huawei.hiai.awareness.AwarenessConstants;

/* loaded from: classes3.dex */
public class TimePeriod {
    private String beginTime;
    private String description;
    private String endTime;

    public String getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(String str) {
        this.beginTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String toString() {
        return "[" + this.beginTime + AwarenessConstants.SECOND_ACTION_SPLITE_TAG + this.endTime + ": " + this.description + "]";
    }
}
