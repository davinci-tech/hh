package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;

/* loaded from: classes5.dex */
public class CustomLandingDetailActivity extends LandingDetailsActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.LandingDetailsActivity, com.huawei.openalliance.ad.activity.e, com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.huawei.openalliance.ad.activity.LandingDetailsActivity, com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
