package com.huawei.ui.homewear21.home.legal;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.nsy;

/* loaded from: classes6.dex */
public class EnterprisePrivacyActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_enterprise_privacy);
        d();
    }

    private void d() {
        LogUtil.a("EnterprisePrivacyActivity", "EnterprisePrivacyActivity enter");
        ((CustomTitleBar) nsy.cMc_(this, R.id.privacy_title_bar)).setTitleText(BaseApplication.getContext().getString(R.string._2130845404_res_0x7f021edc));
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
