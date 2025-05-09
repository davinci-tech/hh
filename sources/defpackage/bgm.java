package defpackage;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.devicesdk.hichain.HiChainAuthManager;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bgm {
    private static TreeSet<Byte> e;
    private static ConcurrentHashMap<String, Long> f = new ConcurrentHashMap<>(16);
    private static byte[] b = blq.a(g());
    private static byte[] c = blq.a(j());

    /* renamed from: a, reason: collision with root package name */
    private static byte[] f361a = b(a(), e(), d());
    private static byte[] d = b(b(), h(), f());
    private static Map<String, String> g = new HashMap(4);
    private static Map<String, byte[]> j = new ConcurrentHashMap(4);

    static {
        TreeSet<Byte> treeSet = new TreeSet<>();
        e = treeSet;
        treeSet.add((byte) 124);
        e.add((byte) 125);
        e.add((byte) 126);
    }

    private bgm() {
    }

    public static String b(int i, byte[] bArr, DeviceInfo deviceInfo) {
        String c2;
        String str = null;
        if (deviceInfo == null) {
            LogUtil.a("EncryptStrategyGeneral", "create key error, deviceInfo is null");
            return null;
        }
        String deviceMac = deviceInfo.getDeviceMac();
        String b2 = bmf.b(deviceMac);
        byte[] d2 = d(i, deviceMac);
        if (TextUtils.isEmpty(b2)) {
            byte[] c3 = c();
            c2 = blq.d(bArr) + blq.d(bgv.e("AES/CBC/PKCS5Padding", c3, d2, bArr));
            if (c3 != null) {
                str = e(c2, c3);
                bmf.b(deviceMac, blq.d(bmy.c(c3)));
            }
        } else {
            c2 = c(b2, bArr, d2, deviceInfo, i);
        }
        LogUtil.c("EncryptStrategyGeneral", "createKey() finish");
        if (str != null) {
            g.put(deviceMac, str);
            j.put(deviceMac, new byte[0]);
        }
        return c2;
    }

    public static boolean d(bir birVar, String str, int i) {
        if (birVar == null || TextUtils.isEmpty(str)) {
            return false;
        }
        byte[] bArr = new byte[2];
        int length = birVar.e().length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(birVar.e(), 0, bArr, 0, 2);
        System.arraycopy(birVar.e(), 2, bArr2, 0, length);
        byte[] c2 = c();
        if (c2 == null) {
            return false;
        }
        if (f.containsKey(str)) {
            Long l = f.get(str);
            if (l == null) {
                return false;
            }
            if (l.longValue() != 0) {
                if (l.longValue() == 4294967295L) {
                    f.put(str, 1L);
                } else {
                    l = Long.valueOf(l.longValue() + 1);
                    f.put(str, l);
                }
                byte[] a2 = blq.a(blq.d(l.longValue()));
                if (a2 != null && a2.length == 4 && c2.length == 16) {
                    for (int i2 = 0; i2 < 4; i2++) {
                        c2[i2 + 12] = a2[i2];
                    }
                }
            }
        }
        byte[] b2 = b(bArr2, c2, str, i);
        if (b2 == null) {
            b(str, String.valueOf((int) bArr[0]) + "&" + String.valueOf((int) bArr[1]), 1);
            return false;
        }
        if (b2.length == 0 || !e(b2)) {
            b(str, String.valueOf((int) bArr[0]) + "&" + String.valueOf((int) bArr[1]), 1);
        }
        d(birVar, bArr, c2, b2);
        return true;
    }

    private static boolean e(byte[] bArr) {
        if (bArr.length != 16) {
            return true;
        }
        for (byte b2 : bArr) {
            if (b2 != 0) {
                return true;
            }
        }
        return false;
    }

    private static void b(String str, String str2, int i) {
        DeviceInfo j2 = bjx.a().j(str);
        if (j2 != null) {
            bmw.e(100090, bmh.b(j2.getDeviceName()), i == 1 ? "1" : "2", str2);
        }
    }

    private static void d(bir birVar, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] i = i();
        byte[] d2 = d(bArr2);
        byte[] a2 = a(bArr3);
        int length = bArr.length;
        int length2 = i.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + length2 + d2.length + a2.length);
        allocate.put(bArr).put(i).put(d2).put(a2);
        birVar.e(allocate.array());
        birVar.b(false);
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EncryptStrategyGeneral", "updateBaseCount deviceIdentify is null");
            return;
        }
        if (f.containsKey(str)) {
            Long l = f.get(str);
            if (l == null) {
                LogUtil.a("EncryptStrategyGeneral", "baseCount is null");
                return;
            }
            LogUtil.c("EncryptStrategyGeneral", "before-baseCount: ", l);
            if (l.longValue() != 0) {
                l = Long.valueOf(l.longValue() - 1);
            }
            f.put(str, l);
            LogUtil.c("EncryptStrategyGeneral", "after-baseCount: ", l);
        }
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, String str, int i) {
        byte[] c2;
        byte[] d2 = d(str, bArr2, i);
        if (d2 == null || d2.length == 0) {
            return new byte[0];
        }
        biw c3 = bjx.a().c(str);
        if (bjk.a(str) || (c3 != null && c3.c() == 1)) {
            LogUtil.c("EncryptStrategyGeneral", "encrypt by gcm use aes_gcm");
            c2 = bgv.c("AES/GCM/NoPadding", bArr, d2, bArr2);
        } else {
            c2 = bgv.e("AES/CBC/PKCS5Padding", bArr, d2, bArr2);
        }
        LogUtil.c("EncryptStrategyGeneral", "encryptDataContent key size: ", Integer.valueOf(d2.length), " deviceIdentify: ", blt.b(str));
        return c2;
    }

    private static byte[] d(String str, byte[] bArr, int i) {
        if (bArr == null) {
            LogUtil.a("EncryptStrategyGeneral", "randIv is null");
            return null;
        }
        if (bjn.c(str)) {
            return HiChainAuthManager.d().e(str);
        }
        if (bjr.e(str)) {
            return bjr.b(str);
        }
        if (bjk.a(str)) {
            return bjl.e().c(str);
        }
        byte[] c2 = c(str, i);
        if (c2 == null || c2.length == 0) {
            bmf.e(str, "");
        }
        return c2;
    }

    public static byte[] c(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EncryptStrategyGeneral", "getWorkKey identify null");
            return null;
        }
        byte[] bArr = j.get(str);
        if (bArr != null && bArr.length != 0) {
            return bArr;
        }
        String a2 = a(str);
        if (a2 == null) {
            LogUtil.a("EncryptStrategyGeneral", "getWorkKey key null");
            return null;
        }
        String d2 = d(a2, str, i);
        if (TextUtils.isEmpty(d2)) {
            return null;
        }
        byte[] a3 = blq.a(d2);
        j.put(str, a3);
        return a3;
    }

    public static void b(String str) {
        LogUtil.c("EncryptStrategyGeneral", "clearCounterAndKey: ", blt.a(str));
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("EncryptStrategyGeneral", "identify is invalid");
        } else {
            f.remove(str);
            j.remove(str);
        }
    }

    private static byte[] i() {
        return ByteBuffer.allocate(3).put((byte) 124).put((byte) 1).put((byte) 1).array();
    }

    private static byte[] d(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            return bArr2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + blq.d(bArr.length).length + 1);
        allocate.put(blq.g(125)).put(blq.d(bArr.length)).put(bArr);
        return allocate.array();
    }

    private static byte[] a(byte[] bArr) {
        byte[] bArr2 = new byte[0];
        if (bArr == null) {
            return bArr2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(bArr.length + blq.d(bArr.length).length + 1);
        allocate.put(blq.g(126)).put(blq.d(bArr.length)).put(bArr);
        return allocate.array();
    }

    private static byte[] d(int i, String str) {
        byte[] a2 = blq.a(blq.b(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT));
        if (i == 3) {
            return a(b, c, a2);
        }
        return a(f361a, d, a2);
    }

    private static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            LogUtil.a("EncryptStrategyGeneral", "data error");
            return null;
        }
        byte[] bArr4 = new byte[bArr.length];
        if (bArr.length != bArr2.length) {
            return null;
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr4[i] = (byte) ((bArr[i] << 4) ^ bArr2[i]);
        }
        byte[] b2 = bgv.b(bArr4);
        String d2 = blq.d(b2);
        if (d2 == null) {
            return null;
        }
        int length = b2.length;
        byte[] bArr5 = new byte[length];
        if (length == 0 || bArr3.length == 0) {
            LogUtil.a("EncryptStrategyGeneral", "data or arrayByte is null");
            return null;
        }
        for (int i2 = 0; i2 < b2.length; i2++) {
            int i3 = i2 * 2;
            try {
                bArr5[i2] = (byte) ((Integer.parseInt(d2.substring(i3, i3 + 2), 16) >> 6) ^ bArr3[i2]);
            } catch (NumberFormatException unused) {
                LogUtil.e("EncryptStrategyGeneral", "NumberFormatException");
            }
        }
        byte[] b3 = bgv.b(bArr5);
        LogUtil.c("EncryptStrategyGeneral", "getData success");
        return b3;
    }

    private static String e(String str, byte[] bArr) {
        try {
            String a2 = bmy.a(bArr);
            bgq bgqVar = new bgq();
            bgqVar.b(a2);
            return new Gson().toJson(bgqVar);
        } catch (JsonSyntaxException unused) {
            LogUtil.e("EncryptStrategyGeneral", "encryptWorkKey JsonSyntaxException");
            return null;
        }
    }

    private static String c(String str, byte[] bArr, byte[] bArr2, DeviceInfo deviceInfo, int i) {
        byte[] a2 = blq.a(d(str, deviceInfo.getDeviceMac(), i));
        if (bArr == null) {
            return str;
        }
        byte[] e2 = bgv.e("AES/CBC/PKCS5Padding", a2, bArr2, bArr);
        if (e2 != null) {
            String str2 = blq.d(bArr) + blq.d(e2);
            LogUtil.c("EncryptStrategyGeneral", "processSpecialKeyFromSharePreference key success");
            return str2;
        }
        LogUtil.a("EncryptStrategyGeneral", "keySencrypt is null.");
        return str;
    }

    private static String d(String str, String str2, int i) {
        String c2;
        try {
            bgq bgqVar = (bgq) new Gson().fromJson(JsonSanitizer.sanitize(str), bgq.class);
            if (bgqVar != null && bgqVar.b() == 1) {
                c2 = blq.d(bmy.c(bgqVar.c()));
                LogUtil.c("EncryptStrategyGeneral", "decryptWorkKey byteToHex key");
            } else {
                c2 = c(str, i, str2);
            }
            return c2;
        } catch (JsonSyntaxException unused) {
            LogUtil.e("EncryptStrategyGeneral", "low version key");
            return c(str, i, str2);
        }
    }

    private static String c(String str, int i, String str2) {
        if (str.length() > 64) {
            return a(str.substring(32), blq.a(str.substring(0, 32)), i, str2);
        }
        LogUtil.a("EncryptStrategyGeneral", "key info is incorrect.");
        return null;
    }

    private static String a(String str, byte[] bArr, int i, String str2) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.c("EncryptStrategyGeneral", "decrypt desEncrypt");
            byte[] a2 = a(i);
            byte[] d2 = d(i);
            byte[] a3 = blq.a(blq.b(str2.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT));
            if (bArr != null) {
                return blq.d(bgv.b("AES/CBC/PKCS5Padding", blq.a(str), a(a2, d2, a3), bArr));
            }
            LogUtil.a("EncryptStrategyGeneral", "randIV is null");
        }
        return null;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("get key is null", new Object[0]);
            return str;
        }
        if (TextUtils.isEmpty(g.get(str))) {
            return bmf.b(str);
        }
        return g.get(str);
    }

    public static byte[] c() {
        byte[] bArr;
        try {
            bArr = bgv.d(16);
            if (bArr == null) {
                try {
                    LogUtil.a("EncryptStrategyGeneral", "generateRandomBytes fail");
                } catch (NoSuchAlgorithmException e2) {
                    e = e2;
                    LogUtil.e("EncryptStrategyGeneral", "generateRandomBytes exception with info:", ExceptionUtils.d(e));
                    return bArr;
                }
            }
            return bArr;
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            bArr = null;
        }
    }

    private static byte[] a(int i) {
        synchronized (bgm.class) {
            if (3 == i) {
                return c(b);
            }
            return c(f361a);
        }
    }

    private static byte[] d(int i) {
        synchronized (bgm.class) {
            if (3 == i) {
                return c(c);
            }
            return c(d);
        }
    }

    private static byte[] c(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null) {
            LogUtil.a("EncryptStrategyGeneral", "query c1 info error, hexByte1 is null");
            return bArr;
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        byte[] bArr2 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                try {
                    byteArrayOutputStream.write(bArr);
                    bArr2 = byteArrayOutputStream.toByteArray();
                } catch (IOException e2) {
                    e = e2;
                    LogUtil.e("EncryptStrategyGeneral", "query c1 info exception is ", ExceptionUtils.d(e));
                    blv.d(byteArrayOutputStream);
                    return bArr2;
                }
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream2 = byteArrayOutputStream;
                blv.d(byteArrayOutputStream2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            blv.d(byteArrayOutputStream2);
            throw th;
        }
        blv.d(byteArrayOutputStream);
        return bArr2;
    }

    private static String g() {
        return bmo.e(1, 29);
    }

    private static String j() {
        return bmo.e(1, 1029);
    }

    private static byte[] a() {
        return c(bmo.e(1, 27));
    }

    private static byte[] e() {
        return c(bmo.e(1, 1027));
    }

    private static byte[] d() {
        return c(bmo.e(1, 2027));
    }

    private static byte[] b() {
        return c(bmo.e(1, 28));
    }

    private static byte[] h() {
        return c(bmo.e(1, 1028));
    }

    private static byte[] f() {
        return c(bmo.e(1, 2028));
    }

    private static byte[] c(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        return bArr;
    }

    private static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] bArr4;
        ByteArrayOutputStream byteArrayOutputStream;
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return new byte[0];
        }
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        byte[] bArr5 = null;
        ByteArrayOutputStream byteArrayOutputStream3 = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException unused) {
            bArr4 = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr2);
            byteArrayOutputStream.write(bArr3);
            bArr5 = byteArrayOutputStream.toByteArray();
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            blv.d(byteArrayOutputStream2);
            throw th;
        }
        bArr4 = bArr5;
        byteArrayOutputStream3 = byteArrayOutputStream;
        blv.d(byteArrayOutputStream3);
        return bArr4;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("EncryptStrategyGeneral", "isHaveBaseCounter: identify is invalid");
            return false;
        }
        return f.containsKey(str);
    }

    public static void c(String str, long j2) {
        if (TextUtils.isEmpty(str) || j2 <= 0) {
            LogUtil.a("EncryptStrategyGeneral", "identify is invalid");
        } else {
            f.put(str, Long.valueOf(j2));
        }
    }

    public static byte[] d(byte[] bArr, String str, int i) {
        List<bmu> d2;
        if (str == null || bArr == null || bArr.length < 2) {
            LogUtil.a("EncryptStrategyGeneral", "decryptReceiveData check input parameter is failed");
            return null;
        }
        int length = bArr.length - 2;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 2, bArr2, 0, length);
        try {
            d2 = new bmt().e(bArr2, e).d();
        } catch (bmk unused) {
        }
        if (bkz.e(d2)) {
            return null;
        }
        byte[] bArr3 = null;
        byte[] bArr4 = null;
        int i2 = 0;
        for (bmu bmuVar : d2) {
            switch (bmuVar.a()) {
                case 124:
                    i2 = blq.d(bmuVar.c(), 0);
                    break;
                case 125:
                    bArr3 = bmuVar.c();
                    break;
                case 126:
                    bArr4 = bmuVar.c();
                    break;
            }
        }
        if (i2 == 1) {
            byte[] b2 = b(bArr, str, bArr3, bArr4, i);
            if (b2 != null && b2.length != 0) {
                if (!e(b2)) {
                    LogUtil.a("EncryptStrategyGeneral", "decryptReceiveData data is error");
                    b(str, String.valueOf((int) bArr[0]) + "&" + String.valueOf((int) bArr[1]), 2);
                }
                return b2;
            }
            LogUtil.a("EncryptStrategyGeneral", "decryptReceiveData decryptData is null");
            b(str, String.valueOf((int) bArr[0]) + "&" + String.valueOf((int) bArr[1]), 2);
            return null;
        }
        LogUtil.c("EncryptStrategyGeneral", "decryptReceiveData is not need decrypt");
        return null;
    }

    private static byte[] b(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i) {
        byte[] a2;
        if (bArr2 != null && bArr2.length != 0 && bArr3 != null && bArr3.length != 0) {
            byte[] d2 = d(str, i);
            if (d2 != null && d2.length != 0) {
                LogUtil.c("EncryptStrategyGeneral", "decryptReceiveDataByKey key:,length:", Integer.valueOf(d2.length));
                biw c2 = bjx.a().c(str);
                if (bjk.a(str) || (c2 != null && c2.c() == 1)) {
                    LogUtil.c("EncryptStrategyGeneral", "decrypt by gcm");
                    a2 = bgv.a("AES/GCM/NoPadding", bArr3, d2, bArr2);
                } else {
                    a2 = bgv.b("AES/CBC/PKCS5Padding", bArr3, d2, bArr2);
                }
                if (a2 == null) {
                    return null;
                }
                byte[] bArr4 = new byte[a2.length + 2];
                bArr4[0] = bArr[0];
                bArr4[1] = bArr[1];
                System.arraycopy(a2, 0, bArr4, 2, a2.length);
                return bArr4;
            }
            LogUtil.a("EncryptStrategyGeneral", "key is not incorrect.");
        }
        return null;
    }

    private static byte[] d(String str, int i) {
        if (bjn.c(str)) {
            return HiChainAuthManager.d().e(str);
        }
        if (bjr.e(str)) {
            return bjr.b(str);
        }
        if (bjk.a(str)) {
            return bjl.e().c(str);
        }
        return c(str, i);
    }
}
