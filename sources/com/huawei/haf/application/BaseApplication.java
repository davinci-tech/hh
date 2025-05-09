package com.huawei.haf.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import health.compact.a.LogUtil;
import health.compact.a.ProcessUtil;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes.dex */
public class BaseApplication extends Application {

    /* renamed from: a, reason: collision with root package name */
    private static String f2060a;
    private static Application b;
    private static ActivityStatusMonitor c;
    private static String d;
    private static int e;

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        wf_(this);
    }

    static void wf_(Application application) {
        b = application;
        f2060a = application.getPackageName();
        wd_(application);
        ReleaseLogUtil.b("HAF_BaseApplication", "appType=", d(), ", verName=", a(), ", verCode=", Integer.valueOf(c()), ", procName=", f());
    }

    public static Context e() {
        return b;
    }

    public static String d() {
        return f2060a;
    }

    public static String a() {
        return d;
    }

    public static int c() {
        return e;
    }

    public static Application vZ_() {
        return b;
    }

    public static String f() {
        return ProcessUtil.b();
    }

    public static Activity wa_() {
        ActivityStatusMonitor activityStatusMonitor = c;
        if (activityStatusMonitor != null) {
            return activityStatusMonitor.vY_();
        }
        return null;
    }

    public static void b() {
        ActivityStatusMonitor activityStatusMonitor = c;
        if (activityStatusMonitor != null) {
            activityStatusMonitor.c();
        }
    }

    public static boolean j() {
        ActivityStatusMonitor activityStatusMonitor = c;
        if (activityStatusMonitor != null) {
            return activityStatusMonitor.j();
        }
        return false;
    }

    public static boolean a(long j) {
        ActivityStatusMonitor activityStatusMonitor = c;
        if (activityStatusMonitor != null) {
            return activityStatusMonitor.e(j);
        }
        return true;
    }

    public static boolean c(long j) {
        ActivityStatusMonitor activityStatusMonitor = c;
        if (activityStatusMonitor != null) {
            return activityStatusMonitor.a(j);
        }
        return false;
    }

    public static int c(String str) {
        if (c == null || TextUtils.isEmpty(str)) {
            return -1;
        }
        return c.a(str);
    }

    public static void we_(Application application, boolean z) {
        if (c == null) {
            c = z ? new ActivityMonitor(application) : new ActivityStatusMonitor(true);
        }
    }

    private static void wd_(Application application) {
        PackageInfo packageInfo;
        try {
            packageInfo = application.getPackageManager().getPackageInfo(f2060a, 0);
        } catch (PackageManager.NameNotFoundException e2) {
            LogUtil.a("HAF_BaseApplication", "initVersionInfo() ex=", LogUtil.a(e2));
            packageInfo = null;
        }
        d = wc_(packageInfo);
        e = wb_(packageInfo);
    }

    private static String wc_(PackageInfo packageInfo) {
        String str = packageInfo != null ? packageInfo.versionName : null;
        return TextUtils.isEmpty(str) ? "1.0.0.0" : str;
    }

    private static int wb_(PackageInfo packageInfo) {
        int i = packageInfo != null ? packageInfo.versionCode : 0;
        if (i < 1) {
            return 1;
        }
        return i;
    }
}
