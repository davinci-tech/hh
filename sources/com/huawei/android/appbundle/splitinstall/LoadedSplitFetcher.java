package com.huawei.android.appbundle.splitinstall;

import java.util.List;
import java.util.Set;

/* loaded from: classes8.dex */
public interface LoadedSplitFetcher {
    boolean checkModuleExisted(String str);

    boolean checkModuleUninstalled(String str);

    boolean loadLocalSplits(List<String> list);

    Set<String> loadedSplits();
}
