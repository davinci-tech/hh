package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.huawei.haf.bundle.AppBundle;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.health.sportservice.download.common.BaseDownloader;
import com.huawei.health.sportservice.download.listener.DownloadListener;
import com.huawei.health.sportservice.download.listener.ResDownloadCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class fhd extends BaseDownloader {

    /* renamed from: a, reason: collision with root package name */
    private Activity f12509a;
    private View.OnClickListener b;
    private ResDownloadCallback c;
    private String d;
    private View.OnClickListener e;
    private float j;
    private int h = 0;
    private boolean i = false;
    private DownloadListener g = new DownloadListener() { // from class: fhd.2
        @Override // com.huawei.health.sportservice.download.listener.DownloadListener
        public void getInstallStateDesc(String str) {
        }

        @Override // com.huawei.health.sportservice.download.listener.DownloadListener
        public void showDownloadAskDialog(Context context, List<String> list, long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
            LogUtil.a("SportService_BundleDownload", "showDownloadAskDialog mIsCancelDownload = ", Boolean.valueOf(fhd.this.i));
            if (fhd.this.i) {
                return;
            }
            fhd.this.e = onClickListener2;
            fhd.this.b = onClickListener;
            fhd.this.c.onQueryResult("multilingualBundleDownload", true, false, j, null);
        }

        @Override // com.huawei.health.sportservice.download.listener.DownloadListener
        public void showDownloadErrorDialog(Activity activity, int i, String str, View.OnClickListener onClickListener) {
            LogUtil.a("SportService_BundleDownload", "showDownloadErrorDialog mIsCancelDownload = ", Boolean.valueOf(fhd.this.i));
            onClickListener.onClick(null);
            if (fhd.this.i) {
                return;
            }
            fhd.this.c.onDownloadFail(i, str);
        }

        @Override // com.huawei.health.sportservice.download.listener.DownloadListener
        public void update(int i, String str) {
            int i2 = i - fhd.this.h;
            fhd.this.h = i;
            if (i2 < 0) {
                i2 = 0;
            }
            fhd.this.c.onProgress((int) (i2 * fhd.this.j), "multilingualBundleDownload");
        }

        @Override // com.huawei.health.sportservice.download.listener.DownloadListener
        public void close() {
            fhd.this.c.onCompleteDownload("multilingualBundleDownload");
        }
    };

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public int getMaxNumberOfDownload() {
        return 1;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void onStartDownload() {
    }

    public fhd(Activity activity, String str) {
        this.d = str;
        this.f12509a = activity;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void startQueryState() {
        if (AppBundle.e().isInstalledModule(this.d)) {
            LogUtil.a("SportService_BundleDownload", "startQueryState isInstalledModule = ", this.d);
            if (this.i) {
                return;
            }
            this.c.onQueryResult("multilingualBundleDownload", false, false, 0L, null);
            this.c.onInstallSuccess("multilingualBundleDownload");
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("moduleName", this.d);
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.health.MainActivity");
        LogUtil.a("SportService_BundleDownload", "startQueryState = ", this.d);
        AppBundle.e().launchActivity(this.f12509a, intent, new c(null), new fgp(this.g, this.f12509a));
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void onCancelDownload() {
        LogUtil.a("SportService_BundleDownload", "onCancelDownload");
        this.i = true;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public View.OnClickListener cancelListener() {
        return this.b;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public View.OnClickListener confirmListener() {
        return this.e;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void setDownloadListener(ResDownloadCallback resDownloadCallback) {
        this.c = resDownloadCallback;
    }

    @Override // com.huawei.health.sportservice.download.common.BaseDownloader
    public void setProgressRatio(String str, float f) {
        if ("multilingualBundleDownload".equals(str)) {
            this.j = f;
        }
    }

    class c implements AppBundleLauncher.InstallCallback {
        c(AppBundleLauncher.InstallCallback installCallback) {
        }

        @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
        public boolean call(Context context, Intent intent) {
            LogUtil.a("SportService_BundleDownload", "InnerInstallCallback downloaded mIsCancelDownload = ", Boolean.valueOf(fhd.this.i));
            if (fhd.this.i) {
                return false;
            }
            fhd.this.c.onQueryResult("multilingualBundleDownload", false, false, 0L, null);
            fhd.this.c.onInstallSuccess("multilingualBundleDownload");
            return true;
        }
    }
}
