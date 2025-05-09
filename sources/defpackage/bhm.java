package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bhm extends HandshakeSimpleCommandBase {
    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("RequestAuthenticCommand", "get device command error. device is null");
            return constructDefaultCommandMessage();
        }
        LogUtil.c("RequestAuthenticCommand", "get device command. device: ", blt.a(deviceInfo.getDeviceMac()));
        return constructCommandMessage("02b2a08e-f8b0-4047-b1fd-f4e0efeee679", SimpleDataHead.DB, new byte[0]);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!checkInputParam(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(40101);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        String deviceMac = deviceInfo.getDeviceMac();
        bjx.a().b(deviceMac, a(a2));
        if (a2.length <= 1) {
            LogUtil.c("RequestAuthenticCommand", "[processReceivedData]no need to auth. device= ", blt.a(deviceMac));
            this.mNextCommand = new bhc();
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        if (a2.length < 16) {
            blt.d("RequestAuthenticCommand", a2, "[processReceivedData]parse randA error. randA=");
            this.mNextCommand = null;
            biyVar.c(13);
            biyVar.a(40101);
            return biyVar;
        }
        if (a2.length > 16) {
            String d = blq.d(new byte[]{a2[16]});
            LogUtil.c("RequestAuthenticCommand", "[processReceivedData] extend hex: ", d);
            d(d, deviceMac);
        }
        byte[] bArr = new byte[16];
        System.arraycopy(a2, 0, bArr, 0, 16);
        blt.d("RequestAuthenticCommand", bArr, "[processReceivedData]need to auth. randA=");
        this.mNextCommand = new bgr(bArr);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private static void d(String str, String str2) {
        LogUtil.c("RequestAuthenticCommand", "saveExtendedBytes extend uniqueId: ", blt.b(str2));
        String b = bmr.b(str2);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("weightUnit");
        stringBuffer.append(b);
        blz.a(stringBuffer.toString(), str);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return String.valueOf(40101);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir prepare(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.ENABLE, "02b2a08e-f8b0-4047-b1fd-f4e0efeee679");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir end(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.DISABLE, "02b2a08e-f8b0-4047-b1fd-f4e0efeee679");
    }

    private boolean a(byte[] bArr) {
        byte b;
        if (bArr.length == 1) {
            b = bArr[0];
        } else {
            b = bArr.length > 16 ? bArr[16] : (byte) 0;
        }
        return (b & 1) == 1;
    }
}
