package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Map;

/* loaded from: classes2.dex */
public class up {

    /* renamed from: a, reason: collision with root package name */
    private static final double[] f17494a = {1.0d, 3.0d, 5.0d, 10.0d};

    public static int c(us usVar) {
        int i;
        if (usVar == null) {
            return 100;
        }
        int f = usVar.f();
        long h = usVar.h() / 1000;
        float f2 = (1000 * h) / (f * 1.0f);
        if (f2 < 131.96f) {
            LogUtil.a("Track_AbnormalNorthBoundUtil", "avgPace is Abnormal avgPace = ", Float.valueOf(f2));
            return 1;
        }
        if (e(usVar)) {
            ReleaseLogUtil.b("Track_AbnormalNorthBoundUtil", "pace, checkAbnormalTrack result is ", 1);
            return 1;
        }
        Map<Double, Double> j = usVar.j();
        LogUtil.a("Track_AbnormalNorthBoundUtil", "partTime map", j, "distance", Integer.valueOf(f));
        if (j == null || j.isEmpty()) {
            i = 0;
        } else {
            i = c(f, j);
            if (i != 0) {
                LogUtil.a("Track_AbnormalNorthBoundUtil", "AbnormalTrack break world record Continuous");
                return i;
            }
        }
        float n = usVar.n();
        LogUtil.a("Track_AbnormalNorthBoundUtil", "sportTime ", Long.valueOf(h), "avgStepRate", Float.valueOf(n));
        if (c(usVar, f, n, h) == 4) {
            return 4;
        }
        return i;
    }

    private static boolean e(us usVar) {
        Map<Integer, Float> b = ur.b(usVar.b());
        if (b.size() > 0) {
            LogUtil.a("Track_AbnormalNorthBoundUtil", "validPaceMap abnormal is ", b.toString());
            if (ur.c(b, 131.96f) > 0) {
                return true;
            }
        }
        return false;
    }

    private static int c(us usVar, int i, float f, long j) {
        int c = usVar.c();
        int i2 = usVar.i();
        LogUtil.a("Track_AbnormalNorthBoundUtil", "checkStepAbnormal, sportType ", Integer.valueOf(c), " dataSource ", Integer.valueOf(i2));
        if (c == 264 && i2 == 5) {
            return 0;
        }
        if (f < 1.0E-6d) {
            ReleaseLogUtil.b("Track_AbnormalNorthBoundUtil", "avgStepRate, checkAbnormalTrack result is 1e-6");
            return 4;
        }
        float f2 = i / ((f / 60.0f) * j);
        LogUtil.a("Track_AbnormalNorthBoundUtil", "stride is ", Float.valueOf(f2));
        return f2 > 3.0f ? 4 : 0;
    }

    private static int c(long j, Map<Double, Double> map) {
        int i = 0;
        for (double d : f17494a) {
            if (j < d) {
                return i;
            }
            i = e(j, map, d);
        }
        return i;
    }

    private static int e(long j, Map<Double, Double> map, double d) {
        double b = ur.b((int) d);
        int i = 0;
        int i2 = 0;
        while (true) {
            double d2 = i;
            if (d2 > j - d) {
                return i2;
            }
            double d3 = d2 + d;
            if (map.size() >= d3) {
                if (i == 0) {
                    Double d4 = map.get(Double.valueOf(d));
                    if (d4 == null) {
                        LogUtil.b("Track_AbnormalNorthBoundUtil", "partTime.get(partTimeKey) is null", Double.valueOf(d));
                    } else if (d4.doubleValue() < b) {
                        LogUtil.h("Track_AbnormalNorthBoundUtil", "error time1", map.get(Double.valueOf(d)));
                        i2 = 5;
                    }
                } else {
                    Double d5 = map.get(Double.valueOf(d2));
                    Double d6 = map.get(Double.valueOf(d3));
                    if (d5 == null || d6 == null) {
                        LogUtil.b("Track_AbnormalNorthBoundUtil", "partTime.get(i + partTimeKey) is null");
                    } else if (d6.doubleValue() - d5.doubleValue() < b) {
                        LogUtil.h("Track_AbnormalNorthBoundUtil", "AbnormalTrackForEachDistance", d6, "add", d5, "i", Integer.valueOf(i));
                        i2 = 5;
                    }
                }
            }
            i++;
        }
    }
}
