package defpackage;

import android.text.TextUtils;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ocf {
    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "#";
        }
        String d = pf.d(str, "");
        if (TextUtils.isEmpty(d)) {
            return "#";
        }
        String upperCase = d.substring(0, 1).toUpperCase(Locale.ENGLISH);
        return upperCase.matches("[A-Z]") ? upperCase : "#";
    }

    public static int d(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return 1;
        }
        if (TextUtils.isEmpty(str2)) {
            return -1;
        }
        if (!"#".equals(str) && !"#".equals(str2)) {
            return str.compareTo(str2);
        }
        if ("#".equals(str)) {
            return !"#".equals(str2) ? 1 : 0;
        }
        return -1;
    }
}
