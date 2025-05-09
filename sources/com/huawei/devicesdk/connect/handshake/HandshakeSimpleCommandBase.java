package com.huawei.devicesdk.connect.handshake;

import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.entity.SimpleDataHead;
import defpackage.bir;
import defpackage.biu;
import defpackage.biy;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public abstract class HandshakeSimpleCommandBase extends HandshakeCommandBase {
    private static final int MAX_RETRY_TIMES = 3;
    private static final String TAG = "HandshakeWeightScaleBase";
    private String mServiceUuid = "0000181c-0000-1000-8000-00805f9b34fb";

    public bir prepare(DeviceInfo deviceInfo) {
        return constructDefaultCommandMessage();
    }

    public bir end(DeviceInfo deviceInfo) {
        return constructDefaultCommandMessage();
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        LogUtil.e(TAG, "subclass 's method should be called");
        biy biyVar = new biy();
        biyVar.c(13);
        return biyVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public HandshakeCommandBase getNextCommand() {
        return this.mNextCommand;
    }

    protected bir constructCommandMessage(String str, SimpleDataHead simpleDataHead, byte[] bArr) {
        bir birVar = new bir();
        if (simpleDataHead == null) {
            LogUtil.e(TAG, "dataHead is null");
            return birVar;
        }
        birVar.b(SendMode.PROTOCOL_TYPE_D);
        birVar.c(this.mServiceUuid);
        birVar.b(str);
        if (bArr == null) {
            bArr = new byte[0];
        }
        birVar.e(bArr);
        bir.a aVar = new bir.a();
        aVar.d(CharacterOperationType.WRITE).c(SimpleDataHead.isEncryptedDataHead(simpleDataHead.getDataHead())).a(3).b(simpleDataHead.getDataHead());
        return aVar.b(birVar);
    }

    protected bir constructCharacterNotification(CharacterOperationType characterOperationType, String str) {
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_D);
        birVar.c(this.mServiceUuid);
        birVar.b(str);
        birVar.e(new byte[0]);
        bir.a aVar = new bir.a();
        aVar.d(characterOperationType).c(false).a(3);
        return aVar.b(birVar);
    }

    protected bir constructDefaultCommandMessage() {
        return new bir();
    }

    protected boolean checkInputParam(DeviceInfo deviceInfo, biu biuVar) {
        if (deviceInfo != null && isDataFrameValid(biuVar)) {
            return true;
        }
        LogUtil.e(TAG, "[processReceivedData]param is invalid.");
        return false;
    }

    private boolean isDataFrameValid(biu biuVar) {
        return (biuVar == null || biuVar.a() == null || biuVar.b() == null) ? false : true;
    }
}
