package com.huawei.hms.support.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.operation.utils.Constants;
import java.lang.ref.WeakReference;

/* loaded from: classes4.dex */
public final class ActivityMgr implements Application.ActivityLifecycleCallbacks {
    public static final ActivityMgr INST = new ActivityMgr();

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Activity> f5983a;

    private ActivityMgr() {
    }

    private static String a(Object obj) {
        if (obj == null) {
            return Constants.NULL;
        }
        return obj.getClass().getName() + '@' + Integer.toHexString(obj.hashCode());
    }

    public Activity getCurrentActivity() {
        if (this.f5983a == null) {
            HMSLog.i("ActivityMgr", "mCurrentActivity is " + this.f5983a);
            return null;
        }
        HMSLog.i("ActivityMgr", "mCurrentActivity.get() is " + this.f5983a.get());
        return this.f5983a.get();
    }

    public void init(Application application) {
        HMSLog.d("ActivityMgr", "init");
        if (application == null) {
            HMSLog.w("ActivityMgr", "init failed for app is null");
            return;
        }
        ActivityMgr activityMgr = INST;
        application.unregisterActivityLifecycleCallbacks(activityMgr);
        application.registerActivityLifecycleCallbacks(activityMgr);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        HMSLog.d("ActivityMgr", "onCreated:" + a(activity));
        this.f5983a = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        HMSLog.d("ActivityMgr", "onResumed:" + a(activity));
        this.f5983a = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        HMSLog.d("ActivityMgr", "onStarted:" + a(activity));
        this.f5983a = new WeakReference<>(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
