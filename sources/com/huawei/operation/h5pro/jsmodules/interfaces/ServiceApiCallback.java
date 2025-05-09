package com.huawei.operation.h5pro.jsmodules.interfaces;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface ServiceApiCallback<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
