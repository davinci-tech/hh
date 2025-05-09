package com.huawei.hwcloudmodel.model.intelligent;

/* loaded from: classes5.dex */
public class StartDeviceLinkageRequest {
    private DevInfo devInfo;
    private String deviceCode;

    public String getDeviceCode() {
        return this.deviceCode;
    }

    public void setDeviceCode(String str) {
        this.deviceCode = str;
    }

    public DevInfo getDevInfo() {
        return this.devInfo;
    }

    public void setDevInfo(DevInfo devInfo) {
        this.devInfo = devInfo;
    }

    public String toString() {
        return "StartDeviceLinkageRequest{deviceCode=" + this.deviceCode + ", devInfo=" + this.devInfo + "}";
    }
}
