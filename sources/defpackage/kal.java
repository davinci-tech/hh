package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kal {
    public static String a() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "DeviceUtils");
        if (deviceList.size() == 0) {
            LogUtil.h("DeviceUtils", "getContactedDeviceMac: the object of HwDeviceMgr is null. ");
            return "00:00:00:00:00:00";
        }
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("DeviceUtils", "getContactedDeviceMac: the object of DeviceInfo is null. ");
            return "00:00:00:00:00:00";
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        return "contact_sync_" + kak.b(TextUtils.isEmpty(deviceIdentify) ? "00:00:00:00:00:00" : deviceIdentify);
    }
}
