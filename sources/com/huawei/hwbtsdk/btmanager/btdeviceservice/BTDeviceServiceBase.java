package com.huawei.hwbtsdk.btmanager.btdeviceservice;

import android.bluetooth.BluetoothDevice;
import com.huawei.health.deviceconnect.callback.BtDeviceResponseCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public interface BTDeviceServiceBase {
    void btSwitchChangeInfo(int i);

    void connectBTDevice(BluetoothDevice bluetoothDevice);

    void connectBTDevice(String str);

    void disconnectBTDevice();

    void disconnectGMS();

    int getBTDeviceConnectState();

    DeviceInfo getDeviceInfo();

    void onDestroy();

    void removeV1CheckCommand();

    boolean sendBTDeviceData(byte[] bArr);

    void sendBTFilePath(String str);

    void setAwAssetCallback(BtDeviceResponseCallback btDeviceResponseCallback);

    void setFileCallback(BtDeviceResponseCallback btDeviceResponseCallback);

    void setPathExtendNum(int i);
}
