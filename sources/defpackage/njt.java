package defpackage;

import android.view.animation.Interpolator;

/* loaded from: classes6.dex */
public class njt implements Interpolator {
    private static final float[] c = {0.0f, 0.01819458f, 0.03540039f, 0.051800538f, 0.06757812f, 0.08291626f, 0.09799805f, 0.12812501f, 0.15942383f, 0.19335938f, 0.2313965f, 0.275f, 0.32563478f, 0.38476562f, 0.45385742f, 0.534375f, 0.5793762f, 0.6277832f, 0.67977905f, 0.7355469f, 0.7952698f, 0.85913086f, 0.9273132f, 1.0f};
    private static final float[] d = {0.0f, 0.0028686523f, 0.011230469f, 0.024719238f, 0.04296875f, 0.06561279f, 0.092285156f, 0.15625f, 0.2319336f, 0.31640625f, 0.40673828f, 0.5f, 0.5932617f, 0.68359375f, 0.7680664f, 0.84375f, 0.8773804f, 0.90771484f, 0.9343872f, 0.95703125f, 0.97528076f, 0.98876953f, 0.99713135f, 1.0f};

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int length = c.length - 1;
        int i = 0;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < c[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = c;
        float f2 = fArr[length] - fArr[i];
        if (Float.compare(f2, 0.0f) == 0) {
            return d[i];
        }
        float f3 = (f - fArr[i]) / f2;
        float[] fArr2 = d;
        float f4 = fArr2[i];
        return f4 + (f3 * (fArr2[length] - f4));
    }
}
