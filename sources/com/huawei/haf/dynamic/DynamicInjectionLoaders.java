package com.huawei.haf.dynamic;

import com.huawei.haf.common.utils.CollectionUtils;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public final class DynamicInjectionLoaders {
    private static final Map<String, Object[]> e = new ConcurrentHashMap();

    private DynamicInjectionLoaders() {
    }

    public static void a(String str, ClassLoader classLoader, File file, List<File> list) throws DynamicLoaderException {
        if (list.isEmpty()) {
            return;
        }
        Map<String, Object[]> map = e;
        if (map.containsKey(str)) {
            throw new DynamicLoaderException("loadDex exist needs to unloadDex first, moduleName:" + str);
        }
        Object[] e2 = DynamicDexLoader.e(classLoader, file, list);
        if (e2 == null || e2.length == 0) {
            return;
        }
        map.put(str, e2);
    }

    public static void c(String str, ClassLoader classLoader) throws DynamicLoaderException {
        Map<String, Object[]> map = e;
        Object[] objArr = map.get(str);
        if (CollectionUtils.b(objArr)) {
            return;
        }
        DynamicDexLoader.c(classLoader, objArr);
        map.remove(str);
    }

    public static void c(ClassLoader classLoader, File file) throws DynamicLoaderException {
        DynamicLibraryLoader.c(classLoader, file);
    }
}
