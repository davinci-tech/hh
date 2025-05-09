package defpackage;

import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class jby {
    private static HashMap<String, Integer> d = new HashMap<>(0);

    public static Map<String, Integer> d() {
        if (d.isEmpty()) {
            d.put("1", 0);
            d.put("2", 0);
            d.put("3", 0);
            d.put(HealthZonePushReceiver.CYCLE_BLOOD_OXYGEN_NOTIFY, 0);
            d.put(HealthZonePushReceiver.BODY_TEMPERATURE_HIGH_NOTIFY, 0);
            d.put("4", 1);
            d.put("5", 1);
            d.put("6", 1);
            d.put("7", 1);
            d.put(HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, 1);
            d.put(HealthZonePushReceiver.DATA_POST_COMMENTS_NOTIFY, 1);
            d.put(HealthZonePushReceiver.PROACTIVE_SHARING_NOTIFY, 1);
            d.put(HealthZonePushReceiver.COMMENT_FAILED_NOTIFY, 1);
            d.put("8", 2);
            d.put("12", 3);
            d.put("13", 3);
            d.put("14", 3);
            d.put("39", 3);
            d.put(HealthZonePushReceiver.FAMILY_CARE_NOTIFY, 3);
            d.put("15", 4);
            d.put("16", 4);
            d.put("17", 4);
            d.put(HealthZonePushReceiver.SOS_LOCATION_NOTIFY, 4);
            d.put("42", 4);
            d.put("25", 6);
            d.put(HealthZonePushReceiver.SLEEP_TIME_NOTIFY, 6);
            d.put("18", 8);
            d.put("19", 13);
            d.put(HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, 13);
            d.put("21", 13);
            d.put(HealthZonePushReceiver.ECG_MEASUREMENT_ABNORMAL, 13);
            d.put("22", 15);
            d.put("23", 15);
            d.put("44", 15);
            d.put("11", 18);
            d.put("26", 19);
            d.put("24", 32);
            d.put(HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, 33);
            d.put("31", 34);
            d.put(HealthZonePushReceiver.PRESSURE_NOTIFY, 35);
        }
        return d;
    }
}
