package defpackage;

import com.careforeyou.library.enums.Weight_Digit;
import com.careforeyou.library.enums.Weight_Unit;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;

/* loaded from: classes2.dex */
public class oc {
    public static int b(byte b) {
        return b & 255;
    }

    public static byte[] a(byte[] bArr, int i, int i2) {
        int i3;
        if (i2 <= 0 || bArr.length < (i3 = i + i2)) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[i2];
        for (int i4 = i; i4 < i3; i4++) {
            bArr2[i4 - i] = bArr[i4];
        }
        return bArr2;
    }

    public static String b(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String e(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                hexString = "0" + hexString;
            }
            sb.append("0x" + hexString);
            sb.append(",");
        }
        return sb.toString();
    }

    public static int a(byte[] bArr) {
        try {
            return Integer.parseInt(b(bArr), 16);
        } catch (NumberFormatException unused) {
            return 0;
        }
    }

    public static byte d(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        while (true) {
            i = (byte) (i + 1);
            if (i > i2) {
                return b;
            }
            b = (byte) (b ^ bArr[i]);
        }
    }

    public static void b(byte[] bArr, int i, int i2) {
        bArr[i2] = (byte) (i >> 24);
        bArr[i2 + 1] = (byte) (i >> 16);
        bArr[i2 + 2] = (byte) (i >> 8);
        bArr[i2 + 3] = (byte) i;
    }

    public static void d(byte[] bArr, short s, int i) {
        bArr[i] = (byte) (s >> 8);
        bArr[i + 1] = (byte) s;
    }

    public static String a(byte b) {
        return "" + ((int) ((byte) ((b >> 7) & 1))) + ((int) ((byte) ((b >> 6) & 1))) + ((int) ((byte) ((b >> 5) & 1))) + ((int) ((byte) ((b >> 4) & 1))) + ((int) ((byte) ((b >> 3) & 1))) + ((int) ((byte) ((b >> 2) & 1))) + ((int) ((byte) ((b >> 1) & 1))) + ((int) ((byte) (b & 1)));
    }

    public static byte d(byte b) {
        return Byte.parseByte(a(b).substring(7, 8));
    }

    public static byte c(byte b) {
        return Byte.parseByte(a(b).substring(2, 3));
    }

    public static Weight_Unit g(byte b) {
        Weight_Unit weight_Unit = Weight_Unit.KG;
        String a2 = a(b);
        if (a2.substring(3, 5).equalsIgnoreCase(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            return Weight_Unit.JIN;
        }
        if (a2.substring(3, 5).equalsIgnoreCase("10")) {
            return Weight_Unit.LB;
        }
        if (a2.substring(3, 5).equalsIgnoreCase("11")) {
            return Weight_Unit.ST;
        }
        return Weight_Unit.KG;
    }

    public static Weight_Digit e(byte b) {
        Weight_Digit weight_Digit = Weight_Digit.TWO;
        String a2 = a(b);
        if (a2.substring(5, 7).equalsIgnoreCase(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            return Weight_Digit.ZERO;
        }
        if (a2.substring(5, 7).equalsIgnoreCase("10")) {
            return Weight_Digit.ONE;
        }
        return Weight_Digit.TWO;
    }

    public static Weight_Digit i(byte b) {
        Weight_Digit weight_Digit = Weight_Digit.ONE;
        String a2 = a(b);
        if (a2.substring(5, 7).equalsIgnoreCase(HiAnalyticsConstant.KeyAndValue.NUMBER_01)) {
            return Weight_Digit.ZERO;
        }
        if (a2.substring(5, 7).equalsIgnoreCase("10")) {
            return Weight_Digit.TWO;
        }
        return Weight_Digit.ONE;
    }

    public static byte f(byte b) {
        String a2 = a(b);
        oa.e("BytesUtil", "getXiangshanCmdId:" + a2);
        return "1010".equalsIgnoreCase(a2.substring(0, 4)) ? (byte) 1 : (byte) 0;
    }

    public static Weight_Unit j(byte b) {
        Weight_Unit weight_Unit = Weight_Unit.KG;
        String a2 = a(b);
        if (a2.substring(4, 8).equalsIgnoreCase("0001")) {
            return Weight_Unit.KG;
        }
        if (a2.substring(4, 8).equalsIgnoreCase("0002")) {
            return Weight_Unit.LB;
        }
        if (a2.substring(4, 8).equalsIgnoreCase("0003")) {
            return Weight_Unit.ST;
        }
        if (a2.substring(0, 4).equalsIgnoreCase("0004")) {
            return Weight_Unit.JIN;
        }
        return Weight_Unit.KG;
    }

    public static byte[] d(short s) {
        return new byte[]{(byte) (s >> 8), (byte) s};
    }
}
