package com.huawei.unitedevice.p2p;

import com.huawei.devicesdk.entity.DeviceInfo;
import defpackage.spn;

/* loaded from: classes7.dex */
public interface P2pReceiver {
    void onReceiveMessage(DeviceInfo deviceInfo, spn spnVar);
}
