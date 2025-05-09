package health.compact.a;

import android.text.TextUtils;
import android.text.format.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: classes.dex */
public class HiDateUtil {
    public static String e(Date date, String str) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(str, Locale.ENGLISH).format(date);
    }

    public static String c(String str) {
        return e(new Date(), str);
    }

    public static String c(String str, String str2, int i) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(e(str, str2));
        calendar.add(6, -i);
        return e(calendar.getTime(), str2);
    }

    private static Date e(String str, String str2) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(str);
    }

    public static int c(long j) {
        return a(j, null);
    }

    public static int a(long j, TimeZone timeZone) {
        Calendar calendar = timeZone != null ? Calendar.getInstance(timeZone) : Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return (calendar.get(1) * 10000) + ((calendar.get(2) + 1) * 100) + calendar.get(5);
    }

    public static long a(int i) {
        if (i < 10000000 || i > 100000000) {
            return 0L;
        }
        try {
            return e(Integer.toString(i), "yyyyMMdd").getTime();
        } catch (ParseException unused) {
            return 0L;
        }
    }

    public static String d(String str) {
        if (str != null && !str.isEmpty()) {
            return str;
        }
        return new SimpleDateFormat("Z").format(Calendar.getInstance().getTime());
    }

    public static int c(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(6, -i);
        return c(calendar.getTimeInMillis());
    }

    public static int b(int i, int i2, String str) throws ParseException {
        return c(Integer.toString(i), Integer.toString(i2), str);
    }

    public static int c(String str, String str2, String str3) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str3, Locale.ENGLISH);
        return ((int) ((simpleDateFormat.parse(str2).getTime() - simpleDateFormat.parse(str).getTime()) / 8.64E7d)) + 1;
    }

    public static long t(long j) {
        return d(j, 0, 0, 0, 0).getTime().getTime();
    }

    public static long g(long j) {
        Calendar d = d(j, 0, 0, 0, 0);
        d.add(5, 1);
        return d.getTime().getTime();
    }

    public static long n(long j) {
        Calendar d = d(j, 0, 0, 0, 0);
        d.add(5, -1);
        return d.getTime().getTime();
    }

    public static long f(long j) {
        return d(j, 23, 59, 59, 999).getTime().getTime();
    }

    public static long d(long j) {
        return j - (j % 60000);
    }

    public static long b(long j) {
        Calendar d = d(j, 20, 0, 0, 0);
        d.add(5, -1);
        return d.getTime().getTime();
    }

    public static long e(long j) {
        Calendar d = d(j, 20, 0, 0, 0);
        d.add(5, 1);
        return d.getTime().getTime();
    }

    public static long q(long j) {
        return d(j, 20, 0, 0, 0).getTime().getTime();
    }

    public static long r(long j) {
        if (j < q(j)) {
            return t(j);
        }
        return g(j);
    }

    public static long l(long j) {
        long q = q(j);
        return j >= q ? q : b(j);
    }

    public static long o(long j) {
        long q = q(j);
        return j < q ? q - 1 : e(j) - 1;
    }

    public static String m(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(j));
    }

    public static String b(long j, String str) {
        return new SimpleDateFormat(str).format(Long.valueOf(j));
    }

    public static long s(long j) {
        return (j / 1000) * 1000;
    }

    public static long h(long j) {
        return ((j / 1000) * 1000) + 999;
    }

    public static long b(long j, int i, int i2) {
        return d(j, i, i2, 0, 0).getTime().getTime();
    }

    private static Calendar d(long j, int i, int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
        return calendar;
    }

    private static Calendar d(long j, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance();
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
        }
        calendar.setTimeInMillis(j);
        return calendar;
    }

    public static long[] p(long j) {
        long[] jArr = new long[2];
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (calendar.get(7) == 1) {
            calendar.add(5, -1);
        }
        calendar.setFirstDayOfWeek(2);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        health.compact.a.util.LogUtil.d("HiDateUtil", "current monday date= ", simpleDateFormat.format(calendar.getTime()));
        jArr[0] = t(calendar.getTimeInMillis());
        calendar.add(5, 6);
        jArr[1] = f(calendar.getTimeInMillis());
        health.compact.a.util.LogUtil.d("HiDateUtil", "current monday date =- ", simpleDateFormat.format(calendar.getTime()));
        return jArr;
    }

    public static String i(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Md")).format(date);
    }

    public static String w(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return String.valueOf(calendar.get(1));
    }

    public static String j(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return String.format(Locale.ENGLISH, "%02d", Integer.valueOf(calendar.get(2) + 1));
    }

    public static String a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return String.format(Locale.ENGLISH, "%02d", Integer.valueOf(calendar.get(5)));
    }

    public static String y(long j) {
        Calendar.getInstance().setTimeInMillis(j);
        StringBuilder sb = new StringBuilder(16);
        sb.append(w(j));
        sb.append(j(j));
        sb.append(a(j));
        return sb.toString();
    }

    public static long a(String str) {
        SimpleDateFormat simpleDateFormat;
        health.compact.a.util.LogUtil.d("HiDateUtil", "enter getMilliTime", str);
        long j = -1;
        if (TextUtils.isEmpty(str)) {
            return -1L;
        }
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } catch (IllegalArgumentException e) {
            health.compact.a.util.LogUtil.e("HiDateUtil", "new IllegalArgumentException ", e.getMessage());
            simpleDateFormat = null;
        }
        if (simpleDateFormat == null) {
            health.compact.a.util.LogUtil.d("HiDateUtil", "format null");
            return -1L;
        }
        try {
            Date parse = simpleDateFormat.parse(str);
            health.compact.a.util.LogUtil.d("HiDateUtil", parse);
            j = parse.getTime();
            health.compact.a.util.LogUtil.d("HiDateUtil", "time is ", Long.valueOf(j));
            return j;
        } catch (ParseException e2) {
            health.compact.a.util.LogUtil.e("HiDateUtil", "getMilliTime error ", e2.getMessage());
            return j;
        }
    }

    public static Date d(String str, String str2, TimeZone timeZone) throws ParseException {
        if (str2 == null) {
            return new Date();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str2, Locale.ENGLISH);
        if (timeZone != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
        simpleDateFormat.setLenient(false);
        return simpleDateFormat.parse(str);
    }

    public static String k(long j) {
        return c(j, (TimeZone) null);
    }

    public static String c(long j, TimeZone timeZone) {
        return c(j, timeZone, "Z");
    }

    private static String c(long j, TimeZone timeZone, String str) {
        if (str == null) {
            return "";
        }
        Date date = new Date(j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.ENGLISH);
        if (timeZone != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
        return simpleDateFormat.format(date);
    }

    public static long a(long j, TimeZone timeZone, int i) {
        Calendar d = d(j, timeZone);
        d.add(1, i);
        return d.getTimeInMillis();
    }

    public static long e(long j, int i) {
        return a(j, TimeZone.getDefault(), i);
    }

    public static long e(long j, TimeZone timeZone) {
        return d(j, timeZone, 0);
    }

    public static long b(long j, TimeZone timeZone) {
        return b(j, timeZone, 0);
    }

    public static long b(long j, TimeZone timeZone, int i) {
        return d(j, timeZone, i + 1) - 1;
    }

    public static long d(long j, TimeZone timeZone, int i) {
        Calendar d = d(j, timeZone);
        d.add(5, i);
        a(d);
        return d.getTimeInMillis();
    }

    private static void a(Calendar calendar) {
        d(calendar, 0, 0);
    }

    private static void d(Calendar calendar, int i, int i2) {
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
