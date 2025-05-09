package com.huawei.hwlogsmodel;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hwlogsmodel.common.LogConfig;
import health.compact.a.BuildConfigProperties;
import health.compact.a.BuildTypeConfig;
import health.compact.a.LogAllowListConfig;
import health.compact.a.ProcessUtil;
import health.compact.a.StrLogImpl;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class LogUtil {
    private static StrLogImpl d;
    private static final List<LogConfig> e = new ArrayList(2);

    /* renamed from: a, reason: collision with root package name */
    private static final Object f6385a = new Object();
    private static String c = Integer.toString(BaseApplication.c());
    private static String g = c + "|";
    private static LogConfig b = new LogConfig.d(null).e();

    static {
        d = null;
        Log.d("LogUtil", "LOGPRE_TIME_FORMAT yyyyMMdd-HH:mm:ss:SSS|");
        d = new StrLogImpl();
    }

    private LogUtil() {
    }

    public static void d(Context context, LogConfig logConfig) {
        if (d == null) {
            return;
        }
        e.add(logConfig);
        a(context, logConfig);
    }

    private static void a(Context context, LogConfig logConfig) {
        logConfig.e(context);
        BuildTypeConfig.c(BuildConfigProperties.b());
        h();
        LogConfig.d(new LogConfig.Model() { // from class: com.huawei.hwlogsmodel.LogUtil.3
            @Override // com.huawei.hwlogsmodel.common.LogConfig.Model
            public void clearLogCache() {
                LogUtil.d();
            }
        });
        LogConfig.c(false);
        e(2, false, "R_LogUtil", "APP_VERSION:", c, ", BUILD_TYPE: ", BuildConfigProperties.b(), ", LOG_LEVEL: ", Integer.valueOf(health.compact.a.LogConfig.e()));
    }

    public static List<LogConfig> a() {
        return e;
    }

    public static void d() {
        d.d();
        Log.i("LogUtil", "clearLogCache start in process:" + ProcessUtil.b() + " pid:" + Process.myPid());
        LogConfig.d(new File(e().l()));
        Log.i("LogUtil", "clearLogCache end in process:" + ProcessUtil.b() + " pid:" + Process.myPid());
    }

    public static void b() {
        synchronized (f6385a) {
            d.a();
            d("LogUtil", "blockForAnalyze");
            d.e(false);
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.huawei.hwlogsmodel.LogUtil.1
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.g();
                }
            }, 300000L);
        }
    }

    public static void g() {
        synchronized (f6385a) {
            d("LogUtil", "unBlockForAnalyze");
            d.e(true);
        }
    }

    public static long b(int i, long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - j >= i) {
            return elapsedRealtime;
        }
        return -1L;
    }

    public static boolean c() {
        return LogConfig.i();
    }

    public static void i(String str, Object... objArr) {
        e(0, false, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        e(1, false, str, objArr);
    }

    public static void a(String str, Object... objArr) {
        if (b(str)) {
            e(2, false, str, objArr);
        }
    }

    private static void d(String str, String str2) {
        e(2, true, str, str2);
    }

    private static void e(int i, boolean z, String str, Object... objArr) {
        if (LogAllowListConfig.b(i, str)) {
            d.c(i, str, g, objArr, z);
        }
    }

    public static void h(String str, Object... objArr) {
        if (b(str)) {
            e(3, false, str, objArr);
        }
    }

    public static void b(String str, Object... objArr) {
        if (b(str)) {
            e(4, false, str, objArr);
        }
    }

    public static void bRh_(int i, String str, Bundle bundle, boolean z, Object... objArr) {
        b(str, objArr);
    }

    public static void h() {
        if (LogConfig.i() || (LogConfig.d() && LogConfig.c() && !health.compact.a.LogConfig.d() && !BuildConfigProperties.e("FORCE_ALL_LOG", false))) {
            LogAllowListConfig.d();
            LogAllowListConfig.b();
            g = c + "|R|";
            return;
        }
        LogAllowListConfig.c();
        LogAllowListConfig.a();
        g = c + "|";
    }

    public static void e(String str, Object... objArr) {
        e(4, false, str, objArr);
    }

    public static void d(String str, Object... objArr) {
        e(2, false, str, objArr);
    }

    public static void g(String str, Object... objArr) {
        e(3, false, str, objArr);
    }

    private static boolean b(String str) {
        if (c() || (LogConfig.d() && LogConfig.c() && !health.compact.a.LogConfig.d() && !BuildConfigProperties.e("FORCE_ALL_LOG", false))) {
            return !TextUtils.isEmpty(str) && str.startsWith("R_");
        }
        return true;
    }

    public static LogConfig e() {
        if (!c() && !LogConfig.d()) {
            return b;
        }
        for (LogConfig logConfig : e) {
            if (TextUtils.equals(ProcessUtil.b(), logConfig.n())) {
                return logConfig;
            }
        }
        return b;
    }

    public static LogConfig d(String str) {
        for (LogConfig logConfig : e) {
            if (TextUtils.equals(ProcessUtil.b() + "_" + str, logConfig.n())) {
                return logConfig;
            }
        }
        return b;
    }
}
