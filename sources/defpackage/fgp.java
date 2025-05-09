package defpackage;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.health.sportservice.download.listener.DownloadListener;
import com.huawei.ui.commonui.R$string;
import health.compact.a.LogUtil;
import java.util.List;

/* loaded from: classes7.dex */
public class fgp implements InstallGuide {

    /* renamed from: a, reason: collision with root package name */
    private DownloadListener f12497a;
    private Activity d;

    public fgp(DownloadListener downloadListener, Activity activity) {
        this.f12497a = downloadListener;
        this.d = activity;
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public Class<? extends Activity> getTransparentGuideActivity() {
        LogUtil.c("AudioBundleInstallGuide", "getTransparentGuideActivity");
        return this.d.getClass();
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public void showDownloadAskDialog(Activity activity, List<String> list, long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        LogUtil.c("AudioBundleInstallGuide", "showDownloadAskDialog");
        this.f12497a.showDownloadAskDialog(activity, list, j, onClickListener, onClickListener2);
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public InstallGuide.InstallProgress createInstallProgress(Activity activity, String str, View.OnClickListener onClickListener) {
        LogUtil.c("AudioBundleInstallGuide", "createInstallProgress");
        return new b(onClickListener, this.f12497a);
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public void showDownloadErrorDialog(Activity activity, int i, String str, View.OnClickListener onClickListener) {
        LogUtil.c("AudioBundleInstallGuide", "showDownloadErrorDialog");
        this.f12497a.showDownloadErrorDialog(activity, i, str, onClickListener);
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public String getInstallStateDesc(Context context, InstallSessionState installSessionState) {
        String string;
        LogUtil.c("AudioBundleInstallGuide", "getInstallStateDesc state.status()= ", Integer.valueOf(installSessionState.status()));
        int status = installSessionState.status();
        if (status == 1) {
            string = context.getString(R$string.IDS_bundle_download_begin_desc);
        } else if (status == 2) {
            string = context.getString(installSessionState.bytesDownloaded() <= 0 ? R$string.IDS_bundle_download_begin_desc : R$string.IDS_bundle_downloading_prompt);
        } else {
            string = status != 6 ? "" : getInstallErrorDesc(context, installSessionState.errorCode());
        }
        this.f12497a.getInstallStateDesc(string);
        return string;
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public String getInstallErrorDesc(Context context, int i) {
        LogUtil.c("AudioBundleInstallGuide", "getInstallErrorDesc", Integer.valueOf(i));
        if (i != -100) {
            switch (i) {
                case -9:
                    return context.getString(R$string.IDS_bundle_error_service_died);
                case -8:
                    return context.getString(R$string.IDS_bundle_error_existing_session);
                case -7:
                    return context.getString(R$string.IDS_bundle_error_access_denied);
                case -6:
                    return context.getString(R$string.IDS_bundle_error_network_error);
                case -5:
                    return context.getString(R$string.IDS_bundle_error_internal_error);
                case -4:
                    return context.getString(R$string.IDS_bundle_error_internal_error);
                case -3:
                    return context.getString(R$string.IDS_bundle_error_invalid_request);
                case -2:
                    return context.getString(R$string.IDS_bundle_error_module_unavailable);
                case -1:
                case 0:
                    return "";
                default:
                    return context.getString(R$string.IDS_bundle_download_fail);
            }
        }
        return context.getString(R$string.IDS_bundle_error_internal_error);
    }

    /* loaded from: classes8.dex */
    static class b implements InstallGuide.InstallProgress {
        private DownloadListener b;

        b(View.OnClickListener onClickListener, DownloadListener downloadListener) {
            this.b = downloadListener;
            LogUtil.c("AudioBundleInstallGuide", "InstallProgressDialog");
        }

        @Override // com.huawei.haf.bundle.InstallGuide.InstallProgress
        public void update(int i, String str) {
            LogUtil.c("AudioBundleInstallGuide", "update", Integer.valueOf(i));
            DownloadListener downloadListener = this.b;
            if (downloadListener != null) {
                downloadListener.update(i, str);
            }
        }

        @Override // com.huawei.haf.bundle.InstallGuide.InstallProgress
        public void close() {
            LogUtil.c("AudioBundleInstallGuide", "close");
            DownloadListener downloadListener = this.b;
            if (downloadListener != null) {
                downloadListener.close();
            }
        }
    }
}
