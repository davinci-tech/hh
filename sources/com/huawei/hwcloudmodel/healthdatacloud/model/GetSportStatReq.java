package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetSportStatReq implements IRequest {
    private static final int STRING_BUILDER_INIT_CAPACITY = 150;
    private Integer dataSource = 2;
    private Long deviceCode;
    private Integer endTime;
    private Integer startTime;

    public Integer getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Integer num) {
        this.endTime = num;
    }

    public Integer getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Integer num) {
        this.startTime = num;
    }

    public Integer getDataSource() {
        return this.dataSource;
    }

    public void setDataSource(Integer num) {
        this.dataSource = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(150);
        sb.append("GetSportStatReq{startTime=");
        sb.append(this.startTime);
        sb.append(", endTime=");
        sb.append(this.endTime);
        sb.append(", dataSource=");
        sb.append(this.dataSource);
        sb.append(", deviceCode=");
        sb.append(this.deviceCode);
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/sport/v2/getSportsStat";
    }
}
