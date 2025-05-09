package com.huawei.haf.language;

import android.content.res.Configuration;
import android.os.Build;
import android.os.LocaleList;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.AppInfoUtils;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.haf.download.DownloadPluginCallback;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
final class LanguageCloudHelper {

    /* renamed from: a, reason: collision with root package name */
    private String f2121a;
    private boolean b;
    private boolean c = false;
    private int d = 1;
    private boolean e;
    private final LanguagePackage f;

    LanguageCloudHelper(LanguagePackage languagePackage) {
        this.f = languagePackage;
    }

    void e(boolean z) {
        this.c = z;
    }

    boolean c() {
        if (b()) {
            return true;
        }
        return this.b;
    }

    int a(Locale locale, int i, DownloadPluginCallback downloadPluginCallback) {
        if (c()) {
            return 0;
        }
        ArrayList arrayList = new ArrayList(1);
        d(locale, arrayList);
        if (arrayList.isEmpty()) {
            LogUtil.c("HAF_LanguageCloud", "updateLanguagePackage uuids is empty.");
            return 0;
        }
        LanguagePackage languagePackage = this.f;
        if (downloadPluginCallback == null) {
            downloadPluginCallback = new DefaultDownloadPluginCallback();
        }
        return languagePackage.b(arrayList, i, downloadPluginCallback);
    }

    void e() {
        this.f.e(null);
    }

    boolean e(String str, Locale locale) {
        a(locale);
        c(str, locale);
        if (b()) {
            return false;
        }
        String languageUuid = this.f.e().getLanguageUuid(locale);
        if (languageUuid == null) {
            LogUtil.a("HAF_LanguageCloud", "current locale is not support language package plugin.");
            this.b = true;
            return false;
        }
        boolean a2 = a(locale, languageUuid);
        if (!c() && a()) {
            LogUtil.c("HAF_LanguageCloud", "begin download language package, fileId=", languageUuid, ", taskNo=", Integer.valueOf(this.f.b(Collections.singletonList(languageUuid), -1, new DefaultDownloadPluginCallback())));
        }
        return a2;
    }

    void yL_(String str, Configuration configuration) {
        c(str, configuration.locale);
        int size = configuration.getLocales().size();
        if (this.d == size || !a()) {
            return;
        }
        yK_(configuration.getLocales());
        this.d = size;
    }

    boolean e(Locale locale, String str) {
        if (c() || str == null || !str.equals(this.f.e().getLanguageUuid(locale))) {
            return false;
        }
        return a(locale, str);
    }

    File d(Locale locale) {
        if (b() || !c()) {
            return null;
        }
        String languageUuid = this.f.e().getLanguageUuid(locale);
        if (languageUuid == null) {
            LogUtil.a("HAF_LanguageCloud", "current locale is not support language package plugin.");
            return null;
        }
        return d(locale, languageUuid);
    }

    private File d(Locale locale, String str) {
        File a2 = this.f.a(str);
        return a2 == null ? c(locale, str) : a2;
    }

    private File c(Locale locale, String str) {
        File d = AppInfoUtils.d(null);
        if (d == null) {
            LogUtil.e("HAF_LanguageCloud", "cacheDir is null");
            return null;
        }
        File file = new File(d, this.f.d(str, locale));
        if (file.exists() && file.isFile()) {
            return file;
        }
        return null;
    }

    private boolean a(Locale locale, String str) {
        File d = d(locale, str);
        if (d == null) {
            this.b = false;
            return false;
        }
        LanguageInstallHelper.addResources("HAF_LanguageCloud", BaseApplication.e(), d);
        boolean exists = d.exists();
        this.b = exists;
        return exists;
    }

    private boolean b() {
        return this.e;
    }

    private boolean a() {
        if (!this.c) {
            return false;
        }
        if (NetworkUtil.j()) {
            return true;
        }
        LogUtil.a("HAF_LanguageCloud", "isNetworkAvailable=false");
        return false;
    }

    private void a(Locale locale) {
        String languageUuid = this.f.e().getLanguageUuid(locale);
        this.f2121a = this.f.e().getLanguageName(languageUuid, locale);
        this.e = this.f.e().isCloudPresetLanguage(languageUuid);
    }

    private void d(Locale locale, List<String> list) {
        String languageUuid = this.f.e().getLanguageUuid(locale);
        if (languageUuid == null) {
            LogUtil.a("HAF_LanguageCloud", "locale is not language package plugin, locale=", locale.toString());
        } else if (!this.f.e().isCloudPresetLanguage(languageUuid) && this.f.a(languageUuid) == null) {
            LogUtil.c("HAF_LanguageCloud", "need download fileId=", languageUuid, ", language=", this.f.e().getLanguageName(languageUuid, locale), ", locale=", locale.toString());
            list.add(languageUuid);
        }
    }

    private void yK_(LocaleList localeList) {
        Locale locale;
        if (localeList.isEmpty()) {
            return;
        }
        ArrayList arrayList = new ArrayList(localeList.size());
        if (!c() && (locale = localeList.get(0)) != null) {
            d(locale, arrayList);
        }
        for (int i = 1; i < localeList.size(); i++) {
            Locale locale2 = localeList.get(i);
            if (locale2 != null) {
                d(locale2, arrayList);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        LogUtil.c("HAF_LanguageCloud", "begin download language package, uuids=", Integer.valueOf(arrayList.size()), ", taskNo=", Integer.valueOf(this.f.b(arrayList, -1, new DefaultDownloadPluginCallback())));
    }

    private void c(String str, Locale locale) {
        LogUtil.c("HAF_LanguageCloud", str, ", isPreset=", Boolean.valueOf(b()), ", language=", this.f2121a, ", tag=", locale.toLanguageTag(), ", locale=", locale.toString(), ", display=", locale.getDisplayLanguage(), ", sdkVersion=", Integer.valueOf(Build.VERSION.SDK_INT));
    }

    static class DefaultDownloadPluginCallback implements DownloadPluginCallback {
        private DefaultDownloadPluginCallback() {
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public boolean onDownloadPluginResult(int i, String str, int i2) {
            LogUtil.c("HAF_LanguageCloud", "onDownloadPluginResult taskNo=", Integer.valueOf(i), ", fileId=", str, ", result=", Integer.valueOf(i2));
            LanguageManager.b(str);
            return false;
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void startDownloadProgress(int i) {
            LogUtil.c("HAF_LanguageCloud", "startDownloadProgress taskNo=", Integer.valueOf(i));
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void showDownloadProgress(int i, int i2, int i3) {
            LogUtil.c("HAF_LanguageCloud", "showDownloadProgress taskNo=", Integer.valueOf(i), ", progress=", Integer.valueOf(i3));
        }

        @Override // com.huawei.haf.download.DownloadPluginCallback
        public void onDownloadResult(int i, int i2) {
            LogUtil.c("HAF_LanguageCloud", "onDownloadResult taskNo=", Integer.valueOf(i), ", reason=", Integer.valueOf(i2));
        }
    }
}
