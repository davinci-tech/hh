package com.huawei.watchface.api;

/* loaded from: classes7.dex */
public interface ResponseListener<T> {
    void onError();

    void onResponse(T t);
}
