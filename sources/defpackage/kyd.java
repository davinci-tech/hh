package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.versionmgr.betaversionmgr.BetaVersionMgrApi;
import com.huawei.health.versionmgr.betaversionmgr.CheckBetaUpdateCallBack;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.updatesdk.UpdateSdkAPI;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;

/* loaded from: classes5.dex */
public class kyd {
    public static void b(Context context, CheckUpdateCallBack checkUpdateCallBack, boolean z, int i, boolean z2) {
        LogUtil.a("OTA_UpdateSdkUtil", "checkClientOtaUpdate...");
        if (context == null) {
            LogUtil.h("OTA_UpdateSdkUtil", "checkClientOtaUpdate: context is null");
        } else if (checkUpdateCallBack == null) {
            LogUtil.h("OTA_UpdateSdkUtil", "checkClientOtaUpdate: callback is null");
        } else {
            UpdateSdkAPI.checkClientOTAUpdate(context, checkUpdateCallBack, z, (int) ((i * 60000) / 86400000), z2);
        }
    }

    public static void a(Context context, ApkUpgradeInfo apkUpgradeInfo, boolean z) {
        LogUtil.a("OTA_UpdateSdkUtil", "showUpdateDialog...");
        if (context == null) {
            LogUtil.h("OTA_UpdateSdkUtil", "showUpdateDialog: context is null");
        } else {
            apkUpgradeInfo.setDevType_(0);
            UpdateSdkAPI.showUpdateDialog(context, apkUpgradeInfo, z);
        }
    }

    public static void b(Context context, String str, CheckUpdateCallBack checkUpdateCallBack) {
        LogUtil.a("OTA_UpdateSdkUtil", "checkTargetAppUpdate...");
        if (context == null) {
            LogUtil.h("OTA_UpdateSdkUtil", "checkTargetAppUpdate: context is null");
        } else if (checkUpdateCallBack == null) {
            LogUtil.h("OTA_UpdateSdkUtil", "checkTargetAppUpdate: callback is null");
        } else {
            UpdateSdkAPI.checkTargetAppUpdate(context, str, checkUpdateCallBack);
        }
    }

    public static boolean c() {
        if (CommonUtil.as()) {
            return true;
        }
        return "on".equalsIgnoreCase(jah.c().e("common_config_updatesdk_switch"));
    }

    public static boolean d() {
        if (CommonUtil.as()) {
            return true;
        }
        return !"off".equalsIgnoreCase(jah.c().e("common_config_ota_switch"));
    }

    public static int b() {
        try {
            return Integer.parseInt(jah.c().e("ota_time"));
        } catch (NumberFormatException unused) {
            LogUtil.b("OTA_UpdateSdkUtil", "getOtaTime() NumberFormatException");
            return 4320;
        }
    }

    public static void a(final boolean z) {
        LogUtil.a("OTA_UpdateSdkUtil", "checkNewBetaVersion()...");
        ((BetaVersionMgrApi) Services.c("HWBetaVersionMgr", BetaVersionMgrApi.class)).checkBetaAppVersion(new CheckBetaUpdateCallBack() { // from class: kyd.1
            @Override // com.huawei.health.versionmgr.betaversionmgr.CheckBetaUpdateCallBack
            public void onUpdateBetaInfo(Intent intent) {
                kyd.e(z ? 0 : 2);
                if (intent == null) {
                    LogUtil.h("OTA_UpdateSdkUtil", "mBetaUpdateCallBack onUpdateInfo: intent is null.");
                    return;
                }
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    kyd.bSF_(extras, !z);
                } else {
                    LogUtil.h("OTA_UpdateSdkUtil", "mBetaUpdateCallBack onUpdateInfo: bundle is null.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void bSF_(Bundle bundle, boolean z) {
        boolean z2 = bundle.getBoolean("hasNewBetaVersion");
        int i = bundle.getInt("responseCode");
        if (z2) {
            Context e = BaseApplication.e();
            kxz.f("hasNewBetaVersion", e);
            kxz.d(String.valueOf(CommonUtil.d(e)), e);
        }
        b(z2, z, i);
    }

    private static void b(boolean z, boolean z2, int i) {
        Intent intent;
        LogUtil.a("OTA_UpdateSdkUtil", "broadcastBetaAppCheckResult() in");
        if (z2) {
            intent = new Intent("action_app_check_new_version_state");
        } else {
            intent = new Intent("action_band_auto_check_new_version_result");
            intent.putExtra("result", 5);
        }
        intent.addFlags(1610612736);
        intent.putExtra("hasNewBetaVersion", z);
        intent.putExtra("isManual", z2);
        intent.putExtra("responseCode", i);
        intent.setPackage(com.huawei.hwcommonmodel.application.BaseApplication.getContext().getPackageName());
        com.huawei.hwcommonmodel.application.BaseApplication.getContext().sendBroadcast(intent, LocalBroadcast.c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(int i) {
        if (i == 0) {
            SharedPreferenceManager.e(BaseApplication.e(), String.valueOf(10023), "key_last_check_timestamp", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        }
    }
}
