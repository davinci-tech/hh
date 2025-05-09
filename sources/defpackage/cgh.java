package defpackage;

import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.IoUtils;
import health.compact.a.WhiteBoxManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class cgh {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f700a = cvx.a(WhiteBoxManager.d().d(1, 1033));
    private static final byte[] b = cvx.a(WhiteBoxManager.d().d(1, 2033));
    private static String c;
    private static Map<String, String> e;

    private static void d(String str, byte[] bArr) {
        String d = cvx.d(bArr);
        if (e == null) {
            e = new HashMap(4);
        }
        e.put(str, d);
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
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
        byte[] d = d(bArr4);
        String d2 = cvx.d(d);
        int length = d.length;
        byte[] bArr5 = new byte[length];
        if (length == 0 || bArr3.length == 0) {
            LogUtil.a("HagridEncryptUtil", "keydata len or C_3 len = 0.");
            return new byte[0];
        }
        if (d2 == null) {
            return new byte[0];
        }
        for (int i2 = 0; i2 < d.length; i2++) {
            int i3 = i2 * 2;
            try {
                bArr5[i2] = (byte) ((Integer.parseInt(d2.substring(i3, i3 + 2), 16) >> 6) ^ bArr3[i2]);
            } catch (NumberFormatException unused) {
                LogUtil.b("HagridEncryptUtil", "getRootKey exception");
            }
        }
        byte[] d3 = d(bArr5);
        if (d3.length != 0) {
            return d3;
        }
        LogUtil.a("HagridEncryptUtil", "createKeyData() keymes is null");
        return new byte[0];
    }

    public static byte[] d(String str) {
        byte[] a2;
        LogUtil.a("HagridEncryptUtil", "createKey() begin ");
        Map<String, String> map = e;
        String str2 = (map == null || !map.containsKey(str)) ? "" : e.get(str);
        if (TextUtils.isEmpty(str2)) {
            byte[] bArr = new byte[0];
            try {
                bArr = cgd.d(16);
            } catch (NoSuchAlgorithmException e2) {
                LogUtil.b("HagridEncryptUtil", "sendData NoSuchAlgorithmException", e2.getMessage());
            }
            byte[] b2 = b(f700a, b, cvx.a(cvx.c(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT)));
            a2 = d();
            if (bArr != null) {
                byte[] c2 = c(a2, b2, bArr);
                if (c2 != null) {
                    str2 = cvx.d(bArr) + cvx.d(c2);
                } else {
                    LogUtil.b("HagridEncryptUtil", "keysencrypt is null.");
                }
            } else {
                LogUtil.b("HagridEncryptUtil", "randIV is null");
            }
            d(str, cvx.a(str2));
        } else {
            byte[] bArr2 = new byte[16];
            byte[] a3 = cvx.a(str2);
            System.arraycopy(a3, 0, bArr2, 0, 16);
            byte[] bArr3 = new byte[a3.length - 16];
            System.arraycopy(a3, 16, bArr3, 0, a3.length - 16);
            a2 = a(bArr3, b(f700a, b, cvx.a(cvx.c(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT))), bArr2);
        }
        LogUtil.a("HagridEncryptUtil", "createKey() finish");
        return a2;
    }

    public static byte[] e() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bArr2 = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream3.write(f700a);
                bArr2 = byteArrayOutputStream3.toByteArray();
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream3;
                IoUtils.e(byteArrayOutputStream);
                throw th;
            }
            bArr = bArr2;
            byteArrayOutputStream2 = byteArrayOutputStream3;
        } catch (IOException unused2) {
            bArr = null;
        } catch (Throwable th2) {
            th = th2;
        }
        IoUtils.e(byteArrayOutputStream2);
        return bArr;
    }

    public static byte[] c() {
        byte[] bArr;
        ByteArrayOutputStream byteArrayOutputStream = null;
        byte[] bArr2 = null;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream3.write(b);
                bArr2 = byteArrayOutputStream3.toByteArray();
            } catch (IOException unused) {
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = byteArrayOutputStream3;
                IoUtils.e(byteArrayOutputStream);
                throw th;
            }
            bArr = bArr2;
            byteArrayOutputStream2 = byteArrayOutputStream3;
        } catch (IOException unused2) {
            bArr = null;
        } catch (Throwable th2) {
            th = th2;
        }
        IoUtils.e(byteArrayOutputStream2);
        return bArr;
    }

    public static byte[] d(byte[] bArr) {
        if (bArr == null) {
            return (byte[]) new byte[0].clone();
        }
        byte[] bArr2 = new byte[16];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            System.arraycopy(messageDigest.digest(), 0, bArr2, 0, 16);
            return bArr2;
        } catch (NoSuchAlgorithmException unused) {
            return (byte[]) new byte[0].clone();
        }
    }

    public static byte[] c(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return (byte[]) new byte[0].clone();
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr3);
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(1, secretKeySpec, ivParameterSpec);
            return cipher.doFinal(bArr);
        } catch (BadPaddingException e2) {
            LogUtil.h("HagridEncryptUtil", "encrypt BadPaddingException is", e2.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (IllegalBlockSizeException e3) {
            LogUtil.h("HagridEncryptUtil", "encrypt IllegalBlockSizeException is", e3.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (InvalidKeyException e4) {
            LogUtil.h("HagridEncryptUtil", "encrypt InvalidKeyException is", e4.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (InvalidAlgorithmParameterException e5) {
            LogUtil.h("HagridEncryptUtil", "encrypt InvalidAlgorithmParameterException is", e5.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (NoSuchPaddingException e6) {
            LogUtil.h("HagridEncryptUtil", "encrypt NoSuchPaddingException is", e6.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (NoSuchAlgorithmException e7) {
            LogUtil.h("HagridEncryptUtil", "encrypt NoSuchAlgorithmException is", e7.getMessage());
            return (byte[]) new byte[0].clone();
        } finally {
            LogUtil.h("HagridEncryptUtil", "encrypt finally");
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr2 == null || bArr3 == null) {
            return (byte[]) new byte[0].clone();
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
            cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (BadPaddingException e2) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt BadPaddingException is", e2.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (NoSuchAlgorithmException e3) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt NoSuchAlgorithmException is", e3.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (NoSuchPaddingException e4) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt NoSuchPaddingException is", e4.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (InvalidKeyException e5) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt InvalidKeyException is", e5.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (InvalidAlgorithmParameterException e6) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt InvalidAlgorithmParameterException is", e6.getMessage());
            return (byte[]) new byte[0].clone();
        } catch (IllegalBlockSizeException e7) {
            LogUtil.h("HagridEncryptUtil", "desEncrypt IllegalBlockSizeException is", e7.getMessage());
            return (byte[]) new byte[0].clone();
        } finally {
            LogUtil.h("HagridEncryptUtil", "desEncrypt finally");
        }
    }

    private static byte[] d() {
        SecureRandom secureRandom = new SecureRandom();
        c = "abcdefghijklmnopqrstuvwxyz0123456789";
        char[] charArray = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) charArray[secureRandom.nextInt(36)];
        }
        return bArr;
    }
}
