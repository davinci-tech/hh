package defpackage;

import com.huawei.haf.bundle.AppBundleConfiguration;
import com.huawei.haf.bundle.AppBundleDownloader;
import com.huawei.haf.bundle.InstallGuide;
import health.compact.a.HealthBundleInstallGuide;

/* loaded from: classes.dex */
public final class ccj implements AppBundleConfiguration {
    @Override // com.huawei.haf.bundle.AppBundleConfiguration
    public AppBundleDownloader getDownloader() {
        return new ccm();
    }

    @Override // com.huawei.haf.bundle.AppBundleConfiguration
    public InstallGuide getDefaultInstallGuide() {
        return new HealthBundleInstallGuide();
    }
}
