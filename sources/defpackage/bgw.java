package defpackage;

import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class bgw extends bhp {
    public bgw(biw biwVar) {
        super(biwVar);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("DeviceAvailableNotifyCommand", "deviceInfo is null");
            return getUnencryptedDeviceCommand(deviceInfo);
        }
        ByteBuffer allocate = ByteBuffer.allocate(5);
        byte b = 1;
        allocate.put((byte) 1).put((byte) 22);
        allocate.put((byte) 2).put((byte) 1);
        biz i = bjx.a().i(deviceInfo.getDeviceMac());
        if (i != null && i.c()) {
            b = 0;
        }
        allocate.put(b);
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(allocate.array());
        blt.d("DeviceAvailableNotifyCommand", unencryptedDeviceCommand.e(), "getDeviceCommand:");
        ReleaseLogUtil.b("DEVMGR_DeviceAvailableNotifyCommand", bhh.c("0116"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (this.c == null) {
            LogUtil.e("DeviceAvailableNotifyCommand", "mDeviceLinkParameter is null");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        if (deviceInfo == null || biuVar == null || biuVar.a() == null) {
            LogUtil.e("DeviceAvailableNotifyCommand", "input param is invalid.");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        blt.d("DeviceAvailableNotifyCommand", biuVar.a(), "processReceivedData notify:");
        if (!bhh.d(biuVar.a())) {
            LogUtil.c("DeviceAvailableNotifyCommand", "processReceivedData error.");
            biyVar.c(13);
            biyVar.a(50122);
            return biyVar;
        }
        LogUtil.c("DeviceAvailableNotifyCommand", "Get QueryAvailableNotify checkResponseCode Success.");
        this.mNextCommand = new bhg(deviceInfo);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }
}
