package com.huawei.haf.bundle.extension;

import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class BundleExtension {
    private static BundleStateManager b;
    private static BundleLoadManager e;

    private BundleExtension() {
    }

    public static void d(BundleLoadManager bundleLoadManager) {
        e = bundleLoadManager;
        Object[] objArr = new Object[2];
        objArr[0] = "install ";
        objArr[1] = Boolean.valueOf(bundleLoadManager != null);
        LogUtil.c("Bundle_Extension", objArr);
        b = new BundleStateManager();
    }

    public static BundleLoadManager d() {
        return e;
    }

    public static BundleStateManager a() {
        return b;
    }
}
