package com.huawei.pluginappgallery;

import com.huawei.hwbasemgr.IBaseResponseCallback;

/* loaded from: classes6.dex */
public interface AppGalleryApi {
    void destroyAppMarketAdapter();

    void hasNewVersion();

    void initAppMarketAdapter();

    void launchAppGallery(int i);

    void sendCommand(int i, IBaseResponseCallback iBaseResponseCallback);
}
