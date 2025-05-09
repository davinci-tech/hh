package com.huawei.haf.bundle.extension;

import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public interface BundleLoadManager {
    boolean checkModuleExisted(String str, boolean z);

    boolean checkModuleLoaded(String str);

    boolean checkModuleUninstalled(String str, boolean z);

    Set<String> getLoadedModuleApkPaths();

    Set<String> getLoadedModuleNames();

    boolean loadInstalledModules(List<String> list);
}
