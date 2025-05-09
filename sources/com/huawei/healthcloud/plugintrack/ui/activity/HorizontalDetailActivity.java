package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.fragment.PaceHorizontalFrag;
import com.huawei.healthcloud.plugintrack.ui.fragment.SegmentHorizontalFrag;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;

/* loaded from: classes4.dex */
public class HorizontalDetailActivity extends BaseActivity {
    private float b = 1.0f;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        if (bundle != null) {
            bundle.putParcelable("android:support:fragments", null);
        }
        super.onCreate(bundle);
        setContentView(R.layout.activity_track_fragment);
        this.b = getResources().getConfiguration().fontScale;
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Track_HorizontalDetailActivity", "intent = null");
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("fragment_tag");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("Track_HorizontalDetailActivity", "fragmentTag = null");
            finish();
            return;
        }
        if (stringExtra.equals("pace")) {
            LogUtil.a("Track_HorizontalDetailActivity", "from PACE_TAG");
            beginTransaction.add(R.id.fragment_container, new PaceHorizontalFrag());
        } else if (stringExtra.equals("segmentation")) {
            LogUtil.a("Track_HorizontalDetailActivity", "from segmentation");
            beginTransaction.add(R.id.fragment_container, new SegmentHorizontalFrag());
        } else {
            LogUtil.h("Track_HorizontalDetailActivity", "mFragmentTag exception");
            finish();
            return;
        }
        beginTransaction.commit();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setTahitiModelOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void setDefaultOrientation() {
        setRequestedOrientation(0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        e(1.0f);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        e(this.b);
    }

    private void e(float f) {
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = f;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
        finish();
    }
}
