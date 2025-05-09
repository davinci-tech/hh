package com.huawei.pluginhealthzone.devicelink.deviceproxy;

import com.huawei.pluginhealthzone.devicelink.callback.DeviceDataListener;
import defpackage.mpx;
import defpackage.spn;

/* loaded from: classes6.dex */
public interface HealthZoneModelPolicy {
    void activeSendMsgToDevice(int i, mpx mpxVar, DeviceDataListener deviceDataListener);

    void receiveDataFromDevice(int i, spn spnVar);
}
