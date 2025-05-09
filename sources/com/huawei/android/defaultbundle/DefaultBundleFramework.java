package com.huawei.android.defaultbundle;

import android.content.Context;
import com.huawei.haf.bundle.AppBundleConfiguration;
import com.huawei.haf.bundle.AppBundleExtension;
import com.huawei.haf.bundle.AppBundleFramework;
import com.huawei.haf.bundle.AppBundleInstallManager;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.bundle.AppBundleResources;
import com.huawei.haf.bundle.guide.BaseBundleLauncher;
import com.huawei.haf.bundle.guide.BundleInstallGuideHolder;
import defpackage.xr;
import defpackage.ym;
import defpackage.zo;
import defpackage.zp;
import defpackage.zq;

/* loaded from: classes8.dex */
public final class DefaultBundleFramework implements AppBundleFramework {

    /* renamed from: a, reason: collision with root package name */
    private final AppBundleResources f1828a = new zo();
    private final AppBundleExtension c = new zp();
    private final AppBundleLauncher e = new BaseBundleLauncher(this);

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationAttachBaseContext(Context context) {
        ym.d(context);
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public void onApplicationCreated(Context context, AppBundleConfiguration appBundleConfiguration) {
        ym.d(context, new zq(appBundleConfiguration.getDownloader()), DefaultUserConfirmationDialog.class);
        BundleInstallGuideHolder.d(appBundleConfiguration.getDefaultInstallGuide());
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleInstallManager create(Context context) {
        return xr.e(context);
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleResources getResources() {
        return this.f1828a;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleExtension getExtension() {
        return this.c;
    }

    @Override // com.huawei.haf.bundle.AppBundleFramework
    public AppBundleLauncher getLauncher() {
        return this.e;
    }
}
