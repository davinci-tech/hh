package health.compact.a;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.operation.utils.Constants;
import defpackage.knl;
import defpackage.knn;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class HwEncryptUtil {
    private static final Object c = new Object();
    private static volatile HwEncryptUtil e;
    private WhiteBoxManager d = WhiteBoxManager.d();
    private int b = -1;

    private HwEncryptUtil() {
    }

    private int c() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (this.b == -1) {
            this.b = Cipher.getInstance("AES/CBC/PKCS7Padding").getBlockSize();
        }
        return this.b;
    }

    public static HwEncryptUtil c(Context context) {
        if (e == null && context != null) {
            synchronized (c) {
                if (e == null) {
                    e = new HwEncryptUtil();
                }
            }
        }
        return e;
    }

    public static void b() {
        KeyManager.b();
    }

    public String b(int i, String str) throws GeneralSecurityException {
        if (e(i)) {
            int c2 = c();
            if (str != null) {
                byte[] bytes = str.trim().getBytes(StandardCharsets.UTF_8);
                int length = bytes.length;
                if (c2 != 0) {
                    int i2 = length % c2;
                    length += i2 != 0 ? c2 - i2 : 0;
                }
                byte[] bArr = new byte[length];
                System.arraycopy(bytes, 0, bArr, 0, bytes.length);
                byte[] d = KeyManager.d(i);
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "encryptData getworkkey ", Integer.valueOf(i), str);
                byte[] e2 = KeyManager.e();
                if (d != null && e2 != null) {
                    byte[] e3 = knl.e(d, e2, bArr);
                    String str2 = Base64.a(e2) + Base64.a(e3);
                    com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "encryptData secret is not null");
                    return str2;
                }
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "encryptData secret is null");
                return str;
            }
            com.huawei.hwlogsmodel.LogUtil.b("HwEncryptUtil", "content is null!");
            return null;
        }
        com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "not need encrypt");
        return str;
    }

    public String a(int i, String str) throws GeneralSecurityException, IllegalArgumentException {
        if (e(i)) {
            if (TextUtils.isEmpty(str) || str.length() <= 24) {
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptData content is: ", str);
                return "";
            }
            String substring = str.substring(0, 24);
            String substring2 = str.substring(24, str.length());
            com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptData secret ivString:", substring, ";dataString:", substring2);
            byte[] d = KeyManager.d(i);
            byte[] e2 = Base64.e(substring);
            byte[] e3 = Base64.e(substring2);
            if (d == null || e2 == null || e3 == null) {
                com.huawei.hwlogsmodel.LogUtil.h("HwEncryptUtil", "secrets is null, ivBytes and dataBytes is null, decryptData failed, maybe clone data.");
                return "";
            }
            byte[] c2 = knl.c(d, e2, e3);
            if (CommonUtil.aq()) {
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptData secret original is:", HEXUtils.a(c2));
            }
            if (c2 == null) {
                com.huawei.hwlogsmodel.LogUtil.h("HwEncryptUtil", "originalBytes is null, decryptData failed, maybe clone data.");
                return "";
            }
            return new String(c2, StandardCharsets.UTF_8).trim();
        }
        com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptData11 ");
        return str;
    }

    public String b(String str) {
        return a(str, 0);
    }

    private boolean e(int i) {
        return knn.b(i);
    }

    private String a(String str, int i) {
        byte[] a2;
        if (str == null) {
            return null;
        }
        try {
            if (str.length() < 32) {
                return null;
            }
            String substring = str.substring(0, 32);
            if (i == 1) {
                a2 = e();
            } else {
                a2 = a();
            }
            return d(str.substring(32), a2, HEXUtils.e(substring));
        } catch (Exception unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HwEncryptUtil", "decryptString Exception");
            return null;
        }
    }

    private byte[] a() {
        return Base64.e(String.valueOf(a("D4AAC76288A23005828B8FEF937D5650gjQUAXCxflcmPZ2H4/deJyHSeFoU71xl67CeEsCdM8UbcYpdKUEGhxRdwBmol2/q", 1)));
    }

    private byte[] e() {
        char[] charArray = this.d.d(1, 32).toCharArray();
        char[] charArray2 = this.d.d(1, 1032).toCharArray();
        String a2 = a(this.d.d(1, Constants.START_TO_MAIN_ACTIVITY));
        if (a2 == null || a2.isEmpty()) {
            return new byte[1];
        }
        char[] charArray3 = a2.toCharArray();
        char[] cArr = new char[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            cArr[i] = (char) ((((charArray3[i] ^ (charArray2[i] << 2)) << 3) ^ charArray[i]) >> 4);
        }
        return Base64.e(String.valueOf(cArr));
    }

    private String d(String str, byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        byte[] e2 = Base64.e(str);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr, "AES"), new IvParameterSpec(bArr2));
        return new String(cipher.doFinal(e2), StandardCharsets.UTF_8).trim();
    }

    private String a(String str) {
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
            com.huawei.hwlogsmodel.LogUtil.b("HwEncryptUtil", "hexToString() UnsupportedEncodingException");
            return null;
        }
    }

    public byte[] c(int i, byte[] bArr) throws Exception {
        if (e(i)) {
            int c2 = c();
            int length = bArr.length;
            if (c2 != 0) {
                int i2 = length % c2;
                length += i2 != 0 ? c2 - i2 : 0;
            }
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            byte[] d = KeyManager.d(i);
            byte[] e2 = KeyManager.e();
            if (d != null && e2 != null) {
                byte[] e3 = knl.e(d, e2, bArr2);
                String str = Base64.a(e2) + Base64.a(e3);
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "encryptData secret is not null");
                return str.getBytes(StandardCharsets.UTF_8);
            }
            com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "encryptData secret is null");
            return bArr;
        }
        com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "not need encrypt");
        return bArr;
    }

    public byte[] d(int i, String str) throws Exception {
        if (str == null) {
            com.huawei.hwlogsmodel.LogUtil.a("HwEncryptUtil", "decryptByteData content is null");
            return new byte[0];
        }
        if (str.length() >= 24 && e(i)) {
            com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptData dataType:", Integer.valueOf(i));
            String substring = str.substring(0, 24);
            String substring2 = str.substring(24);
            byte[] d = KeyManager.d(i);
            if (d == null) {
                com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "secretBytes == null");
                return str.getBytes("utf-8");
            }
            return knl.c(d, Base64.e(substring), Base64.e(substring2));
        }
        com.huawei.hwlogsmodel.LogUtil.c("HwEncryptUtil", "decryptByteData else branch");
        return str.getBytes("utf-8");
    }
}
