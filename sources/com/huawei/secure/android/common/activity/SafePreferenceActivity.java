package com.huawei.secure.android.common.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import com.huawei.secure.android.common.intent.IntentUtils;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes9.dex */
public class SafePreferenceActivity extends PreferenceActivity {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8559a = "SafePreferenceActivity";

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception e) {
            a.a(f8559a, "finish exception : " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        try {
            super.finishAffinity();
        } catch (Exception e) {
            a.a(f8559a, "finishAffinity: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        try {
            return new SafeIntent(super.getIntent());
        } catch (Exception e) {
            a.a(f8559a, "getIntent: " + e.getMessage(), true);
            return new SafeIntent(new Intent());
        }
    }

    @Override // android.app.Activity
    public Uri getReferrer() {
        try {
            return super.getReferrer();
        } catch (Exception e) {
            a.a(f8559a, "getReferrer: " + e.getMessage(), true);
            return null;
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, new SafeIntent(intent));
        } catch (Exception e) {
            a.a(f8559a, "onActivityResult exception : " + e.getMessage(), true);
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            finish();
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.ListActivity, android.app.Activity
    protected void onDestroy() {
        try {
            super.onDestroy();
        } catch (Exception e) {
            a.a(f8559a, "onDestroy exception : " + e.getMessage(), true);
        }
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        if (IntentUtils.hasIntentBomb(intent)) {
            a.a(f8559a, "onNewIntent : hasIntentBomb");
        }
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8559a, "onRestart : hasIntentBomb");
        }
        super.onRestart();
    }

    @Override // android.app.Activity
    protected void onResume() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8559a, "onResume : hasIntentBomb");
        }
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8559a, "onStart : hasIntentBomb");
        }
        super.onStart();
    }

    @Override // android.preference.PreferenceActivity, android.app.Activity
    protected void onStop() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8559a, "onStop : hasIntentBomb");
        }
        super.onStop();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr) {
        try {
            super.startActivities(intentArr);
        } catch (Exception e) {
            a.a(f8559a, "startActivities: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            super.startActivity(new SafeIntent(intent));
        } catch (Exception unused) {
            a.a(f8559a, "startActivity Exception ");
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        try {
            super.startActivityForResult(new SafeIntent(intent), i);
        } catch (Exception e) {
            a.a(f8559a, "startActivity: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i) {
        try {
            return super.startActivityIfNeeded(intent, i);
        } catch (Exception e) {
            a.a(f8559a, "startActivityIfNeeded: " + e.getMessage(), true);
            return false;
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr, Bundle bundle) {
        try {
            super.startActivities(intentArr, bundle);
        } catch (Exception e) {
            a.a(f8559a, "startActivities: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        try {
            super.startActivity(new SafeIntent(intent), bundle);
        } catch (Exception e) {
            a.a(f8559a, "startActivity: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        try {
            super.startActivityForResult(new SafeIntent(intent), i, bundle);
        } catch (Exception e) {
            a.a(f8559a, "startActivity: " + e.getMessage(), true);
        }
    }
}
