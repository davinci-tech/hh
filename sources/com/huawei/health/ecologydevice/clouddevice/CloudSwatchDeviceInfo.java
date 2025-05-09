package com.huawei.health.ecologydevice.clouddevice;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes3.dex */
public class CloudSwatchDeviceInfo extends DeviceInfo {
    private String mDeviceSn;
    private String mMac;

    public String getDeviceSn() {
        return this.mDeviceSn;
    }

    public void setDeviceSn(String str) {
        this.mDeviceSn = str;
    }

    public String getMac() {
        return this.mMac;
    }

    public void setMac(String str) {
        this.mMac = str;
    }
}
