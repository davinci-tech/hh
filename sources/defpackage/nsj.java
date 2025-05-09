package defpackage;

import android.content.Context;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import com.huawei.android.text.format.DateUtilsEx;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.R$string;
import health.compact.a.CommonUtil;
import health.compact.a.EmuiBuild;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes.dex */
public class nsj {
    private static Date o(long j) {
        Date date = new Date();
        date.setTime(j);
        return date;
    }

    private static Calendar j(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(j));
        return calendar;
    }

    public static boolean h(long j) {
        return j(j).get(1) == j(System.currentTimeMillis()).get(1);
    }

    public static String c(Context context, long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(skh.b(j(j).get(1)));
        if (LanguageUtil.h(context)) {
            sb.append(context.getResources().getString(R$string.year_view));
        }
        return sb.toString();
    }

    public static String e(Context context, long j, long j2) {
        String formatDateRange = DateUtils.formatDateRange(context, j, j2, 65568);
        return LanguageUtil.h(context) ? formatDateRange.replace("至", " - ") : formatDateRange;
    }

    public static String c(Context context, long j, long j2) {
        return d(context, j, j2, true);
    }

    public static String d(Context context, long j, long j2, boolean z) {
        int i = (!z || b(j, j2)) ? 65552 : 65556;
        if (LanguageUtil.h(context)) {
            String formatDateTime = DateUtils.formatDateTime(context, j, i);
            String formatDateTime2 = DateUtils.formatDateTime(context, j2 - 1, i);
            if (!b(System.currentTimeMillis(), j)) {
                if (formatDateTime.length() > 2) {
                    formatDateTime = formatDateTime.substring(2);
                }
                if (formatDateTime2.length() > 2) {
                    formatDateTime2 = formatDateTime2.substring(2);
                }
            }
            return formatDateTime + Constants.LINK + formatDateTime2;
        }
        if (LanguageUtil.bp(context)) {
            return DateUtils.formatDateRange(context, j2 - 1, j + 1, i);
        }
        return DateUtils.formatDateRange(context, j, j2, i);
    }

    public static boolean a(long j, long j2) {
        Calendar j3 = j(j);
        Calendar j4 = j(j2);
        return j3.get(1) == j4.get(1) && j3.get(2) == j4.get(2) && Math.abs(j3.get(5) - j4.get(5)) <= 1;
    }

    public static boolean b(long j, long j2) {
        return j(j).get(1) == j(j2).get(1);
    }

    public static String d(Context context, long j) {
        String formatDateTime = DateUtils.formatDateTime(context, j, 524306);
        if (!LanguageUtil.h(context)) {
            return formatDateTime;
        }
        if (LanguageUtil.m(context)) {
            return formatDateTime.replace("周", " 周");
        }
        return formatDateTime.replace("週", " 週");
    }

    public static String b(long j) {
        return SimpleDateFormat.getDateInstance().format(o(j));
    }

    public static String d() {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH).format(new Date());
    }

    public static String i(long j) {
        return SimpleDateFormat.getTimeInstance(3).format(o(j));
    }

    public static String f(long j) {
        if (h(j)) {
            return d(j);
        }
        return a(j);
    }

    public static String c(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy/M/d HH:mm:ss")).format(date);
    }

    public static String a(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy/M/d HH:mm")).format(date);
    }

    public static String d(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat("M/d HH:mm", Locale.getDefault()).format(date);
    }

    public static String g(long j) {
        Date date = new Date();
        date.setTime(j);
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(date);
    }

    public static String e(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    public static String c(Context context, long j, int i) {
        Date date;
        if (context == null) {
            LogUtil.h("TimeUtils", "format12Hour context is null");
            return "";
        }
        if (b(context)) {
            return DateUtilsEx.formatChinaDateTime(context, j, i);
        }
        if (j != 0) {
            date = new Date(j);
        } else {
            date = new Date(System.currentTimeMillis());
        }
        return UnitUtil.a(date, i);
    }

    public static String e(Context context, long j, long j2, int i) {
        if (context == null) {
            LogUtil.h("TimeUtils", "format12HourDataRange context is null");
            return "";
        }
        if (b(context)) {
            return DateUtilsEx.formatChinaDateRange(context, new Formatter(new StringBuilder(50), Locale.getDefault()), j, j2, i, (String) null);
        }
        return DateUtils.formatDateRange(context, j, j2, i);
    }

    public static boolean b(Context context) {
        if (context != null) {
            return CommonUtil.bh() && CommonUtil.u(context) && EmuiBuild.f13113a > 12 && !DateFormat.is24HourFormat(context);
        }
        LogUtil.h("TimeUtils", "isEmuiFor12Hour context is null");
        return false;
    }

    public static long e(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(5, i);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }
}
