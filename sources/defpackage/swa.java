package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebSettings;
import java.util.Locale;

/* loaded from: classes7.dex */
public final class swa {

    /* renamed from: a, reason: collision with root package name */
    private static String f17258a;
    private static final Object c = new Object();

    public static String a(Context context) {
        String property;
        if (TextUtils.isEmpty(f17258a)) {
            synchronized (c) {
                try {
                    property = WebSettings.getDefaultUserAgent(context);
                } catch (Exception unused) {
                    property = System.getProperty("http.agent");
                }
                if (property == null) {
                    return f17258a;
                }
                StringBuffer stringBuffer = new StringBuffer();
                int length = property.length();
                for (int i = 0; i < length; i++) {
                    char charAt = property.charAt(i);
                    if (charAt > 31 && charAt < 127) {
                        stringBuffer.append(charAt);
                    }
                    stringBuffer.append(String.format(Locale.ROOT, "\\u%04x", Integer.valueOf(charAt)));
                }
                f17258a = stringBuffer.toString();
            }
        }
        return f17258a;
    }

    public static boolean c(Context context) {
        stq.c("Util", "getDarkTheme system", false);
        return (context.getApplicationContext().getResources().getConfiguration().uiMode & 32) != 0;
    }
}
