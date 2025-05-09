package defpackage;

import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import defpackage.dcz;
import health.compact.a.HiDateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sqg {
    private static final String d = "PluginOperationAdapterHelper";

    private static int[] a() {
        return new int[]{2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015};
    }

    private static int[] b() {
        return new int[]{2006, 2007, 2018};
    }

    private static int[] f() {
        return new int[]{44006, 44007, 44001, 44002, 44003, 44005, 44004};
    }

    public static void a(HiAggregateOption hiAggregateOption, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: sqg.4
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                LogUtil.c(sqg.d, "getHiAggregateData datas = ", list);
                if (list != null) {
                    sqg.c(i, list, IBaseResponseCallback.this);
                } else {
                    IBaseResponseCallback.this.d(0, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(int i, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        if (i == 3) {
            e(list, iBaseResponseCallback);
            return;
        }
        if (i == 4) {
            a(i, list, iBaseResponseCallback);
            return;
        }
        if (i == 5) {
            e(i, list, iBaseResponseCallback);
        } else if (i == 8) {
            d(i, list, iBaseResponseCallback);
        } else {
            if (i != 9) {
                return;
            }
            a(list, iBaseResponseCallback);
        }
    }

    private static void a(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            JSONObject c = c(it.next());
            if (c == null) {
                iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                return;
            }
            jSONArray.put(c);
        }
        try {
            jSONObject.put("professionalSleepTotal", jSONArray);
            LogUtil.c(d, "coreSleepObject = ", jSONObject);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(d, e.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static JSONObject c(HiHealthData hiHealthData) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("fallAsleepTime", hiHealthData.getLong("stat_out_core_sleep_fall_time"));
            jSONObject2.put("wakeupTime", hiHealthData.getLong("stat_out_core_sleep_wake_up_time"));
            jSONObject2.put("sleepScore", hiHealthData.getLong("stat_out_core_sleep_score"));
            jSONObject2.put("sleepLatency ", hiHealthData.getFloat("stat_out_core_sleep_latency"));
            jSONObject2.put("goBedTime", hiHealthData.getFloat("stat_out_core_sleep_go_bed_time"));
            jSONObject2.put("validData", hiHealthData.getFloat("stat_out_core_sleep_valid_data"));
            jSONObject2.put("sleepEfficiency", hiHealthData.getFloat("stat_out_core_sleep_efficiency"));
            jSONObject2.put("lightSleepTime", hiHealthData.getFloat("stat_core_sleep_shallow_duration"));
            jSONObject2.put("deepSleepTime", hiHealthData.getFloat("stat_core_sleep_deep_duration"));
            jSONObject2.put("dreamTime", hiHealthData.getFloat("stat_core_sleep_dream_duration"));
            jSONObject2.put("awakeTime", hiHealthData.getFloat("stat_core_sleep_wake_duration"));
            jSONObject2.put("allSleepTime", hiHealthData.getFloat("stat_core_sleep_duration_sum"));
            jSONObject2.put("wakeupCnt", hiHealthData.getFloat("stat_core_sleep_wake_count"));
            jSONObject2.put("deepSleepPart", hiHealthData.getFloat("stat_core_sleep_deep_part_count"));
            jSONObject2.put("snoreFreq", hiHealthData.getFloat("stat_out_core_sleep_snore_freq"));
            jSONObject2.put("daySleepTime", hiHealthData.getFloat("stat_core_sleep_noon_duration"));
            jSONObject2.put("sleepSuggestion", (Object) null);
            jSONObject.put("date", hiHealthData.getDay());
            jSONObject.put("generateTime", hiHealthData.getCreateTime());
            jSONObject.put("professionalSleep", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b(d, e.getMessage());
            return null;
        }
    }

    private static void d(int i, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (HiHealthData hiHealthData : list) {
            String metaData = hiHealthData.getMetaData();
            if (metaData == null || metaData.length() == 0 || Constants.NULL.equalsIgnoreCase(metaData) || "0".equals(metaData)) {
                JSONObject d2 = d(hiHealthData, metaData, i);
                if (d2 == null) {
                    iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                    return;
                }
                jSONArray.put(d2);
            }
        }
        try {
            jSONObject.put("data", jSONArray);
            LogUtil.c(d, "weightObj = ", jSONObject);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(d, "dealHiHealthAggregateData type5 json exception:", e.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static JSONObject d(HiHealthData hiHealthData, String str, int i) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("extendAttribute", "");
            jSONObject2.put("weightSource", hiHealthData.getInt("trackdata_deviceType"));
            jSONObject2.put(BleConstants.BODY_FAT_RATE, hiHealthData.getDouble(BleConstants.BODY_FAT_RATE));
            jSONObject2.put("bodyWeight", hiHealthData.getDouble("bodyWeight"));
            jSONObject2.put("bodyFat", hiHealthData.getDouble("bodyFat"));
            jSONObject2.put(BleConstants.IMPEDANCE, hiHealthData.getDouble(BleConstants.IMPEDANCE));
            jSONObject2.put(BleConstants.BMI, hiHealthData.getDouble(BleConstants.BMI));
            jSONObject2.put(BleConstants.MUSCLE_MASS, hiHealthData.getDouble(BleConstants.MUSCLE_MASS));
            jSONObject2.put(BleConstants.MOISTURE, hiHealthData.getDouble(BleConstants.MOISTURE));
            jSONObject2.put(BleConstants.BONE_SALT, hiHealthData.getDouble(BleConstants.BONE_SALT));
            jSONObject2.put(BleConstants.BODY_AGE, hiHealthData.getDouble(BleConstants.BODY_AGE));
            jSONObject2.put(BleConstants.BODY_SCORE, hiHealthData.getDouble(BleConstants.BODY_SCORE));
            jSONObject2.put(BleConstants.BASAL_METABOLISM, hiHealthData.getDouble(BleConstants.BASAL_METABOLISM));
            jSONObject2.put(BleConstants.MOISTURE_RATE, hiHealthData.getDouble(BleConstants.MOISTURE_RATE));
            jSONObject2.put(BleConstants.VISCERAL_FAT_LEVEL, hiHealthData.getDouble(BleConstants.VISCERAL_FAT_LEVEL));
            jSONObject2.put(BleConstants.PROTEIN_RATE, hiHealthData.getDouble(BleConstants.PROTEIN_RATE));
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("mStartTime", hiHealthData.getStartTime());
            jSONObject3.put("mEndTime", hiHealthData.getEndTime());
            jSONObject3.put(MedalConstants.EVENT_KEY, "WEIGHT_BODYFAT_BROAD");
            jSONObject3.put("value", jSONObject2);
            jSONObject3.put("unit", "");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject3);
            jSONObject.put("mStartTime", hiHealthData.getStartTime());
            jSONObject.put("mEndTime", hiHealthData.getEndTime());
            jSONObject.put("metadata", str);
            jSONObject.put("recordId", "");
            jSONObject.put("type", i);
            jSONObject.put("timeZone", hiHealthData.getTimeZone());
            jSONObject.put("samplePoints", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b(d, "dealHiHealthAggregateData type8 exception:", e.getMessage());
            return null;
        }
    }

    private static void e(int i, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (HiHealthData hiHealthData : list) {
            float f = hiHealthData.getFloat("BLOOD_PRESSURE_SYSTOLIC");
            float f2 = hiHealthData.getFloat("BLOOD_PRESSURE_DIASTOLIC");
            if (f >= 1.0f && f2 >= 1.0f) {
                JSONObject jSONObject2 = new JSONObject();
                JSONObject jSONObject3 = new JSONObject();
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("BLOODPRESSURE_DIASTOLIC", f2);
                    jSONObject4.put("BLOODPRESSURE_SYSTOLIC", f);
                    jSONObject4.put("DATA_POINT_DYNAMIC_HEARTRATE", hiHealthData.getFloat(BleConstants.BLOODPRESSURE_SPHYGMUS));
                    jSONObject3.put("mStartTime", hiHealthData.getStartTime());
                    jSONObject3.put("mEndTime", hiHealthData.getEndTime());
                    jSONObject3.put(MedalConstants.EVENT_KEY, "BLOODPRESSURE");
                    jSONObject3.put("value", jSONObject4);
                    jSONObject3.put("unit", "");
                    JSONArray jSONArray2 = new JSONArray();
                    jSONArray2.put(jSONObject3);
                    jSONObject2.put("mStartTime", hiHealthData.getStartTime());
                    jSONObject2.put("mEndTime", hiHealthData.getEndTime());
                    jSONObject2.put("metadata", hiHealthData.getMetaData());
                    jSONObject2.put("recordId", "");
                    jSONObject2.put("type", i);
                    jSONObject2.put("timeZone", hiHealthData.getTimeZone());
                    jSONObject2.put("samplePoints", jSONArray2);
                    jSONArray.put(jSONObject2);
                } catch (JSONException unused) {
                    iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                    return;
                }
            }
        }
        try {
            jSONObject.put("data", jSONArray);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException unused2) {
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static void a(int i, List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            JSONObject d2 = d(it.next(), i);
            if (d2 == null) {
                iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                return;
            }
            jSONArray.put(d2);
        }
        try {
            jSONObject.put("data", jSONArray);
            LogUtil.c(d, "bloodSugarObject = ", jSONObject);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(d, "dealHiHealthAggregateData type4 json exception:", e.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static JSONObject d(HiHealthData hiHealthData, int i) {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("BLOOD_SUGAR_BF_BEFORE", hiHealthData.getFloat("bloodsugar_bf_before"));
            jSONObject2.put("BLOOD_SUGAR_BF_AFTER", hiHealthData.getFloat("bloodsugar_bf_after"));
            jSONObject2.put("BLOOD_SUGAR_LC_BEFORE", hiHealthData.getFloat("bloodsugar_lc_before"));
            jSONObject2.put("BLOOD_SUGAR_LC_AFTER", hiHealthData.getFloat("bloodsugar_lc_after"));
            jSONObject2.put("BLOOD_SUGAR_DN_BEFORE", hiHealthData.getFloat("bloodsugar_dn_before"));
            jSONObject2.put("BLOOD_SUGAR_DN_AFTER", hiHealthData.getFloat("bloodsugar_dn_after"));
            jSONObject2.put("BLOOD_SUGAR_SL_BEFORE", hiHealthData.getFloat("bloodsugar_sl_before"));
            jSONObject2.put("BLOOD_SUGAR_BEFORE_DAWN", hiHealthData.getFloat("bloodsugar_before_dawn"));
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("mStartTime", hiHealthData.getStartTime());
            jSONObject3.put("mEndTime", hiHealthData.getEndTime());
            jSONObject3.put(MedalConstants.EVENT_KEY, "BLOODGLUCOSE_BLOODSUGAR");
            jSONObject3.put("value", jSONObject2);
            jSONObject3.put("unit", "");
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject3);
            jSONObject.put("mStartTime", hiHealthData.getStartTime());
            jSONObject.put("mEndTime", hiHealthData.getEndTime());
            jSONObject.put("metadata", hiHealthData.getMetaData());
            jSONObject.put("recordId", "");
            jSONObject.put("type", i);
            jSONObject.put("timeZone", hiHealthData.getTimeZone());
            jSONObject.put("samplePoints", jSONArray);
            return jSONObject;
        } catch (JSONException e) {
            LogUtil.b(d, "dealHiHealthAggregateData type4 exception:", e.getMessage());
            return null;
        }
    }

    private static void e(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        String str = "deepDuration";
        String str2 = "mEndTime";
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        for (HiHealthData hiHealthData : list) {
            JSONObject jSONObject2 = new JSONObject();
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("mStartTime", hiHealthData.getLong("mStartTime"));
                jSONObject3.put(str2, hiHealthData.getLong(str2));
                jSONObject3.put(str, hiHealthData.getFloat(str));
                jSONObject3.put("lightDuration", hiHealthData.getFloat("lightDuration"));
                jSONObject3.put("awakeDuration", hiHealthData.getFloat("awakeDuration"));
                jSONObject3.put("awakeTimes", hiHealthData.getFloat("awakeTimes"));
                jSONObject3.put("totalDuration", hiHealthData.getFloat("totalDuration"));
                String str3 = str;
                String str4 = str2;
                jSONObject2.put("date", hiHealthData.getDay());
                jSONObject2.put("generateTime", hiHealthData.getCreateTime());
                jSONObject2.put("sleepBasic", jSONObject3);
                jSONArray.put(jSONObject2);
                str = str3;
                str2 = str4;
            } catch (JSONException e) {
                LogUtil.b(d, e.getMessage());
                iBaseResponseCallback.d(1001, null);
                return;
            }
        }
        try {
            jSONObject.put("sleepTotal", jSONArray);
            LogUtil.c(d, "jsonObject = ", jSONObject);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e2) {
            LogUtil.b(d, e2.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    public static void ekE_(int i, SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        if (i != 7) {
            if (i == 10) {
                ekF_(sparseArray, iBaseResponseCallback);
                return;
            } else {
                LogUtil.a(d, "dealHealthData stressObject in default branch ");
                iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                return;
            }
        }
        List list = (List) sparseArray.get(2002);
        List list2 = (List) sparseArray.get(2018);
        if (list == null && list2 == null) {
            LogUtil.h(d, "dealHealthData The return run case7 list is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            e(jSONArray, list, "DATA_POINT_DYNAMIC_HEARTRATE", "bpm", iBaseResponseCallback);
        }
        if (list2 != null) {
            e(jSONArray, list2, "DATA_POINT_REST_HEARTRATE", "bpm", iBaseResponseCallback);
        }
        try {
            jSONObject.put("data", jSONArray);
            LogUtil.c(d, "heartRateObject = ", jSONObject);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(d, "dealHealthData heartRateObject Exception = ", e.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static void ekF_(SparseArray<Object> sparseArray, IBaseResponseCallback iBaseResponseCallback) {
        Object obj = sparseArray.get(2019);
        Object obj2 = sparseArray.get(2020);
        List list = (List) obj;
        List list2 = (List) obj2;
        List list3 = (List) sparseArray.get(2021);
        List list4 = (List) sparseArray.get(44306);
        if (list == null && list2 == null && list3 == null && list4 == null) {
            LogUtil.h(d, "dealHealthData The return run case10 list is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            e(jSONArray, list, "STRESS_PASSIVE_MEASUREMENT", "", iBaseResponseCallback);
        }
        if (list2 != null) {
            e(jSONArray, list2, "STRESS_POSITIVE_MEASUREMENT", "", iBaseResponseCallback);
        }
        if (list3 != null) {
            e(jSONArray, list3, "BREATHING_RELAXATION", "", iBaseResponseCallback);
        }
        if (list4 != null) {
            e(jSONArray, list4, "PRESSURE_MESURE_VALUE_AVG", "", iBaseResponseCallback);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", jSONArray);
            iBaseResponseCallback.d(0, jSONObject);
        } catch (JSONException e) {
            LogUtil.b(d, "dealHealthData stressObject Exception = ", e.getMessage());
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static void e(JSONArray jSONArray, List<HiHealthData> list, String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        for (HiHealthData hiHealthData : list) {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("mStartTime", hiHealthData.getStartTime());
                jSONObject2.put("mEndTime", hiHealthData.getEndTime());
                jSONObject2.put(MedalConstants.EVENT_KEY, str);
                jSONObject2.put("value", hiHealthData.getFloatValue());
                jSONObject2.put("unit", str2);
                JSONArray jSONArray2 = new JSONArray();
                jSONArray2.put(jSONObject2);
                jSONObject.put("mStartTime", hiHealthData.getStartTime());
                jSONObject.put("mEndTime", hiHealthData.getEndTime());
                jSONObject.put("metadata", hiHealthData.getMetaData());
                jSONObject.put("recordId", "");
                jSONObject.put("type", hiHealthData.getType());
                jSONObject.put("timeZone", hiHealthData.getTimeZone());
                jSONObject.put("samplePoints", jSONArray2);
                jSONArray.put(jSONObject);
            } catch (JSONException e) {
                LogUtil.b(d, "dealHealthData healthData Exception = ", e.getMessage());
                iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                return;
            }
        }
    }

    public static HiAggregateOption d(long j, long j2, int i) {
        int[] f;
        String[] strArr;
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setAggregateType(1);
        if (i == 3) {
            hiAggregateOption.setGroupUnitType(3);
            f = f();
            strArr = new String[]{"mStartTime", "mEndTime", "deepDuration", "lightDuration", "awakeDuration", "awakeTimes", "totalDuration"};
        } else if (i == 4) {
            hiAggregateOption.setGroupUnitType(3);
            f = a();
            strArr = c();
        } else if (i == 5) {
            f = b();
            strArr = d();
        } else if (i == 8) {
            hiAggregateOption.setGroupUnitType(0);
            hiAggregateOption.setSortOrder(1);
            hiAggregateOption.setFilter("");
            f = new int[]{10006};
            strArr = new String[]{BleConstants.WEIGHT_KEY};
        } else if (i != 9) {
            f = null;
            strArr = null;
        } else {
            hiAggregateOption.setGroupUnitType(3);
            f = HiHealthDataType.a();
            strArr = g();
        }
        hiAggregateOption.setType(f);
        hiAggregateOption.setConstantsKey(strArr);
        if (i != 8) {
            hiAggregateOption.setGroupUnitSize(1);
            hiAggregateOption.setReadType(0);
        }
        return hiAggregateOption;
    }

    private static String[] d() {
        return new String[]{"BLOOD_PRESSURE_SYSTOLIC", "BLOOD_PRESSURE_DIASTOLIC", BleConstants.BLOODPRESSURE_SPHYGMUS};
    }

    private static String[] g() {
        return new String[]{"stat_core_sleep_dream_duration", "stat_core_sleep_deep_duration", "stat_core_sleep_shallow_duration", "stat_core_sleep_wake_duration", "stat_core_sleep_duration_sum", "stat_core_sleep_deep_part_count", "stat_core_sleep_wake_count", "stat_core_sleep_noon_duration", "stat_out_core_sleep_fall_time", "stat_out_core_sleep_wake_up_time", "stat_out_core_sleep_score", "stat_out_core_sleep_latency", "stat_out_core_sleep_go_bed_time", "stat_out_core_sleep_valid_data", "stat_out_core_sleep_efficiency", "stat_out_core_sleep_snore_freq"};
    }

    private static String[] c() {
        return new String[]{"bloodsugar_bf_before", "bloodsugar_bf_after", "bloodsugar_lc_before", "bloodsugar_lc_after", "bloodsugar_dn_before", "bloodsugar_dn_after", "bloodsugar_sl_before", "bloodsugar_before_dawn"};
    }

    public static HiDataReadOption c(long j, long j2, int i) {
        int[] iArr;
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        if (i == 7) {
            iArr = new int[]{2002, 2018};
        } else if (i != 10) {
            LogUtil.a(d, "getHiDataReadOptionByType in default branch");
            iArr = null;
        } else {
            iArr = new int[]{2019, 2020, 2021, 44306};
        }
        hiDataReadOption.setType(iArr);
        return hiDataReadOption;
    }

    public static boolean d(String str, String str2, int i) {
        try {
            int c = HiDateUtil.c(str, str2, "yyyyMMdd");
            String str3 = d;
            LogUtil.a(str3, "isLegalTimeInterval dayCounts = ", Integer.valueOf(c));
            if (c >= 1 && c <= i) {
                return true;
            }
            LogUtil.b(str3, "dayCounts is illegal !!");
            return false;
        } catch (ParseException e) {
            LogUtil.b(d, "parse date exception:", e.getMessage());
            return false;
        } catch (Exception unused) {
            LogUtil.b(d, "isLegalTimeInterval exception");
            return false;
        }
    }

    public static void b(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{30002});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: sqg.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj == null) {
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h(sqg.d, "getTrackSumData The return run data is empty");
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                List list = (List) sparseArray.get(30002);
                if (list != null) {
                    LogUtil.a(sqg.d, "getTrackSumData list size ", Integer.valueOf(list.size()));
                    sqg.d((List<HiHealthData>) list, IBaseResponseCallback.this);
                } else {
                    LogUtil.h(sqg.d, "getTrackSumData The return run list is null");
                    IBaseResponseCallback.this.d(0, null);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(List<HiHealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        JSONArray jSONArray = new JSONArray();
        for (HiHealthData hiHealthData : list) {
            long startTime = hiHealthData.getStartTime();
            long endTime = hiHealthData.getEndTime();
            String timeZone = hiHealthData.getTimeZone();
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                int sportDataSource = hiTrackMetaData.getSportDataSource();
                if (sportDataSource == 0 || sportDataSource == 1) {
                    int b = b(hiTrackMetaData.getSportType());
                    int totalDistance = hiTrackMetaData.getTotalDistance();
                    int totalCalories = hiTrackMetaData.getTotalCalories();
                    long totalTime = hiTrackMetaData.getTotalTime();
                    Map<Double, Double> partTimeMap = hiTrackMetaData.getPartTimeMap();
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put("mStartTime", startTime);
                        jSONObject.put("mEndTime", endTime);
                        jSONObject.put(BleConstants.SPORT_TYPE, b);
                        jSONObject.put(BleConstants.TOTAL_CALORIES, totalCalories);
                        jSONObject.put(BleConstants.TOTAL_DISTANCE, totalDistance);
                        jSONObject.put("totalTime", totalTime);
                        jSONObject.put("timeZone", timeZone);
                        jSONObject.put("partTimeMap", partTimeMap);
                        jSONArray.put(jSONObject);
                    } catch (JSONException unused) {
                        LogUtil.b(d, "getTrackData jsonObject JSONException!");
                        iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
                        return;
                    }
                }
            } catch (JsonSyntaxException unused2) {
                LogUtil.h(d, "trackMetaData is error");
            }
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            iBaseResponseCallback.d(0, jSONObject2);
        } catch (JSONException unused3) {
            LogUtil.b(d, "getTrackData dataObject JSONException!");
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    private static int b(int i) {
        if (i == 257) {
            return 5;
        }
        if (i == 258) {
            return 4;
        }
        if (i == 259) {
            return 3;
        }
        LogUtil.h(d, "getSportType no branch!");
        return i;
    }

    public static long e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static boolean c(HealthDevice.HealthDeviceKind healthDeviceKind) {
        String str = d;
        LogUtil.a(str, "findDevice");
        ArrayList<String> c = ceo.d().c(healthDeviceKind);
        if (koq.b(c)) {
            return false;
        }
        LogUtil.a(str, "findDevice, productList size = ", Integer.valueOf(c.size()));
        ResourceManager e = ResourceManager.e();
        Iterator<String> it = c.iterator();
        while (it.hasNext()) {
            dcz d2 = e.d(it.next());
            if (d2 == null) {
                LogUtil.a(d, "findDevice productInfo is null ");
            } else {
                dcz.b n = d2.n();
                if (n == null) {
                    LogUtil.a(d, "findDevice productManifest is null ");
                } else {
                    String a2 = n.a();
                    if (a2 != null && (a2.toUpperCase(Locale.ENGLISH).contains("HUAWEI") || a2.toUpperCase(Locale.ENGLISH).contains("HONOR"))) {
                        LogUtil.a(d, "findDevice = ", a2);
                        return true;
                    }
                    if (a2 != null) {
                        LogUtil.a(d, "other = ", a2);
                    } else if (b(d2.g())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean b(String str) {
        int parseInt;
        cuw c;
        try {
            parseInt = Integer.parseInt(str);
            c = jfu.c(parseInt);
        } catch (NumberFormatException e) {
            LogUtil.b(d, "findDeviceInner Exception = ", e.getMessage());
        }
        if (c != null && (c.p() == 1 || c.p() == 2)) {
            return true;
        }
        if (jfu.a().m() == parseInt) {
            LogUtil.a(d, "findDeviceInner getAm16ProInfo");
            return true;
        }
        return false;
    }

    public static void e(HiAggregateOption hiAggregateOption, int i) {
        int[] iArr;
        List synchronizedList = Collections.synchronizedList(new ArrayList());
        switch (i) {
            case 1:
                iArr = new int[]{42102};
                synchronizedList.clear();
                synchronizedList.add("Track_Run_Distance_Sum");
                break;
            case 2:
                iArr = new int[]{40002};
                synchronizedList.clear();
                synchronizedList.add("Track_Walk_Step_Sum");
                break;
            case 3:
                iArr = new int[]{42152};
                synchronizedList.clear();
                synchronizedList.add("Track_Ride_Distance_Sum");
                break;
            case 4:
                iArr = new int[]{42202};
                synchronizedList.clear();
                synchronizedList.add("Track_Swim_Distance_Sum");
                break;
            case 5:
                iArr = new int[]{40003};
                synchronizedList.clear();
                synchronizedList.add("Track_Calorie_Sum");
                break;
            case 6:
                hiAggregateOption.setGroupUnitType(3);
                iArr = new int[]{22100, 22105};
                synchronizedList.clear();
                synchronizedList.addAll(Arrays.asList("stat_core_sleep_duration_sum", "stat_core_sleep_noon_duration"));
                break;
            default:
                LogUtil.a(d, "unKnow sport type");
                iArr = null;
                break;
        }
        if (koq.c(synchronizedList)) {
            hiAggregateOption.setConstantsKey((String[]) synchronizedList.toArray(new String[0]));
        }
        hiAggregateOption.setType(iArr);
    }

    public static BigDecimal d(List<HiHealthData> list, String[] strArr) {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        for (HiHealthData hiHealthData : list) {
            if (strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    bigDecimal = bigDecimal.add(new BigDecimal(hiHealthData.getDouble(str)));
                }
            }
        }
        return bigDecimal;
    }

    public static HiAggregateOption a(long j, long j2, int i) {
        if (j <= 0 || j2 <= 0 || j > j2) {
            LogUtil.b(d, "startTime or endTime must be >=0 or endTime before startTime .", " startTime:", Long.valueOf(j), " endTime:", Long.valueOf(j2));
            return null;
        }
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setSortOrder(0);
        hiAggregateOption.setStartTime(j);
        hiAggregateOption.setEndTime(j2);
        hiAggregateOption.setGroupUnitType(7);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setReadType(0);
        e(hiAggregateOption, i);
        return hiAggregateOption;
    }

    public static void a(long j, long j2, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        final HiAggregateOption a2 = a(j, j2, i);
        if (a2 != null) {
            LogUtil.c(d, "getHealthDataStatistics option:", a2, " key:", Arrays.toString(a2.getConstantsKey()));
            HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(a2, new HiAggregateListener() { // from class: sqg.1
                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
                }

                @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                public void onResult(List<HiHealthData> list, int i2, int i3) {
                    LogUtil.c(sqg.d, "getHealthDataStatistics got a result type:", Integer.valueOf(i), " datas:", list);
                    if (iBaseResponseCallback == null) {
                        LogUtil.c(sqg.d, "data callback is null , ignore");
                    } else if (koq.c(list)) {
                        iBaseResponseCallback.d(0, sqg.d(list, a2.getConstantsKey()).setScale(2, RoundingMode.HALF_UP));
                    } else {
                        iBaseResponseCallback.d(i2, BigDecimal.ZERO.toString());
                    }
                }
            });
        } else if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(AwarenessConstants.ERROR_UNKNOWN_CODE, BigDecimal.ZERO.toString());
        }
    }
}
