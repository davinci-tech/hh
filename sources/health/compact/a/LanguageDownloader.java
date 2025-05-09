package health.compact.a;

import com.huawei.haf.download.DownloadPluginCallback;
import com.huawei.haf.download.DownloadPluginUrl;
import com.huawei.haf.language.LanguageInfo;
import com.huawei.haf.language.LanguagePlugin;
import com.huawei.pluginmgr.EzPluginConfigBase;
import defpackage.mrx;
import defpackage.mth;
import defpackage.mtk;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/* loaded from: classes.dex */
public final class LanguageDownloader implements LanguagePlugin {

    /* renamed from: a, reason: collision with root package name */
    private final DownloadPluginUrl f13124a;
    private final EzPluginConfigBase b;
    private mtk e;

    public LanguageDownloader(DownloadPluginUrl downloadPluginUrl, String str) {
        this.f13124a = downloadPluginUrl;
        this.b = new mth("com.huawei.health_languages_one", str);
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public String getVersion() {
        return this.b.getVersion();
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public String getCloudDownloadUrl(LanguageInfo languageInfo, String str) {
        String downloadPluginUrl = this.f13124a.getDownloadPluginUrl(null, true);
        StringBuilder sb = new StringBuilder(256);
        sb.append(downloadPluginUrl);
        sb.append(this.b.getPluginConfigId());
        sb.append('?');
        sb.append(this.b.getPluginQueryString(str));
        return sb.toString();
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public int startUpdateTask(List<String> list, int i, DownloadPluginCallback downloadPluginCallback) {
        return e().a(list, i, downloadPluginCallback, this.f13124a);
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public void cancelUpdateTask(int i) {
        e().b(i);
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public File getPluginFile(String str) {
        return e().c(str);
    }

    @Override // com.huawei.haf.language.LanguagePlugin
    public void deleteExpiredPlugins(FilenameFilter filenameFilter) {
        mrx.d(filenameFilter);
    }

    private mtk e() {
        if (this.e == null) {
            this.e = new mtk(this.b);
        }
        return this.e;
    }
}
