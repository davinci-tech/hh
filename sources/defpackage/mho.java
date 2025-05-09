package defpackage;

import androidx.core.location.LocationRequestCompat;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.bean.AnnualReport;
import com.huawei.pluginachievement.report.bean.AnnualReportMarathon;
import com.huawei.pluginachievement.report.bean.AnnualReportRun;
import com.huawei.pluginachievement.report.bean.MarathonGradleDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class mho {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f15005a = {3000, 108000, 216000, 504000};

    public static Map<String, Integer> b(List<HiHealthData> list) {
        HashMap hashMap = new HashMap(16);
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_RunCalRule", "getSumTrackData dataInfos is empty");
            return hashMap;
        }
        LogUtil.a("PLGACHIEVE_RunCalRule", "computeSumTrackData size= ", Integer.valueOf(list.size()));
        for (HiHealthData hiHealthData : list) {
            hashMap.put(mht.d(hiHealthData.getStartTime()), Integer.valueOf((int) hiHealthData.getDouble("Track_Run_Distance_Sum")));
        }
        return hashMap;
    }

    public static String a(Map<String, Integer> map) {
        if (map == null) {
            return "";
        }
        LogUtil.a("PLGACHIEVE_RunCalRule", "getSumTrackData");
        int size = map.size();
        StringBuilder sb = new StringBuilder(size);
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        int i = 1;
        while (it.hasNext()) {
            sb.append(it.next().getKey() + "_" + (r3.getValue().intValue() / 1000.0d));
            if (i < size) {
                sb.append(",");
            }
            i++;
        }
        return sb.toString();
    }

    public static AnnualReport e(List<HiHealthData> list, int i, Map<Integer, Integer> map) {
        long j;
        Iterator<HiHealthData> it;
        MarathonGradleDetail marathonGradleDetail = new MarathonGradleDetail(Double.MAX_VALUE, LocationRequestCompat.PASSIVE_INTERVAL, 0, 0);
        MarathonGradleDetail marathonGradleDetail2 = new MarathonGradleDetail(Double.MAX_VALUE, LocationRequestCompat.PASSIVE_INTERVAL, 0, 0);
        ArrayList<Integer> c = mhu.c();
        LogUtil.a("PLGACHIEVE_RunCalRule", "enter ", Integer.valueOf(c.size()), " half ", Integer.valueOf(marathonGradleDetail.getTimes()), " full ", Integer.valueOf(marathonGradleDetail2.getTimes()));
        HashSet hashSet = new HashSet(16);
        HashMap hashMap = new HashMap(16);
        Iterator<HiHealthData> it2 = list.iterator();
        float f = 0.0f;
        int i2 = 0;
        long j2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (it2.hasNext()) {
            HiHealthData next = it2.next();
            HiTrackMetaData d = d(next.getMetaData());
            if (d != null) {
                int totalDistance = d.getTotalDistance();
                if (mhu.b(d)) {
                    j = j2;
                } else {
                    hashSet.add(Long.valueOf(mht.a(next)));
                    j = j2;
                    b((Map<Long, Integer>) hashMap, mht.a(next), totalDistance);
                    i3++;
                    i2 += totalDistance;
                }
                int i5 = i2;
                int i6 = i3;
                if (!mhu.c(d)) {
                    if (mfg.b(d.getSportType())) {
                        float d2 = d(next, f, d, i);
                        it = it2;
                        e(marathonGradleDetail, marathonGradleDetail2, d, next.getStartTime(), i);
                        long startTime = next.getStartTime();
                        if (totalDistance >= i4) {
                            j = startTime;
                            i4 = totalDistance;
                        }
                        int e = mhu.e(startTime);
                        if (e < 24) {
                            c.set(e, Integer.valueOf(c.get(e).intValue() + 1));
                        }
                        f = d2;
                    } else {
                        it = it2;
                    }
                    it2 = it;
                }
                i2 = i5;
                i3 = i6;
                j2 = j;
            }
        }
        long j3 = j2;
        double d3 = f;
        map.put(Integer.valueOf(i), Integer.valueOf(mht.d(d3)));
        return b(c(b(i4, j3, i2, i3, c), hashSet.size(), b(hashMap), mht.d(d3)), marathonGradleDetail, marathonGradleDetail2);
    }

    private static AnnualReportRun c(AnnualReportRun annualReportRun, int i, int i2, int i3) {
        annualReportRun.setRunNumberOfDays(i);
        annualReportRun.setMaxRunContinuousDays(i2);
        annualReportRun.setBestPace(i3);
        LogUtil.a("PLGACHIEVE_RunCalRule", "initRunData bestPace ", Integer.valueOf(i3));
        return annualReportRun;
    }

    private static float d(HiHealthData hiHealthData, float f, HiTrackMetaData hiTrackMetaData, int i) {
        float f2;
        float f3 = f;
        if (mlg.e(hiTrackMetaData.getBestPace(), 0.0d) != 0) {
            f2 = hiTrackMetaData.getBestPace();
        } else {
            Iterator it = new ArrayList(hiTrackMetaData.getPaceMap().entrySet()).iterator();
            float f4 = 0.0f;
            while (it.hasNext()) {
                float floatValue = ((Float) ((Map.Entry) it.next()).getValue()).floatValue();
                double d = f4;
                if (mlg.e(d, 0.0d) == 0 || mlg.e(floatValue, d) < 0) {
                    f4 = floatValue;
                }
            }
            f2 = f4;
        }
        int totalDistance = hiTrackMetaData.getTotalDistance();
        double d2 = f2;
        if (mlg.e(d2, 0.0d) == 0 || totalDistance < 1000) {
            LogUtil.h("PLGACHIEVE_RunCalRule", "computeBestPace year ", Integer.valueOf(i), " currPace ", Float.valueOf(f2), " date ", Long.valueOf(hiHealthData.getStartTime()), " totalTrackDis ", Integer.valueOf(totalDistance));
            return f3;
        }
        double d3 = f3;
        if (mlg.e(d3, 0.0d) == 0 || mlg.e(d2, d3) < 0) {
            f3 = f2;
        }
        LogUtil.a("PLGACHIEVE_RunCalRule", "computeBestPace bestPace ", Float.valueOf(f3), " currPace ", Float.valueOf(f2), " date ", Long.valueOf(hiHealthData.getStartTime()), " year ", Integer.valueOf(i));
        return f3;
    }

    private static int b(Map<Long, Integer> map) {
        ArrayList arrayList = new ArrayList(16);
        for (Map.Entry entry : new ArrayList(map.entrySet())) {
            long longValue = ((Long) entry.getKey()).longValue();
            int intValue = ((Integer) entry.getValue()).intValue();
            if (intValue >= 2000) {
                arrayList.add(Long.valueOf(longValue));
            }
            LogUtil.a("PLGACHIEVE_RunCalRule", "computeMaxRunContinuousDays date ", Long.valueOf(longValue), " distance ", Integer.valueOf(intValue));
        }
        int c = mgx.c(arrayList);
        LogUtil.a("PLGACHIEVE_RunCalRule", "computeMaxRunContinuousDays reachDays == ", Integer.valueOf(c));
        return c;
    }

    private static void b(Map<Long, Integer> map, long j, int i) {
        if (map.containsKey(Long.valueOf(j))) {
            i += map.get(Long.valueOf(j)).intValue();
        }
        map.put(Long.valueOf(j), Integer.valueOf(i));
    }

    private static AnnualReportRun b(int i, long j, int i2, int i3, ArrayList<Integer> arrayList) {
        AnnualReportRun annualReportRun = new AnnualReportRun();
        annualReportRun.saveMaxDistance(i);
        annualReportRun.saveMaxDistanceDay(j);
        annualReportRun.saveTotalDistance(i2);
        annualReportRun.saveNumberOfTimes(i3);
        annualReportRun.saveTimeOfDay(b(arrayList));
        annualReportRun.saveDescription(mhu.a(i3));
        return annualReportRun;
    }

    private static HiTrackMetaData d(String str) {
        try {
            return (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("PLGACHIEVE_RunCalRule", "trackMetaData is error");
            return null;
        }
    }

    private static AnnualReportMarathon a(MarathonGradleDetail marathonGradleDetail, MarathonGradleDetail marathonGradleDetail2) {
        if (marathonGradleDetail.getTimes() > 0 && mlg.e(marathonGradleDetail.getBestRecord(), Double.MAX_VALUE) == 0) {
            marathonGradleDetail.setBestRecord(0.0d);
        }
        if (marathonGradleDetail2.getTimes() > 0 && mlg.e(marathonGradleDetail2.getBestRecord(), Double.MAX_VALUE) == 0) {
            marathonGradleDetail2.setBestRecord(0.0d);
        }
        AnnualReportMarathon annualReportMarathon = new AnnualReportMarathon();
        annualReportMarathon.setFullMarathon(marathonGradleDetail2);
        annualReportMarathon.setHalfMarathon(marathonGradleDetail);
        return annualReportMarathon;
    }

    private static AnnualReport b(AnnualReportRun annualReportRun, MarathonGradleDetail marathonGradleDetail, MarathonGradleDetail marathonGradleDetail2) {
        AnnualReport annualReport = new AnnualReport();
        annualReport.setReportRun(annualReportRun);
        annualReport.setReportMarathon(a(marathonGradleDetail, marathonGradleDetail2));
        return annualReport;
    }

    private static void e(MarathonGradleDetail marathonGradleDetail, MarathonGradleDetail marathonGradleDetail2, HiTrackMetaData hiTrackMetaData, long j, int i) {
        double b;
        double b2;
        double totalDistance = hiTrackMetaData.getTotalDistance();
        Map<Double, Double> partTimeMap = hiTrackMetaData.getPartTimeMap();
        if (partTimeMap == null) {
            LogUtil.h("PLGACHIEVE_RunCalRule", "marathon partMap is null");
            return;
        }
        if (mht.e(i, j)) {
            if (totalDistance >= 42195.0d) {
                if (partTimeMap.containsKey(Double.valueOf(42.195d))) {
                    LogUtil.a("PLGACHIEVE_RunCalRule", "full marathon");
                    b2 = partTimeMap.get(Double.valueOf(42.195d)).doubleValue();
                } else {
                    b2 = b(partTimeMap, 42.0d, 0.19500000000000028d);
                }
                e(marathonGradleDetail2, b2, j);
                a(marathonGradleDetail2, b2, j);
                return;
            }
            if (totalDistance >= 21097.0d) {
                if (partTimeMap.containsKey(Double.valueOf(21.0975d))) {
                    LogUtil.a("PLGACHIEVE_RunCalRule", "half marathon");
                    b = partTimeMap.get(Double.valueOf(21.0975d)).doubleValue();
                } else {
                    b = b(partTimeMap, 21.0d, 0.09750000000000014d);
                }
                e(marathonGradleDetail, b, j);
                a(marathonGradleDetail, b, j);
                return;
            }
            LogUtil.h("PLGACHIEVE_RunCalRule", "no marathon records");
        }
    }

    private static void e(MarathonGradleDetail marathonGradleDetail, double d, long j) {
        if (j < marathonGradleDetail.getFirstTime()) {
            LogUtil.a("PLGACHIEVE_RunCalRule", "marathon() marathonStartTime=", Long.valueOf(j));
            marathonGradleDetail.setFirstRecord(d);
            marathonGradleDetail.setFirstTime(j);
        }
        marathonGradleDetail.setTimes(marathonGradleDetail.getTimes() + 1);
    }

    private static double b(Map<Double, Double> map, double d, double d2) {
        if (map == null) {
            return 0.0d;
        }
        double doubleValue = map.containsKey(Double.valueOf(d)) ? map.get(Double.valueOf(d)).doubleValue() : 0.0d;
        double d3 = d + 1.0d;
        double doubleValue2 = map.containsKey(Double.valueOf(d3)) ? map.get(Double.valueOf(d3)).doubleValue() : 0.0d;
        return (mht.a(doubleValue, 0.0d) || mht.a(doubleValue2, 0.0d)) ? doubleValue + doubleValue2 : doubleValue + (Math.abs(doubleValue2 - doubleValue) * d2);
    }

    private static void a(MarathonGradleDetail marathonGradleDetail, double d, long j) {
        if (mlg.e(d, 0.0d) == 0) {
            LogUtil.h("PLGACHIEVE_RunCalRule", "marathon() marathonBestPace = 0");
        } else if (d < marathonGradleDetail.getBestRecord()) {
            LogUtil.a("PLGACHIEVE_RunCalRule", "marathon() marathonBestPace=", Double.valueOf(d));
            marathonGradleDetail.setBestRecord(d);
            marathonGradleDetail.setBestTime(j);
        }
    }

    private static int b(ArrayList<Integer> arrayList) {
        int size = arrayList.size();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            int intValue = arrayList.get(i3).intValue();
            if (intValue > i2) {
                i = i3;
                i2 = intValue;
            }
        }
        return i;
    }

    public static int d(int i) {
        return mht.d(i, f15005a);
    }
}
