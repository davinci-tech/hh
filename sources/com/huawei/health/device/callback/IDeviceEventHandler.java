package com.huawei.health.device.callback;

import com.huawei.health.device.model.HealthDevice;

/* loaded from: classes3.dex */
public interface IDeviceEventHandler {
    public static final int PAIRING_FAILED = 8;
    public static final int PAIRING_PASSED = 7;
    public static final int PAIRING_STATE_OK = 10;
    public static final int SCAN_BT_DISABLED = 3;
    public static final int SCAN_IS_BUSY = 2;
    public static final int SCAN_STOP = 5;
    public static final int SCAN_TIMEOUT = 1;
    public static final int SCAN_WIFI_DISABLED = 4;

    void onDeviceFound(HealthDevice healthDevice);

    void onScanFailed(int i);

    void onStateChanged(int i);
}
