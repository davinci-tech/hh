package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.watchface.utils.HwLog;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* loaded from: classes7.dex */
public class am {
    public static String a(String str) {
        return a(str, "UTF-8");
    }

    public static String a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, str2);
        } catch (UnsupportedEncodingException e) {
            HwLog.e("SafeURLEncoder", "Error decoding url, " + HwLog.printException((Exception) e));
            return str;
        }
    }

    public static String b(String str) {
        return b(str, "UTF-8");
    }

    public static String b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return URLDecoder.decode(str, str2);
        } catch (UnsupportedEncodingException e) {
            HwLog.e("SafeURLEncoder", "Error decoding url, " + HwLog.printException((Exception) e));
            return str;
        } catch (Exception e2) {
            HwLog.e("SafeURLEncoder", "Error decoding url, " + HwLog.printException(e2));
            return str;
        }
    }
}
