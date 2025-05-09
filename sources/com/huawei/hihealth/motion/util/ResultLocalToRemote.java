package com.huawei.hihealth.motion.util;

import android.os.Bundle;
import android.os.RemoteException;
import com.huawei.health.IResultCallback;
import com.huawei.hihealth.motion.IFlushResult;

/* loaded from: classes.dex */
public class ResultLocalToRemote extends IResultCallback.Stub {
    private IFlushResult e;

    public ResultLocalToRemote(IFlushResult iFlushResult) {
        this.e = iFlushResult;
    }

    @Override // com.huawei.health.IResultCallback
    public void onSuccess(Bundle bundle) throws RemoteException {
        IFlushResult iFlushResult = this.e;
        if (iFlushResult != null) {
            iFlushResult.onSuccess(bundle);
        }
    }

    @Override // com.huawei.health.IResultCallback
    public void onFailed(Bundle bundle) throws RemoteException {
        IFlushResult iFlushResult = this.e;
        if (iFlushResult != null) {
            iFlushResult.onFailed(bundle);
        }
    }

    @Override // com.huawei.health.IResultCallback
    public void onServiceException(Bundle bundle) throws RemoteException {
        IFlushResult iFlushResult = this.e;
        if (iFlushResult != null) {
            iFlushResult.onServiceException(bundle);
        }
    }
}
