package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes3.dex */
public class bhu extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private String f376a;
    private String e;

    public bhu(String str, String str2) {
        this.f376a = str;
        this.e = str2;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        String str;
        if (this.e != null && (str = this.f376a) != null && deviceInfo != null) {
            byte[] a2 = blq.a(str);
            byte[] d = blq.d(a2.length);
            int length = a2.length + 1 + d.length;
            byte[] bArr = new byte[length];
            bArr[0] = 1;
            System.arraycopy(d, 0, bArr, 1, d.length);
            System.arraycopy(a2, 0, bArr, d.length + 1, a2.length);
            byte[] a3 = blq.a(this.e);
            byte[] d2 = blq.d(a3.length);
            int length2 = a3.length + 1 + d2.length;
            byte[] bArr2 = new byte[length2];
            bArr2[0] = 2;
            System.arraycopy(d2, 0, bArr2, 1, d2.length);
            System.arraycopy(a3, 0, bArr2, d2.length + 1, a3.length);
            ByteBuffer allocate = ByteBuffer.allocate(length + 2 + length2);
            allocate.put((byte) 1).put((byte) 45).put(bArr).put(bArr2);
            bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
            unencryptedDeviceCommand.e(allocate.array());
            bmf.a(deviceInfo.getDeviceMac(), "updateKey");
            LogUtil.c("UpdateDeviceKeyCommand", "getDeviceCommand_UpdateKeySuccessLabel:", bmf.a(deviceInfo.getDeviceMac()));
            ReleaseLogUtil.b("DEVMGR_UpdateDeviceKeyCommand", bhh.c("012D"));
            return unencryptedDeviceCommand;
        }
        LogUtil.e("UpdateDeviceKeyCommand", "getDeviceCommand error");
        return super.getDeviceCommand(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.e("UpdateDeviceKeyCommand", "processReceivedData error. parameters Check fail");
            biyVar.c(13);
            biyVar.a(50145);
            return biyVar;
        }
        if (bjr.e(deviceInfo.getDeviceMac())) {
            int a2 = a(biuVar.a());
            String deviceMac = deviceInfo.getDeviceMac();
            if (a2 == 100000) {
                bmf.d(deviceMac, 1, bmy.b(bjr.d(bmf.d(deviceMac, 2))));
                String a3 = bjp.d().a(deviceMac);
                if (!TextUtils.isEmpty(a3)) {
                    bjp.d().c(deviceMac, a3);
                    bmf.d(deviceMac, 2, bmy.b(a3));
                }
                bmf.a(deviceInfo.getDeviceMac(), "");
                LogUtil.c("UpdateDeviceKeyCommand", "processReceivedData_UpdateKeySuccessLabel: ", bmf.a(deviceInfo.getDeviceMac()));
                this.mNextCommand = new bhk(false);
                biyVar.c(12);
                biyVar.a(100000);
                return biyVar;
            }
            bmf.d(deviceMac, 2, "");
            LogUtil.e("UpdateDeviceKeyCommand", "processReceivedData error, updateStatus: ", Integer.valueOf(a2));
            biyVar.c(13);
            biyVar.a(50145);
            return biyVar;
        }
        LogUtil.e("UpdateDeviceKeyCommand", "processReceivedData error, HiChainLite not init");
        biyVar.c(13);
        biyVar.a(50145);
        return biyVar;
    }

    private int a(byte[] bArr) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.e("UpdateDeviceKeyCommand", "process data error, tlvFather is null");
            return -1;
        }
        List<bmi> b = e.b();
        if (b == null) {
            LogUtil.e("UpdateDeviceKeyCommand", "resolveWearDeviceKey is not correct");
            return -1;
        }
        for (bmi bmiVar : b) {
            if (bli.e(bmiVar.e()) == 127) {
                return bli.e(bmiVar.c());
            }
        }
        return -1;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "012D";
    }
}
