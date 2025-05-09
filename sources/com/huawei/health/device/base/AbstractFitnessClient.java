package com.huawei.health.device.base;

/* loaded from: classes3.dex */
public abstract class AbstractFitnessClient {
    public static final String ACTION_FAILED_UNLOCK_BT_MODULE = "com.huawei.btsportdevice.ACTION_FAILED_UNLOCK_BT_MODULE";
    public static final String ACTION_GATT_STATE_CONNECTED = "com.huawei.btsportdevice.ACTION_GATT_STATE_CONNECTED";
    public static final String ACTION_GATT_STATE_CONNECTING = "com.huawei.btsportdevice.ACTION_GATT_STATE_CONNECTING";
    public static final String ACTION_GATT_STATE_DISCONNECTED = "com.huawei.btsportdevice.ACTION_GATT_STATE_DISCONNECTED";
    public static final String ACTION_GATT_STATE_DISCONNECTING = "com.huawei.btsportdevice.ACTION_GATT_STATE_DISCONNECTING";
    public static final String ACTION_GATT_STATE_RECONNECTED = "com.huawei.btsportdevice.ACTION_GATT_STATE_RECONNECTED";
    public static final String ACTION_GATT_STATE_RECONNECTING = "com.huawei.btsportdevice.ACTION_GATT_STATE_RECONNECTING";
    public static final String ACTION_INVALID_DEVICE_INFO = "com.huawei.btsportdevice.ACTION_INVALID_DEVICE_INFO";
    public static final String ACTION_NOTIFY_FTMP_ALREADY_RUNNING = "com.huawei.btsportdevice.ACTION_NOTIFY_FTMP_ALREADY_RUNNING";
    public static final String ACTION_NOTIFY_FTMP_NO_DIS = "com.huawei.btsportdevice.ACTION_NOTIFY_FTMP_NO_DIS";
    public static final String ACTION_PAIR_UNSUPPORTED = "com.huawei.btsportdevice.ACTION_PAIR_UNSUPPORTED";
    public static final String ACTION_READ_SUCCESS = "com.huawei.btsportdevice.ACTION_READ_SUCCESS";
    public static final String ACTION_REQUEST_MACHINE_CONTROL_SUCCESS = "com.huawei.btsportdevice.ACTION_REQUEST_MACHINE_CONTROL_SUCCESS";
    public static final String ACTION_SERVICE_DISCOVERIED = "com.huawei.btsportdevice.ACTION_SERVICE_DISCOVERIED";
    public static final String ACTION_SERVICE_NOT_SUPPORT = "com.huawei.btsportdevice.ACTION_SERVICE_NOT_SUPPORT";
    public static final String ACTION_SERVICE_REDISCOVERIED = "com.huawei.btsportdevice.ACTION_SERVICE_REDISCOVERIED";

    public abstract void cancelPairing();

    public abstract void connectByMac(boolean z, String str);

    public abstract void connectByName(boolean z, String str);

    public abstract void disconnect(boolean z);

    public abstract String getCurrentMacAddress();

    public abstract boolean reConnect();

    public abstract void releaseResource();

    public abstract void removeMessageOrStateCallback(String str, boolean z);

    public abstract void sendByteToEquip(byte[] bArr);

    public abstract void setDeviceType(String str);

    public abstract void setFitnessMachineControl(int i, int i2, int[] iArr);

    public abstract void setFitnessMachineControl(int i, int[] iArr);

    public abstract void setHasExperiencedStateOfStartForFtmp(boolean z);

    public abstract void setHeartRateFromWearable(int i);

    public abstract void setRealStartWorkout();

    public abstract void stopThreadsInCsafeController();
}
