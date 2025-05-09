package com.huawei.ui.thirdpartservice.activity.komoot;

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
import com.huawei.ui.thirdpartservice.activity.komoot.KomootHealthActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sir;
import health.compact.a.CommonUtil;
import java.util.Locale;

/* loaded from: classes7.dex */
public class KomootHealthActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f10548a;
    private CommonDialog21 c = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oauth_connected);
        d();
    }

    private void d() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821102_res_0x7f11022e);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connected_content);
        String string = getString(R.string._2130850492_res_0x7f0232bc);
        customTitleBar.setTitleText(string);
        healthTextView.setText(getString(R.string._2130844414_res_0x7f021afe, new Object[]{string}));
        HealthButton healthButton = (HealthButton) findViewById(R.id.health_disconnect_button);
        this.f10548a = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: sfq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                KomootHealthActivity.this.dXd_(view);
            }
        });
    }

    public /* synthetic */ void dXd_(View view) {
        a();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        c();
    }

    private void a() {
        if (nsn.o()) {
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_oauth_revoke, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.oauth_health_dialog_text)).setText(getString(R.string._2130844415_res_0x7f021aff, new Object[]{getString(R.string._2130850492_res_0x7f0232bc)}));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.czg_(inflate);
        builder.a(getString(R.string._2130844416_res_0x7f021b00));
        builder.czf_(getString(R.string._2130844416_res_0x7f021b00).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.thirdpartservice.activity.komoot.KomootHealthActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                KomootHealthActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.czd_(getString(R.string._2130845098_res_0x7f021daa).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: com.huawei.ui.thirdpartservice.activity.komoot.KomootHealthActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.b("KomootHealthActivity", "revokeOauth");
        if (!CommonUtil.aa(this)) {
            LogUtil.a("KomootHealthActivity", "Network is not Connected ");
            nrh.e(this, R.string._2130841393_res_0x7f020f31);
        } else {
            b();
            sir.c().revokeOauth(new c(this));
        }
    }

    private void b() {
        LogUtil.a("KomootHealthActivity", "showWaitingDialog: mLoadDataDialog ");
        if (this.c != null) {
            return;
        }
        if (isFinishing()) {
            LogUtil.a("KomootHealthActivity", "showWaitingDialog: isFinishing...");
            return;
        }
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.c = a2;
        a2.e(getString(R.string._2130841528_res_0x7f020fb8));
        this.c.setCancelable(false);
        this.c.a();
        this.c.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("KomootHealthActivity", "closeLoadDataDialog: mLoadDataDialog = ", this.c);
        CommonDialog21 commonDialog21 = this.c;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.c = null;
        }
    }

    static class c extends ReferenceRevokeOauthCallback<KomootHealthActivity> {
        c(KomootHealthActivity komootHealthActivity) {
            super(komootHealthActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void revokeResultWhenReferenceNotNull(final KomootHealthActivity komootHealthActivity, final boolean z, final boolean z2, final String str) {
            komootHealthActivity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.thirdpartservice.activity.komoot.KomootHealthActivity.c.3
                @Override // java.lang.Runnable
                public void run() {
                    komootHealthActivity.c();
                    if (!z) {
                        nrh.b(komootHealthActivity, R.string._2130841884_res_0x7f02111c);
                    } else {
                        if (z2) {
                            komootHealthActivity.startActivity(new Intent(komootHealthActivity, (Class<?>) KomootConnectActivity.class));
                            komootHealthActivity.finish();
                            return;
                        }
                        nrh.c(komootHealthActivity, str);
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
