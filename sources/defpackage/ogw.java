package defpackage;

import android.os.Handler;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.trade.PayApi;
import com.huawei.trade.datatype.DeviceBenefits;
import com.huawei.trade.datatype.DeviceInboxInfo;
import com.huawei.trade.datatype.DevicePerfPurchaseInfo;
import com.huawei.trade.datatype.OtherServiceInfo;
import defpackage.njp;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ogw<T> {
    private List<T> c = new ArrayList(16);
    private boolean e;

    public List<T> e(int i, Object obj, DeviceInfo deviceInfo) {
        this.c.clear();
        this.e = false;
        if (i != 0) {
            LogUtil.h("VipViewPagerSetting", "handleVipInfoResult errorCode is ", Integer.valueOf(i));
            return this.c;
        }
        LogUtil.a("VipViewPagerSetting", "handleVipInfoResult GetVipInfoCallback success, objData is ", obj);
        DeviceBenefits deviceBenefits = obj instanceof DeviceBenefits ? (DeviceBenefits) obj : null;
        if (deviceBenefits == null) {
            LogUtil.h("VipViewPagerSetting", "handleVipInfoResult deviceBenefits is null");
            return this.c;
        }
        List<DeviceInboxInfo> inboxInfos = deviceBenefits.getInboxInfos();
        if (inboxInfos == null || inboxInfos.isEmpty()) {
            LogUtil.h("VipViewPagerSetting", "handleVipInfoResult inboxInfoList is null");
            c(deviceBenefits, deviceInfo);
            return this.c;
        }
        LogUtil.a("VipViewPagerSetting", "handleVipInfoResult inboxInfoList size is ", Integer.valueOf(inboxInfos.size()));
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        for (DeviceInboxInfo deviceInboxInfo : inboxInfos) {
            LogUtil.a("VipViewPagerSetting", "handleVipInfoResult deviceInboxInfo is :", Integer.valueOf(deviceInboxInfo.getActiveStatus()), ", time:", Boolean.valueOf(ogj.b(deviceInboxInfo.getEffectiveStartTime(), deviceInboxInfo.getEffectiveEndTime())));
            if (SharedPreferenceManager.a(Integer.toString(10008), accountInfo + "key_ignore_inbox_info" + deviceInboxInfo.getDeviceInboxId(), false)) {
                LogUtil.a("VipViewPagerSetting", "allDevicePurchaseList DeviceInboxId is: ", deviceInboxInfo.getDeviceInboxId());
            } else if (ogj.b(deviceInboxInfo.getEffectiveStartTime(), deviceInboxInfo.getEffectiveEndTime())) {
                if (deviceInboxInfo.getActiveStatus() == 1) {
                    if (gpp.b(deviceInboxInfo) != null) {
                        this.c.add(deviceInboxInfo);
                        d(deviceInboxInfo.getDeviceInboxId());
                    }
                } else if (deviceInboxInfo.getActiveStatus() == 2) {
                    OtherServiceInfo c = c(deviceInboxInfo);
                    if (!this.e && c != null && a(c, deviceInfo)) {
                        this.c.add(deviceInboxInfo);
                        d(deviceInboxInfo.getDeviceInboxId());
                        this.e = true;
                    }
                }
            }
        }
        c(deviceBenefits, deviceInfo);
        return this.c;
    }

    public void cZY_(Handler handler, List<T> list) {
        if (handler == null) {
            LogUtil.h("VipViewPagerSetting", "sendMessageUpdateUi handler is null");
        } else if (list == null || list.isEmpty()) {
            LogUtil.h("VipViewPagerSetting", "sendMessageUpdateUi allDataInfoList is null");
            handler.sendMessage(ogj.cZz_(-1));
        } else {
            handler.sendMessage(ogj.cZz_(0));
        }
    }

    private void c(DeviceBenefits deviceBenefits, DeviceInfo deviceInfo) {
        List<DevicePerfPurchaseInfo> perfPurchaseInfos = deviceBenefits.getPerfPurchaseInfos();
        if (perfPurchaseInfos == null || perfPurchaseInfos.isEmpty()) {
            LogUtil.h("VipViewPagerSetting", "addAllDataInfo devicePurchaseInfoList is null");
        } else {
            LogUtil.a("VipViewPagerSetting", "addAllDataInfo devicePurchaseInfoList size is ", Integer.valueOf(perfPurchaseInfos.size()));
            d(perfPurchaseInfos, deviceInfo);
        }
    }

    private void d(List<DevicePerfPurchaseInfo> list, DeviceInfo deviceInfo) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        for (DevicePerfPurchaseInfo devicePerfPurchaseInfo : list) {
            LogUtil.a("VipViewPagerSetting", "allDevicePurchaseList devicePerfPurchaseInfo ", Integer.valueOf(devicePerfPurchaseInfo.getActiveStatus()), ", time:", Boolean.valueOf(ogj.b(devicePerfPurchaseInfo.getEffectiveStartTime(), devicePerfPurchaseInfo.getEffectiveEndTime())));
            if (SharedPreferenceManager.a(Integer.toString(10008), accountInfo + devicePerfPurchaseInfo.getDevicePerfPurchaseId(), false)) {
                LogUtil.a("VipViewPagerSetting", "allDevicePurchaseList DevicePerfPurchaseId is: ", devicePerfPurchaseInfo.getDevicePerfPurchaseId());
            } else if (ogj.b(devicePerfPurchaseInfo.getEffectiveStartTime(), devicePerfPurchaseInfo.getEffectiveEndTime())) {
                if (devicePerfPurchaseInfo.getActiveStatus() == 1) {
                    if (gpp.a(devicePerfPurchaseInfo) != null) {
                        this.c.add(devicePerfPurchaseInfo);
                        d(devicePerfPurchaseInfo.getDevicePerfPurchaseId());
                    }
                } else if (devicePerfPurchaseInfo.getActiveStatus() == 2) {
                    OtherServiceInfo c = c(devicePerfPurchaseInfo);
                    if (!this.e && c != null && a(c, deviceInfo)) {
                        this.c.add(devicePerfPurchaseInfo);
                        d(devicePerfPurchaseInfo.getDevicePerfPurchaseId());
                        this.e = true;
                    }
                }
            }
        }
    }

    private boolean a(OtherServiceInfo otherServiceInfo, DeviceInfo deviceInfo) {
        PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
        if (payApi == null) {
            LogUtil.h("VipViewPagerSetting", "isWatchPerfPurchaseReceived payApi == null");
            return false;
        }
        final String themeProductCode = otherServiceInfo.getThemeProductCode();
        if (TextUtils.isEmpty(themeProductCode)) {
            LogUtil.h("VipViewPagerSetting", "isWatchPerfPurchaseReceived themeProductCode isEmpty");
            return false;
        }
        njp a2 = a(deviceInfo, "2", themeProductCode);
        if (a2 == null) {
            LogUtil.h("VipViewPagerSetting", "isWatchPerfPurchaseReceived themeProductQueryParam == null");
            return false;
        }
        final boolean[] zArr = {false};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        payApi.productQueryByType(a2, new IBaseResponseCallback() { // from class: ogw.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                try {
                    LogUtil.a("VipViewPagerSetting", "productQueryByType onResponse errorCode:", Integer.valueOf(i));
                    if (i == 0) {
                        boolean e = e(obj);
                        LogUtil.a("VipViewPagerSetting", "productQueryByType isReceived:", Boolean.valueOf(e));
                        zArr[0] = e;
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }

            private boolean e(Object obj) {
                if (!koq.e(obj, njo.class)) {
                    LogUtil.h("VipViewPagerSetting", "isReceived not isListTypeMatch");
                    return false;
                }
                List<njo> list = (List) obj;
                LogUtil.a("VipViewPagerSetting", "isReceived list.size:", Integer.valueOf(list.size()));
                for (njo njoVar : list) {
                    if (themeProductCode.equalsIgnoreCase(njoVar.b())) {
                        String e = njoVar.e();
                        LogUtil.a("VipViewPagerSetting", "productQueryByType isReceivedByCondition:", e);
                        return "2".equals(e);
                    }
                }
                return false;
            }
        });
        try {
            countDownLatch.await(15L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            LogUtil.b("VipViewPagerSetting", "productQueryByType InterruptedException:", e);
        }
        return zArr[0];
    }

    private njp a(DeviceInfo deviceInfo, String str, String str2) {
        String str3;
        cvc pluginInfoByUuid = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getPluginInfoByUuid(jfu.j(deviceInfo.getProductType()));
        if (pluginInfoByUuid == null || pluginInfoByUuid.f() == null) {
            str3 = "";
        } else {
            String f = pluginInfoByUuid.f().f();
            LogUtil.a("VipViewPagerSetting", "deviceInfoToThemeProductQueryParam brand:", f);
            if (String.valueOf(1).equals(f)) {
                str3 = "1";
            } else {
                str3 = String.valueOf(2).equals(f) ? "3" : "4";
            }
        }
        return new njp.b().e(deviceInfo.getDeviceModel()).b(deviceInfo.getUuid()).c("6").a(str3).g(str).d(str2).d();
    }

    private void d(String str) {
        String e = SharedPreferenceManager.e(Integer.toString(10008), "equity_click_red_dot_status", "");
        if (e.contains(str)) {
            return;
        }
        SharedPreferenceManager.c(Integer.toString(10008), "equity_click_red_dot_status", e + str + ",");
    }

    public static OtherServiceInfo c(Object obj) {
        List<OtherServiceInfo> otherServiceList;
        if (obj instanceof DeviceInboxInfo) {
            otherServiceList = ((DeviceInboxInfo) obj).getOtherServiceList();
        } else {
            otherServiceList = obj instanceof DevicePerfPurchaseInfo ? ((DevicePerfPurchaseInfo) obj).getOtherServiceList() : null;
        }
        if (koq.c(otherServiceList)) {
            for (OtherServiceInfo otherServiceInfo : otherServiceList) {
                if (otherServiceInfo.getServiceType() == 3) {
                    return otherServiceInfo;
                }
            }
        }
        return null;
    }
}
