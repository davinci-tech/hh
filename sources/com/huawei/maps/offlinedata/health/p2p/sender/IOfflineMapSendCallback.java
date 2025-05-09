package com.huawei.maps.offlinedata.health.p2p.sender;

/* loaded from: classes5.dex */
public interface IOfflineMapSendCallback {
    void onFileTransferReport(String str);

    void onSendProgress(long j);

    void onSendResult(int i);
}
