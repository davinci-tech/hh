package com.huawei.phoneservice.faq.base.util;

import android.content.Context;
import android.text.format.DateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes5.dex */
public class k {
    public static String a(String str, Context context) {
        String a2 = !l.e(str) ? a(b(b(str)), context) : "";
        return l.e(a2) ? "" : a2;
    }

    public static long e(String str) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(str).getTime();
    }

    private static Date e(String str, String str2) {
        if (str2 != null && str != null) {
            try {
                return new SimpleDateFormat(str2, Locale.getDefault()).parse(str);
            } catch (ParseException unused) {
                i.c("TimeStringUtil", "convertStringToDate ParseException Exception ...");
            }
        }
        return null;
    }

    public static Date b(String str) {
        Date e = e(str, "yyyy-M-d");
        return e == null ? e(str, "yyyy/M/d") : e;
    }

    private static Calendar b(Date date) {
        if (date == null) {
            i.d("TimeStringUtil", "convertDateToCalendar::date is null");
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    private static String e(Calendar calendar, Context context, int i) {
        if (context == null || calendar == null) {
            i.d("TimeStringUtil", "convertCalendarToString::context or calendar is null");
            return null;
        }
        try {
            return DateUtils.formatDateTime(context, calendar.getTimeInMillis(), i);
        } catch (IllegalArgumentException unused) {
            i.c("TimeStringUtil", "get formatTime error");
            return null;
        }
    }

    private static String a(Calendar calendar, Context context) {
        return e(calendar, context, 196628);
    }
}
