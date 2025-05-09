package defpackage;

import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.p2p.MessageParcel;
import health.compact.a.LogUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/* loaded from: classes7.dex */
public class sph {
    public static int b(bmi bmiVar) {
        String c = bmiVar.c();
        int e = !TextUtils.isEmpty(c) ? bli.e(c) : -1;
        LogUtil.c("P2pCommonUtil", "handleNum num is ", Integer.valueOf(e));
        return e;
    }

    public static List<bmi> d(byte[] bArr) {
        String d = blq.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.a("P2pCommonUtil", "info data is error");
            return null;
        }
        String substring = d.substring(4);
        LogUtil.d("P2pCommonUtil", "tlvsString is ", substring);
        try {
            return new bmn().c(substring).b();
        } catch (bmk unused) {
            LogUtil.e("P2pCommonUtil", "getResponseTlvFather TlvException");
            return null;
        }
    }

    public static List<bmi> e(byte[] bArr) {
        String d = blq.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.a("P2pCommonUtil", "info data is error");
            return null;
        }
        LogUtil.d("P2pCommonUtil", "tlvsString is ", d);
        try {
            return new bmn().c(d).b();
        } catch (bmk unused) {
            LogUtil.e("P2pCommonUtil", "getResponseTlvListWithHead TlvException");
            return null;
        }
    }

    public static MessageParcel e(spn spnVar) {
        File a2;
        if (spnVar == null) {
            return null;
        }
        MessageParcel messageParcel = new MessageParcel();
        int d = spnVar.d();
        messageParcel.setType(d);
        messageParcel.setEnableEncrypt(spnVar.g());
        messageParcel.setPrior(spnVar.c());
        if (d == 1) {
            messageParcel.setData(spnVar.b());
            return messageParcel;
        }
        if (d != 2 || (a2 = spnVar.a()) == null) {
            return messageParcel;
        }
        try {
            messageParcel.setParcelFileDescriptor(ParcelFileDescriptor.open(a2, 268435456));
        } catch (FileNotFoundException unused) {
            LogUtil.e("P2pCommonUtil", "convertToMessageParcel FileNotFoundException");
        }
        messageParcel.setFileName(a2.getName());
        messageParcel.setDescription(spnVar.e());
        messageParcel.setFileSha256(sov.e(a2));
        return messageParcel;
    }

    public static UniteDevice d(String str) {
        List<DeviceInfo> deviceMgrList = snq.c().getDeviceMgrList(1, str);
        UniteDevice e = (deviceMgrList == null || deviceMgrList.isEmpty()) ? null : e(deviceMgrList.get(0));
        Object[] objArr = new Object[2];
        objArr[0] = "uniteDevice is null ";
        objArr[1] = Boolean.valueOf(e == null);
        LogUtil.c("P2pCommonUtil", objArr);
        return e;
    }

    public static UniteDevice e(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            return null;
        }
        com.huawei.devicesdk.entity.DeviceInfo deviceInfo2 = new com.huawei.devicesdk.entity.DeviceInfo();
        deviceInfo2.setDeviceBtType(deviceInfo.getDeviceBluetoothType());
        deviceInfo2.setDeviceName(deviceInfo.getDeviceName());
        deviceInfo2.setDeviceMac(deviceInfo.getDeviceIdentify());
        deviceInfo2.setDeviceType(deviceInfo.getProductType());
        UniteDevice uniteDevice = new UniteDevice();
        uniteDevice.build(deviceInfo2.getDeviceMac(), deviceInfo2, null);
        return uniteDevice;
    }
}
