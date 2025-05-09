package com.huawei.hmf.taskstream.impl;

import com.huawei.hmf.taskstream.Disposable;
import com.huawei.hmf.taskstream.ExecuteResult;
import com.huawei.hmf.taskstream.Observer;

/* loaded from: classes9.dex */
public class SubscribeExecuteResult implements ExecuteResult {
    private Disposable result;

    SubscribeExecuteResult(Disposable disposable) {
        this.result = disposable;
    }

    @Override // com.huawei.hmf.taskstream.ExecuteResult
    public final void onComplete(Observer observer) {
        if (observer != null) {
            observer.onSubscribe(this.result);
        }
    }
}
