package defpackage;

import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
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
public class mfa {

    /* renamed from: a, reason: collision with root package name */
    private boolean f14947a;
    private Map<Long, RecentWeekRecordFromDB> d;

    public mfa(boolean z, Map<Long, RecentWeekRecordFromDB> map) {
        this.f14947a = z;
        this.d = map;
    }

    public void d(ArrayList<AnnualReportFitnessRaw> arrayList, Map<Integer, List<HiHealthData>> map) {
        CountDownLatch countDownLatch = null;
        if (map != null) {
            if (map.containsKey(1004)) {
                countDownLatch = new CountDownLatch(1);
                mfj.d((Task<Void>) Tasks.callInBackground(b(map.get(1004), 2147483646)), countDownLatch);
            }
            for (Map.Entry<Integer, List<HiHealthData>> entry : map.entrySet()) {
                switch (entry.getKey().intValue()) {
                    case 1000:
                        d(entry.getValue());
                        break;
                    case 1001:
                        e(entry.getValue());
                        break;
                    case 1002:
                        b(1, entry.getValue());
                        break;
                    case 1003:
                        c(entry.getValue(), Integer.MAX_VALUE);
                        break;
                }
            }
        }
        if (arrayList != null) {
            a(arrayList);
        }
        if (countDownLatch != null) {
            mfj.e(countDownLatch, 16000L, "dealWeekSportTask");
        }
    }

    private Callable<Void> b(final List<HiHealthData> list, final int i) {
        return new Callable<Void>() { // from class: mfa.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mfa.this.c(list, i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, int i) {
        HiTrackMetaData b;
        long e = mkx.e(System.currentTimeMillis(), 1, this.f14947a);
        for (int i2 = 0; i2 < list.size(); i2++) {
            HiHealthData hiHealthData = list.get(i2);
            if (hiHealthData.getStartTime() < e && (b = mfj.b(hiHealthData)) != null) {
                mfj.e(i, a(hiHealthData.getStartTime()).mReportDataMap, b, hiHealthData.getStartTime(), hiHealthData.getEndTime());
            }
        }
    }

    private void e(List<HiHealthData> list) {
        long e = mkx.e(System.currentTimeMillis(), 1, this.f14947a);
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getLong("start_time") < e) {
                mfj.b(a(hiHealthData.getLong("start_time")).mReportDataMap, hiHealthData);
            }
        }
    }

    private void d(List<HiHealthData> list) {
        long e = mkx.e(System.currentTimeMillis(), 1, this.f14947a);
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData.getLong("start_time") < e) {
                RecentWeekRecordFromDB a2 = a(hiHealthData.getLong("start_time"));
                long j = hiHealthData.getInt("Track_Duration_Sum");
                a2.setSumAllTime(a2.acquireSumAllTime() + j);
                a2.setSumCalorie(a2.acquireSumCalorie() + hiHealthData.getInt("sport_calorie_sum"));
                a2.setSumAllCount((a2.acquireSumAllCount() + hiHealthData.getInt("Track_Count_Sum")) - hiHealthData.getInt("Track_Abnormal_Count_Sum"));
                a2.acquireSevenDayTime().put(Long.valueOf(hiHealthData.getLong("start_time")), Long.valueOf(j));
                if (hiHealthData.getInt("sport_calorie_sum") > 0) {
                    a2.acquireSportDays().put(Long.valueOf(HiDateUtil.t(hiHealthData.getLong("start_time"))), 1);
                }
                mfj.c(a2.mReportDataMap, hiHealthData);
            }
        }
    }

    private void a(ArrayList<AnnualReportFitnessRaw> arrayList) {
        long e = mkx.e(System.currentTimeMillis(), 1, this.f14947a);
        for (int i = 0; i < arrayList.size(); i++) {
            AnnualReportFitnessRaw annualReportFitnessRaw = arrayList.get(i);
            if (annualReportFitnessRaw.acquireExerciseTime() < e) {
                RecentWeekRecordFromDB a2 = a(annualReportFitnessRaw.acquireExerciseTime());
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
                long t = HiDateUtil.t(annualReportFitnessRaw.acquireExerciseTime());
                Long l = a2.acquireSevenDayTime().get(Long.valueOf(t));
                if (l == null) {
                    a2.acquireSevenDayTime().put(Long.valueOf(t), Long.valueOf(acquireDuring));
                } else {
                    a2.acquireSevenDayTime().put(Long.valueOf(t), Long.valueOf(acquireDuring + l.longValue()));
                }
            }
        }
    }

    private RecentWeekRecordFromDB a(long j) {
        long e = mkx.e(j, 1, this.f14947a);
        long e2 = mkx.e(j, 2, this.f14947a);
        RecentWeekRecordFromDB recentWeekRecordFromDB = this.d.get(Long.valueOf(e));
        if (recentWeekRecordFromDB != null) {
            return recentWeekRecordFromDB;
        }
        RecentWeekRecordFromDB recentWeekRecordFromDB2 = new RecentWeekRecordFromDB();
        recentWeekRecordFromDB2.setMonday(e);
        recentWeekRecordFromDB2.setSunDay(e2);
        this.d.put(Long.valueOf(e), recentWeekRecordFromDB2);
        return recentWeekRecordFromDB2;
    }

    private void b(int i, List<HiHealthData> list) {
        LogUtil.a("PLGACHIEVE_AchieveReportWeekData", "Enter dataClassification!");
        if (list == null || list.size() <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        long e = mkx.e(System.currentTimeMillis(), 1, this.f14947a);
        LogUtil.a("PLGACHIEVE_AchieveReportWeekData", "AchieveReport_dataClassification currentStartTime", Long.valueOf(e));
        int i2 = 0;
        while (i2 < arrayList.size()) {
            if (arrayList.get(i2).getLong("start_time") >= e) {
                arrayList.remove(i2);
                i2--;
            }
            i2++;
        }
        if (arrayList.size() == 0) {
            LogUtil.a("PLGACHIEVE_AchieveReportWeekData", "classificationList.size()== 0");
        } else {
            a(i, mkx.e(arrayList.get(0).getLong("start_time"), 1, this.f14947a), mkx.e(arrayList.get(0).getLong("start_time"), 2, this.f14947a), arrayList);
        }
    }

    private void a(int i, long j, long j2, List<HiHealthData> list) {
        boolean z;
        int i2;
        int i3;
        int i4;
        LinkedHashMap linkedHashMap;
        LinkedHashMap linkedHashMap2;
        LogUtil.a("PLGACHIEVE_AchieveReportWeekData", "AchieveReport_dataClassification startTime", Long.valueOf(j));
        LogUtil.a("PLGACHIEVE_AchieveReportWeekData", "AchieveReport_dataClassification endTime", Long.valueOf(j2));
        LinkedHashMap linkedHashMap3 = new LinkedHashMap(list.size());
        LinkedHashMap linkedHashMap4 = new LinkedHashMap(list.size());
        HashMap<String, Integer> hashMap = new HashMap<>(8);
        mfj.a(hashMap, i, list.get(0).getInt("sport_walk_step_sum"));
        long j3 = j;
        long j4 = j2;
        LinkedHashMap linkedHashMap5 = linkedHashMap3;
        LinkedHashMap linkedHashMap6 = linkedHashMap4;
        int i5 = 0;
        while (i5 < list.size()) {
            HiHealthData hiHealthData = list.get(i5);
            if (hiHealthData.getLong("start_time") <= j4 && hiHealthData.getLong("start_time") >= j3) {
                linkedHashMap5.put(Long.valueOf(hiHealthData.getLong("start_time")), Integer.valueOf(hiHealthData.getInt("Track_Run_Distance_Sum")));
                linkedHashMap6.put(Long.valueOf(hiHealthData.getLong("start_time")), Integer.valueOf(hiHealthData.getInt("sport_walk_step_sum")));
                mfj.e(hashMap, hiHealthData);
                if (i5 == list.size() - 1) {
                    mfj.e(hashMap);
                    i4 = i5;
                    linkedHashMap = linkedHashMap6;
                    linkedHashMap2 = linkedHashMap5;
                    b(j3, hashMap, linkedHashMap5, linkedHashMap);
                } else {
                    i4 = i5;
                    linkedHashMap = linkedHashMap6;
                    linkedHashMap2 = linkedHashMap5;
                }
                i3 = i4;
                linkedHashMap6 = linkedHashMap;
                linkedHashMap5 = linkedHashMap2;
                z = false;
                i2 = 1;
            } else {
                mfj.e(hashMap);
                b(j3, hashMap, linkedHashMap5, linkedHashMap6);
                z = false;
                mfj.a(hashMap, i, 0);
                linkedHashMap5 = new LinkedHashMap(0);
                linkedHashMap6 = new LinkedHashMap(0);
                i2 = 1;
                j3 = mkx.d(1, 1, j3, this.f14947a);
                j4 = mkx.d(2, 1, j4, this.f14947a);
                i3 = i5 - 1;
            }
            i5 = i3 + i2;
        }
    }

    private void b(long j, HashMap<String, Integer> hashMap, Map<Long, Integer> map, Map<Long, Integer> map2) {
        if (hashMap.get("sumDuration").intValue() > 0 || hashMap.get("sumSteps").intValue() > 0) {
            RecentWeekRecordFromDB a2 = a(j);
            a2.setSumTime(hashMap.get("sumDuration").intValue());
            a2.setSevenDayDis(map);
            a2.setSumDis(hashMap.get("sumDis").intValue());
            a2.setSumCount(hashMap.get("sumCount").intValue());
            a2.setDisBeatPercent(mdn.e(hashMap.get("sumDis").intValue() / 1000.0d, 0));
            a2.setAvgSteps(hashMap.get("avgSteps").intValue());
            a2.setMaxSteps(hashMap.get(ParsedFieldTag.MAX_STEPS).intValue());
            a2.setSumSteps(hashMap.get("sumSteps").intValue());
            a2.setSevenDaySteps(map2);
            a2.setStepBeatPercent(mdn.e(hashMap.get("sumSteps").intValue(), 7));
        }
    }
}
