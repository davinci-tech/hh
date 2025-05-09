package defpackage;

import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.StepRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class hjh {
    public static void c(ArrayList<ffs> arrayList, List<ffs> list) {
        if (koq.b(list)) {
            LogUtil.b("Track_DataPretreatmentUtil", "runningPostureListTemp is null");
            return;
        }
        if (fgf.e(list)) {
            arrayList.addAll(list);
            return;
        }
        boolean e = e(list);
        long j = -1;
        for (ffs ffsVar : list) {
            if (ffsVar != null) {
                j = d(arrayList, j, e, ffsVar);
            }
        }
    }

    private static long d(ArrayList<ffs> arrayList, long j, boolean z, ffs ffsVar) {
        long e = e(b(z, ffsVar.g()));
        long j2 = e - j;
        if (j != -1 && j2 > 5 && j2 < 20) {
            int i = ((int) j2) / 5;
            for (int i2 = 1; i2 <= i; i2++) {
                long j3 = (i2 * 5) + j;
                if (j3 >= e) {
                    break;
                }
                d(j3, arrayList, ffsVar);
            }
        }
        d(e, arrayList, ffsVar);
        return e;
    }

    private static boolean e(List<ffs> list) {
        if (list.size() < 3) {
            return list.size() > 0 && list.get(0).g() > 1000;
        }
        long g = list.get(0).g();
        long g2 = list.get(1).g();
        return g2 - g > 500 && list.get(2).g() - g2 > 500;
    }

    private static long b(boolean z, long j) {
        return z ? TimeUnit.MILLISECONDS.toSeconds(j) : j;
    }

    private static long e(long j) {
        long j2 = j / 5;
        return j % 5 > 2 ? (j2 + 1) * 5 : j2 * 5;
    }

    private static void d(long j, ArrayList<ffs> arrayList, ffs ffsVar) {
        ffs ffsVar2 = new ffs(j, ffsVar.b(), ffsVar.e(), ffsVar.i(), ffsVar.a(), ffsVar.d(), ffsVar.h(), ffsVar.c());
        ffsVar2.g(ffsVar.l());
        ffsVar2.i(ffsVar.o());
        ffsVar2.d(ffsVar.f());
        ffsVar2.b(ffsVar.n());
        ffsVar2.a(ffsVar.m());
        ffsVar2.e(ffsVar.t());
        ffsVar2.c(ffsVar.r());
        if (arrayList != null) {
            arrayList.add(ffsVar2);
        }
    }

    public static void a(List<StepRateData> list, long j, long j2, List<StepRateData> list2) {
        Iterator<StepRateData> it;
        long j3;
        if (e(list, list2)) {
            int d = sqb.d(list, j);
            int i = 0;
            long j4 = 0;
            list2.add(new StepRateData(0L, list.get(0).acquireStepRate()));
            Iterator<StepRateData> it2 = list.iterator();
            long j5 = 0;
            while (it2.hasNext()) {
                StepRateData next = it2.next();
                long j6 = d;
                i++;
                long j7 = i * j6;
                if (it2.hasNext()) {
                    list2.add(new StepRateData(j7, next.acquireStepRate()));
                    it = it2;
                    j3 = j4;
                } else {
                    long seconds = ((int) TimeUnit.MILLISECONDS.toSeconds(j2)) - j5;
                    it = it2;
                    if (seconds < 0) {
                        LogUtil.c("Track_DataPretreatmentUtil", "endIntervalSecs(real) < 0,make use of endInterval = interval");
                        list2.add(new StepRateData(j7, next.acquireStepRate()));
                        j5 = j7;
                        j3 = 0;
                    } else {
                        long j8 = seconds - (seconds % 5);
                        j3 = 0;
                        if (j8 == 0) {
                            LogUtil.c("Track_DataPretreatmentUtil", "endIntervalSecs(floor 5)==0,make time=lastGeneratedDataTime+5");
                            j5 += 5;
                            list2.add(new StepRateData(j5, next.acquireStepRate()));
                        } else if (j8 > j6) {
                            LogUtil.c("Track_DataPretreatmentUtil", "endIntervalSecs(floor 5)> interval, time=lastGeneratedDataTime + interval");
                            list2.add(new StepRateData(j7, next.acquireStepRate()));
                        } else {
                            j5 += j8;
                            list2.add(new StepRateData(j5, next.acquireStepRate()));
                        }
                    }
                    it2 = it;
                    j4 = j3;
                }
                it2 = it;
                j5 = j7;
                j4 = j3;
            }
        }
    }

    private static boolean e(List<StepRateData> list, List<StepRateData> list2) {
        if (koq.b(list) || list2 == null) {
            return false;
        }
        if (list.get(0) != null) {
            return true;
        }
        LogUtil.b("Track_DataPretreatmentUtil", "stepRateListTemp.get(0)  null");
        return false;
    }

    public static List<HeartRateData> a(MotionPath motionPath, long j, String str) {
        if (motionPath == null) {
            LogUtil.b("Track_DataPretreatmentUtil", "motionPath is null");
            return null;
        }
        ArrayList arrayList = new ArrayList(16);
        ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
        ArrayList<HeartRateData> requestInvalidHeartRateList = motionPath.requestInvalidHeartRateList();
        int d = sqb.d(requestHeartRateList, j);
        if (koq.b(requestHeartRateList)) {
            LogUtil.b("Track_DataPretreatmentUtil", "heartRateListTemp is null");
            return null;
        }
        if (b(requestHeartRateList, requestInvalidHeartRateList)) {
            d(requestHeartRateList, str, d, (ArrayList<HeartRateData>) arrayList);
        } else {
            b(requestHeartRateList, requestInvalidHeartRateList, arrayList, d, str);
        }
        return arrayList;
    }

    public static boolean b(List<HeartRateData> list, List<HeartRateData> list2) {
        return !(b(list) && b(list2)) || koq.b(list2);
    }

    public static void d(List<HeartRateData> list, String str, int i, ArrayList<HeartRateData> arrayList) {
        if (koq.b(list)) {
            LogUtil.b("Track_DataPretreatmentUtil", "heartRateListTemp is null");
            return;
        }
        Iterator<HeartRateData> it = list.iterator();
        if ("1".equals(str)) {
            LogUtil.c("Track_DataPretreatmentUtil", "initHeartRateData no invalid list has trust heartRate");
            a(i, arrayList, it);
        } else if (str == null) {
            c(i, arrayList, list);
        }
    }

    private static void c(int i, ArrayList<HeartRateData> arrayList, List<HeartRateData> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            HeartRateData heartRateData = list.get(i2);
            if (arrayList != null && heartRateData != null) {
                arrayList.add(new HeartRateData(i * i2, heartRateData.acquireHeartRate()));
            }
        }
    }

    private static void a(int i, ArrayList<HeartRateData> arrayList, Iterator<HeartRateData> it) {
        HeartRateData heartRateData = null;
        int i2 = 0;
        while (it.hasNext()) {
            HeartRateData next = it.next();
            int c = c(next, heartRateData);
            if (c >= 0) {
                i2 += c;
            }
            if (next != null && arrayList != null) {
                arrayList.add(new HeartRateData(i * i2, next.acquireHeartRate()));
            }
            i2++;
            heartRateData = next;
        }
    }

    private static int c(HeartRateData heartRateData, HeartRateData heartRateData2) {
        if (heartRateData == null || heartRateData2 == null) {
            LogUtil.b("Track_DataPretreatmentUtil", "getTrustHeartRateIntervalNum data is null or heartRate is invalid");
            return 0;
        }
        if ((heartRateData.acquireTime() - heartRateData2.acquireTime()) / 1000 > 5) {
            return (((int) r1) / 5) - 1;
        }
        return 0;
    }

    private static void b(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, String str) {
        if (koq.b(list) || koq.b(list2)) {
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        if (str == null) {
            b(list, list2, list3, i, (ArrayList<HeartRateData>) arrayList);
        } else if ("1".equals(str)) {
            c(list, list2, list3, i, (ArrayList<HeartRateData>) arrayList);
        }
    }

    public static ArrayList<HeartRateData> c(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, String str) {
        ArrayList<HeartRateData> arrayList = new ArrayList<>(16);
        if (list == null || list2 == null) {
            LogUtil.b("Track_DataPretreatmentUtil", "heartRateListTemp or heartRateInvalidListTemp is null");
            return arrayList;
        }
        if (str == null) {
            b(list, list2, list3, i, arrayList);
        } else if ("1".equals(str)) {
            c(list, list2, list3, i, arrayList);
        }
        return arrayList;
    }

    private static void b(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, ArrayList<HeartRateData> arrayList) {
        int i2 = 0;
        int i3 = 0;
        for (HeartRateData heartRateData : list) {
            while (i3 < list2.size()) {
                HeartRateData heartRateData2 = list2.get(i3);
                if (heartRateData2.acquireTime() >= heartRateData.acquireTime()) {
                    break;
                }
                i3++;
                arrayList.add(new HeartRateData(i * i2, heartRateData2.acquireHeartRate()));
                i2++;
            }
            if (list3 != null) {
                list3.add(new HeartRateData(i * i2, heartRateData.acquireHeartRate()));
            }
            i2++;
        }
    }

    private static void c(List<HeartRateData> list, List<HeartRateData> list2, List<HeartRateData> list3, int i, ArrayList<HeartRateData> arrayList) {
        long j;
        if (koq.d(list, 0) && koq.d(list2, 0)) {
            long acquireTime = list.get(0) != null ? list.get(0).acquireTime() : 0L;
            j = list2.get(0) != null ? list2.get(0).acquireTime() : 0L;
            r2 = acquireTime;
        } else {
            j = 0;
        }
        long min = Math.min(r2, j);
        c(list, list3, i, min);
        c(list2, arrayList, i, min);
    }

    private static void c(List<HeartRateData> list, List<HeartRateData> list2, int i, long j) {
        for (HeartRateData heartRateData : list) {
            if (heartRateData != null) {
                list2.add(new HeartRateData(i * (((int) ((heartRateData.acquireTime() - j) / 1000)) / i), heartRateData.acquireHeartRate()));
            }
        }
    }

    private static boolean b(List<HeartRateData> list) {
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
}
