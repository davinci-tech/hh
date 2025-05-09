package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes3.dex */
public class bzk {
    private static double d(double d, double d2, double d3, double d4) {
        return d * 0.5d * d2 * d3 * d4 * d4;
    }

    public static double e(double d, int i, double d2) {
        return e(d, c(d2, i), Math.atan(d2 / 10000.0d));
    }

    public static double c(double d, int i) {
        LogUtil.a("SlopeToPower", "calculationSpeedBySlope ratio is: ", Double.valueOf(d), "; rpm is :" + i);
        return (i / (((Math.max(0.0d, d) * 0.0338d) / 10.0d) + 3.333d)) / 3.6d;
    }

    public static double e(double d, double d2, double d3) {
        ReleaseLogUtil.e("R_SlopeToPower", "translateSlopeToPower: ", Double.valueOf(d2), "  degree:", Double.valueOf(d3));
        if (d <= 0.0d) {
            ReleaseLogUtil.d("R_SlopeToPower", "isWeightValid");
            return 0.0d;
        }
        if (d2 <= 0.0d) {
            return 0.0d;
        }
        double d4 = d + 8.0d;
        double d5 = d(0.005d, d4, d3);
        LogUtil.a("SlopeToPower", "frictional is ", Double.valueOf(d5));
        double d6 = d(0.63d, 1.226d, 0.65d, d2);
        LogUtil.a("SlopeToPower", "windResistance is ", Double.valueOf(d6));
        double c = c(d4, d3, d5, d6, 0.95d);
        LogUtil.a("SlopeToPower", "drivingForce is ", Double.valueOf(c));
        double max = Math.max(c * d2, 1.0d);
        ReleaseLogUtil.d("R_SlopeToPower", "translateSlopeToPower: ", Double.valueOf(max));
        return max;
    }

    private static double c(double d, double d2, double d3, double d4, double d5) {
        if (d5 <= 0.0d || d5 > 1.0d) {
            ReleaseLogUtil.d("R_SlopeToPower", "efficiency is invalids: ", Double.valueOf(d5));
            d5 = 0.95d;
        }
        return ((((d * 9.8d) * Math.sin(d2)) + d3) + d4) / d5;
    }

    private static double d(double d, double d2, double d3) {
        return d * d2 * 9.8d * Math.cos(d3);
    }
}
