package com.huawei.hms.ml.common.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public final class ActivityMgr implements Application.ActivityLifecycleCallbacks {
    public static final ActivityMgr INST = new ActivityMgr();
    private WeakReference<Activity> activity;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    private ActivityMgr() {
    }

    public void init(Application application) {
        if (application == null) {
            SmartLog.w("ActivityMgr", "init failed for app is null");
            return;
        }
        ActivityMgr activityMgr = INST;
        application.unregisterActivityLifecycleCallbacks(activityMgr);
        application.registerActivityLifecycleCallbacks(activityMgr);
    }

    public Activity getCurrentActivity() {
        WeakReference<Activity> weakReference = this.activity;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        SmartLog.d("ActivityMgr", "onCreated:" + toString(activity));
        this.activity = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        SmartLog.d("ActivityMgr", "onStarted:" + toString(activity));
        this.activity = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        SmartLog.d("ActivityMgr", "onResumed:" + toString(activity));
        this.activity = new WeakReference<>(activity);
    }

    private static String toString(Object obj) {
        if (obj == null) {
            return Constants.NULL;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(obj.hashCode());
    }
}
