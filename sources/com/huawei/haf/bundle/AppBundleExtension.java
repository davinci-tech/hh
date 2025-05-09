package com.huawei.haf.bundle;

import android.content.Context;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
public interface AppBundleExtension {
    Set<String> getAllBundleModules(Context context, boolean z);

    List<String> getModuleDependencies(Context context, String str);

    String getModuleDescription(Context context, String str);

    String getModuleForComponent(String str);

    String getModuleTitle(Context context, String str);

    long getModuleZipSize(Context context, String str);

    AppBundleSetting getSetting();

    Set<String> getUpdateModules(Context context, boolean z);

    boolean isBuiltInModule(Context context, String str);

    boolean isBundleModule(String str);

    boolean isExistLocalModule(String str);

    boolean isUnistalledModule(String str);

    boolean loadLocalModules(List<String> list);

    boolean updateModuleInfo(Context context, String str, String str2);
}
