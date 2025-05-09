package com.huawei.hmf.orb;

import com.huawei.hmf.orb.exception.ConnectRemoteException;

/* loaded from: classes8.dex */
public interface ConnectionCallbacks {
    void onConnected();

    void onConnectionFailed(ConnectRemoteException connectRemoteException);

    void onDisconnected();
}
