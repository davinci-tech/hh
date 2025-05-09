package defpackage;

import android.content.Context;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes6.dex */
public class nie {
    public static boolean b(Context context) {
        try {
            return Class.forName("com.huawei.android.provider.SettingsEx") != null;
        } catch (Throwable unused) {
            Log.e("ReflectionUtils", "isSettingsExExit method error");
            return false;
        }
    }

    public static boolean d(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr) != null;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            Log.e("ReflectionUtils", "invoke static method error");
            return false;
        }
    }

    public static Object c(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException | SecurityException | InvocationTargetException unused) {
            Log.e("ReflectionUtils", "get static method error");
            return "";
        }
    }
}
