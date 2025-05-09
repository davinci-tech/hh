package com.huawei.hms.framework.network.download;

/* loaded from: classes9.dex */
public interface DownloadTaskHandler {
    void onCompleted(DownloadTaskBean downloadTaskBean);

    void onException(DownloadTaskBean downloadTaskBean, DownloadException downloadException);

    void onProgress(DownloadTaskBean downloadTaskBean);

    void updateTaskBean(DownloadTaskBean downloadTaskBean);
}
