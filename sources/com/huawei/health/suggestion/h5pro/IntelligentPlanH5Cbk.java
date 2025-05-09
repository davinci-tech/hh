package com.huawei.health.suggestion.h5pro;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface IntelligentPlanH5Cbk<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
