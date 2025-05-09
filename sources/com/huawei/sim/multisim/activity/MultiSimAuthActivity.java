package com.huawei.sim.multisim.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.ktx;
import defpackage.ncf;
import java.util.Locale;

/* loaded from: classes6.dex */
public class MultiSimAuthActivity extends BaseActivity implements View.OnClickListener {
    private HealthButton c;
    private HealthButton d;
    private HealthTextView f;
    private CustomTitleBar g;
    private HealthTextView h;
    private Context b = null;
    private String e = "";

    /* renamed from: a, reason: collision with root package name */
    private int f8714a = 1;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("MultiSimAuthActivity", "onCreate");
        this.b = this;
        setContentView(R.layout.activity_multi_sim_auth);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getStringExtra("simImsi");
            this.f8714a = intent.getIntExtra("cardType", 1);
        }
        e();
        d();
    }

    private void e() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.multi_sim_auth_title_bar);
        this.g = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.f = (HealthTextView) findViewById(R.id.multi_sim_auth_notice);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.multi_sim_auth_tip);
        this.h = healthTextView;
        healthTextView.setVisibility(8);
        HealthButton healthButton = (HealthButton) findViewById(R.id.multi_sim_auth_agree);
        this.d = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.multi_sim_auth_cancel);
        this.c = healthButton2;
        healthButton2.setOnClickListener(this);
    }

    private void d() {
        LogUtil.a("MultiSimAuthActivity", "setViewData()");
        this.f.setText(String.format(Locale.ENGLISH, getResources().getString(R.string.IDS_plugin_multi_sim_get_device_info), ncf.c(this.b, this.e, this.f8714a), ktx.e().f()));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.multi_sim_auth_agree) {
            LogUtil.a("MultiSimAuthActivity", "onClick agree button");
            c();
        } else if (view.getId() == R.id.multi_sim_auth_cancel) {
            LogUtil.a("MultiSimAuthActivity", "onClick cancel button");
            b();
        } else {
            LogUtil.h("MultiSimAuthActivity", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("MultiSimAuthActivity", "onClick back button");
        b();
    }

    private void c() {
        ktx.e().e(ncf.e(this.e, this.f8714a));
        ktx.e().e(true);
        ncf.a(this.b, this.e, ncf.d(this.e, this.f8714a));
        finish();
    }

    private void b() {
        ktx.e().e(false);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
