package com.huawei.openalliance.ad.utils;

import android.text.TextUtils;
import android.util.Base64;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.openalliance.ad.ho;
import com.huawei.operation.utils.Constants;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public abstract class ck {
    public static boolean c(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            ho.c("ReflectAPI", "class not found for %s", str);
            return false;
        }
    }

    public static boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
        } catch (Throwable unused) {
            ho.c("ReflectAPI", "class not found for %s", str);
        }
        return ClassLoader.getSystemClassLoader().loadClass(str) != null;
    }

    public static Field[] a(Class cls) {
        if (cls == null) {
            ho.c("ReflectAPI", "null clazz");
            return null;
        }
        if (cls == Object.class) {
            return null;
        }
        Field[] a2 = cls.getSuperclass() != null ? a(cls.getSuperclass()) : null;
        Field[] declaredFields = cls.getDeclaredFields();
        if (a2 == null || a2.length <= 0) {
            return declaredFields;
        }
        Field[] fieldArr = new Field[declaredFields.length + a2.length];
        System.arraycopy(a2, 0, fieldArr, 0, a2.length);
        System.arraycopy(declaredFields, 0, fieldArr, a2.length, declaredFields.length);
        return fieldArr;
    }

    public static boolean a(String str, String str2, Class<?>[] clsArr) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            Class.forName(str).getDeclaredMethod(str2, clsArr);
            return true;
        } catch (ClassNotFoundException unused) {
            ho.c("ReflectAPI", "class not found for %s", str);
            return false;
        } catch (NoSuchMethodException unused2) {
            ho.c("ReflectAPI", "method not found for %s", str2);
            return false;
        } catch (Throwable th) {
            ho.c("ReflectAPI", "isMethodAvailable %s", th.getClass().getSimpleName());
            return false;
        }
    }

    public static boolean a(String str) {
        return c(str) || b(str);
    }

    public static void a(Object obj, String str, boolean z) {
        StringBuilder sb;
        try {
            Method method = obj.getClass().getMethod(str, Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(obj, Boolean.valueOf(z));
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setBooleanValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setBooleanValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static void a(Object obj, String str, String str2, String str3) {
        StringBuilder sb;
        try {
            if (TypedValues.Custom.S_BOOLEAN.equals(str2)) {
                a(obj, str, Boolean.parseBoolean(str3));
            } else if ("int".equals(str2)) {
                a(obj, str, Integer.parseInt(str3));
            } else if ("long".equals(str2)) {
                a(obj, str, Long.parseLong(str3, 10));
            } else if ("String".equals(str2)) {
                a(obj, str, new String(Base64.decode(str3.getBytes("UTF-8"), 0), "UTF-8"));
            }
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setBooleanValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setBooleanValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static void a(Object obj, String str, String str2) {
        StringBuilder sb;
        try {
            Method method = obj.getClass().getMethod(str, String.class);
            method.setAccessible(true);
            method.invoke(obj, str2);
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setStringValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setStringValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static void a(Object obj, String str, long j) {
        StringBuilder sb;
        try {
            Method method = obj.getClass().getMethod(str, Long.TYPE);
            method.setAccessible(true);
            method.invoke(obj, Long.valueOf(j));
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setLongValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setLongValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static void a(Object obj, String str, int i) {
        StringBuilder sb;
        try {
            Method method = obj.getClass().getMethod(str, Integer.TYPE);
            method.setAccessible(true);
            method.invoke(obj, Integer.valueOf(i));
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setIntValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setIntValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static void a(Object obj, String str) {
        StringBuilder sb;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            for (String str2 : str.split("\\,")) {
                String[] split = str2.split("\\|");
                a(obj, split[0], split[1], split[2]);
            }
        } catch (RuntimeException e) {
            e = e;
            sb = new StringBuilder("setBooleanValue RuntimeException:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        } catch (Exception e2) {
            e = e2;
            sb = new StringBuilder("setBooleanValue Exception:");
            sb.append(e.getClass().getSimpleName());
            ho.c("ReflectAPI", sb.toString());
        }
    }

    public static Type a(int i, ParameterizedType parameterizedType) {
        if (parameterizedType == null) {
            return null;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        if (i >= 0 && i < actualTypeArguments.length) {
            Type type = actualTypeArguments[i];
            return type instanceof WildcardType ? ((WildcardType) type).getUpperBounds()[0] : type;
        }
        throw new IllegalArgumentException(parameterizedType + ": index " + i + " not in range [0," + actualTypeArguments.length + Constants.RIGHT_BRACKET_ONLY);
    }

    public static Field a(Field field, boolean z) {
        if (field == null) {
            ho.c("ReflectAPI", "null field");
            return null;
        }
        field.setAccessible(z);
        return field;
    }

    public static Object a(Object obj, String str, Class<?>[] clsArr, Object[] objArr) {
        String str2;
        if (obj == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Method method = obj.getClass().getMethod(str, clsArr);
            method.setAccessible(true);
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException unused) {
            str2 = "invokeMethod IllegalAccessException";
            ho.c("ReflectAPI", str2);
            return null;
        } catch (NoSuchMethodException unused2) {
            str2 = "invokeMethod NoSuchMethodException";
            ho.c("ReflectAPI", str2);
            return null;
        } catch (SecurityException unused3) {
            str2 = "invokeMethod SecurityException";
            ho.c("ReflectAPI", str2);
            return null;
        } catch (InvocationTargetException unused4) {
            str2 = "invokeMethod InvocationTargetException";
            ho.c("ReflectAPI", str2);
            return null;
        }
    }

    public static Class<?> a(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type).getRawType();
            if (rawType instanceof Class) {
                return (Class) rawType;
            }
            throw new IllegalArgumentException();
        }
        if (type instanceof GenericArrayType) {
            return Array.newInstance(a(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
        }
        if (type instanceof TypeVariable) {
            return Object.class;
        }
        if (type instanceof WildcardType) {
            return a(((WildcardType) type).getUpperBounds()[0]);
        }
        throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
    }

    public static Class a(Field field, int i) {
        Type[] actualTypeArguments;
        String str;
        Class<?> cls;
        if (field == null) {
            ho.c("ReflectAPI", "null field");
            return null;
        }
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType) || (actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments()) == null || actualTypeArguments.length <= i) {
            return null;
        }
        try {
            Type type = actualTypeArguments[i];
            if (type instanceof Class) {
                cls = (Class) type;
            } else {
                String obj = type.toString();
                int indexOf = obj.indexOf("class ");
                if (indexOf < 0) {
                    indexOf = 0;
                }
                int indexOf2 = obj.indexOf(HiDataFilter.DataFilterExpression.LESS_THAN);
                if (indexOf2 < 0) {
                    indexOf2 = obj.length();
                }
                cls = Class.forName(obj.substring(indexOf, indexOf2));
            }
            return cls;
        } catch (ClassNotFoundException unused) {
            str = "getType ClassNotFoundException";
            ho.c("ReflectAPI", str);
            return null;
        } catch (Exception unused2) {
            str = "getType Exception";
            ho.c("ReflectAPI", str);
            return null;
        }
    }

    public static Class a(Field field) {
        int i;
        if (field == null) {
            return null;
        }
        if (Map.class.isAssignableFrom(field.getType())) {
            i = 1;
        } else {
            if (!List.class.isAssignableFrom(field.getType())) {
                return null;
            }
            i = 0;
        }
        return a(field, i);
    }
}
