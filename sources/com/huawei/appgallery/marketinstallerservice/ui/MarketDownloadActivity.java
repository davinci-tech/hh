package com.huawei.appgallery.marketinstallerservice.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.appgallery.marketinstallerservice.api.Constant;
import com.huawei.appgallery.marketinstallerservice.api.MarketInfo;
import com.huawei.appgallery.marketinstallerservice.ui.c;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.secure.android.common.activity.SafeActivity;
import defpackage.agr;
import defpackage.agu;
import defpackage.ahf;

/* loaded from: classes3.dex */
public class MarketDownloadActivity extends SafeActivity implements com.huawei.appgallery.marketinstallerservice.ui.b, c.a {
    private com.huawei.appgallery.marketinstallerservice.ui.a c = new ahf(this, this);
    private TextView b = null;
    private TextView e = null;

    /* renamed from: a, reason: collision with root package name */
    private ProgressBar f1884a = null;
    private View d = null;
    private AlertDialog g = null;
    private AlertDialog h = null;
    private AlertDialog j = null;
    private AlertDialog i = null;
    private int f = 0;
    private boolean n = false;

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (this.c != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                return;
            }
            int i = extras.getInt("android.content.pm.extra.STATUS");
            if (i != 0) {
                this.c.a(-2, 0, 0, i);
                b(-2);
                return;
            }
            this.c.a(0, 0, 0);
        }
        finish();
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.g;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.g.dismiss();
        }
        AlertDialog alertDialog2 = this.h;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.h.dismiss();
        }
        AlertDialog alertDialog3 = this.j;
        if (alertDialog3 == null || !alertDialog3.isShowing()) {
            return;
        }
        this.j.dismiss();
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            r0 = 1
            r7.requestWindowFeature(r0)
            super.onCreate(r8)
            com.huawei.secure.android.common.intent.SafeIntent r8 = new com.huawei.secure.android.common.intent.SafeIntent
            android.content.Intent r0 = r7.getIntent()
            r8.<init>(r0)
            java.lang.String r0 = "callback_key"
            java.lang.String r0 = r8.getStringExtra(r0)
            java.lang.String r1 = "market_info_key"
            java.util.ArrayList r1 = r8.getParcelableArrayListExtra(r1)
            r2 = 0
            r3 = 0
            if (r1 == 0) goto L33
            int r4 = r1.size()
            if (r4 == 0) goto L33
            java.lang.Object r1 = r1.get(r2)
            android.os.Parcelable r1 = (android.os.Parcelable) r1
            boolean r4 = r1 instanceof com.huawei.appgallery.marketinstallerservice.api.MarketInfo
            if (r4 == 0) goto L33
            com.huawei.appgallery.marketinstallerservice.api.MarketInfo r1 = (com.huawei.appgallery.marketinstallerservice.api.MarketInfo) r1
            goto L34
        L33:
            r1 = r3
        L34:
            if (r1 != 0) goto L4a
            java.lang.String r3 = "service_url_key"
            java.lang.String r3 = r8.getStringExtra(r3)
            java.lang.String r4 = "sub_system_key"
            java.lang.String r4 = r8.getStringExtra(r4)
            java.lang.String r5 = "market_pkg_key"
            java.lang.String r5 = r8.getStringExtra(r5)
            goto L4c
        L4a:
            r4 = r3
            r5 = r4
        L4c:
            java.lang.String r6 = "fail_result_type_key"
            int r6 = r8.getIntExtra(r6, r2)
            r7.f = r6
            com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec r6 = new com.huawei.appgallery.marketinstallerservice.api.InstallParamSpec
            r6.<init>()
            r6.setMarketInfo(r1)
            r6.setServerUrl(r3)
            r6.setSubsystem(r4)
            r6.setMarketPkg(r5)
            int r1 = r7.f
            r6.setFailResultPromptType(r1)
            java.lang.String r1 = "is_update_key"
            boolean r8 = r8.getBooleanExtra(r1, r2)
            r7.n = r8
            com.huawei.appgallery.marketinstallerservice.ui.a r8 = r7.c
            if (r8 == 0) goto L79
            r8.a(r6, r0)
        L79:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.appgallery.marketinstallerservice.ui.MarketDownloadActivity.onCreate(android.os.Bundle):void");
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (1000 == i && this.c != null) {
            int i3 = Constant.INSTALL_FAILED_UNKNOW;
            if (intent != null) {
                i3 = intent.getIntExtra("android.intent.extra.INSTALL_RESULT", Constant.INSTALL_FAILED_UNKNOW);
            }
            if (i2 == -1) {
                agr.c("MarketDownloadActivity", "market install succeed");
                this.c.a(0, 0, 0);
            } else {
                if (i2 != 0) {
                    agr.c("MarketDownloadActivity", "market install failed");
                    b(-2);
                    this.c.a(-2, 0, 0, i3);
                    return;
                }
                agr.c("MarketDownloadActivity", "market install userCancel");
                this.c.a(-5, 0, 0);
            }
        }
        finish();
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void finish() {
        if (isFinishing()) {
            return;
        }
        super.finish();
    }

    public void e() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getText(R.string._2130851378_res_0x7f023632));
        builder.setPositiveButton(getResources().getText(R.string._2130851381_res_0x7f023635), new e());
        builder.setNegativeButton(R.string._2130851380_res_0x7f023634, (DialogInterface.OnClickListener) null);
        builder.setOnDismissListener(new b());
        AlertDialog create = builder.create();
        this.i = create;
        create.show();
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.b
    public void d() {
        agr.c("MarketDownloadActivity", "show market download dialog.");
        this.g = new AlertDialog.Builder(this).create();
        View inflate = LayoutInflater.from(this).inflate(getResources().getLayout(R.layout.market_install_app_dl_progress_dialog), (ViewGroup) null);
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.third_app_dl_progressbar);
        this.f1884a = progressBar;
        progressBar.setMax(100);
        this.b = (TextView) inflate.findViewById(R.id.third_app_warn_text);
        this.e = (TextView) inflate.findViewById(R.id.third_app_dl_progress_text);
        View findViewById = inflate.findViewById(R.id.cancel_bg);
        this.d = findViewById;
        findViewById.setOnClickListener(new d());
        this.g.setView(inflate);
        this.g.setCancelable(false);
        this.g.setCanceledOnTouchOutside(false);
        this.e.setText(agu.b(0));
        hL_(this.g);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.b
    public void c() {
        agr.c("MarketDownloadActivity", "show net setting dialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string._2130851388_res_0x7f02363c);
        builder.setPositiveButton(R.string._2130851387_res_0x7f02363b, new c());
        builder.setNegativeButton(R.string._2130851380_res_0x7f023634, (DialogInterface.OnClickListener) null);
        builder.setOnDismissListener(new a());
        AlertDialog create = builder.create();
        this.h = create;
        create.setCancelable(true);
        hL_(this.h);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.c.a
    public void b() {
        this.c.e();
        finish();
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.b
    public void a(MarketInfo marketInfo) {
        TextView textView = this.b;
        if (textView != null) {
            textView.setText(this.n ? getResources().getString(R.string._2130851391_res_0x7f02363f) : getResources().getString(R.string._2130851377_res_0x7f023631, ""));
        }
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.b
    public void a(int i, int i2) {
        View view;
        TextView textView;
        if (i == 1 && this.f1884a != null && (textView = this.e) != null) {
            textView.setText(agu.b(i2));
            this.f1884a.setProgress(i2);
            return;
        }
        if (i != 3 || (view = this.d) == null) {
            return;
        }
        view.setClickable(false);
        TextView textView2 = this.b;
        if (textView2 != null && this.e != null) {
            textView2.setText(getResources().getString(R.string._2130851385_res_0x7f023639));
            this.e.setVisibility(8);
        }
        AlertDialog alertDialog = this.i;
        if (alertDialog == null || !alertDialog.isShowing() || isFinishing()) {
            return;
        }
        this.i.setCancelable(false);
        this.i.dismiss();
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.b
    public void a(int i) {
        b(i);
    }

    @Override // com.huawei.appgallery.marketinstallerservice.ui.c.a
    public void a() {
        this.c.a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        agr.c("MarketDownloadActivity", "market cancelDownload");
        com.huawei.appgallery.marketinstallerservice.ui.a aVar = this.c;
        if (aVar != null) {
            aVar.c();
            this.c.a(-5, 0, 0);
        }
        this.g.dismiss();
        finish();
    }

    private void b(int i) {
        AlertDialog alertDialog = this.g;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.g.dismiss();
        }
        AlertDialog alertDialog2 = this.h;
        if (alertDialog2 != null && alertDialog2.isShowing()) {
            this.h.dismiss();
        }
        int i2 = this.f;
        if (i2 == 0) {
            Toast.makeText(this, getResources().getString(R.string._2130851384_res_0x7f023638), 0).show();
        } else if (i2 == 2) {
            AlertDialog hM_ = com.huawei.appgallery.marketinstallerservice.ui.c.hM_(this, i, this, this.c.d().getAppName());
            this.j = hM_;
            hL_(hM_);
            return;
        }
        finish();
    }

    class a implements DialogInterface.OnDismissListener {
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (MarketDownloadActivity.this.c != null) {
                MarketDownloadActivity.this.c.a(-1, 0, 0);
            }
            MarketDownloadActivity.this.finish();
        }

        a() {
        }
    }

    class b implements DialogInterface.OnDismissListener {
        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (dialogInterface == null || MarketDownloadActivity.this.isFinishing()) {
                return;
            }
            dialogInterface.dismiss();
        }

        b() {
        }
    }

    class c implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent("android.settings.WIRELESS_SETTINGS");
            intent.putExtra("use_emui_ui", true);
            intent.setPackage("com.android.settings");
            try {
                MarketDownloadActivity.this.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                agr.a("MarketDownloadActivity", "can not go Settings", e);
            }
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        c() {
        }
    }

    class d implements View.OnClickListener {
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MarketDownloadActivity.this.e();
            ViewClickInstrumentation.clickOnView(view);
        }

        d() {
        }
    }

    class e implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            MarketDownloadActivity.this.f();
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }

        e() {
        }
    }

    private void hL_(Dialog dialog) {
        if (isDestroyed() || isFinishing()) {
            return;
        }
        dialog.show();
    }
}
