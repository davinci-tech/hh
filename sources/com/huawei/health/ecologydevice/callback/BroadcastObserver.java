package com.huawei.health.ecologydevice.callback;

import android.bluetooth.BluetoothDevice;

/* loaded from: classes3.dex */
public interface BroadcastObserver {
    public static final int BLUE_BOND_BONDED = 3;
    public static final int BLUE_BOND_BONDING = 5;
    public static final int BLUE_BOND_NONE = 4;
    public static final String BLUE_PAIR_STATE_TYPE = "BLUE_PAIR_STATE_TYPE";
    public static final int BLUE_STATE_OFF = 2;
    public static final int BLUE_STATE_ON = 1;
    public static final String BLUE_STATE_TYPE = "BLUE_STATE_TYPE";

    void onNotify(int i, BluetoothDevice bluetoothDevice);
}
