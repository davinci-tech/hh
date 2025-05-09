package defpackage;

import android.content.Context;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.bundle.AppBundleExtension;
import com.huawei.haf.bundle.AppBundleSetting;
import com.huawei.haf.bundle.extension.BundleExtension;
import com.huawei.haf.bundle.extension.ComponentInfoProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes8.dex */
public final class zp implements AppBundleExtension {
    @Override // com.huawei.haf.bundle.AppBundleExtension
    public Set<String> getAllBundleModules(Context context, boolean z) {
        Set<String> d = AppBundleBuildConfig.d();
        if (!z) {
            return d;
        }
        HashSet hashSet = new HashSet(d);
        for (String str : d) {
            if (isBuiltInModule(context, str)) {
                hashSet.remove(str);
            }
        }
        return hashSet;
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public Set<String> getUpdateModules(Context context, boolean z) {
        Set<String> c = BundleExtension.a().c();
        if (c.isEmpty()) {
            return c;
        }
        HashSet hashSet = new HashSet(c);
        hashSet.removeAll(BundleExtension.d().getLoadedModuleNames());
        for (String str : c) {
            if (!isBundleModule(str)) {
                hashSet.remove(str);
            } else if (z && isBuiltInModule(context, str)) {
                hashSet.remove(str);
            } else if (BundleExtension.d().checkModuleExisted(str, false)) {
                hashSet.remove(str);
            }
        }
        return hashSet;
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public long getModuleZipSize(Context context, String str) {
        yi e = yg.a().e(context, str);
        if (e == null || e.k()) {
            return 0L;
        }
        return e.o();
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public List<String> getModuleDependencies(Context context, String str) {
        yi e = yg.a().e(context, str);
        if (e == null) {
            return Collections.emptyList();
        }
        return e.e();
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean isBundleModule(String str) {
        return AppBundleBuildConfig.d().contains(str);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean isBuiltInModule(Context context, String str) {
        yi e = yg.a().e(context, str);
        if (e == null) {
            return false;
        }
        return e.k();
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public String getModuleTitle(Context context, String str) {
        return ComponentInfoProvider.e().d(context, str);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public String getModuleDescription(Context context, String str) {
        return ComponentInfoProvider.e().b(context, str);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public String getModuleForComponent(String str) {
        return ComponentInfoProvider.e().d(str);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean loadLocalModules(List<String> list) {
        return BundleExtension.d().loadInstalledModules(list);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean isExistLocalModule(String str) {
        return BundleExtension.d().checkModuleExisted(str, true);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean isUnistalledModule(String str) {
        return BundleExtension.d().checkModuleUninstalled(str, true);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public boolean updateModuleInfo(Context context, String str, String str2) {
        return ym.e(context, str, str2);
    }

    @Override // com.huawei.haf.bundle.AppBundleExtension
    public AppBundleSetting getSetting() {
        return BundleExtension.a();
    }
}
