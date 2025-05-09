package defpackage;

import android.text.format.DateFormat;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Deprecated
/* loaded from: classes3.dex */
public class dpg {
    public static String e(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy/M/d")).format(date);
    }

    public static String j(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "Md")).format(date);
    }

    public static String b(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "HH:mm")).format(date);
    }

    public static int a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return calendar.get(7);
    }

    public static int n(long j) {
        int a2 = a(j);
        if (a2 - 1 == 0) {
            return 14;
        }
        return a2 + 6;
    }

    public static String m(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat("EE").format(date);
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }

    public static int a() {
        return Calendar.getInstance().get(1);
    }

    public static long h(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d(j));
        calendar.set(7, Utils.o() ? 1 : 2);
        calendar.add(5, -7);
        return calendar.getTimeInMillis();
    }

    public static Long f(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d(j));
        calendar.set(7, Utils.o() ? 1 : 2);
        return Long.valueOf(calendar.getTimeInMillis() - 1);
    }

    public static long i(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d(j));
        calendar.add(2, -1);
        calendar.set(5, 1);
        return calendar.getTimeInMillis();
    }

    public static long g(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(d(j));
        calendar.set(5, 1);
        return calendar.getTimeInMillis() - 1;
    }

    public static long d(String str) {
        try {
            Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            if (parse != null) {
                return parse.getTime();
            }
            return 0L;
        } catch (ParseException e) {
            LogUtil.b("TimeUtil", "getTimeMillisByFormatDay failed, exception = ", ExceptionUtils.d(e));
            return 0L;
        }
    }

    public static int[] c(long j) {
        int i = (int) (j / 86400000);
        int i2 = (int) ((j - (86400000 * i)) / 3600000);
        int ceil = (int) Math.ceil((r4 - (3600000 * i2)) / 60000.0d);
        if (ceil == 60) {
            i2++;
            if (i2 == 24) {
                i++;
                ceil = 0;
                i2 = 0;
            } else {
                ceil = 0;
            }
        }
        return new int[]{i, i2, ceil};
    }
}
