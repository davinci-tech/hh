package com.huawei.hwarkuix.oHBrigdePlugin.health.utils;

import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvs;
import defpackage.cwi;
import defpackage.eii;
import health.compact.a.Utils;

/* loaded from: classes9.dex */
public class DeviceUtil {
    private static final String TAG = "ArkUIX_DeviceUtil";

    public static boolean isSupportEcg() {
        return isSupportEcgCollection();
    }

    public static boolean isSupportEcgAnalysis() {
        if (isDeviceDisconnected()) {
            LogUtil.h(TAG, "isSupportEcgAnalysis device disconnected.");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            LogUtil.h(TAG, "isSupportEcgAnalysis deviceInfo is null.");
            return false;
        }
        return cwi.c(deviceInfo, 106);
    }

    public static boolean isSupportContinuousHeartRate() {
        if (isDeviceDisconnected()) {
            LogUtil.h(TAG, "isSupportContinuousHeartRate device disconnected.");
            return false;
        }
        DeviceCapability d = cvs.d();
        if (d != null) {
            return d.isSupportContinueHeartRate() || d.isSupportHeartRateEnable();
        }
        LogUtil.h(TAG, "isSupportContinuousHeartRate deviceCapability is null.");
        return false;
    }

    public static boolean isSupportPressAutoMonitor() {
        if (isDeviceDisconnected()) {
            LogUtil.h(TAG, "isSupportPressAutoMonitor device disconnected.");
            return false;
        }
        DeviceCapability d = cvs.d();
        if (d == null) {
            LogUtil.h(TAG, "isSupportPressAutoMonitor deviceCapability is null.");
            return false;
        }
        return d.isSupportPressAutoMonitor();
    }

    public static boolean isSupportEmotionAutoMonitor() {
        if (isDeviceDisconnected()) {
            LogUtil.h(TAG, "isSupportEmotionAutoMonitor device disconnected.");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            LogUtil.h(TAG, "isSupportEmotionAutoMonitor deviceInfo is null.");
            return false;
        }
        return cwi.c(deviceInfo, 206);
    }

    public static boolean isSupportEcgCollection() {
        if (Utils.o()) {
            LogUtil.h(TAG, "isSupportEcgCollection oversea.");
            return false;
        }
        if (!isDeviceDisconnected()) {
            DeviceCapability d = cvs.d();
            if (d != null && d.isSupportEcgAuth()) {
                return true;
            }
            LogUtil.h(TAG, "capability is null or not support ecg");
        }
        return false;
    }

    public static boolean isSupportEcgDiagram() {
        EcgFilterManager a2 = EcgFilterManager.a();
        if (eii.g("com.huawei.health.h5.ecgce") && a2.n()) {
            return a2.i();
        }
        return false;
    }

    public static boolean isDeviceDisconnected() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        return deviceInfo == null || deviceInfo.getDeviceConnectState() != 2;
    }
}
