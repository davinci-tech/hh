package com.huawei.haf.bundle.extension;

/* loaded from: classes.dex */
public enum BundleLoadMode {
    MIXED_CLASSLOADER,
    SINGLE_CLASSLOADER,
    MULTIPLE_CLASSLOADER;

    public static BundleLoadMode valueOf(int i) {
        BundleLoadMode bundleLoadMode = SINGLE_CLASSLOADER;
        if (bundleLoadMode.ordinal() == i) {
            return bundleLoadMode;
        }
        BundleLoadMode bundleLoadMode2 = MULTIPLE_CLASSLOADER;
        return bundleLoadMode2.ordinal() == i ? bundleLoadMode2 : MIXED_CLASSLOADER;
    }
}
