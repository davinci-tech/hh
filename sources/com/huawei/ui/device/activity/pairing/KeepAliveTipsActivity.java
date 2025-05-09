package com.huawei.ui.device.activity.pairing;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.nsy;
import defpackage.oau;
import defpackage.obb;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;

/* loaded from: classes6.dex */
public class KeepAliveTipsActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f9198a;
    private NoTitleCustomAlertDialog b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private HealthButton f;
    private HealthTextView h;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_keep_alive_tips);
        b();
    }

    private void b() {
        this.h = (HealthTextView) nsy.cMc_(this, R.id.notification_affect);
        if (Utils.o()) {
            this.h.setText(R.string._2130846716_res_0x7f0223fc);
        }
        this.f = (HealthButton) nsy.cMc_(this, R.id.btn_set_keep_alive);
        this.f9198a = (HealthButton) nsy.cMc_(this, R.id.btn_completed);
        this.f.setOnClickListener(this);
        this.f9198a.setOnClickListener(this);
        this.c = (ImageView) nsy.cMc_(this, R.id.img_notification);
        this.d = (ImageView) nsy.cMc_(this, R.id.img_call_phone);
        this.e = (ImageView) nsy.cMc_(this, R.id.img_endurance);
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            Drawable drawable = getResources().getDrawable(R.mipmap._2131821229_res_0x7f1102ad);
            drawable.setAutoMirrored(true);
            this.c.setImageDrawable(drawable);
            Drawable drawable2 = getResources().getDrawable(R.mipmap._2131821224_res_0x7f1102a8);
            drawable2.setAutoMirrored(true);
            this.d.setImageDrawable(drawable2);
            Drawable drawable3 = getResources().getDrawable(R.mipmap._2131821225_res_0x7f1102a9);
            drawable3.setAutoMirrored(true);
            this.e.setImageDrawable(drawable3);
        }
        if (Utils.i() && CommonUtil.h(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1009)) == 7) {
            this.f.setBackgroundResource(R.drawable._2131428850_res_0x7f0b05f2);
            this.f.setTextColor(getColor(R.color._2131296927_res_0x7f09029f));
            this.f9198a.setBackgroundResource(R.drawable._2131428850_res_0x7f0b05f2);
            this.f9198a.setTextColor(getColor(R.color._2131296927_res_0x7f09029f));
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.btn_set_keep_alive) {
            Intent intent = getIntent();
            if (intent != null) {
                oau.c(100104, intent.getStringExtra("DEVICE_NAME"));
            }
            e();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        if (view.getId() == R.id.btn_completed) {
            d();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void e() {
        ThreadPoolManager.d().d("KeepAliveTipsActivity", new Runnable() { // from class: com.huawei.ui.device.activity.pairing.KeepAliveTipsActivity.5
            @Override // java.lang.Runnable
            public void run() {
                obb.d(KeepAliveTipsActivity.this, "11075");
            }
        });
    }

    private void d() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                this.b.dismiss();
            }
            this.b = null;
        }
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this).e(R.string._2130845369_res_0x7f021eb9).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.KeepAliveTipsActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (KeepAliveTipsActivity.this.b != null) {
                    KeepAliveTipsActivity.this.b.dismiss();
                    KeepAliveTipsActivity.this.b = null;
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czC_(R.string._2130845372_res_0x7f021ebc, new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.KeepAliveTipsActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (KeepAliveTipsActivity.this.b != null) {
                    KeepAliveTipsActivity.this.b.dismiss();
                    KeepAliveTipsActivity.this.b = null;
                }
                KeepAliveTipsActivity.this.setResult(3, new Intent());
                KeepAliveTipsActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.b = e;
        e.setCancelable(false);
        this.b.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d("KeepAliveTipsActivity", "onDestroy()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.d("KeepAliveTipsActivity", "initViewTahiti()");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.d("KeepAliveTipsActivity", "onBackPressed()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
