package com.huawei.hmf.orb.aidl;

import com.huawei.hmf.orb.ConnectionCallbacks;
import com.huawei.hmf.orb.exception.ConnectRemoteException;

/* loaded from: classes8.dex */
class ConnectionCallbacksForConnect implements ConnectionCallbacks {
    private final ConnectionCallbacks mCallbacks;

    ConnectionCallbacksForConnect(ConnectionCallbacks connectionCallbacks) {
        this.mCallbacks = connectionCallbacks;
    }

    @Override // com.huawei.hmf.orb.ConnectionCallbacks
    public void onConnected() {
        this.mCallbacks.onConnected();
    }

    @Override // com.huawei.hmf.orb.ConnectionCallbacks
    public void onDisconnected() {
        this.mCallbacks.onDisconnected();
    }

    @Override // com.huawei.hmf.orb.ConnectionCallbacks
    public void onConnectionFailed(ConnectRemoteException connectRemoteException) {
        this.mCallbacks.onConnectionFailed(connectRemoteException);
    }
}
