package com.huawei.haf.bundle;

import android.content.Context;

/* loaded from: classes.dex */
public interface AppBundleFramework {
    AppBundleInstallManager create(Context context);

    AppBundleExtension getExtension();

    AppBundleLauncher getLauncher();

    AppBundleResources getResources();

    void onApplicationAttachBaseContext(Context context);

    void onApplicationCreated(Context context, AppBundleConfiguration appBundleConfiguration);
}
