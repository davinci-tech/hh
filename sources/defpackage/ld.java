package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public class ld {
    public static String a(String str) {
        String[] split = str.split("=");
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        return str2.contains("\"") ? str2.replaceAll("\"", "") : str2;
    }

    public static String b(String str) {
        if (str.contains("biz_type")) {
            return a(str);
        }
        return null;
    }

    public static String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split("&");
        if (split.length == 0) {
            return "";
        }
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        for (String str6 : split) {
            if (TextUtils.isEmpty(str2)) {
                str2 = b(str6);
            }
            if (TextUtils.isEmpty(str3)) {
                str3 = d(str6);
            }
            if (TextUtils.isEmpty(str4)) {
                str4 = j(str6);
            }
            if (TextUtils.isEmpty(str5)) {
                str5 = e(str6);
            }
        }
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str2)) {
            sb.append("biz_type=" + str2 + ";");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append("biz_no=" + str3 + ";");
        }
        if (!TextUtils.isEmpty(str4)) {
            sb.append("trade_no=" + str4 + ";");
        }
        if (!TextUtils.isEmpty(str5)) {
            sb.append("app_userid=" + str5 + ";");
        }
        String sb2 = sb.toString();
        return sb2.endsWith(";") ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    public static String d(String str) {
        if (str.contains("biz_no")) {
            return a(str);
        }
        return null;
    }

    public static String e(String str) {
        if (str.contains("app_userid")) {
            return a(str);
        }
        return null;
    }

    public static String j(String str) {
        if (!str.contains("trade_no") || str.startsWith("out_trade_no")) {
            return null;
        }
        return a(str);
    }
}
