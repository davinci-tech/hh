package defpackage;

import android.text.TextUtils;
import android.util.Base64;
import com.huawei.hwlogsmodel.LogUtil;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;

/* loaded from: classes3.dex */
public class ctx {
    private static char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1'};

    public static int a(int i, int i2) {
        return (i - 1) | (((i2 >> 4) - 1) << 5);
    }

    public static String d() {
        try {
            SecureRandom secureRandom = new SecureRandom();
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, secureRandom);
            return Base64.encodeToString(keyGenerator.generateKey().getEncoded(), 0);
        } catch (NoSuchAlgorithmException unused) {
            LogUtil.b("GenerateUtil", "generateKey catch NoSuchAlgorithmException");
            return "";
        }
    }

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        nsg.b().nextBytes(bArr);
        return bArr;
    }

    public static int c(byte[] bArr) {
        if (bArr == null) {
            return 255;
        }
        int i = 255;
        for (byte b : bArr) {
            i ^= b & 255;
            for (int i2 = 0; i2 < 8; i2++) {
                int i3 = i & 1;
                i >>= 1;
                if (i3 != 0) {
                    i ^= 184;
                }
            }
        }
        return i;
    }

    public static int b() {
        return new SecureRandom().nextInt(920) + 58080;
    }

    public static String c(int i) {
        if (i <= 0) {
            LogUtil.h("GenerateUtil", "parameter is null!");
            return "";
        }
        SecureRandom secureRandom = new SecureRandom();
        char[] cArr = new char[i];
        int nextInt = secureRandom.nextInt();
        int i2 = 0;
        int i3 = 0;
        while (i2 < i % 5) {
            cArr[i3] = d[nextInt & 63];
            nextInt >>= 6;
            i2++;
            i3++;
        }
        for (int i4 = 0; i4 < i / 5; i4++) {
            int nextInt2 = secureRandom.nextInt();
            int i5 = 0;
            while (i5 < 5) {
                cArr[i3] = d[nextInt2 & 63];
                nextInt2 >>= 6;
                i5++;
                i3++;
            }
        }
        return new String(cArr, 0, i);
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("GenerateUtil", "illegal argument!");
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            stringBuffer.append(str);
            stringBuffer.append(".");
        }
        return stringBuffer.substring(0, stringBuffer.length() - 1);
    }

    public static byte[] a() {
        return new byte[16];
    }
}
