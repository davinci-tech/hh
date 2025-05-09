package com.huawei.wisecloud.drmclient.utils;

import android.util.Base64;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import com.huawei.wisecloud.drmclient.log.HwDrmLog;
import java.io.UnsupportedEncodingException;

/* loaded from: classes7.dex */
public class Base64Util {
    private static final String TAG = "Base64Util";

    public static String base64Encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }

    public static String base64Encode(String str) {
        return Base64.encodeToString(str.getBytes(HwDrmConstant.DRM_CHARSET), 2);
    }

    public static byte[] base64Decode2Bytes(String str) {
        try {
            return Base64.decode(str, 2);
        } catch (IllegalArgumentException e) {
            HwDrmLog.e(TAG, "base64Decode2Bytes error:" + HwDrmLog.printException((Exception) e));
            return new byte[0];
        }
    }

    public static String base64Decode2String(String str) {
        try {
            return new String(Base64.decode(str, 2), "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            HwDrmLog.e(TAG, "UnsupportedEncodingException is caught in base64Decode2String " + HwDrmLog.printException(e));
            return "";
        }
    }

    public static String base64UrlEncode(byte[] bArr) {
        return Base64.encodeToString(bArr, 10);
    }

    public static String base64UrlEncode(String str) {
        return Base64.encodeToString(str.getBytes(HwDrmConstant.DRM_CHARSET), 10);
    }

    public static byte[] base64UrlDecode2Bytes(String str) {
        try {
            return Base64.decode(str, 10);
        } catch (IllegalArgumentException e) {
            HwDrmLog.e(TAG, "base64UrlDecode2Bytes error:" + HwDrmLog.printException((Exception) e));
            return new byte[0];
        }
    }

    public static String base64UrlDecode2String(String str) {
        try {
            return new String(Base64.decode(str, 10), "UTF-8");
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            HwDrmLog.e(TAG, "UnsupportedEncodingException is caught in base64Decode2String " + HwDrmLog.printException(e));
            return "";
        }
    }
}
