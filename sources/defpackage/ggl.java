package defpackage;

import android.text.TextUtils;
import health.compact.a.util.LogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Deprecated
/* loaded from: classes4.dex */
public class ggl {
    public static long i(long j) {
        return j * 1000;
    }

    public static Date e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return new Date();
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (ParseException e) {
            LogUtil.e("Suggestion_DateUtil", e.getMessage());
            return new Date();
        }
    }

    public static int b(long j) {
        return gic.e((Object) new SimpleDateFormat("yyyyMMdd").format(new Date(j)));
    }

    public static String c(long j) {
        return new SimpleDateFormat("HH:mm").format(new Date(j));
    }

    public static int a(Date date) {
        return gic.e((Object) new SimpleDateFormat("yyyyMMdd").format(date));
    }

    public static int c() {
        return gic.e((Object) new SimpleDateFormat("MMddHHmm", Locale.CHINESE).format(new Date()));
    }

    public static String a(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }

    public static String a(Date date, TimeZone timeZone, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
        if (timeZone != null) {
            simpleDateFormat.setTimeZone(timeZone);
        }
        return simpleDateFormat.format(date);
    }

    public static long e(int i) {
        try {
            Date parse = new SimpleDateFormat("yyyyMMdd").parse(Integer.toString(i));
            if (parse != null) {
                return parse.getTime();
            }
        } catch (ParseException unused) {
        }
        return 0L;
    }

    public static int a(Date date, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        LogUtil.b("Suggestion_DateUtil", simpleDateFormat.format(date), "--to---", simpleDateFormat.format(date2));
        try {
            return (int) ((simpleDateFormat.parse(simpleDateFormat.format(date2)).getTime() - simpleDateFormat.parse(simpleDateFormat.format(date)).getTime()) / 86400000);
        } catch (ParseException unused) {
            return -1;
        }
    }

    public static int b(Calendar calendar) {
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        int i = calendar.get(7);
        if (i == 1) {
            return 7;
        }
        return i - 1;
    }

    public static int h(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return b(calendar);
    }

    public static long b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return System.currentTimeMillis() / 1000;
        }
        Date e = e(str, str2);
        if (e == null) {
            return System.currentTimeMillis() / 1000;
        }
        return e.getTime() / 1000;
    }

    public static String b() {
        return new SimpleDateFormat("ZZZ").format(new Date());
    }

    public static long a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        return calendar.getTimeInMillis();
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.add(11, 1);
        return calendar.getTimeInMillis() - 1;
    }

    public static long g(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        return calendar.getTimeInMillis();
    }

    public static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(14, 999);
        calendar.set(13, 59);
        calendar.set(12, 59);
        calendar.set(11, 23);
        return calendar.getTimeInMillis();
    }
}
