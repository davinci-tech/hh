package com.huawei.openalliance.ad.inter.listeners;

import com.huawei.openalliance.ad.download.app.AppStatus;
import com.huawei.openalliance.ad.inter.data.AppInfo;

/* loaded from: classes5.dex */
public interface AppDownloadListener {
    void onAppOpen(AppInfo appInfo);

    void onAppOpen(String str);

    void onDownloadProgress(AppInfo appInfo, int i);

    void onStatusChanged(AppStatus appStatus, AppInfo appInfo);

    void onUserCancel(AppInfo appInfo);
}
