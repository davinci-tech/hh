package defpackage;

import android.view.ViewConfiguration;
import com.huawei.hwcommonmodel.application.BaseApplication;

/* loaded from: classes9.dex */
public class oby {
    private static float c = (float) (Math.log(0.78d) / Math.log(0.9d));

    /* renamed from: a, reason: collision with root package name */
    private static float f15608a = ViewConfiguration.getScrollFriction();
    private static float e = ((BaseApplication.getContext().getResources().getDisplayMetrics().density * 160.0f) * 386.0878f) * 0.84f;

    private static double c(int i) {
        return Math.log((Math.abs(i) * 0.35f) / (f15608a * e));
    }

    private static double c(double d) {
        return ((c - 1.0d) * Math.log(d / (f15608a * e))) / c;
    }

    public static double a(int i) {
        double c2 = c(i);
        double d = c;
        return Math.exp(c2 * (d / (d - 1.0d))) * f15608a * e;
    }

    public static int d(double d) {
        return Math.abs((int) (((Math.exp(c(d)) * f15608a) * e) / 0.3499999940395355d));
    }
}
