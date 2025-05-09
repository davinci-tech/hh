package com.huawei.haf.bundle;

import android.content.Context;
import health.compact.a.LogUtil;
import health.compact.a.ReflectionUtils;

/* loaded from: classes.dex */
final class HealthBundleFramework implements AppBundleFramework {
    private static volatile HealthBundleFramework c;
    private static final String[] d = {"com.huawei.android.defaultbundle.DefaultBundleFramework", "com.huawei.android.googlebundle.GoogleBundleFramework", "com.huawei.android.huaweibundle.HuaweiBundleFramework"};

    /* renamed from: a, reason: collision with root package name */
    private final AppBundleFramework f2063a = b();
    private boolean b;
    private AppBundleFramework e;

    private HealthBundleFramework() {
    }

    public static AppBundleFramework a() {
        if (c == null) {
            synchronized (HealthBundleFramework.class) {
                if (c == null) {
                    c = new HealthBundleFramework();
                }
            }
        }
        return c;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationAttachBaseContext(Context context) {
        this.f2063a.onApplicationAttachBaseContext(context);
        LogUtil.c("Bundle_Framework", "onApplicationAttachBaseContext");
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationCreated(Context context, AppBundleConfiguration appBundleConfiguration) {
        this.f2063a.onApplicationCreated(context, appBundleConfiguration);
        LogUtil.c("Bundle_Framework", "onApplicationCreated");
        this.b = true;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleInstallManager create(Context context) {
        return c().create(context);
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleResources getResources() {
        return c().getResources();
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleExtension getExtension() {
        return c().getExtension();
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleLauncher getLauncher() {
        return c().getLauncher();
    }

    private AppBundleFramework c() {
        if (this.b) {
            return this.f2063a;
        }
        if (this.e == null) {
            AppBundleFramework appBundleFramework = this.f2063a;
            if (!(appBundleFramework instanceof NullBundleFramework)) {
                appBundleFramework = new NullBundleFramework();
            }
            this.e = appBundleFramework;
            LogUtil.a("Bundle_Framework", "AppBundleFramework is not initialized in application, using NullBundleFramework.");
        }
        return this.e;
    }

    private AppBundleFramework b() {
        String simpleName;
        AppBundleFramework appBundleFramework = null;
        for (String str : d) {
            appBundleFramework = d(str);
            if (appBundleFramework != null) {
                break;
            }
        }
        if (appBundleFramework == null) {
            appBundleFramework = new NullBundleFramework();
            simpleName = "NullBundleFramework";
        } else {
            simpleName = appBundleFramework.getClass().getSimpleName();
        }
        LogUtil.c("Bundle_Framework", "current bundle framework: ", simpleName);
        return appBundleFramework;
    }

    private AppBundleFramework d(String str) {
        try {
            Object e = ReflectionUtils.e(str);
            if (e instanceof AppBundleFramework) {
                return (AppBundleFramework) e;
            }
            return null;
        } catch (Exception e2) {
            LogUtil.e("Bundle_Framework", "loadBundleFramework, ex=", LogUtil.a(e2));
            return null;
        }
    }
}
