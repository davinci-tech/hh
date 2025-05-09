package com.huawei.hms.hihealth.data;

/* loaded from: classes9.dex */
public class SubDataRelation {
    private String dataCollectorId;
    private String dataTypeName;
    private long endTime;
    private long startTime;

    public void setStartTime(long j) {
        this.startTime = j;
    }

    public void setEndTime(long j) {
        this.endTime = j;
    }

    public void setDataTypeName(String str) {
        this.dataTypeName = str;
    }

    public void setDataCollectorId(String str) {
        this.dataCollectorId = str;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }

    public String getDataTypeName() {
        return this.dataTypeName;
    }

    public String getDataCollectorId() {
        return this.dataCollectorId;
    }

    public SubDataRelation(long j, long j2, String str, String str2) {
        this.startTime = j;
        this.endTime = j2;
        this.dataCollectorId = str;
        this.dataTypeName = str2;
    }
}
