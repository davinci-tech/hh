package com.huawei.hwcloudmodel.model.unite;

/* loaded from: classes7.dex */
public class SyncKey {
    private Integer dataType;
    private Long deviceCode;
    private Integer type;
    private Long version;

    public Integer getDataType() {
        return this.dataType;
    }

    public void setDataType(Integer num) {
        this.dataType = num;
    }

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

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long l) {
        this.version = l;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("SyncKey{dataType='");
        stringBuffer.append(this.dataType).append("', type='");
        stringBuffer.append(this.type).append("', deviceCode='");
        stringBuffer.append(this.deviceCode).append("', version='");
        stringBuffer.append(this.version).append("'}");
        return stringBuffer.toString();
    }
}
