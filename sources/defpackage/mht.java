package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.UnitUtil;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mht {
    public static long b(int i, boolean z) {
        Calendar calendar = Calendar.getInstance();
        if (z) {
            calendar.set(i, 0, 1, 0, 0, 0);
            calendar.set(14, 0);
        } else {
            calendar.set(1, i);
            calendar.set(2, 11);
            calendar.set(5, 31);
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
        }
        return calendar.getTimeInMillis();
    }

    public static boolean e(int i, long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return i == calendar.get(1);
    }

    public static boolean b(int i, long j, String str) {
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(str)) {
            calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        }
        calendar.setTimeInMillis(j);
        return i == calendar.get(1);
    }

    public static boolean a(int i, int i2) {
        return i == i2 / 10000;
    }

    public static int b(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", "string2Int Exception");
            return 0;
        }
    }

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", "string2Int Exception");
            return 0L;
        }
    }

    public static double d(String str) {
        double d = 0.0d;
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            d = Double.parseDouble(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", "string2Int Exception");
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);
        try {
            return numberFormat.parse(numberFormat.format(d)).doubleValue();
        } catch (ParseException e) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", e.getMessage());
            return d;
        }
    }

    public static JSONObject e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return new JSONObject(str);
        } catch (JSONException unused) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", "string2Json");
            return null;
        }
    }

    public static int d(double d) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        numberFormat.setMaximumFractionDigits(0);
        numberFormat.setMinimumFractionDigits(0);
        try {
            d = numberFormat.parse(numberFormat.format(d)).doubleValue();
        } catch (ParseException e) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", e.getMessage());
        }
        return (int) d;
    }

    public static int a(long j) {
        Calendar calendar = Calendar.getInstance();
        if (j > 0) {
            calendar.setTimeInMillis(j);
        }
        return (calendar.get(1) * 10000) + ((calendar.get(2) + 1) * 100) + calendar.get(5);
    }

    public static String d(long j) {
        Calendar calendar = Calendar.getInstance();
        if (j > 0) {
            calendar.setTimeInMillis(j);
        }
        return String.valueOf(calendar.get(1));
    }

    public static int d(int i, int i2, int i3) {
        if (i2 == 0) {
            return i;
        }
        int i4 = i - i2;
        if (Math.abs(i4) < i3) {
            return 0;
        }
        return i4;
    }

    public static long a(long j, long j2, int i) {
        if (j2 == 0) {
            return j;
        }
        long j3 = j - j2;
        if (Math.abs(j3) < i) {
            return 0L;
        }
        return j3;
    }

    public static double e(double d, double d2, int i) {
        if (d2 == 0.0d) {
            return d;
        }
        double d3 = d - d2;
        if (Math.abs(d3) < i) {
            return 0.0d;
        }
        return d3;
    }

    public static int d(int i, int[] iArr) {
        if (iArr == null || iArr.length <= 0 || i < 0) {
            LogUtil.h("PLGACHIEVE_AchieveAnnualUtils", "getStarLevel() dataArray is null");
            return 0;
        }
        int length = iArr.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length && i >= iArr[i3]; i3++) {
            i2++;
        }
        LogUtil.a("PLGACHIEVE_AchieveAnnualUtils", "getStarLevel() starLevel = ", Integer.valueOf(i2));
        return i2;
    }

    public static void c(CountDownLatch countDownLatch, long j) {
        try {
            countDownLatch.await(j, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_AchieveAnnualUtils", "awaitInterruptedException");
        }
    }

    public static void d(CountDownLatch countDownLatch) {
        if (countDownLatch == null) {
            LogUtil.h("PLGACHIEVE_AchieveAnnualUtils", "need not cleanCountDown");
            return;
        }
        for (long count = countDownLatch.getCount(); count > 0; count--) {
            countDownLatch.countDown();
        }
    }

    public static boolean a(double d, double d2) {
        return Double.compare(d, d2) == 0;
    }

    public static long a(HiHealthData hiHealthData) {
        return c(hiHealthData.getStartTime(), hiHealthData.getTimeZone());
    }

    public static long c(long j, String str) {
        Calendar calendar = Calendar.getInstance();
        if (!TextUtils.isEmpty(str)) {
            calendar.setTimeZone(TimeZone.getTimeZone("GMT" + str));
        }
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        long time = calendar.getTime().getTime();
        LogUtil.a("PLGACHIEVE_AchieveAnnualUtils", "getStartTime time ", Long.valueOf(j), " startTime ", Long.valueOf(time), " timeZone ", str);
        return time;
    }

    public static long e(long j) {
        return Math.round(UnitUtil.e(j));
    }
}
