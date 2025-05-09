package com.huawei.health.marketing.datatype;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class SpecificDateTimePeriod {

    @SerializedName("dates")
    private String dates;

    @SerializedName("endTime")
    private String endTime;

    @SerializedName("startTime")
    private String startTime;

    public String getDates() {
        return this.dates;
    }

    public void setDates(String str) {
        this.dates = str;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String str) {
        this.startTime = str;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String str) {
        this.endTime = str;
    }

    public String toString() {
        return "SpecificDateTimePeriod{dates='" + this.dates + ", startTime=" + this.startTime + ", endTime=" + this.endTime + '}';
    }
}
