package com.huawei.hihealth.motion.util;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.health.IResultCallback;
import com.huawei.hihealth.motion.IExecuteResult;

/* loaded from: classes.dex */
public class ExecuteResultLocalToRemote extends IResultCallback.Stub {
    private IExecuteResult e;

    public ExecuteResultLocalToRemote(IExecuteResult iExecuteResult) {
        this.e = iExecuteResult;
    }

    @Override // com.huawei.health.IResultCallback
    public void onSuccess(Bundle bundle) throws RemoteException {
        IExecuteResult iExecuteResult = this.e;
        if (iExecuteResult != null) {
            iExecuteResult.onSuccess(bundle);
        }
    }

    @Override // com.huawei.health.IResultCallback
    public void onFailed(Bundle bundle) throws RemoteException {
        IExecuteResult iExecuteResult = this.e;
        if (iExecuteResult != null) {
            iExecuteResult.onFailed(bundle);
        }
    }

    @Override // com.huawei.health.IResultCallback
    public void onServiceException(Bundle bundle) throws RemoteException {
        IExecuteResult iExecuteResult = this.e;
        if (iExecuteResult != null) {
            iExecuteResult.onServiceException(bundle);
        }
    }
}
