package com.huawei.ui.thirdpartservice.syncdata.callback;

import defpackage.sjr;
import health.compact.a.LogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public abstract class ReferenceRevokeOauthCallback<T> implements RevokeOauthCallback {
    private static final String TAG = "ReferenceRevokeOauthCallback";
    private final WeakReference<T> mWeakReference;

    protected abstract void revokeResultWhenReferenceNotNull(T t, boolean z, boolean z2, String str);

    public ReferenceRevokeOauthCallback(T t) {
        this.mWeakReference = new WeakReference<>(t);
    }

    @Override // com.huawei.ui.thirdpartservice.syncdata.callback.RevokeOauthCallback
    public final void revokeResult(boolean z, boolean z2, String str) {
        T t = this.mWeakReference.get();
        if (!sjr.c(t)) {
            LogUtil.c(TAG, "connectResult skip the result, isRequestValid = ", Boolean.valueOf(z), " isSuccess = ", Boolean.valueOf(z2), " errMsg = ", str);
        } else {
            revokeResultWhenReferenceNotNull(t, z, z2, str);
        }
    }
}
