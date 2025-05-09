package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hms.common.internal.constant.AuthInternalPickerConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmgr.filedownload.PullListener;
import health.compact.a.CommonUtil;
import health.compact.a.EzPluginManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.util.List;

/* loaded from: classes3.dex */
public class cpe {
    public static void c(String str, PullListener pullListener) {
        EzPluginManager.a().a(str, b(), "?deviceType=bodyFatScales", pullListener);
    }

    public static void e(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || iBaseResponseCallback == null) {
            LogUtil.h("ResourceManagerUtil", "init updateResourceByProductId is error");
        } else {
            EzPluginManager.a().b(new PullListener() { // from class: cpi
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    cpe.b(IBaseResponseCallback.this, str, msqVar, msoVar);
                }
            });
        }
    }

    static /* synthetic */ void b(final IBaseResponseCallback iBaseResponseCallback, final String str, msq msqVar, mso msoVar) {
        if (msoVar == null) {
            LogUtil.h("ResourceManagerUtil", "updateResourceByProductId pullResult is null");
            iBaseResponseCallback.d(-1, AuthInternalPickerConstant.UNKOWN_ERROR);
            return;
        }
        int i = msoVar.i();
        if (i == 1) {
            EzPluginManager.a().e();
            iBaseResponseCallback.d(0, msoVar);
            EzPluginManager.a().b(str, new PullListener() { // from class: cph
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar2, mso msoVar2) {
                    cpe.c(str, iBaseResponseCallback, msqVar2, msoVar2);
                }
            });
        } else {
            LogUtil.a("ResourceManagerUtil", "fetchStatus is:", Integer.valueOf(i));
            iBaseResponseCallback.d(i, msoVar);
        }
    }

    static /* synthetic */ void c(String str, IBaseResponseCallback iBaseResponseCallback, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        LogUtil.a("ResourceManagerUtil", "download status:", Integer.valueOf(i));
        if (i == 20) {
            d(str, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(-11, msoVar);
        }
    }

    private static void d(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        String e = e();
        if (TextUtils.isEmpty(e)) {
            iBaseResponseCallback.d(-1, "resourceDomainName is empty");
        } else {
            EzPluginManager.a().a(str, e, new PullListener() { // from class: cpj
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    cpe.a(str, iBaseResponseCallback, msqVar, msoVar);
                }
            });
        }
    }

    static /* synthetic */ void a(String str, IBaseResponseCallback iBaseResponseCallback, msq msqVar, mso msoVar) {
        int i = msoVar.i();
        LogUtil.a("ResourceManagerUtil", "downLoadDescriptionJsonFile onPullingChange uuid:", str, ", result:", Integer.valueOf(msoVar.i()));
        if (msoVar.i() == 1) {
            a(str, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(i, "download description fail");
        }
    }

    private static void a(String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || iBaseResponseCallback == null) {
            LogUtil.h("ResourceManagerUtil", "init downloadSingleResource is error");
            return;
        }
        String e = e();
        if (TextUtils.isEmpty(e)) {
            iBaseResponseCallback.d(-1, "resourceDomainName is empty");
        } else {
            EzPluginManager.a().b(str, e, new PullListener() { // from class: cpg
                @Override // com.huawei.pluginmgr.filedownload.PullListener
                public final void onPullingChange(msq msqVar, mso msoVar) {
                    cpe.e(IBaseResponseCallback.this, msqVar, msoVar);
                }
            });
        }
    }

    static /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, msq msqVar, mso msoVar) {
        if (msqVar == null || msoVar == null) {
            LogUtil.a("ResourceManagerUtil", "onPullingChange param is null");
            iBaseResponseCallback.d(-1, AuthInternalPickerConstant.UNKOWN_ERROR);
            return;
        }
        int i = msoVar.i();
        LogUtil.a("ResourceManagerUtil", "downloadOneZipFile result status = ", Integer.valueOf(i), ",and uuid = ", msqVar.o());
        if (i == 1) {
            iBaseResponseCallback.d(1, msoVar);
        } else if (i == 0) {
            iBaseResponseCallback.d(0, msoVar);
        } else {
            iBaseResponseCallback.d(i, msoVar);
        }
    }

    private static String e() {
        return (CommonUtil.ag(BaseApplication.getContext()) || a()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta";
    }

    private static String b() {
        String str;
        if ((!Utils.o() || CommonUtil.cc()) && msr.c.containsKey("HDK_WEIGHT")) {
            str = CommonUtil.bv() ? msr.f15154a.get("HDK_WEIGHT") : msr.b.get("HDK_WEIGHT");
        } else {
            str = (CommonUtil.bv() || a()) ? "com.huawei.health_HwWear_deviceConfig" : "com.huawei.health_HwWear_deviceConfigBeta";
        }
        LogUtil.a("ResourceManagerUtil", "getScaleConfigServiceName is ", str);
        return str;
    }

    private static boolean a() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "developeroptions", "developerswitch");
        String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "APP_TEST_SITE", "app_test_site_type");
        LogUtil.a("ResourceManagerUtil", "deviceSite:", b2);
        return CommonUtil.as() && "1".equals(b) && "release".equals(b2);
    }

    public static boolean c() {
        List<cve> deviceList = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceList();
        LogUtil.a("ResourceManagerUtil", "checkIndexAllResourcesExists deviceInfos:", Boolean.valueOf(koq.b(deviceList)));
        if (!koq.b(deviceList)) {
            return false;
        }
        LogUtil.h("ResourceManagerUtil", "DevicePluginInfoBean is null");
        return true;
    }
}
