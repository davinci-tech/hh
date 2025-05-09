package com.huawei.phoneservice.feedback.photolibrary.internal.utils;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.wearengine.sensor.DataResult;
import defpackage.uhy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class c {
    public static final c c = new c();
    private static final ThreadLocal<SimpleDateFormat> b = new ThreadLocal<>();

    public final SimpleDateFormat b() {
        ThreadLocal<SimpleDateFormat> threadLocal = b;
        if (threadLocal.get() == null) {
            threadLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return threadLocal.get();
    }

    @JvmStatic
    public static final boolean d(String str, Context context) {
        c cVar = c;
        String a2 = a(str, "yyyy-MM-dd HH:mm", context);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        Calendar calendar2 = Calendar.getInstance();
        try {
            SimpleDateFormat b2 = cVar.b();
            uhy.d(b2);
            calendar2.setTime(b2.parse(a2));
            return calendar2.get(1) == calendar.get(1) && calendar2.get(6) - calendar.get(6) == 0;
        } catch (ParseException e) {
            i.c("FeedbackTimeUtils", e.getMessage());
            return false;
        }
    }

    @JvmStatic
    public static final String a(String str, String str2, Context context) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(DataResult.UTC));
        try {
            Date parse = simpleDateFormat.parse(str);
            uhy.a(parse, "");
            if (!TextUtils.isEmpty(str2)) {
                simpleDateFormat = new SimpleDateFormat(str2);
            }
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            String format = simpleDateFormat.format(Long.valueOf(parse.getTime()));
            uhy.a(format, "");
            return format;
        } catch (ParseException e) {
            i.e("FeedbackTimeUtils", "utc2Local failed. " + e.getMessage(), new Object[0]);
            return "";
        }
    }

    private c() {
    }
}
