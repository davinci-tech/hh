package com.huawei.ui.device.activity.watchface;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwdevice.outofprocess.util.HwWatchFaceUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import defpackage.nsy;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;

/* loaded from: classes9.dex */
public class PrivacyStatementActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f9270a;
    private ImageView b;
    private RelativeLayout c;
    private Context d;
    private ImageView e;
    private String g = "";
    private ImageView i;
    private RelativeLayout j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_privacy_statement);
        this.d = this;
        if (getIntent() != null) {
            String stringExtra = getIntent().getStringExtra("from");
            this.g = stringExtra;
            LogUtil.a("PrivacyStatementActivity", "mWhereFrom: ", stringExtra);
        }
        d();
    }

    private void d() {
        this.c = (RelativeLayout) nsy.cMc_(this, R.id.rl_cancel_service);
        this.e = (ImageView) nsy.cMc_(this, R.id.im_cancel_service_right);
        this.j = (RelativeLayout) nsy.cMc_(this, R.id.rl_user_agreement);
        this.i = (ImageView) nsy.cMc_(this, R.id.im_user_agreement_right);
        this.f9270a = (RelativeLayout) nsy.cMc_(this, R.id.rl_privacy_statement);
        this.b = (ImageView) nsy.cMc_(this, R.id.im_privacy_statement_right);
        if (LanguageUtil.bc(this.d)) {
            this.e.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.i.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.b.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.e.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.i.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.b.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.PrivacyStatementActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PrivacyStatementActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.j.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.PrivacyStatementActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(PrivacyStatementActivity.this.d, (Class<?>) WatchfaceUrlActivity.class);
                intent.putExtra("Agreement_key", "termsurl");
                intent.addFlags(268435456);
                HwWatchFaceUtil.bJf_(PrivacyStatementActivity.this.d, intent, "PrivacyStatementActivity");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f9270a.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.PrivacyStatementActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(PrivacyStatementActivity.this.d, (Class<?>) WatchfaceUrlActivity.class);
                intent.putExtra("Agreement_key", "privacyurl");
                intent.addFlags(268435456);
                HwWatchFaceUtil.bJf_(PrivacyStatementActivity.this.d, intent, "PrivacyStatementActivity");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.d);
        builder.b(getResources().getString(R.string._2130843331_res_0x7f0216c3)).e(getResources().getString(R.string._2130843318_res_0x7f0216b6)).cyU_(R.string._2130843341_res_0x7f0216cd, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.PrivacyStatementActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SharedPreferenceManager.b(PrivacyStatementActivity.this.d, false);
                Intent intent = new Intent();
                intent.setAction(com.huawei.watchface.mvp.ui.activity.PrivacyStatementActivity.ACTION_WATCHFACE_SERVICE_DISABLE);
                LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(PrivacyStatementActivity.this.d);
                if (localBroadcastManager != null) {
                    localBroadcastManager.sendBroadcast(intent);
                }
                PrivacyStatementActivity.this.setResult(-1);
                PrivacyStatementActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130843324_res_0x7f0216bc, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.watchface.PrivacyStatementActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("PrivacyStatementActivity", "onClick Negative Button");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
