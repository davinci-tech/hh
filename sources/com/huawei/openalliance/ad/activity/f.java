package com.huawei.openalliance.ad.activity;

import android.app.Activity;
import android.app.assist.AssistContent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;

/* loaded from: classes5.dex */
public abstract class f extends Activity {
    @Override // android.app.Activity
    public boolean startNextMatchingActivity(Intent intent, Bundle bundle) {
        try {
            return super.startNextMatchingActivity(intent, bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startNextMatchingActivity with bundle ex: %s.", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // android.app.Activity
    public boolean startNextMatchingActivity(Intent intent) {
        try {
            return super.startNextMatchingActivity(intent);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startNextMatchingActivity ex: %s.", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i, Bundle bundle) {
        try {
            return super.startActivityIfNeeded(intent, i, bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivityIfNeeded with bundle ex: %s.", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // android.app.Activity
    public boolean startActivityIfNeeded(Intent intent, int i) {
        try {
            return super.startActivityIfNeeded(intent, i);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivityIfNeeded ex: %s.", th.getClass().getSimpleName());
            return false;
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        try {
            intent.setClipData(Constants.CLIP_DATA);
            super.startActivityForResult(new SafeIntent(intent), i, bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivityForResult with bundle ex: %s. errMsg %s", th.getClass().getSimpleName(), th.getMessage());
        }
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        try {
            intent.setClipData(Constants.CLIP_DATA);
            super.startActivityForResult(new SafeIntent(intent), i);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivityForResult ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent, Bundle bundle) {
        try {
            intent.setClipData(Constants.CLIP_DATA);
            super.startActivity(new SafeIntent(intent), bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivity with bundle ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivity(Intent intent) {
        try {
            intent.setClipData(Constants.CLIP_DATA);
            super.startActivity(new SafeIntent(intent));
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivity ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr, Bundle bundle) {
        try {
            super.startActivities(intentArr, bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivities with bundle ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity, android.content.ContextWrapper, android.content.Context
    public void startActivities(Intent[] intentArr) {
        try {
            super.startActivities(intentArr);
        } catch (Throwable th) {
            ho.c("SafeActivity", "startActivities ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public boolean showAssist(Bundle bundle) {
        try {
            return super.showAssist(bundle);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "showAssist Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity
    public void setIntent(Intent intent) {
        try {
            super.setIntent(new SafeIntent(intent));
        } catch (Throwable th) {
            ho.c("SafeActivity", "setIntent ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public void recreate() {
        try {
            super.recreate();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "recreate ex.");
        }
    }

    @Override // android.app.Activity
    protected void onUserLeaveHint() {
        try {
            super.onUserLeaveHint();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onUserLeaveHint Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public boolean onTouchEvent(MotionEvent motionEvent) {
        try {
            return super.onTouchEvent(motionEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyShortcut Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        try {
            super.onStop();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onStop Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        try {
            super.onStateNotSaved();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onStateNotSaved Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        try {
            super.onStart();
        } catch (Throwable th) {
            ho.c("SafeActivity", "onStart Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        try {
            super.onSaveInstanceState(bundle, persistableBundle);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onSaveInstanceState Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        try {
            super.onSaveInstanceState(bundle);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onSaveInstanceState Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        try {
            super.onResume();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onResume Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        try {
            super.onRestoreInstanceState(bundle, persistableBundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onRestoreInstanceState Throwable ex: %s", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(Bundle bundle) {
        try {
            super.onRestoreInstanceState(bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onRestoreInstanceState Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        try {
            super.onRestart();
        } catch (Throwable th) {
            ho.c("SafeActivity", "onRestart Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public Uri onProvideReferrer() {
        try {
            return super.onProvideReferrer();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onProvideReferrer ex.");
            return null;
        }
    }

    @Override // android.app.Activity
    public void onProvideAssistData(Bundle bundle) {
        try {
            super.onProvideAssistData(bundle);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onProvideAssistData Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onProvideAssistContent(AssistContent assistContent) {
        try {
            super.onProvideAssistContent(assistContent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onProvideAssistContent Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    protected void onPostResume() {
        try {
            super.onPostResume();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onPostResume Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle, PersistableBundle persistableBundle) {
        try {
            super.onPostCreate(bundle, persistableBundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onPostCreate Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    protected void onPostCreate(Bundle bundle) {
        try {
            super.onPostCreate(bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onPostCreate Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public void onPause() {
        try {
            super.onPause();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onPause Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        try {
            super.onNewIntent(intent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onNewIntent Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        try {
            super.onMultiWindowModeChanged(z, configuration);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onMultiWindowModeChanged Throwable  ex.");
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        try {
            return super.onKeyUp(i, keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyUp Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity
    public boolean onKeyShortcut(int i, KeyEvent keyEvent) {
        try {
            return super.onKeyShortcut(i, keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyShortcut Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        try {
            return super.onKeyMultiple(i, i2, keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyMultiple Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        try {
            return super.onKeyLongPress(i, keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyLongPress Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        try {
            return super.onKeyDown(i, keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onKeyDown Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        try {
            super.onDestroy();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onDestroy Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public CharSequence onCreateDescription() {
        try {
            return super.onCreateDescription();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onCreateDescription Throwable  ex.");
            return null;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle, PersistableBundle persistableBundle) {
        try {
            super.onCreate(bundle, persistableBundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onCreate Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            super.onCreate(bundle);
        } catch (Throwable th) {
            ho.c("SafeActivity", "onCreate Throwable ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        try {
            super.onConfigurationChanged(configuration);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onConfigurationChanged Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        try {
            super.onBackPressed();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onBackPressed Throwable  ex.");
        }
    }

    @Override // android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        try {
            super.onActivityResult(i, i2, new SafeIntent(intent));
        } catch (Throwable unused) {
            ho.c("SafeActivity", "onActivityResult Throwable  ex.");
        }
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        try {
            return super.getSystemService(str);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "finishAffinity ex.");
            return null;
        }
    }

    @Override // android.app.Activity
    public Uri getReferrer() {
        try {
            return super.getReferrer();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "getReferrer ex.");
            return null;
        }
    }

    @Override // android.app.Activity
    public Intent getIntent() {
        try {
            return new SafeIntent(super.getIntent());
        } catch (Throwable th) {
            ho.c("SafeActivity", "getIntent ex: %s.", th.getClass().getSimpleName());
            return new SafeIntent(new Intent());
        }
    }

    @Override // android.app.Activity
    public String getCallingPackage() {
        try {
            return super.getCallingPackage();
        } catch (Throwable th) {
            ho.c("SafeActivity", "getCallingPackage Throwable ex: %s.", th.getClass().getSimpleName());
            return null;
        }
    }

    @Override // android.app.Activity
    public ComponentName getCallingActivity() {
        try {
            return super.getCallingActivity();
        } catch (Throwable th) {
            ho.c("SafeActivity", "getCallingActivity ex: %s.", th.getClass().getSimpleName());
            return null;
        }
    }

    @Override // android.app.Activity
    public void finishAffinity() {
        try {
            super.finishAffinity();
        } catch (Throwable th) {
            ho.c("SafeActivity", "finishAffinity ex: %s.", th.getClass().getSimpleName());
        }
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Throwable unused) {
            ho.c("SafeActivity", "finish Throwable  ex.", true);
        }
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        try {
            return (T) getWindow().findViewById(i);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "findViewById Throwable  ex.");
            return null;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        try {
            return super.dispatchTouchEvent(motionEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "dispatchTouchEvent Throwable  ex.");
            return false;
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        try {
            return super.dispatchKeyEvent(keyEvent);
        } catch (Throwable unused) {
            ho.c("SafeActivity", "dispatchKeyEvent Throwable  ex.");
            return false;
        }
    }
}
