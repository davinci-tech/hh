package defpackage;

import androidx.core.util.Pair;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dth {
    public static int a(int i, int i2) {
        if (i2 < 70) {
            i = 70;
        }
        return i2 >= 210 ? a.C : i;
    }

    public static int b(int i) {
        return i >= 210 ? a.C : i >= 70 ? i : 70;
    }

    public static boolean e(int i) {
        return (i == 0 || i == 255) ? false : true;
    }

    public static dtg e(float f, float f2) {
        return new dtg((Float.compare(f2, 0.8f) < 0 || Float.compare(f2 - 0.8f, 5.0f) <= 0) ? f2 : 5.8f, a(f, f2));
    }

    public static dtg c(float f, float f2) {
        float e;
        LogUtil.a("HRControl_CalculatorUtil", "stageChangeAdjust: input, currentSpeed = ", Float.valueOf(f), " targetSpeed =", Float.valueOf(f2));
        float f3 = f - f2;
        if (Math.abs(f3) >= 1.0E-6d) {
            if (f < f2) {
                float f4 = f2 - f;
                if (f4 > 0.0f && f4 <= 3.0f) {
                    e = BigDecimal.valueOf(Math.max(f2, f + 0.5d)).floatValue();
                } else {
                    e = e(f, f2);
                }
                LogUtil.a("HRControl_CalculatorUtil", "stageChangeAdjust upper  nextSpeed ", Float.valueOf(e), " currentSpeed ", Float.valueOf(f), " nextDuration ", 30);
            } else {
                if (f3 > 0.0f && f3 <= 3.0f) {
                    e = BigDecimal.valueOf(Math.min(f2, f - 0.5d)).floatValue();
                } else {
                    e = e(f, f2);
                }
                LogUtil.a("HRControl_CalculatorUtil", "stageChangeAdjust lower  nextSpeed ", Float.valueOf(e), " currentSpeed ", Float.valueOf(f), " nextDuration ", 30);
            }
            f = e;
        }
        LogUtil.c("HRControl_CalculatorUtil", "stageChangeAdjust: output, nextStepSpeed = ", Float.valueOf(f), " nextDuration =", 30);
        return new dtg(f, 30);
    }

    public static dtg b(float f, int i, Pair<Integer, Integer> pair) {
        LogUtil.c("HRControl_CalculatorUtil", "heartRateChangeAdjustDynamic: input, currentSpeed = ", Float.valueOf(f), " currentHeartRate =", Integer.valueOf(i), " heartRateScope = ", pair);
        int i2 = 30;
        if (pair == null) {
            LogUtil.h("HRControl_CalculatorUtil", "heartRateChangeAdjustDynamic: heartRateScope is null");
            return new dtg(f, 30);
        }
        if (i > pair.second.intValue()) {
            f -= (((i - pair.second.intValue()) / 10.0f) + 1.0f) * 0.5f;
        } else {
            if (i < pair.first.intValue()) {
                f += (((pair.first.intValue() - i) / 10.0f) + 1.0f) * 0.5f;
            }
            LogUtil.c("HRControl_CalculatorUtil", "heartRateChangeAdjustDynamic: output, nextSpeed = ", Float.valueOf(f), " nextDuration =", Integer.valueOf(i2));
            return new dtg(f, i2);
        }
        i2 = 20;
        LogUtil.c("HRControl_CalculatorUtil", "heartRateChangeAdjustDynamic: output, nextSpeed = ", Float.valueOf(f), " nextDuration =", Integer.valueOf(i2));
        return new dtg(f, i2);
    }

    public static dtg e(float f, int i, Pair<Integer, Integer> pair, float f2, int i2) {
        LogUtil.c("HRControl_CalculatorUtil", "heartRateChangeAdjustStatic: input, currentSpeed = ", Float.valueOf(f), " currentHeartRate =", Integer.valueOf(i), " heartRateScope =", pair, " adjSpeedMax = ", Float.valueOf(f2), " stableTime = ", Integer.valueOf(i2));
        if (pair == null) {
            LogUtil.h("HRControl_CalculatorUtil", "heartRateChangeAdjustStatic: heartRateScope is null");
            return new dtg(f, i2);
        }
        if (i > pair.second.intValue()) {
            f -= f2;
        } else if (i < pair.first.intValue()) {
            f += f2;
        }
        LogUtil.c("HRControl_CalculatorUtil", "heartRateChangeAdjustStatic: output, nextSpeed = ", Float.valueOf(f), " nextDuration =", Integer.valueOf(i2));
        return new dtg(f, i2);
    }

    public static float b(float f, float f2, float f3, Pair<Float, Float> pair, float f4) {
        if (pair == null) {
            return f;
        }
        LogUtil.c("HRControl_CalculatorUtil", "getStageChangeCorrectSpeed: speed = ", Float.valueOf(f), " nextSpeed = ", Float.valueOf(f2), " currentSpeedHatStable = ", Float.valueOf(f3), " minTargetSpeed = ", pair.first, " maxTargetSpeed = ", pair.second, " targetSpeed ", Float.valueOf(f4));
        float max = Math.max(0.0f, (f + f2) - f3);
        if (Float.compare(f4, f3) > 0) {
            max = Math.max(max, f4);
        } else if (Float.compare(f3, f4) > 0) {
            max = Math.min(max, f4);
        }
        return b(max, pair);
    }

    public static float e(float f, float f2, float f3, Pair<Float, Float> pair) {
        if (pair == null) {
            return f;
        }
        LogUtil.c("HRControl_CalculatorUtil", "getRateAdjustCorrectSpeed: speed = ", Float.valueOf(f), " nextSpeed = ", Float.valueOf(f2), " currentSpeedHatStable = ", Float.valueOf(f3), " minTargetSpeed = ", pair.first, " maxTargetSpeed = ", pair.second);
        float b = b((f2 + f) - f3, pair);
        if (Float.compare(Math.abs(f - b), 2.0f) > 0) {
            b = ((Float.compare(b, f) > 0 ? 1 : -1) * 2.0f) + f;
        }
        LogUtil.c("HRControl_CalculatorUtil", "getRateAdjustCorrectSpeed: finalSpeed = ", Float.valueOf(b));
        return d(b, 1);
    }

    public static float e(List<Integer> list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        Iterator<Integer> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!e(it.next().intValue())) {
                i++;
            }
        }
        float f = i;
        LogUtil.c("HRControl_CalculatorUtil", "heartRateList is ", list, "invalid radio is ", Float.valueOf(f / list.size()));
        return f / list.size();
    }

    public static float c(List<Integer> list) {
        float f = 0.0f;
        if (koq.b(list)) {
            return 0.0f;
        }
        int size = list.size();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (e(intValue)) {
                f += intValue;
            } else {
                size--;
            }
        }
        float f2 = size != 0 ? f / size : 255.0f;
        LogUtil.c("HRControl_CalculatorUtil", "heartRateList is ", list, " average heart rate is ", Float.valueOf(f2));
        return f2;
    }

    public static int d(List<Integer> list) {
        if (koq.b(list)) {
            return a.C;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            if (e(list.get(size).intValue())) {
                return list.get(size).intValue();
            }
        }
        return a.C;
    }

    public static float d(double d, int i) {
        return new BigDecimal(d).setScale(i, 4).floatValue();
    }

    public static float b(float f, float f2) {
        if (Float.compare(Math.abs(f - f2), 5.0f) <= 0) {
            return f2;
        }
        LogUtil.a("HRControl_CalculatorUtil", "difference in speed exceeds 5, currentSpeed ", Float.valueOf(f), " adjustSpeed ", Float.valueOf(f2));
        return f;
    }

    private static float b(float f, Pair<Float, Float> pair) {
        if (pair == null) {
            return f;
        }
        if (Float.compare(f, pair.first.floatValue()) < 0) {
            return pair.first.floatValue();
        }
        return Float.compare(f, pair.second.floatValue()) > 0 ? pair.second.floatValue() : f;
    }

    private static float e(double d, double d2) {
        return d(BigDecimal.valueOf(d).multiply(BigDecimal.valueOf(0.39749135d)).add(BigDecimal.valueOf(d2).multiply(BigDecimal.valueOf(0.57223183d))).subtract(BigDecimal.valueOf(0.190311418685124d)).doubleValue(), 1);
    }

    private static int a(double d, double d2) {
        return (int) Math.round(BigDecimal.valueOf(d).multiply(BigDecimal.valueOf(-0.57801655d)).add(BigDecimal.valueOf(d2).multiply(BigDecimal.valueOf(0.75211457d))).add(BigDecimal.valueOf(d * d).multiply(BigDecimal.valueOf(0.10304741d))).add(BigDecimal.valueOf(d2 * d2).multiply(BigDecimal.valueOf(0.02739331d))).add(BigDecimal.valueOf(d * d2).multiply(BigDecimal.valueOf(-0.14303393d))).add(BigDecimal.valueOf(11.0d)).doubleValue());
    }
}
