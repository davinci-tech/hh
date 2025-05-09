package com.huawei.hmf.taskstream.impl;

import com.huawei.hmf.taskstream.ExecuteResult;
import com.huawei.hmf.taskstream.Observer;
import java.lang.Exception;

/* loaded from: classes9.dex */
public class ExceptionExecuteResult<TResult extends Exception> implements ExecuteResult {
    private Exception result;

    ExceptionExecuteResult(Exception exc) {
        this.result = exc;
    }

    @Override // com.huawei.hmf.taskstream.ExecuteResult
    public final void onComplete(Observer observer) {
        if (observer != null) {
            observer.onFailure(this.result);
        }
    }
}
