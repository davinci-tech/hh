package defpackage;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes9.dex */
public class nqe {
    private static float d(int i) {
        int i2 = i - 4;
        float f = i2 > 0 ? 1.0f - (i2 * 0.2f) : 1.0f;
        if (f > 0.0f) {
            return f;
        }
        return 0.2f;
    }

    private static boolean d(int i, int i2) {
        return i2 + (-2) >= 0 && i2 + 3 <= i;
    }

    public static float a(float[] fArr, int i, float f) {
        int length = fArr.length;
        int length2 = String.valueOf(jed.b(f, 1, 2)).length();
        if (length < 6 || length2 < 4) {
            return 0.0f;
        }
        if (d(fArr, i) != 4) {
            return Utils.convertDpToPixel(0.0f);
        }
        if (d(fArr, i, 4) > 0.6f) {
            return Utils.convertDpToPixel(22.0f);
        }
        return 0.0f;
    }

    public static float b(float[] fArr, int i, float f) {
        int d;
        if (fArr.length >= 4 && (d = d(fArr, i)) != 3 && d != 4) {
            int length = String.valueOf(jed.b(f, 1, 2)).length();
            if (d(fArr, i, d) > d(length)) {
                if (d == 1 || d == 5) {
                    return Utils.convertDpToPixel(-(length + 4.0f));
                }
                if (d == 6) {
                    return Utils.convertDpToPixel(length + 4.0f);
                }
                if (d == 2) {
                    return Utils.convertDpToPixel(0.0f);
                }
            }
        }
        return 0.0f;
    }

    private static int d(float[] fArr, int i) {
        if (!d(fArr.length, i)) {
            return i - 2 >= 0 ? 2 : 1;
        }
        float f = fArr[i - 1];
        float f2 = fArr[i + 1];
        float f3 = fArr[i + 3] - f2;
        return f - f2 > 0.0f ? f3 > 0.0f ? 3 : 5 : f3 < 0.0f ? 4 : 6;
    }

    private static float d(float[] fArr, int i, int i2) {
        if (i2 == 1 || i2 == 5) {
            return (fArr[i + 1] - fArr[i + 3]) / (fArr[i + 2] - fArr[i]);
        }
        if (i2 == 2 || i2 == 6) {
            return (fArr[i + 1] - fArr[i - 1]) / (fArr[i] - fArr[i - 2]);
        }
        if (i2 != 4) {
            return 1.0f;
        }
        float f = fArr[i + 1];
        float f2 = fArr[i - 1];
        float f3 = fArr[i];
        return ((f - f2) / (f3 - fArr[i + (-2)]) > 0.6f || (f - fArr[i + 3]) / (fArr[i + 2] - f3) > 0.6f) ? 1.0f : 0.0f;
    }
}
