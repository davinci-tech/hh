package com.huawei.pluginfitnessadvice.h5pro;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface SeriesCourseH5Cbk<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
