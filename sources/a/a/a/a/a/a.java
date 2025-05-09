package a.a.a.a.a;

import android.util.Log;
import java.util.IllegalFormatException;
import java.util.Locale;

/* loaded from: classes2.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f125a = true;
    public static boolean b = true;
    public static boolean c = true;

    public static void c(String str, String str2) {
        if (b) {
            Log.w(str, a(str2, new Object[0]));
        }
    }

    public static void b(String str, String str2) {
        if (f125a) {
            Log.i(str, a(str2, new Object[0]));
        }
    }

    public static void a(String str, String str2) {
        if (c) {
            Log.e(str, a(str2, new Object[0]));
        }
    }

    public static String a(String str, Object... objArr) {
        if (objArr.length == 0) {
            return str;
        }
        try {
            return String.format(Locale.ENGLISH, str, objArr);
        } catch (IllegalFormatException unused) {
            Log.e("LogUtil", "Print log format catch IllegalFormatException.");
            return str;
        }
    }
}
