package defpackage;

/* loaded from: classes4.dex */
public class gvl {
    public static float c(float[] fArr) {
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f2 : fArr) {
            f += f2;
        }
        return f;
    }

    public static float b(float[] fArr) {
        float f = 0.0f;
        if (fArr == null) {
            return 0.0f;
        }
        for (float f2 : fArr) {
            f = (float) (f + Math.pow(f2, 2.0d));
        }
        return f;
    }

    public static float c(float[] fArr, float[] fArr2) {
        if (fArr == null || fArr2 == null) {
            return 1.0f;
        }
        float f = 0.0f;
        for (int i = 0; i < fArr.length; i++) {
            if (i < fArr2.length) {
                f += fArr[i] * fArr2[i];
            }
        }
        return f;
    }

    public static float a(float f, float f2, float f3, float f4) {
        return (float) (f / (Math.pow(2.718281828459045d, (-f2) * (f4 - f3)) + 1.0d));
    }
}
