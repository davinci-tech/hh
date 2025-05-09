package com.huawei.callback;

import com.huawei.datatype.DeviceStatusParam;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes3.dex */
public interface DeviceStatusCallback {
    void onDeviceConnectedChanged(DeviceInfo deviceInfo, int i, DeviceStatusParam deviceStatusParam);
}
