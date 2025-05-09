package defpackage;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class ttx {
    private static final String e = "PropertyUtil";

    public static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            Object invoke = cls.getDeclaredMethod("get", String.class, String.class).invoke(cls, str, str2);
            if (invoke instanceof String) {
                return (String) invoke;
            }
        } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | InvocationTargetException unused) {
            Log.e(e, "An exception occurred while reading string system properties: " + str);
        }
        return str2;
    }
}
