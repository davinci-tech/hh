package com.huawei.callback;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes3.dex */
public interface BluetoothDataReceiveCallback {
    void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr);
}
