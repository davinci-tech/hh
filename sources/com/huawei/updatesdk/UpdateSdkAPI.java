package com.huawei.updatesdk;

import android.content.Context;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.appmgr.bean.AppInfoAdapter;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateParams;
import com.huawei.updatesdk.service.otaupdate.g;

/* loaded from: classes7.dex */
public final class UpdateSdkAPI {
    @Deprecated
    public static void setAppStorePkgName(String str) {
    }

    public static void showUpdateDialogByAppInfo(Context context, ApkUpgradeInfo apkUpgradeInfo, AppInfoAdapter appInfoAdapter) {
        g.a(context, apkUpgradeInfo, appInfoAdapter);
    }

    public static void showUpdateDialog(Context context, ApkUpgradeInfo apkUpgradeInfo, boolean z) {
        g.a(context, apkUpgradeInfo, z);
    }

    public static void setServiceZone(String str) {
        g.a(str);
    }

    public static void releaseCallBack() {
        g.a();
    }

    public static void checkTargetAppUpdate(Context context, String str, CheckUpdateCallBack checkUpdateCallBack) {
        g.a(context, str, checkUpdateCallBack);
    }

    public static void checkClientOTAUpdate(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, int i, boolean z2) {
        g.a(context, checkUpdateCallBack, z, i, z2);
    }

    public static void checkAppUpdateForHMSKit(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        g.b(context, updateParams, checkUpdateCallBack);
    }

    public static void checkAppUpdateByAppInfo(Context context, CheckUpdateCallBack checkUpdateCallBack, AppInfoAdapter appInfoAdapter) {
        g.a(context, checkUpdateCallBack, appInfoAdapter);
    }

    public static void checkAppUpdate(Context context, UpdateParams updateParams, CheckUpdateCallBack checkUpdateCallBack) {
        g.a(context, updateParams, checkUpdateCallBack);
    }

    public static void checkAppUpdate(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, boolean z2) {
        g.a(context, checkUpdateCallBack, z, z2);
    }
}
