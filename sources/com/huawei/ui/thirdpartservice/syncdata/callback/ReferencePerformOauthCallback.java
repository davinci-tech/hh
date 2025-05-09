package com.huawei.ui.thirdpartservice.syncdata.callback;

import defpackage.sjr;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public abstract class ReferencePerformOauthCallback<T> implements PerformOauthCallback {
    private static final String TAG = "ReferencePerformOauthCallback";
    private final WeakReference<T> mWeakReference;

    protected abstract void oauthResultWhenReferenceNotNull(T t, boolean z, boolean z2, long j, String str);

    public ReferencePerformOauthCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.callback.PerformOauthCallback
    public final void oauthResult(boolean z, boolean z2, long j, String str) {
        T t = this.mWeakReference.get();
        if (!sjr.c(t)) {
            LogUtil.c(TAG, "connectResult skip the result, isRequestValid = ", Boolean.valueOf(z), " isSuccess = ", Boolean.valueOf(z2), " oauthTime = ", Long.valueOf(j), " errMsg = ", str);
        } else {
            oauthResultWhenReferenceNotNull(t, z, z2, j, str);
        }
    }
}
