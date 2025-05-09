package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import health.compact.a.LanguageUtil;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public final class nkw {
    private static String[] c = c();

    public static int c(String str, Date date) {
        try {
            return Integer.parseInt(new SimpleDateFormat(str).format(date));
        } catch (NumberFormatException e) {
            LogUtil.b("HealthCalendarUtil", "getDate exception" + e.getMessage());
            return 0;
        }
    }

    public static int d(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(i, i2 - 1, 1));
        return calendar.getActualMaximum(5);
    }

    public static int c(int i, int i2, nky nkyVar) {
        int d = d(i, i2);
        int d2 = d(i, i2, nkyVar.z());
        int e = e(i, i2, nkyVar.z());
        HealthCalendar q = nkyVar.q();
        boolean z = q.getYear() == i && q.getMonth() == i2;
        if (!nkyVar.ao() && z) {
            d = q.getDay();
            d2 = b(q, nkyVar.z());
        }
        return ((e + d) + d2) / 7;
    }

    public static boolean j(HealthCalendar healthCalendar, nky nkyVar) {
        return healthCalendar.transformCalendar().getTimeInMillis() > nkyVar.q().transformCalendar().getTimeInMillis();
    }

    public static int d(HealthCalendar healthCalendar, nky nkyVar) {
        int i;
        int c2 = nkyVar.aj() ? c(healthCalendar.getYear(), healthCalendar.getMonth(), nkyVar) : c(nkyVar.r(), nkyVar.n(), nkyVar.z());
        int[] c3 = nkyVar.c(healthCalendar.toString());
        if (c3 == null || c2 <= 0 || c2 + 1 != c3.length) {
            i = 0;
        } else {
            r1 = c3[c2] * nkyVar.l();
            i = c3[c3.length + (-1)] != c3[c3.length + (-2)] ? nkyVar.m() : 0;
        }
        return (c2 * nkyVar.c()) + r1 + i;
    }

    public static int a(int i, int i2, nky nkyVar) {
        return d(new HealthCalendar(i, i2, 1), nkyVar);
    }

    public static int a(HealthCalendar healthCalendar, int i) {
        return (((healthCalendar.getDay() + d(healthCalendar, i)) - 1) / 7) + 1;
    }

    public static int d(HealthCalendar healthCalendar, int i) {
        return healthCalendar.getDayOfWeekOffset(i);
    }

    static int e(int i, int i2, int i3) {
        return new HealthCalendar(i, i2, 1).getDayOfWeekOffset(i3);
    }

    public static int d(int i, int i2, int i3) {
        return 6 - new HealthCalendar(i, i2, d(i, i2)).getDayOfWeekOffset(i3);
    }

    public static boolean c(HealthCalendar healthCalendar, HealthCalendar healthCalendar2, HealthCalendar healthCalendar3) {
        long timeInMillis = healthCalendar2.transformCalendar().getTimeInMillis();
        long timeInMillis2 = healthCalendar3.transformCalendar().getTimeInMillis();
        long timeInMillis3 = healthCalendar.transformCalendar().getTimeInMillis();
        return timeInMillis3 >= timeInMillis && timeInMillis3 <= timeInMillis2;
    }

    public static boolean c(HealthCalendar healthCalendar, nky nkyVar) {
        return c(healthCalendar, nkyVar.r(), nkyVar.n());
    }

    public static int c(HealthCalendar healthCalendar, HealthCalendar healthCalendar2, int i) {
        return ((((int) ((healthCalendar2.transformCalendar().getTimeInMillis() - healthCalendar.transformCalendar().getTimeInMillis()) / 86400000)) + 1) + (healthCalendar.getDayOfWeekOffset(i) + b(healthCalendar2, i))) / 7;
    }

    public static int d(HealthCalendar healthCalendar, HealthCalendar healthCalendar2, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(healthCalendar2.getYear(), healthCalendar2.getMonth() - 1, healthCalendar2.getDay());
        long timeInMillis = healthCalendar2.transformCalendar().getTimeInMillis();
        int dayOfWeekOffset = healthCalendar2.getDayOfWeekOffset(i);
        int dayOfWeekOffset2 = healthCalendar.getDayOfWeekOffset(i);
        int year = healthCalendar.getYear();
        int month = healthCalendar.getMonth();
        int day = healthCalendar.getDay();
        if (dayOfWeekOffset2 == 0) {
            day++;
        }
        calendar.set(year, month - 1, day);
        return ((dayOfWeekOffset + ((int) ((calendar.getTimeInMillis() - timeInMillis) / 86400000))) / 7) + 1;
    }

    public static HealthCalendar d(HealthCalendar healthCalendar, int i, int i2) {
        Calendar transformCalendar = healthCalendar.transformCalendar();
        transformCalendar.add(5, (i - 1) * 7);
        transformCalendar.add(5, -d(transformCalendar).getDayOfWeekOffset(i2));
        return d(transformCalendar);
    }

    public static List<HealthCalendar> b(int i, int i2, nky nkyVar) {
        HealthCalendar healthCalendar = new HealthCalendar(i, i2, 1);
        Calendar transformCalendar = healthCalendar.transformCalendar();
        int d = d(healthCalendar, nkyVar.z());
        HealthCalendar q = nkyVar.q();
        transformCalendar.add(5, -d);
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < 42; i3++) {
            if (i3 != 0) {
                transformCalendar.add(5, 1);
            }
            HealthCalendar d2 = d(transformCalendar);
            if (d2.getMonth() == i2) {
                d2.setPresentMonth(true);
            }
            arrayList.add(d2);
            if (d2.equals(q)) {
                d2.setPresentDay(true);
            }
        }
        return arrayList;
    }

    public static List<HealthCalendar> c(nky nkyVar, int i) {
        HealthCalendar r = nkyVar.r();
        Calendar transformCalendar = r.transformCalendar();
        int dayOfWeekOffset = r.getDayOfWeekOffset(nkyVar.z());
        HealthCalendar q = nkyVar.q();
        transformCalendar.add(5, -dayOfWeekOffset);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i * 7; i2++) {
            if (i2 != 0) {
                transformCalendar.add(5, 1);
            }
            HealthCalendar d = d(transformCalendar);
            arrayList.add(d);
            if (d.equals(q)) {
                d.setPresentDay(true);
            }
            if (c(d, nkyVar)) {
                d.setInRange(true);
            }
        }
        return arrayList;
    }

    public static List<HealthCalendar> a(HealthCalendar healthCalendar, nky nkyVar) {
        int dayOfWeekOffset = healthCalendar.getDayOfWeekOffset(nkyVar.z());
        Calendar transformCalendar = healthCalendar.transformCalendar();
        transformCalendar.add(5, -dayOfWeekOffset);
        return a(d(transformCalendar), nkyVar, nkyVar.z());
    }

    public static List<HealthCalendar> a(HealthCalendar healthCalendar, nky nkyVar, int i) {
        ArrayList arrayList = new ArrayList();
        Calendar transformCalendar = healthCalendar.transformCalendar();
        for (int i2 = 0; i2 < 7; i2++) {
            if (i2 != 0) {
                transformCalendar.add(5, 1);
            }
            HealthCalendar d = d(transformCalendar);
            d.setPresentDay(d.equals(nkyVar.q()));
            d.setPresentMonth(true);
            arrayList.add(d);
        }
        return arrayList;
    }

    private static HealthCalendar d(Calendar calendar) {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(calendar.get(1));
        healthCalendar.setMonth(calendar.get(2) + 1);
        healthCalendar.setDay(calendar.get(5));
        return healthCalendar;
    }

    public static int b(HealthCalendar healthCalendar, int i) {
        return 6 - healthCalendar.getDayOfWeekOffset(i);
    }

    public static HealthCalendar e(int i, nky nkyVar) {
        HealthCalendar healthCalendar = new HealthCalendar();
        healthCalendar.setYear(c(i, nkyVar));
        healthCalendar.setMonth(d(i, nkyVar));
        if (nkyVar.f() != 0) {
            int d = d(healthCalendar.getYear(), healthCalendar.getMonth());
            HealthCalendar healthCalendar2 = nkyVar.f15354a;
            if (healthCalendar2 == null || healthCalendar2.getDay() == 0) {
                d = 1;
            } else if (d >= healthCalendar2.getDay()) {
                d = healthCalendar2.getDay();
            }
            healthCalendar.setDay(d);
        } else {
            healthCalendar.setDay(1);
        }
        if (!c(healthCalendar, nkyVar)) {
            if (h(healthCalendar, nkyVar)) {
                healthCalendar = nkyVar.r();
            } else {
                healthCalendar = nkyVar.n();
            }
        }
        healthCalendar.setPresentMonth(healthCalendar.getYear() == nkyVar.q().getYear() && healthCalendar.getMonth() == nkyVar.q().getMonth());
        healthCalendar.setPresentDay(healthCalendar.equals(nkyVar.q()));
        return healthCalendar;
    }

    public static HealthCalendar b(HealthCalendar healthCalendar, nky nkyVar) {
        if (c(nkyVar.q(), nkyVar) && nkyVar.f() != 2) {
            return nkyVar.d();
        }
        if (c(healthCalendar, nkyVar)) {
            return healthCalendar;
        }
        if (nkyVar.r().isSameMonth(healthCalendar)) {
            return nkyVar.r();
        }
        return nkyVar.n();
    }

    private static boolean h(HealthCalendar healthCalendar, nky nkyVar) {
        return healthCalendar.transformCalendar().getTimeInMillis() < nkyVar.r().transformCalendar().getTimeInMillis();
    }

    public static int b(nky nkyVar) {
        return (((nkyVar.n().getYear() - nkyVar.r().getYear()) * 12) - nkyVar.r().getMonth()) + 1 + nkyVar.n().getMonth();
    }

    public static int c(nky nkyVar) {
        return e(nkyVar.q(), nkyVar);
    }

    public static int e(HealthCalendar healthCalendar, nky nkyVar) {
        return (((healthCalendar.getYear() - nkyVar.r().getYear()) * 12) + healthCalendar.getMonth()) - nkyVar.r().getMonth();
    }

    public static int c(int i, nky nkyVar) {
        return (((i + r2.getMonth()) - 1) / 12) + nkyVar.r().getYear();
    }

    public static int d(int i, nky nkyVar) {
        return (((i + nkyVar.r().getMonth()) - 1) % 12) + 1;
    }

    public static long c(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(i, i2 - 1, 1, 0, 0, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long a(int i, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, i);
        calendar.set(2, i2 - 1);
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static long d(HealthCalendar healthCalendar) {
        if (healthCalendar == null) {
            return 0L;
        }
        Calendar transformCalendar = healthCalendar.transformCalendar();
        transformCalendar.set(11, 0);
        transformCalendar.set(14, 0);
        return transformCalendar.getTimeInMillis();
    }

    public static long c(HealthCalendar healthCalendar) {
        if (healthCalendar == null) {
            return 0L;
        }
        Calendar transformCalendar = healthCalendar.transformCalendar();
        transformCalendar.set(11, 23);
        transformCalendar.set(12, 59);
        transformCalendar.set(13, 59);
        transformCalendar.set(14, 999);
        return transformCalendar.getTimeInMillis();
    }

    public static String b(int i, int i2) {
        if (i2 == 2) {
            i = i == 6 ? 0 : i + 1;
        } else if (i2 == 7) {
            i = i == 0 ? 6 : i - 1;
        }
        String str = c[i];
        return str == null ? "" : str;
    }

    public static String b(Context context, int i, int i2) {
        String b = b(i, i2);
        if (!LanguageUtil.h(context) || TextUtils.isEmpty(b)) {
            return b;
        }
        if (LanguageUtil.m(context)) {
            return b.replace("周", "");
        }
        return b.replace("週", "");
    }

    public static String[] c() {
        c = new String[7];
        String[] shortWeekdays = DateFormatSymbols.getInstance(Locale.getDefault()).getShortWeekdays();
        int length = shortWeekdays.length;
        String[] strArr = c;
        if (length > strArr.length) {
            System.arraycopy(shortWeekdays, 1, strArr, 0, strArr.length);
        }
        return c;
    }

    public static String d() {
        return LanguageUtil.h(BaseApplication.e()) ? "今" : BaseApplication.e().getString(R$string.IDS_detail_sleep_bottom_btu_day_txt);
    }
}
