package health.compact.a.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class StringUtils {
    private StringUtils() {
    }

    public static boolean g(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean i(String str) {
        return !g(str);
    }

    private static boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return Pattern.compile(str2).matcher(str).matches();
    }

    public static boolean c(String str) {
        if (g(str)) {
            return false;
        }
        return a(str, "[a-zA-Z]+");
    }

    public static boolean e(String str) {
        if (g(str)) {
            return false;
        }
        return a(str, "[^\\x00-\\xff]");
    }

    public static boolean a(String str) {
        if (g(str)) {
            return false;
        }
        return a(str, "[0-9]*");
    }

    public static String c(Object obj) {
        return obj == null ? "" : obj.toString().trim();
    }

    public static boolean b(String str) {
        return a(str, "^[0-9]+\\*+[0-9]+$");
    }

    public static boolean d(String str) {
        return a(str, "^[a-zA-Z0-9_-|*]+@[a-zA-Z0-9_-|*]+(\\.[a-zA-Z0-9_-|*]+)+$");
    }
}
