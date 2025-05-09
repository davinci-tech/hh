package com.alipay.sdk.app;

import android.content.res.Configuration;
import com.huawei.haf.language.LanguageInstallHelper;

/* loaded from: classes7.dex */
public class H5AuthActivity extends H5PayActivity {
    @Override // com.alipay.sdk.app.H5PayActivity
    public void e() {
        Object obj = AuthTask.c;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.alipay.sdk.app.H5PayActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
