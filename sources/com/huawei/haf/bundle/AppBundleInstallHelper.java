package com.huawei.haf.bundle;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.Resources;

/* loaded from: classes.dex */
public final class AppBundleInstallHelper {
    private AppBundleInstallHelper() {
    }

    public static void loadResources(Activity activity, Resources resources) {
        AppBundle.b().loadResources(activity, resources);
    }

    public static void loadResources(Service service) {
        AppBundle.b().loadResources(service);
    }

    public static void loadResources(BroadcastReceiver broadcastReceiver, Context context) {
        AppBundle.b().loadResources(broadcastReceiver, context);
    }

    public static void loadLibrary(Context context, String str) {
        AppBundle.b().loadLibrary(context, str);
    }
}
