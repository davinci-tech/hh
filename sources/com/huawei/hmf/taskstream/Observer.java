package com.huawei.hmf.taskstream;

/* loaded from: classes9.dex */
public interface Observer<TResult> {
    void onComplete();

    void onFailure(Exception exc);

    void onNext(TResult tresult);

    void onSubscribe(Disposable disposable);
}
