package com.huawei.devicesdk.connect.handshake;

import com.huawei.devicesdk.entity.DeviceInfo;
import defpackage.bir;
import defpackage.biu;
import defpackage.biy;

/* loaded from: classes3.dex */
public abstract class HandshakeCommandBase {
    private static final String TAG = "HandshakeCommandBase";
    protected HandshakeCommandBase mNextCommand;

    public abstract bir getDeviceCommand(DeviceInfo deviceInfo);

    public abstract String getTag();

    public abstract biy processReceivedData(DeviceInfo deviceInfo, biu biuVar);

    HandshakeCommandBase() {
    }

    public HandshakeCommandBase getNextCommand() {
        return this.mNextCommand;
    }
}
