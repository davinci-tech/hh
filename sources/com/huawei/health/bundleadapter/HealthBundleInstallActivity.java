package com.huawei.health.bundleadapter;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import com.huawei.haf.bundle.guide.BundleInstallGuideHolder;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.ui.commonui.base.BaseActivity;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public final class HealthBundleInstallActivity extends BaseActivity {
    private final BundleInstallGuideHolder c = new BundleInstallGuideHolder("Bundle_InstallActivity");

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.c("Bundle_InstallActivity", "onCreate");
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setFinishOnTouchOutside(false);
        this.c.xc_(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LogUtil.c("Bundle_InstallActivity", "onDestroy");
        this.c.d();
        super.onDestroy();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
