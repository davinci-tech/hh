package com.huawei.devicesdk.reconnect;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public interface BleReconnectScanCallback {
    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
