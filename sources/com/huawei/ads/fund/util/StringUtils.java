package com.huawei.ads.fund.util;

import android.text.TextUtils;
import android.util.Log;
import com.huawei.openplatform.abl.log.HiAdLog;
import com.huawei.operation.utils.Constants;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes2.dex */
public abstract class StringUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final Pattern f1694a = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

    public static String unicodeDecode(String str) {
        if (isBlank(str)) {
            return str;
        }
        Matcher matcher = f1694a.matcher(str);
        while (matcher.find()) {
            char parseInt = (char) Integer.parseInt(matcher.group(2), 16);
            str = str.replace(matcher.group(1), parseInt + "");
        }
        return str;
    }

    public static Short toShort(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Short.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toShort " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Long toLong(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toLong " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Integer toInteger(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Integer.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toInteger " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Float toFloat(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Float.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toFloat " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Double toDouble(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toDouble " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static Byte toByte(String str) {
        if (isBlank(str)) {
            return null;
        }
        try {
            return Byte.valueOf(str);
        } catch (NumberFormatException e) {
            HiAdLog.e("StringUtils", "toByte " + e.getClass().getSimpleName());
            return null;
        }
    }

    public static int parseIntOrDefault(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            Log.d("StringUtils", "parseIntOrDefault exception: " + e.getClass().getSimpleName());
            return i;
        }
    }

    public static double parseDoubleOrDefault(String str, float f) {
        if (TextUtils.isEmpty(str)) {
            return f;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            HiAdLog.w("StringUtils", "parseFloatOrDefault exception: " + e.getClass().getSimpleName());
            return f;
        }
    }

    public static String listToString(List<String> list, String str) {
        if (ListUtil.isEmpty(list)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str2 : list) {
            if (!TextUtils.isEmpty(str2)) {
                if (!z) {
                    sb.append(str);
                }
                sb.append(str2);
                z = false;
            }
        }
        return sb.toString();
    }

    public static String listToSelectionArgs(String[] strArr) {
        if (ListUtil.isEmpty(strArr)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(Constants.LEFT_BRACKET_ONLY);
        int length = strArr.length;
        boolean z = true;
        int i = 0;
        while (i < length) {
            String str = strArr[i];
            if (!z) {
                sb.append(",");
            }
            sb.append("'");
            sb.append(str);
            sb.append("'");
            i++;
            z = false;
        }
        sb.append(Constants.RIGHT_BRACKET_ONLY);
        return sb.toString();
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String getFormatMsg(String str, Object[] objArr) {
        try {
            return String.format(Locale.ENGLISH, str, objArr);
        } catch (Throwable unused) {
            return str;
        }
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        return str.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    public static String a(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj != null) {
            return String.valueOf(obj);
        }
        return null;
    }
}
