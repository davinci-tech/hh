package com.huawei.watchface.mvp.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.secure.android.common.intent.SafeIntent;
import com.huawei.watchface.bb;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes9.dex */
public class HMSSignAuthAgentActivity extends BaseAgentActivity {
    public static final String EXTRA_INTENT = "extra_intent";
    public static final String TAG = "HMSSignAuthAgentActivity";

    @Override // com.huawei.watchface.mvp.ui.activity.BaseAgentActivity, com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        SafeIntent safeIntent = (SafeIntent) new SafeIntent(intent).getParcelableExtra(EXTRA_INTENT);
        if (safeIntent == null) {
            HwLog.i(TAG, "intent1 == null");
            finish();
        } else {
            CommonUtils.startActivityForResult(this, safeIntent, 1001);
        }
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, android.app.Activity
    public Intent getIntent() {
        Intent intent = super.getIntent();
        if (intent == null) {
            return null;
        }
        return new SafeIntent(intent);
    }

    @Override // com.huawei.secure.android.common.activity.SafeFragmentActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        HwLog.i(TAG, "onActivityResult");
        bb.a().a(i, i2, intent);
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
