package defpackage;

import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class slc {
    public static Object b(Object obj, String str, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (IllegalAccessException unused) {
            Log.e("HwReflectUtil", "In get object, IllegalAccessException in reflect " + str);
            return null;
        } catch (NoSuchFieldException unused2) {
            Log.e("HwReflectUtil", "In get object, No field in reflect " + str);
            return null;
        }
    }

    public static Method b(String str, Class[] clsArr, String str2) {
        try {
            return c(str, clsArr, Class.forName(str2));
        } catch (ClassNotFoundException unused) {
            Log.e("HwReflectUtil", "ClassNotFoundException in reflect call " + str);
            return null;
        }
    }

    public static Object c(Object obj, String str, Class[] clsArr, Object[] objArr, Class<?> cls) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            Log.e("HwReflectUtil", "IllegalAccessException in reflect call " + str);
            return null;
        } catch (IllegalArgumentException unused2) {
            Log.e("HwReflectUtil", "IllegalArgumentException in reflect call " + str);
            return null;
        } catch (NoSuchMethodException unused3) {
            Log.e("HwReflectUtil", "there is no " + str + " method");
            return null;
        } catch (InvocationTargetException unused4) {
            Log.e("HwReflectUtil", "InvocationTargetException in reflect call " + str);
            return null;
        }
    }

    public static Object c(Object obj, Method method, Object[] objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            Log.e("HwReflectUtil", "IllegalAccessException in reflect call " + method.getName());
            return null;
        } catch (InvocationTargetException unused2) {
            Log.e("HwReflectUtil", "InvocationTargetException in reflect call " + method.getName());
            return null;
        }
    }

    public static void e(String str, Object obj, Object obj2, Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (IllegalAccessException unused) {
            Log.e("HwReflectUtil", "In set object, IllegalAccessException in reflect " + str);
        } catch (IllegalArgumentException unused2) {
            Log.e("HwReflectUtil", "In set object, IllegalArgumentException in reflect " + str);
        } catch (NoSuchFieldException unused3) {
            Log.e("HwReflectUtil", "In set object, No field in reflect " + str);
        } catch (SecurityException unused4) {
            Log.e("HwReflectUtil", "In set object, SecurityException in reflect " + str);
        }
    }

    public static Method c(String str, Class[] clsArr, Class<?> cls) {
        try {
            Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            Log.e("HwReflectUtil", "there is no " + str + " method");
            return null;
        }
    }

    public static Object b(Object obj, Method method) {
        return c(obj, method, (Object[]) null);
    }

    public static Object b(Method method) {
        return c((Object) null, method, (Object[]) null);
    }
}
