package defpackage;

import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import health.compact.a.Utils;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class kbk {
    private static final Map<Integer, Integer> c = new LinkedHashMap(16);

    static {
        b();
    }

    public static void c() {
        if (Utils.o()) {
            c.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), 113);
        } else {
            c.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), 168);
        }
    }

    public static Map<Integer, Integer> d() {
        return c;
    }

    public static int d(int i) {
        Integer num = c.get(Integer.valueOf(i));
        if (num == null) {
            return -1;
        }
        return num.intValue();
    }

    private static void b() {
        Map<Integer, Integer> map = c;
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value()), 59);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SKIN_TEMPERATURE_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ENVIRONMENT_TEMPERATURE_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.HIGH_BODY_TEMPERATURE_ALARM_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.LOW_BODY_TEMPERATURE_ALARM_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.LOW_SKIN_TEMPERATURE_ALARM_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_ALARM_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SUSPECTED_HIGH_TEMPERATURE_SET.value()), 29);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()), 46);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.LAKELOUISE_AMS_SCORE_SET.value()), 72);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ALTITUDE_TYPE_SET.value()), 72);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()), 75);
        if (Utils.o()) {
            map.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), 113);
        } else {
            map.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), 168);
        }
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()), 128);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()), 128);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_DAILY_RESULT.value()), 150);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_DAILY_SLP_RESULT.value()), 150);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_RISK_GROUP_RESULT.value()), 150);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()), 154);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()), 137);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_WEIGHT.value()), 169);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.PHYSIOLOGICAL_CYCLE.value()), 174);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.WEIGHT_BODYFAT_BROAD.value()), 169);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.RESTING_METABOLISM_SET.value()), 169);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.CURRENT_BASAL_METABOLISM_SET.value()), 169);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BASAL_METABOLISM_AFTER_EXERCISE_SET.value()), 169);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()), 107);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.EMOTION_SET.value()), 206);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_DAILY_STATUS_SET.value()), 197);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_RESULT_SET.value()), 197);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_BLOOD_PRESSURE_RESULT.value()), Integer.valueOf(a.w));
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()), Integer.valueOf(a.L));
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOODPRESSURE_RISK_RESEARCH_RESULT.value()), 229);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOODPRESSURE_RISK_RESULT.value()), 229);
        map.put(Integer.valueOf(DicDataTypeUtil.DataType.HEART_RATE_VARIABILITY.value()), 235);
    }
}
