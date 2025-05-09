package defpackage;

import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes5.dex */
public class ktz {
    private static ThreadLocal<SimpleDateFormat> d = new ThreadLocal<>();

    private static SimpleDateFormat b() {
        SimpleDateFormat simpleDateFormat = d.get();
        if (simpleDateFormat != null) {
            return simpleDateFormat;
        }
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        d.set(simpleDateFormat2);
        return simpleDateFormat2;
    }

    public static String c(Date date) {
        return b().format(date);
    }
}
