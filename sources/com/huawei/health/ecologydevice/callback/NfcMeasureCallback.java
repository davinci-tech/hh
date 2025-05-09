package com.huawei.health.ecologydevice.callback;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;

/* loaded from: classes3.dex */
public interface NfcMeasureCallback {
    public static final int BLUETOOTH_TURN_OFF = 12;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_DEVICE_FOUND = 17;
    public static final int STATUS_DISCONNECTED = 3;
    public static final int STATUS_FIRST_CONNECT_FAILED = 10;
    public static final int STATUS_NO_PAIR = 14;
    public static final int STATUS_PAIRING = 15;
    public static final int STATUS_PAIR_SUCCESS = 16;
    public static final int STATUS_PAIR_TIMEOUT = 13;
    public static final int STATUS_TIME_OUT = 11;

    void onDataChanged(HealthDevice healthDevice, HealthData healthData);

    void onStartMeasuring();

    void onStatusChanged(int i, int i2, int i3);
}
