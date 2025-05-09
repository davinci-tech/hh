package com.huawei.hwversionmgr.utils.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import com.huawei.haf.common.utils.PowerUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.hwversionmgr.selfupdate.appupdate.UpdateBase;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.R$string;
import com.huawei.updatesdk.service.appmgr.bean.ApkUpgradeInfo;
import com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack;
import com.huawei.updatesdk.service.otaupdate.UpdateKey;
import defpackage.kxf;
import defpackage.kxl;
import defpackage.kxu;
import defpackage.kxz;
import defpackage.kyb;
import defpackage.kyc;
import defpackage.kyd;
import defpackage.kye;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class UpdateService extends Service {
    private Context b;
    private int c;
    private String e;
    private HwVersionManager h;
    private UpdateBase j;

    /* renamed from: a, reason: collision with root package name */
    private int f6415a = 2;
    private int f = -1;
    private int d = 0;
    private CheckUpdateCallBack i = new CheckUpdateCallBack() { // from class: com.huawei.hwversionmgr.utils.service.UpdateService.3
        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onUpdateInfo(Intent intent) {
            UpdateService.this.a();
            if (intent == null) {
                ReleaseLogUtil.d("OTA_UpdateService", "mUpdateCallback onUpdateInfo: intent is null.");
                UpdateService.this.stopSelf();
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                UpdateService.this.bSK_(extras);
            } else {
                ReleaseLogUtil.d("OTA_UpdateService", "mUpdateCallback onUpdateInfo: bundle is null.");
                UpdateService.this.stopSelf();
            }
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onMarketInstallInfo(Intent intent) {
            UpdateService.this.a();
            UpdateService.this.stopSelf();
            ReleaseLogUtil.e("OTA_UpdateService", "mUpdateCallback onMarketInstallInfo");
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onMarketStoreError(int i) {
            UpdateService.this.a();
            UpdateService.this.stopSelf();
            ReleaseLogUtil.c("OTA_UpdateService", "mUpdateCallback onMarketStoreError: responseCode is ", Integer.valueOf(i));
        }

        @Override // com.huawei.updatesdk.service.otaupdate.CheckUpdateCallBack
        public void onUpdateStoreError(int i) {
            UpdateService.this.a();
            UpdateService.this.stopSelf();
            ReleaseLogUtil.c("OTA_UpdateService", "mUpdateCallback onUpdateStoreError: responseCode is ", Integer.valueOf(i));
        }
    };

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        if (i == 9) {
            return;
        }
        kyc.e().c();
        if (i == 3) {
            kyc.e().a(this.b.getString(R$string.IDS_update_download_failed), this.b.getString(R$string.IDS_update_network_error));
        } else if (i == 1) {
            kyc.e().a(this.b.getString(R$string.IDS_update_download_failed), this.b.getString(R$string.IDS_update_download_check_failed));
        } else {
            kyc.e().a(this.b.getString(R$string.IDS_update_download_failed), null);
            this.j.a();
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = getApplicationContext();
        LogUtil.a("OTA_UpdateService", "onCreate");
        this.j = new UpdateBase(this.b);
        this.h = HwVersionManager.c(this.b);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("OTA_UpdateService", "onDestroy");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        super.onStartCommand(intent, i, i2);
        LogUtil.a("OTA_UpdateService", "onStartCommand: intent = ", intent, " startId ", Integer.valueOf(i2));
        if (bSJ_(intent, i2)) {
            return 2;
        }
        b(i2, "handleIntent");
        return 2;
    }

    @Override // android.app.Service
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
        LogUtil.a("OTA_UpdateService", "onTaskRemoved");
        kyc e = kyc.e();
        if (e.d()) {
            e.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, String str) {
        stopSelf(i);
        LogUtil.a("OTA_UpdateService", "clearTaskService: tag = ", str, " startId ", Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bSK_(Bundle bundle) {
        int i = bundle.getInt("status", 0);
        LogUtil.a("OTA_UpdateService", "handleUpdateInfo:status is ", Integer.valueOf(i));
        if (i == 7) {
            if (bundle.get(UpdateKey.INFO) instanceof ApkUpgradeInfo) {
                ApkUpgradeInfo apkUpgradeInfo = (ApkUpgradeInfo) bundle.get(UpdateKey.INFO);
                this.e = apkUpgradeInfo.getVersion_();
                this.c = apkUpgradeInfo.getSize_();
                String newFeatures_ = apkUpgradeInfo.getNewFeatures_();
                kxl d = d(apkUpgradeInfo, newFeatures_);
                kxu.f(BaseApplication.getAppPackage());
                kxz.f(this.e, this.b);
                kxz.d(d.x(), this.b);
                if (this.f6415a == 0) {
                    c(newFeatures_);
                    kye.d(13, 5, "", "", 0);
                } else {
                    kye.d(12, this.c, this.e, "", 0);
                    kye.d(32, 0, newFeatures_, "", 0);
                }
                if (CommonUtil.bh()) {
                    kyd.a(this.b, apkUpgradeInfo, false);
                }
            } else {
                ReleaseLogUtil.d("OTA_UpdateService", "handleUpdateInfo: apkUpgradeInfo is null");
                stopSelf();
                return;
            }
        } else if (this.f6415a == 0) {
            kye.b(6);
            kye.d(11, 0, "", "", 0);
        } else if (i == 3) {
            kye.d(11, 0, "", "", 0);
        } else if (i == 2) {
            kye.d(11, 1, "", "", 0);
        } else {
            kye.d(11, 3, "", "", 0);
        }
        stopSelf();
    }

    private kxl d(ApkUpgradeInfo apkUpgradeInfo, String str) {
        kxl e = kxu.e();
        e.s(this.e);
        e.e(this.c);
        e.e(str);
        e.p(String.valueOf(apkUpgradeInfo.getVersionCode_()));
        e.k(BaseApplication.getAppPackage() + "." + this.e + ".apk");
        e.a(apkUpgradeInfo.getDownurl_());
        e.f(apkUpgradeInfo.getSha256_());
        return e;
    }

    private void c(String str) {
        kye.b(new kye.c(5).c(this.e).c(this.c).d(str).a("").d(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean bSJ_(Intent intent, int i) {
        char c;
        if (intent == null) {
            return false;
        }
        String action = intent.getAction();
        ReleaseLogUtil.e("OTA_UpdateService", "handleIntent: action = ", action);
        if (action == null) {
            return false;
        }
        action.hashCode();
        switch (action.hashCode()) {
            case -1907829754:
                if (action.equals("action_cancel_download_app")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1571147019:
                if (action.equals("action_app_manual_update_new_version")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -1049701100:
                if (action.equals("action_app_update_success")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -129550983:
                if (action.equals("action_app_auto_check_new_version")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 129865996:
                if (action.equals("action_app_update_failed")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 775337289:
                if (action.equals("action_app_download_new_version")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1720921710:
                if (action.equals("action_app_install_new_version")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                LogUtil.a("OTA_UpdateService", "handleIntent, cancel download app!");
                this.j.a();
                return false;
            case 1:
            case 3:
            case 5:
            case 6:
                return a(action, i);
            case 2:
            case 4:
                return false;
            default:
                LogUtil.a("OTA_UpdateService", "handleIntent, default branch");
                return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean a(String str, int i) {
        char c;
        if (CommonUtil.cg()) {
            ReleaseLogUtil.d("OTA_UpdateService", "handleAppAction invalid version");
            return false;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -1571147019:
                if (str.equals("action_app_manual_update_new_version")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -129550983:
                if (str.equals("action_app_auto_check_new_version")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 775337289:
                if (str.equals("action_app_download_new_version")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1720921710:
                if (str.equals("action_app_install_new_version")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c == 3) {
                        kye.c(this.b, this.h, this.j);
                        return false;
                    }
                    ReleaseLogUtil.d("OTA_UpdateService", "handleAppAction unknown action");
                    return false;
                }
                d(i);
            } else if (!b()) {
                return false;
            }
        } else if (!d()) {
            return false;
        }
        return true;
    }

    private boolean b() {
        this.f6415a = 0;
        if (kyd.d() && kyd.c()) {
            ReleaseLogUtil.e("OTA_UpdateService", "handleAppAutoUpdateAction auto check switch is on");
            if (CommonUtil.bd()) {
                kyd.b(this.b, this.i, false, kyd.b(), false);
            } else {
                kyb.e(this.b, true, this.i);
            }
            return true;
        }
        ReleaseLogUtil.e("OTA_UpdateService", "handleAppAutoUpdateAction auto check switch is off");
        return false;
    }

    private boolean d() {
        this.f6415a = 2;
        if (kyd.c()) {
            ReleaseLogUtil.e("OTA_UpdateService", "handleAppManualUpdateAction update switch is on");
            if (CommonUtil.bd()) {
                kyd.b(this.b, BaseApplication.getAppPackage(), this.i);
                return true;
            }
            kyb.e(this.b, false, this.i);
            return true;
        }
        ReleaseLogUtil.e("OTA_UpdateService", "handleAppManualUpdateAction update switch is off");
        return false;
    }

    private void e(int i) {
        LogUtil.a("OTA_UpdateService", "enter startBatteryCheck");
        if (c()) {
            String string = this.b.getString(R$string.IDS_settings_firmware_upgrade_phone_low_battery);
            if (nsn.ae(BaseApplication.getContext())) {
                string = this.b.getString(R$string.IDS_pad_phone_low_battery, UnitUtil.e(10.0d, 2, 0));
            }
            kye.d(22, 4, "", "", 0);
            kyc.e().c();
            kyc.e().a(this.b.getString(R$string.IDS_update_download_failed), string);
            this.j.a();
            b(i, "startBatteryCheck");
            return;
        }
        c(i);
    }

    private void c(int i) {
        if (this.f == 2) {
            this.j.downloadFile(new b(i), true);
        }
    }

    private void d(int i) {
        this.f = 2;
        boolean a2 = kye.a(2, this.h);
        LogUtil.a("OTA_UpdateService", "downloadFile: isNewVersionExist = ", Boolean.valueOf(a2));
        if (a2 && e()) {
            kye.d(23, 0, "", "", 0);
            stopSelf(i);
        } else {
            a(i);
        }
    }

    private boolean e() {
        LogUtil.a("OTA_UpdateService", "enter checkAppSha256 mReportSuccess:", Integer.valueOf(this.f));
        String b2 = this.h.b();
        String h = kxu.e().h();
        String k = CommonUtil.k(b2);
        if (h == null) {
            ReleaseLogUtil.d("OTA_UpdateService", "checkAppSha256 srcSha256 is null");
            kye.e(this.h);
            return false;
        }
        if (h.equals(k)) {
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_UPDATE_FAILED_85070026.value(), nsn.e("0"));
            return true;
        }
        ReleaseLogUtil.d("OTA_UpdateService", "checkAppSha256 verify srcSha256 failed");
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_UPDATE_FAILED_85070026.value(), -1);
        kye.e(this.h);
        return false;
    }

    private boolean c() {
        int b2 = PowerUtil.b(this.b);
        if (b2 >= 10 || b2 == -1 || PowerUtil.a(this.b)) {
            return false;
        }
        ReleaseLogUtil.d("OTA_UpdateService", "MIN_PHONE_BATTERY_LEVEL not autoDownload");
        return true;
    }

    private void a(int i) {
        kye.d(20, -1, "", "", 0);
        kyc.e().c();
        kyc.e().c(this.b.getString(R$string.IDS_app_update_updating), 0);
        e(i);
    }

    class b extends AppDownloadHandler {
        private int c;

        b(int i) {
            this.c = i;
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadFailed(int i) {
            ReleaseLogUtil.c("OTA_UpdateService", "ManualAppDownloadHandler: doDownloadFailed");
            kye.d(22, i, "", "", 0);
            UpdateService.this.b(i);
            UpdateService.this.b(this.c, "ManualAppDownloadHandler doDownloadFailed");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doDownloadSuccess(kxf kxfVar) {
            if (kxfVar == null) {
                UpdateService.this.b(this.c, "ManualAppDownloadHandler doDownloadSuccess appDownloadInfo == null");
                return;
            }
            String a2 = kxfVar.a();
            if (kye.b(UpdateService.this.b, a2)) {
                UpdateService.this.h.q(a2);
                kyc.e().a();
                kye.c(UpdateService.this.b, UpdateService.this.h, UpdateService.this.j);
                kye.d(23, 0, "", "", 0);
                UpdateService.this.b(this.c, "ManualAppDownloadHandler doDownloadSuccess");
                return;
            }
            kye.d(22, 47, "", "", 0);
            UpdateService.this.b(this.c, "ManualAppDownloadHandler doDownloadSuccess isSameApkSignatures");
        }

        @Override // com.huawei.hwversionmgr.utils.handler.AppDownloadHandler
        public void doInDownloadProgress(kxf kxfVar) {
            int e;
            if (kxfVar == null) {
                return;
            }
            LogUtil.a("OTA_UpdateService", "ManualAppDownloadHandler doInDownloadProgress total=", Long.valueOf(kxfVar.c()), ",CurrentProgress=", Long.valueOf(kxfVar.e()));
            if (kxfVar.c() == 0 || (e = (int) ((kxfVar.e() * 100) / kxfVar.c())) == UpdateService.this.d) {
                return;
            }
            UpdateService.this.d = e;
            kyc.e().c(UpdateService.this.b.getString(R$string.IDS_app_update_updating), e);
            kye.d(21, e, "", "", 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.f6415a == 0) {
            SharedPreferenceManager.e(this.b, String.valueOf(10023), "key_last_check_timestamp", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        }
    }
}
