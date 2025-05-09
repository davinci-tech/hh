package com.huawei.haf.language;

import android.text.TextUtils;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.download.DownloadPluginCallback;
import com.huawei.openalliance.ad.constant.Constants;
import health.compact.a.BuildConfigProperties;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public final class LanguagePackage {
    static final boolean c;
    static final boolean e;

    /* renamed from: a, reason: collision with root package name */
    private final LanguagePlugin f2124a;
    private final LanguageInfo b;

    static {
        c = AppBundleBuildConfig.o() || BuildConfigProperties.e("SUPPORT_STORE_LANGUAGE", false);
        e = BuildConfigProperties.e("SUPPORT_CLOUD_LANGUAGE", false);
    }

    public LanguagePackage(LanguagePlugin languagePlugin) {
        this(null, languagePlugin);
    }

    public LanguagePackage(LanguageInfo languageInfo, LanguagePlugin languagePlugin) {
        this.b = languageInfo == null ? DefaultLanguageInfo.e() : languageInfo;
        this.f2124a = languagePlugin;
    }

    public boolean d() {
        return this.f2124a != null;
    }

    public LanguageInfo e() {
        return this.b;
    }

    public int b(List<String> list, int i, DownloadPluginCallback downloadPluginCallback) {
        LanguagePlugin languagePlugin = this.f2124a;
        if (languagePlugin != null) {
            return languagePlugin.startUpdateTask(list, i, downloadPluginCallback);
        }
        return 0;
    }

    public File a(String str) {
        LanguagePlugin languagePlugin = this.f2124a;
        if (languagePlugin != null) {
            return languagePlugin.getPluginFile(str);
        }
        return null;
    }

    public void e(String str) {
        LanguagePlugin languagePlugin = this.f2124a;
        if (languagePlugin == null) {
            return;
        }
        if (this.b instanceof DefaultLanguageInfo) {
            if (TextUtils.isEmpty(str)) {
                str = this.f2124a.getVersion();
            }
            this.f2124a.deleteExpiredPlugins(new OtherLanguagePackageFilter(this.b.getAllLanguageUuid(false), str));
            return;
        }
        languagePlugin.deleteExpiredPlugins(null);
    }

    public String d(String str, Locale locale) {
        StringBuilder sb = new StringBuilder(32);
        sb.append("language-");
        sb.append(this.b.getLanguageName(str, locale));
        sb.append(".lpk");
        return sb.toString();
    }

    static class OtherLanguagePackageFilter implements FilenameFilter {

        /* renamed from: a, reason: collision with root package name */
        private final int f2125a;
        private final Collection<String> b;
        private final String c;
        private final String e;

        OtherLanguagePackageFilter(Collection<String> collection, String str) {
            this.c = str;
            this.e = Constants.LINK + str;
            this.b = collection;
            this.f2125a = collection.isEmpty() ? 0 : collection.iterator().next().length();
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            if (this.f2125a <= 0 || str == null) {
                return false;
            }
            int length = str.length();
            int i = this.f2125a;
            if (length <= i) {
                if (str.startsWith(this.c, 15)) {
                    return false;
                }
                return str.startsWith(LanguagePlugin.INDEX_LANGUAGE_DIR);
            }
            if (str.startsWith(this.e, i)) {
                return false;
            }
            return this.b.contains(str.substring(0, this.f2125a));
        }
    }
}
