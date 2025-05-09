package defpackage;

import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevicemgr.R$array;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.Utils;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kjv {
    private kjv() {
    }

    public void a(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("AppMarketFeaturesUtil", "getAppMarketSupportFeatures enter");
        if (iBaseResponseCallback != null) {
            ThreadPoolManager.d().d("AppMarketFeaturesUtil", new Runnable() { // from class: kjv.2
                @Override // java.lang.Runnable
                public void run() {
                    kjv.this.e(iBaseResponseCallback);
                }
            });
        } else {
            LogUtil.h("AppMarketFeaturesUtil", "getAppMarketSupportFeatures callback is null");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final IBaseResponseCallback iBaseResponseCallback) {
        b(new IBaseResponseCallback() { // from class: kjv.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("AppMarketFeaturesUtil", "checkShowAppMarketFeatures showAppMarket errorCode:", Integer.valueOf(i));
                if (i == 1) {
                    iBaseResponseCallback.d(1, null);
                } else {
                    iBaseResponseCallback.d(0, null);
                }
            }
        });
    }

    private void b(IBaseResponseCallback iBaseResponseCallback) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "AppMarketFeaturesUtil");
        if (deviceList.size() == 0) {
            LogUtil.h("AppMarketFeaturesUtil", "fetchAppMarketCapability, deviceInfo is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        DeviceInfo deviceInfo = deviceList.get(0);
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "AppMarketFeaturesUtil");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("AppMarketFeaturesUtil", "fetchAppMarketCapability, deviceCapabilityHashMaps is empty");
            iBaseResponseCallback.d(0, null);
            return;
        }
        DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
        if (deviceCapability == null) {
            LogUtil.h("AppMarketFeaturesUtil", "fetchAppMarketCapability, deviceCapability is null");
            iBaseResponseCallback.d(0, null);
            return;
        }
        if (deviceCapability.isSupportMarketFace() && !Utils.o()) {
            LogUtil.a("AppMarketFeaturesUtil", "fetchAppMarketCapability, deviceCapability isSupportMarketFace and not oversea");
            iBaseResponseCallback.d(1, null);
        } else if (c()) {
            iBaseResponseCallback.d(0, null);
        } else if (d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010)) || !deviceCapability.isSupportMarketParams()) {
            iBaseResponseCallback.d(0, null);
        } else {
            LogUtil.a("AppMarketFeaturesUtil", "fetchAppMarketCapability, deviceCapability isSupportMarketParams");
            iBaseResponseCallback.d(1, null);
        }
    }

    private boolean c() {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo) || "0".equals(accountInfo)) {
            return true;
        }
        LogUtil.a("AppMarketFeaturesUtil", "isNotLogin, userId is normal value, so has login");
        return false;
    }

    private boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("AppMarketFeaturesUtil", "countryCode is empty");
            return true;
        }
        for (String str2 : BaseApplication.getContext().getResources().getStringArray(R$array.not_support_app_market_code)) {
            if (TextUtils.equals(str2, str)) {
                LogUtil.h("AppMarketFeaturesUtil", "fetchAppMarketCapability, countryCode is not support");
                return true;
            }
        }
        return false;
    }

    public static kjv d() {
        return e.e;
    }

    static class e {
        private static final kjv e = new kjv();
    }
}
