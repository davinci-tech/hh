package defpackage;

import java.util.regex.Pattern;

/* loaded from: classes7.dex */
public class ks {
    public static final Pattern d = Pattern.compile("([\t\r\n])+");

    public static boolean e(String str) {
        return str == null || str.length() <= 0;
    }

    public static int b(String str) {
        if (str.length() <= 0) {
            return 0;
        }
        int i = 0;
        for (char c : str.toCharArray()) {
            i = (i * 31) + c;
        }
        return i;
    }
}
