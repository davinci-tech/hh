package com.huawei.networkclient;

/* loaded from: classes5.dex */
public interface ResultCallback<T> {
    void onFailure(Throwable th);

    void onSuccess(T t);
}
