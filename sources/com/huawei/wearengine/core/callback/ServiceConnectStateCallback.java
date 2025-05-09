package com.huawei.wearengine.core.callback;

import android.content.ComponentName;
import android.os.IBinder;

/* loaded from: classes9.dex */
public interface ServiceConnectStateCallback {
    void onConnectChanged(ComponentName componentName, IBinder iBinder, boolean z);
}
