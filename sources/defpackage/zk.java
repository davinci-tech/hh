package defpackage;

import com.huawei.android.bundlecore.load.ModuleLoader;
import com.huawei.haf.dynamic.DynamicApplicationLoaders;
import java.io.File;
import java.util.List;

/* loaded from: classes8.dex */
final class zk extends ModuleLoader {
    zk() {
    }

    @Override // com.huawei.android.bundlecore.load.ModuleLoader
    public ClassLoader loadCode(String str, List<String> list, File file, File file2, List<String> list2) {
        ClassLoader e = DynamicApplicationLoaders.e(str);
        if (e != null) {
            return e;
        }
        ClassLoader d = DynamicApplicationLoaders.d(str, list, file, file2, list2);
        DynamicApplicationLoaders.a(d);
        return d;
    }

    @Override // com.huawei.android.bundlecore.load.ModuleLoader
    public void unloadCode(String str, ClassLoader classLoader) {
        DynamicApplicationLoaders.c(classLoader);
    }
}
