package defpackage;

import android.util.Pair;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.operation.share.HiHealthError;
import com.huawei.pluginachievement.report.bean.AnnualReportFitnessRaw;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class mhn {
    private static final int[] d = {10, 200, 400};

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f15003a = {137, OldToNewMotionPath.SPORT_TYPE_PILATES, OldToNewMotionPath.SPORT_TYPE_AEROBICS, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, HiHealthError.ERR_WRONG_DEVICE, 152, 153, 154, 155, 156, 157, 158, 159, 160, MachineControlPointResponse.OP_CODE_EXTENSION_SET_TOTAL_ENERGY, OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE, OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT};
    private static final String[] b = {"R001", "R002", "N037", "N036"};

    public static Pair<Map<String, Double>, Map<Integer, Double>> cgT_(List<HiHealthData> list, List<AnnualReportFitnessRaw> list2, Map<String, String> map) {
        Pair<Map<String, Double>, Map<Integer, Double>> cgS_ = cgS_(list);
        return new Pair<>(b((Map<String, Double>) cgS_.first, map, list2), (Map) cgS_.second);
    }

    private static Pair<Map<String, Double>, Map<Integer, Double>> cgS_(List<HiHealthData> list) {
        String str = "FitnessSportCalRule";
        LogUtil.a("FitnessSportCalRule", "computeAllSportSum() sportRecords size =", Integer.valueOf(list.size()));
        HashMap hashMap = new HashMap(2);
        HashMap hashMap2 = new HashMap(2);
        Iterator<HiHealthData> it = list.iterator();
        double d2 = 0.0d;
        double d3 = 0.0d;
        double d4 = 0.0d;
        double d5 = 0.0d;
        while (it.hasNext()) {
            HiHealthData next = it.next();
            if (next == null) {
                LogUtil.h(str, "calculateAllSportSum() hiHealthData is null");
            } else {
                int intValue = ((Integer) next.get("hihealth_type")).intValue();
                if (!b(intValue, f15003a)) {
                    LogUtil.h(str, "computeAllSportSum Sport 85, type ", Integer.valueOf(intValue));
                } else {
                    LogUtil.a(str, "computeAllSportSum Sport 26, type ", Integer.valueOf(intValue));
                    double doubleValue = ((Double) next.get("duration_sum")).doubleValue();
                    d2 += doubleValue;
                    String str2 = str;
                    Iterator<HiHealthData> it2 = it;
                    double doubleValue2 = ((Double) next.get("count_sum")).doubleValue();
                    d3 += doubleValue2;
                    b(intValue, doubleValue2, (HashMap<Integer, Double>) hashMap2);
                    if (doubleValue > d5) {
                        d4 = intValue;
                        d5 = doubleValue;
                    }
                    it = it2;
                    str = str2;
                }
            }
        }
        hashMap.put("total_duration", Double.valueOf(d2));
        hashMap.put("exclude_run_course_total_duration", Double.valueOf(d2));
        hashMap.put("total_count", Double.valueOf(d3));
        hashMap.put("favorite_sport_type", Double.valueOf(d4));
        hashMap.put("favorite_sport_total_duration", Double.valueOf((int) Math.round(d5 / 60000.0d)));
        return new Pair<>(hashMap, hashMap2);
    }

    private static void b(int i, double d2, HashMap<Integer, Double> hashMap) {
        Double d3 = hashMap.get(Integer.valueOf(i));
        if (d3 != null) {
            d2 += d3.doubleValue();
        }
        hashMap.put(Integer.valueOf(i), Double.valueOf(d2));
    }

    private static Map<String, Double> b(Map<String, Double> map, Map<String, String> map2, List<AnnualReportFitnessRaw> list) {
        LogUtil.a("FitnessSportCalRule", "computeFitnessSum() fitnessRecords size =", Integer.valueOf(list.size()));
        String str = "total_count";
        double doubleValue = map.get("total_count").doubleValue();
        LogUtil.a("FitnessSportCalRule", "calculateFitnessSum oldSumCount ", Double.valueOf(doubleValue));
        double doubleValue2 = map.get("total_duration").doubleValue();
        double doubleValue3 = map.get("exclude_run_course_total_duration").doubleValue();
        HashSet hashSet = new HashSet(16);
        ArrayList arrayList = new ArrayList(16);
        for (AnnualReportFitnessRaw annualReportFitnessRaw : list) {
            if (annualReportFitnessRaw == null) {
                LogUtil.h("FitnessSportCalRule", "calculateFitnessSum() annualReportFitnessRaw is null");
            } else {
                String str2 = str;
                doubleValue2 += r0;
                hashSet.add(Long.valueOf(HiDateUtil.t(annualReportFitnessRaw.acquireExerciseTime())));
                e(arrayList, annualReportFitnessRaw);
                if (!mlg.b(b, annualReportFitnessRaw.acquireWorkoutId())) {
                    doubleValue3 += r0;
                }
                doubleValue += 1.0d;
                str = str2;
            }
        }
        double round = (int) Math.round(doubleValue2 / 60000.0d);
        double round2 = (int) Math.round(doubleValue3 / 60000.0d);
        LogUtil.h("FitnessSportCalRule", "calculateFitnessSum totalDuration ", Double.valueOf(round), "exRunCourseTotalDuration ", Double.valueOf(round2));
        map.put("total_duration", Double.valueOf(round));
        map.put("exclude_run_course_total_duration", Double.valueOf(round2));
        map.put(str, Double.valueOf(doubleValue));
        map.put("total_Course_Day", Double.valueOf(hashSet.size()));
        map2.put("favorite_Course_Name", e(arrayList));
        return map;
    }

    private static String e(List<b> list) {
        if (koq.b(list)) {
            LogUtil.h("FitnessSportCalRule", "getFavoriteCourseName courseList == null.");
            return "";
        }
        Collections.sort(list);
        String b2 = list.get(0).b();
        LogUtil.a("FitnessSportCalRule", "getFavoriteCourse name ", b2, " courseList ", list.toString());
        return b2;
    }

    private static void e(List<b> list, AnnualReportFitnessRaw annualReportFitnessRaw) {
        b bVar;
        if (list == null) {
            LogUtil.h("FitnessSportCalRule", "getFavoriteCourse courseList == null.");
            return;
        }
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                bVar = null;
                break;
            } else {
                if (list.get(i).f15004a.equals(annualReportFitnessRaw.acquireWorkoutName())) {
                    bVar = list.get(i);
                    break;
                }
                i++;
            }
        }
        if (bVar == null) {
            list.add(new b(annualReportFitnessRaw.acquireWorkoutName(), 1, annualReportFitnessRaw.acquireDuring(), annualReportFitnessRaw.acquireExerciseTime()));
            return;
        }
        bVar.e(bVar.e() + 1);
        bVar.c(bVar.c() + annualReportFitnessRaw.acquireDuring());
        bVar.b(Math.max(annualReportFitnessRaw.acquireExerciseTime(), bVar.a()));
    }

    public static Map<String, Long> e(List<HiHealthData> list, List<AnnualReportFitnessRaw> list2, Set<Long> set) {
        ArrayList arrayList = new ArrayList(2);
        if (koq.c(list)) {
            arrayList.addAll(list);
        }
        return b(e(a(arrayList, set), list2, set));
    }

    private static Map<Long, Long> a(List<HiHealthData> list, Set<Long> set) {
        LogUtil.a("FitnessSportCalRule", "computeAllSportSum() sportRecords size =", Integer.valueOf(list.size()));
        HashMap hashMap = new HashMap(2);
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData == null) {
                LogUtil.h("FitnessSportCalRule", "calculateLongestDurationDay() hiHealthData is null");
            } else {
                Object obj = hiHealthData.get("duration_sum");
                if (!(obj instanceof Double)) {
                    LogUtil.h("FitnessSportCalRule", "durationSumObj type invalid");
                } else {
                    int intValue = ((Integer) hiHealthData.get("hihealth_type")).intValue();
                    if (!b(intValue, f15003a)) {
                        LogUtil.h("FitnessSportCalRule", "computeAllSportSum Sport 85, type ", Integer.valueOf(intValue));
                    } else {
                        long longValue = ((Double) obj).longValue() / 1000;
                        long startTime = hiHealthData.getStartTime();
                        set.add(Long.valueOf(HiDateUtil.t(startTime)));
                        if (!hashMap.containsKey(Long.valueOf(startTime))) {
                            hashMap.put(Long.valueOf(startTime), Long.valueOf(longValue));
                        } else {
                            if (hashMap.get(Long.valueOf(startTime)) != null) {
                                longValue += ((Long) hashMap.get(Long.valueOf(startTime))).longValue();
                            }
                            hashMap.put(Long.valueOf(startTime), Long.valueOf(longValue));
                        }
                    }
                }
            }
        }
        LogUtil.a("FitnessSportCalRule", "calculateFitness sport totalDays == ", d(set));
        return hashMap;
    }

    private static String d(Set<Long> set) {
        if (koq.b(set)) {
            return "";
        }
        ArrayList arrayList = new ArrayList(16);
        Iterator<Long> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(mfn.c(BaseApplication.e(), it.next().longValue()));
        }
        return arrayList.toString();
    }

    private static Map<Long, Long> e(Map<Long, Long> map, List<AnnualReportFitnessRaw> list, Set<Long> set) {
        LogUtil.a("FitnessSportCalRule", "calculateFitnessEverydaySum() fitnessCourseDatas size =", Integer.valueOf(list.size()));
        for (AnnualReportFitnessRaw annualReportFitnessRaw : list) {
            if (annualReportFitnessRaw == null) {
                LogUtil.h("FitnessSportCalRule", "calculateFitnessEverydaySum() fitnessRaw is null");
            } else {
                set.add(Long.valueOf(HiDateUtil.t(annualReportFitnessRaw.acquireExerciseTime())));
                if (!mlg.b(b, annualReportFitnessRaw.acquireWorkoutId())) {
                    long acquireDuring = annualReportFitnessRaw.acquireDuring() / 1000;
                    long e = jec.e(jec.c(new Date(annualReportFitnessRaw.acquireExerciseTime()), "yyyy-MM-dd") + " 00:00:00");
                    if (!map.containsKey(Long.valueOf(e))) {
                        map.put(Long.valueOf(e), Long.valueOf(acquireDuring));
                    } else {
                        if (map.get(Long.valueOf(e)) != null) {
                            acquireDuring += map.get(Long.valueOf(e)).longValue();
                        }
                        map.put(Long.valueOf(e), Long.valueOf(acquireDuring));
                    }
                }
            }
        }
        LogUtil.a("FitnessSportCalRule", "calculateFitness course totalDays == ", d(set));
        return map;
    }

    private static Map<String, Long> b(Map<Long, Long> map) {
        HashMap hashMap = new HashMap(2);
        if (map == null || map.size() == 0) {
            LogUtil.h("FitnessSportCalRule", "getLongestDayTimestamp map is empty");
            return hashMap;
        }
        long j = 0;
        long j2 = 0;
        for (Map.Entry<Long, Long> entry : map.entrySet()) {
            long longValue = entry.getValue().longValue();
            if (longValue > j2) {
                j = entry.getKey().longValue();
                j2 = longValue;
            }
        }
        hashMap.put("longest_day_timestamp", Long.valueOf(j));
        hashMap.put("longest_day_duration", Long.valueOf(Math.round(j2 / 60.0d)));
        return hashMap;
    }

    public static Map<String, Long> d(List<HiHealthData> list) {
        long j;
        HashMap hashMap = new HashMap(2);
        if (koq.b(list)) {
            LogUtil.h("FitnessSportCalRule", "computeSwimTrackInfo() dataInfos is null");
            return hashMap;
        }
        HashSet hashSet = new HashSet(16);
        Iterator<HiHealthData> it = list.iterator();
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        HiTrackMetaData hiTrackMetaData = null;
        long j6 = 0;
        while (it.hasNext()) {
            HiHealthData next = it.next();
            Iterator<HiHealthData> it2 = it;
            HiTrackMetaData hiTrackMetaData2 = hiTrackMetaData;
            try {
                hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(next.getMetaData(), HiTrackMetaData.class);
            } catch (JsonSyntaxException unused) {
                LogUtil.b("FitnessSportCalRule", "trackMetaData is error");
                hiTrackMetaData = hiTrackMetaData2;
            }
            if (hiTrackMetaData != null) {
                int totalDistance = hiTrackMetaData.getTotalDistance();
                if (mhu.b(hiTrackMetaData)) {
                    j = j2;
                } else {
                    j = j2;
                    j3 += totalDistance;
                    j4 += hiTrackMetaData.getTotalTime();
                    j5++;
                    hashSet.add(Long.valueOf(mht.a(next)));
                }
                if (!mhu.c(hiTrackMetaData)) {
                    long startTime = next.getStartTime();
                    long j7 = totalDistance;
                    if (j7 >= j6) {
                        j6 = j7;
                        j2 = startTime;
                    }
                }
                j2 = j;
            }
            it = it2;
        }
        int c = mgx.c(new ArrayList(hashSet));
        LogUtil.a("FitnessSportCalRule", "computeSwimTrackInfo reachDays == ", Integer.valueOf(c));
        hashMap.put("swim_max_distance_day", Long.valueOf(j2));
        hashMap.put("swim_longest_day_distance", Long.valueOf(j6));
        hashMap.put("swim_sum_distance", Long.valueOf(j3));
        hashMap.put("swim_sum_time", Long.valueOf(e(j4)));
        hashMap.put("swim_sum_count", Long.valueOf(j5));
        hashMap.put("max_swim_continuous_days", Long.valueOf(c));
        return hashMap;
    }

    private static long e(long j) {
        return Math.round(j / 60000.0d);
    }

    public static int b(int i) {
        return mht.d(i, d);
    }

    public static boolean b(int i, int[] iArr) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static final class b implements Comparable<b> {

        /* renamed from: a, reason: collision with root package name */
        private String f15004a;
        private int c;
        private long d;
        private int e;

        public b() {
        }

        public b(String str, int i, int i2, long j) {
            this.f15004a = str;
            this.c = i;
            this.e = i2;
            this.d = j;
        }

        public String b() {
            return this.f15004a;
        }

        public int e() {
            return this.c;
        }

        public void e(int i) {
            this.c = i;
        }

        public int c() {
            return this.e;
        }

        public void c(int i) {
            this.e = i;
        }

        public long a() {
            return this.d;
        }

        public void b(long j) {
            this.d = j;
        }

        public boolean equals(Object obj) {
            if (obj instanceof b) {
                b bVar = (b) obj;
                String str = this.f15004a;
                if (str != null && str.equals(bVar.b()) && bVar.e() == this.c && bVar.c() == this.e) {
                    return true;
                }
            }
            return super.equals(obj);
        }

        public int hashCode() {
            return super.hashCode();
        }

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            if (bVar == null) {
                return 0;
            }
            int i = this.c;
            int i2 = bVar.c;
            if (i != i2) {
                return i2 - i;
            }
            int i3 = this.e;
            int i4 = bVar.e;
            return i3 != i4 ? i4 - i3 : mht.d(bVar.d - this.d);
        }

        public String toString() {
            return "Fav{name=" + this.f15004a + ", count=" + this.c + ", duration=" + this.e + ", time=" + this.d + '}';
        }
    }
}
