package com.huawei.health.devicemgr.api.outofprocess;

import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.cvi;
import defpackage.cvp;
import defpackage.cvq;
import defpackage.cvu;

/* loaded from: classes3.dex */
public interface SendSampleCommandApi {
    void registerDeviceSampleListener(String str, DataReceiveCallback dataReceiveCallback);

    boolean sendDictionaryPointInfoCommand(DeviceInfo deviceInfo, cvi cviVar, String str);

    boolean sendSampleConfigCommand(DeviceInfo deviceInfo, cvq cvqVar, String str);

    boolean sendSampleEventCommand(DeviceInfo deviceInfo, cvp cvpVar, String str);

    boolean sendSamplePointCommand(DeviceInfo deviceInfo, cvu cvuVar, String str);

    void unRegisterDeviceSampleListener(String str);
}
