package com.huawei.devicesdk.strategy;

import com.huawei.devicesdk.callback.ConnectFilter;
import com.huawei.devicesdk.callback.ConnectStatusInterface;
import com.huawei.devicesdk.entity.ConnectMode;
import com.huawei.devicesdk.entity.DeviceInfo;
import defpackage.bib;
import defpackage.biu;
import defpackage.biy;
import defpackage.bln;
import defpackage.blt;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public abstract class ConnectStrategy {
    private static final String TAG = "ConnectStrategy";
    public biy mConnectStatusMsg = new biy();
    protected ConnectStatusInterface mHandshakeStatusReporter;

    public abstract void destroy(String str);

    public abstract void getConnectStatus(String str);

    public abstract void onChannelEnable(DeviceInfo deviceInfo, int i);

    public abstract void onDataReceived(DeviceInfo deviceInfo, biu biuVar, int i);

    public void registerHandshakeFilter(DeviceInfo deviceInfo, ConnectFilter connectFilter) {
    }

    public abstract void startHandshake(DeviceInfo deviceInfo);

    public abstract void unPairDevice(DeviceInfo deviceInfo, ConnectMode connectMode, boolean z);

    public void connect(ConnectMode connectMode, DeviceInfo deviceInfo) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null || connectMode == null) {
            LogUtil.a(TAG, "connect error, device is null");
            ConnectStatusInterface connectStatusInterface = this.mHandshakeStatusReporter;
            if (connectStatusInterface != null) {
                connectStatusInterface.onHandshakeFailed(deviceInfo, bln.e(7, 303));
                return;
            }
            return;
        }
        LogUtil.c(TAG, "connect start.", blt.a(deviceInfo));
        bib.a().d(connectMode, deviceInfo);
    }

    public void registerHandshakeStatusReporter(ConnectStatusInterface connectStatusInterface) {
        this.mHandshakeStatusReporter = connectStatusInterface;
    }

    public void disconnect(DeviceInfo deviceInfo) {
        bib.a().d(deviceInfo);
    }

    public void pairDevice(DeviceInfo deviceInfo, ConnectMode connectMode) {
        if (deviceInfo == null || deviceInfo.getDeviceMac() == null || connectMode == null) {
            LogUtil.e(TAG, "pair device error. device or connect mode is invalid.");
        } else {
            LogUtil.c(TAG, "pair device.", blt.a(deviceInfo));
            bib.a().c(deviceInfo, connectMode);
        }
    }
}
