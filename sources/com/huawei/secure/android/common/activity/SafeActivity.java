package com.huawei.secure.android.common.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.secure.android.common.intent.IntentUtils;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes6.dex */
public class SafeActivity extends Activity {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8556a = "SafeActivity";

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception e) {
            a.a(f8556a, "finish exception : " + e.getMessage());
        }
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        try {
            super.finishAffinity();
        } catch (Exception e) {
            a.a(f8556a, "finishAffinity: " + e.getMessage(), e);
        }
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        try {
            return new SafeIntent(super.getIntent());
        } catch (Exception e) {
            a.a(f8556a, "getIntent: " + e.getMessage());
            return new SafeIntent(new Intent());
        }
    }

    @Override // android.app.Activity
    public Uri getReferrer() {
        try {
            return super.getReferrer();
        } catch (Exception e) {
            a.a(f8556a, "getReferrer: " + e.getMessage());
            return null;
        }
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, new SafeIntent(intent));
        } catch (Exception e) {
            a.a(f8556a, "onActivityResult exception : " + e.getMessage(), e);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            finish();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Exception e) {
            a.a(f8556a, "onDestroy exception : " + e.getMessage());
        }
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        if (IntentUtils.hasIntentBomb(intent)) {
            a.a(f8556a, "onNewIntent : hasIntentBomb");
        }
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8556a, "onRestart : hasIntentBomb");
        }
        super.onRestart();
    }

    @Override // android.app.Activity
    public void onResume() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8556a, "onResume : hasIntentBomb");
        }
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8556a, "onStart : hasIntentBomb");
        }
        super.onStart();
    }

    @Override // android.app.Activity
    protected void onStop() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8556a, "onStop : hasIntentBomb");
        }
        super.onStop();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr) {
        try {
            super.startActivities(intentArr);
        } catch (Exception e) {
            a.a(f8556a, "startActivities: " + e.getMessage());
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            super.startActivity(intent);
        } catch (Exception e) {
            a.a(f8556a, "startActivity Exception : " + e.getMessage());
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        try {
            super.startActivityForResult(intent, i);
        } catch (Exception e) {
            a.a(f8556a, "startActivity: " + e.getMessage(), e);
        }
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i) {
        try {
            return super.startActivityIfNeeded(intent, i);
        } catch (Exception e) {
            a.a(f8556a, "startActivityIfNeeded: " + e.getMessage(), e);
            return false;
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr, Bundle bundle) {
        try {
            super.startActivities(intentArr, bundle);
        } catch (Exception e) {
            a.a(f8556a, "startActivities: " + e.getMessage(), e);
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        try {
            super.startActivity(intent, bundle);
        } catch (Exception e) {
            a.a(f8556a, "startActivity: " + e.getMessage(), e);
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        try {
            super.startActivityForResult(intent, i, bundle);
        } catch (Exception e) {
            a.a(f8556a, "startActivity: " + e.getMessage(), e);
        }
    }
}
