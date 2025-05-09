package com.huawei.hwdevice.outofprocess.mgr.device;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public class CloudDeviceInfo extends DeviceInfo {
    private String mDeviceProfileId;
    private boolean mIsCloudDevice;
    private String mSmartDeviceUdid;

    public String getSmartDeviceUdid() {
        return this.mSmartDeviceUdid;
    }

    public void setSmartDeviceUdid(String str) {
        this.mSmartDeviceUdid = str;
    }

    public String getDeviceProfileId() {
        return this.mDeviceProfileId;
    }

    public void setDeviceProfileId(String str) {
        this.mDeviceProfileId = str;
    }

    public boolean getIsCloudDevice() {
        return this.mIsCloudDevice;
    }

    public void setIsCloudDevice(boolean z) {
        this.mIsCloudDevice = z;
    }
}
