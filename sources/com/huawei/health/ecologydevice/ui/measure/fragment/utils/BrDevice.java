package com.huawei.health.ecologydevice.ui.measure.fragment.utils;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes8.dex */
public class BrDevice extends HealthDevice {
    private static final String TAG = "BrDevice";
    private String mDeviceName;
    private String mMacAddress;
    private String mUniqueId;

    public void setUniqueId(String str) {
        this.mUniqueId = str;
        LogUtil.a(TAG, "mUniqueId:", str);
    }

    public void setMacAddress(String str) {
        this.mMacAddress = str;
        LogUtil.a(TAG, "mMacAddress:", str);
    }

    public void setDeviceName(String str) {
        this.mDeviceName = str;
        LogUtil.a(TAG, "mDeviceName:", str);
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        return this.mDeviceName;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        return this.mMacAddress;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        return this.mUniqueId;
    }
}
