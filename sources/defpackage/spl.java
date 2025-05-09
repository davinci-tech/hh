package defpackage;

import android.text.TextUtils;
import com.google.flatbuffers.reflection.BaseType;
import com.huawei.devicesdk.entity.SendMode;
import com.huawei.indoorequip.datastruct.MachineControlPointResponse;
import com.huawei.unitedevice.entity.UniteDevice;
import com.huawei.unitedevice.p2p.IdentityInfo;
import com.huawei.unitedevice.p2p.MessageParcel;
import defpackage.bir;
import defpackage.bmt;
import health.compact.a.LogUtil;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public class spl {
    public static bir c(UniteDevice uniteDevice, int i, int i2, String str, String str2, int i3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.a("P2pCommandUtil", "DeviceCommand srcPkgName or destPkgName is null");
            return null;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("identitySrcInfoKey", new IdentityInfo(str, ""));
        hashMap.put("identityDestInfoKey", new IdentityInfo(str2, ""));
        byte[] b = b(uniteDevice, i, i2, hashMap, (byte[]) null);
        bir birVar = new bir();
        birVar.e(b);
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.d(i3);
        return birVar;
    }

    public static bir d(UniteDevice uniteDevice, int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, MessageParcel messageParcel) {
        byte[] data = messageParcel.getData();
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.a("P2pCommandUtil", "getSendCommand pkgInfo is null");
            return null;
        }
        if (TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.a("P2pCommandUtil", "getSendCommand srcPkgName or destPkgName is null");
            return null;
        }
        if (data == null || data.length == 0) {
            LogUtil.a("P2pCommandUtil", "getSendCommand data is valid");
            return null;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("identitySrcInfoKey", identityInfo);
        hashMap.put("identityDestInfoKey", identityInfo2);
        bir d = d(messageParcel, b(uniteDevice, i, 2, hashMap, data));
        d.d(messageParcel.getPrior());
        return d;
    }

    public static bir b(int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, int i2, int i3) {
        if (identityInfo == null || identityInfo2 == null) {
            LogUtil.a("P2pCommandUtil", "getResponseCommand pkgInfo is null");
            return null;
        }
        if (TextUtils.isEmpty(identityInfo.getPackageName()) || TextUtils.isEmpty(identityInfo2.getPackageName())) {
            LogUtil.a("P2pCommandUtil", "getResponseCommand srcPkgName or destPkgName is null");
            return null;
        }
        byte[] d = d(i, identityInfo, identityInfo2, i2, i3);
        ByteBuffer allocate = ByteBuffer.allocate(d.length + 2);
        allocate.put((byte) 52).put((byte) 1).put(d);
        bir birVar = new bir();
        birVar.e(allocate.array());
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        return birVar;
    }

    private static byte[] d(int i, IdentityInfo identityInfo, IdentityInfo identityInfo2, int i2, int i3) {
        byte[] b = b(false, i, 3, identityInfo, identityInfo2);
        byte[] g = blq.g(8);
        byte[] i4 = blq.i(i3);
        byte[] d = blq.d(i4.length);
        byte[] bArr = new byte[b.length + g.length + i4.length + d.length];
        System.arraycopy(b, 0, bArr, 0, b.length);
        System.arraycopy(g, 0, bArr, b.length, g.length);
        System.arraycopy(d, 0, bArr, b.length + g.length, d.length);
        System.arraycopy(i4, 0, bArr, b.length + g.length + d.length, i4.length);
        return bArr;
    }

    private static byte[] b(UniteDevice uniteDevice, int i, int i2, Map<String, IdentityInfo> map, byte[] bArr) {
        boolean z;
        byte b;
        IdentityInfo identityInfo = map.get("identitySrcInfoKey");
        IdentityInfo identityInfo2 = map.get("identityDestInfoKey");
        if (identityInfo == null || identityInfo2 == null) {
            return null;
        }
        boolean e = bla.e(uniteDevice, MachineControlPointResponse.OP_CODE_EXTENSION_MULTI_DATA);
        LogUtil.c("P2pCommandUtil", "isSupportBtProxy:", Boolean.valueOf(e), "srcInfo.getPackageName():", identityInfo.getPackageName(), "destInfo.getPackageName():", identityInfo2.getPackageName());
        if (e && "hw.unitedevice.btproxy".equals(identityInfo.getPackageName()) && "com.huawei.health.btproxy".equals(identityInfo2.getPackageName())) {
            b = 57;
            z = true;
        } else {
            z = false;
            b = 52;
        }
        byte[] b2 = b(z, i, i2, identityInfo, identityInfo2);
        if (bArr != null && bArr.length > 0) {
            byte[] g = blq.g(7);
            byte[] d = blq.d(bArr.length);
            ByteBuffer allocate = ByteBuffer.allocate(b2.length + 2 + g.length + d.length + bArr.length);
            allocate.put(b).put((byte) 1).put(b2).put(g).put(d).put(bArr);
            return allocate.array();
        }
        ByteBuffer allocate2 = ByteBuffer.allocate(b2.length + 2);
        allocate2.put(b).put((byte) 1).put(b2);
        return allocate2.array();
    }

    private static byte[] e(int i) {
        byte[] g = blq.g(1);
        byte[] g2 = blq.g(i);
        byte[] d = blq.d(g2.length);
        byte[] bArr = new byte[g.length + g2.length + d.length];
        System.arraycopy(g, 0, bArr, 0, g.length);
        System.arraycopy(d, 0, bArr, g.length, d.length);
        System.arraycopy(g2, 0, bArr, g.length + d.length, g2.length);
        return bArr;
    }

    private static byte[] b(int i) {
        byte[] g = blq.g(2);
        byte[] a2 = blq.a(blq.c(i));
        byte[] d = blq.d(a2.length);
        byte[] bArr = new byte[g.length + a2.length + d.length];
        System.arraycopy(g, 0, bArr, 0, g.length);
        System.arraycopy(d, 0, bArr, g.length, d.length);
        System.arraycopy(a2, 0, bArr, g.length + d.length, a2.length);
        return bArr;
    }

    private static byte[] a(int i, String str) {
        byte[] g = blq.g(i);
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        byte[] bArr = new byte[g.length + c.length + d.length];
        System.arraycopy(g, 0, bArr, 0, g.length);
        System.arraycopy(d, 0, bArr, g.length, d.length);
        System.arraycopy(c, 0, bArr, g.length + d.length, c.length);
        return bArr;
    }

    private static byte[] b(boolean z, int i, int i2, IdentityInfo identityInfo, IdentityInfo identityInfo2) {
        byte[] e = e(i2);
        byte[] b = b(i);
        if (z) {
            byte[] bArr = new byte[e.length + b.length];
            System.arraycopy(e, 0, bArr, 0, e.length);
            System.arraycopy(b, 0, bArr, e.length, b.length);
            return bArr;
        }
        byte[] a2 = a(3, identityInfo.getPackageName());
        byte[] a3 = a(4, identityInfo2.getPackageName());
        if (i2 == 2) {
            byte[] a4 = a(5, identityInfo.getFingerPrint());
            byte[] a5 = a(6, identityInfo2.getFingerPrint());
            byte[] bArr2 = new byte[e.length + b.length + a2.length + a3.length + a4.length + a5.length];
            System.arraycopy(e, 0, bArr2, 0, e.length);
            System.arraycopy(b, 0, bArr2, e.length, b.length);
            System.arraycopy(a2, 0, bArr2, e.length + b.length, a2.length);
            System.arraycopy(a3, 0, bArr2, e.length + b.length + a2.length, a3.length);
            System.arraycopy(a4, 0, bArr2, e.length + b.length + a2.length + a3.length, a4.length);
            System.arraycopy(a5, 0, bArr2, e.length + b.length + a2.length + a3.length + a4.length, a5.length);
            return bArr2;
        }
        byte[] bArr3 = new byte[e.length + b.length + a2.length + a3.length];
        System.arraycopy(e, 0, bArr3, 0, e.length);
        System.arraycopy(b, 0, bArr3, e.length, b.length);
        System.arraycopy(a2, 0, bArr3, e.length + b.length, a2.length);
        System.arraycopy(a3, 0, bArr3, e.length + b.length + a2.length, a3.length);
        return bArr3;
    }

    private static bir d(MessageParcel messageParcel, byte[] bArr) {
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(bArr);
        bir.a aVar = new bir.a();
        aVar.c(messageParcel.isEnableEncrypt());
        return aVar.b(birVar);
    }

    public static int c(byte[] bArr, sol solVar) throws bmk {
        if (solVar == null) {
            return 0;
        }
        bmt.b b = new bmt().b(bArr, 2);
        solVar.a(b.b((byte) 1, ""));
        solVar.m(b.a((byte) 3, -1));
        solVar.c(b.a((byte) 4, -1));
        solVar.h(b.b((byte) 8, (String) null));
        solVar.d(b.b((byte) 9, (String) null));
        solVar.f(b.b(BaseType.Float, (String) null));
        solVar.b(b.b(BaseType.Double, (String) null));
        return b.a(Byte.MAX_VALUE, 0);
    }

    public static bir b(sol solVar) {
        if (solVar == null) {
            return new bir();
        }
        byte[] e = e(solVar.m(), solVar.v(), solVar.u());
        byte[] c = blq.c(solVar.ae());
        byte[] g = blq.g(8);
        byte[] d = blq.d(c.length);
        int length = c.length;
        int length2 = d.length;
        int length3 = g.length;
        byte[] c2 = blq.c(solVar.g());
        byte[] g2 = blq.g(9);
        byte[] d2 = blq.d(c2.length);
        int length4 = c2.length;
        int length5 = g2.length;
        int length6 = d2.length;
        byte[] c3 = blq.c(solVar.j());
        byte[] g3 = blq.g(10);
        byte[] d3 = blq.d(c3.length);
        int length7 = c3.length;
        int length8 = g3.length;
        int length9 = d3.length;
        byte[] c4 = blq.c(solVar.ah());
        byte[] g4 = blq.g(11);
        byte[] d4 = blq.d(c4.length);
        int length10 = c4.length;
        int length11 = g4.length;
        int length12 = d4.length;
        byte[] c5 = blq.c(solVar.h());
        byte[] g5 = blq.g(12);
        byte[] d5 = blq.d(c5.length);
        ByteBuffer allocate = ByteBuffer.allocate(e.length + length + length2 + length3 + length4 + length5 + length6 + length7 + length8 + length9 + length10 + length11 + length12 + c5.length + g5.length + d5.length);
        allocate.put(e);
        allocate.put(g).put(d).put(c);
        allocate.put(g2).put(d2).put(c2);
        allocate.put(g3).put(d3).put(c3);
        allocate.put(g4).put(d4).put(c4);
        allocate.put(g5).put(d5).put(c5);
        ByteBuffer allocate2 = ByteBuffer.allocate(allocate.capacity() + 2);
        allocate2.put((byte) 40).put((byte) 2).put(allocate.array());
        bir birVar = new bir();
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        birVar.e(allocate2.array());
        bir.a aVar = new bir.a();
        aVar.c(true);
        blt.d("P2pCommandUtil", allocate.array(), "getStartSendCommand deviceCommand:");
        return aVar.b(birVar);
    }

    public static byte[] e(String str, long j, int i) {
        byte[] c = blq.c(str);
        byte[] d = blq.d(c.length);
        int length = c.length + d.length + 1;
        byte[] bArr = new byte[length];
        bArr[0] = 1;
        System.arraycopy(d, 0, bArr, 1, d.length);
        System.arraycopy(c, 0, bArr, d.length + 1, c.length);
        byte[] a2 = blq.a(blq.d(j));
        int length2 = a2.length + 2;
        byte[] bArr2 = new byte[length2];
        bArr2[0] = 2;
        bArr2[1] = 4;
        System.arraycopy(a2, 0, bArr2, 2, a2.length);
        byte[] g = blq.g(i);
        int length3 = g.length + 2;
        byte[] bArr3 = new byte[length3];
        bArr3[0] = 3;
        bArr3[1] = 1;
        System.arraycopy(g, 0, bArr3, 2, g.length);
        int i2 = length + length2;
        byte[] bArr4 = new byte[i2 + length3];
        System.arraycopy(bArr, 0, bArr4, 0, length);
        System.arraycopy(bArr2, 0, bArr4, length, length2);
        System.arraycopy(bArr3, 0, bArr4, i2, length3);
        return bArr4;
    }

    public static boolean e(String str) {
        return !TextUtils.isEmpty(str) && str.length() <= 255;
    }
}
