package defpackage;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes7.dex */
public class trq {

    /* renamed from: a, reason: collision with root package name */
    private static Method f17356a = null;
    private static Class<?> b = null;
    private static final String d = "ReleaseLogUtil";

    private static void c() {
        if (b != null) {
            return;
        }
        try {
            b = Class.forName("com.huawei.wearengine.utills.WearEngineReflectUtil");
            a();
        } catch (ClassNotFoundException unused) {
            Log.e(d, "class ClassNotFoundException");
        }
    }

    private static void a() {
        Class<?> cls;
        if (f17356a == null && (cls = b) != null) {
            try {
                f17356a = cls.getDeclaredMethod("log", Integer.TYPE, String.class, Object[].class);
            } catch (NoSuchMethodException unused) {
                Log.e(d, "log NoSuchMethodException");
            }
        }
    }

    public static void d(int i, String str, Object... objArr) {
        Method method;
        c();
        Class<?> cls = b;
        if (cls == null || (method = f17356a) == null) {
            Log.w(d, "log clazz or logMethod is null");
            return;
        }
        try {
            method.invoke(cls, Integer.valueOf(i), "WearEngine_" + str, a(objArr));
        } catch (IllegalAccessException unused) {
            Log.e(d, "log IllegalAccessException");
        } catch (InvocationTargetException unused2) {
            Log.e(d, "log InvocationTargetException");
        }
    }

    private static Object[] a(Object... objArr) {
        Object[] objArr2 = new Object[objArr.length];
        System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
        return objArr2;
    }
}
