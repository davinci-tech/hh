package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes.dex */
public class DataDeleteCondition {
    private Long deviceCode;
    private Long endTime;
    private String key;
    private Long startTime;
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

    public void setKey(String str) {
        this.key = str;
    }

    public String getKey() {
        return this.key;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DataDeleteCondition{,startTime=");
        stringBuffer.append(this.startTime);
        stringBuffer.append(", endTime=").append(this.endTime);
        stringBuffer.append(", type=").append(this.type);
        stringBuffer.append(", key=").append(this.key);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
