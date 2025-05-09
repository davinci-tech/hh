package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class bhd extends HandshakeGeneralCommandBase {
    bhd() {
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("DeviceExpandCapabilitySimpleCommand", "deviceInfo is null");
            return super.getDeviceCommand(deviceInfo);
        }
        bmw.e(100045, deviceInfo.getDeviceName(), "", "");
        return b(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("DeviceExpandCapabilitySimpleCommand", "processReceivedData parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50155);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            LogUtil.a("DeviceExpandCapabilitySimpleCommand", "processReceivedData checkResponseCode Failed.");
            biyVar.c(13);
            biyVar.a(50155);
            return biyVar;
        }
        if (!d(a2, deviceInfo)) {
            LogUtil.a("DeviceExpandCapabilitySimpleCommand", "resolveExpandCapabilityCommand Failed.");
            biyVar.c(13);
            biyVar.a(50155);
            return biyVar;
        }
        this.mNextCommand = null;
        biyVar.c(12);
        biyVar.a(100000);
        bmw.e(100046, deviceInfo.getDeviceName(), String.valueOf(biyVar.d()), String.valueOf(biyVar.b()));
        return biyVar;
    }

    private boolean d(byte[] bArr, DeviceInfo deviceInfo) {
        String str;
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("DeviceExpandCapabilitySimpleCommand", "tlvFather is null.");
            return false;
        }
        List<bmi> b = e.b();
        if (b.size() <= 0) {
            LogUtil.a("DeviceExpandCapabilitySimpleCommand", "TLV info is incorrect.");
            return false;
        }
        Iterator<bmi> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                str = "";
                break;
            }
            bmi next = it.next();
            if (((byte) bli.e(next.e())) == 1) {
                str = next.c();
                break;
            }
            LogUtil.c("DeviceExpandCapabilitySimpleCommand", "resolveExpandCapability default");
        }
        bjx.a().a(deviceInfo, str);
        return true;
    }

    bir b(DeviceInfo deviceInfo) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1).put((byte) 55).put((byte) 1).put((byte) 0);
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        deviceCommand.c(bip.f389a.toString());
        deviceCommand.b(bip.i.toString());
        ReleaseLogUtil.b("DEVMGR_DeviceExpandCapabilitySimpleCommand", bhh.c("0137"));
        return deviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0137";
    }
}
