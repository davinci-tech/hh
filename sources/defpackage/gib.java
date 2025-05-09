package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.LogAnonymous;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Deprecated
/* loaded from: classes.dex */
public class gib {
    public static int d(int i) {
        int i2 = i - 1;
        if (i2 == 0) {
            return 7;
        }
        return i2;
    }

    public static long b(long j) {
        return e(j, 0);
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 23);
        calendar.set(13, 59);
        calendar.set(12, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static long a(long j) {
        return e(j, 1) - 1;
    }

    public static long c(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(5, calendar.getActualMinimum(5));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return e(calendar.getTimeInMillis(), 1) - 1;
    }

    private static long e(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(5, i);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int i(long j) {
        return (int) ((j + TimeZone.getDefault().getOffset(j)) / 86400000);
    }

    public static int d(String str) {
        Date e = ggl.e(str, "yyyy-MM-dd");
        if (e != null) {
            return i(e.getTime());
        }
        return -1;
    }

    public static long g(long j) {
        return (j * 1000) - TimeZone.getDefault().getOffset(r2);
    }

    public static long h(long j) {
        return (j + TimeZone.getDefault().getOffset(j)) / 1000;
    }

    public static String d(String str, String str2, Locale locale) {
        if (str == null || str2 == null || locale == null) {
            LogUtil.b("Suggestion_TimeUtil", "convertToFormat error, input data: ", StringUtils.c((Object) str), " format string: ", StringUtils.c((Object) str2), " locale: ", StringUtils.c(locale));
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2);
        try {
            return new SimpleDateFormat(str2, locale).format(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            LogUtil.b("Suggestion_TimeUtil", "convertToFormat error, input data: ", str, " exception message: ", LogAnonymous.b((Throwable) e));
            return null;
        }
    }

    public static long b(long j, int i) {
        return a(j, (TimeZone) null, i);
    }

    public static long a(long j, TimeZone timeZone, int i) {
        Calendar d = d(j, timeZone);
        d.setFirstDayOfWeek(2);
        int i2 = d.get(7);
        if (i2 == 1) {
            i2 = 8;
        }
        d.set(11, 0);
        d.set(12, 0);
        d.set(13, 0);
        d.set(14, 0);
        d.add(5, (d.getFirstDayOfWeek() - i2) + (i * 7));
        return d.getTimeInMillis();
    }

    public static long c(long j, int i) {
        return c(j, null, i);
    }

    public static long c(long j, TimeZone timeZone, int i) {
        Calendar d = d(a(j, timeZone, i), timeZone);
        d.setFirstDayOfWeek(2);
        d.set(11, 23);
        d.set(12, 59);
        d.set(13, 59);
        d.set(14, 999);
        LogUtil.a("Suggestion_TimeUtil", "getWeekSunday calendar.getTimeInMillis1 = ", Long.valueOf(d.getTimeInMillis()));
        d.set(7, 1);
        return d.getTimeInMillis();
    }

    public static int a(long j, TimeZone timeZone, long j2, TimeZone timeZone2) {
        Calendar d = d(j, timeZone);
        Calendar d2 = d(j2, timeZone2);
        d.setMinimalDaysInFirstWeek(1);
        d.setFirstDayOfWeek(2);
        d2.setMinimalDaysInFirstWeek(1);
        d2.setFirstDayOfWeek(2);
        int i = d.get(1);
        int i2 = d.get(3);
        int i3 = d2.get(1);
        int i4 = d2.get(3);
        if (i2 == 1 && d.get(2) == 11) {
            i++;
        }
        if (i4 == 1 && d2.get(2) == 11) {
            i3++;
        }
        int min = Math.min(i, i3);
        return (i4 + a(min, i3)) - (i2 + a(min, i));
    }

    private static int a(int i, int i2) {
        if (i >= i2) {
            return 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2 - i; i4++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setMinimalDaysInFirstWeek(1);
            calendar.setFirstDayOfWeek(2);
            calendar.set(i + i4, 11, 25);
            i3 += calendar.get(3);
        }
        return i3;
    }

    public static Calendar d(long j, TimeZone timeZone) {
        Calendar calendar;
        if (timeZone == null) {
            calendar = Calendar.getInstance();
        } else {
            calendar = Calendar.getInstance(timeZone);
        }
        calendar.setTimeInMillis(j);
        return calendar;
    }

    public static int e(long j, long j2) {
        return a(j, null, j2, null);
    }

    public static long a(long j, int i, int i2) {
        return b(j, i, i2, null);
    }

    public static long b(long j, int i, int i2, TimeZone timeZone) {
        Calendar d = d(j, timeZone);
        d.setFirstDayOfWeek(2);
        int d2 = d(d.get(7));
        d.set(11, 0);
        d.set(12, 0);
        d.set(13, 0);
        d.add(5, (i2 - d2) + (i * 7));
        return d.getTimeInMillis();
    }

    public static long e(long j, int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i3 = calendar.get(7) - 1;
        if (i3 == 0) {
            i3 = 7;
        }
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(5, (i2 - i3) + ((i - 1) * 7));
        return calendar.getTimeInMillis();
    }

    public static String j(long j) {
        return a(j, (TimeZone) null);
    }

    public static String a(long j, TimeZone timeZone) {
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        if (timeZone != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
        return simpleDateFormat.format(date);
    }

    public static long a(String str) {
        String replaceAll = str.replaceAll("/", Constants.LINK);
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(replaceAll);
            if (parse == null) {
                LogUtil.b("getTimeMillisByFormatDate failed, cause formatDate: ", replaceAll, " produce nul date");
                return 0L;
            }
            return parse.getTime();
        } catch (ParseException unused) {
            LogUtil.b("getTimeMillisByFormatDate failed, cause formatDate: ", replaceAll, ", is not yyyy-MM-dd format");
            return 0L;
        }
    }

    public static int a() {
        int i = Calendar.getInstance().get(7);
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    public static long e(long j, TimeZone timeZone) {
        Calendar calendar;
        if (timeZone == null) {
            calendar = Calendar.getInstance();
        } else {
            calendar = Calendar.getInstance(timeZone);
        }
        calendar.setTimeInMillis(j);
        for (int i = calendar.get(7); i != 2; i = calendar.get(7)) {
            calendar.add(5, -1);
        }
        return a(calendar);
    }

    public static final long f(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(7);
        if (i == 2) {
            calendar.add(5, 1);
            i = calendar.get(7);
        }
        while (i != 2) {
            calendar.add(5, 1);
            i = calendar.get(7);
        }
        return a(calendar);
    }

    public static long a(long j, String str, int i) {
        TimeZone e = e(str);
        Calendar calendar = Calendar.getInstance(e);
        calendar.setTimeInMillis(e(j, e));
        calendar.add(5, i - 1);
        return a(calendar);
    }

    public static TimeZone e(String str) {
        TimeZone timeZone = TimeZone.getDefault();
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("Suggestion_TimeUtil", "getTimeZone failed with error zone id:", str, " return the default TimeZone id:", timeZone.getID());
            return timeZone;
        }
        return TimeZone.getTimeZone("GMT" + str);
    }

    public static long b(String str, long j) {
        return e(str).getOffset(j);
    }

    private static long a(Calendar calendar) {
        d(calendar);
        return calendar.getTimeInMillis();
    }

    private static void d(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }
}
