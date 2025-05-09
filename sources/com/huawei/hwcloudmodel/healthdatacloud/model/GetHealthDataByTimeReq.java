package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetHealthDataByTimeReq implements IRequest {
    private Integer dataType;
    private Long deviceCode;
    private Long endTime;
    private Integer queryType;
    private Long startTime;
    private Integer type;

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num;
    }

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

    public Long getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Long l) {
        this.endTime = l;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String toString() {
        return "GetHealthDataByTimeReq{queryType=" + this.queryType + ", startTime=" + this.startTime + ", endTime=" + this.endTime + ", type=" + this.type + ", dataType=" + this.dataType + ", deviceCode=" + this.deviceCode + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/health/getHealthData";
    }
}
