package health.compact.a;

import com.huawei.haf.common.exception.ExceptionUtils;
import java.util.Locale;

/* loaded from: classes.dex */
public class LogAnonymous {
    private static boolean b = false;
    private static double c = 0.0d;
    private static double d = 0.0d;
    private static boolean e = false;

    private LogAnonymous() {
    }

    public static void d() {
        b = true;
        e = true;
        d = 0.0d;
        c = 0.0d;
    }

    public static void b() {
        b = false;
    }

    public static double[] a(double d2, double d3) {
        if (!b) {
            com.huawei.hwlogsmodel.LogUtil.h("LogAnonymous", "please call startSport");
            return new double[2];
        }
        if (e) {
            c = d2;
            d = d3;
            e = false;
        }
        return new double[]{d2 - c, d3 - d};
    }

    public static String d(long j) {
        String valueOf;
        if (j < 100000000) {
            valueOf = String.format(Locale.ROOT, "%09d", Long.valueOf(j));
        } else {
            valueOf = String.valueOf(j);
        }
        StringBuilder sb = new StringBuilder(9);
        sb.append("***");
        sb.append(valueOf.substring(valueOf.length() - 6, valueOf.length() - 3));
        sb.append("***");
        return sb.toString();
    }

    public static String b(int i) {
        String valueOf;
        if (i < 1000000) {
            valueOf = String.format(Locale.ROOT, "%06d", Integer.valueOf(i));
        } else {
            valueOf = String.valueOf(i);
        }
        StringBuilder sb = new StringBuilder(6);
        sb.append("***");
        sb.append(valueOf.substring(valueOf.length() - 3, valueOf.length()));
        return sb.toString();
    }

    public static String e(String str) {
        if (str == null || str.length() < 4) {
            return str;
        }
        StringBuilder sb = new StringBuilder(16);
        sb.append("***");
        sb.append(str.substring(4, str.length()));
        return sb.toString();
    }

    public static String b(Throwable th) {
        return ExceptionUtils.d(th);
    }

    public static String b(Object obj) {
        return "***";
    }
}
