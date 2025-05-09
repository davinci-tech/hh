package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bhc extends HandshakeSimpleCommandBase {
    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("EncryptNegotiationCommand", "get device command error. device is null");
            return constructDefaultCommandMessage();
        }
        LogUtil.c("EncryptNegotiationCommand", "get device command, device: ", blt.a(deviceInfo.getDeviceMac()));
        bgu bguVar = new bgu();
        byte[] d = bguVar.d(bguVar.e(deviceInfo.getDeviceMac()), deviceInfo.getDeviceMac());
        if (d.length == 0) {
            LogUtil.e("EncryptNegotiationCommand", "encrypt work key error");
            return constructDefaultCommandMessage();
        }
        blt.d("EncryptNegotiationCommand", d, "generate work key finish. workKey=");
        return constructCommandMessage("a3d330f8-b84f-4f48-a78c-f8d1e33b597a", SimpleDataHead.DC, d);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!checkInputParam(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(40103);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (a2.length == 0) {
            LogUtil.e("EncryptNegotiationCommand", "[processReceivedData]receive data is invalid.");
            biyVar.c(13);
            biyVar.a(40103);
            return biyVar;
        }
        if (a2[0] == 0) {
            LogUtil.c("EncryptNegotiationCommand", "encrypt negotiation success.");
            if (bjx.a().f(deviceInfo.getDeviceMac())) {
                LogUtil.c("EncryptNegotiationCommand", "start expand device capability.");
                this.mNextCommand = new bhd();
            }
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        blt.d("EncryptNegotiationCommand", a2, "encrypt negotiation error. code: ");
        biyVar.c(13);
        biyVar.a(40103);
        return biyVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return String.valueOf(40103);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir prepare(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.ENABLE, "a3d330f8-b84f-4f48-a78c-f8d1e33b597a");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir end(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.DISABLE, "a3d330f8-b84f-4f48-a78c-f8d1e33b597a");
    }
}
