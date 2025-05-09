package com.huawei.hwbtsdk.btdatatype.callback;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbtsdk.btdatatype.datatype.OperationDeviceInfo;

/* loaded from: classes5.dex */
public interface BtDeviceStateCallback {
    void disconnectBluetooth(DeviceInfo deviceInfo, int i);

    void onAckReceived(DeviceInfo deviceInfo, int i, byte[] bArr);

    void onDataReceived(DeviceInfo deviceInfo, int i, byte[] bArr);

    void onDeviceConnectionStateChanged(DeviceInfo deviceInfo, int i, OperationDeviceInfo operationDeviceInfo);
}
