package com.huawei.health.versionmgr.api;

import android.content.Context;
import android.content.Intent;

/* loaded from: classes4.dex */
public interface VersionMgrApi {
    void autoCheckAppNewVersionService();

    void checkScaleNewVersionService(String str, String str2, String str3, boolean z, String str4);

    void deleteMessage();

    void doManualCheckAppNewVersion();

    boolean getBandOtaStatus(Context context);

    String getUpdateUrl(boolean z);

    boolean haveNewAppVersion(Context context);

    boolean isDeviceOtaUpdating(String str);

    boolean isOtherDeviceOtaUpdating(String str);

    void makeMessage(Context context);

    void resetBandUpdate(String str);

    void resetBandUpdate(String str, String str2);

    void showNewVersionDialog(Context context, Intent intent);
}
