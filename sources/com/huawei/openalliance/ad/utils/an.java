package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import com.huawei.openalliance.ad.ho;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class an {
    public static byte[] b(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        byte[] bytes = upperCase.getBytes("UTF-8");
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (Byte.decode("0x".concat(new String(new byte[]{bytes[i2 + 1]}, "UTF-8"))).byteValue() ^ ((byte) (Byte.decode("0x".concat(new String(new byte[]{bytes[i2]}, "UTF-8"))).byteValue() << 4)));
        }
        return bArr;
    }

    public static byte[] a(String str) {
        StringBuilder sb;
        byte[] bArr = new byte[0];
        try {
            return b(str);
        } catch (NumberFormatException e) {
            e = e;
            sb = new StringBuilder("hex string 2 byte: ");
            sb.append(e.getClass().getSimpleName());
            ho.d("HexUtil", sb.toString());
            return bArr;
        } catch (Throwable th) {
            e = th;
            sb = new StringBuilder("hex string 2 byte: ");
            sb.append(e.getClass().getSimpleName());
            ho.d("HexUtil", sb.toString());
            return bArr;
        }
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
