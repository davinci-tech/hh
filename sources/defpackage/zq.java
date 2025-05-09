package defpackage;

import com.huawei.android.bundlecore.download.DownloadCallback;
import com.huawei.android.bundlecore.download.DownloadRequest;
import com.huawei.android.bundlecore.download.Downloader;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleBuildConfig;
import com.huawei.haf.bundle.AppBundleDownloader;
import com.huawei.haf.bundle.AppBundleSetting;
import com.huawei.haf.common.utils.NetworkUtil;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public final class zq implements Downloader {
    private final AppBundleDownloader b;

    @Override // com.huawei.android.bundlecore.download.Downloader
    public long getDownloadSizeThresholdWhenUsingMobileData() {
        return 10485760L;
    }

    public zq(AppBundleDownloader appBundleDownloader) {
        this.b = appBundleDownloader;
    }

    @Override // com.huawei.android.bundlecore.download.Downloader
    public void startDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback) {
        this.b.startDownload(i, d(list), downloadCallback);
    }

    @Override // com.huawei.android.bundlecore.download.Downloader
    public void deferredDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback, boolean z) {
        boolean j = NetworkUtil.j();
        boolean f = NetworkUtil.f();
        boolean b = b(list);
        LogUtil.c("Bundle_Downloader", "deferredDownload isNetworkAvailable=", String.valueOf(j), ", isMobileAvailable=", String.valueOf(f), ", isUsingMobile=", String.valueOf(z), ", isForceUpdate=", String.valueOf(b));
        if (j) {
            if (!f || z || b) {
                this.b.deferredDownload(i, d(list), downloadCallback);
            }
        }
    }

    @Override // com.huawei.android.bundlecore.download.Downloader
    public boolean cancelDownloadSync(int i) {
        return this.b.cancelDownloadSync(i);
    }

    @Override // com.huawei.android.bundlecore.download.Downloader
    public boolean isDeferredDownloadOnlyWhenUsingWifiData() {
        return !c().isAllowUsingMobileUpdateModule();
    }

    private List<AppBundleDownloader.DownloadRequest> d(List<DownloadRequest> list) {
        return new ArrayList(list);
    }

    private boolean b(List<DownloadRequest> list) {
        AppBundleSetting c = c();
        Iterator<DownloadRequest> it = list.iterator();
        while (it.hasNext()) {
            if (c.isAllowDownloadModule(AppBundleBuildConfig.c(), it.next().getModuleName())) {
                return true;
            }
        }
        return false;
    }

    private AppBundleSetting c() {
        return AppBundle.c().getSetting();
    }
}
