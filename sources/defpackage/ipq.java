package defpackage;

import android.text.TextUtils;
import com.amap.api.services.core.AMapException;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.hihealth.data.Scopes;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.ScopeManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class ipq {

    /* renamed from: a, reason: collision with root package name */
    private static final Map<Integer, String> f13536a;
    private static final Map<Integer, String> b;
    private static final Map<Integer, String> c;
    private static final Map<Integer, String> e;

    static {
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(HiHealthDataType.b), Scopes.HEALTHKIT_BODYTEMPERATURE_READ);
        hashMap.put(2104, Scopes.HEALTHKIT_BODYTEMPERATURE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.TEMPERATURE_RESP_INFECTION.value()), Scopes.HEALTHKIT_BODYTEMPERATURE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.PULSE_WAVE_VELOCITY.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_PULSE.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ARTERIAL_ELASTICITY.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVITY_RECORD.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.PPG_OF_VASCULAR_HEALTH.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ECG_OF_VASCULAR_HEALTH.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(10069, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_FRAGMENT_RESP_INFECTION.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(10007, Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(44000, Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(22000, Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(22100, Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(10066, Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value()), Scopes.HEALTHKIT_SLEEP_READ);
        hashMap.put(31001, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31002, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31006, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31005, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31003, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31004, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(31007, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(61001, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(61002, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(10065, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(10067, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(10009, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(10068, Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.RRI_RESP_INFECTION.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(2103, "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(47201, "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(47202, "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(47203, "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(47204, "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPO2_RESP_INFECTION.value()), "https://www.huawei.com/healthkit/oxygensaturation.read");
        hashMap.put(2002, Scopes.HEALTHKIT_HEARTRATE_READ);
        hashMap.put(10008, Scopes.HEALTHKIT_HEARTRATE_READ);
        hashMap.put(101002, Scopes.HEALTHKIT_HEIGHTWEIGHT_READ);
        hashMap.put(10001, Scopes.HEALTHKIT_BLOODGLUCOSE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_DAILY_PROB_WIN.value()), Scopes.HEALTHKIT_BLOODGLUCOSE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BG_COMBINED_PPG_FEATURE.value()), Scopes.HEALTHKIT_BLOODGLUCOSE_READ);
        hashMap.put(10002, Scopes.HEALTHKIT_BLOODPRESSURE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_BLOOD_PRESSURE_RESULT.value()), Scopes.HEALTHKIT_BLOODPRESSURE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOODPRESSURE_RISK_RESEARCH_RESULT.value()), "https://www.huawei.com/healthkit/extend/research/bloodpressurerisk.read");
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_DAILY_STATUS_SET.value()), Scopes.HEALTHKIT_REPRODUCTIVE_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.OVARY_HEALTH_RESULT_SET.value()), Scopes.HEALTHKIT_REPRODUCTIVE_READ);
        hashMap.put(10006, Scopes.HEALTHKIT_BODYFAT_READ);
        hashMap.put(2034, Scopes.HEALTHKIT_STRESS_READ);
        hashMap.put(44306, Scopes.HEALTHKIT_STRESS_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()), Scopes.HEALTHKIT_STRESS_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.CNTBPORIGINPPGDATA.value()), Scopes.HEALTHKIT_HEARTHEALTH_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE.value()), Scopes.HEALTHKIT_BODYFAT_READ);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE_USER_MODIFY_VALUE.value()), Scopes.HEALTHKIT_BODYFAT_READ);
        hashMap.put(40002, Scopes.HEALTHKIT_STEP_READ);
        hashMap.put(40003, Scopes.HEALTHKIT_CALORIES_READ);
        hashMap.put(40004, Scopes.HEALTHKIT_DISTANCE_READ);
        hashMap.put(47101, Scopes.HEALTHKIT_STRENGTH_READ);
        b = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap(16);
        hashMap2.put(Integer.valueOf(HiHealthDataType.b), Scopes.HEALTHKIT_BODYTEMPERATURE_WRITE);
        hashMap2.put(2104, Scopes.HEALTHKIT_BODYTEMPERATURE_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()), Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22101, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22102, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22103, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22104, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22105, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(10007, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(44000, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22000, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(10066, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_SCORE.value()), Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22106, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(22107, Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()), Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()), Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.MONTHLY_SLEEP.value()), Scopes.HEALTHKIT_SLEEP_WRITE);
        hashMap2.put(2002, Scopes.HEALTHKIT_HEARTRATE_WRITE);
        hashMap2.put(10008, Scopes.HEALTHKIT_HEARTRATE_WRITE);
        hashMap2.put(101002, Scopes.HEALTHKIT_HEIGHTWEIGHT_WRITE);
        hashMap2.put(31001, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31002, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31006, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31005, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31003, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31004, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(31007, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(61001, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(61002, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10065, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10067, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10009, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10068, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_PPG_TYPE.value()), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.ARRHYTHMIA_RESULT_TYPE.value()), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10001, Scopes.HEALTHKIT_BLOODGLUCOSE_WRITE);
        hashMap2.put(2103, "https://www.huawei.com/healthkit/oxygensaturation.write");
        hashMap2.put(47201, "https://www.huawei.com/healthkit/oxygensaturation.write");
        hashMap2.put(47202, "https://www.huawei.com/healthkit/oxygensaturation.write");
        hashMap2.put(47203, "https://www.huawei.com/healthkit/oxygensaturation.write");
        hashMap2.put(47204, "https://www.huawei.com/healthkit/oxygensaturation.write");
        hashMap2.put(10002, Scopes.HEALTHKIT_BLOODPRESSURE_WRITE);
        hashMap2.put(10006, Scopes.HEALTHKIT_BODYFAT_WRITE);
        hashMap2.put(2034, Scopes.HEALTHKIT_STRESS_WRITE);
        hashMap2.put(44306, Scopes.HEALTHKIT_STRESS_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.MINDFULNESS_TYPE.value()), Scopes.HEALTHKIT_STRESS_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH.value()), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.VASCULAR_HEALTH_RESULT.value()), Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(10069, Scopes.HEALTHKIT_HEARTHEALTH_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE.value()), Scopes.HEALTHKIT_BODYFAT_WRITE);
        hashMap2.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_SHAPE_USER_MODIFY_VALUE.value()), Scopes.HEALTHKIT_BODYFAT_WRITE);
        hashMap2.put(50001, Scopes.HEALTHKIT_HEARTRATE_WRITE);
        e = Collections.unmodifiableMap(hashMap2);
        HashMap hashMap3 = new HashMap(16);
        hashMap3.put(Integer.valueOf(HiHealthDataType.b), "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_LEVEL.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_AVG_CNT_PER_HOUR.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_DETAIL_HALF_HOUR.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(101001, "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(101002, "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(2104, "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(1, "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(15002, "https://www.huawei.com/healthkit/extend/healthbehavior.read");
        hashMap3.put(10008, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(10007, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(44000, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(22000, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(22100, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(10066, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(50001, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31001, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31002, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31006, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31005, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31003, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31004, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(31007, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(61001, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(10065, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(Integer.valueOf(AMapException.CODE_AMAP_CLIENT_UPLOADAUTO_STARTED_ERROR), "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(10009, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(2002, "https://www.huawei.com/healthkit/extend/hearthealthsleep.read");
        hashMap3.put(40002, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(40003, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(40004, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(47101, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(101003, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30005, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30006, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30007, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30029, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30264, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30283, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30265, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30273, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30274, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30257, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30258, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30259, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30260, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30262, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30266, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30281, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30001, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(30002, Scopes.HEALTHKIT_EXTEND_SPORT_READ);
        hashMap3.put(10001, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(2103, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(47201, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(47202, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(47203, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(47204, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(10002, "https://www.huawei.com/healthkit/extend/chronicdisease.read");
        hashMap3.put(10006, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10010, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10011, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10015, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10016, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10017, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(10018, "https://www.huawei.com/healthkit/extend/fatreduction.read");
        hashMap3.put(2034, "https://www.huawei.com/healthkit/extend/emotion.read");
        hashMap3.put(44306, "https://www.huawei.com/healthkit/extend/emotion.read");
        hashMap3.put(Integer.valueOf(DicDataTypeUtil.DataType.FATTY_LIVER_SET.value()), "https://www.huawei.com/healthkit/extend/liverhealth.read");
        hashMap3.put(101204, "https://www.huawei.com/health/device.advancedctrl.readonly");
        hashMap3.put(101202, "https://www.huawei.com/health/device.data.readonly");
        hashMap3.put(101201, "https://www.huawei.com/health/device.readonly");
        f13536a = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap(16);
        hashMap4.put(Integer.valueOf(HiHealthDataType.b), "https://www.huawei.com/healthkit/extend/healthbehavior.write");
        hashMap4.put(Integer.valueOf(DicDataTypeUtil.DataType.OSA_SET.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(22101, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(22102, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(22103, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(22104, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(22105, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_SCORE.value()), "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(10001, "https://www.huawei.com/healthkit/extend/chronicdisease.write");
        hashMap4.put(10002, "https://www.huawei.com/healthkit/extend/chronicdisease.write");
        hashMap4.put(2109, "https://www.huawei.com/healthkit/extend/chronicdisease.write");
        hashMap4.put(2103, "https://www.huawei.com/healthkit/extend/chronicdisease.write");
        hashMap4.put(10006, "https://www.huawei.com/healthkit/extend/fatreduction.write");
        hashMap4.put(2104, "https://www.huawei.com/healthkit/extend/healthbehavior.write");
        hashMap4.put(30005, "https://www.huawei.com/healthkit/extend/sport.write");
        hashMap4.put(30006, "https://www.huawei.com/healthkit/extend/sport.write");
        hashMap4.put(30007, "https://www.huawei.com/healthkit/extend/sport.write");
        hashMap4.put(30029, "https://www.huawei.com/healthkit/extend/sport.write");
        hashMap4.put(10010, "https://www.huawei.com/healthkit/extend/womenhealth.write");
        hashMap4.put(10015, "https://www.huawei.com/healthkit/extend/womenhealth.write");
        hashMap4.put(10016, "https://www.huawei.com/healthkit/extend/womenhealth.write");
        hashMap4.put(10017, "https://www.huawei.com/healthkit/extend/womenhealth.write");
        hashMap4.put(10018, "https://www.huawei.com/healthkit/extend/womenhealth.write");
        hashMap4.put(2002, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(31001, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(61001, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(10065, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(10066, "https://www.huawei.com/healthkit/extend/hearthealthsleep.write");
        hashMap4.put(Integer.valueOf(DicDataTypeUtil.DataType.FATTY_LIVER_SET.value()), "https://www.huawei.com/healthkit/extend/liverhealth.write");
        c = Collections.unmodifiableMap(hashMap4);
    }

    private static String a(int i) {
        String str = b.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
        return "";
    }

    private static String e(int i) {
        String str = e.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
        return "";
    }

    private static String c(int i) {
        switch (i) {
            case 50001:
                return "https://www.huawei.com/healthkit/extend/realtimeheart.read";
            case 101201:
            case 101202:
            case 101204:
                return ScopeManager.HEALTHKIT_DEVICE_READ;
            default:
                LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
                return "";
        }
    }

    private static String b(int i) {
        switch (i) {
            case 101201:
            case 101202:
            case 101204:
                return ScopeManager.HEALTHKIT_DEVICE_WRITE;
            case 101203:
            default:
                LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
                return "";
        }
    }

    private static String a(String str) {
        int lastIndexOf;
        if (TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(".")) == -1) {
            return "";
        }
        String substring = str.substring(lastIndexOf + 1);
        String substring2 = str.substring(0, lastIndexOf);
        if (!"read".equals(substring) && !"write".equals(substring)) {
            return "";
        }
        return substring2 + ".both";
    }

    private static String d(int i) {
        String str = f13536a.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
        return "";
    }

    private static String j(int i) {
        String str = c.get(Integer.valueOf(i));
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.h("HMSAuth_HMSScope", "the dataType has no scopes , dataType is ", Integer.valueOf(i));
        return "";
    }

    public static List<String> a(int i, boolean z) {
        ArrayList arrayList = new ArrayList(4);
        String a2 = z ? a(i) : e(i);
        arrayList.add(z ? d(i) : j(i));
        arrayList.add(a2);
        arrayList.add(a(a2));
        arrayList.add(z ? c(i) : b(i));
        return arrayList;
    }

    public static boolean e(int i, boolean z) {
        List<String> a2 = a(i, z);
        return TextUtils.isEmpty(a2.get(0)) && TextUtils.isEmpty(a2.get(1)) && TextUtils.isEmpty(a2.get(2)) && TextUtils.isEmpty(a2.get(3));
    }
}
