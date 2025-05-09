package defpackage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes2.dex */
public class of {
    public static String a() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }

    public static String b(long j) {
        return c(j, "yyyy-MM-dd HH:mm:ss");
    }

    public static String c(long j, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j));
    }
}
