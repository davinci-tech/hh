package defpackage;

import android.os.Bundle;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.UnitUtil;
import java.util.Map;

/* loaded from: classes8.dex */
public class fhk {
    private static boolean c(int i) {
        return i > 0 && i < 255;
    }

    public static Bundle axK_(Map map, int i, boolean z) {
        Bundle bundle = new Bundle();
        if (map == null) {
            return bundle;
        }
        long c = c(map, "TIME_ONE_SECOND_DURATION");
        int d = d(map, "DISTANCE_DATA");
        int d2 = d(map, "SPEED_DATA");
        int d3 = d(map, "AVG_SPEED_DATA");
        int d4 = d(map, "MAX_SPEED_DATA");
        int d5 = d(map, "PACE_DATA");
        int d6 = d(map, "HEART_RATE_DATA");
        int d7 = d(map, "DESCENT_DATA");
        int d8 = d(map, "CREEP_DATA");
        int d9 = d(map, "ALTITUDE_DATA");
        int d10 = d(map, "CALORIES_DATA");
        int round = Math.round(d(map, "ACTIVE_CALORIES_DATA") / 1000.0f);
        int d11 = d(map, "CADENCE_DATA");
        int d12 = d(map, "POWER_DATA");
        bundle.putString("duration", String.valueOf(UnitUtil.d((int) Math.round(c / 1000.0d))));
        bundle.putString("distance", b(d));
        bundle.putString("speed", d(d2));
        bundle.putString("pace", d(d5, d2));
        bundle.putString("avgSpeed", d(d3));
        bundle.putString("maxSpeed", d(d4));
        bundle.putInt(BleConstants.SPORT_TYPE, i);
        bundle.putString(IndoorEquipManagerApi.KEY_HEART_RATE, e(d6));
        bundle.putString("calorie", String.valueOf(d10));
        bundle.putString(HwExerciseConstants.JSON_NAME_ACTIVECALORIE, String.valueOf(round));
        bundle.putString(BleConstants.TOTAL_DESCENT, d7 > 0 ? String.valueOf(d7) : "");
        if (z) {
            bundle.putBoolean("dataFromWear", true);
        }
        bundle.putString("totalCreep", d8 > 0 ? String.valueOf(d8) : "");
        bundle.putString("altitude", String.valueOf(d9));
        bundle.putString("power", d12 >= 0 ? String.valueOf(d12) : "");
        bundle.putString("cadenceData", String.valueOf(d11));
        return bundle;
    }

    private static int d(Map map, String str) {
        return ((Integer) a(map, str, Integer.class, 0)).intValue();
    }

    private static long c(Map map, String str) {
        return ((Long) a(map, str, Long.class, 0L)).longValue();
    }

    private static <T> T a(Map map, String str, Class<T> cls, T t) {
        T t2 = (T) map.get(str);
        return (t2 == null || !cls.isInstance(t2)) ? t : t2;
    }

    private static String b(int i) {
        if (UnitUtil.h()) {
            return String.valueOf(UnitUtil.e(i / 1000.0f, 3));
        }
        return String.valueOf(i / 1000.0f);
    }

    private static String d(int i) {
        if (UnitUtil.h()) {
            return UnitUtil.e(UnitUtil.e(i / 100.0f, 3), 1, 2);
        }
        return UnitUtil.e(i / 100.0f, 1, 2);
    }

    private static String d(int i, int i2) {
        if (i2 != 0) {
            if (UnitUtil.h()) {
                return String.valueOf(3600.0d / UnitUtil.e(i2 / 100.0f, 3));
            }
            return String.valueOf(3600.0f / (i2 / 100.0f));
        }
        return String.valueOf(0);
    }

    private static String e(int i) {
        return c(i) ? String.valueOf(i) : "";
    }
}
