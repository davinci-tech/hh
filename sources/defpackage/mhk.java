package defpackage;

import java.math.BigDecimal;

/* loaded from: classes6.dex */
public class mhk {
    private static float a(short s) {
        float f;
        float f2;
        if (s == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (s < 60) {
            return (s * 10.0f) / 60.0f;
        }
        if (s < 80) {
            return (((s - 60) * 10.0f) / 20.0f) + 10.0f;
        }
        if (s < 90) {
            return (((s - 80) * 10.0f) / 10.0f) + 20.0f;
        }
        if (s < 100) {
            f = ((s - 90) * 10.0f) / 10.0f;
            f2 = 30.0f;
        } else if (s < 110) {
            f = s - 100.0f;
            f2 = 40.0f;
        } else {
            if (s >= 120) {
                return 60.0f;
            }
            f = ((s - 110) * 10.0f) / 10.0f;
            f2 = 50.0f;
        }
        return f + f2;
    }

    private static boolean b(short s) {
        return s >= 90 && s < 120;
    }

    private static boolean d(short s) {
        return s >= 60 && s < 80;
    }

    private static float e(short s) {
        float f;
        float f2;
        if (s == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (s < 90) {
            return (s * 10.0f) / 90.0f;
        }
        if (s < 120) {
            return (((s - 90) * 10.0f) / 30.0f) + 10.0f;
        }
        if (s < 140) {
            return (((s - 120) * 10.0f) / 20.0f) + 20.0f;
        }
        if (s < 160) {
            return (((s - 140) * 10.0f) / 20.0f) + 30.0f;
        }
        if (s < 180) {
            f = ((s - 160) * 10.0f) / 20.0f;
            f2 = 40.0f;
        } else {
            if (s >= 200) {
                return 60.0f;
            }
            f = ((s - 180) * 10.0f) / 20.0f;
            f2 = 50.0f;
        }
        return f + f2;
    }

    public static int e(short s, short s2) {
        return c(c(s, s2));
    }

    public static int c(float f) {
        if (Math.abs(f - Float.MIN_VALUE) < 1.0E-6d) {
            return -32768;
        }
        int i = 0;
        if (f >= d(0)) {
            i = 1;
            if (f >= d(1)) {
                i = 2;
                if (f >= d(2)) {
                    i = 3;
                    if (f >= d(3)) {
                        i = 4;
                        if (f >= d(4)) {
                            return 5;
                        }
                    }
                }
            }
        }
        return i;
    }

    public static float d(int i) {
        float floatValue = new BigDecimal((i + 1) * 10.0f).setScale(2, 4).floatValue();
        if (i == -32768) {
            return Float.MIN_VALUE;
        }
        return floatValue;
    }

    public static float c(short s, short s2) {
        float a2;
        if (s == Short.MIN_VALUE || s2 == Short.MIN_VALUE) {
            return Float.MIN_VALUE;
        }
        if (d(s2)) {
            a2 = e(s);
        } else if (b(s)) {
            a2 = a(s2);
        } else if (s2 >= 80 && s >= 120) {
            a2 = e(s) > a(s2) ? e(s) : a(s2);
        } else if (s2 < 60 && s < 90) {
            a2 = e(s) < a(s2) ? e(s) : a(s2);
        } else {
            a2 = a(s2);
        }
        return new BigDecimal(a2).setScale(2, 4).floatValue();
    }
}
