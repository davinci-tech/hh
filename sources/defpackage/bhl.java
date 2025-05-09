package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.haf.application.BaseApplication;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes3.dex */
public class bhl extends HandshakeGeneralCommandBase {

    /* renamed from: a, reason: collision with root package name */
    private biw f372a;
    private bis c = null;

    public bhl(biw biwVar) {
        this.f372a = biwVar;
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public bir getDeviceCommand(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            LogUtil.e("QueryBtBindStatusCommand", "deviceInfo is null");
            return getUnencryptedDeviceCommand(deviceInfo);
        }
        byte[] d = d(deviceInfo.getDeviceMac());
        byte[] c = c(deviceInfo);
        ByteBuffer allocate = ByteBuffer.allocate(c.length + 11 + d.length);
        allocate.put((byte) 1).put(BaseType.Obj);
        allocate.put((byte) 1).put((byte) 0);
        allocate.put(c).put((byte) 4);
        allocate.put((byte) 1).put((byte) 2);
        allocate.put((byte) 5).put((byte) 0);
        allocate.put(d);
        allocate.put((byte) 9).put((byte) 0);
        bir unencryptedDeviceCommand = getUnencryptedDeviceCommand(deviceInfo);
        unencryptedDeviceCommand.e(allocate.array());
        ReleaseLogUtil.b("DEVMGR_QueryBtBindStatusCommand", bhh.c("010F"));
        return unencryptedDeviceCommand;
    }

    private byte[] c(DeviceInfo deviceInfo) {
        String d = bml.d();
        if (deviceInfo != null) {
            String c = bmf.c(deviceInfo.getDeviceMac());
            if (!"".equals(c)) {
                d = blq.d(c);
            } else {
                String d2 = blb.d(deviceInfo.getDeviceMac(), BaseApplication.e());
                if (!"".equals(d2)) {
                    d = blq.d(d2);
                    bmf.c(deviceInfo.getDeviceMac(), d2);
                    blb.d(deviceInfo.getDeviceMac(), "", "btsdk_sharedpreferences_bindid", BaseApplication.e());
                    b(deviceInfo);
                } else {
                    bmf.c(deviceInfo.getDeviceMac(), blq.b(d));
                }
            }
        }
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.put((byte) 3).put((byte) 6);
        allocate.put(blq.a(blq.b(d)));
        return allocate.array();
    }

    private void b(DeviceInfo deviceInfo) {
        LogUtil.c("QueryBtBindStatusCommand", "Enter updateBindKey().");
        if ("".equals(bmf.b(deviceInfo.getDeviceMac()))) {
            LogUtil.c("QueryBtBindStatusCommand", "updateBindKey() bindKey equals null.");
            bmf.e(deviceInfo.getDeviceMac(), blb.c(deviceInfo.getDeviceMac(), BaseApplication.e()));
            blb.d(deviceInfo.getDeviceMac(), "", "btsdk_sharedpreferences_name4", BaseApplication.e());
        }
    }

    private byte[] d(String str) {
        biz i = bjx.a().i(str);
        String a2 = (i == null || i.a() == null) ? "FF:FF:FF:FF:FF:CC" : i.a();
        return blq.a(blq.b(7) + blq.b(a2.length()) + blq.b(a2));
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeGeneralCommandBase, com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public biy processReceivedData(DeviceInfo deviceInfo, biu biuVar) {
        biy biyVar = new biy();
        if (!bhh.c(deviceInfo, biuVar)) {
            biyVar.c(13);
            biyVar.a(50115);
            return biyVar;
        }
        if (this.f372a == null) {
            LogUtil.e("QueryBtBindStatusCommand", "mDeviceLinkParameter is null");
            biyVar.c(13);
            biyVar.a(50115);
            return biyVar;
        }
        bmj e = bhh.e(biuVar.a());
        if (e == null) {
            LogUtil.a("QueryBtBindStatusCommand", "tlvFather is null");
            biyVar.c(13);
            biyVar.a(50115);
            return biyVar;
        }
        bis e2 = e(deviceInfo.getDeviceMac(), e.b());
        this.c = e2;
        if (e2 == null) {
            LogUtil.e("QueryBtBindStatusCommand", "bondState parameter is incorrect.");
            biyVar.c(13);
            biyVar.a(50115);
            return biyVar;
        }
        if (bjr.e(deviceInfo.getDeviceMac())) {
            return a(biyVar, deviceInfo.getDeviceMac(), deviceInfo.getDeviceBtType());
        }
        if (deviceInfo.getDeviceBtType() == 2) {
            return c(biyVar, deviceInfo.getDeviceMac());
        }
        biyVar.c(13);
        biyVar.a(50115);
        return biyVar;
    }

    private bis e(String str, List<bmi> list) {
        if (str == null || list == null || list.size() <= 0) {
            return null;
        }
        if (bgm.e(str)) {
            bgm.c(str, 0L);
        }
        bis bisVar = new bis();
        for (bmi bmiVar : list) {
            int e = bli.e(bmiVar.e());
            String c = bmiVar.c();
            byte b = (byte) e;
            if (b == 1) {
                bisVar.d(bli.e(c));
            } else if (b == 2) {
                bisVar.a(bli.e(c));
            } else if (b == 4) {
                bisVar.e(bli.e(c));
            } else if (b == 5) {
                bisVar.c(bli.e(c));
            } else if (b == 9) {
                try {
                    bgm.c(str, Long.parseLong(c, 16));
                } catch (NumberFormatException unused) {
                    LogUtil.e("QueryBtBindStatusCommand", "saveReceiveData NumberFormatException");
                }
            } else if (b == Byte.MAX_VALUE) {
                return null;
            }
        }
        return bisVar;
    }

    private biy a(biy biyVar, String str, int i) {
        if (i == 1 && this.c.e() != 1) {
            LogUtil.c("QueryBtBindStatusCommand", "receive bond status incorrect response.hichainlite");
            biyVar.c(13);
            biyVar.a(50115);
            return biyVar;
        }
        if (bjp.d().h(str)) {
            String a2 = bjr.a(str, 2);
            if (TextUtils.isEmpty(a2)) {
                biyVar.c(14);
                biyVar.a(50115);
                return biyVar;
            }
            byte[] a3 = bjr.a();
            bjp.d().d(str, blq.d(a3));
            byte[] a4 = bjr.a();
            String b = b(a3, blq.a(a2), a4);
            LogUtil.c("QueryBtBindStatusCommand", "HiChainLite Retry15:bondKeyValue");
            this.mNextCommand = new bhu(b, blq.d(a4));
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        if (i == 2 && this.c.e() == 1) {
            String d = blq.d(bjr.a());
            this.mNextCommand = new bhu(e(d, str), d);
            biyVar.c(12);
            biyVar.a(100000);
            return biyVar;
        }
        this.mNextCommand = new bgt(this.f372a.a(), false);
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private String b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        String str;
        biw biwVar = this.f372a;
        if (biwVar == null || biwVar.c() != 1) {
            str = "AES/CBC/PKCS5Padding";
        } else {
            LogUtil.c("QueryBtBindStatusCommand", "encrypt by gcm use aes_gcm");
            str = "AES/GCM/NoPadding";
        }
        return blq.d(bgv.e(str, bArr, bArr2, bArr3));
    }

    private String e(String str, String str2) {
        LogUtil.c("QueryBtBindStatusCommand", "Enter getBleBondKeyForHichainLite.");
        String d = blq.d(bjr.a());
        bjp.d().c(str2, d);
        bmf.d(str2, 1, bmy.b(d));
        bmf.d(str2, 2, bmy.b(d));
        String e = bjp.d().e(str2);
        byte[] a2 = blq.a(str);
        return b(blq.a(d), blq.a(e), a2);
    }

    private biy c(biy biyVar, String str) {
        int a2 = this.c.a();
        if (a2 != 0) {
            this.f372a.f(a2);
        } else {
            this.f372a.f(128);
        }
        String b = bmf.b(str);
        a(str, b);
        if (this.c.e() == 0 || TextUtils.isEmpty(b)) {
            this.mNextCommand = new bgt(this.f372a.a(), false);
        } else {
            bmf.b(str, blq.d(bmy.c(bgm.c(str, this.f372a.a()))));
            this.mNextCommand = new bhk(this.f372a.a(), true);
        }
        biyVar.c(12);
        biyVar.a(100000);
        return biyVar;
    }

    private void a(String str, String str2) {
        if (str == null || str2 == null || str2.length() != 64) {
            return;
        }
        LogUtil.c("QueryBtBindStatusCommand", "Need to reset v2 key info empty.");
        bmf.e(str, "");
    }

    @Override // com.huawei.devicesdk.connect.handshake.HandshakeCommandBase
    public String getTag() {
        return "010F";
    }
}
