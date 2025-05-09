package com.huawei.featureuserprofile.route.hwgpxmodel.xmlparser.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes3.dex */
public class ReflectUtils {
    private ReflectUtils() {
    }

    public static List<Field> getClassFields(Class<?> cls) {
        return Arrays.asList(cls.getDeclaredFields());
    }

    public static Object getFieldValue(Object obj, Field field) {
        Method fieldGetMethod = getFieldGetMethod(obj.getClass(), field);
        if (fieldGetMethod == null) {
            return null;
        }
        try {
            return fieldGetMethod.invoke(obj, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException unused) {
            return null;
        }
    }

    public static Method getFieldGetMethod(Class<?> cls, Field field) {
        if (cls == null || field == null) {
            throw new IllegalArgumentException("clazz or field cannot be null.");
        }
        Method methodQuietly = getMethodQuietly(cls, "get" + field.getName().substring(1));
        if (methodQuietly != null) {
            return methodQuietly;
        }
        return getMethodQuietly(cls, "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1));
    }

    private static Method getMethodQuietly(Class<?> cls, String str) {
        try {
            return cls.getMethod(str, new Class[0]);
        } catch (NoSuchMethodException | SecurityException unused) {
            return null;
        }
    }

    public static boolean isSimpleType(Class<?> cls) {
        return cls.isPrimitive() || cls == String.class || cls == Byte.class || cls == Short.class || cls == Integer.class || cls == Long.class || cls == Float.class || cls == Double.class || cls == Character.class || cls == Boolean.class || cls == Float.class;
    }
}
