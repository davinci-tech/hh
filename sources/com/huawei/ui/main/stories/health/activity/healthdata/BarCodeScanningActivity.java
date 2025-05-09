package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import defpackage.ezd;
import defpackage.nsn;

/* loaded from: classes.dex */
public class BarCodeScanningActivity extends BaseActivity {
    private Context c;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.c = this;
        e();
    }

    private void e() {
        LogUtil.a("BarCodeScanningActivity", "startBarCodeScan");
        ezd.aud_(this, 1001);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1001) {
            if (i2 != -1) {
                LogUtil.b("BarCodeScanningActivity", "scan failed...");
                finish();
                return;
            }
            int aub_ = ezd.aub_(intent);
            LogUtil.a("BarCodeScanningActivity", "status code from scanKit: " + aub_);
            if (aub_ == 0) {
                String auc_ = ezd.auc_(intent);
                LogUtil.a("BarCodeScanningActivity", "scan success, result: " + auc_);
                setResult(-1, new Intent().putExtra("result", auc_));
            } else if (aub_ == 2) {
                LogUtil.h("BarCodeScanningActivity", "aborting, no Storage permission...");
                nsn.cKS_(this, 1001);
            }
        }
        finish();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
