package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes5.dex */
public class GetSleepStatReq {
    private static final int STRING_BUILDER_INIT_CAPACITY = 150;
    private Integer dataSource = 2;
    private Long deviceCode;
    private Integer endTime;
    private Integer startTime;

    public Integer getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Integer num) {
        this.startTime = num;
    }

    public Integer getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Integer num) {
        this.endTime = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public Integer getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(Integer num) {
        this.dataSource = num;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(150);
        sb.append("GetSleepStatReq{startTime=");
        sb.append(this.startTime);
        sb.append(", endTime=");
        sb.append(this.endTime);
        sb.append(", dataSource=");
        sb.append(this.dataSource);
        sb.append(", deviceCode=");
        sb.append(this.deviceCode);
        sb.append('}');
        return sb.toString();
    }
}
