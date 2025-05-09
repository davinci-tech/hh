package defpackage;

import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.ui.fitness.module.HeartRateLineChartHolderImpl;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public class ggt {
    private HeartRateLineChartHolderImpl b;

    public ggt(HeartRateLineChartHolderImpl heartRateLineChartHolderImpl) {
        this.b = heartRateLineChartHolderImpl;
    }

    public void a(WorkoutRecord workoutRecord) {
        if (workoutRecord == null) {
            LogUtil.h("Suggestion_HeartRateChartFiller", "initHeartRateData failure. workoutRecord is null, return.");
            return;
        }
        List<HeartRateData> acquireHeartRateDataList = workoutRecord.acquireHeartRateDataList();
        if (koq.b(acquireHeartRateDataList)) {
            LogUtil.h("Suggestion_HeartRateChartFiller", "initHeartRateData failure. heartRateList is null, return.");
            return;
        }
        List<HeartRateData> acquireInvalidHeartRateList = workoutRecord.acquireInvalidHeartRateList();
        c(d(acquireHeartRateDataList), d(acquireInvalidHeartRateList), workoutRecord.acquireHeartRateConfig(), workoutRecord.startTime());
    }

    private void c(ArrayList<HeartRateData> arrayList, ArrayList<HeartRateData> arrayList2, String str, long j) {
        if (arrayList == null) {
            LogUtil.h("Suggestion_HeartRateChartFiller", "fillHeartRateData motionPath requestHeartRateList null,return");
            return;
        }
        ArrayList<HeartRateData> arrayList3 = new ArrayList<>(16);
        this.b.b(arrayList3);
        this.b.a(arrayList2);
        int d = sqb.d(arrayList, j);
        this.b.a(d);
        boolean z = e(arrayList) && e(arrayList2);
        boolean b = koq.b(arrayList2);
        LogUtil.a("Suggestion_HeartRateChartFiller", "initHeartRateData hasTrustHeartRate", str);
        if (!z || b) {
            Iterator<HeartRateData> it = arrayList.iterator();
            if ("1".equals(str)) {
                LogUtil.c("Suggestion_HeartRateChartFiller", "initHeartRateData no invalid list has trust heartRate");
                c(d, arrayList3, it);
            } else if (str == null) {
                c(d, arrayList3, arrayList);
            } else {
                LogUtil.a("Suggestion_HeartRateChartFiller", "initHeartRateData, hasTrustHeartRate is other value:", str);
            }
            if (arrayList.size() - 1 > 0) {
                e(d, arrayList, str);
                return;
            } else {
                this.b.c();
                return;
            }
        }
        c(arrayList, arrayList2, arrayList3, str, d);
        if ((arrayList.size() + arrayList2.size()) - 1 > 0) {
            e(d, arrayList, arrayList2, str);
        } else {
            this.b.c();
        }
    }

    private void c(int i, ArrayList<HeartRateData> arrayList, List<HeartRateData> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            arrayList.add(new HeartRateData(i * i2, list.get(i2).acquireHeartRate()));
        }
    }

    private void c(int i, ArrayList<HeartRateData> arrayList, Iterator<HeartRateData> it) {
        HeartRateData heartRateData = null;
        int i2 = 0;
        while (it.hasNext()) {
            HeartRateData next = it.next();
            int b = b(next, heartRateData);
            if (b >= 0) {
                i2 += b;
            }
            arrayList.add(new HeartRateData(i * i2, next.acquireHeartRate()));
            i2++;
            heartRateData = next;
        }
    }

    private void c(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, String str, int i) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>(16);
        if (str == null) {
            c(list, list2, list3, i, arrayList);
        } else if ("1".equals(str)) {
            b(list, list2, list3, i, arrayList);
        } else {
            LogUtil.a("Suggestion_HeartRateChartFiller", "correctHeartRateData, hasTrustHeartRate is other value:", str);
        }
        this.b.e(arrayList);
    }

    private void c(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, ArrayList<HeartRateData> arrayList) {
        int i2 = 0;
        int i3 = 0;
        for (HeartRateData heartRateData : list) {
            while (i3 < list2.size()) {
                HeartRateData heartRateData2 = list2.get(i3);
                if (heartRateData2.acquireTime() < heartRateData.acquireTime()) {
                    i3++;
                    arrayList.add(new HeartRateData(i * i2, heartRateData2.acquireHeartRate()));
                    i2++;
                }
            }
            list3.add(new HeartRateData(i * i2, heartRateData.acquireHeartRate()));
            i2++;
        }
    }

    private void b(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, ArrayList<HeartRateData> arrayList) {
        long min = Math.min(list.get(0).acquireTime(), list2.get(0).acquireTime());
        e(list, list3, i, min);
        e(list2, arrayList, i, min);
    }

    private void e(List<HeartRateData> list, List<HeartRateData> list2, int i, long j) {
        Iterator<HeartRateData> it = list.iterator();
        while (it.hasNext()) {
            list2.add(new HeartRateData(i * (((int) ((r0.acquireTime() - j) / 1000)) / i), it.next().acquireHeartRate()));
        }
    }

    private boolean e(List<HeartRateData> list) {
        HeartRateData heartRateData = null;
        for (HeartRateData heartRateData2 : list) {
            if (heartRateData2 == null) {
                return false;
            }
            if (heartRateData == null) {
                heartRateData = heartRateData2;
            } else if (heartRateData2.acquireTime() < heartRateData.acquireTime()) {
                return false;
            }
        }
        return true;
    }

    private int b(HeartRateData heartRateData, HeartRateData heartRateData2) {
        if (heartRateData == null || heartRateData2 == null) {
            LogUtil.b("Suggestion_HeartRateChartFiller", "getTrustHeartRateIntervalNum data is null or heartRate is invalid");
            return 0;
        }
        if ((heartRateData.acquireTime() - heartRateData2.acquireTime()) / 1000 > 5) {
            return (((int) r1) / 5) - 1;
        }
        return 0;
    }

    private void e(int i, List<HeartRateData> list, String str) {
        if (str == null) {
            this.b.d(i * (list.size() - 1));
        } else if ("1".equals(str)) {
            this.b.d((list.get(list.size() - 1).acquireTime() - list.get(0).acquireTime()) / 1000.0f);
        } else {
            LogUtil.a("Suggestion_HeartRateChartFiller", "modifyHeartRateSumTime, hasTrustHeartRate is other value:", str);
        }
    }

    private void e(int i, List<HeartRateData> list, List<HeartRateData> list2, String str) {
        if (str == null) {
            this.b.d(i * ((list.size() + list2.size()) - 1));
            return;
        }
        if ("1".equals(str)) {
            long acquireTime = list.get(0).acquireTime();
            long acquireTime2 = list.get(list.size() - 1).acquireTime();
            long acquireTime3 = list2.get(0).acquireTime();
            long acquireTime4 = list2.get(list2.size() - 1).acquireTime();
            this.b.d((Math.max(acquireTime2, acquireTime4) - Math.min(acquireTime, acquireTime3)) / 1000);
            return;
        }
        LogUtil.a("Suggestion_HeartRateChartFiller", "modifyHeartRateSumTime, hasTrustHeartRate is other value:", str);
    }

    private ArrayList<HeartRateData> d(List<HeartRateData> list) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>();
        if (list != null) {
            for (HeartRateData heartRateData : list) {
                HeartRateData heartRateData2 = new HeartRateData();
                heartRateData2.saveTime(heartRateData.acquireTime());
                heartRateData2.saveHeartRate(heartRateData.acquireHeartRate());
                arrayList.add(heartRateData2);
            }
        }
        return arrayList;
    }
}
