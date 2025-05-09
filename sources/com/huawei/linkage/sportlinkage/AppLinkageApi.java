package com.huawei.linkage.sportlinkage;

/* loaded from: classes5.dex */
public interface AppLinkageApi extends LinkageApi {
    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void pause();

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void resume();

    default void resumeLinkage(int i, int i2) {
    }

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void start(int i);

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void stop();

    @Override // com.huawei.linkage.sportlinkage.LinkageApi
    void stopLinkage();
}
