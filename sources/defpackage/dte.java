package defpackage;

import android.util.Pair;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes3.dex */
public class dte {
    public static float b(int i, float f, int i2) {
        LogUtil.a("HRControl_TreadmillHeartRateControl", "getSpeed heartRate = ", Integer.valueOf(i), ", runData = ", Float.valueOf(f), ", age = ", Integer.valueOf(i2));
        if (i2 <= 0) {
            LogUtil.h("HRControl_TreadmillHeartRateControl", "getSpeed age = ", Integer.valueOf(i2), " is invalid");
            return 0.0f;
        }
        BigDecimal divide = new BigDecimal(i).divide(new BigDecimal(a.A).subtract(BigDecimal.valueOf(0.7d).multiply(new BigDecimal(i2))), 4, 4).subtract(BigDecimal.valueOf(0.1578d)).divide(BigDecimal.valueOf(0.855d), RoundingMode.HALF_UP);
        BigDecimal bigDecimal = new BigDecimal(Float.toString(f));
        float d = dth.d(Math.min(26.5267f, Math.max(3.5f, BigDecimal.valueOf(2.5343982549851347d).add(BigDecimal.valueOf(4.53708116d).multiply(divide).multiply(divide)).add(BigDecimal.valueOf(-5.02772615d).multiply(divide)).add(BigDecimal.valueOf(-2.41491498E-4d).multiply(bigDecimal).multiply(bigDecimal)).add(BigDecimal.valueOf(-0.0339425085d).multiply(bigDecimal)).add(BigDecimal.valueOf(-0.169612435d).multiply(divide).multiply(divide).multiply(bigDecimal)).add(BigDecimal.valueOf(-2.08856729E-4d).multiply(divide).multiply(bigDecimal).multiply(bigDecimal)).add(BigDecimal.valueOf(0.495582668d).multiply(divide).multiply(bigDecimal)).floatValue())), 1);
        LogUtil.a("HRControl_TreadmillHeartRateControl", "getSpeed finalSpeed = ", Float.valueOf(d));
        return d;
    }

    public static float b(int i, float f, int i2, float f2) {
        LogUtil.a("HRControl_TreadmillHeartRateControl", "getCourseSpeedLimit maxHeartRate = ", Integer.valueOf(i), ", runData = ", Float.valueOf(f), ", age = ", Integer.valueOf(i2), ", machineSpeedLimit = ", Float.valueOf(f2));
        return Math.min(f2, b(BigDecimal.valueOf(0.95d).multiply(new BigDecimal(i)).intValue(), f, i2));
    }

    public static Pair<Float, Float> ZQ_(Pair<Integer, Integer> pair, int i, float f, int i2, float f2) {
        LogUtil.a("HRControl_TreadmillHeartRateControl", "getCourseZoneSpeedScope courseZoneHeartRateScope = ", pair, ", maxHeartRate = ", Integer.valueOf(i), ", runData = ", Float.valueOf(f), ", age = ", Integer.valueOf(i2), ", machineSpeedLimit = ", Float.valueOf(f2));
        float b = b(i, f, i2, f2);
        if (pair == null) {
            LogUtil.h("HRControl_TreadmillHeartRateControl", "courseZoneHeartRateScope == null");
            return new Pair<>(Float.valueOf(0.0f), Float.valueOf(b));
        }
        float b2 = b(((Integer) pair.first).intValue() - 25, f, i2);
        float b3 = b(((Integer) pair.second).intValue() + 15, f, i2);
        if (b <= b3) {
            b2 = b - (b3 - b2);
        } else {
            b = b3;
        }
        Pair<Float, Float> pair2 = new Pair<>(Float.valueOf(b2), Float.valueOf(b));
        LogUtil.a("HRControl_TreadmillHeartRateControl", "getCourseZoneSpeedScope courseZoneSpeedScope = ", pair2.toString());
        return pair2;
    }
}
