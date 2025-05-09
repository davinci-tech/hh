package defpackage;

import android.animation.TimeInterpolator;
import android.os.SystemClock;
import android.util.Pair;
import android.view.View;
import com.huawei.uikit.hwcommon.anim.HwCubicBezierInterpolator;

/* loaded from: classes7.dex */
public class sll {

    /* renamed from: a, reason: collision with root package name */
    private static long f17103a = 0;
    private static boolean b = false;
    private static boolean d = false;

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private boolean f17104a = true;
        private float b;
        private float c;
        private float d;
        private float e;
        private float g;

        public float a() {
            return this.g;
        }

        public float c() {
            return this.b;
        }

        public float d() {
            return this.c;
        }

        public void d(boolean z) {
            this.f17104a = z;
        }

        public float e() {
            return this.e;
        }

        public boolean f() {
            return this.f17104a;
        }

        public float b() {
            return this.d;
        }

        public void b(float f) {
            this.e = f;
        }

        public void c(float f) {
            this.b = f;
        }

        public void d(float f) {
            this.g = f;
        }

        public void e(float f) {
            this.c = f;
        }

        public void a(float f) {
            this.d = f;
        }
    }

    public static Pair<Float, Float> ecL_(TimeInterpolator timeInterpolator, float f, int i, float f2, float f3) {
        return new Pair<>(Float.valueOf(f2 + ((0.225f - (Math.min(i, 10) * 0.0125f)) * Math.abs(f) * f2)), Float.valueOf((((1.0f - timeInterpolator.getInterpolation(f)) * 0.19999999f) + 0.8f) * f3));
    }

    public static TimeInterpolator ecM_() {
        return new HwCubicBezierInterpolator(0.33f, 0.0f, 0.67f, 1.0f);
    }

    public static TimeInterpolator ecN_() {
        return new HwCubicBezierInterpolator(1.0f, 0.0f, 1.0f, 1.0f);
    }

    public static TimeInterpolator ecO_() {
        return new HwCubicBezierInterpolator(0.1f, 0.2f, 0.48f, 1.0f);
    }

    public static TimeInterpolator ecP_() {
        return new HwCubicBezierInterpolator(0.37f, 0.16f, 0.57f, 0.88f);
    }

    public static TimeInterpolator ecQ_() {
        return new HwCubicBezierInterpolator(0.7f, 0.12f, 0.78f, 0.27f);
    }

    public static TimeInterpolator ecR_() {
        return new HwCubicBezierInterpolator(0.37f, 0.16f, 0.0f, 1.03f);
    }

    public static TimeInterpolator ecS_() {
        return new HwCubicBezierInterpolator(0.2f, 0.05f, 0.45f, 1.13f);
    }

    public static TimeInterpolator ecT_() {
        return new HwCubicBezierInterpolator(0.65f, -0.04f, 0.57f, 2.0f);
    }

    public static TimeInterpolator g() {
        return new HwCubicBezierInterpolator(0.97f, 0.0f, 0.96f, 0.51f);
    }

    public static boolean k() {
        return b;
    }

    public static boolean m() {
        return d;
    }

    public static boolean d(e eVar, float f, boolean z, boolean z2, boolean z3) {
        float b2 = f - eVar.b();
        if (Math.abs(b2) < eVar.d()) {
            return false;
        }
        return ((b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) > 0 && z3 && !z) || ((b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) < 0 && z2 && !z) || ((b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) < 0 && z3 && z) || ((b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) > 0 && z2 && z);
    }

    public static int e(int i, float f) {
        return (int) (f * (((0.225f - (Math.min(i, 10) * 0.0125f)) * 2.0f) + 1.0f));
    }

    public static float e(float f, float f2) {
        float f3 = f + (f2 * 0.05f);
        if (Float.compare(f3, 1.0f) >= 0) {
            return 0.95f;
        }
        return f3;
    }

    public static float a(TimeInterpolator timeInterpolator, TimeInterpolator timeInterpolator2) {
        float f = 0.0f;
        if (timeInterpolator != null && timeInterpolator2 != null) {
            float f2 = 0.0f;
            for (int i = 0; i < 100; i += 5) {
                float f3 = i / 100.0f;
                float abs = Math.abs(timeInterpolator.getInterpolation(f3) - timeInterpolator2.getInterpolation(f3));
                if (abs > f2) {
                    f = f3;
                    f2 = abs;
                }
            }
        }
        return f;
    }

    public static TimeInterpolator ecJ_() {
        return new HwCubicBezierInterpolator(0.2f, 0.0f, 1.0f, 1.0f);
    }

    public static boolean a(e eVar, float f, boolean z, boolean z2, boolean z3) {
        float b2 = f - eVar.b();
        return (!z && z3 && (b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) > 0) || (z && z3 && (b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) < 0) || (!z && z2 && (b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) < 0) || (z && z2 && (b2 > 0.0f ? 1 : (b2 == 0.0f ? 0 : -1)) > 0);
    }

    public static boolean a(slg slgVar, boolean z, float f, float f2) {
        float q = slgVar.q();
        float t = slgVar.t();
        float p = slgVar.p();
        float r = slgVar.r();
        return z ? f >= p && f < q && f2 >= t && f2 < r : f >= q && f < p && f2 >= t && f2 < r;
    }

    public static int d(slg slgVar, float f, float f2, float f3, float f4) {
        float[] g = slgVar.g();
        if (g == null) {
            return -1;
        }
        int length = g.length;
        for (int i = 0; i < length; i++) {
            float sqrt = (float) Math.sqrt(Math.pow(g[i] - f3, 2.0d) + Math.pow(slgVar.i() - f4, 2.0d));
            if ((slgVar.d() == i && Float.compare(sqrt, f) <= 0) || Float.compare(sqrt, f2) <= 0) {
                return i;
            }
        }
        return -1;
    }

    public static void e(boolean z) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long j = f17103a;
        if (j == 0) {
            f17103a = uptimeMillis;
            return;
        }
        if (!z) {
            d = false;
            b = false;
            return;
        }
        long j2 = uptimeMillis - j;
        if (j2 < 200) {
            b = true;
        } else {
            b = false;
        }
        if (j2 < 100) {
            d = true;
        } else {
            d = false;
        }
        f17103a = uptimeMillis;
    }

    public static Pair<Integer, Integer> ecK_(int i, int i2, int i3, int i4) {
        int size = View.MeasureSpec.getSize(i);
        if (View.MeasureSpec.getMode(i) != Integer.MIN_VALUE) {
            i3 = size;
        }
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE || mode == 0) {
            i4 = Math.min(size2, i4);
        } else if (mode == 1073741824) {
            i4 = size2;
        }
        return new Pair<>(Integer.valueOf(i3), Integer.valueOf(i4));
    }
}
