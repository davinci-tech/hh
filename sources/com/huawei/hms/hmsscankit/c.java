package com.huawei.hms.hmsscankit;

import android.os.RemoteException;
import android.util.Log;
import com.huawei.hms.hmsscankit.api.IOnErrorCallback;

/* loaded from: classes4.dex */
class c extends IOnErrorCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private final OnErrorCallback f4634a;

    c(OnErrorCallback onErrorCallback) {
        this.f4634a = onErrorCallback;
    }

    @Override // com.huawei.hms.hmsscankit.api.IOnErrorCallback
    public void onError(int i) throws RemoteException {
        if (this.f4634a != null) {
            Log.i("OnErrorCallbackDelegate", "onError: ErrorCode：" + i);
            this.f4634a.onError(i);
        }
    }
}
