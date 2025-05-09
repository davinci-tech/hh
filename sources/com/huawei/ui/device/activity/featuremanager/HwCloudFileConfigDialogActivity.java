package com.huawei.ui.device.activity.featuremanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.RewardKeys;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import defpackage.jfq;
import defpackage.jyw;

/* loaded from: classes9.dex */
public class HwCloudFileConfigDialogActivity extends BaseActivity {
    private HealthProgressBar b;
    private Bundle c;
    private int d;
    private boolean e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("HwCloudFileConfigDialogActivity", "HwCloudFileConfigDialogActivity onCreate.");
        Window window = getWindow();
        window.setGravity(80);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        Intent intent = getIntent();
        if (intent != null) {
            this.c = intent.getBundleExtra("bundle");
            this.d = intent.getIntExtra(RewardKeys.DIALOG_TYPE, 0);
            d();
        }
    }

    private void d() {
        int i = this.d;
        if (i == 1) {
            cPO_(this.c);
            return;
        }
        if (i == 2) {
            cPP_(this.c);
            return;
        }
        if (i == 3) {
            cPQ_(this.c);
        } else if (i == 4) {
            b();
        } else {
            LogUtil.h("HwCloudFileConfigDialogActivity", "other dialogStyle: ", Integer.valueOf(i));
            finish();
        }
    }

    private void cPO_(Bundle bundle) {
        LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadDialog");
        this.e = true;
        int bLj_ = jyw.bLj_(bundle);
        String b = jyw.b(bundle.getString("hex_data"));
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this);
        builder.b(getResources().getString(R.string.IDS_notify_file_content_title));
        builder.e(jyw.b(bLj_, b));
        builder.cyS_(getResources().getString(R.string.IDS_notify_cancel), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadDialog cancle");
                HwCloudFileConfigDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cyV_(getResources().getString(R.string.IDS_notify_agree), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadDialog download");
                HwCloudFileConfigDialogActivity.this.e = false;
                HwCloudFileConfigDialogActivity.this.finish();
                jfq.c().d("featureManager", new DeviceInfo(), 0, "start_download");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        a2.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        a2.show();
    }

    private void cPP_(Bundle bundle) {
        LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadErrorDialog");
        this.e = true;
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getResources().getString(R.string.IDS_notify_file_download_error_tips));
        builder.czA_(getResources().getString(R.string.IDS_notify_cancel), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadErrorDialog cancle");
                HwCloudFileConfigDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czE_(getResources().getString(R.string.IDS_notify_retry), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showDownloadErrorDialog retry");
                HwCloudFileConfigDialogActivity.this.e = false;
                HwCloudFileConfigDialogActivity.this.finish();
                jfq.c().d("featureManager", new DeviceInfo(), 0, "start_download");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        e.show();
    }

    private void cPQ_(Bundle bundle) {
        LogUtil.a("HwCloudFileConfigDialogActivity", "showTransErrorDialog");
        this.e = true;
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getResources().getString(R.string.IDS_file_transport_error_tips));
        builder.czA_(getResources().getString(R.string.IDS_notify_cancel), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showTransErrorDialog cancle");
                HwCloudFileConfigDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czE_(getResources().getString(R.string.IDS_notify_retry), new View.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showTransErrorDialog retry");
                HwCloudFileConfigDialogActivity.this.e = false;
                HwCloudFileConfigDialogActivity.this.finish();
                jfq.c().d("featureManager", new DeviceInfo(), 0, "start_transfor");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        e.show();
    }

    private void b() {
        LogUtil.a("HwCloudFileConfigDialogActivity", "showTransDoneDialog");
        this.e = true;
        LayoutInflater layoutInflater = getSystemService("layout_inflater") instanceof LayoutInflater ? (LayoutInflater) getSystemService("layout_inflater") : null;
        if (layoutInflater == null) {
            LogUtil.h("HwCloudFileConfigDialogActivity", "showTransDoneDialog, inflater is null");
            return;
        }
        View inflate = layoutInflater.inflate(R.layout.dialog_translate_done_layout, (ViewGroup) null);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        HealthProgressBar healthProgressBar = (HealthProgressBar) inflate.findViewById(R.id.transfor_progress);
        this.b = healthProgressBar;
        healthProgressBar.d(this, R.color._2131296657_res_0x7f090191, R.color.emui_accent);
        this.b.setProgress(100);
        builder.cyn_(R.string.IDS_notify_done, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.activity.featuremanager.HwCloudFileConfigDialogActivity.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("HwCloudFileConfigDialogActivity", "showTransDoneDialog cancle");
                HwCloudFileConfigDialogActivity.this.finish();
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        builder.cyp_(inflate);
        CustomAlertDialog c = builder.c();
        c.setCancelable(false);
        if (isFinishing()) {
            return;
        }
        c.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("HwCloudFileConfigDialogActivity", "onDestroy");
        if (this.e) {
            jfq.c().d("featureManager", new DeviceInfo(), 0, "task_complete");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
