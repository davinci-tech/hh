package defpackage;

/* loaded from: classes4.dex */
public class hbb {
    public static boolean c(double d, double d2) {
        return Math.abs(d - 90.0d) < 1.0E-6d && Math.abs(d2 + 80.0d) < 1.0E-6d;
    }

    public static boolean e(double d, double d2) {
        return (Math.abs(d - d2) < 1.0E-6d && Math.abs(d2 * d) < 1.0E-6d) || d2 > 180.0d || d2 <= -180.0d || d <= -90.0d || d >= 90.0d;
    }
}
