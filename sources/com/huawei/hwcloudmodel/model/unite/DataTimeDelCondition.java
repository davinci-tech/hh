package com.huawei.hwcloudmodel.model.unite;

import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class DataTimeDelCondition {

    @SerializedName("deviceCode")
    private Long deviceCode;

    @SerializedName("endTime")
    private Long endTime;

    @SerializedName("startTime")
    private Long startTime;

    @SerializedName("type")
    private Integer type;

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
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

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DataTimeDelCondition{,startTime=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(", endTime=").append(this.endTime);
        stringBuffer.append(", type=").append(this.type);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
