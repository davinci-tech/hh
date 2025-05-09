package com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.unitedevicemanger;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes.dex */
public interface DeviceStateCallback {
    void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr);

    void onConnectStatusChanged(DeviceInfo deviceInfo);

    void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr);

    void onDatasReceived(DeviceInfo deviceInfo, int i, String str);
}
