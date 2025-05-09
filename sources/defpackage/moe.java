package defpackage;

import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;

/* loaded from: classes.dex */
public class moe {
    public static float a(float f) {
        return ((int) (f * 10.0f)) / 10.0f;
    }

    public static float b(float f) {
        return f / 1000.0f;
    }

    public static float c(float f) {
        return f * 3.6f;
    }

    public static int c(int i) {
        return i * 1000;
    }

    public static float d(int i) {
        return i / 1000.0f;
    }

    public static double e(int i) {
        return (i * 3.5d) / 65536.0d;
    }

    public static int e(float f) {
        if (f <= 1.0E-6f) {
            return Integer.MAX_VALUE;
        }
        return (int) (1000.0f / f);
    }

    public static int h(float f) {
        return (int) (f * 1000.0f);
    }

    public static int j(float f) {
        return (int) (f * 1000.0f);
    }

    public static String c(double d) {
        return UnitUtil.a(d, 1, 2, false);
    }

    public static String d(double d) {
        return c(CommonUtil.a(UnitUtil.a(d, 1, 3, false)));
    }

    public static String j(double d) {
        return UnitUtil.e(d, 1, 2);
    }

    public static String d(float f) {
        return UnitUtil.e(Math.round(f / 1000.0f), 1, 0);
    }

    public static String i(float f) {
        return UnitUtil.e(f, 1, 0);
    }

    public static String g(float f) {
        return UnitUtil.e(f / 60000.0f, 1, 0);
    }

    public static String k(float f) {
        return UnitUtil.e(f / 60.0f, 1, 0);
    }

    public static String f(float f) {
        return UnitUtil.e(f / 1000.0f, 1, 0);
    }

    public static double b(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            d = 0.0d;
        }
        return new BigDecimal(d).setScale(3, 1).floatValue();
    }

    public static int a(int i) {
        return i / 60;
    }

    public static int b(int i) {
        return i % 60;
    }

    public static float e(double d) {
        return (int) Math.round(d);
    }

    public static float a(double d) {
        return new BigDecimal(d).setScale(1, 1).floatValue();
    }

    public static double g(double d) {
        return UnitUtil.h() ? UnitUtil.e(d, 3) : d;
    }
}
