package defpackage;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Calendar;
import java.util.TimeZone;

/* loaded from: classes6.dex */
public class nrq {
    private static nrq b = new nrq();
    private int c;
    private int e;

    public static long c(long j, long j2, int i) {
        if (j != 0) {
            return j2 == 0 ? j : i != 0 ? (i == 1 && j >= j2) ? j : j2 : j < j2 ? j : j2;
        }
        if (j2 == 0) {
            return -1L;
        }
        return j2;
    }

    public static int d(int i, int i2, int i3) {
        return i == -1 ? i2 : i3 != 0 ? (i3 == 1 && i >= i2) ? i : i2 : i < i2 ? i : i2;
    }

    public static nrq a() {
        return b;
    }

    public void d(int i) {
        this.c = i;
    }

    public int e() {
        return this.c;
    }

    public int b() {
        return this.e;
    }

    public void e(int i) {
        this.e = i;
    }

    public static int e(double d, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "+8:00";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis((long) d);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        if (i2 < 20) {
            i2 += 24;
        }
        if (1 == i) {
            i2 -= 20;
        }
        return (i2 * 60) + i3;
    }

    public static int c(double d, long j) {
        int i = ((int) (((long) d) - j)) / 60000;
        if (i < 0) {
            return 0;
        }
        return i;
    }

    public static long d(long j, String str, int i) {
        if (TextUtils.isEmpty(str)) {
            str = "+8:00";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        calendar.add(5, i);
        calendar.set(11, 20);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTimeInMillis();
    }

    public static long e(long j, String str) {
        long n;
        if (TextUtils.isEmpty(str)) {
            str = "+8:00";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        if (calendar.get(11) >= 20) {
            calendar.add(5, 1);
            n = jec.n(calendar.getTime());
        } else {
            n = jec.n(calendar.getTime());
        }
        return n * 1000;
    }

    public static String c(int i) {
        if (LanguageUtil.h(BaseApplication.getContext())) {
            return a(i);
        }
        return g(i);
    }

    private static String a(int i) {
        if (i < 10000) {
            return UnitUtil.e(i, 1, 0);
        }
        if (i < 100000000) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903420_res_0x7f03017c, i, UnitUtil.e(i / 10000.0d, 1, 1));
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903419_res_0x7f03017b, i, UnitUtil.e(i / 1.0E8d, 1, 1));
    }

    private static String g(int i) {
        double d = i;
        if (d < 1000.0d) {
            return UnitUtil.e(d, 1, 0);
        }
        if (d < 1000000.0d) {
            return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903420_res_0x7f03017c, i, UnitUtil.e(d / 1000.0d, 1, 1));
        }
        return BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903419_res_0x7f03017b, i, UnitUtil.e(d / 1000000.0d, 1, 1));
    }

    public static boolean b(int i) {
        return i == Integer.parseInt("00E", 16) || i == Integer.parseInt("011", 16);
    }

    public static int c(double d, long j, String str) {
        return Math.max(c(d, d(j, str, -1)), 0);
    }
}
