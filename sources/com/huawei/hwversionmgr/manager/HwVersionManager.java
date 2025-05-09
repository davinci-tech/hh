package com.huawei.hwversionmgr.manager;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwversionmgr.utils.service.UpdateService;
import defpackage.kxl;
import defpackage.kxv;
import defpackage.kxz;
import defpackage.kyd;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.utils.StringUtils;
import java.io.File;

/* loaded from: classes5.dex */
public class HwVersionManager extends HwBaseManager {
    private static volatile HwVersionManager b;
    private static final Object c = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f6412a;
    private boolean d;
    private boolean e;

    public HwVersionManager(Context context) {
        super(context);
        this.d = false;
        this.e = false;
        this.f6412a = context;
    }

    public static HwVersionManager c(Context context) {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new HwVersionManager(BaseApplication.getContext());
                }
            }
        }
        return b;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 1003;
    }

    public boolean i() {
        return this.d;
    }

    public void e(boolean z) {
        this.d = z;
    }

    public boolean g() {
        return this.e;
    }

    public void d(boolean z) {
        this.e = z;
    }

    public void c(boolean z) {
        LogUtil.a("OTA_HwVersionManager", "checkAppNewVersionService", Boolean.valueOf(z));
        if (CommonUtil.as()) {
            g(z);
            return;
        }
        Intent intent = new Intent(this.f6412a, (Class<?>) UpdateService.class);
        if (z) {
            if (a()) {
                intent.setAction("action_app_auto_check_new_version");
            } else {
                LogUtil.h("OTA_HwVersionManager", "checkAppNewVersionService canAutoCheckNewVersion is false.");
                return;
            }
        } else {
            intent.setAction("action_app_manual_update_new_version");
        }
        try {
            this.f6412a.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "OTA_HwVersionManager", ExceptionUtils.d(e));
        }
    }

    private void g(boolean z) {
        if (z && !a()) {
            LogUtil.h("OTA_HwVersionManager", "checkNewBetaVersion canAutoCheckNewVersion is false.");
            return;
        }
        if (CommonUtil.d(this.f6412a) != CommonUtil.h(kxz.a(this.f6412a))) {
            kxz.f("", this.f6412a);
        }
        kyd.a(z);
    }

    public void c() {
        LogUtil.c("OTA_HwVersionManager", "downloadPackage");
        b(true);
    }

    public void b(boolean z) {
        LogUtil.c("OTA_HwVersionManager", "downloadPackage");
        Intent intent = new Intent(this.f6412a, (Class<?>) UpdateService.class);
        if (z) {
            intent.setAction("action_app_download_new_version");
        } else {
            intent.setAction("action_band_download_new_version");
        }
        try {
            this.f6412a.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "OTA_HwVersionManager", ExceptionUtils.d(e));
        }
    }

    public void d() {
        LogUtil.c("OTA_HwVersionManager", "cancelDownload");
        Intent intent = new Intent(this.f6412a, (Class<?>) UpdateService.class);
        intent.setAction("action_cancel_download_app");
        try {
            this.f6412a.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "OTA_HwVersionManager", ExceptionUtils.d(e));
        }
    }

    public String t(String str) {
        return kxz.g(str);
    }

    public boolean j() {
        String a2 = kxz.a(this.f6412a);
        LogUtil.a("OTA_HwVersionManager", "haveNewAppVersion: newVersionCode = ", a2);
        if (TextUtils.isEmpty(a2)) {
            return false;
        }
        int d = CommonUtil.d(this.f6412a);
        int h = CommonUtil.h(a2);
        if (CommonUtil.as()) {
            return d <= h && !TextUtils.isEmpty(kxz.d(this.f6412a));
        }
        String d2 = kxz.d(this.f6412a);
        LogUtil.a("OTA_HwVersionManager", "haveNewAppVersion: newVersionName = ", d2);
        if (TextUtils.isEmpty(d2)) {
            return false;
        }
        if (d2.contains("Beta")) {
            if (CommonUtil.ag(this.f6412a)) {
                return false;
            }
        } else if (CommonUtil.as()) {
            return false;
        }
        LogUtil.a("OTA_HwVersionManager", "haveNewAppVersion: newCode = ", Integer.valueOf(h), ", code = ", Integer.valueOf(d));
        if (d < h) {
            return true;
        }
        kxz.d(String.valueOf(d), this.f6412a);
        return false;
    }

    public boolean n(String str) {
        String j = kxz.j(this.f6412a, str);
        LogUtil.c("OTA_HwVersionManager", "haveNewBandVersion: newVersion = ", j);
        if (TextUtils.isEmpty(j)) {
            return false;
        }
        LogUtil.c("OTA_HwVersionManager", "haveNewBandVersion: version = ", kxz.g(this.f6412a, str));
        return !j.equals(r4);
    }

    public boolean c(String str, String str2) {
        String g = g(str);
        if (TextUtils.isEmpty(g) || !new File(g).exists()) {
            LogUtil.h("OTA_HwVersionManager", "isUpgradeFileValid isNewVersionExit is false.");
            return false;
        }
        kxl h = h(str2);
        String s = h.s();
        String h2 = h.h();
        if (!TextUtils.isEmpty(s)) {
            if (s.equalsIgnoreCase(CommonUtil.k(g))) {
                LogUtil.a("OTA_HwVersionManager", "isUpgradeFileValid verify sha256 success.");
                return true;
            }
            LogUtil.h("OTA_HwVersionManager", "isUpgradeFileValid verify sha256 failed.");
            return false;
        }
        String e = kxv.e(g);
        if (!TextUtils.isEmpty(h2) && h2.equalsIgnoreCase(e)) {
            LogUtil.a("OTA_HwVersionManager", "isUpgradeFileValid verify md5 success.");
            return true;
        }
        LogUtil.h("OTA_HwVersionManager", "isUpgradeFileValid verify md5 failed.");
        return false;
    }

    public void d(String str) {
        kxz.d(this.f6412a, str);
    }

    public String j(String str) {
        LogUtil.c("OTA_HwVersionManager", "enter getCheckNewBandVersion");
        return kxz.j(this.f6412a, str);
    }

    public String c(String str) {
        LogUtil.c("OTA_HwVersionManager", "enter getBandOsNewVersion");
        return kxz.n(this.f6412a, str);
    }

    public String i(String str) {
        LogUtil.c("OTA_HwVersionManager", "enter getCheckNewBandVersionId");
        return kxz.h(this.f6412a, str);
    }

    public String g(String str) {
        return kxz.o(this.f6412a, str);
    }

    public void g(String str, String str2) {
        kxz.f(str, str2, this.f6412a);
    }

    public String b(String str) {
        return kxz.g(this.f6412a, str);
    }

    public void b(String str, String str2) {
        kxz.g(str, str2, this.f6412a);
    }

    public String e() {
        return kxz.n(this.f6412a);
    }

    public void s(String str) {
        kxz.l(str, this.f6412a);
    }

    public String f(String str) {
        return kxz.e(str, this.f6412a);
    }

    public String b() {
        return kxz.b(this.f6412a);
    }

    public void q(String str) {
        kxz.i(str, this.f6412a);
    }

    public void l(String str) {
        LogUtil.c("OTA_HwVersionManager", "resetBandUpdate");
        kxz.q(this.f6412a, str);
    }

    public void m(String str) {
        LogUtil.a("OTA_HwVersionManager", "resetBandUpdateForEnd");
        d(str);
        kxz.e(this.f6412a, false);
        kxz.b("", str, this.f6412a);
        kxz.d("", str, this.f6412a);
        kxz.j("", str, this.f6412a);
        kxz.e("", str, this.f6412a);
        kxz.i(str, "", this.f6412a);
    }

    public void d(boolean z, boolean z2) {
        LogUtil.a("OTA_HwVersionManager", "reportStatus isApp: ", Boolean.valueOf(z), " isSuccess: ", Boolean.valueOf(z2));
        Intent intent = new Intent(this.f6412a, (Class<?>) UpdateService.class);
        if (z) {
            if (z2) {
                intent.setAction("action_app_update_success");
            } else {
                intent.setAction("action_app_update_failed");
            }
        }
        try {
            this.f6412a.startService(intent);
        } catch (IllegalStateException e) {
            LogUtil.b(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "OTA_HwVersionManager", ExceptionUtils.d(e));
        }
    }

    public void a(boolean z) {
        kxz.b(z);
    }

    public void a(String str, String str2) {
        kxz.j(str);
        kxz.f(str2);
    }

    public boolean o(String str) {
        if (kxz.i(this.f6412a)) {
            return TextUtils.equals(str, kxz.a()) || TextUtils.equals(str, kxz.b());
        }
        return false;
    }

    public boolean k(String str) {
        return (!kxz.i(this.f6412a) || TextUtils.equals(str, kxz.a()) || TextUtils.equals(str, kxz.b())) ? false : true;
    }

    public void a(kxl kxlVar, String str) {
        kxz.c(this.f6412a, kxlVar, str);
    }

    public kxl h(String str) {
        return kxz.k(this.f6412a, str);
    }

    public boolean a() {
        String b2 = SharedPreferenceManager.b(this.f6412a, String.valueOf(10023), "key_last_check_timestamp");
        if (!StringUtils.g(b2)) {
            return Math.abs(System.currentTimeMillis() - CommonUtil.g(b2)) > (CommonUtil.as() ? 86400000L : ((long) kyd.b()) * 60000);
        }
        LogUtil.h("OTA_HwVersionManager", "canAutoCheckNewVersion lastCheckTimeString is null or empty");
        return true;
    }

    public String e(String str) {
        return kxz.e(this.f6412a, str);
    }

    public void d(String str, String str2) {
        kxz.i(this.f6412a, str, str2);
    }

    public String a(String str) {
        return kxz.a(this.f6412a, str);
    }

    public void e(String str, String str2) {
        kxz.g(this.f6412a, str, str2);
    }
}
