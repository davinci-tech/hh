package com.huawei.openalliance.ad.download;

import com.huawei.openalliance.ad.download.DownloadTask;

/* loaded from: classes5.dex */
public interface a<T extends DownloadTask> {
    void a(T t);

    void a(T t, boolean z);

    void b(T t);

    void b(T t, boolean z);

    void c(T t, boolean z);

    void onDownloadFail(T t);

    void onDownloadProgress(T t);

    void onDownloadStart(T t);

    void onDownloadSuccess(T t);

    void onDownloadWaiting(T t);
}
