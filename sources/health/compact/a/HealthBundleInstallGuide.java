package health.compact.a;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import com.huawei.haf.bundle.InstallGuide;
import com.huawei.haf.bundle.InstallSessionState;
import com.huawei.health.R;
import com.huawei.health.bundleadapter.HealthBundleInstallActivity;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.ccl;
import defpackage.cco;
import java.util.List;

/* loaded from: classes.dex */
public final class HealthBundleInstallGuide implements InstallGuide {
    @Override // com.huawei.haf.bundle.InstallGuide
    public Class<? extends Activity> getTransparentGuideActivity() {
        return HealthBundleInstallActivity.class;
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public void showDownloadAskDialog(Activity activity, List<String> list, long j, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        new cco(activity, list, j).Da_(onClickListener, onClickListener2);
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public InstallGuide.InstallProgress createInstallProgress(Activity activity, String str, View.OnClickListener onClickListener) {
        return new a(activity, str, onClickListener);
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public void showDownloadErrorDialog(Activity activity, int i, String str, View.OnClickListener onClickListener) {
        ccl cclVar = new ccl(onClickListener);
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(activity).b(R.string.IDS_service_area_notice_title).e(str).cyR_(R.string._2130841132_res_0x7f020e2c, cclVar).a();
        a2.setCancelable(false);
        a2.setOnDismissListener(cclVar);
        a2.show();
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public String getInstallStateDesc(Context context, InstallSessionState installSessionState) {
        int status = installSessionState.status();
        int i = R.string._2130844003_res_0x7f021963;
        switch (status) {
            case 1:
                return context.getString(R.string._2130844003_res_0x7f021963);
            case 2:
                if (installSessionState.bytesDownloaded() > 0) {
                    i = R.string._2130843998_res_0x7f02195e;
                }
                return context.getString(i);
            case 3:
                return context.getString(R.string.IDS_bundle_downloaded_prompt);
            case 4:
                return context.getString(R.string._2130843999_res_0x7f02195f);
            case 5:
                return context.getString(R.string._2130844000_res_0x7f021960);
            case 6:
                return getInstallErrorDesc(context, installSessionState.errorCode());
            default:
                return "";
        }
    }

    @Override // com.huawei.haf.bundle.InstallGuide
    public String getInstallErrorDesc(Context context, int i) {
        if (i != -100) {
            switch (i) {
                case -9:
                    return context.getString(R.string._2130843996_res_0x7f02195c);
                case -8:
                    return context.getString(R.string._2130843995_res_0x7f02195b);
                case -7:
                    return context.getString(R.string._2130843994_res_0x7f02195a);
                case -6:
                    return context.getString(R.string._2130843993_res_0x7f021959);
                case -5:
                    return context.getString(R.string._2130843997_res_0x7f02195d);
                case -4:
                    return context.getString(R.string._2130843997_res_0x7f02195d);
                case -3:
                    return context.getString(R.string._2130843992_res_0x7f021958);
                case -2:
                    return context.getString(R.string._2130843991_res_0x7f021957);
                case -1:
                case 0:
                    return "";
                default:
                    return context.getString(R.string._2130844004_res_0x7f021964);
            }
        }
        return context.getString(R.string._2130843997_res_0x7f02195d);
    }

    static class a implements InstallGuide.InstallProgress {
        private final Activity b;
        private CustomProgressDialog d;
        private CustomProgressDialog.Builder e;

        a(Activity activity, String str, View.OnClickListener onClickListener) {
            this.b = activity;
            CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(activity);
            this.e = builder;
            builder.d(str).cyH_(onClickListener);
            CustomProgressDialog e = this.e.e();
            this.d = e;
            e.setCanceledOnTouchOutside(false);
        }

        @Override // com.huawei.haf.bundle.InstallGuide.InstallProgress
        public void update(int i, String str) {
            CustomProgressDialog.Builder builder = this.e;
            CustomProgressDialog customProgressDialog = this.d;
            if (builder == null || customProgressDialog == null || this.b.isFinishing()) {
                return;
            }
            if (i > 0) {
                builder.d(i);
            }
            if (!TextUtils.isEmpty(str)) {
                builder.d(str);
            }
            if (customProgressDialog.isShowing()) {
                return;
            }
            customProgressDialog.show();
        }

        @Override // com.huawei.haf.bundle.InstallGuide.InstallProgress
        public void close() {
            CustomProgressDialog customProgressDialog = this.d;
            this.d = null;
            this.e = null;
            if (customProgressDialog != null) {
                customProgressDialog.dismiss();
            }
        }
    }
}
