package com.huawei.hms.maps.model;

import android.os.RemoteException;

/* loaded from: classes4.dex */
public final class RuntimeRemoteException extends RuntimeException {
    public RuntimeRemoteException(String str) {
        super(str);
    }

    public RuntimeRemoteException(RemoteException remoteException) {
        super(remoteException);
    }
}
