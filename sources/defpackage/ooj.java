package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.device.activity.adddevice.AllDeviceListActivity;
import com.huawei.ui.homehealth.qrcode.activity.QrCodeScanningActivity;
import health.compact.a.GRSManager;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ooj {
    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = GRSManager.a(BaseApplication.getContext()).getUrl("domainMessagecenterHicloud") + "/messageH5/html/HwHealthQRCode.html";
        if (!TextUtils.isEmpty(str2)) {
            if (str2.equalsIgnoreCase(str)) {
                LogUtil.a("R_QrCode_OldDeviceQrAdapter", "is ble watch qr.");
                return true;
            }
        } else {
            LogUtil.h("R_QrCode_OldDeviceQrAdapter", "oldWatchUrl is empty.");
        }
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("domainHealthVmall");
        if (TextUtils.isEmpty(url)) {
            LogUtil.h("R_QrCode_OldDeviceQrAdapter", "urlGrsDomain is empty");
            return false;
        }
        if (!str.toUpperCase(Locale.ENGLISH).startsWith(url.concat("/download/download.jsp?device=").toUpperCase(Locale.ENGLISH))) {
            return false;
        }
        LogUtil.a("R_QrCode_OldDeviceQrAdapter", "is aw watch qr.");
        return true;
    }

    public static void a(final QrCodeScanningActivity qrCodeScanningActivity) {
        if (qrCodeScanningActivity == null) {
            return;
        }
        qrCodeScanningActivity.runOnUiThread(new Runnable() { // from class: ooj.1
            @Override // java.lang.Runnable
            public void run() {
                NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(QrCodeScanningActivity.this);
                builder.e(QrCodeScanningActivity.this.getResources().getString(R$string.IDS_qrcode_old_device_tip));
                builder.czE_(QrCodeScanningActivity.this.getResources().getString(R$string.IDS_qrcode_old_device_go_pair), new View.OnClickListener() { // from class: ooj.1.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        ooj.dea_(QrCodeScanningActivity.this);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                builder.czz_(R$string.IDS_settings_button_cancal_ios_btn, new View.OnClickListener() { // from class: ooj.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        QrCodeScanningActivity.this.a();
                        ViewClickInstrumentation.clickOnView(view);
                    }
                });
                if (QrCodeScanningActivity.this.isFinishing()) {
                    return;
                }
                builder.e().show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void dea_(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setClass(activity, AllDeviceListActivity.class);
            activity.startActivity(intent);
            activity.finish();
        } catch (ActivityNotFoundException e) {
            LogUtil.b("OldDeviceQrAdapter", "ActivityNotFoundException e:", e.getMessage());
        }
    }
}
