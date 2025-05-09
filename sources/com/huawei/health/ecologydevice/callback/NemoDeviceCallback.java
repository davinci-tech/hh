package com.huawei.health.ecologydevice.callback;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public interface NemoDeviceCallback {
    public static final int BOND_ERROR = 2;
    public static final int FOUND_TIMEOUT = 1;
    public static final int PARAM_ERROR = -1;

    void bondError(int i);

    void bondSuccess(BluetoothDevice bluetoothDevice);
}
