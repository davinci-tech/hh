package defpackage;

import android.text.format.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes7.dex */
public class brk {
    public static String e(long j) {
        return new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyy/M/d")).format(new Date(j));
    }
}
