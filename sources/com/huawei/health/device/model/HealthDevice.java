package com.huawei.health.device.model;

import android.text.TextUtils;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes3.dex */
public abstract class HealthDevice {
    public static final int CONN_MODE_AUDIO_PLUG = 4;
    public static final int CONN_MODE_BT_CLASSIC = 2;
    public static final int CONN_MODE_BT_LE = 1;
    public static final int CONN_MODE_LINKAGE = 16;
    public static final int CONN_MODE_UNKNOWN = 32;
    public static final int CONN_MODE_WIFI = 8;
    public static final String DEFAULT_VALUE = "-1";

    public abstract String getAddress();

    public abstract String getDeviceName();

    public abstract String getUniqueId();

    public enum HealthDeviceKind {
        HDK_BLOOD_OXYGEN("69"),
        HDK_BLOOD_PRESSURE("68"),
        HDK_BLOOD_SUGAR("-1"),
        HDK_BODY_TEMPERATURE("-1"),
        HDK_ECG("70"),
        HDK_ELLIPTICAL_MACHINE("260"),
        HDK_EXERCISE_BIKE(BleConstants.SPORT_TYPE_BIKE),
        HDK_HEART_RATE("-1"),
        HDK_MASSAGE_GUN(BleConstants.TYPE_FASCIA_GUN_INDEX),
        HDK_NOT_DEVICE("-1"),
        HDK_ROPE_SKIPPING("262"),
        HDK_ROWING_MACHINE("261"),
        HDK_SMART_PILLOW("266"),
        HDK_TREADMILL("31"),
        HDK_UNKNOWN("-1"),
        HDK_WALKING_MACHINE(BleConstants.SPORT_TYPE_TREADMILL),
        HDK_WEIGHT("-1"),
        HDK_FITTINGS("-1");

        private String type;

        HealthDeviceKind(String str) {
            this.type = str;
        }

        public static HealthDeviceKind getHealthDeviceKind(String str) {
            if (TextUtils.isEmpty(str)) {
                return HDK_UNKNOWN;
            }
            for (HealthDeviceKind healthDeviceKind : values()) {
                if (str.equals(healthDeviceKind.getType())) {
                    return healthDeviceKind;
                }
            }
            return HDK_UNKNOWN;
        }

        public String getType() {
            return this.type;
        }
    }
}
