package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class izm {
    private static ConcurrentHashMap b = new ConcurrentHashMap(16);

    public static void a() {
        b.put("currentConnectedDevices", new ArrayList(10));
        b.put("reconnectDevices", new ArrayList(10));
    }

    public static void b(String str) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "setReconnectDeviceDisable enter ", Boolean.valueOf(TextUtils.isEmpty(str)));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = (ArrayList) b.get("reconnectDevices");
        if (arrayList == null || arrayList.isEmpty()) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "putDeviceInfoIntoMap deviceInfos is null");
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (str.equals(deviceInfo.getDeviceIdentify())) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "device exist, set device active disable");
                deviceInfo.setDeviceActiveState(0);
            }
        }
    }

    public static void a(String str) {
        LogUtil.c("ConnectedAndReconnectDeviceMgr", "setReconnectDeviceEnable enter ,", Boolean.valueOf(TextUtils.isEmpty(str)));
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArrayList arrayList = b.get("reconnectDevices") instanceof ArrayList ? (ArrayList) b.get("reconnectDevices") : null;
        if (arrayList == null || arrayList.isEmpty()) {
            LogUtil.a("ConnectedAndReconnectDeviceMgr", "setReconnectDeviceEnable deviceList is empty.");
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (str.equals(deviceInfo.getDeviceIdentify())) {
                LogUtil.c("ConnectedAndReconnectDeviceMgr", "setReconnectDeviceEnable device exist, set device active enable.");
                deviceInfo.setDeviceActiveState(1);
            }
        }
    }

    public static void e(String str, DeviceInfo deviceInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "putDeviceInfoIntoMap enter");
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "putDeviceInfoIntoMap deviceInfos == null");
            return;
        }
        if (arrayList.isEmpty()) {
            LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "putDeviceInfoIntoMap infos is empty");
            arrayList.add(deviceInfo);
            b.put(str, arrayList);
            bio.b(str, arrayList);
            return;
        }
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            if (((DeviceInfo) arrayList.get(i)).getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "device exist, update device info");
                arrayList.remove(i);
                arrayList.add(deviceInfo);
                bio.b(str, arrayList);
                z = true;
            }
        }
        if (z) {
            return;
        }
        arrayList.add(deviceInfo);
        b.put(str, arrayList);
        bio.b(str, arrayList);
    }

    public static DeviceInfo e(String str, String str2) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "Enter getCorrespondingInfo");
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "deviceInfos is null");
            return null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            DeviceInfo deviceInfo = (DeviceInfo) it.next();
            if (deviceInfo.getDeviceIdentify().equals(str2)) {
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "getCorrespondingInfo success");
                return deviceInfo;
            }
        }
        return null;
    }

    public static void d(String str, DeviceInfo deviceInfo) {
        LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "Enter removeCorrespondingDevice");
        ArrayList arrayList = (ArrayList) b.get(str);
        if (arrayList == null) {
            LogUtil.a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "putDeviceInfoIntoMap deviceInfos == null");
            return;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (((DeviceInfo) arrayList.get(i)).getDeviceIdentify().equals(deviceInfo.getDeviceIdentify())) {
                arrayList.remove(i);
                LogUtil.c(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1, "ConnectedAndReconnectDeviceMgr", "removeCorrespondingDevice success");
                b.put(str, arrayList);
                bio.b(str, arrayList);
                return;
            }
        }
    }

    public static ArrayList<DeviceInfo> c(String str) {
        if (b.get(str) instanceof ArrayList) {
            return (ArrayList) b.get(str);
        }
        return new ArrayList<>();
    }
}
