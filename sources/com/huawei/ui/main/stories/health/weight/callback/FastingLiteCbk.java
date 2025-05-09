package com.huawei.ui.main.stories.health.weight.callback;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface FastingLiteCbk<T> {
    void onFailure(int i, String str);

    void onSuccess(T t);
}
