package com.huawei.unitedevice.api;

import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.DeviceCompatibleCallback;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.callback.DeviceStatusChangeCallback;
import com.huawei.devicesdk.callback.MessageReceiveCallback;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.entity.UniteDevice;
import defpackage.bir;
import defpackage.bjf;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public interface UniteChannelInterface {
    void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode);

    void connectDevice(UniteDevice uniteDevice, boolean z, ConnectMode connectMode, ConnectFilter connectFilter);

    void createSystemBond(UniteDevice uniteDevice);

    void disconnect(UniteDevice uniteDevice);

    Map<String, UniteDevice> getDeviceList();

    List<DeviceInfo> getDeviceMgrList(int i, String str);

    boolean isSupportCharactor(UniteDevice uniteDevice, String str, String str2);

    boolean isSupportService(UniteDevice uniteDevice, String str);

    void registerDeviceCompatibleListener(String str, DeviceCompatibleCallback deviceCompatibleCallback);

    void registerDeviceMessageListener(String str, MessageReceiveCallback messageReceiveCallback);

    void registerDeviceStateListener(String str, DeviceStatusChangeCallback deviceStatusChangeCallback);

    void registerHandshakeFilter(ConnectFilter connectFilter);

    void registerListener(String str, String str2, DataReceiveCallback dataReceiveCallback);

    void scanDevice(ScanMode scanMode, List<bjf> list, DeviceScanCallback deviceScanCallback);

    void sendCommand(UniteDevice uniteDevice, bir birVar);

    void setCharacteristicNotify(UniteDevice uniteDevice, String str, String str2, SendMode sendMode, boolean z);

    void stopScanDevice();

    void unPairDevice(UniteDevice uniteDevice);

    void unregisterDeviceCompatibleListener(String str);

    void unregisterDeviceMessageListener(String str);

    void unregisterDeviceStateListener(String str);

    void unregisterListener(String str, String str2);

    void updateDeviceAfterSimulatConnected(String str);
}
