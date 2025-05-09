package com.huawei.hwdevice.phoneprocess.mgr.exercise.utils;

import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvt;
import defpackage.jsz;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class HwExerciseDeviceUtil {
    private static final String TAG = "HwExerciseDeviceUtil";

    private HwExerciseDeviceUtil() {
    }

    public static int getConnectedDeviceType() {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, TAG);
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (next.getDeviceActiveState() == 1) {
                deviceInfo = next;
                break;
            }
        }
        int productType = deviceInfo != null ? deviceInfo.getProductType() : -1;
        LogUtil.a(TAG, "getConnectedDeviceType() deviceType: ", Integer.valueOf(productType), " deviceList.size(): ", Integer.valueOf(deviceList.size()));
        return productType;
    }

    public static DeviceCapability getCapability() {
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(4, "", TAG);
        DeviceCapability deviceCapability = null;
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            LogUtil.h(TAG, "getCapability deviceCapabilityHashMaps is null or empty");
            return null;
        }
        Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
        while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
        }
        return deviceCapability;
    }

    public static DeviceCapability getSingleDeviceCapability(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.h(TAG, "getCapability deviceInfo is null");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), TAG);
        if (queryDeviceCapability == null || queryDeviceCapability.isEmpty()) {
            return null;
        }
        return queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
    }

    public static DeviceInfo getCurrentDeviceInfo() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, TAG);
        if (deviceList.isEmpty()) {
            LogUtil.h(TAG, "getCurrentDeviceInfo deviceInfoList is empty");
            return null;
        }
        LogUtil.a(TAG, "getCurrentDeviceInfo() deviceInfoList.size(): ", Integer.valueOf(deviceList.size()));
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo.getDeviceActiveState() == 1 && !cvt.c(deviceInfo.getProductType())) {
                LogUtil.h(TAG, "getCurrentDeviceInfo() deviceInfo = ", deviceInfo);
                return deviceInfo;
            }
        }
        return null;
    }

    public static String getCurrentDeviceId() {
        DeviceInfo currentDeviceInfo = getCurrentDeviceInfo();
        return currentDeviceInfo != null ? currentDeviceInfo.getSecurityDeviceId() : "";
    }

    public static String getCurrentDeviceMac() {
        DeviceInfo currentDeviceInfo = getCurrentDeviceInfo();
        return currentDeviceInfo != null ? currentDeviceInfo.getDeviceIdentify() : "";
    }
}
