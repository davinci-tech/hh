package defpackage;

import java.text.NumberFormat;

/* loaded from: classes.dex */
public class jed {
    public static int a(int i) {
        return (int) (i * 2.54d);
    }

    public static double b(int i) {
        return i / 1000.0d;
    }

    public static int d(int i) {
        return (int) Math.round(i / 2.54d);
    }

    public static double c(int i) {
        return b(i) * 0.62137119223733d;
    }

    public static String b(double d, int i, int i2) {
        if (i == 1) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            numberFormat.setMaximumFractionDigits(i2);
            numberFormat.setMinimumFractionDigits(i2);
            return numberFormat.format(d);
        }
        if (i != 2) {
            return "";
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMaximumFractionDigits(i2);
        percentInstance.setMinimumFractionDigits(i2);
        return percentInstance.format(d / 100.0d);
    }
}
