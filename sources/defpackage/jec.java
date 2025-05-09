package defpackage;

import android.text.TextUtils;
import android.text.format.DateUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@Deprecated
/* loaded from: classes5.dex */
public class jec {
    public static String x(Date date) {
        return date == null ? "" : DateFormat.getDateInstance().format(date);
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        return calendar.getTimeInMillis();
    }

    public static long g(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        calendar.set(11, 12);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static Date e() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5));
        return calendar.getTime();
    }

    public static Date b() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return calendar.getTime();
    }

    public static long c() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return l(calendar.getTime());
    }

    public static long e(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5) - 1, 20, 0, 0);
        return l(calendar.getTime());
    }

    public static int a() {
        return Calendar.getInstance().get(2) + 1;
    }

    public static int d(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(5);
    }

    public static int t(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(2) + 1;
    }

    public static int u(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(1);
    }

    public static Date s(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date p(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, 1);
        return calendar.getTime();
    }

    public static Date i(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    public static Date d(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        if (date == null) {
            LogUtil.h("TimeDateFormatUtil", "getOffsetDate date is null.");
        } else {
            calendar.setTime(date);
            calendar.add(5, i);
        }
        return calendar.getTime();
    }

    public static Date c(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, i);
        return calendar.getTime();
    }

    public static long e(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(5, i);
        return n(calendar.getTime());
    }

    public static long b(Date date, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(2, i);
        return n(calendar.getTime());
    }

    public static long l(Date date) {
        if (date == null) {
            return 0L;
        }
        return date.getTime() / 1000;
    }

    public static long n(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 0, 0, 0);
        return l(calendar.getTime());
    }

    public static long k(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(calendar.get(1), calendar.get(2), calendar.get(5), 23, 59, 59);
        return l(calendar.getTime());
    }

    public static Date c(long j) {
        return new Date((j * 1000) + TimeZone.getDefault().getRawOffset());
    }

    public static String c(Date date) {
        return c(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String c(Date date, String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getDefault());
        calendar.setTime(date);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static boolean ab(Date date) {
        return b(date, e());
    }

    public static boolean b(Date date, Date date2) {
        boolean z;
        boolean z2;
        if (date == null || date2 == null) {
            z = false;
            z2 = false;
        } else {
            z2 = date.getTime() >= a(date2).getTime();
            z = date.getTime() <= b(date2).getTime();
        }
        return z2 && z;
    }

    public static Date a(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date b(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static String b(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(str);
        if (stringBuffer.length() == 1) {
            stringBuffer.insert(0, "0:0");
        } else if (stringBuffer.length() == 2) {
            stringBuffer.insert(0, "0:");
        } else if (stringBuffer.length() == 3) {
            stringBuffer.insert(1, ":");
        } else {
            stringBuffer.insert(2, ":");
        }
        return stringBuffer.toString();
    }

    public static String b(int i) {
        return b(String.valueOf(i));
    }

    public static int f(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(7);
    }

    public static String e(long j) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
        } catch (RuntimeException e) {
            LogUtil.b("TimeDateFormatUtil", "Date Format exception = ", e.getMessage());
            return null;
        }
    }

    public static Date f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
        } catch (ParseException e) {
            LogUtil.b("TimeDateFormatUtil", "ParseException = ", e.getMessage());
            return null;
        }
    }

    public static Date w(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime();
    }

    public static Date y(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(2, 11);
        calendar.set(5, 31);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime();
    }

    public static int d() {
        return Calendar.getInstance().get(1);
    }

    public static Date v(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(7, calendar.getFirstDayOfWeek());
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.add(7, 7);
        return calendar.getTime();
    }

    public static Date j(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(2, 1);
        return calendar.getTime();
    }

    public static Date h(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.add(2, -1);
        return calendar.getTime();
    }

    public static Date g(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        return calendar.getTime();
    }

    public static Date o(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.add(2, 2);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date q(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.add(2, 0);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date m(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(5, 1);
        calendar.add(2, 1);
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static int r(Date date) {
        Date m = m(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(m);
        return calendar.get(5);
    }

    public static long f(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        for (int i = calendar.get(5); i != 1; i = calendar.get(5)) {
            calendar.add(5, -1);
        }
        calendar.set(9, 0);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static int b(long j) {
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(j);
        return calendar.get(11);
    }

    public static long h() {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMillis);
        return calendar.getTimeInMillis();
    }

    public static long i() {
        long currentTimeMillis = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(currentTimeMillis));
        calendar.add(14, -(calendar.get(15) + calendar.get(16)));
        return calendar.getTimeInMillis();
    }

    public static String b(String str, String str2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        String e = e(str, str2);
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("TimeDateFormatUtil", "getLocalTimeFromUtc standardDateTime is empty.");
            return str;
        }
        try {
            Date parse = simpleDateFormat.parse(e);
            if (parse == null) {
                LogUtil.h("TimeDateFormatUtil", "getLocalTimeFromUtc localDate is null.");
                return str;
            }
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(parse);
            return a(gregorianCalendar, 65552);
        } catch (ParseException e2) {
            LogUtil.b("TimeDateFormatUtil", "getLocalTimeFromUtc parse standardDateTime exception =  ", e2.getMessage());
            return str;
        }
    }

    private static String e(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            Date parse = simpleDateFormat.parse(str);
            if (parse == null) {
                LogUtil.h("TimeDateFormatUtil", "getStandardDateTimeFromUtc localDate is null.");
                return str;
            }
            if (!TextUtils.isEmpty(str2)) {
                simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + str2));
            }
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            LogUtil.b("TimeDateFormatUtil", "getStandardDateTimeFromUtc parse origin exception =  ", e.getMessage());
            return str;
        }
    }

    public static String a(Calendar calendar, int i) {
        String str;
        if (calendar != null) {
            str = DateUtils.formatDateTime(BaseApplication.getContext(), calendar.getTimeInMillis(), i);
        } else {
            LogUtil.h("TimeDateFormatUtil", "formatDateAndTime error, calendar is null");
            str = "";
        }
        LogUtil.c("TimeDateFormatUtil", "formatDateAndTime myDateStr = ", str);
        return str;
    }

    public static long e(String str) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (!TextUtils.isEmpty(str)) {
            try {
                date = simpleDateFormat.parse(str);
            } catch (ParseException e) {
                LogUtil.b("TimeDateFormatUtil", "dateStringToLong ParseException :", e.getMessage());
            }
        }
        return date.getTime();
    }

    public static String g(String str) {
        if (!h(str)) {
            return str;
        }
        String[] split = str.split("/");
        return split[1] + "/" + split[0] + "/" + split[2];
    }

    public static String c(String str) {
        if (!h(str)) {
            return str;
        }
        String[] split = str.split("/");
        String str2 = split[0];
        return split[1] + "/" + str2 + "/" + split[2];
    }

    public static String d(String str) {
        if (!h(str)) {
            return str;
        }
        String[] split = str.split("/");
        return split[2] + "/" + split[1] + "/" + split[0];
    }

    public static String a(String str) {
        return !TextUtils.isEmpty(str) ? str.replaceAll(" ", "") : str;
    }

    private static boolean h(String str) {
        return !TextUtils.isEmpty(str) && str.split("/").length == 3;
    }
}
