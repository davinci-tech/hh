package defpackage;

import android.content.Context;
import com.huawei.hihealth.util.HiBroadcastUtil;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes7.dex */
public class ivc {

    /* renamed from: a, reason: collision with root package name */
    private static double f13622a = 1.0d;
    private static double b = 1.0d;
    private static boolean c = false;
    private static double d = 1.0d;
    private static boolean e = false;

    public static void b(Context context, double d2, double d3, double d4) {
        if (c) {
            a(d2 * d3 * d4);
            double d5 = d;
            if (d5 - f13622a < 1.0d || context == null) {
                return;
            }
            f13622a = d5;
            e(context, d5);
        }
    }

    private static void a(double d2) {
        if (d2 <= 0.0d) {
            return;
        }
        d = Math.min(d + d2, b);
    }

    public static void c(double d2, String str) {
        if (d2 <= 0.0d) {
            return;
        }
        LogUtil.a("Debug_HiSyncProcess", "setCurrentMaxProcess tag is ", str);
        double min = Math.min(b + d2, 99.0d);
        b = min;
        LogUtil.a("Debug_HiSyncProcess", "setCurrentMaxProcess currentMaxProcess is ", Double.valueOf(min));
    }

    public static void b(Context context) {
        if (context == null || !c) {
            return;
        }
        e(context, b);
        d = b;
    }

    public static void d(Context context) {
        if (context == null || !c) {
            return;
        }
        e(context, d);
    }

    public static void d(Context context, int i) {
        if (context == null) {
            return;
        }
        d(i == 0, true);
        e(context);
        e(context, 1.0d);
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        e(context, 99.0d);
        a(context);
        d(false, false);
        ism.d(false);
    }

    public static void a(Context context, int i) {
        if (context == null) {
            return;
        }
        f(context);
        if (i == 20000) {
            ism.d(false);
            ism.a(false);
            d(false, false);
        }
    }

    private static void d(boolean z, boolean z2) {
        d = 1.0d;
        b = 1.0d;
        f13622a = 1.0d;
        e = z;
        c = z2;
    }

    private static void e(Context context, double d2) {
        if (e) {
            HiBroadcastUtil.d(context, d2);
        }
    }

    private static void e(Context context) {
        if (e) {
            HiBroadcastUtil.c(context);
        }
    }

    private static void a(Context context) {
        if (e) {
            HiBroadcastUtil.f(context);
        }
    }

    private static void f(Context context) {
        if (e) {
            HiBroadcastUtil.j(context);
        }
    }

    public static void c(int i) {
        if (i == 0) {
            e = true;
        }
    }
}
