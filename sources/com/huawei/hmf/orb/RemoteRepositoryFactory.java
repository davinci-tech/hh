package com.huawei.hmf.orb;

import com.huawei.hmf.orb.exception.ConnectRemoteException;

/* loaded from: classes8.dex */
public interface RemoteRepositoryFactory {
    RemoteRepository create(RemoteConnector remoteConnector) throws ConnectRemoteException;
}
