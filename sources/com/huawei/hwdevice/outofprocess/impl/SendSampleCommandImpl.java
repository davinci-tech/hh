package com.huawei.hwdevice.outofprocess.impl;

import com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import defpackage.cvi;
import defpackage.cvp;
import defpackage.cvq;
import defpackage.cvu;
import defpackage.spj;

/* loaded from: classes9.dex */
public class SendSampleCommandImpl implements SendSampleCommandApi {
    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSampleConfigCommand(DeviceInfo deviceInfo, cvq cvqVar, String str) {
        return spj.c().d(deviceInfo, cvqVar, str);
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSampleEventCommand(DeviceInfo deviceInfo, cvp cvpVar, String str) {
        return spj.c().e(deviceInfo, cvpVar, str);
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendSamplePointCommand(DeviceInfo deviceInfo, cvu cvuVar, String str) {
        return spj.c().e(deviceInfo, cvuVar, str);
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public boolean sendDictionaryPointInfoCommand(DeviceInfo deviceInfo, cvi cviVar, String str) {
        return spj.c().c(deviceInfo, cviVar, str);
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public void registerDeviceSampleListener(String str, DataReceiveCallback dataReceiveCallback) {
        spj.c().a(str, dataReceiveCallback);
    }

    @Override // com.huawei.health.devicemgr.api.outofprocess.SendSampleCommandApi
    public void unRegisterDeviceSampleListener(String str) {
        spj.c().c(str);
    }
}
