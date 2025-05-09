package defpackage;

import com.huawei.operation.utils.Constants;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class bmx {
    public static String b(String str) {
        return d(str, 0);
    }

    private static String d(String str, int i) {
        byte[] c;
        if (str == null) {
            return null;
        }
        try {
            if (str.length() < 32) {
                return null;
            }
            String substring = str.substring(0, 32);
            if (i == 1) {
                c = a();
            } else {
                c = c();
            }
            return c(str.substring(32), c, blq.e(substring));
        } catch (Exception unused) {
            LogUtil.e("HwEncryptUtil", "decryptString Exception");
            return null;
        }
    }

    private static byte[] c() {
        return bnc.e(String.valueOf(d("D4AAC76288A23005828B8FEF937D5650gjQUAXCxflcmPZ2H4/deJyHSeFoU71xl67CeEsCdM8UbcYpdKUEGhxRdwBmol2/q", 1)));
    }

    private static byte[] a() {
        char[] charArray = bmo.e(1, 32).toCharArray();
        char[] charArray2 = bmo.e(1, 1032).toCharArray();
        String e = e(bmo.e(1, Constants.START_TO_MAIN_ACTIVITY));
        if (e == null || e.isEmpty()) {
            return new byte[1];
        }
        char[] charArray3 = e.toCharArray();
        char[] cArr = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            cArr[i] = (char) ((((charArray3[i] ^ (charArray2[i] << 2)) << 3) ^ charArray[i]) >> 4);
        }
        return bnc.e(String.valueOf(cArr));
    }

    private static String c(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] e = bnc.e(str);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
        return new String(cipher.doFinal(e), StandardCharsets.UTF_8).trim();
    }

    private static String e(String str) {
        if (str == null) {
            return null;
        }
        char[] charArray = str.toCharArray();
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((("0123456789ABCDEF".indexOf(charArray[i2]) * 16) + "0123456789ABCDEF".indexOf(charArray[i2 + 1])) & 255);
        }
        try {
            return new String(bArr, "utf-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("HwEncryptUtil", "hexToString() UnsupportedEncodingException");
            return null;
        }
    }
}
