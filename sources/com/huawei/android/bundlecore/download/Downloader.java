package com.huawei.android.bundlecore.download;

import java.util.List;

/* loaded from: classes8.dex */
public interface Downloader {
    boolean cancelDownloadSync(int i);

    void deferredDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback, boolean z);

    long getDownloadSizeThresholdWhenUsingMobileData();

    boolean isDeferredDownloadOnlyWhenUsingWifiData();

    void startDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback);
}
