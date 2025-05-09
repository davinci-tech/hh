package com.huawei.openalliance.ad.download.ag;

import com.huawei.openalliance.ad.download.DownloadTask;
import com.huawei.openalliance.ad.download.app.AppDownloadTask;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public class RemoteAppDownloadTask {
    private String contentId;
    private long downloadedSize;
    private long fileTotalSize;
    private int pauseReason;
    private int progress;
    private String sha256;
    private String slotId;
    private int status;
    private String url;

    public AppDownloadTask a(AppInfo appInfo) {
        AppDownloadTask a2 = new AppDownloadTask.a().a(appInfo).a();
        if (a2 == null) {
            a2 = new AppDownloadTask();
            a2.a(appInfo);
        }
        a2.m(this.contentId);
        a2.b(this.progress);
        a2.a(com.huawei.openalliance.ad.download.e.a(this.status));
        a2.b(this.downloadedSize);
        a2.a(this.fileTotalSize);
        a2.a(this.url);
        a2.c(this.sha256);
        a2.k(this.slotId);
        a2.a(DownloadTask.c.a(this.pauseReason));
        return a2;
    }
}
