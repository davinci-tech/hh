package com.huawei.haf.bundle.extension;

import android.text.TextUtils;
import com.huawei.haf.dynamic.ClassNotFoundInterceptor;
import health.compact.a.LogUtil;
import java.util.Collections;

/* loaded from: classes.dex */
public final class BundleClassNotFoundInterceptor extends ClassNotFoundInterceptor {
    private final BundleLoadManager c;
    private final BundleLoadMode d;
    private final ClassLoader e;

    public BundleClassNotFoundInterceptor(ClassLoader classLoader, BundleLoadManager bundleLoadManager, BundleLoadMode bundleLoadMode) {
        this.e = classLoader;
        this.c = bundleLoadManager;
        this.d = bundleLoadMode;
        LogUtil.c("Bundle_Classloader", "load mode is : ", bundleLoadMode);
    }

    /* renamed from: com.huawei.haf.bundle.extension.BundleClassNotFoundInterceptor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[BundleLoadMode.values().length];
            e = iArr;
            try {
                iArr[BundleLoadMode.MULTIPLE_CLASSLOADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[BundleLoadMode.SINGLE_CLASSLOADER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.huawei.haf.dynamic.ClassNotFoundInterceptor
    public Class<?> findClass(String str) {
        if (this.c == null) {
            return null;
        }
        int i = AnonymousClass1.e[this.d.ordinal()];
        if (i == 1) {
            return e(str, true, false);
        }
        if (i == 2) {
            return e(str, false, true);
        }
        return e(str, true, true);
    }

    private Class<?> e(String str, boolean z, boolean z2) {
        Class<?> cls;
        Class<?> e;
        if (z) {
            cls = findClassInPlugins(str);
            if (cls != null) {
                LogUtil.d("Bundle_Classloader", "Class ", str, " is found in Modules");
                return cls;
            }
        } else {
            cls = null;
        }
        String d = ComponentInfoProvider.e().d(str);
        if (TextUtils.isEmpty(d)) {
            return cls;
        }
        if (this.c.loadInstalledModules(Collections.singletonList(d))) {
            cls = d(str, z, z2);
        }
        if (cls != null || (e = ComponentInfoProvider.e().e(str)) == null) {
            return cls;
        }
        LogUtil.a("Bundle_Classloader", "Module component ", str, " is still not found, return ", e.getSimpleName(), " to avoid crash");
        return e;
    }

    private Class<?> d(String str, boolean z, boolean z2) {
        Class<?> loadClass;
        if (z2) {
            try {
                loadClass = this.e.loadClass(str);
            } catch (ClassNotFoundException unused) {
            }
            if (loadClass == null && z && (loadClass = findClassInPlugins(str)) != null) {
                LogUtil.c("Bundle_Classloader", "Class ", str, " is found in Modules after loading all installed modules");
            }
            return loadClass;
        }
        loadClass = null;
        if (loadClass == null) {
            LogUtil.c("Bundle_Classloader", "Class ", str, " is found in Modules after loading all installed modules");
        }
        return loadClass;
    }
}
