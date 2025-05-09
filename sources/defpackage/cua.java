package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class cua {
    private static int e;

    private static void c(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 + i;
            bArr[i4] = (byte) (bArr[i4] ^ bArr2[i3]);
        }
    }

    private static void d(byte[] bArr, int i, int i2) {
        bArr[i] = (byte) (i2 >> 24);
        bArr[i + 1] = (byte) (i2 >> 16);
        bArr[i + 2] = (byte) (i2 >> 8);
        bArr[i + 3] = (byte) i2;
    }

    private static void a(Mac mac, byte[] bArr, byte[] bArr2, int i, int i2) {
        int macLength = mac.getMacLength();
        byte[] bArr3 = new byte[bArr2.length + 4];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        d(bArr3, bArr2.length, i2);
        for (int i3 = 0; i3 < i; i3++) {
            bArr3 = mac.doFinal(bArr3);
            c(bArr, e, bArr3, macLength);
        }
    }

    public static byte[] d(byte[] bArr, byte[] bArr2, int i, int i2) throws NoSuchAlgorithmException, InvalidKeyException {
        if (bArr == null || bArr2 == null) {
            return new byte[0];
        }
        if (i2 <= 0 || i == 0) {
            return new byte[0];
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        int macLength = mac.getMacLength();
        if (macLength == 0) {
            return new byte[0];
        }
        int i3 = ((macLength - 1) + i2) / macLength;
        byte[] bArr3 = new byte[i3 * macLength];
        e = 0;
        for (int i4 = 1; i4 <= i3; i4++) {
            a(mac, bArr3, bArr2, i, i4);
            e += macLength;
        }
        if (i2 % macLength == 0) {
            return bArr3;
        }
        byte[] bArr4 = new byte[i2];
        System.arraycopy(bArr3, 0, bArr4, 0, i2);
        return bArr4;
    }

    public static byte[] b(byte[] bArr, byte[] bArr2) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArr, "HmacSHA256"));
            return mac.doFinal(bArr2);
        } catch (InvalidKeyException unused) {
            LogUtil.b("Pbkdf2CipherUtil", "getHmacSha256 catch InvalidKeyException");
            return new byte[0];
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.b("Pbkdf2CipherUtil", "getHmacSha256 catch NoSuchAlgorithmException");
            return new byte[0];
        }
    }
}
