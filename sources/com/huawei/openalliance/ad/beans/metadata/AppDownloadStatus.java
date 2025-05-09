package com.huawei.openalliance.ad.beans.metadata;

import com.huawei.openalliance.ad.download.app.AppStatus;

/* loaded from: classes9.dex */
public class AppDownloadStatus {
    private static final String DOWNLOAD = "download";
    private static final String DOWNLOADING = "downloading";
    private static final String INSTALL = "install";
    private static final String INSTALLED = "installed";
    private static final String INSTALLING = "installing";
    private static final String PAUSE = "pause";
    private static final String UNKNOWN = "unknown";
    private String status = "unknown";
    private int percentage = 0;

    public final void a(AppStatus appStatus) {
        String str;
        if (AppStatus.INSTALLED == appStatus) {
            str = INSTALLED;
        } else if (AppStatus.DOWNLOAD == appStatus) {
            str = "download";
        } else if (AppStatus.DOWNLOADING == appStatus) {
            str = DOWNLOADING;
        } else if (AppStatus.PAUSE == appStatus || AppStatus.WAITING_FOR_WIFI == appStatus) {
            str = "pause";
        } else if (AppStatus.INSTALLING == appStatus) {
            str = "installing";
        } else if (AppStatus.INSTALL != appStatus) {
            return;
        } else {
            str = "install";
        }
        this.status = str;
    }

    public void a(int i) {
        this.percentage = i;
    }

    public String a() {
        return this.status;
    }
}
