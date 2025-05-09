package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.ExternalDeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;

/* loaded from: classes3.dex */
public class bha extends bhd {
    bha() {
    }

    @Override // defpackage.bhd, com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        return b(deviceInfo);
    }

    @Override // defpackage.bhd, com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy processReceivedData = super.processReceivedData(deviceInfo, biuVar);
        if (processReceivedData.b() != 12) {
            return processReceivedData;
        }
        DeviceCapability b = bjx.a().b(deviceInfo.getDeviceMac());
        if (b == null) {
            LogUtil.a("DeviceExpandCapabilityCommand", "processReceivedData deviceCapability is null.");
            processReceivedData.c(13);
            processReceivedData.a(50155);
            return processReceivedData;
        }
        ExternalDeviceCapability a2 = bjx.a().a(deviceInfo.getDeviceMac());
        bjc c = c(b, a2, deviceInfo.getDeviceMac());
        if (!c.b()) {
            LogUtil.a("DeviceExpandCapabilityCommand", "judgeDualChannelCapability Failed");
            processReceivedData.c(13);
            processReceivedData.a(50155);
            return processReceivedData;
        }
        this.mNextCommand = c.d();
        if (this.mNextCommand == null) {
            processReceivedData.c(53);
            processReceivedData.a(100000);
            return processReceivedData;
        }
        processReceivedData.c(12);
        processReceivedData.a(100000);
        DeviceCapability compatibleCapacity = a2.getCompatibleCapacity();
        LogUtil.c("DeviceExpandCapabilityCommand", "processReceivedData isSupportAccountSwitch: ", Boolean.valueOf(compatibleCapacity.isSupportAccountSwitch()));
        if (!compatibleCapacity.isSupportAccountSwitch()) {
            bjx.a().d(deviceInfo.getDeviceMac(), a2);
        }
        return processReceivedData;
    }

    public static bjc c(DeviceCapability deviceCapability, ExternalDeviceCapability externalDeviceCapability, String str) {
        if (deviceCapability == null) {
            LogUtil.a("DeviceExpandCapabilityCommand", "compatibleDeviceCapability is null");
            return new bjc(false);
        }
        if (externalDeviceCapability == null) {
            LogUtil.a("DeviceExpandCapabilityCommand", "externalDeviceCapability is null");
            return new bjc(false);
        }
        com.huawei.health.devicemgr.business.entity.DeviceInfo deviceInfo = new com.huawei.health.devicemgr.business.entity.DeviceInfo();
        deviceInfo.setDeviceIdentify(str);
        deviceInfo.setExpandCapability(externalDeviceCapability.getCapacity());
        boolean a2 = bmm.a(deviceInfo, 56);
        boolean a3 = bmm.a(deviceInfo, 142);
        if (a2) {
            LogUtil.a("DeviceExpandCapabilityCommand", "deviceInfo support dual socket.");
            bgz.d.put(str, true);
            if (a3) {
                LogUtil.c("DeviceExpandCapabilityCommand", "deviceInfo support extend socket deviceIdentify: ", blt.b(str));
                bgz.i.put(str, true);
            }
            return new bjc(true, new bgz());
        }
        return bgz.a(deviceCapability);
    }
}
