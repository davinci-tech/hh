package com.huawei.health.manager.reconnect;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes.dex */
public interface BleReconnectScanCallback {
    void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr);
}
