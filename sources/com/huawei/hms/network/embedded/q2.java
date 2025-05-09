package com.huawei.hms.network.embedded;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import com.huawei.hms.framework.common.ContextHolder;

/* loaded from: classes9.dex */
public class q2 {
    public static final String b = "activity";

    /* renamed from: a, reason: collision with root package name */
    public Application.ActivityLifecycleCallbacks f5428a;

    public class a implements Application.ActivityLifecycleCallbacks {
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

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            a3.reportSysEvent(Long.valueOf(System.currentTimeMillis()), "activity", activity.getComponentName().getClassName() + "_onDestroyed");
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            a3.reportSysEvent(Long.valueOf(System.currentTimeMillis()), "activity", activity.getComponentName().getClassName() + "_onCreate");
        }

        public a() {
        }
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        public static q2 f5430a = new q2(null);
    }

    public void init() {
        Context appContext = ContextHolder.getAppContext();
        if (appContext instanceof Application) {
            a aVar = new a();
            this.f5428a = aVar;
            ((Application) appContext).registerActivityLifecycleCallbacks(aVar);
        }
    }

    public static q2 getInstance() {
        return b.f5430a;
    }

    public /* synthetic */ q2(a aVar) {
        this();
    }

    public q2() {
    }
}
