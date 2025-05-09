package com.huawei.secure.android.common.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.huawei.secure.android.common.intent.IntentUtils;
import com.huawei.secure.android.common.intent.SafeIntent;

/* loaded from: classes6.dex */
public class SafeFragmentActivity extends FragmentActivity {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8558a = "SafeFragmentActivity";

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception e) {
            a.a(f8558a, "finish exception : " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        try {
            super.finishAffinity();
        } catch (Exception e) {
            a.a(f8558a, "finishAffinity: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        try {
            return new SafeIntent(super.getIntent());
        } catch (Exception e) {
            a.a(f8558a, "getIntent: " + e.getMessage(), true);
            return new SafeIntent(new Intent());
        }
    }

    @Override // android.app.Activity
    public Uri getReferrer() {
        try {
            return super.getReferrer();
        } catch (Exception e) {
            a.a(f8558a, "getReferrer: " + e.getMessage(), true);
            return null;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, new SafeIntent(intent));
        } catch (Exception e) {
            a.a(f8558a, "onActivityResult exception : " + e.getMessage(), true);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Exception e) {
            a.a(f8558a, "onDestroy exception : " + e.getMessage(), true);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        if (IntentUtils.hasIntentBomb(intent)) {
            a.a(f8558a, "onNewIntent : hasIntentBomb");
        }
        super.onNewIntent(intent);
    }

    @Override // android.app.Activity
    protected void onRestart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8558a, "onRestart : hasIntentBomb");
        }
        super.onRestart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8558a, "onResume : hasIntentBomb");
        }
        super.onResume();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8558a, "onStart : hasIntentBomb");
        }
        super.onStart();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        if (IntentUtils.hasIntentBomb(super.getIntent())) {
            a.a(f8558a, "onStop : hasIntentBomb");
        }
        super.onStop();
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr) {
        try {
            super.startActivities(intentArr);
        } catch (Exception e) {
            a.a(f8558a, "startActivities: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            super.startActivity(new SafeIntent(intent));
        } catch (Exception unused) {
            a.a(f8558a, "startActivity Exception ");
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        try {
            super.startActivityForResult(new SafeIntent(intent), i);
        } catch (Exception e) {
            a.a(f8558a, "startActivity: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i) {
        try {
            return super.startActivityIfNeeded(intent, i);
        } catch (Exception e) {
            a.a(f8558a, "startActivityIfNeeded: " + e.getMessage(), true);
            return false;
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr, Bundle bundle) {
        try {
            super.startActivities(intentArr, bundle);
        } catch (Exception e) {
            a.a(f8558a, "startActivities: " + e.getMessage(), true);
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        try {
            super.startActivity(new SafeIntent(intent), bundle);
        } catch (Exception e) {
            a.a(f8558a, "startActivity: " + e.getMessage(), true);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        try {
            super.startActivityForResult(new SafeIntent(intent), i, bundle);
        } catch (Exception e) {
            a.a(f8558a, "startActivity: " + e.getMessage(), true);
        }
    }
}
