package com.huawei.health.devicemgr.business.entity.downloadresouce;

/* loaded from: classes3.dex */
public interface DownloadDeviceInfoCallBack {
    void netWorkError();

    void onDownload(int i);

    void onFailure(int i);

    void onSuccess();
}
