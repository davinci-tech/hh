package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.hms.hihealth.HiHealthStatusCodes;
import com.huawei.hms.network.okhttp.PublicSuffixDatabase;
import health.compact.a.BuildConfigProperties;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class bhk extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private int f371a;
    private boolean c;

    private byte[] e() {
        return new byte[]{1, 2, 7, 9, 10, BaseType.Array, BaseType.Vector64, 22, 26, 29, 30, 31, 32, PublicSuffixDatabase.i, 34, 35};
    }

    public bhk(boolean z) {
        this(0, z);
    }

    public bhk(int i, boolean z) {
        this.f371a = i;
        this.c = z;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        ByteBuffer allocate;
        if (deviceInfo != null && deviceInfo.getDeviceBtType() == 0) {
            byte[] d = bii.d();
            allocate = ByteBuffer.allocate(d.length);
            allocate.put(d);
        } else {
            int length = bhh.a(deviceInfo) ? 4 : e().length;
            allocate = ByteBuffer.allocate((length * 2) + 2);
            allocate.put((byte) 1).put((byte) 7);
            for (int i = 0; i < length; i++) {
                allocate.put(e()[i]).put((byte) 0);
            }
        }
        bir deviceCommand = super.getDeviceCommand(deviceInfo);
        deviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_GetDeviceVersionCommand", bhh.c("0107"));
        return deviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(50107);
            return biyVar;
        }
        byte[] a2 = biuVar.a();
        if (!bhh.d(a2)) {
            if (this.c) {
                LogUtil.c("GetDeviceVersionCommand", "Resend 5.1.14 command");
                this.mNextCommand = new bgt(this.f371a, true);
                biyVar.c(12);
                biyVar.a(100000);
                return biyVar;
            }
            biyVar.c(13);
            biyVar.a(50107);
            return biyVar;
        }
        if (!a(a2, deviceInfo)) {
            LogUtil.a("GetDeviceVersionCommand", "Get DeviceVersion processReceivedData Failed.");
            biyVar.c(13);
            biyVar.a(50107);
            return biyVar;
        }
        bhh.e(deviceInfo);
        if (!b(deviceInfo)) {
            ReleaseLogUtil.a("DEVMGR_GetDeviceVersionCommand", "not exist 0x15 hilink device id.");
            biyVar.c(13);
            biyVar.a(HiHealthStatusCodes.INVALID_ACTIVITY_TYPE_IN_ACTIVITY_RECORD);
            return biyVar;
        }
        if (!e(deviceInfo)) {
            ReleaseLogUtil.a("DEVMGR_GetDeviceVersionCommand", "not exist 0x09 sn.");
            biyVar.c(13);
            biyVar.a(HiHealthStatusCodes.INVALID_ACTIVITY_TYPE_IN_ACTIVITY_RECORD);
            return biyVar;
        }
        LogUtil.c("GetDeviceVersionCommand", "Get DeviceVersion processReceivedData Success.");
        this.mNextCommand = new bhn(false);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private boolean a(byte[] bArr, DeviceInfo deviceInfo) {
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("GetDeviceVersionCommand", "tlvFather is null");
            return false;
        }
        for (bmi bmiVar : e.b()) {
            int e2 = bli.e(bmiVar.e());
            String c = bmiVar.c();
            LogUtil.c("GetDeviceVersionCommand", "the case is ", Integer.valueOf(bli.e(bmiVar.e())));
            b(e2, c, deviceInfo);
        }
        deviceInfo.setUdid(blo.a(deviceInfo.getDeviceMac(), blq.d(deviceInfo.getDeviceSn())));
        return true;
    }

    private void b(int i, String str, DeviceInfo deviceInfo) {
        if (str == null) {
            return;
        }
        a(i, str, deviceInfo);
        e(i, str, deviceInfo);
        c(i, str, deviceInfo);
        d(deviceInfo);
    }

    private void d(DeviceInfo deviceInfo) {
        biw c = bjx.a().c(deviceInfo.getDeviceMac());
        LogUtil.c("GetDeviceVersionCommand", "getSingleFrameDevice: ", Integer.valueOf(c.f()));
        deviceInfo.setSingleFrameDevice(c.f());
    }

    private void a(int i, String str, DeviceInfo deviceInfo) {
        byte b = (byte) i;
        if (b == 1) {
            deviceInfo.setBtVersion(blq.d(str));
            return;
        }
        if (b == 3) {
            deviceInfo.setDeviceVersion(blq.d(str));
            return;
        }
        if (b == 7) {
            deviceInfo.setDeviceSoftVersion(blq.d(str));
            return;
        }
        if (b != 25) {
            switch (b) {
                case 36:
                    deviceInfo.setDeviceEnterpriseVersion(blq.d(str));
                    break;
                case 37:
                    deviceInfo.setDeviceSplicingProductVersion(blq.d(str));
                    break;
                case 38:
                    deviceInfo.setDeviceOStVersion(blq.d(str));
                    break;
                case 39:
                    deviceInfo.setDeviceOtaSignatureLength(bli.e(str));
                    break;
                case 40:
                    deviceInfo.setSaleInfo(blq.d(str));
                    break;
                case 41:
                    deviceInfo.setPsiSignature(blq.d(str));
                    break;
            }
            return;
        }
        deviceInfo.setDeviceSubProdId(blq.d(str));
    }

    private void e(int i, String str, DeviceInfo deviceInfo) {
        byte b = (byte) i;
        if (b == 2) {
            deviceInfo.setDeviceType(bli.e(str));
            return;
        }
        if (b == 10) {
            deviceInfo.setDeviceMode(blq.d(str));
            return;
        }
        if (b == 14) {
            deviceInfo.setIdToServerType(bli.e(str));
            return;
        }
        if (b == 22) {
            deviceInfo.setDeviceBtMode(blq.d(str));
            return;
        }
        if (b == 26) {
            deviceInfo.setDeviceVersionType(bli.e(str));
        } else if (b == 17) {
            deviceInfo.setCertMode(blq.d(str));
        } else {
            if (b != 18) {
                return;
            }
            deviceInfo.setPowerSaveMode(bli.e(str));
        }
    }

    private void c(int i, String str, DeviceInfo deviceInfo) {
        byte b = (byte) i;
        if (b == 9) {
            deviceInfo.setDeviceSn(str);
            return;
        }
        if (b == 12) {
            String d = blq.d(str);
            ReleaseLogUtil.b("DEVMGR_GetDeviceVersionCommand", "5.1.7 deviceName is ", d);
            deviceInfo.setDeviceName(d);
            return;
        }
        if (b == 15) {
            LogUtil.c("GetDeviceVersionCommand", "OTA package name is: ", blq.d(str));
            deviceInfo.setDeviceOtaPackageName(blq.d(str));
            return;
        }
        if (b == 27) {
            deviceInfo.setSportVersion(bli.e(str));
            return;
        }
        if (b == 20) {
            int e = bli.e(str);
            LogUtil.c("GetDeviceVersionCommand", "Device Ota Area Type:", Integer.valueOf(e));
            deviceInfo.setDeviceOtaAreaType(e);
            return;
        }
        if (b != 21) {
            switch (b) {
                case 29:
                    deviceInfo.setDfxDeviceUdid(blq.d(str));
                    break;
                case 30:
                    deviceInfo.setDfxDeviceUdidParameter(blq.d(str));
                    break;
                case 31:
                    deviceInfo.setDeviceFactoryReset(bli.e(str));
                    break;
                case 32:
                    deviceInfo.setCountryCode(blq.d(str));
                    break;
                case 33:
                    deviceInfo.setEmuiVersion(blq.d(str));
                    break;
                case 34:
                    deviceInfo.setMultiLinkBleMac(blq.d(str));
                    break;
                case 35:
                    b(str, deviceInfo);
                    break;
            }
            return;
        }
        deviceInfo.setDeviceHilinkId(bmz.d(deviceInfo.getDeviceType(), blq.d(str)));
    }

    private void b(String str, DeviceInfo deviceInfo) {
        List<String> asList = Arrays.asList(str.split("2C"));
        for (int i = 0; i < asList.size(); i++) {
            if (i == 0 || i == 1) {
                String str2 = asList.get(i);
                if (str2.endsWith("00") && str2.length() > 2) {
                    asList.set(i, str2.substring(0, str2.length() - 2));
                }
            }
        }
        deviceInfo.setFieldList(asList);
        LogUtil.c("GetDeviceVersionCommand", "the arrayList size is ", Integer.valueOf(asList.size()));
    }

    private boolean b(DeviceInfo deviceInfo) {
        if (!BuildConfigProperties.e("IS_RELEASE_VERSION", false) && deviceInfo.getDeviceType() >= 55) {
            String deviceHilinkId = deviceInfo.getDeviceHilinkId();
            if (TextUtils.isEmpty(deviceHilinkId) || deviceHilinkId.length() == 0) {
                LogUtil.c("GetDeviceVersionCommand", "isExistHiLinkDeviceId not exist hilink device id.");
                return false;
            }
            if (deviceHilinkId.length() != 4) {
                LogUtil.c("GetDeviceVersionCommand", "isExistHiLinkDeviceId hilinkDeviceId length is error.");
                return false;
            }
        }
        LogUtil.c("GetDeviceVersionCommand", "isExistHiLinkDeviceId exist hilink device id.");
        return true;
    }

    private boolean e(DeviceInfo deviceInfo) {
        if (!BuildConfigProperties.e("IS_RELEASE_VERSION", false) && deviceInfo.getDeviceType() >= 72 && deviceInfo.getDeviceType() != 75 && TextUtils.isEmpty(deviceInfo.getDeviceSn())) {
            LogUtil.a("GetDeviceVersionCommand", "sn is invalid.");
            return false;
        }
        LogUtil.c("GetDeviceVersionCommand", "isExistSn new device sn is empty, Pairing is not allowed.");
        return true;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0107";
    }
}
