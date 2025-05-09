package com.huawei.health.device.wifi.interfaces;

import defpackage.ctn;
import java.util.List;

/* loaded from: classes3.dex */
public interface ScanDeviceCallbackInterface {
    void onDeviceDiscovered(List<ctn> list);

    void onDeviceDiscoveryFinished();

    void onFailure(Object obj);
}
