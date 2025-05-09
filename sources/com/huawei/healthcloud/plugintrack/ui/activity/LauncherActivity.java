package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes4.dex */
public class LauncherActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private Handler f3648a = new Handler();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.activity_launcher);
        this.f3648a.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.LauncherActivity.2
            @Override // java.lang.Runnable
            public void run() {
                LauncherActivity.this.finish();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
