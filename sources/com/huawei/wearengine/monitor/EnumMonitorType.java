package com.huawei.wearengine.monitor;

import com.huawei.health.knit.ui.KnitHealthDetailActivity;

/* loaded from: classes7.dex */
public enum EnumMonitorType {
    EVENT_LOW_POWER(1, "lowPower"),
    EVENT_WEAR_STATUS(2, "wearStatus"),
    EVENT_SPORT_STATUS(3, "sportStatus"),
    EVENT_SLEEP_STATUS(4, KnitHealthDetailActivity.KEY_SLEEP_STATUS),
    EVENT_HEART_RATE_ALARM(5, "heartRateAlarm"),
    EVENT_CHARGE_STATUS(7, "chargeStatus"),
    EVENT_USER_AVAILABLE_KBYTES(8, "userAvailableKbytes"),
    EVENT_WEAR_APP_INSTALLATION_REPORT(9, "wearAppInstallationReport");

    private String mDescription;
    private int mValue;

    EnumMonitorType(int i, String str) {
        this.mValue = i;
        this.mDescription = str;
    }

    public int getValue() {
        return this.mValue;
    }

    public static int getValueByDesc(String str) {
        if (str == null) {
            return -1;
        }
        for (EnumMonitorType enumMonitorType : values()) {
            if (enumMonitorType.mDescription.equals(str)) {
                return enumMonitorType.mValue;
            }
        }
        return -1;
    }

    public static String getDescByValue(int i) {
        for (EnumMonitorType enumMonitorType : values()) {
            if (enumMonitorType.mValue == i) {
                return enumMonitorType.mDescription;
            }
        }
        return null;
    }
}
