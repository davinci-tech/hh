package com.huawei.healthcloud.plugintrack.offlinemap.ui.activity;

import android.os.Bundle;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes4.dex */
public abstract class BaseUiActivity extends BaseActivity {
    protected abstract int getLayoutId();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutId());
        translucent();
    }

    private void translucent() {
        getWindow().getDecorView().setSystemUiVisibility(9216);
    }
}
