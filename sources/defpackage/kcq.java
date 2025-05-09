package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class kcq {
    private static Map<String, String> d = new ConcurrentHashMap(24);

    public static void b(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            LogUtil.h("HwMultiDeviceSettingUtils", "copyDeviceRelationMap deviceRelationMap is empty.");
            return;
        }
        d.clear();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            d.put(entry.getKey(), entry.getValue());
        }
    }

    public static void e(List<DeviceInfo> list) {
        if (bme.b()) {
            LogUtil.h("HwMultiDeviceSettingUtils", "setMultiDeviceState is not debug version.");
            return;
        }
        if (bkz.e(list)) {
            LogUtil.h("HwMultiDeviceSettingUtils", "setMultiDeviceState deviceInfoList is empty.");
            return;
        }
        String a2 = a(list);
        ArrayList arrayList = new ArrayList(5);
        List<DeviceInfo> d2 = jtd.b().d();
        for (DeviceInfo deviceInfo : list) {
            if (!kcw.a().a(deviceInfo)) {
                LogUtil.h("HwMultiDeviceSettingUtils", "setMultiDeviceState no support multi device connect.");
            } else if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
                LogUtil.h("HwMultiDeviceSettingUtils", "setMultiDeviceState deviceIdentify is empty.");
            } else if (!d(deviceInfo.getDeviceIdentify(), d2)) {
                LogUtil.h("HwMultiDeviceSettingUtils", "deviceInfo is not connected: ", CommonUtil.l(deviceInfo.getDeviceIdentify()));
            } else {
                String str = d.get(deviceInfo.getDeviceIdentify());
                String relationship = deviceInfo.getRelationship();
                if ("main_relationship".equalsIgnoreCase(str) && "assistant_relationship".equalsIgnoreCase(relationship)) {
                    arrayList.add(deviceInfo);
                }
                if ("assistant_relationship".equalsIgnoreCase(str) && "main_relationship".equalsIgnoreCase(relationship)) {
                    ReleaseLogUtil.e("DEVMGR_HwMultiDeviceSettingUtils", "setMultiDeviceState main device name: ", deviceInfo.getDeviceName());
                    deviceInfo.setDeviceConnectState(1);
                    jtc.c().b(deviceInfo, 1);
                    jub.b().e(deviceInfo, 1, a2, 0);
                }
            }
        }
        c(arrayList, a2);
    }

    private static void c(List<DeviceInfo> list, String str) {
        for (DeviceInfo deviceInfo : list) {
            ReleaseLogUtil.e("DEVMGR_HwMultiDeviceSettingUtils", "setMainToAssistantDeviceState assistant device name: ", deviceInfo.getDeviceName());
            jub.b().e(deviceInfo, 2, str, 0);
            deviceInfo.setDeviceConnectState(3);
            jtc.c().b(deviceInfo, 3);
        }
    }

    private static boolean d(String str, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && str.equals(deviceInfo.getDeviceIdentify())) {
                return deviceInfo.getDeviceConnectState() == 2;
            }
        }
        return false;
    }

    private static String a(List<DeviceInfo> list) {
        String str = "";
        for (DeviceInfo deviceInfo : list) {
            if ("main_relationship".equalsIgnoreCase(deviceInfo.getRelationship())) {
                str = deviceInfo.getDeviceName();
            }
        }
        ReleaseLogUtil.e("DEVMGR_HwMultiDeviceSettingUtils", "getMainDeviceName mainDeviceName: ", str);
        return str;
    }
}
