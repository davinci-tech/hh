package defpackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes7.dex */
public class swb {
    public static String e(Date date, String str) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }
}
