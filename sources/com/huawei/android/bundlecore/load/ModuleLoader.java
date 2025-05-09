package com.huawei.android.bundlecore.load;

import android.content.Context;
import com.huawei.haf.dynamic.DynamicLoaderException;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import defpackage.zf;
import java.io.File;
import java.util.Collections;
import java.util.List;

/* loaded from: classes8.dex */
public abstract class ModuleLoader {
    protected static final String TAG = "Bundle_ModuleLoader";

    public abstract ClassLoader loadCode(String str, List<String> list, File file, File file2, List<String> list2) throws zf;

    public abstract void unloadCode(String str, ClassLoader classLoader) throws zf;

    public void loadResources(Context context, String str) throws zf {
        try {
            DynamicResourcesLoader.yk_(context, context.getResources(), Collections.singletonList(str));
        } catch (DynamicLoaderException e) {
            throw new zf(-21, e);
        }
    }
}
