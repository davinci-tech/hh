package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.os.Build;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.haf.common.exception.ExceptionUtils;
import defpackage.bir;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes3.dex */
public class bgt extends HandshakeGeneralCommandBase {
    private int b;
    private boolean e;

    public bgt(int i, boolean z) {
        this.b = i;
        this.e = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x00c4  */
    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public defpackage.bir getDeviceCommand(com.huawei.devicesdk.entity.DeviceInfo r10) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.bgt.getDeviceCommand(com.huawei.devicesdk.entity.DeviceInfo):bir");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(50114);
            return biyVar;
        }
        int b = b(biuVar.a());
        if (b == 1) {
            if (deviceInfo.getDeviceBtType() == 2) {
                bmf.e(deviceInfo.getDeviceMac(), bgm.a(deviceInfo.getDeviceMac()));
            }
            this.mNextCommand = new bhk(this.b, !this.e);
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        ReleaseLogUtil.b("DEVMGR_BindRequestCommand", "not allow bond device", Integer.valueOf(b));
        if (bjr.e(deviceInfo.getDeviceMac())) {
            d(deviceInfo.getDeviceMac());
        }
        if (b == 0 && deviceInfo.getDeviceBtType() == 2) {
            biyVar.c(55);
            biyVar.a(50114);
            return biyVar;
        }
        biyVar.c(13);
        biyVar.a(50114);
        return biyVar;
    }

    private String a() {
        if (!bky.f()) {
            return "";
        }
        String d = blj.d(Build.MODEL);
        String b = blq.b(d);
        String str = (blq.b(10) + blq.a(b.length() / 2) + b) + (blq.b(11) + blq.b(1) + blq.b(10));
        LogUtil.c("BindRequestCommand", "get device mode type id:", d, " extendParam:", str);
        return str;
    }

    private String c() {
        try {
            BluetoothAdapter sM_ = bkw.d().sM_();
            if (sM_ != null) {
                String b = blq.b(sM_.getName());
                String a2 = blq.a(b.length() / 2);
                return blq.b(9) + a2 + b;
            }
        } catch (SecurityException e) {
            LogUtil.e("BindRequestCommand", "getPhoneModelTlv SecurityException:", ExceptionUtils.d(e));
        }
        return "";
    }

    private static String e() {
        return blq.b(1) + blq.b(0);
    }

    private static String d() {
        return blq.b(3) + blq.b(1) + blq.b(0);
    }

    private String c(DeviceInfo deviceInfo) {
        String d = bml.d();
        if (deviceInfo != null) {
            String c = bmf.c(deviceInfo.getDeviceMac());
            if (!"".equals(c)) {
                d = blq.d(c);
            }
        }
        if (!this.e) {
            return d;
        }
        String c2 = bkx.c();
        return (c2 == null || c2.length() < 6) ? c2 : c2.substring(c2.length() - 6);
    }

    private static String e(String str, String str2) {
        String d;
        String d2 = blq.d(bjr.a());
        bjp.d().c(str2, d2);
        bmf.d(str2, 1, bmy.b(d2));
        bmf.d(str2, 2, bmy.b(d2));
        String e = bjp.d().e(str2);
        byte[] a2 = blq.a(str);
        byte[] a3 = blq.a(e);
        biw c = bjx.a().c(str2);
        if (c != null && c.c() == 1) {
            LogUtil.c("BindRequestCommand", "getBondKey encrypt ByGcm");
            d = blq.d(bgv.e("AES/GCM/NoPadding", blq.a(d2), a3, a2));
        } else {
            d = blq.d(bgv.e("AES/CBC/PKCS5Padding", blq.a(d2), a3, a2));
        }
        if (d == null) {
            return d;
        }
        return blq.b(6) + blq.b(d.length() / 2) + d;
    }

    private String b(String str) {
        if (str != null && str.length() > 64) {
            String substring = str.substring(0, 32);
            return blq.b(7) + blq.b(substring.length() / 2) + substring;
        }
        LogUtil.e("BindRequestCommand", "getBtIv, key:null.");
        return "";
    }

    private String a(String str) {
        if (str != null && str.length() > 64) {
            return bhh.a(str);
        }
        LogUtil.e("BindRequestCommand", "getBtBondKey, key:null.");
        return "";
    }

    private int b(byte[] bArr) {
        if (bArr == null) {
            LogUtil.a("BindRequestCommand", "parse data error, dataContent is null.");
            return 0;
        }
        bmj e = bhh.e(bArr);
        if (e == null) {
            LogUtil.a("BindRequestCommand", "process data error, tlvFather is null");
            return 0;
        }
        List<bmi> b = e.b();
        if (b == null || b.size() <= 0) {
            return 0;
        }
        return bli.e(b.get(0).c());
    }

    private void d(String str) {
        bmf.d(str, 1, "");
        bmf.d(str, 2, "");
    }

    private bir b(DeviceInfo deviceInfo, ByteBuffer byteBuffer) {
        bir birVar = new bir();
        if (deviceInfo == null) {
            LogUtil.e("BindRequestCommand", "device info is empty when buildCommandMessage");
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
        return "010E";
    }
}
