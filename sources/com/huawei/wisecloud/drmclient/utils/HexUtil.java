package com.huawei.wisecloud.drmclient.utils;

import android.text.TextUtils;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/* loaded from: classes7.dex */
public class HexUtil {
    public static String byteArray2HexStr(byte[] bArr) {
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

    public static byte[] hexStr2ByteArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        String upperCase = str.toUpperCase(Locale.US);
        int length = upperCase.length() / 2;
        byte[] bArr = new byte[length];
        try {
            byte[] bytes = upperCase.getBytes("UTF-8");
            for (int i = 0; i < length; i++) {
                StringBuilder sb = new StringBuilder();
                sb.append("0x");
                int i2 = i * 2;
                sb.append(new String(new byte[]{bytes[i2]}, "UTF-8"));
                bArr[i] = (byte) (((byte) (Byte.decode(sb.toString()).byteValue() << 4)) ^ Byte.decode("0x" + new String(new byte[]{bytes[i2 + 1]}, "UTF-8")).byteValue());
            }
        } catch (UnsupportedEncodingException | NumberFormatException e) {
            HwDrmLog.e("HexUtil", "hex string 2 byte array exception : " + HwDrmLog.printException(e));
        }
        return bArr;
    }

    public static byte[] bytes2Hex2UpperCase2bytes(byte[] bArr) {
        try {
            return byteArray2HexStr(bArr).toUpperCase(Locale.ENGLISH).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            HwDrmLog.e("HexUtil", "hex string 2 byte array exception : " + HwDrmLog.printException((Exception) e));
            return null;
        }
    }
}
