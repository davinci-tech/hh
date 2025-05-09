package defpackage;

import android.view.animation.Interpolator;

/* loaded from: classes6.dex */
public class njq implements Interpolator {
    private static final float[] d = {0.0f, 0.030966187f, 0.06198731f, 0.09305969f, 0.12417969f, 0.15534362f, 0.18654786f, 0.24906251f, 0.31169432f, 0.3744141f, 0.5f, 0.6255859f, 0.6883057f, 0.75093746f, 0.8134521f, 0.84465635f, 0.87582034f, 0.90694034f, 0.9380127f, 0.96903384f, 1.0f};
    private static final float[] e = {0.0f, 0.0028686523f, 0.011230469f, 0.024719238f, 0.04296875f, 0.06561279f, 0.092285156f, 0.15625f, 0.2319336f, 0.31640625f, 0.5f, 0.68359375f, 0.7680664f, 0.84375f, 0.90771484f, 0.9343872f, 0.95703125f, 0.97528076f, 0.98876953f, 0.99713135f, 1.0f};

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int length = d.length - 1;
        int i = 0;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < d[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = d;
        float f2 = fArr[length] - fArr[i];
        if (Float.compare(f2, 0.0f) == 0) {
            return e[i];
        }
        float f3 = (f - fArr[i]) / f2;
        float[] fArr2 = e;
        float f4 = fArr2[i];
        return f4 + (f3 * (fArr2[length] - f4));
    }
}
