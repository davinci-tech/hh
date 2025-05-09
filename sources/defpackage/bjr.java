package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: classes3.dex */
public class bjr {
    public static boolean c(int i) {
        return 3 == i || 2 == i || 8 == i;
    }

    public static boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return bjp.d().i(str);
    }

    public static String a(String str, int i) {
        if (str == null) {
            LogUtil.a("HiChainLiteCommandUtil", "getSecretKey null");
            return "";
        }
        return d(bmf.d(str, i));
    }

    public static String d(String str) {
        byte[] c;
        if (TextUtils.isEmpty(str) || (c = bmy.c(str)) == null) {
            return "";
        }
        try {
            return a(c, "utf-8");
        } catch (CharacterCodingException unused) {
            LogUtil.e("HiChainLiteCommandUtil", "thisKey null");
            return "";
        }
    }

    private static String a(byte[] bArr, String str) throws CharacterCodingException {
        return Charset.forName(str).newDecoder().decode(ByteBuffer.wrap(bArr)).toString();
    }

    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return bjp.d().b(str);
    }

    public static byte[] c(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            LogUtil.a("HiChainLiteCommandUtil", "getAuthenticToken null");
            return bjs.b;
        }
        bjp.d().d(str, 1);
        return a(str, str2, str3);
    }

    private static byte[] a(String str, String str2, String str3) {
        LogUtil.c("HiChainLiteCommandUtil", "getPbkToken");
        if (str2 == null || str3 == null) {
            LogUtil.a("HiChainLiteCommandUtil", "getPbkToken params incorrect");
            return bjs.b;
        }
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(str2.toCharArray(), str3.getBytes("utf-8"), 1000, 256)).getEncoded();
        } catch (UnsupportedEncodingException unused) {
            LogUtil.a("HiChainLiteCommandUtil", "UnsupportedEncodingException");
            return bjs.b;
        } catch (NoSuchAlgorithmException unused2) {
            LogUtil.a("HiChainLiteCommandUtil", "NoSuchAlgorithmException");
            return d(str, str2, str3);
        } catch (InvalidKeySpecException unused3) {
            LogUtil.a("HiChainLiteCommandUtil", "InvalidKeySpecException");
            return bjs.b;
        }
    }

    private static byte[] d(String str, String str2, String str3) {
        LogUtil.c("HiChainLiteCommandUtil", "getMacShaToken");
        bjp.d().d(str, 2);
        return c(str2, str3);
    }

    public static byte[] c(String str, String str2) {
        try {
            return bgv.d(blq.a(str), blq.a(str2));
        } catch (UnsupportedEncodingException unused) {
            LogUtil.e("HiChainLiteCommandUtil", "HMac256 occur exception with info is UnsupportedEncodingException");
            return bjs.b;
        } catch (InvalidKeyException unused2) {
            LogUtil.e("HiChainLiteCommandUtil", "HMac256 occur exception with info is InvalidKeyException");
            return bjs.b;
        } catch (NoSuchAlgorithmException unused3) {
            LogUtil.e("HiChainLiteCommandUtil", "HMac256 occur exception with info is NoSuchAlgorithmException");
            return bjs.b;
        }
    }

    public static String d(DeviceInfo deviceInfo, String str, String str2, int i) {
        if (deviceInfo == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        String deviceMac = deviceInfo.getDeviceMac();
        if (bjp.d().h(deviceMac)) {
            String g = bjp.d().g(deviceMac);
            if (TextUtils.isEmpty(g)) {
                LogUtil.e("HiChainLiteCommandUtil", "secret lost");
                bmf.e();
                return "";
            }
            return blq.d(c(g + str, str2));
        }
        return b(str, i, str2, deviceMac);
    }

    public static String b(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        byte[] a2 = blq.a(bmv.c(bhh.e(str3), "SHA-256"));
        byte[] a3 = blq.a(bhh.b(i));
        for (int i2 = 0; i2 < a3.length; i2++) {
            a3[i2] = (byte) (a2[i2] ^ a3[i2]);
        }
        String d = blq.d(c(str3, blq.d(a3) + str, str2));
        LogUtil.c("HiChainLiteCommandUtil", "hichainlate authentic Kn knZero:", blt.a(d));
        if (str.equals("0100")) {
            bjp.d().e(str3, d.substring(0, d.length() / 2));
        }
        return blq.d(c(d, str2));
    }

    public static byte[] a() {
        try {
            return bgv.d(16);
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.e("HiChainLiteCommandUtil", "getRandomNum exception");
            return bjs.b;
        }
    }
}
