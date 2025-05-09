package defpackage;

import android.text.TextUtils;
import android.util.Log;
import health.compact.a.LogUtil;
import java.util.IllegalFormatException;
import java.util.Locale;

/* loaded from: classes8.dex */
public class xh {
    private final String c;

    public xh(String str) {
        this.c = str;
    }

    public void d(String str, Object... objArr) {
        b(4, str, objArr);
    }

    public void e(String str, Object... objArr) {
        b(3, str, objArr);
    }

    public void b(String str, Object... objArr) {
        b(5, str, objArr);
    }

    public void a(String str, Object... objArr) {
        b(6, str, objArr);
    }

    public void e(Throwable th, String str, Object... objArr) {
        if (Log.isLoggable("Bundle_Logger", 6)) {
            LogUtil.e("Bundle_Logger", c(this.c, str, objArr), ", ex=", LogUtil.a(th));
        }
    }

    private void b(int i, String str, Object[] objArr) {
        if (Log.isLoggable("Bundle_Logger", i)) {
            LogUtil.c("Bundle_Logger", c(this.c, str, objArr));
        }
    }

    private static String c(String str, String str2, Object... objArr) {
        String str3 = str + ":" + str2;
        if (objArr == null || objArr.length <= 0) {
            return str3;
        }
        try {
            return String.format(Locale.ENGLISH, str3, objArr);
        } catch (IllegalFormatException e) {
            LogUtil.e("Bundle_Logger", "Unable to format ", str3, ", ex=", LogUtil.a(e));
            return str3 + " [" + TextUtils.join(", ", objArr) + "]";
        }
    }
}
