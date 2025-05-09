package com.huawei.android.appbundle.remote;

import android.os.IBinder;

/* loaded from: classes8.dex */
public interface RemoteCall<T> {
    T asInterface(IBinder iBinder);
}
