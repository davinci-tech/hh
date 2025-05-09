package com.huawei.phoneservice.faq.base.network;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: classes9.dex */
public class ActivityLifecycle {
    private static Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new d();
    private static CallBack callBack;

    public interface CallBack {
        void onActivityCreated(Activity activity, Bundle bundle);

        void onActivityDestroyed(Activity activity);

        void onActivityPaused(Activity activity);

        void onActivityResumed(Activity activity);

        void onActivitySaveInstanceState(Activity activity, Bundle bundle);

        void onActivityStarted(Activity activity);

        void onActivityStopped(Activity activity);
    }

    class d implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityStopped(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityStarted(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivitySaveInstanceState(activity, bundle);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityResumed(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityPaused(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
            b.ccX_(activity);
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityDestroyed(activity);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (ActivityLifecycle.callBack != null) {
                ActivityLifecycle.callBack.onActivityCreated(activity, bundle);
            }
        }

        d() {
        }
    }

    public static void registerActivityLifecycleCallbacks(Application application, CallBack callBack2) {
        callBack = callBack2;
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public static void registerActivityLifecycleCallbacks(Application application) {
        registerActivityLifecycleCallbacks(application, null);
    }
}
