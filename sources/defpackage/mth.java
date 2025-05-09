package defpackage;

import android.text.TextUtils;
import com.huawei.haf.language.LanguageManager;
import com.huawei.haf.language.LanguagePlugin;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginmgr.EzPluginConfigBase;

/* loaded from: classes.dex */
public final class mth extends EzPluginConfigBase {

    /* renamed from: a, reason: collision with root package name */
    private final String f15162a;

    public mth(String str, String str2) {
        super(str2);
        this.f15162a = str;
    }

    @Override // com.huawei.pluginmgr.EzPluginConfigBase
    public String getPluginConfigId() {
        return this.f15162a;
    }

    @Override // com.huawei.pluginmgr.EzPluginConfigBase
    public String getPluginQueryString(String str) {
        String str2 = "version=" + getVersion();
        if (TextUtils.isEmpty(str) || !LanguageManager.a().isCloudSecondLanguage(str)) {
            return str2;
        }
        return str2 + "&type=second";
    }

    @Override // com.huawei.pluginmgr.EzPluginConfigBase
    public String getIndexSaveKey() {
        return LanguagePlugin.INDEX_LANGUAGE_DIR + getVersion();
    }

    @Override // com.huawei.pluginmgr.EzPluginConfigBase
    public String getPluginSaveKey(String str) {
        return str + Constants.LINK + getVersion();
    }

    @Override // com.huawei.pluginmgr.EzPluginConfigBase
    public String getIndexFileFiled() {
        return "plugin_index_languages_version";
    }
}
