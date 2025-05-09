package defpackage;

import android.text.TextUtils;

/* loaded from: classes7.dex */
public final class swe {
    public static boolean b(CharSequence charSequence, boolean z) {
        if (TextUtils.isEmpty(charSequence)) {
            return true;
        }
        return z && TextUtils.isEmpty(charSequence.toString().trim());
    }

    public static int b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return e(str, i);
        }
    }

    public static int e(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            return i;
        }
        try {
            if (b(str)) {
                if (str.startsWith("0X") || str.startsWith("0x")) {
                    str = str.substring(2, str.length());
                }
                i = Integer.parseInt(str, 16);
            } else {
                i = Integer.parseInt(str);
            }
        } catch (NumberFormatException unused) {
        }
        return i;
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        int i = 2;
        if (str.length() <= 2 || str.charAt(0) != '0' || (str.charAt(1) != 'X' && str.charAt(1) != 'x')) {
            i = 0;
        }
        int i2 = 0;
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                if ((charAt < 'A' || charAt > 'F') && (charAt < 'a' || charAt > 'f')) {
                    return false;
                }
                i2++;
            }
            i++;
        }
        return i2 > 0;
    }
}
