package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import defpackage.bir;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;

/* loaded from: classes3.dex */
public class bhi extends HandshakeGeneralCommandBase {
    private byte b;
    private long d;
    private ByteBuffer e;

    public bhi(byte[] bArr, byte b, long j) {
        if (bArr == null) {
            LogUtil.e("HiChainDataTransmitCommand", "transmitData is null");
            return;
        }
        this.e = ByteBuffer.wrap(bArr);
        this.b = b;
        this.d = j;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        ByteBuffer byteBuffer = this.e;
        if (byteBuffer == null) {
            LogUtil.e("HiChainDataTransmitCommand", "mTransmitData is null");
            return getUnencryptedDeviceCommand(deviceInfo);
        }
        byte[] d = blq.d(byteBuffer.capacity());
        if (this.b != 0) {
            long j = this.d;
            if (j != 0) {
                byte[] a2 = blq.a(blq.b(j));
                int length = d.length;
                allocate = ByteBuffer.allocate(length + 3 + this.e.capacity() + 5 + a2.length);
                allocate.put((byte) 1).put((byte) 40);
                allocate.put((byte) 1).put(d).put(this.e);
                allocate.put((byte) 2).put((byte) 1).put(this.b);
                allocate.put((byte) 3).put(blq.d(a2.length)).put(a2);
                if (deviceInfo == null && deviceInfo.getDeviceBtType() == 2 && this.b != 0 && this.d != 0) {
                    if (bkd.c()) {
                        return a(deviceInfo, allocate);
                    }
                    return b(deviceInfo, allocate);
                }
                return b(deviceInfo, allocate);
            }
        }
        allocate = ByteBuffer.allocate(d.length + 3 + this.e.capacity());
        allocate.put((byte) 1).put((byte) 40);
        allocate.put((byte) 1).put(d).put(this.e);
        if (deviceInfo == null) {
        }
        return b(deviceInfo, allocate);
    }

    private bir b(DeviceInfo deviceInfo, ByteBuffer byteBuffer) {
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(byteBuffer.array());
        ReleaseLogUtil.b("DEVMGR_HiChainDataTransmitCommand", bhh.c("0128"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        bjc a2;
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(50140);
            return biyVar;
        }
        bjo e = bjn.e(biuVar.a());
        if (bjk.c(deviceInfo.getDeviceMac())) {
            a2 = bjl.e().d(deviceInfo.getDeviceMac(), e);
        } else {
            a2 = HiChainAuthManager.d().a(deviceInfo.getDeviceMac(), e.e());
        }
        this.mNextCommand = a2.d();
        if (a2.b()) {
            if (this.mNextCommand != null) {
                biyVar.c(12);
                biyVar.a(100000);
            } else if (bjk.c(deviceInfo.getDeviceMac()) && e.b() == 4) {
                biyVar.c(55);
                biyVar.a(50140);
            } else {
                biyVar.c(17);
                biyVar.a(50140);
            }
            LogUtil.c("HiChainDataTransmitCommand", "processReceivedData");
            return biyVar;
        }
        biyVar.c(13);
        biyVar.a(50140);
        return biyVar;
    }

    private bir a(DeviceInfo deviceInfo, ByteBuffer byteBuffer) {
        bir birVar = new bir();
        if (deviceInfo == null) {
            LogUtil.e("HiChainDataTransmitCommand", "device info is empty when buildCommandMessage");
            return birVar;
        }
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        bir.a aVar = new bir.a();
        aVar.a(0);
        aVar.c(false);
        birVar.e(byteBuffer.array());
        return aVar.b(birVar);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0128";
    }
}
