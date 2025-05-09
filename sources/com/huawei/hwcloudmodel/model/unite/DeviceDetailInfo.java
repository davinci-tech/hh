package com.huawei.hwcloudmodel.model.unite;

import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class DeviceDetailInfo {
    private String devId;
    private DevInfo devInfo;
    private Long deviceCode;
    private List<ServiceInfo> services;

    public void setDevId(String str) {
        this.devId = str;
    }

    public String getDevId() {
        return this.devId;
    }

    public void setDeviceCode(Long l) {
        this.deviceCode = l;
    }

    public Long getDeviceCode() {
        return this.deviceCode;
    }

    public void setDevInfo(DevInfo devInfo) {
        this.devInfo = devInfo;
    }

    public DevInfo getDevInfo() {
        return this.devInfo;
    }

    public void setServices(List<ServiceInfo> list) {
        this.services = list;
    }

    public List<ServiceInfo> getServices() {
        return this.services;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("DeviceDetailInfo{devId='");
        stringBuffer.append(CommonUtil.l(this.devId)).append("', deviceCode='");
        stringBuffer.append(this.deviceCode).append("', devInfo='");
        stringBuffer.append(this.devInfo);
        stringBuffer.append(", services='").append(this.services);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }
}
