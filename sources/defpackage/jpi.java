package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.math.BigInteger;

/* loaded from: classes5.dex */
public class jpi {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f14015a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final int d = 62;

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            LogUtil.h("HwSecureUtils", "getWaterMarkNumber deviceMac and userId is null");
            return "";
        }
        BigInteger d2 = d(str, str2);
        StringBuilder sb = new StringBuilder(16);
        while (d2.compareTo(BigInteger.ONE) >= 0) {
            long j = d;
            sb.append(f14015a[d2.remainder(BigInteger.valueOf(j)).intValue()]);
            d2 = d2.divide(BigInteger.valueOf(j));
        }
        return sb.toString();
    }

    private static BigInteger d(String str, String str2) {
        String str3;
        if (TextUtils.isEmpty(str)) {
            str3 = "2" + str2;
        } else if (TextUtils.isEmpty(str2)) {
            String b = b(str);
            if (TextUtils.isEmpty(b)) {
                return BigInteger.ZERO;
            }
            str3 = "3" + b;
        } else {
            String b2 = b(str);
            if (TextUtils.isEmpty(b2)) {
                str3 = "2" + str2;
            } else {
                str3 = "1" + b2 + str2;
            }
        }
        return new BigInteger(str3);
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwSecureUtils", "hex16To10 stringHex is null");
            return "";
        }
        try {
            String bigInteger = new BigInteger(str, 16).toString(10);
            int length = bigInteger.length();
            if (length >= 5) {
                return bigInteger;
            }
            StringBuilder sb = new StringBuilder(16);
            for (int i = 0; i < 5 - length; i++) {
                sb.append("0");
            }
            return ((Object) sb) + bigInteger;
        } catch (NumberFormatException unused) {
            LogUtil.b("HwSecureUtils", "hex16To10 NumberFormatException");
            return "";
        }
    }
}
