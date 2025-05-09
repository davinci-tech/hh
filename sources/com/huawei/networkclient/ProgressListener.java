package com.huawei.networkclient;

/* loaded from: classes5.dex */
public interface ProgressListener<T> {
    void onFail(Throwable th);

    void onFinish(T t);

    void onProgress(long j, long j2, boolean z);
}
