package androidx.core.app;

import android.app.ActivityManager;

/* loaded from: classes8.dex */
public final class ActivityManagerCompat {
    private ActivityManagerCompat() {
    }

    public static boolean isLowRamDevice(ActivityManager activityManager) {
        return activityManager.isLowRamDevice();
    }
}
