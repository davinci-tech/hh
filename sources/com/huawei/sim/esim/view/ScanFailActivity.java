package com.huawei.sim.esim.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.sim.esim.qrcode.QrCodeActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;

/* loaded from: classes6.dex */
public class ScanFailActivity extends BaseActivity implements View.OnClickListener {
    private HealthButton c;
    private CustomTitleBar d;
    private int e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan_failed);
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getIntExtra("esim_new_original_key", 0);
        }
        HealthButton healthButton = (HealthButton) findViewById(R.id.rescan_qrcode_button);
        this.c = healthButton;
        healthButton.setOnClickListener(this);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.scan_fail_title_bar);
        this.d = customTitleBar;
        customTitleBar.setLeftButtonClickable(true);
        this.d.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.sim.esim.view.ScanFailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ScanFailActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            e();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.rescan_qrcode_button) {
            Intent intent = new Intent(this, (Class<?>) QrCodeActivity.class);
            intent.putExtra("esim_new_original_key", this.e);
            startActivity(intent);
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        Intent intent = new Intent();
        if (this.e == 1) {
            intent.setClass(this, EsimManagerActivity.class);
        } else {
            intent.setClass(this, EsimActivationActivity.class);
        }
        startActivity(intent);
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
