package com.huawei.health.device.callback;

import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import java.util.List;

/* loaded from: classes3.dex */
public interface IHealthDeviceCallback {
    public static final int DEVICE_UNKNOWN = 0;
    public static final int STATUS_BT_ERROR = 9;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_CONNECT_FAILED = 5;
    public static final int STATUS_DEVICE_NOT_BONDED = 15;
    public static final int STATUS_DISCONNECTED = 3;
    public static final int STATUS_DISCONNECTED_AFTER_TRANSFER = 12;
    public static final int STATUS_DISCONNECTING = 4;
    public static final int STATUS_DISCONNECT_FAILED = 6;
    public static final int STATUS_FIRST_CONNECT_FAILED = 16;
    public static final int STATUS_MEASUREMENT_TIMEOUT = 7;
    public static final int STATUS_PAIRING = 11;
    public static final int STATUS_PAIRING_FAILED = 10;
    public static final int STATUS_SCALE_SLEEP = 13;
    public static final int STATUS_SCALE_WAKE_UP = 14;
    public static final int STATUS_SERVICES_DISCOVERED_FAILED = 8;

    void onDataChanged(HealthDevice healthDevice, HealthData healthData);

    void onDataChanged(HealthDevice healthDevice, List<HealthData> list);

    void onFailed(HealthDevice healthDevice, int i);

    default void onMeasureChanged(int i) {
    }

    void onProgressChanged(HealthDevice healthDevice, HealthData healthData);

    void onStatusChanged(HealthDevice healthDevice, int i);
}
