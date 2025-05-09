package com.huawei.healthcloud.plugintrack.physicalfitness.h5;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface PhysicalFitnessH5Callback<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
