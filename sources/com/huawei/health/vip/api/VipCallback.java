package com.huawei.health.vip.api;

import com.huawei.health.h5pro.service.anotation.H5ProCallback;

@H5ProCallback
/* loaded from: classes.dex */
public interface VipCallback {
    void onFailure(int i, String str);

    void onSuccess(Object obj);
}
