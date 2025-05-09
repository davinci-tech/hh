package com.huawei.devicesdk.connect.handshake;

import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import defpackage.bir;
import defpackage.biu;
import defpackage.biy;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public abstract class HandshakeGeneralCommandBase extends HandshakeCommandBase {
    private static final int MAX_RESEND_NUM = 3;
    private static final String TAG = "HandshakeInoperableBase";

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        bir birVar = new bir();
        if (deviceInfo == null) {
            LogUtil.e(TAG, "device info is empty when getDeviceCommand");
            return birVar;
        }
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        bir.a aVar = new bir.a();
        aVar.a(3);
        return aVar.b(birVar);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        LogUtil.e(TAG, "subclass 's method should be called");
        biy biyVar = new biy();
        biyVar.c(13);
        return biyVar;
    }

    public bir getUnencryptedDeviceCommand(DeviceInfo deviceInfo) {
        bir birVar = new bir();
        if (deviceInfo == null) {
            LogUtil.e(TAG, "device info is empty when buildCommandMessage");
            return birVar;
        }
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        bir.a aVar = new bir.a();
        aVar.a(3);
        aVar.c(false);
        return aVar.b(birVar);
    }
}
