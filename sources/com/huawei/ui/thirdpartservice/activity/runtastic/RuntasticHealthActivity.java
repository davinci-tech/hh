package com.huawei.ui.thirdpartservice.activity.runtastic;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticHealthActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sjd;
import health.compact.a.CommonUtil;
import java.util.Locale;

/* loaded from: classes8.dex */
public class RuntasticHealthActivity extends BaseActivity {
    private HealthButton d;
    private CommonDialog21 e = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oauth_connected);
        b();
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821104_res_0x7f110230);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connected_content);
        String string = getString(R.string._2130850493_res_0x7f0232bd);
        customTitleBar.setTitleText(string);
        healthTextView.setText(getString(R.string._2130844414_res_0x7f021afe, new Object[]{string}));
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_disconnect_button);
        this.d = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sga
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RuntasticHealthActivity.this.dXn_(view);
            }
        });
    }

    public /* synthetic */ void dXn_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        d();
    }

    private void e() {
        if (nsn.o()) {
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_oauth_revoke, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.oauth_health_dialog_text)).setText(getString(R.string._2130844415_res_0x7f021aff, new Object[]{getString(R.string._2130850493_res_0x7f0232bd)}));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.czg_(inflate);
        builder.a(getString(R.string._2130844416_res_0x7f021b00));
        builder.czf_(getString(R.string._2130844416_res_0x7f021b00).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticHealthActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RuntasticHealthActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czd_(getString(R.string._2130845098_res_0x7f021daa).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticHealthActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.b("RuntasticHealthActivity", "revokeOauth");
        if (!CommonUtil.aa(this)) {
            LogUtil.a("RuntasticHealthActivity", "Network is not Connected ");
            nrh.e(this, R.string._2130841393_res_0x7f020f31);
        } else {
            a();
            sjd.d().revokeOauth(new e(this));
        }
    }

    private void a() {
        LogUtil.a("RuntasticHealthActivity", "showWaitingDialog: mLoadDataDialog ");
        if (this.e != null) {
            return;
        }
        if (isFinishing()) {
            LogUtil.a("RuntasticHealthActivity", "showWaitingDialog: isFinishing...");
            return;
        }
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.e = a2;
        a2.e(getString(R.string._2130841528_res_0x7f020fb8));
        this.e.setCancelable(false);
        this.e.a();
        this.e.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.a("RuntasticHealthActivity", "closeLoadDataDialog: mLoadDataDialog = ", this.e);
        CommonDialog21 commonDialog21 = this.e;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.e = null;
        }
    }

    static class e extends ReferenceRevokeOauthCallback<RuntasticHealthActivity> {
        e(RuntasticHealthActivity runtasticHealthActivity) {
            super(runtasticHealthActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void revokeResultWhenReferenceNotNull(final RuntasticHealthActivity runtasticHealthActivity, final boolean z, final boolean z2, final String str) {
            runtasticHealthActivity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.thirdpartservice.activity.runtastic.RuntasticHealthActivity.e.1
                @Override // java.lang.Runnable
                public void run() {
                    runtasticHealthActivity.d();
                    if (!z) {
                        nrh.b(runtasticHealthActivity, R.string._2130841884_res_0x7f02111c);
                    } else {
                        if (z2) {
                            runtasticHealthActivity.startActivity(new Intent(runtasticHealthActivity, (Class<?>) RuntasticConnectActivity.class));
                            runtasticHealthActivity.finish();
                            return;
                        }
                        nrh.c(runtasticHealthActivity, str);
                    }
                }
            });
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
