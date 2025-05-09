package com.huawei.hwdevice.api;

import com.huawei.framework.servicemgr.Consumer;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwdevice.mainprocess.mgr.hwmywatchfacemgr.type.h5pro.CustomConfig;

/* loaded from: classes5.dex */
public interface CustomConfigWatchFaceApi {
    void applyCustomConfig(String str, CustomConfig customConfig, IBaseResponseCallback iBaseResponseCallback);

    void destroyWatchFace();

    void queryCustomConfig(String str, String str2);

    void registerCustomConfigListener(String str, Consumer<CustomConfig> consumer);
}
