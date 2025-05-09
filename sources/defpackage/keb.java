package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class keb {
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
        LogUtil.a("SyncDataDeviceUtil", "getConnectDeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo b(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_MAIN_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("SyncDataDeviceUtil", "getActiveDeviceInfo() deviceInfoList is null.", str);
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
        LogUtil.a("SyncDataDeviceUtil", "getActiveDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo c(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("SyncDataDeviceUtil", "getActiveDeviceInfo deviceInfoList is null.", str);
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
        LogUtil.a("SyncDataDeviceUtil", "getActiveDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo c(String str, String str2) {
        DeviceInfo deviceInfo = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("SyncDataDeviceUtil", "getDefiniteActiveDeviceInfo id is empty");
            return null;
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, str2);
        if (deviceList == null || deviceList.isEmpty()) {
            LogUtil.h("SyncDataDeviceUtil", "getDefiniteActiveDeviceInfo deviceInfoList is null.", str2);
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceActiveState() == 1 && str.equals(next.getDeviceIdentify())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("SyncDataDeviceUtil", "getCertainDeviceInfo deviceInfo ", deviceInfo, " , tag is ", str2);
        return deviceInfo;
    }

    public static DeviceCapability e() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", "SyncDataDeviceUtil");
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability;
    }

    public static String b() {
        DeviceInfo b = b("SyncDataDeviceUtil");
        if (b == null) {
            return "";
        }
        return b.getSecurityUuid() + "#ANDROID21";
    }

    public static boolean c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return true;
        }
        int productType = deviceInfo.getProductType();
        boolean z = jpp.d(productType) != 0;
        if (!z) {
            e(productType);
        }
        return z;
    }

    private static void e(int i) {
        LogUtil.h("SyncDataDeviceUtil", "device list info is wrong, can not get right device info now, please check download device info from net");
        HashMap hashMap = new HashMap(16);
        hashMap.put("status", Integer.valueOf(i));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_DEVICE_HI_TYPE_ERROR_1040065.value(), hashMap, 0);
    }

    public static int a() {
        DeviceInfo d = d("SyncDataDeviceUtil");
        if (d == null) {
            LogUtil.h("SyncDataDeviceUtil", "getStressDevNo deviceInfo is null");
            return 0;
        }
        return d.getProductType();
    }
}
