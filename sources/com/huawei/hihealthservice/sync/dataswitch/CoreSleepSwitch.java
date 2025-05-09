package com.huawei.hihealthservice.sync.dataswitch;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleep;
import com.huawei.hwcloudmodel.model.unite.ProfessionalSleepTotal;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.igo;
import defpackage.ikr;
import defpackage.ikv;
import defpackage.iup;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class CoreSleepSwitch {
    private Context b;

    public CoreSleepSwitch(Context context) {
        this.b = context.getApplicationContext();
    }

    public List<igo> e(List<ProfessionalSleepTotal> list, int i) {
        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<ProfessionalSleepTotal> it = list.iterator();
        while (it.hasNext()) {
            List<igo> e = e(it.next(), i);
            if (e != null && !e.isEmpty()) {
                arrayList.addAll(e);
            }
        }
        return arrayList;
    }

    public List<igo> e(ProfessionalSleepTotal professionalSleepTotal, int i) {
        if (professionalSleepTotal.getDeviceCode() != 0) {
            LogUtil.h("Debug_CoreSleepSwitch", "the sportTotal's deviceCode should be 0, deviceCode is ", Long.valueOf(professionalSleepTotal.getDeviceCode()));
            return new ArrayList();
        }
        ikv a2 = ikr.b(this.b).a(0, i, 0);
        if (a2 == null) {
            return new ArrayList();
        }
        int recordDay = professionalSleepTotal.getRecordDay();
        List<igo> d = d(professionalSleepTotal.getProfessionalSleep(), recordDay, professionalSleepTotal.getGenerateTime());
        if (d == null || d.isEmpty()) {
            return new ArrayList();
        }
        int b = a2.b();
        String timeZone = professionalSleepTotal.getTimeZone();
        for (igo igoVar : d) {
            igoVar.b(b);
            igoVar.b(timeZone);
            igoVar.e(recordDay);
            igoVar.g(1);
            igoVar.a(professionalSleepTotal.getGenerateTime());
            igoVar.c(22100);
        }
        return d;
    }

    private List<igo> d(ProfessionalSleep professionalSleep, int i, long j) {
        if (professionalSleep == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(44);
        if (HwExerciseConstants.TEN_DAY_SECOND > System.currentTimeMillis() - HiDateUtil.a(i)) {
            ReleaseLogUtil.e("HiH_CoreSleepSwitch", "recordday=", Integer.valueOf(i), "[", Long.valueOf(j), "]:", professionalSleep);
        }
        b(arrayList, professionalSleep.getFallAsleepTime(), 44201, 5);
        b(arrayList, professionalSleep.getWakeupTime(), 44202, 5);
        b(arrayList, professionalSleep.getSleepScore(), 44203, 17);
        b(arrayList, professionalSleep.getSleepLatency(), 44204, 15);
        b(arrayList, professionalSleep.getGoBedTime(), 44205, 5);
        b(arrayList, professionalSleep.getValidData(), 44206, 0);
        b(arrayList, professionalSleep.getSleepEfficiency(), 44207, 18);
        b(arrayList, professionalSleep.getLightSleepTime(), 44103, 15);
        b(arrayList, professionalSleep.getDeepSleepTime(), 44102, 15);
        b(arrayList, professionalSleep.getDreamTime(), 44101, 15);
        b(arrayList, professionalSleep.getAwakeTime(), 44104, 15);
        b(arrayList, professionalSleep.getAllSleepTime(), 44105, 15);
        b(arrayList, professionalSleep.getWakeUpCount(), 44107, 16);
        b(arrayList, professionalSleep.getDeepSleepPart(), 44106, 16);
        b(arrayList, professionalSleep.getSnoreFrequency(), 44208, 0);
        b(arrayList, professionalSleep.getDaySleepTime(), 44108, 15);
        b(arrayList, professionalSleep.getBedTime(), 44109, 15);
        b(arrayList, professionalSleep.getDeviceCategory(), 44110, 0);
        c(professionalSleep, arrayList);
        e(professionalSleep, arrayList);
        return arrayList;
    }

    private static void c(ProfessionalSleep professionalSleep, List<igo> list) {
        b(list, professionalSleep.getMinHeartrate(), 44209, 0);
        b(list, professionalSleep.getMaxHeartrate(), 44210, 0);
        b(list, professionalSleep.getMinOxygenSaturation(), 44211, 0);
        b(list, professionalSleep.getMaxOxygenSaturation(), 44212, 0);
        b(list, professionalSleep.getMinBreathrate(), 44213, 0);
        b(list, professionalSleep.getMaxBreathrate(), 44214, 0);
        b(list, professionalSleep.getNewSleepLatency(), 44218, 0);
        b(list, professionalSleep.getWakeUpFeeling(), 44215, 0);
        b(list, professionalSleep.getRisingTime(), 44216, 0);
        b(list, professionalSleep.getDeviceBedTime(), 44217, 0);
    }

    private void e(ProfessionalSleep professionalSleep, List<igo> list) {
        e(list, professionalSleep.getLastAvgHeartrate(), 44219, 8);
        e(list, professionalSleep.getLastMinHeartrateBaseline(), 44220, 8);
        e(list, professionalSleep.getLastMaxHeartrateBaseline(), 44221, 8);
        e(list, professionalSleep.getLastHeartrateDayToBaseline(), 44222, 0);
        e(list, professionalSleep.getLastAvgSpO2(), 44223, 0);
        e(list, professionalSleep.getLastMinSpO2Baseline(), 44224, 0);
        e(list, professionalSleep.getLastMaxSpO2Baseline(), 44225, 0);
        e(list, professionalSleep.getLastSpO2DayToBaseline(), 44226, 0);
        e(list, professionalSleep.getLastAvgBreathrate(), 44227, 0);
        e(list, professionalSleep.getLastMinBreathrateBaseline(), 44228, 0);
        e(list, professionalSleep.getLastMaxBreathrateBaseline(), 44229, 0);
        e(list, professionalSleep.getLastBreathrateDayToBaseline(), 44230, 0);
        e(list, professionalSleep.getLastAvgHrv(), 44231, 0);
        e(list, professionalSleep.getLastMinHrvBaseline(), 44232, 0);
        e(list, professionalSleep.getLastMaxHrvBaseline(), 44233, 0);
        e(list, professionalSleep.getLastHrvDayToBaseline(), 44234, 0);
    }

    private static void e(List<igo> list, Integer num, int i, int i2) {
        if (num != null) {
            b(list, num.intValue(), i, i2);
        }
    }

    private static void b(List<igo> list, double d, int i, int i2) {
        if (d >= 0.0d) {
            list.add(iup.d(i, d, i2));
        }
    }

    public List<ProfessionalSleepTotal> d(List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            ProfessionalSleepTotal professionalSleepTotal = new ProfessionalSleepTotal();
            professionalSleepTotal.setTimeZone(hiHealthData.getTimeZone());
            professionalSleepTotal.setRecordDay(HiDateUtil.c(hiHealthData.getStartTime()));
            professionalSleepTotal.setDataSource(2);
            professionalSleepTotal.setDeviceCode(0L);
            professionalSleepTotal.setGenerateTime(hiHealthData.getLong("modified_time"));
            ProfessionalSleep a2 = a(hiHealthData);
            if (a2 != null) {
                professionalSleepTotal.setProfessionalSleep(a2);
                arrayList.add(professionalSleepTotal);
            }
        }
        return arrayList;
    }

    private ProfessionalSleep a(HiHealthData hiHealthData) {
        ProfessionalSleep professionalSleep = new ProfessionalSleep();
        professionalSleep.setFallAsleepTime(hiHealthData.getLong("stat_out_core_sleep_fall_time"));
        professionalSleep.setWakeupTime(hiHealthData.getLong("stat_out_core_sleep_wake_up_time"));
        professionalSleep.setSleepScore(hiHealthData.getInt("stat_out_core_sleep_score"));
        professionalSleep.setSleepLatency(hiHealthData.getInt("stat_out_core_sleep_latency"));
        professionalSleep.setGoBedTime(hiHealthData.getLong("stat_out_core_sleep_go_bed_time"));
        professionalSleep.setValidData(hiHealthData.getFloat("stat_out_core_sleep_valid_data"));
        professionalSleep.setSleepEfficiency(hiHealthData.getInt("stat_out_core_sleep_efficiency"));
        professionalSleep.setLightSleepTime(hiHealthData.getInt("stat_core_sleep_shallow_duration"));
        professionalSleep.setDeepSleepTime(hiHealthData.getInt("stat_core_sleep_deep_duration"));
        professionalSleep.setDreamTime(hiHealthData.getInt("stat_core_sleep_dream_duration"));
        professionalSleep.setAwakeTime(hiHealthData.getInt("stat_core_sleep_wake_duration"));
        professionalSleep.setAllSleepTime(hiHealthData.getInt("stat_core_sleep_duration_sum"));
        professionalSleep.setWakeUpCount(hiHealthData.getInt("stat_core_sleep_wake_count"));
        professionalSleep.setDeepSleepPart(hiHealthData.getInt("stat_core_sleep_deep_part_count"));
        professionalSleep.setSnoreFrequency(hiHealthData.getInt("stat_out_core_sleep_snore_freq"));
        professionalSleep.setDaySleepTime(hiHealthData.getInt("stat_core_sleep_noon_duration"));
        professionalSleep.setBedTime(hiHealthData.getInt("stat_core_sleep_bed_duration"));
        professionalSleep.setDeviceCategory(hiHealthData.getInt("stat_core_sleep_device_category"));
        professionalSleep.setMinHeartrate(hiHealthData.getInt("stat_out_core_sleep_min_heartrate"));
        professionalSleep.setMaxHeartrate(hiHealthData.getInt("stat_out_core_sleep_max_heartrate"));
        professionalSleep.setMinOxygenSaturation(hiHealthData.getDouble("stat_core_sleep_min_oxygen_saturation"));
        professionalSleep.setMaxOxygenSaturation(hiHealthData.getDouble("stat_core_sleep_max_oxygen_saturation"));
        professionalSleep.setMinBreathrate(hiHealthData.getDouble("stat_out_core_sleep_min_breathrate"));
        professionalSleep.setMaxBreathrate(hiHealthData.getDouble("stat_out_core_sleep_max_breathrate"));
        professionalSleep.setWakeUpFeeling(hiHealthData.getInt("stat_out_core_sleep_wakeup_feeling"));
        professionalSleep.setRisingTime(hiHealthData.getLong("stat_out_core_sleep_rising_time"));
        professionalSleep.setDeviceBedTime(hiHealthData.getInt("stat_out_core_sleep_device_bed_time"));
        professionalSleep.setNewSleepLatency(hiHealthData.getInt("stat_out_core_sleep_new_latency"));
        a(hiHealthData, professionalSleep);
        return professionalSleep;
    }

    private static void a(HiHealthData hiHealthData, ProfessionalSleep professionalSleep) {
        if (hiHealthData.containsKey("lastAvgHeartrate")) {
            professionalSleep.setLastAvgHeartrate(Integer.valueOf(hiHealthData.getInt("lastAvgHeartrate")));
        }
        if (hiHealthData.containsKey("lastMinHeartrateBaseline")) {
            professionalSleep.setLastMinHeartrateBaseline(Integer.valueOf(hiHealthData.getInt("lastMinHeartrateBaseline")));
        }
        if (hiHealthData.containsKey("lastMaxHeartrateBaseline")) {
            professionalSleep.setLastMaxHeartrateBaseline(Integer.valueOf(hiHealthData.getInt("lastMaxHeartrateBaseline")));
        }
        if (hiHealthData.containsKey("lastHeartrateDayToBaseline")) {
            professionalSleep.setLastHeartrateDayToBaseline(Integer.valueOf(hiHealthData.getInt("lastHeartrateDayToBaseline")));
        }
        if (hiHealthData.containsKey("lastAvgSpO2")) {
            professionalSleep.setLastAvgSpO2(Integer.valueOf(hiHealthData.getInt("lastAvgSpO2")));
        }
        if (hiHealthData.containsKey("lastMinSpO2Baseline")) {
            professionalSleep.setLastMinSpO2Baseline(Integer.valueOf(hiHealthData.getInt("lastMinSpO2Baseline")));
        }
        if (hiHealthData.containsKey("lastMaxSpO2Baseline")) {
            professionalSleep.setLastMaxSpO2Baseline(Integer.valueOf(hiHealthData.getInt("lastMaxSpO2Baseline")));
        }
        if (hiHealthData.containsKey("lastSpO2DayToBaseline")) {
            professionalSleep.setLastSpO2DayToBaseline(Integer.valueOf(hiHealthData.getInt("lastSpO2DayToBaseline")));
        }
        if (hiHealthData.containsKey("lastAvgBreathrate")) {
            professionalSleep.setLastAvgBreathrate(Integer.valueOf(hiHealthData.getInt("lastAvgBreathrate")));
        }
        if (hiHealthData.containsKey("lastMinBreathrateBaseline")) {
            professionalSleep.setLastMinBreathrateBaseline(Integer.valueOf(hiHealthData.getInt("lastMinBreathrateBaseline")));
        }
        if (hiHealthData.containsKey("lastMaxBreathrateBaseline")) {
            professionalSleep.setLastMaxBreathrateBaseline(Integer.valueOf(hiHealthData.getInt("lastMaxBreathrateBaseline")));
        }
        if (hiHealthData.containsKey("lastBreathrateDayToBaseline")) {
            professionalSleep.setLastBreathrateDayToBaseline(Integer.valueOf(hiHealthData.getInt("lastBreathrateDayToBaseline")));
        }
        if (hiHealthData.containsKey("lastAvgHrv")) {
            professionalSleep.setLastAvgHrv(Integer.valueOf(hiHealthData.getInt("lastAvgHrv")));
        }
        if (hiHealthData.containsKey("lastMinHrvBaseline")) {
            professionalSleep.setLastMinHrvBaseline(Integer.valueOf(hiHealthData.getInt("lastMinHrvBaseline")));
        }
        if (hiHealthData.containsKey("lastMaxHrvBaseline")) {
            professionalSleep.setLastMaxHrvBaseline(Integer.valueOf(hiHealthData.getInt("lastMaxHrvBaseline")));
        }
        if (hiHealthData.containsKey("lastHrvDayToBaseline")) {
            professionalSleep.setLastHrvDayToBaseline(Integer.valueOf(hiHealthData.getInt("lastHrvDayToBaseline")));
        }
    }
}
