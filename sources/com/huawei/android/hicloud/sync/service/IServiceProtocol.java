package com.huawei.android.hicloud.sync.service;

import android.os.RemoteException;

/* loaded from: classes8.dex */
public interface IServiceProtocol {
    void execute() throws RemoteException;

    void executeBatches(byte[] bArr) throws RemoteException;

    void handleTranDataTooLarge();
}
