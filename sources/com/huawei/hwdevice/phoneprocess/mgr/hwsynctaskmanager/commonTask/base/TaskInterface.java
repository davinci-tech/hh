package com.huawei.hwdevice.phoneprocess.mgr.hwsynctaskmanager.commonTask.base;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.cvi;
import defpackage.cvp;

/* loaded from: classes5.dex */
public interface TaskInterface {
    void handleEvent(DeviceInfo deviceInfo, cvp cvpVar);

    void handlePoint(DeviceInfo deviceInfo, cvi cviVar);
}
