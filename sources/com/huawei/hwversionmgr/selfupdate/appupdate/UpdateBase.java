package com.huawei.hwversionmgr.selfupdate.appupdate;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.hwversionmgr.utils.handler.AppCheckNewVersionHandler;
import com.huawei.hwversionmgr.utils.handler.AppDownloadHandler;
import com.huawei.hwversionmgr.utils.handler.AppPullChangeLogHandler;
import defpackage.cvz;
import defpackage.kxn;
import defpackage.kxs;
import defpackage.kxu;
import defpackage.kxz;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class UpdateBase implements Update {
    private static DeviceInfo c = null;
    private static int d = -1;
    private static boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    protected kxs f6413a = new kxs();
    protected Context b;

    public UpdateBase(Context context) {
        this.b = context;
    }

    public static void d(DeviceInfo deviceInfo) {
        c = deviceInfo;
    }

    public static void c(int i) {
        d = i;
    }

    public static void c(boolean z) {
        e = z;
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void checkAppNewVersion(String str, AppCheckNewVersionHandler appCheckNewVersionHandler) {
        LogUtil.c("UpdateBase", "checkAppNewVersion() handler = ", appCheckNewVersionHandler);
        kxu.a(kxu.e(false));
        PackageInfo bSw_ = bSw_();
        if (bSw_ == null) {
            if (appCheckNewVersionHandler != null) {
                appCheckNewVersionHandler.handleCheckFailed(3);
                return;
            }
            return;
        }
        kxn kxnVar = new kxn();
        kxnVar.c("");
        kxnVar.e(true);
        kxnVar.d(false);
        kxnVar.b("");
        kxnVar.d("");
        this.f6413a.bSu_(bSw_, kxnVar, this.b, appCheckNewVersionHandler);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void checkBandNewVersion(int i, String str, String str2, AppCheckNewVersionHandler appCheckNewVersionHandler) {
        LogUtil.c("UpdateBase", "checkBandNewVersion() type = ", Integer.valueOf(i), ", version = ", str, ", handler = ", appCheckNewVersionHandler);
        checkBandNewVersion(i, str, str2, false, appCheckNewVersionHandler);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void checkBandNewVersion(int i, String str, String str2, boolean z, AppCheckNewVersionHandler appCheckNewVersionHandler) {
        LogUtil.c("UpdateBase", "checkBandNewVersion() type = ", Integer.valueOf(i), ", version = ", str, ", handler = ", appCheckNewVersionHandler, ", isEnterprise = ", Boolean.valueOf(z));
        boolean c2 = cvz.c(c);
        if (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode") && !c2) {
            LogUtil.a("UpdateBase", "checkBandNewVersion() Url.CHECK_TEST_VERSION_URL=", "");
            kxu.a("/ring2/v2/" + kxu.c(z));
        } else {
            kxu.a(kxu.d(c2, z));
        }
        PackageInfo bSy_ = bSy_(str2, i, str);
        if (bSy_ == null) {
            if (appCheckNewVersionHandler != null) {
                appCheckNewVersionHandler.handleCheckFailed(3);
                return;
            }
            return;
        }
        kxn kxnVar = new kxn();
        kxnVar.c(str2);
        kxnVar.e(false);
        kxnVar.d(z);
        DeviceInfo deviceInfo = c;
        if (deviceInfo != null) {
            kxnVar.e(TextUtils.isEmpty(deviceInfo.getCertModel()) ? c.getDeviceModel() : c.getCertModel());
            kxnVar.b(c.getDeviceModel());
            kxnVar.d(kxz.b(BaseApplication.getContext(), c.getDeviceIdentify()));
            kxnVar.a(c.getDeviceIdentify());
            kxnVar.b(d);
        }
        this.f6413a.bSu_(bSy_, kxnVar, this.b, appCheckNewVersionHandler);
    }

    public PackageInfo bSy_(String str, int i, String str2) {
        LogUtil.c("UpdateBase", "getBandPackageInfo() type=", Integer.valueOf(i), ", version = ", str2);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        PackageInfo packageInfo = new PackageInfo();
        if (i == 1) {
            packageInfo.packageName = "com.huawei.btwo.firmware";
        } else if (i == 5) {
            packageInfo.packageName = "com.huawei.bzero.firmware";
        } else if (i == 15) {
            packageInfo.packageName = "com.huawei.Eris.firmware";
        } else if (i == 7) {
            packageInfo.packageName = "com.huawei.gemini.firmware";
        } else if (i == 8) {
            packageInfo.packageName = "com.huawei.metis.firmware";
        } else if (i == 12) {
            packageInfo.packageName = "com.huawei.aonepro.firmware";
        } else if (i == 13) {
            packageInfo.packageName = "com.huawei.nyx.firmware";
        } else {
            String e2 = e(str, i);
            if (TextUtils.isEmpty(e2)) {
                return null;
            }
            packageInfo.packageName = e2;
        }
        if (!TextUtils.isEmpty(HwVersionManager.c(this.b).f(str))) {
            packageInfo.packageName = HwVersionManager.c(this.b).f(str);
        }
        packageInfo.versionCode = 0;
        packageInfo.versionName = str2;
        return packageInfo;
    }

    private String e(String str, int i) {
        if (i == 14) {
            return "com.huawei.grus.firmware";
        }
        if (i == 16) {
            return "com.huawei.Janus.firmware";
        }
        if (i == 23) {
            return "com.huawei.AW70B29.firmware";
        }
        if (i == 24) {
            return "com.huawei.AW70B19.firmware";
        }
        if (i == 36) {
            return "com.huawei.AW70B39.firmware";
        }
        if (i == 37) {
            return "com.huawei.AW70B39HN.firmware";
        }
        switch (i) {
            case 18:
                return "com.huawei.Crius.firmware";
            case 19:
                return "com.huawei.Terra.firmware";
            case 20:
                return "com.huawei.Talos.firmware";
            case 21:
                return "com.huawei.Fortuna.firmware";
            default:
                return HwVersionManager.c(this.b).f(str);
        }
    }

    private PackageInfo bSw_() {
        try {
            return this.b.getPackageManager().getPackageInfo(this.b.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.b("UpdateBase", "getAppPackageInfo PackageManager.NameNotFoundException");
            return null;
        }
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void fetchChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z) {
        this.f6413a.c(this.b, appPullChangeLogHandler, z, false, false);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void saveChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, String str, String str2, boolean z) {
        this.f6413a.a(this.b, appPullChangeLogHandler, z, str, str2);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void saveAw70ChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, String str, String str2, boolean z) {
        this.f6413a.e(this.b, appPullChangeLogHandler, z, str, str2);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void fetchAw70ChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z) {
        this.f6413a.c(this.b, appPullChangeLogHandler, z, true, false);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void fetchScaleChangeLog(AppPullChangeLogHandler appPullChangeLogHandler, boolean z) {
        this.f6413a.c(this.b, appPullChangeLogHandler, z, false, true);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void downloadFile(AppDownloadHandler appDownloadHandler, boolean z) {
        this.f6413a.a(this.b, appDownloadHandler, z, false, false);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void downloadAw70File(AppDownloadHandler appDownloadHandler, boolean z) {
        this.f6413a.a(this.b, appDownloadHandler, z, true, false);
    }

    @Override // com.huawei.hwversionmgr.selfupdate.appupdate.Update
    public void downloadScaleFile(AppDownloadHandler appDownloadHandler, boolean z) {
        this.f6413a.a(this.b, appDownloadHandler, z, false, true);
    }

    public void d(String str, String str2) {
        String packageName = this.b.getPackageName();
        LogUtil.c("UpdateBase", "install: path = ", str, ", version = ", str2, ", packageName = ", packageName);
        this.f6413a.b(this.b, str, packageName);
    }

    public void a() {
        this.f6413a.b();
    }

    public void e(String str, String str2, AppCheckNewVersionHandler appCheckNewVersionHandler, String str3) {
        ReleaseLogUtil.e("R_Weight_UpdateBase", "checkScaleBandNewVersion productId:", CommonUtil.l(str), ", version = ", str2);
        if (e(str)) {
            if (CommonUtil.as() && TextUtils.equals(kxz.g(BaseApplication.getContext()), "test_mode") && !e) {
                LogUtil.a("UpdateBase", "checkScaleBandNewVersion() Url.CHECK_TEST_VERSION_URL=", "");
                kxu.d("/ring2/v2/CheckEx.action?ruleAttr=true");
            } else {
                kxu.d(kxu.e(e));
            }
        } else {
            kxu.d("https://query.hicloud.com/accessory/v2/checkEx.action");
        }
        PackageInfo bSx_ = bSx_(str, str2);
        if (bSx_ != null) {
            this.f6413a.bSv_(bSx_, this.b, appCheckNewVersionHandler, str, str3);
        } else if (appCheckNewVersionHandler != null) {
            appCheckNewVersionHandler.handleCheckFailed(3);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private boolean e(String str) {
        char c2;
        if (str == null) {
            return false;
        }
        str.hashCode();
        switch (str.hashCode()) {
            case -771439772:
                if (str.equals("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4")) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -237937675:
                if (str.equals("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4")) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case 299435131:
                if (str.equals("e835d102-af95-48a6-ae13-2983bc06f5c0")) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 759413903:
                if (str.equals("c943c933-442e-4c34-bcd0-66597f24aaed")) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 912505950:
                if (str.equals("b29df4e3-b1f7-4e40-960d-4cfb63ccca05")) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        return c2 == 0 || c2 == 1 || c2 == 2 || c2 == 3 || c2 == 4;
    }

    private PackageInfo bSx_(String str, String str2) {
        LogUtil.a("UpdateBase", "getScaleBandPackageInfo productId:", CommonUtil.l(str), ", version = ", str2);
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        PackageInfo packageInfo = new PackageInfo();
        if ("34fa0346-d46c-439d-9cb0-2f696618846b".equals(str)) {
            packageInfo.packageName = "com.huawei.ch18";
        } else if ("33123f39-7fc1-420b-9882-a4b0d6c61100".equals(str)) {
            packageInfo.packageName = "com.huawei.ch100";
        } else if ("ccd1f0f8-8c57-4bd7-a884-0ef38482f15f".equals(str)) {
            packageInfo.packageName = "com.huawei.ah100";
        } else if ("25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(str)) {
            packageInfo.packageName = "com.huawei.Lupin.firmware";
        } else if ("8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4".equals(str)) {
            packageInfo.packageName = "com.huawei.Hagrid.firmware";
        } else if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(str)) {
            packageInfo.packageName = "com.huawei.Hagrid-B29.firmware";
        } else if ("e835d102-af95-48a6-ae13-2983bc06f5c0".equals(str)) {
            packageInfo.packageName = "com.huawei.Herm-B19.firmware";
        } else if ("c943c933-442e-4c34-bcd0-66597f24aaed".equals(str)) {
            packageInfo.packageName = "com.huawei.Dobby-B19.firmware";
        } else {
            LogUtil.h("UpdateBase", "getScaleBandPackageInfo other productId.");
        }
        packageInfo.versionCode = 0;
        packageInfo.versionName = str2;
        return packageInfo;
    }
}
