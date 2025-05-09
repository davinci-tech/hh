package com.huawei.haf.bundle;

import android.content.Context;
import android.content.Intent;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public interface AppBundleLauncher {
    public static final String KEY_MODULE_NAME = "moduleName";
    public static final String KEY_MODULE_NAME_LIST = "moduleNameList";

    public interface InstallCallback {
        boolean call(Context context, Intent intent);

        default void onFailed(Context context, Intent intent) {
        }
    }

    AppBundleInstallManager getInstallManager();

    Set<String> getInstalledModules();

    boolean isInstalledModule(String str);

    void launchActivity(Context context, Intent intent, InstallCallback installCallback);

    void launchActivity(Context context, Intent intent, InstallCallback installCallback, InstallGuide installGuide);

    void loadPlugins(Context context, List<String> list, InstallCallback installCallback);

    void preDownloadPlugins(Context context, List<String> list, boolean z, boolean z2);

    void updateModules(Context context, boolean z);
}
