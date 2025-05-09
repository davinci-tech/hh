package com.huawei.hihealth.data.constant;

import android.content.Context;
import android.util.SparseArray;
import com.huawei.hihealth.dictionary.HiHealthDictManager;
import com.huawei.hihealth.dictionary.model.HiHealthDictField;
import com.huawei.operation.ble.BleConstants;

/* loaded from: classes.dex */
public class HiHealthDataKey {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<String> f4116a;
    private static SparseArray<String[]> d;

    public static class BloodOxygenSaturation {
    }

    public static class BloodPressureSet {
    }

    public static class BloodSugarSet {
    }

    public static class BradycardiaStat {
    }

    public static class ContinueBloodSugar {
    }

    public static class CoreSleepStat {
    }

    public static class ExerciseIntensityStat {
    }

    public static class HeartRate {
    }

    public static class HeartRateRiseStat {
    }

    public static class HiAggregate {
    }

    public static class RealTimeMergeMode {
    }

    public static class Sleep {
    }

    public static class SleepStat {
    }

    public static class Sport {
    }

    public static class Stat {
    }

    public static class StatPpgIrregularHeartBeat {
    }

    public static class StressAndRelaxationKeys {
    }

    public static class TotalBasketballSequenceStat {
    }

    public static class TotalRideSequenceStat {
    }

    public static class TotalRunSequenceStat {
    }

    public static class TotalSequenceStat {
    }

    public static class TotalSwimSequenceStat {
    }

    public static class TotalWalkSequenceStat {
    }

    public static class TrackMetaDataSet {
    }

    public static class UserPreference {
    }

    public static class WeightSet {
    }

    static {
        SparseArray<String> sparseArray = new SparseArray<>();
        f4116a = sparseArray;
        d = new SparseArray<>(1);
        sparseArray.put(20001, "session_type");
        sparseArray.put(2018, BleConstants.BLOODPRESSURE_SPHYGMUS);
        sparseArray.put(2002, BleConstants.BLOODPRESSURE_SPHYGMUS);
        sparseArray.put(2015, "bloodsugar_before_dawn");
        sparseArray.put(2009, "bloodsugar_bf_after");
        sparseArray.put(2008, "bloodsugar_bf_before");
        sparseArray.put(2013, "bloodsugar_dn_after");
        sparseArray.put(2012, "bloodsugar_dn_before");
        sparseArray.put(2010, "bloodsugar_lc_before");
        sparseArray.put(2011, "bloodsugar_lc_after");
        sparseArray.put(2014, "bloodsugar_sl_before");
        sparseArray.put(2106, "bloodsugar_random");
        bwn_(sparseArray);
        d.put(10010, new String[]{"menstrual_status", "menstrual_sub_status", "menstrual_quantity", "menstrual_dysmenorrhea", "menstrual_physical"});
    }

    private HiHealthDataKey() {
    }

    public static String[] d() {
        return new String[]{"sport_walk_step_sum", "sport_run_step_sum", "sport_climb_step_sum", "sport_walk_distance_sum", "sport_run_distance_sum", "sport_cycle_distance_sum", "sport_climb_distance_sum", "sport_swim_distance_sum", "sport_walk_duration_sum", "sport_run_duration_sum", "sport_cycle_duration_sum", "sport_climb_duration_sum", "sport_swim_duration_sum", "sport_walk_calorie_sum", "sport_run_calorie_sum", "sport_cycle_calorie_sum", "sport_climb_calorie_sum", "sport_swim_calorie_sum", "sport_altitude_offset_sum"};
    }

    public static String[] b() {
        return new String[]{"stat_core_sleep_dream_duration", "stat_core_sleep_deep_duration", "stat_core_sleep_shallow_duration", "stat_core_sleep_wake_duration", "stat_core_sleep_duration_sum", "stat_core_sleep_deep_part_count", "stat_core_sleep_wake_count", "stat_core_sleep_noon_duration", "stat_core_sleep_bed_duration", "stat_core_sleep_device_category", "stat_out_core_sleep_fall_time", "stat_out_core_sleep_wake_up_time", "stat_out_core_sleep_score", "stat_out_core_sleep_latency", "stat_out_core_sleep_go_bed_time", "stat_out_core_sleep_valid_data", "stat_out_core_sleep_efficiency", "stat_out_core_sleep_snore_freq", "stat_out_core_sleep_min_heartrate", "stat_out_core_sleep_max_heartrate", "stat_core_sleep_min_oxygen_saturation", "stat_core_sleep_max_oxygen_saturation", "stat_out_core_sleep_min_breathrate", "stat_out_core_sleep_max_breathrate", "stat_out_core_sleep_wakeup_feeling", "stat_out_core_sleep_rising_time", "stat_out_core_sleep_device_bed_time", "stat_out_core_sleep_new_latency", "lastAvgHeartrate", "lastMinHeartrateBaseline", "lastMaxHeartrateBaseline", "lastHeartrateDayToBaseline", "lastAvgSpO2", "lastMinSpO2Baseline", "lastMaxSpO2Baseline", "lastSpO2DayToBaseline", "lastAvgBreathrate", "lastMinBreathrateBaseline", "lastMaxBreathrateBaseline", "lastBreathrateDayToBaseline", "lastAvgHrv", "lastMinHrvBaseline", "lastMaxHrvBaseline", "lastHrvDayToBaseline"};
    }

    public static String[] c() {
        return new String[]{"normal", "premature", "fibrillation", "fibrillation_risk", "premature_risk"};
    }

    private static void bwn_(SparseArray<String> sparseArray) {
        sparseArray.put(47401, "menstrual_status");
        sparseArray.put(47405, "menstrual_sub_status");
        sparseArray.put(47402, "menstrual_quantity");
        sparseArray.put(47403, "menstrual_dysmenorrhea");
        sparseArray.put(47404, "menstrual_physical");
    }

    public static String c(int i) {
        HiHealthDictField b;
        String str = f4116a.get(i);
        return (str == null && (b = HiHealthDictManager.d((Context) null).b(i)) != null) ? b.a() : str;
    }

    public static String[] b(int i) {
        return d.get(i);
    }

    public static String a(String str) {
        return str + "_unit";
    }

    public static class MenstrualSet {
        private MenstrualSet() {
        }
    }
}
