package defpackage;

import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.secure.android.common.util.HexUtil;
import health.compact.a.WhiteBoxManager;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes3.dex */
public class cgd {
    private static final byte[] b = {57, 56, 53, 54};
    private static final byte[] d = {49, 49, 50, 51};
    private static final byte[] e = cvx.a(WhiteBoxManager.d().d(1, 33));

    /* renamed from: a, reason: collision with root package name */
    private static String f697a = "";
    private static String c = "";

    private static byte[] b(byte[] bArr, byte[] bArr2) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        LogUtil.a("BtHaiGeSendUtil", "hmacSha256 is start");
        byte[] bArr3 = (byte[]) bArr2.clone();
        byte[] bArr4 = (byte[]) bArr.clone();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, "HMACSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        return mac.doFinal(bArr4);
    }

    public static byte[] d(int i) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(i * 8, secureRandom);
        return keyGenerator.generateKey().getEncoded();
    }

    public static byte[] e(byte[] bArr) {
        LogUtil.a("BtHaiGeSendUtil", "Enter startAuthenticBTDevice().");
        f697a = cvx.d(bArr);
        try {
            byte[] d2 = d(16);
            if (d2 == null) {
                LogUtil.b("BtHaiGeSendUtil", "generateRandomBytes fail.");
                return new byte[0];
            }
            c = cvx.d(d2);
            LogUtil.a("BtHaiGeSendUtil", "startAuthenticBtDevice randB = ", cvx.d(d2));
            String c2 = c(d);
            if (c2 == null) {
                return new byte[0];
            }
            byte[] bArr2 = new byte[48];
            System.arraycopy(d2, 0, bArr2, 0, 16);
            System.arraycopy(cvx.a(c2), 0, bArr2, 16, 32);
            LogUtil.c("BtHaiGeSendUtil", "get device TokenB ", cvx.d(bArr2));
            return bArr2;
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.b("BtHaiGeSendUtil", "generateRandomBytes exception with info is ", e2.getMessage());
            return new byte[0];
        }
    }

    private static String c(byte[] bArr) {
        LogUtil.a("BtHaiGeSendUtil", "Enter getAuthenticTokenValue().rand = ", cvx.d(bArr));
        byte[] a2 = cvx.a(f697a);
        byte[] a3 = cvx.a(c);
        LogUtil.a("BtHaiGeSendUtil", "Enter getAuthenticTokenValue().randA = ", cvx.d(a2), "  randB = ", cvx.d(a3));
        byte[] bArr2 = new byte[32];
        System.arraycopy(a2, 0, bArr2, 0, 16);
        System.arraycopy(a3, 0, bArr2, 16, 16);
        byte[] bArr3 = e;
        LogUtil.a("BtHaiGeSendUtil", "after convert old strCak to Hex is ", cvx.d(bArr3));
        byte[] bArr4 = new byte[bArr3.length + bArr.length];
        System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
        System.arraycopy(bArr, 0, bArr4, bArr3.length, bArr.length);
        try {
            LogUtil.a("BtHaiGeSendUtil", "Start to create encode info.");
            return cvx.d(b(bArr2, b(bArr2, bArr4)));
        } catch (UnsupportedEncodingException e2) {
            LogUtil.b("BtHaiGeSendUtil", "HMac256 occur UnsupportedEncodingException with info is ", e2.getMessage());
            return null;
        } catch (InvalidKeyException e3) {
            LogUtil.b("BtHaiGeSendUtil", "HMac256 occur InvalidKeyException with info is ", e3.getMessage());
            return null;
        } catch (NoSuchAlgorithmException e4) {
            LogUtil.b("BtHaiGeSendUtil", "HMac256 occur NoSuchAlgorithmException with info is ", e4.getMessage());
            return null;
        }
    }

    public static boolean b(String str) {
        LogUtil.a("BtHaiGeSendUtil", "Enter checkAuthenticBTDevice().");
        if (f697a.length() == 32 && c.length() == 32) {
            String c2 = c(b);
            if (c2 != null && str != null) {
                return c2.equalsIgnoreCase(str);
            }
            LogUtil.b("BtHaiGeSendUtil", "Authentic codeInfoHex is incorrect.");
            return false;
        }
        LogUtil.b("BtHaiGeSendUtil", "Authentic Random parameter is incorrect.");
        return false;
    }

    public static ArrayList<byte[]> a(byte[] bArr, boolean z, String str, boolean z2) {
        LogUtil.c("BtHaiGeSendUtil", "sendData playload", HexUtil.byteArray2HexStr(bArr), " isEncrypted,", Boolean.valueOf(z));
        if (z) {
            byte[] bArr2 = new byte[0];
            try {
                bArr2 = d(16);
            } catch (NoSuchAlgorithmException e2) {
                LogUtil.b("BtHaiGeSendUtil", "sendData NoSuchAlgorithmException", e2.getMessage());
            }
            byte[] c2 = cgh.c(bArr, a(z2, str), bArr2);
            byte[] bArr3 = new byte[bArr2.length + c2.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(c2, 0, bArr3, bArr2.length, c2.length);
            LogUtil.a("BtHaiGeSendUtil", "sendData encryptData size = ", Integer.valueOf(c2.length), " playload size = ", Integer.valueOf(bArr.length));
            return cgf.b(bArr3, z);
        }
        return cgf.b(bArr, z);
    }

    public static ArrayList<byte[]> c(byte[] bArr, boolean z, String str, boolean z2) {
        LogUtil.a("BtHaiGeSendUtil", "sendData playload", HexUtil.byteArray2HexStr(bArr), " isEncrypted,", Boolean.valueOf(z));
        if (bArr == null) {
            return new ArrayList<>();
        }
        ArrayList<byte[]> c2 = c(bArr, z);
        ArrayList<byte[]> arrayList = new ArrayList<>();
        if (koq.b(c2)) {
            return arrayList;
        }
        int size = c2.size() - 1;
        LogUtil.a("BtHaiGeSendUtil", "sendData total frames ", Integer.valueOf(size));
        Iterator<byte[]> it = c2.iterator();
        int i = 0;
        while (it.hasNext()) {
            byte[] next = it.next();
            if (next != null) {
                LogUtil.a("BtHaiGeSendUtil", "frame length ", Integer.valueOf(next.length), " data ", HexUtil.byteArray2HexStr(next));
                if (z && next.length > 0) {
                    next = b(next, z2, str);
                }
                int length = next.length + 2;
                byte[] bArr2 = new byte[length];
                bArr2[0] = (byte) size;
                bArr2[1] = (byte) i;
                i++;
                System.arraycopy(next, 0, bArr2, 2, next.length);
                LogUtil.a("BtHaiGeSendUtil", "encryptFrame length ", Integer.valueOf(length), " data ", HexUtil.byteArray2HexStr(bArr2));
                arrayList.addAll(cgf.c(bArr2, z));
            }
        }
        return arrayList;
    }

    public static ArrayList<byte[]> c(byte[] bArr, boolean z) {
        ArrayList<byte[]> arrayList = new ArrayList<>();
        if (bArr == null || bArr.length == 0) {
            arrayList.add(new byte[0]);
            return arrayList;
        }
        int i = z ? 222 : 238;
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i2 + i;
            int min = Math.min(bArr.length, i3) - i2;
            byte[] bArr2 = new byte[min];
            System.arraycopy(bArr, i2, bArr2, 0, min);
            arrayList.add(bArr2);
            i2 = i3;
        }
        return arrayList;
    }

    private static byte[] b(byte[] bArr, boolean z, String str) {
        byte[] bArr2 = new byte[0];
        try {
            bArr2 = d(16);
        } catch (NoSuchAlgorithmException e2) {
            LogUtil.b("BtHaiGeSendUtil", "sendData NoSuchAlgorithmException", e2.getMessage());
        }
        byte[] c2 = cgh.c(bArr, a(z, str), bArr2);
        byte[] bArr3 = new byte[bArr2.length + c2.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        System.arraycopy(c2, 0, bArr3, bArr2.length, c2.length);
        return bArr3;
    }

    private static byte[] a(boolean z, String str) {
        if (z) {
            byte[] e2 = cgh.e();
            byte[] c2 = cgh.c();
            byte[] a2 = cvx.a(cvx.c(str.replace(":", "") + AgdConstant.INSTALL_TYPE_DEFAULT));
            LogUtil.c("BtHaiGeSendUtil", "encryptFrame c3", HexUtil.byteArray2HexStr(a2));
            return cgh.b(e2, c2, a2);
        }
        return cgh.d(str);
    }
}
