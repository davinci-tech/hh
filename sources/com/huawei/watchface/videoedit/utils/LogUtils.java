package com.huawei.watchface.videoedit.utils;

import android.text.TextUtils;
import com.huawei.secure.android.common.util.SafeString;
import java.io.File;

/* loaded from: classes9.dex */
public final class LogUtils {
    private static final int DEFAULT_SIZE = 16;

    public static String garbleFileName(String str) {
        String str2;
        if (TextUtils.isEmpty(str) || str.startsWith("Screenshot_") || str.startsWith("IMG_") || str.startsWith("VID_") || str.startsWith("SVID_")) {
            return str;
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf > 0) {
            str2 = SafeString.substring(str, lastIndexOf, str.length());
            str = SafeString.substring(str, 0, lastIndexOf);
        } else {
            str2 = "";
        }
        int length = str.length();
        if (length <= 3) {
            return "****" + SafeString.substring(str, length - 1) + str2 + "****";
        }
        if (length > 8) {
            return "**" + SafeString.substring(str, 8) + str2 + "**";
        }
        return "***" + SafeString.substring(str, length - 2) + str2 + "***";
    }

    public static String garbleFilePath(String str) {
        String[] split;
        int length;
        if (TextUtils.isEmpty(str) || (length = (split = str.split("/")).length) < 1) {
            return str;
        }
        String garbleFileName = garbleFileName(split[length - 1]);
        if (length > 2) {
            StringBuffer stringBuffer = new StringBuffer(16);
            int i = length - 2;
            String garbleFileName2 = garbleFileName(split[i]);
            for (int i2 = 0; i2 < i; i2++) {
                stringBuffer.append(File.separator + split[i2]);
            }
            return stringBuffer.toString() + File.separator + garbleFileName2 + File.separator + garbleFileName;
        }
        return File.separator + split[0] + File.separator + garbleFileName;
    }
}
