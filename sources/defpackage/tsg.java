package defpackage;

import com.huawei.wearengine.sensor.Sensor;

/* loaded from: classes9.dex */
public class tsg {
    public static int a(String str) {
        return Ctry.d(str, "getFrequencyType", "FrequencyType", 2);
    }

    public static void a(Sensor sensor, int i) {
        if (sensor != null) {
            sensor.setExtendJson(Ctry.e(sensor.getExtendJson(), "setFrequencyType", "FrequencyType", Integer.valueOf(i)));
        }
    }
}
