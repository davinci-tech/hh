package com.huawei.haf.language;

import com.huawei.haf.download.DownloadPluginCallback;
import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

/* loaded from: classes.dex */
public interface LanguagePlugin {
    public static final String INDEX_LANGUAGE_DIR = "index_language_";

    void cancelUpdateTask(int i);

    void deleteExpiredPlugins(FilenameFilter filenameFilter);

    String getCloudDownloadUrl(LanguageInfo languageInfo, String str);

    File getPluginFile(String str);

    String getVersion();

    int startUpdateTask(List<String> list, int i, DownloadPluginCallback downloadPluginCallback);
}
