package defpackage;

import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bhe extends HandshakeGeneralCommandBase {
    bhe() {
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        return b(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "processReceivedData parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50118);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "processReceivedData checkResponseCode Failed.");
            biyVar.c(13);
            biyVar.a(50118);
            return biyVar;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        DeviceCapability b = bjx.a().b(deviceMac);
        if (b == null) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "processReceivedData deviceCapability is null.");
            biyVar.c(13);
            biyVar.a(50118);
            return biyVar;
        }
        if (!c(a2, b)) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "resolveExpandCapabilityCommand Failed.");
            biyVar.c(13);
            biyVar.a(50118);
            return biyVar;
        }
        bjx.a().c(deviceMac, b);
        bjc d = d(b);
        if (!d.b()) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "judgeSupportMessagePushCapability Failed");
            biyVar.c(13);
            biyVar.a(50118);
            return biyVar;
        }
        this.mNextCommand = d.d();
        if (this.mNextCommand == null) {
            biyVar.c(53);
            biyVar.a(100000);
            return biyVar;
        }
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private bir b(DeviceInfo deviceInfo) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1).put(BaseType.Vector64).put((byte) -127).put((byte) 0);
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_DeviceSupportActivityTypeCommand", bhh.c("0112"));
        return deviceCommand;
    }

    private boolean c(byte[] bArr, DeviceCapability deviceCapability) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "tlvFather is null.");
            return false;
        }
        List<bmj> e2 = e.e();
        if (bkz.e(e2)) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "tlvFathers is empty.");
            return true;
        }
        Iterator<bmj> it = e2.iterator();
        while (it.hasNext()) {
            for (bmi bmiVar : it.next().b()) {
                int e3 = bli.e(bmiVar.e());
                if (e3 == 2) {
                    d(bli.e(bmiVar.c()), deviceCapability);
                } else if (e3 == 3) {
                    a(bli.e(bmiVar.c()), deviceCapability);
                }
            }
        }
        return true;
    }

    private void d(int i, DeviceCapability deviceCapability) {
        if (i == 4) {
            deviceCapability.configureWalk(true);
        }
        switch (i) {
            case 7:
                deviceCapability.configureRun(true);
                break;
            case 8:
                deviceCapability.configureClimb(true);
                break;
            case 9:
                deviceCapability.configureRiding(true);
                break;
            case 10:
                deviceCapability.configureSleep(true);
                deviceCapability.configureSleepShallow(true);
                deviceCapability.configureSleepDeep(true);
                break;
        }
    }

    private void a(int i, DeviceCapability deviceCapability) {
        if (((i >> 5) & 1) == 1) {
            deviceCapability.configureIsSupportHeartRate(true);
        } else {
            deviceCapability.configureIsSupportHeartRate(false);
        }
    }

    public static bjc d(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.a("DeviceSupportActivityTypeCommand", "deviceCapability is null");
            return new bjc(false);
        }
        return new bjc(true);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0112";
    }
}
