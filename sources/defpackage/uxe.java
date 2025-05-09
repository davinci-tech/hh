package defpackage;

import android.os.Build;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/* loaded from: classes7.dex */
public class uxe {
    public static Method e(Method method, ClassLoader classLoader) throws ClassNotFoundException, NoSuchMethodException {
        return Class.forName(method.getDeclaringClass().getName(), true, classLoader).getDeclaredMethod(method.getName(), method.getParameterTypes());
    }

    public static <T> T b(Class<T> cls, InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return cls.cast(Proxy.newProxyInstance(uxe.class.getClassLoader(), new Class[]{cls}, invocationHandler));
    }

    public static InvocationHandler c(Object obj) {
        if (obj == null) {
            return null;
        }
        return new c(obj);
    }

    public static Object d(InvocationHandler invocationHandler) {
        if (invocationHandler == null) {
            return null;
        }
        return ((c) invocationHandler).d();
    }

    /* loaded from: classes10.dex */
    static class c implements InvocationHandler {
        private final Object d;

        public c(Object obj) {
            this.d = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                return uxe.e(method, this.d.getClass().getClassLoader()).invoke(this.d, objArr);
            } catch (InvocationTargetException e) {
                throw e.getTargetException();
            } catch (ReflectiveOperationException e2) {
                throw new RuntimeException("Reflection failed for method " + method, e2);
            }
        }

        public Object d() {
            return this.d;
        }
    }

    private static boolean a() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }

    public static boolean a(Collection<String> collection, String str) {
        if (!collection.contains(str)) {
            if (a()) {
                if (collection.contains(str + ":dev")) {
                }
            }
            return false;
        }
        return true;
    }
}
