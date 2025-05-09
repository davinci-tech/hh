package com.huawei.hwcloudmodel.model.intelligent;

import java.util.List;

/* loaded from: classes5.dex */
public class TransferDeviceDataRequest {
    private String devId;
    private List<DeviceServiceInfo> services;

    public String getDevId() {
        return this.devId;
    }

    public void setDevId(String str) {
        this.devId = str;
    }

    public List<DeviceServiceInfo> getServices() {
        return this.services;
    }

    public void setServices(List<DeviceServiceInfo> list) {
        this.services = list;
    }

    public String toString() {
        return "TransferDeviceDataRequest{devId=" + this.devId + ", services=" + this.services + "}";
    }
}
