package com.huawei.openplatform.abl.log.util;

import android.net.Uri;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

/* loaded from: classes5.dex */
public abstract class g {
    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str.contains("://") ? b(str) : a(str);
    }

    private static String b(String str) {
        String substring;
        StringBuilder sb = new StringBuilder();
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (scheme != null) {
            sb.append(scheme);
            sb.append("://");
        }
        String lastPathSegment = parse.getLastPathSegment();
        if (lastPathSegment == null) {
            try {
                lastPathSegment = new URL(str).getHost();
            } catch (MalformedURLException unused) {
                lastPathSegment = Uri.parse(str.replaceAll("@", "")).getHost();
            }
        } else {
            sb.append("******/");
        }
        if (lastPathSegment != null) {
            int length = lastPathSegment.length();
            if (length > 3) {
                substring = lastPathSegment.substring(0, 3);
            } else if (length > 1) {
                substring = lastPathSegment.substring(0, length - 1);
            }
            sb.append(substring);
        }
        sb.append(Constants.CONFUSION_CHARS);
        return sb.toString();
    }

    public static String a(String str, Object[] objArr) {
        try {
            return String.format(Locale.ENGLISH, str, objArr);
        } catch (Throwable unused) {
            return str;
        }
    }

    private static String a(String str) {
        StringBuilder sb;
        int i;
        int lastIndexOf = str.lastIndexOf("/");
        if (lastIndexOf >= 0 && (i = lastIndexOf + 1) < str.length()) {
            str = str.substring(i);
        }
        int length = str.length();
        if (length > 3) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str.substring(0, 3));
            sb = sb2;
        } else {
            if (length <= 1) {
                return Constants.CONFUSION_CHARS;
            }
            sb = new StringBuilder();
            sb.append(str.substring(0, length - 1));
        }
        sb.append(Constants.CONFUSION_CHARS);
        return sb.toString();
    }
}
