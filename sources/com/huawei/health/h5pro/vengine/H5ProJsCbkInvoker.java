package com.huawei.health.h5pro.vengine;

/* loaded from: classes3.dex */
public interface H5ProJsCbkInvoker<T> {
    void invokeJsFunc(String str, Object... objArr);

    void onComplete(int i, String str, long j);

    void onFailure(int i, String str, long j);

    void onSuccess(T t, long j);
}
