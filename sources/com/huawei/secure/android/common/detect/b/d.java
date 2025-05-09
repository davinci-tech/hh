package com.huawei.secure.android.common.detect.b;

import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes6.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f8582a = "ReflectUtil";

    public static Object a(String str, String str2, Class<?>[] clsArr, Object[] objArr) throws Exception {
        Class<?> cls = Class.forName(str);
        return a(cls, cls.newInstance(), str2, clsArr, objArr);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object a2 = a("android.os.SystemProperties", "get", new Class[]{String.class}, new Object[]{str});
            if (a2 instanceof String) {
                return (String) a2;
            }
            return null;
        } catch (Exception e) {
            c.b(f8582a, "getSystemProperties, Excetion." + e.getMessage());
            return null;
        }
    }

    public static boolean a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return z;
        }
        try {
            Object a2 = a("android.os.SystemProperties", "getBoolean", new Class[]{String.class, Boolean.TYPE}, new Object[]{str, Boolean.valueOf(z)});
            if (a2 instanceof Boolean) {
                return ((Boolean) a2).booleanValue();
            }
        } catch (Exception e) {
            c.b(f8582a, "getSystemPropertyBoolean, Excetion." + e.getMessage());
        }
        return z;
    }

    private static Object a(Class<?> cls, Object obj, String str, Class<?>[] clsArr, Object[] objArr) throws Exception {
        a(cls, clsArr, objArr);
        try {
            try {
                try {
                    return cls.getMethod(str, clsArr).invoke(obj, objArr);
                } catch (InvocationTargetException e) {
                    c.b(f8582a, "InvocationTargetException" + e.getMessage());
                    return null;
                }
            } catch (IllegalAccessException e2) {
                c.b(f8582a, "IllegalAccessException" + e2.getMessage());
                return null;
            } catch (IllegalArgumentException e3) {
                c.b(f8582a, "IllegalArgumentException" + e3.getMessage());
                return null;
            }
        } catch (NoSuchMethodException e4) {
            c.b(f8582a, "NoSuchMethodException" + e4.getMessage());
            return null;
        }
    }

    private static void a(Class cls, Class[] clsArr, Object[] objArr) throws Exception {
        if (cls == null) {
            throw new Exception("class is null in staticFun");
        }
        if (clsArr == null) {
            if (objArr != null) {
                throw new Exception("paramsType is null, but params is not null");
            }
        } else {
            if (objArr != null) {
                if (clsArr.length == objArr.length) {
                    return;
                }
                throw new Exception("paramsType len:" + clsArr.length + " should equal params.len:" + objArr.length);
            }
            throw new Exception("paramsType or params should be same");
        }
    }

    public static int a(Class<?> cls, String str, int i) {
        try {
            return cls.getField(str).getInt(null);
        } catch (IllegalAccessException e) {
            c.b(f8582a, "IllegalAccessException err:" + e.getMessage());
            return i;
        } catch (IllegalArgumentException e2) {
            c.b(f8582a, "IllegalArgumentException err:" + e2.getMessage());
            return i;
        } catch (NoSuchFieldException e3) {
            c.b(f8582a, "NoSuchFieldException err:" + e3.getMessage());
            return i;
        }
    }

    public static int a(String str, String str2, int i) {
        try {
            return a(Class.forName(str), str2, i);
        } catch (Exception e) {
            c.b(f8582a, "getIntFiled exception" + e.getMessage());
            return i;
        }
    }

    public static Object a(Class<?> cls, String str, Object obj) {
        try {
            return cls.getField(str).get(null);
        } catch (IllegalAccessException e) {
            c.b(f8582a, "IllegalAccessException" + e.getMessage());
            return obj;
        } catch (IllegalArgumentException e2) {
            c.b(f8582a, "IllegalArgumentException" + e2.getMessage());
            return obj;
        } catch (NoSuchFieldException e3) {
            c.b(f8582a, "NoSuchFieldException" + e3.getMessage());
            return obj;
        } catch (Exception e4) {
            c.b(f8582a, "Exception" + e4.getMessage());
            return obj;
        }
    }

    public static Object a(String str, String str2, Object obj) {
        try {
            return Class.forName(str).getField(str2).get(null);
        } catch (ClassNotFoundException e) {
            c.b(f8582a, "ClassNotFoundException" + e.getMessage());
            return obj;
        } catch (IllegalAccessException e2) {
            c.b(f8582a, "IllegalAccessException" + e2.getMessage());
            return obj;
        } catch (IllegalArgumentException e3) {
            c.b(f8582a, "IllegalArgumentException" + e3.getMessage());
            return obj;
        } catch (NoSuchFieldException e4) {
            c.b(f8582a, "NoSuchMethodException" + e4.getMessage());
            return obj;
        } catch (Exception e5) {
            c.b(f8582a, "Exception" + e5.getMessage());
            return obj;
        }
    }

    public static Class a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            c.b(f8582a, "ClassNotFoundException" + e.getMessage());
            return null;
        } catch (Exception e2) {
            c.b(f8582a, "Exception" + e2.getMessage());
            return null;
        } catch (Throwable th) {
            c.b(f8582a, "Throwable" + th.getMessage());
            return null;
        }
    }
}
