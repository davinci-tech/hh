package com.huawei.hihealth.device.open;

/* loaded from: classes.dex */
public abstract class HealthDevice {
    public static final int CONN_MODE_AUDIO_PLUG = 4;
    public static final int CONN_MODE_BT_CLASSIC = 2;
    public static final int CONN_MODE_BT_LE = 1;
    public static final int CONN_MODE_UNKNOWN = 16;
    public static final int CONN_MODE_WIFI = 8;

    public enum HEALTH_DEVICE_KIND {
        HDK_UNKNOWN,
        HDK_WEIGHT,
        HDK_HEIGHT,
        HDK_BLOOD_PRESSURE,
        HDK_BLOOD_SUGAR,
        HDK_HEART_RATE,
        HDK_ECG,
        HDK_STEPS
    }

    public abstract boolean doCreatePair(IDeviceEventHandler iDeviceEventHandler);

    public abstract boolean doRemovePair();

    public abstract String getAddress();

    public abstract String getDeviceName();

    public abstract String getUniqueId();
}
