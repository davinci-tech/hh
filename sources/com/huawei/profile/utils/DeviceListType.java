package com.huawei.profile.utils;

/* loaded from: classes9.dex */
public final class DeviceListType {
    public static final int DEVICE_LIST_DISCOVER_FROM_BLE = 1;
    public static final int DEVICE_LIST_DISCOVER_FROM_HILINK_WIFI = 4;
    public static final int DEVICE_LIST_DISCOVER_FROM_WIFI = 2;
    public static final int DEVICE_LIST_NETWORKING_FROM_SOFTBUS = 8;
    private static final int DEVICE_LIST_TYPE_NUM = 4;

    public static boolean isValidListType(int i) {
        if (i <= 0) {
            return false;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            if (((1 << i3) & i) > 0) {
                i2++;
            }
        }
        return i2 == 1;
    }

    public static boolean isValidListTypes(int i) {
        return i > 0 && i < 16;
    }

    private DeviceListType() {
    }
}
