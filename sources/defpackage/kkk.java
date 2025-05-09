package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CloudImpl;
import health.compact.a.GRSManager;
import health.compact.a.ReleaseLogUtil;

/* loaded from: classes5.dex */
public class kkk {
    public static void a(final Context context, final DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("HwWearableUtil", "sendCountryCode btDeviceInfo is null");
            return;
        }
        if (deviceInfo.getDeviceConnectState() != 2) {
            LogUtil.h("HwWearableUtil", "sendCountryCode device not connect");
        } else if (!cwi.c(deviceInfo, 30)) {
            LogUtil.h("HwWearableUtil", "sendCountryCode no support NewCapabilityConstants.DEVICE_COUNTRY_CODE");
        } else {
            jdx.b(new Runnable() { // from class: kkk.3
                @Override // java.lang.Runnable
                public void run() {
                    String str;
                    String commonCountryCode = GRSManager.a(context).getCommonCountryCode();
                    if (TextUtils.isEmpty(commonCountryCode)) {
                        LogUtil.h("HwWearableUtil", "countryCode is null");
                        return;
                    }
                    String c = cvx.c(commonCountryCode);
                    String str2 = cvx.e(1) + cvx.d(cvx.a(c).length) + c;
                    if (cwi.c(deviceInfo, 170)) {
                        String e = cvx.e(CloudImpl.c(BaseApplication.e()).getSiteIdByCountry(commonCountryCode));
                        str = cvx.e(2) + cvx.d(cvx.a(e).length) + e;
                        LogUtil.a("HwWearableUtil", "siteId support NewCapabilityConstants.DEVICE_SUPPORT_SITE_ID");
                    } else {
                        LogUtil.a("HwWearableUtil", "device not support siteId");
                        str = "";
                    }
                    String str3 = str2 + str;
                    DeviceCommand deviceCommand = new DeviceCommand();
                    deviceCommand.setServiceID(26);
                    deviceCommand.setCommandID(10);
                    deviceCommand.setDataLen(cvx.a(str3).length);
                    deviceCommand.setDataContent(cvx.a(str3));
                    deviceCommand.setmIdentify(deviceInfo.getDeviceIdentify());
                    LogUtil.a("HwWearableUtil", "sendCountryCode() : countryCodeTypeLengthValue", str3);
                    ReleaseLogUtil.b("DEVMGR_HwWearableUtil", "sendCountryCode finish.");
                    jsz.b(context).sendDeviceData(deviceCommand);
                }
            });
        }
    }
}
