package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class gvs {
    private static final List<Double> b = Collections.unmodifiableList(Arrays.asList(Double.valueOf(0.92d), Double.valueOf(0.8464d), Double.valueOf(0.778688d), Double.valueOf(0.716393d), Double.valueOf(0.659082d), Double.valueOf(0.606355d), Double.valueOf(0.557847d), Double.valueOf(0.513219d), Double.valueOf(0.472161d), Double.valueOf(0.434388d), Double.valueOf(0.399637d), Double.valueOf(0.36766d)));
    private int e = 0;

    /* renamed from: a, reason: collision with root package name */
    private int f12965a = 0;
    private int[] d = new int[60];
    private int c = 0;

    private int a(double d) {
        int i = d <= 1.0d ? 4 : d <= 2.0d ? 3 : d <= 3.0d ? -3 : d <= 4.0d ? -6 : -9;
        if (i < 3) {
            return 3;
        }
        return i;
    }

    private double c(int i, double d) {
        return i * 12 * d;
    }

    public void a(int i) {
        int i2 = this.c;
        if (i2 < 60) {
            this.d[i2] = i;
            this.c = i2 + 1;
        } else {
            d(i);
        }
        ReleaseLogUtil.e("Track_CalcStepRateManager", "saveDataByStepChange last time stamp");
    }

    private void d(int i) {
        int i2 = 0;
        while (true) {
            int[] iArr = this.d;
            if (i2 < iArr.length - 1) {
                int i3 = i2 + 1;
                iArr[i2] = iArr[i3];
                i2 = i3;
            } else {
                iArr[iArr.length - 1] = i;
                return;
            }
        }
    }

    public void e() {
        int i = this.c;
        if (i <= 10) {
            LogUtil.a("Track_CalcStepRateManager", "dealCalcRealStepRate size<2");
            return;
        }
        int i2 = (i - 1) / 5;
        e(i2, a(Math.max(Math.max(b(i), c(i)), g(i))), i);
    }

    private void e(int i, int i2, int i3) {
        if (i >= i2) {
            i = i2;
        }
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i4 = 0; i4 < i; i4++) {
            int a2 = a(i3, i4);
            List<Double> list = b;
            d += c(a2, list.get(i4).doubleValue());
            d2 += list.get(i4).doubleValue();
        }
        e(d, d2);
    }

    private int a(int i, int i2) {
        int[] iArr = this.d;
        return iArr[(i - 1) - (i2 * 5)] - iArr[(i - ((i2 + 1) * 5)) - 1];
    }

    private void e(double d, double d2) {
        int e = e(Math.round((float) (d / d2)));
        this.e = e;
        this.f12965a = e;
    }

    private int e(int i) {
        if (i < 0) {
            LogUtil.h("Track_CalcStepRateManager", "checkStepRateValue exception", Integer.valueOf(i));
            return this.f12965a;
        }
        if (i <= 300) {
            return i;
        }
        LogUtil.h("Track_CalcStepRateManager", "checkStepRateValue exception", 0);
        return 0;
    }

    private double b(int i) {
        return Math.abs(a(i, 0) - a(i, 1)) * 1.0d;
    }

    private double c(int i) {
        return Math.abs(a(i, 0) - (i > 15 ? a(i, 2) : 0)) * 0.9d;
    }

    private double g(int i) {
        return Math.abs(a(i, 0) - (i > 20 ? a(i, 3) : 0)) * 0.8333333333333334d;
    }

    public int d() {
        LogUtil.a("Track_CalcStepRateManager", "getCurrentStepRate = ", Integer.valueOf(this.e));
        return this.e;
    }
}
