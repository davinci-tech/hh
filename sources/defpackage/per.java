package defpackage;

import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class per {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        if (str.contains("@")) {
            return e(str);
        }
        if (c(str)) {
            return c(str, 0.5d, 2, 5);
        }
        return c(str, 0.5d, 2, 5);
    }

    private static String e(String str) {
        String c;
        String[] split = str.split("@");
        if (split.length == 2) {
            if (split[0].length() > 0 && split[1].length() > 0) {
                String str2 = split[0];
                String str3 = split[1];
                if (str2.length() > 6 && b(str2)) {
                    if (str2.length() >= 8) {
                        c = c(str2, 0.6d, 4, 3);
                    } else {
                        c = c(str2, 0.5d, 3, 3);
                    }
                } else if (str2.length() > 8) {
                    c = c(str2, 0.6d, 0, 3);
                } else {
                    c = c(str2, 0.5d, 0, 3);
                }
                int intValue = new BigDecimal((str3.length() + 1) * 0.3d).setScale(0, 4).intValue();
                StringBuilder sb = new StringBuilder(16);
                sb.append(c);
                sb.append("@");
                for (int i = 0; i < str3.length(); i++) {
                    if (i < intValue) {
                        sb.append("*");
                    } else {
                        sb.append(str3.charAt(i));
                    }
                }
                return sb.toString();
            }
        }
        return c(str, 0.5d, 2, 3);
    }

    private static String c(String str, double d, int i, int i2) {
        if (str.length() < i2) {
            return "*****".substring(0, str.length());
        }
        int intValue = new BigDecimal(str.length() * d).setScale(0, 4).intValue();
        if (intValue > str.length() - i) {
            intValue = str.length() - i;
        }
        return str.substring(0, (str.length() - i) - intValue) + e(intValue) + str.substring(str.length() - i);
    }

    private static boolean c(String str) {
        if (TextUtils.isEmpty("^([0-9]|(\\+))\\d+$") || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return Pattern.compile("^([0-9]|(\\+))\\d+$").matcher(str).matches();
        } catch (PatternSyntaxException unused) {
            return false;
        }
    }

    private static String e(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private static boolean b(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }
}
