package defpackage;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Deprecated
/* loaded from: classes4.dex */
public class ghz {
    public static long c(long j) {
        return j * 1000;
    }

    public static long a(String str, String str2) {
        Calendar calendar = Calendar.getInstance();
        Date e = ggl.e(str, str2);
        if (e != null) {
            calendar.setTime(e);
        }
        return calendar.getTimeInMillis() / 1000;
    }

    public static String a(long j, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        return ggl.a(calendar.getTime(), str);
    }

    public static String e(long j, TimeZone timeZone, String str) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        return ggl.a(calendar.getTime(), timeZone, str);
    }

    public static long a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j * 1000);
        return calendar.getTimeInMillis();
    }

    public static long d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.getTimeInMillis() / 1000;
    }

    public static long e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.getTimeInMillis();
    }
}
