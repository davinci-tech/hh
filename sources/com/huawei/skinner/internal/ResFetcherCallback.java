package com.huawei.skinner.internal;

/* loaded from: classes9.dex */
public interface ResFetcherCallback<Q, T> {
    void onFailed(Q q, T t);

    void onStart(Q q);

    void onSuccess(T t);
}
