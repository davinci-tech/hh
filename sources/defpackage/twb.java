package defpackage;

import android.util.Log;
import com.huawei.wisesecurity.ucs.common.log.ILogUcs;
import java.text.MessageFormat;

/* loaded from: classes7.dex */
public class twb {
    private static ILogUcs b = new twd();

    public static void b(String str, String str2, Object... objArr) {
        ILogUcs iLogUcs = b;
        if (iLogUcs != null) {
            iLogUcs.w(b(str), c(str, str2, objArr));
        }
    }

    public static void d(ILogUcs iLogUcs) {
        if (iLogUcs != null) {
            b = iLogUcs;
        }
    }

    public static void a(String str, String str2, Object... objArr) {
        ILogUcs iLogUcs = b;
        if (iLogUcs != null) {
            iLogUcs.i(b(str), c(str, str2, objArr));
        }
    }

    private static String b(String str) {
        return "UCS-" + str;
    }

    private static String c(String str, String str2, Object... objArr) {
        try {
            return MessageFormat.format(str2, objArr);
        } catch (Throwable th) {
            Log.e(str, "log error : " + th.getMessage());
            return "return default";
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        ILogUcs iLogUcs = b;
        if (iLogUcs != null) {
            iLogUcs.e(b(str), c(str, str2, objArr));
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        ILogUcs iLogUcs = b;
        if (iLogUcs != null) {
            iLogUcs.d(b(str), c(str, str2, objArr));
        }
    }
}
