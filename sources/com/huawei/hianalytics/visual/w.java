package com.huawei.hianalytics.visual;

import android.app.Activity;
import android.os.Bundle;

/* loaded from: classes4.dex */
public interface w {
    default void onActivityCreated(Activity activity, Bundle bundle) {
    }

    default void onActivityDestroyed(Activity activity) {
    }

    default void onActivityPaused(Activity activity) {
    }

    default void onActivityPostCreated(Activity activity, Bundle bundle) {
    }

    default void onActivityResumed(Activity activity) {
    }

    default void onActivityStarted(Activity activity) {
    }

    default void onActivityStopped(Activity activity) {
    }
}
