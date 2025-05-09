package defpackage;

import com.huawei.health.R;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.ui.main.R$string;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes6.dex */
public class qmx {

    /* renamed from: a, reason: collision with root package name */
    public static final HashMap<String, Integer> f16490a = new HashMap<String, Integer>() { // from class: qmx.5
        {
            put("SLEEP_DURATION", 0);
            put("BED_TIME", 1);
            put("SLEEP_LATENCY", 2);
            put("SLEEP_EFFICIENCY", 3);
            put("DEEP_RATIO", 4);
            put("SHALLOW_RATIO", 5);
            put("REM_RATIO", 6);
            put("DEEP_CONT", 7);
            put("AWAKE_NUMS", 8);
            put("BREATH_QUALITY", 9);
            put("GO_BED_TIME", 10);
            put("FALL_ASLEEP_TIME", 11);
            put("WAKE_UP_TIME", 12);
            put("RISING_TIME", 13);
            put("GO_BED_REGULARITY", 14);
            put("OFF_BED_REGULARITY", 15);
            put("SLEEP_HEART_RATE", 16);
            put("SLEEP_BLOOD_OX", 17);
            put("SLEEP_BREATH_RATIO", 18);
            put("TRUSLEEP_FIVE_HEART_RATE", 19);
            put("TRUSLEEP_FIVE_HRV", 20);
            put("TRUSLEEP_FIVE_SpO2", 21);
            put("TRUSLEEP_FIVE_BREATH_RATE", 22);
            put("NOON_SLEEP", 23);
            put("TOTAL_SLEEP", 24);
            put("WAKE_UP_FEELING", 25);
        }
    };
    public static final HashMap<Integer, Integer> b = new HashMap<Integer, Integer>() { // from class: qmx.3
        {
            put(0, -1);
            put(1, qmx.f16490a.get("REM_RATIO"));
            put(2, qmx.f16490a.get("AWAKE_NUMS"));
            put(3, qmx.f16490a.get("REM_RATIO"));
            put(4, qmx.f16490a.get("DEEP_RATIO"));
            put(5, qmx.f16490a.get("SLEEP_DURATION"));
            put(6, -1);
            put(7, qmx.f16490a.get("DEEP_CONT"));
            put(8, qmx.f16490a.get("SLEEP_DURATION"));
            put(9, -1);
            put(10, qmx.f16490a.get("SLEEP_EFFICIENCY"));
            put(11, qmx.f16490a.get("SLEEP_LATENCY"));
        }
    };
    public static final int[] c = {22101, 22102, 22103, 22104, 22105, 22107, 22106};
    public static final int[] e = {22001, 22002, 22003};
    public static final int[] d = {DicDataTypeUtil.DataType.SLEEP_DETAILS.value(), DicDataTypeUtil.DataType.ENVIRONMENT_NOISE_TYPE.value()};
    public static final Map<String, Integer> f = new HashMap<String, Integer>() { // from class: qmx.2
        {
            put("TRUSLEEP_FIVE_HEART_RATE", Integer.valueOf(R$string.IDS_trusleep_five_avg_heart_rate_title));
            put("TRUSLEEP_FIVE_BREATH_RATE", Integer.valueOf(R$string.IDS_trusleep_five_avg_breath_rate_title));
            put("TRUSLEEP_FIVE_HRV", Integer.valueOf(R$string.IDS_trusleep_five_avg_hrv_title));
            put("TRUSLEEP_FIVE_SpO2", Integer.valueOf(R$string.IDS_trusleep_five_avg_spo2_title));
        }
    };
    public static final Map<String, Integer> h = new HashMap<String, Integer>() { // from class: qmx.4
        {
            put("TRUSLEEP_FIVE_HEART_RATE", Integer.valueOf(R$string.IDS_heart_rate_switch_description));
            put("TRUSLEEP_FIVE_BREATH_RATE", -1);
            put("TRUSLEEP_FIVE_HRV", -1);
            put("TRUSLEEP_FIVE_SpO2", Integer.valueOf(R$string.IDS_spo2_switch_description));
        }
    };
    public static final Map<String, Integer> i = new HashMap<String, Integer>() { // from class: qmx.1
        {
            put("TRUSLEEP_FIVE_HEART_RATE", Integer.valueOf(R.plurals._2130903427_res_0x7f030183));
            put("TRUSLEEP_FIVE_BREATH_RATE", Integer.valueOf(R.plurals._2130903435_res_0x7f03018b));
            put("TRUSLEEP_FIVE_HRV", Integer.valueOf(R.plurals._2130903428_res_0x7f030184));
            put("TRUSLEEP_FIVE_SpO2", Integer.valueOf(R$string.IDS_base_line_percent));
        }
    };
    public static final Map<String, Integer[]> g = new HashMap<String, Integer[]>() { // from class: qmx.9
        {
            put("TRUSLEEP_FIVE_HEART_RATE", new Integer[]{40, 120});
            put("TRUSLEEP_FIVE_BREATH_RATE", new Integer[]{1, 80});
            put("TRUSLEEP_FIVE_HRV", new Integer[]{1, 200});
            put("TRUSLEEP_FIVE_SpO2", new Integer[]{90, 100});
        }
    };
    public static final Map<String, Integer> j = new HashMap<String, Integer>() { // from class: qmx.8
        {
            put("TRUSLEEP_FIVE_HEART_RATE", Integer.valueOf(R.plurals._2130903426_res_0x7f030182));
            put("TRUSLEEP_FIVE_BREATH_RATE", Integer.valueOf(R.plurals._2130903434_res_0x7f03018a));
            put("TRUSLEEP_FIVE_HRV", Integer.valueOf(R.plurals._2130903425_res_0x7f030181));
            put("TRUSLEEP_FIVE_SpO2", -1);
        }
    };
}
