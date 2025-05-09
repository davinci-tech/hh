package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.wearengine.capability.EnumWearEngineCapabilityItem;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class tog {
    public static boolean d(String str, EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        if (TextUtils.isEmpty(str) || enumWearEngineCapabilityItem == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "isSupport deviceId or item is empty");
            return false;
        }
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "WearEngine_WearEngineCapabilityUtils");
        if (deviceList.size() > 0) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deviceId is connected.");
            for (DeviceInfo deviceInfo2 : deviceList) {
                if (str.equals(deviceInfo2.getDeviceUdid()) || str.equals(deviceInfo2.getUuid())) {
                    deviceInfo = deviceInfo2;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getOtherConnectedDevice is invalid.");
            throw new IllegalStateException(String.valueOf(16));
        }
        if (str.equals(deviceInfo.getDeviceUdid()) || str.equals(deviceInfo.getUuid())) {
            return cwi.c(deviceInfo, enumWearEngineCapabilityItem.getValue());
        }
        LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deviceId not equal");
        throw new IllegalStateException(String.valueOf(16));
    }

    public static boolean c(String str, EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        if (TextUtils.isEmpty(str) || enumWearEngineCapabilityItem == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "isSupport deviceId or item is empty");
            return false;
        }
        if (d(str, EnumWearEngineCapabilityItem.SUPPORT_CHECK_DETAIL_CAPABILITY)) {
            return d(str, enumWearEngineCapabilityItem);
        }
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "WearEngine_WearEngineCapabilityUtils");
        if (deviceList.size() > 0) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deviceId is connected.");
            for (DeviceInfo deviceInfo2 : deviceList) {
                if (str.equals(deviceInfo2.getDeviceUdid()) || str.equals(deviceInfo2.getUuid())) {
                    deviceInfo = deviceInfo2;
                    break;
                }
            }
        }
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getOtherConnectedDevice is invalid.");
            throw new IllegalStateException(String.valueOf(16));
        }
        if (cwi.c(deviceInfo, enumWearEngineCapabilityItem.getValue())) {
            return true;
        }
        return b(deviceInfo, enumWearEngineCapabilityItem);
    }

    public static boolean a(DeviceInfo deviceInfo, EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        if (deviceInfo == null || enumWearEngineCapabilityItem == null) {
            LogUtil.b("WearEngine_WearEngineCapabilityUtils", "isSupportByDeviceInfo deviceInfo or capabilityItem is null ");
            return false;
        }
        byte[] a2 = cvx.a(deviceInfo.getExpandCapability());
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "isSupportByDeviceInfo expandCapability is " + deviceInfo.getExpandCapability());
        boolean a3 = CommonUtil.a(a2, enumWearEngineCapabilityItem.getValue());
        LogUtil.a("WearEngine_WearEngineCapabilityUtils", "modelName is " + deviceInfo.getDeviceModel() + ", isSupport  " + a3);
        return a3;
    }

    public static boolean b(DeviceInfo deviceInfo, EnumWearEngineCapabilityItem enumWearEngineCapabilityItem) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "isSupportOldCapability deviceInfo is null");
            return false;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "isSupportOldCapability deviceIdentify is empty");
            return false;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(3, "", "WearEngine_WearEngineCapabilityUtils");
        if (queryDeviceCapability != null && queryDeviceCapability.size() > 0 && queryDeviceCapability.containsKey(deviceIdentify)) {
            DeviceCapability deviceCapability = queryDeviceCapability.get(deviceIdentify);
            if (deviceCapability == null) {
                LogUtil.h("WearEngine_WearEngineCapabilityUtils", "isSupportOldCapability deviceCapability is null");
                return false;
            }
            if (enumWearEngineCapabilityItem.getValue() == 2) {
                return deviceCapability.isSupportHiWear();
            }
            if (enumWearEngineCapabilityItem.getValue() >= 3 && enumWearEngineCapabilityItem.getValue() <= 12) {
                return deviceCapability.isSupportWearEngine();
            }
            if (enumWearEngineCapabilityItem.getValue() == 13) {
                return deviceCapability.isSupportCheckDeviceSpace();
            }
        }
        return false;
    }

    public static String c(Map<String, DeviceCapability> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getCapabilityStringFromMap map is empty");
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, DeviceCapability> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(new Gson().toJson(entry.getValue()));
            sb.append("&");
        }
        String sb2 = sb.toString();
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "resultCapabilityString is ", sb2);
        if (TextUtils.isEmpty(sb2)) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getCapabilityStringFromMap resultCapabilityString is emtpty");
            return "";
        }
        return sb2.substring(0, sb2.length() - 1);
    }

    public static Map<String, DeviceCapability> e(String str) {
        HashMap hashMap = new HashMap(16);
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getCapabilityMapFromString capabilityStringList is empty");
            return hashMap;
        }
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "capabilityStringList: ", str);
        String[] split = str.split("&");
        if (split.length <= 0) {
            return hashMap;
        }
        for (String str2 : split) {
            String[] split2 = str2.split("=");
            if (split2.length == 2) {
                try {
                    hashMap.put(split2[0], (DeviceCapability) new Gson().fromJson(split2[1], DeviceCapability.class));
                } catch (JsonSyntaxException unused) {
                    LogUtil.b("WearEngine_WearEngineCapabilityUtils", "getCapabilityMapFromString JsonSyntaxException");
                }
            } else {
                LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getCapabilityMapFromString parse exception");
            }
        }
        return hashMap;
    }

    public static void b() {
        String e;
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(3, "", "WearEngine_WearEngineCapabilityUtils");
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "saveCapability map is empty");
            return;
        }
        boolean z = !"true".equals(b("key_safe_update_save_capability"));
        if (z) {
            e = b("key_save_capality");
        } else {
            e = cvx.e(b("key_save_capality"));
        }
        if (e == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getSharedPreference found exception");
            return;
        }
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "saveCapability: ", e);
        Map<String, DeviceCapability> e2 = e(e);
        for (Map.Entry<String, DeviceCapability> entry : queryDeviceCapability.entrySet()) {
            e2.put(entry.getKey(), entry.getValue());
        }
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearEngine_WearEngineCapabilityUtils");
        if (deviceList != null && deviceList.size() > 0) {
            HashMap hashMap = new HashMap(16);
            for (DeviceInfo deviceInfo : deviceList) {
                hashMap.put(deviceInfo.getDeviceIdentify(), deviceInfo);
            }
            Iterator<Map.Entry<String, DeviceCapability>> it = e2.entrySet().iterator();
            while (it.hasNext()) {
                if (!hashMap.containsKey(it.next().getKey())) {
                    it.remove();
                }
            }
        }
        String c = c(e2);
        c("key_save_capality", cvx.c(c), new StorageParams());
        if (z) {
            c("key_safe_update_save_capability", "true", new StorageParams());
        }
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "toBeSavedCapabityStr : ", c);
    }

    public static void e(DeviceInfo deviceInfo) {
        String e;
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deleteDeviceFromStorage deviceInfo is null");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        boolean z = !"true".equals(b("key_safe_update_save_capability"));
        if (z) {
            e = b("key_save_capality");
        } else {
            e = cvx.e(b("key_save_capality"));
        }
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deleteDeviceFromStorage savedCapabilityStr is empty");
            return;
        }
        Map<String, DeviceCapability> e2 = e(e);
        if (e2.isEmpty()) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "deleteDeviceFromStorage saveCapabilityMap is empty");
            return;
        }
        if (e2.containsKey(deviceIdentify)) {
            e2.remove(deviceIdentify);
        }
        String c = c(e2);
        LogUtil.c("WearEngine_WearEngineCapabilityUtils", "deleteDeviceFrdomStorage toBeSavedCapabityStr : ", c);
        c("key_save_capality", cvx.c(c), new StorageParams());
        if (z) {
            c("key_safe_update_save_capability", "true", new StorageParams());
        }
    }

    public static boolean a(DeviceInfo deviceInfo) {
        DeviceCapability deviceCapability;
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "checkHiWearCapabilityFromStorage device is null");
            return false;
        }
        String e = cvx.e(b("key_save_capality"));
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "checkHiWearCapabilityFromStorage saveCapability is empty");
            return false;
        }
        Map<String, DeviceCapability> e2 = e(e);
        if (e2.isEmpty()) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "checkHiWearCapabilityFromStorage savedCapabilityMap is empty");
            return false;
        }
        if (!e2.containsKey(deviceInfo.getDeviceIdentify()) || (deviceCapability = e2.get(deviceInfo.getDeviceIdentify())) == null) {
            return false;
        }
        return deviceCapability.isSupportHiWear();
    }

    private static String b(String str) {
        return SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(53), str);
    }

    private static void c(String str, String str2, StorageParams storageParams) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(53), str, str2, storageParams);
    }

    public static int b(DeviceInfo deviceInfo, boolean z) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getP2pCapability deviceInfo is null");
            return 2;
        }
        if (!b(deviceInfo, EnumWearEngineCapabilityItem.POINT_TO_POINT_PING_AND_SEND_BYTES_ENUM)) {
            return 1;
        }
        if (z) {
            return 0;
        }
        if (tqy.d(deviceInfo.getDeviceModel())) {
            return !b(deviceInfo, EnumWearEngineCapabilityItem.QUERY_DEVICE_APP_INSTALL_INFO_ENUM) ? 1 : 0;
        }
        return b(deviceInfo);
    }

    public static int b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getP2pCapabilityNew deviceInfo is null");
            return 2;
        }
        if (a(deviceInfo, EnumWearEngineCapabilityItem.QUERY_DEVICE_APP_INSTALL_INFO_ENUM)) {
            return 0;
        }
        return h(deviceInfo);
    }

    private static int h(DeviceInfo deviceInfo) {
        String deviceModel = deviceInfo.getDeviceModel();
        return (TextUtils.isEmpty(deviceModel) || !tqy.a(deviceModel)) ? 1 : 0;
    }

    public static int c(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getMonitorCapability device is null");
            return 2;
        }
        tof tofVar = new tof(deviceInfo, EnumWearEngineCapabilityItem.SPORT_STATUS_QUERY_AND_REPORT_ENUM);
        tofVar.d(EnumWearEngineCapabilityItem.LOW_POWER_REPORT_ENUM).d(EnumWearEngineCapabilityItem.POWER_STATUS_QUERY_ENUM).d(EnumWearEngineCapabilityItem.WEAR_STATUS_QUERY_AND_REPORT_ENUM).d(EnumWearEngineCapabilityItem.SLEEP_STATUS_QUERY_AND_REPORT_ENUM).d(EnumWearEngineCapabilityItem.CHARGING_STATUS_QUERY_AND_REPORT_ENUM).d(EnumWearEngineCapabilityItem.HEART_RATE_ALARM_REPORT_ENUM).d(EnumWearEngineCapabilityItem.QUERY_DEVICE_AVAILABLE_SPACE_ENUM);
        if (tofVar.a() == 0) {
            return 0;
        }
        return h(deviceInfo);
    }

    public static int d(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getNotifyCapability device is null");
            return 2;
        }
        if (a(deviceInfo, EnumWearEngineCapabilityItem.SEND_NOTIFY_TO_WATCH_ENUM)) {
            return 0;
        }
        return h(deviceInfo);
    }

    public static int i(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return !a(deviceInfo, EnumWearEngineCapabilityItem.SUPPORT_SENSOR_ENUM) ? 0 : 1;
        }
        LogUtil.h("WearEngine_WearEngineCapabilityUtils", "getSensorCapability device is null");
        return 2;
    }
}
