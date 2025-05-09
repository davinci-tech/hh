package com.huawei.health.baseapi.pluginaudiodevice;

/* loaded from: classes8.dex */
public interface ResultCallback<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
