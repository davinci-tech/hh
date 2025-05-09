package com.huawei.sim.esim.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.cvs;
import defpackage.ktx;

/* loaded from: classes6.dex */
public class EsimProfileSuccessActivity extends BaseActivity implements View.OnClickListener {
    private HealthButton b;
    private HealthTextView c;
    private ImageView d;
    private boolean e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_confirm_success);
        HealthButton healthButton = (HealthButton) findViewById(R.id.confirm_button);
        this.b = healthButton;
        healthButton.setOnClickListener(this);
        if (getIntent() != null) {
            this.e = getIntent().getBooleanExtra("confirm_status", false);
        }
        this.d = (ImageView) findViewById(R.id.confirm_success_image);
        this.c = (HealthTextView) findViewById(R.id.confirm_sucess_tips);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.e) {
            this.c.setVisibility(0);
            this.d.setImageResource(R.drawable._2131431494_res_0x7f0b1046);
        } else {
            this.c.setVisibility(4);
            this.d.setImageResource(R.drawable._2131431490_res_0x7f0b1042);
        }
        ktx.e().a(true);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.confirm_button) {
            startActivity(new Intent(this, (Class<?>) ((cvs.d() == null || !cvs.d().isSupportNewEsim()) ? WirelessManagerActivity.class : EsimManagerActivity.class)));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
