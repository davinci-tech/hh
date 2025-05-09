package com.huawei.watchface;

import android.content.Context;

/* loaded from: classes7.dex */
public class cs {
    public static Context a(Context context) {
        return context.isDeviceProtectedStorage() ? context : context.createDeviceProtectedStorageContext();
    }
}
