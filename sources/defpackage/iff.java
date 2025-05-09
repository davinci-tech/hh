package defpackage;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.up.utils.Constants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class iff {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<String, Integer> f13333a;
    private static final Map<Integer, String> b;
    private static final Map<Integer, String> c;
    private static final Map<Integer, String> d;
    private static final Map<Integer, String> e;
    private static volatile int f;
    private static final Map<Integer, String> g;
    private static volatile int i;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("query_step_detail", 1);
        hashMap.put("query_stress_detail", 1);
        hashMap.put("query_heart_rate_detail", 1);
        hashMap.put("delete_sample", 2);
        hashMap.put("save_samples", 3);
        hashMap.put("get_category", 3);
        hashMap.put("query_new_body_thermometer", 3);
        hashMap.put("query_oxygen_detail", 4);
        hashMap.put("save_new_body_thermometer", 4);
        hashMap.put("save_oxygen", 4);
        hashMap.put("save_uric_acid", 4);
        hashMap.put("delete_new_body_thermometer", 4);
        hashMap.put("delete_oxygen", 4);
        hashMap.put("delete_uric_acid", 4);
        hashMap.put("save_osa_set", 5);
        hashMap.put("query_osa_set", 5);
        hashMap.put("query_osa_detail_half_hour", 5);
        hashMap.put("save_heart_rate_detail", 6);
        hashMap.put("save_core_sleep_session", 6);
        hashMap.put("query_core_sleep_session", 6);
        hashMap.put("divide_save_samples", 7);
        hashMap.put("query_data", 7);
        hashMap.put("save_sleep_record_score", 7);
        hashMap.put("query_vascular_health", 8);
        hashMap.put("query_blood_pressure", 8);
        hashMap.put("query_convergence_core_sleep_session", 9);
        hashMap.put("query_convergence_heart_rate_detail", 9);
        hashMap.put("query_convergence_new_body_thermometer", 9);
        hashMap.put("basicSportQuery", 9);
        hashMap.put("register_data_auto_report", 9);
        hashMap.put("unregister_data_auto_report", 9);
        hashMap.put("query_arrhythmia", 10);
        hashMap.put("query_blood_sugar", 10);
        hashMap.put("query_electrocardiogram", 11);
        hashMap.put(Constants.METHOD_GET_USER_INFO, 11);
        hashMap.put("setUserPreference", 12);
        hashMap.put("getUserPreference", 12);
        hashMap.put("subscribe_data", 12);
        hashMap.put("savePhysiologicalCycle", 13);
        hashMap.put("queryPhysiologicalCycle", 13);
        hashMap.put("savePhysiologicalCycleRemark", 13);
        hashMap.put("queryPhysiologicalCycleRemark", 13);
        hashMap.put("saveDrinkWater", 13);
        hashMap.put("queryDrinkWater", 13);
        hashMap.put("savePhysiologicalCycleBusiness", 13);
        hashMap.put("queryPhysiologicalCycleBusiness", 13);
        hashMap.put("queryCntbpPpg", 13);
        hashMap.put("subscribe_goal_achieve", 14);
        hashMap.put("bodyShapeAllAbility", 15);
        hashMap.put("save_sleep_on_off_bed_record", 15);
        hashMap.put("query_sleep_on_off_bed_record", 15);
        hashMap.put("delete_sleep_on_off_bed_record", 15);
        hashMap.put("delete_core_sleep_bed", 15);
        hashMap.put("delete_core_sleep_on", 15);
        hashMap.put("send_device_controlinstruction", 16);
        hashMap.put("mindfulnessAllAbility", 16);
        hashMap.put("pauseSport", 17);
        hashMap.put("resumeSport", 17);
        hashMap.put("syncData", 18);
        hashMap.put("connectSportDevice", 18);
        hashMap.put("queryTrends", 19);
        f13333a = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(400011975, "save_new_body_thermometer");
        hashMap2.put(2103, "save_oxygen");
        hashMap2.put(2109, "save_uric_acid");
        hashMap2.put(500002, "save_osa_set");
        hashMap2.put(22101, "save_core_sleep_session");
        hashMap2.put(22102, "save_core_sleep_session");
        hashMap2.put(22103, "save_core_sleep_session");
        hashMap2.put(22104, "save_core_sleep_session");
        hashMap2.put(22105, "save_core_sleep_session");
        hashMap2.put(22106, "save_core_sleep_session");
        hashMap2.put(22107, "save_core_sleep_session");
        hashMap2.put(2002, "save_heart_rate_detail");
        hashMap2.put(500005569, "save_sleep_record_score");
        hashMap2.put(400018, "savePhysiologicalCycle");
        hashMap2.put(400018283, "savePhysiologicalCycleRemark");
        hashMap2.put(Integer.valueOf(AwarenessConstants.ERROR_UNKNOWN_CODE), "saveDrinkWater");
        hashMap2.put(400019462, "savePhysiologicalCycleBusiness");
        hashMap2.put(400020, "bodyShapeAllAbility");
        hashMap2.put(500010, "save_sleep_on_off_bed_record");
        hashMap2.put(700011, "mindfulnessAllAbility");
        b = Collections.unmodifiableMap(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put(400011975, "delete_new_body_thermometer");
        hashMap3.put(2103, "delete_oxygen");
        hashMap3.put(2109, "delete_uric_acid");
        hashMap3.put(400020, "bodyShapeAllAbility");
        hashMap3.put(500010, "delete_sleep_on_off_bed_record");
        hashMap3.put(22106, "delete_core_sleep_bed");
        hashMap3.put(22107, "delete_core_sleep_on");
        hashMap3.put(700011, "mindfulnessAllAbility");
        d = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(2, "query_step_detail");
        hashMap4.put(2002, "query_heart_rate_detail");
        hashMap4.put(2034, "query_stress_detail");
        hashMap4.put(400011975, "query_new_body_thermometer");
        hashMap4.put(2103, "query_oxygen_detail");
        hashMap4.put(500002, "query_osa_set");
        hashMap4.put(500002656, "query_osa_detail_half_hour");
        hashMap4.put(22100, "query_core_sleep_session");
        c = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.put(700002, "query_vascular_health");
        hashMap5.put(700003, "query_vascular_health");
        hashMap5.put(400017, "query_vascular_health");
        hashMap5.put(500002, "query_vascular_health");
        hashMap5.put(500002656, "query_vascular_health");
        hashMap5.put(10002, "query_blood_pressure");
        hashMap5.put(10001, "query_blood_sugar");
        hashMap5.put(22100, "query_convergence_core_sleep_session");
        hashMap5.put(2002, "query_convergence_heart_rate_detail");
        hashMap5.put(400011975, "query_convergence_new_body_thermometer");
        hashMap5.put(30264, "basicSportQuery");
        hashMap5.put(30283, "basicSportQuery");
        hashMap5.put(30265, "basicSportQuery");
        hashMap5.put(30273, "basicSportQuery");
        hashMap5.put(30274, "basicSportQuery");
        hashMap5.put(30257, "basicSportQuery");
        hashMap5.put(30258, "basicSportQuery");
        hashMap5.put(30259, "basicSportQuery");
        hashMap5.put(30260, "basicSportQuery");
        hashMap5.put(30262, "basicSportQuery");
        hashMap5.put(30266, "basicSportQuery");
        hashMap5.put(30281, "basicSportQuery");
        hashMap5.put(500007, "query_arrhythmia");
        hashMap5.put(700004, "query_arrhythmia");
        hashMap5.put(700009, "query_electrocardiogram");
        hashMap5.put(700005, "subscribe_data");
        hashMap5.put(700006, "subscribe_data");
        hashMap5.put(700007, "subscribe_data");
        hashMap5.put(700008, "subscribe_data");
        hashMap5.put(400018, "queryPhysiologicalCycle");
        hashMap5.put(400018283, "queryPhysiologicalCycleRemark");
        hashMap5.put(Integer.valueOf(AwarenessConstants.ERROR_UNKNOWN_CODE), "queryDrinkWater");
        hashMap5.put(400019462, "queryPhysiologicalCycleBusiness");
        hashMap5.put(700010, "queryCntbpPpg");
        hashMap5.put(400020, "bodyShapeAllAbility");
        hashMap5.put(500010, "query_sleep_on_off_bed_record");
        hashMap5.put(700011, "mindfulnessAllAbility");
        e = Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        hashMap6.put(700005, "subscribe_data");
        hashMap6.put(700006, "subscribe_data");
        hashMap6.put(700007, "subscribe_data");
        hashMap6.put(700008, "subscribe_data");
        g = Collections.unmodifiableMap(hashMap6);
    }

    public static int a(Context context) {
        if (f != 0) {
            return f;
        }
        f = e(context);
        return f;
    }

    public static int c() {
        return i;
    }

    public static void e(int i2) {
        i = i2;
    }

    public static boolean d(String str) {
        Log.i("HiHealthKitVersionUtil", "isServiceSupport apiName:" + str);
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        Map<String, Integer> map = f13333a;
        int intValue = map.get(str) == null ? 0 : map.get(str).intValue();
        if (intValue == 0) {
            Log.w("HiHealthKitVersionUtil", "support by default");
            return true;
        }
        Log.i("HiHealthKitVersionUtil", "isServiceSupport apiLevel: " + i + ", minSupportApiLevel:" + intValue);
        return i >= intValue;
    }

    private static int e(Context context) {
        if (context == null) {
            Log.w("HiHealthKitVersionUtil", "getApiLevelForMetaData context is null");
            return 0;
        }
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            Log.w("HiHealthKitVersionUtil", "getApiLevelForMetaData PackageManager is null");
            return 0;
        }
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                int i2 = applicationInfo.metaData.getInt("com.huawei.hihealthkit.hihealthkitapi.sdk.api_level");
                Log.i("HiHealthKitVersionUtil", "getApiLevelForMetaData apiLevel:" + i2);
                return i2;
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e("HiHealthKitVersionUtil", "getApiLevelForMetaData PackageManager.NameNotFoundException");
        }
        return 0;
    }

    public static boolean d(int i2) {
        return d(b.get(Integer.valueOf(i2)));
    }

    public static boolean a(int i2) {
        return d(d.get(Integer.valueOf(i2)));
    }

    public static boolean b(int i2) {
        return d(c.get(Integer.valueOf(i2)));
    }

    public static boolean c(int i2) {
        return d(e.get(Integer.valueOf(i2)));
    }
}
