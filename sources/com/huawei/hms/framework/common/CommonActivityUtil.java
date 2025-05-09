package com.huawei.hms.framework.common;

import android.app.Activity;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class CommonActivityUtil {
    private static final int MAX_NUM = 20;
    private static final String TAG = "CommonActivityUtil";
    private static volatile CommonActivityUtil instance;
    private boolean isForeground;
    private List<OnAppStatusListener> onAppStatusListeners = new ArrayList();
    private boolean isRegistered = false;
    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() { // from class: com.huawei.hms.framework.common.CommonActivityUtil.1
        private int activityStartCount = 0;

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

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
            Logger.i(CommonActivityUtil.TAG, "onActivityStarted");
            int i = this.activityStartCount + 1;
            this.activityStartCount = i;
            if (i == 1) {
                CommonActivityUtil.this.isForeground = true;
                Logger.d(CommonActivityUtil.TAG, "onActivityStarted");
                for (int i2 = 0; i2 < CommonActivityUtil.this.onAppStatusListeners.size(); i2++) {
                    ((OnAppStatusListener) CommonActivityUtil.this.onAppStatusListeners.get(i2)).onFront();
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            this.activityStartCount--;
            Logger.i(CommonActivityUtil.TAG, "onActivityStopped");
            if (this.activityStartCount == 0) {
                Logger.d(CommonActivityUtil.TAG, "onActivityStopped");
                CommonActivityUtil.this.isForeground = false;
                for (int i = 0; i < CommonActivityUtil.this.onAppStatusListeners.size(); i++) {
                    ((OnAppStatusListener) CommonActivityUtil.this.onAppStatusListeners.get(i)).onBack();
                }
            }
        }
    };

    public interface OnAppStatusListener {
        void onBack();

        void onFront();
    }

    private CommonActivityUtil() {
    }

    public static CommonActivityUtil getInstance() {
        if (instance == null) {
            synchronized (CommonActivityUtil.class) {
                if (instance == null) {
                    Logger.i(TAG, "new CommonActivityUtil");
                    instance = new CommonActivityUtil();
                }
            }
        }
        return instance;
    }

    @Deprecated
    public static boolean isForeground(Context context) {
        return getInstance().isForeground();
    }

    public boolean isForeground() {
        return this.isForeground;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public static PendingIntent getActivities(Context context, int i, Intent[] intentArr, int i2) {
        if (context == null) {
            Logger.w(TAG, "context is null");
            return null;
        }
        try {
            return PendingIntent.getActivities(context, i, intentArr, i2);
        } catch (RuntimeException e) {
            Logger.e(TAG, "dealType rethrowFromSystemServer:", e);
            return null;
        }
    }

    public void register(Context context) {
        if (context == null) {
            Logger.i(TAG, "appContext is null");
        }
        if (context instanceof Application) {
            ((Application) context).registerActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
            this.isRegistered = true;
        } else {
            Logger.w(TAG, "context is not application, register background fail");
        }
    }

    public void unRegister() {
        Context appContext = ContextHolder.getAppContext();
        if (appContext instanceof Application) {
            ((Application) appContext).unregisterActivityLifecycleCallbacks(this.activityLifecycleCallbacks);
        } else {
            Logger.w(TAG, "context is not application, unRegister background fail");
        }
    }

    public void setOnAppStatusListener(OnAppStatusListener onAppStatusListener) {
        if (onAppStatusListener == null) {
            Logger.w(TAG, "onAppStatusListener is null");
        } else if (this.onAppStatusListeners.size() >= 20) {
            Logger.w(TAG, "onAppStatusListener of count is max");
        } else {
            this.onAppStatusListeners.add(onAppStatusListener);
        }
    }
}
