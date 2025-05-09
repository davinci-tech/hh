package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class bgx extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private volatile List<Integer> f367a;
    private boolean c = false;
    private LinkedHashMap<String, Boolean> d = new LinkedHashMap<>(16);

    bgx(List<Integer> list) {
        this.f367a = list;
    }

    public static bjc a(DeviceCapability deviceCapability) {
        if (deviceCapability == null) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "deviceCapability is null");
            return new bjc(false);
        }
        if (deviceCapability.isSupportExpandCapability()) {
            LogUtil.c("DeviceCommandCapabilityConsultCommand", "isSupportExpandCapability");
            return new bjc(true, new bha());
        }
        LogUtil.c("DeviceCommandCapabilityConsultCommand", "is not SupportExpandCapability");
        return bgz.a(deviceCapability);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("DeviceCommandCapabilityConsultCommand", "deviceInfo is null");
            return super.getDeviceCommand(deviceInfo);
        }
        bmw.e(100043, deviceInfo.getDeviceName(), "", "");
        return d(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "processReceivedData parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50103);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        blt.d("DeviceCommandCapabilityConsultCommand", a2, "processReceivedData dataInfos:");
        if (!bhh.d(a2)) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "Get CommandCapability checkResonpeCode Failed.");
            biyVar.c(13);
            biyVar.a(50103);
            return biyVar;
        }
        LogUtil.c("DeviceCommandCapabilityConsultCommand", "Get CommandCapability checkResonpeCode Success.");
        String deviceMac = deviceInfo.getDeviceMac();
        DeviceCapability b = bjx.a().b(deviceMac);
        if (b == null) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "processReceivedData deviceCapability is null.");
            biyVar.c(13);
            biyVar.a(50103);
            return biyVar;
        }
        bhr.a(b, deviceInfo.getDeviceType());
        if (!d(a2, b)) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "CommandCapability parse Exception.");
            biyVar.c(13);
            biyVar.a(50103);
            return biyVar;
        }
        c(this.d, b);
        bhr.b(deviceInfo, b);
        bjx.a().c(deviceMac, b);
        biy d = d(b, biyVar);
        LogUtil.c("DeviceCommandCapabilityConsultCommand", "isSupportAccountSwitch: ", Boolean.valueOf(b.isSupportAccountSwitch()), " isSupportExpandCapability: ", Boolean.valueOf(b.isSupportExpandCapability()));
        if (!b.isSupportAccountSwitch() && !b.isSupportExpandCapability()) {
            bjx.a().d(deviceMac, bjx.a().a(deviceMac));
        }
        bmw.e(100044, deviceInfo.getDeviceName(), String.valueOf(d.d()), String.valueOf(d.b()));
        return d;
    }

    private biy d(DeviceCapability deviceCapability, biy biyVar) {
        bjc a2 = a(deviceCapability);
        if (!a2.b()) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "judgeExpandCapability Failed");
            biyVar.c(13);
            biyVar.a(50103);
            return biyVar;
        }
        this.mNextCommand = a2.d();
        if (this.mNextCommand == null) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "capability success, start oobe");
            biyVar.c(53);
            biyVar.a(100000);
            return biyVar;
        }
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private bir d(DeviceInfo deviceInfo) {
        this.c = bld.b();
        if (this.f367a == null || this.f367a.size() <= 0) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "get servicesList exception");
            return super.getDeviceCommand(deviceInfo);
        }
        Map<Integer, List<Integer>> d = ble.d(this.c);
        Iterator<Integer> it = this.f367a.iterator();
        int i = 0;
        while (it.hasNext()) {
            List<Integer> list = d.get(Integer.valueOf(it.next().intValue()));
            if (list != null && list.size() > 0) {
                i = i + 3 + list.size() + 2;
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(bhh.a(i).length + 3 + i);
        allocate.put((byte) 1).put((byte) 3).put(blq.g(129)).put(bhh.a(i));
        Iterator<Integer> it2 = this.f367a.iterator();
        while (it2.hasNext()) {
            int intValue = it2.next().intValue();
            List<Integer> list2 = d.get(Integer.valueOf(intValue));
            if (list2 != null && list2.size() > 0) {
                int size = list2.size();
                allocate.put((byte) 2).put((byte) 1).put(bhh.a(intValue));
                allocate.put((byte) 3).put(bhh.a(size));
                try {
                    Iterator<Integer> it3 = list2.iterator();
                    while (it3.hasNext()) {
                        allocate.put(bhh.a(it3.next().intValue()));
                    }
                } catch (BufferOverflowException unused) {
                    LogUtil.a("DeviceCommandCapabilityConsultCommand", "BufferOverflowException");
                }
            }
        }
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_DeviceCommandCapabilityConsultCommand", bhh.c("0103"));
        return deviceCommand;
    }

    private boolean d(byte[] bArr, DeviceCapability deviceCapability) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "tlvFather is null.");
            return false;
        }
        List<bmj> e2 = e.e();
        if (bkz.e(e2)) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "tlvFathers list is null.");
            return false;
        }
        for (bmj bmjVar : e2) {
            if (bmjVar == null) {
                LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData tlvFatherInfo is null.");
                return false;
            }
            if (!d(bmjVar, deviceCapability)) {
                LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData exception.");
                return false;
            }
        }
        return true;
    }

    private boolean d(bmj bmjVar, DeviceCapability deviceCapability) {
        if (bkz.e(bmjVar.b())) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData tlvList is empty");
            return false;
        }
        String str = "";
        int i = 0;
        for (bmi bmiVar : bmjVar.b()) {
            int e = bli.e(bmiVar.e());
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData subNodeType is:", Integer.valueOf(e));
            byte b = (byte) e;
            if (b == 2) {
                i = bli.e(bmiVar.c());
                if (i == 0) {
                    LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData serviceId exception.");
                    return false;
                }
            } else if (b == 4) {
                str = bmiVar.c();
                if (TextUtils.isEmpty(str) || i == 0) {
                    LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData commandSupportInfo exception.");
                    return false;
                }
                LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData commandSupportInfo is:", str);
                a(i, str, this.c);
            } else {
                LogUtil.c("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData default");
            }
        }
        if (!TextUtils.isEmpty(str) && i != 0) {
            return true;
        }
        LogUtil.a("DeviceCommandCapabilityConsultCommand", "parseTlvSubNodeData exception.");
        return false;
    }

    private void a(int i, String str, boolean z) {
        List<Integer> list = ble.d(z).get(Integer.valueOf(i));
        if (list == null) {
            LogUtil.a("DeviceCommandCapabilityConsultCommand", "commandIdList is null");
            return;
        }
        int length = str.length() / 2;
        for (int i2 = 0; i2 < length; i2++) {
            boolean a2 = a(str, i2);
            if (list.size() <= i2) {
                LogUtil.c("DeviceCommandCapabilityConsultCommand", "handleDeviceCommandSupportInfo finish");
            }
            if (i2 < list.size()) {
                this.d.put(i + "." + list.get(i2).intValue(), Boolean.valueOf(a2));
            }
        }
    }

    private boolean a(String str, int i) {
        int i2 = i * 2;
        return bli.e(str.substring(i2, i2 + 2)) == 1;
    }

    private void c(LinkedHashMap<String, Boolean> linkedHashMap, DeviceCapability deviceCapability) {
        bht.d(linkedHashMap, deviceCapability);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0103";
    }
}
