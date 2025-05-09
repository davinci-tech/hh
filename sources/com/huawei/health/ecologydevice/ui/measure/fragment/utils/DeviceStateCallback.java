package com.huawei.health.ecologydevice.ui.measure.fragment.utils;

import com.huawei.health.device.model.HealthDevice;

/* loaded from: classes3.dex */
public interface DeviceStateCallback {
    public static final int BLUETOOTH_TURN_OFF = 12;
    public static final int STATUS_BOND_SUCCESS = 18;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_DEVICE_FOUND = 17;
    public static final int STATUS_DISCONNECTED = 3;
    public static final int STATUS_FIRST_CONNECT_FAILED = 10;
    public static final int STATUS_NO_PAIR = 14;
    public static final int STATUS_PAIRING = 15;
    public static final int STATUS_PAIR_SUCCESS = 16;
    public static final int STATUS_PAIR_TIMEOUT = 13;
    public static final int STATUS_SCAN_SUCCESS = 19;
    public static final int STATUS_TIME_OUT = 11;

    void onNotify(int i, int i2, int i3, HealthDevice healthDevice);
}
