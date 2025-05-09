package com.huawei.hwbtsdk.btdatatype.callback;

import com.huawei.hwbtsdk.btdatatype.datatype.BluetoothDeviceNode;

/* loaded from: classes5.dex */
public interface BtDeviceDiscoverCallback {
    void onDeviceDiscovered(BluetoothDeviceNode bluetoothDeviceNode, int i, byte[] bArr);

    void onDeviceDiscoveryCanceled();

    void onDeviceDiscoveryFinished();

    void onFailure(int i, String str);
}
