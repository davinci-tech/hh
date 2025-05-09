package defpackage;

import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.wearengine.sensor.DataResult;
import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public final class jdl {
    public static int a(long j, long j2) {
        if (j > j2) {
            return -a(j2, j);
        }
        return ((int) Math.round(((j2 - j) * 1.0d) / 8.64E7d)) + 1;
    }

    public static int d(int i, int i2) {
        return d(String.valueOf(i), String.valueOf(i2), DateFormatUtil.DateFormatType.DATE_FORMAT_8);
    }

    public static int d(String str, String str2, DateFormatUtil.DateFormatType dateFormatType) {
        try {
            return e(DateFormatUtil.e(str, dateFormatType, TimeZone.getDefault()).getTime(), DateFormatUtil.e(str2, dateFormatType, TimeZone.getDefault()).getTime());
        } catch (ParseException unused) {
            return 0;
        }
    }

    public static int c(long j, TimeZone timeZone, long j2, TimeZone timeZone2) {
        return e(d(j, timeZone), d(j2, timeZone2));
    }

    private static int e(int i, int i2) {
        return i > i2 ? -e(i2, i) : (i2 - i) + 1;
    }

    public static int e(long j, long j2) {
        return c(j, TimeZone.getDefault(), j2, TimeZone.getDefault());
    }

    public static int b(long j, long j2) {
        return e(j, j2, 2);
    }

    public static int e(long j, long j2, int i) {
        return a(j, null, j2, null, i);
    }

    public static int a(long j, TimeZone timeZone, long j2, TimeZone timeZone2, int i) {
        Calendar ab = ab(j, timeZone);
        Calendar ab2 = ab(j2, timeZone2);
        ab.setMinimalDaysInFirstWeek(1);
        ab.setFirstDayOfWeek(i);
        ab2.setMinimalDaysInFirstWeek(1);
        ab2.setFirstDayOfWeek(i);
        int i2 = ab.get(1);
        int i3 = ab.get(3);
        int i4 = ab2.get(1);
        int i5 = ab2.get(3);
        if (i3 == 1 && ab.get(2) == 11) {
            i2++;
        }
        if (i5 == 1 && ab2.get(2) == 11) {
            i4++;
        }
        if (i2 > i4) {
            i3 += e(i4, i2 - 1, i);
        } else if (i2 < i4) {
            i5 += e(i2, i4 - 1, i);
        }
        return i3 > i5 ? (i5 - i3) - 1 : (i5 - i3) + 1;
    }

    public static int b(int i, int i2) {
        if (i < 1970 || i2 < 1 || i2 > 7) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setMinimalDaysInFirstWeek(1);
        calendar.setFirstDayOfWeek(i2);
        calendar.set(i, 11, 25);
        return calendar.get(3);
    }

    public static int e(int i, int i2, int i3) {
        if (i > i2) {
            return -e(i2, i, i3);
        }
        int i4 = 0;
        while (i <= i2) {
            i4 += b(i, i3);
            i++;
        }
        return i4;
    }

    public static boolean ac(long j) {
        return d(j, System.currentTimeMillis());
    }

    public static boolean ag(long j) {
        return d(j, b(System.currentTimeMillis(), -1));
    }

    public static boolean z(long j) {
        return ad(j, TimeZone.getDefault());
    }

    public static boolean ad(long j, TimeZone timeZone) {
        return c(j, System.currentTimeMillis(), timeZone);
    }

    public static boolean c(long j, long j2, TimeZone timeZone) {
        return c(j, timeZone, j2, timeZone) > 1;
    }

    public static boolean ad(long j) {
        return z(j, TimeZone.getDefault());
    }

    public static boolean z(long j, TimeZone timeZone) {
        return b(j, System.currentTimeMillis(), timeZone);
    }

    public static boolean b(long j, long j2, TimeZone timeZone) {
        return c(j2, timeZone, j, timeZone) > 1;
    }

    public static boolean h(long j, long j2) {
        return TimeUnit.MILLISECONDS.toMinutes(j) == TimeUnit.MILLISECONDS.toMinutes(j2);
    }

    public static boolean c(long j, long j2) {
        return d(j, j2) && ab(j, TimeZone.getDefault()).get(10) == ab(j2, TimeZone.getDefault()).get(10);
    }

    public static boolean d(long j, long j2) {
        return e(j, j2) == 1;
    }

    public static boolean f(long j, long j2) {
        return d(j, j2, 2);
    }

    public static boolean d(long j, long j2, int i) {
        return e(j, j2, i) == 1;
    }

    public static boolean i(long j, long j2) {
        Calendar ab = ab(j, TimeZone.getDefault());
        Calendar ab2 = ab(j2, TimeZone.getDefault());
        return ab.get(1) == ab2.get(1) && ab.get(2) == ab2.get(2);
    }

    public static boolean g(long j, long j2) {
        return ab(j, TimeZone.getDefault()).get(1) == ab(j2, TimeZone.getDefault()).get(1);
    }

    public static int c(long j) {
        return d(j, (TimeZone) null);
    }

    public static int d(long j, TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        return ((int) ((j + timeZone.getOffset(j)) / 86400000)) + 1;
    }

    public static int o(long j) {
        return n(j, null);
    }

    public static int n(long j, TimeZone timeZone) {
        return ab(j, timeZone).getActualMaximum(5);
    }

    public static long b(long j, int i) {
        return f(j, TimeZone.getDefault(), i);
    }

    public static long f(long j, TimeZone timeZone, int i) {
        Calendar ab = ab(j, timeZone);
        ab.add(5, i);
        return ab.getTimeInMillis();
    }

    public static long e(long j, int i, int i2) {
        return d(j, TimeZone.getDefault(), i, i2);
    }

    public static long d(long j, TimeZone timeZone, int i, int i2) {
        Calendar ab = ab(j, timeZone);
        b(ab, i, i2);
        return ab.getTimeInMillis();
    }

    public static long d(long j, int i) {
        return a(j, TimeZone.getDefault(), i);
    }

    public static long a(long j, TimeZone timeZone, int i) {
        Calendar ab = ab(j, timeZone);
        ab.add(5, i);
        c(ab);
        return ab.getTimeInMillis();
    }

    public static long c(long j, int i) {
        return c(j, TimeZone.getDefault(), i);
    }

    public static long c(long j, TimeZone timeZone, int i) {
        return a(j, timeZone, i + 1) - 1;
    }

    public static long t(long j) {
        return q(j, TimeZone.getDefault());
    }

    public static long q(long j, TimeZone timeZone) {
        return a(j, timeZone, 0);
    }

    public static long e(long j) {
        return e(j, TimeZone.getDefault());
    }

    public static long e(long j, TimeZone timeZone) {
        return c(j, timeZone, 0);
    }

    public static long v(long j) {
        return v(j, TimeZone.getDefault());
    }

    public static long v(long j, TimeZone timeZone) {
        return a(j, timeZone, -1);
    }

    public static long g(long j) {
        return j(j, TimeZone.getDefault());
    }

    public static long j(long j, TimeZone timeZone) {
        return c(j, timeZone, -1);
    }

    public static long y(long j) {
        return s(j, TimeZone.getDefault());
    }

    public static long s(long j, TimeZone timeZone) {
        return a(j, timeZone, 1);
    }

    public static long h(long j) {
        return f(j, TimeZone.getDefault());
    }

    public static long f(long j, TimeZone timeZone) {
        return c(j, timeZone, 1);
    }

    public static long c(long j, int i, int i2) {
        return b(j, TimeZone.getDefault(), i, i2);
    }

    public static long b(long j, TimeZone timeZone, int i, int i2) {
        Calendar ab = ab(j, timeZone);
        ab.add(3, i2);
        while (ab.get(7) != i) {
            ab.add(5, -1);
        }
        c(ab);
        return ab.getTimeInMillis();
    }

    public static long b(long j, int i, int i2) {
        return a(j, TimeZone.getDefault(), i, i2);
    }

    public static long a(long j, TimeZone timeZone, int i, int i2) {
        return b(j, timeZone, i, i2 + 1) - 1;
    }

    public static long[] e(long j, TimeZone timeZone, int i, int i2) {
        return new long[]{b(j, timeZone, i, i2), a(j, timeZone, i, i2)};
    }

    public static int n(long j) {
        return o(j, TimeZone.getDefault());
    }

    public static int d(long j) {
        return ab(j, TimeZone.getDefault()).get(7);
    }

    public static int o(long j, TimeZone timeZone) {
        int i = ab(j, timeZone).get(7);
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    public static long e(long j, int i) {
        return i(j, TimeZone.getDefault(), i);
    }

    public static long i(long j, TimeZone timeZone, int i) {
        Calendar ab = ab(j, timeZone);
        ab.set(5, 1);
        ab.add(2, i);
        c(ab);
        return ab.getTimeInMillis();
    }

    public static long b(long j, TimeZone timeZone, int i) {
        return i(j, timeZone, i + 1) - 1;
    }

    public static long u(long j) {
        return y(j, TimeZone.getDefault());
    }

    public static long y(long j, TimeZone timeZone) {
        return i(j, timeZone, -1);
    }

    public static long f(long j) {
        return g(j, TimeZone.getDefault());
    }

    public static long g(long j, TimeZone timeZone) {
        return b(j, timeZone, -1);
    }

    public static long s(long j) {
        return t(j, TimeZone.getDefault());
    }

    public static long t(long j, TimeZone timeZone) {
        return i(j, timeZone, 0);
    }

    public static long a(long j) {
        return c(j, TimeZone.getDefault());
    }

    public static long c(long j, TimeZone timeZone) {
        return b(j, timeZone, 0);
    }

    public static long x(long j) {
        return w(j, TimeZone.getDefault());
    }

    public static long w(long j, TimeZone timeZone) {
        return i(j, timeZone, 1);
    }

    public static long j(long j) {
        return i(j, TimeZone.getDefault());
    }

    public static long i(long j, TimeZone timeZone) {
        return b(j, timeZone, 1);
    }

    public static long a(long j, int i) {
        return g(j, TimeZone.getDefault(), i);
    }

    public static long g(long j, TimeZone timeZone, int i) {
        Calendar ab = ab(j, timeZone);
        ab.set(5, 1);
        ab.set(2, 0);
        ab.add(1, i);
        c(ab);
        return ab.getTimeInMillis();
    }

    public static long d(long j, TimeZone timeZone, int i) {
        return g(j, timeZone, i + 1) - 1;
    }

    public static long p(long j) {
        return r(j, TimeZone.getDefault());
    }

    public static long r(long j, TimeZone timeZone) {
        return g(j, timeZone, 0);
    }

    public static long b(long j) {
        return a(j, TimeZone.getDefault());
    }

    public static long a(long j, TimeZone timeZone) {
        return d(j, timeZone, 0);
    }

    public static long aa(long j) {
        return u(j, TimeZone.getDefault());
    }

    public static long u(long j, TimeZone timeZone) {
        return g(j, timeZone, -1);
    }

    public static long i(long j) {
        return h(j, TimeZone.getDefault());
    }

    public static long h(long j, TimeZone timeZone) {
        return d(j, timeZone, -1);
    }

    public static long w(long j) {
        return x(j, TimeZone.getDefault());
    }

    public static long x(long j, TimeZone timeZone) {
        return g(j, timeZone, 1);
    }

    public static String q(long j) {
        return p(j, null);
    }

    public static String p(long j, TimeZone timeZone) {
        return DateFormatUtil.a(j, timeZone, DateFormatUtil.DateFormatType.DATE_FORMAT_RFC_TIME_ZONE);
    }

    public static TimeZone d(String str) {
        if (str == null || str.isEmpty()) {
            return TimeZone.getDefault();
        }
        return TimeZone.getTimeZone("GMT" + str);
    }

    public static long c(long j, TimeZone timeZone, TimeZone timeZone2) {
        if (timeZone == null || timeZone2 == null) {
            return j;
        }
        if (timeZone2.useDaylightTime()) {
            try {
                return DateFormatUtil.e(DateFormatUtil.a(j, timeZone, DateFormatUtil.DateFormatType.DATE_FORMAT_17), DateFormatUtil.DateFormatType.DATE_FORMAT_17, timeZone2).getTime();
            } catch (ParseException unused) {
                return j;
            }
        }
        return (timeZone.getOffset(j) + j) - timeZone2.getOffset(j);
    }

    public static long b(long j, TimeZone timeZone) {
        return c(j, timeZone, b());
    }

    public static TimeZone b() {
        return TimeZone.getTimeZone(DataResult.UTC);
    }

    public static int d() {
        return ab(System.currentTimeMillis());
    }

    public static int ab(long j) {
        return aa(j, TimeZone.getDefault());
    }

    public static int aa(long j, TimeZone timeZone) {
        return ab(j, timeZone).get(1);
    }

    public static int k(long j) {
        return l(j, TimeZone.getDefault());
    }

    public static int l(long j, TimeZone timeZone) {
        return ab(j, timeZone).get(2);
    }

    public static int m(long j) {
        return k(j, TimeZone.getDefault());
    }

    public static int k(long j, TimeZone timeZone) {
        return ab(j, timeZone).get(11);
    }

    public static int l(long j) {
        return m(j, TimeZone.getDefault());
    }

    public static long h(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(d(q(j)));
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, i);
        return calendar.getTimeInMillis();
    }

    public static int m(long j, TimeZone timeZone) {
        return ab(j, timeZone).get(12);
    }

    public static long r(long j) {
        return e(j, TimeZone.getDefault(), 0);
    }

    public static long e(long j, TimeZone timeZone, int i) {
        Calendar ab = ab(j, timeZone);
        ab.set(14, 0);
        ab.set(13, 0);
        ab.set(12, 0);
        ab.add(11, i);
        return ab.getTimeInMillis();
    }

    private static Calendar ab(long j, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTimeInMillis(j);
        return calendar;
    }

    private static void c(Calendar calendar) {
        b(calendar, 0, 0);
    }

    private static void b(Calendar calendar, int i, int i2) {
        if (i < 0) {
            calendar.set(11, 0);
        } else {
            calendar.set(11, Math.min(i, 23));
        }
        if (i2 < 0) {
            calendar.set(12, 0);
        } else {
            calendar.set(12, Math.min(i2, 59));
        }
        calendar.set(13, 0);
        calendar.set(14, 0);
    }
}
