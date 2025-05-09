package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import com.huawei.pluginachievement.report.bean.ReportDataBean;
import com.huawei.pluginachievement.report.bean.ReportFitnessData;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mew {
    private boolean d;
    private Map<Long, RecentMonthRecordFromDB> e;

    public mew(boolean z, Map<Long, RecentMonthRecordFromDB> map) {
        this.d = z;
        this.e = map;
    }

    public void b(ArrayList<AnnualReportFitnessRaw> arrayList, Map<Integer, List<HiHealthData>> map) {
        CountDownLatch countDownLatch = null;
        if (map != null) {
            if (map.containsKey(1004)) {
                countDownLatch = new CountDownLatch(1);
                mfj.d((Task<Void>) Tasks.callInBackground(c(map.get(1004), 2147483646)), countDownLatch);
            }
            for (Map.Entry<Integer, List<HiHealthData>> entry : map.entrySet()) {
                switch (entry.getKey().intValue()) {
                    case 1000:
                        d(entry.getValue());
                        break;
                    case 1001:
                        b(entry.getValue());
                        break;
                    case 1002:
                        c(2, entry.getValue());
                        break;
                    case 1003:
                        b(entry.getValue(), Integer.MAX_VALUE);
                        break;
                }
            }
        }
        if (arrayList != null) {
            c(arrayList);
        }
        if (countDownLatch != null) {
            mfj.e(countDownLatch, 16000L, "dealMonthSportTask");
        }
    }

    private Callable<Void> c(final List<HiHealthData> list, final int i) {
        return new Callable<Void>() { // from class: mew.1
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mew.this.b((List<HiHealthData>) list, i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, int i) {
        HiTrackMetaData b;
        long c = mkx.c(System.currentTimeMillis(), 1);
        for (int i2 = 0; i2 < list.size(); i2++) {
            HiHealthData hiHealthData = list.get(i2);
            if (hiHealthData.getStartTime() < c && (b = mfj.b(hiHealthData)) != null) {
                mfj.e(i, a(hiHealthData.getStartTime()).mReportDataMap, b, hiHealthData.getStartTime(), hiHealthData.getEndTime());
            }
        }
    }

    private void b(List<HiHealthData> list) {
        long c = mkx.c(System.currentTimeMillis(), 1);
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getLong("start_time") < c) {
                mfj.b(a(hiHealthData.getStartTime()).mReportDataMap, hiHealthData);
            }
        }
    }

    private void d(List<HiHealthData> list) {
        long c = mkx.c(System.currentTimeMillis(), 1);
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getLong("start_time") < c) {
                RecentMonthRecordFromDB a2 = a(hiHealthData.getStartTime());
                long j = hiHealthData.getLong("Track_Duration_Sum");
                a2.setSumAllTime(a2.acquireSumAllTime() + j);
                a2.setSumCalorie(a2.acquireSumCalorie() + hiHealthData.getFloat("sport_calorie_sum"));
                a2.setSumAllCount((a2.acquireSumAllCount() + hiHealthData.getInt("Track_Count_Sum")) - hiHealthData.getInt("Track_Abnormal_Count_Sum"));
                if (hiHealthData.getInt("sport_calorie_sum") > 0) {
                    a2.acquireSportDays().put(Long.valueOf(HiDateUtil.t(hiHealthData.getLong("start_time"))), 1);
                }
                a2.setSumCalcCalorie(a2.acquireSumCalcCalorie() + mfj.c(hiHealthData));
                b(hiHealthData.getLong("start_time"), hiHealthData.getFloat("sport_calorie_sum"), j, a2);
                mfj.c(a2.mReportDataMap, hiHealthData);
            }
        }
    }

    private void c(ArrayList<AnnualReportFitnessRaw> arrayList) {
        long c = mkx.c(System.currentTimeMillis(), 1);
        for (int i = 0; i < arrayList.size(); i++) {
            AnnualReportFitnessRaw annualReportFitnessRaw = arrayList.get(i);
            if (annualReportFitnessRaw.acquireExerciseTime() < c) {
                RecentMonthRecordFromDB a2 = a(annualReportFitnessRaw.acquireExerciseTime());
                ReportDataBean a3 = mfj.a(a2.mReportDataMap, 10001);
                if (a3 instanceof ReportFitnessData) {
                    ReportFitnessData reportFitnessData = (ReportFitnessData) a3;
                    String str = annualReportFitnessRaw.acquireWorkoutId() + "|" + annualReportFitnessRaw.acquireExerciseTime();
                    if (!reportFitnessData.getRecordList().contains(str)) {
                        reportFitnessData.getRecordList().add(str);
                        mfj.e(reportFitnessData, annualReportFitnessRaw);
                    }
                }
                a2.setSumAllCount(a2.acquireSumAllCount() + 1);
                long acquireDuring = annualReportFitnessRaw.acquireDuring();
                a2.setSumAllTime(a2.acquireSumAllTime() + acquireDuring);
                b(annualReportFitnessRaw.acquireExerciseTime(), 0.0f, acquireDuring, a2);
            }
        }
    }

    private void b(long j, float f, long j2, RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (recentMonthRecordFromDB.acquireWeekTime().size() == 0) {
            a(recentMonthRecordFromDB);
        }
        long max = Math.max(mkx.e(j, 1, this.d), recentMonthRecordFromDB.acquireFirstday());
        recentMonthRecordFromDB.acquireWeekTime().put(Long.valueOf(max), Long.valueOf(j2 + recentMonthRecordFromDB.acquireWeekTime().get(Long.valueOf(max)).longValue()));
        recentMonthRecordFromDB.acquireWeekCalorie().put(Long.valueOf(max), Float.valueOf(f + recentMonthRecordFromDB.acquireWeekCalorie().get(Long.valueOf(max)).floatValue()));
    }

    private void a(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        long acquireFirstday = recentMonthRecordFromDB.acquireFirstday();
        recentMonthRecordFromDB.acquireWeekTime().put(Long.valueOf(acquireFirstday), 0L);
        Map<Long, Float> acquireWeekCalorie = recentMonthRecordFromDB.acquireWeekCalorie();
        Float valueOf = Float.valueOf(0.0f);
        acquireWeekCalorie.put(Long.valueOf(acquireFirstday), valueOf);
        long e = mkx.e(recentMonthRecordFromDB.acquireLastDay(), 1, this.d);
        while (e > acquireFirstday) {
            recentMonthRecordFromDB.acquireWeekTime().put(Long.valueOf(e), 0L);
            recentMonthRecordFromDB.acquireWeekCalorie().put(Long.valueOf(e), valueOf);
            e = mkx.d(1, 1, e, this.d);
        }
    }

    private RecentMonthRecordFromDB a(long j) {
        long c = mkx.c(j, 1);
        long c2 = mkx.c(j, 2);
        RecentMonthRecordFromDB recentMonthRecordFromDB = this.e.get(Long.valueOf(c));
        if (recentMonthRecordFromDB != null) {
            return recentMonthRecordFromDB;
        }
        RecentMonthRecordFromDB recentMonthRecordFromDB2 = new RecentMonthRecordFromDB();
        recentMonthRecordFromDB2.setFirstday(c);
        recentMonthRecordFromDB2.setLastDay(c2);
        this.e.put(Long.valueOf(c), recentMonthRecordFromDB2);
        return recentMonthRecordFromDB2;
    }

    private void c(int i, List<HiHealthData> list) {
        LogUtil.a("PLGACHIEVE_AchieveReportMonthData", "Enter dataClassification.");
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        long c = mkx.c(System.currentTimeMillis(), 1);
        LogUtil.a("PLGACHIEVE_AchieveReportMonthData", "AchieveReport_dataClassification currentStartTime ", Long.valueOf(c));
        int i2 = 0;
        while (i2 < arrayList.size()) {
            if (arrayList.get(i2).getLong("start_time") >= c) {
                arrayList.remove(i2);
                i2--;
            }
            i2++;
        }
        if (arrayList.size() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveReportMonthData", "classificationList size == 0");
        } else {
            a(i, mkx.c(arrayList.get(0).getLong("start_time"), 1), mkx.c(arrayList.get(0).getLong("start_time"), 2), arrayList);
        }
    }

    private void a(int i, long j, long j2, List<HiHealthData> list) {
        int i2;
        boolean z;
        int i3;
        int i4;
        LinkedHashMap linkedHashMap;
        LogUtil.a("PLGACHIEVE_AchieveReportMonthData", "AchieveReport_dataClassification startTime", Long.valueOf(j), " endTime", Long.valueOf(j2));
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(list.size());
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(list.size());
        HashMap<String, Integer> hashMap = new HashMap<>(8);
        mfj.a(hashMap, i, list.get(0).getInt("sport_walk_step_sum"));
        long j3 = j2;
        LinkedHashMap linkedHashMap4 = linkedHashMap2;
        LinkedHashMap linkedHashMap5 = linkedHashMap3;
        int i5 = 0;
        long j4 = j;
        while (i5 < list.size()) {
            HiHealthData hiHealthData = list.get(i5);
            if (hiHealthData.getLong("start_time") <= j3 && hiHealthData.getLong("start_time") >= j4) {
                linkedHashMap4.put(Long.valueOf(hiHealthData.getLong("start_time")), Integer.valueOf(hiHealthData.getInt("Track_Run_Distance_Sum")));
                linkedHashMap5.put(Long.valueOf(hiHealthData.getLong("start_time")), Integer.valueOf(hiHealthData.getInt("sport_walk_step_sum")));
                mfj.e(hashMap, hiHealthData);
                if (i5 == list.size() - 1) {
                    mfj.e(hashMap);
                    i2 = 1;
                    i4 = i5;
                    linkedHashMap = linkedHashMap5;
                    a(j4, hashMap, linkedHashMap4, linkedHashMap5);
                } else {
                    i2 = 1;
                    i4 = i5;
                    linkedHashMap = linkedHashMap5;
                }
                i3 = i4;
                linkedHashMap5 = linkedHashMap;
                z = false;
            } else {
                i2 = 1;
                mfj.e(hashMap);
                a(j4, hashMap, linkedHashMap4, linkedHashMap5);
                z = false;
                mfj.a(hashMap, i, 0);
                LinkedHashMap linkedHashMap6 = new LinkedHashMap(0);
                LinkedHashMap linkedHashMap7 = new LinkedHashMap(0);
                j4 = mkx.d(-1, j4, 1);
                linkedHashMap4 = linkedHashMap6;
                j3 = mkx.d(-1, j3, 2);
                i3 = i5 - 1;
                linkedHashMap5 = linkedHashMap7;
            }
            i5 = i3 + i2;
        }
    }

    private void a(long j, HashMap<String, Integer> hashMap, Map<Long, Integer> map, Map<Long, Integer> map2) {
        RecentMonthRecordFromDB a2 = a(j);
        a2.setSumTime(hashMap.get("sumDuration").intValue());
        a2.setOneMonthDis(map);
        a2.setSumDis(hashMap.get("sumDis").intValue());
        a2.setSumCount(hashMap.get("sumCount").intValue());
        a2.setDisBeatPercent(mdn.e(hashMap.get("sumDis").intValue() / 1000.0d, 1));
        a2.setAvgSteps(hashMap.get("avgSteps").intValue());
        a2.setMaxSteps(hashMap.get(ParsedFieldTag.MAX_STEPS).intValue());
        a2.setSumSteps(hashMap.get("sumSteps").intValue());
        a2.setOneMonthSteps(map2);
        a2.setStepBeatPercent(mdn.e(hashMap.get("sumSteps").intValue(), mkx.e(j)));
    }
}
