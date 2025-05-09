package com.huawei.openalliance.ad.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.ll;

/* loaded from: classes5.dex */
public class PPSNotificationActivity extends f {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ho.b("PPSNotificationActivity", "PPSNotificationActivity activity");
        ll.a(this).a(this, getIntent());
        finish();
    }

    @Override // com.huawei.openalliance.ad.activity.f, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
