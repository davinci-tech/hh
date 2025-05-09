package com.huawei.pluginmgr;

import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import defpackage.mrv;
import java.io.File;

/* loaded from: classes.dex */
public abstract class EzPluginConfigBase {
    private final String mPluginDir;
    private final String mVersion;

    public abstract String getIndexFileFiled();

    public abstract String getIndexSaveKey();

    public abstract String getPluginConfigId();

    public abstract String getPluginQueryString(String str);

    public abstract String getPluginSaveKey(String str);

    public boolean isPluginDirectory() {
        return false;
    }

    public boolean needDescriptionFile() {
        return false;
    }

    public EzPluginConfigBase(String str) {
        this(null, str);
    }

    public EzPluginConfigBase(String str, String str2) {
        this.mPluginDir = TextUtils.isEmpty(str) ? mrv.d : str;
        this.mVersion = getVersionImpl(str2);
    }

    public final String getVersion() {
        return this.mVersion;
    }

    private String getVersionImpl(String str) {
        if (TextUtils.isEmpty(str)) {
            str = BaseApplication.a();
        }
        int indexOf = str.indexOf(45);
        return indexOf >= 0 ? str.substring(0, indexOf) : str;
    }

    public String getIndexFileSavePath() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this.mPluginDir);
        sb.append(getIndexSaveKey());
        sb.append(File.separator);
        sb.append(getIndexFileFiled());
        sb.append(".json");
        return sb.toString();
    }

    public StringBuilder getPluginSavePath(String str) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(this.mPluginDir);
        sb.append(getPluginSaveKey(str));
        sb.append(File.separator);
        return sb;
    }
}
