package com.huawei.haf.language;

import android.content.Context;
import android.content.res.Configuration;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.download.DownloadPluginCallback;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LanguageManager {

    /* renamed from: a, reason: collision with root package name */
    private static Locale f2123a = Locale.getDefault();
    private static LanguageInfo b;
    private static LanguageMonitor c;
    private static LanguageStoreHelper d;
    private static LanguageCloudHelper e;

    private LanguageManager() {
    }

    public static boolean c() {
        return LanguagePackage.c;
    }

    public static boolean d() {
        return LanguagePackage.e;
    }

    public static String d(Locale locale, boolean z) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        LanguageInfo a2 = a();
        String languageUuid = a2.getLanguageUuid(locale);
        if (languageUuid == null) {
            LogUtil.a("HAF_LanguageManager", "getSupportLanguageName is null, locale=", locale.toString());
            if (z) {
                return languageUuid;
            }
        }
        return a2.getLanguageName(languageUuid, locale);
    }

    public static void a(LanguagePackage languagePackage) {
        if (languagePackage == null) {
            throw new IllegalArgumentException("install argument is illegal");
        }
        if (c != null) {
            LogUtil.a("HAF_LanguageManager", "language package feature is enabled.");
            return;
        }
        if (!c() && !d()) {
            LogUtil.c("HAF_LanguageManager", "language package feature not supported.");
            return;
        }
        LogUtil.c("HAF_LanguageManager", "install language package feature, support list(", Boolean.valueOf(c()), ", ", Boolean.valueOf(d()), ").");
        c = new LanguageMonitor(languagePackage.d());
        b = languagePackage.e();
        if (c()) {
            d = new LanguageStoreHelper(b);
        }
        if (d()) {
            e = new LanguageCloudHelper(languagePackage);
        }
        d("locale reInit", Locale.getDefault());
    }

    public static LanguageInfo a() {
        LanguageInfo languageInfo = b;
        return languageInfo == null ? DefaultLanguageInfo.e() : languageInfo;
    }

    public static void b(boolean z) {
        LanguageCloudHelper languageCloudHelper = e;
        if (languageCloudHelper != null) {
            languageCloudHelper.e(z);
        }
    }

    public static boolean b() {
        LanguageCloudHelper languageCloudHelper = e;
        if (languageCloudHelper != null) {
            return languageCloudHelper.c();
        }
        return true;
    }

    public static int a(int i, DownloadPluginCallback downloadPluginCallback) {
        LanguageCloudHelper languageCloudHelper = e;
        if (languageCloudHelper != null) {
            return languageCloudHelper.a(g(), i, downloadPluginCallback);
        }
        return 0;
    }

    public static void b(String str) {
        LanguageCloudHelper languageCloudHelper = e;
        if (languageCloudHelper == null || !languageCloudHelper.e(g(), str)) {
            return;
        }
        i();
    }

    public static void e() {
        if (d != null) {
            LanguageStoreHelper.b();
        }
        LanguageCloudHelper languageCloudHelper = e;
        if (languageCloudHelper != null) {
            languageCloudHelper.e();
        }
    }

    static void c(Context context) {
        if (context == null) {
            return;
        }
        LanguageStoreHelper languageStoreHelper = d;
        File a2 = languageStoreHelper != null ? languageStoreHelper.a(g()) : null;
        LanguageCloudHelper languageCloudHelper = e;
        LanguageInstallHelper.checkOrUpdateResources("HAF_LanguageManager", context, a2, languageCloudHelper != null ? languageCloudHelper.d(g()) : null);
    }

    static void yM_(Configuration configuration, boolean z) {
        LanguageMonitor languageMonitor;
        if (configuration == null) {
            LogUtil.e("HAF_LanguageManager", "newConfig == null");
            return;
        }
        if (configuration.locale == null) {
            LogUtil.e("HAF_LanguageManager", "newConfig.locale == null");
            return;
        }
        if (z && (languageMonitor = c) != null) {
            languageMonitor.e();
        }
        if (configuration.locale.equals(f2123a)) {
            LanguageStoreHelper languageStoreHelper = d;
            if (languageStoreHelper != null) {
                languageStoreHelper.yO_("locale not changed", configuration);
            }
            LanguageCloudHelper languageCloudHelper = e;
            if (languageCloudHelper != null) {
                languageCloudHelper.yL_("locale not changed", configuration);
            }
            c(BaseApplication.e());
            return;
        }
        d("locale changed", configuration.locale);
    }

    private static Locale g() {
        return f2123a;
    }

    private static void d(String str, Locale locale) {
        f2123a = locale;
        LanguageStoreHelper languageStoreHelper = d;
        boolean b2 = languageStoreHelper != null ? languageStoreHelper.b(str, locale) : false;
        LanguageCloudHelper languageCloudHelper = e;
        boolean e2 = languageCloudHelper != null ? languageCloudHelper.e(str, locale) : false;
        if (b2 || e2) {
            i();
        }
    }

    private static void i() {
        LanguageMonitor.b();
        LogUtil.c("HAF_LanguageManager", "sendUpdateLanguageReadyBroadcast");
    }
}
