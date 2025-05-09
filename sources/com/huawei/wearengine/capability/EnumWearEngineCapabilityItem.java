package com.huawei.wearengine.capability;

/* loaded from: classes7.dex */
public enum EnumWearEngineCapabilityItem {
    POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM(2),
    POINT_TO_POINT_SEND_FILES_TO_WATCH_ENUM(3),
    SEND_FILES_TO_PHONE_ENUM(4),
    SPORT_STATUS_QUERY_AND_REPORT_ENUM(5),
    LOW_POWER_REPORT_ENUM(6),
    POWER_STATUS_QUERY_ENUM(7),
    WEAR_STATUS_QUERY_AND_REPORT_ENUM(8),
    SLEEP_STATUS_QUERY_AND_REPORT_ENUM(9),
    CHARGING_STATUS_QUERY_AND_REPORT_ENUM(10),
    HEART_RATE_ALARM_REPORT_ENUM(11),
    SEND_NOTIFY_TO_WATCH_ENUM(12),
    QUERY_DEVICE_AVAILABLE_SPACE_ENUM(13),
    QUERY_DEVICE_APP_INSTALL_INFO_ENUM(14),
    OFFLINE_MESSAGE_ENUM(15),
    WEAR_APP_INSTALLATION_REPORT_ENUM(69),
    SUPPORT_SENSOR_ENUM(86),
    SUPPORT_CHECK_DETAIL_CAPABILITY(88);

    private int value;

    EnumWearEngineCapabilityItem(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }
}
