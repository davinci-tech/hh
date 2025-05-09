package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes5.dex */
public class GetMotionPathByTimeReq {
    private Integer dataType;
    private Long deviceCode;
    private Long endTime;
    private Integer queryType;
    private Long startTime;

    public Integer getQueryType() {
        return this.queryType;
    }

    public void setQueryType(Integer num) {
        this.queryType = num;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num;
    }

    public String toString() {
        return "GetMotionPathByTimeReq{queryType=" + this.queryType + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", dataType=" + this.dataType + ", deviceCode=" + this.deviceCode + '}';
    }
}
