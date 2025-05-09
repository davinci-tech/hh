package defpackage;

/* loaded from: classes6.dex */
public class pjm {
    private static final float[] c = {60.0f, 70.0f, 80.0f, 85.0f, 90.0f, 95.0f, 100.0f};

    public static int c(float f) {
        if (f <= 100.0f && f < 90.0f) {
            return f >= 80.0f ? 70 : 60;
        }
        return 85;
    }

    public static float[] a() {
        return (float[]) c.clone();
    }
}
