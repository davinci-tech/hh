package com.huawei.haf.language;

import android.content.Context;
import android.content.res.Configuration;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.dynamic.DynamicLoaderException;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/* loaded from: classes.dex */
public final class LanguageInstallHelper {
    private LanguageInstallHelper() {
    }

    public static void onConfigurationChanged(Configuration configuration) {
        LanguageManager.yM_(configuration, true);
    }

    public static void updateResources(Context context) {
        LanguageManager.c(context);
    }

    static boolean addResources(String str, Context context, File file) {
        try {
            if (file.exists() && file.isFile()) {
                FileUtils.f(file);
                String absolutePath = file.getAbsolutePath();
                boolean b = DynamicResourcesLoader.b(absolutePath);
                DynamicResourcesLoader.yk_(context, context.getResources(), Collections.singletonList(absolutePath));
                return !b && DynamicResourcesLoader.b(absolutePath);
            }
            LogUtil.e(str, file.getName(), " does not exists.");
            return false;
        } catch (DynamicLoaderException e) {
            LogUtil.e(str, "addResources ex=", LogUtil.a(e));
            return false;
        }
    }

    static boolean checkUsedResources(File file) {
        return DynamicResourcesLoader.b(file.getAbsolutePath());
    }

    static void checkOrUpdateResources(String str, Context context, File file, File file2) {
        try {
            DynamicResourcesLoader.yk_(context, context.getResources(), getLoadedResourcesPaths(file, file2));
        } catch (DynamicLoaderException e) {
            LogUtil.e(str, "checkOrUpdateResources ex=", LogUtil.a(e));
        }
    }

    private static Collection<String> getLoadedResourcesPaths(File file, File file2) {
        ArrayList arrayList = new ArrayList();
        if (file != null && file.exists() && file.isFile()) {
            arrayList.add(file.getAbsolutePath());
        }
        if (file2 != null && file2.exists() && file2.isFile()) {
            arrayList.add(file2.getAbsolutePath());
        }
        return arrayList;
    }
}
