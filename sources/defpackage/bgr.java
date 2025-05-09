package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase;
import com.huawei.devicesdk.entity.CharacterOperationType;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SimpleDataHead;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* loaded from: classes3.dex */
public class bgr extends HandshakeSimpleCommandBase {
    private byte[] c;
    private byte[] e;

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f365a = {57, 56, 53, 54};
    private static final byte[] d = {49, 49, 50, 51};
    private static final byte[] b = blq.a(bmo.e(1, 33));

    bgr(byte[] bArr) {
        this.c = bArr;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        bir constructDefaultCommandMessage = constructDefaultCommandMessage();
        if (deviceInfo == null) {
            LogUtil.a("AccessAuthenticCommand", "get device command error. device is null");
            return constructDefaultCommandMessage;
        }
        byte[] bArr = this.c;
        if (bArr == null || bArr.length != 16) {
            LogUtil.a("AccessAuthenticCommand", "randA is invalid.");
            return constructDefaultCommandMessage;
        }
        blt.d("AccessAuthenticCommand", bArr, "randA is valid. randA=", blt.a(deviceInfo.getDeviceMac()), "get device command, device: ");
        try {
            byte[] d2 = bgv.d(16);
            this.e = d2;
            if (d2 == null) {
                LogUtil.a("AccessAuthenticCommand", "generate randB error.");
                return constructDefaultCommandMessage;
            }
            blt.d("AccessAuthenticCommand", d2, "generate randB success. randB=");
            byte[] d3 = d(d);
            int length = d3.length;
            if (length == 0) {
                return constructDefaultCommandMessage;
            }
            byte[] bArr2 = this.e;
            int length2 = bArr2.length;
            byte[] bArr3 = new byte[length2 + length];
            System.arraycopy(bArr2, 0, bArr3, 0, length2);
            System.arraycopy(d3, 0, bArr3, length2, length);
            blt.c("AccessAuthenticCommand", bArr3, "generate tokenB success. TokenB=");
            return constructCommandMessage("32330a04-15d9-421a-91c5-2a2d5c7525c9", SimpleDataHead.DB, bArr3);
        } catch (NoSuchAlgorithmException e) {
            LogUtil.e("AccessAuthenticCommand", "generate randB error. error: ", e.getLocalizedMessage());
            return constructDefaultCommandMessage;
        }
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        byte[] bArr;
        biy biyVar = new biy();
        if (!checkInputParam(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(40102);
            return biyVar;
        }
        byte[] bArr2 = this.c;
        if (bArr2 == null || bArr2.length != 16 || (bArr = this.e) == null || bArr.length != 16) {
            LogUtil.a("AccessAuthenticCommand", "randA or randB is invalid");
            biyVar.c(13);
            biyVar.a(40102);
            return biyVar;
        }
        if (Arrays.equals(biuVar.a(), d(f365a))) {
            this.mNextCommand = new bhc();
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        this.mNextCommand = null;
        biyVar.c(13);
        biyVar.a(40102);
        return biyVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return String.valueOf(40102);
    }

    private byte[] d(byte[] bArr) {
        blt.d("AccessAuthenticCommand", bArr, "generateAuthToken. factor=");
        byte[] bArr2 = b;
        if (bArr2 == null) {
            LogUtil.e("AccessAuthenticCommand", "CAK_BYTES is null");
            return new byte[0];
        }
        byte[] bArr3 = this.c;
        int length = bArr3.length;
        int length2 = this.e.length;
        byte[] bArr4 = new byte[length + length2];
        System.arraycopy(bArr3, 0, bArr4, 0, length);
        System.arraycopy(this.e, 0, bArr4, length, length2);
        blt.c("AccessAuthenticCommand", bArr4, "combine randA and randB finish. randAB=");
        byte[] bArr5 = new byte[bArr2.length + bArr.length];
        System.arraycopy(bArr2, 0, bArr5, 0, bArr2.length);
        System.arraycopy(bArr, 0, bArr5, bArr2.length, bArr.length);
        blt.c("AccessAuthenticCommand", bArr4, "generate cak key finish. cakKey=");
        LogUtil.c("AccessAuthenticCommand", "start to create encode info.");
        try {
            byte[] d2 = bgv.d(bgv.d(bArr5, bArr4), bArr4);
            blt.d("AccessAuthenticCommand", d2, "generateAuthToken finish. code: ");
            return d2;
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            LogUtil.e("AccessAuthenticCommand", "generateAuthToken error. error:", e.getLocalizedMessage());
            return new byte[0];
        }
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir prepare(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.ENABLE, "32330a04-15d9-421a-91c5-2a2d5c7525c9");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeSimpleCommandBase
    public bir end(DeviceInfo deviceInfo) {
        return constructCharacterNotification(CharacterOperationType.DISABLE, "32330a04-15d9-421a-91c5-2a2d5c7525c9");
    }
}
