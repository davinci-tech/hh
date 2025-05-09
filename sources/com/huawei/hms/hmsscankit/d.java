package com.huawei.hms.hmsscankit;

import android.os.RemoteException;
import com.huawei.hms.hmsscankit.api.IOnLightCallback;

/* loaded from: classes9.dex */
class d extends IOnLightCallback.Stub {

    /* renamed from: a, reason: collision with root package name */
    private final OnLightVisibleCallBack f4635a;

    d(OnLightVisibleCallBack onLightVisibleCallBack) {
        this.f4635a = onLightVisibleCallBack;
    }

    @Override // com.huawei.hms.hmsscankit.api.IOnLightCallback
    public void onVisibleChanged(boolean z) throws RemoteException {
        this.f4635a.onVisibleChanged(z);
    }
}
