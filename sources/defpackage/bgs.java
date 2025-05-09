package defpackage;

import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class bgs extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private boolean f366a = false;
    private String b;
    private biw c;
    private String e;

    public bgs(String str, String str2, biw biwVar) {
        this.b = str2;
        this.e = str;
        this.c = biwVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (this.c == null || this.e == null || this.b == null || deviceInfo == null) {
            LogUtil.a("AccessValidityAuthCommand", "getDeviceCommand error, parameter check is null");
            return super.getDeviceCommand(deviceInfo);
        }
        String str = blq.b(1) + blq.b(19) + (blq.b(1) + blq.b(this.e.length() / 2) + this.e) + (blq.b(2) + blq.b((this.b.length() / 2) + 2) + blq.c(this.c.a()) + this.b);
        if (bjr.e(deviceInfo.getDeviceMac())) {
            str = str + (blq.b(3) + blq.b(1) + blq.b(bjp.d().c(deviceInfo.getDeviceMac())));
        }
        byte[] a2 = blq.a(str);
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(a2);
        ReleaseLogUtil.b("DEVMGR_AccessValidityAuthCommand", bhh.c("0113"));
        return unencryptedDeviceCommand;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(50119);
            return biyVar;
        }
        bmj e = bhh.e(biuVar.a());
        if (e == null || bkz.e(e.b())) {
            LogUtil.a("AccessValidityAuthCommand", "parse Data To Tlv failed");
            biyVar.c(13);
            biyVar.a(50119);
            return biyVar;
        }
        Iterator<bmi> it = e.b().iterator();
        if (it.hasNext()) {
            bmi next = it.next();
            int e2 = bli.e(next.e());
            if (e2 == 1) {
                return c(deviceInfo, next.c());
            }
            if (e2 == 127) {
                return e(deviceInfo, bli.e(next.c()));
            }
            LogUtil.e("AccessValidityAuthCommand", "Unknown type : ", next.e());
            biyVar.c(13);
            biyVar.a(50119);
            return biyVar;
        }
        biyVar.c(13);
        biyVar.a(50119);
        return biyVar;
    }

    private biy e(DeviceInfo deviceInfo, int i) {
        ReleaseLogUtil.c("DEVMGR_AccessValidityAuthCommand", "Error code is : ", Integer.valueOf(i));
        biy biyVar = new biy();
        String deviceMac = deviceInfo.getDeviceMac();
        if (!bjr.e(deviceInfo.getDeviceMac())) {
            LogUtil.e("AccessValidityAuthCommand", "Error code is : ", Integer.valueOf(i));
            bhh.d(deviceMac);
            biyVar.c(13);
            biyVar.a(50119);
            return biyVar;
        }
        if (i == 100012) {
            bmf.e();
            LogUtil.e("AccessValidityAuthCommand", "key lost");
            biyVar.c(14);
            biyVar.a(50119);
            return biyVar;
        }
        if (i == 100011) {
            String a2 = bjr.a(deviceMac, 1);
            bjp.d().a(a2, deviceMac);
            bmf.d(deviceMac, 2, bmy.b(a2));
            if (TextUtils.isEmpty(a2)) {
                LogUtil.e("AccessValidityAuthCommand", "key lost");
                bmf.e();
                biyVar.c(14);
                biyVar.a(50119);
                return biyVar;
            }
            if (this.c != null) {
                this.mNextCommand = new bgs(blq.d(bjr.c(a2 + "0100", this.c.j() + this.b)), this.b, this.c);
                biyVar.c(12);
                biyVar.a(100000);
                return biyVar;
            }
        }
        bhh.d(deviceMac);
        biyVar.c(13);
        biyVar.a(50119);
        return biyVar;
    }

    private biy c(DeviceInfo deviceInfo, String str) {
        biy biyVar = new biy();
        boolean d = d(deviceInfo, str);
        bhh.d(deviceInfo.getDeviceMac());
        LogUtil.c("AccessValidityAuthCommand", "checkResult: ", Boolean.valueOf(d));
        if (d) {
            if (bjr.e(deviceInfo.getDeviceMac()) || deviceInfo.getDeviceBtType() == 2) {
                this.mNextCommand = new bhl(this.c);
            } else {
                this.mNextCommand = new bhk(false);
            }
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        if (this.f366a) {
            LogUtil.c("AccessValidityAuthCommand", "connect failed, key lost");
            biyVar.c(14);
            biyVar.a(50119);
            return biyVar;
        }
        biyVar.c(13);
        biyVar.a(50119);
        return biyVar;
    }

    private boolean d(DeviceInfo deviceInfo, String str) {
        String c;
        LogUtil.c("AccessValidityAuthCommand", "Enter checkAuthenticTokenValue.");
        biw biwVar = this.c;
        if (biwVar != null && biwVar.j() != null && 16 == this.c.j().length() / 2 && 16 == this.b.length() / 2) {
            if (bjr.e(deviceInfo.getDeviceMac())) {
                c = bjr.d(deviceInfo, AgdConstant.INSTALL_TYPE_AUTO, this.c.j() + this.b, this.c.a());
                if (TextUtils.isEmpty(c)) {
                    this.f366a = true;
                }
            } else {
                c = bhh.c(this.c, this.b, AgdConstant.INSTALL_TYPE_AUTO);
            }
            if (c != null) {
                return c.equalsIgnoreCase(str);
            }
            LogUtil.a("AccessValidityAuthCommand", "0xA0200008", " Authentic codeInfoHex is incorrect.");
            return false;
        }
        LogUtil.a("AccessValidityAuthCommand", "0xA0200008", " Authentic Random parameter is incorrect.");
        return false;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "0113";
    }
}
