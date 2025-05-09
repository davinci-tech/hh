package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class bhf extends HandshakeGeneralCommandBase {
    bhf() {
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("DeviceServiceCapabilityConsultCommand", "deviceInfo is null");
            return super.getDeviceCommand(deviceInfo);
        }
        return b(deviceInfo);
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            LogUtil.a("DeviceServiceCapabilityConsultCommand", "Get ServiceCapability parameterCheck Failed.");
            biyVar.c(13);
            biyVar.a(50102);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            LogUtil.c("DeviceServiceCapabilityConsultCommand", "Get ServiceCapability checkResponseCode Failed.");
            biyVar.c(13);
            biyVar.a(50102);
            return biyVar;
        }
        LogUtil.c("DeviceServiceCapabilityConsultCommand", "Get ServiceCapability checkResponseCode Success.");
        ArrayList arrayList = new ArrayList();
        if (!e(deviceInfo, a2, arrayList)) {
            LogUtil.a("DeviceServiceCapabilityConsultCommand", "Get ServiceCapability parseData Failed.");
            biyVar.c(13);
            biyVar.a(50102);
            return biyVar;
        }
        bjx.a().l(deviceInfo.getDeviceMac());
        this.mNextCommand = new bgx(arrayList);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private bir b(DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        ArrayList arrayList = new ArrayList();
        if (deviceInfo.isMultiLink()) {
            arrayList.add(1);
            arrayList.add(23);
        } else {
            arrayList.addAll(ble.b());
        }
        int size = arrayList.size();
        biz i = bjx.a().i(deviceInfo.getDeviceMac());
        if (i != null && i.b() > 0) {
            allocate = ByteBuffer.allocate(size + 6);
        } else {
            allocate = ByteBuffer.allocate(size + 3);
        }
        allocate.put((byte) 1).put((byte) 2);
        allocate.put((byte) 1).put(bhh.a(size - 1));
        for (int i2 = 1; i2 < size; i2++) {
            allocate.put(bhh.a(((Integer) arrayList.get(i2)).intValue()));
        }
        if (i != null && i.b() > 0) {
            allocate.put((byte) 3);
            allocate.put(bhh.a(String.valueOf(i.b()).length()));
            allocate.put(bhh.a(i.b()));
        }
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_DeviceServiceCapabilityConsultCommand", bhh.c("0102"));
        return deviceCommand;
    }

    private boolean e(DeviceInfo deviceInfo, byte[] bArr, List<Integer> list) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("DeviceServiceCapabilityConsultCommand", "tlvFather is null.");
            return false;
        }
        List<bmi> b = e.b();
        if (bkz.e(b)) {
            LogUtil.a("DeviceServiceCapabilityConsultCommand", "TLV info is incorrect.");
            return false;
        }
        String c = b.get(0).c();
        ArrayList arrayList = new ArrayList();
        if (deviceInfo.isMultiLink()) {
            arrayList.add(1);
            arrayList.add(23);
        }
        arrayList.addAll(ble.b());
        if (TextUtils.isEmpty(c)) {
            LogUtil.a("DeviceServiceCapabilityConsultCommand", "servicesSupportInfo length exception");
            return false;
        }
        d(c, arrayList, list);
        return true;
    }

    private void d(String str, List<Integer> list, List<Integer> list2) {
        list2.add(1);
        int length = str.length() / 2;
        int size = list.size();
        for (int i = 0; i < length; i++) {
            int d = d(str, i);
            if (i < size - 1 && d == 1) {
                list2.add(list.get(i + 1));
            }
        }
    }

    private int d(String str, int i) {
        int i2 = i * 2;
        return bli.e(str.substring(i2, i2 + 2));
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0102";
    }
}
