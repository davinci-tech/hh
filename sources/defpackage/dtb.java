package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes8.dex */
public class dtb {
    private static int e(int i) {
        if (i > 35) {
            return 60;
        }
        return (20 >= i || i > 35) ? 30 : 45;
    }

    public static int e(int i, int i2, int i3) {
        if (i < i2) {
            return 1;
        }
        return i > i3 ? -1 : 0;
    }

    public static float a(int i, float f) {
        LogUtil.a("HRControl_BikeHeartRateControl", "getHeartRatePowerFactor vo2Max = ", Integer.valueOf(i), ", weight = ", Float.valueOf(f));
        if (i <= 0) {
            return 1.1f;
        }
        float floatValue = new BigDecimal(i).multiply(new BigDecimal(Float.toString(f))).divide(new BigDecimal(2925), 1, RoundingMode.HALF_UP).floatValue();
        LogUtil.a("HRControl_BikeHeartRateControl", "getHeartRatePowerFactor heartRatePowerFactor = ", Float.valueOf(floatValue));
        return floatValue;
    }

    public static float b(int i, float f) {
        LogUtil.a("HRControl_BikeHeartRateControl", "getRpmPowerFactor vo2Max = ", Integer.valueOf(i), ", weight = ", Float.valueOf(f));
        float min = i <= 0 ? 16.0f : Math.min(32.0f, new BigDecimal(15).multiply(new BigDecimal(i)).multiply(new BigDecimal(Float.toString(f))).divide(new BigDecimal(2925), 1, RoundingMode.HALF_UP).floatValue());
        LogUtil.a("HRControl_BikeHeartRateControl", "getRpmPowerFactor rpmPowerFactor = ", Float.valueOf(min));
        return min;
    }

    public static int b(float f, int i) {
        LogUtil.a("HRControl_BikeHeartRateControl", "getRpmLimitPower rpmPowerFactor = ", Float.valueOf(f), ", currentRpm = ", Integer.valueOf(i));
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        BigDecimal bigDecimal2 = new BigDecimal(i);
        BigDecimal valueOf = BigDecimal.valueOf(-2.6917118d);
        BigDecimal valueOf2 = BigDecimal.valueOf(0.16503129d);
        BigDecimal valueOf3 = BigDecimal.valueOf(0.00830111d);
        BigDecimal valueOf4 = BigDecimal.valueOf(0.00525906d);
        BigDecimal valueOf5 = BigDecimal.valueOf(0.19709128d);
        int max = Math.max(20, valueOf.multiply(bigDecimal).add(valueOf2.multiply(bigDecimal2)).subtract(valueOf3.multiply(bigDecimal).multiply(bigDecimal)).add(valueOf4.multiply(bigDecimal2).multiply(bigDecimal2)).add(valueOf5.multiply(bigDecimal).multiply(bigDecimal2)).add(BigDecimal.valueOf(1.65261776753669d)).setScale(0, 4).intValue());
        LogUtil.a("HRControl_BikeHeartRateControl", "getRpmLimitPower rmpLimitPower = ", Integer.valueOf(max));
        return max;
    }

    public static dtc b(dtc dtcVar, int i, int i2, int i3, float f) {
        LogUtil.a("HRControl_BikeHeartRateControl", "getBikeHeartRateAdjustResult lastBikeAdjustResult = ", dtcVar.toString(), ", currentHeartRate = ", Integer.valueOf(i), ", targetHR = ", Integer.valueOf(i2), ", adjustOrientation = ", Integer.valueOf(i3), ", heartRatePowerFactor = ", Float.valueOf(f));
        int d = d(i, i2, i3, f);
        int e = e(d);
        dtcVar.b(i3);
        dtcVar.e(d);
        dtcVar.a(e);
        if (i3 != 0) {
            dtcVar.c(dtcVar.b() + 1);
            dtcVar.d(dtcVar.e() + Math.abs(d));
        }
        LogUtil.a("HRControl_BikeHeartRateControl", "getBikeHeartRateAdjustResult Result = ", dtcVar.toString());
        return dtcVar;
    }

    public static dtc d(dtc dtcVar, int i, int i2, int i3, float f) {
        LogUtil.a("HRControl_BikeHeartRateControl", "getBikeZoneAdjustResult lastBikeAdjustResult = ", dtcVar.toString(), ", currentHeartRate = ", Integer.valueOf(i), ", targetHR = ", Integer.valueOf(i2), ", adjustOrientation = ", Integer.valueOf(i3), ", heartRatePowerFactor = ", Float.valueOf(f));
        int d = d(i, i2, i3, f);
        int e = e(d);
        dtcVar.b(i3);
        dtcVar.e(d);
        dtcVar.a(e);
        LogUtil.a("HRControl_BikeHeartRateControl", "getBikeZoneAdjustResult Result = ", dtcVar.toString());
        return dtcVar;
    }

    private static int d(int i, int i2, int i3, float f) {
        if (i3 == 0) {
            return 0;
        }
        int intValue = new BigDecimal(i2 - i).multiply(new BigDecimal(Float.toString(f))).intValue();
        if (i3 == -1) {
            return Math.max(-100, intValue);
        }
        if (i3 != 1) {
            return 0;
        }
        return Math.min(50, intValue);
    }
}
