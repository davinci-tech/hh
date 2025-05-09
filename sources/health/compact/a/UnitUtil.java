package health.compact.a;

import android.content.Context;
import android.icu.text.PluralRules;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.text.style.TextAppearanceSpan;
import androidx.core.util.Supplier;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import health.compact.a.UnitUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;

/* loaded from: classes.dex */
public class UnitUtil {
    private static String h;
    public static final long d = TimeUnit.HOURS.toSeconds(1);
    private static String e = "show_imperial_unit_key";

    /* renamed from: a, reason: collision with root package name */
    private static String f13144a = "show_weight_unit_key";
    private static int i = -1;
    private static ReentrantReadWriteLock c = new ReentrantReadWriteLock();
    private static String b = "";
    private static int j = -1;

    public static double b(double d2) {
        return d2 * 2.0d;
    }

    public static double c(double d2) {
        return d2 / 2.0d;
    }

    public static double d(double d2, int i2) {
        double d3;
        if (i2 == 0) {
            d3 = 2.54d;
        } else if (i2 == 1) {
            d3 = 0.3048d;
        } else if (i2 == 2) {
            d3 = 0.9144d;
        } else {
            if (i2 != 3) {
                return 0.0d;
            }
            d3 = 1.609344d;
        }
        return d2 * d3;
    }

    public static double e(double d2, int i2) {
        double d3;
        if (i2 == 0) {
            d3 = 0.3937d;
        } else if (i2 == 1) {
            d3 = 3.2808d;
        } else if (i2 == 2) {
            d3 = 1.0936132d;
        } else {
            if (i2 != 3) {
                return 0.0d;
            }
            d3 = 0.6213712d;
        }
        return d2 * d3;
    }

    public static double e(long j2) {
        return j2 / 60000.0d;
    }

    public static double h(double d2) {
        return d2 * 2.2046d;
    }

    public static double i(double d2) {
        return d2 * 0.4536d;
    }

    private UnitUtil() {
    }

    public static int[] j(double d2) {
        int[] iArr = {0, 0};
        double d3 = d2 * 3.2808d;
        int i2 = (int) d3;
        iArr[0] = i2;
        int round = (int) Math.round((d3 - i2) * 12.0d);
        iArr[1] = round;
        if (round == 12) {
            iArr[0] = iArr[0] + 1;
            iArr[1] = 0;
        }
        return iArr;
    }

    public static void e(String str) {
        if (str != null) {
            com.huawei.hwlogsmodel.LogUtil.c("UnitUtil", "setCloudVersion cloudVersion = ", str);
            h = str;
        }
    }

    public static boolean c() {
        return i();
    }

    public static boolean h() {
        if (!TextUtils.isEmpty(b)) {
            return Boolean.valueOf(b).booleanValue();
        }
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), e);
        b = b2;
        if (health.compact.a.utils.StringUtils.g(b2)) {
            b = c(e);
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), e, b, (StorageParams) null);
        }
        if ("true".equals(b)) {
            return true;
        }
        if ("".equals(b) && i()) {
            e(e, "true");
            b = "true";
            return true;
        }
        e(e, "false");
        b = "false";
        return false;
    }

    public static int a() {
        if (j == -1) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), f13144a);
            if (health.compact.a.utils.StringUtils.g(b2)) {
                b2 = c(f13144a);
                SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), f13144a, String.valueOf(b2), (StorageParams) null);
            }
            j = CommonUtil.e(b2, 0);
        }
        int i2 = j;
        if (i2 == 0 || (i2 == 1 && !LanguageUtil.h(BaseApplication.getContext()))) {
            j = h() ? 3 : 2;
        }
        return j;
    }

    public static int e() {
        if (j == -1) {
            final AtomicReference atomicReference = new AtomicReference(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), f13144a));
            if (health.compact.a.utils.StringUtils.g((String) atomicReference.get())) {
                com.huawei.hwlogsmodel.LogUtil.a("UnitUtil", "unitvalue is empty");
                ThreadPoolManager.d().execute(new Runnable() { // from class: ixr
                    @Override // java.lang.Runnable
                    public final void run() {
                        UnitUtil.c(atomicReference);
                    }
                });
            }
            j = CommonUtil.e((String) atomicReference.get(), 0);
        }
        int i2 = j;
        if (i2 == 0 || (i2 == 1 && !LanguageUtil.h(BaseApplication.getContext()))) {
            j = h() ? 3 : 2;
        }
        return j;
    }

    public static /* synthetic */ void c(AtomicReference atomicReference) {
        atomicReference.set(c(f13144a));
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), f13144a, String.valueOf(atomicReference), (StorageParams) null);
    }

    public static double d(double d2) {
        int a2 = a();
        if (a2 == 1) {
            return c(d2);
        }
        return a2 == 3 ? i(d2) : d2;
    }

    public static double a(double d2) {
        return c(d2, 2);
    }

    public static double a(float f) {
        return c(b(f), 2);
    }

    public static double b(double d2, int i2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            return b(0.0d, i2);
        }
        int e2 = e();
        if (e2 == 1) {
            return a(b(d2), i2);
        }
        if (e2 == 3) {
            return a(h(d2), i2);
        }
        return a(d2, i2);
    }

    public static double c(double d2, int i2) {
        if (Double.isNaN(d2) || Double.isInfinite(d2)) {
            return c(0.0d, i2);
        }
        int a2 = a();
        if (a2 == 1) {
            return a(b(d2), i2);
        }
        if (a2 == 3) {
            return a(h(d2), i2);
        }
        return a(d2, i2);
    }

    private static void e(String str, String str2) {
        ReentrantReadWriteLock.WriteLock writeLock = c.writeLock();
        writeLock.lock();
        try {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), str, str2, (StorageParams) null);
            KeyValDbManager.b(BaseApplication.getContext()).e(str, str2);
        } finally {
            writeLock.unlock();
        }
    }

    private static String c(String str) {
        ReentrantReadWriteLock.ReadLock readLock = c.readLock();
        readLock.lock();
        try {
            String e2 = KeyValDbManager.b(BaseApplication.getContext()).e(str);
            if (TextUtils.isEmpty(e2)) {
                e2 = DbManager.d(str);
                KeyValDbManager.b(BaseApplication.getContext()).e(str, e2);
            }
            return e2;
        } finally {
            readLock.unlock();
        }
    }

    public static boolean b() {
        String c2 = c(e);
        if ("true".equals(c2)) {
            return true;
        }
        if ("".equals(c2) && i()) {
            e(e, "true");
            return true;
        }
        e(e, "false");
        return false;
    }

    private static boolean i() {
        if (BaseApplication.getContext() == null) {
            return false;
        }
        return "US".equalsIgnoreCase(BaseApplication.getContext().getResources().getConfiguration().locale.getCountry());
    }

    public static void c(boolean z) {
        b = "";
        j = -1;
        e(e, String.valueOf(z));
    }

    public static void c(int i2) {
        ReentrantReadWriteLock.WriteLock writeLock = c.writeLock();
        writeLock.lock();
        try {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), f13144a, String.valueOf(i2), (StorageParams) null);
            KeyValDbManager.b(BaseApplication.getContext()).e(f13144a, String.valueOf(i2));
            j = i2;
        } finally {
            writeLock.unlock();
        }
    }

    public static boolean d() {
        boolean equals;
        if (ProcessUtil.d()) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "temperature_unit");
            com.huawei.hwlogsmodel.LogUtil.a("UnitUtil", "isShowCelsiusUnit unitValue:", b2);
            if (!health.compact.a.utils.StringUtils.g(b2)) {
                return "true".equals(b2) || "".equals(b2);
            }
            String c2 = c("show_temperature_unit_key");
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "temperature_unit", String.valueOf(c2), (StorageParams) null);
            equals = "false".equals(c2);
        } else {
            String c3 = c("show_temperature_unit_key");
            com.huawei.hwlogsmodel.LogUtil.a("UnitUtil", "isShowCelsiusUnit getUnitValue:", c3);
            equals = "false".equals(c3);
        }
        return !equals;
    }

    public static void a(boolean z) {
        com.huawei.hwlogsmodel.LogUtil.a("UnitUtil", "setShowCelsiusUnit isCelsius:", Boolean.valueOf(z));
        ReentrantReadWriteLock.WriteLock writeLock = c.writeLock();
        writeLock.lock();
        try {
            SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "temperature_unit", String.valueOf(z), (StorageParams) null);
            e("show_temperature_unit_key", String.valueOf(z));
        } finally {
            writeLock.unlock();
        }
    }

    public static String c(Calendar calendar, int i2) {
        String str;
        if (calendar != null) {
            str = DateUtils.formatDateTime(BaseApplication.getContext(), calendar.getTimeInMillis(), i2);
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("UnitUtil", "formatDateAndTime error, calendar is null");
            str = "";
        }
        com.huawei.hwlogsmodel.LogUtil.c("UnitUtil", "formatDateAndTime myDateStr = ", str);
        return str;
    }

    public static String a(Date date, int i2) {
        String str;
        if (date != null) {
            str = DateUtils.formatDateTime(BaseApplication.getContext(), date.getTime(), i2);
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("UnitUtil", "formatDateAndTime error, date is null");
            str = "";
        }
        com.huawei.hwlogsmodel.LogUtil.c("UnitUtil", "formatDateAndTime myDateStr = ", str);
        return str;
    }

    public static String a(String str, long j2) {
        return str != null ? new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), str)).format(Long.valueOf(j2)) : "";
    }

    public static String a(int i2) {
        String formatElapsedTime;
        StringBuilder sb = new StringBuilder(10);
        Formatter formatter = new Formatter(new StringBuilder(10), Locale.getDefault());
        long j2 = i2;
        long j3 = d;
        if (j2 < j3) {
            formatElapsedTime = DateUtils.formatElapsedTime(sb, j2);
        } else if (j2 < TimeUnit.HOURS.toSeconds(10L)) {
            if (DateUtils.formatElapsedTime(j3).length() == DateUtils.formatElapsedTime(TimeUnit.HOURS.toSeconds(10L)).length()) {
                formatElapsedTime = DateUtils.formatElapsedTime(sb, j2);
            } else {
                formatElapsedTime = formatter.format("%d%s", 0, DateUtils.formatElapsedTime(sb, j2)).toString();
            }
        } else {
            formatElapsedTime = DateUtils.formatElapsedTime(sb, j2);
        }
        return formatElapsedTime != null ? LanguageUtil.n(BaseApplication.getContext()) ? formatElapsedTime.replace(FilenameUtils.EXTENSION_SEPARATOR, ':') : formatElapsedTime : "";
    }

    public static String d(int i2) {
        String formatElapsedTime;
        StringBuilder sb = new StringBuilder(10);
        Formatter formatter = new Formatter(new StringBuilder(10), Locale.getDefault());
        if (i2 < 3600) {
            formatElapsedTime = formatter.format("%02d:%s", 0, DateUtils.formatElapsedTime(sb, i2)).toString();
        } else if (i2 < 36000) {
            formatElapsedTime = DateUtils.formatElapsedTime(3600L).length() == DateUtils.formatElapsedTime(36000L).length() ? DateUtils.formatElapsedTime(sb, i2) : formatter.format("%d%s", 0, DateUtils.formatElapsedTime(sb, i2)).toString();
        } else {
            formatElapsedTime = DateUtils.formatElapsedTime(sb, i2);
        }
        return formatElapsedTime != null ? LanguageUtil.n(BaseApplication.getContext()) ? formatElapsedTime.replace(FilenameUtils.EXTENSION_SEPARATOR, ':') : formatElapsedTime : "";
    }

    public static String e(int i2) {
        String formatElapsedTime;
        StringBuilder sb = new StringBuilder(10);
        Formatter formatter = new Formatter(new StringBuilder(10), Locale.getDefault());
        if (i2 < 3600) {
            formatElapsedTime = formatter.format("%02d:%s", 0, DateUtils.formatElapsedTime(sb, i2)).toString();
            if (formatElapsedTime.contains(":")) {
                formatElapsedTime = formatElapsedTime.split(":")[0] + ":" + formatElapsedTime.split(":")[1];
            }
        } else if (i2 < 36000) {
            if (DateUtils.formatElapsedTime(3600L).length() != DateUtils.formatElapsedTime(36000L).length()) {
                formatElapsedTime = formatter.format("%d%s", 0, DateUtils.formatElapsedTime(sb, i2)).toString();
                if (formatElapsedTime.contains(":")) {
                    formatElapsedTime = formatElapsedTime.split(":")[0] + ":" + formatElapsedTime.split(":")[1];
                }
            } else {
                formatElapsedTime = DateUtils.formatElapsedTime(sb, i2);
                if (formatElapsedTime.contains(":")) {
                    formatElapsedTime = formatElapsedTime.split(":")[0] + ":" + formatElapsedTime.split(":")[1];
                }
            }
        } else {
            formatElapsedTime = DateUtils.formatElapsedTime(sb, i2);
            if (formatElapsedTime.contains(":")) {
                formatElapsedTime = formatElapsedTime.split(":")[0] + ":" + formatElapsedTime.split(":")[1];
            }
        }
        return LanguageUtil.n(BaseApplication.getContext()) ? formatElapsedTime.replace(FilenameUtils.EXTENSION_SEPARATOR, ':') : formatElapsedTime;
    }

    public static double a(double d2, int i2) {
        return (i2 < 0 || i2 > 2) ? d2 : new BigDecimal(Double.toString(d2)).setScale(i2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double b(float f) {
        return new BigDecimal(String.valueOf(f)).doubleValue();
    }

    private static int g(double d2) {
        if (Math.abs(d2 - Math.floor(d2)) >= 1.0E-6d) {
            return 100;
        }
        return (int) d2;
    }

    @Deprecated
    public static int e(double d2) {
        return g(d2);
    }

    public static int e(double d2, Locale locale) {
        PluralRules forLocale = PluralRules.forLocale(locale);
        if (forLocale == null) {
            ReleaseLogUtil.a("UnitUtil", "getQuantity pluralRules is null");
            return -1;
        }
        String select = forLocale.select(d2);
        if (TextUtils.isEmpty(select)) {
            ReleaseLogUtil.a("UnitUtil", "getQuantity type ", select);
            return -1;
        }
        Collection<Double> samples = forLocale.getSamples(select);
        if (CollectionUtils.d(samples)) {
            ReleaseLogUtil.a("UnitUtil", "getQuantity sampleList ", samples);
            return -1;
        }
        Double next = samples.iterator().next();
        if (next == null) {
            ReleaseLogUtil.a("UnitUtil", "getQuantity doubleValue is null");
            return -1;
        }
        return next.intValue();
    }

    public static String e(double d2, int i2, int i3, Supplier<Boolean> supplier) {
        if (i2 == 1) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            try {
                numberFormat.setRoundingMode(RoundingMode.HALF_UP);
            } catch (Exception unused) {
                com.huawei.hwlogsmodel.LogUtil.b("UnitUtil", "getNumberFormat Exception");
            }
            if (i3 >= 0) {
                numberFormat.setMaximumFractionDigits(i3);
                numberFormat.setMinimumFractionDigits(i3);
            }
            if (LanguageUtil.b(BaseApplication.getContext()) || (supplier != null && supplier.get().booleanValue())) {
                numberFormat.setMinimumFractionDigits(0);
            }
            return numberFormat.format(d2);
        }
        if (i2 != 2) {
            return "";
        }
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        try {
            percentInstance.setRoundingMode(RoundingMode.HALF_UP);
        } catch (Exception e2) {
            com.huawei.hwlogsmodel.LogUtil.b("UnitUtil", "getNumberFormat ", ExceptionUtils.d(e2));
        }
        if (i3 >= 0) {
            percentInstance.setMaximumFractionDigits(i3);
            percentInstance.setMinimumFractionDigits(i3);
        }
        if (LanguageUtil.b(BaseApplication.getContext()) || (supplier != null && supplier.get().booleanValue())) {
            percentInstance.setMinimumFractionDigits(0);
        }
        return percentInstance.format(d2 / 100.0d);
    }

    public static String a(double d2, int i2, int i3, boolean z) {
        if (i2 != 1) {
            return i2 != 2 ? "" : e(d2, i2, i3);
        }
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        numberFormat.setGroupingUsed(z);
        numberFormat.setRoundingMode(RoundingMode.HALF_UP);
        if (i3 >= 0) {
            numberFormat.setMaximumFractionDigits(i3);
            numberFormat.setMinimumFractionDigits(i3);
        }
        if (LanguageUtil.b(BaseApplication.getContext())) {
            numberFormat.setMinimumFractionDigits(0);
        }
        return numberFormat.format(d2);
    }

    public static String e(double d2, int i2, int i3) {
        return e(d2, i2, i3, null);
    }

    public static SpannableString bCR_(Context context, String str, String str2, int i2, int i3) {
        SpannableString spannableString = new SpannableString(str2);
        spannableString.setSpan(new TextAppearanceSpan(context, i3), 0, spannableString.toString().length(), 33);
        Matcher matcher = Pattern.compile(str, 2).matcher(spannableString);
        while (matcher.find()) {
            spannableString.setSpan(new TextAppearanceSpan(context, i2), matcher.start(), matcher.end(), 33);
        }
        return spannableString;
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            com.huawei.hwlogsmodel.LogUtil.h("UnitUtil", "toWideChar input is null");
            return "";
        }
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            char c2 = charArray[i2];
            if (c2 == 12288) {
                charArray[i2] = ' ';
            } else if (c2 > 65280 && c2 < 65375) {
                charArray[i2] = (char) (c2 - 65248);
            }
        }
        return new String(charArray);
    }
}
