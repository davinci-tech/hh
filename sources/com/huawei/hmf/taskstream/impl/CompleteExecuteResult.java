package com.huawei.hmf.taskstream.impl;

import com.huawei.hmf.taskstream.ExecuteResult;
import com.huawei.hmf.taskstream.Observer;

/* loaded from: classes9.dex */
public class CompleteExecuteResult implements ExecuteResult {
    CompleteExecuteResult() {
    }

    @Override // com.huawei.hmf.taskstream.ExecuteResult
    public final void onComplete(Observer observer) {
        if (observer != null) {
            observer.onComplete();
        }
    }
}
