package com.huawei.hihealth.device;

/* loaded from: classes.dex */
public class HiHealthDeviceInfo {
    private String mDeviceModel;
    private String mDeviceName;
    private String mDeviceType;
    private String mDeviceUniqueCode;

    public HiHealthDeviceInfo(String str, String str2, String str3) {
        this.mDeviceUniqueCode = str;
        this.mDeviceName = str2;
        this.mDeviceModel = str3;
    }

    public String getDeviceUniqueCode() {
        return this.mDeviceUniqueCode;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public String getDeviceModel() {
        return this.mDeviceModel;
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(String str) {
        this.mDeviceType = str;
    }
}
