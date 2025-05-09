package com.huawei.haf.bundle;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.res.Resources;

/* loaded from: classes.dex */
public interface AppBundleResources {
    void loadLibrary(Context context, String str);

    void loadResources(Activity activity, Resources resources);

    void loadResources(Service service);

    void loadResources(BroadcastReceiver broadcastReceiver, Context context);

    void loadResources(Context context);
}
