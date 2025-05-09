package defpackage;

import com.google.gson.Gson;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class caz {
    public static double e(int[] iArr) {
        double d;
        double d2;
        if (iArr == null || iArr.length != 5) {
            d = 0.0d;
        } else {
            int i = 0;
            d = 0.0d;
            while (i < iArr.length) {
                int i2 = i + 1;
                if (i2 == 1) {
                    d2 = 0.25d;
                } else if (i2 == 2) {
                    d2 = 0.4d;
                } else if (i2 == 3) {
                    d2 = 0.6d;
                } else if (i2 == 4) {
                    d2 = 0.8d;
                } else if (i2 != 5) {
                    LogUtil.b("Suggestion_TrainPointUtils", "statisticPoint error tmpZone", Integer.valueOf(i2));
                    d2 = 0.0d;
                } else {
                    d2 = 1.0d;
                }
                d += (iArr[i] * d2) / 60.0d;
                i = i2;
            }
        }
        return UnitUtil.a(d >= 0.0d ? d : 0.0d, 1);
    }

    private static double a(double d, double d2, double d3) {
        if (d2 == d3) {
            LogUtil.b("Suggestion_TrainPointUtils", "getPerMinPoint mMaxHeartRate == mRestHeartRate");
            return 0.0d;
        }
        double d4 = (d - d3) / (d2 - d3);
        if (d4 < 0.5d) {
            return 0.0d;
        }
        double d5 = (((2.626d * d4) * d4) - (d4 * 1.8663d)) + 0.2835d;
        if (d5 < 0.0d) {
            return 0.0d;
        }
        return d5;
    }

    public static double e(HeartZoneConf heartZoneConf, List<HeartRateData> list, int i) {
        int i2;
        double d;
        if (koq.b(list) || heartZoneConf == null) {
            return 0.0d;
        }
        int maxThreshold = heartZoneConf.getMaxThreshold();
        int restHeartRate = heartZoneConf.getRestHeartRate();
        if (restHeartRate == 0) {
            restHeartRate = 60;
        }
        int i3 = restHeartRate;
        if (heartZoneConf.getClassifyMethod() != 0) {
            return e(c(list, heartZoneConf, i));
        }
        double d2 = 0.0d;
        double d3 = 0.0d;
        loop0: while (true) {
            i2 = 0;
            d = 0.0d;
            for (HeartRateData heartRateData : list) {
                if (heartRateData != null) {
                    if (heartRateData.acquireHeartRate() != 0) {
                        i2++;
                    }
                    d += heartRateData.acquireHeartRate();
                    if (heartRateData.acquireTime() % 60 == 0) {
                        if (i2 != 0) {
                            d2 = d / i2;
                        }
                        double d4 = d2;
                        d3 = e(maxThreshold, i3, d4, d3);
                        d2 = d4;
                    }
                }
            }
            break loop0;
        }
        if (d > 0.0d && i2 > 0) {
            double a2 = a(d / i2, maxThreshold, i3);
            if (a2 > 0.0d) {
                a2 *= (i2 * i) / 60.0d;
            }
            d3 += a2;
        }
        if (d3 < 0.0d) {
            return 0.0d;
        }
        return d3;
    }

    private static double e(int i, int i2, double d, double d2) {
        return d2 + a(d, i, i2);
    }

    private static int[] c(List<HeartRateData> list, HeartZoneConf heartZoneConf, int i) {
        int findRateRegion;
        int[] iArr = new int[5];
        if (koq.b(list) || heartZoneConf == null) {
            LogUtil.h("Suggestion_TrainPointUtils", "getReserveHeartTypeTrainPoint mHeartRateList is null");
            return iArr;
        }
        LogUtil.a("Suggestion_TrainPointUtils", "getHeartTimeZone heartRateList", new Gson().toJson(list));
        for (HeartRateData heartRateData : list) {
            if (heartRateData != null && (findRateRegion = heartZoneConf.findRateRegion(heartRateData.acquireHeartRate())) != -1) {
                iArr[findRateRegion] = iArr[findRateRegion] + i;
            }
        }
        return iArr;
    }

    public static int d(MotionPath motionPath, cab cabVar, long j) {
        long round;
        if (motionPath == null || cabVar == null) {
            LogUtil.h("Suggestion_TrainPointUtils", "motionPath == null || courseEnvParams == null");
            return 0;
        }
        if (cabVar.f()) {
            round = Math.round(e(cabVar.c(), motionPath.requestHeartRateList(), 5) * 10.0d);
        } else {
            round = Math.round(e(fgc.c(cabVar.g(), motionPath.requestRealTimePaceList(), j)) * 10.0d);
        }
        return (int) round;
    }
}
