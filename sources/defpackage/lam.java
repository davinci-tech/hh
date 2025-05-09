package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogAnonymous;
import java.io.UnsupportedEncodingException;

/* loaded from: classes5.dex */
public class lam extends lan {
    private DeviceInformation c = new DeviceInformation();

    public DeviceInformation e() {
        return this.c;
    }

    public DeviceInformation b(byte[] bArr, int i) {
        String b = b(bArr);
        if (this.c != null) {
            switch (i) {
                case 20006:
                    LogUtil.c("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: mManufacturerString:", b);
                    this.c.setManufacturerString(b);
                    break;
                case 20007:
                    LogUtil.c("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: mModelNumber:", b);
                    this.c.setModelString(b);
                    break;
                case 20010:
                    LogUtil.c("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: dataSerialNumber:");
                    this.c.setSerialNumber(b);
                    break;
                case CapabilityStatus.AWA_CAP_CODE_WIFI /* 20011 */:
                    LogUtil.a("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: Hardware Revision String:", b);
                    this.c.setHardwareVersion(b);
                    break;
                case CapabilityStatus.AWA_CAP_CODE_APPLICATION /* 20012 */:
                    LogUtil.a("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: Software Revision StringRead:", b);
                    this.c.setSoftwareVersion(b);
                    break;
                case 20015:
                    LogUtil.a("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: Firmware Revision String:", b);
                    this.c.setFirmwareVersion(b);
                    break;
                case 20016:
                    LogUtil.c("Track_IDEQ_DisParser", "(FTMP_TS) parseDeviceInfoData: System ID:", b);
                    this.c.setSystemId(b);
                    break;
            }
        }
        return this.c;
    }

    private String b(byte[] bArr) {
        String str;
        try {
            str = new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("Track_IDEQ_DisParser", LogAnonymous.b((Throwable) e));
            str = "";
        }
        return !TextUtils.isEmpty(str) ? str.trim() : str;
    }

    public void d(String str, int i) {
        DeviceInformation deviceInformation;
        if (i != 20014 || (deviceInformation = this.c) == null) {
            return;
        }
        deviceInformation.setBleDeviceName(str);
    }
}
