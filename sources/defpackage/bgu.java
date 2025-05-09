package defpackage;

import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.devicesdk.connect.encrypt.EncryptBase;
import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.LogUtil;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes3.dex */
public class bgu extends EncryptBase {
    private static Map<String, String> d = new ConcurrentHashMap(4);
    private static final byte[] c = blq.a(bmo.e(1, 1033));
    private static final byte[] e = blq.a(bmo.e(1, 2033));

    @Override // com.huawei.devicesdk.connect.encrypt.EncryptBase
    public byte[] encrypt(byte[] bArr, String str) {
        byte[] d2 = d(str);
        byte[] bArr2 = new byte[0];
        try {
            bArr2 = bgv.d(16);
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("ScaleEncryptStrategy", "sendData NoSuchAlgorithmException", ExceptionUtils.d(e2));
        }
        byte[] e3 = bgv.e("AES/CTR/NoPadding", bArr, d2, bArr2);
        byte[] bArr3 = new byte[bArr2.length + e3.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(e3, 0, bArr3, bArr2.length, e3.length);
        return bArr3;
    }

    public byte[] d(byte[] bArr, String str) {
        byte[] d2 = d(str);
        byte[] bArr2 = new byte[0];
        try {
            bArr2 = bgv.d(16);
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.e("ScaleEncryptStrategy", "sendData NoSuchAlgorithmException", ExceptionUtils.d(e2));
        }
        byte[] e3 = bgv.e("AES/CTR/NoPadding", d2, bArr, bArr2);
        byte[] bArr3 = new byte[bArr2.length + e3.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(e3, 0, bArr3, bArr2.length, e3.length);
        return bArr3;
    }

    @Override // com.huawei.devicesdk.connect.encrypt.EncryptBase
    public byte[] decrypt(byte[] bArr, String str) {
        if (bArr == null) {
            LogUtil.a("ScaleEncryptStrategy", "decrypt data is null");
            return new byte[0];
        }
        if (bArr.length < 16) {
            blt.d("ScaleEncryptStrategy", bArr, "decrypt data");
            return new byte[0];
        }
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 0, bArr2, 0, 16);
        int length = bArr.length - 16;
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr, 16, bArr3, 0, length);
        blt.d("ScaleEncryptStrategy", bArr3, "decrypt data playLoad");
        return bgv.b("AES/CTR/NoPadding", bArr3, d(str), bArr2);
    }

    private byte[] d(String str) {
        LogUtil.c("ScaleEncryptStrategy", "createKey() begin ");
        byte[] bArr = null;
        if (str == null) {
            return null;
        }
        Map<String, String> map = d;
        String str2 = (map == null || !map.containsKey(str)) ? null : d.get(str);
        if (!TextUtils.isEmpty(str2)) {
            return blq.a(str2);
        }
        try {
            bArr = bgv.d(16);
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.a("ScaleEncryptStrategy", "getWorkKey NoSuchPaddingException is", ExceptionUtils.d(e2));
        }
        String d2 = blq.d(bArr);
        if (d2 != null) {
            d.put(str, d2);
            LogUtil.c("ScaleEncryptStrategy", "getWorkKey finish");
        }
        return bArr;
    }

    public byte[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("ScaleEncryptStrategy", "getRootKey address is null");
            return null;
        }
        return a(c, e, blq.a(blq.b(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT)));
    }

    public static void b(String str) {
        LogUtil.c("ScaleEncryptStrategy", "clearWorkKey ", blt.a(str));
        d.remove(str);
    }

    private byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return new byte[0];
        }
        byte[] bArr4 = new byte[bArr.length];
        if (bArr.length != bArr2.length) {
            return new byte[0];
        }
        for (int i = 0; i < bArr.length; i++) {
            bArr4[i] = (byte) ((bArr[i] << 4) ^ bArr2[i]);
        }
        byte[] a2 = a(bArr4);
        String d2 = blq.d(a2);
        int length = a2.length;
        byte[] bArr5 = new byte[length];
        if (length == 0 || bArr3.length == 0) {
            LogUtil.c("ScaleEncryptStrategy", "key data length is 0");
            return new byte[0];
        }
        for (int i2 = 0; i2 < a2.length; i2++) {
            int i3 = i2 * 2;
            try {
                bArr5[i2] = (byte) ((Integer.parseInt(d2.substring(i3, i3 + 2), 16) >> 6) ^ bArr3[i2]);
            } catch (NumberFormatException unused) {
                LogUtil.c("ScaleEncryptStrategy", "getRootKey NumberFormatException");
            }
        }
        byte[] a3 = a(bArr5);
        if (a3.length != 0) {
            return a3;
        }
        LogUtil.c("ScaleEncryptStrategy", "createKeyData() result is null");
        return new byte[0];
    }

    private byte[] a(byte[] bArr) {
        if (bArr == null) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[16];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            System.arraycopy(messageDigest.digest(), 0, bArr2, 0, 16);
            return bArr2;
        } catch (NoSuchAlgorithmException unused) {
            return new byte[0];
        }
    }
}
