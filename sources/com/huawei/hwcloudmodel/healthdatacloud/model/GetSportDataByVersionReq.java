package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetSportDataByVersionReq implements IRequest {
    private Integer dataType;
    private Long deviceCode;
    private Long version;

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public void setVersion(int i) {
        this.version = Long.valueOf(i);
    }

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public String toString() {
        return "GetSportDataByVersionReq{version=" + this.version + ", dataType=" + this.dataType + ", deviceCode=" + this.deviceCode + '}';
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/sport/getSportsDataByVersion";
    }
}
