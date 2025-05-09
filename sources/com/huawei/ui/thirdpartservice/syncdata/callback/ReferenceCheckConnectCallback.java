package com.huawei.ui.thirdpartservice.syncdata.callback;

import defpackage.sjr;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public abstract class ReferenceCheckConnectCallback<T> implements RefreshTokenCallback {
    private static final String TAG = "ReferenceCheckConnectCallback";
    private final WeakReference<T> mWeakReference;

    protected abstract void connectResultWhenReferenceNotNull(T t, boolean z, boolean z2);

    public ReferenceCheckConnectCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RefreshTokenCallback
    public final void refreshResult(boolean z, boolean z2) {
        T t = this.mWeakReference.get();
        if (!sjr.c(t)) {
            LogUtil.c(TAG, "connectResult skip the result, isRequestValid = ", Boolean.valueOf(z), " isConnected = ", Boolean.valueOf(z2));
        } else {
            connectResultWhenReferenceNotNull(t, z, z2);
        }
    }
}
