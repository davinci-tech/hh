package defpackage;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class msr {

    /* renamed from: a, reason: collision with root package name */
    public static final Map<String, String> f15154a;
    public static final Map<String, String> b;
    public static final Map<String, String> c;
    public static final Map<String, String> d;
    public static final Map<String, String> e;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("HDK_WEIGHT", "bodyFatScales");
        hashMap.put("HDK_BLOOD_SUGAR", "bloodSugarMonitor");
        hashMap.put("HDK_BLOOD_PRESSURE", "bloodPressureMeter");
        hashMap.put("HDK_HEART_RATE", "heartRateMonitor");
        hashMap.put("HDK_BODY_TEMPERATURE", "temperatureMonitor");
        hashMap.put("HDK_BLOOD_OXYGEN", "bloopOxygenMonitor");
        hashMap.put("HDK_ROPE_SKIPPING", "ropeSkippingMonitor");
        hashMap.put("HDK_TREADMILL", "sportTreadmillMonitor");
        hashMap.put("HDK_EXERCISE_BIKE", "sportExerciseBikeMonitor");
        hashMap.put("HDK_ROWING_MACHINE", "sportRowingMachineMonitor");
        hashMap.put("HDK_ELLIPTICAL_MACHINE", "ellipticalMachineMonitor");
        hashMap.put("HDK_WALKING_MACHINE", "sportWalkingMachineMonitor");
        hashMap.put("HDK_SMART_WATCH", "thirdWatchMonitor");
        hashMap.put("HDK_ECG", "ecgMonitor");
        hashMap.put("HDK_SMART_PILLOW", "smartPillowMonitor");
        hashMap.put("HDK_MASSAGE_GUN", "fasciaGunMonitor");
        hashMap.put("HDK_VENTILATOR", "Ventilator");
        hashMap.put("SMART_HEADPHONES", "smartHeadphones");
        c = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("HDK_WEIGHT", "bodyFat_index_0");
        hashMap2.put("HDK_BLOOD_SUGAR", "bloodSugar_index_0");
        hashMap2.put("HDK_BLOOD_PRESSURE", "bloodPressure_index_0");
        hashMap2.put("HDK_HEART_RATE", "heartRate_index_0");
        hashMap2.put("HDK_BODY_TEMPERATURE", "temperature_index_0");
        hashMap2.put("HDK_BLOOD_OXYGEN", "bloodOxygen_index_0");
        hashMap2.put("HDK_ROPE_SKIPPING", "ropeSkipping_index_0");
        hashMap2.put("HDK_TREADMILL", "treadmill_index_0");
        hashMap2.put("HDK_EXERCISE_BIKE", "exerciseBike_index_0");
        hashMap2.put("HDK_ROWING_MACHINE", "rowingMachine_index_0");
        hashMap2.put("HDK_ELLIPTICAL_MACHINE", "elliptical_index_0");
        hashMap2.put("HDK_WALKING_MACHINE", "walkingMachine_index_0");
        hashMap2.put("HDK_SMART_WATCH", "thirdWatch_index_0");
        hashMap2.put("HDK_ECG", "ecg_index_0");
        hashMap2.put("HDK_SMART_PILLOW", "smartPillow_index_0");
        hashMap2.put("HDK_MASSAGE_GUN", "fasciaGun_index_0");
        hashMap2.put("HDK_VENTILATOR", "ventilator_index_1");
        hashMap2.put("SMART_HEADPHONES", "index");
        d = Collections.unmodifiableMap(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("HDK_WEIGHT", "com.huawei.health_BodyFat_deviceConfigBeta");
        hashMap3.put("HDK_BLOOD_SUGAR", "com.huawei.health_BloodSugar_deviceConfigBeta");
        hashMap3.put("HDK_BLOOD_PRESSURE", "com.huawei.health_BloodPressure_deviceConfigBeta");
        hashMap3.put("HDK_HEART_RATE", "com.huawei.health_HeartRate_deviceConfigBeta");
        hashMap3.put("HDK_BODY_TEMPERATURE", "com.huawei.health_Temperature_deviceConfigBeta");
        hashMap3.put("HDK_BLOOD_OXYGEN", "com.huawei.health_BloodOxygen_deviceConfigBeta");
        hashMap3.put("HDK_ROPE_SKIPPING", "com.huawei.health_Rope_deviceConfigBeta");
        hashMap3.put("HDK_TREADMILL", "com.huawei.health_Treadmill_deviceConfigBeta");
        hashMap3.put("HDK_EXERCISE_BIKE", "com.huawei.health_ExerciseBike_deviceConfigBeta");
        hashMap3.put("HDK_ROWING_MACHINE", "com.huawei.health_RowingMachine_deviceConfigBeta");
        hashMap3.put("HDK_ELLIPTICAL_MACHINE", "com.huawei.health_Elliptical_deviceConfigBeta");
        hashMap3.put("HDK_WALKING_MACHINE", "com.huawei.health_WalkingMachine_deviceConfigBeta");
        hashMap3.put("HDK_SMART_WATCH", "com.huawei.health_ThirdWatch_deviceConfigBeta");
        hashMap3.put("HDK_ECG", "com.huawei.health_Ecg_deviceConfigBeta");
        hashMap3.put("HDK_SMART_PILLOW", "com.huawei.health_SmartPillow_deviceConfigBeta");
        hashMap3.put("HDK_MASSAGE_GUN", "com.huawei.health_FasciaGun_deviceConfigBeta");
        hashMap3.put("HDK_VENTILATOR", "com.huawei.health_Ventilator_deviceConfigBeta");
        hashMap3.put("SMART_HEADPHONES", "com.huawei.health_HwWear_deviceConfigBeta");
        b = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put("HDK_WEIGHT", "com.huawei.health_BodyFat_deviceConfig");
        hashMap4.put("HDK_BLOOD_SUGAR", "com.huawei.health_BloodSugar_deviceConfig");
        hashMap4.put("HDK_BLOOD_PRESSURE", "com.huawei.health_BloodPressure_deviceConfig");
        hashMap4.put("HDK_HEART_RATE", "com.huawei.health_HeartRate_deviceConfig");
        hashMap4.put("HDK_BODY_TEMPERATURE", "com.huawei.health_Temperature_deviceConfig");
        hashMap4.put("HDK_BLOOD_OXYGEN", "com.huawei.health_BloodOxygen_deviceConfig");
        hashMap4.put("HDK_ROPE_SKIPPING", "com.huawei.health_Rope_deviceConfig");
        hashMap4.put("HDK_TREADMILL", "com.huawei.health_Treadmill_deviceConfig");
        hashMap4.put("HDK_EXERCISE_BIKE", "com.huawei.health_ExerciseBike_deviceConfig");
        hashMap4.put("HDK_ROWING_MACHINE", "com.huawei.health_RowingMachine_deviceConfig");
        hashMap4.put("HDK_ELLIPTICAL_MACHINE", "com.huawei.health_Elliptical_deviceConfig");
        hashMap4.put("HDK_WALKING_MACHINE", "com.huawei.health_WalkingMachine_deviceConfig");
        hashMap4.put("HDK_SMART_WATCH", "com.huawei.health_ThirdWatch_deviceConfig");
        hashMap4.put("HDK_ECG", "com.huawei.health_Ecg_deviceConfig");
        hashMap4.put("HDK_SMART_PILLOW", "com.huawei.health_SmartPillow_deviceConfig");
        hashMap4.put("HDK_MASSAGE_GUN", "com.huawei.health_FasciaGun_deviceConfig");
        hashMap4.put("HDK_VENTILATOR", "com.huawei.health_Ventilator_deviceConfig");
        hashMap4.put("SMART_HEADPHONES", "com.huawei.health_HwWear_deviceBeta");
        f15154a = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.put("HDK_WEIGHT", "deviceType=bodyFatScales");
        hashMap5.put("HDK_BLOOD_SUGAR", "deviceType=bloodSugarMonitor");
        hashMap5.put("HDK_BLOOD_PRESSURE", "deviceType=bloodPressureMeter");
        hashMap5.put("HDK_HEART_RATE", "deviceType=heartRateMonitor");
        hashMap5.put("HDK_BODY_TEMPERATURE", "deviceType=temperatureMonitor");
        hashMap5.put("HDK_BLOOD_OXYGEN", "deviceType=bloopOxygenMonitor");
        hashMap5.put("HDK_ROPE_SKIPPING", "deviceType=ropeSkippingMonitor");
        hashMap5.put("HDK_TREADMILL", "deviceType=sportTreadmillMonitor");
        hashMap5.put("HDK_EXERCISE_BIKE", "deviceType=sportExerciseBikeMonitor");
        hashMap5.put("HDK_ROWING_MACHINE", "deviceType=sportRowingMachineMonitor");
        hashMap5.put("HDK_ELLIPTICAL_MACHINE", "deviceType=ellipticalMachineMonitor");
        hashMap5.put("HDK_WALKING_MACHINE", "deviceType=sportWalkingMachineMonitor");
        hashMap5.put("HDK_ECG", "deviceType=ecgMonitor");
        hashMap5.put("HDK_SMART_WATCH", "deviceType=thirdWatchMonitor");
        hashMap5.put("HDK_SMART_PILLOW", "deviceType=smartPillowMonitor");
        hashMap5.put("HDK_MASSAGE_GUN", "deviceType=fasciaGunMonitor");
        hashMap5.put("HDK_VENTILATOR", "deviceType=Ventilator");
        hashMap5.put("SMART_HEADPHONES", "deviceType=smartHeadphones");
        e = Collections.unmodifiableMap(hashMap5);
    }

    public static String a(String str) {
        Map<String, String> map = e;
        return map.containsKey(str) ? map.get(str) : "";
    }
}
