package com.huawei.sim.esim.view;

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

/* loaded from: classes6.dex */
public class EsimConfirmInvailActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private int f8679a = 9999;
    private HealthTextView c;
    private HealthButton d;
    private CustomTitleBar e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_confirm_invail);
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("confirm_error", 9999);
        this.f8679a = intExtra;
        LogUtil.a("EsimConfirmInvailActivity", "the mErrorCode: ", Integer.valueOf(intExtra));
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.confirm_invalid_title_bar);
        this.e = customTitleBar;
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimConfirmInvailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimConfirmInvailActivity.this.startActivity(new Intent(EsimConfirmInvailActivity.this, (Class<?>) EsimActivationActivity.class));
                EsimConfirmInvailActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HealthButton healthButton = (HealthButton) findViewById(R.id.confirm_button);
        this.d = healthButton;
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.EsimConfirmInvailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EsimConfirmInvailActivity.this.startActivity(new Intent(EsimConfirmInvailActivity.this, (Class<?>) EsimActivationActivity.class));
                EsimConfirmInvailActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c = (HealthTextView) findViewById(R.id.confirm_invide_info);
        int i = this.f8679a;
        if (i == 1000) {
            this.e.setTitleText(getResources().getString(R.string._2130847926_res_0x7f0228b6));
            this.c.setText(R.string._2130847945_res_0x7f0228c9);
        } else if (i == 3) {
            this.e.setTitleText(getResources().getString(R.string._2130847935_res_0x7f0228bf));
            this.c.setText(R.string._2130847878_res_0x7f022886);
        } else {
            this.e.setTitleText(getResources().getString(R.string._2130847926_res_0x7f0228b6));
            this.c.setText(R.string._2130843116_res_0x7f0215ec);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
