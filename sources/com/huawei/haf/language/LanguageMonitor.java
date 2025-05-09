package com.huawei.haf.language;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.common.security.SecurityConstant;
import health.compact.a.ProcessUtil;

/* loaded from: classes.dex */
final class LanguageMonitor extends BroadcastReceiver implements ComponentCallbacks, Application.ActivityLifecycleCallbacks {
    private boolean b = true;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    LanguageMonitor(boolean z) {
        Application vZ_ = BaseApplication.vZ_();
        vZ_.registerComponentCallbacks(this);
        vZ_.registerActivityLifecycleCallbacks(this);
        if (z) {
            return;
        }
        BroadcastManager.wt_(this);
    }

    void e() {
        this.b = false;
    }

    static void b() {
        Intent intent = new Intent("com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY");
        intent.setPackage(BaseApplication.d());
        intent.putExtra("sendProcess", ProcessUtil.b());
        BaseApplication.e().sendBroadcast(intent, SecurityConstant.b);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("com.huawei.haf.intent.action.UPDATE_LANGUAGE_READY".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("sendProcess");
            if (TextUtils.isEmpty(stringExtra) || ProcessUtil.b().equals(stringExtra)) {
                return;
            }
            LanguageManager.c(context);
        }
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        if (this.b) {
            LanguageManager.yM_(configuration, false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        LanguageManager.c(activity);
    }
}
