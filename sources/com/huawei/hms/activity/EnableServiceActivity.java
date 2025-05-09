package com.huawei.hms.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: classes9.dex */
public class EnableServiceActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(getApplicationContext());
        }
        setContentView(ResourceLoaderUtil.getLayoutId("activity_endisable_service"));
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
