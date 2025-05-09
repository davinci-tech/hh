package defpackage;

/* loaded from: classes4.dex */
public class gvm {
    public static float a(float[] fArr, float[] fArr2) {
        int length;
        if (fArr == null || (length = fArr.length) == 1) {
            return 0.0f;
        }
        float f = length;
        float b = (float) ((gvl.b(fArr) * f) - Math.pow(gvl.c(fArr), 2.0d));
        if (b == 0.0f) {
            return 0.0f;
        }
        return ((f * gvl.c(fArr, fArr2)) - (gvl.c(fArr) * gvl.c(fArr2))) / b;
    }

    public static float b(float[] fArr, float[] fArr2, float f) {
        int length;
        if (fArr == null || (length = fArr.length) == 1 || length == 0) {
            return 1.0f;
        }
        float f2 = length;
        return (gvl.c(fArr2) / f2) - ((f * gvl.c(fArr)) / f2);
    }
}
