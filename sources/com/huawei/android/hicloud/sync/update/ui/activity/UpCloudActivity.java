package com.huawei.android.hicloud.sync.update.ui.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.android.hicloud.sync.update.UpdateManager;
import com.huawei.android.hicloud.sync.update.ui.dialog.UpHisyncDialogCallback;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import defpackage.aax;
import defpackage.abd;
import defpackage.abj;
import defpackage.abl;

/* loaded from: classes8.dex */
public class UpCloudActivity extends Activity implements UpHisyncDialogCallback {

    /* renamed from: a, reason: collision with root package name */
    private ApkUpgradeInfo f1841a;
    private AlertDialog b;
    private TextView d;
    private aax g;
    private ProgressBar h;
    private AlertDialog i;
    private Context e = this;
    private final Handler c = new e();

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            UpCloudActivity.this.d();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    class b implements DialogInterface.OnClickListener {
        b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            UpCloudActivity upCloudActivity = UpCloudActivity.this;
            upCloudActivity.fr_(upCloudActivity.b);
            UpCloudActivity.this.b = null;
            UpCloudActivity.this.i.show();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    class c implements DialogInterface.OnKeyListener {
        c() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }
    }

    class d implements DialogInterface.OnKeyListener {
        d() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }
    }

    class e extends Handler {
        e() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 2) {
                UpdateManager.a().d(1);
                return;
            }
            if (i == 16) {
                Toast.makeText(UpCloudActivity.this.e, UpCloudActivity.this.e.getString(R.string._2130851196_res_0x7f02357c), 0).show();
                UpdateManager.a().d(2);
                UpCloudActivity.this.finish();
            } else if (i == 4) {
                abd.c("UpCloudActivity", "UPDATE_CODE_SHOW_DOWNLOAD_PROGRESS");
                UpCloudActivity.this.fs_(message, false);
            } else if (i == 5) {
                abd.c("UpCloudActivity", "UPDATE_CODE_DOWNLOAD_FINISH");
                UpCloudActivity.this.fs_(message, true);
                UpCloudActivity.this.finish();
            } else {
                if (i != 6) {
                    return;
                }
                Toast.makeText(UpCloudActivity.this.e, UpCloudActivity.this.e.getString(R.string._2130851191_res_0x7f023577), 0).show();
                UpCloudActivity.this.finish();
            }
        }
    }

    class j implements DialogInterface.OnClickListener {
        j() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            UpdateManager.a().d();
            UpdateManager.a().d(-1);
            UpCloudActivity.this.finish();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    private void c() {
        Intent intent = getIntent();
        if (intent == null) {
            abd.b("UpCloudActivity", "intent is null");
            finish();
            return;
        }
        try {
            this.f1841a = (ApkUpgradeInfo) intent.getSerializableExtra("new_version_info");
        } catch (Exception e2) {
            abd.b("UpCloudActivity", "initView: getApkUpgradeInfo Serializable error " + e2.getMessage());
        }
        aax aaxVar = new aax(this.e, this, this.f1841a != null ? r0.getSize_() : 0L);
        this.g = aaxVar;
        try {
            aaxVar.show();
        } catch (Exception unused) {
            abd.b("UpCloudActivity", "initView exception");
            finish();
        }
    }

    private void c(ApkUpgradeInfo apkUpgradeInfo) {
        fr_(this.g);
        this.g = null;
        e();
        UpdateManager.a().e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.e);
        builder.setOnKeyListener(new c());
        builder.setPositiveButton(R.string._2130851194_res_0x7f02357a, new j()).setNegativeButton(R.string._2130851193_res_0x7f023579, new b());
        builder.setMessage(R.string._2130851192_res_0x7f023578);
        AlertDialog create = builder.create();
        this.b = create;
        create.setCancelable(false);
        fr_(this.i);
        this.b.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fr_(AlertDialog alertDialog) {
        if (alertDialog != null) {
            try {
                alertDialog.dismiss();
            } catch (Exception unused) {
                abd.b("UpCloudActivity", "Dialog dismiss exception");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fs_(Message message, boolean z) {
        TextView textView = this.d;
        if (textView == null || this.h == null) {
            return;
        }
        if (z) {
            textView.setText(this.e.getString(R.string._2130851195_res_0x7f02357b, 100));
            this.h.setProgress(100);
        } else {
            int i = message.arg1;
            textView.setText(this.e.getString(R.string._2130851195_res_0x7f02357b, Integer.valueOf(i)));
            this.h.setProgress(i);
        }
    }

    public void d(ApkUpgradeInfo apkUpgradeInfo) {
        if (apkUpgradeInfo == null || TextUtils.isEmpty(apkUpgradeInfo.getDetailId_()) || TextUtils.isEmpty(apkUpgradeInfo.getPackage_())) {
            return;
        }
        try {
            Intent intent = new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withdetailId");
            intent.setPackage("com.huawei.appmarket");
            intent.putExtra("appDetailId", apkUpgradeInfo.getDetailId_());
            intent.putExtra("thirdId", apkUpgradeInfo.getPackage_());
            startActivity(intent);
            UpdateManager.a().d(11);
            finish();
        } catch (Exception unused) {
            abd.c("UpCloudActivity", "Exception");
            c(apkUpgradeInfo);
        }
    }

    public void e() {
        abd.c("UpCloudActivity", "startDownloadDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this.e);
        builder.setOnKeyListener(new d());
        View inflate = LayoutInflater.from(this.e).inflate(R.layout.up_hisync_download_dialog, (ViewGroup) null);
        TextView textView = (TextView) abj.fw_(inflate, R.id.up_hisync_download_dialog_progress_text);
        this.d = textView;
        textView.setText(this.e.getString(R.string._2130851195_res_0x7f02357b, 0));
        ProgressBar progressBar = (ProgressBar) abj.fw_(inflate, R.id.up_hisync_download_dialog_progress);
        this.h = progressBar;
        progressBar.setProgress(0);
        TextView textView2 = (TextView) abj.fw_(inflate, R.id.up_hisync_download_dialog_content);
        Context context = this.e;
        textView2.setText(context.getString(R.string._2130851602_res_0x7f023712, context.getString(R.string._2130851190_res_0x7f023576)));
        ImageView imageView = (ImageView) abj.fw_(inflate, R.id.cancel_download);
        if (Build.VERSION.SDK_INT > 26) {
            imageView.setImageResource(R.drawable._2131427736_res_0x7f0b0198);
        } else {
            imageView.setImageResource(R.drawable._2131427735_res_0x7f0b0197);
        }
        imageView.setOnClickListener(new a());
        AlertDialog create = builder.create();
        this.i = create;
        create.setCanceledOnTouchOutside(false);
        this.i.setView(inflate);
        this.i.show();
    }

    @Override // com.huawei.android.hicloud.sync.update.ui.dialog.UpHisyncDialogCallback
    public void onClickCancel() {
        abd.c("UpCloudActivity", "onClickCancel");
        UpdateManager.a().d(-1);
        finish();
    }

    @Override // com.huawei.android.hicloud.sync.update.ui.dialog.UpHisyncDialogCallback
    public void onClickInstall() {
        abd.c("UpCloudActivity", "onClickInstall");
        if (abl.b(this.e)) {
            fr_(this.g);
            this.g = null;
            d(this.f1841a);
        } else {
            Context context = this.e;
            Toast.makeText(context, context.getString(R.string._2130851196_res_0x7f02357c), 0).show();
            UpdateManager.a().d(7);
            finish();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        UpdateManager.a().fl_(this.c);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        abd.c("UpCloudActivity", "onDestroy");
        super.onDestroy();
        UpdateManager.a().b();
        fr_(this.g);
        this.g = null;
        fr_(this.i);
        this.i = null;
        fr_(this.b);
        this.b = null;
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
