package com.huawei.hihealth.device.open;

import com.huawei.hihealth.device.open.data.MeasureRecord;
import com.huawei.hihealth.device.open.data.MeasureResult;

/* loaded from: classes.dex */
public interface IHealthDeviceCallback {
    public static final int DEVICE_UNKNOWN = 0;
    public static final int STATUS_BT_ERROR = 9;
    public static final int STATUS_CONNECTED = 2;
    public static final int STATUS_CONNECTING = 1;
    public static final int STATUS_CONNECT_FAILED = 5;
    public static final int STATUS_DISCONNECTED = 3;
    public static final int STATUS_DISCONNECTED_AFTER_TRANSFER = 12;
    public static final int STATUS_DISCONNECTING = 4;
    public static final int STATUS_DISCONNECT_FAILED = 6;
    public static final int STATUS_MEASUREMENT_TIMEOUT = 7;
    public static final int STATUS_PAIRING = 11;
    public static final int STATUS_PAIRING_FAILED = 10;
    public static final int STATUS_SERVICESDISCOVERED_FAILED = 8;

    void onDataChanged(HealthDevice healthDevice, MeasureResult measureResult);

    void onFailed(HealthDevice healthDevice, int i);

    void onProgressChanged(HealthDevice healthDevice, MeasureRecord measureRecord);

    void onStatusChanged(HealthDevice healthDevice, int i);
}
