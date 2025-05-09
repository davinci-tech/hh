package com.huawei.health.userprofilemgr.model;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface HealthRecordCbk<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
