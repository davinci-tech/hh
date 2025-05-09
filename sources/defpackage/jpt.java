package defpackage;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jpt extends jpx {
    public static DeviceInfo d(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
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
        LogUtil.a("HwGetDeviceInfoUtil", "getConnectDeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo a(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("HwGetDeviceInfoUtil", "getActiveDeviceInfo() deviceInfoList is null.", str);
            return null;
        }
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
        LogUtil.a("HwGetDeviceInfoUtil", "getActiveDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static List<DeviceInfo> e() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwGetDeviceInfoUtil");
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("HwGetDeviceInfoUtil", "getCurrentNotAw70DeviceInfoList deviceInfoList is null");
            return deviceList;
        }
        ArrayList arrayList = new ArrayList(10);
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }
}
