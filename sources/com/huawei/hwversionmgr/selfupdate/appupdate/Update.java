package com.huawei.hwversionmgr.selfupdate.appupdate;

import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler;

/* loaded from: classes5.dex */
public interface Update {
    void checkAppNewVersion(String str, AppCheckNewVersionHandler appCheckNewVersionHandler);

    void checkBandNewVersion(int i, String str, String str2, AppCheckNewVersionHandler appCheckNewVersionHandler);

    void checkBandNewVersion(int i, String str, String str2, boolean z, AppCheckNewVersionHandler appCheckNewVersionHandler);

    void downloadAw70File(AppDownloadHandler appDownloadHandler, boolean z);

    void downloadFile(AppDownloadHandler appDownloadHandler, boolean z);

    void downloadScaleFile(AppDownloadHandler appDownloadHandler, boolean z);

    void fetchAw70ChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z);

    void fetchChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z);

    void fetchScaleChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z);

    void saveAw70ChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, String str, String str2, boolean z);

    void saveChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, String str, String str2, boolean z);
}
