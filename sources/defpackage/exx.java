package defpackage;

import java.util.regex.Pattern;

/* loaded from: classes3.dex */
public class exx {
    private static final Pattern b = Pattern.compile("(.*([/\\\\]{1}[\\.\\.]{1,2}|[\\.\\.]{1,2}[/\\\\]{1}|\\.\\.).*|\\.)");

    public static boolean d(String str) {
        return !b.matcher(str).matches();
    }

    private static String e(String str) {
        if (str == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            int i2 = 0;
            while (true) {
                if (i2 >= 94) {
                    break;
                }
                if (str.charAt(i) == "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".charAt(i2)) {
                    stringBuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890-=[];\\',./ ~!@#$%^&*()_+\"{}|:<>?".charAt(i2));
                    break;
                }
                i2++;
            }
        }
        return stringBuffer.toString();
    }

    public static String a(String str) {
        if (d(str)) {
            return e(str);
        }
        throw new RuntimeException("Invalid file path");
    }
}
