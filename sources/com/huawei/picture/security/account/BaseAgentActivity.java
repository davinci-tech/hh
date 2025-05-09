package com.huawei.picture.security.account;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.secure.android.common.activity.SafeActivity;

/* loaded from: classes6.dex */
public class BaseAgentActivity extends SafeActivity {
    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e();
    }

    private void e() {
        requestWindowFeature(1);
        getWindow().getDecorView().setSystemUiVisibility(256);
    }

    @Override // com.huawei.secure.android.common.activity.SafeActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }
}
