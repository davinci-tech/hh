package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.connect.physical.PhysicalLayerBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bhg extends HandshakeGeneralCommandBase {
    private boolean b = false;

    public bhg(DeviceInfo deviceInfo) {
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        ByteBuffer byteBuffer;
        boolean m = Utils.m();
        ReleaseLogUtil.b("DEVMGR_LinkNegotiationCommand", "is demo mobile ", Boolean.valueOf(m));
        if (deviceInfo != null && deviceInfo.getDeviceBtType() == 0) {
            LogUtil.c("LinkNegotiationCommand", "get device command. device bt type is AW ");
            byteBuffer = ByteBuffer.allocate(12);
            byteBuffer.put(bii.a());
        } else {
            boolean z = !m && (Utils.o() ^ true);
            LogUtil.c("LinkNegotiationCommand", "isNotDemoPhone ", Boolean.valueOf(z));
            this.b = z;
            if (z) {
                allocate = ByteBuffer.allocate(12);
            } else {
                allocate = ByteBuffer.allocate(10);
            }
            allocate.put((byte) 1).put((byte) 1);
            allocate.put((byte) 1).put((byte) 0);
            allocate.put((byte) 2).put((byte) 0);
            allocate.put((byte) 3).put((byte) 0);
            allocate.put((byte) 4).put((byte) 0);
            if (z) {
                allocate.put(BaseType.Union).put((byte) 0);
            }
            byteBuffer = allocate;
        }
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(byteBuffer.array());
        if (deviceInfo != null && bjx.a().c(deviceInfo.getDeviceMac()) != null) {
            LogUtil.c("LinkNegotiationCommand", "remove DeviceExInfo cache");
            bjx.a().o(deviceInfo.getDeviceMac());
        }
        ReleaseLogUtil.b("DEVMGR_LinkNegotiationCommand", bhh.c("0101"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        int i;
        biy biyVar = new biy();
        if (biuVar == null || deviceInfo == null) {
            LogUtil.e("LinkNegotiationCommand", "invalid input param");
            biyVar.c(13);
            biyVar.a(50101);
            return biyVar;
        }
        try {
            biw d = d(biuVar.a(), deviceInfo);
            if (d == null) {
                LogUtil.e("LinkNegotiationCommand", "DeviceLinkParameter is null");
                biyVar.c(13);
                biyVar.a(50101);
                return biyVar;
            }
            if (CommonUtil.aq()) {
                LogUtil.c("LinkNegotiationCommand", "debug bypassing.");
            } else if (d.a() == 0) {
                LogUtil.a("LinkNegotiationCommand", "invalid device version.");
                biyVar.c(13);
                biyVar.a(50101);
                return biyVar;
            }
            bmw.e(100061, deviceInfo.getDeviceName(), String.valueOf(d.g()), "");
            bjn.a(deviceInfo, false);
            bjk.e(deviceInfo, false);
            bjx.a().a(deviceInfo.getDeviceMac(), d);
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(deviceInfo.getDeviceMac());
            if (remoteDevice != null) {
                LogUtil.c("LinkNegotiationCommand", "getBondState");
                i = remoteDevice.getBondState();
            } else {
                i = 0;
            }
            LogUtil.c("LinkNegotiationCommand", "received device bondState:", Integer.valueOf(d.e()), ",bluetoothDevice state:", Integer.valueOf(i));
            if (deviceInfo.getDeviceBtType() == 2 && d.e() == 2 && i == 12) {
                LogUtil.c("LinkNegotiationCommand", "process KeyMiss");
                PhysicalLayerBase d2 = bib.a().d(deviceInfo.getDeviceMac());
                if (d2 == null) {
                    LogUtil.a("LinkNegotiationCommand", "process physicalLayerBase is empty.");
                    biyVar.c(13);
                    biyVar.a(50101);
                    return biyVar;
                }
                boolean unPairDevice = d2.unPairDevice(deviceInfo);
                LogUtil.c("LinkNegotiationCommand", "unPair device.", blt.a(deviceInfo.getDeviceMac()), " result:", Boolean.valueOf(unPairDevice));
                if (unPairDevice) {
                    LogUtil.c("LinkNegotiationCommand", "unPairResult success");
                    d.b(true);
                    biyVar.c(56);
                    biyVar.a(50101);
                    return biyVar;
                }
                LogUtil.c("LinkNegotiationCommand", "keyMiss unPairResult fail");
            }
            bkw.d().e(deviceInfo.getDeviceMac());
            return d(deviceInfo, d, biyVar);
        } catch (SecurityException e) {
            LogUtil.e("LinkNegotiationCommand", "processReceivedData SecurityException:", ExceptionUtils.d(e));
            biyVar.c(13);
            biyVar.a(50101);
            return biyVar;
        }
    }

    private biy d(DeviceInfo deviceInfo, biw biwVar, biy biyVar) {
        if (deviceInfo.getDeviceBtType() == 2) {
            bgy bgyVar = new bgy(biwVar);
            this.mNextCommand = bgyVar.b(this.mNextCommand, 50101);
            return bgyVar.b();
        }
        this.mNextCommand = new bhj(biwVar);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private biw d(byte[] bArr, DeviceInfo deviceInfo) {
        if (bArr == null || bkz.e(c(bArr))) {
            LogUtil.e("LinkNegotiationCommand", "get link param failed by data is null or tlvList is empty");
            return null;
        }
        List<bmi> c = c(bArr);
        biw biwVar = new biw();
        int i = 0;
        boolean z = false;
        for (bmi bmiVar : c) {
            int e = bli.e(bmiVar.e());
            if (e == 15) {
                int e2 = bli.e(bmiVar.c());
                ReleaseLogUtil.b("DEVMGR_LinkNegotiationCommand", "setSingleFrameDevice singleFrame : ", Integer.valueOf(e2));
                biwVar.h(e2);
            } else if (e != 16) {
                switch (e) {
                    case 1:
                        biwVar.a(bli.e(bmiVar.c()));
                        break;
                    case 2:
                        biwVar.j(bli.e(bmiVar.c()));
                        break;
                    case 3:
                        biwVar.f(bli.e(bmiVar.c()));
                        break;
                    case 4:
                        int e3 = bli.e(bmiVar.c());
                        ReleaseLogUtil.b("DEVMGR_LinkNegotiationCommand", "getLinkParameter interval : ", Integer.valueOf(e3));
                        biwVar.g(e3);
                        break;
                    case 5:
                        e(bmiVar, biwVar);
                        break;
                    case 6:
                        break;
                    default:
                        z = c(biwVar, z, bmiVar);
                        break;
                }
            } else if (bli.e(bmiVar.c()) == 1) {
                i = 1;
            }
        }
        deviceInfo.setDemoWatch((byte) i);
        if (i != 0 && this.b) {
            return null;
        }
        b(biwVar);
        if (z) {
            return null;
        }
        return biwVar;
    }

    private void b(biw biwVar) {
        if (biwVar.g() == 2698) {
            ReleaseLogUtil.b("DEVMGR_LinkNegotiationCommand", "avoid phone problem saga mtu 2698");
            biwVar.f(990);
            biwVar.j(990);
            biwVar.g(10);
        }
    }

    private boolean c(biw biwVar, boolean z, bmi bmiVar) {
        int e = bli.e(bmiVar.e());
        if (e == 7) {
            biwVar.i(bli.e(bmiVar.c()));
            return z;
        }
        if (e == 8) {
            biwVar.b(bli.e(bmiVar.c()));
            return z;
        }
        if (e == 9) {
            biwVar.e(bli.e(bmiVar.c()));
            return z;
        }
        if (e == 12) {
            biwVar.d(bli.e(bmiVar.c()));
            return z;
        }
        if (e == 127) {
            return true;
        }
        LogUtil.e("LinkNegotiationCommand", "unknown Protocol Type");
        return z;
    }

    private List<bmi> c(byte[] bArr) {
        if (bArr == null) {
            LogUtil.e("LinkNegotiationCommand", "data is empty and return");
            return new ArrayList();
        }
        String d = blq.d(bArr);
        if (d == null || d.length() < 4) {
            LogUtil.a("LinkNegotiationCommand", "dataStrInfo is null");
            return new ArrayList();
        }
        try {
            return new bmn().c(d.substring(4)).b();
        } catch (bmk unused) {
            LogUtil.e("LinkNegotiationCommand", "resolveBTDeviceLinkParameter tlv resolve exception.");
            return new ArrayList();
        }
    }

    private void e(bmi bmiVar, biw biwVar) {
        String c = bmiVar.c();
        if (36 == c.length()) {
            String substring = c.substring(0, 4);
            biwVar.c(bli.e(substring));
            String substring2 = c.substring(4, 36);
            LogUtil.c("LinkNegotiationCommand", "resolveBTDeviceLinkParameter Authentic version : ", substring, " resolveBTDeviceLinkParameter Authentic randA info : ", substring2);
            biwVar.c(substring2);
            return;
        }
        LogUtil.c("LinkNegotiationCommand", "resolveBTDeviceLinkParameter error with handshake parameter is incorrect.");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0101";
    }
}
