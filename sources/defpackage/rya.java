package defpackage;

import com.huawei.ui.main.R$string;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class rya {
    private static Map<String, Integer> e;

    static {
        HashMap hashMap = new HashMap(10);
        e = hashMap;
        hashMap.put("HeartRateConstructor", Integer.valueOf(R$string.IDS_health_no_data_heart_rate));
        e.put("SleepCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_sleep));
        e.put("WeightCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_weight));
        e.put("StressCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_pressure));
        e.put("BloodSugarCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_blood_sugar));
        e.put("BloodPressureCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_blood_pressure));
        e.put("BloodOxygenCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_blood_oxygen_saturation));
        e.put("BodyTemperatureCardConstructor", Integer.valueOf(R$string.IDS_health_no_data_body_temperature));
    }

    public static String e(String str, String str2) {
        return rye.d(a(str) + str2);
    }

    public static String a(String str) {
        return d(str);
    }

    public static String d(String str) {
        return mrv.d + "ux_model_res" + File.separator + str + File.separator;
    }
}
