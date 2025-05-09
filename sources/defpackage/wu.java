package defpackage;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes2.dex */
public final class wu {

    /* renamed from: a, reason: collision with root package name */
    private static Class<?> f17736a;
    private static Method b;
    private static Method d;
    private static Method e;

    public static boolean c(String str, boolean z) {
        try {
            Class<?> cls = f17736a;
            if (cls == null) {
                cls = Class.forName("android.os.SystemProperties");
                f17736a = cls;
            }
            Method method = b;
            if (method == null) {
                method = cls.getDeclaredMethod("getBoolean", String.class, Boolean.TYPE);
                b = method;
            }
            return ((Boolean) method.invoke(null, str, Boolean.valueOf(z))).booleanValue();
        } catch (ClassNotFoundException unused) {
            e("ReflectSystemProperties", "ClassNotFoundException");
            return z;
        } catch (IllegalAccessException unused2) {
            e("ReflectSystemProperties", "IllegalAccessException");
            return z;
        } catch (IllegalArgumentException unused3) {
            e("ReflectSystemProperties", "IllegalArgumentException");
            return z;
        } catch (NoSuchMethodException unused4) {
            e("ReflectSystemProperties", "NoSuchMethodException");
            return z;
        } catch (SecurityException unused5) {
            e("ReflectSystemProperties", "SecurityException");
            return z;
        } catch (InvocationTargetException unused6) {
            e("ReflectSystemProperties", "InvocationTargetException");
            return z;
        }
    }

    public static String d(String str, String str2) {
        try {
            Class<?> cls = f17736a;
            if (cls == null) {
                cls = Class.forName("android.os.SystemProperties");
                f17736a = cls;
            }
            Method method = d;
            if (method == null) {
                method = cls.getDeclaredMethod("get", String.class, String.class);
                d = method;
            }
            return String.valueOf(method.invoke(null, str, str2));
        } catch (ClassNotFoundException unused) {
            e("ReflectSystemProperties", "ClassNotFoundException");
            return str2;
        } catch (IllegalAccessException unused2) {
            e("ReflectSystemProperties", "IllegalAccessException");
            return str2;
        } catch (IllegalArgumentException unused3) {
            e("ReflectSystemProperties", "IllegalArgumentException");
            return str2;
        } catch (NoSuchMethodException unused4) {
            e("ReflectSystemProperties", "NoSuchMethodException");
            return str2;
        } catch (SecurityException unused5) {
            e("ReflectSystemProperties", "SecurityException");
            return str2;
        } catch (InvocationTargetException unused6) {
            e("ReflectSystemProperties", "InvocationTargetException");
            return str2;
        }
    }

    public static int b(String str, int i) {
        try {
            Class<?> cls = f17736a;
            if (cls == null) {
                cls = Class.forName("android.os.SystemProperties");
                f17736a = cls;
            }
            Method method = e;
            if (method == null) {
                method = cls.getDeclaredMethod("getInt", String.class, Integer.TYPE);
                e = method;
            }
            return ((Integer) method.invoke(null, str, Integer.valueOf(i))).intValue();
        } catch (ClassNotFoundException unused) {
            e("ReflectSystemProperties", "ClassNotFoundException");
            return i;
        } catch (IllegalAccessException unused2) {
            e("ReflectSystemProperties", "IllegalAccessException");
            return i;
        } catch (IllegalArgumentException unused3) {
            e("ReflectSystemProperties", "IllegalArgumentException");
            return i;
        } catch (NoSuchMethodException unused4) {
            e("ReflectSystemProperties", "NoSuchMethodException");
            return i;
        } catch (SecurityException unused5) {
            e("ReflectSystemProperties", "SecurityException");
            return i;
        } catch (InvocationTargetException unused6) {
            e("ReflectSystemProperties", "InvocationTargetException");
            return i;
        }
    }

    public static void e(String str, String str2) {
        Log.e(str, a(str2));
    }

    private static String a(String str) {
        if (str != null && str.length() > 1000) {
            str = str.substring(0, 1000);
            Log.w("ReflectSystemProperties", "msg length bigger than 1000");
        }
        StringBuilder sb = new StringBuilder(str != null ? 50 + str.length() : 50);
        sb.append(str);
        return sb.toString();
    }
}
