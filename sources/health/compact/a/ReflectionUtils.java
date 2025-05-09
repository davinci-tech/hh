package health.compact.a;

import android.text.TextUtils;
import com.huawei.haf.common.utils.CollectionUtils;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes.dex */
public final class ReflectionUtils {
    private ReflectionUtils() {
    }

    public static Class<?> b(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            LogUtil.a("HAF_ReflectionUtils", "getClass ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Class<?> d(String str, ClassLoader classLoader) {
        try {
            return Class.forName(str, true, classLoader);
        } catch (ClassNotFoundException e) {
            LogUtil.a("HAF_ReflectionUtils", "getClass ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Object b(Object obj, String str) {
        try {
            return d(obj, str).get(obj);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LogUtil.a("HAF_ReflectionUtils", "getFieldValue ex=", LogUtil.a(e));
            return null;
        }
    }

    public static boolean c(Object obj, String str, Object obj2) {
        try {
            d(obj, str).set(obj, obj2);
            return true;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LogUtil.a("HAF_ReflectionUtils", "setFieldValue ex=", LogUtil.a(e));
            return false;
        }
    }

    public static Object b(Class<?> cls, String str) {
        try {
            return e(cls, str).get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LogUtil.a("HAF_ReflectionUtils", "getStaticFieldValue ex=", LogUtil.a(e));
            return null;
        }
    }

    public static boolean e(Class<?> cls, String str, Object obj) {
        try {
            e(cls, str).set(null, obj);
            return true;
        } catch (IllegalAccessException | NoSuchFieldException e) {
            LogUtil.a("HAF_ReflectionUtils", "setStaticFieldValue ex=", LogUtil.a(e));
            return false;
        }
    }

    public static Object c(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LogUtil.a("HAF_ReflectionUtils", "invokeMethod ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Object a(Object obj, String str) {
        try {
            return c(e(obj, str, (Class<?>[]) null), obj, (Object[]) null);
        } catch (NoSuchMethodException e) {
            LogUtil.a("HAF_ReflectionUtils", "invokeMethod ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Object c(Class<?> cls, String str) {
        try {
            Method b = b(cls, str, null);
            return c(b, (Object) null, (Object[]) null);
        } catch (NoSuchMethodException e) {
            LogUtil.a("HAF_ReflectionUtils", "invokeStaticMethod ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Object d(Constructor<?> constructor, Object... objArr) {
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            LogUtil.a("HAF_ReflectionUtils", "newInstance ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Object e(String str) {
        Class<?> b = b(str);
        if (b != null) {
            return b(b);
        }
        return null;
    }

    public static Object b(String str, ClassLoader classLoader) {
        Class<?> d = d(str, classLoader);
        if (d != null) {
            return b(d);
        }
        return null;
    }

    public static Object b(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            LogUtil.a("HAF_ReflectionUtils", "newInstance ex=", LogUtil.a(e));
            return null;
        }
    }

    public static Class<?> d(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    public static Class<?> c(String str, ClassLoader classLoader) throws ClassNotFoundException {
        return Class.forName(str, true, classLoader);
    }

    public static Field d(Object obj, String str) throws NoSuchFieldException {
        return e(obj.getClass(), str);
    }

    public static Field e(Class<?> cls, String str) throws NoSuchFieldException {
        Field d = d(cls, str);
        if (!d.isAccessible()) {
            d.setAccessible(true);
        }
        return d;
    }

    public static Field a(Class<?> cls, String str) {
        Field field;
        try {
            field = d(cls, str);
        } catch (NoSuchFieldException e) {
            e = e;
            field = null;
        }
        try {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
        } catch (NoSuchFieldException e2) {
            e = e2;
            LogUtil.e("HAF_ReflectionUtils", "findField ex=", LogUtil.a(e));
            return field;
        }
        return field;
    }

    private static Field d(Class<?> cls, String str) throws NoSuchFieldException {
        try {
            return cls.getField(str);
        } catch (NoSuchFieldException unused) {
            for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                try {
                    return cls2.getDeclaredField(str);
                } catch (NoSuchFieldException unused2) {
                }
            }
            throw new NoSuchFieldException(b("Field", str, cls, null));
        }
    }

    public static Method e(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        return b(obj.getClass(), str, clsArr);
    }

    public static Method b(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        Method c = c(cls, str, clsArr);
        if (!c.isAccessible()) {
            c.setAccessible(true);
        }
        return c;
    }

    public static Method d(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return b(cls, str, clsArr);
        } catch (NoSuchMethodException e) {
            LogUtil.e("HAF_ReflectionUtils", "findMethod ex=", LogUtil.a(e));
            return null;
        }
    }

    private static Method c(Class<?> cls, String str, Class<?>... clsArr) throws NoSuchMethodException {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException unused) {
            for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                try {
                    return cls2.getDeclaredMethod(str, clsArr);
                } catch (NoSuchMethodException unused2) {
                }
            }
            throw new NoSuchMethodException(b("Method", str, cls, clsArr));
        }
    }

    public static Constructor<?> b(Class<?> cls, Class<?>... clsArr) throws NoSuchMethodException {
        Constructor<?> c = c(cls, clsArr);
        if (!c.isAccessible()) {
            c.setAccessible(true);
        }
        return c;
    }

    private static Constructor<?> c(Class<?> cls, Class<?>... clsArr) throws NoSuchMethodException {
        try {
            return cls.getConstructor(clsArr);
        } catch (NoSuchMethodException unused) {
            for (Class<?> cls2 = cls; cls2 != null; cls2 = cls2.getSuperclass()) {
                try {
                    return cls2.getDeclaredConstructor(clsArr);
                } catch (NoSuchMethodException unused2) {
                }
            }
            throw new NoSuchMethodException(b("Constructor", "", cls, clsArr));
        }
    }

    private static String b(String str, String str2, Class<?> cls, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder(128);
        sb.append(str);
        if (!TextUtils.isEmpty(str2)) {
            sb.append(' ');
            sb.append(str2);
        }
        if (clsArr != null) {
            sb.append(" with parameters ");
            sb.append(Arrays.asList(clsArr));
        }
        sb.append(" not found in ");
        sb.append(cls);
        return sb.toString();
    }

    public static void a(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (CollectionUtils.b(objArr)) {
            return;
        }
        Field d = d(obj, str);
        Object obj2 = d.get(obj);
        if (!(obj2 instanceof Object[])) {
            LogUtil.a("HAF_ReflectionUtils", "expandFieldArray object is not array: ", obj2);
            return;
        }
        Object[] objArr2 = (Object[]) obj2;
        Object newInstance = Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        if (newInstance instanceof Object[]) {
            Object[] objArr3 = (Object[]) newInstance;
            System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
            System.arraycopy(objArr2, 0, objArr3, objArr.length, objArr2.length);
            d.set(obj, objArr3);
        }
    }

    public static void e(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (CollectionUtils.b(objArr)) {
            return;
        }
        Field d = d(obj, str);
        Object obj2 = d.get(obj);
        if (!(obj2 instanceof Object[])) {
            LogUtil.a("HAF_ReflectionUtils", "reduceFieldArray object is not array: ", obj2);
            return;
        }
        Object[] objArr2 = (Object[]) obj2;
        int length = objArr2.length - objArr.length;
        if (length < 0) {
            LogUtil.a("HAF_ReflectionUtils", "reduceFieldArray target length error: ", Integer.valueOf(length));
            return;
        }
        Object newInstance = Array.newInstance(objArr2.getClass().getComponentType(), length);
        if (newInstance instanceof Object[]) {
            Object[] objArr3 = (Object[]) newInstance;
            List asList = Arrays.asList(objArr);
            int i = 0;
            for (Object obj3 : objArr2) {
                if (!asList.contains(obj3)) {
                    if (i == objArr3.length) {
                        throw new IllegalArgumentException("extraElements exist data error.");
                    }
                    objArr3[i] = obj3;
                    i++;
                }
            }
            if (i != objArr3.length) {
                throw new IllegalArgumentException("extraElements exist data duplicate.");
            }
            d.set(obj, objArr3);
        }
    }
}
