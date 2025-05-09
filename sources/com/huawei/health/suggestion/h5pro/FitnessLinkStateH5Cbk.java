package com.huawei.health.suggestion.h5pro;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface FitnessLinkStateH5Cbk {
    void onFailure(int i, String str);

    void onSuccess(Object obj);
}
