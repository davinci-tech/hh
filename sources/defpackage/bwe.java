package defpackage;

import android.text.TextUtils;
import com.huawei.hmf.services.Module;
import health.compact.a.LogUtil;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public final class bwe {

    /* loaded from: classes3.dex */
    static final class a {
        private static final Map<Class<?>, Object> b = new HashMap(28);

        static {
            c(Boolean.TYPE, Boolean.class, Boolean.FALSE);
            c(Byte.TYPE, Byte.class, (byte) 0);
            c(Character.TYPE, Character.class, (char) 0);
            c(Double.TYPE, Double.class, Double.valueOf(0.0d));
            c(Float.TYPE, Float.class, Float.valueOf(0.0f));
            c(Integer.TYPE, Integer.class, 0);
            c(Long.TYPE, Long.class, 0L);
            c(Short.TYPE, Short.class, (short) 0);
            c(Void.TYPE, Void.class, null);
            b(boolean[].class, new boolean[0]);
            b(byte[].class, new byte[0]);
            b(char[].class, new char[0]);
            b(double[].class, new double[0]);
            b(float[].class, new float[0]);
            b(int[].class, new int[0]);
            b(long[].class, new long[0]);
            b(short[].class, new short[0]);
            b(String.class, "");
            b(String[].class, new String[0]);
        }

        private static <T> void c(Class<T> cls, Class<T> cls2, T t) {
            b(cls, t);
            b(cls2, t);
        }

        private static <T> void b(Class<T> cls, T t) {
            b.put(cls, t);
        }

        public static boolean e(Class<?> cls) {
            return b.containsKey(cls);
        }

        public static <T> T c(Class<? extends T> cls) {
            return (T) b.get(cls);
        }
    }

    /* loaded from: classes3.dex */
    static class d implements InvocationHandler {
        private d() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            LogUtil.e("ServiceManagerImpl", "Dummy method ", method.getName(), " of ", b(obj.getClass()), " was invoked!");
            Object c = c(method.getReturnType());
            LogUtil.d("ServiceManagerImpl", "Dummy method ", method.getName(), " was invoked with result=", c);
            return c;
        }

        protected static Class<?> b(Class<?> cls) {
            Class<?>[] interfaces = cls.getInterfaces();
            return interfaces.length == 0 ? cls : interfaces[0];
        }

        protected static <T> T c(Class<? extends T> cls) {
            if (a.e(cls)) {
                return (T) a.c(cls);
            }
            if (List.class.isAssignableFrom(cls)) {
                return (T) Collections.emptyList();
            }
            if (Set.class.isAssignableFrom(cls)) {
                return (T) Collections.emptySet();
            }
            if (Map.class.isAssignableFrom(cls)) {
                return (T) Collections.emptyMap();
            }
            if (cls.isArray()) {
                return (T) Array.newInstance(cls.getComponentType(), 0);
            }
            LogUtil.d("ServiceManagerImpl", "Dummy method without default value: type=", cls.getName());
            return null;
        }
    }

    public static <T> T a(Module module, Class<T> cls, String str) {
        T t = TextUtils.isEmpty(str) ? (T) module.create(cls) : (T) module.create(cls, str);
        if (t == null) {
            LogUtil.e("ServiceManagerImpl", "Instantiate the implementation of ", cls, " in ", module.getName(), " failed");
            throw new IllegalStateException("Instantiate failed: " + cls);
        }
        LogUtil.d("ServiceManagerImpl", "Got instance of ", cls, " with result: ", t.getClass().getName(), "@", Integer.toHexString(t.hashCode()));
        return t;
    }

    public static <T> T a(Class<? extends T> cls) {
        if (!cls.isInterface()) {
            throw new UnsupportedOperationException("Can't create dummy instance of class: " + cls.getName());
        }
        return (T) a(cls, new d());
    }

    public static <T> T a(Class<? extends T> cls, InvocationHandler invocationHandler) {
        LogUtil.c("ServiceManagerImpl", "Creating new dummy instance of ", cls.getName(), " with handler=", invocationHandler);
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocationHandler);
    }
}
