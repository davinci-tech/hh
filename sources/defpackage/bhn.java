package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class bhn extends HandshakeGeneralCommandBase {
    private boolean c;
    private boolean e = false;

    public bhn(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        return a(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (a(deviceInfo, biuVar)) {
            biyVar.c(12);
            biyVar.a(100000);
        } else {
            biyVar.c(13);
            biyVar.a(50105);
        }
        return biyVar;
    }

    private bir a(DeviceInfo deviceInfo) {
        byte[] d = bhh.d();
        ByteBuffer allocate = ByteBuffer.allocate(d.length + 2);
        allocate.put((byte) 1).put((byte) 5).put(d);
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_SetDeviceDateAndTimeCommand", bhh.c("0105"));
        return deviceCommand;
    }

    private boolean a(DeviceInfo deviceInfo, biu biuVar) {
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.c("SetDeviceDateAndTimeCommand", "ParameterCheck Failed");
            return false;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            LogUtil.c("SetDeviceDateAndTimeCommand", "SetDeviceDateAndTime checkResponseCode Failed.");
            return false;
        }
        LogUtil.c("SetDeviceDateAndTimeCommand", "SetDeviceDateAndTime checkResponseCode Success.");
        if (!e(a2)) {
            LogUtil.c("SetDeviceDateAndTimeCommand", "parseSetDateAndTimeDataResult Failed");
            return false;
        }
        if (this.e) {
            LogUtil.c("SetDeviceDateAndTimeCommand", "parseSetDateAndTimeDataResult Resend Cmd");
            this.mNextCommand = new bhn(true);
            return true;
        }
        this.mNextCommand = new bhf();
        return true;
    }

    private boolean e(byte[] bArr) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("SetDeviceDateAndTimeCommand", "parseSetDateAndTimeDataResult tlvFather is null");
            return false;
        }
        c(e);
        return true;
    }

    private void c(bmj bmjVar) {
        LogUtil.c("SetDeviceDateAndTimeCommand", "parseTimeData");
        for (bmi bmiVar : bmjVar.b()) {
            if (bli.e(bmiVar.e()) == 1) {
                int e = bli.e(bmiVar.c());
                int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                int abs = Math.abs(currentTimeMillis - e);
                LogUtil.c("SetDeviceDateAndTimeCommand", "parseTimeData currentTime:", Integer.valueOf(currentTimeMillis), ",replyTime:", Integer.valueOf(e), "parseTimeData offset:", Integer.valueOf(abs));
                if (abs >= 1 && !this.c) {
                    LogUtil.c("SetDeviceDateAndTimeCommand", "parseTimeData offset: set isNeedResendCmd true");
                    this.e = true;
                }
            }
        }
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0105";
    }
}
