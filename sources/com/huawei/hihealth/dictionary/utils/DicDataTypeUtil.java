package com.huawei.hihealth.dictionary.utils;

import com.huawei.haf.bundle.extension.ComponentInfo;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HealthDataStatPolicy;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.hms.network.embedded.r3;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.jsoperation.JsUtil;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import health.compact.a.HuaweiHealth;
import health.compact.a.util.LogUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/* loaded from: classes.dex */
public class DicDataTypeUtil {

    /* renamed from: a, reason: collision with root package name */
    private static HiHealthDictManager f4131a;

    public static class BgRemindResult {
        public static int b = 500029;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(int i, String str) {
        HiHealthDictManager d = HiHealthDictManager.d(HuaweiHealth.a());
        f4131a = d;
        d.e(false);
        HiHealthDictField d2 = f4131a.d(i, str);
        if (d2 == null) {
            LogUtil.c("HiH_DicDataTypeUtil", "dictField is null, type id is ", Integer.valueOf(i), ", filedName is ", str);
            return -1;
        }
        return d2.c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int e(int i, String str, String str2) {
        Objects.requireNonNull(f4131a, "need execute getHealthType first!");
        return f4131a.b(i, str, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int a(int i, String str, double d) {
        Objects.requireNonNull(f4131a, "need execute getHealthType first!");
        return f4131a.b(i, str, d);
    }

    public static class NonDictDataTransUtil {
        private static final Map<Integer, String> b;

        static {
            HashMap hashMap = new HashMap(16);
            b = hashMap;
            hashMap.put(300010035, "lthrHr");
            hashMap.put(300010036, "lthrPace");
            hashMap.put(300010037, "sportHeartPosture");
            hashMap.put(300010038, "recoveryTime");
            hashMap.put(300010039, "aerobicTime");
            hashMap.put(300010040, "rhythmTime");
            hashMap.put(300010041, "lactateTime");
            hashMap.put(300010042, HwExerciseConstants.JSON_NAME_MAX_OXYGEN_TIME);
            hashMap.put(300010043, HwExerciseConstants.JSON_NAME_ANAEROBIC_TIME);
            hashMap.put(300010044, "spurtTime");
            hashMap.put(300010048, "mAvgForeFootStrikePattern");
            hashMap.put(300010049, "mAvgWholeFootStrikePattern");
            hashMap.put(300010050, "mAvgHindFootStrikePattern");
            hashMap.put(300010051, "mAvgGroundContactTime");
            hashMap.put(300010052, "mAverageHangTime");
            hashMap.put(300010053, "mGroundHangTimeRate");
            hashMap.put(300010054, "mAvgEversionExcursion");
            hashMap.put(300010055, "mAvgSwingAngle");
            hashMap.put(300010056, "avg_i_p");
            hashMap.put(300010057, "avg_v_i_r");
            hashMap.put(300010058, "avg_gc_tb");
            hashMap.put(300010059, "avg_v_osc");
            hashMap.put(300010060, "avg_v_s_r");
            hashMap.put(300010061, "recordFlag");
            hashMap.put(300010079, "rowMachinePowerModeTime");
            hashMap.put(300010100, "skipNum");
            hashMap.put(300010101, "stumblingRope");
            hashMap.put(300010102, "maxSkippingTimes");
            hashMap.put(300010103, "skipSpeed");
            hashMap.put(300010104, "interruptTimes");
            hashMap.put(300010105, "singleShakeNum");
            hashMap.put(300010106, "doubleShakeNum");
            hashMap.put(300010107, "tripleShakeNum");
            hashMap.put(300010108, "reversedSkipNum");
            hashMap.put(300010109, "maxSkipSpeed");
            hashMap.put(300010110, "minSkipSpeed");
            hashMap.put(300010111, "targetDuration");
            hashMap.put(300010112, "targetSkipNum");
            hashMap.put(300010113, "targetSegmentNum");
            hashMap.put(300010114, "targetSegmentDuration");
            hashMap.put(300010115, "targetSegmentSkipNum");
            hashMap.put(300010116, "skippingMode");
            hashMap.put(300010117, "mWorkoutTypeOrigin");
            hashMap.put(300010118, "maxSkipSpeedRank");
            hashMap.put(300010119, "enduranceAbility");
            hashMap.put(300010120, "enduranceAbilityRank");
            hashMap.put(300010121, "enduranceTimeAbility");
            hashMap.put(300010122, "enduranceTimeAbilityRank");
            hashMap.put(300010123, "skipNumAbilityRank");
            hashMap.put(300010124, "maxSkippingTimesAbilityRank");
            hashMap.put(300010150, "avgHeartRate");
            hashMap.put(300010151, "coordinate");
            hashMap.put(300010152, "hotPathId");
            hashMap.put(300010153, "hotPathName");
            hashMap.put(300010154, "finishState");
            hashMap.put(300010155, "cpPunchState");
            hashMap.put(300010156, "routeType");
            hashMap.put(300010157, "finishedGroups");
            hashMap.put(300010158, "trainingActionCount");
            hashMap.put(300010159, HwExerciseConstants.JSON_NAME_ACTIVECALORIE);
        }

        private NonDictDataTransUtil() {
        }

        public static String d(int i) {
            return b.get(Integer.valueOf(i));
        }
    }

    public enum DataType {
        BODY_TEMPERATURE_SET(400011),
        BODY_TEMPERATURE(DicDataTypeUtil.c(400011, "bodyTemperature")),
        MAX_BODY_TEMPERATURE(DicDataTypeUtil.e(400011, "bodyTemperature", "MAX")),
        MIN_BODY_TEMPERATURE(DicDataTypeUtil.e(400011, "bodyTemperature", "MIN")),
        AVG_BODY_TEMPERATURE(DicDataTypeUtil.e(400011, "bodyTemperature", HealthDataStatPolicy.AVG)),
        COUNT_BODY_TEMPERATURE(DicDataTypeUtil.e(400011, "bodyTemperature", HealthDataStatPolicy.COUNT)),
        SKIN_TEMPERATURE_SET(400012),
        SKIN_TEMPERATURE(DicDataTypeUtil.c(400012, "skinTemperature")),
        MAX_SKIN_TEMPERATURE(DicDataTypeUtil.e(400012, "skinTemperature", "MAX")),
        MIN_SKIN_TEMPERATURE(DicDataTypeUtil.e(400012, "skinTemperature", "MIN")),
        AVG_SKIN_TEMPERATURE(DicDataTypeUtil.e(400012, "skinTemperature", HealthDataStatPolicy.AVG)),
        COUNT_SKIN_TEMPERATURE(DicDataTypeUtil.e(400012, "skinTemperature", HealthDataStatPolicy.COUNT)),
        ENVIRONMENT_TEMPERATURE_SET(400013),
        ENVIRONMENT_TEMPERATURE(DicDataTypeUtil.c(400013, "environmentTemperature")),
        MAX_ENV_TEMPERATURE(DicDataTypeUtil.e(400013, "environmentTemperature", "MAX")),
        MIN_ENV_TEMPERATURE(DicDataTypeUtil.e(400013, "environmentTemperature", "MIN")),
        AVG_ENV_TEMPERATURE(DicDataTypeUtil.e(400013, "environmentTemperature", HealthDataStatPolicy.AVG)),
        COUNT_ENV_TEMPERATURE(DicDataTypeUtil.e(400013, "environmentTemperature", HealthDataStatPolicy.COUNT)),
        HIGH_BODY_TEMPERATURE_ALARM_SET(400014),
        HIGH_BODY_TEMPERATURE_ALARM(DicDataTypeUtil.c(400014, "highBodyTemperatureAlarm")),
        EMOTION_SET(500031),
        EMOTION_STATUS(DicDataTypeUtil.c(500031, "emotionStatus")),
        EMOTION_STATUS_ALL_COUNT(DicDataTypeUtil.e(500031, "emotionStatus", HealthDataStatPolicy.COUNT)),
        EMOTION_STATUS_HAPPY_COUNT(DicDataTypeUtil.a(500031, "emotionStatus", 3.0d)),
        EMOTION_STATUS_PEACE_COUNT(DicDataTypeUtil.a(500031, "emotionStatus", 2.0d)),
        EMOTION_STATUS_UNHAPPY_COUNT(DicDataTypeUtil.a(500031, "emotionStatus", 1.0d)),
        VALENCE_CHARACTER(DicDataTypeUtil.c(500031, "valenceCharacter")),
        ORIGIN_STATUS(DicDataTypeUtil.c(500031, "originStatus")),
        AROUSAL_CHARACTER(DicDataTypeUtil.c(500031, "arousalCharacter")),
        OVARY_HEALTH_DAILY_STATUS_SET(500032),
        STATUS(DicDataTypeUtil.c(500032, "status")),
        PROGRESS(DicDataTypeUtil.c(500032, "progress")),
        OVARY_HEALTH_RESULT_SET(500033),
        PERIODIC_START_DATE(DicDataTypeUtil.c(500033, "periodicStartDate")),
        PERIODIC_END_DATE(DicDataTypeUtil.c(500033, "periodicEndDate")),
        RISK_LEVEL(DicDataTypeUtil.c(500033, "riskLevel")),
        SCORE(DicDataTypeUtil.c(500033, JsUtil.SCORE)),
        LOW_BODY_TEMPERATURE_ALARM_SET(400015),
        LOW_BODY_TEMPERATURE_ALARM(DicDataTypeUtil.c(400015, "lowBodyTemperatureAlarm")),
        LOW_SKIN_TEMPERATURE_ALARM_SET(400016),
        LOW_SKIN_TEMPERATURE_ALARM(DicDataTypeUtil.c(400016, "lowSkinTemperatureAlarm")),
        SUSPECTED_HIGH_TEMPERATURE_ALARM_SET(400025),
        SUSPECTED_HIGH_TEMPERATURE_ALARM(DicDataTypeUtil.c(400025, "suspectedHighTemperatureAlarm")),
        SUSPECTED_HIGH_TEMPERATURE_SET(400026),
        SUSPECTED_HIGH_TEMPERATURE_VALUE(DicDataTypeUtil.c(400026, "highTemperature")),
        SUSPECTED_HIGH_TEMPERATURE(DicDataTypeUtil.c(400026, "suspectedHighTemperature")),
        MAX_SUSPECTED_HIGH_TEMPERATURE(DicDataTypeUtil.e(400026, "suspectedHighTemperature", "MAX")),
        MIN_SUSPECTED_HIGH_TEMPERATURE(DicDataTypeUtil.e(400026, "suspectedHighTemperature", "MIN")),
        BREATH_TRAIN_SET(500001),
        BREATH_TRAIN(DicDataTypeUtil.c(500001, "breathTrain")),
        CONTINUE_BLOODSUGAR_SET(500035),
        CONTINUE_BLOODSUGAR_VALUE(DicDataTypeUtil.c(500035, "continueBloodSugar")),
        CONTINUE_BLOODSUGAR_STATUS(DicDataTypeUtil.c(500035, "status")),
        URGENT_HYPOGLYCEMIA(500036),
        URGENT_HYPOGLYCEMIA_VALUE(DicDataTypeUtil.c(500036, "urgentHypoglycemia")),
        URGENT_HYPOGLYCEMIA_THRESHOLD(DicDataTypeUtil.c(500036, "urgentHypoglycemiaThreshold")),
        MAX_URGENT_HYPOGLYCEMIA(DicDataTypeUtil.e(500036, "urgentHypoglycemia", "MAX")),
        MIN_URGENT_HYPOGLYCEMIA(DicDataTypeUtil.e(500036, "urgentHypoglycemia", "MIN")),
        APPROACHING_HYPOGLYCEMIA(500037),
        APPROACHING_HYPOGLYCEMIA_VALUE(DicDataTypeUtil.c(500037, "approachingHypoglycemia")),
        APPROACHING_HYPOGLYCEMIA_THRESHOLD(DicDataTypeUtil.c(500037, "approachingHypoglycemiaThreshold")),
        MAX_APPROACHING_HYPOGLYCEMIA(DicDataTypeUtil.e(500037, "approachingHypoglycemia", "MAX")),
        MIN_APPROACHING_HYPOGLYCEMIA(DicDataTypeUtil.e(500037, "approachingHypoglycemia", "MIN")),
        HYPOGLYCEMIA(500038),
        HYPOGLYCEMIA_VALUE(DicDataTypeUtil.c(500038, "hypoglycemia")),
        HYPOGLYCEMIA_THRESHOLD(DicDataTypeUtil.c(500038, "hypoglycemiaThreshold")),
        MAX_HYPOGLYCEMIA(DicDataTypeUtil.e(500038, "hypoglycemia", "MAX")),
        MIN_HYPOGLYCEMIA(DicDataTypeUtil.e(500038, "hypoglycemia", "MIN")),
        HYPERGLYCEMIA(500039),
        HYPERGLYCEMIA_VALUE(DicDataTypeUtil.c(500039, "hyperglycemia")),
        HYPERGLYCEMIA_THRESHOLD(DicDataTypeUtil.c(500039, "hyperglycemiaThreshold")),
        MAX_HYPERGLYCEMIA(DicDataTypeUtil.e(500039, "hyperglycemia", "MAX")),
        MIN_HYPERGLYCEMIA(DicDataTypeUtil.e(500039, "hyperglycemia", "MIN")),
        BLOODSUGAR_RISE(500040),
        BLOODSUGAR_RISE_VALUE(DicDataTypeUtil.c(500040, "bloodGlucoseRise")),
        BLOODSUGAR_RISING_RATE(DicDataTypeUtil.c(500040, "bloodGlucoseRisingRate")),
        BLOODSUGAR_LOWER_THRESHOLD(DicDataTypeUtil.c(500040, "lowerThreshold")),
        MAX_BLOODSUGAR_RISE_VALUE(DicDataTypeUtil.e(500040, "bloodGlucoseRise", "MAX")),
        MIN_BLOODSUGAR_RISE_VALUE(DicDataTypeUtil.e(500040, "bloodGlucoseRise", "MIN")),
        MAX_BLOODSUGAR_RISING_RATE(DicDataTypeUtil.e(500040, "bloodGlucoseRisingRate", "MAX")),
        MIN_BLOODSUGAR_RISING_RATE(DicDataTypeUtil.e(500040, "bloodGlucoseRisingRate", "MIN")),
        BLOODSUGAR_DECREASE(500041),
        BLOODSUGAR_DECREASE_VALUE(DicDataTypeUtil.c(500041, "bloodGlucoseDecrease")),
        BLOODSUGAR_DECREASE_RATE(DicDataTypeUtil.c(500041, "bloodGlucoseDecreaseRate")),
        BLOODSUGAR_UPPER_THRESHOLD(DicDataTypeUtil.c(500041, "upperThreshold")),
        MAX_BLOODSUGAR_DECREASE_VALUE(DicDataTypeUtil.e(500041, "bloodGlucoseDecrease", "MAX")),
        MIN_BLOODSUGAR_DECREASE_VALUE(DicDataTypeUtil.e(500041, "bloodGlucoseDecrease", "MIN")),
        MAX_BLOODSUGAR_DECREASE_RATE(DicDataTypeUtil.e(500041, "bloodGlucoseDecreaseRate", "MAX")),
        MIN_BLOODSUGAR_DECREASE_RATE(DicDataTypeUtil.e(500041, "bloodGlucoseDecreaseRate", "MIN")),
        BLOOD_GLUCOSE_TREND_SET(500043),
        BLOOD_GLUCOSE_TREND(DicDataTypeUtil.c(500043, "bloodGlucoseTrend")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT(500045),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Day_Sbp_Avg(DicDataTypeUtil.c(500045, "daySbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Day_Dbp_Avg(DicDataTypeUtil.c(500045, "dayDbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Day_Risk_Status(DicDataTypeUtil.c(500045, "dayRiskStatus")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Night_Sbp_Avg(DicDataTypeUtil.c(500045, "nightSbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Night_Dbp_Avg(DicDataTypeUtil.c(500045, "nightDbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Night_Risk_Status(DicDataTypeUtil.c(500045, "nightRiskStatus")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_All_Sbp_Avg(DicDataTypeUtil.c(500045, "allSbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_All_Dbp_Avg(DicDataTypeUtil.c(500045, "allDbpAvg")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_All_Risk_Status(DicDataTypeUtil.c(500045, "allRiskStatus")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Bp_Risk_Res(DicDataTypeUtil.c(500045, "bpRiskRes")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_StartTime_Stamp(DicDataTypeUtil.c(500045, "startTimestamp")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_Alg_Type(DicDataTypeUtil.c(500045, "algType")),
        BLOODPRESSURE_RISK_RESEARCH_RESULT_EndTime_Stamp(DicDataTypeUtil.c(500045, "endTimestamp")),
        BLOODPRESSURE_RISK_RESULT(500046),
        BLOODPRESSURE_RISK_RESULT_Day_Sbp_Avg(DicDataTypeUtil.c(500046, "daySbpAvg")),
        BLOODPRESSURE_RISK_RESULT_Day_Dbp_Avg(DicDataTypeUtil.c(500046, "dayDbpAvg")),
        BLOODPRESSURE_RISK_RESULT_Day_Risk_Status(DicDataTypeUtil.c(500046, "dayRiskStatus")),
        BLOODPRESSURE_RISK_RESULT_Night_Sbp_Avg(DicDataTypeUtil.c(500046, "nightSbpAvg")),
        BLOODPRESSURE_RISK_RESULT_Night_Dbp_Avg(DicDataTypeUtil.c(500046, "nightDbpAvg")),
        BLOODPRESSURE_RISK_RESULT_Night_Risk_Status(DicDataTypeUtil.c(500046, "nightRiskStatus")),
        BLOODPRESSURE_RISK_RESULT_All_Sbp_Avg(DicDataTypeUtil.c(500046, "allSbpAvg")),
        BLOODPRESSURE_RISK_RESULT_All_Dbp_Avg(DicDataTypeUtil.c(500046, "allDbpAvg")),
        BLOODPRESSURE_RISK_RESULT_All_Risk_Status(DicDataTypeUtil.c(500046, "allRiskStatus")),
        BLOODPRESSURE_RISK_RESULT_Bp_Risk_Res(DicDataTypeUtil.c(500046, "bpRiskRes")),
        BLOODPRESSURE_RISK_RESULT_StartTime_Stamp(DicDataTypeUtil.c(500046, "startTimestamp")),
        BLOODPRESSURE_RISK_RESULT_Alg_Type(DicDataTypeUtil.c(500046, "algType")),
        BLOODPRESSURE_RISK_RESULT_EndTime_Stamp(DicDataTypeUtil.c(500046, "endTimestamp")),
        LIGHT_FASTING_SET(600001),
        LIGHT_FASTING(DicDataTypeUtil.c(600001, "lightFasting")),
        DIET_RECORD_SET(600002),
        DIET_RECORD(DicDataTypeUtil.c(600002, "dietRecord")),
        FASTING_LITE_PHASE_SET(600003),
        FASTINGLITE_STARTTIME(DicDataTypeUtil.c(600003, "fastingLiteStartTime")),
        FASTINGLITE_ENDTIME(DicDataTypeUtil.c(600003, "fastingLiteEndTime")),
        NEXT_EAT_WINDOW_TIME(DicDataTypeUtil.c(600003, "nextEatingWindowTime")),
        FASTINGLITE_IS_EATING(DicDataTypeUtil.c(600003, "fastingLiteIsEating")),
        FASTINGLITE_RECORD_ID(DicDataTypeUtil.c(600003, "recordId")),
        BLOOD_PRESSURE_SET(10002),
        BLOOD_PRESSURE_DIASTOLIC(DicDataTypeUtil.c(10002, "BLOOD_PRESSURE_DIASTOLIC")),
        BLOOD_PRESSURE_SYSTOLIC(DicDataTypeUtil.c(10002, "BLOOD_PRESSURE_SYSTOLIC")),
        BLOOD_PRESSURE_SPHYGMUS(DicDataTypeUtil.c(10002, BleConstants.BLOODPRESSURE_SPHYGMUS)),
        WEIGHT_BODYFAT_BROAD(10006),
        BODY_WEIGHT(DicDataTypeUtil.c(10006, "bodyWeight")),
        BODY_FAT_RATE(DicDataTypeUtil.c(10006, BleConstants.BODY_FAT_RATE)),
        BODY_FAT(DicDataTypeUtil.c(10006, "bodyFat")),
        IMPEDANCE(DicDataTypeUtil.c(10006, BleConstants.IMPEDANCE)),
        BMI(DicDataTypeUtil.c(10006, BleConstants.BMI)),
        MUSCLE_MASS(DicDataTypeUtil.c(10006, BleConstants.MUSCLE_MASS)),
        BASAL_METABOLISM(DicDataTypeUtil.c(10006, BleConstants.BASAL_METABOLISM)),
        MOISTURE(DicDataTypeUtil.c(10006, BleConstants.MOISTURE)),
        MOISTURE_RATE(DicDataTypeUtil.c(10006, BleConstants.MOISTURE_RATE)),
        VISCERAL_FAT_LEVEL(DicDataTypeUtil.c(10006, BleConstants.VISCERAL_FAT_LEVEL)),
        BONE_SALT(DicDataTypeUtil.c(10006, BleConstants.BONE_SALT)),
        PROTEIN_RATE(DicDataTypeUtil.c(10006, BleConstants.PROTEIN_RATE)),
        BODY_SCORE(DicDataTypeUtil.c(10006, BleConstants.BODY_SCORE)),
        BODY_AGE(DicDataTypeUtil.c(10006, BleConstants.BODY_AGE)),
        HEART_RATE(DicDataTypeUtil.c(10006, IndoorEquipManagerApi.KEY_HEART_RATE)),
        PRESSURE(DicDataTypeUtil.c(10006, "pressure")),
        SKELETAL_MUSCLEL_MASS(DicDataTypeUtil.c(10006, "skeletalMusclelMass")),
        GENDER(DicDataTypeUtil.c(10006, CommonConstant.KEY_GENDER)),
        AGE(DicDataTypeUtil.c(10006, "age")),
        HEIGHT(DicDataTypeUtil.c(10006, "height")),
        HEALTHY_WEIGHT(DicDataTypeUtil.c(10006, "healthyWeight")),
        HEALTHY_FATRATE(DicDataTypeUtil.c(10006, "healthyFatRate")),
        BODY_SIZE(DicDataTypeUtil.c(10006, "bodySize")),
        RIGHTLEG_MUSCLEMASS(DicDataTypeUtil.c(10006, "rightLegMuscleMass")),
        LEFTLEG_MUSCLEMASS(DicDataTypeUtil.c(10006, "leftLegMuscleMass")),
        RIGHTARM_MUSCLEMASS(DicDataTypeUtil.c(10006, "rightArmMuscleMass")),
        LEFTARM_MUSCLEMASS(DicDataTypeUtil.c(10006, "leftArmMuscleMass")),
        TRUNK_MUSCLEMASS(DicDataTypeUtil.c(10006, "trunkMuscleMass")),
        RIGHTLEG_FATMASS(DicDataTypeUtil.c(10006, "rightLegFatMass")),
        LEFTLEG_FATMASS(DicDataTypeUtil.c(10006, "leftLegFatMass")),
        RIGHTARM_FATMASS(DicDataTypeUtil.c(10006, "rightArmFatMass")),
        LEFTARM_FATMASS(DicDataTypeUtil.c(10006, "leftArmFatMass")),
        TRUNK_FATMASS(DicDataTypeUtil.c(10006, "trunkFatMass")),
        WAIST_HIPRATIO(DicDataTypeUtil.c(10006, "waistHipRatio")),
        WEIGHT_BODY_SHAPE(DicDataTypeUtil.c(10006, "bodyShape")),
        RASM(DicDataTypeUtil.c(10006, "rasm")),
        FAT_BALANCE(DicDataTypeUtil.c(10006, "fatBalance")),
        MUSCLE_BALANCE(DicDataTypeUtil.c(10006, "muscleBalance")),
        POLE(DicDataTypeUtil.c(10006, "pole")),
        LHRH_IMPEDANCE(DicDataTypeUtil.c(10006, "lhrhImpedance")),
        LHLF_IMPEDANCE(DicDataTypeUtil.c(10006, "lhlfImpedance")),
        LHRF_IMPEDANCE(DicDataTypeUtil.c(10006, "lhrfImpedance")),
        RHLF_IMPEDANCE(DicDataTypeUtil.c(10006, "rhlfImpedance")),
        RHRF_IMPEDANCE(DicDataTypeUtil.c(10006, "rhrfImpedance")),
        LFRF_IMPEDANCE(DicDataTypeUtil.c(10006, "lfrfImpedance")),
        WAIST_HIPRATIO_USER(DicDataTypeUtil.c(10006, "waistHipRatioUser")),
        LHRH_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "lhrhHfImpedance")),
        LHLF_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "lhlfHfImpedance")),
        LHRF_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "lhrfHfImpedance")),
        RHLF_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "rhlfHfImpedance")),
        RHRF_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "rhrfHfImpedance")),
        LFRF_HF_IMPEDANCE(DicDataTypeUtil.c(10006, "lfrfHfImpedance")),
        CONFLICT_FLAG(DicDataTypeUtil.c(10006, "conflictFlag")),
        PROTEIN(DicDataTypeUtil.c(10006, "protein")),
        BLOOD_PRESSURE_REMINDER(DicDataTypeUtil.c(10002, "measurementReminder")),
        BLOOD_PRESSURE_ABNORMAL(DicDataTypeUtil.c(10002, "measurementAnomalyFlag")),
        BLOOD_BEFORE_ACTIVITY(DicDataTypeUtil.c(10002, "beforeMeasureActivity")),
        OSA_SET(500002),
        OSA_LEVEL(DicDataTypeUtil.c(500002, "osaLevel")),
        OSA_DETAIL_HALF_HOUR(DicDataTypeUtil.c(500002, "osaDetailHalfHour")),
        OSA_AVG_CNT_PER_HOUR(DicDataTypeUtil.c(500002, "osaAvgCntPerHour")),
        FATTY_LIVER_SET(500027),
        FATTY_LIVER_RISK(DicDataTypeUtil.c(500027, "fattyLiverRisk")),
        FATTY_LIVER_GRADE(DicDataTypeUtil.c(500027, "fattyLiverGrade")),
        MEDICATION_RULE(AwarenessConstants.ERROR_NO_PERMISSION_CODE),
        MEDICATION_RULE_ID(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleId")),
        MEDICATION_RULE_SOURCE(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleSource")),
        MEDICATION_RULE_DRUG(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleDrug")),
        MEDICATION_RULE_EXPECTED_TIME(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleExpectedTime")),
        MEDICATION_RULE_TYPE(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleType")),
        MEDICATION_RULE_START_TIME(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleStartTime")),
        MEDICATION_RULE_END_TIME(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationRuleEndTime")),
        MEDICATION_RULE_NAME(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationName")),
        MEDICATION_RULE_MARK(DicDataTypeUtil.c(AwarenessConstants.ERROR_NO_PERMISSION_CODE, "medicationMark")),
        MEDICATION_PUNCHING(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE),
        MEDICATION_PUNCH_SOURCE(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchSource")),
        MEDICATION_PUNCH_TASK_ID(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchTaskId")),
        MEDICATION_PUNCH_STATUS(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchStatus")),
        MEDICATION_PUNCH_DRUG(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchDrug")),
        MEDICATION_PUNCH_EXPECTED_TIME(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchExpectedTime")),
        MEDICATION_PUNCH_TIME(DicDataTypeUtil.c(AwarenessConstants.ERROR_INVALID_FREQUENCY_CODE, "medicationPunchTime")),
        ENVIRONMENT_NOISE_TYPE(700001),
        ARRHYTHMIA_PPG_TYPE(700004),
        DYNAMIC_BP_REPORT(700019),
        COURSE_RECORD(700014),
        MEDICAL_EXAM_REPORT(700017),
        MEDICAL_EXAM_REPORT_RESEARCH(700022),
        MINDFULNESS_TYPE(700011),
        VENTILATOR_RECORD_TYPE(700012),
        ELECTROCARDIOGRAM(700009),
        LAKELOUISE_AMS_SCORE_SET(500006),
        LAKELOUISE_HEADACHE(DicDataTypeUtil.c(500006, "headache")),
        LAKELOUISE_STOMACHACHE(DicDataTypeUtil.c(500006, "stomachache")),
        LAKELOUISE_WEAK(DicDataTypeUtil.c(500006, "weak")),
        LAKELOUISE_SCORE(DicDataTypeUtil.c(500006, "lakeLouiseScore")),
        LAKELOUISE_DIZZINESS(DicDataTypeUtil.c(500006, "dizziness")),
        SLEEP_DETAILS(700013),
        SLEEP_RDI(DicDataTypeUtil.c(700013, "rdi")),
        SLEEP_DATA_QUALITY(DicDataTypeUtil.c(700013, "sleepDataQuality")),
        DEEP_PART(DicDataTypeUtil.c(700013, "deepPart")),
        SNORE_FREQ(DicDataTypeUtil.c(700013, "snoreFreq")),
        SLEEP_LATENCY(DicDataTypeUtil.c(700013, "sleepLatency")),
        SLEEPDETAILS_WAKE_COUNT(DicDataTypeUtil.c(700013, "wakeCount")),
        SLEEPDETAILS_SLEEP_SCORE(DicDataTypeUtil.c(700013, "sleepScore")),
        TURNOVER_COUNT(DicDataTypeUtil.c(700013, "turnOverCount")),
        SLEEPDETAILS_BED_TIME(DicDataTypeUtil.c(700013, "bedTime")),
        SLEEPDETAILS_RISING_TIME(DicDataTypeUtil.c(700013, "risingTime")),
        FALLASLEEP_TIME(DicDataTypeUtil.c(700013, "fallAsleepTime")),
        SLEEPDETAILS_WAKEUP_TIME(DicDataTypeUtil.c(700013, "wakeupTime")),
        SLEEPDETAILS_PREPARE_SLEEP_TIME(DicDataTypeUtil.c(700013, "prepareSleepTime")),
        SLEEP_DETAILS_SLEEP_EFFICIENCY(DicDataTypeUtil.c(700013, "sleepEfficiency")),
        SLEEP_DETAILS_VALID_DATA(DicDataTypeUtil.c(700013, "validData")),
        SLEEP_DETAILS_MIN_HEARTRATE(DicDataTypeUtil.c(700013, "minHeartrate")),
        SLEEP_DETAILS_MAX_HEARTRATE(DicDataTypeUtil.c(700013, "maxHeartrate")),
        SLEEP_DETAILS_MIN_OXYGEN_SATURATION(DicDataTypeUtil.c(700013, "minOxygenSaturation")),
        SLEEP_DETAILS_MAX_OXYGEN_SATURATION(DicDataTypeUtil.c(700013, "maxOxygenSaturation")),
        SLEEP_DETAILS_MIN_BREATHRATE(DicDataTypeUtil.c(700013, "minBreathrate")),
        SLEEP_DETAILS_MAX_BREATHRATE(DicDataTypeUtil.c(700013, "maxBreathrate")),
        SLEEP_DETAILS_WAKEUP_FEELING(DicDataTypeUtil.c(700013, "wakeUpFeeling")),
        SLEEP_DETAILS_AVG_HEARTRATE(DicDataTypeUtil.c(700013, "avgHeartrate")),
        SLEEP_DETAILS_MIN_HEARTRATE_BASELINE(DicDataTypeUtil.c(700013, "minHeartrateBaseline")),
        SLEEP_DETAILS_MAX_HEARTRATE_BASELINE(DicDataTypeUtil.c(700013, "maxHeartrateBaseline")),
        SLEEP_DETAILS_HEARTRATE_DAY_TO_BASELINE(DicDataTypeUtil.c(700013, "heartrateDayToBaseline")),
        SLEEP_DETAILS_AVG_OXYGEN_SATURATION(DicDataTypeUtil.c(700013, "avgOxygenSaturation")),
        SLEEP_DETAILS_MIN_OXYGEN_SATURATION_BASELINE(DicDataTypeUtil.c(700013, "minOxygenSaturationBaseline")),
        SLEEP_DETAILS_MAX_OXYGEN_SATURATION_BASELINE(DicDataTypeUtil.c(700013, "maxOxygenSaturationBaseline")),
        SLEEP_DETAILS_OXYGEN_SATURATION_DAY_TO_BASELINE(DicDataTypeUtil.c(700013, "oxygenSaturationDayToBaseline")),
        SLEEP_DETAILS_AVG_BREATHRATE(DicDataTypeUtil.c(700013, "avgBreathrate")),
        SLEEP_DETAILS_MIN_BREATHRATE_BASELINE(DicDataTypeUtil.c(700013, "minBreathrateBaseline")),
        SLEEP_DETAILS_MAX_BREATHRATE_BASELINE(DicDataTypeUtil.c(700013, "maxBreathrateBaseline")),
        SLEEP_DETAILS_BREATHRATE_DAY_TO_BASELINE(DicDataTypeUtil.c(700013, "breathrateDayToBaseline")),
        SLEEP_DETAILS_AVG_HRV(DicDataTypeUtil.c(700013, "avgHrv")),
        SLEEP_DETAILS_MIN_HRV_BASELINE(DicDataTypeUtil.c(700013, "minHrvBaseline")),
        SLEEP_DETAILS_MAX_HRV_BASELINE(DicDataTypeUtil.c(700013, "maxHrvBaseline")),
        SLEEP_DETAILS_HRV_DAY_TO_BASELINE(DicDataTypeUtil.c(700013, "hrvDayToBaseline")),
        ARRHYTHMIA_RESULT_TYPE(500007),
        ARRHYTHMIA_SAVE_TIME(DicDataTypeUtil.c(500007, "saveTime")),
        ARRHYTHMIA_MEASURE_TYPE(DicDataTypeUtil.c(500007, "measureType")),
        PPG_IRREGULAR_HEARTBEAT(DicDataTypeUtil.c(500007, "ppgIrregularHeartbeat")),
        SLEEP_ON_OFF_BED_RECORD(500010),
        SLEEP_ON_OFF_BED_RECORD_BED_TIME(DicDataTypeUtil.c(500010, "bedTime")),
        SLEEP_ON_OFF_BED_RECORD_RISING_TIME(DicDataTypeUtil.c(500010, "risingTime")),
        SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME(DicDataTypeUtil.c(500010, "fallAsleepTime")),
        SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME(DicDataTypeUtil.c(500010, "wakeupTime")),
        PHYSIOLOGICAL_CYCLE(400018),
        PHYSIOLOGICAL_CYCLE_MOOD(DicDataTypeUtil.c(400018, "mood")),
        PHYSIOLOGICAL_CYCLE_SKIN(DicDataTypeUtil.c(400018, "skin")),
        PHYSIOLOGICAL_CYCLE_SEXUALITY(DicDataTypeUtil.c(400018, "sexuality")),
        PHYSIOLOGICAL_CYCLE_OVULATION(DicDataTypeUtil.c(400018, "ovulationTestPaper")),
        PHYSIOLOGICAL_CYCLE_REMARKS(DicDataTypeUtil.c(400018, "remarks")),
        PHYSIOLOGICAL_CYCLE_MUCUS(DicDataTypeUtil.c(400018, "cervicalMucus")),
        PHYSIOLOGICAL_CYCLE_QUANTITY(DicDataTypeUtil.c(400018, "quantity")),
        PHYSIOLOGICAL_CYCLE_DYSMENORRHEA(DicDataTypeUtil.c(400018, "dysmenorrhea")),
        BODY_SHAPE(400020),
        BODY_SHAPE_BUST_GIRTH(DicDataTypeUtil.c(400020, "bustGirth")),
        BODY_SHAPE_WAIST_GIRTH(DicDataTypeUtil.c(400020, "waistGirth")),
        BODY_SHAPE_HIPLINE(DicDataTypeUtil.c(400020, "hipline")),
        BODY_SHAPE_THIGH_GIRTH(DicDataTypeUtil.c(400020, "thighGirth")),
        BODY_SHAPE_CALVES(DicDataTypeUtil.c(400020, "calves")),
        BODY_SHAPE_ARM_CIRCUMFERENCE(DicDataTypeUtil.c(400020, "armCircumference")),
        BODY_SHAPE_HEAD_CIRCUMFERENCE(DicDataTypeUtil.c(400020, "headCircumference")),
        BODY_SHAPE_LEG_LENGTH(DicDataTypeUtil.c(400020, "legLength")),
        BODY_SHAPE_ARM_LENGTH(DicDataTypeUtil.c(400020, "armLength")),
        BODY_SHAPE_SHOULDER_WIDTH(DicDataTypeUtil.c(400020, "shoulderWidth")),
        BODY_SHAPE_WAIST_HIP_RATIO(DicDataTypeUtil.c(400020, "waistHipRatio")),
        BODY_SHAPE_BODY_FORM(DicDataTypeUtil.c(400020, "bodyForm")),
        BODY_SHAPE_USER_MODIFY_VALUE(DicDataTypeUtil.c(400020, "userModifyValue")),
        ALTITUDE_TYPE_SET(AwarenessConstants.ERROR_TIMEOUT_CODE),
        ALTITUDE_TYPE(DicDataTypeUtil.c(AwarenessConstants.ERROR_TIMEOUT_CODE, "altitude")),
        RESTING_METABOLISM_SET(500018),
        RESTING_METABOLISM(DicDataTypeUtil.c(500018, BleConstants.BASAL_METABOLISM)),
        CURRENT_BASAL_METABOLISM_SET(500019),
        CURRENT_BASAL_METABOLISM(DicDataTypeUtil.c(500019, "currentBasalMetabolism")),
        BASAL_METABOLISM_AFTER_EXERCISE_SET(500025),
        BASAL_METABOLISM_AFTER_EXERCISE(DicDataTypeUtil.c(500025, "basalMetabolismAfterExercise")),
        DRINK_WATER_SET(AwarenessConstants.ERROR_UNKNOWN_CODE),
        DRINK_WATER(DicDataTypeUtil.c(AwarenessConstants.ERROR_UNKNOWN_CODE, "drinkWater")),
        ACTIVE_HOUR(AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE),
        ACTIVE_HOUR_IS_ACTIVE(DicDataTypeUtil.c(AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE, r3.B)),
        ACTIVE_HOUR_IS_ACTIVE_COUNT(DicDataTypeUtil.e(AwarenessConstants.ERROR_LIMITED_REGISTRY_CODE, r3.B, HealthDataStatPolicy.COUNT)),
        PHYSIOLOGICAL_CYCLE_BUSINESS(400019),
        PHYSIOLOGICAL_CYCLE_LINKAGES(DicDataTypeUtil.c(400019, "linkages")),
        GOLF_COURSE_MODEL_TYPE(30287),
        GOLF_COURSE_MODEL_SPORT_TYPE(286),
        AVG_GOLF_SWING_COUNT(DicDataTypeUtil.e(30287, "golfSwingCount", HealthDataStatPolicy.AVG)),
        SUM_GOLF_SWING_COUNT(DicDataTypeUtil.e(30287, "golfSwingCount", HealthDataStatPolicy.SUM)),
        AVG_PUTTS(DicDataTypeUtil.e(30287, "putts", HealthDataStatPolicy.AVG)),
        AVG_GIR(DicDataTypeUtil.e(30287, "gir", HealthDataStatPolicy.AVG)),
        SUM_PAR(DicDataTypeUtil.e(30287, "par", HealthDataStatPolicy.SUM)),
        SUM_EAGLE(DicDataTypeUtil.e(30287, "eagle", HealthDataStatPolicy.SUM)),
        SUM_BIRDIE(DicDataTypeUtil.e(30287, "birdie", HealthDataStatPolicy.SUM)),
        SENSE_SPORT_TYPE(30291),
        SENSE_SPORT_SPORT_TYPE(290),
        SLEEP_RECORD(500005),
        SLEEP_SCORE(DicDataTypeUtil.c(500005, "sleepScore")),
        WAKE_COUNT(DicDataTypeUtil.c(500005, "wakeCount")),
        TURN_OVER_COUNT(DicDataTypeUtil.c(500005, "turnOverCount")),
        BED_TIME(DicDataTypeUtil.c(500005, "bedTime")),
        RISING_TIME(DicDataTypeUtil.c(500005, "risingTime")),
        SLEEP_END_REASON(DicDataTypeUtil.c(500005, "sleepEndReason")),
        SLEEP_SYMPTOMS(DicDataTypeUtil.c(500005, "sleepSymptoms")),
        EFFECTIVE_SLEEP_DURATION(DicDataTypeUtil.c(500005, "effectiveSleepDuration")),
        EFFECTIVE_SLEEP_CYCLES(DicDataTypeUtil.c(500005, "effectiveSleepCycles")),
        WAKE_UP_FEELING(DicDataTypeUtil.c(500005, "wakeUpFeeling")),
        NOON_SLEEP_INFO(DicDataTypeUtil.c(500005, "noonSleepInfo")),
        FALL_ASLEEP_TIME(DicDataTypeUtil.c(500005, "fallAsleepTime")),
        WAKEUP_TIME(DicDataTypeUtil.c(500005, "wakeupTime")),
        PREPARE_SLEEP_TIME(DicDataTypeUtil.c(500005, "prepareSleepTime")),
        DAILY_SLEEP_PROBLEM(DicDataTypeUtil.c(500005, "dailySleepProblem")),
        DAILY_SLEEP_DEGREE(DicDataTypeUtil.c(500005, "dailySleepDegree")),
        DAILY_SLEEP_INTERPRET(DicDataTypeUtil.c(500005, "dailySleepInterpretParam")),
        DAILY_TARGET_PROBLEM(DicDataTypeUtil.c(500005, "dailyTargetProblem")),
        DAILY_SLEEP_TASKS(DicDataTypeUtil.c(500005, "dailySleepTasks")),
        SLEEP_RECORD_QUALITY(DicDataTypeUtil.c(500005, "sleepRecordQuality")),
        DAY_SLEEP_RECORD_QUALITY(DicDataTypeUtil.e(500005, "sleepRecordQuality", "MIN")),
        DAILY_SLEEP_LATENCY(DicDataTypeUtil.c(500005, "latency")),
        MONTHLY_SLEEP(500013),
        RHYTHM_TYPE(DicDataTypeUtil.c(500013, "rhythmType")),
        MONTHLY_SLEEP_PROBLEM(DicDataTypeUtil.c(500013, "monthlySleepProblem")),
        MONTHLY_SLEEP_DEGREE(DicDataTypeUtil.c(500013, "monthlySleepDegree")),
        MONTHLY_SLEEP_TASKS(DicDataTypeUtil.c(500013, "monthlySleepTasks")),
        MONTHLY_SLEEP_INTERPRET(DicDataTypeUtil.c(500013, "monthlySleepInterpretParam")),
        BG_DAILY_RESULT(500012),
        BG_DAILY_RESULT_TIMESTAMP(DicDataTypeUtil.c(500012, "timestamp")),
        BG_DAILY_RESULT_TOTAL_NUMS(DicDataTypeUtil.c(500012, "totalNums")),
        BG_DAILY_RESULT_ABNORMAL_NUMS(DicDataTypeUtil.c(500012, "abnormalNums")),
        BG_DAILY_SLP_RESULT(500015),
        BG_REMIND(BgRemindResult.b),
        BG_REMIND_VALUE(DicDataTypeUtil.c(BgRemindResult.b, "bloodGlucoseRemind")),
        BG_COUNT_BLOOD_REMIND(DicDataTypeUtil.e(BgRemindResult.b, "countBloodRemind", HealthDataStatPolicy.COUNT)),
        BG_REMIND_TYPE(DicDataTypeUtil.c(BgRemindResult.b, "RemindType")),
        BG_REMIND_THRESHOLD(DicDataTypeUtil.c(BgRemindResult.b, "RemindThreshold")),
        BG_DAILY_SLP_RESULT_SLP_START_TIME(DicDataTypeUtil.c(500015, "slpStartTime")),
        BG_DAILY_SLP_RESULT_SLP_END_TIME(DicDataTypeUtil.c(500015, "slpEndTime")),
        BG_DAILY_SLP_RESULT_TOTAL_NUMS(DicDataTypeUtil.c(500015, "totalNums")),
        BG_DAILY_SLP_RESULT_ABNORMAL_NUMS(DicDataTypeUtil.c(500015, "abnormalNums")),
        BG_RISK_GROUP_RESULT(500014),
        BG_RISK_GROUP_RESULT_START_TIMESTAMP(DicDataTypeUtil.c(500014, "startTimestamp")),
        BG_RISK_GROUP_RESULT_END_TIMESTAMP(DicDataTypeUtil.c(500014, "endTimestamp")),
        BG_RISK_GROUP_RESULT_RISK_LEVEL(DicDataTypeUtil.c(500014, "riskLevel")),
        SPORT_BLOOD_PRESSURE_RESULT(500034),
        CARDIOVASCULAR_RISK_LEVEL_SBR(DicDataTypeUtil.c(500034, "cardiovascularRiskLevel")),
        BLOOD_PRESSUE_RISK(DicDataTypeUtil.c(500034, "bloodPressureRisk")),
        PERCEIVED_EXERTION(DicDataTypeUtil.c(500034, "perceivedExertion")),
        SYMPTOM(DicDataTypeUtil.c(500034, "symptom")),
        ECG_RESULT(DicDataTypeUtil.c(500034, "ecgResult")),
        ECG_AVG_HEART_RATE(DicDataTypeUtil.c(500034, "ecgAvgHeartRate")),
        SBP_BEFORE_SPORT(DicDataTypeUtil.c(500034, "sbpBeforeSport")),
        DBP_BEFORE_SPORT(DicDataTypeUtil.c(500034, "dbpBeforeSport")),
        SBP_AFTER_SPORT(DicDataTypeUtil.c(500034, "sbpAfterSport")),
        DBP_AFTER_SPORT(DicDataTypeUtil.c(500034, "dbpAfterSport")),
        SBP_AFTER_SPORT_THREE_MIN(DicDataTypeUtil.c(500034, "sbpAfterSportThreeMin")),
        DBP_AFTER_SPORT_THREE_MIN(DicDataTypeUtil.c(500034, "dbpAfterSportThreeMin")),
        SBP_AFTER_SPORT_SIX_MIN(DicDataTypeUtil.c(500034, "sbpAfterSportSixMin")),
        DBP_AFTER_SPORT_SIX_MIN(DicDataTypeUtil.c(500034, "dbpAfterSportSixMin")),
        SPORT_TYPE(DicDataTypeUtil.c(500034, BleConstants.SPORT_TYPE)),
        TOTAL_DISTANCE(DicDataTypeUtil.c(500034, BleConstants.TOTAL_DISTANCE)),
        TOTAL_TIME(DicDataTypeUtil.c(500034, "totalTime")),
        AVG_PACE(DicDataTypeUtil.c(500034, "avgPace")),
        AVG_HEART_RATE(DicDataTypeUtil.c(500034, "avgHeartRate")),
        MAX_HEART_RATE(DicDataTypeUtil.c(500034, "maxHeartRate")),
        START_TIME_STAMP(DicDataTypeUtil.c(500034, "startTimestamp")),
        END_TIME_STAMP(DicDataTypeUtil.c(500034, "endTimestamp")),
        BP_LABEL(DicDataTypeUtil.c(500034, "bpLabel")),
        ECG_START_TIME(DicDataTypeUtil.c(500034, "ecgStartTime")),
        ECG_END_TIME(DicDataTypeUtil.c(500034, "ecgEndTime")),
        BP_BEFORE_SPORT_TIME(DicDataTypeUtil.c(500034, "bpBeforeSportTime")),
        BP_AFTER_SPORT_TIME(DicDataTypeUtil.c(500034, "bpAfterSportTime")),
        BP_AFTER_SPORT_THREE_MIN_TIME(DicDataTypeUtil.c(500034, "bpAfterSportThreeMinTime")),
        BP_AFTER_SPORT_SIX_MIN_TIME(DicDataTypeUtil.c(500034, "bpAfterSportSixMinTime")),
        VASCULAR_HEALTH(400017),
        PULSE_WAVE_VELOCITY(DicDataTypeUtil.c(400017, "pulseWaveVelocity")),
        VASCULAR_PULSE(DicDataTypeUtil.c(400017, "vascularPulse")),
        ARTERIAL_ELASTICITY(DicDataTypeUtil.c(400017, "arterialElasticity")),
        ACTIVITY_RECORD(DicDataTypeUtil.c(400017, "activityRecord")),
        VASCULAR_AGE(DicDataTypeUtil.c(400017, "vascularAge")),
        CARDIOVASCULAR_RISK_LEVEL(DicDataTypeUtil.c(400017, "cardiovascularRiskLevel")),
        VASCULAR_HEALTH_HEART_RATE_VARIABILITY(DicDataTypeUtil.c(400017, "heartRateVariability")),
        VASCULAR_HEALTH_RESULT(400023),
        PULSE_WAVE_VELOCITY_RES(DicDataTypeUtil.c(400023, "pulseWaveVelocity")),
        VASCULAR_PULSE_RES(DicDataTypeUtil.c(400023, "vascularPulse")),
        ARTERIAL_ELASTICITY_RES(DicDataTypeUtil.c(400023, "arterialElasticity")),
        ACTIVITY_RECORD_RES(DicDataTypeUtil.c(400023, "activityRecord")),
        VASCULAR_AGE_RES(DicDataTypeUtil.c(400023, "vascularAge")),
        CARDIOVASCULAR_RISK_LEVEL_RES(DicDataTypeUtil.c(400023, "cardiovascularRiskLevel")),
        HEART_RATE_VARIABILITY_RES(DicDataTypeUtil.c(400023, "heartRateVariability")),
        ECG_START_TIME_RES(DicDataTypeUtil.c(400023, "ecgStartTime")),
        ECG_END_TIME_RES(DicDataTypeUtil.c(400023, "ecgEndTime")),
        RISK(DicDataTypeUtil.c(400023, "risk")),
        PPG_OF_VASCULAR_HEALTH(700002),
        ECG_OF_VASCULAR_HEALTH(700003),
        SPO2_RESP_INFECTION(700005),
        RRI_RESP_INFECTION(700006),
        BG_DAILY_PROB_WIN(700015),
        BG_COMBINED_PPG_FEATURE(700016),
        MARK_POINT(700018),
        TEMPERATURE_RESP_INFECTION(700007),
        SLEEP_FRAGMENT_RESP_INFECTION(700008),
        CNTBPORIGINPPGDATA(700010),
        DIVING_TYPE(30288),
        DIVING_SPORT_TYPE(287),
        SCUBA_DIVING_TYPE(30292),
        SCUBA_DIVING_SPORT_TYPE(291),
        BREATH_HOLDING_TRAIN_TYPE(30289),
        BREATH_HOLDING_TRAIN_SPORT_TYPE(288),
        BREATH_HOLDING_TEST_TYPE(30290),
        BREATH_HOLDING_TEST_SPORT_TYPE(ComponentInfo.PluginPay_A_N),
        ADVENTURES_TYPE(30223),
        ADVENTURES_SPORT_TYPE(222),
        SPORT_GOAL_ACHIEVEMENT_DATA(300002),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE(DicDataTypeUtil.c(300002, "stepGoalValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE(DicDataTypeUtil.c(300002, "stepUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE(DicDataTypeUtil.c(300002, "stepGoalState")),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING(DicDataTypeUtil.c(300002, "stepIsRing")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE(DicDataTypeUtil.c(300002, "calorieGoalValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE(DicDataTypeUtil.c(300002, "calorieUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE(DicDataTypeUtil.c(300002, "calorieGoalState")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING(DicDataTypeUtil.c(300002, "calorieIsRing")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE(DicDataTypeUtil.c(300002, "durationGoalValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE(DicDataTypeUtil.c(300002, "durationUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE(DicDataTypeUtil.c(300002, "durationGoalState")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING(DicDataTypeUtil.c(300002, "durationIsRing")),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE(DicDataTypeUtil.c(300002, "activeGoalValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE(DicDataTypeUtil.c(300002, "activeUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE(DicDataTypeUtil.c(300002, "activeGoalState")),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING(DicDataTypeUtil.c(300002, "activeIsRing")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_NEW(DicDataTypeUtil.c(300002, "calorieIsRingNew")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CLIMB_USER_VALUE(DicDataTypeUtil.c(300002, "climbUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DISTANCE_USER_VALUE(DicDataTypeUtil.c(300002, "distanceUserValue")),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_VALUE_STAT(DicDataTypeUtil.e(300002, "stepGoalValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_USER_VALUE_STAT(DicDataTypeUtil.e(300002, "stepUserValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_GOAL_STATE_STAT(DicDataTypeUtil.e(300002, "stepGoalState", "MAX")),
        SPORT_GOAL_ACHIEVEMENT_DATA_STEP_IS_RING_STAT(DicDataTypeUtil.e(300002, "stepIsRing", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_VALUE_STAT(DicDataTypeUtil.e(300002, "calorieGoalValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_USER_VALUE_STAT(DicDataTypeUtil.e(300002, "calorieUserValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_GOAL_STATE_STAT(DicDataTypeUtil.e(300002, "calorieGoalState", "MAX")),
        SPORT_GOAL_ACHIEVEMENT_DATA_CALORIE_IS_RING_STAT(DicDataTypeUtil.e(300002, "calorieIsRingNew", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_VALUE_STAT(DicDataTypeUtil.e(300002, "durationGoalValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_USER_VALUE_STAT(DicDataTypeUtil.e(300002, "durationUserValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_GOAL_STATE_STAT(DicDataTypeUtil.e(300002, "durationGoalState", "MAX")),
        SPORT_GOAL_ACHIEVEMENT_DATA_DURATION_IS_RING_STAT(DicDataTypeUtil.e(300002, "durationIsRing", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_VALUE_STAT(DicDataTypeUtil.e(300002, "activeGoalValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_USER_VALUE_STAT(DicDataTypeUtil.e(300002, "activeUserValue", HealthDataStatPolicy.LAST)),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_GOAL_STATE_STAT(DicDataTypeUtil.e(300002, "activeGoalState", "MAX")),
        SPORT_GOAL_ACHIEVEMENT_DATA_ACTIVE_IS_RING_STAT(DicDataTypeUtil.e(300002, "activeIsRing", HealthDataStatPolicy.LAST)),
        ALTITUDE_MAX_STAT(DicDataTypeUtil.e(AwarenessConstants.ERROR_TIMEOUT_CODE, "altitude", "MAX")),
        ALTITUDE_MIN_STAT(DicDataTypeUtil.e(AwarenessConstants.ERROR_TIMEOUT_CODE, "altitude", "MIN")),
        DAILY_ACTIVITY_RECORD(300003),
        DAILY_ACTIVITY_RECORD_STEPS(DicDataTypeUtil.c(300003, MedalConstants.EVENT_STEPS)),
        DAILY_ACTIVITY_RECORD_CALORIE(DicDataTypeUtil.c(300003, "calorie")),
        DAILY_ACTIVITY_RECORD_DURATION(DicDataTypeUtil.c(300003, "duration")),
        DAILY_ACTIVITY_RECORD_ACTIVE_HOUR(DicDataTypeUtil.c(300003, "activeHour")),
        TODAY_ACTIVITY_RECORD(300004),
        TODAY_ACTIVITY_RECORD_STEPS(DicDataTypeUtil.c(300004, MedalConstants.EVENT_STEPS)),
        TODAY_ACTIVITY_RECORD_ACTIVE_CALORIE(DicDataTypeUtil.c(300004, HwExerciseConstants.JSON_NAME_ACTIVECALORIE)),
        TODAY_ACTIVITY_RECORD_DURATION(DicDataTypeUtil.c(300004, "duration")),
        TODAY_ACTIVITY_RECORD_ACTIVE_HOUR(DicDataTypeUtil.c(300004, "activeHour")),
        TODAY_ACTIVITY_RECORD_DISTANCE(DicDataTypeUtil.c(300004, "distance")),
        TODAY_ACTIVITY_RECORD_CLIMB(DicDataTypeUtil.c(300004, "climb")),
        REALTIME_SPORT_DATA(300001),
        REALTIME_SPORT_DATA_WALK_OR_RUN(DicDataTypeUtil.c(300001, "walkOrRun")),
        REALTIME_SPORT_DATA_STEPS(DicDataTypeUtil.c(300001, MedalConstants.EVENT_STEPS)),
        REALTIME_SPORT_DATA_EVERSION(DicDataTypeUtil.c(300001, "eversion")),
        REALTIME_SPORT_DATA_FORE_STEPS(DicDataTypeUtil.c(300001, "foreSteps")),
        REALTIME_SPORT_DATA_WHOLE_STEPS(DicDataTypeUtil.c(300001, "wholeSteps")),
        REALTIME_SPORT_DATA_HIND_STEPS(DicDataTypeUtil.c(300001, "hindSteps")),
        REALTIME_GROUND_IMPACT_ACCELERATION(DicDataTypeUtil.c(300001, "groundImpactAcceleration")),
        REALTIME_SPORT_VERTICAL_RATIO(DicDataTypeUtil.c(300001, "verticalRatio")),
        REALTIME_SPORT_DATA_ACTIVE_PEAK(DicDataTypeUtil.c(300001, "activePeak")),
        REALTIME_SPORT_DATA_VERTICAL_LOADING_RATE(DicDataTypeUtil.c(300001, "verticalLoadingRate")),
        REALTIME_SPORT_DATA_SPEED(DicDataTypeUtil.c(300001, "speed")),
        REALTIME_SPORT_DATA_STEP_RATE(DicDataTypeUtil.c(300001, "stepRate")),
        REALTIME_SPORT_DATA_STRIDE(DicDataTypeUtil.c(300001, "stride")),
        REALTIME_SPORT_DATA_GROUND_CONTACT_TIME(DicDataTypeUtil.c(300001, "groundContactTime")),
        REALTIME_SPORT_DATA_HANG_TIME(DicDataTypeUtil.c(300001, "hangTime")),
        REALTIME_SPORT_DATA_FLIGHT_RATIO(DicDataTypeUtil.c(300001, "flightRatio")),
        REALTIME_SPORT_DATA_SWING_ANGLE(DicDataTypeUtil.c(300001, "swingAngle")),
        REALTIME_SPORT_DATA_VERTICAL_OSCILLATION(DicDataTypeUtil.c(300001, "verticalOscillation")),
        REALTIME_SPORT_DATA_GC_TIME_BALANCE(DicDataTypeUtil.c(300001, "GCTimeBalance")),
        REALTIME_SPORT_DATA_CADENCE(DicDataTypeUtil.c(300001, "cadence")),
        REALTIME_SPORT_DATA_DELTA_CIRCLE(DicDataTypeUtil.c(300001, "deltaCircle")),
        REALTIME_SPORT_DATA_SUM_CIRCLE(DicDataTypeUtil.c(300001, "sumCircle")),
        BREATH_RATE(500030),
        BREATH_RATE_VALUE(DicDataTypeUtil.c(500030, "breathRate")),
        MAX_BREATH_RATE(DicDataTypeUtil.e(500030, "breathRate", "MAX")),
        MIN_BREATH_RATE(DicDataTypeUtil.e(500030, "breathRate", "MIN")),
        AVG_BREATH_RATE(DicDataTypeUtil.e(500030, "breathRate", HealthDataStatPolicy.AVG)),
        HEART_RATE_VARIABILITY(500044),
        HEART_RATE_VARIABILITY_RMSSD(DicDataTypeUtil.c(500044, "heartRateVariabilityRMSSD")),
        HEART_RATE_VARIABILITY_RMSSD_MAX(DicDataTypeUtil.e(500044, "maxHrv", "MAX")),
        HEART_RATE_VARIABILITY_RMSSD_MIN(DicDataTypeUtil.e(500044, "minHrv", "MIN")),
        HEART_RATE_VARIABILITY_RMSSD_LAST(DicDataTypeUtil.e(500044, "lastHrv", HealthDataStatPolicy.LAST));

        private final int mDataType;

        DataType(int i) {
            this.mDataType = i;
        }

        public int value() {
            return this.mDataType;
        }
    }

    public static class BodyTemperature {
        private BodyTemperature() {
        }
    }

    public static class SkinTemperature {
        private SkinTemperature() {
        }
    }

    public static class EnvironmentTemperature {
        private EnvironmentTemperature() {
        }
    }

    public static class Emotion {
        private Emotion() {
        }
    }

    public static class OvaryHealthDailyStatus {
        private OvaryHealthDailyStatus() {
        }
    }

    public static class OvaryHealthResult {
        private OvaryHealthResult() {
        }
    }

    public static class HighBodyTemperatureAlarm {
        private HighBodyTemperatureAlarm() {
        }
    }

    public static class LowBodyTemperatureAlarm {
        private LowBodyTemperatureAlarm() {
        }
    }

    public static class LowSkinTemperatureAlarm {
        private LowSkinTemperatureAlarm() {
        }
    }

    public static class SuspectedHighTemperatureAlarm {
        private SuspectedHighTemperatureAlarm() {
        }
    }

    public static class SuspectedHighTemperature {
        private SuspectedHighTemperature() {
        }
    }

    public static class ContinueBloodsugar {
        private ContinueBloodsugar() {
        }
    }

    public static class UrgentHypoglycemia {
        private UrgentHypoglycemia() {
        }
    }

    public static class ApproachingHypoglycemia {
        private ApproachingHypoglycemia() {
        }
    }

    public static class Hypoglycemia {
        private Hypoglycemia() {
        }
    }

    public static class Hyperglycemia {
        private Hyperglycemia() {
        }
    }

    public static class BloodsugarRise {
        private BloodsugarRise() {
        }
    }

    public static class BloodsugarDecrease {
        private BloodsugarDecrease() {
        }
    }

    public static class BloodGlucoseTrend {
        private BloodGlucoseTrend() {
        }
    }

    public static class BloodPressureRiskResearchResult {
        private BloodPressureRiskResearchResult() {
        }
    }

    public static class BloodPressureRiskResult {
        private BloodPressureRiskResult() {
        }
    }

    public static class BreathTrain {
        private BreathTrain() {
        }
    }

    public static class LightFasting {
        private LightFasting() {
        }
    }

    public static class DietRecord {
        private DietRecord() {
        }
    }

    public static class BloodPressure {
        private BloodPressure() {
        }
    }

    public static class ObstructiveSleepApnea {
        private ObstructiveSleepApnea() {
        }
    }

    public static class FattyLiver {
        private FattyLiver() {
        }
    }

    public static class FastingLitePhase {
        private FastingLitePhase() {
        }
    }

    static class MedicationRule {
        private MedicationRule() {
        }
    }

    static class MedicationPunching {
        private MedicationPunching() {
        }
    }

    static class LakeLouiseAmsScore {
        private LakeLouiseAmsScore() {
        }
    }

    static class PhysiologicalCycle {
        private PhysiologicalCycle() {
        }
    }

    public static class BodyShape {
        private BodyShape() {
        }
    }

    public static class WeightBodyfatBroad {
        private WeightBodyfatBroad() {
        }
    }

    static class ArrhythmiaResult {
        private ArrhythmiaResult() {
        }
    }

    public static class SleepOnOffBedRecord {
        private SleepOnOffBedRecord() {
        }
    }

    static class Altitude {
        private Altitude() {
        }
    }

    static class PhysiologicalCycleBusiness {
        private PhysiologicalCycleBusiness() {
        }
    }

    static class RestingMetabolism {
        private RestingMetabolism() {
        }
    }

    static class CurrentBasalMetabolism {
        private CurrentBasalMetabolism() {
        }
    }

    static class BasalMetabolismAfterExercise {
        private BasalMetabolismAfterExercise() {
        }
    }

    static class DrinkWater {
        private DrinkWater() {
        }
    }

    static class ActiveHour {
        private ActiveHour() {
        }
    }

    public static class EnvironmentNoise {
        private EnvironmentNoise() {
        }
    }

    public static class ArrhythmiaPpg {
        private ArrhythmiaPpg() {
        }
    }

    public static class DynamicBpReport {
        private DynamicBpReport() {
        }
    }

    public static class CourseRecord {
        private CourseRecord() {
        }
    }

    public static class MedicalExamReport {
        private MedicalExamReport() {
        }
    }

    public static class Mindfulness {
        private Mindfulness() {
        }
    }

    public static class VentilatorRecord {
        private VentilatorRecord() {
        }
    }

    public static class Electrocardiogram {
        private Electrocardiogram() {
        }
    }

    public static class SleepDetails {
        private SleepDetails() {
        }
    }

    public static class GolfCourseModel {
        private GolfCourseModel() {
        }
    }

    public static class SleepRecord {
        private SleepRecord() {
        }
    }

    public static class VascularHealth {
        private VascularHealth() {
        }
    }

    public static class VascularHealthResult {
        private VascularHealthResult() {
        }
    }

    public static class SportBloodPressureResult {
        private SportBloodPressureResult() {
        }
    }

    public static class MonthlySleep {
        private MonthlySleep() {
        }
    }

    public static class BgDailyResult {
        private BgDailyResult() {
        }
    }

    public static class BgDailySlpResult {
        private BgDailySlpResult() {
        }
    }

    public static class BgRiskGroupResult {
        private BgRiskGroupResult() {
        }
    }

    public static class PpgOfVascularHealth {
        private PpgOfVascularHealth() {
        }
    }

    public static class EcgOfVascularHealth {
        private EcgOfVascularHealth() {
        }
    }

    public static class Spo2RespInfection {
        private Spo2RespInfection() {
        }
    }

    public static class RriRespInfection {
        private RriRespInfection() {
        }
    }

    public static class BgDailyProbWin {
        private BgDailyProbWin() {
        }
    }

    public static class BgCombinedPpgFeature {
        private BgCombinedPpgFeature() {
        }
    }

    public static class MarkPoint {
        private MarkPoint() {
        }
    }

    public static class TemperatureRespInfection {
        private TemperatureRespInfection() {
        }
    }

    public static class SleepFragmentRespInfection {
        private SleepFragmentRespInfection() {
        }
    }

    public static class CntbpOriginPpgData {
        private CntbpOriginPpgData() {
        }
    }

    public static class Diving {
        private Diving() {
        }
    }

    public static class ScubaDiving {
        private ScubaDiving() {
        }
    }

    public static class BreathHoldingTrain {
        private BreathHoldingTrain() {
        }
    }

    public static class SenseSport {
        private SenseSport() {
        }
    }

    public static class BreathHoldingTest {
        private BreathHoldingTest() {
        }
    }

    public static class Adventures {
        private Adventures() {
        }
    }

    public static class RealtimeSportData {
        private RealtimeSportData() {
        }
    }

    public static class SportGoalAchievementData {
        private SportGoalAchievementData() {
        }
    }

    public static class DailyActivityRecord {
        private DailyActivityRecord() {
        }
    }

    public static class TodayActivityRecord {
        private TodayActivityRecord() {
        }
    }

    public static class BreathRate {
        private BreathRate() {
        }
    }

    public static class HeartRateVariability {
        private HeartRateVariability() {
        }
    }

    public static class MedicalExamReportResearch {
        private MedicalExamReportResearch() {
        }
    }
}
