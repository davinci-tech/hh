package com.huawei.devicesdk.api;

import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import defpackage.bjf;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public interface DevicesManagementInterface {
    void connectDevice(DeviceInfo deviceInfo, boolean z, ConnectMode connectMode);

    void createSystemBond(DeviceInfo deviceInfo, ConnectMode connectMode);

    void destory();

    void disconnect(DeviceInfo deviceInfo);

    Map<String, UniteDevice> getDeviceList();

    void initUniteService(DeviceCompatibleCallback deviceCompatibleCallback);

    boolean isSupportCharacter(DeviceInfo deviceInfo, String str, String str2);

    boolean isSupportService(DeviceInfo deviceInfo, String str);

    void registerDeviceMessageListener(MessageReceiveCallback messageReceiveCallback);

    void registerDeviceStateListener(DeviceStatusChangeCallback deviceStatusChangeCallback);

    void registerHandshakeFilter(ConnectFilter connectFilter);

    void scanDevice(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback);

    void sendCommand(DeviceInfo deviceInfo, bir birVar);

    void stopScanDevice();

    void unPairDevice(DeviceInfo deviceInfo);

    void unregisterDeviceMessageListener();

    void unregisterDeviceStateListener();
}
