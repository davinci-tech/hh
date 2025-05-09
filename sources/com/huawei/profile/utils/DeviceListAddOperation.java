package com.huawei.profile.utils;

/* loaded from: classes9.dex */
public final class DeviceListAddOperation {
    public static final String APPEND_DEVICES_ID_KEY = "appendDevicesID";
    private static final int APPEND_INDEX = 1;
    public static final String DELETE_BEFORE_ADD_KEY = "deleteBeforeAdd";
    private static final int DELETE_INDEX = 2;
    public static final String EDLETED_DEVICES_ID_KEY = "deleteDevicesID";
    public static final String FIRST_DEVICE_LIST_FLAG_KEY = "firstDeviceList";
    public static final String OPERATION_KEY = "opDeviceListMode";
    private static final int OVERRID_INDEX = 0;

    public enum AddMode {
        OVERRIDE(0),
        APPEND(1),
        DELETE(2);

        private int value;

        AddMode(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    private DeviceListAddOperation() {
    }
}
