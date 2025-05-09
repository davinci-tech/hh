package com.huawei.haf.bundle;

import java.util.List;

/* loaded from: classes.dex */
public interface AppBundleDownloader {

    public interface DownloadCallback {
        void onCanceled();

        void onCanceling();

        void onCompleted();

        void onError(int i);

        void onProgress(long j);

        void onStart();
    }

    public interface DownloadRequest {
        String getFileDir();

        String getFileName();

        int getFileNum();

        String getModuleName();

        String getUrl();
    }

    boolean cancelDownloadSync(int i);

    void deferredDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback);

    void startDownload(int i, List<DownloadRequest> list, DownloadCallback downloadCallback);
}
