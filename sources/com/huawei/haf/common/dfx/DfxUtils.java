package com.huawei.haf.common.dfx;

import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.common.utils.AppInfoUtils;
import health.compact.a.ProcessUtil;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes.dex */
public final class DfxUtils {

    /* renamed from: a, reason: collision with root package name */
    private static final SimpleDateFormat f2079a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
    static File b;

    private DfxUtils() {
    }

    public static File d() {
        File file = b;
        if (file != null) {
            return file;
        }
        File file2 = new File(AppInfoUtils.f(null), AppInfoUtils.a());
        b = file2;
        return file2;
    }

    public static File a(File file) {
        return new File(file, "dfx_log_" + AppInfoUtils.f());
    }

    public static String a(String str) {
        return ProcessUtil.b().replace(':', '_') + str;
    }

    public static String e(String str, boolean z) {
        if (!z) {
            return a(str);
        }
        return AppInfoUtils.a() + str;
    }

    public static String d(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter(2048);
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ExceptionUtils.b(thread, th, printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public static String a(long j) {
        String format;
        if (j <= 0) {
            return "N/A";
        }
        SimpleDateFormat simpleDateFormat = f2079a;
        synchronized (simpleDateFormat) {
            format = simpleDateFormat.format(new Date(j));
        }
        return format;
    }

    public static String b(String str, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.CHINA);
        if (date == null) {
            date = new Date();
        }
        return simpleDateFormat.format(date);
    }
}
