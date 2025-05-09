package com.huawei.hwdevice.mainprocess.mgr.settingcamera;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;

/* loaded from: classes5.dex */
public interface CameraContorlInterface {
    boolean checkSupportCamera();

    void deleteCameraAuthorization(DeviceInfo deviceInfo);

    void getCameraStatus(CameraAuthStatusCallback cameraAuthStatusCallback);

    boolean supportAlgoArch();

    void unbindCameraService();

    void updateCameraAuthStatus(boolean z);
}
