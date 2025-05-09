package defpackage;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes7.dex */
public class uwq {
    public static Class<?> e(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static <T> T e(Object obj, String str, Class<?>[] clsArr, Object[] objArr, Class<T> cls) {
        if (!e(obj, clsArr, objArr, cls, str)) {
            return null;
        }
        try {
            Object invoke = obj.getClass().getDeclaredMethod(str, clsArr).invoke(obj, objArr);
            if (invoke == null || !cls.isInstance(invoke)) {
                return null;
            }
            return cls.cast(invoke);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            uwn.b("ReflectUtil", "Unexpected exception, invoke method failed.");
            return null;
        }
    }

    private static <T> boolean e(Object obj, Class<?>[] clsArr, Object[] objArr, Class<T> cls, String str) {
        if (obj == null || clsArr == null || objArr == null || cls == null) {
            uwn.e("ReflectUtil", "Parameters is invalid.");
            return false;
        }
        if (!TextUtils.isEmpty(str)) {
            return true;
        }
        uwn.e("ReflectUtil", "Method not found.");
        return false;
    }
}
