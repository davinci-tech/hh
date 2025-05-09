package com.huawei.health.devicemgr.business.callback;

import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.cvr;

/* loaded from: classes.dex */
public interface DataReceiveCallback {
    void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar);
}
