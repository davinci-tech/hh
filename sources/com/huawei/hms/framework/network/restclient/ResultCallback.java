package com.huawei.hms.framework.network.restclient;

@Deprecated
/* loaded from: classes4.dex */
public interface ResultCallback<T> {
    void onFailure(Throwable th);

    void onResponse(Response<T> response);
}
