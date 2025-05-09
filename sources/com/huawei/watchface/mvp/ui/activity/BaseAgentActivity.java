package com.huawei.watchface.mvp.ui.activity;

import android.os.Bundle;
import android.view.Window;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.secure.android.common.activity.SafeFragmentActivity;

/* loaded from: classes9.dex */
public class BaseAgentActivity extends SafeFragmentActivity {
    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestActivityTransparent();
    }

    private void requestActivityTransparent() {
        requestWindowFeature(1);
        Window window = getWindow();
        if (window != null) {
            window.addFlags(AppRouterExtras.COLDSTART);
        }
    }
}
