package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;
import java.util.Set;

/* loaded from: classes7.dex */
public class GetSportDataByTimeReq implements IRequest {
    private static final int STRING_BUILDER_INIT_CAPACITY = 200;
    private Integer dataType;
    private Long deviceCode;
    private Long endTime;
    private Integer queryType;
    private Set<Integer> sportTypes;
    private Long startTime;

    public Integer getQueryType() {
        return this.queryType;
    }

    public void setQueryType(Integer num) {
        this.queryType = num;
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

    public Set<Integer> getSportTypes() {
        return this.sportTypes;
    }

    public void setSportTypes(Set<Integer> set) {
        this.sportTypes = set;
    }

    public Long getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Long l) {
        this.startTime = l;
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(200);
        sb.append("GetSportDataByTimeReq{queryType=");
        sb.append(this.queryType);
        sb.append(", startTime=");
        sb.append(this.startTime);
        sb.append(", endTime=");
        sb.append(this.endTime);
        sb.append(", sportTypes=");
        sb.append(this.sportTypes);
        sb.append(", dataType=");
        sb.append(this.dataType);
        sb.append(", deviceCode=");
        sb.append(this.deviceCode);
        sb.append('}');
        return sb.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/sport/getSportsDataByTime";
    }
}
