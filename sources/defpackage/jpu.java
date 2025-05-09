package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jpu extends jpx {
    public static DeviceInfo e(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            deviceInfo = deviceList.get(0);
        }
        LogUtil.a("HwGetAw70DeviceInfoUtil", "getConnectDeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo d(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("HwGetAw70DeviceInfoUtil", "getActiveDeviceInfo deviceInfoList is null.", str);
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("HwGetAw70DeviceInfoUtil", "getActiveDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }
}
