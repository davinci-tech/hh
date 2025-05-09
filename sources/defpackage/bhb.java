package defpackage;

import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class bhb extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private String f370a;
    private biw b;

    public bhb(biw biwVar) {
        this("", biwVar);
    }

    public bhb(String str, biw biwVar) {
        this.f370a = str;
        this.b = biwVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        boolean z;
        ArrayList arrayList = new ArrayList(bgl.c().getDeviceList().values());
        if (!arrayList.isEmpty() && deviceInfo != null) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (deviceInfo.getDeviceMac().equals(((UniteDevice) it.next()).getIdentify())) {
                    z = true;
                    break;
                }
            }
            LogUtil.c("GetDevicePinCodeCommand", "isEffectiveDevice:", Boolean.valueOf(z));
            if (z) {
                bmw.e(100081, bmh.b(deviceInfo.getDeviceName()), "", "");
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1).put((byte) 44);
        allocate.put((byte) 1).put((byte) 0);
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_GetDevicePinCodeCommand", bhh.c("012C"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar) || this.b == null) {
            LogUtil.c("GetDevicePinCodeCommand", "Parameter Check Failed");
            biyVar.c(13);
            biyVar.a(50144);
            return biyVar;
        }
        LogUtil.c("GetDevicePinCodeCommand", "start deal 5.1.44 message");
        String b = bjn.b(biuVar.a(), bhh.b(this.b.a()), deviceInfo.getDeviceMac());
        bmw.e(100017, deviceInfo.getDeviceName(), b, "");
        String deviceMac = deviceInfo.getDeviceMac();
        if ("-1".equals(b)) {
            LogUtil.c("GetDevicePinCodeCommand", "PIN_ERROR pinCode: ", b);
            HiChainAuthManager.d().d(deviceMac);
            biyVar.c(13);
            biyVar.a(50144);
            return biyVar;
        }
        bhh.e(deviceMac, b);
        if (bjn.b(deviceMac)) {
            return d(biyVar, deviceMac);
        }
        if (bjk.c(deviceMac)) {
            return c(biyVar, deviceMac);
        }
        if (bjr.e(deviceMac)) {
            return a(biyVar, deviceMac);
        }
        LogUtil.e("GetDevicePinCodeCommand", "device's authType unknown");
        biyVar.c(13);
        biyVar.a(50144);
        return biyVar;
    }

    private biy d(biy biyVar, String str) {
        this.mNextCommand = HiChainAuthManager.d().b(str);
        if (this.mNextCommand != null) {
            biyVar.c(12);
            biyVar.a(100000);
        } else {
            biyVar.c(13);
            biyVar.a(50144);
        }
        return biyVar;
    }

    private biy c(biy biyVar, String str) {
        this.mNextCommand = bjl.e().d(str);
        if (this.mNextCommand != null) {
            biyVar.c(12);
            biyVar.a(100000);
        } else {
            biyVar.c(13);
            biyVar.a(50144);
        }
        return biyVar;
    }

    private biy a(biy biyVar, String str) {
        this.mNextCommand = new bgs(bjr.b("0100", this.b.a(), this.b.j() + this.f370a, str), this.f370a, this.b);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "012C";
    }
}
