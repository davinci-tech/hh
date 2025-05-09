package com.huawei.haf.bundle;

import android.content.Context;

/* loaded from: classes.dex */
public final class AppBundle {
    private AppBundle() {
    }

    public static void c(Context context) {
        HealthBundleFramework.a().onApplicationAttachBaseContext(context);
    }

    public static void a(Context context, AppBundleConfiguration appBundleConfiguration) {
        HealthBundleFramework.a().onApplicationCreated(context, appBundleConfiguration);
    }

    public static AppBundleInstallManager a(Context context) {
        return HealthBundleFramework.a().create(context);
    }

    public static AppBundleResources b() {
        return HealthBundleFramework.a().getResources();
    }

    public static AppBundleExtension c() {
        return HealthBundleFramework.a().getExtension();
    }

    public static AppBundleLauncher e() {
        return HealthBundleFramework.a().getLauncher();
    }
}
