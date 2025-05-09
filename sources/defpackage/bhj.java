package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class bhj extends bhp {
    public bhj(biw biwVar) {
        super(biwVar);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1).put((byte) 22);
        allocate.put((byte) 1).put((byte) 0);
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_QueryAvailableCommand", bhh.c("0116"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (this.c == null) {
            LogUtil.e("QueryAvailableCommand", "mDeviceLinkParameter is null");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        if (deviceInfo == null || biuVar == null || biuVar.a() == null) {
            LogUtil.e("QueryAvailableCommand", "input param is invalid.");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        if (!bhh.d(biuVar.a())) {
            LogUtil.c("QueryAvailableCommand", "processReceivedData error. receive error code: 100000");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        LogUtil.c("QueryAvailableCommand", "Get QueryAvailable checkResponseCode Success.");
        int a2 = a(biuVar.a(), deviceInfo);
        if (a2 == -1 || a2 == 0 || a2 == 1) {
            bgy bgyVar = new bgy(this.c);
            this.mNextCommand = bgyVar.b(this.mNextCommand, 50122);
            return bgyVar.b();
        }
        if (a2 == 2) {
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        if (a2 == 3) {
            this.mNextCommand = new bgw(this.c);
            biyVar.c(3);
            biyVar.a(100000);
            return biyVar;
        }
        LogUtil.c("QueryAvailableCommand", "can not deal with status.", Integer.valueOf(a2));
        biyVar.c(13);
        biyVar.a(50122);
        return biyVar;
    }

    private int a(byte[] bArr, DeviceInfo deviceInfo) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("QueryAvailableCommand", "tlvFather is null");
            return -1;
        }
        for (bmi bmiVar : e.b()) {
            int e2 = bli.e(bmiVar.e());
            String c = bmiVar.c();
            if (e2 == 1) {
                int e3 = bli.e(c);
                LogUtil.c("QueryAvailableCommand", "check_available_status: ", Integer.valueOf(e3), blt.a(deviceInfo));
                return e3;
            }
        }
        LogUtil.c("QueryAvailableCommand", "not find check_available_status command type.");
        return -1;
    }
}
