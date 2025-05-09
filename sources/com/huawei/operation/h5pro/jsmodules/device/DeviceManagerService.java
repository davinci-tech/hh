package com.huawei.operation.h5pro.jsmodules.device;

import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.service.anotation.H5ProCallback;
import com.huawei.health.h5pro.service.anotation.H5ProMethod;
import com.huawei.health.h5pro.service.anotation.H5ProService;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import defpackage.cun;
import defpackage.cwi;
import java.util.Objects;

@H5ProService(name = DeviceManagerService.TAG, users = {"9ea3f06fe2dea3e2c7c21ea1ba1e07c370a786c68417a4a96cb986576883e10f"})
/* loaded from: classes.dex */
public class DeviceManagerService {
    private static final String TAG = "DeviceManager";

    @H5ProCallback
    interface IH5ProCallback<T> {
        void onFailure(int i, String str);

        void onSuccess(T t);
    }

    @H5ProMethod(name = "getCurrentDeviceId")
    public static void getCurrentDeviceId(IH5ProCallback<String> iH5ProCallback) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            iH5ProCallback.onFailure(-1, "currentDevice is null");
            return;
        }
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        if (TextUtils.isEmpty(deviceIdentify)) {
            iH5ProCallback.onFailure(-1, "deviceId is empty");
        } else {
            iH5ProCallback.onSuccess(deviceIdentify);
        }
    }

    @H5ProMethod(name = "queryDeviceCapability")
    public static void queryDeviceCapability(String str, IH5ProCallback<DeviceCapability> iH5ProCallback) {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, TAG);
        if (deviceInfo == null) {
            iH5ProCallback.onFailure(-1, "currentDevice is null");
        } else if (!Objects.equals(str, deviceInfo.getDeviceIdentify())) {
            iH5ProCallback.onFailure(-1, "currentDevice changed");
        } else {
            iH5ProCallback.onSuccess(DeviceCapability.createDeviceCapability(deviceInfo));
        }
    }

    static class DeviceCapability {
        private boolean isSupportCustomWatchFace;

        private DeviceCapability() {
        }

        public boolean isSupportCustomWatchFace() {
            return this.isSupportCustomWatchFace;
        }

        public void setSupportCustomWatchFace(boolean z) {
            this.isSupportCustomWatchFace = z;
        }

        public static DeviceCapability createDeviceCapability(DeviceInfo deviceInfo) {
            DeviceCapability deviceCapability = new DeviceCapability();
            deviceCapability.isSupportCustomWatchFace = cwi.c(deviceInfo, a.D);
            return deviceCapability;
        }
    }
}
