package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.TimeZone;

/* loaded from: classes3.dex */
public class bhs extends HandshakeGeneralCommandBase {
    private boolean b = false;
    private boolean c;

    bhs(boolean z) {
        this.c = z;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        return b(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "processReceivedData parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50150);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "processReceivedData checkResponseCode Failed.");
            biyVar.c(13);
            biyVar.a(50150);
            return biyVar;
        }
        if (!e(a2)) {
            LogUtil.e("SetDeviceTimeZoneIdCommand", "processReceivedData parse timezone result error.");
            biyVar.c(13);
            biyVar.a(50150);
            return biyVar;
        }
        if (this.b) {
            LogUtil.c("SetDeviceTimeZoneIdCommand", "processReceivedData Resend Cmd");
            this.mNextCommand = new bhs(true);
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        if (e(biyVar, deviceInfo)) {
            return biyVar;
        }
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private boolean e(biy biyVar, DeviceInfo deviceInfo) {
        DeviceCapability b = bjx.a().b(deviceInfo.getDeviceMac());
        if (b == null) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "processReceivedData deviceCapability is null.");
            biyVar.c(13);
            biyVar.a(50150);
            return true;
        }
        bjc c = c(b);
        if (!c.b()) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "judgeSupportActivityTypeCapability Failed");
            biyVar.c(13);
            biyVar.a(50150);
            return true;
        }
        this.mNextCommand = c.d();
        if (this.mNextCommand != null) {
            return false;
        }
        biyVar.c(53);
        biyVar.a(100000);
        return true;
    }

    private boolean e(byte[] bArr) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "parseSetDeviceTimeZoneResult tlvFather is null");
            return false;
        }
        d(e);
        return true;
    }

    private void d(bmj bmjVar) {
        LogUtil.c("SetDeviceTimeZoneIdCommand", "checkTimeOffset");
        for (bmi bmiVar : bmjVar.b()) {
            if (bli.e(bmiVar.e()) == 1) {
                int e = bli.e(bmiVar.c());
                int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
                int abs = Math.abs(currentTimeMillis - e);
                LogUtil.c("SetDeviceTimeZoneIdCommand", "checkTimeOffset currentTime:", Integer.valueOf(currentTimeMillis), ", replyTime:", Integer.valueOf(e), "checkTimeOffset offset:", Integer.valueOf(abs));
                if (abs < 1 || this.c) {
                    return;
                }
                LogUtil.c("SetDeviceTimeZoneIdCommand", "checkTimeOffset offset: set isNeedResendCmd true");
                this.b = true;
                return;
            }
        }
    }

    private bir b(DeviceInfo deviceInfo) {
        byte[] d = bhh.d();
        String id = TimeZone.getDefault().getID();
        int length = blq.a(blq.b(id)).length;
        ByteBuffer allocate = ByteBuffer.allocate(d.length + 4 + length);
        allocate.put((byte) 1).put((byte) 50).put(d).put((byte) 3).put((byte) length).put(blq.a(blq.b(id)));
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_SetDeviceTimeZoneIdCommand", bhh.c("0132"));
        return deviceCommand;
    }

    public static bjc c(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.a("SetDeviceTimeZoneIdCommand", "deviceCapability is null");
            return new bjc(false);
        }
        if (deviceCapability.isSupportActivityType()) {
            return new bjc(true, new bhe());
        }
        return bhe.d(deviceCapability);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0132";
    }
}
