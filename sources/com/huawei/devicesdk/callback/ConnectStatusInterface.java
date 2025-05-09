package com.huawei.devicesdk.callback;

import com.huawei.devicesdk.entity.DeviceInfo;

/* loaded from: classes3.dex */
public interface ConnectStatusInterface {
    void onCapabilityChanged(DeviceInfo deviceInfo);

    void onDeviceBond(DeviceInfo deviceInfo, int i);

    void onHandshakeFailed(DeviceInfo deviceInfo, int i);

    void onHandshakeFinish(DeviceInfo deviceInfo);

    default void onHandshakeStartOobe(DeviceInfo deviceInfo) {
    }

    void onPhysicalLayerConnectFailed(DeviceInfo deviceInfo, int i);

    void onPhysicalLayerConnected(DeviceInfo deviceInfo);

    void onPhysicalLayerDisconnected(DeviceInfo deviceInfo, int i);
}
