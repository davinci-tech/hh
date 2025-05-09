package com.huawei.hms.ads.jsb.inner.data;

import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.download.app.AppStatusV1;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes4.dex */
public class AppDownloadInfo {
    private String appName;
    private int code;
    private String packageName;
    private int progress;
    private int reserveStatus;
    private String reservedPkgName;
    private String status;
    private long time;
    private String uniqueId;

    private void a(AppInfo appInfo) {
        if (appInfo != null) {
            this.packageName = appInfo.getPackageName();
            this.appName = appInfo.getAppName();
            this.uniqueId = appInfo.getUniqueId();
            this.time = System.currentTimeMillis();
        }
    }

    public AppDownloadInfo(String str, int i) {
        this.reservedPkgName = str;
        this.reserveStatus = i;
    }

    public AppDownloadInfo(AppInfo appInfo, AppStatusV1 appStatusV1, int i) {
        if (appInfo != null) {
            this.packageName = appInfo.getPackageName();
            this.appName = appInfo.getAppName();
        }
        if (appStatusV1 != null) {
            this.status = appStatusV1.toString();
        }
        this.code = i;
    }

    public AppDownloadInfo(AppInfo appInfo, AppStatus appStatus) {
        a(appInfo);
        if (appStatus != null) {
            this.status = appStatus.toString();
        }
    }

    public AppDownloadInfo(AppInfo appInfo, int i) {
        a(appInfo);
        this.progress = i;
    }
}
