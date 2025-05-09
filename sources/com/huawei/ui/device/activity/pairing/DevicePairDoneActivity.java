package com.huawei.ui.device.activity.pairing;

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
import defpackage.nsy;
import defpackage.oau;

/* loaded from: classes6.dex */
public class DevicePairDoneActivity extends BaseActivity {
    private HealthButton c;
    private HealthButton e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_pair_done);
        LogUtil.a("DevicePairDoneActivity", "onCreate()");
        this.e = (HealthButton) nsy.cMc_(this, R.id.device_pair_enable_button);
        this.c = (HealthButton) nsy.cMc_(this, R.id.device_pair_manual_button);
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairDoneActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DevicePairDoneActivity", "mPairEnableButton onclick");
                oau.b(1);
                DevicePairDoneActivity.this.c(2);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairDoneActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("DevicePairDoneActivity", "mPairManualButton onclick");
                oau.b(2);
                DevicePairDoneActivity.this.c(1);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        setResult(i, new Intent());
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("DevicePairDoneActivity", "onDestroy()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.a("DevicePairDoneActivity", "initViewTahiti()");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        LogUtil.a("DevicePairDoneActivity", "onBackPressed()");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
