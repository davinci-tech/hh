package defpackage;

import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;

/* loaded from: classes3.dex */
public class dtp {
    public static int a(int i) {
        if (i < 40 || i > 85) {
            return 65;
        }
        return i;
    }

    public static int c(int i) {
        if (i <= 0) {
            return 35;
        }
        return i;
    }

    public static int e(int i) {
        return (i < 80 || i > 250) ? MachineControlPointResponse.OP_CODE_EXTENSION_SET_UNLOCK_CODE : i;
    }

    public static float e(float f) {
        if (Float.compare(f, 30.0f) < 0 || Float.compare(f, 200.0f) > 0) {
            return 70.0f;
        }
        return f;
    }

    public static float e(float f, int i) {
        float e = e(f);
        BigDecimal divide = new BigDecimal(e(i)).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
        return new BigDecimal(Float.toString(e)).divide(divide.multiply(divide), RoundingMode.HALF_UP).floatValue();
    }

    public static int b(int i) {
        if (i <= 0) {
            return 0;
        }
        return Math.min(90, Math.max(20, i));
    }

    public static float e(float f, int i, float f2, int i2) {
        int b = b(i);
        float e = e(f2, i2);
        if (Float.compare(f, 1.0E-6f) > 0) {
            return new BigDecimal(Float.toString(f)).subtract(BigDecimal.valueOf(1.5d)).floatValue();
        }
        if (b > 0) {
            return new BigDecimal(i - 13).subtract(BigDecimal.valueOf(1.5d)).floatValue();
        }
        if (e > 25.2f) {
            return 25.0f;
        }
        return e <= 24.0f ? 30.0f : 27.0f;
    }

    public static int d(int i, int i2) {
        return (i < 155 || i > 200) ? 209 - BigDecimal.valueOf(0.7d).multiply(new BigDecimal(c(i2))).intValue() : i;
    }
}
