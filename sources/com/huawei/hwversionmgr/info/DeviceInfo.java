package com.huawei.hwversionmgr.info;

/* loaded from: classes5.dex */
public class DeviceInfo {
    private String deviceId;
    private String imei;
    private String location;
    private String udid;

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getUdid() {
        return this.udid;
    }

    public void setUdid(String str) {
        this.udid = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String str) {
        this.location = str;
    }
}
