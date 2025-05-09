package defpackage;

import com.huawei.android.bundlecore.load.ModuleLoader;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.dynamic.DynamicInjectionLoaders;
import com.huawei.haf.dynamic.DynamicLoaderException;
import health.compact.a.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
final class zl extends ModuleLoader {
    zl() {
    }

    @Override // com.huawei.android.bundlecore.load.ModuleLoader
    public ClassLoader loadCode(String str, List<String> list, File file, File file2, List<String> list2) throws zf {
        ClassLoader classLoader = ModuleLoader.class.getClassLoader();
        c(classLoader, file2);
        e(str, classLoader, list, file);
        return classLoader;
    }

    @Override // com.huawei.android.bundlecore.load.ModuleLoader
    public void unloadCode(String str, ClassLoader classLoader) throws zf {
        try {
            DynamicInjectionLoaders.c(str, classLoader);
        } catch (DynamicLoaderException e) {
            throw new zf(-24, e);
        }
    }

    private void c(ClassLoader classLoader, File file) throws zf {
        if (file == null) {
            return;
        }
        if (!file.exists()) {
            LogUtil.e("Bundle_ModuleLoader", "load, folder ", file, " is illegal");
            return;
        }
        try {
            DynamicInjectionLoaders.c(classLoader, file);
        } catch (DynamicLoaderException e) {
            throw new zf(-22, e);
        }
    }

    private void e(String str, ClassLoader classLoader, List<String> list, File file) throws zf {
        if (CollectionUtils.d(list)) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new File(it.next()));
        }
        try {
            DynamicInjectionLoaders.a(str, classLoader, file, arrayList);
        } catch (DynamicLoaderException e) {
            throw new zf(-23, e);
        }
    }
}
