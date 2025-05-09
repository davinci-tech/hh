package com.huawei.hwcloudmodel.healthdatacloud.model;

import com.huawei.networkclient.IRequest;
import health.compact.a.Utils;

/* loaded from: classes7.dex */
public class GetHealthDataByVersionReq implements IRequest {
    private Integer dataType;
    private Long deviceCode;
    private Integer type;
    private long version;

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

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(long j) {
        this.version = j;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("GetHealthDataByVersionReq{version=");
        stringBuffer.append(this.version);
        stringBuffer.append(", type=").append(this.type);
        stringBuffer.append(", dataType=").append(this.dataType);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    @Override // com.huawei.networkclient.IRequest
    public String getUrl() {
        return Utils.e() + "/dataQuery/health/getHealthDataByVersion";
    }
}
