package com.huawei.sim.esim.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;

/* loaded from: classes6.dex */
public class EsimProfileAuthenticationFail extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthButton f8692a;
    private View b;
    private HealthButton c;
    private CustomTitleBar d;
    private int e;
    private View f;
    private int g;
    private HealthButton h;
    private HealthTextView i;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_esim_profile_fail);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getIntExtra("mata_report", 1);
            this.g = intent.getIntExtra("esim_new_original_key", 0);
        }
        e();
        a();
    }

    private void e() {
        this.b = findViewById(R.id.double_button);
        this.f = findViewById(R.id.single_button);
        HealthButton healthButton = (HealthButton) findViewById(R.id.esim_profile_cancel);
        this.f8692a = healthButton;
        healthButton.setOnClickListener(this);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.esim_profile_retry);
        this.h = healthButton2;
        healthButton2.setOnClickListener(this);
        HealthButton healthButton3 = (HealthButton) findViewById(R.id.esim_profile_ok);
        this.c = healthButton3;
        healthButton3.setOnClickListener(this);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.profile_auth_fail_title_bar);
        this.d = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimProfileAuthenticationFail.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimProfileAuthenticationFail.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i = (HealthTextView) findViewById(R.id.profile_auth_tips);
    }

    private void a() {
        int i = this.e;
        if (i == 1) {
            this.b.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setText(R.string._2130848010_res_0x7f02290a);
            return;
        }
        if (i == 8) {
            this.b.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setText(R.string._2130847889_res_0x7f022891);
        } else if (i == 5) {
            this.b.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setText(R.string._2130847886_res_0x7f02288e);
        } else if (i == 6) {
            this.b.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setText(R.string._2130847887_res_0x7f02288f);
        } else {
            this.b.setVisibility(8);
            this.f.setVisibility(0);
            this.i.setText(R.string._2130847883_res_0x7f02288b);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.esim_profile_cancel) {
            d();
        } else if (view.getId() == R.id.esim_profile_retry) {
            b();
        } else if (view.getId() == R.id.esim_profile_ok) {
            d();
        } else {
            LogUtil.h("EsimProfileAuthenticationFail", "onClick other");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            d();
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        Intent intent = new Intent();
        if (this.g == 1) {
            intent.setClass(this, EsimManagerActivity.class);
        } else {
            intent.setClass(this, EsimActivationActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void b() {
        Intent intent = new Intent(this, (Class<?>) QrCodeActivity.class);
        intent.putExtra("esim_new_original_key", this.g);
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
