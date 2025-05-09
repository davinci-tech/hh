package com.huawei.secure.android.common.webview;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.huawei.secure.android.common.util.LogsUtil;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes6.dex */
public class UriUtil {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8663a = "UriUtil";

    private static String a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return !URLUtil.isNetworkUrl(str) ? str : getHostByURI(str);
        }
        LogsUtil.i(f8663a, "whiteListUrl is null");
        return null;
    }

    public static String getHostByURI(String str) {
        if (TextUtils.isEmpty(str)) {
            LogsUtil.i(f8663a, "url is null");
            return str;
        }
        try {
            if (URLUtil.isNetworkUrl(str)) {
                return new URL(str.replaceAll("[\\\\#]", "/")).getHost();
            }
            LogsUtil.e(f8663a, "url don't starts with http or https");
            return "";
        } catch (MalformedURLException e) {
            LogsUtil.e(f8663a, "getHostByURI error  MalformedURLException : " + e.getMessage());
            return "";
        }
    }

    public static boolean isUrlHostAndPathInWhitelist(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            LogsUtil.e(f8663a, "whitelist is null");
            return false;
        }
        for (String str2 : strArr) {
            if (isUrlHostAndPathMatchWhitelist(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUrlHostAndPathMatchWhitelist(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            String decode = Uri.decode(str);
            String decode2 = Uri.decode(str2);
            if (!decode.contains("..") && !decode2.contains("@")) {
                if (str.contains("..") || str.contains("@")) {
                    Log.e(f8663a, "url contains unsafe char");
                    return false;
                }
                if (!str2.equals(str)) {
                    if (!str.startsWith(str2 + "?")) {
                        if (!str.startsWith(str2 + "#")) {
                            if (!str2.endsWith("/")) {
                                return false;
                            }
                            if (Uri.parse(decode).getPathSegments().size() - Uri.parse(decode2).getPathSegments().size() != 1) {
                                return false;
                            }
                            return str.startsWith(str2);
                        }
                    }
                }
                return true;
            }
            Log.e(f8663a, "url contains unsafe char");
        }
        return false;
    }

    public static boolean isUrlHostInWhitelist(String str, String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            LogsUtil.e(f8663a, "whitelist is null");
            return false;
        }
        for (String str2 : strArr) {
            if (isUrlHostMatchWhitelist(str, str2)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isUrlHostMatchWhitelist(String str, String str2) {
        String hostByURI = getHostByURI(str);
        if (TextUtils.isEmpty(hostByURI) || TextUtils.isEmpty(str2)) {
            LogsUtil.e(f8663a, "url or whitelist is null");
            return false;
        }
        String a2 = a(str2);
        if (TextUtils.isEmpty(a2)) {
            Log.e(f8663a, "whitelist host is null");
            return false;
        }
        if (a2.equals(hostByURI)) {
            return true;
        }
        if (hostByURI.endsWith(a2)) {
            try {
                String substring = hostByURI.substring(0, hostByURI.length() - a2.length());
                if (substring.endsWith(".")) {
                    return substring.matches("^[A-Za-z0-9.-]+$");
                }
                return false;
            } catch (IndexOutOfBoundsException e) {
                LogsUtil.e(f8663a, "IndexOutOfBoundsException" + e.getMessage());
            } catch (Exception e2) {
                LogsUtil.e(f8663a, "Exception : " + e2.getMessage());
                return false;
            }
        }
        return false;
    }

    public static boolean isUrlHostSameWhitelist(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return TextUtils.equals(getHostByURI(str), a(str2));
        }
        Log.e(f8663a, "isUrlHostSameWhitelist: url or host is null");
        return false;
    }

    public static boolean isUrlHostSameWhitelist(String str, String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str2 : strArr) {
                if (isUrlHostSameWhitelist(str, str2)) {
                    return true;
                }
            }
            return false;
        }
        LogsUtil.e(f8663a, "whitelist is null");
        return false;
    }
}
