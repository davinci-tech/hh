package com.huawei.health.userprofilemgr.model;

/* loaded from: classes4.dex */
public interface RouteResultCallBack<T> {
    void onFailure(Throwable th);

    void onSuccess(T t);
}
