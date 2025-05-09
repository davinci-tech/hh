package com.huawei.ui.thirdpartservice.activity.strava;

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
import com.huawei.ui.thirdpartservice.activity.strava.StravaHealthActivity;
import com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.sjo;
import health.compact.a.CommonUtil;
import java.util.Locale;

/* loaded from: classes8.dex */
public class StravaHealthActivity extends BaseActivity {
    private CommonDialog21 d = null;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_oauth_connected);
        c();
    }

    private void c() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        ((ImageView) findViewById(R.id.logo_img)).setImageResource(R.mipmap._2131821105_res_0x7f110231);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.sport_connected_content);
        String string = getString(R.string._2130850494_res_0x7f0232be);
        customTitleBar.setTitleText(string);
        healthTextView.setText(getString(R.string._2130844414_res_0x7f021afe, new Object[]{string}));
        ((HealthButton) findViewById(R.id.health_disconnect_button)).setOnClickListener(new View.OnClickListener() { // from class: sgj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StravaHealthActivity.this.dXq_(view);
            }
        });
    }

    public /* synthetic */ void dXq_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        a();
    }

    private void b() {
        if (nsn.o()) {
            return;
        }
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_oauth_revoke, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.oauth_health_dialog_text)).setText(getString(R.string._2130844415_res_0x7f021aff, new Object[]{getString(R.string._2130850494_res_0x7f0232be)}));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this);
        builder.czg_(inflate);
        builder.a(getString(R.string._2130844416_res_0x7f021b00));
        builder.czf_(getString(R.string._2130844416_res_0x7f021b00).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sgf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StravaHealthActivity.this.dXr_(view);
            }
        });
        builder.czd_(getString(R.string._2130845098_res_0x7f021daa).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sgb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void dXr_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        LogUtil.b("StravaHealthActivity", "revokeOauth");
        if (!CommonUtil.aa(this)) {
            LogUtil.a("StravaHealthActivity", "Network is not Connected ");
            nrh.e(this, R.string._2130841393_res_0x7f020f31);
        } else {
            d();
            sjo.d().revokeOauth(new b(this));
        }
    }

    private void d() {
        LogUtil.a("StravaHealthActivity", "showWaitingDialog: mLoadDataDialog ");
        if (this.d != null) {
            return;
        }
        if (isFinishing()) {
            LogUtil.a("StravaHealthActivity", "showWaitingDialog: isFinishing...");
            return;
        }
        CommonDialog21 a2 = CommonDialog21.a(this);
        this.d = a2;
        a2.e(getString(R.string._2130841528_res_0x7f020fb8));
        this.d.setCancelable(false);
        this.d.a();
        this.d.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("StravaHealthActivity", "closeLoadDataDialog: mLoadDataDialog = ", this.d);
        CommonDialog21 commonDialog21 = this.d;
        if (commonDialog21 != null) {
            commonDialog21.dismiss();
            this.d = null;
        }
    }

    public static class b extends ReferenceRevokeOauthCallback<StravaHealthActivity> {
        b(StravaHealthActivity stravaHealthActivity) {
            super(stravaHealthActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.ui.thirdpartservice.syncdata.callback.ReferenceRevokeOauthCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void revokeResultWhenReferenceNotNull(final StravaHealthActivity stravaHealthActivity, final boolean z, final boolean z2, final String str) {
            stravaHealthActivity.runOnUiThread(new Runnable() { // from class: sgk
                @Override // java.lang.Runnable
                public final void run() {
                    StravaHealthActivity.b.d(StravaHealthActivity.this, z, z2, str);
                }
            });
        }

        public static /* synthetic */ void d(StravaHealthActivity stravaHealthActivity, boolean z, boolean z2, String str) {
            stravaHealthActivity.a();
            if (!z) {
                nrh.b(stravaHealthActivity, R.string._2130841884_res_0x7f02111c);
            } else if (z2) {
                stravaHealthActivity.startActivity(new Intent(stravaHealthActivity, (Class<?>) StravaConnectActivity.class));
                stravaHealthActivity.finish();
            } else {
                nrh.c(stravaHealthActivity, str);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
